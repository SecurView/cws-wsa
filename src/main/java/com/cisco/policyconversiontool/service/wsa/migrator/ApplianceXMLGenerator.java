package com.cisco.policyconversiontool.service.wsa.migrator;


import com.cisco.policyconversiontool.dto.wsa.wsanormalized.WSAMigratedConfig;



public interface ApplianceXMLGenerator {
	public String generateXML(WSAMigratedConfig wsaMigratedConfig,Object objWSAInitialConfig) throws Exception;
}
