package io.github.nichetoolkit.rice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.mybatis.stereotype.column.RestLogicKey;

@SuppressWarnings("WeakerAccess")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LogicEntity implements RestLogic {

    @RestLogicKey
    protected String logic;

    public LogicEntity() {
    }

    public LogicEntity(LogicEntity.Builder builder) {
        this.logic = builder.logic;
    }

    @Override
    public String getLogic() {
        return logic;
    }

    @Override
    public void setLogic(String logic) {
        this.logic = logic;
    }

    @SuppressWarnings("WeakerAccess")
    public static class Builder {
        protected String logic;

        public Builder() {
        }

        public LogicEntity.Builder logic(String logic) {
            this.logic = logic;
            return this;
        }

        public LogicEntity build() {
            return new LogicEntity(this);
        }
    }
}
