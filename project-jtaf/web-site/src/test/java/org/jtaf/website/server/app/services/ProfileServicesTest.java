package org.jtaf.website.server.app.services;

import junit.framework.Assert;

import org.jtaf.website.JtafTest;
import org.jtaf.website.server.app.domain.entities.UserProfile;
import org.jtaf.website.server.app.domain.repository.UserProfileRepository;
import org.jtaf.website.server.app.domain.webservices.RESTRequestMethods;
import org.junit.Before;
import org.junit.Ignore;
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
    @Ignore
    public void userProfilTest() {
        boolean test = userProfileRepository.findAll().iterator().hasNext();
        Assert.assertFalse(test);
        UserProfile userProfile = userProfileRepository.findOne("1");
        Assert.assertNull(userProfile);
    }
}
