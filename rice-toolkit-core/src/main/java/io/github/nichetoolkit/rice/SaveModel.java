package io.github.nichetoolkit.rice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.rice.enums.SaveType;

/**
 * <code>SaveModel</code>
 * <p>The save model class.</p>
 * @see  io.github.nichetoolkit.rice.RestSave
 * @see  java.lang.SuppressWarnings
 * @see  com.fasterxml.jackson.annotation.JsonInclude
 * @see  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@SuppressWarnings("WeakerAccess")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SaveModel implements RestSave {

    /**
     * <code>save</code>
     * {@link io.github.nichetoolkit.rice.enums.SaveType} <p>The <code>save</code> field.</p>
     * @see  io.github.nichetoolkit.rice.enums.SaveType
     * @see  com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    protected SaveType save = SaveType.NONE;

    /**
     * <code>SaveModel</code>
     * <p>Instantiates a new save model.</p>
     */
    public SaveModel() {
    }

    /**
     * <code>SaveModel</code>
     * <p>Instantiates a new save model.</p>
     * @param builder {@link io.github.nichetoolkit.rice.SaveModel.Builder} <p>The builder parameter is <code>Builder</code> type.</p>
     * @see  io.github.nichetoolkit.rice.SaveModel.Builder
     */
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

    /**
     * <code>isSaveNone</code>
     * <p>The is save none method.</p>
     * @return boolean <p>The is save none return object is <code>boolean</code> type.</p>
     * @see  com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    public boolean isSaveNone() {
        return this.save == SaveType.NONE;
    }

    /**
     * <code>isSaveCreate</code>
     * <p>The is save create method.</p>
     * @return boolean <p>The is save create return object is <code>boolean</code> type.</p>
     * @see  com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    public boolean isSaveCreate() {
        return this.save == SaveType.CREATE;
    }

    /**
     * <code>isSaveUpdate</code>
     * <p>The is save update method.</p>
     * @return boolean <p>The is save update return object is <code>boolean</code> type.</p>
     * @see  com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    public boolean isSaveUpdate() {
        return this.save == SaveType.UPDATE;
    }

    /**
     * <code>isSaveCopy</code>
     * <p>The is save copy method.</p>
     * @return boolean <p>The is save copy return object is <code>boolean</code> type.</p>
     * @see  com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    public boolean isSaveCopy() {
        return this.save == SaveType.COPY;
    }

    /**
     * <code>isSaveRemove</code>
     * <p>The is save remove method.</p>
     * @return boolean <p>The is save remove return object is <code>boolean</code> type.</p>
     * @see  com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    public boolean isSaveRemove() {
        return this.save == SaveType.REMOVE;
    }

    /**
     * <code>isSaveDelete</code>
     * <p>The is save delete method.</p>
     * @return boolean <p>The is save delete return object is <code>boolean</code> type.</p>
     * @see  com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    public boolean isSaveDelete() {
        return this.save == SaveType.DELETE;
    }

    /**
     * <code>isSaveHigher</code>
     * <p>The is save higher method.</p>
     * @param saveType {@link io.github.nichetoolkit.rice.enums.SaveType} <p>The save type parameter is <code>SaveType</code> type.</p>
     * @see  io.github.nichetoolkit.rice.enums.SaveType
     * @return boolean <p>The is save higher return object is <code>boolean</code> type.</p>
     */
    public boolean isSaveHigher(SaveType saveType) {
        return this.save.getKey() >= saveType.getKey();
    }

    /**
     * <code>isSaveLower</code>
     * <p>The is save lower method.</p>
     * @param saveType {@link io.github.nichetoolkit.rice.enums.SaveType} <p>The save type parameter is <code>SaveType</code> type.</p>
     * @see  io.github.nichetoolkit.rice.enums.SaveType
     * @return boolean <p>The is save lower return object is <code>boolean</code> type.</p>
     */
    public boolean isSaveLower(SaveType saveType) {
        return this.save.getKey() < saveType.getKey();
    }

    /**
     * <code>ofSave</code>
     * <p>The of save method.</p>
     * @return  {@link io.github.nichetoolkit.rice.SaveModel.Builder} <p>The of save return object is <code>Builder</code> type.</p>
     * @see  io.github.nichetoolkit.rice.SaveModel.Builder
     */
    public static SaveModel.Builder ofSave() {
        return new SaveModel.Builder();
    }

    /**
     * <code>Builder</code>
     * <p>The builder class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk1.8
     */
    public static class Builder {
        /**
         * <code>save</code>
         * {@link io.github.nichetoolkit.rice.enums.SaveType} <p>The <code>save</code> field.</p>
         * @see  io.github.nichetoolkit.rice.enums.SaveType
         */
        protected SaveType save = SaveType.NONE;

        /**
         * <code>Builder</code>
         * <p>Instantiates a new builder.</p>
         */
        public Builder() {
        }

        /**
         * <code>save</code>
         * <p>The save method.</p>
         * @param save {@link io.github.nichetoolkit.rice.enums.SaveType} <p>The save parameter is <code>SaveType</code> type.</p>
         * @see  io.github.nichetoolkit.rice.enums.SaveType
         * @return  {@link io.github.nichetoolkit.rice.SaveModel.Builder} <p>The save return object is <code>Builder</code> type.</p>
         */
        public SaveModel.Builder save(SaveType save) {
            this.save = save;
            return this;
        }

        /**
         * <code>save</code>
         * <p>The save method.</p>
         * @param save {@link java.lang.Integer} <p>The save parameter is <code>Integer</code> type.</p>
         * @see  java.lang.Integer
         * @return  {@link io.github.nichetoolkit.rice.SaveModel.Builder} <p>The save return object is <code>Builder</code> type.</p>
         */
        public SaveModel.Builder save(Integer save) {
            this.save = SaveType.parseKey(save);
            return this;
        }

        /**
         * <code>build</code>
         * <p>The build method.</p>
         * @return  {@link io.github.nichetoolkit.rice.SaveModel} <p>The build return object is <code>SaveModel</code> type.</p>
         */
        public SaveModel build() {
            return new SaveModel(this);
        }
    }
}
