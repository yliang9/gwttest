package org.mc.client.client.widget;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mc.client.client.ConfiguratorService;
import org.mc.client.client.ConfiguratorServiceAsync;
import org.mc.client.client.object.Attribute;
import org.mc.client.client.object.Containable;
import org.mc.client.client.object.Container;
import org.mc.client.client.object.Filter;
import org.mc.client.client.object.McConfig;
import org.mc.client.client.object.McLiteObject;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.TreeViewModel;

public class ConfigTreeModel implements TreeViewModel {

	private McConfig config;
	private Container rootContainer;
	private final CellTable<List<String>> propertyTable;
	private final SingleSelectionModel<McLiteObject> selectionModel;
	
	public ConfigTreeModel(McConfig config, Container rootContainer, final CellTable<List<String>> propertyTable) {
		this.config = config;
		this.rootContainer = rootContainer;
		this.propertyTable = propertyTable;
		this.selectionModel = new SingleSelectionModel<McLiteObject>();
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {

			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				SingleSelectionModel<McLiteObject> source = (SingleSelectionModel<McLiteObject>)event.getSource();
				//update table
				List<List<String>> list = new ArrayList<List<String>>();
				for(Map.Entry<String, String> entry: source.getSelectedObject().getProperties().entrySet()) {
					List<String> item = new ArrayList<String>();
					item.add(entry.getKey());
					item.add(entry.getValue());
					list.add(item);
				}
				propertyTable.setRowData(list);
			}
			
		});
	}
	
	@Override
	public <T> NodeInfo<?> getNodeInfo(T value) {
		if(this.config == null) {
			return new DefaultNodeInfo<String>(new ListDataProvider<String>(), new TextCell());
		}
			
		//load data on the fly
		if("root".equals(value)) {
			ListDataProvider<McLiteObject> dataProvider = new ListDataProvider<McLiteObject>(); 
	        dataProvider.getList().add(this.config);
	        return new DefaultNodeInfo<McLiteObject>(dataProvider, new McLiteObjectCell(),selectionModel,null);
		} else if(value instanceof McConfig) {
			ListDataProvider<McLiteObject> dataProvider = new ListDataProvider<McLiteObject>(); 			
	        dataProvider.getList().add(this.rootContainer);
	        return new DefaultNodeInfo<McLiteObject>(dataProvider, new McLiteObjectCell(),selectionModel,null);					
		} else if(value instanceof Container) {
			Container container = (Container) value;
			ListDataProvider<McLiteObject> dataProvider = new ListDataProvider<McLiteObject>(); 
			for(Containable c :container.getContainables()) {
				dataProvider.getList().add(c);
			}
	        return new DefaultNodeInfo<McLiteObject>(dataProvider, new McLiteObjectCell(),selectionModel,null);					
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