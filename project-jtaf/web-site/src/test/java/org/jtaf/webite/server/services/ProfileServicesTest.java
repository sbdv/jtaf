package org.jtaf.webite.server.services;

import junit.framework.Assert;

import org.jtaf.website.JtafTest;
import org.jtaf.website.server.domain.repository.UserProfileRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ProfileServicesTest extends JtafTest {

    @Autowired
    UserProfileRepository userProfileRepository;

    @Test
    public void userProfilTest() {
        boolean test = userProfileRepository.findAll().iterator().hasNext();
        Assert.assertFalse(test);
    }
}
