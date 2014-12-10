package com.cisco.policyconversiontool.dto.wsa.wsanormalized;

import java.io.Serializable;
import java.util.List;


public class WSATimeRange  implements Serializable{
	private String startTime;
	private String endTime;
	private List<String> validDays;
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public List<String> getValidDays() {
		return validDays;
	}
	public void setValidDays(List<String> validDays) {
		this.validDays = validDays;
	}
	
}
