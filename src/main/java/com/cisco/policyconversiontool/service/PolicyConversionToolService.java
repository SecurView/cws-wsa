/**
 * 
 */
package com.cisco.policyconversiontool.service;

import java.io.OutputStream;

import com.cisco.policyconversiontool.dto.WSAMigrationPageInfo;
import com.cisco.policyconversiontool.dto.WSAMigrationParameters;
import com.cisco.policyconversiontool.service.exception.PolicyConversionToolException;

/**
 * @author 
 *
 */
public interface PolicyConversionToolService {

	public WSAMigrationPageInfo getWSAMigrationPageInfo() ;
	public OutputStream doWSAMigration(WSAMigrationParameters wsaMigrationParameters) throws PolicyConversionToolException; 
}
