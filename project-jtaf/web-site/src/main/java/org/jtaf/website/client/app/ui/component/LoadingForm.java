package org.jtaf.website.client.app.ui.component;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class LoadingForm extends Composite {

    private static LoadingFormUiBinder uiBinder = GWT.create(LoadingFormUiBinder.class);

    interface LoadingFormUiBinder extends UiBinder<Widget, LoadingForm> {
    }

    public LoadingForm() {
        initWidget(uiBinder.createAndBindUi(this));
    }

}
