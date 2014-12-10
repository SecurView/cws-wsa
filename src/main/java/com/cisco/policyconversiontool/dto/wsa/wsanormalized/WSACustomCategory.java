package com.cisco.policyconversiontool.dto.wsa.wsanormalized;

import java.io.Serializable;
import java.util.List;

public class WSACustomCategory implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private List<String> sites;
	private List<String> regularExpressions;
	private String action;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getSites() {
		return sites;
	}
	public void setSites(List<String> sites) {
		this.sites = sites;
	}
	public List<String> getRegularExpressions() {
		return regularExpressions;
	}
	public void setRegularExpressions(List<String> regularExpressions) {
		this.regularExpressions = regularExpressions;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
	
}
