//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.11.26 at 05:46:49 PM IST 
//


package com.cisco.policyconversiontool.dto.wsa.asyncos806;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
    "proxAclGroupAuthScheme"
})
@XmlRootElement(name = "prox_acl_group_auth_schemes")
public class ProxAclGroupAuthSchemes  implements Serializable {
    @XmlElement(name = "prox_acl_group_auth_scheme")
    protected List<ProxAclGroupAuthScheme> proxAclGroupAuthScheme;

    /**
     * Gets the value of the proxAclGroupAuthScheme property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the proxAclGroupAuthScheme property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProxAclGroupAuthScheme().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProxAclGroupAuthScheme }
     * 
     * 
     */
    public List<ProxAclGroupAuthScheme> getProxAclGroupAuthScheme() {
        if (proxAclGroupAuthScheme == null) {
            proxAclGroupAuthScheme = new ArrayList<ProxAclGroupAuthScheme>();
        }
        return this.proxAclGroupAuthScheme;
    }

}
