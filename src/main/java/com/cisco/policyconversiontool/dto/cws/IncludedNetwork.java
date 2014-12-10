
package com.cisco.policyconversiontool.dto.cws;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "network"
})
public class IncludedNetwork {

    @JsonProperty("network")
    private String network;
   
    /**
     * 
     * @return
     *     The network
     */
    @JsonProperty("network")
    public String getNetwork() {
        return network;
    }

    /**
     * 
     * @param network
     *     The network
     */
    @JsonProperty("network")
    public void setNetwork(String network) {
        this.network = network;
    }

}
