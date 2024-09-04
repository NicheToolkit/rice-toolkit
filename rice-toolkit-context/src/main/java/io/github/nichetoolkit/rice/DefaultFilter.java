package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.builder.SqlBuilders;
import io.github.nichetoolkit.rice.enums.DeleteMode;
import io.github.nichetoolkit.rice.enums.OperateType;
import io.github.nichetoolkit.rice.enums.RemoveMode;
import io.github.nichetoolkit.rice.filter.NameFilter;
import io.github.nichetoolkit.rice.jsonb.ContainRule;
import io.github.nichetoolkit.rice.jsonb.ContrastRule;
import io.github.nichetoolkit.rice.jsonb.EqualRule;
import io.github.nichetoolkit.rice.jsonb.RangeRule;
import org.springframework.lang.NonNull;

import java.util.*;

/**
 * <code>DefaultFilter</code>
 * <p>The type default filter class.</p>
 * @param <I> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @param <K> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.filter.NameFilter
 * @since Jdk1.8
 */
public abstract class DefaultFilter<I, K> extends NameFilter<I, K> {

    /**
     * <code>DefaultFilter</code>
     * Instantiates a new default filter.
     */
    public DefaultFilter() {
    }

    /**
     * <code>DefaultFilter</code>
     * Instantiates a new default filter.
     * @param ids I <p>the ids parameter is <code>I</code> type.</p>
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings(value = "unchecked")
    public DefaultFilter(I... ids) {
        super(ids);
    }

    /**
     * <code>DefaultFilter</code>
     * Instantiates a new default filter.
     * @param builder {@link io.github.nichetoolkit.rice.DefaultFilter.Builder} <p>the builder parameter is <code>Builder</code> type.</p>
     * @see io.github.nichetoolkit.rice.DefaultFilter.Builder
     */
    public DefaultFilter(DefaultFilter.Builder<I, K> builder) {
        super(builder);
    }

    /**
     * <code>toRemoveSql</code>
     * <p>the remove sql method.</p>
     * @param removeMode   {@link io.github.nichetoolkit.rice.enums.RemoveMode} <p>the remove mode parameter is <code>RemoveMode</code> type.</p>
     * @param removeSign   {@link java.lang.String} <p>the remove sign parameter is <code>String</code> type.</p>
     * @param pinpointSign {@link java.lang.Boolean} <p>the pinpoint sign parameter is <code>Boolean</code> type.</p>
     * @param removeValue  {@link java.lang.String} <p>the remove value parameter is <code>String</code> type.</p>
     * @param alias        {@link java.lang.String} <p>the alias parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.DefaultFilter} <p>the remove sql return object is <code>DefaultFilter</code> type.</p>
     * @see io.github.nichetoolkit.rice.enums.RemoveMode
     * @see java.lang.String
     * @see java.lang.Boolean
     * @see org.springframework.lang.NonNull
     */
    public DefaultFilter<I, K> toRemoveSql(RemoveMode removeMode, String removeSign, Boolean pinpointSign, String removeValue, @NonNull String alias) {
        if (GeneralUtils.isNotEmpty(removeSign)) {
            if (removeMode == RemoveMode.BOOLEAN || removeMode == RemoveMode.NUMBER) {
                if (pinpointSign && GeneralUtils.isNotEmpty(removeValue)) {
                    if (this.isRemove) {
                        SqlBuilders.equal(SQL_BUILDER, alias, removeSign);
                    } else {
                        SqlBuilders.equal(SQL_BUILDER, alias, removeValue);
                    }
                } else {
                    if (this.isRemove) {
                        SqlBuilders.equal(SQL_BUILDER, alias, removeSign);
                    } else {
                        SqlBuilders.unequal(SQL_BUILDER, alias, removeSign);
                    }
                }
            } else if (removeMode == RemoveMode.DATETIME || removeMode == RemoveMode.IDENTITY) {
                if (this.isRemove) {
                    SqlBuilders.nonnull(SQL_BUILDER, alias);
                } else {
                    SqlBuilders.isnull(SQL_BUILDER, alias);
                }
            }
        }
        return this;
    }

