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
    "sortableQueryId",
    "sortableColumnId"
})
@XmlRootElement(name = "sortable_table")
public class SortableTable {

    @XmlElement(name = "sortable_query_id", required = true)
    protected String sortableQueryId;
    @XmlElement(name = "sortable_column_id", required = true)
    protected String sortableColumnId;

    /**
     * Gets the value of the sortableQueryId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSortableQueryId() {
        return sortableQueryId;
    }

    /**
     * Sets the value of the sortableQueryId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSortableQueryId(String value) {
        this.sortableQueryId = value;
    }

    /**
     * Gets the value of the sortableColumnId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSortableColumnId() {
        return sortableColumnId;
    }

    /**
     * Sets the value of the sortableColumnId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSortableColumnId(String value) {
        this.sortableColumnId = value;
    }

}
