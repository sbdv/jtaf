package org.jtaf.website.client.app.ui.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class BackboneViewImpl extends Composite implements BackboneView {

    private static BackboneUiBinder uiBinder = GWT.create(BackboneUiBinder.class);
    private final BackboneView.Activity backboneActivity;
    @UiField
    LeftContainer leftContainer;

    interface BackboneUiBinder extends UiBinder<Widget, BackboneViewImpl> {
    }

    @Inject
    public BackboneViewImpl(BackboneView.Activity backboneActivity) {
        initWidget(uiBinder.createAndBindUi(this));
        this.backboneActivity = backboneActivity;
    }

    @Override
    public AcceptsOneWidget getLeftContainer() {
        return leftContainer.simplePanel;
    }
}
