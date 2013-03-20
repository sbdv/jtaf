package org.jtaf.website.client.app.ui.gin.provider;

import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.web.bindery.event.shared.EventBus;

public class PlaceControllerProvider implements Provider<PlaceController> {
	
	private PlaceController placeController;
	
	@Inject
	public PlaceControllerProvider(EventBus eventBus){
		this.placeController = new PlaceController(eventBus);
	}

	@Override
	public PlaceController get() {
		return placeController;
	}

}
