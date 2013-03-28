package org.jtaf.website.security.client;

import org.springframework.security.core.AuthenticationException;

public class JtafAuthenticationException extends AuthenticationException {

    /**
     * 
     */
    private static final long serialVersionUID = -4462477410358536120L;

    public JtafAuthenticationException(String msg) {
        super(msg);
    }

}
