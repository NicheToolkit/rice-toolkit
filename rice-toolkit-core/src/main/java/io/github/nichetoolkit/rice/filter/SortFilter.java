package io.github.nichetoolkit.rice.filter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.github.nichetoolkit.mybatis.enums.SortType;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.RestSort;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.jspecify.annotations.NonNull;

import java.util.*;

/**
 * <code>SortFilter</code>
 * <p>The sort filter class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.filter.PageFilter
 * @see lombok.Getter
 * @see lombok.Setter
 * @see lombok.NoArgsConstructor
 * @see lombok.AllArgsConstructor
 * @see lombok.experimental.SuperBuilder
 * @see java.lang.SuppressWarnings
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk17
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(builderMethodName = "ofSortBuilder")
@SuppressWarnings({"WeakerAccess", "UnusedReturnValue", "MixedMutabilityReturnType"})
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SortFilter extends PageFilter {
    /**
     * <code>SORT_REGEX</code>
     * {@link java.lang.String} <p>The constant <code>SORT_REGEX</code> field.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    public static final String SORT_REGEX = ",";
    /**
     * <code>SORT_ORDER</code>
     * {@link java.lang.String} <p>The constant <code>SORT_ORDER</code> field.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    public static final String SORT_ORDER = " ORDER BY ";

    /**
     * <code>sorts</code>
     * {@link java.util.Set} <p>The <code>sorts</code> field.</p>
     * @see java.util.Set
     */
    protected Set<RestSort<?>> sorts;

    /**
     * <code>isSort</code>
     * <p>The <code>isSort</code> field.</p>
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     * @see lombok.Builder.Default
     */
    @JsonIgnore
    @Builder.Default
    protected boolean isSort = true;

    /**
     * <code>SortFilter</code>
     * <p>Instantiates a new sort filter.</p>
     * @param sorts {@link io.github.nichetoolkit.rice.RestSort} <p>The sorts parameter is <code>RestSort</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestSort
     * @see org.jspecify.annotations.NonNull
     */
    public SortFilter(@NonNull RestSort<?>... sorts) {
        this();
        this.sorts = new LinkedHashSet<>(Arrays.asList(sorts));
    }

    /**
     * <code>SortFilter</code>
     * <p>Instantiates a new sort filter.</p>
     * @param sorts {@link java.lang.String} <p>The sorts parameter is <code>String</code> type.</p>
     * @see java.lang.String
     * @see org.jspecify.annotations.NonNull
     */
    public SortFilter(@NonNull String... sorts) {
        this();
        this.sorts = new LinkedHashSet<>(RestSort.build(sorts));
    }

    /**
     * <code>SortFilter</code>
     * <p>Instantiates a new sort filter.</p>
     * @param sorts {@link java.util.Collection} <p>The sorts parameter is <code>Collection</code> type.</p>
     * @see java.util.Collection
     * @see org.jspecify.annotations.NonNull
     */
    public SortFilter(@NonNull Collection<String> sorts) {
        this();
        this.sorts = new LinkedHashSet<>(RestSort.build(sorts));
    }

    /**
     * <code>getSorts</code>
     * <p>The get sorts getter method.</p>
     * @return {@link java.util.List} <p>The get sorts return object is <code>List</code> type.</p>
     * @see java.util.List
     */
    public List<RestSort<?>> getSorts() {
        if (GeneralUtils.isNotEmpty(sorts)) {
            return new ArrayList<>(sorts);
        }
        return Collections.emptyList();
    }

    /**
     * <code>setSorts</code>
     * <p>The set sorts setter method.</p>
     * @param sorts {@link java.lang.String} <p>The sorts parameter is <code>String</code> type.</p>
     * @see java.lang.String
     * @see org.jspecify.annotations.NonNull
     */
    public void setSorts(@NonNull String... sorts) {
        this.setSorts(RestSort.build(sorts));
    }

    /**
     * <code>setSorts</code>
     * <p>The set sorts setter method.</p>
     * @param sorts {@link io.github.nichetoolkit.rice.RestSort} <p>The sorts parameter is <code>RestSort</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestSort
     * @see org.jspecify.annotations.NonNull
     */
    public void setSorts(@NonNull RestSort<?>... sorts) {
        this.sorts = new LinkedHashSet<>(Arrays.asList(sorts));
    }

    /**
     * <code>setSorts</code>
     * <p>The set sorts setter method.</p>
     * @param sorts {@link java.util.Collection} <p>The sorts parameter is <code>Collection</code> type.</p>
     * @see java.util.Collection
     * @see org.jspecify.annotations.NonNull
     * @see com.fasterxml.jackson.annotation.JsonSetter
     */
    @JsonSetter
    public void setSorts(@NonNull Collection<RestSort<?>> sorts) {
        this.sorts = new LinkedHashSet<>(sorts);
    }

    /**
     * <code>addSorts</code>
     * <p>The add sorts method.</p>
     * @param sorts {@link java.lang.String} <p>The sorts parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.filter.SortFilter} <p>The add sorts return object is <code>SortFilter</code> type.</p>
     * @see java.lang.String
     * @see org.jspecify.annotations.NonNull
     */
    public SortFilter addSorts(@NonNull String... sorts) {
        if (GeneralUtils.isEmpty(this.sorts)) {
            this.sorts = new LinkedHashSet<>(RestSort.build(sorts));
        } else {
            this.sorts.addAll(RestSort.build(sorts));
        }
        return this;
    }

    /**
     * <code>addSorts</code>
     * <p>The add sorts method.</p>
     * @param sorts {@link io.github.nichetoolkit.rice.RestSort} <p>The sorts parameter is <code>RestSort</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.filter.SortFilter} <p>The add sorts return object is <code>SortFilter</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestSort
     * @see org.jspecify.annotations.NonNull
     */
    public SortFilter addSorts(@NonNull RestSort<?>... sorts) {
        if (GeneralUtils.isEmpty(this.sorts)) {
            this.sorts = new LinkedHashSet<>(Arrays.asList(sorts));
        } else {
            this.sorts.addAll(Arrays.asList(sorts));
        }
        return this;
    }

    /**
     * <code>addSorts</code>
     * <p>The add sorts method.</p>
     * @param sorts {@link java.util.Collection} <p>The sorts parameter is <code>Collection</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.filter.SortFilter} <p>The add sorts return object is <code>SortFilter</code> type.</p>
     * @see java.util.Collection
     * @see org.jspecify.annotations.NonNull
     */
    public SortFilter addSorts(@NonNull Collection<RestSort<?>> sorts) {
        if (GeneralUtils.isEmpty(this.sorts)) {
            this.sorts = new LinkedHashSet<>(sorts);
        } else {
            this.sorts.addAll(sorts);
        }
        return this;
    }

    /**
     * <code>toSort</code>
     * <p>The to sort method.</p>
     * @return {@link java.lang.String} <p>The to sort return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public String toSort() {
        StringBuilder sortBuilder = new StringBuilder();
        if (this.isSort() && GeneralUtils.isNotEmpty(this.sorts)) {
            sortBuilder.append(SORT_ORDER);
            this.sorts.stream().filter(sort -> GeneralUtils.isNotEmpty(sort) && SortType.NONE != sort.getType())
                    .forEach(sort -> sortBuilder.append(sort).append(SORT_REGEX));
            sortBuilder.deleteCharAt(sortBuilder.length() - 1);
        }
        return sortBuilder.toString();
    }

}
