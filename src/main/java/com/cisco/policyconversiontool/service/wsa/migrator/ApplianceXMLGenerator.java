package com.cisco.policyconversiontool.service.wsa.migrator;

import java.io.OutputStream;

import com.cisco.policyconversiontool.dto.wsa.wsanormalized.WSAMigratedConfig;



public interface ApplianceXMLGenerator {
	public OutputStream generateXML(WSAMigratedConfig wsaMigratedConfig) throws Exception;
}
