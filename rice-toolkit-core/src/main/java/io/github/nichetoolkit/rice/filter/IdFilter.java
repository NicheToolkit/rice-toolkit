package io.github.nichetoolkit.rice.filter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.github.nichetoolkit.rest.util.common.GeneralUtils;
import io.github.nichetoolkit.rice.RestSort;
import io.github.nichetoolkit.rice.builder.SqlBuilder;
import io.github.nichetoolkit.rice.builder.SqlBuilders;
import org.springframework.lang.NonNull;

import java.util.*;

/**
 * <p>IdFilter</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@SuppressWarnings("WeakerAccess")
public class IdFilter<I> extends SortFilter{
    @JsonIgnore
    protected final SqlBuilder SQL_BUILDER = new SqlBuilder();

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

    public String toIdSort() {
        return toIdSort("id");
    }

    public String toIdSort(@NonNull String alias) {
        addSorts(alias);
        return super.toSort();
    }

    public String toSql() {
        String sort = super.toSort();
        String sql = this.SQL_BUILDER.append(sort).toString();
        return sql.length() > 0 ? sql : null;
    }

    public String toIdSql() {
        return toIdSql("id").toSql();
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

    public IdFilter<I> toIdSql(@NonNull String alias) {
        if (GeneralUtils.isNotEmpty(this.id)) {
            SqlBuilders.equal(SQL_BUILDER, alias, this.id);
        } else if (GeneralUtils.isNotEmpty(this.ids)) {
            SqlBuilders.in(SQL_BUILDER, alias, this.ids);
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

    public static class Builder<I> extends SortFilter.Builder {
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

        public IdFilter.Builder<I> sorts(@NonNull Collection<RestSort> sorts) {
            this.sorts = new HashSet<>(sorts);
            return this;
        }

        public IdFilter.Builder<I> sorts(@NonNull RestSort... sorts) {
            this.sorts = new HashSet<>(Arrays.asList(sorts));
            return this;
        }

        public IdFilter.Builder<I> pageNum(Integer pageNum) {
            this.pageNum = pageNum;
            return this;
        }

        public IdFilter.Builder<I> pageSize(Integer pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public IdFilter<I> build() {
            return new IdFilter<>(this);
        }
    }
}
