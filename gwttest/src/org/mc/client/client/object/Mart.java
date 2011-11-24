package org.mc.client.client.object;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.xml.bind.annotation.XmlRootElement;



@Entity
@Table(name="MART")
@XmlRootElement(name="mart")
public class Mart extends McLiteObject {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long registryid;
	private int ord;
	
	@Column(name="REGISTRYID")
	public long getRegistryid() {
		return this.registryid;
	}
	
	@Column(name="NAME")
	public String getName() {
		return super.getName();
	}
	
	@Column(name="DISPLAYNAME") 
	public String getDisplayname(){
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
	
	@Override
	@Column(name="HIDE")
	public boolean isHidden() {
		return super.isHidden();
	}
	
	public void setHidden(boolean value) {
		super.setHidden(value);
	}
	
	@Column(name="OPTIMISER")
	public String getOptimiser() {
		return this.getPropertyValue(XMLElements.OPTIMISER);
	}
	
	public void setOptimiser(String value) {
		this.setProperty(XMLElements.OPTIMISER, value);
	}
	
	@Column(name="INDEXOPTIMISED")
	public boolean getIndexoptimised() {
		return Boolean.parseBoolean(this.getPropertyValue(XMLElements.INDEXOPTIMISED));
	}
	
	public void setIndexoptimised(boolean value) {
		this.setProperty(XMLElements.INDEXOPTIMISED, Boolean.toString(value));
	}
	
	@Column(name="VIRTUAL")
	public boolean getVirtual() {
		return Boolean.parseBoolean(this.getPropertyValue(XMLElements.VIRTUAL));
	}
	
	public void setVirtual(boolean value) {
		this.setProperty(XMLElements.VIRTUAL, Boolean.toString(value));
	}
	
	@Column(name="CENTRALTABLE")
	public String getCentralTable() {
		return this.getPropertyValue(XMLElements.CENTRALTABLE);
	}
	
	public void setCentralTable(String value) {
		this.setProperty(XMLElements.CENTRALTABLE, value);
	}
	
	@Column(name="SELECTEDTABLES")
	public String getSelectedtables() {
		return this.getPropertyValue(XMLElements.SELECTEDTABLES);
	}
	
	public void setSelectedtables(String value) {
		this.setProperty(XMLElements.SELECTEDTABLES, value);
	}
	
	@Column(name="SEARCHFROMTARGET")
	public boolean getSearchfromtarget() {
		return Boolean.parseBoolean(this.getPropertyValue(XMLElements.SEARCHFROMTARGET));
	}
	
	public void setSearchfromtarget(boolean value) {
		this.setProperty(XMLElements.SEARCHFROMTARGET, Boolean.toString(value));
	}
	
	@Column(name="SOURCECONTAINER")
	public String getSourcecontainer() {
		return this.getPropertyValue(XMLElements.SOURCECONTAINER);
	}
	
	public void setSourcecontainer(String value) {
		this.setProperty(XMLElements.SOURCECONTAINER, value);
	}
	
	@Column(name="ORD")
	public int getOrd() {
		return this.ord;
	}
	
	public void setOrd(int value) {
		this.ord = value;
	}
	
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
	
	public void setRegistryid(long id) {
		this.registryid = id;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(null==obj || !(obj instanceof Mart))
			return false;
		return this.getName().equals(((Mart)obj).getName());
	}
	
	@Override
	public int hashCode() {
		return this.getName().hashCode();
	}
	
	public Mart() {
		
	}
}