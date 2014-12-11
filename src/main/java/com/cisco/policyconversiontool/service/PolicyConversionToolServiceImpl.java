package com.cisco.policyconversiontool.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;





import org.apache.poi.util.IOUtils;

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
		WSAMigratedConfig wsaMigratedConfig=wsaMigrator.generateWSAPolicyConfig(objCWSPolicy,wsaMigrationParameters);
		loggerStream.append(dateFormat.format(new Date())+"\r\nTransaction Summary : \r\n--------------------------");
		// set Transaction Summary into loggerStream.....
		loggerStream.append("\r\n\tTotal Http Policy  : "+(wsaMigratedConfig.getWsaPolicyList()==null?"0":wsaMigratedConfig.getWsaPolicyList().size()));
		loggerStream.append("\r\n\tTotal Https Policy : "+(wsaMigratedConfig.getWsaHttpsPolicyList()==null?"0":wsaMigratedConfig.getWsaHttpsPolicyList().size()));
		loggerStream.append("\r\n\tTotal Identities   : "+(wsaMigratedConfig.getWsaIdentityList()==null?"0":wsaMigratedConfig.getWsaIdentityList().size()));
		
//		wsaMigrator.printwsaMigratedConfig(wsaMigratedConfig);
		
//		ApplianceXMLGeneratorFactory objApplianceXMLGeneratorFactory = new ApplianceXMLGeneratorFactory();
//		wsaMigratedConfig.setInputStream(wsaMigrationParameters.getTargetXMLFile());
//		ApplianceXMLGenerator objApplianceXMLGenerator =   objApplianceXMLGeneratorFactory.getApplianceXMLGenerator(wsaMigrationParameters.getTargetAppliance(),wsaMigrationParameters.getTargetSoftware());
//		OutputStream wsaXMLOutputStream = null ;//objApplianceXMLGenerator.generateXML(wsaMigratedConfig);
		
		
		
		// generate Zip Archive...here....
//		objByteArrayOutputStrem  = new ByteArrayOutputStream();
//		ZipOutputStream zos = new ZipOutputStream(objByteArrayOutputStrem);
//		addToZipFile(bytes,zos,Constants.FILE_SOURCE_PATH+"."+(wsaMigrationParameters.getSourceAppliance().equals(Constants.APPLIENCE_ID_BLUECOAT)?Constants.FILE_EXTENSION_XML:Constants.FILE_EXTENSION_XLS));
//		addToZipFile((wsaMigratedConfig.getReviewByteArrayOutputStream()).toByteArray(),zos,Constants.FILE_TARGET_REVIEW_PATH);
//		addToZipFile(((ByteArrayOutputStream)wsaXMLOutputStream).toByteArray(),zos,Constants.FILE_TARGET_XML_PATH);
		
		
//		loggerStream.append(dateFormat.format(new Date())+"Processing Status : Successful");
//		loggerStream.append(dateFormat.format(new Date())+"Conversion completed successfully.");
		
////			FileInputStream fis = new FileInputStream(new File(Constants.FILE_LOG_INPUT_PATH));
//		addToZipFile(loggerStream.toString().getBytes(),zos,Constants.FILE_LOG_OUTPUT_PATH);
////			fis.close();
//		
////			fis = new FileInputStream(new File(Constants.FILE_PDF_INPUT_PATH));
////			addToZipFile(IOUtils.toByteArray(fis),zos,Constants.FILE_PDF_OUTPUT_PATH);
////			fis.close();
//		zos.close();
		return (OutputStream)objByteArrayOutputStrem;
		 }catch(Exception e){
			 	StringWriter errors = new StringWriter();
			 	e.printStackTrace(new PrintWriter(errors));
			 	PolicyConversionToolException se = new PolicyConversionToolException();
				se.setMessage(errors.toString());
				se.setCause(e);
				throw se;
			}
	}
	public static void addToZipFile(byte[] in, ZipOutputStream zos,String fname) throws Exception
	{
   		ZipEntry zipEntry = new ZipEntry(fname);
   		zos.putNextEntry(zipEntry);
   		zos.write(in, 0, in.length);
   		zos.closeEntry();
   	}
}
