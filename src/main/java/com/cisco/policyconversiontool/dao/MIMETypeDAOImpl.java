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

public class MIMETypeDAOImpl implements MIMETypeDAO
{
    private JdbcTemplate jdbcTemplate;
    
    private static Logger logger = Logger.getLogger(MIMETypeDAOImpl.class);
	
	public MIMETypeDAOImpl(DataSource dataSource)
	{
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	@Override
	public Map<String, String> getMIMETypeMap()
	{
		String QUERY = "select * from MST_CONTENTTYPE where cws_contenttype <>''";
		logger.debug("MIME Type Select Query : "+QUERY);
		Map<String, String> mimeTypeMap = null;
		mimeTypeMap=jdbcTemplate.query(QUERY,new ResultSetExtractor<Map<String, String>>()
				 {
					@Override
					public Map<String, String> extractData(ResultSet rs) throws SQLException, DataAccessException 
					{
						Map<String, String> mimeTypeMap=new HashMap<String, String>();
						while(rs.next())
						{
							mimeTypeMap.put(rs.getString("CWS_CONTENTTYPE"),rs.getString("WSA_CONTENTTYPE"));
						}
						return mimeTypeMap;
					}
				 
				 }
			 );  
		
		return mimeTypeMap;
	}
}