    /**
     * <code>toQuerySql</code>
     * <p>the query sql method.</p>
     * @param deleteMode   {@link io.github.nichetoolkit.rice.enums.DeleteMode} <p>the delete mode parameter is <code>DeleteMode</code> type.</p>
     * @param removeMode   {@link io.github.nichetoolkit.rice.enums.RemoveMode} <p>the remove mode parameter is <code>RemoveMode</code> type.</p>
     * @param removeSign   {@link java.lang.String} <p>the remove sign parameter is <code>String</code> type.</p>
     * @param pinpointSign {@link java.lang.Boolean} <p>the pinpoint sign parameter is <code>Boolean</code> type.</p>
     * @param removeValue  {@link java.lang.String} <p>the remove value parameter is <code>String</code> type.</p>
     * @param alias        {@link java.lang.String} <p>the alias parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.DefaultFilter} <p>the query sql return object is <code>DefaultFilter</code> type.</p>
     * @see io.github.nichetoolkit.rice.enums.DeleteMode
     * @see io.github.nichetoolkit.rice.enums.RemoveMode
     * @see java.lang.String
     * @see java.lang.Boolean
     * @see org.springframework.lang.NonNull
     */
    public DefaultFilter<I, K> toQuerySql(DeleteMode deleteMode, RemoveMode removeMode, String removeSign, Boolean pinpointSign, String removeValue, @NonNull String alias) {
        if (deleteMode == DeleteMode.OPERATE) {
            return toOperateSql(alias);
        } else if (deleteMode == DeleteMode.REMOVE) {
            return toRemoveSql(removeMode,removeSign,pinpointSign,removeValue, alias);
        }
        return this;
    }

    @Override
    public DefaultFilter<I, K> toNameSql(@NonNull String alias) {
        super.toNameSql(alias);
        return this;
    }

    @Override
    public DefaultFilter<I, K> toJsonbSql(@NonNull String alias) {
        super.toJsonbSql(alias);
        return this;
    }

    @Override
    public DefaultFilter<I, K> toJsonbSql(@NonNull String alias, String variable) {
        super.toJsonbSql(alias, variable);
        return this;
    }

    @Override
    public DefaultFilter<I, K> toTimeSql(@NonNull String alias) {
        super.toTimeSql(alias);
        return this;
    }

    @Override
    public DefaultFilter<I, K> toIdSql(@NonNull String alias) {
        super.toIdSql(alias);
        return this;
    }

    @Override
    public DefaultFilter<I, K> toOperateSql(@NonNull String alias) {
        super.toOperateSql(alias);
        return this;
    }

    /**
     * <code>Builder</code>
     * <p>The type builder class.</p>
     * @param <I> {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <K> {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rice.filter.NameFilter.Builder
     * @since Jdk1.8
     */
    public static abstract class Builder<I, K> extends NameFilter.Builder<I, K> {

        /**
         * <code>Builder</code>
         * Instantiates a new builder.
         */
        public Builder() {
        }

        @Override
        public DefaultFilter.Builder<I, K> name(String name) {
            this.name = name;
            return this;
        }

        @Override
        public DefaultFilter.Builder<I, K> names(Collection<String> names) {
            this.names = Optional.ofNullable(names).map(HashSet::new).orElse(null);
            return this;
        }

        @Override
        public DefaultFilter.Builder<I, K> names(String... names) {
            this.names = Optional.ofNullable(names).map(propertyList -> new HashSet<>(Arrays.asList(propertyList))).orElse(null);
            return this;
        }

        @Override
        public DefaultFilter.Builder<I, K> contrasts(Collection<ContrastRule> contrasts) {
            this.contrasts = Optional.ofNullable(contrasts).map(HashSet::new).orElse(null);
            return this;
        }

        @Override
        public DefaultFilter.Builder<I, K> contrasts(ContrastRule... contrasts) {
            this.contrasts = Optional.ofNullable(contrasts).map(contrastList -> new HashSet<>(Arrays.asList(contrastList))).orElse(null);
            return this;
        }

