package org.jtaf.website.client.app.ui.activities;

import org.jtaf.website.client.app.ui.views.Backbone;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

public class BackboneActivity extends AbstractActivity implements Backbone.Activity {
	
	private PlaceController placeController;

	@Inject
	public BackboneActivity(PlaceController placeController){
		this.placeController = placeController;
	}
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		// TODO Auto-generated method stub

	}

	@Override
	public void go(Place place) {
		placeController.goTo(place);
	}

}
