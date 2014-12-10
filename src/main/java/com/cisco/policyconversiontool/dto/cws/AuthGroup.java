
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
    "groupType",
    "users",
    "directoryUsers",
    "machines",
    "agentLicenses",
    "ipExpressions"
})
public class AuthGroup {

    @JsonProperty("name")
    private String name;
    @JsonProperty("groupType")
    private String groupType;
    @JsonProperty("users")
    private List<User> users = new ArrayList<User>();
    @JsonProperty("directoryUsers")
    private List<Object> directoryUsers = new ArrayList<Object>();
    @JsonProperty("machines")
    private List<Object> machines = new ArrayList<Object>();
    @JsonProperty("agentLicenses")
    private List<Object> agentLicenses = new ArrayList<Object>();
    @JsonProperty("ipExpressions")
    private List<IpExpression> ipExpressions = new ArrayList<IpExpression>();
   
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
     *     The groupType
     */
    @JsonProperty("groupType")
    public String getGroupType() {
        return groupType;
    }

    /**
     * 
     * @param groupType
     *     The groupType
     */
    @JsonProperty("groupType")
    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    /**
     * 
     * @return
     *     The users
     */
    @JsonProperty("users")
    public List<User> getUsers() {
        return users;
    }

    /**
     * 
     * @param users
     *     The users
     */
    @JsonProperty("users")
    public void setUsers(List<User> users) {
        this.users = users;
    }

    /**
     * 
     * @return
     *     The directoryUsers
     */
    @JsonProperty("directoryUsers")
    public List<Object> getDirectoryUsers() {
        return directoryUsers;
    }

    /**
     * 
     * @param directoryUsers
     *     The directoryUsers
     */
    @JsonProperty("directoryUsers")
    public void setDirectoryUsers(List<Object> directoryUsers) {
        this.directoryUsers = directoryUsers;
    }

    /**
     * 
     * @return
     *     The machines
     */
    @JsonProperty("machines")
    public List<Object> getMachines() {
        return machines;
    }

    /**
     * 
     * @param machines
     *     The machines
     */
    @JsonProperty("machines")
    public void setMachines(List<Object> machines) {
        this.machines = machines;
    }

    /**
     * 
     * @return
     *     The agentLicenses
     */
    @JsonProperty("agentLicenses")
    public List<Object> getAgentLicenses() {
        return agentLicenses;
    }

    /**
     * 
     * @param agentLicenses
     *     The agentLicenses
     */
    @JsonProperty("agentLicenses")
    public void setAgentLicenses(List<Object> agentLicenses) {
        this.agentLicenses = agentLicenses;
    }

    /**
    * 
    * @return
    * The ipExpressions
    */
    @JsonProperty("ipExpressions")
    public List<IpExpression> getIpExpressions() {
    return ipExpressions;
    }

    /**
    * 
    * @param ipExpressions
    * The ipExpressions
    */
    @JsonProperty("ipExpressions")
    public void setIpExpressions(List<IpExpression> ipExpressions) {
    this.ipExpressions = ipExpressions;
    }

 }
