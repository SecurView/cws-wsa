
package com.cisco.policyconversiontool.dto.cws;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "name",
    "pattern"
})
public class PreDefUserAgent {

    @JsonProperty("name")
    private String name;
    @JsonProperty("pattern")
    private String pattern;
   
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
     *     The pattern
     */
    @JsonProperty("pattern")
    public String getPattern() {
        return pattern;
    }

    /**
     * 
     * @param pattern
     *     The pattern
     */
    @JsonProperty("pattern")
    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

  
}
