package io.github.nichetoolkit.rice.filter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.RestOperate;
import io.github.nichetoolkit.rice.enums.OperateType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.jspecify.annotations.NonNull;

import java.util.*;

/**
 * <code>OperateFilter</code>
 * <p>The operate filter class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.filter.SortFilter
 * @see lombok.Setter
 * @see lombok.Getter
 * @see lombok.experimental.SuperBuilder
 * @see java.lang.SuppressWarnings
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk17
 */
@Setter
@Getter
@SuperBuilder(builderMethodName = "ofOperateBuilder")
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
     * @see io.github.nichetoolkit.rice.enums.OperateType
     */
    protected OperateType operate;

    /**
     * <code>operates</code>
     * {@link java.util.Set} <p>The <code>operates</code> field.</p>
     * @see java.util.Set
     */
    protected Set<OperateType> operates;

    /**
     * <code>OperateFilter</code>
     * <p>Instantiates a new operate filter.</p>
     */
    public OperateFilter() {
    }

    /**
     * <code>getOperates</code>
     * <p>The get operates getter method.</p>
     * @return {@link java.util.List} <p>The get operates return object is <code>List</code> type.</p>
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
     * <p>The set operates setter method.</p>
     * @param operates {@link java.lang.Integer} <p>The operates parameter is <code>Integer</code> type.</p>
     * @see java.lang.Integer
     * @see org.jspecify.annotations.NonNull
     */
    public void setOperates(@NonNull Integer... operates) {
        this.setOperates(RestOperate.build(operates));
    }

    /**
     * <code>setOperates</code>
     * <p>The set operates setter method.</p>
     * @param operates {@link io.github.nichetoolkit.rice.enums.OperateType} <p>The operates parameter is <code>OperateType</code> type.</p>
     * @see io.github.nichetoolkit.rice.enums.OperateType
     * @see org.jspecify.annotations.NonNull
     */
    public void setOperates(@NonNull OperateType... operates) {
        this.operates = new HashSet<>(Arrays.asList(operates));
    }

    /**
     * <code>setOperates</code>
     * <p>The set operates setter method.</p>
     * @param operates {@link java.util.Collection} <p>The operates parameter is <code>Collection</code> type.</p>
     * @see java.util.Collection
     * @see org.jspecify.annotations.NonNull
     * @see com.fasterxml.jackson.annotation.JsonSetter
     */
    @JsonSetter
    public void setOperates(@NonNull Collection<OperateType> operates) {
        this.operates = new HashSet<>(operates);
    }

    /**
     * <code>addOperates</code>
     * <p>The add operates method.</p>
     * @param operates {@link java.lang.Integer} <p>The operates parameter is <code>Integer</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.filter.OperateFilter} <p>The add operates return object is <code>OperateFilter</code> type.</p>
     * @see java.lang.Integer
     * @see org.jspecify.annotations.NonNull
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
     * @return {@link io.github.nichetoolkit.rice.filter.OperateFilter} <p>The add operates return object is <code>OperateFilter</code> type.</p>
     * @see io.github.nichetoolkit.rice.enums.OperateType
     * @see org.jspecify.annotations.NonNull
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
     * @return {@link io.github.nichetoolkit.rice.filter.OperateFilter} <p>The add operates return object is <code>OperateFilter</code> type.</p>
     * @see java.util.Collection
     * @see org.jspecify.annotations.NonNull
     */
    public OperateFilter addOperates(@NonNull Collection<OperateType> operates) {
        if (GeneralUtils.isEmpty(this.operates)) {
            this.operates = new HashSet<>(operates);
        } else {
            this.operates.addAll(operates);
        }
        return this;
    }

}
