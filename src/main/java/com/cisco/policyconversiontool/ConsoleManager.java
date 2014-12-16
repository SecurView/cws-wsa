package com.cisco.policyconversiontool;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.cisco.policyconversiontool.dto.Appliance;
import com.cisco.policyconversiontool.dto.Software;
import com.cisco.policyconversiontool.dto.Vendor;
import com.cisco.policyconversiontool.dto.WSAMigrationPageInfo;
public class ConsoleManager {
	
	private Scanner scanner = new Scanner(System.in);
	
	public void acceptParameter(WSAMigrationPageInfo pageInfo) throws ParseException, IOException {
		
//		// Source Vendor List
//		printAcceptMessage("Accepting Source Information:\n\n");
//		for(Vendor Vendor:pageInfo.getSourceVendorList()){
//			printAcceptMessage(""+Vendor.getId()+". "+Vendor.getName()+"\n");
//		}
//		printAcceptMessage("\nSelect the Source vendor: ");
//		String sourceVendorId = scanner.next();
//		wsaMigrationParameters.setSourceVendor(sourceVendorId);
//		printAcceptMessage("\n");
//		
//		// Source Appliance List
//		for(Appliance appliance:pageInfo.getApplianceList(sourceVendorId)){
//			printAcceptMessage(appliance.getId()+".	"+appliance.getName()+"\n");
//		}
//		printAcceptMessage("\nSelect appliance: ");
//		wsaMigrationParameters.setSourceAppliance(scanner.next());
//		
//		// Source Software List
//		for(Software software:pageInfo.getSoftwareList(sourceVendorId)){
//			printAcceptMessage(""+software.getId()+". "+software.getName()+"\n");
//		}
//		printAcceptMessage("\nSelect Software: ");
//		wsaMigrationParameters.setSourceSoftware(scanner.next());
//		printAcceptMessage("\n");
//		// SourceFilePath 
//		printAcceptMessage("\nSelect the Source archieved file Path: ");
//		wsaMigrationParameters.setSourceArchievedFile(generate(scanner.next()));
//		printAcceptMessage("\n");
////		printAcceptMessage("\nDo you want to change the Source Vendor now [y/n]: ");
////		acceptSourceVendor(wsaMigrationParameters, pageInfo);
//		
//		
//
//		// Target Vendor List
//		printAcceptMessage("Accepting Target Information:\n\n");
//		for(Vendor Vendor:pageInfo.getTargetVendorList()){
//			printAcceptMessage(""+Vendor.getId()+".	"+Vendor.getName()+"\n");
//		}
//		printAcceptMessage("\nSelect Vendor: ");
//		String targetVendorId = scanner.next();
//		wsaMigrationParameters.setTargetVendor(targetVendorId);
//		printAcceptMessage("\n");
//		
//						
//		// Target Appliance List
//		for(Appliance appliance:pageInfo.getApplianceList(targetVendorId)){
//			printAcceptMessage(""+appliance.getId()+".	"+appliance.getName()+"\n");
//		}
//		printAcceptMessage("\nSelect Appliance: ");
//		wsaMigrationParameters.setTargetAppliance(scanner.next());
//		printAcceptMessage("\n");
//				
//		// Target Software List
//		for(Software software:pageInfo.getSoftwareList(targetVendorId)){
//			printAcceptMessage(""+software.getId()+". "+software.getName()+"\n");
//		}
//		printAcceptMessage("\nSelect Software: ");
//		wsaMigrationParameters.setTargetSoftware(scanner.next());
//		// TargetFilePath 
//		printAcceptMessage("\nSelect the Initial Target XML File Path: ");
//		wsaMigrationParameters.setTargetXMLFile(new FileInputStream(new File(scanner.next())));
////		printAcceptMessage("\nDo you want to change the Target Vendor now [y/n]: ");
////		acceptTargetVendor(wsaMigrationParameters,pageInfo);
//		
//		
////		return wsaMigrationParameters;
	}
	public boolean acceptSourceVendor(WSAMigrationPageInfo pageInfo) throws IOException{
		return false;
//		if (scanner.next().trim().equalsIgnoreCase("y")){
//			for(Vendor Vendor:pageInfo.getSourceVendorList()){
//				printAcceptMessage(" \nVendorId- "+Vendor.getId()+"	Vendor Name- "+Vendor.getName()+"	Vendor Description- "+Vendor.getDesc());
//			}
//			printAcceptMessage("\nProvide Source Vendor Id: ");
//			String strVendorSourceId=scanner.next();
//			wsaMigrationParameters.setSourceVendor(strVendorSourceId);
//			for(Appliance appliance:pageInfo.getApplianceList(strVendorSourceId)){
//				printAcceptMessage("\n ApplianceId- "+appliance.getId()+"	Vendor Name- "+appliance.getName()+"	Vendor Description- "+appliance.getDesc());
//			}
//			printAcceptMessage("\nProvide Source Appliance Id: ");
//			wsaMigrationParameters.setSourceAppliance(scanner.next());
//			for(Software software:pageInfo.getSoftwareList(strVendorSourceId)){
//				printAcceptMessage(" \nSoftwareId- "+software.getId()+"	Software Name- "+software.getName()+"	Software Description- "+software.getDesc());
//			}
//			printAcceptMessage("\nProvide Source Software Id: ");
//			wsaMigrationParameters.setSourceSoftware(scanner.next());
//			printAcceptMessage("\nSelect the wsa migration Source Zip File Path:\n");
//			wsaMigrationParameters.setSourceArchievedFile(generate(scanner.next()));
//			return true;
//		}else{
//			return false;
//		}
	}
	
