package org.mc.client.client.object;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="PARTITIONROW")
public class PartitionRow extends LiteObject {
	private int ord;
	private long martid;
	
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
	
	@Column(name="MARTID")
	public long getMartid() {
		return this.martid;
	}
	
	public void setMartid(long id) {
		this.martid = id;
	}
	
	@Column(name="VALUE")
	public String getValue() {
		return this.getPropertyValue(XMLElements.VALUE);
	}
	
	public void setValue(String value) {
		this.setProperty(XMLElements.VALUE, value);
	}
}