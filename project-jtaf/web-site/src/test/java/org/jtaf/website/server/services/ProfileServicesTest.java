package org.jtaf.website.server.services;

import java.math.BigInteger;

import junit.framework.Assert;

import org.jtaf.website.JtafTest;
import org.jtaf.website.server.domain.entities.UserProfile;
import org.jtaf.website.server.domain.repository.UserProfileRepository;
import org.jtaf.website.server.domain.webservices.RESTRequestMethods;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

public class ProfileServicesTest extends JtafTest {

    @Autowired
    UserProfileRepository userProfileRepository;
    @Autowired
    ProfileServices profileService;

    @Mock
    RESTRequestMethods restMock;

    @Before
    public void setUp() {
        // profileService = new ProfileServicesImpl();
    }

    @Test
    public void userProfilTest() {
        boolean test = userProfileRepository.findAll().iterator().hasNext();
        Assert.assertFalse(test);
        UserProfile userProfile = userProfileRepository.findOne(BigInteger.ONE);
        Assert.assertNull(userProfile);
    }
}
