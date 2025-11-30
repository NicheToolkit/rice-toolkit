package io.github.nichetoolkit.rice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.mybatis.column.RestUniqueKey;
import lombok.experimental.SuperBuilder;

/**
 * <code>InfoEntity</code>
 * <p>The info entity class.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.IdEntity
 * @see io.github.nichetoolkit.rice.RestInfo
 * @see lombok.experimental.SuperBuilder
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk17
 */
@SuperBuilder(builderMethodName = "ofInfoBuilder")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class InfoEntity<I> extends IdEntity<I> implements RestInfo<I> {
    /**
     * <code>name</code>
     * {@link java.lang.String} <p>The <code>name</code> field.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.mybatis.column.RestUniqueKey
     */
    @RestUniqueKey
    protected String name;
    /**
     * <code>description</code>
     * {@link java.lang.String} <p>The <code>description</code> field.</p>
     * @see java.lang.String
     */
    protected String description;

    /**
     * <code>InfoEntity</code>
     * <p>Instantiates a new info entity.</p>
     */
    public InfoEntity() {
    }

    /**
     * <code>InfoEntity</code>
     * <p>Instantiates a new info entity.</p>
     * @param id I <p>The id parameter is <code>I</code> type.</p>
     */
    public InfoEntity(I id) {
        super(id);
    }

    /**
     * <code>InfoEntity</code>
     * <p>Instantiates a new info entity.</p>
     * @param id   I <p>The id parameter is <code>I</code> type.</p>
     * @param name {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public InfoEntity(I id, String name) {
        super(id);
        this.name = name;
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
