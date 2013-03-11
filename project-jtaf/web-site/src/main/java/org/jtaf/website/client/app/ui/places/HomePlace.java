package org.jtaf.website.client.app.ui.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class HomePlace extends Place {
	
	private String helloName;

    public HomePlace(String token) {
        this.helloName = token;
    }

    public String getHomeName() {
        return helloName;
    }

    public static class Tokenizer implements PlaceTokenizer<HomePlace> {
        @Override
        public String getToken(HomePlace place) {
            return place.getHomeName();
        }

        @Override
        public HomePlace getPlace(String token) {
            return new HomePlace(token);
        }
    }

}
