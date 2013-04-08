package org.jtaf.website.client;

import org.jtaf.website.client.app.ui.gin.JtafGinjector;
import org.jtaf.website.client.app.ui.places.HomePlace;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;

public class Jtaf implements EntryPoint {

    private final JtafGinjector injector = GWT.create(JtafGinjector.class);
    private final SimplePanel basePanel = new SimplePanel();

    @Override
    public void onModuleLoad() {
        // request factory
        injector.getAuthentifiedRequestFactory().initialize(injector.getEventBus());
        injector.getActivityManager().setDisplay(basePanel);
        injector.getPlaceHistoryHandler().register(injector.getPlaceController(), injector.getEventBus(),
                new HomePlace("Home"));
        RootPanel.get().add(basePanel);
        injector.getPlaceHistoryHandler().handleCurrentHistory();

    }
}
