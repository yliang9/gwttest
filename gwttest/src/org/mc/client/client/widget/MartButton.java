package org.mc.client.client.widget;

import org.mc.client.client.object.Mart;
import com.google.gwt.user.client.ui.Button;

public class MartButton extends Button {
	private Mart mart;
	
	public MartButton(String name) {
		super(name);
	}

	public Mart getMart() {
		return mart;
	}

	public void setMart(Mart mart) {
		this.mart = mart;
	}
	
}