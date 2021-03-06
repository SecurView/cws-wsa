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
    "periodicReportFormat",
    "periodicReportLang",
    "periodicReportHostId",
    "periodicReportLogo",
    "periodicReportDaysInclude",
    "periodicReportRows",
    "periodicReportFilter",
    "periodicReportDuration",
    "periodicReportMaxItems",
    "periodicReportSortColumns",
    "periodicReportChartColumns"
})
@XmlRootElement(name = "periodic_report_options")
public class PeriodicReportOptions {

    @XmlElement(name = "periodic_report_format")
    protected String periodicReportFormat;
    @XmlElement(name = "periodic_report_lang")
    protected String periodicReportLang;
    @XmlElement(name = "periodic_report_host_id")
    protected String periodicReportHostId;
    @XmlElement(name = "periodic_report_logo")
    protected String periodicReportLogo;
    @XmlElement(name = "periodic_report_days_include")
    protected String periodicReportDaysInclude;
    @XmlElement(name = "periodic_report_rows")
    protected String periodicReportRows;
    @XmlElement(name = "periodic_report_filter")
    protected String periodicReportFilter;
    @XmlElement(name = "periodic_report_duration")
    protected String periodicReportDuration;
    @XmlElement(name = "periodic_report_max_items")
    protected String periodicReportMaxItems;
    @XmlElement(name = "periodic_report_sort_columns")
    protected PeriodicReportSortColumns periodicReportSortColumns;
    @XmlElement(name = "periodic_report_chart_columns")
    protected PeriodicReportChartColumns periodicReportChartColumns;

    /**
     * Gets the value of the periodicReportFormat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPeriodicReportFormat() {
        return periodicReportFormat;
    }

    /**
     * Sets the value of the periodicReportFormat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPeriodicReportFormat(String value) {
        this.periodicReportFormat = value;
    }

    /**
     * Gets the value of the periodicReportLang property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPeriodicReportLang() {
        return periodicReportLang;
    }

    /**
     * Sets the value of the periodicReportLang property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPeriodicReportLang(String value) {
        this.periodicReportLang = value;
    }

    /**
     * Gets the value of the periodicReportHostId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPeriodicReportHostId() {
        return periodicReportHostId;
    }

    /**
     * Sets the value of the periodicReportHostId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPeriodicReportHostId(String value) {
        this.periodicReportHostId = value;
    }

    /**
     * Gets the value of the periodicReportLogo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPeriodicReportLogo() {
        return periodicReportLogo;
    }

    /**
     * Sets the value of the periodicReportLogo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPeriodicReportLogo(String value) {
        this.periodicReportLogo = value;
    }

    /**
     * Gets the value of the periodicReportDaysInclude property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPeriodicReportDaysInclude() {
        return periodicReportDaysInclude;
    }

    /**
     * Sets the value of the periodicReportDaysInclude property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPeriodicReportDaysInclude(String value) {
        this.periodicReportDaysInclude = value;
    }

    /**
     * Gets the value of the periodicReportRows property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPeriodicReportRows() {
        return periodicReportRows;
    }

    /**
     * Sets the value of the periodicReportRows property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPeriodicReportRows(String value) {
        this.periodicReportRows = value;
    }

    /**
     * Gets the value of the periodicReportFilter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPeriodicReportFilter() {
        return periodicReportFilter;
    }

    /**
     * Sets the value of the periodicReportFilter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPeriodicReportFilter(String value) {
        this.periodicReportFilter = value;
    }

    /**
     * Gets the value of the periodicReportDuration property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPeriodicReportDuration() {
        return periodicReportDuration;
    }

    /**
     * Sets the value of the periodicReportDuration property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPeriodicReportDuration(String value) {
        this.periodicReportDuration = value;
    }

    /**
     * Gets the value of the periodicReportMaxItems property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPeriodicReportMaxItems() {
        return periodicReportMaxItems;
    }

    /**
     * Sets the value of the periodicReportMaxItems property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPeriodicReportMaxItems(String value) {
        this.periodicReportMaxItems = value;
    }

    /**
     * Gets the value of the periodicReportSortColumns property.
     * 
     * @return
     *     possible object is
     *     {@link PeriodicReportSortColumns }
     *     
     */
    public PeriodicReportSortColumns getPeriodicReportSortColumns() {
        return periodicReportSortColumns;
    }

    /**
     * Sets the value of the periodicReportSortColumns property.
     * 
     * @param value
     *     allowed object is
     *     {@link PeriodicReportSortColumns }
     *     
     */
    public void setPeriodicReportSortColumns(PeriodicReportSortColumns value) {
        this.periodicReportSortColumns = value;
    }

    /**
     * Gets the value of the periodicReportChartColumns property.
     * 
     * @return
     *     possible object is
     *     {@link PeriodicReportChartColumns }
     *     
     */
    public PeriodicReportChartColumns getPeriodicReportChartColumns() {
        return periodicReportChartColumns;
    }

    /**
     * Sets the value of the periodicReportChartColumns property.
     * 
     * @param value
     *     allowed object is
     *     {@link PeriodicReportChartColumns }
     *     
     */
    public void setPeriodicReportChartColumns(PeriodicReportChartColumns value) {
        this.periodicReportChartColumns = value;
    }

}
