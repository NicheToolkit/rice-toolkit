package io.github.nichetoolkit.rice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.rest.util.JsonUtils;
import lombok.experimental.SuperBuilder;

import java.util.Objects;

/**
 * <code>IdModel</code>
 * <p>The id model class.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.TimeModel
 * @see io.github.nichetoolkit.rice.RestId
 * @see lombok.experimental.SuperBuilder
 * @see java.lang.SuppressWarnings
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk1.8
 */
@SuppressWarnings("WeakerAccess")
@SuperBuilder(builderMethodName = "ofIdBuilder")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class IdModel<I> extends TimeModel implements RestId<I>{
    /**
     * <code>id</code>
     * <p>The <code>id</code> field.</p>
     */
    protected I id;

    /**
     * <code>IdModel</code>
     * <p>Instantiates a new id model.</p>
     */
    public IdModel() {
    }

    /**
     * <code>IdModel</code>
     * <p>Instantiates a new id model.</p>
     * @param id I <p>The id parameter is <code>I</code> type.</p>
     */
    public IdModel(I id) {
        this.id = id;
    }

    @Override
    public I getId() {
        return id;
    }

    @Override
    public void setId(I id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof IdModel)) return false;
        IdModel<?> idModel = (IdModel<?>) o;
        return Objects.equals(id, idModel.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return JsonUtils.parseJson(this);
    }

}
