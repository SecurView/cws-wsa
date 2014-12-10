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

import com.cisco.policyconversiontool.dto.Application;


public class ApplicationDAOImpl implements ApplicationDAO{
private JdbcTemplate jdbcTemplate;

private static Logger logger = Logger.getLogger(ApplicationDAOImpl.class);

	public ApplicationDAOImpl(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	public Map<String, Application> getApplicationsMap( String vendorId) 
	{
		String QUERY = "SELECT a.name as vendor_applicationname,b.application_id,b.vendor_id,b.name,b.abbreviation,b.code,b.app_type,b.behaviors " +
				       "FROM mst_application as a " +
				       "join mst_application as b on a.mappedwsaapplicationid=b.application_id " +
				       "WHERE a.vendor_id="+vendorId;
		logger.debug("Application Select Query "+QUERY);
		Map<String, Application> applicationMap=jdbcTemplate.query(QUERY, new ResultSetExtractor<Map<String, Application>>()
				{
					@Override
					public Map<String, Application> extractData(ResultSet rs) throws SQLException, DataAccessException 
					{
						Map<String, Application> applicationMap=new HashMap<String, Application>();
						while(rs.next())
						{
							Application application=new Application();
							application.setId(rs.getString("application_id"));
							application.setName(rs.getString("name").trim());
							application.setCode(rs.getString("code").trim());
							application.setAbbreviation(rs.getString("abbreviation").trim());
							application.setVendorId(rs.getString("vendor_id"));
							application.setAppType(rs.getString("app_type"));
							application.setBehaviors("behaviors");
							applicationMap.put(rs.getString("vendor_applicationname"), application);
						}
						return applicationMap;
					}
			
				}
			);
		return applicationMap;
	
	}
	
	public void addApplication(Application application) 
	{
		String Add_APPLICATION="INSERT INTO mst_application(application_id, vendor_id, name, abbreviation, code, app_type, behaviors, mappedwsaapplicationid) " +
				               "VALUES("+application.getId()+", "+application.getVendorId()+", '"+application.getName()+"', '"+application.getAbbreviation()+"', " +
				               	"'"+application.getCode()+"', "+application.getAppType()+", '"+application.getBehaviors()+"', "+application.getMappedWSAApplicationId()+")";
		logger.debug("Application Insert Query "+Add_APPLICATION);
		jdbcTemplate.update(Add_APPLICATION);
	}
	
	public void updateApplication(Application application) 
	{
		String UPDATE_APPLICATION="UPDATE mst_application SET name='"+application.getName()+"',abbreviation='"+application.getAbbreviation()+"'," +
	                              "code='"+application.getCode()+"',app_type="+application.getAppType()+",behaviors='"+application.getBehaviors()+"'," +
			                      "mappedwsaapplicationid="+application.getMappedWSAApplicationId()+",vendor_id="+application.getVendorId()+" " +
					               "WHERE application_id="+application.getId()+"";
		logger.debug("Application Update Query "+UPDATE_APPLICATION);
		jdbcTemplate.update(UPDATE_APPLICATION);
	}
}
