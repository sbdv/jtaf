package org.jtaf.website.security;

import static org.mockito.Mockito.when;

import java.math.BigInteger;

import javax.ws.rs.core.MultivaluedMap;

import org.jtaf.website.JtafTest;
import org.jtaf.website.server.domain.entities.UserProfile;
import org.jtaf.website.server.domain.repository.UserProfileRepository;
import org.jtaf.website.server.domain.webservices.RESTRequestMethods;
import org.jtaf.website.server.domain.webservices.RESTRequestMethodsImpl.GoogleInfoApi;
import org.jtaf.website.server.services.ProfileServicesImpl;
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
        when(profileServicesMock.userProfileInformation(Mockito.anyString())).thenThrow(
                new ClientHandlerException("Erreur http quelconque"));
        authenticationManager.authenticate(buildDummyAuthentication());
    }

    @Test(expected = JtafAuthenticationException.class)
    public void deserializeErrorOnRequestProfileTest() {
        // test sur les erreurs de traitement de la réponse. Exemple : le type de retour attendu n'est pas le bon
        authenticationManager.setProfileServices(profileServicesMock);
        when(profileServicesMock.userProfileInformation(Mockito.anyString())).thenThrow(
                new UniformInterfaceException("La réponse n'a pu être traité par le client", null, false));
        authenticationManager.authenticate(buildDummyAuthentication());
    }

    @Test
    public void insertNewProfileTest() {
        // init variable
        UserProfile user = buildDummyProfile();
        String pseudoExpected = user.getGivenName().concat(".").concat(user.getLastName());
        ProfileServicesImpl profileService = new ProfileServicesImpl();
        profileService.setRESTRequestMethods(restRequestMock);
        profileService.setUserProfileRepository(userProfileRepository);
        authenticationManager.setProfileServices(profileService);
        MultivaluedMap<String, String> params = new StringKeyStringValueIgnoreCaseMultivaluedMap();
        params.add(GoogleInfoApi.ACCESS_TOKEN, "bidon");
        // init mock
        when(restRequestMock.get(GoogleInfoApi.GOOGLE_URI, UserProfile.class, params)).thenReturn(user);
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
    }

    private Authentication buildDummyAuthentication() {
        Authentication authentiation = new UsernamePasswordAuthenticationToken("bidon", "bidon");
        return authentiation;
    }

    private UserProfile buildDummyProfile() {
        UserProfile userProfile = new UserProfile();
        userProfile.setId(BigInteger.ONE);
        userProfile.setGivenName("The");
        userProfile.setLastName("Boss");
        userProfile.setMail("theBoss@gmail.com");
        userProfile.setAvatar("theBossPhoto");
        userProfile.setPseudo(null);
        return userProfile;
    }
}
