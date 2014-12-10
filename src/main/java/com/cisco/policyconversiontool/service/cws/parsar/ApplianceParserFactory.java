package com.cisco.policyconversiontool.service.cws.parsar;

import com.cisco.policyconversiontool.service.util.Constants;

public class ApplianceParserFactory {
	public ApplianceParser getApplianceParser(String format) {
		if(format.equals(Constants.APPLIENCE_ID_CWS))
		{
			return (ApplianceParser) new CWSParser();
		}
		return null;
	}
}
