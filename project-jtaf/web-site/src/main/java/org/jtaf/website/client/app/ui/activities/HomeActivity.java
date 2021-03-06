package org.jtaf.website.client.app.ui.activities;

import org.jtaf.website.client.app.domain.access.JtafRequestFactory;
import org.jtaf.website.client.app.domain.access.UserProfileRequest;
import org.jtaf.website.client.app.domain.entities.UserProfileProxy;
import org.jtaf.website.client.app.ui.resources.JtafResources;
import org.jtaf.website.client.app.ui.views.BackboneView;
import org.jtaf.website.client.app.ui.views.LoginView;
import org.jtaf.website.client.app.ui.widgets.Status;
import org.jtaf.website.client.security.domain.access.SecuredReceiver;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

public class HomeActivity extends AbstractActivity implements BackboneView.Presenter {

    private final PlaceController placeController;
    private final BackboneView backBoneView;
    private final JtafResources resources;
    private final LoginView loginComponent;
    private final LoginPresenter loginPresenter;
    private final HandlerManager handlerManager;
    private final JtafRequestFactory requestFactory;

    @Inject
    public HomeActivity(PlaceController placeController, BackboneView backbone, JtafResources resources,
            LoginView loginComponent, LoginPresenter loginPresenter, HandlerManager handlerManager,
            JtafRequestFactory requestFactory) {
        this.placeController = placeController;
        this.backBoneView = backbone;
        this.resources = resources;
        this.loginComponent = loginComponent;
        this.loginPresenter = loginPresenter;
        this.handlerManager = handlerManager;
        this.requestFactory = requestFactory;
        bind();
    }

    private void bind() {

    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        backBoneView.getProfilContainer().add(loginComponent.asWidget());
        UserProfileRequest userRequest = requestFactory.getUserProfileRequest();
        userRequest.userProfileInformation().fire(new SecuredReceiver<UserProfileProxy>() {

            @Override
            public void onSuccess(UserProfileProxy response) {
                loginPresenter.alreadyLogged(response);
            }

            @Override
            public void onAccessDenied() {
                loginPresenter.notLogged();
            }
        });
        panel.setWidget(backBoneView);
        loginComponent.setPresenter(loginPresenter);
        backBoneView.setPresenter(this);
        backBoneView.getWallContainer().add(new Status(resources, true, false));
        backBoneView.getWallContainer().add(new Status(resources, false, false));
        backBoneView.getWallContainer().add(new Status(resources, false, false));
        backBoneView.getWallContainer().add(new Status(resources, false, false));
        backBoneView.getWallContainer().add(new Status(resources, false, false));
        backBoneView.getWallContainer().add(new Status(resources, false, true));
    }

    @Override
    public void go(Place place) {
        placeController.goTo(place);
    }

}
