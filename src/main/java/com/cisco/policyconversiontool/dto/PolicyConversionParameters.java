package com.cisco.policyconversiontool.dto;


public class PolicyConversionParameters {
	private String targetAppliance;
	private String targetSoftware;
	private String sourceConfiguration;
	private String targetConfiguration;
	public String getTargetAppliance() {
		return targetAppliance;
	}
	public void setTargetAppliance(String targetAppliance) {
		this.targetAppliance = targetAppliance;
	}
	public String getTargetSoftware() {
		return targetSoftware;
	}
	public void setTargetSoftware(String targetSoftware) {
		this.targetSoftware = targetSoftware;
	}
	public String getSourceConfiguration() {
		return sourceConfiguration;
	}
	public void setSourceConfiguration(String sourceConfiguration) {
		this.sourceConfiguration = sourceConfiguration;
	}
	public String getTargetConfiguration() {
		return targetConfiguration;
	}
	public void setTargetConfiguration(String targetConfiguration) {
		this.targetConfiguration = targetConfiguration;
	}
	 
}
