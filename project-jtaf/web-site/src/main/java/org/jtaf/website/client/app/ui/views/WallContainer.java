package org.jtaf.website.client.app.ui.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class WallContainer extends Composite {

	private static WallContainerUiBinder uiBinder = GWT
			.create(WallContainerUiBinder.class);
	@UiField
    HTMLPanel basePanel;

	interface WallContainerUiBinder extends UiBinder<Widget, WallContainer> {
	}

	public WallContainer() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
