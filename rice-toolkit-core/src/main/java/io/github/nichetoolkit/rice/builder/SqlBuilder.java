package io.github.nichetoolkit.rice.builder;

import io.github.nichetoolkit.rest.RestKey;
import io.github.nichetoolkit.rest.util.DateUtils;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * <code>SqlBuilder</code>
 * <p>The type sql builder class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.io.Serializable
 * @see java.lang.CharSequence
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@SuppressWarnings({"WeakerAccess","UnusedReturnValue"})
public final class SqlBuilder implements Serializable, CharSequence {

    /**
     * <code>EMPTY</code>
     * {@link java.lang.String} <p>the constant <code>EMPTY</code> field.</p>
     * @see java.lang.String
     */
    public static final String EMPTY = "";

    private final StringBuilder sqlBuilder;

    /**
     * <code>SqlBuilder</code>
     * Instantiates a new sql builder.
     */
    public SqlBuilder() {
        this.sqlBuilder = new StringBuilder();
    }

    /**
     * <code>SqlBuilder</code>
     * Instantiates a new sql builder.
     * @param sqlBuilder {@link java.lang.StringBuilder} <p>the sql builder parameter is <code>StringBuilder</code> type.</p>
     * @see java.lang.StringBuilder
     */
    public SqlBuilder(StringBuilder sqlBuilder) {
        this.sqlBuilder = sqlBuilder;
    }

    /**
     * <code>SqlBuilder</code>
     * Instantiates a new sql builder.
     * @param capacity int <p>the capacity parameter is <code>int</code> type.</p>
     */
    public SqlBuilder(int capacity) {
        this.sqlBuilder = new StringBuilder(capacity);
    }

    /**
     * <code>SqlBuilder</code>
     * Instantiates a new sql builder.
     * @param str {@link java.lang.String} <p>the str parameter is <code>String</code> type.</p>
     * @throws RuntimeException {@link java.lang.RuntimeException} <p>the runtime exception is <code>RuntimeException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.RuntimeException
     */
    public SqlBuilder(String str) throws RuntimeException {
        this.sqlBuilder = new StringBuilder(str);
    }

    /**
     * <code>SqlBuilder</code>
     * Instantiates a new sql builder.
     * @param seq {@link java.lang.CharSequence} <p>the seq parameter is <code>CharSequence</code> type.</p>
     * @see java.lang.CharSequence
     */
    public SqlBuilder(CharSequence seq) {
        this.sqlBuilder = new StringBuilder(seq);
    }

    /**
     * <code>clear</code>
     * <p>the method.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder clear() {
        this.sqlBuilder.delete(0, this.sqlBuilder.length());
        return this;
    }

    /**
     * <code>append</code>
     * <p>the method.</p>
     * @param obj {@link java.lang.Object} <p>the obj parameter is <code>Object</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.Object
     */
    public SqlBuilder append(Object obj) {
        return append(String.valueOf(obj));
    }

    /**
     * <code>append</code>
     * <p>the method.</p>
     * @param str {@link java.lang.String} <p>the str parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.String
     */
    public SqlBuilder append(String str) {
        sqlBuilder.append(str);
        return this;
    }

    /**
     * <code>append</code>
     * <p>the method.</p>
     * @param sb {@link java.lang.StringBuffer} <p>the sb parameter is <code>StringBuffer</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.StringBuffer
     */
    public SqlBuilder append(StringBuffer sb) {
        sqlBuilder.append(sb);
        return this;
    }

    /**
     * <code>append</code>
     * <p>the method.</p>
     * @param s {@link java.lang.CharSequence} <p>the s parameter is <code>CharSequence</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.CharSequence
     */
    public SqlBuilder append(CharSequence s) {
        sqlBuilder.append(s);
        return this;
    }

    /**
     * <code>append</code>
     * <p>the method.</p>
     * @param s     {@link java.lang.CharSequence} <p>the s parameter is <code>CharSequence</code> type.</p>
     * @param start int <p>the start parameter is <code>int</code> type.</p>
     * @param end   int <p>the end parameter is <code>int</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.CharSequence
     */
    public SqlBuilder append(CharSequence s, int start, int end) {
        sqlBuilder.append(s, start, end);
        return this;
    }

