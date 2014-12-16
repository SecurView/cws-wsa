package com.cisco.policyconversiontool.service.cws.parsar;

public class ApplianceParserFactory {
	public ApplianceParser getApplianceParser() {
		 
			return (ApplianceParser) new CWSParser();
	}
}
