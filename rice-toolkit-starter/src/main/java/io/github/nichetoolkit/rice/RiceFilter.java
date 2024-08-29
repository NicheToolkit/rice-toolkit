package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rice.enums.DeleteType;
import io.github.nichetoolkit.rice.enums.OperateType;
import io.github.nichetoolkit.rice.enums.RemoveType;
import io.github.nichetoolkit.rice.jsonb.ContainRule;
import io.github.nichetoolkit.rice.jsonb.ContrastRule;
import io.github.nichetoolkit.rice.jsonb.EqualRule;
import io.github.nichetoolkit.rice.jsonb.RangeRule;
import io.github.nichetoolkit.rice.service.SuperService;
import org.springframework.lang.NonNull;

import java.util.*;

public abstract class RiceFilter extends BaseFilter<String,String> {

    public RiceFilter() {
    }

    public RiceFilter(String... ids) {
        super(ids);
    }

    public RiceFilter(RiceFilter.Builder builder) {
        super(builder);
    }

    public RiceFilter toRemoveSql(SuperService<String,String,? extends IdModel<String>,? extends IdEntity<String>,? extends RiceFilter> superService, @NonNull String alias) {
        RemoveType removeType = superService.removeModel();
        String removeSign = superService.removeSign();
        Boolean removeIndex = superService.removeIndex();
        String removeValue = superService.removeValue();
        super.toRemoveSql(removeType,removeSign,removeIndex,removeValue,alias);
        return this;
    }

    public RiceFilter toQuerySql(SuperService<String,String,? extends IdModel<String>,? extends IdEntity<String>,? extends RiceFilter> superService, @NonNull String alias) {
        DeleteType deleteType = superService.deleteModel();
        RemoveType removeType = superService.removeModel();
        String removeSign = superService.removeSign();
        Boolean removeIndex = superService.removeIndex();
        String removeValue = superService.removeValue();
        super.toQuerySql(deleteType,removeType,removeSign,removeIndex,removeValue,alias);
        return this;
    }


    @Override
    public RiceFilter toNameSql(@NonNull String alias) {
        super.toNameSql(alias);
        return this;
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

    @Override
    public RiceFilter toOperateSql(@NonNull String alias) {
        super.toOperateSql(alias);
        return this;
    }

    public static abstract class Builder extends BaseFilter.Builder<String,String> {

        public Builder() {
        }

        @Override
        public RiceFilter.Builder name(String name) {
            this.name = name;
            return this;
        }

        @Override
        public RiceFilter.Builder names(Collection<String> names) {
            this.names = Optional.ofNullable(names).map(HashSet::new).orElse(null);
            return this;
        }

        @Override
        public RiceFilter.Builder names(String... names) {
            this.names = Optional.ofNullable(names).map(propertyList -> new HashSet<>(Arrays.asList(propertyList))).orElse(null);
            return this;
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
        public RiceFilter.Builder tablekey(String tablekey) {
            this.tablekey = tablekey;
            return this;
        }

        @Override
        public RiceFilter.Builder isRemove(boolean isRemove) {
            this.isRemove = isRemove;
            return this;
        }

        @Override
        public RiceFilter.Builder operate(OperateType operate) {
            this.operate = operate;
            return this;
        }

        @Override
        public RiceFilter.Builder operate(Integer operate) {
            this.operate = OperateType.parseKey(operate);
            return this;
        }

        @Override
        public RiceFilter.Builder operates(@NonNull Collection<OperateType> operates) {
            this.operates = new HashSet<>(operates);
            return this;
        }

        @Override
        public RiceFilter.Builder operates(@NonNull OperateType... operates) {
            this.operates = new HashSet<>(Arrays.asList(operates));
            return this;
        }

        @Override
        public RiceFilter.Builder operates(@NonNull Integer... operates) {
            this.operates = new HashSet<>(RestOperate.build(operates));
            return this;
        }

        @Override
        public RiceFilter.Builder sorts(@NonNull Collection<RestSort<?>> sorts) {
            this.sorts = new HashSet<>(sorts);
            return this;
        }

        @Override
        public RiceFilter.Builder sorts(@NonNull RestSort<?>... sorts) {
            this.sorts = new HashSet<>(Arrays.asList(sorts));
            return this;
        }

        @Override
        public RiceFilter.Builder sorts(@NonNull String... sorts) {
            this.sorts = new HashSet<>(RestSort.build(sorts));
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