    /**
     * <code>append</code>
     * <p>the method.</p>
     * @param str char <p>the str parameter is <code>char</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder append(char[] str) {
        sqlBuilder.append(str);
        return this;
    }


    /**
     * <code>append</code>
     * <p>the method.</p>
     * @param str    char <p>the str parameter is <code>char</code> type.</p>
     * @param offset int <p>the offset parameter is <code>int</code> type.</p>
     * @param len    int <p>the len parameter is <code>int</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder append(char[] str, int offset, int len) {
        sqlBuilder.append(str, offset, len);
        return this;
    }


    /**
     * <code>append</code>
     * <p>the method.</p>
     * @param b boolean <p>the b parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder append(boolean b) {
        sqlBuilder.append(b);
        return this;
    }


    /**
     * <code>append</code>
     * <p>the method.</p>
     * @param c char <p>the c parameter is <code>char</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder append(char c) {
        sqlBuilder.append(c);
        return this;
    }


    /**
     * <code>append</code>
     * <p>the method.</p>
     * @param i int <p>the parameter is <code>int</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder append(int i) {
        sqlBuilder.append(i);
        return this;
    }


    /**
     * <code>append</code>
     * <p>the method.</p>
     * @param lng long <p>the lng parameter is <code>long</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder append(long lng) {
        sqlBuilder.append(lng);
        return this;
    }


    /**
     * <code>append</code>
     * <p>the method.</p>
     * @param f float <p>the f parameter is <code>float</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder append(float f) {
        sqlBuilder.append(f);
        return this;
    }

    /**
     * <code>append</code>
     * <p>the method.</p>
     * @param d double <p>the d parameter is <code>double</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder append(double d) {
        sqlBuilder.append(d);
        return this;
    }

    /**
     * <code>appendCodePoint</code>
     * <p>the code point method.</p>
     * @param codePoint int <p>the code point parameter is <code>int</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the code point return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder appendCodePoint(int codePoint) {
        sqlBuilder.appendCodePoint(codePoint);
        return this;
    }

    /**
     * <code>delete</code>
     * <p>the method.</p>
     * @param start int <p>the start parameter is <code>int</code> type.</p>
     * @param end   int <p>the end parameter is <code>int</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder delete(int start, int end) {
        sqlBuilder.delete(start, end);
        return this;
    }

    /**
     * <code>deleteCharAt</code>
     * <p>the char at method.</p>
     * @param index int <p>the index parameter is <code>int</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the char at return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder deleteCharAt(int index) {
        sqlBuilder.deleteCharAt(index);
        return this;
    }

    /**
     * <code>replace</code>
     * <p>the method.</p>
     * @param start int <p>the start parameter is <code>int</code> type.</p>
     * @param end   int <p>the end parameter is <code>int</code> type.</p>
     * @param str   {@link java.lang.String} <p>the str parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.String
     */
    public SqlBuilder replace(int start, int end, String str) {
        sqlBuilder.replace(start, end, str);
        return this;
    }

    /**
     * <code>insert</code>
     * <p>the method.</p>
     * @param index  int <p>the index parameter is <code>int</code> type.</p>
     * @param str    char <p>the str parameter is <code>char</code> type.</p>
     * @param offset int <p>the offset parameter is <code>int</code> type.</p>
     * @param len    int <p>the len parameter is <code>int</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder insert(int index, char[] str, int offset,
                             int len) {
        sqlBuilder.insert(index, str, offset, len);
        return this;
    }

    /**
     * <code>insert</code>
     * <p>the method.</p>
     * @param offset int <p>the offset parameter is <code>int</code> type.</p>
     * @param obj    {@link java.lang.Object} <p>the obj parameter is <code>Object</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.Object
     */
    public SqlBuilder insert(int offset, Object obj) {
        sqlBuilder.insert(offset, obj);
        return this;
    }

    /**
     * <code>insert</code>
     * <p>the method.</p>
     * @param offset int <p>the offset parameter is <code>int</code> type.</p>
     * @param str    {@link java.lang.String} <p>the str parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.String
     */
    public SqlBuilder insert(int offset, String str) {
        sqlBuilder.insert(offset, str);
        return this;
    }

    /**
     * <code>insert</code>
     * <p>the method.</p>
     * @param offset int <p>the offset parameter is <code>int</code> type.</p>
     * @param str    char <p>the str parameter is <code>char</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder insert(int offset, char[] str) {
        sqlBuilder.insert(offset, str);
        return this;
    }

    /**
     * <code>insert</code>
     * <p>the method.</p>
     * @param dstOffset int <p>the dst offset parameter is <code>int</code> type.</p>
     * @param s         {@link java.lang.CharSequence} <p>the s parameter is <code>CharSequence</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.CharSequence
     */
    public SqlBuilder insert(int dstOffset, CharSequence s) {
        sqlBuilder.insert(dstOffset, s);
        return this;
    }

    /**
     * <code>insert</code>
     * <p>the method.</p>
     * @param dstOffset int <p>the dst offset parameter is <code>int</code> type.</p>
     * @param s         {@link java.lang.CharSequence} <p>the s parameter is <code>CharSequence</code> type.</p>
     * @param start     int <p>the start parameter is <code>int</code> type.</p>
     * @param end       int <p>the end parameter is <code>int</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.CharSequence
     */
    public SqlBuilder insert(int dstOffset, CharSequence s,
                             int start, int end) {
        sqlBuilder.insert(dstOffset, s, start, end);
        return this;
    }

