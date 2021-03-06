package com.cisco.policyconversiontool.dto.wsa.wsanormalized;

import java.util.List;


public class WSAMigratedConfig {
	private List<WSAPolicy> wsaPolicyList;
	private List<WSAPolicy> wsaHttpsPolicyList;
	private List<WSATimeDefinition> wsaTimeDefinitionList;
	private List<WSAIdentity> wsaIdentityList;
	
	
	public List<WSAPolicy> getWsaPolicyList() {
		return wsaPolicyList;
	}
	public void setWsaPolicyList(List<WSAPolicy> wsaPolicyList) {
		this.wsaPolicyList = wsaPolicyList;
	}
	public List<WSAPolicy> getWsaHttpsPolicyList() {
		return wsaHttpsPolicyList;
	}
	public void setWsaHttpsPolicyList(List<WSAPolicy> wsaHttpsPolicyList) {
		this.wsaHttpsPolicyList = wsaHttpsPolicyList;
	}
	public List<WSATimeDefinition> getWsaTimeDefinitionList() {
		return wsaTimeDefinitionList;
	}
	public void setWsaTimeDefinitionList(List<WSATimeDefinition> wsaTimeDefinitionList) {
		this.wsaTimeDefinitionList = wsaTimeDefinitionList;
	}
	public List<WSAIdentity> getWsaIdentityList() {
		return wsaIdentityList;
	}
	public void setWsaIdentityList(List<WSAIdentity> wsaIdentityList) {
		this.wsaIdentityList = wsaIdentityList;
	}
}
