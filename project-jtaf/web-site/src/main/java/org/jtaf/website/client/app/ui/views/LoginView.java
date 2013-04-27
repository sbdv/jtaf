package org.jtaf.website.client.app.ui.views;

import org.jtaf.website.client.app.domain.entities.UserProfileProxy;
import org.jtaf.website.client.app.ui.widgets.Profile;

import com.google.gwt.user.client.ui.IsWidget;

public interface LoginView extends IsWidget {

    void logged();

    void notLogged();

    void isLoggingIn();

    void setPresenter(Presenter presenter);
    
    Profile getProfile();

    public interface Presenter {

        void processLogin();
        
        void alreadyLogged(UserProfileProxy user);
        
        void notLogged();
    }
}
