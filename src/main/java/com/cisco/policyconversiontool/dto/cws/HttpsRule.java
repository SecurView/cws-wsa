
package com.cisco.policyconversiontool.dto.cws;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "name",
    "enabled",
    "seqNumber",
    "httpsRuleFilter",
    "httpsRuleGroups"
})
public class HttpsRule {

    @JsonProperty("name")
    private String name;
    @JsonProperty("enabled")
    private boolean enabled;
    @JsonProperty("seqNumber")
    private long seqNumber;
    @JsonProperty("httpsRuleFilter")
    private HttpsRuleFilter httpsRuleFilter;
    @JsonProperty("httpsRuleGroups")
    private List<HttpsRuleGroup> httpsRuleGroups = new ArrayList<HttpsRuleGroup>();
   
    /**
     * 
     * @return
     *     The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The enabled
     */
    @JsonProperty("enabled")
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * 
     * @param enabled
     *     The enabled
     */
    @JsonProperty("enabled")
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * 
     * @return
     *     The seqNumber
     */
    @JsonProperty("seqNumber")
    public long getSeqNumber() {
        return seqNumber;
    }

    /**
     * 
     * @param seqNumber
     *     The seqNumber
     */
    @JsonProperty("seqNumber")
    public void setSeqNumber(long seqNumber) {
        this.seqNumber = seqNumber;
    }

    /**
     * 
     * @return
     *     The httpsRuleFilter
     */
    @JsonProperty("httpsRuleFilter")
    public HttpsRuleFilter getHttpsRuleFilter() {
        return httpsRuleFilter;
    }

    /**
     * 
     * @param httpsRuleFilter
     *     The httpsRuleFilter
     */
    @JsonProperty("httpsRuleFilter")
    public void setHttpsRuleFilter(HttpsRuleFilter httpsRuleFilter) {
        this.httpsRuleFilter = httpsRuleFilter;
    }

    /**
     * 
     * @return
     *     The httpsRuleGroups
     */
    @JsonProperty("httpsRuleGroups")
    public List<HttpsRuleGroup> getHttpsRuleGroups() {
        return httpsRuleGroups;
    }

    /**
     * 
     * @param httpsRuleGroups
     *     The httpsRuleGroups
     */
    @JsonProperty("httpsRuleGroups")
    public void setHttpsRuleGroups(List<HttpsRuleGroup> httpsRuleGroups) {
        this.httpsRuleGroups = httpsRuleGroups;
    }

}
