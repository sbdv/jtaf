package org.jtaf.website.rf;

/**
 * Interface de sécurité des callBack
 * 
 * @author mbellang
 * 
 */
public interface ISecurityCallbackHandler {

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
