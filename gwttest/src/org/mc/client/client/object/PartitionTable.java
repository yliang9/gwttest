package org.mc.client.client.object;

import java.util.List;

import org.biomart.configurator.controller.RequestHandler;

public class PartitionTable extends McLiteObject {
	 private List<List<String>> table;
	 
	 public void setMart(String mart) {
		 this.setProperty(XMLElements.MART, mart);
	 }
	 
	 public String getMart() {
		 return this.getPropertyValue(XMLElements.MART);
	 }
	 
	 public void setTable(List<List<String>> value) {
		 this.table = value;
	 }
	 
	 public List<List<String>> getTable() {
		 return this.table;
	 }
	 
	 public int getRowCount() {
		 return this.table.size();
	 }
	 
	 public String getValue(int row, int col) {
		 return this.table.get(row).get(col);
	 }
	 
	 public void setValue(int row, int col, String value) {
		// RequestHandler.getInstance().requestSetPartitionTableValue(this.getMart(), "main".equals(this.getName()), row, col, value);
	 }
	 
	public String getDatasetName(int row) {
		boolean main = !"p0".equals(this.getName());
		if(main)
			return this.table.get(row).get(PartitionUtils.DATASETNAME);
		else
			return this.table.get(row).get(0);
	}
	
	public boolean isRowVisible(int row) {
		if("p0".equals(this.getName())) {
			return true;
		}
		else {
			return !Boolean.parseBoolean(this.table.get(row).get(PartitionUtils.HIDE));
		}
	}
	
	public int getRowNumberByDatasetName(String ds) {
		for(int i=0; i<this.table.size(); i++) {
			if(this.getDatasetName(i).equals(ds))
				return i;			
		}
		return -1;
	}
	
	public int getRowNumberByDatasetSuffix(String suffix) {		
		for(int i=0; i<this.table.size(); i++) {
			if(this.getDatasetName(i).endsWith(suffix))
				return i;					
		}
		return -1;
	}
}