package org.jtaf.website.client.app.ui.gin;

import org.jtaf.website.client.app.ui.activities.ActivityManagerProvider;
import org.jtaf.website.client.app.ui.activities.Home;
import org.jtaf.website.client.app.ui.activities.HomeActivity;
import org.jtaf.website.client.app.ui.activities.PlaceControllerProvider;
import org.jtaf.website.client.app.ui.activities.PlaceHistoryHandlerProvider;
import org.jtaf.website.client.app.ui.places.JtafActivityMapper;
import org.jtaf.website.client.app.ui.views.BackboneView;
import org.jtaf.website.client.app.ui.views.BackboneViewImpl;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.inject.Singleton;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

public class JtafGinModule extends AbstractGinModule {

    @Override
    protected void configure() {
        // resources
        bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
        bind(PlaceController.class).toProvider(PlaceControllerProvider.class).in(Singleton.class);
        bind(ActivityMapper.class).to(JtafActivityMapper.class).in(Singleton.class);
        bind(ActivityManager.class).toProvider(ActivityManagerProvider.class).in(Singleton.class);
        bind(PlaceHistoryHandler.class).toProvider(PlaceHistoryHandlerProvider.class).in(Singleton.class);
        // view
        bind(BackboneView.class).to(BackboneViewImpl.class).in(Singleton.class);
        // activity
        bind(Activity.class).annotatedWith(Home.class).to(HomeActivity.class).in(Singleton.class);

    }
}
