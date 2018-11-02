package org.folio.marccat.resources.domain;

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
@JsonPropertyOrder({"categories"})
public class IndexCategoryCollection {

  /**
   * (Required)
   */
  @JsonProperty("categories")
  @Valid
  @NotNull
  private List <Category> categories = new ArrayList <Category>();

  @JsonIgnore
  private Map <String, Object> additionalProperties = new HashMap <String, Object>();

  /**
   * (Required)
   *
   * @return The categories
   */
  @JsonProperty("categories")
  public List <Category> getCategories() {
    return categories;
  }

  /**
   * (Required)
   *
   * @param categories The categories
   */
  @JsonProperty("categories")
  public void setCategories(List <Category> categories) {
    this.categories = categories;
  }

  public IndexCategoryCollection withCategories(List <Category> categories) {
    this.categories = categories;
    return this;
  }

  @JsonAnyGetter
  public Map <String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  @JsonAnySetter
  public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
  }

  public IndexCategoryCollection withAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
    return this;
  }
}
