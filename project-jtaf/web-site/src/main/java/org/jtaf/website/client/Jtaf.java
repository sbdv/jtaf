package org.jtaf.website.client;

import org.jtaf.website.client.app.ui.resources.AppGinjector;
import org.jtaf.website.client.app.ui.views.Backbone;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.RootLayoutPanel;

public class Jtaf implements EntryPoint {
	
	private final AppGinjector injector = GWT.create(AppGinjector.class);

	@Override
	public void onModuleLoad() {
		Backbone backbone = injector.getBackbone();
		RootLayoutPanel.get().add(backbone);
		
	}

}
