//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.11.15 at 08:28:02 PM IST 
//


package com.cisco.policyconversiontool.dto.wsa.asyncos805;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "dnsHostprefOverrideDomain",
    "dnsHostprefOverridePref"
})
@XmlRootElement(name = "dns_hostpref_override")
public class DnsHostprefOverride {

    @XmlElement(name = "dns_hostpref_override_domain", required = true)
    protected String dnsHostprefOverrideDomain;
    @XmlElement(name = "dns_hostpref_override_pref", required = true)
    protected String dnsHostprefOverridePref;

    /**
     * Gets the value of the dnsHostprefOverrideDomain property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDnsHostprefOverrideDomain() {
        return dnsHostprefOverrideDomain;
    }

    /**
     * Sets the value of the dnsHostprefOverrideDomain property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDnsHostprefOverrideDomain(String value) {
        this.dnsHostprefOverrideDomain = value;
    }

    /**
     * Gets the value of the dnsHostprefOverridePref property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDnsHostprefOverridePref() {
        return dnsHostprefOverridePref;
    }

    /**
     * Sets the value of the dnsHostprefOverridePref property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDnsHostprefOverridePref(String value) {
        this.dnsHostprefOverridePref = value;
    }

}
