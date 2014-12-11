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
    "periodicReportKey",
    "periodicReportDefId",
    "periodicReportArchive",
    "periodicReportCreationTimestamp",
    "periodicReportTitle",
    "periodicReportType",
    "periodicReportUserName",
    "periodicReportRecipients",
    "periodicReportOptions",
    "periodicReportDays",
    "periodicReportHours",
    "periodicReportMinutes",
    "periodicReportSeconds",
    "periodicReportWeekdays",
    "periodicReportMonths",
    "periodicReportYears"
})
@XmlRootElement(name = "periodic_report")
public class PeriodicReport {

    @XmlElement(name = "periodic_report_key", required = true)
    protected String periodicReportKey;
    @XmlElement(name = "periodic_report_def_id", required = true)
    protected String periodicReportDefId;
    @XmlElement(name = "periodic_report_archive", required = true)
    protected String periodicReportArchive;
    @XmlElement(name = "periodic_report_creation_timestamp", required = true)
    protected String periodicReportCreationTimestamp;
    @XmlElement(name = "periodic_report_title", required = true)
    protected String periodicReportTitle;
    @XmlElement(name = "periodic_report_type", required = true)
    protected String periodicReportType;
    @XmlElement(name = "periodic_report_user_name", required = true)
    protected String periodicReportUserName;
    @XmlElement(name = "periodic_report_recipients", required = true)
    protected PeriodicReportRecipients periodicReportRecipients;
    @XmlElement(name = "periodic_report_options", required = true)
    protected PeriodicReportOptions periodicReportOptions;
    @XmlElement(name = "periodic_report_days")
    protected PeriodicReportDays periodicReportDays;
    @XmlElement(name = "periodic_report_hours")
    protected PeriodicReportHours periodicReportHours;
    @XmlElement(name = "periodic_report_minutes")
    protected PeriodicReportMinutes periodicReportMinutes;
    @XmlElement(name = "periodic_report_seconds")
    protected PeriodicReportSeconds periodicReportSeconds;
    @XmlElement(name = "periodic_report_weekdays")
    protected PeriodicReportWeekdays periodicReportWeekdays;
    @XmlElement(name = "periodic_report_months")
    protected PeriodicReportMonths periodicReportMonths;
    @XmlElement(name = "periodic_report_years")
    protected PeriodicReportYears periodicReportYears;

    /**
     * Gets the value of the periodicReportKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPeriodicReportKey() {
        return periodicReportKey;
    }

    /**
     * Sets the value of the periodicReportKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPeriodicReportKey(String value) {
        this.periodicReportKey = value;
    }

    /**
     * Gets the value of the periodicReportDefId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPeriodicReportDefId() {
        return periodicReportDefId;
    }

    /**
     * Sets the value of the periodicReportDefId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPeriodicReportDefId(String value) {
        this.periodicReportDefId = value;
    }

    /**
     * Gets the value of the periodicReportArchive property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPeriodicReportArchive() {
        return periodicReportArchive;
    }

    /**
     * Sets the value of the periodicReportArchive property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPeriodicReportArchive(String value) {
        this.periodicReportArchive = value;
    }

    /**
     * Gets the value of the periodicReportCreationTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPeriodicReportCreationTimestamp() {
        return periodicReportCreationTimestamp;
    }

    /**
     * Sets the value of the periodicReportCreationTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPeriodicReportCreationTimestamp(String value) {
        this.periodicReportCreationTimestamp = value;
    }

    /**
     * Gets the value of the periodicReportTitle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPeriodicReportTitle() {
        return periodicReportTitle;
    }

    /**
     * Sets the value of the periodicReportTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPeriodicReportTitle(String value) {
        this.periodicReportTitle = value;
    }

    /**
     * Gets the value of the periodicReportType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPeriodicReportType() {
        return periodicReportType;
    }

    /**
     * Sets the value of the periodicReportType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPeriodicReportType(String value) {
        this.periodicReportType = value;
    }

    /**
     * Gets the value of the periodicReportUserName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPeriodicReportUserName() {
        return periodicReportUserName;
    }

    /**
     * Sets the value of the periodicReportUserName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPeriodicReportUserName(String value) {
        this.periodicReportUserName = value;
    }

    /**
     * Gets the value of the periodicReportRecipients property.
     * 
     * @return
     *     possible object is
     *     {@link PeriodicReportRecipients }
     *     
     */
    public PeriodicReportRecipients getPeriodicReportRecipients() {
        return periodicReportRecipients;
    }

    /**
     * Sets the value of the periodicReportRecipients property.
     * 
     * @param value
     *     allowed object is
     *     {@link PeriodicReportRecipients }
     *     
     */
    public void setPeriodicReportRecipients(PeriodicReportRecipients value) {
        this.periodicReportRecipients = value;
    }

