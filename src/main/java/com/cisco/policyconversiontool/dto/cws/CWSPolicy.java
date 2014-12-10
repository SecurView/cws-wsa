
package com.cisco.policyconversiontool.dto.cws;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)

@JsonPropertyOrder({
    "name",
    "user",
    "filterPolicies",
    "schedules",
    "authGroups",
    "httpsCerts",
    "advRules",
    "httpsRules",
    "httpsFilters",
    "expressionLists",
    "agentLicenses",
    "backupDateTime"
})
public class CWSPolicy {

    @JsonProperty("name")
    private String name;
    @JsonProperty("user")
    private String user;
    @JsonProperty("filterPolicies")
    private List<FilterPolicy> filterPolicies = new ArrayList<FilterPolicy>();
    @JsonProperty("schedules")
    private List<Schedule> schedules = new ArrayList<Schedule>();
    @JsonProperty("authGroups")
    private List<AuthGroup> authGroups = new ArrayList<AuthGroup>();
    @JsonProperty("httpsCerts")
    private List<Object> httpsCerts = new ArrayList<Object>();
    @JsonProperty("advRules")
    private List<AdvRule> advRules = new ArrayList<AdvRule>();
    @JsonProperty("httpsRules")
    private List<HttpsRule> httpsRules = new ArrayList<HttpsRule>();
    @JsonProperty("httpsFilters")
    private List<HttpsFilter> httpsFilters = new ArrayList<HttpsFilter>();
    @JsonProperty("expressionLists")
    private List<Object> expressionLists = new ArrayList<Object>();
    @JsonProperty("agentLicenses")
    private List<Object> agentLicenses = new ArrayList<Object>();
    @JsonProperty("backupDateTime")
    private String backupDateTime;
 
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
     *     The user
     */
    @JsonProperty("user")
    public String getUser() {
        return user;
    }

    /**
     * 
     * @param user
     *     The user
     */
    @JsonProperty("user")
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * 
     * @return
     *     The filterPolicies
     */
    @JsonProperty("filterPolicies")
    public List<FilterPolicy> getFilterPolicies() {
        return filterPolicies;
    }

    /**
     * 
     * @param filterPolicies
     *     The filterPolicies
     */
    @JsonProperty("filterPolicies")
    public void setFilterPolicies(List<FilterPolicy> filterPolicies) {
        this.filterPolicies = filterPolicies;
    }

    /**
     * 
     * @return
     *     The schedules
     */
    @JsonProperty("schedules")
    public List<Schedule> getSchedules() {
        return schedules;
    }

    /**
     * 
     * @param schedules
     *     The schedules
     */
    @JsonProperty("schedules")
    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    /**
     * 
     * @return
     *     The authGroups
     */
    @JsonProperty("authGroups")
    public List<AuthGroup> getAuthGroups() {
        return authGroups;
    }

    /**
     * 
     * @param authGroups
     *     The authGroups
     */
    @JsonProperty("authGroups")
    public void setAuthGroups(List<AuthGroup> authGroups) {
        this.authGroups = authGroups;
    }

    /**
     * 
     * @return
     *     The httpsCerts
     */
    @JsonProperty("httpsCerts")
    public List<Object> getHttpsCerts() {
        return httpsCerts;
    }

    /**
     * 
     * @param httpsCerts
     *     The httpsCerts
     */
    @JsonProperty("httpsCerts")
    public void setHttpsCerts(List<Object> httpsCerts) {
        this.httpsCerts = httpsCerts;
    }

    /**
     * 
     * @return
     *     The advRules
     */
    @JsonProperty("advRules")
    public List<AdvRule> getAdvRules() {
        return advRules;
    }

    /**
     * 
     * @param advRules
     *     The advRules
     */
    @JsonProperty("advRules")
    public void setAdvRules(List<AdvRule> advRules) {
        this.advRules = advRules;
    }

    /**
     * 
     * @return
     *     The httpsRules
     */
    @JsonProperty("httpsRules")
    public List<HttpsRule> getHttpsRules() {
        return httpsRules;
    }

    /**
     * 
     * @param httpsRules
     *     The httpsRules
     */
    @JsonProperty("httpsRules")
    public void setHttpsRules(List<HttpsRule> httpsRules) {
        this.httpsRules = httpsRules;
    }

    /**
     * 
     * @return
     *     The httpsFilters
     */
    @JsonProperty("httpsFilters")
    public List<HttpsFilter> getHttpsFilters() {
        return httpsFilters;
    }

    /**
     * 
     * @param httpsFilters
     *     The httpsFilters
     */
    @JsonProperty("httpsFilters")
    public void setHttpsFilters(List<HttpsFilter> httpsFilters) {
        this.httpsFilters = httpsFilters;
    }

    /**
     * 
     * @return
     *     The expressionLists
     */
    @JsonProperty("expressionLists")
    public List<Object> getExpressionLists() {
        return expressionLists;
    }

    /**
     * 
     * @param expressionLists
     *     The expressionLists
     */
    @JsonProperty("expressionLists")
    public void setExpressionLists(List<Object> expressionLists) {
        this.expressionLists = expressionLists;
    }

    /**
     * 
     * @return
     *     The agentLicenses
     */
    @JsonProperty("agentLicenses")
    public List<Object> getAgentLicenses() {
        return agentLicenses;
    }

    /**
     * 
     * @param agentLicenses
     *     The agentLicenses
     */
    @JsonProperty("agentLicenses")
    public void setAgentLicenses(List<Object> agentLicenses) {
        this.agentLicenses = agentLicenses;
    }

    /**
     * 
     * @return
     *     The backupDateTime
     */
    @JsonProperty("backupDateTime")
    public String getBackupDateTime() {
        return backupDateTime;
    }

    /**
     * 
     * @param backupDateTime
     *     The backupDateTime
     */
    @JsonProperty("backupDateTime")
    public void setBackupDateTime(String backupDateTime) {
        this.backupDateTime = backupDateTime;
    }

  
}
