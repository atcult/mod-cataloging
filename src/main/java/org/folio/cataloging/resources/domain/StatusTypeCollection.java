package org.folio.cataloging.resources.domain;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "statusTypes" })
public class StatusTypeCollection {

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("statusTypes")
    @Valid
    @NotNull
    private List<StatusType> statusTypes = new ArrayList<StatusType>();

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * (Required)
     * 
     * @return
     *     The statusTypes
     */
    @JsonProperty("statusTypes")
    public List<StatusType> getStatusTypes() {
        return statusTypes;
    }

    /**
     * 
     * (Required)
     * 
     * @param statusTypes
     *     The statusTypes
     */
    @JsonProperty("statusTypes")
    public void setStatusTypes(List<StatusType> statusTypes) {
        this.statusTypes = statusTypes;
    }

    public StatusTypeCollection withStatusTypes(List<StatusType> statusTypes) {
        this.statusTypes = statusTypes;
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

    public StatusTypeCollection withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }
}
