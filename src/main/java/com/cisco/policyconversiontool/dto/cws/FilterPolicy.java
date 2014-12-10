
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
    "taxonomyVer",
    "createdDate",
    "blacklistIps",
    "whitelistIps",
    "blacklistDomains",
    "whitelistDomains",
    "contentTypes",
    "httpCategories",
    "httpsCategories",
    "customRegex",
    "preConfRegex",
    "customUserAgents",
    "preDefUserAgents",
    "filterProtocols",
    "inboundExtensions",
    "outboundExtensions",
    "avcSelections"
})
public class FilterPolicy {

    @JsonProperty("name")
    private String name;
    @JsonProperty("taxonomyVer")
    private String taxonomyVer;
    @JsonProperty("createdDate")
    private String createdDate;
    @JsonProperty("blacklistIps")
    private List<BlacklistIp> blacklistIps = new ArrayList<BlacklistIp>();
    @JsonProperty("whitelistIps")
    private List<WhitelistIp> whitelistIps = new ArrayList<WhitelistIp>();
    @JsonProperty("blacklistDomains")
    private List<BlacklistDomain> blacklistDomains = new ArrayList<BlacklistDomain>();
    @JsonProperty("whitelistDomains")
    private List<WhitelistDomain> whitelistDomains = new ArrayList<WhitelistDomain>();
    @JsonProperty("contentTypes")
    private List<ContentType> contentTypes = new ArrayList<ContentType>();
    @JsonProperty("httpCategories")
    private List<HttpCategory> httpCategories = new ArrayList<HttpCategory>();
    @JsonProperty("httpsCategories")
    private List<Object> httpsCategories = new ArrayList<Object>();
    @JsonProperty("customRegex")
    private List<Object> customRegex = new ArrayList<Object>();
    @JsonProperty("preConfRegex")
    private List<PreConfRegex> preConfRegex = new ArrayList<PreConfRegex>();
    @JsonProperty("customUserAgents")
    private List<Object> customUserAgents = new ArrayList<Object>();
    @JsonProperty("preDefUserAgents")
    private List<PreDefUserAgent> preDefUserAgents = new ArrayList<PreDefUserAgent>();
    @JsonProperty("filterProtocols")
    private List<FilterProtocol> filterProtocols = new ArrayList<FilterProtocol>();
    @JsonProperty("inboundExtensions")
    private List<InboundExtension> inboundExtensions = new ArrayList<InboundExtension>();
    @JsonProperty("outboundExtensions")
    private List<OutboundExtension> outboundExtensions = new ArrayList<OutboundExtension>();
    @JsonProperty("avcSelections")
    private List<AvcSelection> avcSelections = new ArrayList<AvcSelection>();
  
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
     *     The taxonomyVer
     */
    @JsonProperty("taxonomyVer")
    public String getTaxonomyVer() {
        return taxonomyVer;
    }

    /**
     * 
     * @param taxonomyVer
     *     The taxonomyVer
     */
    @JsonProperty("taxonomyVer")
    public void setTaxonomyVer(String taxonomyVer) {
        this.taxonomyVer = taxonomyVer;
    }

    /**
     * 
     * @return
     *     The createdDate
     */
    @JsonProperty("createdDate")
    public String getCreatedDate() {
        return createdDate;
    }

    /**
     * 
     * @param createdDate
     *     The createdDate
     */
    @JsonProperty("createdDate")
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * 
     * @return
     *     The blacklistIps
     */
    @JsonProperty("blacklistIps")
    public List<BlacklistIp> getBlacklistIps() {
        return blacklistIps;
    }

    /**
     * 
     * @param blacklistIps
     *     The blacklistIps
     */
    @JsonProperty("blacklistIps")
    public void setBlacklistIps(List<BlacklistIp> blacklistIps) {
        this.blacklistIps = blacklistIps;
    }

    /**
     * 
     * @return
     *     The whitelistIps
     */
    @JsonProperty("whitelistIps")
    public List<WhitelistIp> getWhitelistIps() {
        return whitelistIps;
    }

    /**
     * 
     * @param whitelistIps
     *     The whitelistIps
     */
    @JsonProperty("whitelistIps")
    public void setWhitelistIps(List<WhitelistIp> whitelistIps) {
        this.whitelistIps = whitelistIps;
    }

    /**
     * 
     * @return
     *     The blacklistDomains
     */
    @JsonProperty("blacklistDomains")
    public List<BlacklistDomain> getBlacklistDomains() {
        return blacklistDomains;
    }

    /**
     * 
     * @param blacklistDomains
     *     The blacklistDomains
     */
    @JsonProperty("blacklistDomains")
    public void setBlacklistDomains(List<BlacklistDomain> blacklistDomains) {
        this.blacklistDomains = blacklistDomains;
    }

    /**
     * 
     * @return
     *     The whitelistDomains
     */
    @JsonProperty("whitelistDomains")
    public List<WhitelistDomain> getWhitelistDomains() {
        return whitelistDomains;
    }

