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
    "domainExceptionMember",
    "domainExceptionPolicy",
    "domainExceptionSmtpCode",
    "domainExceptionSmtpResponse"
})
@XmlRootElement(name = "domain_exception")
public class DomainException {

    @XmlElement(name = "domain_exception_member", required = true)
    protected String domainExceptionMember;
    @XmlElement(name = "domain_exception_policy", required = true)
    protected String domainExceptionPolicy;
    @XmlElement(name = "domain_exception_smtp_code")
    protected String domainExceptionSmtpCode;
    @XmlElement(name = "domain_exception_smtp_response")
    protected String domainExceptionSmtpResponse;

    /**
     * Gets the value of the domainExceptionMember property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDomainExceptionMember() {
        return domainExceptionMember;
    }

    /**
     * Sets the value of the domainExceptionMember property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDomainExceptionMember(String value) {
        this.domainExceptionMember = value;
    }

    /**
     * Gets the value of the domainExceptionPolicy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDomainExceptionPolicy() {
        return domainExceptionPolicy;
    }

    /**
     * Sets the value of the domainExceptionPolicy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDomainExceptionPolicy(String value) {
        this.domainExceptionPolicy = value;
    }

    /**
     * Gets the value of the domainExceptionSmtpCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDomainExceptionSmtpCode() {
        return domainExceptionSmtpCode;
    }

    /**
     * Sets the value of the domainExceptionSmtpCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDomainExceptionSmtpCode(String value) {
        this.domainExceptionSmtpCode = value;
    }

    /**
     * Gets the value of the domainExceptionSmtpResponse property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDomainExceptionSmtpResponse() {
        return domainExceptionSmtpResponse;
    }

    /**
     * Sets the value of the domainExceptionSmtpResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDomainExceptionSmtpResponse(String value) {
        this.domainExceptionSmtpResponse = value;
    }

}