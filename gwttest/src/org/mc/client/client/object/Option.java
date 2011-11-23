package org.mc.client.client.object;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.mc.client.client.object.LiteObject;

@Entity
@Table(name="FILTEROPTION")
@XmlRootElement(name="option")
public class Option extends LiteObject {
	private long filterid;
	private long id;
	private long datasetid;
	private String value;
	private long pushedby;
	private int order;
	
	@Id
	@GeneratedValue
	@Column(name="ID")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Column(name="FILTERID")
	public long getFilterid() {
		return filterid;
	}
	public void setFilterid(long filterid) {
		this.filterid = filterid;
	}
	@Column(name="DATASETID")
	public long getDatasetid() {
		return datasetid;
	}
	public void setDatasetid(long datasetid) {
		this.datasetid = datasetid;
	}
	@Column(name="DISPLAYNAME")
	public String getDisplayname() {
		return super.getDisplayname();
	}

	@Column(name="VALUE")
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Column(name="PUSHEDBY")
	public long getPushedby() {
		return pushedby;
	}
	public void setPushedby(long pushedby) {
		this.pushedby = pushedby;
	}
	@Column(name="ORD")
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}

}
