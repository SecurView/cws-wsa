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
    "sendNotifyFromDisplayName",
    "sendNotifyFromUsername",
    "sendNotifyFromDomain"
})
@XmlRootElement(name = "send_notify_from")
public class SendNotifyFrom {

    @XmlElement(name = "send_notify_from_display_name", required = true)
    protected String sendNotifyFromDisplayName;
    @XmlElement(name = "send_notify_from_username", required = true)
    protected String sendNotifyFromUsername;
    @XmlElement(name = "send_notify_from_domain", required = true)
    protected String sendNotifyFromDomain;

    /**
     * Gets the value of the sendNotifyFromDisplayName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSendNotifyFromDisplayName() {
        return sendNotifyFromDisplayName;
    }

    /**
     * Sets the value of the sendNotifyFromDisplayName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSendNotifyFromDisplayName(String value) {
        this.sendNotifyFromDisplayName = value;
    }

    /**
     * Gets the value of the sendNotifyFromUsername property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSendNotifyFromUsername() {
        return sendNotifyFromUsername;
    }

    /**
     * Sets the value of the sendNotifyFromUsername property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSendNotifyFromUsername(String value) {
        this.sendNotifyFromUsername = value;
    }

    /**
     * Gets the value of the sendNotifyFromDomain property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSendNotifyFromDomain() {
        return sendNotifyFromDomain;
    }

    /**
     * Sets the value of the sendNotifyFromDomain property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSendNotifyFromDomain(String value) {
        this.sendNotifyFromDomain = value;
    }

}