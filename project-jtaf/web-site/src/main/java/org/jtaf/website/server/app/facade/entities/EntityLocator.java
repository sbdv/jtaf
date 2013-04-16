package org.jtaf.website.server.app.facade.entities;

import java.math.BigInteger;

import org.jtaf.website.server.app.domain.entities.AbstractEntity;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.google.web.bindery.requestfactory.shared.Locator;

public abstract class EntityLocator<T extends AbstractEntity> extends Locator<T, BigInteger> implements
        ApplicationContextAware {

    private ApplicationContext appContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.appContext = applicationContext;
    }

    @Override
    public T create(Class<? extends T> clazz) {
        return appContext.getBean(clazz);
    }

    @Override
    public abstract T find(Class<? extends T> clazz, BigInteger id);

    @Override
    public abstract Class<T> getDomainType();

    @Override
    public BigInteger getId(T domainObject) {
        return domainObject.getId();
    }

    @Override
    public Class<BigInteger> getIdType() {
        return BigInteger.class;
    }

    @Override
    public Object getVersion(T domainObject) {
        return domainObject;
    }

}
