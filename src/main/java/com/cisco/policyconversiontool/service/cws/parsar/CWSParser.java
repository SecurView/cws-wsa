package com.cisco.policyconversiontool.service.cws.parsar;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.cisco.policyconversiontool.dto.cws.CWSPolicy;
import com.fasterxml.jackson.databind.ObjectMapper;


public class CWSParser implements ApplianceParser {
	@Override
	public CWSPolicy doParsing(InputStream inutStream) throws Exception {
		 ObjectMapper mapper = new ObjectMapper();
	       CWSPolicy cwsPolicy =new CWSPolicy();
	       BufferedReader br = new BufferedReader(new InputStreamReader(inutStream));
	       String strfile="";
	       String line="";
	       while ((line = br.readLine()) != null){
	           if(line.contains("\\")){
	               line=line.replace("\\","\\\\");
	           }
	    	   strfile+=line;
	       }
//	       System.out.println(strfile.contains("\\"));
//	      strfile = strfile.replaceAll("\\", "\\\\");
	       br.close();
	       cwsPolicy = mapper.readValue(strfile.getBytes(), CWSPolicy.class);
	       return cwsPolicy; 
	}
}
