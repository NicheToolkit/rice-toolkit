package io.github.nichetoolkit.rice.filter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.RestOperate;
import io.github.nichetoolkit.rice.RestSort;
import io.github.nichetoolkit.rice.builder.SqlBuilders;
import io.github.nichetoolkit.rice.enums.OperateType;
import io.github.nichetoolkit.rice.jsonb.ContainRule;
import io.github.nichetoolkit.rice.jsonb.ContrastRule;
import io.github.nichetoolkit.rice.jsonb.EqualRule;
import io.github.nichetoolkit.rice.jsonb.RangeRule;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.util.*;


/**
 * <code>NameFilter</code>
 * <p>The name filter class.</p>
 * @param <I>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @see  io.github.nichetoolkit.rice.filter.JsonbFilter
 * @see  lombok.Setter
 * @see  lombok.Getter
 * @see  lombok.EqualsAndHashCode
 * @see  java.lang.SuppressWarnings
 * @see  com.fasterxml.jackson.annotation.JsonInclude
 * @see  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings({"WeakerAccess", "unchecked", "MixedMutabilityReturnType"})
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class NameFilter<I, K> extends JsonbFilter<I, K> {
    /**
     * <code>name</code>
     * {@link java.lang.String} <p>The <code>name</code> field.</p>
     * @see  java.lang.String
     */
    protected String name;
    /**
     * <code>names</code>
     * {@link java.util.Set} <p>The <code>names</code> field.</p>
     * @see  java.util.Set
     */
    protected Set<String> names;

    /**
     * <code>NameFilter</code>
     * <p>Instantiates a new name filter.</p>
     */
    public NameFilter() {
    }

    /**
     * <code>NameFilter</code>
     * <p>Instantiates a new name filter.</p>
     * @param id I <p>The id parameter is <code>I</code> type.</p>
     */
    public NameFilter(I id) {
        super(id);
    }

    /**
     * <code>NameFilter</code>
     * <p>Instantiates a new name filter.</p>
     * @param ids I <p>The ids parameter is <code>I</code> type.</p>
     * @see  java.lang.SuppressWarnings
     */
    @SuppressWarnings(value = "unchecked")
    public NameFilter(I... ids) {
        super(ids);
    }

    /**
     * <code>NameFilter</code>
     * <p>Instantiates a new name filter.</p>
     * @param builder {@link io.github.nichetoolkit.rice.filter.NameFilter.Builder} <p>The builder parameter is <code>Builder</code> type.</p>
     * @see  io.github.nichetoolkit.rice.filter.NameFilter.Builder
     */
    public NameFilter(NameFilter.Builder<I, K> builder) {
        super(builder);
        this.name = builder.name;
        this.names = builder.names;
    }

    /**
     * <code>getNames</code>
     * <p>The get names getter method.</p>
     * @return  {@link java.util.List} <p>The get names return object is <code>List</code> type.</p>
     * @see  java.util.List
     */
    public List<String> getNames() {
        if (GeneralUtils.isNotEmpty(names)) {
            return new ArrayList<>(names);
        }
        return null;
    }

    /**
     * <code>setNames</code>
     * <p>The set names setter method.</p>
     * @param names {@link java.lang.String} <p>The names parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    public void setNames(String... names) {
        this.names = Optional.ofNullable(names).map(propertyList -> new HashSet<>(Arrays.asList(propertyList))).orElse(null);
    }

    /**
     * <code>setNames</code>
     * <p>The set names setter method.</p>
     * @param names {@link java.util.Collection} <p>The names parameter is <code>Collection</code> type.</p>
     * @see  java.util.Collection
     * @see  com.fasterxml.jackson.annotation.JsonSetter
     */
    @JsonSetter
    public void setNames(Collection<String> names) {
        this.names = Optional.ofNullable(names).map(HashSet::new).orElse(null);
    }

    /**
     * <code>addNames</code>
     * <p>The add names method.</p>
     * @param names {@link java.lang.String} <p>The names parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    public void addNames(String... names) {
        if (GeneralUtils.isEmpty(this.names)) {
            this.names = Optional.ofNullable(names).map(propertyList -> new HashSet<>(Arrays.asList(propertyList))).orElse(null);
        } else {
            Optional.ofNullable(names).ifPresent(propertyList -> this.names.addAll(Arrays.asList(propertyList)));
        }
    }

    /**
     * <code>addNames</code>
     * <p>The add names method.</p>
     * @param names {@link java.util.Collection} <p>The names parameter is <code>Collection</code> type.</p>
     * @see  java.util.Collection
     */
    public void addNames(Collection<String> names) {
        if (GeneralUtils.isEmpty(this.names)) {
            this.names = Optional.ofNullable(names).map(HashSet::new).orElse(null);
        } else {
            Optional.ofNullable(names).ifPresent(this.names::addAll);
        }
    }

    /**
     * <code>toNameSql</code>
     * <p>The to name sql method.</p>
     * @param alias {@link java.lang.String} <p>The alias parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  org.springframework.lang.NonNull
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link io.github.nichetoolkit.rice.filter.NameFilter} <p>The to name sql return object is <code>NameFilter</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public NameFilter<I,K> toNameSql(@NonNull String alias) throws RestException {
        if (GeneralUtils.isNotEmpty(this.name)) {
            SqlBuilders.like(SQL_BUILDER, alias, this.name);
        } else if (GeneralUtils.isNotEmpty(this.names)) {
            SqlBuilders.in(SQL_BUILDER, alias, this.names);
        }
        return this;
    }

    @Override
    public NameFilter<I, K> toJsonbSql(@NonNull String alias) throws RestException {
        super.toJsonbSql(alias);
        return this;
    }

    @Override
    public NameFilter<I, K> toJsonbSql(@NonNull String alias, String variable) throws RestException {
        super.toJsonbSql(alias, variable);
        return this;
    }

    @Override
    public NameFilter<I, K> toTimeSql(@NonNull String alias) throws RestException {
        super.toTimeSql(alias);
        return this;
    }

    @Override
    public NameFilter<I, K> toIdSql(@NonNull String alias) throws RestException {
        super.toIdSql(alias);
        return this;
    }

    @Override
    public NameFilter<I, K> toOperateSql(@NonNull String alias) throws RestException {
        super.toOperateSql(alias);
        return this;
    }

    /**
     * <code>Builder</code>
     * <p>The builder class.</p>
     * @param <I>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @see  io.github.nichetoolkit.rice.filter.JsonbFilter.Builder
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk1.8
     */
    public static class Builder<I, K> extends JsonbFilter.Builder<I, K> {
        /**
         * <code>name</code>
         * {@link java.lang.String} <p>The <code>name</code> field.</p>
         * @see  java.lang.String
         */
        protected String name;
        /**
         * <code>names</code>
         * {@link java.util.Set} <p>The <code>names</code> field.</p>
         * @see  java.util.Set
         */
        protected Set<String> names;

        /**
         * <code>Builder</code>
         * <p>Instantiates a new builder.</p>
         */
        public Builder() {
        }

        /**
         * <code>name</code>
         * <p>The name method.</p>
         * @param name {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
         * @see  java.lang.String
         * @return  {@link io.github.nichetoolkit.rice.filter.NameFilter.Builder} <p>The name return object is <code>Builder</code> type.</p>
         */
        public NameFilter.Builder<I, K> name(String name) {
            this.name = name;
            return this;
        }

        /**
         * <code>names</code>
         * <p>The names method.</p>
         * @param names {@link java.util.Collection} <p>The names parameter is <code>Collection</code> type.</p>
         * @see  java.util.Collection
         * @return  {@link io.github.nichetoolkit.rice.filter.NameFilter.Builder} <p>The names return object is <code>Builder</code> type.</p>
         */
        public NameFilter.Builder<I, K> names(Collection<String> names) {
            this.names = Optional.ofNullable(names).map(HashSet::new).orElse(null);
            return this;
        }

        /**
         * <code>names</code>
         * <p>The names method.</p>
         * @param names {@link java.lang.String} <p>The names parameter is <code>String</code> type.</p>
         * @see  java.lang.String
         * @return  {@link io.github.nichetoolkit.rice.filter.NameFilter.Builder} <p>The names return object is <code>Builder</code> type.</p>
         */
        public NameFilter.Builder<I, K> names(String... names) {
            this.names = Optional.ofNullable(names).map(propertyList -> new HashSet<>(Arrays.asList(propertyList))).orElse(null);
            return this;
        }

        @Override
        public NameFilter.Builder<I, K> contrasts(Collection<ContrastRule> contrasts) {
            this.contrasts = Optional.ofNullable(contrasts).map(HashSet::new).orElse(null);
            return this;
        }

        @Override
        public NameFilter.Builder<I, K> contrasts(ContrastRule... contrasts) {
            this.contrasts = Optional.ofNullable(contrasts).map(contrastList -> new HashSet<>(Arrays.asList(contrastList))).orElse(null);
            return this;
        }

        @Override
        public NameFilter.Builder<I, K> ranges(Collection<RangeRule> ranges) {
            this.ranges = Optional.ofNullable(ranges).map(HashSet::new).orElse(null);
            return this;
        }

        public NameFilter.Builder<I, K> ranges(RangeRule... ranges) {
            this.ranges = Optional.ofNullable(ranges).map(rangeList -> new HashSet<>(Arrays.asList(rangeList))).orElse(null);
            return this;
        }

        @Override
        public NameFilter.Builder<I, K> equals(Collection<EqualRule> equals) {
            this.equals = Optional.ofNullable(equals).map(HashSet::new).orElse(null);
            return this;
        }

        @Override
        public NameFilter.Builder<I, K> equals(EqualRule... equals) {
            this.equals = Optional.ofNullable(equals).map(equalList -> new HashSet<>(Arrays.asList(equalList))).orElse(null);
            return this;
        }

        @Override
        public NameFilter.Builder<I, K> contains(Collection<ContainRule> contains) {
            this.contains = Optional.ofNullable(contains).map(HashSet::new).orElse(null);
            return this;
        }

        @Override
        public NameFilter.Builder<I, K> contains(ContainRule... contains) {
            this.contains = Optional.ofNullable(contains).map(containList -> new HashSet<>(Arrays.asList(containList))).orElse(null);
            return this;
        }

        @Override
        public NameFilter.Builder<I, K> startTime(Date startTime) {
            this.startTime = startTime;
            return this;
        }

        @Override
        public NameFilter.Builder<I, K> endTime(Date endTime) {
            this.endTime = endTime;
            return this;
        }

        @Override
        public NameFilter.Builder<I, K> ids(@NonNull Collection<I> ids) {
            this.ids = new HashSet<>(ids);
            return this;
        }

        @Override
        @SuppressWarnings(value = "unchecked")
        public NameFilter.Builder<I, K> ids(@NonNull I... ids) {
            this.ids = new HashSet<>(Arrays.asList(ids));
            return this;
        }

        @Override
        public NameFilter.Builder<I, K> tablekey(K tablekey) {
            this.tablekey = tablekey;
            return this;
        }

        @Override
        public NameFilter.Builder<I, K> isRemove(boolean isRemove) {
            this.isRemove = isRemove;
            return this;
        }

        @Override
        public NameFilter.Builder<I, K> operate(OperateType operate) {
            this.operate = operate;
            return this;
        }

        @Override
        public NameFilter.Builder<I, K> operate(Integer operate) {
            this.operate = OperateType.parseKey(operate);
            return this;
        }

        @Override
        public NameFilter.Builder<I, K> operates(@NonNull Collection<OperateType> operates) {
            this.operates = new HashSet<>(operates);
            return this;
        }

        @Override
        public NameFilter.Builder<I, K> operates(@NonNull OperateType... operates) {
            this.operates = new HashSet<>(Arrays.asList(operates));
            return this;
        }

        @Override
        public NameFilter.Builder<I, K> operates(@NonNull Integer... operates) {
            this.operates = new HashSet<>(RestOperate.build(operates));
            return this;
        }

        @Override
        public NameFilter.Builder<I, K> sorts(@NonNull Collection<RestSort<?>> sorts) {
            this.sorts = new HashSet<>(sorts);
            return this;
        }

        @Override
        public NameFilter.Builder<I, K> sorts(@NonNull RestSort<?>... sorts) {
            this.sorts = new HashSet<>(Arrays.asList(sorts));
            return this;
        }

        @Override
        public NameFilter.Builder<I, K> sorts(@NonNull String... sorts) {
            this.sorts = new HashSet<>(RestSort.build(sorts));
            return this;
        }

        @Override
        public NameFilter.Builder<I, K> pageNum(Integer pageNum) {
            this.pageNum = pageNum;
            return this;
        }

        @Override
        public NameFilter.Builder<I, K> pageSize(Integer pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        @Override
        public NameFilter<I, K> build() {
            return new NameFilter<>(this);
        }
    }
}
