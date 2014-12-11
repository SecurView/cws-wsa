package com.cisco.policyconversiontool;

 
 

 

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import com.cisco.policyconversiontool.dto.cws.CWSPolicy;
import com.cisco.policyconversiontool.dto.wsa.asyncos805.Config;
import com.cisco.policyconversiontool.dto.wsa.asyncos805.ProxAclGroup;
import com.cisco.policyconversiontool.dto.wsa.asyncos805.ProxAclPolicyGroups;
import com.cisco.policyconversiontool.dto.wsa.asyncos805.WgaConfig;
import com.cisco.policyconversiontool.service.wsa.migrator.WSAMigrator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
 
public class MockTestUserStoryFirst {

	private final String WSA = "wsa";
	private final String CWS = "cws";
	private final String WSA_INIT_CONFIG_NWF = "wsa/wsaInitialConfigNWF.xml";  // WSA intital Configuartion xml file name which is not well formed...
	private final String WSA_INIT_CONFIG_WF = "wsa/wsaInitialConfig.xml";  // WSA intital Configuartion xml file name which is well formed...
	private final String CWS_CONFIG_WF = "cws/cwsConfig.json";  // cws Configuartion JSON file name which is well formed...
	private final String CWS_CONFIG_NWF = "cws/cwsConfigNWF.json";  // cws Configuartion JSON file name which is NOT well formed...
	private final String WSA_INIT_CONFIG_NEG_TEST = "wsa/wsaInitialConfig_neg_test.xml";  // WSA Configuartion xml file name which is well formed and used for negative test...
	
	WSAMigrator objWSAMigrator ;
	 
	 @BeforeClass
	   public void Before() throws Exception
	   {
		  objWSAMigrator = new WSAMigrator();
	   }
	 
	 @Rule public ExpectedException thrown = ExpectedException.none();
	 /**
	  * Test case created to validate behavior for incorrect (not well formed) WSA configuration.
	 * @throws Exception 
	  */
	 	@Test
	   public void acceptanceCriteria1_testCase2() throws Exception {
	 		thrown.expect(Exception.class);
	 		thrown.expectMessage("initial WSA configuration");
		   InputStream wsaInitialConfig = getInputStreams(WSA_INIT_CONFIG_NWF);
		   objWSAMigrator.readWSAConfiguration(wsaInitialConfig);
	   }
	 	/**
	 	 * Test case created to validate behavior for correct ( well formed) WSA configuration.
	 	 * @throws Exception 
	 	 */
	   @Test
	   public void acceptanceCriteria1_testCase1() throws Exception {
		   InputStream wsaInitialConfig = getInputStreams(WSA_INIT_CONFIG_WF);
		   Config objConfig = objWSAMigrator.readWSAConfiguration(wsaInitialConfig);
//		   assertThat(objConfig,IsNull.notNullValue());
	   }
	   /**
	    * Test case created to validate behavior for correct ( well formed) CWS configuration.
	    * @throws JsonParseException
	    * @throws JsonMappingException
	    * @throws IOException
	    */
	   @Test
	   public void acceptanceCriteria1_testCase3() throws JsonParseException, JsonMappingException, IOException {
		   InputStream cwsConfig = getInputStreams(CWS_CONFIG_WF);
		   CWSPolicy objCWSPolicy = objWSAMigrator.readCWSConfiguration(cwsConfig);
		   assertNotNull(objCWSPolicy);
	   }
	   
	   /**
	    * Test case created to validate behavior for incorrect (not well formed) WSA configuration.
	    * @throws JsonParseException
	    * @throws JsonMappingException
	    * @throws IOException
	    */
	   @Test(expected = JsonParseException.class)
	   public void acceptanceCriteria1_testCase4() throws JsonParseException, JsonMappingException, IOException {
		   InputStream cwsConfig = getInputStreams(CWS_CONFIG_NWF);
		   CWSPolicy objCWSPolicy = objWSAMigrator.readCWSConfiguration(cwsConfig);
	   }
	   
	   /**
	    * Test case created to validate behavior If web reputation is not enabled in default policy.
	    * @throws JsonParseException
	    * @throws JsonMappingException
	    * @throws IOException
	    */
	  
