package org.mc.client.client.object;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="DATASET")
public class Dataset extends McLiteObject {
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
	
	@Column(name="HIDE")
	public boolean isHidden() {
		return super.isHidden();
	}
	
	public void setHidden(boolean b) {
		super.setHidden(b);
	}
	
	@Column(name="CONNECTION")
	public String getConnection() {
		return this.getPropertyValue(XMLElements.CONNECTION);
	}
	
	public void setConnection(String value) {
		this.setProperty(XMLElements.CONNECTION, value);
	}
	
	@Column(name="MCDATABASE")
	public String getDatabase() {
		return this.getPropertyValue(XMLElements.DATABASE);
	}
	
	public void setDatabase(String value) {
		this.setProperty(XMLElements.DATABASE, value);
	}
	
	@Column(name="MCSCHEMA")
	public String getSchema() {
		return this.getPropertyValue(XMLElements.SCHEMA);
	}
	
	public void setSchema(String value) {
		this.setProperty(XMLElements.SCHEMA, value);
	}
	
	@Column(name="USERNAME")
	public String getUsername() {
		return this.getPropertyValue(XMLElements.USERNAME);
	}
	
	public void setUsername(String value) {
		this.setProperty(XMLElements.USERNAME, value);
	}
	
	@Column(name="PASSWORD")
	public String getPassword() {
		return this.getPropertyValue(XMLElements.PASSWORD);
	}
	
	public void setPassword(String value) {
		this.setProperty(XMLElements.PASSWORD, value);
	}
	
	@Column(name="MCVERSION")
	public String getVersion() {
		return this.getPropertyValue(XMLElements.VERSION);
	}
	
	public void setVersion(String value) {
		this.setProperty(XMLElements.VERSION, value);
	}
	
	@Column(name="MCKEY")
	public String getKey() {
		return this.getPropertyValue(XMLElements.KEY);
	}
	
	public void setKey(String value) {
		this.setProperty(XMLElements.KEY, value);
	}
	
	@Column(name="MDATABASE")
	public String getMDatabase() {
		return this.getPropertyValue(XMLElements.MDATABASE);
	}
	
	public void setMDatabase(String value) {
		this.setProperty(XMLElements.MDATABASE, value);
	}
	
	@Column(name="MSCHEMA")
	public String getMSchema() {
		return this.getPropertyValue(XMLElements.MSCHEMA);
	}
	
	public void setMSchema(String value) {
		this.setProperty(XMLElements.MSCHEMA, value);
	}
	
	@Column(name="MUSERNAME")
	public String getMUsername() {
		return this.getPropertyValue(XMLElements.MUSERNAME);
	}
	
	public void setMUsername(String value) {
		this.setProperty(XMLElements.MUSERNAME, value);
	}
	
	@Column(name="MPASSWORD")
	public String getMPassword() {
		return this.getPropertyValue(XMLElements.MPASSWORD);
	}
	
	public void setMPassword(String value){
		this.setProperty(XMLElements.MPASSWORD, value);
	}

	@Override
	public boolean equals(Object object) {
		if(object == null)
			return false;
		if(!(object instanceof Dataset))
			return false;
		Dataset ds = (Dataset)object;
		return ds.getName().equals(this.getName()) && ds.getMartid() == ds.getMartid();
	}
	
	@Override
	public int hashCode() {
		return this.getName().hashCode();
	}

}