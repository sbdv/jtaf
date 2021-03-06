package org.jtaf.website.server.app.services;

import javax.ws.rs.core.MultivaluedMap;

import org.jtaf.website.server.app.domain.entities.UserProfile;
import org.jtaf.website.server.app.domain.repository.UserProfileRepository;
import org.jtaf.website.server.app.domain.webservices.RESTRequestMethods;
import org.jtaf.website.server.app.domain.webservices.RESTRequestMethodsImpl.GoogleInfoApi;
import org.jtaf.website.server.app.domain.webservices.UserGoogle;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.jersey.core.util.StringKeyStringValueIgnoreCaseMultivaluedMap;

@Service
public class ProfileServicesImpl implements ProfileServices {

    @Autowired
    private RESTRequestMethods restRequestMethods;
    @Autowired
    private UserProfileRepository userProfileRepository;

    @Override
    public UserProfile googleProfileInformation(String token) {
        // TODO test d'intégration
        MultivaluedMap<String, String> params = new StringKeyStringValueIgnoreCaseMultivaluedMap();
        params.add(GoogleInfoApi.ACCESS_TOKEN, token);
        UserGoogle userGoogle = restRequestMethods.get(GoogleInfoApi.GOOGLE_URI, UserGoogle.class, params);
        UserProfile userProfile = new UserProfile();
        BeanUtils.copyProperties(userGoogle, userProfile);
        return userProfile;
    }

    @Override
    public UserProfile userProfileInformation() {
        Authentication authenticateUser = SecurityContextHolder.getContext().getAuthentication();
        UserProfile user = null;
        if (authenticateUser.isAuthenticated() && authenticateUser.getDetails() instanceof UserProfile) {
            user = (UserProfile) SecurityContextHolder.getContext().getAuthentication().getDetails();
        }
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public UserProfile userProfileInformation(String id) {
        return userProfileRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = false)
    public UserProfile createOrUpdateUserProfile(UserProfile userProfile) {
        UserProfile userInBdd = userProfileInformation(userProfile.getId());
        if (userInBdd == null) {
            userInBdd = new UserProfile();
            userInBdd.setPseudo(userProfile.getGivenName().concat(".").concat(userProfile.getLastName()));
        }
        mergeUserProfile(userProfile, userInBdd);
        return userProfileRepository.save(userInBdd);
    }

    private void mergeUserProfile(UserProfile userGoogle, UserProfile userBdd) {
        BeanUtils.copyProperties(userGoogle, userBdd, NOT_MERGE_PROFILE_PROPERTIES);
    }

    private static final String[] NOT_MERGE_PROFILE_PROPERTIES = { "pseudo" };

    public void setUserProfileRepository(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    public void setRESTRequestMethods(RESTRequestMethods restRequestMethods) {
        this.restRequestMethods = restRequestMethods;
    }
}
