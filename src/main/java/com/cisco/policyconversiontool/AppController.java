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

import com.cisco.policyconversiontool.dto.PolicyConversionParameters;
import com.cisco.policyconversiontool.service.PolicyConversionToolService;
import com.cisco.policyconversiontool.service.exception.PolicyConversionToolException;
import com.cisco.policyconversiontool.service.util.CommonUtility;
import com.cisco.policyconversiontool.service.util.DTDProvider;

public class AppController {

	private static Logger logger = Logger.getLogger(AppController.class);

	public void start(String args[]) throws PolicyConversionToolException, ParseException, IOException{
		logger.info("***********************************");
		logger.info("Started Policy Conversion Tool");
		logger.info("***********************************");
/*Need to remove*/
/**/		
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
		PolicyConversionToolService policyConversionToolService=(PolicyConversionToolService)applicationContext.getBean("PolicyConversionToolServiceImpl");
		PolicyConversionParameters objPolicyConversionParameters = new PolicyConversionParameters();
		ConsoleManager consoleManager = new ConsoleManager();
//		consoleManager.acceptParameter(policyConversionToolService.getWSAMigrationPageInfo(), wsaMigrationParameters);
		objPolicyConversionParameters.setSourceConfiguration(CommonUtility.getStringFromInputStream(new FileInputStream("src/test/resource/fixtures/cws/SecurView_6_CiscoFixes.json")));
		objPolicyConversionParameters.setTargetAppliance("1");
		objPolicyConversionParameters.setTargetSoftware("1");
		objPolicyConversionParameters.setTargetConfiguration(CommonUtility.getStringFromInputStream(new FileInputStream("src/test/resource/fixtures/wsa/AllSettings.xml")));
		DTDProvider.setAsyncosDTD(objPolicyConversionParameters.getTargetSoftware());
		
		ByteArrayOutputStream out = (ByteArrayOutputStream) policyConversionToolService.doPolicyConversion(objPolicyConversionParameters);
		
		FileOutputStream outStream = new FileOutputStream("D:\\out.zip");
		outStream.write(out.toByteArray());
		out.close();
		outStream.close();
/*Need to close applicationcontext*/
		 
		logger.info("***********************************");
		logger.info("Stopped Policy Conversion Tool");
		logger.info("***********************************");
		
	}
}
