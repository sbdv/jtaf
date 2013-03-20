package org.jtaf.website.client.app.ui.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class NavigationViewImpl extends Composite implements NavigationView {

    private static NavigationContainerUiBinder uiBinder = GWT.create(NavigationContainerUiBinder.class);

    interface NavigationContainerUiBinder extends UiBinder<Widget, NavigationViewImpl> {
    }

    public NavigationViewImpl() {
        initWidget(uiBinder.createAndBindUi(this));
    }

}
