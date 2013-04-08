package org.jtaf.website.client.app.ui.views;

import com.google.gwt.user.client.ui.IsWidget;

public interface LoginView extends IsWidget {

    void logged();

    void notLogged();

    void isLoggingIn();

    void setPresenter(Presenter presenter);

    public interface Presenter {

        void processLogin();
    }
}
