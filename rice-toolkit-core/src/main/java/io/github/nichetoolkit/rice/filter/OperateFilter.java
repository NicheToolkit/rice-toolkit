package io.github.nichetoolkit.rice.filter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.RestOperate;
import io.github.nichetoolkit.rice.enums.OperateType;
import org.springframework.lang.NonNull;

import java.util.*;

/**
 * <code>OperateFilter</code>
 * <p>The type operate filter class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.filter.SortFilter
 * @see java.lang.SuppressWarnings
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk1.8
 */
@SuppressWarnings({"WeakerAccess", "MixedMutabilityReturnType"})
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OperateFilter extends SortFilter {
    /**
     * <code>isRemove</code>
     * <p>the <code>isRemove</code> field.</p>
     */
    protected boolean isRemove;

    /**
     * <code>operate</code>
     * {@link io.github.nichetoolkit.rice.enums.OperateType} <p>the <code>operate</code> field.</p>
     * @see io.github.nichetoolkit.rice.enums.OperateType
     */
    protected OperateType operate;

    /**
     * <code>operates</code>
     * {@link java.util.Set} <p>the <code>operates</code> field.</p>
     * @see java.util.Set
     */
    protected Set<OperateType> operates;

    /**
     * <code>OperateFilter</code>
     * Instantiates a new operate filter.
     */
    public OperateFilter() {
    }

    /**
     * <code>OperateFilter</code>
     * Instantiates a new operate filter.
     * @param builder {@link io.github.nichetoolkit.rice.filter.OperateFilter.Builder} <p>the builder parameter is <code>Builder</code> type.</p>
     * @see io.github.nichetoolkit.rice.filter.OperateFilter.Builder
     */
    public OperateFilter(OperateFilter.Builder builder) {
        super(builder);
        this.isRemove = builder.isRemove;
        this.operate = builder.operate;
        this.operates = builder.operates;
    }

    /**
     * <code>isRemove</code>
     * <p>the remove method.</p>
     * @return boolean <p>the remove return object is <code>boolean</code> type.</p>
     */
    public boolean isRemove() {
        return isRemove;
    }

    /**
     * <code>setRemove</code>
     * <p>the remove setter method.</p>
     * @param remove boolean <p>the remove parameter is <code>boolean</code> type.</p>
     */
    public void setRemove(boolean remove) {
        isRemove = remove;
    }

    /**
     * <code>getOperate</code>
     * <p>the operate getter method.</p>
     * @return {@link io.github.nichetoolkit.rice.enums.OperateType} <p>the operate return object is <code>OperateType</code> type.</p>
     * @see io.github.nichetoolkit.rice.enums.OperateType
     */
    public OperateType getOperate() {
        return operate;
    }

    /**
     * <code>setOperate</code>
     * <p>the operate setter method.</p>
     * @param operate {@link io.github.nichetoolkit.rice.enums.OperateType} <p>the operate parameter is <code>OperateType</code> type.</p>
     * @see io.github.nichetoolkit.rice.enums.OperateType
     */
    public void setOperate(OperateType operate) {
        this.operate = operate;
    }

    /**
     * <code>getOperates</code>
     * <p>the operates getter method.</p>
     * @return {@link java.util.List} <p>the operates return object is <code>List</code> type.</p>
     * @see java.util.List
     */
    public List<OperateType> getOperates() {
        if (GeneralUtils.isNotEmpty(operates)) {
            return new ArrayList<>(operates);
        }
        return Collections.emptyList();
    }

    /**
     * <code>setOperates</code>
     * <p>the operates setter method.</p>
     * @param operates {@link java.lang.Integer} <p>the operates parameter is <code>Integer</code> type.</p>
     * @see java.lang.Integer
     * @see org.springframework.lang.NonNull
     */
    public void setOperates(@NonNull Integer... operates) {
        this.setOperates(RestOperate.build(operates));
    }

    /**
     * <code>setOperates</code>
     * <p>the operates setter method.</p>
     * @param operates {@link io.github.nichetoolkit.rice.enums.OperateType} <p>the operates parameter is <code>OperateType</code> type.</p>
     * @see io.github.nichetoolkit.rice.enums.OperateType
     * @see org.springframework.lang.NonNull
     */
    public void setOperates(@NonNull OperateType... operates) {
        this.operates = new HashSet<>(Arrays.asList(operates));
    }

    /**
     * <code>setOperates</code>
     * <p>the operates setter method.</p>
     * @param operates {@link java.util.Collection} <p>the operates parameter is <code>Collection</code> type.</p>
     * @see java.util.Collection
     * @see org.springframework.lang.NonNull
     * @see com.fasterxml.jackson.annotation.JsonSetter
     */
    @JsonSetter
    public void setOperates(@NonNull Collection<OperateType> operates) {
        this.operates = new HashSet<>(operates);
    }

    /**
     * <code>addOperates</code>
     * <p>the operates method.</p>
     * @param operates {@link java.lang.Integer} <p>the operates parameter is <code>Integer</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.filter.OperateFilter} <p>the operates return object is <code>OperateFilter</code> type.</p>
     * @see java.lang.Integer
     * @see org.springframework.lang.NonNull
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
     * <p>the operates method.</p>
     * @param operates {@link io.github.nichetoolkit.rice.enums.OperateType} <p>the operates parameter is <code>OperateType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.filter.OperateFilter} <p>the operates return object is <code>OperateFilter</code> type.</p>
     * @see io.github.nichetoolkit.rice.enums.OperateType
     * @see org.springframework.lang.NonNull
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
     * <p>the operates method.</p>
     * @param operates {@link java.util.Collection} <p>the operates parameter is <code>Collection</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.filter.OperateFilter} <p>the operates return object is <code>OperateFilter</code> type.</p>
     * @see java.util.Collection
     * @see org.springframework.lang.NonNull
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
     * <p>The type builder class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rice.filter.SortFilter.Builder
     * @since Jdk1.8
     */
    public static class Builder extends SortFilter.Builder {
        /**
         * <code>isRemove</code>
         * <p>the <code>isRemove</code> field.</p>
         */
        protected boolean isRemove;
        /**
         * <code>operate</code>
         * {@link io.github.nichetoolkit.rice.enums.OperateType} <p>the <code>operate</code> field.</p>
         * @see io.github.nichetoolkit.rice.enums.OperateType
         */
        protected OperateType operate;
        /**
         * <code>operates</code>
         * {@link java.util.Set} <p>the <code>operates</code> field.</p>
         * @see java.util.Set
         */
        protected Set<OperateType> operates;

        /**
         * <code>Builder</code>
         * Instantiates a new builder.
         */
        public Builder() {
        }

        /**
         * <code>isRemove</code>
         * <p>the remove method.</p>
         * @param isRemove boolean <p>the is remove parameter is <code>boolean</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.filter.OperateFilter.Builder} <p>the remove return object is <code>Builder</code> type.</p>
         */
        public OperateFilter.Builder isRemove(boolean isRemove) {
            this.isRemove = isRemove;
            return this;
        }

        /**
         * <code>operate</code>
         * <p>the method.</p>
         * @param operate {@link io.github.nichetoolkit.rice.enums.OperateType} <p>the operate parameter is <code>OperateType</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.filter.OperateFilter.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see io.github.nichetoolkit.rice.enums.OperateType
         */
        public OperateFilter.Builder operate(OperateType operate) {
            this.operate = operate;
            return this;
        }

        /**
         * <code>operate</code>
         * <p>the method.</p>
         * @param operate {@link java.lang.Integer} <p>the operate parameter is <code>Integer</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.filter.OperateFilter.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see java.lang.Integer
         */
        public OperateFilter.Builder operate(Integer operate) {
            this.operate = OperateType.parseKey(operate);
            return this;
        }

        /**
         * <code>operates</code>
         * <p>the method.</p>
         * @param operates {@link java.util.Collection} <p>the operates parameter is <code>Collection</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.filter.OperateFilter.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see java.util.Collection
         * @see org.springframework.lang.NonNull
         */
        public OperateFilter.Builder operates(@NonNull Collection<OperateType> operates) {
            this.operates = new HashSet<>(operates);
            return this;
        }

        /**
         * <code>operates</code>
         * <p>the method.</p>
         * @param operates {@link io.github.nichetoolkit.rice.enums.OperateType} <p>the operates parameter is <code>OperateType</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.filter.OperateFilter.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see io.github.nichetoolkit.rice.enums.OperateType
         * @see org.springframework.lang.NonNull
         */
        public OperateFilter.Builder operates(@NonNull OperateType... operates) {
            this.operates = new HashSet<>(Arrays.asList(operates));
            return this;
        }

        /**
         * <code>operates</code>
         * <p>the method.</p>
         * @param operates {@link java.lang.Integer} <p>the operates parameter is <code>Integer</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.filter.OperateFilter.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see java.lang.Integer
         * @see org.springframework.lang.NonNull
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
