package org.jtaf.website.server.services;

import org.jtaf.website.server.domain.entities.UserProfile;

public interface ProfileServices {

    /**
     * Récupère les informations
     * 
     * @param token
     * @return
     */
    UserProfile profilInformation(String token);
}
