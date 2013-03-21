package org.jtaf.website.security;

import org.jtaf.website.server.services.ProfilServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class JtafAuthenticationManager implements AuthenticationManager {

    @Autowired
    ProfilServices profilServices;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println(authentication.getPrincipal());
        profilServices.profilInformation(authentication.getPrincipal().toString());
        return authentication;
    }

}
