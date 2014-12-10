
package com.cisco.policyconversiontool.dto.cws;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "extension"
})
public class InboundExtension {

    @JsonProperty("extension")
    private String extension;
  
    /**
     * 
     * @return
     *     The extension
     */
    @JsonProperty("extension")
    public String getExtension() {
        return extension;
    }

    /**
     * 
     * @param extension
     *     The extension
     */
    @JsonProperty("extension")
    public void setExtension(String extension) {
        this.extension = extension;
    }

 }
