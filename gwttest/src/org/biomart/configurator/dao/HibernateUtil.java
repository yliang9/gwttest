package org.biomart.configurator.dao;

import java.io.File;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * The HibernateUtil class helps in creating the SessionFactory
 * from the Hibernate configuration file
 */
public class HibernateUtil {
	
	private static final SessionFactory sessionFactory;
	
	static {
		try {
			sessionFactory = new Configuration()
								.configure(new File("conf/xml/hibernate.cfg.xml"))
								.addPackage("org.mc.client.client") 
								.addAnnotatedClass(org.mc.client.client.object.McUser.class)
								.addAnnotatedClass(org.mc.client.client.object.Registry.class)
								.addAnnotatedClass(org.mc.client.client.object.Mart.class)
								.addAnnotatedClass(org.mc.client.client.object.Config.class)
								.addAnnotatedClass(org.mc.client.client.object.Container.class)
								.addAnnotatedClass(org.mc.client.client.object.Attribute.class)
								.addAnnotatedClass(org.mc.client.client.object.Filter.class)
								.addAnnotatedClass(org.mc.client.client.object.Dataset.class)
								.addAnnotatedClass(org.mc.client.client.object.PartitionRow.class)
								.addAnnotatedClass(org.mc.client.client.object.UserGroup.class)
								.addAnnotatedClass(org.mc.client.client.object.PortalUser.class)
								.addAnnotatedClass(org.mc.client.client.object.PortalUser.class)
								.addAnnotatedClass(org.mc.client.client.object.GuiContainer.class)
								.addAnnotatedClass(org.mc.client.client.object.GuiAccessPoint.class)
								.addAnnotatedClass(org.mc.client.client.object.SourceContainer.class)
								.addAnnotatedClass(org.mc.client.client.object.DatasetTable.class)
								.addAnnotatedClass(org.mc.client.client.object.LiteColumn.class)
								.addAnnotatedClass(org.mc.client.client.object.Key.class)
								.addAnnotatedClass(org.mc.client.client.object.Option.class)
								.buildSessionFactory();
			
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}