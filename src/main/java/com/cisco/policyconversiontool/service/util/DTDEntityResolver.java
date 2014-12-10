package com.cisco.policyconversiontool.service.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class DTDEntityResolver implements EntityResolver{
	private File file = null;
	 public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
         return new InputSource(new FileInputStream(file));
     }
	public void setFile(File file) {
		this.file = file;
	}
}