package com.cisco.policyconversiontool.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;

import org.apache.poi.util.IOUtils;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.cisco.policyconversiontool.dao.ApplianceDAO;
import com.cisco.policyconversiontool.dao.VendorDAO;
import com.cisco.policyconversiontool.dao.VendorSoftwareDAO;
import com.cisco.policyconversiontool.dto.Appliance;
import com.cisco.policyconversiontool.dto.Software;
import com.cisco.policyconversiontool.dto.Vendor;
import com.cisco.policyconversiontool.dto.WSAMigrationPageInfo;
import com.cisco.policyconversiontool.dto.WSAMigrationParameters;
import com.cisco.policyconversiontool.dto.cws.CWSPolicy;
import com.cisco.policyconversiontool.dto.wsa.wsanormalized.WSAMigratedConfig;
import com.cisco.policyconversiontool.service.cws.parsar.ApplianceParser;
import com.cisco.policyconversiontool.service.cws.parsar.ApplianceParserFactory;
import com.cisco.policyconversiontool.service.cws.parsar.CWSParser;
import com.cisco.policyconversiontool.service.exception.PolicyConversionToolException;
import com.cisco.policyconversiontool.service.util.Constants;
import com.cisco.policyconversiontool.service.util.DTDEntityResolver;
import com.cisco.policyconversiontool.service.util.DTDProvider;
import com.cisco.policyconversiontool.service.wsa.migrator.ApplianceXMLGenerator;
import com.cisco.policyconversiontool.service.wsa.migrator.ApplianceXMLGeneratorFactory;
import com.cisco.policyconversiontool.service.wsa.migrator.WSAMigrator;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

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
	
	public OutputStream doWSAMigration(WSAMigrationParameters wsaMigrationParameters) throws PolicyConversionToolException 
	{
		ByteArrayOutputStream objByteArrayOutputStrem = null;
		DateFormat dateFormat = new SimpleDateFormat("\r\n\r\n[yyyy-MM-dd HH:mm:ss] : ");
		StringBuffer loggerStream = new StringBuffer();
		String sourceVendor = null;
		 try{
		// setting selected params....
		loggerStream.append(dateFormat.format(new Date())+"Application Started.");
//			
		loggerStream.append(dateFormat.format(new Date())+"Source Vendor Details :\r\n--------------------------");
		Vendor objVendor = vendorDAO.getVendor(wsaMigrationParameters.getSourceVendor());
		loggerStream.append("\r\n\tVendor    : " + objVendor.getName());
		sourceVendor = objVendor.getName();
		Appliance objAppliance = applianceDAO.getAppliance(wsaMigrationParameters.getSourceAppliance());
		loggerStream.append("\r\n\tAppliance : " + objAppliance.getName());
		Software objSoftware =  vendorSoftwareDAO.getSoftware(wsaMigrationParameters.getSourceSoftware());
		loggerStream.append("\r\n\tSoftware  : " + objSoftware.getName());
		
		loggerStream.append(dateFormat.format(new Date())+"Target Vendor Details :\r\n--------------------------");
		objVendor = vendorDAO.getVendor(wsaMigrationParameters.getTargetVendor());
		loggerStream.append("\r\n\tVendor    : " + objVendor.getName());
		objAppliance = applianceDAO.getAppliance(wsaMigrationParameters.getTargetAppliance());
		loggerStream.append("\r\n\tAppliance : " + objAppliance.getName());
		objSoftware =  vendorSoftwareDAO.getSoftware(wsaMigrationParameters.getTargetSoftware());
		loggerStream.append("\r\n\tSoftware  : " + objSoftware.getName());
		
		loggerStream.append(dateFormat.format(new Date())+"Application is initialised properly");
		
		loggerStream.append(dateFormat.format(new Date())+"Validating the WSA Inital Configuration.");
		StringBuffer reviewBuffer = new StringBuffer();
		Object objWSAConfig = validateWSAInitalConfig(wsaMigrationParameters.getTargetXMLFile(),wsaMigrationParameters.getTargetSoftware(),reviewBuffer);
		
		
		loggerStream.append(dateFormat.format(new Date())+"Parsing configuration data...");
		
		ApplianceParserFactory obj_applienceParserFactory = new ApplianceParserFactory();
		ApplianceParser obj_appParser = obj_applienceParserFactory.getApplianceParser(wsaMigrationParameters.getSourceAppliance());
		loggerStream.append(dateFormat.format(new Date())+sourceVendor+" config parser started");
		
		InputStream inputStream = wsaMigrationParameters.getSourceArchievedFile();
		byte[] bytes = IOUtils.toByteArray(inputStream);
		inputStream.close();
		inputStream = new ByteArrayInputStream(bytes);
		
		loggerStream.append(dateFormat.format(new Date())+"Parsing " + sourceVendor +" input File.");
		CWSPolicy objCWSPolicy = obj_appParser.doParsing(inputStream);
		loggerStream.append(dateFormat.format(new Date())+ sourceVendor +" config parsing completed.");
		
		 
		loggerStream.append(dateFormat.format(new Date())+"Generating commands from Parsed data...");
		WSAMigratedConfig wsaMigratedConfig=wsaMigrator.generateWSAPolicyConfig(objCWSPolicy,wsaMigrationParameters,reviewBuffer);
		loggerStream.append(dateFormat.format(new Date())+"\r\nTransaction Summary : \r\n--------------------------");
		// set Transaction Summary into loggerStream.....
		loggerStream.append("\r\n\tTotal Http Policy  : "+(wsaMigratedConfig.getWsaPolicyList()==null?"0":wsaMigratedConfig.getWsaPolicyList().size()));
		loggerStream.append("\r\n\tTotal Https Policy : "+(wsaMigratedConfig.getWsaHttpsPolicyList()==null?"0":wsaMigratedConfig.getWsaHttpsPolicyList().size()));
		loggerStream.append("\r\n\tTotal Identities   : "+(wsaMigratedConfig.getWsaIdentityList()==null?"0":wsaMigratedConfig.getWsaIdentityList().size()));
		
//		wsaMigrator.printwsaMigratedConfig(wsaMigratedConfig);
		
		ApplianceXMLGeneratorFactory objApplianceXMLGeneratorFactory = new ApplianceXMLGeneratorFactory();
		wsaMigratedConfig.setInputStream(wsaMigrationParameters.getTargetXMLFile());
		ApplianceXMLGenerator objApplianceXMLGenerator =   objApplianceXMLGeneratorFactory.getApplianceXMLGenerator(wsaMigrationParameters.getTargetAppliance(),wsaMigrationParameters.getTargetSoftware());
		OutputStream wsaXMLOutputStream = objApplianceXMLGenerator.generateXML(wsaMigratedConfig,objWSAConfig);
		
//		OutputStream out = new FileOutputStream("D:\\output.xml");
//		out.write(wsaXMLOutputStream.toString().getBytes());
		
		// generate Zip Archive...here....
		objByteArrayOutputStrem  = new ByteArrayOutputStream();
		ZipOutputStream zos = new ZipOutputStream(objByteArrayOutputStrem);
		addToZipFile(bytes,zos,Constants.FILE_SOURCE_PATH+".json");
		addToZipFile((reviewBuffer.toString()).getBytes(),zos,Constants.FILE_TARGET_REVIEW_PATH);
		addToZipFile(((ByteArrayOutputStream)wsaXMLOutputStream).toByteArray(),zos,Constants.FILE_TARGET_XML_PATH);
		
		
		loggerStream.append(dateFormat.format(new Date())+"Processing Status : Successful");
		loggerStream.append(dateFormat.format(new Date())+"Conversion completed successfully.");
		
//			FileInputStream fis = new FileInputStream(new File(Constants.FILE_LOG_INPUT_PATH));
			addToZipFile(loggerStream.toString().getBytes(),zos,Constants.FILE_LOG_OUTPUT_PATH);
//			fis.close();
			
//			fis = new FileInputStream(new File(Constants.FILE_PDF_INPUT_PATH));
//			addToZipFile(IOUtils.toByteArray(fis),zos,Constants.FILE_PDF_OUTPUT_PATH);
//			fis.close();
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
	public Object validateWSAInitalConfig(InputStream wsaInitialConfig,String software,StringBuffer reviewBuffer) throws Exception
	{
		Object objConfig = readWSAConfiguration(wsaInitialConfig, software);
		boolean flag = checkWebReputation(objConfig,software);
		if(!flag)
		{
			reviewBuffer.append(Constants.NEW_LINE + Constants.NEW_LINE + "!************************ Web Reputation ***********************************!");
			reviewBuffer.append(Constants.NEW_LINE + Constants.NEW_LINE + "Web Reputation is not enabled in Default Policy of WSA.");
		}
		flag = checkHttpsCertificates(objConfig, software);
		if(!flag)
		{
			reviewBuffer.append(Constants.NEW_LINE + Constants.NEW_LINE + "!************************ Https Certificate ***********************************!");
			reviewBuffer.append(Constants.NEW_LINE + Constants.NEW_LINE + "The HTTPS certificate is not configured on the WSA.");
		}
		return objConfig;
		
	}
	public boolean checkHttpsCertificates(Object objConfig,String software) throws Exception
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
			throw new Exception("Target Software is invalid.");
		}
		return true;
	}
	public Object readWSAConfiguration(InputStream wsaInitialConfig,String software) throws Exception
	{
		Object objConfig = null;
		JAXBContext ctx = null;
		File asyncosDTD = null;
		try{
			if(software.equals(Constants.TARGET_SOWFWARE_WSA_ASYNCOS805))
			{
				ctx = JAXBContext.newInstance(com.cisco.policyconversiontool.dto.wsa.asyncos805.Config.class);
				asyncosDTD = DTDProvider.getAsyncos805DTD();
			}else if(software.equals(Constants.TARGET_SOWFWARE_WSA_ASYNCOS806))
			{
				ctx = JAXBContext.newInstance(com.cisco.policyconversiontool.dto.wsa.asyncos806.Config.class);
				asyncosDTD = DTDProvider.getAsyncos806DTD();
			}
	        Unmarshaller unmarshaller = ctx.createUnmarshaller();
	        XMLReader xmlreader = XMLReaderFactory.createXMLReader();
	        DTDEntityResolver enRes = new DTDEntityResolver();
	        enRes.setFile(asyncosDTD);
	        xmlreader.setEntityResolver(enRes);
	        InputSource input = new InputSource(wsaInitialConfig);
	        Source source = new SAXSource(xmlreader, input);
	        objConfig = unmarshaller.unmarshal(source);
		}catch(Exception e)
		{
			throw new Exception("Invalid initial WSA configuration");
		}

		return objConfig;
	}
	public boolean checkWebReputation(Object objConfig,String software) throws Exception
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
		}else if(software.equals(Constants.TARGET_SOWFWARE_WSA_ASYNCOS806))
		{
			com.cisco.policyconversiontool.dto.wsa.asyncos806.ProxAclPolicyGroups objProxAclPolicyGroup = ((com.cisco.policyconversiontool.dto.wsa.asyncos806.Config)objConfig).getWgaConfig().getProxAclPolicyGroups();
			com.cisco.policyconversiontool.dto.wsa.asyncos806.ProxAclGroup objProxAclGroup = objProxAclPolicyGroup.getProxAclGroup().get(0);
			if(objProxAclGroup.getProxAclGroupWbrsEnabled().equalsIgnoreCase("yes"))
				return true;
			else
				return false;
		}
		throw new Exception("Target Software is invalid.");
	}
}
