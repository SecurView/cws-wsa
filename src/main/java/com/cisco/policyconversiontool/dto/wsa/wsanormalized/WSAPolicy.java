package com.cisco.policyconversiontool.dto.wsa.wsanormalized;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

import com.cisco.policyconversiontool.service.util.Constants;


public class WSAPolicy implements Serializable , Comparator<WSAPolicy>, Comparable<WSAPolicy>{
	private String name;
	private WSAIdentity identity;
	private WSATimeDefinition timeDefinition;
	private String clientType;
	private List<WSACategory> wsaCategoryList;
	private List<WSACustomCategory> wsaCustomCategoryList;
	private List<WSAApplication> wsaApplicationList;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public WSAIdentity getIdentity() {
		return identity;
	}
	public void setIdentity(WSAIdentity identity) {
		this.identity = identity;
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

	@Override
	public int compareTo(WSAPolicy wsaPolicy) { 
	int iprange=0;
	int thisint=0;
	if(((WSAPolicy) wsaPolicy).getIdentity().getName().contains("/"))
	{
		iprange=Integer.parseInt(((WSAPolicy) wsaPolicy).getIdentity().getName().split("/")[1]);
		
	}
	if(this.getIdentity().getName().contains("/"))
	{
		thisint=Integer.parseInt(this.getIdentity().getName().split("/")[1]);
	}
		//int iprangeno = iprange;
		//descending order
		return  thisint-iprange;
 
	}	
	@Override
	public int compare(WSAPolicy NwsaPolicy1, WSAPolicy NwsaPolicy2) 
	{
		String ip1[]=new String[2];
		String ip2[]=new String[2];
		String idaddress1="";
		String idaddress2="";
		
		if(NwsaPolicy1.getIdentity().getType().equals(Constants.CLIENTTYPE_USER)){
		  return (NwsaPolicy2.getIdentity().getName()).compareTo(NwsaPolicy1.getIdentity().getName());
		}
		
		if(NwsaPolicy1.getIdentity().getType().equals(Constants.CLIENTTYPE_NETWORK)){
				if(NwsaPolicy1.getIdentity().getName().contains("/")){
				ip1=NwsaPolicy1.getIdentity().getName().split("/");
				idaddress1=ip1[0].trim();
				}else{
					idaddress1=NwsaPolicy1.getIdentity().getName().trim();
				}
				
				if(NwsaPolicy2.getIdentity().getName().contains("/")){
				ip2=NwsaPolicy2.getIdentity().getName().split("/");
				idaddress2=ip2[0].trim();
				}else{
					idaddress2=NwsaPolicy2.getIdentity().getName().trim();
				}
		}else{
				idaddress1=NwsaPolicy1.getIdentity().getName().trim();
				idaddress2=NwsaPolicy2.getIdentity().getName().trim();
		}
		
		byte[] ba1 =idaddress1.getBytes();
		byte[] ba2 =idaddress2.getBytes();
        
        if(ba1.length < ba2.length) return 1;
        if(ba1.length > ba2.length) return -1;

        for(int i = 0; i < ba1.length; i++){
            int b1 = unsignedByteToInt(ba1[i]);
            int b2 = unsignedByteToInt(ba2[i]);
            if(b1 == b2)
                continue;
            if(b1 < b2)
                return 1;
            else
                return -1;
        }
        return 0;
        
    }
	private int unsignedByteToInt(byte b) {
        return (int) b & 0xFF;
    }
		
}
