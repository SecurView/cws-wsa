package com.cisco.policyconversiontool.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.cisco.policyconversiontool.dto.Vendor;


public class VendorDAOImpl implements VendorDAO{
private JdbcTemplate jdbcTemplate;

private static Logger logger = Logger.getLogger(VendorDAOImpl.class);
	
	public VendorDAOImpl(DataSource dataSource)
	{
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	public List<Vendor> getVendors(int platform ) 
	{
		String QUERY = "SELECT vendor_id, name, description FROM mst_vendor WHERE platform="+platform+"";
		logger.debug("Vendor Select Query "+QUERY);
		List<Vendor> vendors = jdbcTemplate.query(QUERY, new RowMapper<Vendor>()
		{
			@Override
			public Vendor mapRow(ResultSet resultset, int rowNo)throws SQLException
			{
				//System.out.println("map row");
				Vendor vendor = new Vendor();
				vendor.setId(resultset.getString("vendor_id"));
				vendor.setName(resultset.getString("name").trim());
				vendor.setDesc(resultset.getString("description").trim());
				return vendor;	
			}
		});
		return vendors;
	}
	
	public void addVendor(Vendor vendor) 
	{
		String ADD_NEW_VENDOR_QUERY = "INSERT INTO mst_vendor(vendor_id, name, description) VALUES("+vendor.getId()+"," +
				                      " '"+vendor.getName()+"', '"+vendor.getDesc()+"')";
		logger.debug("Vendor Insert Query "+ADD_NEW_VENDOR_QUERY);
		jdbcTemplate.update(ADD_NEW_VENDOR_QUERY);
	}
	
	public void updateVendor(Vendor vendor) 
	{
		String UPDATE_VENDOR_QUERY = "UPDATE mst_vendor SET name='"+vendor.getName()+"',description='"+vendor.getDesc()+"'  " +
				                     "WHERE vendor_id="+vendor.getId()+"";
		logger.debug("Vendor Update Query "+UPDATE_VENDOR_QUERY);
		jdbcTemplate.update(UPDATE_VENDOR_QUERY);
	}
	@Override
	public Vendor getVendor(String vendorId) {
		String QUERY="SELECT vendor_id, name, description, platform FROM mst_vendor WHERE vendor_id="+vendorId;
		logger.debug("Select query for vendor "+QUERY);
		return jdbcTemplate.queryForObject(QUERY, new RowMapper<Vendor>(){

			@Override
			public Vendor mapRow(ResultSet resultset, int arg1) throws SQLException {
				Vendor vendor = new Vendor();
				vendor.setId(resultset.getString("vendor_id"));
				vendor.setName(resultset.getString("name").trim());
				vendor.setDesc(resultset.getString("description").trim());
				return vendor;	
			}
			
		});
		
	}
}
