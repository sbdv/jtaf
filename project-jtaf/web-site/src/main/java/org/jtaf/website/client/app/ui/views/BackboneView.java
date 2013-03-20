package org.jtaf.website.client.app.ui.views;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.IsWidget;

public interface BackboneView extends IsWidget {

    HasWidgets getProfilContainer();

    HasWidgets getWallContainer();

    void setPresenter(Presenter presenter);

    public interface Presenter {

        void go(Place place);
    }

}
