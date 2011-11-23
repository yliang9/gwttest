package org.biomart.common.utils;

import java.io.IOException;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import org.biomart.common.resources.Log;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.mc.client.client.object.XMLElements;

public class McUtils {
	//private static Calendar calendar = new GregorianCalendar();
	private static int containerId = 0;
	private static String key;
	private static boolean testingMode = false; 
	
	public static void setTestingMode(boolean b) {
		testingMode = b;
	}
	
	public static boolean isTestingMode() {
		return testingMode;
	}

	public static String getKey() {
		return key;
	}
	
	public static String getCurrentTimeString() {
		Format formatter = new SimpleDateFormat("yyyy-mm-dd-HH-mm-ss");
		Date date = new Date();
		return formatter.format(date);
	}

	public static void gc() {
	}
	
	public static String StrListToStr(List<String> list, String separator) {
		if(list==null || list.size()==0)
			return "";
		else {
			StringBuffer res = new StringBuffer();
			res.append(list.get(0));
			for(int i=1;i<list.size();i++) {
				if(list.get(i).indexOf("|")>=0) {
					String tmp = list.get(i).replaceAll("\\|", " or ");
					list.set(i, tmp);
				//	list.get(i).replaceAll("|", " or ");
				}
				res.append(separator);
				res.append(list.get(i));
			}
			return res.toString();
		}
	}


	/**
	 * Necessary in order to order mixed case strings properly.
	 * 	with default Comparator, the following sequence:	a_, A_, ab, AB
	 * 	would end up being ordered as:						AB, A_, a_, ab
	 *  while it should be:									A_, a_, AB, ab
	 * -> see DCCTEST-491
	 * @author Anthony Cros
	 */
	public static final Comparator<String> BETTER_STRING_COMPARATOR = new Comparator<String>() {
		public int compare(String string1, String string2) {
			return string1.toLowerCase().compareTo(string2.toLowerCase());	// assumes no null 
		}		
	};
	
	public static long getCurrentTime() {
		Calendar calendar = new GregorianCalendar();
		return calendar.getTimeInMillis();
	}

	public static String getRegValue(String reg, String expression, String input) {
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(input);
		return m.replaceAll(expression);
	}

    public static int getNextContainerId() {
    	return containerId++;
    }

    public static String getPartitionTableName(String value) {
    	int index = value.indexOf(XMLElements.PARTITIONCOLUMNPREFIX.toString());
    	//remove "("
    	return value.substring(1,index);
    }
    
    
    public static int getPartitionColumnValue(String value) {
    	int index = value.indexOf(XMLElements.PARTITIONCOLUMNPREFIX.toString());
    	String colStr = value.substring(index+1, value.length()-1);
    	//remove the ")"
    	int ret = -1;
    	try{
    		ret = Integer.parseInt(colStr);
    	}catch(Exception e) {
    		//TODO
    		return -1;
    	}
    	return ret;
    }

    public static List<String> extractPartitionReferences(String value) {
        List<String> list = new ArrayList<String>();
        if (null!=value) {
        	String pat = "\\(p\\d*c\\d*\\)";
        	
            Pattern pattern = Pattern.compile(pat);
            Matcher m = pattern.matcher(value);
            while (m.find()) {
                int start = m.start();
                int end = m.end();
                list.add(value.substring(0, start));
                list.add(value.substring(start, end));
                value = value.substring(end);
                m = pattern.matcher(value);
            }
            list.add(value);
        } else {
            list.add("");    // null becomes an empty value
        }
        return list;
    }
    
    public static boolean hasPartitionBinding(String value) {
    	String pat = "\\(p\\d*c\\d*\\)";
    	return hasPatternInString(value,pat);
    }
    
    private static boolean hasPatternInString(String value, String pat) {
		Pattern pattern = Pattern.compile(pat);
        Matcher m = pattern.matcher(value);
		return m.find();
    }
    

    /*
     * check null, and trim string
     */
    public static boolean isStringEmpty(String value) {
    	if(value == null)
    		return true;
    	else if(value.trim().length() == 0)
    		return true;
    	else 
    		return false;
    }

    public static org.jdom.Element findChildElementByAttribute(org.jdom.Element element, String att, String value) {
    	@SuppressWarnings("unchecked")
    	List<org.jdom.Element> elist = element.getChildren();
    	for(org.jdom.Element e: elist) {
    		if(value.equals(e.getAttributeValue(att))) 
    			return e;
    	}
    	return null;
    }

    public static String encrypt(String source) throws Exception {
    	if(McUtils.isStringEmpty(key))
    		return source;
    	byte[] raw = hexStringToByteArray(key);
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = null;
		cipher = Cipher.getInstance("AES");
       
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		byte[] encrypted =
			     cipher.doFinal(source.getBytes());		
    	return asHex(encrypted);
    }
    
    public static String decrypt(String encrypted) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
    	if(McUtils.isStringEmpty(key))
    		return encrypted;
    	byte[] raw = hexStringToByteArray(key);
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = null;
		cipher = Cipher.getInstance("AES");
       
