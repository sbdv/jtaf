package org.jtaf.website.server.domain.webservices;

import javax.ws.rs.core.MultivaluedMap;

public interface RESTRequestMethods {

    /**
     * Méthode générique d'appel GET à un web service
     * 
     * @param <T>
     * @param uri
     * @param clazz
     *            de l'objet
     * @param params
     *            de la requête
     * @return objet demandé
     */
    <T> T get(String uri, Class<T> clazz, MultivaluedMap<String, String> params);

}