        @Override
        public DefaultFilter.Builder<I, K> ranges(Collection<RangeRule> ranges) {
            this.ranges = Optional.ofNullable(ranges).map(HashSet::new).orElse(null);
            return this;
        }

        @Override
        public DefaultFilter.Builder<I, K> ranges(RangeRule... ranges) {
            this.ranges = Optional.ofNullable(ranges).map(rangeList -> new HashSet<>(Arrays.asList(rangeList))).orElse(null);
            return this;
        }

        @Override
        public DefaultFilter.Builder<I, K> equals(Collection<EqualRule> equals) {
            this.equals = Optional.ofNullable(equals).map(HashSet::new).orElse(null);
            return this;
        }

        @Override
        public DefaultFilter.Builder<I, K> equals(EqualRule... equals) {
            this.equals = Optional.ofNullable(equals).map(equalList -> new HashSet<>(Arrays.asList(equalList))).orElse(null);
            return this;
        }

        @Override
        public DefaultFilter.Builder<I, K> contains(Collection<ContainRule> contains) {
            this.contains = Optional.ofNullable(contains).map(HashSet::new).orElse(null);
            return this;
        }

        @Override
        public DefaultFilter.Builder<I, K> contains(ContainRule... contains) {
            this.contains = Optional.ofNullable(contains).map(containList -> new HashSet<>(Arrays.asList(containList))).orElse(null);
            return this;
        }

        @Override
        public DefaultFilter.Builder<I, K> startTime(Date startTime) {
            this.startTime = startTime;
            return this;
        }

        @Override
        public DefaultFilter.Builder<I, K> endTime(Date endTime) {
            this.endTime = endTime;
            return this;
        }

        @Override
        public DefaultFilter.Builder<I, K> ids(@NonNull Collection<I> ids) {
            this.ids = new HashSet<>(ids);
            return this;
        }

        @SuppressWarnings(value = "unchecked")
        @Override
        public DefaultFilter.Builder<I, K> ids(@NonNull I... ids) {
            this.ids = new HashSet<>(Arrays.asList(ids));
            return this;
        }

        @Override
        public DefaultFilter.Builder<I, K> tablekey(K tablekey) {
            this.tablekey = tablekey;
            return this;
        }

        @Override
        public DefaultFilter.Builder<I, K> isRemove(boolean isRemove) {
            this.isRemove = isRemove;
            return this;
        }

        @Override
        public DefaultFilter.Builder<I, K> operate(OperateType operate) {
            this.operate = operate;
            return this;
        }

        @Override
        public DefaultFilter.Builder<I, K> operate(Integer operate) {
            this.operate = OperateType.parseKey(operate);
            return this;
        }

        @Override
        public DefaultFilter.Builder<I, K> operates(@NonNull Collection<OperateType> operates) {
            this.operates = new HashSet<>(operates);
            return this;
        }

        @Override
        public DefaultFilter.Builder<I, K> operates(@NonNull OperateType... operates) {
            this.operates = new HashSet<>(Arrays.asList(operates));
            return this;
        }

        @Override
        public DefaultFilter.Builder<I, K> operates(@NonNull Integer... operates) {
            this.operates = new HashSet<>(RestOperate.build(operates));
            return this;
        }

        @Override
        public DefaultFilter.Builder<I, K> sorts(@NonNull Collection<RestSort<?>> sorts) {
            this.sorts = new HashSet<>(sorts);
            return this;
        }

        @Override
        public DefaultFilter.Builder<I, K> sorts(@NonNull RestSort<?>... sorts) {
            this.sorts = new HashSet<>(Arrays.asList(sorts));
            return this;
        }

        @Override
        public DefaultFilter.Builder<I, K> sorts(@NonNull String... sorts) {
            this.sorts = new HashSet<>(RestSort.build(sorts));
            return this;
        }

        @Override
        public DefaultFilter.Builder<I, K> pageNum(Integer pageNum) {
            this.pageNum = pageNum;
            return this;
        }

        @Override
        public DefaultFilter.Builder<I, K> pageSize(Integer pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        @Override
        public abstract DefaultFilter<I, K> build();
    }
}
