package org.folio.rest.jaxrs.model;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import javax.validation.Valid;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Index
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "code", "description", "constraints" })
public class Index {

    @JsonProperty("code")
    private String code;

    @JsonProperty("description")
    private String description;

    @JsonProperty("constraints")
    @Valid
    private List<Constraint> constraints = new ArrayList<Constraint>();

    /**
     * 
     * @return
     *     The code
     */
    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    /**
     * 
     * @param code
     *     The code
     */
    @JsonProperty("code")
    public void setCode(String code) {
        this.code = code;
    }

    public Index withCode(String code) {
        this.code = code;
        return this;
    }

    /**
     * 
     * @return
     *     The description
     */
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    public Index withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * 
     * @return
     *     The constraints
     */
    @JsonProperty("constraints")
    public List<Constraint> getConstraints() {
        return constraints;
    }

    /**
     * 
     * @param constraints
     *     The constraints
     */
    @JsonProperty("constraints")
    public void setConstraints(List<Constraint> constraints) {
        this.constraints = constraints;
    }

    public Index withConstraints(List<Constraint> constraints) {
        this.constraints = constraints;
        return this;
    }
}
