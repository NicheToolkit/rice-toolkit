package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.enums.DeleteMode;
import io.github.nichetoolkit.rice.enums.OperateType;
import io.github.nichetoolkit.rice.enums.LogicMode;
import io.github.nichetoolkit.rice.jsonb.ContainRule;
import io.github.nichetoolkit.rice.jsonb.ContrastRule;
import io.github.nichetoolkit.rice.jsonb.EqualRule;
import io.github.nichetoolkit.rice.jsonb.RangeRule;
import io.github.nichetoolkit.rice.service.SuperService;
import org.springframework.lang.NonNull;

import java.util.*;

/**
 * <code>RestFilter</code>
 * <p>The rest filter class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.DefaultFilter
 * @since Jdk1.8
 */
public abstract class RestFilter extends DefaultFilter<String, String> {

    /**
     * <code>RestFilter</code>
     * <p>Instantiates a new rest filter.</p>
     */
    public RestFilter() {
    }

    /**
     * <code>RestFilter</code>
     * <p>Instantiates a new rest filter.</p>
     * @param ids {@link java.lang.String} <p>The ids parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public RestFilter(String... ids) {
        super(ids);
    }

    /**
     * <code>RestFilter</code>
     * <p>Instantiates a new rest filter.</p>
     * @param builder {@link io.github.nichetoolkit.rice.RestFilter.Builder} <p>The builder parameter is <code>Builder</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestFilter.Builder
     */
    public RestFilter(RestFilter.Builder builder) {
        super(builder);
    }

    /**
     * <code>toRemoveSql</code>
     * <p>The to remove sql method.</p>
     * @param superService {@link io.github.nichetoolkit.rice.service.SuperService} <p>The super service parameter is <code>SuperService</code> type.</p>
     * @param alias        {@link java.lang.String} <p>The alias parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.RestFilter} <p>The to remove sql return object is <code>RestFilter</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.service.SuperService
     * @see java.lang.String
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    public RestFilter toRemoveSql(SuperService<? extends IdModel<String>, ? extends IdEntity<String>, ? extends RestFilter, String, String> superService, @NonNull String alias) throws RestException {
        LogicMode logicMode = superService.logicMode();
        Object unmarkOfLogic = superService.unmarkOfLogic();
        Boolean accurateJudge = superService.judgeOfAccurate();
        Object markOfLogic = superService.markOfLogic();
        super.toRemoveSql(logicMode, markOfLogic, accurateJudge, unmarkOfLogic, alias);
        return this;
    }

    /**
     * <code>toQuerySql</code>
     * <p>The to query sql method.</p>
     * @param superService {@link io.github.nichetoolkit.rice.service.SuperService} <p>The super service parameter is <code>SuperService</code> type.</p>
     * @param alias        {@link java.lang.String} <p>The alias parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.RestFilter} <p>The to query sql return object is <code>RestFilter</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.service.SuperService
     * @see java.lang.String
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    public RestFilter toQuerySql(SuperService<? extends IdModel<String>, ? extends IdEntity<String>, ? extends RestFilter, String, String> superService, @NonNull String alias) throws RestException {
        DeleteMode deleteMode = superService.deleteMode();
        LogicMode logicMode = superService.logicMode();
        Object markOfLogic = superService.markOfLogic();
        Boolean accurateJudge = superService.judgeOfAccurate();
        Object unmarkOfLogic = superService.unmarkOfLogic();
        super.toQuerySql(deleteMode, logicMode, markOfLogic, accurateJudge, unmarkOfLogic, alias);
        return this;
    }


    @Override
    public RestFilter toNameSql(@NonNull String alias) {
        super.toNameSql(alias);
        return this;
    }

    @Override
    public RestFilter toJsonbSql(@NonNull String alias) {
        super.toJsonbSql(alias);
        return this;
    }

    @Override
    public RestFilter toJsonbSql(@NonNull String alias, String variable) {
        super.toJsonbSql(alias, variable);
        return this;
    }

    @Override
    public RestFilter toTimeSql(@NonNull String alias) {
        super.toTimeSql(alias);
        return this;
    }

    @Override
    public RestFilter toIdSql(@NonNull String alias) {
        super.toIdSql(alias);
        return this;
    }

    @Override
    public RestFilter toOperateSql(@NonNull String alias) {
        super.toOperateSql(alias);
        return this;
    }

    /**
     * <code>Builder</code>
     * <p>The builder class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rice.DefaultFilter.Builder
     * @since Jdk1.8
     */
    public static abstract class Builder extends DefaultFilter.Builder<String, String> {

        /**
         * <code>Builder</code>
         * <p>Instantiates a new builder.</p>
         */
        public Builder() {
        }

