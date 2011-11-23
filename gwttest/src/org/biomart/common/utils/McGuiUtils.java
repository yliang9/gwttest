package org.biomart.common.utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import javax.naming.InvalidNameException;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import org.biomart.common.resources.Log;
import org.biomart.common.resources.Resources;
import org.biomart.common.resources.Settings;
import org.biomart.configurator.controller.RequestHandler;
import org.biomart.configurator.dao.DaoFactoryV2;
import org.biomart.configurator.utils.type.DaoType;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.mc.client.client.object.Containable;
import org.mc.client.client.object.McUser;

/**
 * this class should not be used for web, should only be used for
 * MartConfigurator GUI.
 */
public enum McGuiUtils {
	INSTANCE;


	//for xml, it is the full filename, for db, it is registryname
	private String registryname;
	private McUser user;

	private int cutCopyOperation;

	public int getCutCopyOperation() {
		return cutCopyOperation;
	}

	public void setCutCopyOperation(int cutCopyOperation) {
		this.cutCopyOperation = cutCopyOperation;
	}

	public McUser getUser() {
		return this.user;
	}

	public void setUser() {	
		user = DaoFactoryV2.getFactory(DaoType.valueOf(Settings.getProperty("persistent.type"))).getDaoObject().
				getUser(System.getProperty("user.name"));
	}

	
	public String getRegistryName() {
		return this.registryname;
	}
	
	public void setRegistryName(String name) {
		this.registryname = name;
	}




	public enum ErrorMsg {
		YES,NO_MORE_WARNING,CANCEL,NO_CONFIG,NO_MASTER_CONFIG,DEFAULT,INVALID_QUERY_RESTRICTION,
		TABLE_NAME_ERROR, TABLE_IS_NULL, COLUMN_NAME_ERROR , NULL_MART
	}
	public boolean isWindows() {
		String os = System.getProperty("os.name").toLowerCase();
		return os.indexOf("win") >= 0;
	}


	public <T, E> T getKeysByValue(Map<T, E> map, E value) {
		for (Map.Entry<T, E> entry : map.entrySet()) {
			if (entry.getValue().equals(value)) {
				return entry.getKey();
			}
		}
		return null;
	}	


	public File getIndicesDirectory() {
		//check ../registry/linkindices exist
		File file = new File("../registry/linkindices");
		if(file.exists())
			return file;
		else
			return null;
	}

	public File getDistIndicesDirectory() {		
		final File registryDir = new File(".", "registry");
		if(!registryDir.exists())
			registryDir.mkdir();
		File linkindicesDir = new File(registryDir,"linkindices");
		if(!linkindicesDir.exists())
			linkindicesDir.mkdir();
		return linkindicesDir;		
	}



	/**
	 * most mc property has ',' separated string, we need to replace one part of the string
	 * for example, listStr = 'a,b,c,d'  oldvalue = 'a'  newvalue = 'x'
	 * return x,b,c,d

	 */
	public String replaceValueInListStr(String listStr, String oldvalue, String newvalue) {		
		String[] items = listStr.split(",");
		List<String> list = Arrays.asList(items);
		int index = list.indexOf(oldvalue);
		list.set(index, newvalue);
		return McUtils.StrListToStr(list, ",");
	}

	public boolean inProperty(String listStr, String value) {
		if(listStr.indexOf(value)>=0) {
			String[] items = listStr.split(",");
			List<String> list = Arrays.asList(items);
			return list.contains(value);			
		}
		return false;
	}


	/**
	 * use bubble sort to sort the partition table
	 * @param table
	 * @param col
	 * @param ascend
	 */
	public void sortPartitionTable(String martname, int col, boolean ascend) {
/*		org.biomart.configurator.view.gui.objects.PartitionTable table = RequestHandler.getInstance().getMainPartitionTableFromMart(martname);
		int count = table.getRowCount();
		for(int i=0; i<count; i++) {
			for(int j=i+1; j<count; j++) {
				String s0 = table.getValue(i, col);
				String s1 = table.getValue(j, col);
				if(ascend) {
					if(s0.compareTo(s1)>0) {
						RequestHandler.getInstance().requestSwapRowsInPartitionTable(table.getMart(), i, j);
					}
				} else {
					if(s0.compareTo(s1)<0) {
						RequestHandler.getInstance().requestSwapRowsInPartitionTable(table.getMart(), i, j);
					}
				}
			}
		}*/
	}
	
	
	
	public String getOptionFileName(File file) {
		String tmpName = file.getName();
		int index = tmpName.lastIndexOf(".");
		if(index>0)
			tmpName = tmpName.substring(0,index);
		return file.getParent()+File.separator+tmpName+"_option.xml";
	}
	

	

	
	public List<String> serializeForRH(Containable liteObj){
		//Temporary method for "serializing" to RequestHandler. We can replace this with something better when we have a protocol
		List<String> serialized = new ArrayList<String>();
		
		serialized.add(liteObj.getMart());
		serialized.add(liteObj.getConfig());
		serialized.add(liteObj.getName());
		serialized.add(liteObj.getNodeType().toString());
		return serialized;
	}
	
	
}