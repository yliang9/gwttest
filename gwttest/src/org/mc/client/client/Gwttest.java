package org.mc.client.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Gwttest implements EntryPoint {
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {	
		McClient client = new McClient();
		RootLayoutPanel.get().add(client.createMcFrame());
	}
	
	
}
