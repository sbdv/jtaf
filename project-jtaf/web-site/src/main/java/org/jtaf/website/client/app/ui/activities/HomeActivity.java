package org.jtaf.website.client.app.ui.activities;

import org.jtaf.website.client.app.ui.component.Status;
import org.jtaf.website.client.app.ui.resources.JtafResources;
import org.jtaf.website.client.app.ui.views.BackboneView;

import com.google.api.gwt.oauth2.client.Auth;
import com.google.api.gwt.oauth2.client.AuthRequest;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.Callback;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Image;
import com.google.inject.Inject;

public class HomeActivity extends AbstractActivity implements BackboneView.Presenter {

    private final PlaceController placeController;
    private final BackboneView backBoneView;
    private final JtafResources resources;
    private static final Auth AUTH = Auth.get();
    private static final String GOOGLE_AUTH_URL = "https://accounts.google.com/o/oauth2/auth";

    // This app's personal client ID assigned by the Google APIs Console
    // (http://code.google.com/apis/console).
    private static final String GOOGLE_CLIENT_ID = "264546229669.apps.googleusercontent.com";

    // The auth scope being requested. This scope will allow the application to
    // identify who the authenticated user is.
    private static final String PLUS_ME_SCOPE = "https://www.googleapis.com/auth/plus.me";

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
        img.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                final AuthRequest req = new AuthRequest(GOOGLE_AUTH_URL, GOOGLE_CLIENT_ID).withScopes(PLUS_ME_SCOPE);
                AUTH.login(req, new Callback<String, Throwable>() {
                    @Override
                    public void onSuccess(String token) {
                        Window.alert("Got an OAuth token:\n" + token + "\n" + "Token expires in " + AUTH.expiresIn(req)
                                + " ms\n");
                    }

                    @Override
                    public void onFailure(Throwable caught) {
                        Window.alert("Error:\n" + caught.getMessage());
                    }
                });
            }
        });
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
