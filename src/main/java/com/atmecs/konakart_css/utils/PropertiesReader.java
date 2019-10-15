package com.atmecs.konakart_css.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 
 * @author ajith.periyasamy
 * This class have the method to read the value from any properties file
 * using the KeyValueLoader
 *
 */

public class PropertiesReader {
	/**
	 * This KeyValueLoader method take the parameters
	 * @param path
	 * and read the value from the properties file.
	 * and stored in properties Object. 
	 *  finally @return the Property object.
	 * @throws IOException
	 */
	public  Properties KeyValueLoader(String path) throws IOException {
		File file = new File(path);
		Properties property = new Properties();
		FileInputStream input = new FileInputStream(file);
		property.load(input);
		return property;
	}
}
