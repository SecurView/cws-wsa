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
    "proxAclRulesUrlId",
    "proxAclRulesUrlUuids"
})
@XmlRootElement(name = "prox_acl_rules_url_tag")
public class ProxAclRulesUrlTag {

    @XmlElement(name = "prox_acl_rules_url_id", required = true)
    protected String proxAclRulesUrlId;
    @XmlElement(name = "prox_acl_rules_url_uuids")
    protected ProxAclRulesUrlUuids proxAclRulesUrlUuids;

    /**
     * Gets the value of the proxAclRulesUrlId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProxAclRulesUrlId() {
        return proxAclRulesUrlId;
    }

    /**
     * Sets the value of the proxAclRulesUrlId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProxAclRulesUrlId(String value) {
        this.proxAclRulesUrlId = value;
    }

    /**
     * Gets the value of the proxAclRulesUrlUuids property.
     * 
     * @return
     *     possible object is
     *     {@link ProxAclRulesUrlUuids }
     *     
     */
    public ProxAclRulesUrlUuids getProxAclRulesUrlUuids() {
        return proxAclRulesUrlUuids;
    }

    /**
     * Sets the value of the proxAclRulesUrlUuids property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProxAclRulesUrlUuids }
     *     
     */
    public void setProxAclRulesUrlUuids(ProxAclRulesUrlUuids value) {
        this.proxAclRulesUrlUuids = value;
    }

}
