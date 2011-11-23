package org.biomart.configurator.controller;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.biomart.common.resources.Settings;

public class BiomartConfig implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent event) {
/*		ServletContext context = event.getServletContext();
		context.
		context.setAttribute("TEST", "TEST_VALUE");	*/
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		Settings.loadGUIConfigProperties();
		Settings.loadAllConfigProperties();
		
	}
	
}