package io.github.nichetoolkit.rice.jsonb;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.io.Serializable;
import java.util.Objects;

/**
 * <p>Property</p>
 * @author Cyan (snow22314@outlook.com)
 * @version V.0.0.1
 * @group cyan.tool.kit
 * @date 8:45 2021/5/8
 */
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Property implements Serializable {
    public static final String VALUE = "value";

    protected String name;
    protected Object value;

    public Property() {
    }

    public Property(String name) {
        this.name = name;
    }

    public Property(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Property)) return false;
        Property property = (Property) o;
        return Objects.equals(name, property.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }


}
