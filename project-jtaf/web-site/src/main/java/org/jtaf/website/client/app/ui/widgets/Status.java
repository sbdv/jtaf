package org.jtaf.website.client.app.ui.widgets;

import org.jtaf.website.client.app.ui.resources.JtafResources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class Status extends Composite {

	private static StatusUiBinder uiBinder = GWT.create(StatusUiBinder.class);
	@UiField
	Image avatar;
	@UiField
	Anchor pseudo;
	@UiField
	Label date;

	interface StatusUiBinder extends UiBinder<Widget, Status> {
	}

	public Status(JtafResources jtafResources) {
		initWidget(uiBinder.createAndBindUi(this));
		avatar.setResource(jtafResources.jtafLogo());

		pseudo.setText(randomPseudo());

		date.setText(randomDate());
	}

	/**
	 * TODO : A virer. Sert uniquement pour des tests
	 */
	private String randomDate() {
		String annee = String
				.valueOf(2010 + (int) Math.round(Math.random() * (3)));
		String mois = String
				.valueOf(1 + (int) Math.round(Math.random() * (12)));
		String jours = String
				.valueOf(1 + (int) Math.round(Math.random() * (30)));
		return "Le " + jours + "/" + mois + "/" + annee;
	}

	/**
	 * TODO : A virer. Sert uniquement pour des tests
	 */
	private String randomPseudo() {
		String pseudo2 = "defaut";
		switch ((int) Math.round(Math.random() * 5)) {
		case 0:
			pseudo2 = "Mr.Renard";
			break;

		case 1:
			pseudo2 = "Panda";
			break;

		case 2:
			pseudo2 = "EtoileDeMer";
			break;

		case 3:
			pseudo2 = "MathieuBellange";
			break;
			
		case 4:
			pseudo2 = "OursPolaire";
			break;
			
		case 5:
			pseudo2 = "PseudoIncroyablementLoooooooooong";
			break;

		default:
			pseudo2 = "JTAFeur invité";
		}
		return pseudo2;
	}

}