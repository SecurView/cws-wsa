package com.cisco.policyconversiontool.service.wsa.migrator;
 
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.cisco.policyconversiontool.dao.ApplicationDAO;
import com.cisco.policyconversiontool.dao.URLCategoryDAO;
import com.cisco.policyconversiontool.dto.WSAMigrationParameters;
import com.cisco.policyconversiontool.dto.cws.AdvRule;
import com.cisco.policyconversiontool.dto.cws.AdvRuleArgument;
import com.cisco.policyconversiontool.dto.cws.AuthGroup;
import com.cisco.policyconversiontool.dto.cws.CWSPolicy;
import com.cisco.policyconversiontool.dto.cws.FilterPolicy;
import com.cisco.policyconversiontool.dto.cws.IpExpression;
import com.cisco.policyconversiontool.dto.cws.Schedule;
import com.cisco.policyconversiontool.dto.cws.User;
import com.cisco.policyconversiontool.dto.wsa.asyncos805.Config;
import com.cisco.policyconversiontool.dto.wsa.asyncos805.ProxAclGroup;
import com.cisco.policyconversiontool.dto.wsa.asyncos805.ProxAclPolicyGroups;
import com.cisco.policyconversiontool.dto.wsa.wsanormalized.WSAIdentity;
import com.cisco.policyconversiontool.dto.wsa.wsanormalized.WSAMigratedConfig;
import com.cisco.policyconversiontool.dto.wsa.wsanormalized.WSAPolicy;
import com.cisco.policyconversiontool.dto.wsa.wsanormalized.WSATimeDefinition;
import com.cisco.policyconversiontool.dto.wsa.wsanormalized.WSATimeRange;
import com.cisco.policyconversiontool.service.cws.parsar.CWSParser;
import com.cisco.policyconversiontool.service.util.Constants;
import com.cisco.policyconversiontool.service.util.DTDEntityResolver;
import com.cisco.policyconversiontool.service.util.DTDProvider;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
 


public class WSAMigrator 
{
	//below fields are used for sequencingWSAPolicies() method
	private URLCategoryDAO urlCategoryDAO;
	private ApplicationDAO applicationDAO;
	
	public URLCategoryDAO getUrlCategoryDAO() {
		return urlCategoryDAO;
	}

	public void setUrlCategoryDAO(URLCategoryDAO urlCategoryDAO) {
		this.urlCategoryDAO = urlCategoryDAO;
	}

	public ApplicationDAO getApplicationDAO() {
		return applicationDAO;
	}

	public void setApplicationDAO(ApplicationDAO applicationDAO) {
		this.applicationDAO = applicationDAO;
	}

