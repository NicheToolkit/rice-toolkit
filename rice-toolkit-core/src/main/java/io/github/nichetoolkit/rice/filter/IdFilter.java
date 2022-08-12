package io.github.nichetoolkit.rice.filter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.OperateModel;
import io.github.nichetoolkit.rice.RestOperate;
import io.github.nichetoolkit.rice.RestSort;
import io.github.nichetoolkit.rice.builder.SqlBuilder;
import io.github.nichetoolkit.rice.builder.SqlBuilders;
import io.github.nichetoolkit.rice.enums.OperateType;
import org.springframework.lang.NonNull;

import java.util.*;

/**
 * <p>IdFilter</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@SuppressWarnings({"WeakerAccess", "MixedMutabilityReturnType"})
public class IdFilter<I> extends OperateFilter {
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

    /**
     * the generic paradigm of T is not Like List<T>、Set<T>
     * It should be String、Long、Integer ...
     * @param ids id list
     */
    @SuppressWarnings(value = "unchecked")
    public IdFilter(@NonNull I... ids) {
        this.ids = new HashSet<>(Arrays.asList(ids));
    }

    public IdFilter(@NonNull Collection<I> ids) {
        this.ids = new HashSet<>(ids);
    }

    public IdFilter(IdFilter.Builder<I> builder) {
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
    public IdFilter<I> addSorts(@NonNull String... sorts) {
        super.addSorts(sorts);
        return this;
    }

    @Override
    public IdFilter<I> addSorts(@NonNull RestSort... sorts) {
        super.addSorts(sorts);
        return this;
    }

    @Override
    public IdFilter<I> addSorts(@NonNull Collection<RestSort> sorts) {
        super.addSorts(sorts);
        return this;
    }

    public IdFilter<I> toIdSql() {
        return toIdSql("id");
    }

    public IdFilter<I> toIdSql(@NonNull String alias) {
        if (GeneralUtils.isNotEmpty(this.id)) {
            SqlBuilders.equal(SQL_BUILDER, alias, this.id);
        } else if (GeneralUtils.isNotEmpty(this.ids)) {
            SqlBuilders.in(SQL_BUILDER, alias, this.ids);
        }
        return this;
    }

    public IdFilter<I> toOperateSql() {
        toOperateSql("operate");
        return this;
    }

    public IdFilter<I> toOperateSql(@NonNull String alias) {
        if (this.isRemove) {
            SqlBuilders.equal(SQL_BUILDER, alias, OperateType.REMOVE);
        } else if (GeneralUtils.isNotEmpty(this.operate)) {
            SqlBuilders.equal(SQL_BUILDER, alias, this.operate);
        } else if (GeneralUtils.isNotEmpty(this.operates)) {
            SqlBuilders.in(SQL_BUILDER, alias, this.operates);
        } else {
            SqlBuilders.nonin(SQL_BUILDER, alias, Arrays.asList(OperateType.REMOVE,OperateType.DELETE));
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

    public static class Builder<I> extends OperateFilter.Builder {
        protected I id;
        protected Set<I> ids;

        public Builder() {
        }

        public IdFilter.Builder<I> id(I id) {
            this.id = id;
            return this;
        }

        public IdFilter.Builder<I> ids(@NonNull Collection<I> ids) {
            this.ids = new HashSet<>(ids);
            return this;
        }

        @SuppressWarnings(value = "unchecked")
        public IdFilter.Builder<I> ids(@NonNull I... ids) {
            this.ids = new HashSet<>(Arrays.asList(ids));
            return this;
        }

        @Override
        public IdFilter.Builder<I> isRemove(boolean isRemove) {
            this.isRemove = isRemove;
            return this;
        }

        @Override
        public IdFilter.Builder<I> operate(OperateType operate) {
            this.operate = operate;
            return this;
        }

        @Override
        public IdFilter.Builder<I> operate(Integer operate) {
            this.operate = OperateType.parseKey(operate);
            return this;
        }

        @Override
        public IdFilter.Builder<I> operates(@NonNull Collection<OperateType> operates) {
            this.operates = new HashSet<>(operates);
            return this;
        }

        @Override
        public IdFilter.Builder<I> operates(@NonNull OperateType... operates) {
            this.operates = new HashSet<>(Arrays.asList(operates));
            return this;
        }

        @Override
        public IdFilter.Builder<I> operates(@NonNull Integer... operates) {
            this.operates = new HashSet<>(RestOperate.build(operates));
            return this;
        }

        @Override
        public IdFilter.Builder<I> sorts(@NonNull Collection<RestSort> sorts) {
            this.sorts = new HashSet<>(sorts);
            return this;
        }

        @Override
        public IdFilter.Builder<I> sorts(@NonNull RestSort... sorts) {
            this.sorts = new HashSet<>(Arrays.asList(sorts));
            return this;
        }

        @Override
        public IdFilter.Builder sorts(@NonNull String... sorts) {
            this.sorts = new HashSet<>(RestSort.build(sorts));
            return this;
        }

        @Override
        public IdFilter.Builder<I> pageNum(Integer pageNum) {
            this.pageNum = pageNum;
            return this;
        }

        @Override
        public IdFilter.Builder<I> pageSize(Integer pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        @Override
        public IdFilter<I> build() {
            return new IdFilter<>(this);
        }
    }
}
