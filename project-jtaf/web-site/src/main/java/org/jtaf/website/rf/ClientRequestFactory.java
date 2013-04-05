package org.jtaf.website.rf;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.requestfactory.shared.RequestFactory;

/**
 * RequestFactory du client TxplanOnline
 * 
 * @author mbellang
 * 
 */
public interface ClientRequestFactory extends RequestFactory {

    public class Util {

        private static ClientRequestFactory instance;

        public static ClientRequestFactory getInstance() {
            if (instance == null) {
                instance = GWT.create(ClientRequestFactory.class);
            }
            return instance;
        }
    }

}