    /**
     * <code>insert</code>
     * <p>the method.</p>
     * @param offset int <p>the offset parameter is <code>int</code> type.</p>
     * @param b      boolean <p>the b parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder insert(int offset, boolean b) {
        sqlBuilder.insert(offset, b);
        return this;
    }

    /**
     * <code>insert</code>
     * <p>the method.</p>
     * @param offset int <p>the offset parameter is <code>int</code> type.</p>
     * @param c      char <p>the c parameter is <code>char</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder insert(int offset, char c) {
        sqlBuilder.insert(offset, c);
        return this;
    }

    /**
     * <code>insert</code>
     * <p>the method.</p>
     * @param offset int <p>the offset parameter is <code>int</code> type.</p>
     * @param i      int <p>the parameter is <code>int</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder insert(int offset, int i) {
        sqlBuilder.insert(offset, i);
        return this;
    }

    /**
     * <code>insert</code>
     * <p>the method.</p>
     * @param offset int <p>the offset parameter is <code>int</code> type.</p>
     * @param l      long <p>the l parameter is <code>long</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder insert(int offset, long l) {
        sqlBuilder.insert(offset, l);
        return this;
    }

    /**
     * <code>insert</code>
     * <p>the method.</p>
     * @param offset int <p>the offset parameter is <code>int</code> type.</p>
     * @param f      float <p>the f parameter is <code>float</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder insert(int offset, float f) {
        sqlBuilder.insert(offset, f);
        return this;
    }

    /**
     * <code>insert</code>
     * <p>the method.</p>
     * @param offset int <p>the offset parameter is <code>int</code> type.</p>
     * @param d      double <p>the d parameter is <code>double</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder insert(int offset, double d) {
        sqlBuilder.insert(offset, d);
        return this;
    }


    /**
     * <code>indexOf</code>
     * <p>the of method.</p>
     * @param str {@link java.lang.String} <p>the str parameter is <code>String</code> type.</p>
     * @return int <p>the of return object is <code>int</code> type.</p>
     * @see java.lang.String
     */
    public int indexOf(String str) {
        return sqlBuilder.indexOf(str);
    }


    /**
     * <code>indexOf</code>
     * <p>the of method.</p>
     * @param str       {@link java.lang.String} <p>the str parameter is <code>String</code> type.</p>
     * @param fromIndex int <p>the from index parameter is <code>int</code> type.</p>
     * @return int <p>the of return object is <code>int</code> type.</p>
     * @see java.lang.String
     */
    public int indexOf(String str, int fromIndex) {
        return sqlBuilder.indexOf(str, fromIndex);
    }


    /**
     * <code>lastIndexOf</code>
     * <p>the index of method.</p>
     * @param str {@link java.lang.String} <p>the str parameter is <code>String</code> type.</p>
     * @return int <p>the index of return object is <code>int</code> type.</p>
     * @see java.lang.String
     */
    public int lastIndexOf(String str) {
        return sqlBuilder.lastIndexOf(str);
    }


    /**
     * <code>lastIndexOf</code>
     * <p>the index of method.</p>
     * @param str       {@link java.lang.String} <p>the str parameter is <code>String</code> type.</p>
     * @param fromIndex int <p>the from index parameter is <code>int</code> type.</p>
     * @return int <p>the index of return object is <code>int</code> type.</p>
     * @see java.lang.String
     */
    public int lastIndexOf(String str, int fromIndex) {
        return sqlBuilder.lastIndexOf(str, fromIndex);
    }


    /**
     * <code>reverse</code>
     * <p>the method.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder reverse() {
        sqlBuilder.reverse();
        return this;
    }

    @Override
    public int length() {
        return sqlBuilder.length();
    }

    @Override
    public char charAt(int index) {
        return sqlBuilder.charAt(index);
    }

    @NonNull
    @Override
    public CharSequence subSequence(int start, int end) {
        return sqlBuilder.subSequence(start,end);
    }

    @NonNull
    @Override
    public String toString() {
        if (GeneralUtils.isEmpty(sqlBuilder)) {
            return "";
        }
        return sqlBuilder.toString();
    }

    /**
     * <code>getSqlBuilder</code>
     * <p>the sql builder getter method.</p>
     * @return {@link java.lang.StringBuilder} <p>the sql builder return object is <code>StringBuilder</code> type.</p>
     * @see java.lang.StringBuilder
     */
    public StringBuilder getSqlBuilder() {
        return sqlBuilder;
    }


    /**
     * <code>nu</code>
     * <p>the method.</p>
     * @param target  {@link java.lang.String} <p>the target parameter is <code>String</code> type.</p>
     * @param andOfOr {@link java.lang.Boolean} <p>the and of or parameter is <code>Boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Boolean
     */
    public SqlBuilder nu(String target, Boolean andOfOr) {
      if (GeneralUtils.isNotEmpty(target)) {
            this.andOfOr(andOfOr);
            this.append(target).append(" IS NULL ");
        }
        return this;
    }

