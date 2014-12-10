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
    "email",
    "mobile",
    "agentLicenseReq",
    "agentLicenses"
})
public class User {

    @JsonProperty("name")
    private String name;
    @JsonProperty("email")
    private String email;
    @JsonProperty("mobile")
    private Boolean mobile;
    @JsonProperty("agentLicenseReq")
    private Boolean agentLicenseReq;
    @JsonProperty("agentLicenses")
    private List<AgentLicense> agentLicenses = new ArrayList<AgentLicense>();
  
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
     *     The email
     */
    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    /**
     * 
     * @param email
     *     The email
     */
    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 
     * @return
     *     The mobile
     */
    @JsonProperty("mobile")
    public Boolean getMobile() {
        return mobile;
    }

    /**
     * 
     * @param mobile
     *     The mobile
     */
    @JsonProperty("mobile")
    public void setMobile(Boolean mobile) {
        this.mobile = mobile;
    }

    /**
     * 
     * @return
     *     The agentLicenseReq
     */
    @JsonProperty("agentLicenseReq")
    public Boolean getAgentLicenseReq() {
        return agentLicenseReq;
    }

    /**
     * 
     * @param agentLicenseReq
     *     The agentLicenseReq
     */
    @JsonProperty("agentLicenseReq")
    public void setAgentLicenseReq(Boolean agentLicenseReq) {
        this.agentLicenseReq = agentLicenseReq;
    }

    /**
     * 
     * @return
     *     The agentLicenses
     */
    @JsonProperty("agentLicenses")
    public List<AgentLicense> getAgentLicenses() {
        return agentLicenses;
    }

    /**
     * 
     * @param agentLicenses
     *     The agentLicenses
     */
    @JsonProperty("agentLicenses")
    public void setAgentLicenses(List<AgentLicense> agentLicenses) {
        this.agentLicenses = agentLicenses;
    }

 
}
