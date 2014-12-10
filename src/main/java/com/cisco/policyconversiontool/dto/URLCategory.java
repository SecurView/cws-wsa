package com.cisco.policyconversiontool.dto;

import java.io.Serializable;


public class URLCategory implements Serializable{
	private String id;
	private String name;
	private String description;
	private String abbreviation;
	private String code;
	private String vendorId;
	private String mappedWSACategoryId;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	public String getMappedWSACategoryId() {
		return mappedWSACategoryId;
	}
	public void setMappedWSACategoryId(String mappedWSACategoryId) {
		this.mappedWSACategoryId = mappedWSACategoryId;
	}
	
}