    /**
     * <code>nnu</code>
     * <p>the method.</p>
     * @param target  {@link java.lang.String} <p>the target parameter is <code>String</code> type.</p>
     * @param andOfOr {@link java.lang.Boolean} <p>the and of or parameter is <code>Boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Boolean
     */
    public SqlBuilder nnu(String target, Boolean andOfOr) {
        if (GeneralUtils.isNotEmpty(target)) {
            this.andOfOr(andOfOr);
            this.append(target).append(" IS NOT NULL ");
        }
        return this;
    }

    /**
     * <code>eq</code>
     * <p>the method.</p>
     * @param target  {@link java.lang.String} <p>the target parameter is <code>String</code> type.</p>
     * @param value   {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @param andOfOr {@link java.lang.Boolean} <p>the and of or parameter is <code>Boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see java.lang.Boolean
     */
    public SqlBuilder eq(String target, Object value, Boolean andOfOr) {
        if (value instanceof Number) {
            this.andOfOr(andOfOr);
            this.append(target).append(" = ");
            this.value(value);
        } else if (GeneralUtils.isValid(value)) {
            this.andOfOr(andOfOr);
            this.append(target).append(" = ");
            this.value(value);
        }
        return this;
    }

    /**
     * <code>neq</code>
     * <p>the method.</p>
     * @param target  {@link java.lang.String} <p>the target parameter is <code>String</code> type.</p>
     * @param value   {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @param andOfOr {@link java.lang.Boolean} <p>the and of or parameter is <code>Boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see java.lang.Boolean
     */
    public SqlBuilder neq(String target, Object value, Boolean andOfOr) {
        if (value instanceof Number) {
            this.andOfOr(andOfOr);
            this.append(target).append(" != ");
            this.value(value);
        } else if (GeneralUtils.isValid(value)) {
            this.andOfOr(andOfOr);
            this.append(target).append(" != ");
            this.value(value);
        }
        return this;
    }

    /**
     * <code>lk</code>
     * <p>the method.</p>
     * @param targets {@link java.util.Collection} <p>the targets parameter is <code>Collection</code> type.</p>
     * @param value   {@link java.lang.String} <p>the value parameter is <code>String</code> type.</p>
     * @param andOfOr {@link java.lang.Boolean} <p>the and of or parameter is <code>Boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     * @see java.util.Collection
     * @see java.lang.String
     * @see java.lang.Boolean
     */
    public SqlBuilder lk(Collection<String> targets, String value, Boolean andOfOr) {
        if (GeneralUtils.isNotEmpty(value)) {
            this.andOfOr(andOfOr);
            targets.forEach(target -> this.append(target).append(" LIKE CONCAT('%','").append(value).append("','%')").append(" OR "));
            this.delete(this.length() - 4, this.length());
            this.append(" )");
        }
        return this;
    }

    /**
     * <code>lk</code>
     * <p>the method.</p>
     * @param target  {@link java.lang.String} <p>the target parameter is <code>String</code> type.</p>
     * @param value   {@link java.lang.String} <p>the value parameter is <code>String</code> type.</p>
     * @param andOfOr {@link java.lang.Boolean} <p>the and of or parameter is <code>Boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Boolean
     */
    public SqlBuilder lk(String target, String value, Boolean andOfOr) {
        if (GeneralUtils.isNotEmpty(value)) {
            this.andOfOr(andOfOr);
            this.append(target).append(" LIKE CONCAT('%','").append(value).append("','%')");
        }
        return this;
    }

    /**
     * <code>ain</code>
     * <p>the method.</p>
     * @param target {@link java.lang.String} <p>the target parameter is <code>String</code> type.</p>
     * @param values {@link java.util.Collection} <p>the values parameter is <code>Collection</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.String
     * @see java.util.Collection
     */
    public SqlBuilder ain(String target, Collection<?> values) {
        return in(target,values,true);
    }

    /**
     * <code>oin</code>
     * <p>the method.</p>
     * @param target {@link java.lang.String} <p>the target parameter is <code>String</code> type.</p>
     * @param values {@link java.util.Collection} <p>the values parameter is <code>Collection</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.String
     * @see java.util.Collection
     */
    public SqlBuilder oin(String target, Collection<?> values) {
        return in(target,values,false);
    }

    /**
     * <code>nin</code>
     * <p>the method.</p>
     * @param target  {@link java.lang.String} <p>the target parameter is <code>String</code> type.</p>
     * @param values  {@link java.util.Collection} <p>the values parameter is <code>Collection</code> type.</p>
     * @param andOfOr {@link java.lang.Boolean} <p>the and of or parameter is <code>Boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.String
     * @see java.util.Collection
     * @see java.lang.Boolean
     */
    public SqlBuilder nin(String target, Collection<?> values, Boolean andOfOr) {
        if (GeneralUtils.isNotEmpty(values)) {
            if (values.size() == 1) {
                Object value = values.stream().findFirst().get();
                this.neq(target,value,andOfOr);
            } else {
                this.andOfOr(andOfOr);
                this.append(target).append(" NOT IN (");
                values.forEach(value -> this.value(value,true));
                this.deleteCharAt(this.length() - 2);
                this.append(")");
            }
        }
        return this;
    }

