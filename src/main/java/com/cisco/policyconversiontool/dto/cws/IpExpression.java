package com.cisco.policyconversiontool.dto.cws;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
"expression"
})
public class IpExpression {

@JsonProperty("expression")
private String expression;

/**
* 
* @return
* The expression
*/
@JsonProperty("expression")
public String getExpression() {
return expression;
}

/**
* 
* @param expression
* The expression
*/
@JsonProperty("expression")
public void setExpression(String expression) {
this.expression = expression;
}

}