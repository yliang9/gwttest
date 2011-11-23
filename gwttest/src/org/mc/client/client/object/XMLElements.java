package org.mc.client.client.object;

public enum XMLElements {
	//element
	ID("id"),
	PORTAL ("portal"),
	USERS ("users"),
	USER ("user"),
	GROUP ("group"),
	MARTREGISTRY ("martregistry"),
	MART ("mart"),
	SOURCESCHEMAS("sourceschemas"),
	SOURCESCHEMA ("sourceschema"),
	SCHEMA("schema"),
	TABLES ("tables"),
	 TABLE  ("table"),
	 COLUMN  ("column"),
	 LINKINDEXES  ("linkindices"),
	 LINKINDEX  ("linkindex"),
	 GUICONTAINER ("guicontainer"),
	 GUIACCESSPOINT  ("guiaccesspoint"),
	 PROCESSORGROUP  ("processorgroup"),
	 PROCESSOR  ("processor"),
	 DATASET ("dataset"),
	
	 CONFIG ("config"),
	 PARTITIONTABLE ("partitiontable"),
	 CONTAINER ("container"),
	 CONTAINERAP("containerap"),
	 FILTER("filter"),
	 FILTERWRAPPER("filterwrapper"),
	 ATTRIBUTE("attribute"),
	 ATTRIBUTEWRAPPER("attributewrapper"),
	 ANNOTATIONS("annotations"),
	 IMPORTABLE("importable"),
	 EXPORTABLE("exportable"),
	 ITEM("item"),
	 RELATIONS("relations"),
	 RELATION("relation"),
	 PRIMARYKEY("primarykey"),
	 FOREIGNKEY("foreignkey"),
	 LINK("link"),
	 LINKMART("linkedmart"),
	 LINKS("links"),
	 OPTIONS("options"),
	 TRANSFORM("transform"),
	 SOURCECONTAINERS("sourcecontainers"),
	 SOURCECONTAINER("sourcecontainer"),
	 PARAMETER ("parameter"),
	 PARAMETEROPTION ("parameteroption"),
	 ACCESSPOINT("accesspoint"),
	 ANALYSIS("analysis"),
	 PARTITIONROW("partition.row"),
	 MCUSER("mcuser"),

	//attribute
	 HIDE("hide"),
	 NAME("name"),
	 DISPLAYNAME("displayname"),
	 DESCRIPTION("description"),
	 VERSION("version"),
	 INTERNALNAME("internalname"),
	 MAXCONTAINERS("maxcontainers"),
	 MAXATTRIBUTES("maxattributes"),
	 INDEPENDENTQUERYING("independentquerying"),
	 SPLITON("spliton"),
	 OPERATION("operation"),
	 DATAFILE("datafile"),
	
	 UNIQUEID("uniqueid"),
	 DRIVERCLASSNAME("driverclassname"),
	 URL("url"),
	 DATABASE("database"),
	 MDATABASE("mdatabase"),
	 MSCHEMA("mschema"),
	 USERNAME("username"),
	 PASSWORD("password"),
	 MUSERNAME("musername"),
	 MPASSWORD("mpassword"),
	 MASKED("masked"),
	 KEYGUESSING("keyguessing"),
	 KEY("key"),
	
	 OPTIMISER("optimiser"),
	 INDEXOPTIMISED("indexoptimised"),
	 CENTRALTABLE("centraltable"),
	 TYPE("type"),

	 ROW("row"),
	 COL("col"),
	 ROWS("rows"),
	 COLS("cols"),
	
	 POINTER("pointer"),
	 GUITYPE("guitype"),
	 ICONS("icons"),
	 ICON("icon"),
	 DATASETS("datasets"),
	 CONTAINERS("containers"),
	 ENTRYLAYOUT("entrylayout"),
	 FILTERLIST("filterlist"),
	 DEFAULT("default"),
	 ORDERBY("orderby"),
	 FILTERS("filters"),
	 ATTRIBUTES("attributes"),
	 LINKVERSION("linkversion"),
	 EXPORTABLEDATASETS("exportabledatasets"),
	 IMPORTABLEDATASETS("importabledatasets"),
	 IMPORTABLEINMART("importableinmart"),
	 EXPORTABLEINMART("exportableinmart"),
	 EXPORT("export"),
	 LINKVERSIONS("linkversions"),
	 INPARTITIONS("inpartitions"),
	 QUALIFIER("qualifier"),
	 POINTEDATTRIBUTE("pointedattribute"),
	 POINTEDFILTER("pointedfilter"),
	 POINTEDMART("pointedmart"),
	 POINTEDCONFIG("pointedconfig"),
	 POINTEDDATASET("pointeddataset"),
	 ONLY("only"),
	 EXCLUDED("excluded"),
	 ATTRIBUTELIST("attributelist"),
	 LINKOUTURL ("linkouturl"),
	 VALUE("value"),
	 REFCONTAINER("refcontainer"),
	 DATA("data"),
	 METAINFO("metainfo"),
	 DATASETDISPLAYNAME ("datasetdisplayname"),
	 DATASETHIDEVALUE ("datasethidevalue"),
     SUBCLASSOF ("subclassof"),
	 
	 FIRSTTABLE ("firsttable"),
	 FIRSTKEY ("firstkey"),
	 SECONDTABLE ("secondtable"),
	 SECONDKEY ("secondkey"),
	 INUSERS ("inusers"),
	 OPENID ("openid"),
	 SUBPARTITION("subpartition"),
	 MULTIFILE ("multifile"),
	 MASTER ("master"),
	 READONLY ("readonly"),
	 VIRTUAL ("virtual"),
	 SELECTEDTABLES ("selectedtables"),
	 SEARCHFROMTARGET ("searchfromtarget"),
	 LOCATION ("location"),
	 DEPENDSON("dependson"),
	 RDF("rdf"),
	 RDFCLASS("rdfclass"),
	 REQUIRED ("required"),
	 DATATYPE("datatype"),
	 POINTERINSOURCE("pointerinsource"),
	 ENABLESELECTALL("enableselectall"),
	 DEFAULTSELECTED("defaultselected"),
	 CONNECTION("connection"),
	 UUID("uuid"),
	//value
	 TRUE_VALUE("true"),
	 FALSE_VALUE("false"),
	 NONE("none"),
	
	 PARTITIONPREFIX("p"),
	 PARTITIONCOLUMNPREFIX("c"), 
	 TABPROCESSORGROUP("Tabular"),
	 SEQUENCESPROCESSORGROUP("Sequences"),
	 GRAPHSPROCESSORGROUP("Graphs"),
	 
	 //gui only
	 VISIBLEMODIFIED("visiblemodified"),
	 ERROR("error"),
	 
	 //may change 
	 RDBMS("rdbms"),
	 SETBY("setby"),
	 PLUGIN("plugin"),
	 OPTION("option"),
	 
	 NA("")
	 
	 ;
	 
	private String value;
	
	 XMLElements(String value) {
		 this.value = value;
	 }
	 
	 public String toString() {
		 return this.value;
	 }
	 
	 public static XMLElements valueFrom(String value) {
		 for(XMLElements xe: XMLElements.values()) {
			 if(xe.toString().equalsIgnoreCase(value))
				 return xe;
		 }
		 return null;
	 }
}