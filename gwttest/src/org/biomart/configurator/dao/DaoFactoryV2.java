package org.biomart.configurator.dao;

import org.biomart.configurator.utils.type.DaoType;

public abstract class DaoFactoryV2 {
	public abstract IDao getDaoObject();
	
	public static DaoFactoryV2 getFactory(DaoType type) {
		return DBDaoFactory.getInstance();
	}
}