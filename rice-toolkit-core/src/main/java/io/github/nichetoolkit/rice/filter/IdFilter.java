package io.github.nichetoolkit.rice.filter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.github.nichetoolkit.mybatis.builder.SqlBuilder;
import io.github.nichetoolkit.mybatis.table.RestIdentity;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.error.natives.UnsupportedErrorException;
import io.github.nichetoolkit.rest.reflect.RestGenericTypes;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.RestSort;
import io.github.nichetoolkit.rice.builder.SqlBuilders;
import io.github.nichetoolkit.rice.enums.OperateType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.NonNull;

import java.util.*;

@Getter
@Setter
@SuperBuilder(builderMethodName = "ofIdBuilder")
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

    @JsonIgnore
    @SuppressWarnings("unchecked")
    public Class<I> getIdType() {
        return (Class<I>) RestGenericTypes.resolveClass(RestGenericTypes.resolveType(
                IdFilter.class.getTypeParameters()[0], getClass(), IdFilter.class));
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

    public IdFilter<I, K> toIdSql() throws RestException {
        return toIdSql("id");
    }

    public IdFilter<I, K> toIdSql(@NonNull String alias) throws RestException {
        if (getIdType().isAnnotationPresent(RestIdentity.class)) {
            toIdentitySql(SQL_BUILDER,alias);
        } else {
            if (GeneralUtils.isNotEmpty(this.id)) {
                SqlBuilders.equal(SQL_BUILDER, alias, this.id);
            } else if (GeneralUtils.isNotEmpty(this.ids)) {
                SqlBuilders.in(SQL_BUILDER, alias, this.ids);
            }
        }
        return this;
    }

    public void toIdentitySql(SqlBuilder sqlBuilder,@NonNull String alias) throws RestException {
        throw new UnsupportedErrorException("the method of 'toIdentitySql()' is unsupported.");
    }

    public IdFilter<I, K> toOperateSql() throws RestException {
        toOperateSql("operate");
        return this;
    }

    public IdFilter<I, K> toOperateSql(@NonNull String alias) throws RestException {
        if (this.isRemove) {
            SqlBuilders.equal(SQL_BUILDER, alias, OperateType.REMOVE);
        } else if (GeneralUtils.isNotEmpty(this.operate)) {
            SqlBuilders.equal(SQL_BUILDER, alias, this.operate);
        } else if (GeneralUtils.isNotEmpty(this.operates)) {
            SqlBuilders.in(SQL_BUILDER, alias, this.operates);
        } else {
            SqlBuilders.nin(SQL_BUILDER, alias, Arrays.asList(OperateType.REMOVE, OperateType.DELETE));
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
}
