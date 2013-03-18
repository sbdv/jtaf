package org.jtaf.website.security;

/**
 * Interface des messages sécurisés
 * 
 * @author mbellang
 * 
 */
public interface SecurityResponseMessage {

    /**
     * Retourne le status du message
     * 
     * @return code
     */
    int getStatus();

    /**
     * Défini le status du message
     * 
     * @return code
     */
    void setStatus(int status);

    /**
     * Récupère le message
     * 
     * @return message
     */
    String getMessage();

    /**
     * Défini le message
     * 
     * @param message
     */
    void setMessage(String message);

    /**
     * Récupère la page de login
     * 
     * @return
     */
    String getLoginFormUrl();

    /**
     * Défini la page de login
     * 
     * @param loginFormUrl
     */
    void setLoginFormUrl(String loginFormUrl);

}