package org.mc.client.client.object;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;



@Entity
@Table(name="USERGROUP")
public class UserGroup extends McLiteObject {
	
	private int ord;
	private long registryid;
	
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

	@Column(name="ORD")
	public int getOrd() {
		return this.ord;
	}
	
	public void setOrd(int ord) {
		this.ord = ord;
	}
	
	@Column(name="REGISTRYID")
	public long getRegistryid() {
		return this.registryid;
	}
	
	public void setRegistryid(long id) {
		this.registryid = id;
	}

	@Column(name="LOCATION")
	public String getLocation() {
		return this.getPropertyValue(XMLElements.LOCATION);
	}
	
	public void setLocation(String value) {
		this.setProperty(XMLElements.LOCATION, value);
	}
	
	@Column(name="DISPLAYNAME")
	public String getDisplayname() {
		return this.getPropertyValue(XMLElements.DISPLAYNAME);
	}
	
	@Column(name="PASSWORD")
	public String getPassword() {
		return this.getPropertyValue(XMLElements.PASSWORD);
	}
	
	public void setPassword(String value) {
		this.setProperty(XMLElements.PASSWORD, value);
	}
	
	@Column(name="CONFIG")
	public String getConfig() {
		return this.getPropertyValue(XMLElements.CONFIG);
	}
	
	public void setConfig(String value) {
		this.setProperty(XMLElements.CONFIG, value);
	}
	
	@Column(name="HIDE")
	public boolean isHidden() {
		return super.isHidden();
	}
	
	public void setHidden(boolean b) {
		super.setHidden(b);
	}
}