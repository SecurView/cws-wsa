
package com.cisco.policyconversiontool.dto.cws;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "httpsRuleGroupName",
    "isGroupException"
})
public class HttpsRuleGroup {

    @JsonProperty("httpsRuleGroupName")
    private String httpsRuleGroupName;
    @JsonProperty("isGroupException")
    private boolean isGroupException;

    /**
     * 
     * @return
     *     The httpsRuleGroupName
     */
    @JsonProperty("httpsRuleGroupName")
    public String getHttpsRuleGroupName() {
        return httpsRuleGroupName;
    }

    /**
     * 
     * @param httpsRuleGroupName
     *     The httpsRuleGroupName
     */
    @JsonProperty("httpsRuleGroupName")
    public void setHttpsRuleGroupName(String httpsRuleGroupName) {
        this.httpsRuleGroupName = httpsRuleGroupName;
    }

    /**
     * 
     * @return
     *     The isGroupException
     */
    @JsonProperty("isGroupException")
    public boolean isIsGroupException() {
        return isGroupException;
    }

    /**
     * 
     * @param isGroupException
     *     The isGroupException
     */
    @JsonProperty("isGroupException")
    public void setIsGroupException(boolean isGroupException) {
        this.isGroupException = isGroupException;
    }

 }
