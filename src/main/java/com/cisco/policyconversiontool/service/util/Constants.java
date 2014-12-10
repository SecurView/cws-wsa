package com.cisco.policyconversiontool.service.util;

public class Constants 
{
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
	
	public static final String BLUECOAT_ACTION_ALLOW = "Allow";
	public static final String BLUECOAT_ACTION_DENY = "Deny";
	
	public static final String NORMALIZED_ACTION_DECRYPT = "Decrypt";
	public static final String NORMALIZED_ACTION_DONOTDECRYPT = "Do Not Decrypt";
	
	
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
	public  final static String ACTION_PERMIT="Permit";
	public  final static String ACTION_BLOCK="Block";
	public  final static String ACTION_SCAN="Scan";
	public  final static String ACTION_MONITOR="Monitor";
	
	public  final static String BEFORE="1";
	public  final static String EQUAL="0";
	public  final static String AFTER="-1";
	
	public final static String BLUECOAT_DAY_MONDAY = "1";
	public final static String BLUECOAT_DAY_TUESDAY = "2";
	public final static String BLUECOAT_DAY_WEDNESDAY = "3";
	public final static String BLUECOAT_DAY_THURSDAY = "4";
	public final static String BLUECOAT_DAY_FRIDAY = "5";
	public final static String BLUECOAT_DAY_SATURDAY = "6";
	public final static String BLUECOAT_DAY_SUNDAY = "7";
	
	public final static String NORMALIZED_DAY_MONDAY = "mon";
	public final static String NORMALIZED_DAY_TUESDAY = "tue";
	public final static String NORMALIZED_DAY_WEDNESDAY = "wed";
	public final static String NORMALIZED_DAY_THURSDAY = "thu";
	public final static String NORMALIZED_DAY_FRIDAY = "fri";
	public final static String NORMALIZED_DAY_SATURDAY = "sat";
	public final static String NORMALIZED_DAY_SUNDAY = "sun";
	
	
	public final static String TRUE = "true";
	
	public final static String BLUECOAT_COLITEM_SO = "so";
	public final static String BLUECOAT_COLITEM_DE = "de";
	public final static String BLUECOAT_COLITEM_SE = "se";
	public final static String BLUECOAT_COLITEM_TI = "ti";
	public final static String BLUECOAT_COLITEM_AC = "ac";
	
	
	public final static String ELEMENT_VPMAPP = "vpmapp";
	public final static String ELEMENT_VPMXML_INFO = "vpmxml-info";
	public final static String ELEMENT_CONDITIONOBJECTS = "conditionObjects";
	public final static String ELEMENT_CATEGORYLIST4 = "categorylist4";
	public final static String ELEMENT_SEL = "sel";
	public final static String ELEMENT_AI = "ai";
	public final static String ELEMENT_COMB_OBJ = "comb-obj";
	public final static String ELEMENT_C_L_1 = "c-l-1";
	public final static String ELEMENT_AUTH_OBJ = "auth-obj";
	public final static String ELEMENT_IPOBJECT = "ipobject";
	public final static String ELEMENT_PROTOCOL = "protocol";
	public final static String ELEMENT_PROT_METH = "prot-meth";
	public final static String ELEMENT_APP_URL = "app-url";
	public final static String ELEMENT_SELECTED_APPLICATIONS = "selected-applications";
	public final static String ELEMENT_ITEM = "item";
	public final static String ELEMENT_SCAN = "scan";
	public final static String ELEMENT_OP_URL = "op-url";
	public final static String ELEMENT_SELECTED_OPERATIONS = "selected-operations";
	public final static String ELEMENT_USER_AGENT_LIST = "user-agent-list";
	public final static String ELEMENT_USER_AGENT_ITEM = "user-agent-item";
	public final static String ELEMENT_SVC_NAME = "svc-name";
	public final static String ELEMENT_SSL_FWD_PRXY522 = "ssl-fwd-prxy522";
	public final static String ELEMENT_SSL_FWD_PRXY = "ssl-fwd-prxy";
	public final static String ELEMENT_DEF_GRP = "def-grp";
	public final static String ELEMENT_GROUP = "group";
	public final static String ELEMENT_FILE_DOWNLOAD = "file-download";
	public final static String ELEMENT_FILE_TYPE_INFO = "file-type-info";
	public final static String ELEMENT_PROTOCOL3_2 = "protocol3_2";
	public final static String ELEMENT_H_O = "h-o";
	public final static String ELEMENT_AF_HOST = "af-host";
	public final static String ELEMENT_H = "h";
	public final static String ELEMENT_LAYERS = "layers";
	public final static String ELEMENT_LAYER = "layer";
	public final static String ELEMENT_ROWITEM = "rowItem";
	public final static String ELEMENT_COLITEM = "colItem";
	public final static String ELEMENT_TIME = "time";
	
