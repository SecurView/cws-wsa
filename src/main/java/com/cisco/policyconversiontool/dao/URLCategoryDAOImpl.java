package com.cisco.policyconversiontool.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.cisco.policyconversiontool.dto.URLCategory;


public class URLCategoryDAOImpl implements URLCategoryDAO
{
    private JdbcTemplate jdbcTemplate;
    
    private static Logger logger = Logger.getLogger(URLCategoryDAOImpl.class);
	
	public URLCategoryDAOImpl(DataSource dataSource)
	{
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	public Map<String, URLCategory> getURLCategoryMap(String vendorId) 
	{
		
		String QUERY = "SELECT a.name as vendor_categoryname,b.category_id, b.vendor_id, b.name, b.description, b.abbreviation, b.code " +
				       "FROM mst_urlcategory as a " +
				       "join mst_urlcategory as b on a.mappedwsacategoryid=b.category_id " +
				       "WHERE a.vendor_id="+vendorId;
		logger.debug("URLCategory Select Query "+QUERY);
		Map<String, URLCategory> urlCategoryMap = null;
		urlCategoryMap=jdbcTemplate.query(QUERY,new ResultSetExtractor<Map<String, URLCategory>>()
				 {
					@Override
					public Map<String, URLCategory> extractData(ResultSet rs) throws SQLException, DataAccessException 
					{
						Map<String, URLCategory> urlCategoryMap=new HashMap<String, URLCategory>();
						while(rs.next())
						{
							URLCategory urlCategory=new URLCategory();
							urlCategory.setId(rs.getString("category_id"));
							urlCategory.setVendorId(rs.getString("vendor_id"));
							urlCategory.setName(rs.getString("name"));
							urlCategory.setDescription(rs.getString("description"));
							urlCategory.setAbbreviation(rs.getString("abbreviation"));
							urlCategory.setCode(rs.getString("code"));
							urlCategoryMap.put(rs.getString("vendor_categoryname"), urlCategory);
						}
						return urlCategoryMap;
					}
				 
				 }
			 );  
		
		return urlCategoryMap;
		
	}
	
	public void addURLCategory(URLCategory urlCategory) 
	{
		String ADD_URLCategory = "INSERT INTO mst_urlcategory(category_id, vendor_id, name, description, abbreviation, code, mappedwsacategoryid) " +
				                 "VALUES("+urlCategory.getId()+", "+urlCategory.getVendorId()+", '"+urlCategory.getName()+"', '"+urlCategory.getDescription()+"', " +
				       	         "'"+urlCategory.getAbbreviation()+"', '"+urlCategory.getCode()+"', "+urlCategory.getMappedWSACategoryId()+")";
		logger.debug("URLCategory Insert Query "+ADD_URLCategory);
		jdbcTemplate.update(ADD_URLCategory);
	}
	
	public void updateURLCategory(URLCategory urlCategory) 
	{
		String UPDATE_URLCategory="UPDATE mst_urlcategory SET vendor_id="+urlCategory.getVendorId()+", name='"+urlCategory.getName()+"', " +
				                  "description='"+urlCategory.getDescription()+"', abbreviation='"+urlCategory.getAbbreviation()+"', code='"+urlCategory.getCode()+"', " +
						          "mappedwsacategoryid="+urlCategory.getMappedWSACategoryId()+"  WHERE category_id="+urlCategory.getId()+"";
		logger.debug("URLCategory Update Query "+UPDATE_URLCategory);
		jdbcTemplate.update(UPDATE_URLCategory);
	}
}
