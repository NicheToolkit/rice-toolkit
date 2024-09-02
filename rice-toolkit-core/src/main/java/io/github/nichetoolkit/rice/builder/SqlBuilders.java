package io.github.nichetoolkit.rice.builder;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.github.nichetoolkit.mybatis.enums.SortType;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.RestSort;
import io.github.nichetoolkit.rice.filter.PageFilter;
import io.github.nichetoolkit.rice.filter.SortFilter;

import java.util.Collection;

/**
 * <code>SqlBuilders</code>
 * <p>The type sql builders class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class SqlBuilders {

    /**
     * <code>newSqlBuilder</code>
     * <p>the sql builder method.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the sql builder return object is <code>SqlBuilder</code> type.</p>
     */
    public static SqlBuilder newSqlBuilder() {
        return new SqlBuilder();
    }

    /**
     * <code>newSqlBuilder</code>
     * <p>the sql builder method.</p>
     * @param sqlBuilder {@link java.lang.StringBuilder} <p>the sql builder parameter is <code>StringBuilder</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the sql builder return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.StringBuilder
     */
    public static SqlBuilder newSqlBuilder(StringBuilder sqlBuilder) {
        return new SqlBuilder(sqlBuilder);
    }

    /**
     * <code>newSqlBuilder</code>
     * <p>the sql builder method.</p>
     * @param capacity int <p>the capacity parameter is <code>int</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the sql builder return object is <code>SqlBuilder</code> type.</p>
     */
    public static SqlBuilder newSqlBuilder(int capacity) {
        return new SqlBuilder(capacity);
    }

    /**
     * <code>newSqlBuilder</code>
     * <p>the sql builder method.</p>
     * @param str {@link java.lang.String} <p>the str parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the sql builder return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.String
     */
    public static SqlBuilder newSqlBuilder(String str) {
        return new SqlBuilder(str);
    }

    /**
     * <code>newSqlBuilder</code>
     * <p>the sql builder method.</p>
     * @param seq {@link java.lang.CharSequence} <p>the seq parameter is <code>CharSequence</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the sql builder return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.CharSequence
     */
    public static SqlBuilder newSqlBuilder(CharSequence seq) {
        return new SqlBuilder(seq);
    }

    /**
     * <code>nonin</code>
     * <p>the method.</p>
     * @param sqlBuilder {@link java.lang.StringBuilder} <p>the sql builder parameter is <code>StringBuilder</code> type.</p>
     * @param target     {@link java.lang.String} <p>the target parameter is <code>String</code> type.</p>
     * @param values     {@link java.util.Collection} <p>the values parameter is <code>Collection</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.StringBuilder
     * @see java.util.Collection
     */
    public static SqlBuilder nonin(StringBuilder sqlBuilder, String target, Collection<?> values) {
        return newSqlBuilder(sqlBuilder).nin(target, values, true);
    }

    /**
     * <code>nonin</code>
     * <p>the method.</p>
     * @param sqlBuilder {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the sql builder parameter is <code>SqlBuilder</code> type.</p>
     * @param target     {@link java.lang.String} <p>the target parameter is <code>String</code> type.</p>
     * @param values     {@link java.util.Collection} <p>the values parameter is <code>Collection</code> type.</p>
     * @see java.lang.String
     * @see java.util.Collection
     */
    public static void nonin(SqlBuilder sqlBuilder, String target, Collection<?> values) {
        sqlBuilder.nin(target, values, true);
    }

    /**
     * <code>in</code>
     * <p>the method.</p>
     * @param sqlBuilder {@link java.lang.StringBuilder} <p>the sql builder parameter is <code>StringBuilder</code> type.</p>
     * @param target     {@link java.lang.String} <p>the target parameter is <code>String</code> type.</p>
     * @param values     {@link java.util.Collection} <p>the values parameter is <code>Collection</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.StringBuilder
     * @see java.util.Collection
     */
    public static SqlBuilder in(StringBuilder sqlBuilder, String target, Collection<?> values) {
        return newSqlBuilder(sqlBuilder).in(target, values, true);
    }

    /**
     * <code>in</code>
     * <p>the method.</p>
     * @param sqlBuilder {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the sql builder parameter is <code>SqlBuilder</code> type.</p>
     * @param target     {@link java.lang.String} <p>the target parameter is <code>String</code> type.</p>
     * @param values     {@link java.util.Collection} <p>the values parameter is <code>Collection</code> type.</p>
     * @see java.lang.String
     * @see java.util.Collection
     */
    public static void in(SqlBuilder sqlBuilder, String target, Collection<?> values) {
        sqlBuilder.in(target, values, true);
    }

    /**
     * <code>range</code>
     * <p>the method.</p>
     * @param sqlBuilder {@link java.lang.StringBuilder} <p>the sql builder parameter is <code>StringBuilder</code> type.</p>
     * @param target     {@link java.lang.String} <p>the target parameter is <code>String</code> type.</p>
     * @param beginValue {@link java.lang.Object} <p>the begin value parameter is <code>Object</code> type.</p>
     * @param endValue   {@link java.lang.Object} <p>the end value parameter is <code>Object</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.StringBuilder
     * @see java.lang.Object
     */
    public static SqlBuilder range(StringBuilder sqlBuilder, String target, Object beginValue, Object endValue) {
        return newSqlBuilder(sqlBuilder).re(target, beginValue, endValue, true);
    }

    /**
     * <code>range</code>
     * <p>the method.</p>
     * @param sqlBuilder {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the sql builder parameter is <code>SqlBuilder</code> type.</p>
     * @param target     {@link java.lang.String} <p>the target parameter is <code>String</code> type.</p>
     * @param beginValue {@link java.lang.Object} <p>the begin value parameter is <code>Object</code> type.</p>
     * @param endValue   {@link java.lang.Object} <p>the end value parameter is <code>Object</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public static void range(SqlBuilder sqlBuilder, String target, Object beginValue, Object endValue) {
        sqlBuilder.re(target, beginValue, endValue, true);
    }

    /**
     * <code>scope</code>
     * <p>the method.</p>
     * @param sqlBuilder {@link java.lang.StringBuilder} <p>the sql builder parameter is <code>StringBuilder</code> type.</p>
     * @param minTarget  {@link java.lang.String} <p>the min target parameter is <code>String</code> type.</p>
     * @param maxTarget  {@link java.lang.String} <p>the max target parameter is <code>String</code> type.</p>
     * @param value      {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.StringBuilder
     * @see java.lang.Object
     */
    public static SqlBuilder scope(StringBuilder sqlBuilder, String minTarget, String maxTarget,Object value) {
        return newSqlBuilder(sqlBuilder).se(minTarget, maxTarget, value, true);
    }

    /**
     * <code>scope</code>
     * <p>the method.</p>
     * @param sqlBuilder {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the sql builder parameter is <code>SqlBuilder</code> type.</p>
     * @param minTarget  {@link java.lang.String} <p>the min target parameter is <code>String</code> type.</p>
     * @param maxTarget  {@link java.lang.String} <p>the max target parameter is <code>String</code> type.</p>
     * @param value      {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public static void scope(SqlBuilder sqlBuilder, String minTarget, String maxTarget,Object value) {
        sqlBuilder.se(minTarget, maxTarget, value, true);
    }

    /**
     * <code>both</code>
     * <p>the method.</p>
     * @param sqlBuilder {@link java.lang.StringBuilder} <p>the sql builder parameter is <code>StringBuilder</code> type.</p>
     * @param target     {@link java.lang.String} <p>the target parameter is <code>String</code> type.</p>
     * @param beginValue {@link java.lang.Object} <p>the begin value parameter is <code>Object</code> type.</p>
     * @param endValue   {@link java.lang.Object} <p>the end value parameter is <code>Object</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.StringBuilder
     * @see java.lang.Object
     */
    public static SqlBuilder both(StringBuilder sqlBuilder, String target, Object beginValue, Object endValue) {
        return newSqlBuilder(sqlBuilder).reb(target, beginValue, endValue);
    }

    /**
     * <code>both</code>
     * <p>the method.</p>
     * @param sqlBuilder {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the sql builder parameter is <code>SqlBuilder</code> type.</p>
     * @param target     {@link java.lang.String} <p>the target parameter is <code>String</code> type.</p>
     * @param beginValue {@link java.lang.Object} <p>the begin value parameter is <code>Object</code> type.</p>
     * @param endValue   {@link java.lang.Object} <p>the end value parameter is <code>Object</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public static void both(SqlBuilder sqlBuilder, String target, Object beginValue, Object endValue) {
        sqlBuilder.reb(target, beginValue, endValue);
    }

    /**
     * <code>like</code>
     * <p>the method.</p>
     * @param sqlBuilder {@link java.lang.StringBuilder} <p>the sql builder parameter is <code>StringBuilder</code> type.</p>
     * @param target     {@link java.lang.String} <p>the target parameter is <code>String</code> type.</p>
     * @param value      {@link java.lang.String} <p>the value parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.StringBuilder
     */
    public static SqlBuilder like(StringBuilder sqlBuilder, String target, String value) {
        return newSqlBuilder(sqlBuilder).lk(target, value, true);
    }

    /**
     * <code>like</code>
     * <p>the method.</p>
     * @param sqlBuilder {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the sql builder parameter is <code>SqlBuilder</code> type.</p>
     * @param target     {@link java.lang.String} <p>the target parameter is <code>String</code> type.</p>
     * @param value      {@link java.lang.String} <p>the value parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static void like(SqlBuilder sqlBuilder, String target, String value) {
        sqlBuilder.lk(target, value, true);
    }

    /**
     * <code>like</code>
     * <p>the method.</p>
     * @param sqlBuilder {@link java.lang.StringBuilder} <p>the sql builder parameter is <code>StringBuilder</code> type.</p>
     * @param targets    {@link java.util.Collection} <p>the targets parameter is <code>Collection</code> type.</p>
     * @param value      {@link java.lang.String} <p>the value parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.StringBuilder
     * @see java.util.Collection
     */
    public static SqlBuilder like(StringBuilder sqlBuilder, Collection<String> targets, String value) {
        return newSqlBuilder(sqlBuilder).lk(targets, value, true);
    }

    /**
     * <code>like</code>
     * <p>the method.</p>
     * @param sqlBuilder {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the sql builder parameter is <code>SqlBuilder</code> type.</p>
     * @param targets    {@link java.util.Collection} <p>the targets parameter is <code>Collection</code> type.</p>
     * @param value      {@link java.lang.String} <p>the value parameter is <code>String</code> type.</p>
     * @see java.util.Collection
     * @see java.lang.String
     */
    public static void like(SqlBuilder sqlBuilder, Collection<String> targets, String value) {
        sqlBuilder.lk(targets, value, true);
    }

    /**
     * <code>equal</code>
     * <p>the method.</p>
     * @param sqlBuilder {@link java.lang.StringBuilder} <p>the sql builder parameter is <code>StringBuilder</code> type.</p>
     * @param target     {@link java.lang.String} <p>the target parameter is <code>String</code> type.</p>
     * @param value      {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.StringBuilder
     * @see java.lang.Object
     */
    public static SqlBuilder equal(StringBuilder sqlBuilder, String target, Object value) {
        return newSqlBuilder(sqlBuilder).eq(target, value, true);
    }

    /**
     * <code>equal</code>
     * <p>the method.</p>
     * @param sqlBuilder {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the sql builder parameter is <code>SqlBuilder</code> type.</p>
     * @param target     {@link java.lang.String} <p>the target parameter is <code>String</code> type.</p>
     * @param value      {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public static void equal(SqlBuilder sqlBuilder, String target, Object value) {
        sqlBuilder.eq(target, value, true);
    }

    /**
     * <code>unequal</code>
     * <p>the method.</p>
     * @param sqlBuilder {@link java.lang.StringBuilder} <p>the sql builder parameter is <code>StringBuilder</code> type.</p>
     * @param target     {@link java.lang.String} <p>the target parameter is <code>String</code> type.</p>
     * @param value      {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.StringBuilder
     * @see java.lang.Object
     */
    public static SqlBuilder unequal(StringBuilder sqlBuilder, String target, Object value) {
        return newSqlBuilder(sqlBuilder).neq(target, value, true);
    }

    /**
     * <code>unequal</code>
     * <p>the method.</p>
     * @param sqlBuilder {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the sql builder parameter is <code>SqlBuilder</code> type.</p>
     * @param target     {@link java.lang.String} <p>the target parameter is <code>String</code> type.</p>
     * @param value      {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public static void unequal(SqlBuilder sqlBuilder, String target, Object value) {
        sqlBuilder.neq(target, value, true);
    }

    /**
     * <code>isnull</code>
     * <p>the method.</p>
     * @param sqlBuilder {@link java.lang.StringBuilder} <p>the sql builder parameter is <code>StringBuilder</code> type.</p>
     * @param target     {@link java.lang.String} <p>the target parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.StringBuilder
     */
    public static SqlBuilder isnull(StringBuilder sqlBuilder, String target) {
        return newSqlBuilder(sqlBuilder).nu(target,true);
    }

    /**
     * <code>isnull</code>
     * <p>the method.</p>
     * @param sqlBuilder {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the sql builder parameter is <code>SqlBuilder</code> type.</p>
     * @param target     {@link java.lang.String} <p>the target parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static void isnull(SqlBuilder sqlBuilder, String target) {
        sqlBuilder.nu(target,true);
    }

    /**
     * <code>nonnull</code>
     * <p>the method.</p>
     * @param sqlBuilder {@link java.lang.StringBuilder} <p>the sql builder parameter is <code>StringBuilder</code> type.</p>
     * @param target     {@link java.lang.String} <p>the target parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.StringBuilder
     */
    public static SqlBuilder nonnull(StringBuilder sqlBuilder, String target) {
        return newSqlBuilder(sqlBuilder).nnu(target, true);
    }

    /**
     * <code>nonnull</code>
     * <p>the method.</p>
     * @param sqlBuilder {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the sql builder parameter is <code>SqlBuilder</code> type.</p>
     * @param target     {@link java.lang.String} <p>the target parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static void nonnull(SqlBuilder sqlBuilder, String target) {
        sqlBuilder.nnu(target, true);
    }

    /**
     * <code>append</code>
     * <p>the method.</p>
     * @param sqlBuilder {@link java.lang.StringBuilder} <p>the sql builder parameter is <code>StringBuilder</code> type.</p>
     * @param sql        {@link java.lang.String} <p>the sql parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.StringBuilder
     */
    public static SqlBuilder append(StringBuilder sqlBuilder, String sql) {
        return newSqlBuilder(sqlBuilder).append(sql);
    }

    /**
     * <code>append</code>
     * <p>the method.</p>
     * @param sqlBuilder {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the sql builder parameter is <code>SqlBuilder</code> type.</p>
     * @param sql        {@link java.lang.String} <p>the sql parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static void append(SqlBuilder sqlBuilder, String sql) {
        sqlBuilder.append(sql);
    }

    /**
     * <code>sort</code>
     * <p>the method.</p>
     * @param sqlBuilder {@link java.lang.StringBuilder} <p>the sql builder parameter is <code>StringBuilder</code> type.</p>
     * @param name       {@link java.lang.String} <p>the name parameter is <code>String</code> type.</p>
     * @param type       {@link io.github.nichetoolkit.mybatis.enums.SortType} <p>the type parameter is <code>SortType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.StringBuilder
     * @see io.github.nichetoolkit.mybatis.enums.SortType
     */
    public static SqlBuilder sort(StringBuilder sqlBuilder, String name, SortType type) {
        return newSqlBuilder(sqlBuilder).append(new SortFilter(new RestSort<>(name,type)).toSort());
    }

    /**
     * <code>sort</code>
     * <p>the method.</p>
     * @param sqlBuilder {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the sql builder parameter is <code>SqlBuilder</code> type.</p>
     * @param name       {@link java.lang.String} <p>the name parameter is <code>String</code> type.</p>
     * @param type       {@link io.github.nichetoolkit.mybatis.enums.SortType} <p>the type parameter is <code>SortType</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.mybatis.enums.SortType
     */
    public static void sort(SqlBuilder sqlBuilder, String name, SortType type) {
        sqlBuilder.append(new SortFilter(new RestSort<>(name,type)).toSort());
    }

    /**
     * <code>sort</code>
     * <p>the method.</p>
     * @param sqlBuilder {@link java.lang.StringBuilder} <p>the sql builder parameter is <code>StringBuilder</code> type.</p>
     * @param sorts      {@link io.github.nichetoolkit.rice.RestSort} <p>the sorts parameter is <code>RestSort</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.StringBuilder
     * @see io.github.nichetoolkit.rice.RestSort
     */
    public static SqlBuilder sort(StringBuilder sqlBuilder, RestSort<?>... sorts) {
        return newSqlBuilder(sqlBuilder).append(new SortFilter(sorts).toSort());
    }

    /**
     * <code>sort</code>
     * <p>the method.</p>
     * @param sqlBuilder {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the sql builder parameter is <code>SqlBuilder</code> type.</p>
     * @param sorts      {@link io.github.nichetoolkit.rice.RestSort} <p>the sorts parameter is <code>RestSort</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestSort
     */
    public static void sort(SqlBuilder sqlBuilder, RestSort<?>... sorts) {
        sqlBuilder.append(new SortFilter(sorts).toSort());
    }

    /**
     * <code>sort</code>
     * <p>the method.</p>
     * @param sqlBuilder {@link java.lang.StringBuilder} <p>the sql builder parameter is <code>StringBuilder</code> type.</p>
     * @param filter     {@link io.github.nichetoolkit.rice.filter.SortFilter} <p>the filter parameter is <code>SortFilter</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.StringBuilder
     * @see io.github.nichetoolkit.rice.filter.SortFilter
     */
    public static SqlBuilder sort(StringBuilder sqlBuilder, SortFilter filter) {
        return newSqlBuilder(sqlBuilder).append(filter.toSort());
    }

    /**
     * <code>sort</code>
     * <p>the method.</p>
     * @param sqlBuilder {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the sql builder parameter is <code>SqlBuilder</code> type.</p>
     * @param filter     {@link io.github.nichetoolkit.rice.filter.SortFilter} <p>the filter parameter is <code>SortFilter</code> type.</p>
     * @see io.github.nichetoolkit.rice.filter.SortFilter
     */
    public static void sort(SqlBuilder sqlBuilder, SortFilter filter) {
        sqlBuilder.append(filter.toSort());
    }

    /**
     * <code>page</code>
     * <p>the method.</p>
     * @param <T>  {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param page {@link java.lang.Integer} <p>the page parameter is <code>Integer</code> type.</p>
     * @param size {@link java.lang.Integer} <p>the size parameter is <code>Integer</code> type.</p>
     * @return {@link com.github.pagehelper.Page} <p>the return object is <code>Page</code> type.</p>
     * @see java.lang.Integer
     * @see com.github.pagehelper.Page
     */
    public static <T> Page<T> page(Integer page, Integer size) {
        if (GeneralUtils.isNotEmpty(size)) {
            return PageHelper.startPage(page, size);
        }
        return null;
    }

    /**
     * <code>page</code>
     * <p>the method.</p>
     * @param <T>    {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param filter {@link io.github.nichetoolkit.rice.filter.PageFilter} <p>the filter parameter is <code>PageFilter</code> type.</p>
     * @return {@link com.github.pagehelper.Page} <p>the return object is <code>Page</code> type.</p>
     * @see io.github.nichetoolkit.rice.filter.PageFilter
     * @see com.github.pagehelper.Page
     */
    public static <T> Page<T> page(PageFilter filter) {
        if (GeneralUtils.isNotEmpty(filter.getPageSize())) {
            if (filter.getLoadLastPage()) {
                return PageHelper.startPage(Integer.MAX_VALUE, filter.getPageSize(),true,true,null);
            } else {
                return PageHelper.startPage(filter.getPageNum(), filter.getPageSize());
            }
        }
        return null;
    }
}
