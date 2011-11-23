package org.biomart.configurator.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import javax.naming.InvalidNameException;
import org.biomart.common.resources.Resources;
import org.biomart.common.resources.Settings;
import org.biomart.common.utils.McGuiUtils;
import org.biomart.common.utils.McUtils;
import org.biomart.configurator.dao.DaoFactoryV2;
import org.biomart.configurator.utils.type.DaoType;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.mc.client.client.object.Config;
import org.mc.client.client.object.Container;
import org.mc.client.client.object.FilterType;
import org.mc.client.client.object.GuiAccessPoint;
import org.mc.client.client.object.GuiContainer;
import org.mc.client.client.object.LiteObject;
import org.mc.client.client.object.Mart;
import org.mc.client.client.object.McNodeType;
import org.mc.client.client.object.PartitionUtils;
import org.mc.client.client.object.SourceContainer;
import org.mc.client.client.object.XMLElements;

public class RequestHandler {
	private static RequestHandler instance;

	public static RequestHandler getInstance() {
		if(instance == null)
			instance = new RequestHandler();
		return instance;
	}

	private RequestHandler() {

	}
	/**
	 * Get the light mart object from the server
	 * @param name - the mart name
	 * @return null if the mart does not exist, otherwise, return the light mart object 
	 */
	public Mart getMartByName(String name) {
		return DaoFactoryV2.getFactory(DaoType.valueOf(Settings.getProperty("persistent.type"))).
			getDaoObject().getMartByName(name);
	}
	

	
	
	public Collection<org.mc.client.client.object.Dataset> getDatasets(String martname) {
		return DaoFactoryV2.getFactory(DaoType.valueOf(Settings.getProperty("persistent.type"))).
				getDaoObject().getDatasets(martname);
	}
	

	
	

	public Collection<Mart> getMarts() {
		return DaoFactoryV2.getFactory(DaoType.valueOf(Settings.getProperty("persistent.type"))).
				getDaoObject().getMarts(McGuiUtils.INSTANCE.getUser().getName(), McGuiUtils.INSTANCE.getRegistryName());
	}

	public Collection<SourceContainer> getSourceContainers() {
		return DaoFactoryV2.getFactory(DaoType.valueOf(Settings.getProperty("persistent.type"))).
				getDaoObject().getSourceContainers();
	}

	public Config getAccessPoint(String mart, String accesspoint) {
		return DaoFactoryV2.getFactory(DaoType.valueOf(Settings.getProperty("persistent.type"))).
			getDaoObject().getAccessPoint(mart, accesspoint);
	}
	


	public Container getRootContainerRecursively(String mart, String config) {
		return DaoFactoryV2.getFactory(DaoType.valueOf(Settings.getProperty("persistent.type"))).
				getDaoObject().getRootContainerRecursively(mart, config);
	}

	
	public boolean requestMoveGuiContainer(String source, String target, boolean before) {
		return false;
	}

	public boolean requestMoveGuiAccessPoints(Collection<String> source, String targetContainer) {
		return false;
	}

	public boolean requestReorderGuiAccessPoint(Collection<String> source, int index) {
		return false;
	}


	public boolean requestAddAccessPoint(String mart) {

		return false;
	}

	public boolean requestAddNaivePortalForAccessPoint(String mart, String accesspoint, String user, String guicontainer) {
		return false;
	}

	public boolean requestRemoveObject(Collection<LiteObject> objects) {
		
		return false;
	}

	
	
	public Collection<String> getAllRegistrysForUser(String username) {
		return DaoFactoryV2.getFactory(DaoType.valueOf(Settings.getProperty("persistent.type"))).getDaoObject().getAllRegistrysForUser(username);
	}
	
	public boolean registryExist(String user, String registry) {
		return DaoFactoryV2.getFactory(DaoType.valueOf(Settings.getProperty("persistent.type"))).getDaoObject().registryExist(user, registry);
	}
	
	public boolean deleteRegistry(String user, String registry) {
		DaoFactoryV2.getFactory(DaoType.valueOf(Settings.getProperty("persistent.type"))).getDaoObject().deleteRegistry(user, registry);
		return true;
	}
	
	public GuiContainer getRootGuiContainer(String user, String registry) {
		return DaoFactoryV2.getFactory(DaoType.valueOf(Settings.getProperty("persistent.type"))).
				getDaoObject().getRootGuiContainer(user, registry);
	}

	public Collection<GuiAccessPoint> getGuiAccessPoints(GuiContainer gc) {
		return DaoFactoryV2.getFactory(DaoType.valueOf(Settings.getProperty("persistent.type"))).
				getDaoObject().getGuiAccessPoints(gc);
	}

}