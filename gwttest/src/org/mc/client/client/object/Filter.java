package org.mc.client.client.object;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Column;
import javax.xml.bind.annotation.XmlRootElement;



@Entity
@Table(name="FILTER")
@XmlRootElement(name="filter")
public class Filter extends Containable {
	private long containerid;
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
	
	@Transient
	public String getDefaultvalue() {
		return this.getPropertyValue(XMLElements.DEFAULT);
	}
	public void setDefaultvalue(String value) {
		this.setProperty(XMLElements.DEFAULT, value);
	}
	@Column(name="MART")
	public String getMart() {
		return this.getPropertyValue(XMLElements.MART);
	}
	public void setMart(String value) {
		this.setProperty(XMLElements.MART, value);
	}
	@Column(name="CONFIG")
	public String getConfig() {
		return this.getPropertyValue(XMLElements.CONFIG);
	}
	public void setConfig(String value) {
		this.setProperty(XMLElements.CONFIG, value);
	}
	@Transient
	public String getTable() {
		return this.getPropertyValue(XMLElements.TABLE);
	}
	public void setTable(String value) {
		this.setProperty(XMLElements.TABLE, value);
	}
	@Transient
	public String getColumn() {
		return this.getPropertyValue(XMLElements.COLUMN);
	}
	public void setColumn(String value) {
		this.setProperty(XMLElements.COLUMN, value);
	}
	@Column(name="TYPE")
	public String getType() {
		return this.getPropertyValue(XMLElements.TYPE);
	}
	public void setType(String value) {
		this.setProperty(XMLElements.TYPE, value);
	}
	@Column(name="SPLITON")
	public String getSpliton() {
		return this.getPropertyValue(XMLElements.SPLITON);
	}
	public void setSpliton(String value) {
		this.setProperty(XMLElements.SPLITON, value);
	}
	@Column(name="OPERATION")
	public String getOperation() {
		return this.getPropertyValue(XMLElements.OPERATION);
	}
	public void setOperation(String value) {
		this.setProperty(XMLElements.OPERATION, value);
	}
	@Column(name="DATAFILE")
	public String getDatafile() {
		return this.getPropertyValue(XMLElements.DATAFILE);
	}
	public void setDatafile(String value) {
		this.setProperty(XMLElements.DATAFILE, value);
	}
	@Column(name="FILTERLIST")
	public String getFilterlist() {
		return this.getPropertyValue(XMLElements.FILTERLIST);
	}
	public void setFilterlist(String value) {
		this.setProperty(XMLElements.FILTERLIST, value);
	}
	@Column(name="QUALIFIER")
	public String getQualifier() {
		return this.getPropertyValue(XMLElements.QUALIFIER);
	}
	public void setQualifier(String value) {
		this.setProperty(XMLElements.QUALIFIER, value);
	}
	@Column(name="POINTEDMART")
	public String getPointedmart() {
		return this.getPropertyValue(XMLElements.POINTEDMART);
	}
	public void setPointedmart(String value) {
		this.setProperty(XMLElements.POINTEDMART, value);
	}
	@Column(name="POINTEDCONFIG")
	public String getPointedconfig() {
		return this.getPropertyValue(XMLElements.POINTEDCONFIG);
	}
	public void setPointedconfig(String value) {
		this.setProperty(XMLElements.POINTEDCONFIG, value);
	}
	@Column(name="POINTEDDATASET")
	public String getPointeddataset() {
		return this.getPropertyValue(XMLElements.POINTEDDATASET);
	}
	public void setPointeddataset(String value) {
		this.setProperty(XMLElements.POINTEDDATASET, value);
	}
	@Column(name="POINTEDFILTER")
	public String getPointedfilter() {
		return this.getPropertyValue(XMLElements.POINTEDFILTER);
	}
	public void setPointedfilter(String value) {
		this.setProperty(XMLElements.POINTEDFILTER, value);
	}
	@Column(name="ONLY")
	public String getOnly() {
		return this.getPropertyValue(XMLElements.ONLY);
	}
	public void setOnly(String value) {
		this.setProperty(XMLElements.ONLY, value);
	}
	@Column(name="EXCLUDED")
	public String getExcluded() {
		return this.getPropertyValue(XMLElements.EXCLUDED);
	}
	public void setExcluded(String value) {
		this.setProperty(XMLElements.EXCLUDED, value);
	}
	@Column(name="REFCONTAINER")
	public String getRefcontainer() {
		return this.getPropertyValue(XMLElements.REFCONTAINER);
	}
	public void setRefcontainer(String value) {
		this.setProperty(XMLElements.REFCONTAINER, value);
	}
	@Transient
	public String getInusers() {
		return this.getPropertyValue(XMLElements.INUSERS);
	}
	public void setInusers(String value) {
		this.setProperty(XMLElements.INUSERS, value);
	}
	@Column(name="DEPENDSON")
	public String getDependson() {
		return this.getPropertyValue(XMLElements.DEPENDSON);
	}
	public void setDependson(String value) {
		this.setProperty(XMLElements.DEPENDSON, value);
	}
	@Transient
	public String getRdf() {
		return this.getPropertyValue(XMLElements.RDF);
	}
	public void setRdf(String value) {
		this.setProperty(XMLElements.RDF, value);
	}
	@Column(name="REQUIRED")
	public boolean isRequired() {
		return Boolean.parseBoolean(this.getPropertyValue(XMLElements.REQUIRED));
	}
	public void setRequired(boolean b) {
		this.setProperty(XMLElements.REQUIRED, Boolean.toString(b));
	}


