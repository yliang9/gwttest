package org.mc.client.client;

import java.util.Collection;

import org.mc.client.client.object.GuiAccessPoint;
import org.mc.client.client.object.GuiContainer;
import org.mc.client.client.object.Mart;
import org.mc.client.client.object.SourceContainer;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ConfiguratorServiceAsync {
	public void getMarts(String user, String registry, AsyncCallback<Collection<Mart>> callback);
	public void getSourceContainers(String user, String registry, AsyncCallback<Collection<SourceContainer>> callback);
	public void getAllRegistrysForUser(String username, AsyncCallback<Collection<String>> callback);
	public void getRootGuiContainer(String username, String registry, AsyncCallback<GuiContainer> callback);
	public void getGuiAccessPoints(GuiContainer gc, AsyncCallback<Collection<GuiAccessPoint>> callback);
}
