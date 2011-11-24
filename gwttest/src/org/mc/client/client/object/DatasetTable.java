package org.mc.client.client.object;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="MCTABLE")
public class DatasetTable extends McLiteObject {

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
	
	@Column(name="HIDE")
	public boolean isHidden() {
		return super.isHidden();
	}
	
	public void setHidden(boolean b) {
		super.setHidden(b);
	}
	@Column(name="INPARTITIONS")
	public String getInPartitions() {
		return this.getPropertyValue(XMLElements.INPARTITIONS);
	}
	public void setInPartitions(String value) {
		this.setProperty(XMLElements.INPARTITIONS, value);
	}
	@Column(name="TYPE")
	public String getType() {
		return this.getPropertyValue(XMLElements.TYPE);
	}
	public void setType(String value) {
		this.setProperty(XMLElements.TYPE, value);
	}
	@Column(name="SUBPARTITION")
	public String getSubpartition() {
		return this.getPropertyValue(XMLElements.SUBPARTITION);
	}
	public void setSubpartition(String value) {
		this.setProperty(XMLElements.SUBPARTITION, value);
	}
}