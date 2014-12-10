package com.cisco.policyconversiontool.dto.wsa.wsanormalized;

import java.io.Serializable;


public class WSAIdentity  implements Serializable{
	private String name;
	private String type;
	
	private String authenticationSequence;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAuthenticationSequence() {
		return authenticationSequence;
	}
	public void setAuthenticationSequence(String authenticationSequence) {
		this.authenticationSequence = authenticationSequence;
	}
	
}