    /**
     * 
     * @param whitelistDomains
     *     The whitelistDomains
     */
    @JsonProperty("whitelistDomains")
    public void setWhitelistDomains(List<WhitelistDomain> whitelistDomains) {
        this.whitelistDomains = whitelistDomains;
    }

    /**
     * 
     * @return
     *     The contentTypes
     */
    @JsonProperty("contentTypes")
    public List<ContentType> getContentTypes() {
        return contentTypes;
    }

    /**
     * 
     * @param contentTypes
     *     The contentTypes
     */
    @JsonProperty("contentTypes")
    public void setContentTypes(List<ContentType> contentTypes) {
        this.contentTypes = contentTypes;
    }

    /**
     * 
     * @return
     *     The httpCategories
     */
    @JsonProperty("httpCategories")
    public List<HttpCategory> getHttpCategories() {
        return httpCategories;
    }

    /**
     * 
     * @param httpCategories
     *     The httpCategories
     */
    @JsonProperty("httpCategories")
    public void setHttpCategories(List<HttpCategory> httpCategories) {
        this.httpCategories = httpCategories;
    }

    /**
     * 
     * @return
     *     The httpsCategories
     */
    @JsonProperty("httpsCategories")
    public List<Object> getHttpsCategories() {
        return httpsCategories;
    }

    /**
     * 
     * @param httpsCategories
     *     The httpsCategories
     */
    @JsonProperty("httpsCategories")
    public void setHttpsCategories(List<Object> httpsCategories) {
        this.httpsCategories = httpsCategories;
    }

    /**
     * 
     * @return
     *     The customRegex
     */
    @JsonProperty("customRegex")
    public List<Object> getCustomRegex() {
        return customRegex;
    }

    /**
     * 
     * @param customRegex
     *     The customRegex
     */
    @JsonProperty("customRegex")
    public void setCustomRegex(List<Object> customRegex) {
        this.customRegex = customRegex;
    }

    /**
     * 
     * @return
     *     The preConfRegex
     */
    @JsonProperty("preConfRegex")
    public List<PreConfRegex> getPreConfRegex() {
        return preConfRegex;
    }

    /**
     * 
     * @param preConfRegex
     *     The preConfRegex
     */
    @JsonProperty("preConfRegex")
    public void setPreConfRegex(List<PreConfRegex> preConfRegex) {
        this.preConfRegex = preConfRegex;
    }

    /**
     * 
     * @return
     *     The customUserAgents
     */
    @JsonProperty("customUserAgents")
    public List<Object> getCustomUserAgents() {
        return customUserAgents;
    }

    /**
     * 
     * @param customUserAgents
     *     The customUserAgents
     */
    @JsonProperty("customUserAgents")
    public void setCustomUserAgents(List<Object> customUserAgents) {
        this.customUserAgents = customUserAgents;
    }

    /**
     * 
     * @return
     *     The preDefUserAgents
     */
    @JsonProperty("preDefUserAgents")
    public List<PreDefUserAgent> getPreDefUserAgents() {
        return preDefUserAgents;
    }

    /**
     * 
     * @param preDefUserAgents
     *     The preDefUserAgents
     */
    @JsonProperty("preDefUserAgents")
    public void setPreDefUserAgents(List<PreDefUserAgent> preDefUserAgents) {
        this.preDefUserAgents = preDefUserAgents;
    }

    /**
     * 
     * @return
     *     The filterProtocols
     */
    @JsonProperty("filterProtocols")
    public List<FilterProtocol> getFilterProtocols() {
        return filterProtocols;
    }

    /**
     * 
     * @param filterProtocols
     *     The filterProtocols
     */
    @JsonProperty("filterProtocols")
    public void setFilterProtocols(List<FilterProtocol> filterProtocols) {
        this.filterProtocols = filterProtocols;
    }

    /**
     * 
     * @return
     *     The inboundExtensions
     */
    @JsonProperty("inboundExtensions")
    public List<InboundExtension> getInboundExtensions() {
        return inboundExtensions;
    }

    /**
     * 
     * @param inboundExtensions
     *     The inboundExtensions
     */
    @JsonProperty("inboundExtensions")
    public void setInboundExtensions(List<InboundExtension> inboundExtensions) {
        this.inboundExtensions = inboundExtensions;
    }

    /**
     * 
     * @return
     *     The outboundExtensions
     */
    @JsonProperty("outboundExtensions")
    public List<OutboundExtension> getOutboundExtensions() {
        return outboundExtensions;
    }

    /**
     * 
     * @param outboundExtensions
     *     The outboundExtensions
     */
    @JsonProperty("outboundExtensions")
    public void setOutboundExtensions(List<OutboundExtension> outboundExtensions) {
        this.outboundExtensions = outboundExtensions;
    }

    /**
     * 
     * @return
     *     The avcSelections
     */
    @JsonProperty("avcSelections")
    public List<AvcSelection> getAvcSelections() {
        return avcSelections;
    }

    /**
     * 
     * @param avcSelections
     *     The avcSelections
     */
    @JsonProperty("avcSelections")
    public void setAvcSelections(List<AvcSelection> avcSelections) {
        this.avcSelections = avcSelections;
    }


}
