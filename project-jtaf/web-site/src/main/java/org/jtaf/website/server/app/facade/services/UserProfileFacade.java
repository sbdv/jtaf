package org.jtaf.website.server.app.facade.services;

import org.jtaf.website.server.app.domain.entities.UserProfile;
import org.jtaf.website.server.app.services.ProfileServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

@Component
public class UserProfileFacade {

    @Autowired
    private ProfileServices profilServices;

    @Secured(value = { "ROLE_USER" })
    public UserProfile userProfileInformation() {
        return profilServices.userProfileInformation();
    }
}
