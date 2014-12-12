package com.cisco.policyconversiontool.dto.wsa.wsanormalized;

import java.io.Serializable;


public class WSAMIMEType  implements Serializable{
	private String contentType;
	private String action;

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	 
	
}
