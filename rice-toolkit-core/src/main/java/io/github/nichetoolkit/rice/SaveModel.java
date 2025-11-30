package io.github.nichetoolkit.rice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.rice.enums.SaveType;
import lombok.Builder;
import lombok.experimental.SuperBuilder;

/**
 * <code>SaveModel</code>
 * <p>The save model class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestSave
 * @see java.lang.SuppressWarnings
 * @see lombok.experimental.SuperBuilder
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk17
 */
@SuppressWarnings("WeakerAccess")
@SuperBuilder(builderMethodName = "ofSaveBuilder")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SaveModel implements RestSave {

    /**
     * <code>save</code>
     * {@link io.github.nichetoolkit.rice.enums.SaveType} <p>The <code>save</code> field.</p>
     * @see io.github.nichetoolkit.rice.enums.SaveType
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     * @see lombok.Builder.Default
     */
    @JsonIgnore
    @Builder.Default
    protected SaveType save = SaveType.NONE;

    /**
     * <code>SaveModel</code>
     * <p>Instantiates a new save model.</p>
     */
    public SaveModel() {
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
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    public boolean isSaveNone() {
        return this.save == SaveType.NONE;
    }

    /**
     * <code>isSaveCreate</code>
     * <p>The is save create method.</p>
     * @return boolean <p>The is save create return object is <code>boolean</code> type.</p>
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    public boolean isSaveCreate() {
        return this.save == SaveType.CREATE;
    }

    /**
     * <code>isSaveUpdate</code>
     * <p>The is save update method.</p>
     * @return boolean <p>The is save update return object is <code>boolean</code> type.</p>
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    public boolean isSaveUpdate() {
        return this.save == SaveType.UPDATE;
    }

    /**
     * <code>isSaveCopy</code>
     * <p>The is save copy method.</p>
     * @return boolean <p>The is save copy return object is <code>boolean</code> type.</p>
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    public boolean isSaveCopy() {
        return this.save == SaveType.COPY;
    }

    /**
     * <code>isSaveRemove</code>
     * <p>The is save remove method.</p>
     * @return boolean <p>The is save remove return object is <code>boolean</code> type.</p>
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    public boolean isSaveRemove() {
        return this.save == SaveType.REMOVE;
    }

    /**
     * <code>isSaveDelete</code>
     * <p>The is save delete method.</p>
     * @return boolean <p>The is save delete return object is <code>boolean</code> type.</p>
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    public boolean isSaveDelete() {
        return this.save == SaveType.DELETE;
    }

    /**
     * <code>isSaveHigher</code>
     * <p>The is save higher method.</p>
     * @param saveType {@link io.github.nichetoolkit.rice.enums.SaveType} <p>The save type parameter is <code>SaveType</code> type.</p>
     * @return boolean <p>The is save higher return object is <code>boolean</code> type.</p>
     * @see io.github.nichetoolkit.rice.enums.SaveType
     */
    public boolean isSaveHigher(SaveType saveType) {
        return this.save.getKey() >= saveType.getKey();
    }

    /**
     * <code>isSaveLower</code>
     * <p>The is save lower method.</p>
     * @param saveType {@link io.github.nichetoolkit.rice.enums.SaveType} <p>The save type parameter is <code>SaveType</code> type.</p>
     * @return boolean <p>The is save lower return object is <code>boolean</code> type.</p>
     * @see io.github.nichetoolkit.rice.enums.SaveType
     */
    public boolean isSaveLower(SaveType saveType) {
        return this.save.getKey() < saveType.getKey();
    }

}
