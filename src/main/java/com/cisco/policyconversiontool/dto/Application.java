package com.cisco.policyconversiontool.dto;

import java.io.Serializable;


public class Application implements Serializable{
	private String id;
	private String code;
	private String abbreviation;
	private String name;
	private String vendorId;
	private String mappedWSAApplicationId;
	private String appType;
	private String behaviors;
	
	public String getAppType() {
		return appType;
	}
	public void setAppType(String appType) {
		this.appType = appType;
	}
	public String getBehaviors() {
		return behaviors;
	}
	public void setBehaviors(String behaviors) {
		this.behaviors = behaviors;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	public String getMappedWSAApplicationId() {
		return mappedWSAApplicationId;
	}
	public void setMappedWSAApplicationId(String mappedWSAApplicationId) {
		this.mappedWSAApplicationId = mappedWSAApplicationId;
	}
	
}
