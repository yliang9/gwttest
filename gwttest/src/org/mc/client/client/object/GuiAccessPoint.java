package org.mc.client.client.object;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;


@Entity
@Table(name="GUIACCESSPOINT")
public class GuiAccessPoint extends LiteObject{

	private int ord;
	private long parentid;
	@Id
	@GeneratedValue
	@Column(name="ID")
	public long getId() {
		if(this.getPropertyValue(XMLElements.ID).isEmpty())
			return 0;
		return Long.parseLong(this.getPropertyValue(XMLElements.ID));
	}
	
	public void setId(long id) {
		this.setProperty(XMLElements.ID, ""+id);
	}
	
	@Column(name="NAME")
	public String getName() {
		return super.getName();
	}
	
	@Column(name="DISPLAYNAME")
	public String getDisplayname() {
		return super.getName();
	}	

	@Column(name="ORD")
	public int getOrd() {
		return this.ord;
	}
	
	public void setOrd(int ord) {
		this.ord = ord;
	}
	
	@Column(name="PARENTID")
	public long getParentid() {
		return this.parentid;
	}
	
	public void setParentid(long id) {
		this.parentid = id;
	}

	@Column(name="INTERNALNAME")
	public String getInternalname() {
		return this.getPropertyValue(XMLElements.INTERNALNAME);
	}
	public void setInternalname(String value) {
		this.setProperty(XMLElements.INTERNALNAME, value);
	}
	@Column(name="MART")
	public String getMart() {
		return this.getPropertyValue(XMLElements.MART);
	}
	public void setMart(String value) {
		this.setProperty(XMLElements.MART, value);
	}
	@Column(name="ACCESSPOINT")
	public String getAccesspoint() {
		return this.getPropertyValue(XMLElements.ACCESSPOINT);
	}
	public void setAccesspoint(String value) {
		this.setProperty(XMLElements.ACCESSPOINT, value);
	}
	@Column(name="MCGROUP")
	public String getGroup() {
		return this.getPropertyValue(XMLElements.GROUP);
	}
	public void setGroup(String value) {
		this.setProperty(XMLElements.GROUP, value);
	}
	@Column(name="OPERATION")
	public String getOperation() {
		return this.getPropertyValue(XMLElements.OPERATION);
	}
	public void setOperation(String value) {
		this.setProperty(XMLElements.OPERATION, value);
	}
	@Column(name="ICON")
	public String getIcon() {
		return this.getPropertyValue(XMLElements.ICON);
	}
	public void setIcon(String value) {
		this.setProperty(XMLElements.ICON, value);
	}
	@Column(name="INDEPENDENTQUERYING")
	public boolean isIndependentquerying() {
		return Boolean.parseBoolean(this.getPropertyValue(XMLElements.INDEPENDENTQUERYING));
	}
	public void setIndependentquerying(boolean value) {
		this.setProperty(XMLElements.INDEPENDENTQUERYING, Boolean.toString(value));
	}
	@Column(name="INUSERS")
	public String getInusers() {
		return this.getPropertyValue(XMLElements.INUSERS);
	}
	public void setInusers(String value) {
		this.setProperty(XMLElements.INUSERS, value);
	}
	@Column(name="DESCRIPTION")
	public String getDescription() {
		return this.getPropertyValue(XMLElements.DESCRIPTION);
	}
	public void setDescription(String value) {
		this.setProperty(XMLElements.DESCRIPTION, value);
	}
}