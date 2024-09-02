package io.github.nichetoolkit.rice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.rice.enums.OperateType;
import io.github.nichetoolkit.mybatis.stereotype.column.RestUniqueKey;
import org.springframework.lang.NonNull;

import java.util.Date;

/**
 * <code>InfoEntity</code>
 * <p>The type info entity class.</p>
 * @param <I> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.IdEntity
 * @see io.github.nichetoolkit.rice.RestInfo
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk1.8
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class InfoEntity<I> extends IdEntity<I> implements RestInfo<I> {
    /**
     * <code>name</code>
     * {@link java.lang.String} <p>the <code>name</code> field.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.mybatis.stereotype.column.RestUniqueKey
     */
    @RestUniqueKey
    protected String name;
    /**
     * <code>description</code>
     * {@link java.lang.String} <p>the <code>description</code> field.</p>
     * @see java.lang.String
     */
    protected String description;

    /**
     * <code>InfoEntity</code>
     * Instantiates a new info entity.
     */
    public InfoEntity() {
    }

    /**
     * <code>InfoEntity</code>
     * Instantiates a new info entity.
     * @param id I <p>the id parameter is <code>I</code> type.</p>
     */
    public InfoEntity(I id) {
        super(id);
    }

    /**
     * <code>InfoEntity</code>
     * Instantiates a new info entity.
     * @param id   I <p>the id parameter is <code>I</code> type.</p>
     * @param name {@link java.lang.String} <p>the name parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public InfoEntity(I id, String name) {
        super(id);
        this.name = name;
    }

    /**
     * <code>InfoEntity</code>
     * Instantiates a new info entity.
     * @param builder {@link io.github.nichetoolkit.rice.InfoEntity.Builder} <p>the builder parameter is <code>Builder</code> type.</p>
     * @see io.github.nichetoolkit.rice.InfoEntity.Builder
     */
    public InfoEntity(InfoEntity.Builder<I> builder) {
        super(builder);
        this.name = builder.name;
        this.description = builder.description;
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

    /**
     * <code>Builder</code>
     * <p>The type builder class.</p>
     * @param <I> {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rice.IdEntity.Builder
     * @since Jdk1.8
     */
    public static class Builder<I> extends IdEntity.Builder<I> {
        /**
         * <code>name</code>
         * {@link java.lang.String} <p>the <code>name</code> field.</p>
         * @see java.lang.String
         */
        protected String name;
        /**
         * <code>description</code>
         * {@link java.lang.String} <p>the <code>description</code> field.</p>
         * @see java.lang.String
         */
        protected String description;

        /**
         * <code>Builder</code>
         * Instantiates a new builder.
         */
        public Builder() {
        }

        @Override
        public InfoEntity.Builder<I> id(I id) {
            this.id = id;
            return this;
        }

        /**
         * <code>name</code>
         * <p>the method.</p>
         * @param name {@link java.lang.String} <p>the name parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.InfoEntity.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public InfoEntity.Builder<I> name(String name) {
            this.name = name;
            return this;
        }

        /**
         * <code>description</code>
         * <p>the method.</p>
         * @param description {@link java.lang.String} <p>the description parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.InfoEntity.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public InfoEntity.Builder<I> description(String description) {
            this.description = description;
            return this;
        }

        @Override
        public InfoEntity.Builder<I> createTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }

        @Override
        public InfoEntity.Builder<I> updateTime(Date updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        @Override
        public InfoEntity.Builder<I> operate(Integer operate) {
            this.operate = operate;
            return this;
        }

        @Override
        public InfoEntity.Builder<I> operate(@NonNull OperateType operate) {
            this.operate = operate.getKey();
            return this;
        }

        @Override
        public InfoEntity.Builder<I> logicSign(String logicSign) {
            this.logicSign = logicSign;
            return this;
        }

        @Override
        public InfoEntity<I> build() {
            return new InfoEntity<>(this);
        }
    }
}
