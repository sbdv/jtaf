package org.jtaf.website.client.app.ui.activities;

import org.jtaf.website.client.app.ui.component.Status;
import org.jtaf.website.client.app.ui.event.LoadingUserDataEvent;
import org.jtaf.website.client.app.ui.event.LoadingUserDataHandler;
import org.jtaf.website.client.app.ui.resources.JtafResources;
import org.jtaf.website.client.app.ui.views.BackboneView;
import org.jtaf.website.client.app.ui.views.LoginView;

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

    @Inject
    public HomeActivity(PlaceController placeController, BackboneView backbone, JtafResources resources,
            LoginView loginComponent, LoginPresenter loginPresenter, HandlerManager handlerManager) {
        this.placeController = placeController;
        this.backBoneView = backbone;
        this.resources = resources;
        this.loginComponent = loginComponent;
        this.loginPresenter = loginPresenter;
        this.handlerManager = handlerManager;
        bind();
    }

    private void bind() {
        handlerManager.addHandler(LoadingUserDataEvent.TYPE, new LoadingUserDataHandler() {

            @Override
            public void onLoadingUserData(LoadingUserDataEvent event) {
                // TODO chargement des données utilisateurs
            }
        });
    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        panel.setWidget(backBoneView);
        backBoneView.getProfilContainer().add(loginComponent.asWidget());
        loginComponent.setPresenter(loginPresenter);
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
