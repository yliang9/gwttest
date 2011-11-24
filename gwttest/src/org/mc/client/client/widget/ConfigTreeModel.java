package org.mc.client.client.widget;

import org.mc.client.client.ConfiguratorService;
import org.mc.client.client.ConfiguratorServiceAsync;
import org.mc.client.client.object.Attribute;
import org.mc.client.client.object.Containable;
import org.mc.client.client.object.Container;
import org.mc.client.client.object.Filter;
import org.mc.client.client.object.McConfig;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.TreeViewModel;

public class ConfigTreeModel implements TreeViewModel {

	private McConfig config;
	private Container rootContainer;
	
	public ConfigTreeModel(McConfig config, Container rootContainer) {
		this.config = config;
		this.rootContainer = rootContainer;
	}
	
	@Override
	public <T> NodeInfo<?> getNodeInfo(T value) {
		if(this.config == null) {
			return new DefaultNodeInfo<String>(new ListDataProvider<String>(), new TextCell());
		}
			
		//load data on the fly
		if("root".equals(value)) {
			ListDataProvider<McConfig> dataProvider = new ListDataProvider<McConfig>(); 
	        dataProvider.getList().add(this.config);
	        return new DefaultNodeInfo<McConfig>(dataProvider, new McConfigCell());
		} else if(value instanceof McConfig) {
			ListDataProvider<Containable> dataProvider = new ListDataProvider<Containable>(); 			
	        dataProvider.getList().add(this.rootContainer);
	        return new DefaultNodeInfo<Containable>(dataProvider, new ContainableCell());					
		} else if(value instanceof Container) {
			Container container = (Container) value;
			ListDataProvider<Containable> dataProvider = new ListDataProvider<Containable>(); 
			for(Containable c :container.getContainables()) {
				dataProvider.getList().add(c);
			}
	        return new DefaultNodeInfo<Containable>(dataProvider, new ContainableCell());					
		} else {
			return new DefaultNodeInfo<String>(new ListDataProvider<String>(), new TextCell());
		}
	}

	@Override
	public boolean isLeaf(Object value) {
		return value==null || ((value instanceof Container) && ((Container)value).getContainables().isEmpty())
				|| (value instanceof Attribute) || (value instanceof Filter);	
	}
	

	
}