	@Transient
	public String getPointerinsource() {
		return this.getPropertyValue(XMLElements.POINTERINSOURCE);
	}
	public void setPointerinsource(String value) {
		this.setProperty(XMLElements.POINTERINSOURCE, value);
	}
	
	public boolean hasDropdown(){
		if(this.getType().equals(FilterType.MULTISELECT.toString())
				|| this.getType().equals(FilterType.SINGLESELECT.toString()))
			return true;
		
		return false;
	}
	@Transient
	public boolean isFilterList() {
		return !XMLElements.NA.toString().equals(this.getFilterlist());
	}
	@Column(name="POINTER")
	public boolean isPointer() {
		return Boolean.parseBoolean(this.getPropertyValue(XMLElements.POINTER));
	}
	
	public void setPointer(boolean b) {
		this.setProperty(XMLElements.POINTER, Boolean.toString(b));
	}
	public String getAttribute() {
		return this.getPropertyValue(XMLElements.ATTRIBUTE);
	}
	@Column(name="ATTRIBUTE")
	public void setAttribute(String value) {
		this.setProperty(XMLElements.ATTRIBUTE, value);
	}
	
	
	@Override
	public void add(Containable containable) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean equals(Object obj) {
		if(null==obj || !(obj instanceof Filter))
			return false;
		Filter container = (Filter)obj;
		return (this.getMart().equals(container.getMart()) && this.getConfig().equals(container.getConfig()) 
				&& this.getName().equals(container.getName()));
	}
	
	@Override
	public int hashCode() {
		return this.getName().hashCode();
	}
	
	@Column(name="ORD")
	public int getOrd() {
		return this.ord;
	}
	
	public void setOrd(int ord) {
		this.ord = ord;
	}
	
	@Column(name="CONTAINERID")
	public long getContainerid() {
		return this.containerid;
	}
	
	public void setContainerid(long id) {
		this.containerid = id;
	}
	@Column(name="REGISTRYID")
	public long getRegistryid() {
		return registryid;
	}

	public void setRegistryid(long registryid) {
		this.registryid = registryid;
	}
	
	@Column(name="VALID")
	public boolean isValid() {
		return super.isValid();
	}

}