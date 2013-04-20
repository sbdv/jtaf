package org.jtaf.website.client.app.ui.component;

import org.jtaf.website.client.app.domain.entities.UserProfileProxy;
import org.jtaf.website.client.app.ui.resources.JtafResources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class Profile extends Composite{

	private static ProfileUiBinder uiBinder = GWT
			.create(ProfileUiBinder.class);
	@UiField
    Image avatar;
    @UiField
    Label pseudo;
    @UiField
    Label name;
    
	interface ProfileUiBinder extends UiBinder<Widget, Profile> {
	}

	public Profile() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public void setProfile(UserProfileProxy userProfileProxy){
		this.avatar.setUrl(userProfileProxy.getAvatar());
		this.pseudo.setText(userProfileProxy.getPseudo());
		this.name.setText(userProfileProxy.getGivenName()+ " " + userProfileProxy.getLastName());
	}


	public Profile(JtafResources jtafResources) {
		initWidget(uiBinder.createAndBindUi(this));
        avatar.setResource(jtafResources.jtafLogo());
        pseudo.setText("Pseudo");
        name.setText("Prénom Nom");
	}

}
