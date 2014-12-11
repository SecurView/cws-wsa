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
    "proxConfigAuthRealmAdDomain",
    "proxConfigAuthRealmAdNetbiosDomain",
    "proxConfigAuthRealmAdServers",
    "proxConfigAuthRealmAdInterfaces",
    "proxConfigAuthRealmAdPassword",
    "proxConfigAuthRealmAdUsername",
    "proxConfigAuthRealmAdSecurityMode",
    "proxConfigAuthRealmAdLocation",
    "proxConfigAuthRealmAdUseSecureLdap",
    "proxConfigAuthRealmAdPageSize",
    "proxConfigAuthRealmAdEnableSsoAd",
    "proxConfigAuthRealmAdPrimaryAgentAddress",
    "proxConfigAuthRealmAdPrimaryAgentSecret",
    "proxConfigAuthRealmAdBackupAgentAddress",
    "proxConfigAuthRealmAdBackupAgentSecret"
})
@XmlRootElement(name = "prox_config_auth_realm_ad")
public class ProxConfigAuthRealmAd {

    @XmlElement(name = "prox_config_auth_realm_ad_domain", required = true)
    protected String proxConfigAuthRealmAdDomain;
    @XmlElement(name = "prox_config_auth_realm_ad_netbios_domain", required = true)
    protected String proxConfigAuthRealmAdNetbiosDomain;
    @XmlElement(name = "prox_config_auth_realm_ad_servers", required = true)
    protected ProxConfigAuthRealmAdServers proxConfigAuthRealmAdServers;
    @XmlElement(name = "prox_config_auth_realm_ad_interfaces", required = true)
    protected String proxConfigAuthRealmAdInterfaces;
    @XmlElement(name = "prox_config_auth_realm_ad_password")
    protected String proxConfigAuthRealmAdPassword;
    @XmlElement(name = "prox_config_auth_realm_ad_username")
    protected String proxConfigAuthRealmAdUsername;
    @XmlElement(name = "prox_config_auth_realm_ad_security_mode", required = true)
    protected String proxConfigAuthRealmAdSecurityMode;
    @XmlElement(name = "prox_config_auth_realm_ad_location", required = true)
    protected String proxConfigAuthRealmAdLocation;
    @XmlElement(name = "prox_config_auth_realm_ad_use_secure_ldap", required = true)
    protected String proxConfigAuthRealmAdUseSecureLdap;
    @XmlElement(name = "prox_config_auth_realm_ad_page_size", required = true)
    protected String proxConfigAuthRealmAdPageSize;
    @XmlElement(name = "prox_config_auth_realm_ad_enable_sso_ad", required = true)
    protected String proxConfigAuthRealmAdEnableSsoAd;
    @XmlElement(name = "prox_config_auth_realm_ad_primary_agent_address")
    protected String proxConfigAuthRealmAdPrimaryAgentAddress;
    @XmlElement(name = "prox_config_auth_realm_ad_primary_agent_secret")
    protected String proxConfigAuthRealmAdPrimaryAgentSecret;
    @XmlElement(name = "prox_config_auth_realm_ad_backup_agent_address")
    protected String proxConfigAuthRealmAdBackupAgentAddress;
    @XmlElement(name = "prox_config_auth_realm_ad_backup_agent_secret")
    protected String proxConfigAuthRealmAdBackupAgentSecret;

    /**
     * Gets the value of the proxConfigAuthRealmAdDomain property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProxConfigAuthRealmAdDomain() {
        return proxConfigAuthRealmAdDomain;
    }

    /**
     * Sets the value of the proxConfigAuthRealmAdDomain property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProxConfigAuthRealmAdDomain(String value) {
        this.proxConfigAuthRealmAdDomain = value;
    }

    /**
     * Gets the value of the proxConfigAuthRealmAdNetbiosDomain property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProxConfigAuthRealmAdNetbiosDomain() {
        return proxConfigAuthRealmAdNetbiosDomain;
    }

    /**
     * Sets the value of the proxConfigAuthRealmAdNetbiosDomain property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProxConfigAuthRealmAdNetbiosDomain(String value) {
        this.proxConfigAuthRealmAdNetbiosDomain = value;
    }

    /**
     * Gets the value of the proxConfigAuthRealmAdServers property.
     * 
     * @return
     *     possible object is
     *     {@link ProxConfigAuthRealmAdServers }
     *     
     */
    public ProxConfigAuthRealmAdServers getProxConfigAuthRealmAdServers() {
        return proxConfigAuthRealmAdServers;
    }

    /**
     * Sets the value of the proxConfigAuthRealmAdServers property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProxConfigAuthRealmAdServers }
     *     
     */
    public void setProxConfigAuthRealmAdServers(ProxConfigAuthRealmAdServers value) {
        this.proxConfigAuthRealmAdServers = value;
    }

