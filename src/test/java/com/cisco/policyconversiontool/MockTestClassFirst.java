package com.cisco.policyconversiontool;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.io.FileNotFoundException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import com.cisco.policyconversiontool.dto.PolicyConversionParameters;
import com.cisco.policyconversiontool.service.PolicyConversionToolServiceImpl;
import com.cisco.policyconversiontool.service.util.Constants;
import com.cisco.policyconversiontool.util.TestUtil;



 
public class MockTestClassFirst {
	/**
	 * Class is written for the validate following user story.
	 * US001-Validating the WSA initial configuration
	 */

	PolicyConversionToolServiceImpl objPolicyConversionToolServiceImpl;
	 @org.junit.Before
	   public void Before() throws Exception
	   {
		 TestUtil.policyConversionToolInitialSetup();
		 objPolicyConversionToolServiceImpl = new PolicyConversionToolServiceImpl();
	   }
	 
	 @Rule public ExpectedException thrown = ExpectedException.none();
	 /**
	 	 * Test case created to validate behavior for correct ( well formed) WSA configuration.
	 	 * For asyncos : 805
	 	 * @throws Exception 
	 	 */
	   @Test
	   public void acceptanceCriteria1_testCase1() throws Exception {
		   String wsaInitialConfig = TestUtil.getStringFromInputStream(TestUtil.getInputStreams(TestUtil.WSA_INIT_CONFIG_WF));
		   PolicyConversionParameters objPolicyConversionParameters = TestUtil.getMockedPolicyConversionParameters("{}", wsaInitialConfig);
		   StringBuffer reviewBuffer = new StringBuffer();
		   com.cisco.policyconversiontool.dto.wsa.asyncos805.Config objConfig = 
		(com.cisco.policyconversiontool.dto.wsa.asyncos805.Config) 
		objPolicyConversionToolServiceImpl.validateInitalConfig(objPolicyConversionParameters, reviewBuffer);
		   assertNotNull(objConfig);
	   }
	   
	 /**
	  * Test case created to validate behavior for incorrect (not well formed) WSA configuration.
	  * For asyncos : 805
	 * @throws Exception 
	  */
	 	@Test
	   public void acceptanceCriteria1_testCase2() throws Exception {
	 		thrown.expect(Exception.class);
	 		thrown.expectMessage(Constants.ERROR_INVALID_INI_WSA_CONFIG);
	 		
			String wsaInitialConfig = TestUtil.getStringFromInputStream(TestUtil.getInputStreams(TestUtil.WSA_INIT_CONFIG_NWF));
			PolicyConversionParameters objPolicyConversionParameters = TestUtil.getMockedPolicyConversionParameters("{}", wsaInitialConfig);
			StringBuffer reviewBuffer = new StringBuffer();
			com.cisco.policyconversiontool.dto.wsa.asyncos805.Config objConfig = 
					(com.cisco.policyconversiontool.dto.wsa.asyncos805.Config) 
					objPolicyConversionToolServiceImpl.validateInitalConfig(objPolicyConversionParameters, reviewBuffer);
	   }
	 	/**
	 	 * Test case created to validate behavior for correct ( well formed) WSA configuration.
	 	 * For asyncos : 806
	 	 * @throws Exception 
	 	 */
	   @Test
	   public void acceptanceCriteria1_testCase3() throws Exception {
		   String wsaInitialConfig = TestUtil.getStringFromInputStream(TestUtil.getInputStreams(TestUtil.WSA_INIT_CONFIG_WF));
		   PolicyConversionParameters objPolicyConversionParameters = TestUtil.getMockedPolicyConversionParameters("{}", wsaInitialConfig);
		   when(objPolicyConversionParameters.getTargetSoftware()).thenReturn(Constants.TARGET_SOWFWARE_WSA_ASYNCOS806);
		   
		   StringBuffer reviewBuffer = new StringBuffer();
		   com.cisco.policyconversiontool.dto.wsa.asyncos806.Config objConfig = 
				   (com.cisco.policyconversiontool.dto.wsa.asyncos806.Config) 
				   objPolicyConversionToolServiceImpl.validateInitalConfig(objPolicyConversionParameters, reviewBuffer);
		   assertNotNull(objConfig);
	   }
	   