    /**
     * Gets the value of the periodicReportOptions property.
     * 
     * @return
     *     possible object is
     *     {@link PeriodicReportOptions }
     *     
     */
    public PeriodicReportOptions getPeriodicReportOptions() {
        return periodicReportOptions;
    }

    /**
     * Sets the value of the periodicReportOptions property.
     * 
     * @param value
     *     allowed object is
     *     {@link PeriodicReportOptions }
     *     
     */
    public void setPeriodicReportOptions(PeriodicReportOptions value) {
        this.periodicReportOptions = value;
    }

    /**
     * Gets the value of the periodicReportDays property.
     * 
     * @return
     *     possible object is
     *     {@link PeriodicReportDays }
     *     
     */
    public PeriodicReportDays getPeriodicReportDays() {
        return periodicReportDays;
    }

    /**
     * Sets the value of the periodicReportDays property.
     * 
     * @param value
     *     allowed object is
     *     {@link PeriodicReportDays }
     *     
     */
    public void setPeriodicReportDays(PeriodicReportDays value) {
        this.periodicReportDays = value;
    }

    /**
     * Gets the value of the periodicReportHours property.
     * 
     * @return
     *     possible object is
     *     {@link PeriodicReportHours }
     *     
     */
    public PeriodicReportHours getPeriodicReportHours() {
        return periodicReportHours;
    }

    /**
     * Sets the value of the periodicReportHours property.
     * 
     * @param value
     *     allowed object is
     *     {@link PeriodicReportHours }
     *     
     */
    public void setPeriodicReportHours(PeriodicReportHours value) {
        this.periodicReportHours = value;
    }

    /**
     * Gets the value of the periodicReportMinutes property.
     * 
     * @return
     *     possible object is
     *     {@link PeriodicReportMinutes }
     *     
     */
    public PeriodicReportMinutes getPeriodicReportMinutes() {
        return periodicReportMinutes;
    }

    /**
     * Sets the value of the periodicReportMinutes property.
     * 
     * @param value
     *     allowed object is
     *     {@link PeriodicReportMinutes }
     *     
     */
    public void setPeriodicReportMinutes(PeriodicReportMinutes value) {
        this.periodicReportMinutes = value;
    }

    /**
     * Gets the value of the periodicReportSeconds property.
     * 
     * @return
     *     possible object is
     *     {@link PeriodicReportSeconds }
     *     
     */
    public PeriodicReportSeconds getPeriodicReportSeconds() {
        return periodicReportSeconds;
    }

    /**
     * Sets the value of the periodicReportSeconds property.
     * 
     * @param value
     *     allowed object is
     *     {@link PeriodicReportSeconds }
     *     
     */
    public void setPeriodicReportSeconds(PeriodicReportSeconds value) {
        this.periodicReportSeconds = value;
    }

    /**
     * Gets the value of the periodicReportWeekdays property.
     * 
     * @return
     *     possible object is
     *     {@link PeriodicReportWeekdays }
     *     
     */
    public PeriodicReportWeekdays getPeriodicReportWeekdays() {
        return periodicReportWeekdays;
    }

    /**
     * Sets the value of the periodicReportWeekdays property.
     * 
     * @param value
     *     allowed object is
     *     {@link PeriodicReportWeekdays }
     *     
     */
    public void setPeriodicReportWeekdays(PeriodicReportWeekdays value) {
        this.periodicReportWeekdays = value;
    }

    /**
     * Gets the value of the periodicReportMonths property.
     * 
     * @return
     *     possible object is
     *     {@link PeriodicReportMonths }
     *     
     */
    public PeriodicReportMonths getPeriodicReportMonths() {
        return periodicReportMonths;
    }

    /**
     * Sets the value of the periodicReportMonths property.
     * 
     * @param value
     *     allowed object is
     *     {@link PeriodicReportMonths }
     *     
     */
    public void setPeriodicReportMonths(PeriodicReportMonths value) {
        this.periodicReportMonths = value;
    }

    /**
     * Gets the value of the periodicReportYears property.
     * 
     * @return
     *     possible object is
     *     {@link PeriodicReportYears }
     *     
     */
    public PeriodicReportYears getPeriodicReportYears() {
        return periodicReportYears;
    }

    /**
     * Sets the value of the periodicReportYears property.
     * 
     * @param value
     *     allowed object is
     *     {@link PeriodicReportYears }
     *     
     */
    public void setPeriodicReportYears(PeriodicReportYears value) {
        this.periodicReportYears = value;
    }

}