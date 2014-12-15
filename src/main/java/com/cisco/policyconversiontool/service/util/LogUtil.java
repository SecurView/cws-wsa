package com.cisco.policyconversiontool.service.util;

public class LogUtil {
	public static String getHeader(String hName)
	{
		int length = 80;
		length -=hName.length();
		String temp = "";
		for(int i = 0; i< length/2 ; i++)
		{
			temp += "*";
		}
		return "!"+temp+"  " + hName + "  "+(length%2==0?"":" ") +temp+"!";
	}
	public static void main(String sr[])
	{
		System.out.println(getHeader("hit thislidlk"));
		System.out.println(getHeader("hit"));
		System.out.println(getHeader("hit aksjdfh aksjdf thislidlk"));
		System.out.println(getHeader("hit cmmwoie owieuri wierioweroi wierio soidr"));
		System.out.println(getHeader("h"));
	}

}
