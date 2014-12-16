package com.cisco.policyconversiontool.service.util;

public class Constants 
{
	
	public final static String ERROR_INVALID_INI_WSA_CONFIG = "Invalid initial WSA configuration";
	public final static String ERROR_TRG_SOFTWARE_INVALID = "Target Software is invalid.";
	public final static String ERROR_TRG_CONFIG_INVALID = "Target Configuration is Invalid.";
	public final static String ERROR_TRG_APPLIANCE_INVALID = "Target Appliance is Invalid.";
	public final static String ERROR_SRC_CONFIG_INVALID = "Source Configuration is Invalid.";
	
	
	public final static String WSA_CONFIG_DTD_805_PATH = "src//main//resource//config//wsaDTD805.dtd";
	public final static String WSA_CONFIG_DTD_806_PATH = "src//main//resource//config//wsaDTD806.dtd";
	public  final static String WINNT="WinNT://";
    public  final static String LDAP="LDAP://";
    public  final static String AUTH_GROUP_TYPE_CUSTOM="";
    public  final static String AUTH_GROUP_TYPE_DIRECTORY="";
    public  final static String CATAGORY_CHILD_ABUSE_CONTENT_CODE="1064";
    
	public static final String NEW_LINE = "\r\n";
	public static final String CWS_ACTION_ALLOW = "1";
	public static final String CWS_ACTION_BLOCK = "2";
	public static final String CWS_ACTION_WARN = "3";
	
	public static final String TARGET_SOWFWARE_WSA_ASYNCOS805 = "1";
	public static final String TARGET_SOWFWARE_WSA_ASYNCOS806 = "2";
	
	public static final String FILE_PDF_INPUT_PATH = "pdf\\readMe.pdf";
	public static final String FILE_PDF_OUTPUT_PATH = "ReadMe.pdf";
	
	public static final String FILE_LOG_OUTPUT_PATH = "logs\\info.log";
	public static final String FILE_LOG_INPUT_PATH = "logs\\platinfoLogs.log";
	
	
	public static final String FILE_TARGET_XML_PATH = "tgt\\target.xml";
	public static final String FILE_TARGET_REVIEW_PATH = "tgt\\review.txt";
	public static final String FILE_SOURCE_PATH = "src\\source";
	public static final String FILE_EXTENSION_XML = "xml";
	public static final String FILE_EXTENSION_XLS = "xls";
 
	
	public static final String TARGET_APPLIENCE_ID_WSA="1";
	public static final String APPLIENCE_ID_BLUECOAT="3";
	public static final String APPLIENCE_ID_WEBSENCE="2";
	public static final String APPLIENCE_ID_WSA="1";
	
	public static final String DAO_SOURCE_PLATFORM="1";
	public static final String DAO_TARGET_PLATFORM="2";
	//for WSAMigratior
	public  final static String CLIENTTYPE_USER="1";
	public  final static String CLIENTTYPE_COMPUTER="2";
	public  final static String CLIENTTYPE_NETWORK="3";
	public  final static String CLIENTTYPE_GROUP="4";
	public  final static String CLIENTTYPE_DOMAIN="5";
	
	public  final static String LIMITEDACCESSFILTER="1";
	public  final static String CUSTOM="2";
	public  final static String ACTION_PERMIT="permit";
	public  final static String ACTION_BLOCK="block";
	public  final static String ACTION_SCAN="scan";
	public  final static String ACTION_MONITOR="monitor";
	
	public  final static String BEFORE="1";
	public  final static String EQUAL="0";
	public  final static String AFTER="-1";
	
	 
	
	public final static String MONDAY = "mon";
	public final static String TUESDAY = "tue";
	public final static String WEDNESDAY = "wed";
	public final static String THURSDAY = "thu";
	public final static String FRIDAY = "fri";
	public final static String SATURDAY = "sat";
	public final static String SUNDAY = "sun";
	
	public final static long ARGTYPE_FILTER_POLICY_NAME = 1L;
	public final static long ARGTYPE_SCHEDULE_NAME = 2L;
	public final static long ARGTYPE_AUTHGROUP_NAME = 3L;
	
	public final static String POLICY_NAME_SEPRATOR = ".";
	
	public final static String TRUE = "true";
	
	public final static long ADV_RULE_ACTION_ALLOW = 1L;
	public final static long ADV_RULE_ACTION_WARN = 2L;
	public final static long ADV_RULE_ACTION_BLOCK = 1L;
	
	public static final String WSA_REALM_IDENTITY = "realm";
	public static final String APPLIENCE_ID_CWS = "4";
	
}
