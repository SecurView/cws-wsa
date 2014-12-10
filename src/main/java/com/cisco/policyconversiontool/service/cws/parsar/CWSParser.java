package com.cisco.policyconversiontool.service.cws.parsar;

import java.io.InputStream;

import com.cisco.policyconversiontool.dto.cws.CWSPolicy;
import com.fasterxml.jackson.databind.ObjectMapper;



public class CWSParser implements ApplianceParser {
	@Override
	public CWSPolicy doParsing(InputStream inutStream) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		CWSPolicy cwsPolicy = mapper.readValue(inutStream, CWSPolicy.class);
		return cwsPolicy;
	}
}
