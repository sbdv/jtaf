package org.jtaf.website.server.services;

import org.springframework.stereotype.Service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

@Service
public class ProfilServicesImpl implements ProfilServices {

    @Override
    public String profilInformation(String token) {
        Client client = Client.create();

        WebResource webResource = client.resource("https://www.googleapis.com/oauth2/v1/userinfo").queryParam(
                "access_token", token);

        System.out.println(webResource.getURI().toString());
        String output = webResource.get(String.class);

        System.out.println("Output from Server .... \n");
        System.out.println(output);
        return output;
    }

}
