package com.cisco.policyconversiontool.dto.cws;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "key",
    "agentState",
    "startDate",
    "endDate",
    "licenseKeyTail"
})
public class AgentLicense {

    @JsonProperty("key")
    private String key;
    @JsonProperty("agentState")
    private Integer agentState;
    @JsonProperty("startDate")
    private String startDate;
    @JsonProperty("endDate")
    private String endDate;
    @JsonProperty("licenseKeyTail")
    private String licenseKeyTail;
   
    /**
     * 
     * @return
     *     The key
     */
    @JsonProperty("key")
    public String getKey() {
        return key;
    }

    /**
     * 
     * @param key
     *     The key
     */
    @JsonProperty("key")
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * 
     * @return
     *     The agentState
     */
    @JsonProperty("agentState")
    public Integer getAgentState() {
        return agentState;
    }

    /**
     * 
     * @param agentState
     *     The agentState
     */
    @JsonProperty("agentState")
    public void setAgentState(Integer agentState) {
        this.agentState = agentState;
    }

    /**
     * 
     * @return
     *     The startDate
     */
    @JsonProperty("startDate")
    public String getStartDate() {
        return startDate;
    }

    /**
     * 
     * @param startDate
     *     The startDate
     */
    @JsonProperty("startDate")
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * 
     * @return
     *     The endDate
     */
    @JsonProperty("endDate")
    public String getEndDate() {
        return endDate;
    }

    /**
     * 
     * @param endDate
     *     The endDate
     */
    @JsonProperty("endDate")
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * 
     * @return
     *     The licenseKeyTail
     */
    @JsonProperty("licenseKeyTail")
    public String getLicenseKeyTail() {
        return licenseKeyTail;
    }

    /**
     * 
     * @param licenseKeyTail
     *     The licenseKeyTail
     */
    @JsonProperty("licenseKeyTail")
    public void setLicenseKeyTail(String licenseKeyTail) {
        this.licenseKeyTail = licenseKeyTail;
    }

}
