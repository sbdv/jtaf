package org.jtaf.website.client.app.ui.resources;

import org.jtaf.website.client.app.ui.views.Backbone;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

@GinModules(AppGinModule.class)
public interface AppGinjector extends Ginjector {
	
	Backbone getBackbone();

}
