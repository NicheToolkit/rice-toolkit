package io.github.nichetoolkit.rice.jsonb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.io.Serializable;
import java.util.Objects;

/**
 * <code>Property</code>
 * <p>The type property class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.io.Serializable
 * @see lombok.Builder
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk1.8
 */
@Builder
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Property implements Serializable {
    /**
     * <code>VALUE</code>
     * {@link java.lang.String} <p>The constant <code>VALUE</code> field.</p>
     * @see java.lang.String
     */
    public static final String VALUE = "value";

    /**
     * <code>name</code>
     * {@link java.lang.String} <p>The <code>name</code> field.</p>
     * @see java.lang.String
     */
    protected String name;
    /**
     * <code>value</code>
     * {@link java.lang.Object} <p>The <code>value</code> field.</p>
     * @see java.lang.Object
     */
    protected Object value;

    /**
     * <code>Property</code>
     * <p>Instantiates a new property.</p>
     */
    public Property() {
    }

    /**
     * <code>Property</code>
     * <p>Instantiates a new property.</p>
     * @param name {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public Property(String name) {
        this.name = name;
    }

    /**
     * <code>Property</code>
     * <p>Instantiates a new property.</p>
     * @param name  {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @param value {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public Property(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null ) return false;
        if (!(o instanceof Property)) return false;
        Property property = (Property) o;
        return Objects.equals(name, property.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /**
     * <code>getName</code>
     * <p>The name getter method.</p>
     * @return {@link java.lang.String} <p>The name return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public String getName() {
        return name;
    }

    /**
     * <code>setName</code>
     * <p>The name setter method.</p>
     * @param name {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * <code>getValue</code>
     * <p>The value getter method.</p>
     * @return {@link java.lang.Object} <p>The value return object is <code>Object</code> type.</p>
     * @see java.lang.Object
     */
    public Object getValue() {
        return value;
    }

    /**
     * <code>setValue</code>
     * <p>The value setter method.</p>
     * @param value {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @see java.lang.Object
     */
    public void setValue(Object value) {
        this.value = value;
    }


}