    /**
     * <code>in</code>
     * <p>the method.</p>
     * @param target  {@link java.lang.String} <p>the target parameter is <code>String</code> type.</p>
     * @param values  {@link java.util.Collection} <p>the values parameter is <code>Collection</code> type.</p>
     * @param andOfOr {@link java.lang.Boolean} <p>the and of or parameter is <code>Boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.String
     * @see java.util.Collection
     * @see java.lang.Boolean
     */
    public SqlBuilder in(String target, Collection<?> values, Boolean andOfOr) {
        if (GeneralUtils.isNotEmpty(values)) {
            if (values.size() == 1) {
                Object value = values.stream().findFirst().get();
                this.eq(target,value,andOfOr);
            } else {
                this.andOfOr(andOfOr);
                this.append(target).append(" IN (");
                values.forEach(value -> this.value(value,true));
                this.deleteCharAt(this.length() - 2);
                this.append(")");
            }
        }
        return this;
    }

    /**
     * <code>rb</code>
     * <p>the method.</p>
     * @param target     {@link java.lang.String} <p>the target parameter is <code>String</code> type.</p>
     * @param beginValue {@link java.lang.Object} <p>the begin value parameter is <code>Object</code> type.</p>
     * @param endValue   {@link java.lang.Object} <p>the end value parameter is <code>Object</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public SqlBuilder rb(String target,  Object beginValue, Object endValue) {
        if (GeneralUtils.isValid(beginValue) && GeneralUtils.isValid(endValue)) {
            this.gt(target,endValue,true);
            this.lt(target,beginValue,true);
        }
        return this;
    }

    /**
     * <code>sb</code>
     * <p>the method.</p>
     * @param minTarget {@link java.lang.String} <p>the min target parameter is <code>String</code> type.</p>
     * @param maxTarget {@link java.lang.String} <p>the max target parameter is <code>String</code> type.</p>
     * @param value     {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public SqlBuilder sb(String minTarget, String maxTarget, Object value) {
        if (GeneralUtils.isValid(value)) {
            this.lt(minTarget,value,true);
            this.gt(maxTarget,value,true);
        }
        return this;
    }

    /**
     * <code>reb</code>
     * <p>the method.</p>
     * @param target     {@link java.lang.String} <p>the target parameter is <code>String</code> type.</p>
     * @param beginValue {@link java.lang.Object} <p>the begin value parameter is <code>Object</code> type.</p>
     * @param endValue   {@link java.lang.Object} <p>the end value parameter is <code>Object</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public SqlBuilder reb(String target,  Object beginValue, Object endValue) {
        if (GeneralUtils.isValid(beginValue) && GeneralUtils.isValid(endValue)) {
            this.gte(target,endValue,true);
            this.lte(target,beginValue,true);
        }
        return this;
    }

    /**
     * <code>seb</code>
     * <p>the method.</p>
     * @param minTarget {@link java.lang.String} <p>the min target parameter is <code>String</code> type.</p>
     * @param maxTarget {@link java.lang.String} <p>the max target parameter is <code>String</code> type.</p>
     * @param value     {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public SqlBuilder seb(String minTarget, String maxTarget, Object value) {
        if (GeneralUtils.isValid(value)) {
            this.lte(minTarget,value,true);
            this.gte(maxTarget,value,true);
        }
        return this;
    }

    /**
     * <code>reo</code>
     * <p>the method.</p>
     * @param target     {@link java.lang.String} <p>the target parameter is <code>String</code> type.</p>
     * @param beginValue {@link java.lang.Object} <p>the begin value parameter is <code>Object</code> type.</p>
     * @param endValue   {@link java.lang.Object} <p>the end value parameter is <code>Object</code> type.</p>
     * @param andOfOr    {@link java.lang.Boolean} <p>the and of or parameter is <code>Boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see java.lang.Boolean
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("Duplicates")
    public SqlBuilder reo(String target,  Object beginValue, Object endValue, Boolean andOfOr) {
        this.andOfOr(andOfOr);
        this.append("( ");
        this.gte(target,endValue,null);
        this.lte(target,beginValue,false);
        this.append(" )");
        return this;
    }

    /**
     * <code>seo</code>
     * <p>the method.</p>
     * @param minTarget {@link java.lang.String} <p>the min target parameter is <code>String</code> type.</p>
     * @param maxTarget {@link java.lang.String} <p>the max target parameter is <code>String</code> type.</p>
     * @param value     {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @param andOfOr   {@link java.lang.Boolean} <p>the and of or parameter is <code>Boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see java.lang.Boolean
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("Duplicates")
    public SqlBuilder seo(String minTarget, String maxTarget, Object value, Boolean andOfOr) {
        this.andOfOr(andOfOr);
        this.append("( ");
        this.gte(maxTarget,value,null);
        this.lte(minTarget,value,false);
        this.append(" )");
        return this;
    }

    /**
     * <code>ro</code>
     * <p>the method.</p>
     * @param target     {@link java.lang.String} <p>the target parameter is <code>String</code> type.</p>
     * @param beginValue {@link java.lang.Object} <p>the begin value parameter is <code>Object</code> type.</p>
     * @param endValue   {@link java.lang.Object} <p>the end value parameter is <code>Object</code> type.</p>
     * @param andOfOr    {@link java.lang.Boolean} <p>the and of or parameter is <code>Boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see java.lang.Boolean
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("Duplicates")
    public SqlBuilder ro(String target,  Object beginValue, Object endValue, Boolean andOfOr) {
        this.andOfOr(andOfOr);
        this.append("( ");
        this.gt(target,endValue,null);
        this.lt(target,beginValue,false);
        this.append(" )");
        return this;
    }

    /**
     * <code>so</code>
     * <p>the method.</p>
     * @param minTarget {@link java.lang.String} <p>the min target parameter is <code>String</code> type.</p>
     * @param maxTarget {@link java.lang.String} <p>the max target parameter is <code>String</code> type.</p>
     * @param value     {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @param andOfOr   {@link java.lang.Boolean} <p>the and of or parameter is <code>Boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see java.lang.Boolean
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("Duplicates")
    public SqlBuilder so(String minTarget, String maxTarget, Object value, Boolean andOfOr) {
        this.andOfOr(andOfOr);
        this.append("( ");
        this.gt(maxTarget,value,null);
        this.lt(minTarget,value,false);
        this.append(" )");
        return this;
    }

    /**
     * <code>ra</code>
     * <p>the method.</p>
     * @param target     {@link java.lang.String} <p>the target parameter is <code>String</code> type.</p>
     * @param beginValue {@link java.lang.Object} <p>the begin value parameter is <code>Object</code> type.</p>
     * @param endValue   {@link java.lang.Object} <p>the end value parameter is <code>Object</code> type.</p>
     * @param andOfOr    {@link java.lang.Boolean} <p>the and of or parameter is <code>Boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see java.lang.Boolean
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("Duplicates")
    public SqlBuilder ra(String target,  Object beginValue, Object endValue, Boolean andOfOr) {
        this.andOfOr(andOfOr);
        this.append("( ");
        this.gt(target,beginValue,null);
        this.lt(target,endValue,true);
        this.append(" )");
        return this;
    }

    /**
     * <code>sa</code>
     * <p>the method.</p>
     * @param minTarget {@link java.lang.String} <p>the min target parameter is <code>String</code> type.</p>
     * @param maxTarget {@link java.lang.String} <p>the max target parameter is <code>String</code> type.</p>
     * @param value     {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @param andOfOr   {@link java.lang.Boolean} <p>the and of or parameter is <code>Boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see java.lang.Boolean
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("Duplicates")
    public SqlBuilder sa(String minTarget, String maxTarget, Object value, Boolean andOfOr) {
        this.andOfOr(andOfOr);
        this.append("( ");
        this.gt(maxTarget,value,null);
        this.lt(minTarget,value,true);
        this.append(" )");
        return this;
    }

    /**
     * <code>rea</code>
     * <p>the method.</p>
     * @param target     {@link java.lang.String} <p>the target parameter is <code>String</code> type.</p>
     * @param beginValue {@link java.lang.Object} <p>the begin value parameter is <code>Object</code> type.</p>
     * @param endValue   {@link java.lang.Object} <p>the end value parameter is <code>Object</code> type.</p>
     * @param andOfOr    {@link java.lang.Boolean} <p>the and of or parameter is <code>Boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see java.lang.Boolean
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("Duplicates")
    public SqlBuilder rea(String target, Object beginValue, Object endValue, Boolean andOfOr) {
        this.andOfOr(andOfOr);
        this.append("( ");
        this.gte(target,beginValue,null);
        this.lte(target,endValue,true);
        this.append(" )");
        return this;
    }

    /**
     * <code>sea</code>
     * <p>the method.</p>
     * @param minTarget {@link java.lang.String} <p>the min target parameter is <code>String</code> type.</p>
     * @param maxTarget {@link java.lang.String} <p>the max target parameter is <code>String</code> type.</p>
     * @param value     {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @param andOfOr   {@link java.lang.Boolean} <p>the and of or parameter is <code>Boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see java.lang.Boolean
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("Duplicates")
    public SqlBuilder sea(String minTarget, String maxTarget, Object value, Boolean andOfOr) {
        this.andOfOr(andOfOr);
        this.append("( ");
        this.gte(maxTarget,value,null);
        this.lte(minTarget,value,true);
        this.append(" )");
        return this;
    }

    /**
     * <code>r</code>
     * <p>the method.</p>
     * @param target     {@link java.lang.String} <p>the target parameter is <code>String</code> type.</p>
     * @param beginValue {@link java.lang.Object} <p>the begin value parameter is <code>Object</code> type.</p>
     * @param endValue   {@link java.lang.Object} <p>the end value parameter is <code>Object</code> type.</p>
     * @param andOfOr    {@link java.lang.Boolean} <p>the and of or parameter is <code>Boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see java.lang.Boolean
     */
    public SqlBuilder r(String target,  Object beginValue, Object endValue, Boolean andOfOr) {
        this.gt(target, beginValue, andOfOr);
        this.lt(target, endValue, andOfOr);
        return this;
    }

