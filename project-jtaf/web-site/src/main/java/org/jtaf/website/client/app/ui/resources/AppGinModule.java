package org.jtaf.website.client.app.ui.resources;

import org.jtaf.website.client.app.ui.views.Backbone;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

public class AppGinModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(Backbone.class).in(Singleton.class);
	}

}
