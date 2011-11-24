package org.biomart.configurator.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.naming.InvalidNameException;

import org.biomart.common.utils.McGuiUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.mc.client.client.object.Attribute;
import org.mc.client.client.object.McConfig;
import org.mc.client.client.object.Containable;
import org.mc.client.client.object.Container;
import org.mc.client.client.object.Dataset;
import org.mc.client.client.object.Filter;
import org.mc.client.client.object.GuiAccessPoint;
import org.mc.client.client.object.GuiContainer;
import org.mc.client.client.object.Mart;
import org.mc.client.client.object.McNodeType;
import org.mc.client.client.object.McUser;
import org.mc.client.client.object.PortalUser;
import org.mc.client.client.object.Registry;
import org.mc.client.client.object.SourceContainer;
import org.mc.client.client.object.UserGroup;
import org.mc.client.client.object.XMLElements;

public class DBDao implements IDao {

	/*
	 * FIXME, these are temporally map, they are not thread safe, may cause problem when multiple users modify them 
	 * at the same time.
	 */
	private Map<String,Map<String,Dataset>> martDatasetMap = new HashMap<String,Map<String,Dataset>>();
	private Map<String, Filter> filterMap = new HashMap<String,Filter>();
	
	@Override
	public Object addRegistry(Registry registry) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object addMart(Mart mart) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object addConfig(McConfig config) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object addContainer(Container container) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object addAttribute(Attribute attribute) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object addFilter(Filter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object addContainerRecursively(Container container) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	
	private void checkUser() {
		McUser user = McGuiUtils.INSTANCE.getUser();
		if(user==null) {
			McGuiUtils.INSTANCE.setUser();			
		}
	}

	
	@Override
	public Collection<String> getAllRegistrysForUser(String username) {
		Collection<String> result = new ArrayList<String>();
		Session session = HibernateUtil.getSessionFactory().openSession();

		Query query = session.createQuery(" from Registry where userid=:uid");
		query.setParameter("uid", McGuiUtils.INSTANCE.getUser().getId());
		
		List<Registry> registrylist = query.list();
		session.close();
		for(Registry r: registrylist) {
			result.add(r.getName());
		}
		return result;
	}

	@Override
	public boolean registryExist(String user, String name) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery(" from Registry where userid=:userid and name=:name");
		query.setParameter("name", name);
		query.setParameter("userid", McGuiUtils.INSTANCE.getUser().getId());
		List<Registry> rlist = query.list();
		session.close();
		return rlist.size()>0;
	}

