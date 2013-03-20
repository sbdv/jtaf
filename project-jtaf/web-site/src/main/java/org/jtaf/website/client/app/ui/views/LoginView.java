package org.jtaf.website.client.app.ui.views;

import com.google.gwt.user.client.ui.IsWidget;

public interface LoginView extends IsWidget {

    public interface Presenter {

        void processLogin();
    }
}
