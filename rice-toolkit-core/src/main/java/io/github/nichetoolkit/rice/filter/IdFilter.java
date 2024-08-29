package io.github.nichetoolkit.rice.filter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.RestOperate;
import io.github.nichetoolkit.rice.RestSort;
import io.github.nichetoolkit.rice.builder.SqlBuilder;
import io.github.nichetoolkit.rice.builder.SqlBuilders;
import io.github.nichetoolkit.rice.enums.OperateType;
import org.springframework.lang.NonNull;

import java.util.*;

@SuppressWarnings({"WeakerAccess", "MixedMutabilityReturnType"})
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class IdFilter<I, K> extends TableFilter<K> {
    @JsonIgnore
    protected final SqlBuilder SQL_BUILDER = new SqlBuilder();

    @JsonIgnore
    protected final ThreadLocal<String> SQL_CACHE = new ThreadLocal<>();

    protected I id;

    protected Set<I> ids;

    public IdFilter() {
    }

    public IdFilter(I id) {
        this.id = id;
    }

    @SuppressWarnings(value = "unchecked")
    public IdFilter(@NonNull I... ids) {
        this.ids = new HashSet<>(Arrays.asList(ids));
    }

    public IdFilter(@NonNull Collection<I> ids) {
        this.ids = new HashSet<>(ids);
    }

    public IdFilter(IdFilter.Builder<I, K> builder) {
        super(builder);
        this.id = builder.id;
        this.ids = builder.ids;
    }

    public I getId() {
        return id;
    }

    public void setId(I id) {
        this.id = id;
    }

    public List<I> getIds() {
        if (GeneralUtils.isNotEmpty(ids)) {
            return new ArrayList<>(ids);
        }
        return Collections.emptyList();
    }

    @JsonSetter
    public void setIds(@NonNull Collection<I> ids) {
        this.ids = new HashSet<>(ids);
    }

    @SuppressWarnings(value = "unchecked")
    public void setIds(@NonNull I... ids) {
        this.ids = new HashSet<>(Arrays.asList(ids));
    }

    @SuppressWarnings(value = "unchecked")
    public void addIds(@NonNull I... ids) {
        if (GeneralUtils.isEmpty(this.ids)) {
            this.ids = new HashSet<>(Arrays.asList(ids));
        } else {
            this.ids.addAll(Arrays.asList(ids));
        }
    }

    public void addIds(@NonNull Collection<I> ids) {
        if (GeneralUtils.isEmpty(this.ids)) {
            this.ids = new HashSet<>(ids);
        } else {
            this.ids.addAll(ids);
        }
    }

    public List<I> toIds() {
        Set<I> idSet = new HashSet<>();
        if (GeneralUtils.isNotEmpty(this.id)) {
            idSet.add(this.id);
        }
        if (GeneralUtils.isNotEmpty(this.ids)) {
            idSet.addAll(this.ids);
        }
        return new ArrayList<>(idSet);
    }

    public String toIdSort(@NonNull String alias) {
        addSorts(alias);
        return super.toSort();
    }

    public String toSql() {
        return toSql(false);
    }

    public String toSql(boolean resume) {
        String sql = this.SQL_CACHE.get();
        if (!resume && GeneralUtils.isNotEmpty(sql)) {
            return sql;
        }
        this.SQL_CACHE.remove();
        String sort = super.toSort();
        sql = this.SQL_BUILDER.append(sort).toString();
        if (GeneralUtils.isNotEmpty(sql)) {
            this.SQL_CACHE.set(sql);
            this.SQL_BUILDER.clear();
            return sql;
        }
        return null;
    }

    public String toDeleteSql() {
        return toNonsortSql();
    }

    public String toNonsortSql() {
        String sql = this.SQL_BUILDER.toString();
        if (GeneralUtils.isNotEmpty(sql)) {
            this.SQL_BUILDER.clear();
            return sql;
        }
        return null;
    }

    @Override
    public IdFilter<I, K> addSorts(@NonNull String... sorts) {
        super.addSorts(sorts);
        return this;
    }

    @Override
    public IdFilter<I, K> addSorts(@NonNull RestSort<?>... sorts) {
        super.addSorts(sorts);
        return this;
    }

    @Override
    public IdFilter<I, K> addSorts(@NonNull Collection<RestSort<?>> sorts) {
        super.addSorts(sorts);
        return this;
    }

    public IdFilter<I, K> toIdSql() {
        return toIdSql("id");
    }

    public IdFilter<I, K> toIdSql(@NonNull String alias) {
        if (GeneralUtils.isNotEmpty(this.id)) {
            SqlBuilders.equal(SQL_BUILDER, alias, this.id);
        } else if (GeneralUtils.isNotEmpty(this.ids)) {
            SqlBuilders.in(SQL_BUILDER, alias, this.ids);
        }
        return this;
    }

    public IdFilter<I, K> toOperateSql() {
        toOperateSql("operate");
        return this;
    }

    public IdFilter<I, K> toOperateSql(@NonNull String alias) {
        if (this.isRemove) {
            SqlBuilders.equal(SQL_BUILDER, alias, OperateType.REMOVE);
        } else if (GeneralUtils.isNotEmpty(this.operate)) {
            SqlBuilders.equal(SQL_BUILDER, alias, this.operate);
        } else if (GeneralUtils.isNotEmpty(this.operates)) {
            SqlBuilders.in(SQL_BUILDER, alias, this.operates);
        } else {
            SqlBuilders.nonin(SQL_BUILDER, alias, Arrays.asList(OperateType.REMOVE, OperateType.DELETE));
        }
        return this;
    }

    @Override
    public String toKey() {
        String pageKey = super.toKey();
        StringBuilder keyBuilder = new StringBuilder();
        if (GeneralUtils.isNotEmpty(id)) {
            keyBuilder.append(id).append(PAGE_REGEX);
        }
        if (GeneralUtils.isNotEmpty(ids)) {
            this.ids.forEach(index -> keyBuilder.append(index).append(PAGE_REGEX));
        }
        keyBuilder.append(pageKey);
        return keyBuilder.toString();
    }

    public static class Builder<I, K> extends TableFilter.Builder<K> {
        protected I id;
        protected Set<I> ids;

        public Builder() {
        }

        public IdFilter.Builder<I, K> id(I id) {
            this.id = id;
            return this;
        }

        public IdFilter.Builder<I, K> ids(@NonNull Collection<I> ids) {
            this.ids = new HashSet<>(ids);
            return this;
        }

        @SuppressWarnings(value = "unchecked")
        public IdFilter.Builder<I, K> ids(@NonNull I... ids) {
            this.ids = new HashSet<>(Arrays.asList(ids));
            return this;
        }

        @Override
        public IdFilter.Builder<I, K> tablekey(K tablekey) {
            this.tablekey = tablekey;
            return this;
        }

        @Override
        public IdFilter.Builder<I, K> isRemove(boolean isRemove) {
            this.isRemove = isRemove;
            return this;
        }

        @Override
        public IdFilter.Builder<I, K> operate(OperateType operate) {
            this.operate = operate;
            return this;
        }

        @Override
        public IdFilter.Builder<I, K> operate(Integer operate) {
            this.operate = OperateType.parseKey(operate);
            return this;
        }

        @Override
        public IdFilter.Builder<I, K> operates(@NonNull Collection<OperateType> operates) {
            this.operates = new HashSet<>(operates);
            return this;
        }

        @Override
        public IdFilter.Builder<I, K> operates(@NonNull OperateType... operates) {
            this.operates = new HashSet<>(Arrays.asList(operates));
            return this;
        }

        @Override
        public IdFilter.Builder<I, K> operates(@NonNull Integer... operates) {
            this.operates = new HashSet<>(RestOperate.build(operates));
            return this;
        }

        @Override
        public IdFilter.Builder<I, K> sorts(@NonNull Collection<RestSort<?>> sorts) {
            this.sorts = new HashSet<>(sorts);
            return this;
        }

        @Override
        public IdFilter.Builder<I, K> sorts(@NonNull RestSort<?>... sorts) {
            this.sorts = new HashSet<>(Arrays.asList(sorts));
            return this;
        }

        @Override
        public IdFilter.Builder<I, K> sorts(@NonNull String... sorts) {
            this.sorts = new HashSet<>(RestSort.build(sorts));
            return this;
        }

        @Override
        public IdFilter.Builder<I, K> pageNum(Integer pageNum) {
            this.pageNum = pageNum;
            return this;
        }

        @Override
        public IdFilter.Builder<I, K> pageSize(Integer pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        @Override
        public IdFilter<I, K> build() {
            return new IdFilter<>(this);
        }
    }
}
