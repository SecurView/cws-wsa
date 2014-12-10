package com.cisco.policyconversiontool.dto.wsa.wsanormalized;

import java.io.Serializable;


public class WSACategory implements Serializable{
	private String id;
	private String action;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
}
