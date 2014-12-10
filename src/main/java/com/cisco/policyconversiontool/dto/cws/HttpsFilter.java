
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
    "createDate",
    "avcInspectionEnabled",
    "domains",
    "excludedNetworks",
    "includedNetworks",
    "categories",
    "exceptions"
})
public class HttpsFilter {

    @JsonProperty("name")
    private String name;
    @JsonProperty("createDate")
    private String createDate;
    @JsonProperty("avcInspectionEnabled")
    private boolean avcInspectionEnabled;
    @JsonProperty("domains")
    private List<Domain> domains = new ArrayList<Domain>();
    @JsonProperty("excludedNetworks")
    private List<ExcludedNetwork> excludedNetworks = new ArrayList<ExcludedNetwork>();
    @JsonProperty("includedNetworks")
    private List<IncludedNetwork> includedNetworks = new ArrayList<IncludedNetwork>();
    @JsonProperty("categories")
    private List<Category> categories = new ArrayList<Category>();
    @JsonProperty("exceptions")
    private List<Exception> exceptions = new ArrayList<Exception>();
  
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
     *     The createDate
     */
    @JsonProperty("createDate")
    public String getCreateDate() {
        return createDate;
    }

    /**
     * 
     * @param createDate
     *     The createDate
     */
    @JsonProperty("createDate")
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * 
     * @return
     *     The avcInspectionEnabled
     */
    @JsonProperty("avcInspectionEnabled")
    public boolean isAvcInspectionEnabled() {
        return avcInspectionEnabled;
    }

    /**
     * 
     * @param avcInspectionEnabled
     *     The avcInspectionEnabled
     */
    @JsonProperty("avcInspectionEnabled")
    public void setAvcInspectionEnabled(boolean avcInspectionEnabled) {
        this.avcInspectionEnabled = avcInspectionEnabled;
    }

    /**
     * 
     * @return
     *     The domains
     */
    @JsonProperty("domains")
    public List<Domain> getDomains() {
        return domains;
    }

    /**
     * 
     * @param domains
     *     The domains
     */
    @JsonProperty("domains")
    public void setDomains(List<Domain> domains) {
        this.domains = domains;
    }

    /**
     * 
     * @return
     *     The excludedNetworks
     */
    @JsonProperty("excludedNetworks")
    public List<ExcludedNetwork> getExcludedNetworks() {
        return excludedNetworks;
    }

    /**
     * 
     * @param excludedNetworks
     *     The excludedNetworks
     */
    @JsonProperty("excludedNetworks")
    public void setExcludedNetworks(List<ExcludedNetwork> excludedNetworks) {
        this.excludedNetworks = excludedNetworks;
    }

    /**
     * 
     * @return
     *     The includedNetworks
     */
    @JsonProperty("includedNetworks")
    public List<IncludedNetwork> getIncludedNetworks() {
        return includedNetworks;
    }

    /**
     * 
     * @param includedNetworks
     *     The includedNetworks
     */
    @JsonProperty("includedNetworks")
    public void setIncludedNetworks(List<IncludedNetwork> includedNetworks) {
        this.includedNetworks = includedNetworks;
    }

    /**
     * 
     * @return
     *     The categories
     */
    @JsonProperty("categories")
    public List<Category> getCategories() {
        return categories;
    }

    /**
     * 
     * @param categories
     *     The categories
     */
    @JsonProperty("categories")
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    /**
     * 
     * @return
     *     The exceptions
     */
    @JsonProperty("exceptions")
    public List<Exception> getExceptions() {
        return exceptions;
    }

    /**
     * 
     * @param exceptions
     *     The exceptions
     */
    @JsonProperty("exceptions")
    public void setExceptions(List<Exception> exceptions) {
        this.exceptions = exceptions;
    }


}
