package io.github.nichetoolkit.rice.jsonb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.builder.SqlBuilder;
import io.github.nichetoolkit.rice.builder.SqlBuilders;
import org.springframework.lang.NonNull;

import java.io.Serializable;

/**
 * <p>JsonbRule</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class JsonbRule<R extends JsonbRule<R>> implements Serializable {
    /** 属性名 */
    String name;

    JsonbRule() {
    }

    JsonbRule(String name) {
        this.name = name;
    }

    JsonbRule(JsonbRule.Builder builder) {
        this.name = builder.name;
    }

    abstract public String toSql(@NonNull String alias);

    abstract public String toSql(@NonNull String alias, @NonNull String variable);

    String target(String target, String name, String value, ValueType type) {
        return target(target,name,value,type.getField());
    }

    private String target(String target, String name, String value, String type) {
        SqlBuilder targetBuilder = SqlBuilders.newSqlBuilder();
        if (GeneralUtils.isNotEmpty(name) && GeneralUtils.isNotEmpty(value)) {
            targetBuilder.append("((").append(target);
            targetBuilder.append(" #>'{").append(name);
            targetBuilder.append("}') ->>'").append(value);
            targetBuilder.append("')::").append(type);
        }
        return targetBuilder.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract static class Builder<R extends JsonbRule<R>> {
        String name;

        public Builder() {
        }

        public JsonbRule.Builder name(String name) {
            this.name = name;
            return this;
        }

        abstract public R build();
    }
}