    /**
     * <code>s</code>
     * <p>the method.</p>
     * @param minTarget {@link java.lang.String} <p>the min target parameter is <code>String</code> type.</p>
     * @param maxTarget {@link java.lang.String} <p>the max target parameter is <code>String</code> type.</p>
     * @param value     {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @param andOfOr   {@link java.lang.Boolean} <p>the and of or parameter is <code>Boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see java.lang.Boolean
     */
    public SqlBuilder s(String minTarget, String maxTarget, Object value, Boolean andOfOr) {
        this.gt(maxTarget, value, andOfOr);
        this.lt(minTarget, value, andOfOr);
        return this;
    }

    /**
     * <code>re</code>
     * <p>the method.</p>
     * @param target     {@link java.lang.String} <p>the target parameter is <code>String</code> type.</p>
     * @param beginValue {@link java.lang.Object} <p>the begin value parameter is <code>Object</code> type.</p>
     * @param endValue   {@link java.lang.Object} <p>the end value parameter is <code>Object</code> type.</p>
     * @param andOfOr    {@link java.lang.Boolean} <p>the and of or parameter is <code>Boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see java.lang.Boolean
     */
    public SqlBuilder re(String target,  Object beginValue, Object endValue, Boolean andOfOr) {
        this.gte(target,beginValue, andOfOr);
        this.lte(target,endValue, andOfOr);
        return this;
    }

