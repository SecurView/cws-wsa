package com.cisco.policyconversiontool.service.wsa.migrator;

import com.cisco.policyconversiontool.service.util.Constants;



public class ApplianceXMLGeneratorFactory {
	public ApplianceXMLGenerator getApplianceXMLGenerator(String appliance, String software) throws Exception {
		if(appliance.equals(Constants.TARGET_APPLIENCE_ID_WSA) && software.equals(Constants.TARGET_SOWFWARE_WSA_ASYNCOS806))
		{
			return new XMLGeneratorWSA100Asyncos806();
		}else if(appliance.equals(Constants.TARGET_APPLIENCE_ID_WSA) && software.equals(Constants.TARGET_SOWFWARE_WSA_ASYNCOS805))
		{
			return new XMLGeneratorWSA100Asyncos805();
		}
		else
		{
			throw new Exception("Match Not found For ApplianceXML Generator in ApplianceXMLGeneratorFactory.");
		}
	
	}
}
