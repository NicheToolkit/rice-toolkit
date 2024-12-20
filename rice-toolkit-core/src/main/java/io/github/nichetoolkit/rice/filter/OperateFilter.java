package io.github.nichetoolkit.rice.filter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.RestOperate;
import io.github.nichetoolkit.rice.enums.OperateType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.util.*;

/**
 * <code>OperateFilter</code>
 * <p>The operate filter class.</p>
 * @see  io.github.nichetoolkit.rice.filter.SortFilter
 * @see  java.lang.SuppressWarnings
 * @see  com.fasterxml.jackson.annotation.JsonInclude
 * @see  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@SuppressWarnings({"WeakerAccess", "MixedMutabilityReturnType"})
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OperateFilter extends SortFilter {
    /**
     * <code>isRemove</code>
     * <p>The <code>isRemove</code> field.</p>
     */
    protected boolean isRemove;

    /**
     * <code>operate</code>
     * {@link io.github.nichetoolkit.rice.enums.OperateType} <p>The <code>operate</code> field.</p>
     * @see  io.github.nichetoolkit.rice.enums.OperateType
     * @see  lombok.Getter
     * @see  lombok.Setter
     */
    @Getter
    @Setter
    protected OperateType operate;

    /**
     * <code>operates</code>
     * {@link java.util.Set} <p>The <code>operates</code> field.</p>
     * @see  java.util.Set
     */
    protected Set<OperateType> operates;

    /**
     * <code>OperateFilter</code>
     * <p>Instantiates a new operate filter.</p>
     */
    public OperateFilter() {
    }

    /**
     * <code>OperateFilter</code>
     * <p>Instantiates a new operate filter.</p>
     * @param builder {@link io.github.nichetoolkit.rice.filter.OperateFilter.Builder} <p>The builder parameter is <code>Builder</code> type.</p>
     * @see  io.github.nichetoolkit.rice.filter.OperateFilter.Builder
     */
    public OperateFilter(OperateFilter.Builder builder) {
        super(builder);
        this.isRemove = builder.isRemove;
        this.operate = builder.operate;
        this.operates = builder.operates;
    }

    /**
     * <code>isRemove</code>
     * <p>The is remove method.</p>
     * @return boolean <p>The is remove return object is <code>boolean</code> type.</p>
     */
    public boolean isRemove() {
        return isRemove;
    }

    /**
     * <code>setRemove</code>
     * <p>The set remove setter method.</p>
     * @param remove boolean <p>The remove parameter is <code>boolean</code> type.</p>
     */
    public void setRemove(boolean remove) {
        isRemove = remove;
    }

    /**
     * <code>getOperates</code>
     * <p>The get operates getter method.</p>
     * @return  {@link java.util.List} <p>The get operates return object is <code>List</code> type.</p>
     * @see  java.util.List
     */
    public List<OperateType> getOperates() {
        if (GeneralUtils.isNotEmpty(operates)) {
            return new ArrayList<>(operates);
        }
        return Collections.emptyList();
    }

    /**
     * <code>setOperates</code>
     * <p>The set operates setter method.</p>
     * @param operates {@link java.lang.Integer} <p>The operates parameter is <code>Integer</code> type.</p>
     * @see  java.lang.Integer
     * @see  org.springframework.lang.NonNull
     */
    public void setOperates(@NonNull Integer... operates) {
        this.setOperates(RestOperate.build(operates));
    }

    /**
     * <code>setOperates</code>
     * <p>The set operates setter method.</p>
     * @param operates {@link io.github.nichetoolkit.rice.enums.OperateType} <p>The operates parameter is <code>OperateType</code> type.</p>
     * @see  io.github.nichetoolkit.rice.enums.OperateType
     * @see  org.springframework.lang.NonNull
     */
    public void setOperates(@NonNull OperateType... operates) {
        this.operates = new HashSet<>(Arrays.asList(operates));
    }

    /**
     * <code>setOperates</code>
     * <p>The set operates setter method.</p>
     * @param operates {@link java.util.Collection} <p>The operates parameter is <code>Collection</code> type.</p>
     * @see  java.util.Collection
     * @see  org.springframework.lang.NonNull
     * @see  com.fasterxml.jackson.annotation.JsonSetter
     */
    @JsonSetter
    public void setOperates(@NonNull Collection<OperateType> operates) {
        this.operates = new HashSet<>(operates);
    }

    /**
     * <code>addOperates</code>
     * <p>The add operates method.</p>
     * @param operates {@link java.lang.Integer} <p>The operates parameter is <code>Integer</code> type.</p>
     * @see  java.lang.Integer
     * @see  org.springframework.lang.NonNull
     * @return  {@link io.github.nichetoolkit.rice.filter.OperateFilter} <p>The add operates return object is <code>OperateFilter</code> type.</p>
     */
    public OperateFilter addOperates(@NonNull Integer... operates) {
        if (GeneralUtils.isEmpty(this.operates)) {
            this.operates = new HashSet<>(RestOperate.build(operates));
        } else {
            this.operates.addAll(RestOperate.build(operates));
        }
        return this;
    }

    /**
     * <code>addOperates</code>
     * <p>The add operates method.</p>
     * @param operates {@link io.github.nichetoolkit.rice.enums.OperateType} <p>The operates parameter is <code>OperateType</code> type.</p>
     * @see  io.github.nichetoolkit.rice.enums.OperateType
     * @see  org.springframework.lang.NonNull
     * @return  {@link io.github.nichetoolkit.rice.filter.OperateFilter} <p>The add operates return object is <code>OperateFilter</code> type.</p>
     */
    public OperateFilter addOperates(@NonNull OperateType... operates) {
        if (GeneralUtils.isEmpty(this.operates)) {
            this.operates = new HashSet<>(Arrays.asList(operates));
        } else {
            this.operates.addAll(Arrays.asList(operates));
        }
        return this;
    }

    /**
     * <code>addOperates</code>
     * <p>The add operates method.</p>
     * @param operates {@link java.util.Collection} <p>The operates parameter is <code>Collection</code> type.</p>
     * @see  java.util.Collection
     * @see  org.springframework.lang.NonNull
     * @return  {@link io.github.nichetoolkit.rice.filter.OperateFilter} <p>The add operates return object is <code>OperateFilter</code> type.</p>
     */
    public OperateFilter addOperates(@NonNull Collection<OperateType> operates) {
        if (GeneralUtils.isEmpty(this.operates)) {
            this.operates = new HashSet<>(operates);
        } else {
            this.operates.addAll(operates);
        }
        return this;
    }

    /**
     * <code>Builder</code>
     * <p>The builder class.</p>
     * @see  io.github.nichetoolkit.rice.filter.SortFilter.Builder
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk1.8
     */
    public static class Builder extends SortFilter.Builder {
        /**
         * <code>isRemove</code>
         * <p>The <code>isRemove</code> field.</p>
         */
        protected boolean isRemove;
        /**
         * <code>operate</code>
         * {@link io.github.nichetoolkit.rice.enums.OperateType} <p>The <code>operate</code> field.</p>
         * @see  io.github.nichetoolkit.rice.enums.OperateType
         */
        protected OperateType operate;
        /**
         * <code>operates</code>
         * {@link java.util.Set} <p>The <code>operates</code> field.</p>
         * @see  java.util.Set
         */
        protected Set<OperateType> operates;

        /**
         * <code>Builder</code>
         * <p>Instantiates a new builder.</p>
         */
        public Builder() {
        }

        /**
         * <code>isRemove</code>
         * <p>The is remove method.</p>
         * @param isRemove boolean <p>The is remove parameter is <code>boolean</code> type.</p>
         * @return  {@link io.github.nichetoolkit.rice.filter.OperateFilter.Builder} <p>The is remove return object is <code>Builder</code> type.</p>
         */
        public OperateFilter.Builder isRemove(boolean isRemove) {
            this.isRemove = isRemove;
            return this;
        }

        /**
         * <code>operate</code>
         * <p>The operate method.</p>
         * @param operate {@link io.github.nichetoolkit.rice.enums.OperateType} <p>The operate parameter is <code>OperateType</code> type.</p>
         * @see  io.github.nichetoolkit.rice.enums.OperateType
         * @return  {@link io.github.nichetoolkit.rice.filter.OperateFilter.Builder} <p>The operate return object is <code>Builder</code> type.</p>
         */
        public OperateFilter.Builder operate(OperateType operate) {
            this.operate = operate;
            return this;
        }

        /**
         * <code>operate</code>
         * <p>The operate method.</p>
         * @param operate {@link java.lang.Integer} <p>The operate parameter is <code>Integer</code> type.</p>
         * @see  java.lang.Integer
         * @return  {@link io.github.nichetoolkit.rice.filter.OperateFilter.Builder} <p>The operate return object is <code>Builder</code> type.</p>
         */
        public OperateFilter.Builder operate(Integer operate) {
            this.operate = OperateType.parseKey(operate);
            return this;
        }

        /**
         * <code>operates</code>
         * <p>The operates method.</p>
         * @param operates {@link java.util.Collection} <p>The operates parameter is <code>Collection</code> type.</p>
         * @see  java.util.Collection
         * @see  org.springframework.lang.NonNull
         * @return  {@link io.github.nichetoolkit.rice.filter.OperateFilter.Builder} <p>The operates return object is <code>Builder</code> type.</p>
         */
        public OperateFilter.Builder operates(@NonNull Collection<OperateType> operates) {
            this.operates = new HashSet<>(operates);
            return this;
        }

        /**
         * <code>operates</code>
         * <p>The operates method.</p>
         * @param operates {@link io.github.nichetoolkit.rice.enums.OperateType} <p>The operates parameter is <code>OperateType</code> type.</p>
         * @see  io.github.nichetoolkit.rice.enums.OperateType
         * @see  org.springframework.lang.NonNull
         * @return  {@link io.github.nichetoolkit.rice.filter.OperateFilter.Builder} <p>The operates return object is <code>Builder</code> type.</p>
         */
        public OperateFilter.Builder operates(@NonNull OperateType... operates) {
            this.operates = new HashSet<>(Arrays.asList(operates));
            return this;
        }

        /**
         * <code>operates</code>
         * <p>The operates method.</p>
         * @param operates {@link java.lang.Integer} <p>The operates parameter is <code>Integer</code> type.</p>
         * @see  java.lang.Integer
         * @see  org.springframework.lang.NonNull
         * @return  {@link io.github.nichetoolkit.rice.filter.OperateFilter.Builder} <p>The operates return object is <code>Builder</code> type.</p>
         */
        public OperateFilter.Builder operates(@NonNull Integer... operates) {
            this.operates = new HashSet<>(RestOperate.build(operates));
            return this;
        }

        @Override
        public OperateFilter.Builder pageNum(Integer pageNum) {
            this.pageNum = pageNum;
            return this;
        }

        @Override
        public OperateFilter.Builder pageSize(Integer pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        @Override
        public OperateFilter build() {
            return new OperateFilter(this);
        }
    }


}
