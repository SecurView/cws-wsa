package com.cisco.policyconversiontool.service.cws.parsar;

import java.io.InputStream;

import com.cisco.policyconversiontool.dto.cws.CWSPolicy;


public interface ApplianceParser {
	public CWSPolicy doParsing(InputStream inutStream) throws Exception;
	
}

