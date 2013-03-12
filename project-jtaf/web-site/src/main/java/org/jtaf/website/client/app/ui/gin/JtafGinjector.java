package org.jtaf.website.client.app.ui.gin;

import org.jtaf.website.client.app.ui.views.BackboneView;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.web.bindery.event.shared.EventBus;

@GinModules(JtafGinModule.class)
public interface JtafGinjector extends Ginjector {

    BackboneView getBackbone();

    EventBus getEventBus();

    PlaceController getPlaceController();

    ActivityManager getActivityManager();

    PlaceHistoryHandler getPlaceHistoryHandler();

}
