package io.github.nichetoolkit.rice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.experimental.SuperBuilder;

/**
 * <code>InfoModel</code>
 * <p>The info model class.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.IdModel
 * @see io.github.nichetoolkit.rice.RestInfo
 * @see java.lang.SuppressWarnings
 * @see lombok.experimental.SuperBuilder
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk17
 */
@SuppressWarnings("WeakerAccess")
@SuperBuilder(builderMethodName = "ofInfoBuilder")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class InfoModel<I> extends IdModel<I> implements RestInfo<I> {
    /**
     * <code>name</code>
     * {@link java.lang.String} <p>The <code>name</code> field.</p>
     * @see java.lang.String
     */
    protected String name;
    /**
     * <code>description</code>
     * {@link java.lang.String} <p>The <code>description</code> field.</p>
     * @see java.lang.String
     */
    protected String description;

    /**
     * <code>InfoModel</code>
     * <p>Instantiates a new info model.</p>
     */
    public InfoModel() {
    }

    /**
     * <code>InfoModel</code>
     * <p>Instantiates a new info model.</p>
     * @param id I <p>The id parameter is <code>I</code> type.</p>
     */
    public InfoModel(I id) {
        super(id);
    }

    /**
     * <code>InfoModel</code>
     * <p>Instantiates a new info model.</p>
     * @param name        {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @param description {@link java.lang.String} <p>The description parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public InfoModel(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

}
