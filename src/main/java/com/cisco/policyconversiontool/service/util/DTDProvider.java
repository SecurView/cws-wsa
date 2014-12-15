package com.cisco.policyconversiontool.service.util;

import java.io.File;

public class DTDProvider {

	private static File asyncos805DTD;
	private static File asyncos806DTD;
	
	public static File getAsyncos805DTD() {
		return asyncos805DTD;
	}
	public static void setAsyncos805DTD(File asyncos805DTD) {
		DTDProvider.asyncos805DTD = asyncos805DTD;
	}
	public static File getAsyncos806DTD() {
		return asyncos806DTD;
	}
	public static void setAsyncos806DTD(File asyncos806DTD) {
		DTDProvider.asyncos806DTD = asyncos806DTD;
	}
	public static File getAsyncosDTD(String os) {
		if(os.equals(Constants.TARGET_SOWFWARE_WSA_ASYNCOS805))
		{
			return asyncos805DTD;
		}
		else if(os.equals(Constants.TARGET_SOWFWARE_WSA_ASYNCOS806))
		{
			return asyncos806DTD;
		}
		return null;
	}

		
}
