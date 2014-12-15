package com.cisco.policyconversiontool.dto.wsa.wsanormalized;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

import com.cisco.policyconversiontool.service.util.Constants;


public class WSAPolicy implements Serializable , Comparator<WSAPolicy>, Comparable<WSAPolicy>{
	private String name;
	private String description;
	private List<WSAIdentity> wsaIdentityList;
	private WSATimeDefinition timeDefinition;
	private String clientType;
	private List<WSACategory> wsaCategoryList;
	private List<WSACustomCategory> wsaCustomCategoryList;
	private List<WSAApplication> wsaApplicationList;
	private List<WSAUserAgent> wsaUserAgentList;
	private List<String> wsaCustomUserAgentList;
	private List<WSAMIMEType> wsaMIMETypeList;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public WSATimeDefinition getTimeDefinition() {
		return timeDefinition;
	}
	public void setTimeDefinition(WSATimeDefinition timeDefinition) {
		this.timeDefinition = timeDefinition;
	}
	public String getClientType() {
		return clientType;
	}
	public void setClientType(String clientType) {
		this.clientType = clientType;
	}
	public List<WSACategory> getWsaCategoryList() {
		return wsaCategoryList;
	}
	public void setWsaCategoryList(List<WSACategory> wsaCategoryList) {
		this.wsaCategoryList = wsaCategoryList;
	}
	public List<WSAApplication> getWsaApplicationList() {
		return wsaApplicationList;
	}
	public void setWsaApplicationList(List<WSAApplication> wsaApplicationList) {
		this.wsaApplicationList = wsaApplicationList;
	}

	public List<WSACustomCategory> getWsaCustomCategoryList() {
		return wsaCustomCategoryList;
	}
	public void setWsaCustomCategoryList(
			List<WSACustomCategory> wsaCustomCategoryList) {
		this.wsaCustomCategoryList = wsaCustomCategoryList;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<WSAUserAgent> getWsaUserAgentList() {
		return wsaUserAgentList;
	}
	public void setWsaUserAgentList(List<WSAUserAgent> wsaUserAgentList) {
		this.wsaUserAgentList = wsaUserAgentList;
	}
	public List<String> getWsaCustomUserAgentList() {
		return wsaCustomUserAgentList;
	}
	public void setWsaCustomUserAgentList(List<String> wsaCustomUserAgentList) {
		this.wsaCustomUserAgentList = wsaCustomUserAgentList;
	}
	public List<WSAMIMEType> getWsaMIMETypeList() {
		return wsaMIMETypeList;
	}
	public void setWsaMIMETypeList(List<WSAMIMEType> wsaMIMITypeList) {
		this.wsaMIMETypeList = wsaMIMITypeList;
	}
	public List<WSAIdentity> getWsaIdentityList() {
		return wsaIdentityList;
	}
	public void setWsaIdentityList(List<WSAIdentity> wsaIdentityList) {
		this.wsaIdentityList = wsaIdentityList;
	}
	@Override
	public int compareTo(WSAPolicy o) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int compare(WSAPolicy o1, WSAPolicy o2) {
		// TODO Auto-generated method stub
		return 0;
	}
		
}
