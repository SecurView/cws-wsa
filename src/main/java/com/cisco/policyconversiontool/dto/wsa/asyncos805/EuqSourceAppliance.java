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
    "euqHost",
    "euqPort"
})
@XmlRootElement(name = "euq_source_appliance")
public class EuqSourceAppliance {

    @XmlElement(name = "euq_host", required = true)
    protected String euqHost;
    @XmlElement(name = "euq_port", required = true)
    protected String euqPort;

    /**
     * Gets the value of the euqHost property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEuqHost() {
        return euqHost;
    }

    /**
     * Sets the value of the euqHost property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEuqHost(String value) {
        this.euqHost = value;
    }

    /**
     * Gets the value of the euqPort property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEuqPort() {
        return euqPort;
    }

    /**
     * Sets the value of the euqPort property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEuqPort(String value) {
        this.euqPort = value;
    }

}
