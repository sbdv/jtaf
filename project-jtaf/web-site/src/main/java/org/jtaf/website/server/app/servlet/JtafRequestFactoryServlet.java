package org.jtaf.website.server.app.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.google.web.bindery.requestfactory.server.ExceptionHandler;
import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;
import com.google.web.bindery.requestfactory.server.ServiceLayerDecorator;

/**
 * Servlet des request Factory
 * 
 * @author mbellang
 * 
 */
public class JtafRequestFactoryServlet extends RequestFactoryServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 6869520033025881851L;

    public JtafRequestFactoryServlet() {
        this(new SpringSecurityRequestFactoryExceptionHandler(), new SpringServiceLayerDecorator());
    }

    public JtafRequestFactoryServlet(ExceptionHandler exceptionHandler, ServiceLayerDecorator... serviceDecorators) {
        super(exceptionHandler, serviceDecorators);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException {
        super.doPost(request, response);
    }
}
