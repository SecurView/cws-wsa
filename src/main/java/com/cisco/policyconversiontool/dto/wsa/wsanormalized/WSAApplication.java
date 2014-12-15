package com.cisco.policyconversiontool.dto.wsa.wsanormalized;

import java.io.Serializable;


public class WSAApplication  implements Serializable{
	private String id;
	private String action;
	private String behavior;
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
	public String getBehavior() {
		return behavior;
	}
	public void setBehavior(String behavior) {
		this.behavior = behavior;
	}

	
}
