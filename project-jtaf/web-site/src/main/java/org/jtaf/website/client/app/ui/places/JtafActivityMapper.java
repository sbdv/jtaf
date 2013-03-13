package org.jtaf.website.client.app.ui.places;

import org.jtaf.website.client.app.ui.activities.Home;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;

public class JtafActivityMapper implements ActivityMapper {

    private final Activity homeActivity;

    @Inject
    public JtafActivityMapper(@Home
    Activity homeActivity) {
        this.homeActivity = homeActivity;
    }

    @Override
    public Activity getActivity(Place place) {
        if (place instanceof HomePlace) {
            return homeActivity;
        }
        return null;
    }

}
