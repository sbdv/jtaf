package org.jtaf.website.client.app.ui.components;

import org.jtaf.website.client.app.ui.resources.JtafResources;

import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;

public class ImgAvatar extends Composite implements LeafValueEditor<String> {

	private String url;
	private Image img;
	private final JtafResources resources;

	public @UiConstructor ImgAvatar(JtafResources resources) {
		this.resources = resources;
		this.img = new Image();
		initWidget(img);
	}

	@Override
	public void addStyleName(String style) {
		img.addStyleName(style);
	}

	@Override
	public String getStyleName() {
		return img.getStyleName();
	}

	@Override
	public String getStylePrimaryName() {
		return img.getStylePrimaryName();
	}

	@Override
	public void setHeight(String height) {
		img.setHeight(height);
	}

	@Override
	public void setSize(String width, String height) {
		img.setSize(width, height);
	}

	@Override
	public void setWidth(String width) {
		img.setWidth(width);
	}

	@Override
	public void setStylePrimaryName(String style) {
		img.setStylePrimaryName(style);
	}

	@Override
	public void setValue(String value) {
		if (value == null) {
			img.setResource(resources.jtafLogo());
		} else {
			this.url = value;
			img.setUrl(value);
		}
	}

	@Override
	public String getValue() {
		return this.url;
	}

}
