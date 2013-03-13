package org.jtaf.website.client.app.ui.gin;

import org.jtaf.website.client.app.ui.places.JtafPlaceHistoryMapper;

import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class PlaceHistoryHandlerProvider implements Provider<PlaceHistoryHandler> {

    private final PlaceHistoryHandler placeHistoryHandler;

    @Inject
    public PlaceHistoryHandlerProvider(JtafPlaceHistoryMapper historyMapper) {
        this.placeHistoryHandler = new PlaceHistoryHandler(historyMapper);
    }

    @Override
    public PlaceHistoryHandler get() {
        return placeHistoryHandler;
    }

}