	@Override
	public McUser getUser(String name) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Iterator<McUser> userit = session.createQuery("from McUser where name='"+name+"'").iterate();
		if(userit.hasNext())
			return userit.next();
		else {
			//create a user
			McUser user = new McUser();

			Transaction transaction = null;

			try {
				transaction = session.beginTransaction();						
				user.setName(name);
				session.save(user);
				transaction.commit();
			} catch (HibernateException e) {
				transaction.rollback();
				e.printStackTrace();
			} 
			finally {
				session.close();
			}			
			return user;
		}
	}

	@Override
	public void deleteRegistry(String user, String registry) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		//delete registry
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();	
			Query query = session.createQuery(" from Registry where userid=:userid and name=:name");
			query.setParameter("name", registry);
			query.setParameter("userid", McGuiUtils.INSTANCE.getUser().getId());
			List<Registry> rlist = query.list();
			for(Registry r: rlist) {
				long rid = r.getId();
				session.delete(r);
				//delete usergroup
				query = session.createQuery(" from UserGroup where registryid=:rid");
				query.setParameter("rid", rid);
				List<UserGroup> uglist = query.list();
				for(UserGroup ug: uglist) {
					long ugid = ug.getId();
					session.delete(ug);
					//delete user
					query = session.createQuery(" from PortalUser where groupid=:gid");
					query.setParameter("gid", ugid);
					List<PortalUser> pulist = query.list();
					for(PortalUser pu: pulist) {
						session.delete(pu);
					}
				}
				//session.createQuery(" from ");
			}
			transaction.commit();
		}catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} 
		finally {
			session.close();
		}				
	}

	@Override
	public Collection<Mart> getMarts(String user, String registry) {
		Collection<Mart> result = new ArrayList<Mart>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Registry r = this.getRegistry();
		if(r==null)
			return result;

		Query query = session.createQuery(" from Mart where registryid=:rid order by ord");
		query.setParameter("rid", r.getId());
		List<Mart> mlist = query.list();	
		session.close();
		return mlist;
	}

	@Override
	public Registry getRegistry() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery(" from Registry where userid=:userid and name=:name");
		query.setParameter("name", McGuiUtils.INSTANCE.getRegistryName());
		query.setParameter("userid", McGuiUtils.INSTANCE.getUser().getId());
		List<Registry> rlist = query.list();
		session.close();
		if(rlist.isEmpty())
			return null;
		else
			return rlist.get(0);
	}

	@Override
	public Mart getMartByName(String name) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery(" from Mart where name=:name and registryid=:registry");
		query.setParameter("name", name);
		query.setParameter("registry", this.getRegistry().getId());
		List<Mart> mlist = query.list();
		session.close();
		if(mlist.isEmpty())
			return null;
		else
			return mlist.get(0);
	}

	@Override
	public McConfig getAccessPoint(String mart, String accesspoint) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery(" from McConfig where name=:accesspoint and martid=:mart");
		query.setParameter("accesspoint",accesspoint);
		query.setParameter("mart",this.getMartByName(mart).getId());
		List<McConfig> clist = query.list();
		session.close();
		if(clist.isEmpty())
			return null;
		else {
			//FIXME: add mart manually
			McConfig config = clist.get(0);
			config.setMart(mart);
			return config;
		}
	}

	@Override
	public Container getRootContainerRecursively(McConfig config) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		//get root container;
		Query query = session.createQuery(" from Container where parentid=:parentid and level=0");
		query.setParameter("parentid", config.getId());
		List<Container> containerlist = query.list();
		if(containerlist.isEmpty())
			return null;
		Container rootContainer = containerlist.get(0);
		//get subcomponents
		this.fillContainerRecursively(rootContainer, session);
		session.close();
		return rootContainer;
	}
	
	private void fillContainerRecursively(Container parentc, Session session) {
		List<Containable> litecontainables = new ArrayList<Containable>();
		//add attributes
		Query query = session.createQuery(" from Attribute where containerid=:containerid order by ord");
		query.setParameter("containerid", parentc.getId());
		List<Attribute> alist = query.list();
		litecontainables.addAll(alist);
		//add filters
		query = session.createQuery(" from Filter where containerid=:containerid order by ord");
		query.setParameter("containerid", parentc.getId());
		List<Filter> flist = query.list();
		litecontainables.addAll(flist);
		//add container
		query = session.createQuery(" from Container where parentid=:parentid and level!=0 order by ord");
		query.setParameter("parentid", parentc.getId());
		List<Container> containerlist = query.list();
		litecontainables.addAll(containerlist);
		//sort the list by ord
		
		for(org.mc.client.client.object.Containable c: litecontainables) {
			if(c instanceof Container) {
				this.fillContainerRecursively((Container)c, session);
			} 
		}
		for(org.mc.client.client.object.Containable c: litecontainables) {
			parentc.add(c);
		}
	}


	@Override
	public Collection<Dataset> getDatasets(String martname) {
		Mart mart = this.getMartByName(martname);
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery(" from Dataset where MARTID=:mid order by ord");
		query.setParameter("mid", mart.getId());
		List<Dataset> result = query.list();
		session.close();
		return result;
	}


	@Override
	public Dataset getDataset(String martname, String dsname) {
		Mart mart = this.getMartByName(martname);
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery(" from Dataset where MARTID=:mid and name=:name");
		query.setParameter("mid", mart.getId());
		query.setParameter("name", dsname);
		List<Dataset> dss = query.list();
		if(dss.isEmpty())
			return null;
		return dss.get(0);
	}
	
	@Override
	public Collection<SourceContainer> getSourceContainers() {
		Registry registry = this.getRegistry();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery(" from SourceContainer where PARENTID=:rid order by ord");
		query.setParameter("rid", registry.getId());
		List<SourceContainer> dss = query.list();
		return dss;
	}

	@Override
	public GuiContainer getRootGuiContainer(String user, String registry) {
		Registry r = this.getRegistry();
		//query
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery(" from GuiContainer where parentid=:parentid and level=0 ");
		query.setParameter("parentid", r.getId());
		List<GuiContainer> gclist = query.list();
		GuiContainer root = gclist.get(0);
		
		this.addGuiContainerRecursivly(root, session);
		session.close();
		return root;
	}
	
	private void addGuiContainerRecursivly(GuiContainer parent, Session session) {
		Query query = session.createQuery(" from GuiContainer where parentid=:parentid and level>0 order by ord");
		query.setParameter("parentid", parent.getId());
		List<GuiContainer> subgclist = query.list();
		for(GuiContainer subgc: subgclist) {
			parent.addGuiContainer(subgc);
			this.addGuiContainerRecursivly(subgc, session);
		}
	}

	@Override
	public Collection<GuiAccessPoint> getGuiAccessPoints(GuiContainer gc) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery(" from GuiAccessPoint where parentid=:parentid order by ord");
		query.setParameter("parentid", gc.getId());
		List<GuiAccessPoint> gaplist = query.list();
		session.close();
		return gaplist;
	}

	@Override
	public Collection<McConfig> getAllConfigsInMart(Mart mart) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery(" from McConfig where MARTID=:parentid order by ord");
		query.setParameter("parentid", mart.getId());
		List<McConfig> gaplist = query.list();
		session.close();
		for(McConfig config: gaplist) {
			config.setMart(mart.getName());
		}
		return gaplist;
	}

	@Override
	public Collection<McConfig> getAllAccessPointInMart(Mart mart) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery(" from McConfig where MARTID=:parentid and master=0 order by ord");
		query.setParameter("parentid", mart.getId());
		List<McConfig> gaplist = query.list();
		session.close();
		for(McConfig config: gaplist) {
			config.setMart(mart.getName());
		}
		return gaplist;
	}

	@Override
	public McConfig getMasterConfig(Mart mart) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery(" from McConfig where MARTID=:parentid and master=1");
		query.setParameter("parentid", mart.getId());
		List<McConfig> gaplist = query.list();
		session.close();
		if(gaplist.isEmpty())
			return null;
		gaplist.get(0).setMart(mart.getName());
		return gaplist.get(0);
	}



	
}