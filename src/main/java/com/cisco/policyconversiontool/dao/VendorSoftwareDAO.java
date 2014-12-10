package com.cisco.policyconversiontool.dao;

import java.util.List;

import com.cisco.policyconversiontool.dto.Software;


public interface VendorSoftwareDAO {
	public List<Software> getSoftwareList();
	public void addSoftware(Software software);
	public void updateSoftware(Software software);
	public Software getSoftware(String softwareId);
}
