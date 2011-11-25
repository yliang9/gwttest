package org.mc.client.client.object;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


public abstract class McLiteObject implements Serializable {
	
	//default is true
	private boolean master = true;
	//default is valid
	private boolean valid = true;
	
	public void setMaster(boolean b) {
		this.master = b;
	}
	
	public boolean isMaster() {
		return this.master;
	}
	
	private Map<String,String> properties;
	
	protected McLiteObject() {
		this.properties = new LinkedHashMap<String,String>();
	}
	
	public String getName() {
		return this.getPropertyValue(XMLElements.NAME);
	}
	public void setName(String value) {
		this.setProperty(XMLElements.NAME, value);
	}
	public String getDisplayname() {
		return this.getPropertyValue(XMLElements.DISPLAYNAME);
	}
	public void setDisplayname(String value) {
		this.setProperty(XMLElements.DISPLAYNAME, value);
	}
	public boolean isHidden() {
		return Boolean.parseBoolean( this.getPropertyValue(XMLElements.HIDE));
	}

	public void setHidden(boolean hide) {
		this.setProperty(XMLElements.HIDE, Boolean.toString(hide));
	}
	
	public void setInternalname(String value) {
		this.setProperty(XMLElements.INTERNALNAME, value);
	}
	
	public void setDescription(String value) {
		this.setProperty(XMLElements.DESCRIPTION, value);
	}
	
	public String toString() {
		String result = this.getDisplayname();
		if("".equals(result))
			result = this.getName();
		return result;
	}
	
	public McNodeType getNodeType() {
		if(this instanceof Attribute)
			return McNodeType.ATTRIBUTE;
		else if(this instanceof LiteColumn) 
			return McNodeType.COLUMN;
		else if(this instanceof McConfig)
			return McNodeType.CONFIG;
		else if(this instanceof Container)
			return McNodeType.CONTAINER;
		else if(this instanceof DatasetTable)
			return McNodeType.TABLE;
		else if(this instanceof Filter)
			return McNodeType.FILTER;
		else if(this instanceof GuiContainer)
			return McNodeType.GUICONTAINER;
		else if(this instanceof GuiAccessPoint)
			return McNodeType.GUIACCESSPOINT;
		else if(this instanceof Key)
			return McNodeType.PRIMARYKEY;
		else if(this instanceof Mart)
			return McNodeType.MART;
		else if(this instanceof Portable)
			return McNodeType.IMPORTABLE;
		else if(this instanceof Relation)
			return McNodeType.RELATION;
		else if(this instanceof Link)
			return McNodeType.LINK;
		else if(this instanceof LinkMart)
			return McNodeType.LINKMART;
		else if(this instanceof Registry)
			return McNodeType.MARTREGISTRY;
		else if(this instanceof Dataset)
			return McNodeType.DATASET;
		else if(this instanceof PartitionTable)
			return McNodeType.PARTITIONTABLE;
		else if(this instanceof PartitionRow) 
			return McNodeType.PARTITIONROW;
		else if(this instanceof SourceContainer)
			return McNodeType.SOURCECONTAINER;
		else if(this instanceof UserGroup)
			return McNodeType.GROUP;
		else if(this instanceof McUser)
			return McNodeType.MCUSER;
		else 
			return null;
	}
	
	public String getPropertyValue(XMLElements key) {
		String result = this.properties.get(key.toString());
		if(null==result)
			return XMLElements.NA.toString();
		else
			return result;
	}
	
	public void setProperty(XMLElements key, String value) {
		this.properties.put(key.toString(), value);
	}
	
	public boolean isValid() {
		return this.valid;
	}
	
	public void setValid(boolean b) {
		this.valid = b;
	}
	
	public Map<String,String> getProperties() {
		return this.properties;
	}
}