package org.jtaf.website.server.app.facade.services;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.google.web.bindery.requestfactory.shared.ServiceLocator;

@Component
public class ServicesLocator implements ServiceLocator, ApplicationContextAware {

    private ApplicationContext appContext;

    @Override
    public void setApplicationContext(ApplicationContext appContext) throws BeansException {
        this.appContext = appContext;
    }

    @Override
    public Object getInstance(Class<?> clazz) {
        return appContext.getBean(clazz);
    }

}
