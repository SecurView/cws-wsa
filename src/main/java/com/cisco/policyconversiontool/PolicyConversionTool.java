package com.cisco.policyconversiontool;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Hello world!
 *
 */
public class PolicyConversionTool 
{
	static Logger logger = null;

	public static void main(String args[]) {
	
		try {
//			PropertyConfigurator.configure("config/log4j.properties");
			logger = Logger.getLogger(PolicyConversionTool.class);
			new AppController().start(null);
			System.out.println("\n\nExiting.");
			System.exit(0);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
//			if (e.getMessage().startsWith("Error:")){
//				System.out.println("\n\n" + e.getMessage());
//			}else{
//				System.out.println("\n\nError: Unexpected error occurred during processing. Please check the application log file under the logs sub-directory for further details");
//			}
			System.out.println("\n\nExiting.");
			System.exit(0);
		}
		
	}
}
