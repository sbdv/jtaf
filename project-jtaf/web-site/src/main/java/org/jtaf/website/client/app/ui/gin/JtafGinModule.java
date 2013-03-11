package org.jtaf.website.client.app.ui.gin;

import org.jtaf.website.client.app.ui.activities.BackboneActivity;
import org.jtaf.website.client.app.ui.activities.PlaceControllerProvider;
import org.jtaf.website.client.app.ui.views.Backbone;
import org.jtaf.website.client.app.ui.views.BackboneView;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Singleton;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

public class JtafGinModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(Backbone.Activity.class).to(BackboneActivity.class).in(Singleton.class);
		bind(Backbone.class).to(BackboneView.class).in(Singleton.class);
		bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
		bind(PlaceController.class).toProvider(PlaceControllerProvider.class);
	}

}
