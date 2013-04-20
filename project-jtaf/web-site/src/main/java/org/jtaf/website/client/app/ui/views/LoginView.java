package org.jtaf.website.client.app.ui.views;

import org.jtaf.website.client.app.domain.entities.UserProfileProxy;

import com.google.gwt.user.client.ui.IsWidget;

public interface LoginView extends IsWidget {

    void logged(UserProfileProxy userProfileProxy);

    void notLogged();

    void isLoggingIn();

    void setPresenter(Presenter presenter);

    public interface Presenter {

        void processLogin();
    }
}
