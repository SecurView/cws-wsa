package com.cisco.policyconversiontool.dao;

import java.util.Map;

import com.cisco.policyconversiontool.dto.Application;


public interface ApplicationDAO {
	public Map<String, Application> getApplicationsMap(String vendorId);
	public void addApplication(Application application);
	public void updateApplication(Application application);
}
