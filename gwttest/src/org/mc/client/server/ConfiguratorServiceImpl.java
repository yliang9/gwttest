package org.mc.client.server;

import java.util.Collection;

import org.biomart.common.utils.McGuiUtils;
import org.biomart.configurator.controller.RequestHandler;
import org.mc.client.client.ConfiguratorService;
import org.mc.client.client.object.McConfig;
import org.mc.client.client.object.Container;
import org.mc.client.client.object.GuiAccessPoint;
import org.mc.client.client.object.GuiContainer;
import org.mc.client.client.object.Mart;
import org.mc.client.client.object.SourceContainer;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ConfiguratorServiceImpl extends RemoteServiceServlet implements ConfiguratorService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Collection<Mart> getMarts(String user, String registry) {
		McGuiUtils.INSTANCE.setUser();
		McGuiUtils.INSTANCE.setRegistryName(registry);
		return RequestHandler.getInstance().getMarts();
	}

	@Override
	public Collection<SourceContainer> getSourceContainers(String user,
			String registry) {
		McGuiUtils.INSTANCE.setUser();
		McGuiUtils.INSTANCE.setRegistryName(registry);
		return RequestHandler.getInstance().getSourceContainers();
	}

	@Override
	public Collection<String> getAllRegistrysForUser(String username) {
		McGuiUtils.INSTANCE.setUser();
		return RequestHandler.getInstance().getAllRegistrysForUser(username);
	}

	@Override
	public GuiContainer getRootGuiContainer(String username, String registry) {
		return RequestHandler.getInstance().getRootGuiContainer(username, registry);
	}

	@Override
	public Collection<GuiAccessPoint> getGuiAccessPoints(GuiContainer gc) {
		return RequestHandler.getInstance().getGuiAccessPoints(gc);
	}

	@Override
	public Container getRootContainerRecursively(McConfig config) {
		return RequestHandler.getInstance().getRootContainerRecursively(config);
	}

	@Override
	public McConfig getMasterConfig(Mart mart) {
		return RequestHandler.getInstance().getMasterConfig(mart);
	}
}