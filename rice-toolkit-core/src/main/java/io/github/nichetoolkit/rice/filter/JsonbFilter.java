package io.github.nichetoolkit.rice.filter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.github.nichetoolkit.mybatis.builder.SqlBuilder;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.RestOperate;
import io.github.nichetoolkit.rice.RestSort;
import io.github.nichetoolkit.rice.builder.SqlBuilders;
import io.github.nichetoolkit.rice.enums.OperateType;
import io.github.nichetoolkit.rice.jsonb.*;
import org.springframework.lang.NonNull;

import java.util.*;

/**
 * <code>JsonbFilter</code>
 * <p>The jsonb filter class.</p>
 * @param <I>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @see  io.github.nichetoolkit.rice.filter.TimeFilter
 * @see  java.lang.SuppressWarnings
 * @see  com.fasterxml.jackson.annotation.JsonInclude
 * @see  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@SuppressWarnings({"WeakerAccess", "unchecked", "MixedMutabilityReturnType"})
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonbFilter<I, K> extends TimeFilter<I, K> {
    /**
     * <code>contrasts</code>
     * {@link java.util.Set} <p>The <code>contrasts</code> field.</p>
     * @see  java.util.Set
     */
    protected Set<ContrastRule> contrasts;
    /**
     * <code>ranges</code>
     * {@link java.util.Set} <p>The <code>ranges</code> field.</p>
     * @see  java.util.Set
     */
    protected Set<RangeRule> ranges;
    /**
     * <code>equals</code>
     * {@link java.util.Set} <p>The <code>equals</code> field.</p>
     * @see  java.util.Set
     */
    protected Set<EqualRule> equals;
    /**
     * <code>contains</code>
     * {@link java.util.Set} <p>The <code>contains</code> field.</p>
     * @see  java.util.Set
     */
    protected Set<ContainRule> contains;

    /**
     * <code>JsonbFilter</code>
     * <p>Instantiates a new jsonb filter.</p>
     */
    public JsonbFilter() {
    }

    /**
     * <code>JsonbFilter</code>
     * <p>Instantiates a new jsonb filter.</p>
     * @param id I <p>The id parameter is <code>I</code> type.</p>
     */
    public JsonbFilter(I id) {
        super(id);
    }

    /**
     * <code>JsonbFilter</code>
     * <p>Instantiates a new jsonb filter.</p>
     * @param ids I <p>The ids parameter is <code>I</code> type.</p>
     * @see  java.lang.SuppressWarnings
     */
    @SuppressWarnings(value = "unchecked")
    public JsonbFilter(I... ids) {
        super(ids);
    }

    /**
     * <code>JsonbFilter</code>
     * <p>Instantiates a new jsonb filter.</p>
     * @param builder {@link io.github.nichetoolkit.rice.filter.JsonbFilter.Builder} <p>The builder parameter is <code>Builder</code> type.</p>
     * @see  io.github.nichetoolkit.rice.filter.JsonbFilter.Builder
     */
    public JsonbFilter(JsonbFilter.Builder<I, K> builder) {
        super(builder);
        this.contrasts = builder.contrasts;
        this.ranges = builder.ranges;
        this.equals = builder.equals;
        this.contains = builder.contains;
    }

    /**
     * <code>addContains</code>
     * <p>The add contains method.</p>
     * @param contains {@link io.github.nichetoolkit.rice.jsonb.ContainRule} <p>The contains parameter is <code>ContainRule</code> type.</p>
     * @see  io.github.nichetoolkit.rice.jsonb.ContainRule
     */
    public void addContains(ContainRule... contains) {
        if (GeneralUtils.isEmpty(this.contains)) {
            this.contains = Optional.ofNullable(contains).map(containList -> new HashSet<>(Arrays.asList(containList))).orElse(null);
        } else {
            Optional.ofNullable(contains).ifPresent(containList -> this.contains.addAll(Arrays.asList(containList)));
        }
    }

    /**
     * <code>addContains</code>
     * <p>The add contains method.</p>
     * @param contains {@link java.util.Collection} <p>The contains parameter is <code>Collection</code> type.</p>
     * @see  java.util.Collection
     */
    public void addContains(Collection<ContainRule> contains) {
        if (GeneralUtils.isEmpty(this.contains)) {
            this.contains = Optional.ofNullable(contains).map(HashSet::new).orElse(null);
        } else {
            Optional.ofNullable(contains).ifPresent(this.contains::addAll);
        }
    }

    /**
     * <code>getContains</code>
     * <p>The get contains getter method.</p>
     * @return  {@link java.util.List} <p>The get contains return object is <code>List</code> type.</p>
     * @see  java.util.List
     */
    public List<ContainRule> getContains() {
        if (GeneralUtils.isNotEmpty(this.contains)) {
            return new ArrayList<>(contains);
        }
        return Collections.emptyList();
    }

    /**
     * <code>setContains</code>
     * <p>The set contains setter method.</p>
     * @param contains {@link io.github.nichetoolkit.rice.jsonb.ContainRule} <p>The contains parameter is <code>ContainRule</code> type.</p>
     * @see  io.github.nichetoolkit.rice.jsonb.ContainRule
     */
    public void setContains(ContainRule... contains) {
        this.contains = Optional.ofNullable(contains).map(containList -> new HashSet<>(Arrays.asList(containList))).orElse(null);
    }

    /**
     * <code>setContains</code>
     * <p>The set contains setter method.</p>
     * @param contains {@link java.util.Collection} <p>The contains parameter is <code>Collection</code> type.</p>
     * @see  java.util.Collection
     * @see  com.fasterxml.jackson.annotation.JsonSetter
     */
    @JsonSetter
    public void setContains(Collection<ContainRule> contains) {
        this.contains = Optional.ofNullable(contains).map(HashSet::new).orElse(null);
    }

    /**
     * <code>addContrasts</code>
     * <p>The add contrasts method.</p>
     * @param contrasts {@link io.github.nichetoolkit.rice.jsonb.ContrastRule} <p>The contrasts parameter is <code>ContrastRule</code> type.</p>
     * @see  io.github.nichetoolkit.rice.jsonb.ContrastRule
     */
    public void addContrasts(ContrastRule... contrasts) {
        if (GeneralUtils.isEmpty(this.contrasts)) {
            this.contrasts = Optional.ofNullable(contrasts).map(contrastList -> new HashSet<>(Arrays.asList(contrastList))).orElse(null);
        } else {
            Optional.ofNullable(contrasts).ifPresent(contrastList -> this.contrasts.addAll(Arrays.asList(contrastList)));
        }
    }

    /**
     * <code>addContrasts</code>
     * <p>The add contrasts method.</p>
     * @param contrasts {@link java.util.Collection} <p>The contrasts parameter is <code>Collection</code> type.</p>
     * @see  java.util.Collection
     */
    public void addContrasts(Collection<ContrastRule> contrasts) {
        if (GeneralUtils.isEmpty(this.contrasts)) {
            this.contrasts = Optional.ofNullable(contrasts).map(HashSet::new).orElse(null);
        } else {
            Optional.ofNullable(contrasts).ifPresent(this.contrasts::addAll);
        }
    }

    /**
     * <code>getContrasts</code>
     * <p>The get contrasts getter method.</p>
     * @return  {@link java.util.List} <p>The get contrasts return object is <code>List</code> type.</p>
     * @see  java.util.List
     */
    public List<ContrastRule> getContrasts() {
        if (GeneralUtils.isNotEmpty(this.contrasts)) {
            return new ArrayList<>(contrasts);
        }
        return Collections.emptyList();
    }

    /**
     * <code>setContrasts</code>
     * <p>The set contrasts setter method.</p>
     * @param contrasts {@link io.github.nichetoolkit.rice.jsonb.ContrastRule} <p>The contrasts parameter is <code>ContrastRule</code> type.</p>
     * @see  io.github.nichetoolkit.rice.jsonb.ContrastRule
     */
    public void setContrasts(ContrastRule... contrasts) {
        this.contrasts = Optional.ofNullable(contrasts).map(contrastsList -> new HashSet<>(Arrays.asList(contrastsList))).orElse(null);
    }

    /**
     * <code>setContrasts</code>
     * <p>The set contrasts setter method.</p>
     * @param contrasts {@link java.util.Collection} <p>The contrasts parameter is <code>Collection</code> type.</p>
     * @see  java.util.Collection
     * @see  com.fasterxml.jackson.annotation.JsonSetter
     */
    @JsonSetter
    public void setContrasts(Collection<ContrastRule> contrasts) {
        this.contrasts = Optional.ofNullable(contrasts).map(HashSet::new).orElse(null);
    }

    /**
     * <code>addRanges</code>
     * <p>The add ranges method.</p>
     * @param ranges {@link io.github.nichetoolkit.rice.jsonb.RangeRule} <p>The ranges parameter is <code>RangeRule</code> type.</p>
     * @see  io.github.nichetoolkit.rice.jsonb.RangeRule
     */
    public void addRanges(RangeRule... ranges) {
        if (GeneralUtils.isEmpty(this.ranges)) {
            this.ranges = Optional.ofNullable(ranges).map(rangeList -> new HashSet<>(Arrays.asList(rangeList))).orElse(null);
        } else {
            Optional.ofNullable(ranges).ifPresent(rangeList -> this.ranges.addAll(Arrays.asList(rangeList)));
        }
    }

    /**
     * <code>addRanges</code>
     * <p>The add ranges method.</p>
     * @param ranges {@link java.util.Collection} <p>The ranges parameter is <code>Collection</code> type.</p>
     * @see  java.util.Collection
     */
    public void addRanges(Collection<RangeRule> ranges) {
        if (GeneralUtils.isEmpty(this.ranges)) {
            this.ranges = Optional.ofNullable(ranges).map(HashSet::new).orElse(null);
        } else {
            Optional.ofNullable(ranges).ifPresent(this.ranges::addAll);
        }
    }

    /**
     * <code>getRanges</code>
     * <p>The get ranges getter method.</p>
     * @return  {@link java.util.List} <p>The get ranges return object is <code>List</code> type.</p>
     * @see  java.util.List
     */
    public List<RangeRule> getRanges() {
        if (GeneralUtils.isNotEmpty(this.ranges)) {
            return new ArrayList<>(ranges);
        }
        return Collections.emptyList();
    }

    /**
     * <code>setRanges</code>
     * <p>The set ranges setter method.</p>
     * @param ranges {@link io.github.nichetoolkit.rice.jsonb.RangeRule} <p>The ranges parameter is <code>RangeRule</code> type.</p>
     * @see  io.github.nichetoolkit.rice.jsonb.RangeRule
     */
    public void setRanges(RangeRule... ranges) {
        this.ranges = Optional.ofNullable(ranges).map(rangeList -> new HashSet<>(Arrays.asList(rangeList))).orElse(null);
    }

    /**
     * <code>setRanges</code>
     * <p>The set ranges setter method.</p>
     * @param ranges {@link java.util.Collection} <p>The ranges parameter is <code>Collection</code> type.</p>
     * @see  java.util.Collection
     * @see  com.fasterxml.jackson.annotation.JsonSetter
     */
    @JsonSetter
    public void setRanges(Collection<RangeRule> ranges) {
        this.ranges = Optional.ofNullable(ranges).map(HashSet::new).orElse(null);
    }

    /**
     * <code>addEquals</code>
     * <p>The add equals method.</p>
     * @param equals {@link io.github.nichetoolkit.rice.jsonb.EqualRule} <p>The equals parameter is <code>EqualRule</code> type.</p>
     * @see  io.github.nichetoolkit.rice.jsonb.EqualRule
     */
    public void addEquals(EqualRule... equals) {
        if (GeneralUtils.isEmpty(this.equals)) {
            this.equals = Optional.ofNullable(equals).map(equalList -> new HashSet<>(Arrays.asList(equalList))).orElse(null);
        } else {
            Optional.ofNullable(equals).ifPresent(equalList -> this.equals.addAll(Arrays.asList(equalList)));
        }
    }

    /**
     * <code>addEquals</code>
     * <p>The add equals method.</p>
     * @param equals {@link java.util.Collection} <p>The equals parameter is <code>Collection</code> type.</p>
     * @see  java.util.Collection
     */
    public void addEquals(Collection<EqualRule> equals) {
        if (GeneralUtils.isEmpty(this.equals)) {
            this.equals = Optional.ofNullable(equals).map(HashSet::new).orElse(null);
        } else {
            Optional.ofNullable(equals).ifPresent(this.equals::addAll);
        }
    }

    /**
     * <code>getEquals</code>
     * <p>The get equals getter method.</p>
     * @return  {@link java.util.List} <p>The get equals return object is <code>List</code> type.</p>
     * @see  java.util.List
     */
    public List<EqualRule> getEquals() {
        if (GeneralUtils.isNotEmpty(this.equals)) {
            return new ArrayList<>(equals);
        }
        return null;
    }

    /**
     * <code>setEquals</code>
     * <p>The set equals setter method.</p>
     * @param equals {@link io.github.nichetoolkit.rice.jsonb.EqualRule} <p>The equals parameter is <code>EqualRule</code> type.</p>
     * @see  io.github.nichetoolkit.rice.jsonb.EqualRule
     */
    public void setEquals(EqualRule... equals) {
        this.equals = Optional.ofNullable(equals).map(equalList -> new HashSet<>(Arrays.asList(equalList))).orElse(null);
    }

    /**
     * <code>setEquals</code>
     * <p>The set equals setter method.</p>
     * @param equals {@link java.util.Collection} <p>The equals parameter is <code>Collection</code> type.</p>
     * @see  java.util.Collection
     * @see  com.fasterxml.jackson.annotation.JsonSetter
     */
    @JsonSetter
    public void setEquals(Collection<EqualRule> equals) {
        this.equals = Optional.ofNullable(equals).map(HashSet::new).orElse(null);
    }

    /**
     * <code>toJsonbSql</code>
     * <p>The to jsonb sql method.</p>
     * @param alias {@link java.lang.String} <p>The alias parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  org.springframework.lang.NonNull
     * @return  {@link io.github.nichetoolkit.rice.filter.JsonbFilter} <p>The to jsonb sql return object is <code>JsonbFilter</code> type.</p>
     */
    public JsonbFilter<I, K> toJsonbSql(@NonNull String alias) {
        this.toJsonbSql(alias, "value");
        return this;
    }

    /**
     * <code>toJsonbSql</code>
     * <p>The to jsonb sql method.</p>
     * @param alias {@link java.lang.String} <p>The alias parameter is <code>String</code> type.</p>
     * @param variable {@link java.lang.String} <p>The variable parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  org.springframework.lang.NonNull
     * @return  {@link io.github.nichetoolkit.rice.filter.JsonbFilter} <p>The to jsonb sql return object is <code>JsonbFilter</code> type.</p>
     */
    public JsonbFilter<I, K> toJsonbSql(@NonNull String alias, String variable) {
        SqlBuilder sqlBuilder = SqlBuilders.newSqlBuilder();
        this.appendSql(alias, variable, getContrasts(), sqlBuilder);
        this.appendSql(alias, variable, getRanges(), sqlBuilder);
        this.appendSql(alias, variable, getContains(), sqlBuilder);
        this.appendSql(alias, variable, getEquals(), sqlBuilder);
        SqlBuilders.append(this.SQL_BUILDER, sqlBuilder.toString());
        return this;
    }

    /**
     * <code>appendSql</code>
     * <p>The append sql method.</p>
     * @param <R>  {@link io.github.nichetoolkit.rice.jsonb.JsonbRule} <p>The generic parameter is <code>JsonbRule</code> type.</p>
     * @param alias {@link java.lang.String} <p>The alias parameter is <code>String</code> type.</p>
     * @param variable {@link java.lang.String} <p>The variable parameter is <code>String</code> type.</p>
     * @param rules {@link java.util.List} <p>The rules parameter is <code>List</code> type.</p>
     * @param sqlBuilder {@link SqlBuilder} <p>The sql builder parameter is <code>SqlBuilder</code> type.</p>
     * @see  io.github.nichetoolkit.rice.jsonb.JsonbRule
     * @see  java.lang.String
     * @see  org.springframework.lang.NonNull
     * @see  java.util.List
     * @see  SqlBuilder
     */
    public <R extends JsonbRule<R>> void appendSql(@NonNull String alias, String variable, List<R> rules, SqlBuilder sqlBuilder) {
        if (GeneralUtils.isNotEmpty(rules)) {
            rules.forEach(rule -> {
                String sql = rule.toSql(alias, variable);
                if (GeneralUtils.isNotEmpty(sql)) {
                    sqlBuilder.append(sql);
                }
            });
        }
    }


    @Override
    public JsonbFilter<I, K> toTimeSql(@NonNull String alias) {
        super.toTimeSql(alias);
        return this;
    }

    @Override
    public JsonbFilter<I, K> toIdSql(@NonNull String alias) {
        super.toIdSql(alias);
        return this;
    }

    @Override
    public JsonbFilter<I, K> toOperateSql(@NonNull String alias) {
        super.toOperateSql(alias);
        return this;
    }

    /**
     * <code>Builder</code>
     * <p>The builder class.</p>
     * @param <I>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @see  io.github.nichetoolkit.rice.filter.TimeFilter.Builder
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk1.8
     */
    public static class Builder<I, K> extends TimeFilter.Builder<I, K> {
        /**
         * <code>contrasts</code>
         * {@link java.util.Set} <p>The <code>contrasts</code> field.</p>
         * @see  java.util.Set
         */
        protected Set<ContrastRule> contrasts;
        /**
         * <code>ranges</code>
         * {@link java.util.Set} <p>The <code>ranges</code> field.</p>
         * @see  java.util.Set
         */
        protected Set<RangeRule> ranges;
        /**
         * <code>equals</code>
         * {@link java.util.Set} <p>The <code>equals</code> field.</p>
         * @see  java.util.Set
         */
        protected Set<EqualRule> equals;
        /**
         * <code>contains</code>
         * {@link java.util.Set} <p>The <code>contains</code> field.</p>
         * @see  java.util.Set
         */
        protected Set<ContainRule> contains;

        /**
         * <code>Builder</code>
         * <p>Instantiates a new builder.</p>
         */
        public Builder() {
        }

        /**
         * <code>contrasts</code>
         * <p>The contrasts method.</p>
         * @param contrasts {@link java.util.Collection} <p>The contrasts parameter is <code>Collection</code> type.</p>
         * @see  java.util.Collection
         * @return  {@link io.github.nichetoolkit.rice.filter.JsonbFilter.Builder} <p>The contrasts return object is <code>Builder</code> type.</p>
         */
        public JsonbFilter.Builder<I, K> contrasts(Collection<ContrastRule> contrasts) {
            this.contrasts = Optional.ofNullable(contrasts).map(HashSet::new).orElse(null);
            return this;
        }

        /**
         * <code>contrasts</code>
         * <p>The contrasts method.</p>
         * @param contrasts {@link io.github.nichetoolkit.rice.jsonb.ContrastRule} <p>The contrasts parameter is <code>ContrastRule</code> type.</p>
         * @see  io.github.nichetoolkit.rice.jsonb.ContrastRule
         * @return  {@link io.github.nichetoolkit.rice.filter.JsonbFilter.Builder} <p>The contrasts return object is <code>Builder</code> type.</p>
         */
        public JsonbFilter.Builder<I, K> contrasts(ContrastRule... contrasts) {
            this.contrasts = Optional.ofNullable(contrasts).map(contrastList -> new HashSet<>(Arrays.asList(contrastList))).orElse(null);
            return this;
        }

        /**
         * <code>ranges</code>
         * <p>The ranges method.</p>
         * @param ranges {@link java.util.Collection} <p>The ranges parameter is <code>Collection</code> type.</p>
         * @see  java.util.Collection
         * @return  {@link io.github.nichetoolkit.rice.filter.JsonbFilter.Builder} <p>The ranges return object is <code>Builder</code> type.</p>
         */
        public JsonbFilter.Builder<I, K> ranges(Collection<RangeRule> ranges) {
            this.ranges = Optional.ofNullable(ranges).map(HashSet::new).orElse(null);
            return this;
        }

        /**
         * <code>ranges</code>
         * <p>The ranges method.</p>
         * @param ranges {@link io.github.nichetoolkit.rice.jsonb.RangeRule} <p>The ranges parameter is <code>RangeRule</code> type.</p>
         * @see  io.github.nichetoolkit.rice.jsonb.RangeRule
         * @return  {@link io.github.nichetoolkit.rice.filter.JsonbFilter.Builder} <p>The ranges return object is <code>Builder</code> type.</p>
         */
        public JsonbFilter.Builder<I, K> ranges(RangeRule... ranges) {
            this.ranges = Optional.ofNullable(ranges).map(rangeList -> new HashSet<>(Arrays.asList(rangeList))).orElse(null);
            return this;
        }

        /**
         * <code>equals</code>
         * <p>The equals method.</p>
         * @param equals {@link java.util.Collection} <p>The equals parameter is <code>Collection</code> type.</p>
         * @see  java.util.Collection
         * @return  {@link io.github.nichetoolkit.rice.filter.JsonbFilter.Builder} <p>The equals return object is <code>Builder</code> type.</p>
         */
        public JsonbFilter.Builder<I, K> equals(Collection<EqualRule> equals) {
            this.equals = Optional.ofNullable(equals).map(HashSet::new).orElse(null);
            return this;
        }

        /**
         * <code>equals</code>
         * <p>The equals method.</p>
         * @param equals {@link io.github.nichetoolkit.rice.jsonb.EqualRule} <p>The equals parameter is <code>EqualRule</code> type.</p>
         * @see  io.github.nichetoolkit.rice.jsonb.EqualRule
         * @return  {@link io.github.nichetoolkit.rice.filter.JsonbFilter.Builder} <p>The equals return object is <code>Builder</code> type.</p>
         */
        public JsonbFilter.Builder<I, K> equals(EqualRule... equals) {
            this.equals = Optional.ofNullable(equals).map(equalList -> new HashSet<>(Arrays.asList(equalList))).orElse(null);
            return this;
        }

        /**
         * <code>contains</code>
         * <p>The contains method.</p>
         * @param contains {@link java.util.Collection} <p>The contains parameter is <code>Collection</code> type.</p>
         * @see  java.util.Collection
         * @return  {@link io.github.nichetoolkit.rice.filter.JsonbFilter.Builder} <p>The contains return object is <code>Builder</code> type.</p>
         */
        public JsonbFilter.Builder<I, K> contains(Collection<ContainRule> contains) {
            this.contains = Optional.ofNullable(contains).map(HashSet::new).orElse(null);
            return this;
        }

        /**
         * <code>contains</code>
         * <p>The contains method.</p>
         * @param contains {@link io.github.nichetoolkit.rice.jsonb.ContainRule} <p>The contains parameter is <code>ContainRule</code> type.</p>
         * @see  io.github.nichetoolkit.rice.jsonb.ContainRule
         * @return  {@link io.github.nichetoolkit.rice.filter.JsonbFilter.Builder} <p>The contains return object is <code>Builder</code> type.</p>
         */
        public JsonbFilter.Builder<I, K> contains(ContainRule... contains) {
            this.contains = Optional.ofNullable(contains).map(containList -> new HashSet<>(Arrays.asList(containList))).orElse(null);
            return this;
        }

        @Override
        public JsonbFilter.Builder<I, K> startTime(Date startTime) {
            this.startTime = startTime;
            return this;
        }

        @Override
        public JsonbFilter.Builder<I, K> endTime(Date endTime) {
            this.endTime = endTime;
            return this;
        }

        @Override
        public JsonbFilter.Builder<I, K> ids(@NonNull Collection<I> ids) {
            this.ids = new HashSet<>(ids);
            return this;
        }

        @Override
        @SuppressWarnings(value = "unchecked")
        public JsonbFilter.Builder<I, K> ids(@NonNull I... ids) {
            this.ids = new HashSet<>(Arrays.asList(ids));
            return this;
        }

        @Override
        public JsonbFilter.Builder<I, K> tablekey(K tablekey) {
            this.tablekey = tablekey;
            return this;
        }

        @Override
        public JsonbFilter.Builder<I, K> isRemove(boolean isRemove) {
            this.isRemove = isRemove;
            return this;
        }

        @Override
        public JsonbFilter.Builder<I, K> operate(OperateType operate) {
            this.operate = operate;
            return this;
        }

        @Override
        public JsonbFilter.Builder<I, K> operate(Integer operate) {
            this.operate = OperateType.parseKey(operate);
            return this;
        }

        @Override
        public JsonbFilter.Builder<I, K> operates(@NonNull Collection<OperateType> operates) {
            this.operates = new HashSet<>(operates);
            return this;
        }

        @Override
        public JsonbFilter.Builder<I, K> operates(@NonNull OperateType... operates) {
            this.operates = new HashSet<>(Arrays.asList(operates));
            return this;
        }

        @Override
        public JsonbFilter.Builder<I, K> operates(@NonNull Integer... operates) {
            this.operates = new HashSet<>(RestOperate.build(operates));
            return this;
        }

        @Override
        public JsonbFilter.Builder<I, K> sorts(@NonNull Collection<RestSort<?>> sorts) {
            this.sorts = new HashSet<>(sorts);
            return this;
        }

        @Override
        public JsonbFilter.Builder<I, K> sorts(@NonNull RestSort<?>... sorts) {
            this.sorts = new HashSet<>(Arrays.asList(sorts));
            return this;
        }

        @Override
        public JsonbFilter.Builder<I, K> sorts(@NonNull String... sorts) {
            this.sorts = new HashSet<>(RestSort.build(sorts));
            return this;
        }

        @Override
        public JsonbFilter.Builder<I, K> pageNum(Integer pageNum) {
            this.pageNum = pageNum;
            return this;
        }

        @Override
        public JsonbFilter.Builder<I, K> pageSize(Integer pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        @Override
        public JsonbFilter<I, K> build() {
            return new JsonbFilter<>(this);
        }
    }

}
