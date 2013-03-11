package org.jtaf.website.client.app.ui.places;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;

public class JtafActivityMapper implements ActivityMapper {
	
	private Activity backBoneActivity;
	

	@Inject
    public JtafActivityMapper(Activity backBoneActivity) {
		this.backBoneActivity = backBoneActivity;
    }

	@Override
	public Activity getActivity(Place place) {
		if (place instanceof HomePlace)
            return backBoneActivity;
        return null;
	}

}
