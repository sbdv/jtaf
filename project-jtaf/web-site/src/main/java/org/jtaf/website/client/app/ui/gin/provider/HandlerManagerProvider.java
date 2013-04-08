package org.jtaf.website.client.app.ui.gin.provider;

import com.google.gwt.event.shared.HandlerManager;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.web.bindery.event.shared.EventBus;

public class HandlerManagerProvider implements Provider<HandlerManager> {

    private final HandlerManager handlerManager;

    @Inject
    public HandlerManagerProvider(EventBus eventBus) {
        this.handlerManager = new HandlerManager(eventBus);
    }

    @Override
    public HandlerManager get() {
        return handlerManager;
    }

}
