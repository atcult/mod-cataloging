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
@JsonPropertyOrder({"catalogSources"})
public class CatalogSourceCollection {

  /**
   * (Required)
   */
  @JsonProperty("catalogSources")
  @Valid
  @NotNull
  private List <CatalogSource> catalogSources = new ArrayList <CatalogSource> ( );

  @JsonIgnore
  private Map <String, Object> additionalProperties = new HashMap <String, Object> ( );

  /**
   * (Required)
   *
   * @return The catalogSources
   */
  @JsonProperty("catalogSources")
  public List <CatalogSource> getCatalogSources() {
    return catalogSources;
  }

  /**
   * (Required)
   *
   * @param catalogSources The catalogSources
   */
  @JsonProperty("catalogSources")
  public void setCatalogSources(List <CatalogSource> catalogSources) {
    this.catalogSources = catalogSources;
  }

  public CatalogSourceCollection withCatalogSources(List <CatalogSource> catalogSources) {
    this.catalogSources = catalogSources;
    return this;
  }

  @JsonAnyGetter
  public Map <String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  @JsonAnySetter
  public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put (name, value);
  }

  public CatalogSourceCollection withAdditionalProperty(String name, Object value) {
    this.additionalProperties.put (name, value);
    return this;
  }
}
