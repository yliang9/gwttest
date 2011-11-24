package org.mc.client.client.widget;

import org.mc.client.client.object.McConfig;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.TreeViewModel;

public class ConfigTreeModel implements TreeViewModel {

	private McConfig config;
	
	public ConfigTreeModel(McConfig config) {
		this.config = config;
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