package org.mc.client.client.object;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Column;
import javax.xml.bind.annotation.XmlRootElement;

import org.biomart.common.utils.McUtils;


@Entity
@Table(name="ATTRIBUTE")
@XmlRootElement(name="attribute")
public class Attribute extends Containable {

	private int ord;
	private long containerid;
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
	public String getContainer() {
		return this.getPropertyValue(XMLElements.CONTAINER);
	}
	public void setContainer(String value) {
		this.setProperty(XMLElements.CONTAINER, value);
	}
	
	@Column(name="CONFIG")
	public String getConfig() {
		return this.getPropertyValue(XMLElements.CONFIG);
	}
	public void setConfig(String value) {
		this.setProperty(XMLElements.CONFIG, value);
	}
	@Column(name="MART")
	public String getMart() {
		return this.getPropertyValue(XMLElements.MART);
	}
	public void setMart(String value) {
		this.setProperty(XMLElements.MART, value);
	}
	@Column(name="MCTABLE")
	public String getTable() {
		return this.getPropertyValue(XMLElements.TABLE);
	}
	public void setTable(String value) {
		this.setProperty(XMLElements.TABLE, value);
	}
	@Column(name="MCCOLUMN")
	public String getColumn() {
		return this.getPropertyValue(XMLElements.COLUMN);
	}
	public void setColumn(String value) {
		this.setProperty(XMLElements.COLUMN, value);
	}
	@Column(name="POINTEDATTRIBUTE")
	public String getPointedattribute() {
		return this.getPropertyValue(XMLElements.POINTEDATTRIBUTE);
	}
	public void setPointedattribute(String value) {
		this.setProperty(XMLElements.POINTEDATTRIBUTE, value);
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
	@Column(name="ATTRIBUTELIST")
	public String getAttributelist() {
		return this.getPropertyValue(XMLElements.ATTRIBUTELIST);
	}
	public void setAttributelist(String value) {
		this.setProperty(XMLElements.ATTRIBUTELIST, value);
	}
	@Column(name="LINKOUTURL")
	public String getLinkouturl() {
		return this.getPropertyValue(XMLElements.LINKOUTURL);
	}
	public void setLinkouturl(String value) {
		this.setProperty(XMLElements.LINKOUTURL, value);
	}
	@Column(name="VALUE")
	public String getValue() {
		return this.getPropertyValue(XMLElements.VALUE);
	}
	public void setValue(String value) {
		this.setProperty(XMLElements.VALUE, value);
	}
	@Transient
	public String getInusers() {
		return this.getPropertyValue(XMLElements.INUSERS);
	}
	
	public void setInusers(String value) {
		this.setProperty(XMLElements.INUSERS, value);
	}
	@Transient
	public String getRdf() {
		return this.getPropertyValue(XMLElements.RDF);
	}
	public void setRdf(String value) {
		this.setProperty(XMLElements.RDF, value);
	}
	@Column(name="DATATYPE")
	public String getDatatype() {
		return this.getPropertyValue(XMLElements.DATATYPE);
	}
	public void setDatatype(String value) {
		this.setProperty(XMLElements.DATATYPE, value);
	}
	@Transient
	public String getPointerinsource() {
		return this.getPropertyValue(XMLElements.POINTERINSOURCE);
	}
	public void setPointerinsource(String value) {
		this.setProperty(XMLElements.POINTERINSOURCE, value);
	}
	@Column(name="DEFAULTSELECTED")
	public boolean getDefaultvalue() {
		return Boolean.parseBoolean(this.getPropertyValue(XMLElements.DEFAULTSELECTED));
	}
	public void setDefaultvalue(boolean value) {
		this.setProperty(XMLElements.DEFAULTSELECTED, Boolean.toString(value));
	}
	@Transient
	public boolean isAttributeList() {
		return !XMLElements.NA.toString().equals(this.getPropertyValue(XMLElements.ATTRIBUTELIST));
	}
	@Transient
	public boolean isPointer() {
		return /*(!XMLElements.NA.toString().equals(this.getPropertyValue(XMLElements.POINTEDMART))) 
				&& (!XMLElements.NA.toString().equals(this.getPropertyValue(XMLElements.POINTEDCONFIG))) 
				&&*/ (!XMLElements.NA.toString().equals(this.getPointedattribute())) && 
				(!XMLElements.NA.toString().equals(this.getPointeddataset()));
	}
	@Transient
	public boolean isPseudo() {
		return !this.getValue().isEmpty();
	}
	
	@Override
	public void add(Containable containable) {
		// TODO Auto-generated method stub		
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
	
	public void setContainerid(long cid) {
		this.containerid = cid;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(null==obj || !(obj instanceof Attribute))
			return false;
		Attribute container = (Attribute)obj;
		return (this.getMart().equals(container.getMart()) && this.getConfig().equals(container.getConfig()) 
				&& this.getName().equals(container.getName()));
	}
	
	@Override
	public int hashCode() {
		return this.getName().hashCode();
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