	   @Test 
	   public void acceptanceCriteria1_testCase5() throws JsonParseException, JsonMappingException, IOException {
		  
		 
		   Config objConfig = getMockedConfig();
		   ProxAclGroup objProxAclGroup = objConfig.getWgaConfig().getProxAclPolicyGroups().getProxAclGroup().get(0);
		   
		   when(objProxAclGroup.getProxAclGroupWbrsEnabled()).thenReturn("yes");
		   assertTrue(objWSAMigrator.checkWebReputation(objConfig));
		   verify(objProxAclGroup,atLeastOnce()).getProxAclGroupWbrsEnabled();
		   
		   when(objProxAclGroup.getProxAclGroupWbrsEnabled()).thenReturn("no");
		   assertFalse(objWSAMigrator.checkWebReputation(objConfig));
		   verify(objProxAclGroup,times(2)).getProxAclGroupWbrsEnabled();
		   
		   when(objProxAclGroup.getProxAclGroupWbrsEnabled()).thenReturn("");
		   assertFalse(objWSAMigrator.checkWebReputation(objConfig));
		   verify(objProxAclGroup,times(3)).getProxAclGroupWbrsEnabled();
		   
	   }
	   /**
	    * Test case created to validate behavior checkHttpsCertificates
	    */
	   @Test 
	   public void acceptanceCriteria1_testCase6(){
		   
	   }
	   
	   
	   /**
	    * Test case created to validate behavior of checkWebReputation method Expected : exception
	 * @throws Exception 
	 * @throws FileNotFoundException 
	    */
//	   @Test
//	   public void acceptanceCriteria1_testCase7() throws FileNotFoundException, Exception{
//		   thrown.expect(Exception.class);
//		   thrown.expectMessage("Web Reputation");
//		   objWSAMigrator.userStroyFirst(getInputStreams(WSA_INIT_CONFIG_NEG_TEST), getInputStreams(CWS_CONFIG_WF));
//	   }
	   
	   /**
	    * Test case created to validate behavior of checkWebReputation method in no exception Expected ..
	    * but expected exception in checkHttpsCertificate.
	 * @throws Exception 
	 * @throws FileNotFoundException 
	    */
	   
//	   @Test
//	   public void acceptanceCriteria1_testCase8() throws FileNotFoundException, Exception{
//		   thrown.expect(Exception.class);
//		   thrown.expectMessage("HTTPS certificate");
//		   thrown.expectMessage(" WSA and CWS are ");
////		   objWSAMigrator.userStroyFirst(getInputStreams(WSA_INIT_CONFIG_WF), getInputStreams(CWS_CONFIG_WF));
//	   }
	   
	   
	   /**
	    * 
	    * @param args
	    */
	   
	   
	   public static void main(String[] args) {
		     Result result = JUnitCore.runClasses(MockTestUserStoryFirst.class);
		      for (Failure failure : result.getFailures()) {
		         System.out.println(failure.getDescription()+"==Exception Msg :==>"+failure.getMessage()+"==>" + failure.getTrace());
		      }
		      System.out.print("==>Test Result : "+result.wasSuccessful());
		   }
	   
	   public Config getMockedConfig()
	   {
		   Config objConfig = mock(Config.class);
		   
		   WgaConfig objWgaConfig = mock(WgaConfig.class);
		   when(objConfig.getWgaConfig()).thenReturn(objWgaConfig);
		   
		   ProxAclPolicyGroups objProxAclGroups = mock(ProxAclPolicyGroups.class);
		   when(objWgaConfig.getProxAclPolicyGroups()).thenReturn(objProxAclGroups);
		   
		   ArrayList<ProxAclGroup> objProxAclGroupList = mock(ArrayList.class);
		   when(objProxAclGroups.getProxAclGroup()).thenReturn(objProxAclGroupList);
		   
		   ProxAclGroup objProxAclGroup = mock(ProxAclGroup.class);
		   when(objProxAclGroupList.get(0)).thenReturn(objProxAclGroup);
		   
		   return objConfig;
	   }
	 
	   public InputStream getInputStreams(String fileName) throws FileNotFoundException 
	   {
		
			return new FileInputStream("src/test/resources/fixtures/"+fileName);
	   }
	   
}