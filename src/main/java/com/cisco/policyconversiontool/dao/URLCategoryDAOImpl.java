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
	public Map<String, URLCategory> getURLCategoryMap() 
	{
		
		String QUERY = "SELECT CATEGORY_ID ,NAME ,ABBREVIATION ,CODE  FROM MST_URLCATEGORY  WHERE STATUS ='A'";
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
							urlCategory.setCategoryId(rs.getString("CATEGORY_ID"));
							urlCategory.setName(rs.getString("NAME"));
							urlCategory.setAbbreviation(rs.getString("ABBREVIATION"));
							urlCategory.setCode(rs.getString("CODE"));
							urlCategoryMap.put(rs.getString("ABBREVIATION"), urlCategory);
						}
						return urlCategoryMap;
					}
				 
				 }
			 );  
		
		return urlCategoryMap;
		
	}
	
	public void addURLCategory(URLCategory urlCategory) 
	{
		String ADD_URLCategory = "INSERT INTO MST_URLCATEGORY (CATEGORY_ID ,NAME ,ABBREVIATION ,CODE,STATUS ) " +
				                 "VALUES("+urlCategory.getCategoryId()+",'"+urlCategory.getName()+"','"+urlCategory.getAbbreviation()+"'," +
				                 "'"+urlCategory.getCode()+"','A')";
		logger.debug("URLCategory Insert Query "+ADD_URLCategory);
		jdbcTemplate.update(ADD_URLCategory);
	}
	
	public void updateURLCategory(URLCategory urlCategory) 
	{
		String UPDATE_URLCategory="UPDATE  MST_URLCATEGORY  SET  NAME ='"+urlCategory.getName()+"',ABBREVIATION ='"+urlCategory.getAbbreviation()+"',CODE ='"+urlCategory.getCode()+"' WHERE CATEGORY_ID ="+urlCategory.getCategoryId();
		logger.debug("URLCategory Update Query "+UPDATE_URLCategory);
		jdbcTemplate.update(UPDATE_URLCategory);
	}
}
