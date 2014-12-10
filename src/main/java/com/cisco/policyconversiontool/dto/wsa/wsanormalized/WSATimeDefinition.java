package com.cisco.policyconversiontool.dto.wsa.wsanormalized;

import java.io.Serializable;
import java.util.List;


public class WSATimeDefinition  implements Serializable{
	private String name;
	private List<WSATimeRange> timeRangeList;
	private String timeZone;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<WSATimeRange> getTimeRangeList() {
		return timeRangeList;
	}
	public void setTimeRangeList(List<WSATimeRange> timeRangeList) {
		this.timeRangeList = timeRangeList;
	}
	public String getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	
}
