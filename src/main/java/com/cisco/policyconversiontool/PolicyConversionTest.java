package com.cisco.policyconversiontool;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cisco.policyconversiontool.dto.WSAMigrationPageInfo;
import com.cisco.policyconversiontool.dto.WSAMigrationParameters;
import com.cisco.policyconversiontool.service.PolicyConversionToolService;
import com.cisco.policyconversiontool.service.PolicyConversionToolServiceImpl;
import com.cisco.policyconversiontool.service.util.DTDProvider;

public class PolicyConversionTest{

	private static Logger logger = Logger.getLogger(PolicyConversionTest.class); 
//	private static ConsoleManager consoleManager;
	public void printWSAMigrationPageInfo(WSAMigrationPageInfo pageInfo)
	{
	//// To Print the database data
			
	}
	
	public static void main(String[]args){
		try{
//			Scanner scanner = new Scanner(System.in);
//			PropertyConfigurator.configure("config/log4j.properties");
//			logger = Logger.getLogger(MigrationServiceTest.class);
			DTDProvider.setAsyncos805DTD(new File("src\\main\\resource\\config\\wsaDTD805.dtd"));
	        DTDProvider.setAsyncos806DTD(new File("src\\main\\resource\\config\\wsaDTD806.dtd"));
	        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
	        PolicyConversionToolService policyConvertionService =(PolicyConversionToolServiceImpl)applicationContext.getBean("PolicyConversionToolServiceImpl");
			
//	        PolicyConversionToolService policyConvertionService = new PolicyConversionToolServiceImpl();
	        
			WSAMigrationPageInfo pageInfo=policyConvertionService.getWSAMigrationPageInfo();
			WSAMigrationParameters wsaMigrationParameters = new WSAMigrationParameters();
		 
			wsaMigrationParameters.setSourceAppliance("4");
			wsaMigrationParameters.setSourceSoftware("1");
			wsaMigrationParameters.setSourceVendor("4");
			wsaMigrationParameters.setTargetSoftware("2");
			wsaMigrationParameters.setTargetVendor("1");
			wsaMigrationParameters.setTargetAppliance("1");
			
			wsaMigrationParameters.setSourceArchievedFile(new FileInputStream("src\\test\\resource\\fixtures\\cws\\SecurView_6_CiscoFixes.json"));
			wsaMigrationParameters.setTargetXMLFile(new FileInputStream("src\\test\\resource\\fixtures\\wsa\\wsaInitialConfig.xml"));
			
			
			ByteArrayOutputStream objByteArrayOutputStrem = (ByteArrayOutputStream) policyConvertionService.doWSAMigration(wsaMigrationParameters);	
		 
//			FileOutputStream outputStream = new FileOutputStream(scanner.next());
//			objByteArrayOutputStrem.writeTo(outputStream);
//			objByteArrayOutputStrem.close();
	        
			 
		}catch(Exception e){
			e.printStackTrace();
//			logger.error(e.getMessage());
//			System.out.println("\n\nError: Unexpected error occurred during processing. Please check the application log file under the logs sub-directory for further details");
//			System.out.println("\n\nExiting.");
			System.exit(0);
		}
	}
}
