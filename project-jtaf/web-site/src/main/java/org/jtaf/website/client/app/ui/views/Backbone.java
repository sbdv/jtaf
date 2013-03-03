package org.jtaf.website.client.app.ui.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class Backbone extends Composite {

	private static BackboneUiBinder uiBinder = GWT
			.create(BackboneUiBinder.class);

	interface BackboneUiBinder extends UiBinder<Widget, Backbone> {
	}


	public Backbone() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
