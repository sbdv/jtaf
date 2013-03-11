package org.jtaf.website.client.app.ui.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class BackboneView extends Composite implements Backbone {

	private static BackboneUiBinder uiBinder = GWT
			.create(BackboneUiBinder.class);
	private Backbone.Activity backboneActivity;

	interface BackboneUiBinder extends UiBinder<Widget, BackboneView> {
	}


	@Inject
	public BackboneView(Backbone.Activity backboneActivity) {
		initWidget(uiBinder.createAndBindUi(this));
		this.backboneActivity = backboneActivity;
	}
}
