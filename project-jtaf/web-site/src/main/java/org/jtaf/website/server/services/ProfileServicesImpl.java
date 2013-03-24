package org.jtaf.website.server.services;

import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.jtaf.website.server.domain.entities.UserProfile;
import org.springframework.stereotype.Service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

@Service
public class ProfileServicesImpl implements ProfileServices {

    @Override
    public UserProfile profilInformation(String token) {
    	
    	ClientConfig cc = new DefaultClientConfig();
    	  cc.getClasses().add(JacksonJsonProvider.class);
    	  Client client = Client.create(cc);
    	 
    	  WebResource resource = client.resource("https://www.googleapis.com/oauth2/v1/userinfo").queryParam(
                  "access_token", token);
    	  resource.accept(MediaType.APPLICATION_JSON_TYPE);
    	  return resource.get(UserProfile.class);
    }
    
}
