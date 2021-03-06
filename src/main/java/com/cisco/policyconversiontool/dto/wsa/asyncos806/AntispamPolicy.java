//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.11.26 at 05:46:49 PM IST 
//


package com.cisco.policyconversiontool.dto.wsa.asyncos806;

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
    "policyStatus",
    "antispamEngine",
    "casePolicy",
    "antispam",
    "suspectspam"
})
@XmlRootElement(name = "antispam_policy")
public class AntispamPolicy {

    @XmlElement(name = "policy_status", required = true)
    protected String policyStatus;
    @XmlElement(name = "antispam_engine")
    protected AntispamEngine antispamEngine;
    @XmlElement(name = "case_policy")
    protected CasePolicy casePolicy;
    protected Antispam antispam;
    protected Suspectspam suspectspam;

    /**
     * Gets the value of the policyStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPolicyStatus() {
        return policyStatus;
    }

    /**
     * Sets the value of the policyStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPolicyStatus(String value) {
        this.policyStatus = value;
    }

    /**
     * Gets the value of the antispamEngine property.
     * 
     * @return
     *     possible object is
     *     {@link AntispamEngine }
     *     
     */
    public AntispamEngine getAntispamEngine() {
        return antispamEngine;
    }

    /**
     * Sets the value of the antispamEngine property.
     * 
     * @param value
     *     allowed object is
     *     {@link AntispamEngine }
     *     
     */
    public void setAntispamEngine(AntispamEngine value) {
        this.antispamEngine = value;
    }

    /**
     * Gets the value of the casePolicy property.
     * 
     * @return
     *     possible object is
     *     {@link CasePolicy }
     *     
     */
    public CasePolicy getCasePolicy() {
        return casePolicy;
    }

    /**
     * Sets the value of the casePolicy property.
     * 
     * @param value
     *     allowed object is
     *     {@link CasePolicy }
     *     
     */
    public void setCasePolicy(CasePolicy value) {
        this.casePolicy = value;
    }

    /**
     * Gets the value of the antispam property.
     * 
     * @return
     *     possible object is
     *     {@link Antispam }
     *     
     */
    public Antispam getAntispam() {
        return antispam;
    }

    /**
     * Sets the value of the antispam property.
     * 
     * @param value
     *     allowed object is
     *     {@link Antispam }
     *     
     */
    public void setAntispam(Antispam value) {
        this.antispam = value;
    }

    /**
     * Gets the value of the suspectspam property.
     * 
     * @return
     *     possible object is
     *     {@link Suspectspam }
     *     
     */
    public Suspectspam getSuspectspam() {
        return suspectspam;
    }

    /**
     * Sets the value of the suspectspam property.
     * 
     * @param value
     *     allowed object is
     *     {@link Suspectspam }
     *     
     */
    public void setSuspectspam(Suspectspam value) {
        this.suspectspam = value;
    }

}
