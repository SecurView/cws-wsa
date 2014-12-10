
package com.cisco.policyconversiontool.dto.cws;


import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "exception"
})
public class Exception {

    @JsonProperty("exception")
    private String exception;
  
    /**
     * 
     * @return
     *     The exception
     */
    @JsonProperty("exception")
    public String getException() {
        return exception;
    }

    /**
     * 
     * @param exception
     *     The exception
     */
    @JsonProperty("exception")
    public void setException(String exception) {
        this.exception = exception;
    }

}
