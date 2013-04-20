package org.jtaf.website.server.security;

import static org.mockito.Mockito.when;

import javax.ws.rs.core.MultivaluedMap;

import org.jtaf.website.JtafTest;
import org.jtaf.website.server.app.domain.entities.UserProfile;
import org.jtaf.website.server.app.domain.repository.UserProfileRepository;
import org.jtaf.website.server.app.domain.webservices.RESTRequestMethods;
import org.jtaf.website.server.app.domain.webservices.RESTRequestMethodsImpl.GoogleInfoApi;
import org.jtaf.website.server.app.domain.webservices.UserGoogle;
import org.jtaf.website.server.app.exception.JtafAuthenticationException;
import org.jtaf.website.server.app.services.ProfileServicesImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.core.util.StringKeyStringValueIgnoreCaseMultivaluedMap;

public class AuthenticationTest extends JtafTest {

    JtafAuthenticationManager authenticationManager;
    @Mock
    ProfileServicesImpl profileServicesMock;
    @Mock
    RESTRequestMethods restRequestMock;
    @Autowired
    private UserProfileRepository userProfileRepository;

    @Before
    public void setUp() {
        authenticationManager = new JtafAuthenticationManager();
    }

    @After
    public void tearDown() {
        userProfileRepository.deleteAll();
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    @Test(expected = JtafAuthenticationException.class)
    public void httpErrorOnRequestProfileTest() {
        // test sur les erreurs de connection au service google ClientHandlerException
        authenticationManager.setProfileServices(profileServicesMock);
        when(profileServicesMock.googleProfileInformation(Mockito.anyString())).thenThrow(
                new ClientHandlerException("Erreur http quelconque"));
        authenticationManager.authenticate(buildDummyAuthentication());
    }

    @Test(expected = JtafAuthenticationException.class)
    public void deserializeErrorOnRequestProfileTest() {
        // test sur les erreurs de traitement de la réponse. Exemple : le type de retour attendu n'est pas le bon
        authenticationManager.setProfileServices(profileServicesMock);
        when(profileServicesMock.googleProfileInformation(Mockito.anyString())).thenThrow(
                new UniformInterfaceException("La réponse n'a pu être traité par le client", null, false));
        authenticationManager.authenticate(buildDummyAuthentication());
    }

    @Test
    public void insertNewProfileTest() {
        // init variable
    	UserGoogle user = buildDummyProfile();
        String pseudoExpected = user.getGivenName().concat(".").concat(user.getLastName());
        ProfileServicesImpl profileService = new ProfileServicesImpl();
        profileService.setRESTRequestMethods(restRequestMock);
        profileService.setUserProfileRepository(userProfileRepository);
        authenticationManager.setProfileServices(profileService);
        MultivaluedMap<String, String> params = new StringKeyStringValueIgnoreCaseMultivaluedMap();
        params.add(GoogleInfoApi.ACCESS_TOKEN, "bidon");
        // init mock
        when(restRequestMock.get(GoogleInfoApi.GOOGLE_URI, UserGoogle.class, params)).thenReturn(user);
        // run test
        Authentication authToTest = authenticationManager.authenticate(buildDummyAuthentication());
        // check result
        // force l'insertion du user dans le contexte spring pour tester
        SecurityContextHolder.getContext().setAuthentication(authToTest);
        UserProfile userToTest = profileService.userProfileInformation();
        Assert.assertNotNull(userToTest);
        Assert.assertNotNull(userToTest.getId());
        Assert.assertNotNull(userToTest.getMail());
        Assert.assertNotNull(userToTest.getLastName());
        Assert.assertNotNull(userToTest.getGivenName());
        Assert.assertEquals(user.getAvatar(), userToTest.getAvatar());
        Assert.assertEquals(pseudoExpected, userToTest.getPseudo());
        // test des data en bdd
        UserProfile userBdd = userProfileRepository.findOne("1");
        Assert.assertNotNull(userBdd);
        Assert.assertNotNull(userToTest.getMail());
        Assert.assertNotNull(userToTest.getLastName());
        Assert.assertNotNull(userToTest.getGivenName());
        Assert.assertNotNull(userBdd.getAvatar());
        Assert.assertNotNull(userBdd.getPseudo());
    }

    @Test
    public void updateProfileAlreadyExistTest() {
        // init variable
    	UserGoogle user = buildRealProfile();
        UserProfile userToTest = userProfileRepository.findOne("10");
        Assert.assertFalse(user.getAvatar().equals(userToTest.getAvatar()));
        Assert.assertFalse(userToTest.getPseudo().equals(user.getPseudo()));
        Assert.assertFalse(userToTest.getGivenName().equals(user.getGivenName()));
        Assert.assertFalse(userToTest.getLastName().equals(user.getLastName()));
        Assert.assertEquals(user.getId(), userToTest.getId());
        ProfileServicesImpl profileService = new ProfileServicesImpl();
        profileService.setRESTRequestMethods(restRequestMock);
        profileService.setUserProfileRepository(userProfileRepository);
        authenticationManager.setProfileServices(profileService);
        MultivaluedMap<String, String> params = new StringKeyStringValueIgnoreCaseMultivaluedMap();
        params.add(GoogleInfoApi.ACCESS_TOKEN, "bidon");
        // init mock
        when(restRequestMock.get(GoogleInfoApi.GOOGLE_URI, UserGoogle.class, params)).thenReturn(user);
        // run test
        Authentication authToTest = authenticationManager.authenticate(buildDummyAuthentication());
        // check result
        // force l'insertion du user dans le contexte spring pour tester
        SecurityContextHolder.getContext().setAuthentication(authToTest);
        userToTest = profileService.userProfileInformation();
        Assert.assertEquals(user.getAvatar(), userToTest.getAvatar());
        Assert.assertEquals(user.getId(), userToTest.getId());
        Assert.assertEquals(user.getGivenName(), userToTest.getGivenName());
        Assert.assertEquals(user.getLastName(), userToTest.getLastName());
        Assert.assertNotNull(userToTest.getPseudo());
        // test des data en bdd
        UserProfile userBdd = userProfileRepository.findOne(user.getId());
        Assert.assertNotNull(userBdd);
        Assert.assertEquals(user.getMail(), userBdd.getMail());
        Assert.assertEquals(user.getAvatar(), userBdd.getAvatar());
        Assert.assertEquals(user.getGivenName(), userBdd.getGivenName());
        Assert.assertEquals(user.getLastName(), userBdd.getLastName());
        Assert.assertNotNull(userBdd.getPseudo());
    }

    private Authentication buildDummyAuthentication() {
        Authentication authentiation = new UsernamePasswordAuthenticationToken("bidon", "bidon");
        return authentiation;
    }

    private UserGoogle buildDummyProfile() {
    	UserGoogle userProfile = new UserGoogle();
        userProfile.setId("1");
        userProfile.setGivenName("The");
        userProfile.setLastName("Boss");
        userProfile.setMail("theBoss@gmail.com");
        userProfile.setAvatar("theBossPhoto");
        return userProfile;
    }

    private UserGoogle buildRealProfile() {
    	UserGoogle userProfile = new UserGoogle();
        userProfile.setId("10");
        userProfile.setGivenName("The");
        userProfile.setLastName("BigBoss");
        userProfile.setMail("theBigBoss@gmail.com");
        userProfile.setAvatar("theBigBossPhoto");
        return userProfile;
    }
}
