
package com.cisco.policyconversiontool.dto.cws;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "name",
    "log",
    "block"
})
public class HttpCategory {

    @JsonProperty("name")
    private String name;
    @JsonProperty("log")
    private boolean log;
    @JsonProperty("block")
    private boolean block;

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
     *     The log
     */
    @JsonProperty("log")
    public boolean isLog() {
        return log;
    }

    /**
     * 
     * @param log
     *     The log
     */
    @JsonProperty("log")
    public void setLog(boolean log) {
        this.log = log;
    }

    /**
     * 
     * @return
     *     The block
     */
    @JsonProperty("block")
    public boolean isBlock() {
        return block;
    }

    /**
     * 
     * @param block
     *     The block
     */
    @JsonProperty("block")
    public void setBlock(boolean block) {
        this.block = block;
    }


}
