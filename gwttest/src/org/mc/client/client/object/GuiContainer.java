package org.mc.client.client.object;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Transient;



@Entity
@Table(name="GUICONTAINER")
public class GuiContainer extends LiteObject{

	private List<GuiContainer> subGuiContainer;
	private int ord;
	private long parentid;
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
		return super.isHidden();
	}
	
	public void setHidden(boolean b) {
		super.setHidden(b);
	}
	
	public void setParentid(long id) {
		this.parentid = id;
	}
	public GuiContainer() {
		this.subGuiContainer = new ArrayList<GuiContainer>();
	}
	
	@Column(name="INUSERS")
	public String getInusers() {
		return this.getPropertyValue(XMLElements.INUSERS);
	}
	public void setInusers(String value) {
		this.setProperty(XMLElements.INUSERS, value);
	}
	@Column(name="GUITYPE")
	public String getGuitype() {
		return this.getPropertyValue(XMLElements.GUITYPE);
	}
	public void setGuitype(String value) {
		this.setProperty(XMLElements.GUITYPE, value);
	}
	
	public void addGuiContainer(GuiContainer gc) {
		this.subGuiContainer.add(gc);
	}
	
	public void removeGuiContainer(GuiContainer gc){
		this.subGuiContainer.remove(gc);
	}
	@Transient
	public Collection<GuiContainer> getGuiContainers() {
		return this.subGuiContainer;
	}
	@Transient
	public boolean isLeaf() {
		return this.subGuiContainer.isEmpty();
	}
	@Transient
	public String getEntrylayout() {
		return this.getPropertyValue(XMLElements.ENTRYLAYOUT);
	}

	public void setEntrylayout(String value) {
		this.setProperty(XMLElements.ENTRYLAYOUT, value);
	}
	
	@Column(name="LEVEL")
	public int getLevel() {
		return this.level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
}