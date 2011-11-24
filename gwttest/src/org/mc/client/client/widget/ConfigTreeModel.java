package org.mc.client.client.widget;

import org.mc.client.client.object.Config;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.TreeViewModel;

public class ConfigTreeModel implements TreeViewModel {

	private Config config;
	
	public ConfigTreeModel(Config config) {
		this.config = config;
	}
	
	@Override
	public <T> NodeInfo<?> getNodeInfo(T value) {
		if(this.config == null) {
			return new DefaultNodeInfo<String>(new ListDataProvider<String>(), new TextCell());
		}
			
		//load data on the fly
		if("root".equals(value)) {
			ListDataProvider<Config> dataProvider = new ListDataProvider<Config>(); 
	        dataProvider.getList().add(this.config);
	        return new DefaultNodeInfo<Config>(dataProvider, null);
		}
        ListDataProvider<String> dataProvider = new ListDataProvider<String>(); 
        dataProvider.getList().add(value + "." + String.valueOf(0));
        return new DefaultNodeInfo<String>(dataProvider, new TextCell());
	}

	@Override
	public boolean isLeaf(Object value) {
		return value==null || value.toString().length() > 10;	
	}
	
}