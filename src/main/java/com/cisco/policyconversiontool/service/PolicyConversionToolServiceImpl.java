package com.cisco.policyconversiontool.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.cisco.policyconversiontool.dao.ApplianceDAO;
import com.cisco.policyconversiontool.dao.VendorDAO;
import com.cisco.policyconversiontool.dao.VendorSoftwareDAO;
import com.cisco.policyconversiontool.dto.PolicyConversionParameters;
import com.cisco.policyconversiontool.dto.Software;
import com.cisco.policyconversiontool.dto.WSAMigrationPageInfo;
import com.cisco.policyconversiontool.dto.cws.CWSPolicy;
import com.cisco.policyconversiontool.dto.wsa.wsanormalized.WSAMigratedConfig;
import com.cisco.policyconversiontool.service.cws.parsar.ApplianceParser;
import com.cisco.policyconversiontool.service.cws.parsar.ApplianceParserFactory;
import com.cisco.policyconversiontool.service.exception.PolicyConversionToolException;
import com.cisco.policyconversiontool.service.util.Constants;
import com.cisco.policyconversiontool.service.util.DTDEntityResolver;
import com.cisco.policyconversiontool.service.util.DTDProvider;
import com.cisco.policyconversiontool.service.util.LogUtil;
import com.cisco.policyconversiontool.service.wsa.migrator.ApplianceXMLGenerator;
import com.cisco.policyconversiontool.service.wsa.migrator.ApplianceXMLGeneratorFactory;
import com.cisco.policyconversiontool.service.wsa.migrator.WSAMigrator;

public class PolicyConversionToolServiceImpl implements PolicyConversionToolService {

	private VendorDAO vendorDAO;
	private ApplianceDAO applianceDAO;
	private VendorSoftwareDAO vendorSoftwareDAO;
	
	private WSAMigrator wsaMigrator;
	public VendorDAO getVendorDAO() {
		return vendorDAO;
	}

	public void setVendorDAO(VendorDAO vendorDAO) {
		this.vendorDAO = vendorDAO;
	}

	public ApplianceDAO getApplianceDAO() {
		return applianceDAO;
	}

	public void setApplianceDAO(ApplianceDAO applianceDAO) {
		this.applianceDAO = applianceDAO;
	}

	public VendorSoftwareDAO getVendorSoftwareDAO() {
		return vendorSoftwareDAO;
	}

	public void setVendorSoftwareDAO(VendorSoftwareDAO vendorSoftwareDAO) {
		this.vendorSoftwareDAO = vendorSoftwareDAO;
	}

	public void setWsaMigrator(WSAMigrator wsaMigrator) {
		this.wsaMigrator = wsaMigrator;
	}

	public WSAMigrator getWsaMigrator() {
		return wsaMigrator;
	}

