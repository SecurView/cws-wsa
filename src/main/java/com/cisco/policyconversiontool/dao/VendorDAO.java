package com.cisco.policyconversiontool.dao;

import java.util.List;

import com.cisco.policyconversiontool.dto.Vendor;


public interface VendorDAO {
	public List<Vendor> getVendors( int platform);
	public void addVendor(Vendor vendor);
	public void updateVendor(Vendor vendor);
	public Vendor getVendor(String vendorId);
}
