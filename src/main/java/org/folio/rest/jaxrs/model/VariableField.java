package org.folio.rest.jaxrs.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * variableField
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "categoryCode", "headingTypeCode", "itemTypeCode", "functionCode", "ind1", "ind2", "code", "value", "description", "subfields", "defaultSubfieldCode" })
public class VariableField {

    @JsonProperty("categoryCode")
    private Integer categoryCode;

    @JsonProperty("headingTypeCode")
    private String headingTypeCode;

    @JsonProperty("itemTypeCode")
    private String itemTypeCode;

    @JsonProperty("functionCode")
    private String functionCode;

    @JsonProperty("ind1")
    @Size(min = 1, max = 1)
    private String ind1;

    @JsonProperty("ind2")
    @Size(min = 1, max = 1)
    private String ind2;

    @JsonProperty("code")
    private String code;

    @JsonProperty("value")
    private String value;

    @JsonProperty("description")
    private String description;

    @JsonProperty("subfields")
    @Valid
    private List<String> subfields = new ArrayList<String>();

    @JsonProperty("defaultSubfieldCode")
    @Pattern(regexp = "[a-z|0-9]")
    private String defaultSubfieldCode;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The categoryCode
     */
    @JsonProperty("categoryCode")
    public Integer getCategoryCode() {
        return categoryCode;
    }

    /**
     * 
     * @param categoryCode
     *     The categoryCode
     */
    @JsonProperty("categoryCode")
    public void setCategoryCode(Integer categoryCode) {
        this.categoryCode = categoryCode;
    }

    public VariableField withCategoryCode(Integer categoryCode) {
        this.categoryCode = categoryCode;
        return this;
    }

    /**
     * 
     * @return
     *     The headingTypeCode
     */
    @JsonProperty("headingTypeCode")
    public String getHeadingTypeCode() {
        return headingTypeCode;
    }

    /**
     * 
     * @param headingTypeCode
     *     The headingTypeCode
     */
    @JsonProperty("headingTypeCode")
    public void setHeadingTypeCode(String headingTypeCode) {
        this.headingTypeCode = headingTypeCode;
    }

    public VariableField withHeadingTypeCode(String headingTypeCode) {
        this.headingTypeCode = headingTypeCode;
        return this;
    }

    /**
     * 
     * @return
     *     The itemTypeCode
     */
    @JsonProperty("itemTypeCode")
    public String getItemTypeCode() {
        return itemTypeCode;
    }

    /**
     * 
     * @param itemTypeCode
     *     The itemTypeCode
     */
    @JsonProperty("itemTypeCode")
    public void setItemTypeCode(String itemTypeCode) {
        this.itemTypeCode = itemTypeCode;
    }

    public VariableField withItemTypeCode(String itemTypeCode) {
        this.itemTypeCode = itemTypeCode;
        return this;
    }

    /**
     * 
     * @return
     *     The functionCode
     */
    @JsonProperty("functionCode")
    public String getFunctionCode() {
        return functionCode;
    }

    /**
     * 
     * @param functionCode
     *     The functionCode
     */
    @JsonProperty("functionCode")
    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
    }

    public VariableField withFunctionCode(String functionCode) {
        this.functionCode = functionCode;
        return this;
    }

    /**
     * 
     * @return
     *     The ind1
     */
    @JsonProperty("ind1")
    public String getInd1() {
        return ind1;
    }

    /**
     * 
     * @param ind1
     *     The ind1
     */
    @JsonProperty("ind1")
    public void setInd1(String ind1) {
        this.ind1 = ind1;
    }

    public VariableField withInd1(String ind1) {
        this.ind1 = ind1;
        return this;
    }

    /**
     * 
     * @return
     *     The ind2
     */
    @JsonProperty("ind2")
    public String getInd2() {
        return ind2;
    }

    /**
     * 
     * @param ind2
     *     The ind2
     */
    @JsonProperty("ind2")
    public void setInd2(String ind2) {
        this.ind2 = ind2;
    }

    public VariableField withInd2(String ind2) {
        this.ind2 = ind2;
        return this;
    }

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

    public VariableField withCode(String code) {
        this.code = code;
        return this;
    }

    /**
     * 
     * @return
     *     The value
     */
    @JsonProperty("value")
    public String getValue() {
        return value;
    }

    /**
     * 
     * @param value
     *     The value
     */
    @JsonProperty("value")
    public void setValue(String value) {
        this.value = value;
    }

    public VariableField withValue(String value) {
        this.value = value;
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

    public VariableField withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * 
     * @return
     *     The subfields
     */
    @JsonProperty("subfields")
    public List<String> getSubfields() {
        return subfields;
    }

    /**
     * 
     * @param subfields
     *     The subfields
     */
    @JsonProperty("subfields")
    public void setSubfields(List<String> subfields) {
        this.subfields = subfields;
    }

    public VariableField withSubfields(List<String> subfields) {
        this.subfields = subfields;
        return this;
    }

    /**
     * 
     * @return
     *     The defaultSubfieldCode
     */
    @JsonProperty("defaultSubfieldCode")
    public String getDefaultSubfieldCode() {
        return defaultSubfieldCode;
    }

    /**
     * 
     * @param defaultSubfieldCode
     *     The defaultSubfieldCode
     */
    @JsonProperty("defaultSubfieldCode")
    public void setDefaultSubfieldCode(String defaultSubfieldCode) {
        this.defaultSubfieldCode = defaultSubfieldCode;
    }

    public VariableField withDefaultSubfieldCode(String defaultSubfieldCode) {
        this.defaultSubfieldCode = defaultSubfieldCode;
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

    public VariableField withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }
}