	public WSAMigrationPageInfo getWSAMigrationPageInfo() 
	{
		WSAMigrationPageInfo wsaMigrationPageInfo = new WSAMigrationPageInfo();
		wsaMigrationPageInfo.setSourceVendorList(vendorDAO.getVendors(Integer.parseInt(Constants.DAO_SOURCE_PLATFORM)));
		wsaMigrationPageInfo.setApplianceList(applianceDAO.getAppliances());
		wsaMigrationPageInfo.setSoftwareList(vendorSoftwareDAO.getSoftwareList());
		wsaMigrationPageInfo.setTargetVendorList(vendorDAO.getVendors(Integer.parseInt(Constants.DAO_TARGET_PLATFORM)));
		return wsaMigrationPageInfo;
	
	}
	@Override
	public OutputStream doPolicyConversion(PolicyConversionParameters policyConversionParameters) throws PolicyConversionToolException 
	{
		ByteArrayOutputStream objByteArrayOutputStrem = null;
		DateFormat dateFormat = new SimpleDateFormat("\r\n\r\n[yyyy-MM-dd HH:mm:ss] : ");
		StringBuffer objApplicationLogBuffer = new StringBuffer();
		 try{
		// setting selected params....
		objApplicationLogBuffer.append(dateFormat.format(new Date())+"Application Started.");
//			
		objApplicationLogBuffer.append(dateFormat.format(new Date())+"Source Vendor Details :\r\n--------------------------");
		objApplicationLogBuffer.append("\r\n\tVendor    : CWS");
		objApplicationLogBuffer.append("\r\n\tAppliance : CWS");
		
		objApplicationLogBuffer.append(dateFormat.format(new Date())+"Target Vendor Details :\r\n--------------------------");
		objApplicationLogBuffer.append("\r\n\tVendor    : WSA");
		objApplicationLogBuffer.append("\r\n\tAppliance : WSA");
		Software objSoftware =  vendorSoftwareDAO.getSoftware(policyConversionParameters.getTargetSoftware());
		objApplicationLogBuffer.append("\r\n\tSoftware  : " + objSoftware.getName());
		
		objApplicationLogBuffer.append(dateFormat.format(new Date())+"Application is initialised properly");
		
		objApplicationLogBuffer.append(dateFormat.format(new Date())+"Validating the WSA Inital Configuration.");
		StringBuffer objReviewBuffer = new StringBuffer();
		Object objWSAConfig = validateInitalConfig(policyConversionParameters,objReviewBuffer);
		
		
		objApplicationLogBuffer.append(dateFormat.format(new Date())+"Parsing configuration data...");
		
		ApplianceParserFactory obj_applienceParserFactory = new ApplianceParserFactory();
		ApplianceParser obj_appParser = obj_applienceParserFactory.getApplianceParser();
		objApplicationLogBuffer.append(dateFormat.format(new Date())+"CWS config parser started");
		
		
		objApplicationLogBuffer.append(dateFormat.format(new Date())+"Parsing CWS Configuration.");
		CWSPolicy objCWSPolicy = obj_appParser.doParsing(policyConversionParameters.getSourceConfiguration());
		objApplicationLogBuffer.append(dateFormat.format(new Date())+ "CWS config parsing completed.");
		
		 
		objApplicationLogBuffer.append(dateFormat.format(new Date())+"Generating commands from Parsed data...");
		WSAMigratedConfig wsaMigratedConfig=wsaMigrator.generateWSAPolicyConfig(objCWSPolicy,objReviewBuffer);
		objApplicationLogBuffer.append(dateFormat.format(new Date())+"\r\nTransaction Summary : \r\n--------------------------");
		// set Transaction Summary into loggerStream.....
		objApplicationLogBuffer.append("\r\n\tTotal Http Policy  : "+(wsaMigratedConfig.getWsaPolicyList()==null?"0":wsaMigratedConfig.getWsaPolicyList().size()));
		objApplicationLogBuffer.append("\r\n\tTotal Https Policy : "+(wsaMigratedConfig.getWsaHttpsPolicyList()==null?"0":wsaMigratedConfig.getWsaHttpsPolicyList().size()));
		objApplicationLogBuffer.append("\r\n\tTotal Identities   : "+(wsaMigratedConfig.getWsaIdentityList()==null?"0":wsaMigratedConfig.getWsaIdentityList().size()));
		
		
		ApplianceXMLGeneratorFactory objApplianceXMLGeneratorFactory = new ApplianceXMLGeneratorFactory();
		ApplianceXMLGenerator objApplianceXMLGenerator =   objApplianceXMLGeneratorFactory.getApplianceXMLGenerator(policyConversionParameters.getTargetAppliance(),policyConversionParameters.getTargetSoftware());
		String objWSAGeneratedConfig = objApplianceXMLGenerator.generateXML(wsaMigratedConfig,objWSAConfig);
		
		// generate Zip Archive...here....
		objByteArrayOutputStrem  = new ByteArrayOutputStream();
		ZipOutputStream zos = new ZipOutputStream(objByteArrayOutputStrem);
		addToZipFile(policyConversionParameters.getSourceConfiguration().getBytes(),zos,Constants.FILE_SOURCE_PATH+".json");
		addToZipFile((objReviewBuffer.toString()).getBytes(),zos,Constants.FILE_TARGET_REVIEW_PATH);
		addToZipFile(objWSAGeneratedConfig.getBytes(),zos,Constants.FILE_TARGET_XML_PATH);
		
		
		objApplicationLogBuffer.append(dateFormat.format(new Date())+"Processing Status : Successful");
		objApplicationLogBuffer.append(dateFormat.format(new Date())+"Conversion completed successfully.");
		
		// add log file to archive...
		addToZipFile(objApplicationLogBuffer.toString().getBytes(),zos,Constants.FILE_LOG_OUTPUT_PATH);
		zos.close();
		
		 }catch(Exception e){
//			 	StringWriter errors = new StringWriter();
//			 	e.printStackTrace(new PrintWriter(errors));
//			 	PolicyConversionToolException se = new PolicyConversionToolException();
//				se.setMessage(errors.toString());
//				se.setCause(e);
//				throw se;
			 e.printStackTrace();
			}
		 return (OutputStream)objByteArrayOutputStrem;
	}
	public void addToZipFile(byte[] in, ZipOutputStream zos,String fname) throws Exception
	{
   		ZipEntry zipEntry = new ZipEntry(fname);
   		zos.putNextEntry(zipEntry);
   		zos.write(in, 0, in.length);
   		zos.closeEntry();
   	}
	public Object validateInitalConfig(PolicyConversionParameters objPolicyConversionParameters,StringBuffer reviewBuffer) throws Exception
	{
		// validate the parameters.....
		if(objPolicyConversionParameters.getSourceConfiguration()==null  )
		{
			throw new Exception(Constants.ERROR_SRC_CONFIG_INVALID);
		}
		if(! objPolicyConversionParameters.getTargetAppliance().equals(Constants.TARGET_APPLIENCE_ID_WSA) )
		{
			throw new Exception(Constants.ERROR_TRG_APPLIANCE_INVALID);
		}
		if( objPolicyConversionParameters.getTargetConfiguration() == null )
		{
			throw new Exception(Constants.ERROR_TRG_CONFIG_INVALID);
		}
		if( !(objPolicyConversionParameters.getTargetSoftware().equals(Constants.TARGET_SOWFWARE_WSA_ASYNCOS805) || objPolicyConversionParameters.getTargetSoftware().equals(Constants.TARGET_SOWFWARE_WSA_ASYNCOS806)) )
		{
			throw new Exception(Constants.ERROR_TRG_SOFTWARE_INVALID);
		}
		
		//*******************************
		Object objConfig = readWSAConfiguration(objPolicyConversionParameters.getTargetConfiguration(), objPolicyConversionParameters.getTargetSoftware());
		
		boolean flag = checkWebReputation(objConfig, objPolicyConversionParameters.getTargetSoftware());
		if(!flag)
		{
			reviewBuffer.append(Constants.NEW_LINE + Constants.NEW_LINE + LogUtil.getHeader("Web Reputation"));
			reviewBuffer.append(Constants.NEW_LINE + Constants.NEW_LINE + "Web Reputation is not enabled in Default Policy of WSA.");
		}
		flag = checkHttpsCertificates(objConfig,  objPolicyConversionParameters.getTargetSoftware());
		if(!flag)
		{
			reviewBuffer.append(Constants.NEW_LINE + Constants.NEW_LINE + LogUtil.getHeader("Https Certificate"));
			reviewBuffer.append(Constants.NEW_LINE + Constants.NEW_LINE + "The HTTPS certificate is not configured on the WSA.");
		}
		return objConfig;
		
	}
	private boolean checkHttpsCertificates(Object objConfig,String software) throws Exception
	{
		if(software.equals(Constants.TARGET_SOWFWARE_WSA_ASYNCOS805))
		{
			com.cisco.policyconversiontool.dto.wsa.asyncos805.Config objConfig805 = (com.cisco.policyconversiontool.dto.wsa.asyncos805.Config)objConfig;
			if(objConfig805.getHttpsCertificate()==null || objConfig805.getHttpsCertificate().getCertificate()==null || objConfig805.getHttpsCertificate().getCertificate().equals(""))
			{
				return false;
			}
		}else if(software.equals(Constants.TARGET_SOWFWARE_WSA_ASYNCOS806))
		{
			com.cisco.policyconversiontool.dto.wsa.asyncos806.Config objConfig806 = (com.cisco.policyconversiontool.dto.wsa.asyncos806.Config)objConfig;
			if(objConfig806.getHttpsCertificate()==null || objConfig806.getHttpsCertificate().getCertificate()==null || objConfig806.getHttpsCertificate().getCertificate().equals(""))
			{
				return false;
			}
		}else
		{
			throw new Exception(Constants.ERROR_TRG_SOFTWARE_INVALID);
		}
		return true;
	}
	private Object readWSAConfiguration(String wsaInitialConfiguration,String software) throws Exception
	{
		Object objConfig = null;
		JAXBContext ctx = null;
		File asyncosDTD = DTDProvider.getAsyncosDTD(software);
		try{
			if(software.equals(Constants.TARGET_SOWFWARE_WSA_ASYNCOS805))
			{
				ctx = JAXBContext.newInstance(com.cisco.policyconversiontool.dto.wsa.asyncos805.Config.class);
			}else if(software.equals(Constants.TARGET_SOWFWARE_WSA_ASYNCOS806))
			{
				ctx = JAXBContext.newInstance(com.cisco.policyconversiontool.dto.wsa.asyncos806.Config.class);
			}
	        Unmarshaller unmarshaller = ctx.createUnmarshaller();
	        XMLReader xmlreader = XMLReaderFactory.createXMLReader();
	        DTDEntityResolver enRes = new DTDEntityResolver();
	        enRes.setFile(asyncosDTD);
	        xmlreader.setEntityResolver(enRes);
	        InputSource input = new InputSource(new ByteArrayInputStream(wsaInitialConfiguration.getBytes()));
	        Source source = new SAXSource(xmlreader, input);
	        objConfig = unmarshaller.unmarshal(source);
		}catch(Exception e)
		{
//			e.printStackTrace();
			throw new Exception(Constants.ERROR_INVALID_INI_WSA_CONFIG);
		}

		return objConfig;
	}
	private boolean checkWebReputation(Object objConfig,String software) throws Exception
	{
		//prox_acl_policy_groups
		if(software.equals(Constants.TARGET_SOWFWARE_WSA_ASYNCOS805))
		{
			com.cisco.policyconversiontool.dto.wsa.asyncos805.ProxAclPolicyGroups objProxAclPolicyGroup = ((com.cisco.policyconversiontool.dto.wsa.asyncos805.Config)objConfig).getWgaConfig().getProxAclPolicyGroups();
			com.cisco.policyconversiontool.dto.wsa.asyncos805.ProxAclGroup objProxAclGroup = objProxAclPolicyGroup.getProxAclGroup().get(0);
			if(objProxAclGroup.getProxAclGroupWbrsEnabled().equalsIgnoreCase("yes"))
				return true;
			else
				return false;
		}
		com.cisco.policyconversiontool.dto.wsa.asyncos806.ProxAclPolicyGroups objProxAclPolicyGroup = ((com.cisco.policyconversiontool.dto.wsa.asyncos806.Config)objConfig).getWgaConfig().getProxAclPolicyGroups();
		com.cisco.policyconversiontool.dto.wsa.asyncos806.ProxAclGroup objProxAclGroup = objProxAclPolicyGroup.getProxAclGroup().get(0);
		if(objProxAclGroup.getProxAclGroupWbrsEnabled().equalsIgnoreCase("yes"))
			return true;
		else
			return false;
	}

}