		cipher.init(Cipher.DECRYPT_MODE, skeySpec);
		byte[] source = cipher.doFinal(hexStringToByteArray(encrypted));		
    	return new String(source);    	
    }
    
    private static byte[] hexStringToByteArray(String s) {
	    int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                             + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
    }
    
    /**
     * Turns array of bytes into string
     *
     * @param buf	Array of bytes to convert to hex string
     * @return	Generated hex string
     */
    public static String asHex (byte buf[]) {
      StringBuffer strbuf = new StringBuffer(buf.length * 2);
      int i;

      for (i = 0; i < buf.length; i++) {
    	  if (((int) buf[i] & 0xff) < 0x10)
    		  strbuf.append("0");
       	strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
      }

      return strbuf.toString();
     }

    public static void setKey(String value) {
    	key = value;
    }
    
    

	public static byte[] computeHash(String x)    
	  {
	     java.security.MessageDigest d =null;
	     try {
			d = java.security.MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     d.reset();
	     d.update(x.getBytes());
	     return  d.digest();
	 }
	  
	public static String byteArrayToHexString(byte[] b){
	     StringBuffer sb = new StringBuffer(b.length * 2);
	     for (int i = 0; i < b.length; i++){
	       int v = b[i] & 0xff;
	       if (v < 16) {
	         sb.append('0');
	       }
	       sb.append(Integer.toHexString(v));
	     }
	     return sb.toString().toUpperCase();
	}
	

	public static Document buildDocument(String content) {
		SAXBuilder builder = new SAXBuilder();
		Document document = null;
		try {
			document = builder.build(new StringReader(content));
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return document;
	}


	public static <T> boolean isCollectionEmpty(Collection<T> collection) {
		if(collection == null || collection.isEmpty())
			return true;
		return false;
	}

	
	public static boolean isHttpServerAvailable(String url){
		try{
			//create the httpURLconnection
			URL newURL = new URL(url);
			HttpURLConnection connection = (HttpURLConnection)newURL.openConnection();
			
			connection.setRequestMethod("GET");
			//set timeout to be 15 sec
			connection.setReadTimeout(15*1000);
			connection.connect();
			
			return true;
		}
		catch(Exception e){
			Log.debug("checking url "+url + " ...");
			return false;
		}
	}
	
    
	

	public static String[] getOptionsDataFromString(String value) {
		String[] tmp =  value.split("(?<!\\\\)\\|",-1);
		for(int i=0; i<tmp.length; i++) {
			if(tmp[i].indexOf("\\|")>=0) {
				tmp[i]= tmp[i].replaceAll("\\\\\\|", "\\|");
			}
		}
		return tmp;
	}


	private static int loopCount = 0;
    private static Long timestamp1 = 0L;
    private static Long timestamp2 = 0L;
	public static void timing1() {
		timestamp1 = new Date().getTime();
		System.out.println("> " + loopCount + "\t" + timestamp1 + "\t\t" + (timestamp1-timestamp2) + "\n");
	}
	public static void timing2() {
		timestamp2 = new Date().getTime();
        System.out.println("< " + loopCount + "\t" + timestamp2 + "\t\t\t" + (timestamp2-timestamp1) + "\n\n");
        loopCount++;
	}
	
	public static String getPortFromURL(String url) {
		//remove http://
		int index = url.indexOf("://")+3;
		String tmp = url.substring(index);
		String[] _tmp = tmp.split("/");
		String[] __tmp = _tmp[0].split(":");
		if(__tmp.length == 2)
			return __tmp[1];
		else //default 80
			return "80";		
	}
	
	public static String getPathFromURL(String url) {
		//remove http://
		int index = url.indexOf("://")+3;
		String tmp = url.substring(index);
		String[] _tmp = tmp.split("/",2);
		return "/"+_tmp[1];
	}
	
	/**
	 * assuming that tablename follow the name convention p0__p1__p2
	 * @param tablename
	 * @return p0
	 */
	public static String getFirstPartOfTableName(String tablename) {
		String[] _p = tablename.split("__");
		return _p[0];
	}
	
	/**
	 * assuming that tablename follow the name convention p0__p1__p2
	 * @param tablename
	 * @return p1__p2
	 */
	public static String getSecondLastPartOfTableName(String tablename) {
		String[] _p = tablename.split("__");
		if(_p.length == 3)
			return _p[1]+"__"+_p[2];
		else
			return tablename;
	}
	
	public static boolean hasPartitionVariable(String value) {
		String pat = "\\(#\\S*#\\)";
		return hasPatternInString(value, pat);
	}
	
	public static String getFirstPartitionVariableFromString(String value) {
        if (null!=value) {
        	String pat = "\\(#\\S*#\\)";
        	
            Pattern pattern = Pattern.compile(pat);
            Matcher m = pattern.matcher(value);
            if (m.find()) {
                int start = m.start()+2;
                int end = m.end()-2;
                return value.substring(start, end);
            }
            return null;
        } else
        	return null;
	}
	
	/**
	 * check if the input name follow the convention
	 * 1. allowed characters: a..z (26 lower case letters), 0..9 (digits), _ (underscore) 
	 * 2. name can not start with a digit
	 * @param name
	 * @return 
	 */
	public static boolean checkName(String name) {
		String pat = "^[^a-z]|[A-Z\\W]";
		Pattern pattern = Pattern.compile(pat);
        Matcher m = pattern.matcher(name);
		return !m.find();
	}
	
}