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
    "dictname",
    "ignorecase",
    "wholewords",
    "encoding",
    "words"
})
@XmlRootElement(name = "dictionary")
public class Dictionary {

    @XmlElement(required = true)
    protected String dictname;
    @XmlElement(required = true)
    protected String ignorecase;
    @XmlElement(required = true)
    protected String wholewords;
    @XmlElement(required = true)
    protected String encoding;
    protected Words words;

    /**
     * Gets the value of the dictname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDictname() {
        return dictname;
    }

    /**
     * Sets the value of the dictname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDictname(String value) {
        this.dictname = value;
    }

    /**
     * Gets the value of the ignorecase property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIgnorecase() {
        return ignorecase;
    }

    /**
     * Sets the value of the ignorecase property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIgnorecase(String value) {
        this.ignorecase = value;
    }

    /**
     * Gets the value of the wholewords property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWholewords() {
        return wholewords;
    }

    /**
     * Sets the value of the wholewords property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWholewords(String value) {
        this.wholewords = value;
    }

    /**
     * Gets the value of the encoding property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEncoding() {
        return encoding;
    }

    /**
     * Sets the value of the encoding property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEncoding(String value) {
        this.encoding = value;
    }

    /**
     * Gets the value of the words property.
     * 
     * @return
     *     possible object is
     *     {@link Words }
     *     
     */
    public Words getWords() {
        return words;
    }

    /**
     * Sets the value of the words property.
     * 
     * @param value
     *     allowed object is
     *     {@link Words }
     *     
     */
    public void setWords(Words value) {
        this.words = value;
    }

}