	public WSAMigratedConfig generateWSAPolicyConfig(CWSPolicy objCWSPolicy, WSAMigrationParameters wsaMigrationParameters) throws Exception 
	{
		StringBuffer reviewBuffer = new StringBuffer();
		WSAMigratedConfig objWSAMigratedConfig = new WSAMigratedConfig();
		
		Map<String,Boolean> usedUnusedFilterPolicy = getUsedUnusedFilterPolicy(objCWSPolicy);
		logUnusedHttpFilters(usedUnusedFilterPolicy,reviewBuffer);
		
		List<WSATimeDefinition> wsaTimeDefinitionList = convertScheduleToWSATimeRanges(objCWSPolicy.getSchedules());
		List<WSAIdentity> wsaIdentityList = null ;
		List<WSAPolicy> wsaPolicyList= getWSAPolicies(objCWSPolicy.getAdvRules(),wsaIdentityList,wsaTimeDefinitionList,reviewBuffer);
		
		
		
		
		return objWSAMigratedConfig;
	}
	private List<WSAPolicy> getWSAPolicies(List<AdvRule> advRulesList,List<WSAIdentity> wsaIdentityList,List<WSATimeDefinition> wsaTimeDefinitionList,StringBuffer reviewBuffer)
	{
		List<WSAPolicy> wsaPolicyList = new ArrayList<WSAPolicy>();
		String scenarioFirst[] = {"N.A","N.A","Monitor","Monitor","Monitor","Monitor","Block","Monitor"};
		String scenarioSecond[] = {"N.A","N.A","Block","Block","Block","Block","Monitor","Block"};
		String scenarioThird[] = {"N.A","N.A","Warn","Warn","Warn","Warn","Block","Warn"};
		String scenarioFourth[] =  {"N.A","N.A","Block","Block","Block","Block","Warn","Block"};
		String scenario[] = null;
		StringBuffer reviewBufferLocal = new StringBuffer();
		for(AdvRule objAdvRule : advRulesList)
		{
			 String ruleName = objAdvRule.getName();
			 String ruleDescription = objAdvRule.getDescription();
			 long ruleAction = objAdvRule.getAdvAction();
			 List<Integer> filterPolicyIndexList = new ArrayList<Integer>();
			 List<Integer> scheduleIndexList = new ArrayList<Integer>();
			 List<Integer> authGroupIndexList = new ArrayList<Integer>();
			 
			 List<AdvRuleArgument> advRuleArgumentsList = objAdvRule.getAdvRuleArguments();
			 for( int advRuleArgumentsIndex = 0 ; advRuleArgumentsIndex < advRuleArgumentsList.size(); advRuleArgumentsIndex++)
			 {
				 AdvRuleArgument objAdvRuleArgument = advRuleArgumentsList.get(advRuleArgumentsIndex);
				 if(objAdvRuleArgument.getArgType()==Constants.ARGTYPE_FILTER_POLICY_NAME)
				 {
					 if(objAdvRuleArgument.isException())
					 {
						// log filter Policy to review file.....
						 reviewBufferLocal.append(Constants.NEW_LINE+objAdvRuleArgument.getFilterPolicyName());
					 }else
					 {
						 filterPolicyIndexList.add(advRuleArgumentsIndex);
					 }
				 }else if(objAdvRuleArgument.getArgType()==Constants.ARGTYPE_SCHEDULE_NAME)
				 {
					 scheduleIndexList.add(advRuleArgumentsIndex);
				 }else if(objAdvRuleArgument.getArgType()==Constants.ARGTYPE_AUTHGROUP_NAME)
				 {
					 authGroupIndexList.add(advRuleArgumentsIndex);
				 }
			 }
			 
			 // iterate over authGroup
			 for(int authGroupIndex : authGroupIndexList)
			 {
				 String authGroupName = advRuleArgumentsList.get(authGroupIndex).getAuthGroupName();
				 boolean isAuthGroupException = advRuleArgumentsList.get(authGroupIndex).isException();
				 
				 //iterate over schedules
				 for(int schedulesIndex : scheduleIndexList)
				 {
					 String scheduleName = advRuleArgumentsList.get(schedulesIndex).getScheduleName();
					 boolean isScheduleException = advRuleArgumentsList.get(authGroupIndex).isException();
					 //iterate Over filterPolicy
					 for(int filterPolicyIndex : filterPolicyIndexList)
					 {
						 String filterPolicyName = advRuleArgumentsList.get(filterPolicyIndex).getFilterPolicyName();
						
						 if(isAuthGroupException)
						 {
							 if(ruleAction==Constants.ADV_RULE_ACTION_ALLOW)
							 {
								 scenario = scenarioSecond;
							 }else if(ruleAction==Constants.ADV_RULE_ACTION_BLOCK)
							 {
								 scenario = scenarioFirst;
							 } else if(ruleAction==Constants.ADV_RULE_ACTION_WARN)
							 {
								 scenario = scenarioFourth;
							 }
						 }else
						 {
							 if(ruleAction==Constants.ADV_RULE_ACTION_ALLOW)
							 {
								 scenario = scenarioFirst;
							 }else if(ruleAction==Constants.ADV_RULE_ACTION_BLOCK)
							 {
								 scenario = scenarioSecond;
							 } else if(ruleAction==Constants.ADV_RULE_ACTION_WARN)
							 {
								 scenario = scenarioThird;
							 }
						 }
						 
						 wsaPolicyList.add(createWSAPolicy(ruleName,ruleDescription,authGroupName,scheduleName,isScheduleException,filterPolicyName,scenario,wsaIdentityList,wsaTimeDefinitionList));
					 }
				 }
			 }
		}
		
		if(reviewBufferLocal.length() > 0)
		{
			reviewBuffer.append(Constants.NEW_LINE+"!******************************* Exception Filter Policy ***************************!");
			reviewBuffer.append(reviewBufferLocal);
		}
		return wsaPolicyList;
	}