	 /**
	  * Test case created to validate behavior for incorrect (not well formed) WSA configuration.
	  * For asyncos : 806
	 * @throws Exception 
	  */
	 	@Test
	   public void acceptanceCriteria1_testCase4() throws Exception {
	 		thrown.expect(Exception.class);
	 		thrown.expectMessage(Constants.ERROR_INVALID_INI_WSA_CONFIG);
	 		
	 		String wsaInitialConfig = TestUtil.getStringFromInputStream(TestUtil.getInputStreams(TestUtil.WSA_INIT_CONFIG_NWF));
			   PolicyConversionParameters objPolicyConversionParameters = TestUtil.getMockedPolicyConversionParameters("{}", wsaInitialConfig);
			   when(objPolicyConversionParameters.getTargetSoftware()).thenReturn(Constants.TARGET_SOWFWARE_WSA_ASYNCOS806);
			   StringBuffer reviewBuffer = new StringBuffer();
			   com.cisco.policyconversiontool.dto.wsa.asyncos806.Config objConfig = 
					   (com.cisco.policyconversiontool.dto.wsa.asyncos806.Config) 
			objPolicyConversionToolServiceImpl.validateInitalConfig(objPolicyConversionParameters, reviewBuffer);
	   }
	   /**
	    * Test case created to validate behavior If web reputation is not enabled in default policy
	    * For asyncos 805.
	    * @throws Exception 
	    */
	   @Test 
	   public void acceptanceCriteria1_testCase5() throws Exception {
		   
		   String wsaInitialConfig = null;
		   PolicyConversionParameters objPolicyConversionParameters = null;
		   StringBuffer reviewBuffer = null;
		 
		   
		   reviewBuffer = new StringBuffer();
		   wsaInitialConfig = TestUtil.getStringFromInputStream(TestUtil.getInputStreams(TestUtil.WSA_INIT_CONFIG_WF));
		   objPolicyConversionParameters = TestUtil.getMockedPolicyConversionParameters("{}", wsaInitialConfig);
		   objPolicyConversionToolServiceImpl.validateInitalConfig(objPolicyConversionParameters, reviewBuffer);
		   assertTrue(reviewBuffer.indexOf("Web Reputation") < 0);
		   
		   
		   reviewBuffer = new StringBuffer();
		   wsaInitialConfig = TestUtil.getStringFromInputStream(TestUtil.getInputStreams(TestUtil.WSA_INIT_CONFIG_NEG_TEST));
		   objPolicyConversionParameters = TestUtil.getMockedPolicyConversionParameters("{}", wsaInitialConfig);
		   objPolicyConversionToolServiceImpl.validateInitalConfig(objPolicyConversionParameters, reviewBuffer);
		   assertTrue(reviewBuffer.indexOf("Web Reputation") > 0);
		   
	   }
	   /**
	    * Test case created to validate behavior If web reputation is not enabled in default policy
	    * For asyncos 806.
	    * @throws Exception 
	    */
	  
