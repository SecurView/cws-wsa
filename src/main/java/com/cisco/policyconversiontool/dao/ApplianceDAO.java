package com.cisco.policyconversiontool.dao;

import java.util.List;

import com.cisco.policyconversiontool.dto.Appliance;


public interface ApplianceDAO {
	public List<Appliance> getAppliances();
	public void addAppliance(Appliance appliance);
	public void updateAppliance(Appliance appliance);
	public Appliance getAppliance(String applianceId);
}