	public boolean acceptTargetVendor(WSAMigrationPageInfo pageInfo) throws IOException{
		return false;
//		if (scanner.next().trim().equalsIgnoreCase("y")){
//			for(Vendor Vendor:pageInfo.getTargetVendorList()){
//				printAcceptMessage("\n VendorId- "+Vendor.getId()+"	Vendor Name- "+Vendor.getName()+"	Vendor Description- "+Vendor.getDesc());
//				}
//			printAcceptMessage("\nProvide Target Vendor Id: ");
//			String strTargetSourceId=scanner.next(); 
//			wsaMigrationParameters.setTargetVendor(strTargetSourceId);
//			for(Appliance appliance:pageInfo.getApplianceList(strTargetSourceId)){
//				printAcceptMessage("\n ApplianceId- "+appliance.getId()+"	Vendor Name- "+appliance.getName()+"	Vendor Description- "+appliance.getDesc());
//			}
//			printAcceptMessage("\nProvide Target Appliance Id: ");
//			wsaMigrationParameters.setTargetAppliance(scanner.next());
//			for(Software software:pageInfo.getSoftwareList(strTargetSourceId)){
//				printAcceptMessage(" \nSoftwareId- "+software.getId()+"	Software Name- "+software.getName()+"	Software Description- "+software.getDesc());
//			}
//			printAcceptMessage("\nProvide Target Software Id: ");
//			wsaMigrationParameters.setTargetSoftware(scanner.next());
//			// TargetFilePath 
//			printAcceptMessage("\nSelect the wsa migration Target XML File Path:\n");
//			wsaMigrationParameters.setTargetXMLFile(new FileInputStream(new File(scanner.next())));
//			return true;
//		}else{
//			return false;
//		}
	}
	
	public static void printConsoleMessage(String message){
		System.out.println(message);
	}

	public static void printAcceptMessage(String message){
		System.out.print(message);
	}
	
	public void showMessage(List<String> messageList){
		for(String strMessage: messageList){
			printConsoleMessage(strMessage);
		}		
	}
	public void closeScanner(){
		scanner.close();
	}
	
	public InputStream generate(String zipInPath) throws IOException
    {
            InputStream in = null;
            int BUFFER_SIZE = 4096;
            ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipInPath));
            ZipEntry entry = zipIn.getNextEntry();
            while (entry != null)
            {
                ByteArrayOutputStream by =new ByteArrayOutputStream();
                BufferedOutputStream bos = new BufferedOutputStream(by);
                byte[] bytesIn = new byte[BUFFER_SIZE];
                int read = 0;
                while ((read = zipIn.read(bytesIn)) != -1) {
                    bos.write(bytesIn, 0, read);
                }
                bos.close();
                in = new ByteArrayInputStream(by.toByteArray());
                zipIn.closeEntry();
                entry = zipIn.getNextEntry();
            }
            zipIn.close();
            return in;
    }
}
