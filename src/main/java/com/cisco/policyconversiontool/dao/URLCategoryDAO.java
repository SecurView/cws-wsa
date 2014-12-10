package com.cisco.policyconversiontool.dao;

import java.util.Map;

import com.cisco.policyconversiontool.dto.URLCategory;


public interface URLCategoryDAO {
	public Map<String, URLCategory> getURLCategoryMap(String vendorId);
	public void addURLCategory(URLCategory urlCategory);
	public void updateURLCategory(URLCategory urlCategory);
}
