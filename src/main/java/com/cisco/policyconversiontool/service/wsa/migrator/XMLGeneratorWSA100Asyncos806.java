package com.cisco.policyconversiontool.service.wsa.migrator;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.cisco.policyconversiontool.dto.wsa.asyncos806.Config;
import com.cisco.policyconversiontool.dto.wsa.asyncos806.ProxAclCustomCategories;
import com.cisco.policyconversiontool.dto.wsa.asyncos806.ProxAclCustomCategory;
import com.cisco.policyconversiontool.dto.wsa.asyncos806.ProxAclCustomCategoryRegex;
import com.cisco.policyconversiontool.dto.wsa.asyncos806.ProxAclCustomCategoryRegexList;
import com.cisco.policyconversiontool.dto.wsa.asyncos806.ProxAclCustomCategoryServer;
import com.cisco.policyconversiontool.dto.wsa.asyncos806.ProxAclCustomCategoryServers;
import com.cisco.policyconversiontool.dto.wsa.asyncos806.ProxAclGroup;
import com.cisco.policyconversiontool.dto.wsa.asyncos806.ProxAclGroupAuthGroup;
import com.cisco.policyconversiontool.dto.wsa.asyncos806.ProxAclGroupAuthGroups;
import com.cisco.policyconversiontool.dto.wsa.asyncos806.ProxAclGroupAuthGroupsOfRealm;
import com.cisco.policyconversiontool.dto.wsa.asyncos806.ProxAclGroupAuthRealmGroups;
import com.cisco.policyconversiontool.dto.wsa.asyncos806.ProxAclGroupAuthSurrogateByProto;
import com.cisco.policyconversiontool.dto.wsa.asyncos806.ProxAclGroupAuthSurrogateProtoSetting;
import com.cisco.policyconversiontool.dto.wsa.asyncos806.ProxAclGroupAvcAction;
import com.cisco.policyconversiontool.dto.wsa.asyncos806.ProxAclGroupAvcActions;
import com.cisco.policyconversiontool.dto.wsa.asyncos806.ProxAclGroupConnectPorts;
import com.cisco.policyconversiontool.dto.wsa.asyncos806.ProxAclGroupCustomUserAgents;
import com.cisco.policyconversiontool.dto.wsa.asyncos806.ProxAclGroupCustomcatAction;
import com.cisco.policyconversiontool.dto.wsa.asyncos806.ProxAclGroupCustomcatActions;
import com.cisco.policyconversiontool.dto.wsa.asyncos806.ProxAclGroupCustomcatRedirects;
import com.cisco.policyconversiontool.dto.wsa.asyncos806.ProxAclGroupFileExtensions;
import com.cisco.policyconversiontool.dto.wsa.asyncos806.ProxAclGroupFileTypes;
import com.cisco.policyconversiontool.dto.wsa.asyncos806.ProxAclGroupFirestoneAction;
import com.cisco.policyconversiontool.dto.wsa.asyncos806.ProxAclGroupFirestoneActions;
import com.cisco.policyconversiontool.dto.wsa.asyncos806.ProxAclGroupIdentities;
import com.cisco.policyconversiontool.dto.wsa.asyncos806.ProxAclGroupIdentity;
import com.cisco.policyconversiontool.dto.wsa.asyncos806.ProxAclGroupIp;
import com.cisco.policyconversiontool.dto.wsa.asyncos806.ProxAclGroupIps;
import com.cisco.policyconversiontool.dto.wsa.asyncos806.ProxAclGroupMalwareVerdicts;
import com.cisco.policyconversiontool.dto.wsa.asyncos806.ProxAclGroupMembershipProtocol;
import com.cisco.policyconversiontool.dto.wsa.asyncos806.ProxAclGroupMembershipProtocols;
import com.cisco.policyconversiontool.dto.wsa.asyncos806.ProxAclGroupProtocols;
import com.cisco.policyconversiontool.dto.wsa.asyncos806.ProxAclGroupUsername;
import com.cisco.policyconversiontool.dto.wsa.asyncos806.ProxAclGroupUsernames;
import com.cisco.policyconversiontool.dto.wsa.asyncos806.ProxAclGroupWebcatActions;
import com.cisco.policyconversiontool.dto.wsa.asyncos806.ProxAclHttpsGroups;
import com.cisco.policyconversiontool.dto.wsa.asyncos806.ProxAclIdentityGroups;
import com.cisco.policyconversiontool.dto.wsa.asyncos806.ProxAclPolicyGroups;
import com.cisco.policyconversiontool.dto.wsa.asyncos806.ProxAclRulesTimeDefinition;
import com.cisco.policyconversiontool.dto.wsa.asyncos806.ProxAclRulesTimeDefinitionRange;
import com.cisco.policyconversiontool.dto.wsa.asyncos806.ProxAclRulesTimeDefinitionRanges;
import com.cisco.policyconversiontool.dto.wsa.asyncos806.ProxAclRulesTimeDefinitions;
import com.cisco.policyconversiontool.dto.wsa.asyncos806.ProxConfigAuthRealms;
import com.cisco.policyconversiontool.dto.wsa.asyncos806.WgaConfig;
import com.cisco.policyconversiontool.dto.wsa.wsanormalized.WSAApplication;
import com.cisco.policyconversiontool.dto.wsa.wsanormalized.WSACategory;
import com.cisco.policyconversiontool.dto.wsa.wsanormalized.WSACustomCategory;
import com.cisco.policyconversiontool.dto.wsa.wsanormalized.WSAIdentity;
import com.cisco.policyconversiontool.dto.wsa.wsanormalized.WSAMigratedConfig;
import com.cisco.policyconversiontool.dto.wsa.wsanormalized.WSAPolicy;
import com.cisco.policyconversiontool.dto.wsa.wsanormalized.WSATimeDefinition;
import com.cisco.policyconversiontool.dto.wsa.wsanormalized.WSATimeRange;
import com.cisco.policyconversiontool.service.util.Constants;
import com.cisco.policyconversiontool.service.util.DTDEntityResolver;
import com.cisco.policyconversiontool.service.util.DTDProvider;





