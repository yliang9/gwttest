package org.mc.client.client.object;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;



@Entity
@Table(name="CONFIG")
@XmlRootElement(name="config")
public class Config extends LiteObject {
	
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
	
	@Column(name="DISPLAYNAME")
	public String getDisplayname() {
		return super.getDisplayname();
	}
	
	@Column(name="INTERNALNAME")
	public String getInternalname() {
		return this.getPropertyValue(XMLElements.INTERNALNAME);
	}
	
	@Column(name="DESCRIPTION")
	public String getDescription() {
		return this.getPropertyValue(XMLElements.DESCRIPTION);
	}
	
	@Column(name="HIDE")
	public boolean isHidden() {
		return Boolean.parseBoolean(this.getPropertyValue(XMLElements.HIDE));
	}
	
	public void setHidden(boolean value) {
		super.setHidden(value);
	}
	
	@Column(name="DEFAULTCONFIG")
	public boolean getDefault() {
		return Boolean.parseBoolean(this.getPropertyValue(XMLElements.DEFAULT));
	}
	public void setDefault(boolean value) {
		this.setProperty(XMLElements.DEFAULT, Boolean.toString(value));
	}
	
	@Column(name="DATASETHIDEVALUE")
	public String getDatasethidevalue() {
		return this.getPropertyValue(XMLElements.DATASETHIDEVALUE);
	}
	public void setDatasethidevalue(String value) {
		this.setProperty(XMLElements.DATASETHIDEVALUE, value);
	}
	@Column(name="MASTER")
	public boolean getMaster() {
		return Boolean.parseBoolean(this.getPropertyValue(XMLElements.MASTER));
	}
	public void setMaster(boolean b) {
		this.setProperty(XMLElements.MASTER, Boolean.toString(b));
	}
	
	@Column(name="DATASETDISPLAYNAME")
	public String getDatasetdisplayname() {
		return this.getPropertyValue(XMLElements.DATASETDISPLAYNAME);
	}
	public void setDatasetdisplayname(String value) {
		this.setProperty(XMLElements.DATASETDISPLAYNAME, value);
	}
	@Column(name="METAINFO")
	public String getMetainfo() {
		return this.getPropertyValue(XMLElements.METAINFO);
	}
	public void setMetainfo(String value) {
		this.setProperty(XMLElements.METAINFO, value);
	}
	@Column(name="READONLY")
	public boolean isReadonly() {
		return Boolean.parseBoolean(this.getPropertyValue(XMLElements.READONLY));
	}
	public void setReadonly(boolean b) {
		this.setProperty(XMLElements.READONLY, Boolean.toString(b));
	}
	@Column(name="MCPASSWORD")
	public String getPassword() {
		return this.getPropertyValue(XMLElements.PASSWORD);
	}
	public void setPassword(String value) {
		this.setProperty(XMLElements.PASSWORD, value);
	}
	@Transient
	public String getRdfclass() {
		return this.getPropertyValue(XMLElements.RDF);
	}
	public void setRdfclass(String value) {
		this.setProperty(XMLElements.RDF, value);
	}
	
	@Transient
	public String getMart() {
		return this.getPropertyValue(XMLElements.MART);
	}
	public void setMart(String value) {
		this.setProperty(XMLElements.MART, value);
	}
	
	@Column(name="ORD")
	public int getOrd() {
		return this.ord;
	}
	
	public void setOrd(int value) {
		this.ord = value;
	}
	
	@Column(name="MARTID")
	public long getMartid() {
		return this.martid;
	}
	
	public void setMartid(long id) {
		this.martid = id;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj ==null || !(obj instanceof Config))
			return false;
		Config config = (Config)obj;
		return this.getMart().equals(config.getMart()) && 
				this.getName().equals(config.getName());
	}
	
	@Override
	public int hashCode() {
		return this.getName().hashCode();
	}
	
	@Override
	public String toString() {
		String result = this.getDisplayname();
		if("".equals(result))
			result = this.getName();
		result += " (" + this.getName() +")";
		return result;
	}
	
	@Override
	public boolean isMaster() {
		return Boolean.parseBoolean(this.getPropertyValue(XMLElements.MASTER));
	}
}