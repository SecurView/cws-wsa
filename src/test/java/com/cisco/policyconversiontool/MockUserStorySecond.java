package com.cisco.policyconversiontool;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.xml.sax.SAXException;

import com.cisco.policyconversiontool.dto.cws.CWSPolicy;
import com.cisco.policyconversiontool.dto.cws.FilterPolicy;
import com.cisco.policyconversiontool.dto.wsa.wsanormalized.WSATimeDefinition;
import com.cisco.policyconversiontool.service.wsa.migrator.WSAMigrator;
import com.cisco.policyconversiontool.util.TestUtil;

public class MockUserStorySecond {

	private final String CWS_CONFIG_FILE_PATH = "cws/SecurView_6_CiscoFixes.json";  // WSA intital Configuartion xml file name which is well formed...

	WSAMigrator objWSAMigrator ;
	 
	 @Before
	   public void Before() throws JAXBException, SAXException, FileNotFoundException
	   {
		  objWSAMigrator = new WSAMigrator();
	   }
	 
	 @Rule public ExpectedException thrown = ExpectedException.none();
	 /**
	  * Test case created to validate Converted Schedules to WSATimeDefinition.
	 * @throws Exception 
	  */
	 	@Test
	   public void convertSchduleToTimeDefinition_testCase1() throws Exception {
	 		List<WSATimeDefinition> wsaTimeDefinitionList = objWSAMigrator.convertSchedultToWSATimeRanges(TestUtil.getSchedule());
	 		
	 		WSATimeDefinition objWSATimeDefinition = wsaTimeDefinitionList.get(0);
	 		assertTrue(objWSATimeDefinition.getName().equals("Test Schedule-1"));
	 		assertTrue(objWSATimeDefinition.getTimeZone().equals("Etc/GMT"));
	 		assertTrue(objWSATimeDefinition.getTimeRangeList().get(0).getValidDays().get(0).equals("MON"));
	 		assertTrue(objWSATimeDefinition.getTimeRangeList().get(0).getValidDays().get(4).equals("SAT"));
	   }
	 	
 	 /**
	  * Test case created to validate list of unused http filter from the funtion getUnusedHttpFilters.
	 * @throws Exception 
	  */
	 	@Test
	   public void getUnusedHttpFilters_testCase1() throws Exception {
	 		
	 		CWSPolicy objCWSPolicy = TestUtil.getCWSPolicyConfig(CWS_CONFIG_FILE_PATH);
	 		
	 		Map<String,Boolean> usedUnusedFilterPolicy = objWSAMigrator.getUsedUnusedFilterPolicy(objCWSPolicy);
	 		assertTrue(usedUnusedFilterPolicy.get("default").equals(false));
	 		assertTrue(usedUnusedFilterPolicy.get("unused").equals(false));
	 		assertTrue(usedUnusedFilterPolicy.get("FilterOne").equals(true));
	 		
	 		objWSAMigrator.logUnusedHttpFilters(usedUnusedFilterPolicy);
			String reviewBuffer = objWSAMigrator.reviewBuffer.toString();
			assertTrue(reviewBuffer.contains("Unused Filter Policy"));
			assertTrue(reviewBuffer.contains("unused"));
			assertTrue(reviewBuffer.contains("default"));
	   }
	   
	   public static void main(String[] args) {
		     Result result = JUnitCore.runClasses(MockUserStorySecond.class);
		      for (Failure failure : result.getFailures()) {
		         System.out.println(failure.getDescription()+"==Exception Msg :==>"+failure.getMessage()+"==>" + failure.getTrace());
		      }
		      System.out.print("==>Test Result : "+result.wasSuccessful());
		   }
	   
	 
	   
}