    /**
     * <code>se</code>
     * <p>the method.</p>
     * @param minTarget {@link java.lang.String} <p>the min target parameter is <code>String</code> type.</p>
     * @param maxTarget {@link java.lang.String} <p>the max target parameter is <code>String</code> type.</p>
     * @param value     {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @param andOfOr   {@link java.lang.Boolean} <p>the and of or parameter is <code>Boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see java.lang.Boolean
     */
    public SqlBuilder se(String minTarget, String maxTarget, Object value, Boolean andOfOr) {
        this.gte(maxTarget, value, andOfOr);
        this.lte(minTarget, value, andOfOr);
        return this;
    }

    /**
     * <code>rs</code>
     * <p>the method.</p>
     * @param target     {@link java.lang.String} <p>the target parameter is <code>String</code> type.</p>
     * @param beginValue {@link java.lang.Object} <p>the begin value parameter is <code>Object</code> type.</p>
     * @param endValue   {@link java.lang.Object} <p>the end value parameter is <code>Object</code> type.</p>
     * @param andOfOr    {@link java.lang.Boolean} <p>the and of or parameter is <code>Boolean</code> type.</p>
     * @param beginOfEnd {@link java.lang.Boolean} <p>the begin of end parameter is <code>Boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see java.lang.Boolean
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("Duplicates")
    public SqlBuilder rs(String target,  Object beginValue, Object endValue, Boolean andOfOr, Boolean beginOfEnd) {
        if (beginOfEnd) {
            this.gte(target, beginValue, andOfOr);
            this.lt(target, endValue, andOfOr);
        } else {
            this.gt(target, beginValue, andOfOr);
            this.lte(target, endValue, andOfOr);
        }
        return this;
    }

    /**
     * <code>ss</code>
     * <p>the method.</p>
     * @param minTarget  {@link java.lang.String} <p>the min target parameter is <code>String</code> type.</p>
     * @param maxTarget  {@link java.lang.String} <p>the max target parameter is <code>String</code> type.</p>
     * @param value      {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @param andOfOr    {@link java.lang.Boolean} <p>the and of or parameter is <code>Boolean</code> type.</p>
     * @param beginOfEnd {@link java.lang.Boolean} <p>the begin of end parameter is <code>Boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see java.lang.Boolean
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("Duplicates")
    public SqlBuilder ss(String minTarget, String maxTarget, Object value, Boolean andOfOr, Boolean beginOfEnd) {
        if (beginOfEnd) {
            this.gte(maxTarget, value, andOfOr);
            this.lt(minTarget, value, andOfOr);
        } else {
            this.gt(maxTarget, value, andOfOr);
            this.lte(minTarget, value, andOfOr);
        }
        return this;
    }

    /**
     * <code>gt</code>
     * <p>the method.</p>
     * @param target  {@link java.lang.String} <p>the target parameter is <code>String</code> type.</p>
     * @param value   {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @param andOfOr {@link java.lang.Boolean} <p>the and of or parameter is <code>Boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see java.lang.Boolean
     */
    public SqlBuilder gt(String target, Object value, Boolean andOfOr) {
        if (GeneralUtils.isValid(value)) {
            this.andOfOr(andOfOr);
            this.append(target).append(" > ");
            this.value(value);
        }
        return this;
    }

