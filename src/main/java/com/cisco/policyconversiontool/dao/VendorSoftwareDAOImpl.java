package com.cisco.policyconversiontool.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.cisco.policyconversiontool.dto.Software;


public class VendorSoftwareDAOImpl implements VendorSoftwareDAO{
private JdbcTemplate jdbcTemplate;

private static Logger logger = Logger.getLogger(Software.class);
	
	public VendorSoftwareDAOImpl(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	public List<Software> getSoftwareList( ) {
		String QUERY = "SELECT software_id, vendor_id, name, description FROM mst_software order by vendor_id";
		logger.debug("Software Select Query "+QUERY);
		List<Software> softwares = jdbcTemplate.query(QUERY, new RowMapper<Software>(){
			@Override
			public Software mapRow(ResultSet resultset, int rowNo) throws SQLException{
				Software software = new Software();
				
				software.setId(resultset.getString("software_id"));
				software.setName(resultset.getString("name").trim());
				software.setDesc(resultset.getString("description").trim());
				software.setVendorid(resultset.getString("vendor_id"));
				return software;
			}
		});
		return softwares;
	
	}
	
	public void addSoftware(Software software) 
	{
		String ADD_NEW_SOFTWARE = "INSERT INTO mst_software(software_id, vendor_id, name, description) " +
				                  "VALUES("+software.getId()+", "+software.getVendorid()+", '"+software.getName()+"', " +
				                  "'"+software.getDesc()+"')";
		logger.debug("Software Insert Query "+ADD_NEW_SOFTWARE);
		jdbcTemplate.update(ADD_NEW_SOFTWARE);
	}
	
	public void updateSoftware(Software software) 
	{
		String UPDATE_SOFTWARE = "UPDATE mst_software SET vendor_id="+software.getVendorid()+",name='"+software.getName()+"'," +
				                 "description='"+software.getDesc()+"'  WHERE software_id="+software.getId()+"";
		logger.debug("Software Update Query "+UPDATE_SOFTWARE);
		jdbcTemplate.update(UPDATE_SOFTWARE);
	}
	@Override
	public Software getSoftware(String softwareId) {
		String QUERY="SELECT software_id, vendor_id, name, description FROM mst_software WHERE software_id="+softwareId;
		return jdbcTemplate.queryForObject(QUERY,  new RowMapper<Software>(){

			@Override
			public Software mapRow(ResultSet resultset, int arg1)throws SQLException {
				Software software = new Software();
				software.setId(resultset.getString("software_id"));
				software.setName(resultset.getString("name").trim());
				software.setDesc(resultset.getString("description").trim());
				software.setVendorid(resultset.getString("vendor_id"));
				return software;
			}
			
		});
	}
}
