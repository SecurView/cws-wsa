package com.cisco.policyconversiontool.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.cisco.policyconversiontool.dto.Appliance;


public class ApplianceDAOImpl implements ApplianceDAO{
	private JdbcTemplate jdbcTemplate;
	
	private static Logger logger = Logger.getLogger(ApplianceDAOImpl.class);
	
	public ApplianceDAOImpl(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Appliance> getAppliances() {
		
 
		String QUERY = "SELECT applience_id, vendor_id, name, description FROM mst_appliance order by vendor_id";
		logger.debug("Appliance Query "+QUERY);
		List<Appliance> appliances = jdbcTemplate.query(QUERY, new RowMapper<Appliance>()
				{

			@Override
			public Appliance mapRow(ResultSet resultset, int rowNo) throws SQLException{
				Appliance appliance = new Appliance();
				
				appliance.setId(resultset.getString("applience_id"));
				appliance.setName(resultset.getString("name").trim());
				appliance.setDesc(resultset.getString("description").trim());
				appliance.setVendorId(resultset.getString("vendor_id"));
				return appliance;
			}
		});
		
		return appliances;
	
	}
	
	public void addAppliance(Appliance appliance) 
	{
		String ADD_NEW_APPLIANCE = "INSERT INTO mst_appliance(applience_id, vendor_id, name, description) " +
				                   "VALUES("+appliance.getId()+", "+appliance.getVendorId()+", '"+appliance.getName()+"', '"+appliance.getDesc()+"')";
		logger.debug("Insert Query of Appliances "+ADD_NEW_APPLIANCE);
		jdbcTemplate.update(ADD_NEW_APPLIANCE);
	}
	
	public void updateAppliance(Appliance appliance) 
	{
		String UPDATE_QUERY = "UPDATE mst_appliance SET vendor_id="+appliance.getVendorId()+",name='"+appliance.getName()+"'," +
				              "description='"+appliance.getDesc()+"' WHERE applience_id="+appliance.getId()+"";
		logger.debug("Update Query of Appliances "+UPDATE_QUERY);
		jdbcTemplate.update(UPDATE_QUERY);
	}

	@Override
	public Appliance getAppliance(String applianceId) {
		String QUERY="SELECT applience_id, vendor_id, name, description FROM mst_appliance WHERE applience_id="+applianceId;
		return jdbcTemplate.queryForObject(QUERY, new RowMapper<Appliance>(){

			@Override
			public Appliance mapRow(ResultSet resultset, int arg1)throws SQLException {
				Appliance appliance = new Appliance();
				appliance.setId(resultset.getString("applience_id"));
				appliance.setName(resultset.getString("name").trim());
				appliance.setDesc(resultset.getString("description").trim());
				appliance.setVendorId(resultset.getString("vendor_id"));
				return appliance;
			}
			
		});
	}
	
}