	   @Test 
	   public void acceptanceCriteria1_testCase6() throws Exception {
		  
		   String wsaInitialConfig = null;
		   PolicyConversionParameters objPolicyConversionParameters = null;
		   StringBuffer reviewBuffer = null;
		 
		   
		   reviewBuffer = new StringBuffer();
		   wsaInitialConfig = TestUtil.getStringFromInputStream(TestUtil.getInputStreams(TestUtil.WSA_INIT_CONFIG_WF));
		   objPolicyConversionParameters = TestUtil.getMockedPolicyConversionParameters("{}", wsaInitialConfig);
		   when(objPolicyConversionParameters.getTargetSoftware()).thenReturn(Constants.TARGET_SOWFWARE_WSA_ASYNCOS806);
		   objPolicyConversionToolServiceImpl.validateInitalConfig(objPolicyConversionParameters, reviewBuffer);
		   assertTrue(reviewBuffer.indexOf("Web Reputation") < 0);
		   
		   
		   reviewBuffer = new StringBuffer();
		   wsaInitialConfig = TestUtil.getStringFromInputStream(TestUtil.getInputStreams(TestUtil.WSA_INIT_CONFIG_NEG_TEST));
		   objPolicyConversionParameters = TestUtil.getMockedPolicyConversionParameters("{}", wsaInitialConfig);
		   when(objPolicyConversionParameters.getTargetSoftware()).thenReturn(Constants.TARGET_SOWFWARE_WSA_ASYNCOS806);
		   objPolicyConversionToolServiceImpl.validateInitalConfig(objPolicyConversionParameters, reviewBuffer);
		   assertTrue(reviewBuffer.indexOf("Web Reputation") > 0);
	   }
	   /**
	    * Test case created to validate behavior of checkHttpsCertificate method.
	    * For asyncos : 805
		 * @throws Exception 
		 * @throws FileNotFoundException 
	    */
	   @Test
	   public void acceptanceCriteria1_testCase7() throws FileNotFoundException, Exception{

		   String wsaInitialConfig = null;
		   PolicyConversionParameters objPolicyConversionParameters = null;
		   StringBuffer reviewBuffer = null;
		 
		   
		   reviewBuffer = new StringBuffer();
		   wsaInitialConfig = TestUtil.getStringFromInputStream(TestUtil.getInputStreams(TestUtil.WSA_INIT_CONFIG_WF));
		   objPolicyConversionParameters = TestUtil.getMockedPolicyConversionParameters("{}", wsaInitialConfig);
		   objPolicyConversionToolServiceImpl.validateInitalConfig(objPolicyConversionParameters, reviewBuffer);
		   assertTrue(reviewBuffer.indexOf("Https Certificate") < 0);
		   
		   
		   reviewBuffer = new StringBuffer();
		   wsaInitialConfig = TestUtil.getStringFromInputStream(TestUtil.getInputStreams(TestUtil.WSA_INIT_CONFIG_NEG_TEST));
		   objPolicyConversionParameters = TestUtil.getMockedPolicyConversionParameters("{}", wsaInitialConfig);
		   objPolicyConversionToolServiceImpl.validateInitalConfig(objPolicyConversionParameters, reviewBuffer);
		   assertTrue(reviewBuffer.indexOf("Https Certificate") > 0);
	   }
	   /**
	    * Test case created to validate behavior of checkHttpsCertificate method.
	    * For asyncos : 806
		 * @throws Exception 
		 * @throws FileNotFoundException 
	    */
	   @Test
	   public void acceptanceCriteria1_testCase8() throws FileNotFoundException, Exception{

		   String wsaInitialConfig = null;
		   PolicyConversionParameters objPolicyConversionParameters = null;
		   StringBuffer reviewBuffer = null;
		 
		   
		   reviewBuffer = new StringBuffer();
		   wsaInitialConfig = TestUtil.getStringFromInputStream(TestUtil.getInputStreams(TestUtil.WSA_INIT_CONFIG_WF));
		   objPolicyConversionParameters = TestUtil.getMockedPolicyConversionParameters("{}", wsaInitialConfig);
		   when(objPolicyConversionParameters.getTargetSoftware()).thenReturn(Constants.TARGET_SOWFWARE_WSA_ASYNCOS806);
		   objPolicyConversionToolServiceImpl.validateInitalConfig(objPolicyConversionParameters, reviewBuffer);
		   assertTrue(reviewBuffer.indexOf("Https Certificate") < 0);
		   
		   
		   reviewBuffer = new StringBuffer();
		   wsaInitialConfig = TestUtil.getStringFromInputStream(TestUtil.getInputStreams(TestUtil.WSA_INIT_CONFIG_NEG_TEST));
		   objPolicyConversionParameters = TestUtil.getMockedPolicyConversionParameters("{}", wsaInitialConfig);
		   when(objPolicyConversionParameters.getTargetSoftware()).thenReturn(Constants.TARGET_SOWFWARE_WSA_ASYNCOS806);
		   objPolicyConversionToolServiceImpl.validateInitalConfig(objPolicyConversionParameters, reviewBuffer);
		   assertTrue(reviewBuffer.indexOf("Https Certificate") > 0);
	   }
	   
	   public static void main(String[] args) {
		     Result result = JUnitCore.runClasses(MockTestClassFirst.class);
		      for (Failure failure : result.getFailures()) {
		         System.out.println(failure.getDescription()+"==Exception Msg :==>"+failure.getMessage()+"==>"+ failure.getTrace());
		      }
		      System.out.print("==>Test Result : "+result.wasSuccessful());
		   }
	   
}