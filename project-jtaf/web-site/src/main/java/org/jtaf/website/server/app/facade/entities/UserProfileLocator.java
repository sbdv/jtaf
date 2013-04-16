package org.jtaf.website.server.app.facade.entities;

import java.math.BigInteger;

import org.jtaf.website.server.app.domain.entities.UserProfile;
import org.jtaf.website.server.app.services.ProfileServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserProfileLocator extends EntityLocator<UserProfile> {

    @Autowired
    private ProfileServices profileServices;

    @Override
    public UserProfile find(Class<? extends UserProfile> clazz, BigInteger id) {
        return profileServices.userProfileInformation(id);
    }

    @Override
    public Class<UserProfile> getDomainType() {
        return UserProfile.class;
    }

}
