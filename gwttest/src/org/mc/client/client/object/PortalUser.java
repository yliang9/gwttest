package org.mc.client.client.object;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="PORTALUSER")
public class PortalUser extends LiteObject {
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
	
	@Column(name="GROUPID")
	public long getParentid() {
		return this.parentid;
	}
	
	public void setParentid(long id) {
		this.parentid = id;
	}
	
	@Column(name="PASSWORD")
	public String getPassword() {
		return this.getPropertyValue(XMLElements.PASSWORD);
	}
	
	public void setPassword(String value) {
		this.setProperty(XMLElements.PASSWORD, value);
	}
	
	@Column(name="HIDE")
	public boolean isHidden() {
		return super.isHidden();
	}
	
	public void setHidden(boolean b) {
		super.setHidden(b);
	}
	
	@Column(name="OPENID")
	public String getOpenid() {
		return this.getPropertyValue(XMLElements.OPENID);
	}
	
	public void setOpenid(String value) {
		this.setProperty(XMLElements.OPENID, value);
	}

}