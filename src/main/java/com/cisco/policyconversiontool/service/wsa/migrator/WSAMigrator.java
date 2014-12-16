package com.cisco.policyconversiontool.service.wsa.migrator;
 
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.cisco.policyconversiontool.dao.ApplicationDAO;
import com.cisco.policyconversiontool.dao.URLCategoryDAO;
import com.cisco.policyconversiontool.dto.URLCategory;
import com.cisco.policyconversiontool.dto.cws.AdvRule;
import com.cisco.policyconversiontool.dto.cws.AdvRuleArgument;
import com.cisco.policyconversiontool.dto.cws.AuthGroup;
import com.cisco.policyconversiontool.dto.cws.BlacklistDomain;
import com.cisco.policyconversiontool.dto.cws.BlacklistIp;
import com.cisco.policyconversiontool.dto.cws.CWSPolicy;
import com.cisco.policyconversiontool.dto.cws.FilterPolicy;
import com.cisco.policyconversiontool.dto.cws.HttpCategory;
import com.cisco.policyconversiontool.dto.cws.IpExpression;
import com.cisco.policyconversiontool.dto.cws.Schedule;
import com.cisco.policyconversiontool.dto.cws.User;
import com.cisco.policyconversiontool.dto.wsa.wsanormalized.WSACategory;
import com.cisco.policyconversiontool.dto.wsa.wsanormalized.WSACustomCategory;
import com.cisco.policyconversiontool.dto.wsa.wsanormalized.WSAIdentity;
import com.cisco.policyconversiontool.dto.wsa.wsanormalized.WSAMigratedConfig;
import com.cisco.policyconversiontool.dto.wsa.wsanormalized.WSAPolicy;
import com.cisco.policyconversiontool.dto.wsa.wsanormalized.WSATimeDefinition;
import com.cisco.policyconversiontool.dto.wsa.wsanormalized.WSATimeRange;
import com.cisco.policyconversiontool.service.util.Constants;
import com.cisco.policyconversiontool.service.util.LogUtil;
 


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

	public WSAMigratedConfig generateWSAPolicyConfig(CWSPolicy objCWSPolicy, StringBuffer reviewBuffer) throws Exception 
	{
		WSAMigratedConfig objWSAMigratedConfig = new WSAMigratedConfig();
		
		// Log unused Http Filters.....
		logUnusedHttpFilters(objCWSPolicy,reviewBuffer);
		
		Map<String,WSATimeDefinition> wsaTimeDefinitionMap = convertScheduleToWSATimeRanges(objCWSPolicy.getSchedules());
		Map<String,WSAIdentity> wsaIdentityMap = getAuthGroupsIdentity(objCWSPolicy.getAuthGroups(),reviewBuffer) ;
		List<WSAPolicy> wsaPolicyList= getWSAPolicies(objCWSPolicy,wsaIdentityMap,wsaTimeDefinitionMap,reviewBuffer);
		
		objWSAMigratedConfig.setWsaIdentityList(getIdentityListFromMap(wsaIdentityMap));
		objWSAMigratedConfig.setWsaTimeDefinitionList(getTimeDefinitionListFromMap(wsaTimeDefinitionMap));
		objWSAMigratedConfig.setWsaPolicyList(wsaPolicyList);
		
		return objWSAMigratedConfig;
	}
	private List<WSATimeDefinition> getTimeDefinitionListFromMap(Map<String, WSATimeDefinition> wsaTimeDefinitionMap) {
		List<WSATimeDefinition> wsaTimeDefinitionList = new ArrayList<WSATimeDefinition>();
		for(String key : wsaTimeDefinitionMap.keySet())
		{
			wsaTimeDefinitionList.add(wsaTimeDefinitionMap.get(key));
		}
		return wsaTimeDefinitionList;
	}

	private List<WSAIdentity> getIdentityListFromMap(Map<String, WSAIdentity> wsaIdentityMap) {
		List<WSAIdentity> wsaIdentityList = new ArrayList<WSAIdentity>();
		for(String key : wsaIdentityMap.keySet())
		{
			wsaIdentityList.add(wsaIdentityMap.get(key));
		}
		return wsaIdentityList;
	}

	private List<WSAPolicy> getWSAPolicies(CWSPolicy objCWSPolicy,Map<String,WSAIdentity> wsaIdentityMap, Map<String,WSATimeDefinition> wsaTimeDefinitionMap,StringBuffer reviewBuffer) throws IOException
	{
		List<WSAPolicy> wsaPolicyList = new ArrayList<WSAPolicy>();
		String scenarioFirst[] = {"N.A","N.A","monitor","monitor","monitor","monitor","block","monitor"};
		String scenarioSecond[] = {"N.A","N.A","block","block","block","block","monitor","block"};
		String scenarioThird[] = {"N.A","N.A","warn","warn","warn","warn","block","warn"};
		String scenarioFourth[] =  {"N.A","N.A","block","block","block","block","warn","block"};
		String scenario[] = null;
		StringBuffer reviewBufferLocal = new StringBuffer();
		for(AdvRule objAdvRule : objCWSPolicy.getAdvRules())
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
						 FilterPolicy objFilterPolicy = getFilterPolicyListFromName(objCWSPolicy.getFilterPolicies(),filterPolicyName);
						 AuthGroup objAuthGroup = getAuthGroupFromAuthGroupList(objCWSPolicy.getAuthGroups(), authGroupName);
						 
						 wsaPolicyList.add(createWSAPolicy(ruleName,ruleDescription,authGroupName,scheduleName,isScheduleException,filterPolicyName,scenario,wsaIdentityMap, wsaTimeDefinitionMap,reviewBuffer,objFilterPolicy,objAuthGroup));
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

	private FilterPolicy getFilterPolicyListFromName(List<FilterPolicy> filterPolicyList, String filterPolicyName) {
		
		for(FilterPolicy objFilterPolicy : filterPolicyList)
		{
			if(filterPolicyName.equals(objFilterPolicy.getName()))
				return objFilterPolicy;
		}
		return null;
	}
	
	private AuthGroup getAuthGroupFromAuthGroupList(List<AuthGroup> authGroupList, String authGroupName) {
		
		for(AuthGroup objAuthGroup : authGroupList)
		{
			if(authGroupName.equals(objAuthGroup.getName()))
				return objAuthGroup;
		}
		return null;
	}
	
	public WSAPolicy createWSAPolicy(String ruleName,String ruleDescription,String authGroupName,String scheduleName,boolean isScheduleException,String filterPolicyName,String [] scenario,Map<String,WSAIdentity> wsaIdentityMap, Map<String,WSATimeDefinition> wsaTimeDefinitionMap,StringBuffer reviewBuffer,FilterPolicy objFilterPolicy,AuthGroup objAuthGroup) throws IOException
	{
		 WSAPolicy objWSAPolicy = new WSAPolicy();
		 String wsaPolicyName = (ruleName+Constants.POLICY_NAME_SEPRATOR+authGroupName+Constants.POLICY_NAME_SEPRATOR+scheduleName+Constants.POLICY_NAME_SEPRATOR+filterPolicyName).replace("/", "").replace(":", "").replace(" ", "");
		 System.out.println(wsaPolicyName);
		 objWSAPolicy.setName(wsaPolicyName);
		 objWSAPolicy.setDescription((ruleDescription==null?wsaPolicyName:ruleDescription)+".Description");
		 
		 List<WSAIdentity> wsaIdentityList  = new ArrayList<WSAIdentity>();
		 
		 // iterate over auth group=>users.....
		 for(User objUser : objAuthGroup.getUsers())
		 {
			 wsaIdentityList.add(wsaIdentityMap.get(objUser.getName()));
		 }
		 // iterate over auth group==>ip Expressions.....
		 for(IpExpression objIpExpressions : objAuthGroup.getIpExpressions())
		 {
			 wsaIdentityList.add(wsaIdentityMap.get(objIpExpressions.getExpression()));
		 }
		 
		 objWSAPolicy.setWsaIdentityList(wsaIdentityList);
		 
		 objWSAPolicy.setTimeDefinition(wsaTimeDefinitionMap.get(scheduleName));
		 
//		 url categories
		 objWSAPolicy.setWsaCategoryList(getHttpCategoryToURLCategory(objFilterPolicy.getHttpCategories(),reviewBuffer,scenario[2],wsaPolicyName));
//		 Domains --- custom url categories
		 objWSAPolicy.setWsaCustomCategoryList(getDomainToCustomURLCategory(filterPolicyName,objFilterPolicy.getBlacklistDomains(),objFilterPolicy.getBlacklistIps(),scenario[3]));
//		 Applications
		 objWSAPolicy.setWsaApplicationList(null);
//		 User Agents
		 objWSAPolicy.setWsaUserAgentList(null);
//		 Custom User Agents
		 objWSAPolicy.setWsaCustomUserAgentList(null);
//		 Content Types
		 objWSAPolicy.setWsaMIMETypeList(null);

//		 Exception
		return objWSAPolicy;
	}
	/**Conversion of CWS Policy with Used Filters having Domains
	 **/

	private List<WSACustomCategory> getDomainToCustomURLCategory( String filterName,List<BlacklistDomain> blacklistDomainList,List<BlacklistIp> blacklistIpList , String action) throws IOException
	{
		List<WSACustomCategory> wsaCustomCategoryList =  new ArrayList<WSACustomCategory>();
		WSACustomCategory wsaCustomCategory	            =  new WSACustomCategory();
		List<String> sites							    =  new ArrayList<String>();
		wsaCustomCategory.setName(filterName);
		wsaCustomCategory.setAction(action);
		for (BlacklistDomain blacklistDomain : blacklistDomainList) {
			sites.add(blacklistDomain.getAddress().replaceFirst("www.", ""));
		}
		
		for (BlacklistIp blacklistIp : blacklistIpList) {
			sites.add(blacklistIp.getAddress());
		}
		
		wsaCustomCategory.setSites(sites);
		wsaCustomCategoryList.add(wsaCustomCategory);
		return wsaCustomCategoryList;
	}
	private List<WSACategory> getHttpCategoryToURLCategory(List<HttpCategory> httpCategoryList,StringBuffer reviewBuffer,String action,String policyName) throws IOException
	{
		List<WSACategory> wsaHttpCategoryList =  new ArrayList<WSACategory>();
		Map<String, URLCategory> urlCategoryMap 		= 	urlCategoryDAO.getURLCategoryMap("1");
		for (HttpCategory httpCategory : httpCategoryList) {
			String category 							= 	httpCategory.getName();
			URLCategory urlCategory						= 	urlCategoryMap.get(category);
			if (urlCategory != null) {
				String categorycode=urlCategory.getCode().trim();
				WSACategory wsaCategory 				= 	getWSACategory(categorycode,wsaHttpCategoryList);
				if (wsaCategory == null) {
					wsaCategory							= 	new WSACategory();
					wsaCategory.setId(categorycode);
					wsaCategory.setAction(action);
					wsaHttpCategoryList.add(wsaCategory);
				}
			}
			else{
				//TODO: Add the unmapped category in review file.
				reviewBuffer.append( "!*********************Policy Name : policyname*************************!\r\n" );
				reviewBuffer.append( "Category Name :	"+category.trim()+"\r\n\r\n" );
			}
		} 
		WSACategory wsaCategory							= new WSACategory();
		wsaCategory.setId(Constants.CATAGORY_CHILD_ABUSE_CONTENT_CODE);
		wsaCategory.setAction(Constants.ACTION_BLOCK);
		wsaHttpCategoryList.add(wsaCategory);
		return wsaHttpCategoryList;
	}

	private WSACategory getWSACategory(String categorycode, List<WSACategory> wsaCategoryList) 
	{
		for (WSACategory wsaCategory: wsaCategoryList) {	  
			if(wsaCategory == null || wsaCategory.getId() == null)
				continue;
			if(wsaCategory.getId().equals(categorycode)){
				return wsaCategory;
			}
		}
		return null;
	}
	
	private Map<String,WSAIdentity> getAuthGroupsIdentity( List<AuthGroup> authGroupList,StringBuffer reviewBuffer) throws IOException
	{
		Map<String,WSAIdentity> wsaIdentityMap 			= new  HashMap<String,WSAIdentity>();
		for (AuthGroup authGroup : authGroupList) {
			if(authGroup.getName().startsWith(Constants.WINNT)){
				WSAIdentity wsaIdentity    				= new WSAIdentity(); 
				wsaIdentity.setName(authGroup.getName().replace(Constants.WINNT, ""));
				wsaIdentity.setType(Constants.CLIENTTYPE_GROUP);
				wsaIdentityMap.put(authGroup.getName(), wsaIdentity);
			}else if(authGroup.getName().startsWith(Constants.LDAP)){
				reviewBuffer.append( "Identity Name :	"+authGroup.getName().replace(Constants.LDAP, "")+"\r\n\r\n");
			}else{

				List<User> userList  					= authGroup.getUsers();
				if( userList != null ){
					for (User user : userList) {
						if(user.getName().startsWith(Constants.LDAP))
						{
							reviewBuffer.append( "Identity Name :	"+user.getName().replace(Constants.LDAP, "")+"\r\n\r\n");

						}else {
							WSAIdentity wsaIdentity     = new WSAIdentity(); 
							wsaIdentity.setName(user.getName().replace(Constants.WINNT, ""));
							wsaIdentity.setType(Constants.CLIENTTYPE_USER);
							wsaIdentityMap.put(user.getName(),wsaIdentity);

						}
					}
				}
				List<IpExpression> IpExpressionList 	= authGroup.getIpExpressions();
				if( IpExpressionList != null ){
					for ( IpExpression ipExpression : IpExpressionList) {
						WSAIdentity wsaIdentity     	= new WSAIdentity(); 
						String identityName 			= ipExpression.getExpression();
						if(identityName.endsWith("/32")) {
						wsaIdentity.setName(identityName);
						wsaIdentity.setType(Constants.CLIENTTYPE_COMPUTER);
						} else {
						wsaIdentity.setName(identityName);
						wsaIdentity.setType(Constants.CLIENTTYPE_NETWORK);
						}
						wsaIdentityMap.put(identityName,wsaIdentity);
					}
				}
		  }
		}
		return wsaIdentityMap;
	}
	
	public void logUnusedHttpFilters(CWSPolicy objCWSPolicy, StringBuffer reviewBuffer)
	{
		String unusedFilters = "";
		Map<String,Boolean> usedUnusedFilterPolicy = getUsedUnusedFilterPolicy(objCWSPolicy);
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
			 reviewBuffer.append(Constants.NEW_LINE+LogUtil.getHeader("Unused Filter Policy"));
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
	public Map<String,WSATimeDefinition> convertScheduleToWSATimeRanges(List<Schedule> scheduleList)
	{
		Map<String,WSATimeDefinition> wsaTimeDefinitionMap = new HashMap<String,WSATimeDefinition>();
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
			wsaTimeDefinitionMap.put(objSchedule.getName(),objWSATimeDefinition);
		}
		return wsaTimeDefinitionMap;
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
	
}
