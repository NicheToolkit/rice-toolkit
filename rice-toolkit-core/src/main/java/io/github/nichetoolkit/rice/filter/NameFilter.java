package io.github.nichetoolkit.rice.filter;

import com.fasterxml.jackson.annotation.JsonSetter;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.RestOperate;
import io.github.nichetoolkit.rice.RestSort;
import io.github.nichetoolkit.rice.builder.SqlBuilders;
import io.github.nichetoolkit.rice.enums.OperateType;
import io.github.nichetoolkit.rice.jsonb.ContainRule;
import io.github.nichetoolkit.rice.jsonb.ContrastRule;
import io.github.nichetoolkit.rice.jsonb.EqualRule;
import io.github.nichetoolkit.rice.jsonb.RangeRule;
import org.springframework.lang.NonNull;

import java.util.*;

/**
 * <p>NameFilter</p>
 * @author Cyan (snow22314@outlook.com)
 * @version V.0.0.1
 */
public class NameFilter<D,I> extends JsonbFilter<D,I> {
    /** 通过名称查询数据 */
    protected String name;
    protected Set<String> names;

    public NameFilter() {
    }

    public NameFilter(I id) {
        super(id);
    }

    @SuppressWarnings(value = "unchecked")
    public NameFilter(I... ids) {
        super(ids);
    }