	public static final Object ELEMENT_NAME = "name";
	public static final Object ELEMENT_NUMROWS = "numRows";

	
	
	
	public final static String ATTRIBUTE_VERSION = "version";
	public final static String ATTRIBUTE_NAME = "name";
	public final static String ATTRIBUTE_TYP = "typ";
	public final static String ATTRIBUTE_A = "a";
	public final static String ATTRIBUTE_N = "n";
	public final static String ATTRIBUTE_D = "d";
	public final static String ATTRIBUTE_N_1 = "n-1";
	public final static String ATTRIBUTE_N_2 = "n-2";
	public final static String ATTRIBUTE_T = "t";
	public final static String ATTRIBUTE_F = "f";
	public final static String ATTRIBUTE_FORM = "form";
	public final static String ATTRIBUTE_MODE = "mode";
	public final static String ATTRIBUTE_NP_FORM = "np-form";
	public final static String ATTRIBUTE_Q_FORM = "q-form";
	public final static String ATTRIBUTE_R_N = "r-n";
	public final static String ATTRIBUTE_SINGLE = "single";
	public final static String ATTRIBUTE_TYPE = "type";
	public final static String ATTRIBUTE_VALUE = "value";
	public final static String ATTRIBUTE_SUBTYPE = "subtype";
	public final static String ATTRIBUTE_M = "m";
	public final static String ATTRIBUTE_NON_H = "non-h";
	public final static String ATTRIBUTE_APPNAME = "appName";
	public final static String ATTRIBUTE_ICAP_SERVICE = "ICAP-service";
	public final static String ATTRIBUTE_FAIL_OPEN = "fail-open";
	public final static String ATTRIBUTE_SECURE = "secure";
	public final static String ATTRIBUTE_SL = "sl";
	public final static String ATTRIBUTE_DESCRIPTION = "description";
	public final static String ATTRIBUTE_USER_AGENT_REGEX = "user-agent-regex";
	public final static String ATTRIBUTE_V = "v";
	public final static String ATTRIBUTE_K = "k";
	public final static String ATTRIBUTE_ON_EXC = "on-exc";
	public final static String ATTRIBUTE_DELIMITERS = "delimiters";
	public final static String ATTRIBUTE_GROUP_BASE = "group-base";
	public final static String ATTRIBUTE_GROUP_LOCATION = "group-location";
	public final static String ATTRIBUTE_GROUP_PREFIX = "group-prefix";
	public final static String ATTRIBUTE_GROUP_SUFFIX = "group-suffix";
	public final static String ATTRIBUTE_REALM_NAME = "realm-name";
	public final static String ATTRIBUTE_REALM_TYPE = "realm-type";
	public final static String ATTRIBUTE_SUFFIX = "suffix";
	public final static String ATTRIBUTE_RESTRICT = "restrict";
	public final static String ATTRIBUTE_USER = "user";
	public final static String ATTRIBUTE_ALLOW_MIME_TYPES = "allow-mime-types";
	public final static String ATTRIBUTE_FILE_EXTENSION = "file-extension";
	public final static String ATTRIBUTE_MIME_TYPE = "mime-type";
	public final static String ATTRIBUTE_USE_FILE_EXTENSION = "use-file-extension";
	public final static String ATTRIBUTE_USE_MIME_TYPE = "use-mime-type";
	public final static String ATTRIBUTE_H = "h";
	public final static String ATTRIBUTE_H_T = "h-t";
	public final static String ATTRIBUTE_F_O = "f-o";
	public final static String ATTRIBUTE_LAYERTYPE = "layertype";
	public final static String ATTRIBUTE_ENABLED = "enabled";
	public final static String ATTRIBUTE_NUM = "num";
	public final static String ATTRIBUTE_COL = "col";
	public final static String ATTRIBUTE_ID = "id";
	public final static String ATTRIBUTE_NEGATE = "negate";
	public static final String ATTRIBUTE_TIME = "time";
	public static final String ATTRIBUTE_UTC = "UTC";
	public static final String ATTRIBUTE_DAYS = "days";
	
	public static final  String CONST_POLICIES	=	"Policies";
	public static final  String CONST_CATEGORYFILTERS	=	"Category Filters";
	public static final  String CONST_TIMEPERIODS	=	"Time Periods";
	public static final  String CONST_PROTOCOLFILTERS	=	"Protocol Filters";
	public static final  String CONST_CATEGORYACTIONS	=	"Category Actions";
	public static final  String CONST_WEBSENSELIMITEDACCESS	=	"WebsenseLimitedAccess";
	public static final  String CONST_PROTOCOLACTIONS	=	"Protocol Actions";
	public static final  String CONST_CUSTOMCATEGORIES =	"Custom Categories";
	public static final  String CONST_CUSTOMPROTOCOLS =	"Custom Protocols";
	public static final  String CONST_KEYWORDS ="Keywords";
	public static final  String CONST_PROTOCOLDEFINITION ="Protocol Definition";
	public static final  String CONST_UNFILTEREDURLS	=	"Unfiltered URLs";
	public static final  String CONST_CATEGORYNAME	=	"Category Name";
	public static final  String CONST_REGULAREXPRESSIONS	=	"Regular Expressions";
	public static final  String CONST_FILTERWORDS	=	"Filter words";
	public static final  String CONST_ROLECONFIGURATION	=	"Role Configuration";
	public static final  String CONST_REPORTINGPERMISSIONS	=	"Reporting Permissions";
	public static final  String CONST_MANAGEDCLIENTS		=	"Managed Clients";
	public static final  String CONST_CUSTOMLDAPGROUPS	= 	"Custom LDAP Groups";
	public  final static String CONST_CLIENTTYPE_USER="user";
	public  final static String CONST_CLIENTTYPE_COMPUTER="computer";
	public  final static String CONST_CLIENTTYPE_NETWORK="network";
	public  final static String CONST_CLIENTTYPE_GROUP="group";
	public  final static String CONST_CLIENTTYPE_DOMAIN="domain";
	public  final static String CONST_RECATEGORIZEDURLS="Recategorized URLs";
	
	public static final String WSA_REALM_IDENTITY = "realm";
	public static final String APPLIENCE_ID_CWS = "4";
	
}