    /**
     * Gets the value of the proxConfigAuthRealmAdInterfaces property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProxConfigAuthRealmAdInterfaces() {
        return proxConfigAuthRealmAdInterfaces;
    }

    /**
     * Sets the value of the proxConfigAuthRealmAdInterfaces property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProxConfigAuthRealmAdInterfaces(String value) {
        this.proxConfigAuthRealmAdInterfaces = value;
    }

    /**
     * Gets the value of the proxConfigAuthRealmAdPassword property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProxConfigAuthRealmAdPassword() {
        return proxConfigAuthRealmAdPassword;
    }

    /**
     * Sets the value of the proxConfigAuthRealmAdPassword property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProxConfigAuthRealmAdPassword(String value) {
        this.proxConfigAuthRealmAdPassword = value;
    }

    /**
     * Gets the value of the proxConfigAuthRealmAdUsername property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProxConfigAuthRealmAdUsername() {
        return proxConfigAuthRealmAdUsername;
    }

    /**
     * Sets the value of the proxConfigAuthRealmAdUsername property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProxConfigAuthRealmAdUsername(String value) {
        this.proxConfigAuthRealmAdUsername = value;
    }

    /**
     * Gets the value of the proxConfigAuthRealmAdSecurityMode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProxConfigAuthRealmAdSecurityMode() {
        return proxConfigAuthRealmAdSecurityMode;
    }

    /**
     * Sets the value of the proxConfigAuthRealmAdSecurityMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProxConfigAuthRealmAdSecurityMode(String value) {
        this.proxConfigAuthRealmAdSecurityMode = value;
    }

    /**
     * Gets the value of the proxConfigAuthRealmAdLocation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProxConfigAuthRealmAdLocation() {
        return proxConfigAuthRealmAdLocation;
    }

    /**
     * Sets the value of the proxConfigAuthRealmAdLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProxConfigAuthRealmAdLocation(String value) {
        this.proxConfigAuthRealmAdLocation = value;
    }

    /**
     * Gets the value of the proxConfigAuthRealmAdUseSecureLdap property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProxConfigAuthRealmAdUseSecureLdap() {
        return proxConfigAuthRealmAdUseSecureLdap;
    }

    /**
     * Sets the value of the proxConfigAuthRealmAdUseSecureLdap property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProxConfigAuthRealmAdUseSecureLdap(String value) {
        this.proxConfigAuthRealmAdUseSecureLdap = value;
    }

    /**
     * Gets the value of the proxConfigAuthRealmAdPageSize property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProxConfigAuthRealmAdPageSize() {
        return proxConfigAuthRealmAdPageSize;
    }

    /**
     * Sets the value of the proxConfigAuthRealmAdPageSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProxConfigAuthRealmAdPageSize(String value) {
        this.proxConfigAuthRealmAdPageSize = value;
    }

    /**
     * Gets the value of the proxConfigAuthRealmAdEnableSsoAd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProxConfigAuthRealmAdEnableSsoAd() {
        return proxConfigAuthRealmAdEnableSsoAd;
    }

    /**
     * Sets the value of the proxConfigAuthRealmAdEnableSsoAd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProxConfigAuthRealmAdEnableSsoAd(String value) {
        this.proxConfigAuthRealmAdEnableSsoAd = value;
    }

    /**
     * Gets the value of the proxConfigAuthRealmAdPrimaryAgentAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProxConfigAuthRealmAdPrimaryAgentAddress() {
        return proxConfigAuthRealmAdPrimaryAgentAddress;
    }

    /**
     * Sets the value of the proxConfigAuthRealmAdPrimaryAgentAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProxConfigAuthRealmAdPrimaryAgentAddress(String value) {
        this.proxConfigAuthRealmAdPrimaryAgentAddress = value;
    }

    /**
     * Gets the value of the proxConfigAuthRealmAdPrimaryAgentSecret property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProxConfigAuthRealmAdPrimaryAgentSecret() {
        return proxConfigAuthRealmAdPrimaryAgentSecret;
    }

    /**
     * Sets the value of the proxConfigAuthRealmAdPrimaryAgentSecret property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProxConfigAuthRealmAdPrimaryAgentSecret(String value) {
        this.proxConfigAuthRealmAdPrimaryAgentSecret = value;
    }

    /**
     * Gets the value of the proxConfigAuthRealmAdBackupAgentAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProxConfigAuthRealmAdBackupAgentAddress() {
        return proxConfigAuthRealmAdBackupAgentAddress;
    }

    /**
     * Sets the value of the proxConfigAuthRealmAdBackupAgentAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProxConfigAuthRealmAdBackupAgentAddress(String value) {
        this.proxConfigAuthRealmAdBackupAgentAddress = value;
    }

    /**
     * Gets the value of the proxConfigAuthRealmAdBackupAgentSecret property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProxConfigAuthRealmAdBackupAgentSecret() {
        return proxConfigAuthRealmAdBackupAgentSecret;
    }

    /**
     * Sets the value of the proxConfigAuthRealmAdBackupAgentSecret property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProxConfigAuthRealmAdBackupAgentSecret(String value) {
        this.proxConfigAuthRealmAdBackupAgentSecret = value;
    }

}