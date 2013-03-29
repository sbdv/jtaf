package org.jtaf.website.server.domain.webservices;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.springframework.stereotype.Service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.util.StringKeyStringValueIgnoreCaseMultivaluedMap;

@Service
public class RESTRequestMethodsImpl implements RESTRequestMethods {

    @Override
    public <T> T get(String uri, Class<T> clazz, MultivaluedMap<String, String> params) {
        ClientConfig cc = new DefaultClientConfig(JacksonJsonProvider.class);
        Client client = Client.create(cc);
        if (params == null) {
            params = new StringKeyStringValueIgnoreCaseMultivaluedMap();
        }
        WebResource webResource = client.resource(uri).queryParams(params);
        webResource.accept(MediaType.APPLICATION_JSON_TYPE);
        return webResource.get(clazz);
    }

    public static class GoogleInfoApi {

        public static final String GOOGLE_URI = "https://www.googleapis.com/oauth2/v1/userinfo";
        public static final String ACCESS_TOKEN = "access_token";

        private GoogleInfoApi() {

        }
    }

}
