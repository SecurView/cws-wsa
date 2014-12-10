package com.cisco.policyconversiontool.service;

import java.io.IOException;
import java.text.ParseException;

import org.apache.log4j.Logger;

import com.cisco.policyconversiontool.service.exception.PolicyConversionToolException;

public abstract class  ExceptionHandlingTemplate {

	Logger logger = Logger.getLogger(ExceptionHandlingTemplate.class);

	public Object process() throws PolicyConversionToolException{
		try{
			return doProcess();
		}catch(RuntimeException dae){
			PolicyConversionToolException se = new PolicyConversionToolException();
			se.setMessage("Unknown Error occured");
			se.setCause(dae);
			logger.error(se.getMessage(), dae);
			throw se;
		}catch(IOException ioe){
			PolicyConversionToolException se = new PolicyConversionToolException();
			se.setMessage("File operation Error. Please refer tool logs for more information.");
			se.setCause(ioe);
			logger.error(se.getMessage(), ioe);
			throw se;
		}catch(ParseException pe){
			PolicyConversionToolException se = new PolicyConversionToolException();
			se.setMessage("Exception occured while parsing the policy data. Please refer tool logs for more information.");
			se.setCause(pe);
			logger.error(se.getMessage(), pe);
			throw se;
		}catch(PolicyConversionToolException se){
			logger.error("Policy conversion tool exception occured", se);
			throw se;
		}
		
	}
	
	public abstract  Object doProcess() throws PolicyConversionToolException,IOException,ParseException;	
}