    public NameFilter(NameFilter.Builder<D,I> builder) {
        super(builder);
        this.name = builder.name;
        this.names = builder.names;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getNames() {
        if (GeneralUtils.isNotEmpty(names)) {
            return new ArrayList<>(names);
        }
        return null;
    }

    public void setNames(String... names) {
        this.names = Optional.ofNullable(names).map(propertyList -> new HashSet<>(Arrays.asList(propertyList))).orElse(null);
    }

    @JsonSetter
    public void setNames(Collection<String> names) {
        this.names = Optional.ofNullable(names).map(HashSet::new).orElse(null);
    }

    public void addNames(String... names) {
        if (GeneralUtils.isEmpty(this.names)) {
            this.names = Optional.ofNullable(names).map(propertyList -> new HashSet<>(Arrays.asList(propertyList))).orElse(null);
        } else {
            Optional.ofNullable(names).ifPresent(propertyList -> this.names.addAll(Arrays.asList(propertyList)));
        }
    }

    public void addNames(Collection<String> names) {
        if (GeneralUtils.isEmpty(this.names)) {
            this.names = Optional.ofNullable(names).map(HashSet::new).orElse(null);
        } else {
            Optional.ofNullable(names).ifPresent(this.names::addAll);
        }
    }

    public NameFilter toNameSql(@NonNull String alias) {
        if (GeneralUtils.isNotEmpty(this.name)) {
            SqlBuilders.like(SQL_BUILDER, alias, this.name);
        } else if (GeneralUtils.isNotEmpty(this.names)) {
            SqlBuilders.in(SQL_BUILDER, alias, this.names);
        }
        return this;
    }

    public static class Builder<D,I> extends JsonbFilter.Builder<D,I> {
        protected String name;
        protected Set<String> names;

        public Builder() {
        }

        public NameFilter.Builder<D,I> name(String name) {
            this.name = name;
            return this;
        }

        public NameFilter.Builder<D,I> names(Collection<String> names) {
            this.names = Optional.ofNullable(names).map(HashSet::new).orElse(null);
            return this;
        }

        public NameFilter.Builder<D,I> names(String... names) {
            this.names = Optional.ofNullable(names).map(propertyList -> new HashSet<>(Arrays.asList(propertyList))).orElse(null);
            return this;
        }

        @Override
        public NameFilter.Builder<D,I> contrasts(Collection<ContrastRule> contrasts) {
            this.contrasts = Optional.ofNullable(contrasts).map(HashSet::new).orElse(null);
            return this;
        }

        @Override
        public NameFilter.Builder<D,I> contrasts(ContrastRule... contrasts) {
            this.contrasts = Optional.ofNullable(contrasts).map(contrastList -> new HashSet<>(Arrays.asList(contrastList))).orElse(null);
            return this;
        }

        @Override
        public NameFilter.Builder<D,I> ranges(Collection<RangeRule> ranges) {
            this.ranges = Optional.ofNullable(ranges).map(HashSet::new).orElse(null);
            return this;
        }

        public NameFilter.Builder<D,I> ranges(RangeRule... ranges) {
            this.ranges = Optional.ofNullable(ranges).map(rangeList -> new HashSet<>(Arrays.asList(rangeList))).orElse(null);
            return this;
        }

        @Override
        public NameFilter.Builder<D,I> equals(Collection<EqualRule> equals) {
            this.equals = Optional.ofNullable(equals).map(HashSet::new).orElse(null);
            return this;
        }

        @Override
        public NameFilter.Builder<D,I> equals(EqualRule... equals) {
            this.equals = Optional.ofNullable(equals).map(equalList -> new HashSet<>(Arrays.asList(equalList))).orElse(null);
            return this;
        }

        @Override
        public NameFilter.Builder<D,I> contains(Collection<ContainRule> contains) {
            this.contains = Optional.ofNullable(contains).map(HashSet::new).orElse(null);
            return this;
        }

        @Override
        public NameFilter.Builder<D,I> contains(ContainRule... contains) {
            this.contains = Optional.ofNullable(contains).map(containList -> new HashSet<>(Arrays.asList(containList))).orElse(null);
            return this;
        }

        @Override
        public NameFilter.Builder<D,I> startTime(D startTime) {
            this.startTime = startTime;
            return this;
        }

        @Override
        public NameFilter.Builder<D,I> endTime(D endTime) {
            this.endTime = endTime;
            return this;
        }

        @Override
        public NameFilter.Builder<D,I> ids(@NonNull Collection<I> ids) {
            this.ids = new HashSet<>(ids);
            return this;
        }

        @Override
        @SuppressWarnings(value = "unchecked")
        public NameFilter.Builder<D,I> ids(@NonNull I... ids) {
            this.ids = new HashSet<>(Arrays.asList(ids));
            return this;
        }

        @Override
        public NameFilter.Builder<D,I>  tableKey(String tableKey) {
            this.tableKey = tableKey;
            return this;
        }

        @Override
        public NameFilter.Builder<D,I> isRemove(boolean isRemove) {
            this.isRemove = isRemove;
            return this;
        }

        @Override
        public NameFilter.Builder<D,I> operate(OperateType operate) {
            this.operate = operate;
            return this;
        }

        @Override
        public NameFilter.Builder<D,I> operate(Integer operate) {
            this.operate = OperateType.parseKey(operate);
            return this;
        }

        @Override
        public NameFilter.Builder<D,I> operates(@NonNull Collection<OperateType> operates) {
            this.operates = new HashSet<>(operates);
            return this;
        }

        @Override
        public NameFilter.Builder<D,I> operates(@NonNull OperateType... operates) {
            this.operates = new HashSet<>(Arrays.asList(operates));
            return this;
        }

        @Override
        public NameFilter.Builder<D,I> operates(@NonNull Integer... operates) {
            this.operates = new HashSet<>(RestOperate.build(operates));
            return this;
        }

        @Override
        public NameFilter.Builder<D,I> sorts(@NonNull Collection<RestSort> sorts) {
            this.sorts = new HashSet<>(sorts);
            return this;
        }

        @Override
        public NameFilter.Builder<D,I> sorts(@NonNull RestSort... sorts) {
            this.sorts = new HashSet<>(Arrays.asList(sorts));
            return this;
        }

        @Override
        public NameFilter.Builder sorts(@NonNull String... sorts) {
            this.sorts = new HashSet<>(RestSort.build(sorts));
            return this;
        }

        @Override
        public NameFilter.Builder<D,I> pageNum(Integer pageNum) {
            this.pageNum = pageNum;
            return this;
        }

        @Override
        public NameFilter.Builder<D,I> pageSize(Integer pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        @Override
        public NameFilter<D,I> build() {
            return new NameFilter<>(this);
        }
    }
}
