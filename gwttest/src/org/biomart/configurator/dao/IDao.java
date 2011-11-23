package org.biomart.configurator.dao;

import java.util.Collection;
import java.util.List;

import org.mc.client.client.object.Attribute;
import org.mc.client.client.object.Config;
import org.mc.client.client.object.Container;
import org.mc.client.client.object.Dataset;
import org.mc.client.client.object.Filter;
import org.mc.client.client.object.GuiAccessPoint;
import org.mc.client.client.object.GuiContainer;
import org.mc.client.client.object.Mart;
import org.mc.client.client.object.McUser;
import org.mc.client.client.object.Registry;
import org.mc.client.client.object.SourceContainer;

public interface IDao {
	public Object addRegistry(Registry registry);
	public Object addMart(Mart mart);
	public Object addConfig(Config config);
	
	public Object addContainer(Container container);
	public Object addAttribute(Attribute attribute);
	public Object addFilter(Filter filter);
	public Object addContainerRecursively(Container container);
	
	public Collection<String> getAllRegistrysForUser(String username);
	
	public void deleteRegistry(String user, String registry);
	public boolean registryExist(String user, String name);
	//user is the root node for the application
	public McUser getUser(String name);	
	
	public Collection<Mart> getMarts(String user, String registry);
	public Registry getRegistry();
	public Mart getMartByName(String name);
	public Config getAccessPoint(String mart, String accesspoint);
	public Collection<SourceContainer> getSourceContainers();
	//public Filter getFilter(String )
	public Container getRootContainerRecursively(String mart, String accesspoint);
	public Collection<Dataset> getDatasets(String martname);
	public Dataset getDataset(String mart, String dsname);
	public GuiContainer getRootGuiContainer(String user, String registry);
	public Collection<GuiAccessPoint> getGuiAccessPoints(GuiContainer gc);

}