package org.jtaf.website.client.app.ui.views;

import org.jtaf.website.client.app.domain.entities.UserProfileProxy;
import org.jtaf.website.client.app.ui.component.LoadingForm;
import org.jtaf.website.client.app.ui.component.Profile;
import org.jtaf.website.client.app.ui.resources.JtafResources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class LoginViewImpl extends Composite implements LoginView {

    private static LoginUiBinder uiBinder = GWT.create(LoginUiBinder.class);
    private Presenter presenter;
    private final LoadingForm loadingForm = new LoadingForm();
    private final Profile profile = new Profile();
    @UiField
    HTMLPanel basePanel;
    @UiField
    Image imgLogin;
    

    interface LoginUiBinder extends UiBinder<Widget, LoginViewImpl> {
    }

    @Inject
    public LoginViewImpl(JtafResources jtafResources) {
        initWidget(uiBinder.createAndBindUi(this));
        imgLogin.setResource(jtafResources.signInWithGoogle());
    }

    @UiHandler("imgLogin")
    void handleClick(ClickEvent event) {
        presenter.processLogin();
    }

    @Override
    public void isLoggingIn() {
        basePanel.remove(imgLogin);
        basePanel.add(loadingForm);
    }

    @Override
    public void logged(UserProfileProxy userProfileProxy) {
        basePanel.remove(imgLogin);
        basePanel.remove(loadingForm);
        profile.setProfile(userProfileProxy);
        basePanel.add(profile);
    }

    @Override
    public void notLogged() {
        basePanel.remove(loadingForm);
        basePanel.add(imgLogin);
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

}
