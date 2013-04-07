package org.jtaf.website.client.security.domain.access;

/**
 * Interface de sécurité des callBack
 * 
 * @author mbellang
 * 
 */
public interface SecurityCallbackHandler {

    /**
     * Gestion de l'erreur d'authentication (timeout)
     * 
     * @param externalLoginUrl
     */
    void onAuthorizationExpected(final String externalLoginUrl);

    /**
     * Gestion de restriction d'accès
     * 
     */
    void onAccessDenied();

}
