package org.jtaf.website.security;

import org.jtaf.website.server.domain.entities.UserProfile;
import org.jtaf.website.server.services.ProfileServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.UniformInterfaceException;

@Component
public class JtafAuthenticationManager implements AuthenticationManager {

    @Autowired
    private ProfileServices profileServices;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserProfile userProfile = null;
        try {
            userProfile = profileServices.userProfileInformation(authentication.getPrincipal().toString());
        }
        catch (ClientHandlerException e) {
            throw new JtafAuthenticationException(e.getMessage(), e);
        }
        catch (UniformInterfaceException ue) {
            throw new JtafAuthenticationException(ue.getMessage(), ue);
        }

        UserProfile userInBdd = profileServices.userProfileInformation(userProfile.getId());
        if (userInBdd == null) {
            userProfile = profileServices.createUserProfile(userProfile);
        }

        // TODO ajouter l'utilisateur
        UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken("", "", null);
        user.setDetails(userProfile);
        return user;
    }

    protected void setProfileServices(ProfileServices profileServices) {
        this.profileServices = profileServices;
    }

}
