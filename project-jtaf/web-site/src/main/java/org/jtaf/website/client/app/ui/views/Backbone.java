package org.jtaf.website.client.app.ui.views;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;

public interface Backbone extends IsWidget {		

	public interface Activity {
		
		void go(Place place);
	}

}