	public WSAPolicy createWSAPolicy(String ruleName,String ruleDescription,String authGroupName,String scheduleName,boolean isScheduleException,String filterPolicyName,String [] scenario, List<WSAIdentity> wsaIdentityList, List<WSATimeDefinition> wsaTimeDefinitionList)
	{
		WSAPolicy objWSAPolicy = new WSAPolicy();
		 String wsaPolicyName = (ruleName+Constants.POLICY_NAME_SEPRATOR+authGroupName+Constants.POLICY_NAME_SEPRATOR+scheduleName+Constants.POLICY_NAME_SEPRATOR+filterPolicyName).replace("/", "").replace(":", "").replace(" ", "");
		 System.out.println(wsaPolicyName);
		 objWSAPolicy.setName(wsaPolicyName);
		 objWSAPolicy.setDescription((ruleDescription==null?"":ruleDescription)+".Description");
		 
		 
		 
		return objWSAPolicy;
	}
	public void logUnusedHttpFilters(Map<String,Boolean> usedUnusedFilterPolicy, StringBuffer reviewBuffer)
	{
		String unusedFilters = "";
		// iterate HashMap if value of filter Name gets false it is unused filter there should be entry in review file...
		 for(String filterName : usedUnusedFilterPolicy.keySet())
		 {
			 if(!usedUnusedFilterPolicy.get(filterName))
			 {
				 unusedFilters += Constants.NEW_LINE+filterName;
			 }
		 }
		 // if there are unused http filter then there should be header..
		 if(unusedFilters.length() > 0)
		 {
			 reviewBuffer.append(Constants.NEW_LINE+"********************************** Unused Filter Policy *****************************");
			 reviewBuffer.append(Constants.NEW_LINE + unusedFilters);
		 }
	}
	public Map<String,Boolean> getUsedUnusedFilterPolicy(CWSPolicy objCWSPolicy)
	{
		Map<String,Boolean> usedUnusedFilterPolicy = new HashMap<String,Boolean>();
		
		List<FilterPolicy> filterPolicyList = objCWSPolicy.getFilterPolicies();
		// put all filter policy into hash map and mark them as unused..
		for(int filterPolicyIndex=0; filterPolicyIndex < filterPolicyList.size(); filterPolicyIndex++)
		{
			usedUnusedFilterPolicy.put(filterPolicyList.get(filterPolicyIndex).getName(), false);
		}
		// iterate over advRules
		// occurrence of filterName in advRule will considered as used HTTP Filter. so it will be mark as used in hashmap.
		List<AdvRule> advRuleList = objCWSPolicy.getAdvRules();
		for(int advRuleIndex = 0; advRuleIndex < advRuleList.size(); advRuleIndex++)
		{
			AdvRule objAdvRule = advRuleList.get(advRuleIndex);
			List<AdvRuleArgument> advRuleArgumentList = objAdvRule.getAdvRuleArguments();
			// in list of advRuleArgument there will one such object where filterPolicyname will not null..
			for(int advRuleArgumentIndex = 0 ; advRuleArgumentIndex < advRuleArgumentList.size(); advRuleArgumentIndex++)
			{
				AdvRuleArgument objAdvRuleArgument = advRuleArgumentList.get(advRuleArgumentIndex);
				if(objAdvRuleArgument.getFilterPolicyName()!=null)
				{
					usedUnusedFilterPolicy.put(objAdvRuleArgument.getFilterPolicyName(), true);
					break;
				}
			}
		}
		return usedUnusedFilterPolicy;
	}
	public List<WSATimeDefinition> convertScheduleToWSATimeRanges(List<Schedule> scheduleList)
	{
		List<WSATimeDefinition> wsaTimeDefinitionList = new ArrayList<WSATimeDefinition>();
		Schedule objSchedule = null;
		String weekday[] = { Constants.SUNDAY , Constants.MONDAY , Constants.TUESDAY , Constants.WEDNESDAY , Constants.THURSDAY , Constants.FRIDAY , Constants.SATURDAY };
		
		for(int schedulIndex = 0; schedulIndex < scheduleList.size(); schedulIndex++)
		{
			objSchedule = scheduleList.get(schedulIndex);
			WSATimeDefinition objWSATimeDefinition = new WSATimeDefinition();
			objWSATimeDefinition.setName(objSchedule.getName());
			objWSATimeDefinition.setTimeZone(objSchedule.getTimezone());
			List<WSATimeRange> wsaTimeRangeList = new ArrayList<WSATimeRange>(); 
			objWSATimeDefinition.setTimeRangeList(wsaTimeRangeList);
			
			WSATimeRange objWSATimeRange = new WSATimeRange();
			objWSATimeRange.setEndTime(objSchedule.getTo());
			objWSATimeRange.setStartTime(objSchedule.getFrom());
			List<String> objWeekDaysList = new ArrayList<String>();
			 
			for(int weekIndex = 0; weekIndex < 7; weekIndex++)
			{
				if(objSchedule.getDays()!=null && objSchedule.getDays().get(weekIndex).equalsIgnoreCase("true"))
				{	
					objWeekDaysList.add(weekday[weekIndex]);
				}
			}
			objWSATimeRange.setValidDays(objWeekDaysList);
			wsaTimeRangeList.add(objWSATimeRange);
			wsaTimeDefinitionList.add(objWSATimeDefinition);
		}
		return wsaTimeDefinitionList;
	}
	
