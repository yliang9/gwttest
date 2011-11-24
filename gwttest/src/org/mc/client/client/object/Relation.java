package org.mc.client.client.object;



public class Relation extends McLiteObject {

	private String parentname;
	private boolean inSource;
	public String getFirstkey() {
		return this.getPropertyValue(XMLElements.FIRSTKEY);
	}
	public void setFirstkey(String firstkey) {
		this.setProperty(XMLElements.FIRSTKEY, firstkey);
	}
	public String getFirsttable() {
		return this.getPropertyValue(XMLElements.FIRSTTABLE);
	}
	public void setFirsttable(String firsttable) {
		this.setProperty(XMLElements.FIRSTTABLE, firsttable);
	}
	public String getSecondkey() {
		return this.getPropertyValue(XMLElements.SECONDKEY);
	}
	public void setSecondkey(String secondkey) {
		this.setProperty(XMLElements.SECONDKEY, secondkey);
	}
	public String getSecondtable() {
		return this.getPropertyValue(XMLElements.SECONDTABLE);
	}
	public void setSecondtable(String secondtable) {
		this.setProperty(XMLElements.SECONDTABLE, secondtable);
	}
	public String getType() {
		return this.getPropertyValue(XMLElements.TYPE);
	}
	public void setType(String type) {
		this.setProperty(XMLElements.TYPE, type);
	}
	public String getParentname() {
		return parentname;
	}
	public void setParentname(String parentname) {
		this.parentname = parentname;
	}
	public boolean isInSource() {
		return inSource;
	}
	public void setInSource(boolean b) {
		this.inSource = b;
	}
}