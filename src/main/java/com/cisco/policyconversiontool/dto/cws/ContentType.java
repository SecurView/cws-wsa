
package com.cisco.policyconversiontool.dto.cws;


import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "contentType"
})
public class ContentType {

    @JsonProperty("contentType")
    private String contentType;
 
    /**
     * 
     * @return
     *     The contentType
     */
    @JsonProperty("contentType")
    public String getContentType() {
        return contentType;
    }

    /**
     * 
     * @param contentType
     *     The contentType
     */
    @JsonProperty("contentType")
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }


}
