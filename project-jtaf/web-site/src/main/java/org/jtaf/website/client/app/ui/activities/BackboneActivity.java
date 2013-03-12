package org.jtaf.website.client.app.ui.activities;

import org.jtaf.website.client.app.ui.views.BackboneView;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.widget.client.TextButton;
import com.google.inject.Inject;

public class BackboneActivity extends AbstractActivity implements BackboneView.Activity {

    private final PlaceController placeController;

    @Inject
    public BackboneActivity(PlaceController placeController) {
        this.placeController = placeController;
    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        SimplePanel sPanel = new SimplePanel();
        sPanel.setSize("100%", "250px");
        sPanel.add(new TextButton("Test"));
        panel.setWidget(sPanel);
    }

    @Override
    public void go(Place place) {
        placeController.goTo(place);
    }

}
