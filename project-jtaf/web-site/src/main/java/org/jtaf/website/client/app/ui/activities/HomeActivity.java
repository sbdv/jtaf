package org.jtaf.website.client.app.ui.activities;

import org.jtaf.website.client.app.ui.views.BackboneView;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

public class HomeActivity extends AbstractActivity implements BackboneView.Presenter {

    private final PlaceController placeController;
    private final BackboneView backBoneView;

    @Inject
    public HomeActivity(PlaceController placeController, BackboneView backbone) {
        this.placeController = placeController;
        this.backBoneView = backbone;
    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        panel.setWidget(backBoneView);
        backBoneView.setPresenter(this);
    }

    @Override
    public void go(Place place) {
        placeController.goTo(place);
    }

}
