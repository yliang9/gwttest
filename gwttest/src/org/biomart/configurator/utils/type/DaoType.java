package org.biomart.configurator.utils.type;

public enum DaoType {
	XML,
	DB,
	NEO;
	
	public static DaoType valueFrom(String value) {
		//default xml
		if(value==null || value.isEmpty())
			return DaoType.XML;
		else
			return DaoType.valueOf(value);
	}
}