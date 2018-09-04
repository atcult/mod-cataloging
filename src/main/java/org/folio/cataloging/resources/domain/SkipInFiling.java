package org.folio.cataloging.resources.domain;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Skip In Filing Schema
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "value", "label" })
public class SkipInFiling {

    @JsonProperty("value")
    private Integer code;

    @JsonProperty("label")
    private String description;

    /**
     * 
     * @return
     *     The code
     */
    @JsonProperty("value")
    public Integer getCode() {
        return code;
    }

    /**
     * 
     * @param code
     *     The code
     */
    @JsonProperty("value")
    public void setCode(Integer code) {
        this.code = code;
    }

    public SkipInFiling withCode(Integer code) {
        this.code = code;
        return this;
    }

    /**
     * 
     * @return
     *     The description
     */
    @JsonProperty("label")
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    @JsonProperty("label")
    public void setDescription(String description) {
        this.description = description;
    }

    public SkipInFiling withDescription(String description) {
        this.description = description;
        return this;
    }
}
