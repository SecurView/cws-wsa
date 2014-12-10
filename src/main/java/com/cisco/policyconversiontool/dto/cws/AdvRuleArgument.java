
package com.cisco.policyconversiontool.dto.cws;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "argType",
    "exception",
    "filterPolicyName",
    "scheduleName",
    "authGroupName"
})
public class AdvRuleArgument {

    @JsonProperty("argType")
    private long argType;
    @JsonProperty("exception")
    private boolean exception;
    @JsonProperty("filterPolicyName")
    private String filterPolicyName;
    @JsonProperty("scheduleName")
    private String scheduleName;
    @JsonProperty("authGroupName")
    private String authGroupName;
    
    /**
     * 
     * @return
     *     The argType
     */
    @JsonProperty("argType")
    public long getArgType() {
        return argType;
    }

    /**
     * 
     * @param argType
     *     The argType
     */
    @JsonProperty("argType")
    public void setArgType(long argType) {
        this.argType = argType;
    }

    /**
     * 
     * @return
     *     The exception
     */
    @JsonProperty("exception")
    public boolean isException() {
        return exception;
    }

    /**
     * 
     * @param exception
     *     The exception
     */
    @JsonProperty("exception")
    public void setException(boolean exception) {
        this.exception = exception;
    }

    /**
     * 
     * @return
     *     The filterPolicyName
     */
    @JsonProperty("filterPolicyName")
    public String getFilterPolicyName() {
        return filterPolicyName;
    }

    /**
     * 
     * @param filterPolicyName
     *     The filterPolicyName
     */
    @JsonProperty("filterPolicyName")
    public void setFilterPolicyName(String filterPolicyName) {
        this.filterPolicyName = filterPolicyName;
    }

    /**
     * 
     * @return
     *     The scheduleName
     */
    @JsonProperty("scheduleName")
    public String getScheduleName() {
        return scheduleName;
    }

    /**
     * 
     * @param scheduleName
     *     The scheduleName
     */
    @JsonProperty("scheduleName")
    public void setScheduleName(String scheduleName) {
        this.scheduleName = scheduleName;
    }

    /**
     * 
     * @return
     *     The authGroupName
     */
    @JsonProperty("authGroupName")
    public String getAuthGroupName() {
        return authGroupName;
    }

    /**
     * 
     * @param authGroupName
     *     The authGroupName
     */
    @JsonProperty("authGroupName")
    public void setAuthGroupName(String authGroupName) {
        this.authGroupName = authGroupName;
    }

 }