    /**
     * <code>lt</code>
     * <p>the method.</p>
     * @param target  {@link java.lang.String} <p>the target parameter is <code>String</code> type.</p>
     * @param value   {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @param andOfOr {@link java.lang.Boolean} <p>the and of or parameter is <code>Boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see java.lang.Boolean
     */
    public SqlBuilder lt(String target, Object value, Boolean andOfOr) {
        if (GeneralUtils.isValid(value)) {
            this.andOfOr(andOfOr);
            this.append(target).append(" < ");
            this.value(value);
        }
        return this;
    }


    /**
     * <code>gte</code>
     * <p>the method.</p>
     * @param target  {@link java.lang.String} <p>the target parameter is <code>String</code> type.</p>
     * @param value   {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @param andOfOr {@link java.lang.Boolean} <p>the and of or parameter is <code>Boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see java.lang.Boolean
     */
    public SqlBuilder gte(String target, Object value, Boolean andOfOr) {
        if (GeneralUtils.isValid(value)) {
            this.andOfOr(andOfOr);
            this.append(target).append(" >= ");
            this.value(value);
        }
        return this;
    }

    /**
     * <code>lte</code>
     * <p>the method.</p>
     * @param target  {@link java.lang.String} <p>the target parameter is <code>String</code> type.</p>
     * @param value   {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @param andOfOr {@link java.lang.Boolean} <p>the and of or parameter is <code>Boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see java.lang.Boolean
     */
    public SqlBuilder lte(String target, Object value, Boolean andOfOr) {
        if (GeneralUtils.isValid(value)) {
            this.andOfOr(andOfOr);
            this.append(target).append(" <= ");
            this.value(value);
        }
        return this;
    }

    /**
     * <code>value</code>
     * <p>the method.</p>
     * @param value {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.Object
     */
    public SqlBuilder value(Object value) {
        this.value(value,false);
        return this;
    }

    /**
     * <code>value</code>
     * <p>the method.</p>
     * @param value       {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @param commaOfNone {@link java.lang.Boolean} <p>the comma of none parameter is <code>Boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.Object
     * @see java.lang.Boolean
     */
    public SqlBuilder value(Object value, Boolean commaOfNone) {
        if (value instanceof String) {
            this.append("'").append(value).append("'");
        } else if (value instanceof Date) {
            // the value is like '2020-09-11 00:00:00'
            this.append("'").append(DateUtils.formatTime((Date) value)).append("'");
        } else if (value instanceof RestKey) {
            this.append(((RestKey<?>) value).getKey());
        } else {
            this.append(value);
        }
        if (commaOfNone) {
            this.append(", ");
        } else {
            this.append(" ");
        }
        return this;
    }

    /**
     * <code>value</code>
     * <p>the method.</p>
     * @param value  {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @param symbol {@link java.lang.String} <p>the symbol parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.Object
     * @see java.lang.String
     */
    public SqlBuilder value(Object value, String symbol) {
        if (value instanceof String) {
            this.append("'").append(value).append("'").append(symbol).append(" ");
        } else if (value instanceof Date) {
            // the value is like '2020-09-11 00:00:00'
            this.append("'").append(DateUtils.formatTime((Date) value)).append("'").append(symbol).append(" ");
        } else if (value instanceof RestKey) {
            this.append("'").append(((RestKey<?>) value).getKey()).append("'").append(symbol).append(" ");
        } else {
            this.append(value).append(symbol).append(" ");
        }
        return this;
    }

    /**
     * <code>andOfOr</code>
     * <p>the of or method.</p>
     * @param andOfOr {@link java.lang.Boolean} <p>the and of or parameter is <code>Boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the of or return object is <code>SqlBuilder</code> type.</p>
     * @see java.lang.Boolean
     */
    public SqlBuilder andOfOr(Boolean andOfOr) {
        if (GeneralUtils.isNotEmpty(andOfOr)) {
            if (andOfOr) {
                this.and();
            } else {
                this.or();
            }
        }
        return this;
    }

    /**
     * <code>and</code>
     * <p>the method.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder and() {
        this.append(" AND ");
        return this;
    }

    /**
     * <code>or</code>
     * <p>the method.</p>
     * @return {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>the return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder or() {
        this.append(" OR ");
        return this;
    }
}
