package org.jtaf.website.client.app.domain.entities;

import org.jtaf.website.server.app.domain.entities.UserProfile;
import org.jtaf.website.server.app.facade.entities.UserProfileLocator;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(value = UserProfile.class, locator = UserProfileLocator.class)
public interface UserProfileProxy extends EntityProxy {

    public String getGivenName();

    public String getLastName();

    public String getPseudo();

    public String getAvatar();

    public String getMail();
}
