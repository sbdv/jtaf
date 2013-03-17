package org.jtaf.website.client.app.ui.component;

import org.jtaf.website.client.app.ui.resources.JtafResources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class Status extends Composite {

	private static StatusUiBinder uiBinder = GWT.create(StatusUiBinder.class);
	@UiField
	Image avatar;
	@UiField
	Anchor pseudo;
	@UiField
	Label date;

	interface StatusUiBinder extends UiBinder<Widget, Status> {
	}

	public Status(JtafResources jtafResources) {
		initWidget(uiBinder.createAndBindUi(this));
		avatar.setResource(jtafResources.jtafLogo());
	}

}
