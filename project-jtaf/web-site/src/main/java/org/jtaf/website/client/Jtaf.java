package org.jtaf.website.client;

import org.jtaf.website.client.app.ui.views.Backbone;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;

public class Jtaf implements EntryPoint {

	@Override
	public void onModuleLoad() {
		Backbone backbone = new Backbone();
		RootLayoutPanel.get().add(backbone);
		
	}

}
