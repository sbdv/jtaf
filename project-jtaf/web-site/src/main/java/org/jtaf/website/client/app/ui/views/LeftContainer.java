package org.jtaf.website.client.app.ui.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class LeftContainer extends Composite {

    private static LeftContainerUiBinder uiBinder = GWT.create(LeftContainerUiBinder.class);
    @UiField
    SimplePanel simplePanel;

    interface LeftContainerUiBinder extends UiBinder<Widget, LeftContainer> {
    }

    public LeftContainer() {
        initWidget(uiBinder.createAndBindUi(this));
    }

}
