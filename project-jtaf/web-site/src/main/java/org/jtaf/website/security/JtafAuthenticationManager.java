package org.jtaf.website.security;

import org.jtaf.website.server.domain.entities.UserProfile;
import org.jtaf.website.server.services.ProfileServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class JtafAuthenticationManager implements AuthenticationManager {

    @Autowired
    ProfileServices profileServices;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserProfile userProfile = profileServices.profilInformation(authentication.getPrincipal().toString());
        
        //TODO ajouter l'utilisateur
        UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken("", "", null);
        user.setDetails(userProfile);
        return user;
    }

}
