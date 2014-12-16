package com.cisco.policyconversiontool.service.util;

import java.io.File;

public class DTDProvider {

	private static File asyncosDTD;
	public static void setAsyncosDTD(String software) {
		if(software.equals(Constants.TARGET_SOWFWARE_WSA_ASYNCOS805))
			asyncosDTD = new File(Constants.WSA_CONFIG_DTD_805_PATH);
		else if(software.equals(Constants.TARGET_SOWFWARE_WSA_ASYNCOS806))
			asyncosDTD = new File(Constants.WSA_CONFIG_DTD_806_PATH);
	}
	public static File getAsyncosDTD() {
		return asyncosDTD;
	}

		
}
