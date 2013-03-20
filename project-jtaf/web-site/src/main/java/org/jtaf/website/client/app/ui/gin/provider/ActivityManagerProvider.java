package org.jtaf.website.client.app.ui.gin.provider;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.web.bindery.event.shared.EventBus;

public class ActivityManagerProvider implements Provider<ActivityManager> {

    private final ActivityManager activityManager;

    @Inject
    public ActivityManagerProvider(ActivityMapper activityMapper, EventBus eventBus) {
        this.activityManager = new ActivityManager(activityMapper, eventBus);
    }

    @Override
    public ActivityManager get() {
        return activityManager;
    }

}
