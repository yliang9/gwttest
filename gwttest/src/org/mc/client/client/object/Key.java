package org.mc.client.client.object;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="MCKEY")
public class Key extends McLiteObject {

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

	@Column(name="MCCOLUMN")
	public String getColumn() {
		return this.getPropertyValue(XMLElements.COLUMN);
	}
	public void setColumn(String value) {
		this.setProperty(XMLElements.COLUMN, value);
	}
	@Transient
	public String getTable() {
		return this.getPropertyValue(XMLElements.TABLE);
	}
	public void setTable(String value) {
		this.setProperty(XMLElements.TABLE, value);
	}
	@Column(name="PK")
	public boolean isPrimary() {
		return Boolean.parseBoolean(this.getPropertyValue(XMLElements.PRIMARYKEY));
	}
	public void setPrimary(boolean value) {
		this.setProperty(XMLElements.PRIMARYKEY, Boolean.toString(value));
	}
	
	
}