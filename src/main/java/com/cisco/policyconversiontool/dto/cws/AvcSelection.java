
package com.cisco.policyconversiontool.dto.cws;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "applicationMnemonic",
    "behaviourMnemonic"
})
public class AvcSelection {

    @JsonProperty("applicationMnemonic")
    private String applicationMnemonic;
    @JsonProperty("behaviourMnemonic")
    private String behaviourMnemonic;
   
    /**
     * 
     * @return
     *     The applicationMnemonic
     */
    @JsonProperty("applicationMnemonic")
    public String getApplicationMnemonic() {
        return applicationMnemonic;
    }

    /**
     * 
     * @param applicationMnemonic
     *     The applicationMnemonic
     */
    @JsonProperty("applicationMnemonic")
    public void setApplicationMnemonic(String applicationMnemonic) {
        this.applicationMnemonic = applicationMnemonic;
    }

    /**
     * 
     * @return
     *     The behaviourMnemonic
     */
    @JsonProperty("behaviourMnemonic")
    public String getBehaviourMnemonic() {
        return behaviourMnemonic;
    }

    /**
     * 
     * @param behaviourMnemonic
     *     The behaviourMnemonic
     */
    @JsonProperty("behaviourMnemonic")
    public void setBehaviourMnemonic(String behaviourMnemonic) {
        this.behaviourMnemonic = behaviourMnemonic;
    }

}
