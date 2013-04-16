package org.jtaf.website.client.app.domain.access;

import org.jtaf.website.client.app.domain.entities.UserProfileProxy;
import org.jtaf.website.server.app.facade.services.ServicesLocator;
import org.jtaf.website.server.app.facade.services.UserProfileFacade;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

@Service(locator = ServicesLocator.class, value = UserProfileFacade.class)
public interface UserProfileRequest extends RequestContext {

    Request<UserProfileProxy> userProfileInformation();
}
