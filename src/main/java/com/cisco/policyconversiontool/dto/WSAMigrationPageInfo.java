package com.cisco.policyconversiontool.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class WSAMigrationPageInfo {
	private List<Vendor> sourceVendorList;
	private List<Vendor> targetVendorList;
	private List<Appliance> applianceList;
	private  List<Software> softwareList;
	private Map<String, List<Appliance>> applianceMap; 
	private Map<String, List<Software>> softwareMap; 
	
	public List<Vendor> getSourceVendorList() {
		return sourceVendorList;
	}
	public void setSourceVendorList(List<Vendor> sourceVendorList) {
		this.sourceVendorList = sourceVendorList;
	}
	public List<Vendor> getTargetVendorList() {
		return targetVendorList;
	}
	public void setTargetVendorList(List<Vendor> targetVendorList) {
		this.targetVendorList = targetVendorList;
	}
	public List<Appliance> getApplianceList(String vendorId) {
		return applianceMap.get(vendorId);
	}
	public void setApplianceList(List<Appliance> applianceList) {
		this.applianceList = applianceList;
		applianceMap = new HashMap<String, List<Appliance>>();
		for(int i=0;i<applianceList.size();i++)
		{
			Appliance appliance=applianceList.get(i);
			if (applianceMap.get(appliance.getVendorId()) == null){
				applianceMap.put(appliance.getVendorId(), new ArrayList<Appliance>());
			}
			applianceMap.get(appliance.getVendorId()).add(appliance);
		}
	}
	public List<Software> getSoftwareList(String vendorId) {
		return softwareMap.get(vendorId);
	}
	public void setSoftwareList(List<Software> softwareList) {
		this.softwareList = softwareList;
	    softwareMap = new HashMap<String, List<Software>>();
		for(int i=0;i<softwareList.size();i++)
		{
			Software software=softwareList.get(i);
			if (softwareMap.get(software.getVendorid()) == null){
				softwareMap.put(software.getVendorid(), new ArrayList<Software>());
			}
			softwareMap.get(software.getVendorid()).add(software);
		}
	}
	
}
