package org.jtaf.website.client.app.domain.access;

import com.google.web.bindery.requestfactory.shared.RequestFactory;


/**
 * RequestFactory des request authentifié
 * 
 * @author mbellang
 * 
 */
public interface JtafRequestFactory extends RequestFactory {

    UserProfileRequest getUserProfileRequest();

}
