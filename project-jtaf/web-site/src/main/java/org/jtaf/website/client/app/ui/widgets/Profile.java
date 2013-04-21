package org.jtaf.website.client.app.ui.widgets;

import org.jtaf.website.client.app.domain.entities.UserProfileProxy;
import org.jtaf.website.client.app.ui.components.ImgAvatar;
import org.jtaf.website.client.app.ui.components.LabelJtaf;
import org.jtaf.website.client.app.ui.resources.JtafResources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class Profile extends Composite implements Editor<UserProfileProxy>{

	private static ProfileUiBinder uiBinder = GWT
			.create(ProfileUiBinder.class);
	@UiField
    ImgAvatar avatar;
    @UiField
    LabelJtaf pseudo;
    @UiField
    @Path("givenName")
    LabelJtaf givenName;
    @UiField
    @Path("lastName")
    LabelJtaf lastName;
    
	interface ProfileUiBinder extends UiBinder<Widget, Profile> {
	}

	public Profile() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public Profile(JtafResources jtafResources) {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
