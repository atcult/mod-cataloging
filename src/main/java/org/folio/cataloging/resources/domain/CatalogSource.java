
package org.folio.cataloging.resources.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;


/**
 * Catalog Source Schema
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "value", "label" })
public class CatalogSource {

    @JsonProperty("value")
    private String code;
    @JsonProperty("label")
    private String description;

    /**
     * 
     * @return
     *     The code
     */
    @JsonProperty("value")
    public String getCode() {
        return code;
    }

    /**
     * 
     * @param code
     *     The code
     */
    @JsonProperty("value")
    public void setCode(String code) {
        this.code = code;
    }

    public CatalogSource withCode(String code) {
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

    public CatalogSource withDescription(String description) {
        this.description = description;
        return this;
    }

}
