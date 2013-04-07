package org.jtaf.website.server.security;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.commons.lang3.StringUtils;
import org.jtaf.website.shared.security.SecurityResponseMessage;
import org.jtaf.website.shared.security.SecurityResponseMessageFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanUtils;
import com.google.web.bindery.autobean.vm.AutoBeanFactorySource;

/**
 * Utilisé dans l'ExceptionTranslationFilter de spring security, permet de wrapper les messages d'erreur du serveur pour
 * être compréhensible côté GWT
 * 
 * @author mbellang
 * 
 */
@Component
public class GwtAwareAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final String reasonPhrase;

    public GwtAwareAuthenticationEntryPoint() {
        this(HttpStatus.UNAUTHORIZED, ". Authentication needed");
    }

    public GwtAwareAuthenticationEntryPoint(HttpStatus status, String complementPhrase) {

        final StringBuilder sb = new StringBuilder(status.getReasonPhrase());
        if (StringUtils.isNotBlank(complementPhrase)) {
            sb.append(complementPhrase);
        }
        reasonPhrase = sb.toString();
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {

        final HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(response);

        final Writer out = responseWrapper.getWriter();

        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        final SecurityResponseMessageFactory factory = AutoBeanFactorySource
                .create(SecurityResponseMessageFactory.class);
        final AutoBean<SecurityResponseMessage> autobeanMessage = factory.create(SecurityResponseMessage.class);
        final SecurityResponseMessage message = autobeanMessage.as();

        message.setStatus(HttpStatus.UNAUTHORIZED.value());
        message.setMessage(reasonPhrase);

        final AutoBean<SecurityResponseMessage> bean = AutoBeanUtils.getAutoBean(message);
        final String encodedString = AutoBeanCodex.encode(bean).getPayload();

        out.write(encodedString);
        out.flush();
        out.close();

    }

}
