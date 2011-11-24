package org.mc.client.client.object;

import javax.xml.bind.annotation.XmlRootElement;



public abstract class Containable extends McLiteObject {
	public String getMart() {
		return this.getPropertyValue(XMLElements.MART);
	}
	
	public String getConfig() {
		return this.getPropertyValue(XMLElements.CONFIG);
	}
	
	public abstract void add(Containable containable);

	
}