	public WSATimeDefinition getTimeDefinitionFromList(String timeDefinitionName,List<WSATimeDefinition> wsaTimeDefinitionList)
	{
		for(WSATimeDefinition objWSATimeDefinition : wsaTimeDefinitionList)
		{
			if(timeDefinitionName.equals(objWSATimeDefinition.getName()))
				return objWSATimeDefinition;
		}
		return null;
	}
	
	 
	public Config readWSAConfiguration(InputStream wsaInitialConfig) throws Exception  
	{
		Config objConfig = null;
		try{
		 	JAXBContext ctx = JAXBContext.newInstance(Config.class);
	        Unmarshaller unmarshaller = ctx.createUnmarshaller();
	        XMLReader xmlreader = XMLReaderFactory.createXMLReader();
	        DTDEntityResolver enRes = new DTDEntityResolver();
	        enRes.setFile(DTDProvider.getAsyncos806DTD());
	        xmlreader.setEntityResolver(enRes);
	        InputSource input = new InputSource(wsaInitialConfig);
	        Source source = new SAXSource(xmlreader, input);
	        objConfig = (Config)unmarshaller.unmarshal(source);
		}catch(Exception e)
		{
			throw new Exception("Invalid initial WSA configuration");
		}

		return objConfig;
	}
	public boolean checkWebReputation(Config objConfig)
	{
		//prox_acl_policy_groups
		ProxAclPolicyGroups objProxAclPolicyGroup = objConfig.getWgaConfig().getProxAclPolicyGroups();
		ProxAclGroup objProxAclGroup = objProxAclPolicyGroup.getProxAclGroup().get(0);
		if(objProxAclGroup.getProxAclGroupWbrsEnabled().equalsIgnoreCase("yes"))
			return true;
		else
			return false;
	}
	public boolean checkHttpsCertificates(Config objConfig,CWSPolicy objCWSPolicy)
	{
		ArrayList<Object> httpsCertificateList = (ArrayList<Object>) objCWSPolicy.getHttpsCerts();
		if(httpsCertificateList==null)
			return false;
		if(objConfig.getHttpsCertificate()==null)
			return false;
		Object httpsCertificate = null;
		for(int httpsCertificateIndex = 0; httpsCertificateIndex < httpsCertificateList.size() ; httpsCertificateIndex++)
		{
			httpsCertificate  = httpsCertificateList.get(httpsCertificateIndex);
			if(objConfig.getHttpsCertificate().getCertificate().equals(httpsCertificate.toString()))
			{
					return true;
			}
		}
		return false;
	}
	public static void main(String arg[]) throws Exception
	{
		WSAMigrator objWSAMigrator = new WSAMigrator();
		objWSAMigrator.generateWSAPolicyConfig((new CWSParser()).doParsing(new FileInputStream("src/test/resource/fixtures/cws/SecurView_6_CiscoFixes.json")),null);
	}
}
