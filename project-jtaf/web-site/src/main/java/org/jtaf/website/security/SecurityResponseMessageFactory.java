package org.jtaf.website.security;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;

/**
 * Factory des messages sécurisés
 * 
 * @author mbellang
 * 
 */
public interface SecurityResponseMessageFactory extends AutoBeanFactory {

    AutoBean<SecurityResponseMessage> securityResponseMessage();

    final class Builder {

        private static final SecurityResponseMessageFactory FACTORY = GWT.create(SecurityResponseMessageFactory.class);

        public static final SecurityResponseMessage getSecurityResponseMessage(final String json) {
            final AutoBean<SecurityResponseMessage> bean = AutoBeanCodex.decode(FACTORY, SecurityResponseMessage.class,
                    json);
            return bean.as();
        }

    }
}