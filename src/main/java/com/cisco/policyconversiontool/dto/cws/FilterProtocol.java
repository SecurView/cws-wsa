
package com.cisco.policyconversiontool.dto.cws;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "filterProtocol"
})
public class FilterProtocol {

    @JsonProperty("filterProtocol")
    private String filterProtocol;
   
    /**
     * 
     * @return
     *     The filterProtocol
     */
    @JsonProperty("filterProtocol")
    public String getFilterProtocol() {
        return filterProtocol;
    }

    /**
     * 
     * @param filterProtocol
     *     The filterProtocol
     */
    @JsonProperty("filterProtocol")
    public void setFilterProtocol(String filterProtocol) {
        this.filterProtocol = filterProtocol;
    }

}
