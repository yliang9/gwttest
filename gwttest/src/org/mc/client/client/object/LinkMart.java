package org.mc.client.client.object;



public class LinkMart extends LiteObject {

	public void setAttributes(String value){
		this.setProperty(XMLElements.ATTRIBUTES, value);
	}
	
	public String getAttributes(){
		return this.getPropertyValue(XMLElements.ATTRIBUTES);
	}
	
	public void setFilters(String value){
		this.setProperty(XMLElements.FILTERS, value);
	}
	
	public String getFilters(){
		return this.getPropertyValue(XMLElements.FILTERS);
	}
	
	public void setDatasets(String value){
		this.setProperty(XMLElements.DATASETS, value);
	}
	
	public String getDatasets(){
		return this.getPropertyValue(XMLElements.DATASETS);
	}
	
	public void setMart(String value){
		this.setProperty(XMLElements.MART, value);
	}
	
	public String getMart(){
		return this.getPropertyValue(XMLElements.MART);
	}
}
