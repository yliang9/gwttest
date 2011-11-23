package org.mc.client.client;


import java.util.Collection;

import org.mc.client.client.object.GuiAccessPoint;
import org.mc.client.client.object.GuiContainer;
import org.mc.client.client.object.Mart;
import org.mc.client.client.object.SourceContainer;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("configurator")
public interface ConfiguratorService extends RemoteService {
	public Collection<Mart> getMarts(String user, String registry);
	public Collection<SourceContainer> getSourceContainers(String user, String registry);
	public Collection<String> getAllRegistrysForUser(String username);
	public GuiContainer getRootGuiContainer(String username, String registry);
	public Collection<GuiAccessPoint> getGuiAccessPoints(GuiContainer gc);
}