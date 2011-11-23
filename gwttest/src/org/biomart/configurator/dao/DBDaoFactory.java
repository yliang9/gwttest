package org.biomart.configurator.dao;

public class DBDaoFactory extends DaoFactoryV2 {

	private IDao dbDao;
	
	private static DBDaoFactory instance;
	
	public static DBDaoFactory getInstance() {
		if(instance == null) {
			instance = new DBDaoFactory();
		}
		return instance;
	}
	
	private DBDaoFactory() {
		this.dbDao = new DBDao();
	}
	
	@Override
	public IDao getDaoObject() {
		return this.dbDao;
	}
	
}