public class XMLGeneratorWSA100Asyncos806 implements ApplianceXMLGenerator{

//	asyncos806.wsas100v
	HashMap<String,String> objCustomCategoryHashMap;  
public OutputStream generateXML(WSAMigratedConfig wsaMigratedConfig,Object objWSAInitialConfig) throws Exception {
		
		Config objConfig = (Config)objWSAInitialConfig;

        String realmName = null;
        
        objCustomCategoryHashMap = new HashMap<String,String>();
        
        if(objConfig==null)
        {
        	throw new Exception("Configuration From Target File is NULL.");
        }
        
        WgaConfig objWgaConfig = objConfig.getWgaConfig();
        if(objWgaConfig==null)
        {
        	throw new Exception("Configuration From Target File is NULL.");
        }
        
		// policy List.....
		ArrayList<WSAPolicy> wsaPolicyList = (ArrayList<WSAPolicy>)wsaMigratedConfig.getWsaPolicyList();
		
		// generate random number for custom categories....
		long customCategoryCodeIndex = 0;
		long customCategoryCodeIndexBegining = 0;
		//prox_acl_rules_customcat_code_index
		if(objWgaConfig.getProxAclRulesCustomcatCodeIndex() == null)
		{
	        customCategoryCodeIndex = Long.parseLong(Long.parseLong((((long) (System.nanoTime() * (Math.random() * 1000)))+"").substring(0, 9))+"0");
		}else
		{
			customCategoryCodeIndex = Long.parseLong(objWgaConfig.getProxAclRulesCustomcatCodeIndex());
		}
		customCategoryCodeIndexBegining = customCategoryCodeIndex;
        
        ProxAclCustomCategories objProxAclCustomCategories = objWgaConfig.getProxAclCustomCategories();
		if(objProxAclCustomCategories == null)
		{
			objProxAclCustomCategories = new ProxAclCustomCategories();
			objWgaConfig.setProxAclCustomCategories(objProxAclCustomCategories);
		}
		 //prox_config_auth_realms
        ProxConfigAuthRealms objProxConfigAuthRealms =  objWgaConfig.getProxConfigAuthRealms();
        if(objProxConfigAuthRealms == null)
        {
        	throw new Exception("Realm Not found.");
        }
        realmName = objProxConfigAuthRealms.getProxConfigAuthRealm().get(0).getProxConfigAuthRealmName();
        
        // add time definitions
        addTimeDefinitions(wsaMigratedConfig,objWgaConfig);
        
        // add identities...
        addIdentites(objWgaConfig,wsaMigratedConfig,realmName);
        
		
		if(wsaPolicyList !=null)
		{
			// policy object...
			WSAPolicy objWSAPolicy = null;
			
			 // set default policy object
	        ProxAclPolicyGroups objProxAclPolicyGroups = null;
	        ProxAclGroup objDefaultProxAclGroup = null;
	        objProxAclPolicyGroups =  objWgaConfig.getProxAclPolicyGroups();
			if(objProxAclPolicyGroups.getProxAclGroup() ==null)
			{
				 throw new Exception("Prox Acl Group Object(Default Settings) Not found.");
			}
			
			ArrayList<ProxAclGroup> proxAclGroupPolicyList = (ArrayList<ProxAclGroup>)objProxAclPolicyGroups.getProxAclGroup();
			objDefaultProxAclGroup = proxAclGroupPolicyList.get(0);
			objDefaultProxAclGroup = getCloneForDefaultObject(objDefaultProxAclGroup);
			objDefaultProxAclGroup.setProxAclGroupFirestoneActions(new ProxAclGroupFirestoneActions());
			objDefaultProxAclGroup.setProxAclGroupAvcActions(new ProxAclGroupAvcActions());			
			
			ProxAclGroup objProxAclGroup = null;
			
			for(int wsaPolicyListIndex = 0; wsaPolicyListIndex < wsaPolicyList.size() ; wsaPolicyListIndex++)
			{
				objWSAPolicy = wsaPolicyList.get(wsaPolicyListIndex);
				objProxAclGroup = getCloneForDefaultObject(objDefaultProxAclGroup);
				proxAclGroupPolicyList.add(objProxAclGroup);
				objProxAclGroup.setProxAclGroupId(objWSAPolicy.getName());
				objProxAclGroup.setProxAclGroupDescription(objWSAPolicy.getName()+".description");
				//<prox_acl_group_enabled>1</prox_acl_group_enabled>
				objProxAclGroup.setProxAclGroupEnabled("1");
				// add custom categories....
				customCategoryCodeIndex = addCustomCategories(objProxAclGroup,customCategoryCodeIndex,objWSAPolicy,objProxAclCustomCategories);
				/***********************************************************************************************************
				// setting time_definition name to prox_acl_group_membership_time element in prox_Acl_policy ..
				 ***********************************************************************************************************/
				WSATimeDefinition objWSATimeDefinition = objWSAPolicy.getTimeDefinition();
				objProxAclGroup.setProxAclGroupMembershipTime(objWSATimeDefinition.getName());
				// copy all URL Categories into proxy_acl_group_firestone_actions
				addCategories(objProxAclGroup,objWSAPolicy);
				// copy all Application into  prox_acl_group_avc_actions
				addApplication(objProxAclGroup,objWSAPolicy);
				// setting identity...in Policy...prox_acl_identity_groups => prox_acl_group
//				ProxAclGroup objIdentityProxAclGroup = null;
				List<WSAIdentity> wsaIdentityList = objWSAPolicy.getWsaIdentityList();
				// in Policy ... setting prox_acl_group_identities ===> prox_acl_group_identity
				addIdentityDetailsInPolicy(objProxAclGroup,wsaIdentityList,realmName);
			}
		}
		
		// policy List.....
		ArrayList<WSAPolicy> wsaHttpsPolicyList = (ArrayList<WSAPolicy>)wsaMigratedConfig.getWsaHttpsPolicyList();
		// wsaHttpsPolicy.............
		if(wsaHttpsPolicyList!=null)
		{
			// policy object...
			WSAPolicy objWSAHttpsPolicy = null;
			ProxAclGroup objProxAclGroup = null;
			ProxAclHttpsGroups objProxAclHttpsGroups = objWgaConfig.getProxAclHttpsGroups();
			ArrayList<ProxAclGroup> proxAclGroupList = (ArrayList<ProxAclGroup>) objProxAclHttpsGroups.getProxAclGroup();
			for(int wsaHttpsPolicyListIndex = 0; wsaHttpsPolicyListIndex < wsaHttpsPolicyList.size() ; wsaHttpsPolicyListIndex++)
			{
				objWSAHttpsPolicy = wsaHttpsPolicyList.get(wsaHttpsPolicyListIndex);
				objProxAclGroup = new ProxAclGroup();
				proxAclGroupList.add(objProxAclGroup);
				objProxAclGroup.setProxAclGroupId(objWSAHttpsPolicy.getName());
				objProxAclGroup.setProxAclGroupDescription(objWSAHttpsPolicy.getName()+".description");
				//<prox_acl_group_enabled>1</prox_acl_group_enabled>
				objProxAclGroup.setProxAclGroupEnabled("1");
				// add custom categories....
				customCategoryCodeIndex = addCustomCategories(objProxAclGroup,customCategoryCodeIndex,objWSAHttpsPolicy,objProxAclCustomCategories);
				// setting time_definition name to prox_acl_group_membership_time element in prox_Acl_policy ..
				WSATimeDefinition objWSATimeDefinition = objWSAHttpsPolicy.getTimeDefinition();
				objProxAclGroup.setProxAclGroupMembershipTime(objWSATimeDefinition.getName());
				// copy all URL Categories into proxy_acl_group_firestone_actions
				addCategories(objProxAclGroup, objWSAHttpsPolicy);
				// setting identity......prox_acl_identity_groups => prox_acl_group
//				ProxAclGroup objIdentityProxAclGroup = null;
				List<WSAIdentity> wsaIdentityList = objWSAHttpsPolicy.getWsaIdentityList();
				// in Policy ... setting prox_acl_group_identities ===> prox_acl_group_identity
				addIdentityDetailsInPolicy(objProxAclGroup,wsaIdentityList,realmName);
			}
		
		}
		
		if(customCategoryCodeIndex != customCategoryCodeIndexBegining)
		{
			setExcludeForAllCustomCategoryInDefaultPolicy(objWgaConfig);
			objWgaConfig.setProxAclRulesCustomcatCodeIndex(customCategoryCodeIndex+"");
		}
		
		
		// marshal the object.
		JAXBContext jaxbContext = JAXBContext.newInstance(Config.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        ByteArrayOutputStream objOutputStream = new ByteArrayOutputStream();
        jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING,"ISO-8859-1");
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        objOutputStream.write("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\r\n<!DOCTYPE config SYSTEM \"config.dtd\">".getBytes());
        jaxbMarshaller.marshal(objConfig, objOutputStream);
		return (OutputStream)objOutputStream;
	}
	private void addIdentityDetailsInPolicy(ProxAclGroup objProxAclGroup,List<WSAIdentity> wsaIdentityList,String realmName) {

		for(WSAIdentity objWSAIdentity : wsaIdentityList)
			{
			String clientType = objWSAIdentity.getType();
			ProxAclGroupIdentities objProxAclGroupIdentitiesPolicy = objProxAclGroup.getProxAclGroupIdentities();
			if(objProxAclGroupIdentitiesPolicy==null)
			{
				objProxAclGroupIdentitiesPolicy = new ProxAclGroupIdentities();
				objProxAclGroup.setProxAclGroupIdentities(objProxAclGroupIdentitiesPolicy);
			}
			
			ArrayList<ProxAclGroupIdentity> proxAclGroupIdentityPolicyList =  (ArrayList<ProxAclGroupIdentity>) objProxAclGroupIdentitiesPolicy.getProxAclGroupIdentity();
			ProxAclGroupIdentity objProxAclGroupIdentityForPolicy = null;
			 
			objProxAclGroupIdentityForPolicy = new ProxAclGroupIdentity();
			proxAclGroupIdentityPolicyList.add(objProxAclGroupIdentityForPolicy);
			 
			ProxAclGroupAuthGroups objProxAclGroupAuthGroups =  objProxAclGroupIdentityForPolicy.getProxAclGroupAuthGroups();
			if(objProxAclGroupAuthGroups==null)
			{
				objProxAclGroupAuthGroups = new ProxAclGroupAuthGroups();
				objProxAclGroupIdentityForPolicy.setProxAclGroupAuthGroups(objProxAclGroupAuthGroups);
			}
			ArrayList<ProxAclGroupAuthGroupsOfRealm>  proxAclGroupAuthGroupsOfRealmList = (ArrayList<ProxAclGroupAuthGroupsOfRealm>)objProxAclGroupAuthGroups.getProxAclGroupAuthGroupsOfRealm();
			ProxAclGroupAuthGroupsOfRealm objProxAclGroupAuthGroupsOfRealm = new ProxAclGroupAuthGroupsOfRealm();
			
			
			proxAclGroupAuthGroupsOfRealmList.add(objProxAclGroupAuthGroupsOfRealm);
			
			if(clientType.equals(Constants.CLIENTTYPE_USER))
			{
				ProxAclGroupUsernames objProxAclGroupUsernames = objProxAclGroupIdentityForPolicy.getProxAclGroupUsernames();
				if(objProxAclGroupUsernames==null)
				{
					objProxAclGroupUsernames = new ProxAclGroupUsernames();
					objProxAclGroupIdentityForPolicy.setProxAclGroupUsernames(objProxAclGroupUsernames);
				}
				ArrayList<ProxAclGroupUsername> proxAclGroupUsernameList =  (ArrayList<ProxAclGroupUsername>)objProxAclGroupUsernames.getProxAclGroupUsername();
				ProxAclGroupUsername objProxAclGroupUsername = new ProxAclGroupUsername();
				objProxAclGroupUsername.setvalue(objWSAIdentity.getName());
				objProxAclGroupIdentityForPolicy.setProxAclGroupIdentityName(realmName);
				proxAclGroupUsernameList.add(objProxAclGroupUsername);
				objProxAclGroupAuthGroupsOfRealm.setProxAclGroupAuthRealmName(realmName);
				// setting prox_acl_group_auth_sequence
			}else if(clientType.equals(Constants.CLIENTTYPE_GROUP))
			{
				//prox_acl_group_auth_realm_groups
				ProxAclGroupAuthRealmGroups objProxAclGroupAuthRealmGroups = objProxAclGroupAuthGroupsOfRealm.getProxAclGroupAuthRealmGroups();
				if(objProxAclGroupAuthRealmGroups==null)
				{
					objProxAclGroupAuthRealmGroups = new ProxAclGroupAuthRealmGroups();
					objProxAclGroupAuthGroupsOfRealm.setProxAclGroupAuthRealmGroups(objProxAclGroupAuthRealmGroups);
				}
				ArrayList<ProxAclGroupAuthGroup> objProxAclGroupAuthGroupList = (ArrayList<ProxAclGroupAuthGroup>) objProxAclGroupAuthRealmGroups.getProxAclGroupAuthGroup();
				ProxAclGroupAuthGroup objProxAclGroupAuthGroup = new ProxAclGroupAuthGroup();
				objProxAclGroupAuthGroupList.add(objProxAclGroupAuthGroup);
				objProxAclGroupAuthGroup.setvalue(objWSAIdentity.getName());
				objProxAclGroupIdentityForPolicy.setProxAclGroupIdentityName(realmName);
				objProxAclGroupAuthGroupsOfRealm.setProxAclGroupAuthRealmName(realmName);
			}else if(clientType.equals(Constants.CLIENTTYPE_DOMAIN))
			{
				objProxAclGroupIdentityForPolicy.setProxAclGroupIdentityName(realmName);
				objProxAclGroupAuthGroupsOfRealm.setProxAclGroupAuthRealmName(realmName);
	
			}else if(clientType.equals(Constants.CLIENTTYPE_COMPUTER))
			{
				objProxAclGroupIdentityForPolicy.setProxAclGroupIdentityName(objWSAIdentity.getName());
				objProxAclGroupIdentityForPolicy.setProxAclGroupAuthGroups(new ProxAclGroupAuthGroups());
				objProxAclGroupIdentityForPolicy.setProxAclGroupUsernames(new ProxAclGroupUsernames());
				objProxAclGroupIdentityForPolicy.setProxAclGroupAuthRealm(null);
				objProxAclGroupIdentityForPolicy.setProxAclGroupForGuestsOnly("0");
			}
			else if(clientType.equals(Constants.CLIENTTYPE_NETWORK))
			{
//				 <prox_acl_group_auth_groups>
//              </prox_acl_group_auth_groups>
//              <prox_acl_group_usernames>
//              </prox_acl_group_usernames>
//              <prox_acl_group_auth_realm></prox_acl_group_auth_realm>
//              <prox_acl_group_for_guests_only>0</prox_acl_group_for_guests_only>
//              <prox_acl_group_order>0</prox_acl_group_order>
				objProxAclGroupIdentityForPolicy.setProxAclGroupIdentityName(objWSAIdentity.getName());
				objProxAclGroupIdentityForPolicy.setProxAclGroupAuthGroups(new ProxAclGroupAuthGroups());
				objProxAclGroupIdentityForPolicy.setProxAclGroupUsernames(new ProxAclGroupUsernames());
				objProxAclGroupIdentityForPolicy.setProxAclGroupAuthRealm(null);
				objProxAclGroupIdentityForPolicy.setProxAclGroupForGuestsOnly("0");
			}
		}
			
	}
	private void addApplication(ProxAclGroup objProxAclGroup,WSAPolicy objWSAPolicy) {
		
		ProxAclGroupAvcActions objProxAclGroupAvcActions = objProxAclGroup.getProxAclGroupAvcActions();
		ArrayList<ProxAclGroupAvcAction> objProxAclGroupAvcActionList = (ArrayList<ProxAclGroupAvcAction>)objProxAclGroupAvcActions.getProxAclGroupAvcAction();
		ArrayList<WSAApplication> wsaApplicationList = (ArrayList<WSAApplication>) objWSAPolicy.getWsaApplicationList();
		if(wsaApplicationList !=null)
		{
			// loop through wsaCategories.....
			for(int wsaApplicationIndex=0; wsaApplicationIndex < wsaApplicationList.size(); wsaApplicationIndex++)
			{
				WSAApplication objWSAApplication = wsaApplicationList.get(wsaApplicationIndex);
				ProxAclGroupAvcAction objProxAclGroupAvcAction = new ProxAclGroupAvcAction();
				objProxAclGroupAvcAction.setProxAclGroupAvcId(objWSAApplication.getId());
				objProxAclGroupAvcAction.setProxAclGroupAvcPolicy(objWSAApplication.getAction());
				// add action obj to action list.....
				objProxAclGroupAvcActionList.add(objProxAclGroupAvcAction);
			}
		}
	}
	private void addIdentites(WgaConfig objWgaConfig,WSAMigratedConfig wsaMigratedConfig,String realmName) {
		// TODO Auto-generated method stub
		int countUserNetworkIdentitiesInPolicy = 0;
		ProxAclIdentityGroups objProxAclIdentityGroups = objWgaConfig.getProxAclIdentityGroups();
		if(objProxAclIdentityGroups==null)
		{
			objProxAclIdentityGroups = new ProxAclIdentityGroups();
			objWgaConfig.setProxAclIdentityGroups(objProxAclIdentityGroups);
		}
		ArrayList<ProxAclGroup> proxAclGroupList = (ArrayList<ProxAclGroup>) objProxAclIdentityGroups.getProxAclGroup();
		
		ArrayList<WSAIdentity> wsaIdentityList = (ArrayList<WSAIdentity>) wsaMigratedConfig.getWsaIdentityList();
		ProxAclGroup objProxAclGroup = null;
		String clientType = null;
		String clientName = null;
		for(int wsaIdentityIndex = 0; wsaIdentityIndex < wsaIdentityList.size(); wsaIdentityIndex++)
		{
			clientType = wsaIdentityList.get(wsaIdentityIndex).getType();
			clientName = wsaIdentityList.get(wsaIdentityIndex).getName();
			
			if(clientType.equals(Constants.CLIENTTYPE_USER))
			{
				countUserNetworkIdentitiesInPolicy++;
			}else if(clientType.equals(Constants.CLIENTTYPE_GROUP))
			{
				countUserNetworkIdentitiesInPolicy++;
			}else if(clientType.equals(Constants.CLIENTTYPE_DOMAIN))
			{
				countUserNetworkIdentitiesInPolicy++;
			}else if(clientType.equals(Constants.CLIENTTYPE_COMPUTER))
			{
				objProxAclGroup = setIdentityValues(clientName);
				// if type is ip set identity into prox_acl_identity_groups element.
				proxAclGroupList.add(objProxAclGroup);
				// setting prox_Acl_Group_Ips
				objProxAclGroup.setProxAclGroupIps(setProxAclGroupIPs(null,clientName));
			}
			else if(clientType.equals(Constants.CLIENTTYPE_NETWORK))
			{
				objProxAclGroup = setIdentityValues(clientName);
				// if type is ip range(network) set identity into prox_acl_identity_groups element.
				proxAclGroupList.add(objProxAclGroup);
				// setting prox_Acl_Group_Ips
				objProxAclGroup.setProxAclGroupIps(setProxAclGroupIPs(null,clientName));
			}
		}
		// add realm identity....
		if(countUserNetworkIdentitiesInPolicy > 0)
		{
			objProxAclGroup = setIdentityValues(realmName);
			objProxAclGroup.setProxAclGroupAuthSequence(realmName);
			proxAclGroupList.add(objProxAclGroup);
		}
	}
	private void addCategories(ProxAclGroup objProxAclGroup,WSAPolicy objWSAPolicy)
	{
		ProxAclGroupFirestoneActions objProxAclGroupFirestoneActions = objProxAclGroup.getProxAclGroupFirestoneActions();
		if(objProxAclGroupFirestoneActions==null)
		{
			objProxAclGroupFirestoneActions = new ProxAclGroupFirestoneActions();
			objProxAclGroup.setProxAclGroupFirestoneActions(objProxAclGroupFirestoneActions);
		}
		ArrayList<ProxAclGroupFirestoneAction> objProxAclGroupFirestoneActionList = (ArrayList<ProxAclGroupFirestoneAction>)objProxAclGroupFirestoneActions.getProxAclGroupFirestoneAction();
		ArrayList<WSACategory> wsaCategoryList = (ArrayList<WSACategory>) objWSAPolicy.getWsaCategoryList();
		if(wsaCategoryList !=null)
		{
			// loop through wsaCategories.....
			for(int wsaCategoryIndex=0; wsaCategoryIndex < wsaCategoryList.size(); wsaCategoryIndex++)
			{
				WSACategory objWSACategory = wsaCategoryList.get(wsaCategoryIndex);
				ProxAclGroupFirestoneAction objProxAclGroupFirestoneAction = new ProxAclGroupFirestoneAction();
				objProxAclGroupFirestoneAction.setCategoryId(objWSACategory.getId());
				objProxAclGroupFirestoneAction.setCategoryAction(objWSACategory.getAction());
				// add action obj to action list.....
				objProxAclGroupFirestoneActionList.add(objProxAclGroupFirestoneAction);
			}
		}
	}
	private void setExcludeForAllCustomCategoryInDefaultPolicy(WgaConfig objWgaConfig)
	{
		//prox_acl_custom_categories
		ProxAclCustomCategories objProxAclCustomCategories =  objWgaConfig.getProxAclCustomCategories();
		//prox_acl_custom_category
		ArrayList<ProxAclCustomCategory> proxAclCustomCategoryList = (ArrayList<ProxAclCustomCategory>) objProxAclCustomCategories.getProxAclCustomCategory();
		// get default policy <prox_acl_policy_groups>
		ProxAclPolicyGroups objProxAclPolicyGroups = objWgaConfig.getProxAclPolicyGroups();
		ArrayList<ProxAclGroup> proxAclPolicyGroupList = (ArrayList<ProxAclGroup>) objProxAclPolicyGroups.getProxAclGroup();
		if(proxAclPolicyGroupList.size()==0)
			return ;
		ProxAclGroup objProxAclGroup = proxAclPolicyGroupList.get(0);
		ProxAclGroupCustomcatActions objProxAclGroupCustomcatActions = objProxAclGroup.getProxAclGroupCustomcatActions();
		if(objProxAclGroupCustomcatActions==null)
		{
			objProxAclGroupCustomcatActions = new ProxAclGroupCustomcatActions();
			objProxAclGroup.setProxAclGroupCustomcatActions(objProxAclGroupCustomcatActions);
		}
		ArrayList<ProxAclGroupCustomcatAction> proxAclGroupCustomcatActionList = (ArrayList<ProxAclGroupCustomcatAction>)  objProxAclGroupCustomcatActions.getProxAclGroupCustomcatAction();
		for(int customCategoryIndex = 0; customCategoryIndex < proxAclCustomCategoryList.size(); customCategoryIndex++)
		{
			ProxAclCustomCategory objProxAclCustomCategory = proxAclCustomCategoryList.get(customCategoryIndex);
			ProxAclGroupCustomcatAction objProxAclGroupCustomcatAction = new ProxAclGroupCustomcatAction();
			objProxAclGroupCustomcatAction.setCategoryId(objProxAclCustomCategory.getProxAclCustomCategoryCode());
			objProxAclGroupCustomcatAction.setCategoryAction("exclude");
			proxAclGroupCustomcatActionList.add(objProxAclGroupCustomcatAction);
		}
	}
	private ProxAclGroup setIdentityValues(String identityName)
	{
		ProxAclGroup objIdentityProxAclGroup = new ProxAclGroup();
		objIdentityProxAclGroup.setProxAclGroupId(identityName);
		objIdentityProxAclGroup.setProxAclGroupDescription(identityName+".Description");
		objIdentityProxAclGroup.setProxAclGroupEnabled("1");
		objIdentityProxAclGroup.setProxAclGroupSplashExempt("Disabled");
		
		//<prox_acl_group_user_type>both</prox_acl_group_user_type>
		objIdentityProxAclGroup.setProxAclGroupUserType("both");
//		setting <prox_acl_group_membership_protocols>
		ProxAclGroupMembershipProtocols objProxAclGroupMembershipProtocols = new ProxAclGroupMembershipProtocols();
		objIdentityProxAclGroup.setProxAclGroupMembershipProtocols(objProxAclGroupMembershipProtocols);
		
		ArrayList<ProxAclGroupMembershipProtocol> proxAclGroupMembershipProtocolsList = (ArrayList<ProxAclGroupMembershipProtocol>) objProxAclGroupMembershipProtocols.getProxAclGroupMembershipProtocol();
		ProxAclGroupMembershipProtocol objProxAclGroupMembershipProtocol ;
		objProxAclGroupMembershipProtocol = new ProxAclGroupMembershipProtocol();
		objProxAclGroupMembershipProtocol.setvalue("http");
		proxAclGroupMembershipProtocolsList.add(objProxAclGroupMembershipProtocol);
		
		objProxAclGroupMembershipProtocol = new ProxAclGroupMembershipProtocol();
		objProxAclGroupMembershipProtocol.setvalue("https");
		proxAclGroupMembershipProtocolsList.add(objProxAclGroupMembershipProtocol);
		
		objProxAclGroupMembershipProtocol = new ProxAclGroupMembershipProtocol();
		objProxAclGroupMembershipProtocol.setvalue("ftp");
		proxAclGroupMembershipProtocolsList.add(objProxAclGroupMembershipProtocol);
		
		//setting 	<prox_acl_group_sso_scheme>sso_none</prox_acl_group_sso_scheme>
		objIdentityProxAclGroup.setProxAclGroupSsoScheme("sso_none");
		//<prox_acl_group_prompt_on_sso_failure>1</prox_acl_group_prompt_on_sso_failure>
		objIdentityProxAclGroup.setProxAclGroupPromptOnSsoFailure("1");
		//<prox_acl_group_use_forward_surrogates>0</prox_acl_group_use_forward_surrogates>
		objIdentityProxAclGroup.setProxAclGroupUseForwardSurrogates("0");
		
	//	<prox_acl_group_auth_surrogate_by_proto>
		
		ProxAclGroupAuthSurrogateByProto objProxAclGroupAuthSurrogateByProto = new ProxAclGroupAuthSurrogateByProto();
		objIdentityProxAclGroup.setProxAclGroupAuthSurrogateByProto(objProxAclGroupAuthSurrogateByProto);
		
		ArrayList<ProxAclGroupAuthSurrogateProtoSetting> proxAclGroupAuthSurrogateProtoSettingList = (ArrayList<ProxAclGroupAuthSurrogateProtoSetting>)objProxAclGroupAuthSurrogateByProto.getProxAclGroupAuthSurrogateProtoSetting();
		ProxAclGroupAuthSurrogateProtoSetting objProxAclGroupAuthSurrogateProtoSetting ;
		
		objProxAclGroupAuthSurrogateProtoSetting = new ProxAclGroupAuthSurrogateProtoSetting();
		objProxAclGroupAuthSurrogateProtoSetting.setAuthProtocol("ftp");
		objProxAclGroupAuthSurrogateProtoSetting.setSurrogate("ip");
		proxAclGroupAuthSurrogateProtoSettingList.add(objProxAclGroupAuthSurrogateProtoSetting);
		
		objProxAclGroupAuthSurrogateProtoSetting = new ProxAclGroupAuthSurrogateProtoSetting();
		objProxAclGroupAuthSurrogateProtoSetting.setAuthProtocol("http");
		objProxAclGroupAuthSurrogateProtoSetting.setSurrogate("Disabled");
		proxAclGroupAuthSurrogateProtoSettingList.add(objProxAclGroupAuthSurrogateProtoSetting);
		
		objProxAclGroupAuthSurrogateProtoSetting = new ProxAclGroupAuthSurrogateProtoSetting();
		objProxAclGroupAuthSurrogateProtoSetting.setAuthProtocol("https");
		objProxAclGroupAuthSurrogateProtoSetting.setSurrogate("Disabled");
		proxAclGroupAuthSurrogateProtoSettingList.add(objProxAclGroupAuthSurrogateProtoSetting);
		
		//prox_acl_group_connect_ports
		ProxAclGroupConnectPorts temp = new ProxAclGroupConnectPorts();
		temp.setInherited("");
		objIdentityProxAclGroup.setProxAclGroupConnectPorts(temp);
		
		//prox_acl_group_customcat_actions
		ProxAclGroupConnectPorts objProxAclGroupConnectPorts = new ProxAclGroupConnectPorts();
		objProxAclGroupConnectPorts.setInherited("");
		objIdentityProxAclGroup.setProxAclGroupConnectPorts(objProxAclGroupConnectPorts);
		
		//prox_acl_group_customcat_redirects
		ProxAclGroupCustomcatRedirects objProxAclGroupCustomcatRedirects = new ProxAclGroupCustomcatRedirects();
		objProxAclGroupCustomcatRedirects.setInherited("");
		objIdentityProxAclGroup.setProxAclGroupCustomcatRedirects(objProxAclGroupCustomcatRedirects);
		//prox_acl_group_webcat_actions
		ProxAclGroupWebcatActions objProxAclGroupWebcatActions = new ProxAclGroupWebcatActions();
		objProxAclGroupWebcatActions.setInherited("");
		objIdentityProxAclGroup.setProxAclGroupWebcatActions(objProxAclGroupWebcatActions);
		//prox_acl_group_firestone_actions
		ProxAclGroupFirestoneActions objProxAclGroupFirestoneActions  = new ProxAclGroupFirestoneActions();
		objProxAclGroupFirestoneActions.setInherited("");
		objIdentityProxAclGroup.setProxAclGroupFirestoneActions(objProxAclGroupFirestoneActions);
		//prox_acl_group_protocols
		ProxAclGroupProtocols objProxAclGroupProtocols = new ProxAclGroupProtocols();
		objProxAclGroupProtocols.setInherited("");
		objIdentityProxAclGroup.setProxAclGroupProtocols(objProxAclGroupProtocols);
		//prox_acl_group_file_types
		ProxAclGroupFileTypes objProxAclGroupFileTypes = new ProxAclGroupFileTypes();
		objProxAclGroupFileTypes.setInherited("");
		objIdentityProxAclGroup.setProxAclGroupFileTypes(objProxAclGroupFileTypes);
		//prox_acl_group_file_extensions
		ProxAclGroupFileExtensions objProxAclGroupFileExtensions = new ProxAclGroupFileExtensions();
		objProxAclGroupFileExtensions.setInherited("");
		objIdentityProxAclGroup.setProxAclGroupFileExtensions(objProxAclGroupFileExtensions);
		//prox_acl_group_malware_verdicts
		ProxAclGroupMalwareVerdicts objProxAclGroupMalwareVerdicts = new ProxAclGroupMalwareVerdicts();
		objProxAclGroupMalwareVerdicts.setInherited("");
		objIdentityProxAclGroup.setProxAclGroupMalwareVerdicts(objProxAclGroupMalwareVerdicts);
		//prox_acl_group_custom_user_agents
		ProxAclGroupCustomUserAgents objProxAclGroupCustomUserAgents = new ProxAclGroupCustomUserAgents();
		objProxAclGroupCustomUserAgents.setInherited("");
		objIdentityProxAclGroup.setProxAclGroupCustomUserAgents(objProxAclGroupCustomUserAgents);
		
		return objIdentityProxAclGroup;
	}
	private void addTimeDefinitions(WSAMigratedConfig wsaMigratedConfig,WgaConfig objWgaConfig)
	{
		// setting time definitions.
					ArrayList<WSATimeDefinition> objWSATimeDefinitionList = (ArrayList<WSATimeDefinition>) wsaMigratedConfig.getWsaTimeDefinitionList();
					// get prox_acl_rules_time_definitions element from wsaconfig..
					
					ProxAclRulesTimeDefinitions objProxAclRulesTimeDefinitions = objWgaConfig.getProxAclRulesTimeDefinitions();
					if(objProxAclRulesTimeDefinitions==null)
					{
						objProxAclRulesTimeDefinitions = new ProxAclRulesTimeDefinitions();
						objWgaConfig.setProxAclRulesTimeDefinitions(objProxAclRulesTimeDefinitions);
					}
					// get prox_acl_rules_time_definition list from prox_acl_rules_time_definitions
					ArrayList<ProxAclRulesTimeDefinition> proxAclRulesTimeDefinitionList = (ArrayList<ProxAclRulesTimeDefinition>)objProxAclRulesTimeDefinitions.getProxAclRulesTimeDefinition();
					
					for(int wsaTimeDefinitionIndex = 0; wsaTimeDefinitionIndex < objWSATimeDefinitionList.size(); wsaTimeDefinitionIndex++)
					{
						
						WSATimeDefinition objWSATimeDefinition = objWSATimeDefinitionList.get(wsaTimeDefinitionIndex);
						
						ProxAclRulesTimeDefinition objProxAclRulesTimeDefinition = new ProxAclRulesTimeDefinition();
						proxAclRulesTimeDefinitionList.add(objProxAclRulesTimeDefinition);
						
						objProxAclRulesTimeDefinition.setProxAclRulesTimeDefinitionName(objWSATimeDefinition.getName());
//						objProxAclGroup.setProxAclGroupMembershipTime(objWSATimeDefinition.getName());
						objProxAclRulesTimeDefinition.setProxAclRulesTimeDefinitionTimezone("Etc/GMT");
						objProxAclRulesTimeDefinition.setProxAclRulesTimeDefinitionUseLocal("1");
						ProxAclRulesTimeDefinitionRanges  objProxAclRulesTimeDefinitionRanges = new ProxAclRulesTimeDefinitionRanges();
						objProxAclRulesTimeDefinition.setProxAclRulesTimeDefinitionRanges(objProxAclRulesTimeDefinitionRanges);
						ArrayList<ProxAclRulesTimeDefinitionRange> proxAclRulesTimeDefinitionRangeList = (ArrayList<ProxAclRulesTimeDefinitionRange>) objProxAclRulesTimeDefinitionRanges.getProxAclRulesTimeDefinitionRange();
						
						
						ArrayList<WSATimeRange> wsaTimeRangeList = (ArrayList<WSATimeRange>) objWSATimeDefinition.getTimeRangeList();
						// loop through WSA TimeRange
						if(wsaTimeRangeList != null)
						{
							WSATimeRange objWSATimeRange = null;
							
							ProxAclRulesTimeDefinitionRange objProxAclRulesTimeDefinitionRange= null;
							
							for(int wsaTimeRangeListIndex=0; wsaTimeRangeListIndex < wsaTimeRangeList.size() ; wsaTimeRangeListIndex++)
							{
								
								objWSATimeRange = wsaTimeRangeList.get(wsaTimeRangeListIndex);
								// create new object of prox_acl_rules_time_definition_range
								objProxAclRulesTimeDefinitionRange = new ProxAclRulesTimeDefinitionRange();
								objProxAclRulesTimeDefinitionRange.setProxAclRulesTimeDefinitionStartTime(objWSATimeRange.getStartTime());
								objProxAclRulesTimeDefinitionRange.setProxAclRulesTimeDefinitionEndTime(objWSATimeRange.getEndTime());
								// setting valid days.....
								ArrayList<String> validDaysList = (ArrayList<String>) objWSATimeRange.getValidDays();
								String validDaysString = "";
								
								if(validDaysList != null)
								{
									String day= null;
									for(int validDaysIndex=0; validDaysIndex < validDaysList.size(); validDaysIndex++)
									{
										day = validDaysList.get(validDaysIndex);
										if(day.equalsIgnoreCase(Constants.SATURDAY)) {
											validDaysString += "A";
										}else if(day.equalsIgnoreCase(Constants.FRIDAY)) {
											validDaysString += "F";
										}else if(day.equalsIgnoreCase(Constants.THURSDAY)) {
											validDaysString += "H";
										}else if(day.equalsIgnoreCase(Constants.MONDAY)) {
											validDaysString += "M";
										}else  if(day.equalsIgnoreCase(Constants.SUNDAY)) {
											validDaysString += "S";
										}else  if(day.equalsIgnoreCase(Constants.TUESDAY)) {
											validDaysString += "T";
										}else  if(day.equalsIgnoreCase(Constants.WEDNESDAY)) {
											validDaysString += "W";
										}
									}
									// sorting of validDaysString.......
									char[] chars = validDaysString.toCharArray();
									Arrays.sort(chars);
									validDaysString = new String(chars);
								}
								
								objProxAclRulesTimeDefinitionRange.setProxAclRulesTimeDefinitionValidDays(validDaysString);
								proxAclRulesTimeDefinitionRangeList.add(objProxAclRulesTimeDefinitionRange);
							}
						}
					}
					// end time_definition...
	}
	private ProxAclGroup getCloneForDefaultObject(ProxAclGroup objDefaultProxAclGroup) throws IOException, ClassNotFoundException{
		
		 ObjectOutputStream oos = null;
	     ObjectInputStream ois = null;
	      
        // deep copy
        ByteArrayOutputStream bos = new ByteArrayOutputStream(); 
        oos = new ObjectOutputStream(bos); 
        // serialize and pass the object
        oos.writeObject(objDefaultProxAclGroup);   
        oos.flush();               
        ByteArrayInputStream bin =       new ByteArrayInputStream(bos.toByteArray()); 
        ois = new ObjectInputStream(bin);                  
        // return the new object
        objDefaultProxAclGroup = (ProxAclGroup) ois.readObject(); 
  
        oos.close();
        ois.close();
	       
		return objDefaultProxAclGroup;
	}
	private long addCustomCategories(ProxAclGroup objProxAclGroup,long customCategoryCodeIndex,WSAPolicy objWSAPolicy,ProxAclCustomCategories objProxAclCustomCategories)
	{
		/***********************************************************************************************************
		// SETTING custom categories...element Name : prox_acl_custom_categories => prox_acl_custom_categories
		 ***********************************************************************************************************/
		
		if(objWSAPolicy.getWsaCustomCategoryList()==null || objWSAPolicy.getWsaCustomCategoryList().size()==0)
		{
			return customCategoryCodeIndex;
		}
		
		ProxAclGroupCustomcatActions objProxAclGroupCustomcatActions = objProxAclGroup.getProxAclGroupCustomcatActions();
		if(objProxAclGroupCustomcatActions==null)
		{
			objProxAclGroupCustomcatActions = new ProxAclGroupCustomcatActions();
			objProxAclGroup.setProxAclGroupCustomcatActions(objProxAclGroupCustomcatActions);
		}
		ArrayList<ProxAclGroupCustomcatAction> proxAclGroupCustomcatActionList = (ArrayList<ProxAclGroupCustomcatAction>)objProxAclGroupCustomcatActions.getProxAclGroupCustomcatAction();
		
		ArrayList<ProxAclCustomCategory> proxAclCustomCategoryList = (ArrayList<ProxAclCustomCategory>)objProxAclCustomCategories.getProxAclCustomCategory();
		ArrayList<WSACustomCategory> objWSACustomCategoryList = (ArrayList<WSACustomCategory>)objWSAPolicy.getWsaCustomCategoryList();
		for(int customCategoryIndex = 0; customCategoryIndex < objWSACustomCategoryList.size(); customCategoryIndex++)
		{
			WSACustomCategory objWSACustomCategory = objWSACustomCategoryList.get(customCategoryIndex);
			
			String customCategoryIdFromHashMap = objCustomCategoryHashMap.get(objWSACustomCategory.getName());
			// setting in policy ..
			ProxAclGroupCustomcatAction objProxAclGroupCustomcatAction = new ProxAclGroupCustomcatAction();
			objProxAclGroupCustomcatAction.setCategoryAction(objWSACustomCategory.getAction());
			proxAclGroupCustomcatActionList.add(objProxAclGroupCustomcatAction);
			if(customCategoryIdFromHashMap == null)
			{
				// generate new custom category code ...
				customCategoryCodeIndex++;
				objProxAclGroupCustomcatAction.setCategoryId(customCategoryCodeIndex+"");
				objCustomCategoryHashMap.put(objWSACustomCategory.getName(), customCategoryCodeIndex+"");
			}else
			{
				objProxAclGroupCustomcatAction.setCategoryId(customCategoryIdFromHashMap);	
				continue;
			}
			
			// setting custom category object....
			ProxAclCustomCategory objProxAclCustomCategory = new ProxAclCustomCategory();
			proxAclCustomCategoryList.add(objProxAclCustomCategory);
			objProxAclCustomCategory.setProxAclCustomCategoryCode(customCategoryCodeIndex+"");
			objProxAclCustomCategory.setProxAclCustomCategoryName(objWSACustomCategory.getName());
			if(objProxAclCustomCategory.getProxAclCustomCategoryName()==null || objProxAclCustomCategory.getProxAclCustomCategoryName().length() < 4)
	        {
				objProxAclCustomCategory.setProxAclCustomCategoryAbbrev(objProxAclCustomCategory.getProxAclCustomCategoryName());
	        }else
	        {
	        	objProxAclCustomCategory.setProxAclCustomCategoryAbbrev(objProxAclCustomCategory.getProxAclCustomCategoryName().substring(0, 4));
	        }

			
			// setting regular expression....
			ArrayList<String> regularExpresssionList = (ArrayList<String>) objWSACustomCategory.getRegularExpressions();
			
			//prox_acl_custom_category_regex_list
			ProxAclCustomCategoryRegexList objProxAclCustomCategoryRegexList=  new ProxAclCustomCategoryRegexList();
			objProxAclCustomCategory.setProxAclCustomCategoryRegexList(objProxAclCustomCategoryRegexList);
			//prox_acl_custom_category_regex
			ArrayList<ProxAclCustomCategoryRegex> proxAclCustomCategoryRegexList = (ArrayList<ProxAclCustomCategoryRegex>) objProxAclCustomCategoryRegexList.getProxAclCustomCategoryRegex();
			
			if(regularExpresssionList != null)
			{
				// setting regular Expression.....
				for(int regularExpressionListIndex = 0 ; regularExpressionListIndex < regularExpresssionList.size(); regularExpressionListIndex++)
				{
					ProxAclCustomCategoryRegex objProxAclCustomCategoryRegex = new ProxAclCustomCategoryRegex();
					objProxAclCustomCategoryRegex.setvalue(regularExpresssionList.get(regularExpressionListIndex));
					proxAclCustomCategoryRegexList.add(objProxAclCustomCategoryRegex);
				}
			}
			
			// setting servers......into prox_acl_custom_category_servers element....
			ArrayList<String> objCustomCategoryServers = (ArrayList<String>) objWSACustomCategory.getSites();
			if(objCustomCategoryServers != null)
			{
				ProxAclCustomCategoryServers objProxAclCustomCategoryServers = new ProxAclCustomCategoryServers();
				objProxAclCustomCategory.setProxAclCustomCategoryServers(objProxAclCustomCategoryServers);
				ArrayList<ProxAclCustomCategoryServer> proxAclCustomCategoryServerList =  (ArrayList<ProxAclCustomCategoryServer>)objProxAclCustomCategoryServers.getProxAclCustomCategoryServer();
				
				for(int customCategoryServerIndex = 0; customCategoryServerIndex < objCustomCategoryServers.size(); customCategoryServerIndex++)
				{
					ProxAclCustomCategoryServer objProxAclCustomCategoryServer = new ProxAclCustomCategoryServer();
					objProxAclCustomCategoryServer.setvalue(objCustomCategoryServers.get(customCategoryServerIndex));
					proxAclCustomCategoryServerList.add(objProxAclCustomCategoryServer);
				}
			}
		}
		return customCategoryCodeIndex;
	}
	private ProxAclGroupIps setProxAclGroupIPs(ProxAclGroupIps objProxAclGroupIps,String ip)
	{
		// setting prox_acl_group_ips
		
		if(objProxAclGroupIps==null)
			objProxAclGroupIps = new ProxAclGroupIps();
		ArrayList<ProxAclGroupIp> proxAclGroupIpList = (ArrayList<ProxAclGroupIp>) objProxAclGroupIps.getProxAclGroupIp();
		ProxAclGroupIp objProxAclGroupIp = new ProxAclGroupIp();
		objProxAclGroupIp.setvalue(ip);
		proxAclGroupIpList.add(objProxAclGroupIp);
		return objProxAclGroupIps;
	}
}
