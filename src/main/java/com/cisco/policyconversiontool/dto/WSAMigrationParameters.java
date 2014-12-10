package com.cisco.policyconversiontool.dto;

import java.io.InputStream;


public class WSAMigrationParameters {
	private String sourceVendor;
	private String targetVendor;
	private String sourceAppliance;
	private String targetAppliance;
	private String sourceSoftware;
	private String targetSoftware;
	private InputStream sourceArchievedFile;
	private InputStream targetXMLFile;
	public String getSourceVendor() {
		return sourceVendor;
	}
	public void setSourceVendor(String sourceVendor) {
		this.sourceVendor = sourceVendor;
	}
	public String getTargetVendor() {
		return targetVendor;
	}
	public void setTargetVendor(String targetVendor) {
		this.targetVendor = targetVendor;
	}
	public String getSourceAppliance() {
		return sourceAppliance;
	}
	public void setSourceAppliance(String sourceAppliance) {
		this.sourceAppliance = sourceAppliance;
	}
	public String getTargetAppliance() {
		return targetAppliance;
	}
	public void setTargetAppliance(String targetAppliance) {
		this.targetAppliance = targetAppliance;
	}
	public String getSourceSoftware() {
		return sourceSoftware;
	}
	public void setSourceSoftware(String sourceSoftware) {
		this.sourceSoftware = sourceSoftware;
	}
	public String getTargetSoftware() {
		return targetSoftware;
	}
	public void setTargetSoftware(String targetSoftware) {
		this.targetSoftware = targetSoftware;
	}
	public InputStream getSourceArchievedFile() {
		return sourceArchievedFile;
	}
	public void setSourceArchievedFile(InputStream sourceArchievedFile) {
		this.sourceArchievedFile = sourceArchievedFile;
	}
	public InputStream getTargetXMLFile() {
		return targetXMLFile;
	}
	public void setTargetXMLFile(InputStream targetXMLFile) {
		this.targetXMLFile = targetXMLFile;
	}
	  
	
	
}
