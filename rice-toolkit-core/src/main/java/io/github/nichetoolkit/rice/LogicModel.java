package io.github.nichetoolkit.rice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.rice.enums.SaveType;

@SuppressWarnings("WeakerAccess")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LogicModel extends SaveModel implements RestLogic {

    @JsonIgnore
    protected String logic;

    public LogicModel() {
    }

    public LogicModel(LogicModel.Builder builder) {
        super(builder);
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

    public static LogicModel.Builder ofLogic() {
        return new LogicModel.Builder();
    }

    public static class Builder extends SaveModel.Builder {
        protected String logic;

        public Builder() {
        }

        public LogicModel.Builder logic(String logic) {
            this.logic = logic;
            return this;
        }

        @Override
        public LogicModel.Builder save(SaveType save) {
            this.save = save;
            return this;
        }

        @Override
        public LogicModel.Builder save(Integer save) {
            this.save = SaveType.parseKey(save);
            return this;
        }

        @Override
        public LogicModel build() {
            return new LogicModel(this);
        }
    }
}
