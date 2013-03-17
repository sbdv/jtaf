package org.jtaf.website.client.app.ui.activities;

import org.jtaf.website.client.app.ui.component.Status;
import org.jtaf.website.client.app.ui.resources.JtafResources;
import org.jtaf.website.client.app.ui.views.BackboneView;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Image;
import com.google.inject.Inject;

public class HomeActivity extends AbstractActivity implements BackboneView.Presenter {

    private final PlaceController placeController;
    private final BackboneView backBoneView;
    private final JtafResources resources;

    @Inject
    public HomeActivity(PlaceController placeController, BackboneView backbone, JtafResources resources) {
        this.placeController = placeController;
        this.backBoneView = backbone;
        this.resources = resources;
    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        panel.setWidget(backBoneView);
        Image img = new Image(resources.signInWithGoogle());
        backBoneView.getLeftContainer().add(img);
        backBoneView.setPresenter(this);
        backBoneView.getWallContainer().add(new Status(resources));
        backBoneView.getWallContainer().add(new Status(resources));
        backBoneView.getWallContainer().add(new Status(resources));
        backBoneView.getWallContainer().add(new Status(resources));
    }

    @Override
    public void go(Place place) {
        placeController.goTo(place);
    }

}
