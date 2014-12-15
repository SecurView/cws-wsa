package com.cisco.policyconversiontool;

 
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import java.io.FileNotFoundException;
import java.io.InputStream;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import com.cisco.policyconversiontool.service.PolicyConversionToolServiceImpl;
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
		   InputStream wsaInitialConfig = TestUtil.getInputStreams(TestUtil.WSA_INIT_CONFIG_WF);
		   com.cisco.policyconversiontool.dto.wsa.asyncos805.Config objConfig = (com.cisco.policyconversiontool.dto.wsa.asyncos805.Config) objPolicyConversionToolServiceImpl.readWSAConfiguration(wsaInitialConfig,"1");
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
	 		thrown.expectMessage("initial WSA configuration");
		   InputStream wsaInitialConfig = TestUtil.getInputStreams(TestUtil.WSA_INIT_CONFIG_NWF);
		   objPolicyConversionToolServiceImpl.readWSAConfiguration(wsaInitialConfig,"1");
	   }
	 	/**
	 	 * Test case created to validate behavior for correct ( well formed) WSA configuration.
	 	 * For asyncos : 806
	 	 * @throws Exception 
	 	 */
	   @Test
	   public void acceptanceCriteria1_testCase3() throws Exception {
		   InputStream wsaInitialConfig = TestUtil.getInputStreams(TestUtil.WSA_INIT_CONFIG_WF);
		   com.cisco.policyconversiontool.dto.wsa.asyncos806.Config objConfig = (com.cisco.policyconversiontool.dto.wsa.asyncos806.Config) objPolicyConversionToolServiceImpl.readWSAConfiguration(wsaInitialConfig,"2");
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
	 		thrown.expectMessage("initial WSA configuration");
		   InputStream wsaInitialConfig = TestUtil.getInputStreams(TestUtil.WSA_INIT_CONFIG_NWF);
		   objPolicyConversionToolServiceImpl.readWSAConfiguration(wsaInitialConfig,"2");
	   }
	   /**
	    * Test case created to validate behavior If web reputation is not enabled in default policy
	    * For asyncos 805.
	 * @throws Exception 
	    */
	  
	   @Test 
	   public void acceptanceCriteria1_testCase5() throws Exception {
		  
		   com.cisco.policyconversiontool.dto.wsa.asyncos805.Config objConfig = TestUtil.getMockedConfig805();
		   com.cisco.policyconversiontool.dto.wsa.asyncos805.ProxAclGroup objProxAclGroup = objConfig.getWgaConfig().getProxAclPolicyGroups().getProxAclGroup().get(0);
		   
		   when(objProxAclGroup.getProxAclGroupWbrsEnabled()).thenReturn("yes");
		   assertTrue(objPolicyConversionToolServiceImpl.checkWebReputation(objConfig,"1"));
		   verify(objProxAclGroup,atLeastOnce()).getProxAclGroupWbrsEnabled();
		   
		   when(objProxAclGroup.getProxAclGroupWbrsEnabled()).thenReturn("no");
		   assertFalse(objPolicyConversionToolServiceImpl.checkWebReputation(objConfig,"1"));
		   verify(objProxAclGroup,times(2)).getProxAclGroupWbrsEnabled();
		   
		   when(objProxAclGroup.getProxAclGroupWbrsEnabled()).thenReturn("");
		   assertFalse(objPolicyConversionToolServiceImpl.checkWebReputation(objConfig,"1"));
		   verify(objProxAclGroup,times(3)).getProxAclGroupWbrsEnabled();
	   }
	   /**
	    * Test case created to validate behavior If web reputation is not enabled in default policy
	    * For asyncos 806.
	    * @throws Exception 
	    */
	  
	   @Test 
	   public void acceptanceCriteria1_testCase6() throws Exception {
		  
		   com.cisco.policyconversiontool.dto.wsa.asyncos806.Config objConfig = TestUtil.getMockedConfig806();
		   com.cisco.policyconversiontool.dto.wsa.asyncos806.ProxAclGroup objProxAclGroup = objConfig.getWgaConfig().getProxAclPolicyGroups().getProxAclGroup().get(0);
		   
		   when(objProxAclGroup.getProxAclGroupWbrsEnabled()).thenReturn("yes");
		   assertTrue(objPolicyConversionToolServiceImpl.checkWebReputation(objConfig,"2"));
		   verify(objProxAclGroup,atLeastOnce()).getProxAclGroupWbrsEnabled();
		   
		   when(objProxAclGroup.getProxAclGroupWbrsEnabled()).thenReturn("no");
		   assertFalse(objPolicyConversionToolServiceImpl.checkWebReputation(objConfig,"2"));
		   verify(objProxAclGroup,times(2)).getProxAclGroupWbrsEnabled();
		   
		   when(objProxAclGroup.getProxAclGroupWbrsEnabled()).thenReturn("");
		   assertFalse(objPolicyConversionToolServiceImpl.checkWebReputation(objConfig,"2"));
		   verify(objProxAclGroup,times(3)).getProxAclGroupWbrsEnabled();
//		   
	   }
	   /**
	    * Test case created to validate behavior of checkHttpsCertificate method.
	    * For asyncos : 805
		 * @throws Exception 
		 * @throws FileNotFoundException 
	    */
	   @Test
	   public void acceptanceCriteria1_testCase7() throws FileNotFoundException, Exception{
		   com.cisco.policyconversiontool.dto.wsa.asyncos805.Config objConfig = TestUtil.getMockedConfig805();
		   com.cisco.policyconversiontool.dto.wsa.asyncos805.HttpsCertificate objHttpsCertificate= TestUtil.getMockedHttpsCertificates805("", "");
		   when(objConfig.getHttpsCertificate()).thenReturn(objHttpsCertificate);
		   assertFalse(objPolicyConversionToolServiceImpl.checkHttpsCertificates(objConfig, "1"));
		   
		   objHttpsCertificate= TestUtil.getMockedHttpsCertificates805(" ", "");
		   when(objConfig.getHttpsCertificate()).thenReturn(objHttpsCertificate);
		   assertTrue(objPolicyConversionToolServiceImpl.checkHttpsCertificates(objConfig, "1"));
	   }
	   /**
	    * Test case created to validate behavior of checkHttpsCertificate method.
	    * For asyncos : 806
		 * @throws Exception 
		 * @throws FileNotFoundException 
	    */
	   @Test
	   public void acceptanceCriteria1_testCase8() throws FileNotFoundException, Exception{
		   com.cisco.policyconversiontool.dto.wsa.asyncos806.Config objConfig = TestUtil.getMockedConfig806();
		   com.cisco.policyconversiontool.dto.wsa.asyncos806.HttpsCertificate objHttpsCertificate= TestUtil.getMockedHttpsCertificates806("", "");
		   when(objConfig.getHttpsCertificate()).thenReturn(objHttpsCertificate);
		   assertFalse(objPolicyConversionToolServiceImpl.checkHttpsCertificates(objConfig, "2"));
		   
		   objHttpsCertificate= TestUtil.getMockedHttpsCertificates806(" ", "");
		   when(objConfig.getHttpsCertificate()).thenReturn(objHttpsCertificate);
		   assertTrue(objPolicyConversionToolServiceImpl.checkHttpsCertificates(objConfig, "2"));
	   }
	   
	   public static void main(String[] args) {
		     Result result = JUnitCore.runClasses(MockTestClassFirst.class);
		      for (Failure failure : result.getFailures()) {
		         System.out.println(failure.getDescription()+"==Exception Msg :==>"+failure.getMessage()+"==>" + failure.getTrace());
		      }
		      System.out.print("==>Test Result : "+result.wasSuccessful());
		   }
	   
}