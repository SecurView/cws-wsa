package com.cisco.policyconversiontool.service.wsa.migrator;
 
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

import com.cisco.policyconversiontool.dao.ApplicationDAO;
import com.cisco.policyconversiontool.dao.MIMETypeDAO;
import com.cisco.policyconversiontool.dao.URLCategoryDAO;
import com.cisco.policyconversiontool.dto.URLCategory;
import com.cisco.policyconversiontool.dto.cws.AdvRule;
import com.cisco.policyconversiontool.dto.cws.AdvRuleArgument;
import com.cisco.policyconversiontool.dto.cws.AuthGroup;
import com.cisco.policyconversiontool.dto.cws.BlacklistDomain;
import com.cisco.policyconversiontool.dto.cws.BlacklistIp;
import com.cisco.policyconversiontool.dto.cws.CWSPolicy;
import com.cisco.policyconversiontool.dto.cws.Category;
import com.cisco.policyconversiontool.dto.cws.ContentType;
import com.cisco.policyconversiontool.dto.cws.Domain;
import com.cisco.policyconversiontool.dto.cws.Exception;
import com.cisco.policyconversiontool.dto.cws.ExcludedNetwork;
import com.cisco.policyconversiontool.dto.cws.FilterPolicy;
import com.cisco.policyconversiontool.dto.cws.HttpCategory;
import com.cisco.policyconversiontool.dto.cws.HttpsCategory;
import com.cisco.policyconversiontool.dto.cws.HttpsFilter;
import com.cisco.policyconversiontool.dto.cws.HttpsRule;
import com.cisco.policyconversiontool.dto.cws.HttpsRuleFilter;
import com.cisco.policyconversiontool.dto.cws.HttpsRuleGroup;
import com.cisco.policyconversiontool.dto.cws.InboundExtension;
import com.cisco.policyconversiontool.dto.cws.IncludedNetwork;
import com.cisco.policyconversiontool.dto.cws.IpExpression;
import com.cisco.policyconversiontool.dto.cws.OutboundExtension;
import com.cisco.policyconversiontool.dto.cws.Schedule;
import com.cisco.policyconversiontool.dto.cws.User;
import com.cisco.policyconversiontool.dto.cws.WhitelistDomain;
import com.cisco.policyconversiontool.dto.cws.WhitelistIp;
import com.cisco.policyconversiontool.dto.wsa.asyncos805.HttpsCertificate;
import com.cisco.policyconversiontool.dto.wsa.asyncos806.Config;
import com.cisco.policyconversiontool.dto.wsa.wsanormalized.WSACategory;
import com.cisco.policyconversiontool.dto.wsa.wsanormalized.WSACustomCategory;
import com.cisco.policyconversiontool.dto.wsa.wsanormalized.WSAIdentity;
import com.cisco.policyconversiontool.dto.wsa.wsanormalized.WSAMIMEType;
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
	private MIMETypeDAO mimeTypeDAOImpl;
	
	public MIMETypeDAO getMimeTypeDAOImpl() {
		return mimeTypeDAOImpl;
	}

	public void setMimeTypeDAOImpl(MIMETypeDAO mimeTypeDAOImpl) {
		this.mimeTypeDAOImpl = mimeTypeDAOImpl;
	}
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

	public WSAMigratedConfig generateWSAPolicyConfig(CWSPolicy objCWSPolicy, StringBuffer reviewBuffer) throws IOException  
	{
		WSAMigratedConfig objWSAMigratedConfig = new WSAMigratedConfig();
		Map<String,String> mimeTypeMap = mimeTypeDAOImpl.getMIMETypeMap();
		// Log unused Http Filters.....
		logUnusedHttpFilters(objCWSPolicy,reviewBuffer);
		
		Map<String,WSATimeDefinition> wsaTimeDefinitionMap = convertScheduleToWSATimeRanges(objCWSPolicy.getSchedules());
		Map<String,WSAIdentity> wsaIdentityMap = getWSAIdentity(objCWSPolicy.getAuthGroups(),reviewBuffer) ;
		List<WSAPolicy> wsaPolicyList= getWSAPolicies(objCWSPolicy,wsaIdentityMap,wsaTimeDefinitionMap,reviewBuffer);
		
		objWSAMigratedConfig.setWsaIdentityList(getIdentityListFromMap(wsaIdentityMap));
		objWSAMigratedConfig.setWsaTimeDefinitionList(getTimeDefinitionListFromMap(wsaTimeDefinitionMap));
		objWSAMigratedConfig.setWsaPolicyList(wsaPolicyList);
		
		return objWSAMigratedConfig;
	}
	
	private List<WSAPolicy> getWSAPolicies(CWSPolicy objCWSPolicy,Map<String,WSAIdentity> wsaIdentityMap, Map<String,WSATimeDefinition> wsaTimeDefinitionMap,
											StringBuffer reviewBuffer) throws IOException{
		List<WSAPolicy> wsaPolicyList = new ArrayList<WSAPolicy>();
		String scenarioFirst[] = {"N.A","N.A","monitor","monitor","monitor","monitor","block","monitor"};
		String scenarioSecond[] = {"N.A","N.A","block","block","block","block","monitor","block"};
		String scenarioThird[] = {"N.A","N.A","warn","warn","warn","warn","block","warn"};
		String scenarioFourth[] =  {"N.A","N.A","block","block","block","block","warn","block"};
		String scenario[] = null;
		StringBuffer reviewBufferLocal = new StringBuffer();
		for(AdvRule objAdvRule : objCWSPolicy.getAdvRules()){
			 String ruleName = objAdvRule.getName();
			 String ruleDescription = objAdvRule.getDescription();
			 long ruleAction = objAdvRule.getAdvAction();
			 List<Integer> filterPolicyIndexList = new ArrayList<Integer>();
			 List<Integer> scheduleIndexList = new ArrayList<Integer>();
			 List<Integer> authGroupIndexList = new ArrayList<Integer>();
			 
			 List<AdvRuleArgument> advRuleArgumentsList = objAdvRule.getAdvRuleArguments();
			 for( int advRuleArgumentsIndex = 0 ; advRuleArgumentsIndex < advRuleArgumentsList.size(); advRuleArgumentsIndex++){
				 AdvRuleArgument objAdvRuleArgument = advRuleArgumentsList.get(advRuleArgumentsIndex);
				 if(objAdvRuleArgument.getArgType() == Constants.ARGTYPE_FILTER_POLICY_NAME){
					 if(objAdvRuleArgument.isException()){
						// log filter Policy to review file.....
						 reviewBufferLocal.append(Constants.NEW_LINE+objAdvRuleArgument.getFilterPolicyName());
					 }else{
						 filterPolicyIndexList.add(advRuleArgumentsIndex);
					 }
				 }else if(objAdvRuleArgument.getArgType() == Constants.ARGTYPE_SCHEDULE_NAME){
					 scheduleIndexList.add(advRuleArgumentsIndex);
				 }else if(objAdvRuleArgument.getArgType() == Constants.ARGTYPE_AUTHGROUP_NAME){
					 authGroupIndexList.add(advRuleArgumentsIndex);
				 }
			 }
			 
			 // iterate over authGroup
			 for(int authGroupIndex : authGroupIndexList){
				 String authGroupName = advRuleArgumentsList.get(authGroupIndex).getAuthGroupName();
				 boolean isAuthGroupException = advRuleArgumentsList.get(authGroupIndex).isException();
				 
				 //iterate over schedules
				 for(int schedulesIndex : scheduleIndexList){
					 String scheduleName = advRuleArgumentsList.get(schedulesIndex).getScheduleName();
					 boolean isScheduleException = advRuleArgumentsList.get(authGroupIndex).isException();
					 //iterate Over filterPolicy
					 for(int filterPolicyIndex : filterPolicyIndexList){
						 String filterPolicyName = advRuleArgumentsList.get(filterPolicyIndex).getFilterPolicyName();
						
						 if(isAuthGroupException){
							 if(ruleAction == Constants.ADV_RULE_ACTION_ALLOW){
								 scenario = scenarioSecond;
							 }else if(ruleAction == Constants.ADV_RULE_ACTION_BLOCK){
								 scenario = scenarioFirst;
							 } else if(ruleAction == Constants.ADV_RULE_ACTION_WARN){
								 scenario = scenarioFourth;
							 }
						 }else{
							 if(ruleAction == Constants.ADV_RULE_ACTION_ALLOW){
								 scenario = scenarioFirst;
							 }else if(ruleAction == Constants.ADV_RULE_ACTION_BLOCK) {
								 scenario = scenarioSecond;
							 } else if(ruleAction == Constants.ADV_RULE_ACTION_WARN) {
								 scenario = scenarioThird;
							 }
						 }
						 FilterPolicy objFilterPolicy = getFilterPolicyListFromName(objCWSPolicy.getFilterPolicies(), filterPolicyName);
						 AuthGroup objAuthGroup = getAuthGroupFromAuthGroupList(objCWSPolicy.getAuthGroups(), authGroupName);
						 
						 wsaPolicyList.add(createWSAPolicy(ruleName, ruleDescription, authGroupName, scheduleName, isScheduleException, filterPolicyName,
								 			scenario, wsaIdentityMap, wsaTimeDefinitionMap, reviewBuffer, objFilterPolicy, objAuthGroup));
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
	private WSAPolicy createWSAPolicy(String ruleName, String ruleDescription, String authGroupName, String scheduleName, boolean isScheduleException, 
						String filterPolicyName, String [] scenario, Map<String,WSAIdentity> wsaIdentityMap, Map<String,WSATimeDefinition> wsaTimeDefinitionMap,
						StringBuffer objReviewBuffer, FilterPolicy objFilterPolicy, AuthGroup objAuthGroup) throws IOException{
		
		 WSAPolicy objWSAPolicy = new WSAPolicy();
		 List<WSAMIMEType> mimeTypeList = new ArrayList<WSAMIMEType>();
		 List<WSAMIMEType> customMimeTypeList = new ArrayList<WSAMIMEType>();
		 String wsaPolicyName = (ruleName+Constants.POLICY_NAME_SEPRATOR+authGroupName+Constants.POLICY_NAME_SEPRATOR+scheduleName+Constants.POLICY_NAME_SEPRATOR+filterPolicyName).replace("/", "").replace(":", "").replace(" ", "");
		 System.out.println(wsaPolicyName);
		 objWSAPolicy.setName(wsaPolicyName);
		 objWSAPolicy.setDescription((ruleDescription==null?wsaPolicyName:ruleDescription)+".Description");
		 
		 List<WSAIdentity> wsaIdentityList  = new ArrayList<WSAIdentity>();
		 
		 // iterate over auth group=>users.....
		 for(User objUser : objAuthGroup.getUsers()){
			 wsaIdentityList.add(wsaIdentityMap.get(objUser.getName()));
		 }
		 // iterate over auth group==>ip Expressions.....
		 for(IpExpression objIpExpressions : objAuthGroup.getIpExpressions()){
			 wsaIdentityList.add(wsaIdentityMap.get(objIpExpressions.getExpression()));
		 }
		 
		 objWSAPolicy.setWsaIdentityList(wsaIdentityList);
		 
		 objWSAPolicy.setTimeDefinition(wsaTimeDefinitionMap.get(scheduleName));
		 
		 /** set url categories */
		 objWSAPolicy.setWsaCategoryList(getHttpURLCategory(objFilterPolicy.getHttpCategories(),objReviewBuffer,scenario[2],wsaPolicyName));
//		 Domains --- custom url categories
		 objWSAPolicy.setWsaCustomCategoryDomain(getDomainToCustomURLCategory(filterPolicyName,objFilterPolicy.getBlacklistDomains(),objFilterPolicy.getBlacklistIps(),scenario[3]));
//		 Exception --- custom url categories
		 objWSAPolicy.setWsaCustomCategoryException(getConvertedCustomCategoryExceptions(objFilterPolicy.getWhitelistDomains(), objFilterPolicy.getWhitelistIps(), scenario[3]));
//		 Applications
		 objWSAPolicy.setWsaApplicationList(null);
//		 User Agents
		 objWSAPolicy.setWsaUserAgentList(null);
//		 Custom User Agents
		 objWSAPolicy.setWsaCustomUserAgentList(null);
		 
		 /** set Content Types */
		 getMimeType(mimeTypeList, customMimeTypeList, objFilterPolicy.getContentTypes(), scenario[4]);
		 objWSAPolicy.setWsaMIMETypeList(mimeTypeList);
		 objWSAPolicy.setWsaCustomMIMETypeList(customMimeTypeList);
		 
		 /** set File Types */
		 objWSAPolicy.setWsaFileTypeList( getFileTypes(objFilterPolicy.getInboundExtensions(), objFilterPolicy.getOutboundExtensions(), objReviewBuffer));

//		 Exception
		return objWSAPolicy;
	}
	
	/**
	 * US011-Conversion of CWS Policy with Used Filters having Exceptions.
	 * @param whiteListDomainsList
	 * @param whiteListIpsList
	 */
	private WSACustomCategory getConvertedCustomCategoryExceptions(List<WhitelistDomain> whiteListDomainsList, List<WhitelistIp> whiteListIpsList,String action){
		
		WSACustomCategory objWSACustomCategory = new WSACustomCategory();
		List<String> sitesList = new ArrayList<String>();
		
		/** add Ip to list.. */
		for(WhitelistIp objWhitelistIp : whiteListIpsList){
			sitesList.add(objWhitelistIp.getAddress());
		}
		
		/** add domains to list.. */
		for(WhitelistDomain   objWhitelistDomain : whiteListDomainsList){
			/** replace www. before setting.. */
			sitesList.add(objWhitelistDomain.getAddress().replace("www.", ""));
		}
		
		/** set sites list to wsaCustomcategory */
		objWSACustomCategory.setSites(sitesList);
		/** set action to wsaCustomcategory */
		objWSACustomCategory.setAction(action);
		
		return objWSACustomCategory;
	}
	
	/**
	 * US009-Conversion of CWS Policy with Used Filters having File Types
	 * method converts inbound extension list into wsa equivalent file types.
	 * and adds files type into review buffer from outbound extension list.
	 * @param inboundExtensionList
	 * @param outboundExtensionList
	 * @param objReviewBuffer
	 * @return
	 */
	private List<String> getFileTypes(List<InboundExtension> inboundExtensionList, List<OutboundExtension> outboundExtensionList, StringBuffer objReviewBuffer){
		/** for converted inbound extensions. */
		List<String> fileTypeList = new ArrayList<String>();
		
		/** Iterate inboundExtensionList */
		for(InboundExtension objInboundExtension : inboundExtensionList){
			fileTypeList.add(objInboundExtension.getExtension());
		}
		
		if(objReviewBuffer != null){
			objReviewBuffer.append(LogUtil.getHeader("File Type"));
			/** Iterate OutboundExtesionList */
			for(OutboundExtension objOutboundExtension : outboundExtensionList){
				objReviewBuffer.append(Constants.NEW_LINE+objOutboundExtension.getExtension());
			}
		}
		
		return (fileTypeList.size() == 0) ? null : fileTypeList ;
	}
	
	/**
	 * US008-Conversion of CWS Policy with Used Filters having Content Types
	 * @param mimeTypeList
	 * @param customMimeTypeList
	 * @param contentTypeList
	 * @param contentTypeAction
	 */
	private void getMimeType(List<WSAMIMEType> mimeTypeList, List<WSAMIMEType> customMimeTypeList, List<ContentType> contentTypeList, String contentTypeAction){
		/** map contains CWS content type as key and WSA content type as value */
		Map<String, String> mimeTypeMap = mimeTypeDAOImpl.getMIMETypeMap();
		
		WSAMIMEType objWSAMimeType = null;
		String value = null;
		/** 
		 * iterate cws content type and set to wsa content type list
		 * if not available in hash then put it into  customMime type list else put in mime type list
		 **/
		for(ContentType objContentType : contentTypeList)
		{
			objWSAMimeType = new WSAMIMEType();
			objWSAMimeType.setAction(contentTypeAction);
			value = mimeTypeMap.get(objContentType.getContentType());
			if(value == null) {
				objWSAMimeType.setContentType(objContentType.getContentType());
				customMimeTypeList.add(objWSAMimeType);
			}else{
				objWSAMimeType.setContentType(value);
				mimeTypeList.add(objWSAMimeType);
			}
		}
		
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
	/**Conversion of CWS Policy with Used Filters having Domains
	 **/

	private WSACustomCategory getDomainToCustomURLCategory( String filterName,List<BlacklistDomain> blacklistDomainList,List<BlacklistIp> blacklistIpList , String action) throws IOException
	{
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
		return wsaCustomCategory;
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
	
	private Map<String,Boolean> getUsedUnusedFilterPolicy(CWSPolicy objCWSPolicy)
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
	
	private WSATimeDefinition getTimeDefinitionFromList(String timeDefinitionName,List<WSATimeDefinition> wsaTimeDefinitionList)
	{
		for(WSATimeDefinition objWSATimeDefinition : wsaTimeDefinitionList)
		{
			if(timeDefinitionName.equals(objWSATimeDefinition.getName()))
				return objWSATimeDefinition;
		}
		return null;
	}
	
	/**
	 * US003	– Conversion of CWS Authorization Groups
	 **/
	public Map<String,WSAIdentity> getWSAIdentity( List<AuthGroup> authGroupList,StringBuffer reviewBuffer) throws IOException {
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

	/**
	 * to check duplication of URL Categories
	 **/
	private WSACategory getWSACategory(String categorycode, List<WSACategory> wsaCategoryList) {
		for (WSACategory wsaCategory: wsaCategoryList) {	  
			if(wsaCategory == null || wsaCategory.getId() == null)
				continue;
			if(wsaCategory.getId().equals(categorycode)){
				return wsaCategory;
			}
		}
		return null;
	}

	/**
	 * US005	–  Conversion of CWS Policy with Used Filters having HTTP categories
	 **/
	public List<WSACategory> getHttpURLCategory(List<HttpCategory> httpCategoryList,StringBuffer reviewBuffer,String action,String policyName) throws IOException {
		List<WSACategory> wsaHttpCategoryList =  new ArrayList<WSACategory>();
		Map<String, URLCategory> urlCategoryMap 		= 	urlCategoryDAO.getURLCategoryMap();
		for (HttpCategory httpCategory : httpCategoryList) {
			String category 							= 	httpCategory.getName();
			URLCategory urlCategory						= 	urlCategoryMap.get(category);
			if (urlCategory != null) {
				String categorycode=urlCategory.getAbbreviation().trim();
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
				reviewBuffer.append( "!*********************Policy Name : "+policyName+"*************************!\r\n" );
				reviewBuffer.append( "Category Name :	"+category.trim()+"\r\n\r\n" );
			}
		} 
		WSACategory wsaCategory							= new WSACategory();
		wsaCategory.setId(Constants.CATAGORY_CHILD_ABUSE_CONTENT_CODE);
		wsaCategory.setAction(Constants.ACTION_BLOCK);
		wsaHttpCategoryList.add(wsaCategory);
		return wsaHttpCategoryList;
	}

	/**
	 * US006 – Conversion of CWS Policy with Used Filters having HTTPS categories
	 **/

	private List<WSACategory> getHttpsURLCategory(List<HttpsCategory> httpsCategoryList,StringBuffer reviewBuffer , String action) throws IOException {

		List<WSACategory> wsaHttpsCategoryList =  new ArrayList<WSACategory>();
		Map<String, URLCategory> urlCategoryMap 		= 	urlCategoryDAO.getURLCategoryMap();
		for (HttpsCategory httpsCategory : httpsCategoryList) {
			String category 							= 	httpsCategory.getName();
			URLCategory urlCategory						= 	urlCategoryMap.get(category);
			if (urlCategory != null) {
				String categorycode=urlCategory.getAbbreviation().trim();
				WSACategory wsaCategory 				= 	getWSACategory(categorycode,wsaHttpsCategoryList);
				if (wsaCategory == null) {
					wsaCategory							= 	new WSACategory();
					wsaCategory.setId(categorycode);
					wsaCategory.setAction(action);
					wsaHttpsCategoryList.add(wsaCategory);
				}
			}else{
				//TODO: Add the unmapped category in review file.
				reviewBuffer.append( "Category Name :	"+category.trim()+"\r\n\r\n" );

			}
		} 
		WSACategory wsaCategory							= 	new WSACategory();
		wsaCategory.setId(Constants.CATAGORY_CHILD_ABUSE_CONTENT_CODE);
		wsaCategory.setAction(Constants.ACTION_BLOCK);
		wsaHttpsCategoryList.add(wsaCategory);
		return wsaHttpsCategoryList;
	}

	/**
	 * US007 –  Conversion of CWS Policy with Used Filters having Domains
	 **/
	public WSACustomCategory getCustomURLCategoryDomain( String customCategoryName,List<BlacklistDomain> blacklistDomainList,List<BlacklistIp> blacklistIpList , String action) throws IOException{
		WSACustomCategory wsaCustomCategory	            =  new WSACustomCategory();
		List<String> sites							    =  new ArrayList<String>();
		for (BlacklistDomain blacklistDomain : blacklistDomainList) {
			sites.add(blacklistDomain.getAddress().replaceFirst("www.", ""));
		}
		for (BlacklistIp blacklistIp : blacklistIpList) {
			sites.add(blacklistIp.getAddress());
		}
		wsaCustomCategory.setName(customCategoryName);
		wsaCustomCategory.setAction(action);
		wsaCustomCategory.setSites(sites);
		return wsaCustomCategory;
	}

	/**
	 * To get all Used Unused HttpsFilter
	 **/

	public Map<String,Boolean> getUsedUnusedHttpsFilter(CWSPolicy objCWSPolicy)
	{
		Map<String,Boolean> usedUnusedHttpsFilter = new HashMap<String,Boolean>();

		List<HttpsFilter> httpsfilterList = objCWSPolicy.getHttpsFilters();
		// put all Https Filter policy into hash map and mark them as unused..
		for(int httpsFilterIndex=0; httpsFilterIndex < httpsfilterList.size(); httpsFilterIndex++){
			usedUnusedHttpsFilter.put(httpsfilterList.get(httpsFilterIndex).getName(), false);
		}
		// iterate over HttpsRule
		// occurrence of HttpsfilterName in HttpsRule will considered as used HTTPs Filter. so it will be mark as used in hashmap.
		List<HttpsRule> httpsRuleList = objCWSPolicy.getHttpsRules();
		for(int httpsRuleIndex = 0; httpsRuleIndex < httpsRuleList.size(); httpsRuleIndex++) {
			HttpsRuleFilter httpsRuleFilter = httpsRuleList.get(httpsRuleIndex).getHttpsRuleFilter();
			usedUnusedHttpsFilter.put(httpsRuleFilter.getHttpsFilterName(), true);
		}
		return usedUnusedHttpsFilter;
	}
	/**
	 * US013 – Conversion of CWS HTTPS Policy with Unused filters
	 **/
	public void logUnusedHttpsFilters(CWSPolicy objCWSPolicy, StringBuffer reviewBuffer) {
		String unusedHttpsFilters = "";
		Map<String,Boolean> usedUnusedHttpsFilter = getUsedUnusedHttpsFilter(objCWSPolicy);
		//TODO: Add the Unused Https filters in review file
		for(String filterName : usedUnusedHttpsFilter.keySet()) {
			if(!usedUnusedHttpsFilter.get(filterName)) {
				unusedHttpsFilters += Constants.NEW_LINE+filterName;
			}
		}
		if(unusedHttpsFilters.length() > 0) {
			reviewBuffer.append(Constants.NEW_LINE+LogUtil.getHeader("Unused Https Filter "));
			reviewBuffer.append(Constants.NEW_LINE + unusedHttpsFilters);
		}
	}


	/**
	 * US014 – Conversion of CWS HTTPS Policy with Used Filters having categories 
	 **/
	public List<WSACategory> getHttpsFilterURLCategory(List<Category> categoryList,StringBuffer reviewBuffer,String action,String policyName) throws IOException {
		List<WSACategory> wsaHttpCategoryList =  new ArrayList<WSACategory>();
		Map<String, URLCategory> urlCategoryMap 		= 	urlCategoryDAO.getURLCategoryMap();
		for (Category objcategory : categoryList) {
			String category 							= 	objcategory.getName();
			URLCategory urlCategory						= 	urlCategoryMap.get(category);
			if (urlCategory != null) {
				String categorycode=urlCategory.getAbbreviation().trim();
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
				reviewBuffer.append( "!*********************Policy Name : "+policyName+"*************************!\r\n" );
				reviewBuffer.append( "Category Name :	"+category.trim()+"\r\n\r\n" );
			}
		} 
		WSACategory wsaCategory							= new WSACategory();
		wsaCategory.setId(Constants.CATAGORY_CHILD_ABUSE_CONTENT_CODE);
		wsaCategory.setAction(Constants.ACTION_BLOCK);
		wsaHttpCategoryList.add(wsaCategory);
		return wsaHttpCategoryList;
	}

	/**
	 * US015 – Conversion of CWS HTTPS Policy with Used Filters having Domains
	 **/
	public WSACustomCategory getHttpsCustomURLCategoryDomain(String customCategoryName, List<Domain> domainList , List<IncludedNetwork> includedNetworkList  , String action)  {
		WSACustomCategory wsaCustomCategory	            =  new WSACustomCategory();
		List<String> sites							    =  new ArrayList<String>();
		wsaCustomCategory.setName(customCategoryName);
		wsaCustomCategory.setAction(action);
		for (Domain domain : domainList) {
			sites.add(domain.getUrl().replaceFirst("www.", ""));
		}
		for (IncludedNetwork includedNetwork : includedNetworkList) {
			sites.add(includedNetwork.getNetwork());
		}
		wsaCustomCategory.setSites(sites);
		return wsaCustomCategory;
	}


	/**
	 * US016 – Conversion of CWS HTTPS Policy with Used Filters having Exceptions
	 **/
	public WSACustomCategory getHttpsCustomURLCategoryException(String customCategoryName, List<Exception> exceptionList , List<ExcludedNetwork> excludedNetworkList  , String action)  {
		WSACustomCategory wsaCustomCategory	            =  new WSACustomCategory();
		List<String> sites							    =  new ArrayList<String>();
		for (Exception exception : exceptionList) {
			sites.add(exception.getException().replaceFirst("www.", ""));
		}
		for (ExcludedNetwork excludedNetwork : excludedNetworkList) {
			sites.add(excludedNetwork.getNetwork());
		}
		wsaCustomCategory.setName(customCategoryName);
		wsaCustomCategory.setAction(action);
		wsaCustomCategory.setSites(sites);
		return wsaCustomCategory;
	}

	/**
	 * US017 – Conversion of CWS HTTPS Policy with Used Filters having Applications
	 **/
	public void logUnusedApplications( String applicationsName, boolean  avcInspectionEnabled ,StringBuffer reviewBuffer) {
		String unusedHttpsFiltersApplication="";
		if(!avcInspectionEnabled) 
		{
			unusedHttpsFiltersApplication += Constants.NEW_LINE+applicationsName;
		}
		//TODO: Add the Unused Applications from Https filters in review file
		if(unusedHttpsFiltersApplication.length() > 0) {
			reviewBuffer.append(Constants.NEW_LINE+LogUtil.getHeader("Unused Https Filter "));
			reviewBuffer.append(Constants.NEW_LINE + unusedHttpsFiltersApplication);
		}

	}



	/**
	 *	US018 – Conversion of CWS HTTPS Policy using Certificates
	 **/
	public void getWSAHttpsCertificates(List<HttpsCertificate> httpsCertificateList)
	{

	}

	/**
	 *	US019 – Conversion of CWS HTTP Policy Rule to WSA Policy 
	 **/
	
	public List<WSAPolicy> getWSAHttpsPolicies(CWSPolicy objCWSPolicy,Map<String,WSAIdentity> wsaIdentityMap, Map<String,WSATimeDefinition> wsaTimeDefinitionMap,StringBuffer reviewBuffer) throws IOException
	{
		List<WSAPolicy> wsaHttpsPolicyList = new ArrayList<WSAPolicy>();
		String scenarioFirst[] 		= 	{"N.A"	,"monitor"	,"monitor"	,"block"   ,"N.A"};
		String scenarioSecond[] 	= 	{"N.A"	,"block"	,"block"	,"monitor" ,"N.A"};
		String scenarioThird[] 		= 	{"N.A"	,"warn"		,"warn"		,"block"   ,"N.A"};

		String scenario[] = null;




		StringBuffer reviewBufferLocal = new StringBuffer();
		for(HttpsRule objhttpsRule : objCWSPolicy.getHttpsRules())
		{
			String ruleName = objhttpsRule.getName();

			// long ruleAction = objhttpsRule.getAction();
			List<Integer> filterPolicyIndexList = new ArrayList<Integer>();
			List<Integer> scheduleIndexList = new ArrayList<Integer>();
			List<Integer> authGroupIndexList = new ArrayList<Integer>();

			List<HttpsRuleGroup> httpsRuleGroupList = objhttpsRule.getHttpsRuleGroups();
			for( int httpsRuleGroupIndex = 0 ; httpsRuleGroupIndex < httpsRuleGroupList.size(); httpsRuleGroupIndex++)
			{
				HttpsRuleGroup objhttpsRuleGroup = httpsRuleGroupList.get(httpsRuleGroupIndex);
				if(objhttpsRuleGroup.isIsGroupException())
				{

				}
			}

		}
		if(reviewBufferLocal.length() > 0)
		{
			reviewBuffer.append(Constants.NEW_LINE+"!******************************* Exception Filter Policy ***************************!");
			reviewBuffer.append(reviewBufferLocal);
		}
		return wsaHttpsPolicyList;
	}



	public WSAPolicy createWSAHttpsPolicy(String ruleName,String ruleDescription,String authGroupName,String scheduleName,boolean isScheduleException,String HttpsfilterName,String [] scenario,Map<String,WSAIdentity> wsaIdentityMap, Map<String,WSATimeDefinition> wsaTimeDefinitionMap,StringBuffer reviewBuffer,HttpsFilter objHttpsFilter,AuthGroup objAuthGroup) throws IOException
	{
		WSAPolicy objWSAPolicy = new WSAPolicy();
		String wsaPolicyName = (ruleName+Constants.POLICY_NAME_SEPRATOR+authGroupName+Constants.POLICY_NAME_SEPRATOR+scheduleName+Constants.POLICY_NAME_SEPRATOR+HttpsfilterName).replace("/", "").replace(":", "").replace(" ", "");
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
		objWSAPolicy.setWsaCategoryList(getHttpsFilterURLCategory(objHttpsFilter.getCategories(), reviewBuffer, scenario[2], wsaPolicyName));
		//		 Domains --- custom url categories
		//objWSAPolicy.setWsaCustomCategory(getHttpsCustomURLCategoryDomain(objHttpsFilter.getName(), objHttpsFilter.getDomains(), objHttpsFilter.getIncludedNetworks(), scenario[3]));
		//		 Applications
		
		//		 Exception
		return objWSAPolicy;
	}


	
	
	public static void main(String arg[]) throws JAXBException, IOException 
	{
		JAXBContext jaxbContext = JAXBContext.newInstance(Config.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        FileOutputStream objOutputStream = new FileOutputStream(new File("E:\\output.xml"));
        jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING,"ISO-8859-1");
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        objOutputStream.write("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\r\n<!DOCTYPE config SYSTEM \"config.dtd\">".getBytes());
        jaxbMarshaller.marshal(new Config(), objOutputStream);
	}
}
