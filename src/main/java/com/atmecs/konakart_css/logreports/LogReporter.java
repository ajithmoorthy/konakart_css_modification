package com.atmecs.konakart_css.logreports;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.atmecs.konakart_css.constants.FileConstants;
/**
 * logReporter class is used for the implement log 4j
 * and captured the actions which is performed in testscripts 
 */
public class LogReporter {
	Logger logger = null;
	/**
	 * This logReportMessage method is load the log4j.properties file to store the log details of the test script
	 * Logger class getLogger method is used to find or create a logger with the name passed as parameter.
	 * @param message
	 * logger info store the message what we passing.
	 */
	public void logReportMessage(String message) {
		PropertyConfigurator.configure(FileConstants.LOG4J_CONFIG_PROPERTY_PATH);
		logger = Logger.getLogger(LogReporter.class.getName());
		logger.info(message);
	}
}

