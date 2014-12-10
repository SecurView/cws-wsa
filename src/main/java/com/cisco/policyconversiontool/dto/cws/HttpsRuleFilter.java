
package com.cisco.policyconversiontool.dto.cws;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "httpsFilterName",
    "isHttpsFilterException"
})
public class HttpsRuleFilter {

    @JsonProperty("httpsFilterName")
    private String httpsFilterName;
    @JsonProperty("isHttpsFilterException")
    private boolean isHttpsFilterException;
   
    /**
     * 
     * @return
     *     The httpsFilterName
     */
    @JsonProperty("httpsFilterName")
    public String getHttpsFilterName() {
        return httpsFilterName;
    }

    /**
     * 
     * @param httpsFilterName
     *     The httpsFilterName
     */
    @JsonProperty("httpsFilterName")
    public void setHttpsFilterName(String httpsFilterName) {
        this.httpsFilterName = httpsFilterName;
    }

    /**
     * 
     * @return
     *     The isHttpsFilterException
     */
    @JsonProperty("isHttpsFilterException")
    public boolean isIsHttpsFilterException() {
        return isHttpsFilterException;
    }

    /**
     * 
     * @param isHttpsFilterException
     *     The isHttpsFilterException
     */
    @JsonProperty("isHttpsFilterException")
    public void setIsHttpsFilterException(boolean isHttpsFilterException) {
        this.isHttpsFilterException = isHttpsFilterException;
    }

  
}
