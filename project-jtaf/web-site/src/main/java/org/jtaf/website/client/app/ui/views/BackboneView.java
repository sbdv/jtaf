package org.jtaf.website.client.app.ui.views;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;

public interface BackboneView extends IsWidget {

    AcceptsOneWidget getLeftContainer();

    void setPresenter(Presenter presenter);

    public interface Presenter {

        void go(Place place);
    }

}
