package org.jtaf.website.client.app.ui.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class WallContainer extends Composite {

	private static WallContainerUiBinder uiBinder = GWT
			.create(WallContainerUiBinder.class);

	interface WallContainerUiBinder extends UiBinder<Widget, WallContainer> {
	}

	public WallContainer() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
