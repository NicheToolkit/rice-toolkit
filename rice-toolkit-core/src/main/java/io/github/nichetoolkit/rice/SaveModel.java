package io.github.nichetoolkit.rice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.rice.enums.SaveType;

@SuppressWarnings("WeakerAccess")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SaveModel implements RestSave {

    @JsonIgnore
    protected SaveType save = SaveType.NONE;

    public SaveModel() {
    }

    public SaveModel(SaveModel.Builder builder) {
        this.save = builder.save;
    }

    @Override
    public SaveType getSave() {
        return save;
    }

    @Override
    public void setSave(SaveType save) {
        this.save = save;
    }

    @JsonIgnore
    public boolean isSaveNone() {
        return this.save == SaveType.NONE;
    }

    @JsonIgnore
    public boolean isSaveCreate() {
        return this.save == SaveType.CREATE;
    }

    @JsonIgnore
    public boolean isSaveUpdate() {
        return this.save == SaveType.UPDATE;
    }

    @JsonIgnore
    public boolean isSaveCopy() {
        return this.save == SaveType.COPY;
    }

    @JsonIgnore
    public boolean isSaveRemove() {
        return this.save == SaveType.REMOVE;
    }

    @JsonIgnore
    public boolean isSaveDelete() {
        return this.save == SaveType.DELETE;
    }

    public boolean isSaveHigher(SaveType saveType) {
        return this.save.getKey() >= saveType.getKey();
    }

    public boolean isSaveLower(SaveType saveType) {
        return this.save.getKey() < saveType.getKey();
    }

    public static SaveModel.Builder ofSave() {
        return new SaveModel.Builder();
    }

    public static class Builder {
        protected SaveType save = SaveType.NONE;

        public Builder() {
        }

        public SaveModel.Builder save(SaveType save) {
            this.save = save;
            return this;
        }

        public SaveModel.Builder save(Integer save) {
            this.save = SaveType.parseKey(save);
            return this;
        }

        public SaveModel build() {
            return new SaveModel(this);
        }
    }
}
