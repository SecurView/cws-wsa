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
    "ldapServerName",
    "ldapServerPort",
    "ldapServerHostname",
    "ldapServerPass",
    "ldapServerUser",
    "ldapServerBase",
    "ldapServerAuthtype",
    "ldapServerUseSsl",
    "ldapServerCompat"
})
@XmlRootElement(name = "ldap_server")
public class LdapServer {

    @XmlElement(name = "ldap_server_name", required = true)
    protected String ldapServerName;
    @XmlElement(name = "ldap_server_port", required = true)
    protected String ldapServerPort;
    @XmlElement(name = "ldap_server_hostname", required = true)
    protected String ldapServerHostname;
    @XmlElement(name = "ldap_server_pass")
    protected String ldapServerPass;
    @XmlElement(name = "ldap_server_user")
    protected String ldapServerUser;
    @XmlElement(name = "ldap_server_base", required = true)
    protected String ldapServerBase;
    @XmlElement(name = "ldap_server_authtype", required = true)
    protected String ldapServerAuthtype;
    @XmlElement(name = "ldap_server_use_ssl")
    protected String ldapServerUseSsl;
    @XmlElement(name = "ldap_server_compat")
    protected LdapServerCompat ldapServerCompat;

    /**
     * Gets the value of the ldapServerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLdapServerName() {
        return ldapServerName;
    }

    /**
     * Sets the value of the ldapServerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLdapServerName(String value) {
        this.ldapServerName = value;
    }

    /**
     * Gets the value of the ldapServerPort property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLdapServerPort() {
        return ldapServerPort;
    }

    /**
     * Sets the value of the ldapServerPort property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLdapServerPort(String value) {
        this.ldapServerPort = value;
    }

    /**
     * Gets the value of the ldapServerHostname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLdapServerHostname() {
        return ldapServerHostname;
    }

    /**
     * Sets the value of the ldapServerHostname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLdapServerHostname(String value) {
        this.ldapServerHostname = value;
    }

    /**
     * Gets the value of the ldapServerPass property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLdapServerPass() {
        return ldapServerPass;
    }

    /**
     * Sets the value of the ldapServerPass property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLdapServerPass(String value) {
        this.ldapServerPass = value;
    }

    /**
     * Gets the value of the ldapServerUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLdapServerUser() {
        return ldapServerUser;
    }

    /**
     * Sets the value of the ldapServerUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLdapServerUser(String value) {
        this.ldapServerUser = value;
    }

    /**
     * Gets the value of the ldapServerBase property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLdapServerBase() {
        return ldapServerBase;
    }

    /**
     * Sets the value of the ldapServerBase property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLdapServerBase(String value) {
        this.ldapServerBase = value;
    }

    /**
     * Gets the value of the ldapServerAuthtype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLdapServerAuthtype() {
        return ldapServerAuthtype;
    }

    /**
     * Sets the value of the ldapServerAuthtype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLdapServerAuthtype(String value) {
        this.ldapServerAuthtype = value;
    }

    /**
     * Gets the value of the ldapServerUseSsl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLdapServerUseSsl() {
        return ldapServerUseSsl;
    }

    /**
     * Sets the value of the ldapServerUseSsl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLdapServerUseSsl(String value) {
        this.ldapServerUseSsl = value;
    }

    /**
     * Gets the value of the ldapServerCompat property.
     * 
     * @return
     *     possible object is
     *     {@link LdapServerCompat }
     *     
     */
    public LdapServerCompat getLdapServerCompat() {
        return ldapServerCompat;
    }

    /**
     * Sets the value of the ldapServerCompat property.
     * 
     * @param value
     *     allowed object is
     *     {@link LdapServerCompat }
     *     
     */
    public void setLdapServerCompat(LdapServerCompat value) {
        this.ldapServerCompat = value;
    }

}