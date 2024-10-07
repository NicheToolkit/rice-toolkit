package io.github.nichetoolkit.rice.filter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.github.nichetoolkit.mybatis.enums.SortType;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.RestSort;
import org.springframework.lang.NonNull;

import java.util.*;

/**
 * <code>SortFilter</code>
 * <p>The type sort filter class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.filter.PageFilter
 * @see java.lang.SuppressWarnings
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk1.8
 */
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
     */
    @JsonIgnore
    protected boolean isSort = true;

    /**
     * <code>SortFilter</code>
     * <p>Instantiates a new sort filter.</p>
     */
    public SortFilter() {
    }

    /**
     * <code>SortFilter</code>
     * <p>Instantiates a new sort filter.</p>
     * @param sorts {@link io.github.nichetoolkit.rice.RestSort} <p>The sorts parameter is <code>RestSort</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestSort
     * @see org.springframework.lang.NonNull
     */
    public SortFilter(@NonNull RestSort<?>... sorts) {
        this.sorts = new LinkedHashSet<>(Arrays.asList(sorts));
    }

    /**
     * <code>SortFilter</code>
     * <p>Instantiates a new sort filter.</p>
     * @param sorts {@link java.lang.String} <p>The sorts parameter is <code>String</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.lang.NonNull
     */
    public SortFilter(@NonNull String... sorts) {
        this.sorts = new LinkedHashSet<>(RestSort.build(sorts));
    }

    /**
     * <code>SortFilter</code>
     * <p>Instantiates a new sort filter.</p>
     * @param sorts {@link java.util.Collection} <p>The sorts parameter is <code>Collection</code> type.</p>
     * @see java.util.Collection
     * @see org.springframework.lang.NonNull
     */
    public SortFilter(@NonNull Collection<String> sorts) {
        this.sorts = new LinkedHashSet<>(RestSort.build(sorts));
    }

    /**
     * <code>SortFilter</code>
     * <p>Instantiates a new sort filter.</p>
     * @param builder {@link io.github.nichetoolkit.rice.filter.SortFilter.Builder} <p>The builder parameter is <code>Builder</code> type.</p>
     * @see io.github.nichetoolkit.rice.filter.SortFilter.Builder
     */
    public SortFilter(SortFilter.Builder builder) {
        super(builder);
        this.sorts = builder.sorts;
    }

    /**
     * <code>getSorts</code>
     * <p>The sorts getter method.</p>
     * @return {@link java.util.List} <p>The sorts return object is <code>List</code> type.</p>
     * @see java.util.List
     */
    public List<RestSort<?>> getSorts() {
        if (GeneralUtils.isNotEmpty(sorts)) {
            return new ArrayList<>(sorts);
        }
        return Collections.emptyList();
    }

    /**
     * <code>isSort</code>
     * <p>The sort method.</p>
     * @return boolean <p>The sort return object is <code>boolean</code> type.</p>
     */
    public boolean isSort() {
        return isSort;
    }

    /**
     * <code>setSort</code>
     * <p>The sort setter method.</p>
     * @param sort boolean <p>The sort parameter is <code>boolean</code> type.</p>
     */
    public void setSort(boolean sort) {
        isSort = sort;
    }

    /**
     * <code>setSorts</code>
     * <p>The sorts setter method.</p>
     * @param sorts {@link java.lang.String} <p>The sorts parameter is <code>String</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.lang.NonNull
     */
    public void setSorts(@NonNull String... sorts) {
        this.setSorts(RestSort.build(sorts));
    }

    /**
     * <code>setSorts</code>
     * <p>The sorts setter method.</p>
     * @param sorts {@link io.github.nichetoolkit.rice.RestSort} <p>The sorts parameter is <code>RestSort</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestSort
     * @see org.springframework.lang.NonNull
     */
    public void setSorts(@NonNull RestSort<?>... sorts) {
        this.sorts = new LinkedHashSet<>(Arrays.asList(sorts));
    }

    /**
     * <code>setSorts</code>
     * <p>The sorts setter method.</p>
     * @param sorts {@link java.util.Collection} <p>The sorts parameter is <code>Collection</code> type.</p>
     * @see java.util.Collection
     * @see org.springframework.lang.NonNull
     * @see com.fasterxml.jackson.annotation.JsonSetter
     */
    @JsonSetter
    public void setSorts(@NonNull Collection<RestSort<?>> sorts) {
        this.sorts = new LinkedHashSet<>(sorts);
    }

    /**
     * <code>addSorts</code>
     * <p>The sorts method.</p>
     * @param sorts {@link java.lang.String} <p>The sorts parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.filter.SortFilter} <p>The sorts return object is <code>SortFilter</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.lang.NonNull
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
     * <p>The sorts method.</p>
     * @param sorts {@link io.github.nichetoolkit.rice.RestSort} <p>The sorts parameter is <code>RestSort</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.filter.SortFilter} <p>The sorts return object is <code>SortFilter</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestSort
     * @see org.springframework.lang.NonNull
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
     * <p>The sorts method.</p>
     * @param sorts {@link java.util.Collection} <p>The sorts parameter is <code>Collection</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.filter.SortFilter} <p>The sorts return object is <code>SortFilter</code> type.</p>
     * @see java.util.Collection
     * @see org.springframework.lang.NonNull
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
     * <p>The sort method.</p>
     * @return {@link java.lang.String} <p>The sort return object is <code>String</code> type.</p>
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

    /**
     * <code>Builder</code>
     * <p>The type builder class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rice.filter.PageFilter.Builder
     * @since Jdk1.8
     */
    public static class Builder extends PageFilter.Builder {
        /**
         * <code>sorts</code>
         * {@link java.util.Set} <p>The <code>sorts</code> field.</p>
         * @see java.util.Set
         */
        protected Set<RestSort<?>> sorts;

        /**
         * <code>Builder</code>
         * <p>Instantiates a new builder.</p>
         */
        public Builder() {
        }

        /**
         * <code>sorts</code>
         * <p>The method.</p>
         * @param sorts {@link java.util.Collection} <p>The sorts parameter is <code>Collection</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.filter.SortFilter.Builder} <p>The return object is <code>Builder</code> type.</p>
         * @see java.util.Collection
         * @see org.springframework.lang.NonNull
         */
        public SortFilter.Builder sorts(@NonNull Collection<RestSort<?>> sorts) {
            this.sorts = new LinkedHashSet<>(sorts);
            return this;
        }

        /**
         * <code>sorts</code>
         * <p>The method.</p>
         * @param sorts {@link io.github.nichetoolkit.rice.RestSort} <p>The sorts parameter is <code>RestSort</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.filter.SortFilter.Builder} <p>The return object is <code>Builder</code> type.</p>
         * @see io.github.nichetoolkit.rice.RestSort
         * @see org.springframework.lang.NonNull
         */
        public SortFilter.Builder sorts(@NonNull RestSort<?>... sorts) {
            this.sorts = new LinkedHashSet<>(Arrays.asList(sorts));
            return this;
        }

        /**
         * <code>sorts</code>
         * <p>The method.</p>
         * @param sorts {@link java.lang.String} <p>The sorts parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.filter.SortFilter.Builder} <p>The return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         * @see org.springframework.lang.NonNull
         */
        public SortFilter.Builder sorts(@NonNull String... sorts) {
            this.sorts = new LinkedHashSet<>(RestSort.build(sorts));
            return this;
        }

        @Override
        public SortFilter.Builder pageNum(Integer pageNum) {
            this.pageNum = pageNum;
            return this;
        }

        @Override
        public SortFilter.Builder pageSize(Integer pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        @Override
        public SortFilter build() {
            return new SortFilter(this);
        }
    }
}