        @Override
        public RestFilter.Builder name(String name) {
            this.name = name;
            return this;
        }

        @Override
        public RestFilter.Builder names(Collection<String> names) {
            this.names = Optional.ofNullable(names).map(HashSet::new).orElse(null);
            return this;
        }

        @Override
        public RestFilter.Builder names(String... names) {
            this.names = Optional.ofNullable(names).map(propertyList -> new HashSet<>(Arrays.asList(propertyList))).orElse(null);
            return this;
        }

        @Override
        public RestFilter.Builder contrasts(Collection<ContrastRule> contrasts) {
            this.contrasts = Optional.ofNullable(contrasts).map(HashSet::new).orElse(null);
            return this;
        }

        @Override
        public RestFilter.Builder contrasts(ContrastRule... contrasts) {
            this.contrasts = Optional.ofNullable(contrasts).map(contrastList -> new HashSet<>(Arrays.asList(contrastList))).orElse(null);
            return this;
        }

        @Override
        public RestFilter.Builder ranges(Collection<RangeRule> ranges) {
            this.ranges = Optional.ofNullable(ranges).map(HashSet::new).orElse(null);
            return this;
        }

        @Override
        public RestFilter.Builder ranges(RangeRule... ranges) {
            this.ranges = Optional.ofNullable(ranges).map(rangeList -> new HashSet<>(Arrays.asList(rangeList))).orElse(null);
            return this;
        }

        @Override
        public RestFilter.Builder equals(Collection<EqualRule> equals) {
            this.equals = Optional.ofNullable(equals).map(HashSet::new).orElse(null);
            return this;
        }

        @Override
        public RestFilter.Builder equals(EqualRule... equals) {
            this.equals = Optional.ofNullable(equals).map(equalList -> new HashSet<>(Arrays.asList(equalList))).orElse(null);
            return this;
        }

        @Override
        public RestFilter.Builder contains(Collection<ContainRule> contains) {
            this.contains = Optional.ofNullable(contains).map(HashSet::new).orElse(null);
            return this;
        }

        @Override
        public RestFilter.Builder contains(ContainRule... contains) {
            this.contains = Optional.ofNullable(contains).map(containList -> new HashSet<>(Arrays.asList(containList))).orElse(null);
            return this;
        }

        @Override
        public RestFilter.Builder startTime(Date startTime) {
            this.startTime = startTime;
            return this;
        }

        @Override
        public RestFilter.Builder endTime(Date endTime) {
            this.endTime = endTime;
            return this;
        }

        @Override
        public RestFilter.Builder ids(@NonNull Collection<String> ids) {
            this.ids = new HashSet<>(ids);
            return this;
        }

        @Override
        public RestFilter.Builder ids(@NonNull String... ids) {
            this.ids = new HashSet<>(Arrays.asList(ids));
            return this;
        }

        @Override
        public RestFilter.Builder tablekey(String tablekey) {
            this.tablekey = tablekey;
            return this;
        }

        @Override
        public RestFilter.Builder isRemove(boolean isRemove) {
            this.isRemove = isRemove;
            return this;
        }

        @Override
        public RestFilter.Builder operate(OperateType operate) {
            this.operate = operate;
            return this;
        }

        @Override
        public RestFilter.Builder operate(Integer operate) {
            this.operate = OperateType.parseKey(operate);
            return this;
        }

        @Override
        public RestFilter.Builder operates(@NonNull Collection<OperateType> operates) {
            this.operates = new HashSet<>(operates);
            return this;
        }

        @Override
        public RestFilter.Builder operates(@NonNull OperateType... operates) {
            this.operates = new HashSet<>(Arrays.asList(operates));
            return this;
        }

        @Override
        public RestFilter.Builder operates(@NonNull Integer... operates) {
            this.operates = new HashSet<>(RestOperate.build(operates));
            return this;
        }

        @Override
        public RestFilter.Builder sorts(@NonNull Collection<RestSort<?>> sorts) {
            this.sorts = new HashSet<>(sorts);
            return this;
        }

        @Override
        public RestFilter.Builder sorts(@NonNull RestSort<?>... sorts) {
            this.sorts = new HashSet<>(Arrays.asList(sorts));
            return this;
        }

        @Override
        public RestFilter.Builder sorts(@NonNull String... sorts) {
            this.sorts = new HashSet<>(RestSort.build(sorts));
            return this;
        }

        @Override
        public RestFilter.Builder pageNum(Integer pageNum) {
            this.pageNum = pageNum;
            return this;
        }

        @Override
        public RestFilter.Builder pageSize(Integer pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        @Override
        public abstract RestFilter build();
    }
}
