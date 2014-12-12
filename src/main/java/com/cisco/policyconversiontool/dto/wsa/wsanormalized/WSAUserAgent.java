package com.cisco.policyconversiontool.dto.wsa.wsanormalized;

import java.io.Serializable;


public class WSAUserAgent  implements Serializable{
	private String name;
	private String pattern;
	private String action;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
	 
	
}
