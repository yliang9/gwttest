package org.mc.client.client.object;


public class Portable extends LiteObject {
	private String type;
	private boolean portableType;
	private String parent;
	private String referee;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isPortableType() {
		return portableType;
	}
	public void setImportableType(boolean portableType) {
		this.portableType = portableType;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getReferee() {
		return referee;
	}
	public void setReferee(String referee) {
		this.referee = referee;
	}
}