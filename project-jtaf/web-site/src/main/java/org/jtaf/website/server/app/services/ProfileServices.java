package org.jtaf.website.server.app.services;

import java.math.BigInteger;

import org.jtaf.website.server.app.domain.entities.UserProfile;

public interface ProfileServices {

    /**
     * Récupère les informations à partir du service google
     * 
     * @param token
     * @return userProfile
     */
    UserProfile userProfileInformation(String token);

    /**
     * Récupère les informations à partir de l'user en session
     * 
     * @return userProfile
     */
    UserProfile userProfileInformation();

    /**
     * Récupère les informations à partir de la bdd jtaf
     * 
     * @param id
     *            de l'user
     * @return userProfile
     */
    UserProfile userProfileInformation(BigInteger id);

    /**
     * Inscrit ou merge un nouvel utilisateur jtaf
     * 
     * @param userProfile
     */
    UserProfile createOrUpdateUserProfile(UserProfile userProfile);
}
