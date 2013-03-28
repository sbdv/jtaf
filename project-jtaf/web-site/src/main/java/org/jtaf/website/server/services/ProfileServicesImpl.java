package org.jtaf.website.server.services;

import javax.ws.rs.core.MultivaluedMap;

import org.jtaf.website.server.domain.entities.UserProfile;
import org.jtaf.website.server.domain.webservices.RESTRequestMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.jersey.core.util.StringKeyStringValueIgnoreCaseMultivaluedMap;

@Service
public class ProfileServicesImpl implements ProfileServices {

    @Autowired
    private RESTRequestMethods restRequestMethods;

    protected final String googleUri = "https://www.googleapis.com/oauth2/v1/userinfo";

    @Override
    public UserProfile profilInformation(String token) {
        MultivaluedMap<String, String> params = new StringKeyStringValueIgnoreCaseMultivaluedMap();
        params.add("access_token", token);
        return restRequestMethods.get(googleUri, UserProfile.class, params);
    }

    protected void setRESTRequestMethods(RESTRequestMethods restRequestMethods) {
        this.restRequestMethods = restRequestMethods;
    }
}
