package com.cisco.policyconversiontool.dto;

import java.io.Serializable;


public class Progress implements Serializable{
	private int percentage;
	private String description;
	public int getPercentage() {
		return percentage;
	}
	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
