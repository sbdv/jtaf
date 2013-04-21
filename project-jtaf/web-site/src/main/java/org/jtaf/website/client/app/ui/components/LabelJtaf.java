package org.jtaf.website.client.app.ui.components;

import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;

public class LabelJtaf extends Composite implements LeafValueEditor<String> {

	private String text;
	private Label label;
	
	@Override
	public void addStyleName(String style) {
		label.addStyleName(style);
	}
	@Override
	public String getStyleName() {
		return label.getStyleName();
	}
	@Override
	public String getStylePrimaryName() {
		return label.getStylePrimaryName();
	}
	@Override
	public void setStylePrimaryName(String style) {
		label.setStylePrimaryName(style);
	}
	@Override
	public void setHeight(String height) {
		label.setHeight(height);
	}
	@Override
	public void setSize(String width, String height) {
		label.setSize(width, height);
	}
	@Override
	public void setWidth(String width) {
		label.setWidth(width);
	}
	public LabelJtaf(){
		this.label = new Label();
		initWidget(this.label);
	}
	@Override
	public void setValue(String value) {
		this.text=value;
		label.setText(text);
	}

	@Override
	public String getValue() {
		return this.text;
	}
	
	
}
