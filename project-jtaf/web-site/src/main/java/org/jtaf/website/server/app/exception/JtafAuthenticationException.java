package org.jtaf.website.server.app.exception;

import org.springframework.security.core.AuthenticationException;

public class JtafAuthenticationException extends AuthenticationException {

    /**
     * 
     */
    private static final long serialVersionUID = -5295619765085479211L;

    public JtafAuthenticationException(String msg) {
        super(msg);
    }

    public JtafAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }

}
