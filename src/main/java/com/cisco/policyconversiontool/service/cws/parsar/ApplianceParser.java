package com.cisco.policyconversiontool.service.cws.parsar;

import com.cisco.policyconversiontool.dto.cws.CWSPolicy;


public interface ApplianceParser {
	public CWSPolicy doParsing(String applianceConfiguration) throws Exception;
	
}

