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
    "chartQueryId",
    "chartColumnId"
})
@XmlRootElement(name = "chart_column")
public class ChartColumn {

    @XmlElement(name = "chart_query_id", required = true)
    protected String chartQueryId;
    @XmlElement(name = "chart_column_id", required = true)
    protected String chartColumnId;

    /**
     * Gets the value of the chartQueryId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChartQueryId() {
        return chartQueryId;
    }

    /**
     * Sets the value of the chartQueryId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChartQueryId(String value) {
        this.chartQueryId = value;
    }

    /**
     * Gets the value of the chartColumnId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChartColumnId() {
        return chartColumnId;
    }

    /**
     * Sets the value of the chartColumnId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChartColumnId(String value) {
        this.chartColumnId = value;
    }

}
