package io.github.nichetoolkit.rice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.rice.enums.SaveType;

import java.io.Serializable;

/**
 * <code>SaveModel</code>
 * <p>The type save model class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestSave
 * @see java.io.Serializable
 * @see java.lang.SuppressWarnings
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk1.8
 */
@SuppressWarnings("WeakerAccess")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SaveModel implements RestSave, Serializable {

    /**
     * <code>save</code>
     * {@link io.github.nichetoolkit.rice.enums.SaveType} <p>the <code>save</code> field.</p>
     * @see io.github.nichetoolkit.rice.enums.SaveType
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    protected SaveType save = SaveType.NONE;

    /**
     * <code>logicSign</code>
     * {@link java.lang.String} <p>the <code>logicSign</code> field.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    protected String logicSign;

    /**
     * <code>SaveModel</code>
     * Instantiates a new save model.
     */
    public SaveModel() {
    }

    /**
     * <code>SaveModel</code>
     * Instantiates a new save model.
     * @param builder {@link io.github.nichetoolkit.rice.SaveModel.Builder} <p>the builder parameter is <code>Builder</code> type.</p>
     * @see io.github.nichetoolkit.rice.SaveModel.Builder
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
     * <p>the save none method.</p>
     * @return boolean <p>the save none return object is <code>boolean</code> type.</p>
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    public boolean isSaveNone() {
        return this.save == SaveType.NONE;
    }

    /**
     * <code>isSaveCreate</code>
     * <p>the save create method.</p>
     * @return boolean <p>the save create return object is <code>boolean</code> type.</p>
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    public boolean isSaveCreate() {
        return this.save == SaveType.CREATE;
    }

    /**
     * <code>isSaveUpdate</code>
     * <p>the save update method.</p>
     * @return boolean <p>the save update return object is <code>boolean</code> type.</p>
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    public boolean isSaveUpdate() {
        return this.save == SaveType.UPDATE;
    }

    /**
     * <code>isSaveCopy</code>
     * <p>the save copy method.</p>
     * @return boolean <p>the save copy return object is <code>boolean</code> type.</p>
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    public boolean isSaveCopy() {
        return this.save == SaveType.COPY;
    }

    /**
     * <code>isSaveRemove</code>
     * <p>the save remove method.</p>
     * @return boolean <p>the save remove return object is <code>boolean</code> type.</p>
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    public boolean isSaveRemove() {
        return this.save == SaveType.REMOVE;
    }

    /**
     * <code>isSaveDelete</code>
     * <p>the save delete method.</p>
     * @return boolean <p>the save delete return object is <code>boolean</code> type.</p>
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    public boolean isSaveDelete() {
        return this.save == SaveType.DELETE;
    }

    /**
     * <code>isSaveHigher</code>
     * <p>the save higher method.</p>
     * @param saveType {@link io.github.nichetoolkit.rice.enums.SaveType} <p>the save type parameter is <code>SaveType</code> type.</p>
     * @return boolean <p>the save higher return object is <code>boolean</code> type.</p>
     * @see io.github.nichetoolkit.rice.enums.SaveType
     */
    public boolean isSaveHigher(SaveType saveType) {
        return this.save.getKey() >= saveType.getKey();
    }

    /**
     * <code>isSaveLower</code>
     * <p>the save lower method.</p>
     * @param saveType {@link io.github.nichetoolkit.rice.enums.SaveType} <p>the save type parameter is <code>SaveType</code> type.</p>
     * @return boolean <p>the save lower return object is <code>boolean</code> type.</p>
     * @see io.github.nichetoolkit.rice.enums.SaveType
     */
    public boolean isSaveLower(SaveType saveType) {
        return this.save.getKey() < saveType.getKey();
    }

    /**
     * <code>getLogicSign</code>
     * <p>the logic sign getter method.</p>
     * @return {@link java.lang.String} <p>the logic sign return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public String getLogicSign() {
        return logicSign;
    }

    /**
     * <code>setLogicSign</code>
     * <p>the logic sign setter method.</p>
     * @param logicSign {@link java.lang.String} <p>the logic sign parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public void setLogicSign(String logicSign) {
        this.logicSign = logicSign;
    }

    /**
     * <code>Builder</code>
     * <p>The type builder class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk1.8
     */
    public static class Builder {
        /**
         * <code>save</code>
         * {@link io.github.nichetoolkit.rice.enums.SaveType} <p>the <code>save</code> field.</p>
         * @see io.github.nichetoolkit.rice.enums.SaveType
         */
        protected SaveType save = SaveType.NONE;

        /**
         * <code>Builder</code>
         * Instantiates a new builder.
         */
        public Builder() {
        }

        /**
         * <code>save</code>
         * <p>the method.</p>
         * @param save {@link io.github.nichetoolkit.rice.enums.SaveType} <p>the save parameter is <code>SaveType</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.SaveModel.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see io.github.nichetoolkit.rice.enums.SaveType
         */
        public SaveModel.Builder save(SaveType save) {
            this.save = save;
            return this;
        }

        /**
         * <code>save</code>
         * <p>the method.</p>
         * @param save {@link java.lang.Integer} <p>the save parameter is <code>Integer</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.SaveModel.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see java.lang.Integer
         */
        public SaveModel.Builder save(Integer save) {
            this.save = SaveType.parseKey(save);
            return this;
        }

        /**
         * <code>build</code>
         * <p>the method.</p>
         * @return {@link io.github.nichetoolkit.rice.SaveModel} <p>the return object is <code>SaveModel</code> type.</p>
         */
        public SaveModel build() {
            return new SaveModel(this);
        }
    }
}
