package org.mc.client.client.object;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

@Entity
@Table(name="CONTAINER")
@XmlRootElement(name="container")
public class Container extends Containable {
	
	private long parentid;
	private int ord;
	private int level;
	
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
	
	@Column(name="PARENTID")
	public long getParentid() {
		return this.parentid;
	}
	
	public void setParentid(long id) {
		this.parentid = id;
	}
	
	@Column(name="HIDE")
	@Override
	public boolean isHidden() {
		return super.isHidden();
	}
	
	public void setHidden(boolean value) {
		super.setHidden(value);
	}
	
	@Column(name="MAXCONTAINERS")
	public int getMaxcontainers() {
		int qr = 0;
		try {
			qr =  Integer.parseInt(this.getPropertyValue(XMLElements.MAXCONTAINERS));
		}
		catch(NumberFormatException e) {
			qr = 0;
		}
		return qr;
	}
	
	@Column(name="ENABLESELECTALL")
	public boolean isSelectallenabled() {
		return Boolean.parseBoolean(this.getPropertyValue(XMLElements.ENABLESELECTALL));
	}
	
	public void setSelectallenabled(boolean value) {
		this.setProperty(XMLElements.ENABLESELECTALL, Boolean.toString(value));
	}
	
	@Column(name="ORD")
	public int getOrd() {
		return this.ord;
	}
	
	public void setOrd(int ord){
		this.ord = ord;
	}
	
	private List<Containable> containables;
	
	public Container() {
		this.containables = new ArrayList<Containable>();
	}
	
	@Transient
	public String getIndependentquerying() {
		return this.getPropertyValue(XMLElements.INDEPENDENTQUERYING);
	}
	public void setIndependentquerying(String value) {
		this.setProperty(XMLElements.INDEPENDENTQUERYING, value);
	}

	public void setMaxcontainers(int value) {
		this.setProperty(XMLElements.MAXCONTAINERS, ""+value);
	}
	@Column(name="MAXATTRIBUTES")
	public int getMaxattributes() {
		int qr = 0;
		try {
			qr =  Integer.parseInt(this.getPropertyValue(XMLElements.MAXATTRIBUTES));
		}
		catch(NumberFormatException e) {
			qr = 0;
		}
		return qr;
	}
	public void setMaxattributes(int value) {
		this.setProperty(XMLElements.MAXATTRIBUTES, ""+value);
	}

	@Column(name="LEVEL")
	public int getLevel() {
		return this.level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}

	public void setMartName(String name) {
		this.setProperty(XMLElements.MART, name);
	}
	
	@Transient
	public String getMartName() {
		return this.getPropertyValue(XMLElements.MART);
	}
	
	public void setConfigName(String name) {
		this.setProperty(XMLElements.CONFIG,name);
	}
	
	@Transient
	public String getConfigName() {
		return this.getPropertyValue(XMLElements.CONFIG);
	}


	@Override
	public void add(Containable containable) {
		this.containables.add(containable);
	}
	
	@Transient
	public Collection<Containable> getContainables() {
		return this.containables;
	}
	@Transient
    @XmlElement(name="attribute")
    @JsonProperty("attribute")
	public Collection<Attribute> getAttributes() {
		Collection<Attribute> result = new ArrayList<Attribute>();
		for(Containable c: this.containables) {
			if(c instanceof Attribute)
				result.add((Attribute)c);
		}
		return result;
	}
	
/*	public void setContainables(Collection<Containable> c) {
		this.containables.addAll(c);
	}*/
	
	private void setAttributes(Collection<Attribute> a) {
		this.containables.addAll(a);
	}
	
	@Transient
    @XmlElement(name="container")
    @JsonProperty("container")
	public Collection<Container> getContainers() {
		Collection<Container> result = new ArrayList<Container>();
		for(Containable c: this.containables) {
			if(c instanceof Container)
				result.add((Container)c);
		}
		return result;
	}

	private void setContainers(Collection<Container> a) {
		this.containables.addAll(a);
	}

	@Transient
    @XmlElement(name="filter")
    @JsonProperty("filter")
	public Collection<Filter> getFilters() {
		Collection<Filter> result = new ArrayList<Filter>();
		for(Containable c: this.containables) {
			if(c instanceof Filter)
				result.add((Filter)c);
		}
		return result;
	}

	private void setFilters(Collection<Filter> a) {
		this.containables.addAll(a);
	}


	
	@Override
	public boolean equals(Object obj) {
		if(null==obj || !(obj instanceof Container))
			return false;
		Container container = (Container)obj;
		return (this.getMart().equals(container.getMart()) && this.getConfig().equals(container.getConfig()) 
				&& this.getName().equals(container.getName()));
	}
	
	@Override
	public int hashCode() {
		return this.getName().hashCode();
	}
	
	@Override
	@Transient
	public boolean isValid() {
		for(Containable c: this.containables) {
			if(!c.isValid())
				return false;
		}
		return true;
	}
}