package org.jtaf.website.client;

import org.jtaf.website.client.app.ui.gin.JtafGinjector;
import org.jtaf.website.client.app.ui.places.HomePlace;
import org.jtaf.website.client.app.ui.views.BackboneView;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

public class Jtaf implements EntryPoint {

    private final JtafGinjector injector = GWT.create(JtafGinjector.class);

    @Override
    public void onModuleLoad() {
        BackboneView backbone = injector.getBackbone();
        injector.getActivityManager().setDisplay(backbone.getLeftContainer());
        injector.getPlaceHistoryHandler().register(injector.getPlaceController(), injector.getEventBus(),
                new HomePlace("Home"));
        RootPanel.get().add(backbone);
        injector.getPlaceHistoryHandler().handleCurrentHistory();

    }
}
