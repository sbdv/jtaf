package org.jtaf.website.client.app.ui.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class BackboneViewImpl extends Composite implements BackboneView {

    private static BackboneUiBinder uiBinder = GWT.create(BackboneUiBinder.class);
    private BackboneView.Presenter backboneActivity;
    @UiField
    HTMLPanel baseProfil;
    @UiField
    HTMLPanel baseWall;

    interface BackboneUiBinder extends UiBinder<Widget, BackboneViewImpl> {
    }

    public BackboneViewImpl() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public HasWidgets getProfilContainer() {
        return baseProfil;
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.backboneActivity = presenter;
    }

    @Override
    public HasWidgets getWallContainer() {
        return baseWall;
    }
}
