package com.cisco.policyconversiontool;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cisco.policyconversiontool.dto.WSAMigrationParameters;
import com.cisco.policyconversiontool.service.PolicyConversionToolService;
import com.cisco.policyconversiontool.service.exception.PolicyConversionToolException;
import com.cisco.policyconversiontool.service.util.DTDProvider;

public class AppController {

	private static Logger logger = Logger.getLogger(AppController.class);

	public void start(String args[]) throws PolicyConversionToolException, ParseException, IOException{
		logger.info("***********************************");
		logger.info("Started Log Analysing Tool");
		logger.info("***********************************");

		DTDProvider.setAsyncos805DTD(new File("src//main//resource//config//wsaDTD805.dtd"));
        DTDProvider.setAsyncos806DTD(new File("src//main//resource//config//wsaDTD806.dtd"));
		
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
		PolicyConversionToolService policyConversionToolService=(PolicyConversionToolService)applicationContext.getBean("PolicyConversionToolServiceImpl");
		WSAMigrationParameters wsaMigrationParameters = new WSAMigrationParameters();
		ConsoleManager consoleManager = new ConsoleManager();
		
		ConsoleManager.printConsoleMessage("*****************************************");
		ConsoleManager.printConsoleMessage("Welcome to CiscoProxyLogAnalyzerTool v1.0");
		ConsoleManager.printConsoleMessage("*****************************************");
//		consoleManager.acceptParameter(policyConversionToolService.getWSAMigrationPageInfo(), wsaMigrationParameters);
		wsaMigrationParameters.setSourceAppliance("4");
		wsaMigrationParameters.setSourceArchievedFile(new FileInputStream("src/test/resource/fixtures/cws/SecurView_6_CiscoFixes.json"));
		wsaMigrationParameters.setSourceSoftware("7");
		wsaMigrationParameters.setSourceVendor("4");
		wsaMigrationParameters.setTargetVendor("1");
		wsaMigrationParameters.setTargetAppliance("1");
		wsaMigrationParameters.setTargetSoftware("1");
		wsaMigrationParameters.setTargetXMLFile(new FileInputStream("src/test/resource/fixtures/wsa/AllSettings.xml"));
		
		ByteArrayOutputStream out = (ByteArrayOutputStream) policyConversionToolService.doWSAMigration(wsaMigrationParameters);
		
		FileOutputStream outStream = new FileOutputStream("D:\\out.zip");
		outStream.write(out.toByteArray());
		out.close();
		outStream.close();
		 
		logger.info("***********************************");
		logger.info("Started Log Analysing Tool");
		logger.info("***********************************");
		
	}
}
