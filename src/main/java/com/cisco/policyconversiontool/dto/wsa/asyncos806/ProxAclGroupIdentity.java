//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.11.26 at 05:46:49 PM IST 
//


package com.cisco.policyconversiontool.dto.wsa.asyncos806;

import java.io.Serializable;
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
    "proxAclGroupAuthGroups",
    "proxAclGroupUsernames",
    "proxAclGroupAuthRealm",
    "proxAclGroupForGuestsOnly",
    "proxAclGroupOrder",
    "proxAclGroupIdentityName"
})
@XmlRootElement(name = "prox_acl_group_identity")
public class ProxAclGroupIdentity  implements Serializable {
    @XmlElement(name = "prox_acl_group_auth_groups")
    protected ProxAclGroupAuthGroups proxAclGroupAuthGroups;
    @XmlElement(name = "prox_acl_group_usernames")
    protected ProxAclGroupUsernames proxAclGroupUsernames;
    @XmlElement(name = "prox_acl_group_auth_realm")
    protected String proxAclGroupAuthRealm;
    @XmlElement(name = "prox_acl_group_for_guests_only")
    protected String proxAclGroupForGuestsOnly;
    @XmlElement(name = "prox_acl_group_order")
    protected String proxAclGroupOrder;
    @XmlElement(name = "prox_acl_group_identity_name", required = true)
    protected String proxAclGroupIdentityName;

    /**
     * Gets the value of the proxAclGroupAuthGroups property.
     * 
     * @return
     *     possible object is
     *     {@link ProxAclGroupAuthGroups }
     *     
     */
    public ProxAclGroupAuthGroups getProxAclGroupAuthGroups() {
        return proxAclGroupAuthGroups;
    }

    /**
     * Sets the value of the proxAclGroupAuthGroups property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProxAclGroupAuthGroups }
     *     
     */
    public void setProxAclGroupAuthGroups(ProxAclGroupAuthGroups value) {
        this.proxAclGroupAuthGroups = value;
    }

    /**
     * Gets the value of the proxAclGroupUsernames property.
     * 
     * @return
     *     possible object is
     *     {@link ProxAclGroupUsernames }
     *     
     */
    public ProxAclGroupUsernames getProxAclGroupUsernames() {
        return proxAclGroupUsernames;
    }

    /**
     * Sets the value of the proxAclGroupUsernames property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProxAclGroupUsernames }
     *     
     */
    public void setProxAclGroupUsernames(ProxAclGroupUsernames value) {
        this.proxAclGroupUsernames = value;
    }

    /**
     * Gets the value of the proxAclGroupAuthRealm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProxAclGroupAuthRealm() {
        return proxAclGroupAuthRealm;
    }

    /**
     * Sets the value of the proxAclGroupAuthRealm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProxAclGroupAuthRealm(String value) {
        this.proxAclGroupAuthRealm = value;
    }

    /**
     * Gets the value of the proxAclGroupForGuestsOnly property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProxAclGroupForGuestsOnly() {
        return proxAclGroupForGuestsOnly;
    }

    /**
     * Sets the value of the proxAclGroupForGuestsOnly property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProxAclGroupForGuestsOnly(String value) {
        this.proxAclGroupForGuestsOnly = value;
    }

    /**
     * Gets the value of the proxAclGroupOrder property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProxAclGroupOrder() {
        return proxAclGroupOrder;
    }

    /**
     * Sets the value of the proxAclGroupOrder property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProxAclGroupOrder(String value) {
        this.proxAclGroupOrder = value;
    }

    /**
     * Gets the value of the proxAclGroupIdentityName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProxAclGroupIdentityName() {
        return proxAclGroupIdentityName;
    }

    /**
     * Sets the value of the proxAclGroupIdentityName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProxAclGroupIdentityName(String value) {
        this.proxAclGroupIdentityName = value;
    }

}