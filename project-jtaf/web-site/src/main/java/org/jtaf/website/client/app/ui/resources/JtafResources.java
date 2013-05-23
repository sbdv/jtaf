package org.jtaf.website.client.app.ui.resources;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.DataResource;
import com.google.gwt.resources.client.ImageResource;

	
public interface JtafResources extends ClientBundle {
	@Source("images/gmailID.png")
	ImageResource signInWithGoogle();
	
	@Source("images/jtaf.png")
	ImageResource jtafLogo();
	
	@Source("images/loadingBar.gif")
	ImageResource loadingBar();
	
	@Source("images/sbdv_100px.png")
	ImageResource loadingLogoSBDV();
	
	/* Les avatars - temporaire - */
	
	@Source("images/avatarTemp/unkwown.png")
	ImageResource loadingAvatarUnkown();
	
	@Source("images/avatarTemp/renard-avatar.png")
	ImageResource loadingAvatarRenard();
	
	@Source("images/avatarTemp/etoile-avatar.png")
	ImageResource loadingAvatarEtoile();
	
	@Source("images/avatarTemp/orni-avatar.png")
	ImageResource loadingAvatarOrni();
	
	@Source("images/avatarTemp/panda-avatar.png")
	ImageResource loadingAvatarPanda();
	
	@Source("images/avatarTemp/mathieu-bellange.jpg")
	ImageResource loadingAvatarMathieu();

}
