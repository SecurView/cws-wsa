
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
    "advAction",
    "enabled",
    "seqNumber",
    "advRuleArguments",
    "description"
})
public class AdvRule {

    @JsonProperty("name")
    private String name;
    @JsonProperty("advAction")
    private long advAction;
    @JsonProperty("enabled")
    private boolean enabled;
    @JsonProperty("seqNumber")
    private long seqNumber;
    @JsonProperty("advRuleArguments")
    private List<AdvRuleArgument> advRuleArguments = new ArrayList<AdvRuleArgument>();
    @JsonProperty("description")
    private String description;
  
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
     *     The advAction
     */
    @JsonProperty("advAction")
    public long getAdvAction() {
        return advAction;
    }

    /**
     * 
     * @param advAction
     *     The advAction
     */
    @JsonProperty("advAction")
    public void setAdvAction(long advAction) {
        this.advAction = advAction;
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
     *     The advRuleArguments
     */
    @JsonProperty("advRuleArguments")
    public List<AdvRuleArgument> getAdvRuleArguments() {
        return advRuleArguments;
    }

    /**
     * 
     * @param advRuleArguments
     *     The advRuleArguments
     */
    @JsonProperty("advRuleArguments")
    public void setAdvRuleArguments(List<AdvRuleArgument> advRuleArguments) {
        this.advRuleArguments = advRuleArguments;
    }

    /**
     * 
     * @return
     *     The description
     */
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

}
