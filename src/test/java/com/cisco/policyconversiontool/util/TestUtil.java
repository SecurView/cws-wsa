package com.cisco.policyconversiontool.util;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.cisco.policyconversiontool.dto.cws.CWSPolicy;
import com.cisco.policyconversiontool.dto.cws.Schedule;
import com.cisco.policyconversiontool.dto.wsa.asyncos805.ProxAclGroup;
import com.cisco.policyconversiontool.dto.wsa.asyncos805.ProxAclPolicyGroups;
import com.cisco.policyconversiontool.dto.wsa.asyncos805.WgaConfig;
import com.cisco.policyconversiontool.dto.wsa.asyncos805.Config;
import com.cisco.policyconversiontool.service.cws.parsar.CWSParser;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

 

public class TestUtil {
	
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
	
   public static Config getMockedConfig()
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
 
   public static CWSPolicy getCWSPolicyConfig(String fileName) throws Exception
   {
	   return readCWSConfiguration(getInputStreams(fileName));
   }
   public static CWSPolicy readCWSConfiguration(InputStream cwsConfigStream) throws Exception {
	  
		return  (new CWSParser()).doParsing(cwsConfigStream);
	}
   public static InputStream getInputStreams(String fileName) throws FileNotFoundException 
   {
	   
	   return new FileInputStream("src/test/resource/fixtures/"+fileName);
   }

}
