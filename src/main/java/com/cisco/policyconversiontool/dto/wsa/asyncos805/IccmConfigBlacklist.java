//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.11.15 at 08:28:02 PM IST 
//


package com.cisco.policyconversiontool.dto.wsa.asyncos805;

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
    "iccmConfigBlacklistEntry"
})
@XmlRootElement(name = "iccm_config_blacklist")
public class IccmConfigBlacklist {

    @XmlElement(name = "iccm_config_blacklist_entry")
    protected List<IccmConfigBlacklistEntry> iccmConfigBlacklistEntry;

    /**
     * Gets the value of the iccmConfigBlacklistEntry property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the iccmConfigBlacklistEntry property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIccmConfigBlacklistEntry().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IccmConfigBlacklistEntry }
     * 
     * 
     */
    public List<IccmConfigBlacklistEntry> getIccmConfigBlacklistEntry() {
        if (iccmConfigBlacklistEntry == null) {
            iccmConfigBlacklistEntry = new ArrayList<IccmConfigBlacklistEntry>();
        }
        return this.iccmConfigBlacklistEntry;
    }

}
