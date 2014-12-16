package com.cisco.policyconversiontool.service.cws.parsar;

import com.cisco.policyconversiontool.dto.cws.CWSPolicy;
import com.fasterxml.jackson.databind.ObjectMapper;


public class CWSParser implements ApplianceParser {
	@Override
	public CWSPolicy doParsing(String applianceConfiguration) throws Exception {
		 ObjectMapper mapper = new ObjectMapper();
//	       CWSPolicy cwsPolicy =new CWSPolicy();
//	       BufferedReader br = new BufferedReader(new InputStreamReader(inutStream));
//	       String strfile="";
//	       String line="";
//	       while ((line = br.readLine()) != null){
//	           if(line.contains("\\")){
//	               line=line.replace("\\","\\\\");
//	           }
//	    	   strfile+=line;
//	       }
////	       System.out.println(strfile.contains("\\"));
		 applianceConfiguration = applianceConfiguration.replace("\\", "\\\\");
//	       br.close();
	       return mapper.readValue(applianceConfiguration.getBytes(), CWSPolicy.class); 
	}
}
