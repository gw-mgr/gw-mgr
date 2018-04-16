package com.gewei.commons.utils;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * @ClassName: AppUtil  
 * @Description: 系统工具类
 * @author JOJIN
 * @date 2015年11月11日 下午4:40:42  
 * @version V2.0
 */
public class AppUtil {
	
	/**
	 * @Title: getPropertie  
	 * @Description: 系统工具类
	 * @param @param key
	 * @param @return 
	 * @return String 
	 * @throws
	 */
	public static String getPropertie(String key){
		Properties prop = new Properties();
		try {
			 String path = ((new AppUtil()).getClass().getClassLoader().getResource("").toURI()).getPath();
			 System.out.println(path);
			 FileInputStream fis = new FileInputStream(path + "/config/application.properties"); 
			 prop.load(fis);
		} catch (Exception e) {
			 return null;
		}
		return prop.getProperty(key);
	}
}
