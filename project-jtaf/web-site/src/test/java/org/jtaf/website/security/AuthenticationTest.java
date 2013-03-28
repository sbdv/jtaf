package org.jtaf.website.security;

import org.jtaf.website.JtafTest;
import org.junit.Before;
import org.junit.Test;

import com.sun.jersey.api.client.ClientHandlerException;

public class AuthenticationTest extends JtafTest {

    @Before
    public void setUp() {

    }

    @Test(expected = ClientHandlerException.class)
    public void requestGoogleTest() {
        // ClientHandlerException Connection timed out: connect, derrière un par feu par exemple
        // profileService.setRESTRequestMethods(restMock);
        // when(restMock.get(profileService.googleUri, UserProfile.class, null)).thenThrow(new ConnectException());
        // profileService.profilInformation("testConnectionTimeOut");
    }
}
