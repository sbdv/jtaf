package org.jtaf.website.client.app.ui.event;

import com.google.gwt.event.shared.GwtEvent;

public class LoadingUserDataEvent extends GwtEvent<LoadingUserDataHandler> {

    public static Type<LoadingUserDataHandler> TYPE = new Type<LoadingUserDataHandler>();

    @Override
    public Type<LoadingUserDataHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(LoadingUserDataHandler handler) {
        handler.onLoadingUserData(this);
    }

}
