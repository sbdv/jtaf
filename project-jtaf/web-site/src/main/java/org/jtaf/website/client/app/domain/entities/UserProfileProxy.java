package org.jtaf.website.client.app.domain.entities;

import org.jtaf.website.server.app.domain.entities.UserProfile;
import org.jtaf.website.server.app.facade.entities.UserProfileLocator;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;


@ProxyFor(value = UserProfile.class, locator = UserProfileLocator.class)
public interface UserProfileProxy extends EntityProxy {

	public String getId();

	public String getGivenName();

	public String getLastName();

	public String getPseudo();

	public String getAvatar();

	public String getMail();
//	
//	public void setId(String id);
//
//	public void setGivenName(String givenName);
//
//	public void setLastName(String lastname);
//
//	public void setPseudo(String pseudo);
//
//	public void setAvatar(String avatar);
//
//	public void setMail(String mail);
}
