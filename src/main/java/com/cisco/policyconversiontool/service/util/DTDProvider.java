package com.cisco.policyconversiontool.service.util;

import java.io.File;

public class DTDProvider {

	public static File getAsyncosDTD(String software) {
		if(software.equals(Constants.TARGET_SOWFWARE_WSA_ASYNCOS805))
			return new File(Constants.WSA_CONFIG_DTD_805_PATH);
		else if(software.equals(Constants.TARGET_SOWFWARE_WSA_ASYNCOS806))
			return new File(Constants.WSA_CONFIG_DTD_806_PATH);
		return null;
	}
		
}
