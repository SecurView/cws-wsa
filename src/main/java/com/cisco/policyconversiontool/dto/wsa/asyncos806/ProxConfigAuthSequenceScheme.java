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
    "proxConfigAuthSequenceSchemeName",
    "proxConfigAuthSequenceSchemeRealms"
})
@XmlRootElement(name = "prox_config_auth_sequence_scheme")
public class ProxConfigAuthSequenceScheme {

    @XmlElement(name = "prox_config_auth_sequence_scheme_name")
    protected String proxConfigAuthSequenceSchemeName;
    @XmlElement(name = "prox_config_auth_sequence_scheme_realms")
    protected ProxConfigAuthSequenceSchemeRealms proxConfigAuthSequenceSchemeRealms;

    /**
     * Gets the value of the proxConfigAuthSequenceSchemeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProxConfigAuthSequenceSchemeName() {
        return proxConfigAuthSequenceSchemeName;
    }

    /**
     * Sets the value of the proxConfigAuthSequenceSchemeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProxConfigAuthSequenceSchemeName(String value) {
        this.proxConfigAuthSequenceSchemeName = value;
    }

    /**
     * Gets the value of the proxConfigAuthSequenceSchemeRealms property.
     * 
     * @return
     *     possible object is
     *     {@link ProxConfigAuthSequenceSchemeRealms }
     *     
     */
    public ProxConfigAuthSequenceSchemeRealms getProxConfigAuthSequenceSchemeRealms() {
        return proxConfigAuthSequenceSchemeRealms;
    }

    /**
     * Sets the value of the proxConfigAuthSequenceSchemeRealms property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProxConfigAuthSequenceSchemeRealms }
     *     
     */
    public void setProxConfigAuthSequenceSchemeRealms(ProxConfigAuthSequenceSchemeRealms value) {
        this.proxConfigAuthSequenceSchemeRealms = value;
    }

}
