package org.folio.rest.jaxrs.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "retentions" })
public class RetentionCollection {

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("retentions")
    @Valid
    @NotNull
    private List<Retention> retentions = new ArrayList<Retention>();

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * (Required)
     * 
     * @return
     *     The retentions
     */
    @JsonProperty("retentions")
    public List<Retention> getRetentions() {
        return retentions;
    }

    /**
     * 
     * (Required)
     * 
     * @param retentions
     *     The retentions
     */
    @JsonProperty("retentions")
    public void setRetentions(List<Retention> retentions) {
        this.retentions = retentions;
    }

    public RetentionCollection withRetentions(List<Retention> retentions) {
        this.retentions = retentions;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public RetentionCollection withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }
}
