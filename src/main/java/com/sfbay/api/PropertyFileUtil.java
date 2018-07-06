package com.sfbay.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;

public class PropertyFileUtil {

	public static Properties propapi;
	public static Logger log;

	public static String merge(String key, String value) {
		String result = key + value;

		return result;
	}

	public PropertyFileUtil() {
		try {
			log = Logger.getLogger(this.getClass());
			propapi = new Properties();
			File f = new File("./resources/testData.properties");
			FileInputStream ip = new FileInputStream(f);
			propapi.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			log.info("configapi property file missing");
			System.exit(0);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}

	public static String readProperty(String i) {
		String o = PropertyFileUtil.propapi.getProperty(i);

		return o;
	}

}
