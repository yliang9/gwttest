package org.mc.client.client.command;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.DialogBox;

public class DialogCommand implements Command {

	@Override
	public void execute() {
		DialogBox dbox = new DialogBox();
		dbox.center();
		
	}
	
}