//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.11.26 at 05:46:49 PM IST 
//


package com.cisco.policyconversiontool.dto.wsa.asyncos806;

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
    "userReportReportDefSectionId"
})
@XmlRootElement(name = "user_report_small_sections")
public class UserReportSmallSections {

    @XmlElement(name = "user_report_report_def_section_id", required = true)
    protected List<UserReportReportDefSectionId> userReportReportDefSectionId;

    /**
     * Gets the value of the userReportReportDefSectionId property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the userReportReportDefSectionId property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUserReportReportDefSectionId().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UserReportReportDefSectionId }
     * 
     * 
     */
    public List<UserReportReportDefSectionId> getUserReportReportDefSectionId() {
        if (userReportReportDefSectionId == null) {
            userReportReportDefSectionId = new ArrayList<UserReportReportDefSectionId>();
        }
        return this.userReportReportDefSectionId;
    }

}