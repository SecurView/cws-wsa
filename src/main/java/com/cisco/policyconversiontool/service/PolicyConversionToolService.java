/**
 * 
 */
package com.cisco.policyconversiontool.service;

import java.io.OutputStream;

import com.cisco.policyconversiontool.dto.PolicyConversionParameters;
import com.cisco.policyconversiontool.service.exception.PolicyConversionToolException;

/**
 * @author 
 *
 */
public interface PolicyConversionToolService {

	public OutputStream doPolicyConversion(PolicyConversionParameters policyConversionParameters) throws PolicyConversionToolException; 
}
