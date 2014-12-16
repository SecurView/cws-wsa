package com.cisco.policyconversiontool.util;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.cisco.policyconversiontool.dto.cws.CWSPolicy;
import com.cisco.policyconversiontool.dto.cws.Schedule;
import com.cisco.policyconversiontool.service.cws.parsar.CWSParser;
import com.cisco.policyconversiontool.service.util.DTDProvider;

public class TestUtil {
	public final static String WSA = "wsa";
	public final static String CWS = "cws";
	public final static String WSA_INIT_CONFIG_NWF = "wsa/wsaInitialConfigNWF.xml";  // WSA intital Configuartion xml file name which is not well formed...
	public final static String WSA_INIT_CONFIG_WF = "wsa/wsaInitialConfig.xml";  // WSA intital Configuartion xml file name which is well formed...
	public final static String CWS_CONFIG_WF = "cws/cwsConfig.json";  // cws Configuartion JSON file name which is well formed...
	public final static String CWS_CONFIG_NWF = "cws/cwsConfigNWF.json";  // cws Configuartion JSON file name which is NOT well formed...
	public final static String WSA_INIT_CONFIG_NEG_TEST = "wsa/wsaInitialConfig_neg_test.xml";  // WSA Configuartion xml file name which is well formed and used for negative test...
	public final static String CWS_CONFIG_FILE_PATH = "cws/SecurView_6_CiscoFixes.json";  // WSA intital Configuartion xml file name which is well formed...
	public final static String CWS_CONFIG_FILE_PATH_US004 = "cws/SecurView__CiscoFixes__US004.json";  // WSA intital Configuartion xml file name which is well formed...
	
	public static List<Schedule> getSchedule(){
        ArrayList<Schedule> scheduleList = new ArrayList<Schedule>();
        for (int i = 1; i < 6; i++) {
            Schedule schedule =  new Schedule();
            schedule.setName("Test Schedule-"+i);
            schedule.setFrom(i+":"+i+":00");
            schedule.setTo((i+1)+":"+(i+1)+":00");
            List<String> dayList = new ArrayList<String>();
            dayList.add("false");
            dayList.add("true");
            dayList.add("true");
            dayList.add("false");
            dayList.add("true");
            dayList.add("true");
            dayList.add("true");
            schedule.setDays(dayList);
            schedule.setTimezone("Etc/GMT");
            scheduleList.add(schedule);
        }
        return scheduleList;
    }
	public static com.cisco.policyconversiontool.dto.wsa.asyncos805.Config getMockedConfig805()
   {
		com.cisco.policyconversiontool.dto.wsa.asyncos805.Config objConfig = mock(com.cisco.policyconversiontool.dto.wsa.asyncos805.Config.class);
	   
		com.cisco.policyconversiontool.dto.wsa.asyncos805.WgaConfig objWgaConfig = mock(com.cisco.policyconversiontool.dto.wsa.asyncos805.WgaConfig.class);
	   when(objConfig.getWgaConfig()).thenReturn(objWgaConfig);
	   
	   com.cisco.policyconversiontool.dto.wsa.asyncos805.ProxAclPolicyGroups objProxAclGroups = mock(com.cisco.policyconversiontool.dto.wsa.asyncos805.ProxAclPolicyGroups.class);
	   when(objWgaConfig.getProxAclPolicyGroups()).thenReturn(objProxAclGroups);
	   
	   ArrayList<com.cisco.policyconversiontool.dto.wsa.asyncos805.ProxAclGroup> objProxAclGroupList = mock(ArrayList.class);
	   when(objProxAclGroups.getProxAclGroup()).thenReturn(objProxAclGroupList);
	   
	   com.cisco.policyconversiontool.dto.wsa.asyncos805.ProxAclGroup objProxAclGroup = mock(com.cisco.policyconversiontool.dto.wsa.asyncos805.ProxAclGroup.class);
	   when(objProxAclGroupList.get(0)).thenReturn(objProxAclGroup);
	   
	   return objConfig;
   }
public static com.cisco.policyconversiontool.dto.wsa.asyncos806.Config getMockedConfig806()
   {
		com.cisco.policyconversiontool.dto.wsa.asyncos806.Config objConfig = mock(com.cisco.policyconversiontool.dto.wsa.asyncos806.Config.class);
	   
		com.cisco.policyconversiontool.dto.wsa.asyncos806.WgaConfig objWgaConfig = mock(com.cisco.policyconversiontool.dto.wsa.asyncos806.WgaConfig.class);
	   when(objConfig.getWgaConfig()).thenReturn(objWgaConfig);
	   
	   com.cisco.policyconversiontool.dto.wsa.asyncos806.ProxAclPolicyGroups objProxAclGroups = mock(com.cisco.policyconversiontool.dto.wsa.asyncos806.ProxAclPolicyGroups.class);
	   when(objWgaConfig.getProxAclPolicyGroups()).thenReturn(objProxAclGroups);
	   
	   ArrayList<com.cisco.policyconversiontool.dto.wsa.asyncos806.ProxAclGroup> objProxAclGroupList = mock(ArrayList.class);
	   when(objProxAclGroups.getProxAclGroup()).thenReturn(objProxAclGroupList);
	   
	   com.cisco.policyconversiontool.dto.wsa.asyncos806.ProxAclGroup objProxAclGroup = mock(com.cisco.policyconversiontool.dto.wsa.asyncos806.ProxAclGroup.class);
	   when(objProxAclGroupList.get(0)).thenReturn(objProxAclGroup);
	   
	   return objConfig;
   }
 
	public static com.cisco.policyconversiontool.dto.wsa.asyncos805.HttpsCertificate getMockedHttpsCertificates805(String certificate,String key)
	{
		com.cisco.policyconversiontool.dto.wsa.asyncos805.HttpsCertificate objHttpsCertificate = mock(com.cisco.policyconversiontool.dto.wsa.asyncos805.HttpsCertificate.class);
		when(objHttpsCertificate.getCertificate()).thenReturn(certificate);
		when(objHttpsCertificate.getKey()).thenReturn(key);
		return objHttpsCertificate;
	}
	public static com.cisco.policyconversiontool.dto.wsa.asyncos806.HttpsCertificate getMockedHttpsCertificates806(String certificate,String key)
	{
		com.cisco.policyconversiontool.dto.wsa.asyncos806.HttpsCertificate objHttpsCertificate = mock(com.cisco.policyconversiontool.dto.wsa.asyncos806.HttpsCertificate.class);
		when(objHttpsCertificate.getCertificate()).thenReturn(certificate);
		when(objHttpsCertificate.getKey()).thenReturn(key);
		return objHttpsCertificate;
	}
   public static CWSPolicy getCWSPolicyConfig(String fileName) throws Exception
   {
	   return readCWSConfiguration(getStringFromInputStream(getInputStreams(fileName)));
   }
   public static CWSPolicy readCWSConfiguration(String cwsConfig) throws Exception {
	  
		return  (new CWSParser()).doParsing(cwsConfig);
	}
   public static InputStream getInputStreams(String fileName) throws FileNotFoundException 
   {
	   
	   return new FileInputStream("src/test/resource/fixtures/"+fileName);
   }
   public static void policyConversionToolInitialSetup()
   {
//	   DTDProvider.setAsyncos805DTD(new File("src//main//resource//config//wsaDTD805.dtd"));
//       DTDProvider.setAsyncos806DTD(new File("src//main//resource//config//wsaDTD806.dtd"));
   }
   public static String getStringFromInputStream(InputStream is) {
		 
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		String line;
		try {
			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line+"\r\n");
			}
		} catch (IOException e) {
//			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
//					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}
}
