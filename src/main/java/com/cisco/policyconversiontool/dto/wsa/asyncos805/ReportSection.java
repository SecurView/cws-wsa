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
    "reportSectionName",
    "reportSectionChartColumn",
    "reportSectionHiddenColumns"
})
@XmlRootElement(name = "report_section")
public class ReportSection {

    @XmlElement(name = "report_section_name", required = true)
    protected String reportSectionName;
    @XmlElement(name = "report_section_chart_column")
    protected String reportSectionChartColumn;
    @XmlElement(name = "report_section_hidden_columns")
    protected ReportSectionHiddenColumns reportSectionHiddenColumns;

    /**
     * Gets the value of the reportSectionName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReportSectionName() {
        return reportSectionName;
    }

    /**
     * Sets the value of the reportSectionName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReportSectionName(String value) {
        this.reportSectionName = value;
    }

    /**
     * Gets the value of the reportSectionChartColumn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReportSectionChartColumn() {
        return reportSectionChartColumn;
    }

    /**
     * Sets the value of the reportSectionChartColumn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReportSectionChartColumn(String value) {
        this.reportSectionChartColumn = value;
    }

    /**
     * Gets the value of the reportSectionHiddenColumns property.
     * 
     * @return
     *     possible object is
     *     {@link ReportSectionHiddenColumns }
     *     
     */
    public ReportSectionHiddenColumns getReportSectionHiddenColumns() {
        return reportSectionHiddenColumns;
    }

    /**
     * Sets the value of the reportSectionHiddenColumns property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReportSectionHiddenColumns }
     *     
     */
    public void setReportSectionHiddenColumns(ReportSectionHiddenColumns value) {
        this.reportSectionHiddenColumns = value;
    }

}