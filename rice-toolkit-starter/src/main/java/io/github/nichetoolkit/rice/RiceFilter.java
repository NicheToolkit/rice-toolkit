package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rice.filter.JsonbFilter;
import io.github.nichetoolkit.rice.jsonb.ContainRule;
import io.github.nichetoolkit.rice.jsonb.ContrastRule;
import io.github.nichetoolkit.rice.jsonb.EqualRule;
import io.github.nichetoolkit.rice.jsonb.RangeRule;
import org.springframework.lang.NonNull;

import java.util.*;

/**
 * <p>RestFilter</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public abstract class RiceFilter extends JsonbFilter<Date,String> {
    public RiceFilter() {
    }

    public RiceFilter(String... ids) {
        super(ids);
    }

    public RiceFilter(RiceFilter.Builder builder) {
        super(builder);
    }

    @Override
    public RiceFilter toJsonbSql(@NonNull String alias) {
        super.toJsonbSql(alias);
        return this;
    }

    @Override
    public RiceFilter toJsonbSql(@NonNull String alias, String variable) {
        super.toJsonbSql(alias,variable);
        return this;
    }

    @Override
    public RiceFilter toTimeSql(@NonNull String alias) {
        super.toTimeSql(alias);
        return this;
    }

    @Override
    public RiceFilter toIdSql(@NonNull String alias) {
        super.toIdSql(alias);
        return this;
    }

    public static abstract class Builder extends JsonbFilter.Builder<Date,String> {

        public Builder() {
        }

        @Override
        public RiceFilter.Builder contrasts(Collection<ContrastRule> contrasts) {
            this.contrasts = Optional.ofNullable(contrasts).map(HashSet::new).orElse(null);
            return this;
        }

        @Override
        public RiceFilter.Builder contrasts(ContrastRule... contrasts) {
            this.contrasts = Optional.ofNullable(contrasts).map(contrastList -> new HashSet<>(Arrays.asList(contrastList))).orElse(null);
            return this;
        }

        @Override
        public RiceFilter.Builder ranges(Collection<RangeRule> ranges) {
            this.ranges = Optional.ofNullable(ranges).map(HashSet::new).orElse(null);
            return this;
        }

        @Override
        public RiceFilter.Builder ranges(RangeRule... ranges) {
            this.ranges = Optional.ofNullable(ranges).map(rangeList -> new HashSet<>(Arrays.asList(rangeList))).orElse(null);
            return this;
        }

        @Override
        public RiceFilter.Builder equals(Collection<EqualRule> equals) {
            this.equals = Optional.ofNullable(equals).map(HashSet::new).orElse(null);
            return this;
        }

        @Override
        public RiceFilter.Builder equals(EqualRule... equals) {
            this.equals = Optional.ofNullable(equals).map(equalList -> new HashSet<>(Arrays.asList(equalList))).orElse(null);
            return this;
        }

        @Override
        public RiceFilter.Builder contains(Collection<ContainRule> contains) {
            this.contains = Optional.ofNullable(contains).map(HashSet::new).orElse(null);
            return this;
        }

        @Override
        public RiceFilter.Builder contains(ContainRule... contains) {
            this.contains = Optional.ofNullable(contains).map(containList -> new HashSet<>(Arrays.asList(containList))).orElse(null);
            return this;
        }

        @Override
        public RiceFilter.Builder startTime(Date startTime) {
            this.startTime = startTime;
            return this;
        }

        @Override
        public RiceFilter.Builder endTime(Date endTime) {
            this.endTime = endTime;
            return this;
        }

        @Override
        public RiceFilter.Builder ids(@NonNull Collection<String> ids) {
            this.ids = new HashSet<>(ids);
            return this;
        }

        @Override
        public RiceFilter.Builder ids(@NonNull String... ids) {
            this.ids = new HashSet<>(Arrays.asList(ids));
            return this;
        }

        @Override
        public RiceFilter.Builder sorts(@NonNull Collection<RestSort> sorts) {
            this.sorts = new HashSet<>(sorts);
            return this;
        }

        @Override
        public RiceFilter.Builder sorts(@NonNull RestSort... sorts) {
            this.sorts = new HashSet<>(Arrays.asList(sorts));
            return this;
        }

        @Override
        public RiceFilter.Builder pageNum(Integer pageNum) {
            this.pageNum = pageNum;
            return this;
        }

        @Override
        public RiceFilter.Builder pageSize(Integer pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        @Override
        public abstract RiceFilter build();
    }
}
