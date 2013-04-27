package org.jtaf.website.client.app.ui.views;

import org.jtaf.website.client.app.ui.resources.JtafResources;
import org.jtaf.website.client.app.ui.widgets.Profile;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class LoginViewImpl extends Composite implements LoginView {

    private static LoginUiBinder uiBinder = GWT.create(LoginUiBinder.class);
    private Presenter presenter;
    private final Profile profile = new Profile();
    @UiField
    HTMLPanel basePanel;
    @UiField
    Label title;
    @UiField
    Image imgLogin;
    @UiField
    Image imgLoading;
    @UiField
    Button btnLogin;
    

    interface LoginUiBinder extends UiBinder<Widget, LoginViewImpl> {
    }

    @Inject
    public LoginViewImpl(JtafResources jtafResources) {
        initWidget(uiBinder.createAndBindUi(this));
        imgLogin.setResource(jtafResources.signInWithGoogle());
        imgLoading.setResource(jtafResources.loadingBar());
    }

    @UiHandler("btnLogin")
    void handleClick(ClickEvent event) {
        presenter.processLogin();
    }

    @Override
    public void isLoggingIn() {
        btnLogin.setVisible(false);
        imgLoading.setVisible(true);    
    }

    @Override
    public void logged() {
        imgLogin.setVisible(false);
        imgLoading.setVisible(false);
        title.setVisible(false);
        btnLogin.setVisible(false);
        basePanel.add(profile);
    }

    @Override
    public void notLogged() {
        imgLoading.setVisible(false);
        imgLogin.setVisible(true);
        title.setVisible(true);
        btnLogin.setVisible(true);
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

	@Override
	public Profile getProfile() {
		return profile;
	}

}
