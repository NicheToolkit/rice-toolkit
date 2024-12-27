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
import io.github.nichetoolkit.rice.RestOperate;
import io.github.nichetoolkit.rice.RestSort;
import io.github.nichetoolkit.rice.builder.SqlBuilders;
import io.github.nichetoolkit.rice.enums.OperateType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.util.*;

/**
 * <code>IdFilter</code>
 * <p>The id filter class.</p>
 * @param <I>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @see  io.github.nichetoolkit.rice.filter.TableFilter
 * @see  lombok.Getter
 * @see  lombok.Setter
 * @see  java.lang.SuppressWarnings
 * @see  com.fasterxml.jackson.annotation.JsonInclude
 * @see  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Getter
@Setter
@SuppressWarnings({"WeakerAccess", "MixedMutabilityReturnType"})
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class IdFilter<I, K> extends TableFilter<K> {
    /**
     * <code>SQL_BUILDER</code>
     * {@link io.github.nichetoolkit.mybatis.builder.SqlBuilder} <p>The <code>SQL_BUILDER</code> field.</p>
     * @see  io.github.nichetoolkit.mybatis.builder.SqlBuilder
     * @see  com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    protected final SqlBuilder SQL_BUILDER = new SqlBuilder();

    /**
     * <code>SQL_CACHE</code>
     * {@link java.lang.ThreadLocal} <p>The <code>SQL_CACHE</code> field.</p>
     * @see  java.lang.ThreadLocal
     * @see  com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    protected final ThreadLocal<String> SQL_CACHE = new ThreadLocal<>();

    /**
     * <code>id</code>
     * <p>The <code>id</code> field.</p>
     */
    protected I id;

    /**
     * <code>ids</code>
     * {@link java.util.Set} <p>The <code>ids</code> field.</p>
     * @see  java.util.Set
     */
    protected Set<I> ids;

    /**
     * <code>IdFilter</code>
     * <p>Instantiates a new id filter.</p>
     */
    public IdFilter() {
    }

    /**
     * <code>IdFilter</code>
     * <p>Instantiates a new id filter.</p>
     * @param id I <p>The id parameter is <code>I</code> type.</p>
     */
    public IdFilter(I id) {
        this.id = id;
    }

    /**
     * <code>IdFilter</code>
     * <p>Instantiates a new id filter.</p>
     * @param ids I <p>The ids parameter is <code>I</code> type.</p>
     * @see  org.springframework.lang.NonNull
     * @see  java.lang.SuppressWarnings
     */
    @SuppressWarnings(value = "unchecked")
    public IdFilter(@NonNull I... ids) {
        this.ids = new HashSet<>(Arrays.asList(ids));
    }

    /**
     * <code>IdFilter</code>
     * <p>Instantiates a new id filter.</p>
     * @param ids {@link java.util.Collection} <p>The ids parameter is <code>Collection</code> type.</p>
     * @see  java.util.Collection
     * @see  org.springframework.lang.NonNull
     */
    public IdFilter(@NonNull Collection<I> ids) {
        this.ids = new HashSet<>(ids);
    }

    /**
     * <code>IdFilter</code>
     * <p>Instantiates a new id filter.</p>
     * @param builder {@link io.github.nichetoolkit.rice.filter.IdFilter.Builder} <p>The builder parameter is <code>Builder</code> type.</p>
     * @see  io.github.nichetoolkit.rice.filter.IdFilter.Builder
     */
    public IdFilter(IdFilter.Builder<I, K> builder) {
        super(builder);
        this.id = builder.id;
        this.ids = builder.ids;
    }

    /**
     * <code>getIdType</code>
     * <p>The get id type getter method.</p>
     * @return  {@link java.lang.Class} <p>The get id type return object is <code>Class</code> type.</p>
     * @see  java.lang.Class
     * @see  com.fasterxml.jackson.annotation.JsonIgnore
     * @see  java.lang.SuppressWarnings
     */
    @JsonIgnore
    @SuppressWarnings("unchecked")
    public Class<I> getIdType() {
        return (Class<I>) RestGenericTypes.resolveClass(RestGenericTypes.resolveType(
                IdFilter.class.getTypeParameters()[0], getClass(), IdFilter.class));
    }

    /**
     * <code>getIds</code>
     * <p>The get ids getter method.</p>
     * @return  {@link java.util.List} <p>The get ids return object is <code>List</code> type.</p>
     * @see  java.util.List
     */
    public List<I> getIds() {
        if (GeneralUtils.isNotEmpty(ids)) {
            return new ArrayList<>(ids);
        }
        return Collections.emptyList();
    }

    /**
     * <code>setIds</code>
     * <p>The set ids setter method.</p>
     * @param ids {@link java.util.Collection} <p>The ids parameter is <code>Collection</code> type.</p>
     * @see  java.util.Collection
     * @see  org.springframework.lang.NonNull
     * @see  com.fasterxml.jackson.annotation.JsonSetter
     */
    @JsonSetter
    public void setIds(@NonNull Collection<I> ids) {
        this.ids = new HashSet<>(ids);
    }

    /**
     * <code>setIds</code>
     * <p>The set ids setter method.</p>
     * @param ids I <p>The ids parameter is <code>I</code> type.</p>
     * @see  org.springframework.lang.NonNull
     * @see  java.lang.SuppressWarnings
     */
    @SuppressWarnings(value = "unchecked")
    public void setIds(@NonNull I... ids) {
        this.ids = new HashSet<>(Arrays.asList(ids));
    }

    /**
     * <code>addIds</code>
     * <p>The add ids method.</p>
     * @param ids I <p>The ids parameter is <code>I</code> type.</p>
     * @see  org.springframework.lang.NonNull
     * @see  java.lang.SuppressWarnings
     */
    @SuppressWarnings(value = "unchecked")
    public void addIds(@NonNull I... ids) {
        if (GeneralUtils.isEmpty(this.ids)) {
            this.ids = new HashSet<>(Arrays.asList(ids));
        } else {
            this.ids.addAll(Arrays.asList(ids));
        }
    }

    /**
     * <code>addIds</code>
     * <p>The add ids method.</p>
     * @param ids {@link java.util.Collection} <p>The ids parameter is <code>Collection</code> type.</p>
     * @see  java.util.Collection
     * @see  org.springframework.lang.NonNull
     */
    public void addIds(@NonNull Collection<I> ids) {
        if (GeneralUtils.isEmpty(this.ids)) {
            this.ids = new HashSet<>(ids);
        } else {
            this.ids.addAll(ids);
        }
    }

    /**
     * <code>toIds</code>
     * <p>The to ids method.</p>
     * @return  {@link java.util.List} <p>The to ids return object is <code>List</code> type.</p>
     * @see  java.util.List
     */
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

    /**
     * <code>toIdSort</code>
     * <p>The to id sort method.</p>
     * @param alias {@link java.lang.String} <p>The alias parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  org.springframework.lang.NonNull
     * @return  {@link java.lang.String} <p>The to id sort return object is <code>String</code> type.</p>
     */
    public String toIdSort(@NonNull String alias) {
        addSorts(alias);
        return super.toSort();
    }

    /**
     * <code>toSql</code>
     * <p>The to sql method.</p>
     * @return  {@link java.lang.String} <p>The to sql return object is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    public String toSql() {
        return toSql(false);
    }

    /**
     * <code>toSql</code>
     * <p>The to sql method.</p>
     * @param resume boolean <p>The resume parameter is <code>boolean</code> type.</p>
     * @return  {@link java.lang.String} <p>The to sql return object is <code>String</code> type.</p>
     * @see  java.lang.String
     */
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

    /**
     * <code>toDeleteSql</code>
     * <p>The to delete sql method.</p>
     * @return  {@link java.lang.String} <p>The to delete sql return object is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    public String toDeleteSql() {
        return toNonsortSql();
    }

    /**
     * <code>toNonsortSql</code>
     * <p>The to nonsort sql method.</p>
     * @return  {@link java.lang.String} <p>The to nonsort sql return object is <code>String</code> type.</p>
     * @see  java.lang.String
     */
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

    /**
     * <code>toIdSql</code>
     * <p>The to id sql method.</p>
     * @return  {@link io.github.nichetoolkit.rice.filter.IdFilter} <p>The to id sql return object is <code>IdFilter</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see  io.github.nichetoolkit.rest.RestException
     */
    public IdFilter<I, K> toIdSql() throws RestException {
        return toIdSql("id");
    }

    /**
     * <code>toIdSql</code>
     * <p>The to id sql method.</p>
     * @param alias {@link java.lang.String} <p>The alias parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  org.springframework.lang.NonNull
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link io.github.nichetoolkit.rice.filter.IdFilter} <p>The to id sql return object is <code>IdFilter</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
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

    /**
     * <code>toIdentitySql</code>
     * <p>The to identity sql method.</p>
     * @param sqlBuilder {@link io.github.nichetoolkit.mybatis.builder.SqlBuilder} <p>The sql builder parameter is <code>SqlBuilder</code> type.</p>
     * @param alias {@link java.lang.String} <p>The alias parameter is <code>String</code> type.</p>
     * @see  io.github.nichetoolkit.mybatis.builder.SqlBuilder
     * @see  java.lang.String
     * @see  org.springframework.lang.NonNull
     * @see  io.github.nichetoolkit.rest.RestException
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public void toIdentitySql(SqlBuilder sqlBuilder,@NonNull String alias) throws RestException {
        throw new UnsupportedErrorException("the method of 'toIdentitySql()' is unsupported.");
    }

    /**
     * <code>toOperateSql</code>
     * <p>The to operate sql method.</p>
     * @return  {@link io.github.nichetoolkit.rice.filter.IdFilter} <p>The to operate sql return object is <code>IdFilter</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see  io.github.nichetoolkit.rest.RestException
     */
    public IdFilter<I, K> toOperateSql() throws RestException {
        toOperateSql("operate");
        return this;
    }

    /**
     * <code>toOperateSql</code>
     * <p>The to operate sql method.</p>
     * @param alias {@link java.lang.String} <p>The alias parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  org.springframework.lang.NonNull
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link io.github.nichetoolkit.rice.filter.IdFilter} <p>The to operate sql return object is <code>IdFilter</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public IdFilter<I, K> toOperateSql(@NonNull String alias) throws RestException {
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

    /**
     * <code>Builder</code>
     * <p>The builder class.</p>
     * @param <I>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @see  io.github.nichetoolkit.rice.filter.TableFilter.Builder
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk1.8
     */
    public static class Builder<I, K> extends TableFilter.Builder<K> {
        /**
         * <code>id</code>
         * <p>The <code>id</code> field.</p>
         */
        protected I id;
        /**
         * <code>ids</code>
         * {@link java.util.Set} <p>The <code>ids</code> field.</p>
         * @see  java.util.Set
         */
        protected Set<I> ids;

        /**
         * <code>Builder</code>
         * <p>Instantiates a new builder.</p>
         */
        public Builder() {
        }

        /**
         * <code>id</code>
         * <p>The id method.</p>
         * @param id I <p>The id parameter is <code>I</code> type.</p>
         * @return  {@link io.github.nichetoolkit.rice.filter.IdFilter.Builder} <p>The id return object is <code>Builder</code> type.</p>
         */
        public IdFilter.Builder<I, K> id(I id) {
            this.id = id;
            return this;
        }

        /**
         * <code>ids</code>
         * <p>The ids method.</p>
         * @param ids {@link java.util.Collection} <p>The ids parameter is <code>Collection</code> type.</p>
         * @see  java.util.Collection
         * @see  org.springframework.lang.NonNull
         * @return  {@link io.github.nichetoolkit.rice.filter.IdFilter.Builder} <p>The ids return object is <code>Builder</code> type.</p>
         */
        public IdFilter.Builder<I, K> ids(@NonNull Collection<I> ids) {
            this.ids = new HashSet<>(ids);
            return this;
        }

        /**
         * <code>ids</code>
         * <p>The ids method.</p>
         * @param ids I <p>The ids parameter is <code>I</code> type.</p>
         * @see  org.springframework.lang.NonNull
         * @see  java.lang.SuppressWarnings
         * @return  {@link io.github.nichetoolkit.rice.filter.IdFilter.Builder} <p>The ids return object is <code>Builder</code> type.</p>
         */
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
