package io.github.nichetoolkit.rice;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.mybatis.stereotype.column.RestIdentityKey;
import io.github.nichetoolkit.rest.util.JsonUtils;
import io.github.nichetoolkit.rice.enums.OperateType;
import io.mybatis.provider.Entity;
import org.springframework.lang.NonNull;

import java.util.Date;
import java.util.Objects;

/**
 * <code>IdEntity</code>
 * <p>The type id entity class.</p>
 * @param <I> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.TimeEntity
 * @see io.github.nichetoolkit.rice.RestId
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk1.8
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class IdEntity<I> extends TimeEntity implements RestId<I> {
    /**
     * <code>id</code>
     * <p>the <code>id</code> field.</p>
     * @see com.baomidou.mybatisplus.annotation.TableId
     * @see io.mybatis.provider.Entity.Column
     * @see io.github.nichetoolkit.mybatis.stereotype.column.RestIdentityKey
     */
    /* 兼容mybatis-plus 3.x版本 */
    @TableId
    /* 兼容mybatis-mapper 2.x版本 */
    @Entity.Column(id = true)
    @RestIdentityKey
    protected I id;

    /**
     * <code>IdEntity</code>
     * Instantiates a new id entity.
     */
    public IdEntity() {
    }

    /**
     * <code>IdEntity</code>
     * Instantiates a new id entity.
     * @param id I <p>the id parameter is <code>I</code> type.</p>
     */
    public IdEntity(I id) {
        this.id = id;
    }

    /**
     * <code>IdEntity</code>
     * Instantiates a new id entity.
     * @param builder {@link io.github.nichetoolkit.rice.IdEntity.Builder} <p>the builder parameter is <code>Builder</code> type.</p>
     * @see io.github.nichetoolkit.rice.IdEntity.Builder
     */
    public IdEntity(IdEntity.Builder<I> builder) {
        super(builder);
        this.id = builder.id;
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
        if (!(o instanceof IdEntity)) return false;
        IdEntity<?> idEntity = (IdEntity<?>) o;
        return Objects.equals(id, idEntity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return JsonUtils.parseJson(this);
    }

    /**
     * <code>Builder</code>
     * <p>The type builder class.</p>
     * @param <I> {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rice.TimeEntity.Builder
     * @since Jdk1.8
     */
    public static class Builder<I> extends TimeEntity.Builder {
        /**
         * <code>id</code>
         * <p>the <code>id</code> field.</p>
         */
        protected I id;

        /**
         * <code>Builder</code>
         * Instantiates a new builder.
         */
        public Builder() {
        }

        /**
         * <code>id</code>
         * <p>the method.</p>
         * @param id I <p>the id parameter is <code>I</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.IdEntity.Builder} <p>the return object is <code>Builder</code> type.</p>
         */
        public IdEntity.Builder<I> id(I id) {
            this.id = id;
            return this;
        }

        @Override
        public IdEntity.Builder<I> createTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }

        @Override
        public IdEntity.Builder<I> updateTime(Date updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        @Override
        public IdEntity.Builder<I> operate(Integer operate) {
            this.operate = operate;
            return this;
        }

        @Override
        public IdEntity.Builder<I> operate(@NonNull OperateType operate) {
            this.operate = operate.getKey();
            return this;
        }

        @Override
        public IdEntity.Builder<I> logic(String logic) {
            this.logic = logic;
            return this;
        }

        @Override
        public IdEntity<I> build() {
            return new IdEntity<>(this);
        }
    }
}
