package org.jtaf.website.client.app.ui.resources;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface JtafResources extends ClientBundle {
	
	@Source("images/gmailID.png")
	ImageResource signInWithGoogle();
	
	@Source("images/jtaf.png")
	ImageResource jtafLogo();
	
	@Source("images/loadingBar.gif")
	ImageResource loadingBar();

}
