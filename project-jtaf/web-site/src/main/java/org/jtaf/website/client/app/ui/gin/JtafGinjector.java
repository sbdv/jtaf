package org.jtaf.website.client.app.ui.gin;

import org.jtaf.website.client.app.ui.views.Backbone;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;

@GinModules(JtafGinModule.class)
public interface JtafGinjector extends Ginjector {
	
	Backbone getBackbone();
	
	EventBus getEventBus();
	
	PlaceController getPlaceController();

}
