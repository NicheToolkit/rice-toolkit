package io.github.nichetoolkit.rice.builder;

import io.github.nichetoolkit.rice.consts.SQLConstants;
import io.github.nichetoolkit.rice.consts.ScriptConstants;
import io.github.nichetoolkit.rest.RestKey;
import io.github.nichetoolkit.rest.util.DateUtils;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.Getter;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * <code>SqlBuilder</code>
 * <p>The sql builder class.</p>
 * @see  java.io.Serializable
 * @see  java.lang.CharSequence
 * @see  lombok.Getter
 * @see  java.lang.SuppressWarnings
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Getter
@SuppressWarnings({"WeakerAccess", "UnusedReturnValue"})
public final class SqlBuilder implements Serializable, CharSequence {

    /**
     * <code>EMPTY</code>
     * {@link java.lang.String} <p>The constant <code>EMPTY</code> field.</p>
     * @see  java.lang.String
     */
    public static final String EMPTY = SQLConstants.EMPTY;

    /**
     * <code>serialVersionUID</code>
     * <p>The constant <code>serialVersionUID</code> field.</p>
     */
    private static final long serialVersionUID = 4383685877147921098L;

    /**
     * <code>sqlBuilder</code>
     * {@link java.lang.StringBuilder} <p>The <code>sqlBuilder</code> field.</p>
     * @see  java.lang.StringBuilder
     */
    private final StringBuilder sqlBuilder;

    /**
     * <code>SqlBuilder</code>
     * <p>Instantiates a new sql builder.</p>
     */
    public SqlBuilder() {
        this.sqlBuilder = new StringBuilder();
    }

    /**
     * <code>SqlBuilder</code>
     * <p>Instantiates a new sql builder.</p>
     * @param sqlBuilder {@link java.lang.StringBuilder} <p>The sql builder parameter is <code>StringBuilder</code> type.</p>
     * @see  java.lang.StringBuilder
     */
    public SqlBuilder(StringBuilder sqlBuilder) {
        this.sqlBuilder = sqlBuilder;
    }

    /**
     * <code>SqlBuilder</code>
     * <p>Instantiates a new sql builder.</p>
     * @param capacity int <p>The capacity parameter is <code>int</code> type.</p>
     */
    public SqlBuilder(int capacity) {
        this.sqlBuilder = new StringBuilder(capacity);
    }

    /**
     * <code>SqlBuilder</code>
     * <p>Instantiates a new sql builder.</p>
     * @param str {@link java.lang.String} <p>The str parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  java.lang.RuntimeException
     * @throws RuntimeException {@link java.lang.RuntimeException} <p>The runtime exception is <code>RuntimeException</code> type.</p>
     */
    public SqlBuilder(String str) throws RuntimeException {
        this.sqlBuilder = new StringBuilder(str);
    }

    /**
     * <code>SqlBuilder</code>
     * <p>Instantiates a new sql builder.</p>
     * @param seq {@link java.lang.CharSequence} <p>The seq parameter is <code>CharSequence</code> type.</p>
     * @see  java.lang.CharSequence
     */
    public SqlBuilder(CharSequence seq) {
        this.sqlBuilder = new StringBuilder(seq);
    }

    /**
     * <code>clear</code>
     * <p>The clear method.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The clear return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder clear() {
        this.sqlBuilder.delete(0, this.sqlBuilder.length());
        return this;
    }

    /**
     * <code>append</code>
     * <p>The append method.</p>
     * @param obj {@link java.lang.Object} <p>The obj parameter is <code>Object</code> type.</p>
     * @see  java.lang.Object
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The append return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder append(Object obj) {
        return append(String.valueOf(obj));
    }

    /**
     * <code>append</code>
     * <p>The append method.</p>
     * @param str {@link java.lang.String} <p>The str parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The append return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder append(String str) {
        sqlBuilder.append(str);
        return this;
    }

    /**
     * <code>append</code>
     * <p>The append method.</p>
     * @param sb {@link java.lang.StringBuilder} <p>The sb parameter is <code>StringBuilder</code> type.</p>
     * @see  java.lang.StringBuilder
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The append return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder append(StringBuilder sb) {
        sqlBuilder.append(sb);
        return this;
    }

    /**
     * <code>append</code>
     * <p>The append method.</p>
     * @param sb {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The sb parameter is <code>SqlBuilder</code> type.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The append return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder append(SqlBuilder sb) {
        sqlBuilder.append(sb.sqlBuilder);
        return this;
    }

    /**
     * <code>append</code>
     * <p>The append method.</p>
     * @param sb {@link java.lang.StringBuffer} <p>The sb parameter is <code>StringBuffer</code> type.</p>
     * @see  java.lang.StringBuffer
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The append return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder append(StringBuffer sb) {
        sqlBuilder.append(sb);
        return this;
    }

    /**
     * <code>append</code>
     * <p>The append method.</p>
     * @param s {@link java.lang.CharSequence} <p>The s parameter is <code>CharSequence</code> type.</p>
     * @see  java.lang.CharSequence
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The append return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder append(CharSequence s) {
        sqlBuilder.append(s);
        return this;
    }

    /**
     * <code>append</code>
     * <p>The append method.</p>
     * @param s {@link java.lang.CharSequence} <p>The s parameter is <code>CharSequence</code> type.</p>
     * @param start int <p>The start parameter is <code>int</code> type.</p>
     * @param end int <p>The end parameter is <code>int</code> type.</p>
     * @see  java.lang.CharSequence
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The append return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder append(CharSequence s, int start, int end) {
        sqlBuilder.append(s, start, end);
        return this;
    }

    /**
     * <code>append</code>
     * <p>The append method.</p>
     * @param str char <p>The str parameter is <code>char</code> type.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The append return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder append(char[] str) {
        sqlBuilder.append(str);
        return this;
    }


    /**
     * <code>append</code>
     * <p>The append method.</p>
     * @param str char <p>The str parameter is <code>char</code> type.</p>
     * @param offset int <p>The offset parameter is <code>int</code> type.</p>
     * @param len int <p>The len parameter is <code>int</code> type.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The append return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder append(char[] str, int offset, int len) {
        sqlBuilder.append(str, offset, len);
        return this;
    }


    /**
     * <code>append</code>
     * <p>The append method.</p>
     * @param b boolean <p>The b parameter is <code>boolean</code> type.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The append return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder append(boolean b) {
        sqlBuilder.append(b);
        return this;
    }


    /**
     * <code>append</code>
     * <p>The append method.</p>
     * @param c char <p>The c parameter is <code>char</code> type.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The append return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder append(char c) {
        sqlBuilder.append(c);
        return this;
    }


    /**
     * <code>append</code>
     * <p>The append method.</p>
     * @param i int <p>The parameter is <code>int</code> type.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The append return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder append(int i) {
        sqlBuilder.append(i);
        return this;
    }


    /**
     * <code>append</code>
     * <p>The append method.</p>
     * @param lng long <p>The lng parameter is <code>long</code> type.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The append return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder append(long lng) {
        sqlBuilder.append(lng);
        return this;
    }


    /**
     * <code>append</code>
     * <p>The append method.</p>
     * @param f float <p>The f parameter is <code>float</code> type.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The append return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder append(float f) {
        sqlBuilder.append(f);
        return this;
    }

    /**
     * <code>append</code>
     * <p>The append method.</p>
     * @param d double <p>The d parameter is <code>double</code> type.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The append return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder append(double d) {
        sqlBuilder.append(d);
        return this;
    }

    /**
     * <code>appendCodePoint</code>
     * <p>The append code point method.</p>
     * @param codePoint int <p>The code point parameter is <code>int</code> type.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The append code point return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder appendCodePoint(int codePoint) {
        sqlBuilder.appendCodePoint(codePoint);
        return this;
    }

    /**
     * <code>delete</code>
     * <p>The delete method.</p>
     * @param start int <p>The start parameter is <code>int</code> type.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The delete return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder delete(int start) {
        sqlBuilder.delete(start, sqlBuilder.length());
        return this;
    }

    /**
     * <code>delete</code>
     * <p>The delete method.</p>
     * @param start int <p>The start parameter is <code>int</code> type.</p>
     * @param end int <p>The end parameter is <code>int</code> type.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The delete return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder delete(int start, int end) {
        sqlBuilder.delete(start, end);
        return this;
    }

    /**
     * <code>deleteCharAt</code>
     * <p>The delete char at method.</p>
     * @param index int <p>The index parameter is <code>int</code> type.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The delete char at return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder deleteCharAt(int index) {
        sqlBuilder.deleteCharAt(index);
        return this;
    }

    /**
     * <code>deleteLastChar</code>
     * <p>The delete last char method.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The delete last char return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder deleteLastChar() {
        sqlBuilder.deleteCharAt(sqlBuilder.length() - 1);
        return this;
    }

    /**
     * <code>replace</code>
     * <p>The replace method.</p>
     * @param start int <p>The start parameter is <code>int</code> type.</p>
     * @param end int <p>The end parameter is <code>int</code> type.</p>
     * @param str {@link java.lang.String} <p>The str parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The replace return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder replace(int start, int end, String str) {
        sqlBuilder.replace(start, end, str);
        return this;
    }

    /**
     * <code>insert</code>
     * <p>The insert method.</p>
     * @param index int <p>The index parameter is <code>int</code> type.</p>
     * @param str char <p>The str parameter is <code>char</code> type.</p>
     * @param offset int <p>The offset parameter is <code>int</code> type.</p>
     * @param len int <p>The len parameter is <code>int</code> type.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The insert return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder insert(int index, char[] str, int offset,
                             int len) {
        sqlBuilder.insert(index, str, offset, len);
        return this;
    }

    /**
     * <code>insert</code>
     * <p>The insert method.</p>
     * @param offset int <p>The offset parameter is <code>int</code> type.</p>
     * @param obj {@link java.lang.Object} <p>The obj parameter is <code>Object</code> type.</p>
     * @see  java.lang.Object
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The insert return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder insert(int offset, Object obj) {
        sqlBuilder.insert(offset, obj);
        return this;
    }

    /**
     * <code>insert</code>
     * <p>The insert method.</p>
     * @param offset int <p>The offset parameter is <code>int</code> type.</p>
     * @param str {@link java.lang.String} <p>The str parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The insert return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder insert(int offset, String str) {
        sqlBuilder.insert(offset, str);
        return this;
    }

    /**
     * <code>insert</code>
     * <p>The insert method.</p>
     * @param offset int <p>The offset parameter is <code>int</code> type.</p>
     * @param str char <p>The str parameter is <code>char</code> type.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The insert return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder insert(int offset, char[] str) {
        sqlBuilder.insert(offset, str);
        return this;
    }

    /**
     * <code>insert</code>
     * <p>The insert method.</p>
     * @param dstOffset int <p>The dst offset parameter is <code>int</code> type.</p>
     * @param s {@link java.lang.CharSequence} <p>The s parameter is <code>CharSequence</code> type.</p>
     * @see  java.lang.CharSequence
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The insert return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder insert(int dstOffset, CharSequence s) {
        sqlBuilder.insert(dstOffset, s);
        return this;
    }

    /**
     * <code>insert</code>
     * <p>The insert method.</p>
     * @param dstOffset int <p>The dst offset parameter is <code>int</code> type.</p>
     * @param s {@link java.lang.CharSequence} <p>The s parameter is <code>CharSequence</code> type.</p>
     * @param start int <p>The start parameter is <code>int</code> type.</p>
     * @param end int <p>The end parameter is <code>int</code> type.</p>
     * @see  java.lang.CharSequence
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The insert return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder insert(int dstOffset, CharSequence s,
                             int start, int end) {
        sqlBuilder.insert(dstOffset, s, start, end);
        return this;
    }

    /**
     * <code>insert</code>
     * <p>The insert method.</p>
     * @param offset int <p>The offset parameter is <code>int</code> type.</p>
     * @param b boolean <p>The b parameter is <code>boolean</code> type.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The insert return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder insert(int offset, boolean b) {
        sqlBuilder.insert(offset, b);
        return this;
    }

    /**
     * <code>insert</code>
     * <p>The insert method.</p>
     * @param offset int <p>The offset parameter is <code>int</code> type.</p>
     * @param c char <p>The c parameter is <code>char</code> type.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The insert return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder insert(int offset, char c) {
        sqlBuilder.insert(offset, c);
        return this;
    }

    /**
     * <code>insert</code>
     * <p>The insert method.</p>
     * @param offset int <p>The offset parameter is <code>int</code> type.</p>
     * @param i int <p>The parameter is <code>int</code> type.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The insert return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder insert(int offset, int i) {
        sqlBuilder.insert(offset, i);
        return this;
    }

    /**
     * <code>insert</code>
     * <p>The insert method.</p>
     * @param offset int <p>The offset parameter is <code>int</code> type.</p>
     * @param l long <p>The l parameter is <code>long</code> type.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The insert return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder insert(int offset, long l) {
        sqlBuilder.insert(offset, l);
        return this;
    }

    /**
     * <code>insert</code>
     * <p>The insert method.</p>
     * @param offset int <p>The offset parameter is <code>int</code> type.</p>
     * @param f float <p>The f parameter is <code>float</code> type.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The insert return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder insert(int offset, float f) {
        sqlBuilder.insert(offset, f);
        return this;
    }

    /**
     * <code>insert</code>
     * <p>The insert method.</p>
     * @param offset int <p>The offset parameter is <code>int</code> type.</p>
     * @param d double <p>The d parameter is <code>double</code> type.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The insert return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder insert(int offset, double d) {
        sqlBuilder.insert(offset, d);
        return this;
    }


    /**
     * <code>indexOf</code>
     * <p>The index of method.</p>
     * @param str {@link java.lang.String} <p>The str parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @return int <p>The index of return object is <code>int</code> type.</p>
     */
    public int indexOf(String str) {
        return sqlBuilder.indexOf(str);
    }


    /**
     * <code>indexOf</code>
     * <p>The index of method.</p>
     * @param str {@link java.lang.String} <p>The str parameter is <code>String</code> type.</p>
     * @param fromIndex int <p>The from index parameter is <code>int</code> type.</p>
     * @see  java.lang.String
     * @return int <p>The index of return object is <code>int</code> type.</p>
     */
    public int indexOf(String str, int fromIndex) {
        return sqlBuilder.indexOf(str, fromIndex);
    }


    /**
     * <code>lastIndexOf</code>
     * <p>The last index of method.</p>
     * @param str {@link java.lang.String} <p>The str parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @return int <p>The last index of return object is <code>int</code> type.</p>
     */
    public int lastIndexOf(String str) {
        return sqlBuilder.lastIndexOf(str);
    }


    /**
     * <code>lastIndexOf</code>
     * <p>The last index of method.</p>
     * @param str {@link java.lang.String} <p>The str parameter is <code>String</code> type.</p>
     * @param fromIndex int <p>The from index parameter is <code>int</code> type.</p>
     * @see  java.lang.String
     * @return int <p>The last index of return object is <code>int</code> type.</p>
     */
    public int lastIndexOf(String str, int fromIndex) {
        return sqlBuilder.lastIndexOf(str, fromIndex);
    }


    /**
     * <code>reverse</code>
     * <p>The reverse method.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The reverse return object is <code>SqlBuilder</code> type.</p>
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
        return sqlBuilder.subSequence(start, end);
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
     * <code>sqlBuilder</code>
     * <p>The sql builder method.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The sql builder return object is <code>SqlBuilder</code> type.</p>
     */
    public static SqlBuilder sqlBuilder() {
        return new SqlBuilder();
    }

    /**
     * <code>sqlBuilder</code>
     * <p>The sql builder method.</p>
     * @param sql {@link java.lang.String} <p>The sql parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The sql builder return object is <code>SqlBuilder</code> type.</p>
     */
    public static SqlBuilder sqlBuilder(String sql) {
        return new SqlBuilder(sql);
    }

    /**
     * <code>isn</code>
     * <p>The isn method.</p>
     * @param target {@link java.lang.String} <p>The target parameter is <code>String</code> type.</p>
     * @param andOfOr {@link java.lang.Boolean} <p>The and of or parameter is <code>Boolean</code> type.</p>
     * @see  java.lang.String
     * @see  java.lang.Boolean
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The isn return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder isn(String target, Boolean andOfOr) {
        if (GeneralUtils.isNotEmpty(target)) {
            this.andOfOr(andOfOr);
            this.append(target).isn();
        }
        return this;
    }

    /**
     * <code>inn</code>
     * <p>The inn method.</p>
     * @param target {@link java.lang.String} <p>The target parameter is <code>String</code> type.</p>
     * @param andOfOr {@link java.lang.Boolean} <p>The and of or parameter is <code>Boolean</code> type.</p>
     * @see  java.lang.String
     * @see  java.lang.Boolean
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The inn return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder inn(String target, Boolean andOfOr) {
        if (GeneralUtils.isNotEmpty(target)) {
            this.andOfOr(andOfOr);
            this.append(target).inn();
        }
        return this;
    }

    /**
     * <code>eq</code>
     * <p>The eq method.</p>
     * @param target {@link java.lang.String} <p>The target parameter is <code>String</code> type.</p>
     * @param value {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param andOfOr {@link java.lang.Boolean} <p>The and of or parameter is <code>Boolean</code> type.</p>
     * @see  java.lang.String
     * @see  java.lang.Object
     * @see  java.lang.Boolean
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The eq return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder eq(String target, Object value, Boolean andOfOr) {
        if (value instanceof Number) {
            this.andOfOr(andOfOr);
            this.append(target).eq();
            this.value(value);
        } else if (GeneralUtils.isUsable(value)) {
            this.andOfOr(andOfOr);
            this.append(target).eq();
            this.value(value);
        }
        return this;
    }

    /**
     * <code>neq</code>
     * <p>The neq method.</p>
     * @param target {@link java.lang.String} <p>The target parameter is <code>String</code> type.</p>
     * @param value {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param andOfOr {@link java.lang.Boolean} <p>The and of or parameter is <code>Boolean</code> type.</p>
     * @see  java.lang.String
     * @see  java.lang.Object
     * @see  java.lang.Boolean
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The neq return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder neq(String target, Object value, Boolean andOfOr) {
        if (value instanceof Number) {
            this.andOfOr(andOfOr);
            this.append(target).neq();
            this.value(value);
        } else if (GeneralUtils.isUsable(value)) {
            this.andOfOr(andOfOr);
            this.append(target).neq();
            this.value(value);
        }
        return this;
    }

    /**
     * <code>lk</code>
     * <p>The lk method.</p>
     * @param target {@link java.lang.String} <p>The target parameter is <code>String</code> type.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @param andOfOr {@link java.lang.Boolean} <p>The and of or parameter is <code>Boolean</code> type.</p>
     * @see  java.lang.String
     * @see  java.lang.Boolean
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The lk return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder lk(String target, String value, Boolean andOfOr) {
        return lk(target, value, null, andOfOr);
    }

    /**
     * <code>lkl</code>
     * <p>The lkl method.</p>
     * @param target {@link java.lang.String} <p>The target parameter is <code>String</code> type.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @param andOfOr {@link java.lang.Boolean} <p>The and of or parameter is <code>Boolean</code> type.</p>
     * @see  java.lang.String
     * @see  java.lang.Boolean
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The lkl return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder lkl(String target, String value, Boolean andOfOr) {
        return lk(target, value, true, andOfOr);
    }

    /**
     * <code>lkg</code>
     * <p>The lkg method.</p>
     * @param target {@link java.lang.String} <p>The target parameter is <code>String</code> type.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @param andOfOr {@link java.lang.Boolean} <p>The and of or parameter is <code>Boolean</code> type.</p>
     * @see  java.lang.String
     * @see  java.lang.Boolean
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The lkg return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder lkg(String target, String value, Boolean andOfOr) {
        return lk(target, value, false, andOfOr);
    }

    /**
     * <code>lk</code>
     * <p>The lk method.</p>
     * @param target {@link java.lang.String} <p>The target parameter is <code>String</code> type.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The lk return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder lk(String target, String value) {
        return lk(target, value, null, null);
    }

    /**
     * <code>lkl</code>
     * <p>The lkl method.</p>
     * @param target {@link java.lang.String} <p>The target parameter is <code>String</code> type.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The lkl return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder lkl(String target, String value) {
        return lk(target, value, true, null);
    }

    /**
     * <code>lkg</code>
     * <p>The lkg method.</p>
     * @param target {@link java.lang.String} <p>The target parameter is <code>String</code> type.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The lkg return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder lkg(String target, String value) {
        return lk(target, value, false, null);
    }

    /**
     * <code>lk</code>
     * <p>The lk method.</p>
     * @param targets {@link java.util.Collection} <p>The targets parameter is <code>Collection</code> type.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @param andOfOr {@link java.lang.Boolean} <p>The and of or parameter is <code>Boolean</code> type.</p>
     * @see  java.util.Collection
     * @see  java.lang.String
     * @see  java.lang.Boolean
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The lk return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder lk(Collection<String> targets, String value, Boolean andOfOr) {
        return lk(targets, value, null, andOfOr);
    }

    /**
     * <code>lkl</code>
     * <p>The lkl method.</p>
     * @param targets {@link java.util.Collection} <p>The targets parameter is <code>Collection</code> type.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @param andOfOr {@link java.lang.Boolean} <p>The and of or parameter is <code>Boolean</code> type.</p>
     * @see  java.util.Collection
     * @see  java.lang.String
     * @see  java.lang.Boolean
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The lkl return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder lkl(Collection<String> targets, String value, Boolean andOfOr) {
        return lk(targets, value, true, andOfOr);
    }

    /**
     * <code>lkg</code>
     * <p>The lkg method.</p>
     * @param targets {@link java.util.Collection} <p>The targets parameter is <code>Collection</code> type.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @param andOfOr {@link java.lang.Boolean} <p>The and of or parameter is <code>Boolean</code> type.</p>
     * @see  java.util.Collection
     * @see  java.lang.String
     * @see  java.lang.Boolean
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The lkg return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder lkg(Collection<String> targets, String value, Boolean andOfOr) {
        return lk(targets, value, false, andOfOr);
    }

    /**
     * <code>lk</code>
     * <p>The lk method.</p>
     * @param targets {@link java.util.Collection} <p>The targets parameter is <code>Collection</code> type.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @see  java.util.Collection
     * @see  java.lang.String
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The lk return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder lk(Collection<String> targets, String value) {
        return lk(targets, value, null, null);
    }

    /**
     * <code>lkl</code>
     * <p>The lkl method.</p>
     * @param targets {@link java.util.Collection} <p>The targets parameter is <code>Collection</code> type.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @see  java.util.Collection
     * @see  java.lang.String
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The lkl return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder lkl(Collection<String> targets, String value) {
        return lk(targets, value, true, null);
    }

    /**
     * <code>lkg</code>
     * <p>The lkg method.</p>
     * @param targets {@link java.util.Collection} <p>The targets parameter is <code>Collection</code> type.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @see  java.util.Collection
     * @see  java.lang.String
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The lkg return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder lkg(Collection<String> targets, String value) {
        return lk(targets, value, false, null);
    }

    /**
     * <code>lk</code>
     * <p>The lk method.</p>
     * @param targets {@link java.util.Collection} <p>The targets parameter is <code>Collection</code> type.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @param ltOfGt {@link java.lang.Boolean} <p>The lt of gt parameter is <code>Boolean</code> type.</p>
     * @param andOfOr {@link java.lang.Boolean} <p>The and of or parameter is <code>Boolean</code> type.</p>
     * @see  java.util.Collection
     * @see  java.lang.String
     * @see  java.lang.Boolean
     * @see  java.lang.SuppressWarnings
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The lk return object is <code>SqlBuilder</code> type.</p>
     */
    @SuppressWarnings("Duplicates")
    public SqlBuilder lk(Collection<String> targets, String value, Boolean ltOfGt, Boolean andOfOr) {
        if (GeneralUtils.isNotEmpty(value)) {
            this.andOfOr(andOfOr);
            targets.forEach(target -> {
                this.append(target).like();
                if (GeneralUtils.isNotEmpty(ltOfGt)) {
                    this.sQuote();
                    if (ltOfGt) {
                        this.percent();
                    }
                    this.append(value);
                    if (!ltOfGt) {
                        this.percent();
                    }
                    this.sQuote();
                } else {
                    this.sQuote().percent().append(value).percent().sQuote();
                }
                this.or();
            });
            this.delete(this.length() - 4, this.length());
            this.braceGt();
        }
        return this;
    }

    /**
     * <code>lk</code>
     * <p>The lk method.</p>
     * @param target {@link java.lang.String} <p>The target parameter is <code>String</code> type.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @param ltOfGt {@link java.lang.Boolean} <p>The lt of gt parameter is <code>Boolean</code> type.</p>
     * @param andOfOr {@link java.lang.Boolean} <p>The and of or parameter is <code>Boolean</code> type.</p>
     * @see  java.lang.String
     * @see  java.lang.Boolean
     * @see  java.lang.SuppressWarnings
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The lk return object is <code>SqlBuilder</code> type.</p>
     */
    @SuppressWarnings("Duplicates")
    public SqlBuilder lk(String target, String value, Boolean ltOfGt, Boolean andOfOr) {
        if (GeneralUtils.isNotEmpty(value)) {
            this.andOfOr(andOfOr);
            this.append(target).like();
            if (GeneralUtils.isNotEmpty(ltOfGt)) {
                this.sQuote();
                if (ltOfGt) {
                    this.percent();
                }
                this.append(value);
                if (!ltOfGt) {
                    this.percent();
                }
                this.sQuote();
            } else {
                this.sQuote().percent().append(value).percent().sQuote();
            }
            this.blank();
        }
        return this;
    }

    /**
     * <code>ain</code>
     * <p>The ain method.</p>
     * @param target {@link java.lang.String} <p>The target parameter is <code>String</code> type.</p>
     * @param values {@link java.util.Collection} <p>The values parameter is <code>Collection</code> type.</p>
     * @see  java.lang.String
     * @see  java.util.Collection
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The ain return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder ain(String target, Collection<?> values) {
        return in(target, values, true);
    }

    /**
     * <code>oin</code>
     * <p>The oin method.</p>
     * @param target {@link java.lang.String} <p>The target parameter is <code>String</code> type.</p>
     * @param values {@link java.util.Collection} <p>The values parameter is <code>Collection</code> type.</p>
     * @see  java.lang.String
     * @see  java.util.Collection
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The oin return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder oin(String target, Collection<?> values) {
        return in(target, values, false);
    }

    /**
     * <code>nin</code>
     * <p>The nin method.</p>
     * @param target {@link java.lang.String} <p>The target parameter is <code>String</code> type.</p>
     * @param values {@link java.util.Collection} <p>The values parameter is <code>Collection</code> type.</p>
     * @param andOfOr {@link java.lang.Boolean} <p>The and of or parameter is <code>Boolean</code> type.</p>
     * @see  java.lang.String
     * @see  java.util.Collection
     * @see  java.lang.Boolean
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The nin return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder nin(String target, Collection<?> values, Boolean andOfOr) {
        if (GeneralUtils.isNotEmpty(values)) {
            if (values.size() == 1) {
                Object value = values.stream().findFirst().get();
                this.neq(target, value, andOfOr);
            } else {
                this.andOfOr(andOfOr);
                this.append(target).nin().braceLt();
                values.forEach(value -> this.value(value, true));
                this.deleteCharAt(this.length() - 2);
                this.braceGt();
            }
        }
        return this;
    }

    /**
     * <code>in</code>
     * <p>The in method.</p>
     * @param target {@link java.lang.String} <p>The target parameter is <code>String</code> type.</p>
     * @param values {@link java.util.Collection} <p>The values parameter is <code>Collection</code> type.</p>
     * @param andOfOr {@link java.lang.Boolean} <p>The and of or parameter is <code>Boolean</code> type.</p>
     * @see  java.lang.String
     * @see  java.util.Collection
     * @see  java.lang.Boolean
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The in return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder in(String target, Collection<?> values, Boolean andOfOr) {
        if (GeneralUtils.isNotEmpty(values)) {
            if (values.size() == 1) {
                Object value = values.stream().findFirst().get();
                this.eq(target, value, andOfOr);
            } else {
                this.andOfOr(andOfOr);
                this.append(target).in().braceLt();
                values.forEach(value -> this.value(value, true));
                this.deleteCharAt(this.length() - 2);
                this.braceGt();
            }
        }
        return this;
    }

    /**
     * <code>rb</code>
     * <p>The rb method.</p>
     * @param target {@link java.lang.String} <p>The target parameter is <code>String</code> type.</p>
     * @param beginValue {@link java.lang.Object} <p>The begin value parameter is <code>Object</code> type.</p>
     * @param endValue {@link java.lang.Object} <p>The end value parameter is <code>Object</code> type.</p>
     * @see  java.lang.String
     * @see  java.lang.Object
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The rb return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder rb(String target, Object beginValue, Object endValue) {
        if (GeneralUtils.isUsable(beginValue) && GeneralUtils.isUsable(endValue)) {
            this.gt(target, endValue, true);
            this.lt(target, beginValue, true);
        }
        return this;
    }

    /**
     * <code>sb</code>
     * <p>The sb method.</p>
     * @param minTarget {@link java.lang.String} <p>The min target parameter is <code>String</code> type.</p>
     * @param maxTarget {@link java.lang.String} <p>The max target parameter is <code>String</code> type.</p>
     * @param value {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @see  java.lang.String
     * @see  java.lang.Object
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The sb return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder sb(String minTarget, String maxTarget, Object value) {
        if (GeneralUtils.isUsable(value)) {
            this.lt(minTarget, value, true);
            this.gt(maxTarget, value, true);
        }
        return this;
    }

    /**
     * <code>reb</code>
     * <p>The reb method.</p>
     * @param target {@link java.lang.String} <p>The target parameter is <code>String</code> type.</p>
     * @param beginValue {@link java.lang.Object} <p>The begin value parameter is <code>Object</code> type.</p>
     * @param endValue {@link java.lang.Object} <p>The end value parameter is <code>Object</code> type.</p>
     * @see  java.lang.String
     * @see  java.lang.Object
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The reb return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder reb(String target, Object beginValue, Object endValue) {
        if (GeneralUtils.isUsable(beginValue) && GeneralUtils.isUsable(endValue)) {
            this.gte(target, endValue, true);
            this.lte(target, beginValue, true);
        }
        return this;
    }

    /**
     * <code>seb</code>
     * <p>The seb method.</p>
     * @param minTarget {@link java.lang.String} <p>The min target parameter is <code>String</code> type.</p>
     * @param maxTarget {@link java.lang.String} <p>The max target parameter is <code>String</code> type.</p>
     * @param value {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @see  java.lang.String
     * @see  java.lang.Object
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The seb return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder seb(String minTarget, String maxTarget, Object value) {
        if (GeneralUtils.isUsable(value)) {
            this.lte(minTarget, value, true);
            this.gte(maxTarget, value, true);
        }
        return this;
    }

    /**
     * <code>reo</code>
     * <p>The reo method.</p>
     * @param target {@link java.lang.String} <p>The target parameter is <code>String</code> type.</p>
     * @param beginValue {@link java.lang.Object} <p>The begin value parameter is <code>Object</code> type.</p>
     * @param endValue {@link java.lang.Object} <p>The end value parameter is <code>Object</code> type.</p>
     * @param andOfOr {@link java.lang.Boolean} <p>The and of or parameter is <code>Boolean</code> type.</p>
     * @see  java.lang.String
     * @see  java.lang.Object
     * @see  java.lang.Boolean
     * @see  java.lang.SuppressWarnings
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The reo return object is <code>SqlBuilder</code> type.</p>
     */
    @SuppressWarnings("Duplicates")
    public SqlBuilder reo(String target, Object beginValue, Object endValue, Boolean andOfOr) {
        this.andOfOr(andOfOr);
        this.braceLt();
        this.gte(target, endValue, null);
        this.lte(target, beginValue, false);
        this.braceGt();
        return this;
    }

    /**
     * <code>seo</code>
     * <p>The seo method.</p>
     * @param minTarget {@link java.lang.String} <p>The min target parameter is <code>String</code> type.</p>
     * @param maxTarget {@link java.lang.String} <p>The max target parameter is <code>String</code> type.</p>
     * @param value {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param andOfOr {@link java.lang.Boolean} <p>The and of or parameter is <code>Boolean</code> type.</p>
     * @see  java.lang.String
     * @see  java.lang.Object
     * @see  java.lang.Boolean
     * @see  java.lang.SuppressWarnings
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The seo return object is <code>SqlBuilder</code> type.</p>
     */
    @SuppressWarnings("Duplicates")
    public SqlBuilder seo(String minTarget, String maxTarget, Object value, Boolean andOfOr) {
        this.andOfOr(andOfOr);
        this.braceLt();
        this.gte(maxTarget, value, null);
        this.lte(minTarget, value, false);
        this.braceGt();
        return this;
    }

    /**
     * <code>ro</code>
     * <p>The ro method.</p>
     * @param target {@link java.lang.String} <p>The target parameter is <code>String</code> type.</p>
     * @param beginValue {@link java.lang.Object} <p>The begin value parameter is <code>Object</code> type.</p>
     * @param endValue {@link java.lang.Object} <p>The end value parameter is <code>Object</code> type.</p>
     * @param andOfOr {@link java.lang.Boolean} <p>The and of or parameter is <code>Boolean</code> type.</p>
     * @see  java.lang.String
     * @see  java.lang.Object
     * @see  java.lang.Boolean
     * @see  java.lang.SuppressWarnings
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The ro return object is <code>SqlBuilder</code> type.</p>
     */
    @SuppressWarnings("Duplicates")
    public SqlBuilder ro(String target, Object beginValue, Object endValue, Boolean andOfOr) {
        this.andOfOr(andOfOr);
        this.braceLt();
        this.gt(target, endValue, null);
        this.lt(target, beginValue, false);
        this.braceGt();
        return this;
    }

    /**
     * <code>so</code>
     * <p>The so method.</p>
     * @param minTarget {@link java.lang.String} <p>The min target parameter is <code>String</code> type.</p>
     * @param maxTarget {@link java.lang.String} <p>The max target parameter is <code>String</code> type.</p>
     * @param value {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param andOfOr {@link java.lang.Boolean} <p>The and of or parameter is <code>Boolean</code> type.</p>
     * @see  java.lang.String
     * @see  java.lang.Object
     * @see  java.lang.Boolean
     * @see  java.lang.SuppressWarnings
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The so return object is <code>SqlBuilder</code> type.</p>
     */
    @SuppressWarnings("Duplicates")
    public SqlBuilder so(String minTarget, String maxTarget, Object value, Boolean andOfOr) {
        this.andOfOr(andOfOr);
        this.braceLt();
        this.gt(maxTarget, value, null);
        this.lt(minTarget, value, false);
        this.braceGt();
        return this;
    }

    /**
     * <code>ra</code>
     * <p>The ra method.</p>
     * @param target {@link java.lang.String} <p>The target parameter is <code>String</code> type.</p>
     * @param beginValue {@link java.lang.Object} <p>The begin value parameter is <code>Object</code> type.</p>
     * @param endValue {@link java.lang.Object} <p>The end value parameter is <code>Object</code> type.</p>
     * @param andOfOr {@link java.lang.Boolean} <p>The and of or parameter is <code>Boolean</code> type.</p>
     * @see  java.lang.String
     * @see  java.lang.Object
     * @see  java.lang.Boolean
     * @see  java.lang.SuppressWarnings
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The ra return object is <code>SqlBuilder</code> type.</p>
     */
    @SuppressWarnings("Duplicates")
    public SqlBuilder ra(String target, Object beginValue, Object endValue, Boolean andOfOr) {
        this.andOfOr(andOfOr);
        this.braceLt();
        this.gt(target, beginValue, null);
        this.lt(target, endValue, true);
        this.braceGt();
        return this;
    }

    /**
     * <code>sa</code>
     * <p>The sa method.</p>
     * @param minTarget {@link java.lang.String} <p>The min target parameter is <code>String</code> type.</p>
     * @param maxTarget {@link java.lang.String} <p>The max target parameter is <code>String</code> type.</p>
     * @param value {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param andOfOr {@link java.lang.Boolean} <p>The and of or parameter is <code>Boolean</code> type.</p>
     * @see  java.lang.String
     * @see  java.lang.Object
     * @see  java.lang.Boolean
     * @see  java.lang.SuppressWarnings
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The sa return object is <code>SqlBuilder</code> type.</p>
     */
    @SuppressWarnings("Duplicates")
    public SqlBuilder sa(String minTarget, String maxTarget, Object value, Boolean andOfOr) {
        this.andOfOr(andOfOr);
        this.braceLt();
        this.gt(maxTarget, value, null);
        this.lt(minTarget, value, true);
        this.braceGt();
        return this;
    }

    /**
     * <code>rea</code>
     * <p>The rea method.</p>
     * @param target {@link java.lang.String} <p>The target parameter is <code>String</code> type.</p>
     * @param beginValue {@link java.lang.Object} <p>The begin value parameter is <code>Object</code> type.</p>
     * @param endValue {@link java.lang.Object} <p>The end value parameter is <code>Object</code> type.</p>
     * @param andOfOr {@link java.lang.Boolean} <p>The and of or parameter is <code>Boolean</code> type.</p>
     * @see  java.lang.String
     * @see  java.lang.Object
     * @see  java.lang.Boolean
     * @see  java.lang.SuppressWarnings
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The rea return object is <code>SqlBuilder</code> type.</p>
     */
    @SuppressWarnings("Duplicates")
    public SqlBuilder rea(String target, Object beginValue, Object endValue, Boolean andOfOr) {
        this.andOfOr(andOfOr);
        this.braceLt();
        this.gte(target, beginValue, null);
        this.lte(target, endValue, true);
        this.braceGt();
        return this;
    }

    /**
     * <code>sea</code>
     * <p>The sea method.</p>
     * @param minTarget {@link java.lang.String} <p>The min target parameter is <code>String</code> type.</p>
     * @param maxTarget {@link java.lang.String} <p>The max target parameter is <code>String</code> type.</p>
     * @param value {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param andOfOr {@link java.lang.Boolean} <p>The and of or parameter is <code>Boolean</code> type.</p>
     * @see  java.lang.String
     * @see  java.lang.Object
     * @see  java.lang.Boolean
     * @see  java.lang.SuppressWarnings
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The sea return object is <code>SqlBuilder</code> type.</p>
     */
    @SuppressWarnings("Duplicates")
    public SqlBuilder sea(String minTarget, String maxTarget, Object value, Boolean andOfOr) {
        this.andOfOr(andOfOr);
        this.braceLt();
        this.gte(maxTarget, value, null);
        this.lte(minTarget, value, true);
        this.braceGt();
        return this;
    }

    /**
     * <code>r</code>
     * <p>The r method.</p>
     * @param target {@link java.lang.String} <p>The target parameter is <code>String</code> type.</p>
     * @param beginValue {@link java.lang.Object} <p>The begin value parameter is <code>Object</code> type.</p>
     * @param endValue {@link java.lang.Object} <p>The end value parameter is <code>Object</code> type.</p>
     * @param andOfOr {@link java.lang.Boolean} <p>The and of or parameter is <code>Boolean</code> type.</p>
     * @see  java.lang.String
     * @see  java.lang.Object
     * @see  java.lang.Boolean
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The r return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder r(String target, Object beginValue, Object endValue, Boolean andOfOr) {
        this.gt(target, beginValue, andOfOr);
        this.lt(target, endValue, andOfOr);
        return this;
    }

    /**
     * <code>s</code>
     * <p>The s method.</p>
     * @param minTarget {@link java.lang.String} <p>The min target parameter is <code>String</code> type.</p>
     * @param maxTarget {@link java.lang.String} <p>The max target parameter is <code>String</code> type.</p>
     * @param value {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param andOfOr {@link java.lang.Boolean} <p>The and of or parameter is <code>Boolean</code> type.</p>
     * @see  java.lang.String
     * @see  java.lang.Object
     * @see  java.lang.Boolean
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The s return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder s(String minTarget, String maxTarget, Object value, Boolean andOfOr) {
        this.gt(maxTarget, value, andOfOr);
        this.lt(minTarget, value, andOfOr);
        return this;
    }

    /**
     * <code>re</code>
     * <p>The re method.</p>
     * @param target {@link java.lang.String} <p>The target parameter is <code>String</code> type.</p>
     * @param beginValue {@link java.lang.Object} <p>The begin value parameter is <code>Object</code> type.</p>
     * @param endValue {@link java.lang.Object} <p>The end value parameter is <code>Object</code> type.</p>
     * @param andOfOr {@link java.lang.Boolean} <p>The and of or parameter is <code>Boolean</code> type.</p>
     * @see  java.lang.String
     * @see  java.lang.Object
     * @see  java.lang.Boolean
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The re return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder re(String target, Object beginValue, Object endValue, Boolean andOfOr) {
        this.gte(target, beginValue, andOfOr);
        this.lte(target, endValue, andOfOr);
        return this;
    }

    /**
     * <code>se</code>
     * <p>The se method.</p>
     * @param minTarget {@link java.lang.String} <p>The min target parameter is <code>String</code> type.</p>
     * @param maxTarget {@link java.lang.String} <p>The max target parameter is <code>String</code> type.</p>
     * @param value {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param andOfOr {@link java.lang.Boolean} <p>The and of or parameter is <code>Boolean</code> type.</p>
     * @see  java.lang.String
     * @see  java.lang.Object
     * @see  java.lang.Boolean
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The se return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder se(String minTarget, String maxTarget, Object value, Boolean andOfOr) {
        this.gte(maxTarget, value, andOfOr);
        this.lte(minTarget, value, andOfOr);
        return this;
    }

    /**
     * <code>rs</code>
     * <p>The rs method.</p>
     * @param target {@link java.lang.String} <p>The target parameter is <code>String</code> type.</p>
     * @param beginValue {@link java.lang.Object} <p>The begin value parameter is <code>Object</code> type.</p>
     * @param endValue {@link java.lang.Object} <p>The end value parameter is <code>Object</code> type.</p>
     * @param andOfOr {@link java.lang.Boolean} <p>The and of or parameter is <code>Boolean</code> type.</p>
     * @param beginOfEnd {@link java.lang.Boolean} <p>The begin of end parameter is <code>Boolean</code> type.</p>
     * @see  java.lang.String
     * @see  java.lang.Object
     * @see  java.lang.Boolean
     * @see  java.lang.SuppressWarnings
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The rs return object is <code>SqlBuilder</code> type.</p>
     */
    @SuppressWarnings("Duplicates")
    public SqlBuilder rs(String target, Object beginValue, Object endValue, Boolean andOfOr, Boolean beginOfEnd) {
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
     * <p>The ss method.</p>
     * @param minTarget {@link java.lang.String} <p>The min target parameter is <code>String</code> type.</p>
     * @param maxTarget {@link java.lang.String} <p>The max target parameter is <code>String</code> type.</p>
     * @param value {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param andOfOr {@link java.lang.Boolean} <p>The and of or parameter is <code>Boolean</code> type.</p>
     * @param beginOfEnd {@link java.lang.Boolean} <p>The begin of end parameter is <code>Boolean</code> type.</p>
     * @see  java.lang.String
     * @see  java.lang.Object
     * @see  java.lang.Boolean
     * @see  java.lang.SuppressWarnings
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The ss return object is <code>SqlBuilder</code> type.</p>
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
     * <p>The gt method.</p>
     * @param target {@link java.lang.String} <p>The target parameter is <code>String</code> type.</p>
     * @param value {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param andOfOr {@link java.lang.Boolean} <p>The and of or parameter is <code>Boolean</code> type.</p>
     * @see  java.lang.String
     * @see  java.lang.Object
     * @see  java.lang.Boolean
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The gt return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder gt(String target, Object value, Boolean andOfOr) {
        if (GeneralUtils.isUsable(value)) {
            this.andOfOr(andOfOr);
            this.append(target).gt();
            this.value(value);
        }
        return this;
    }

    /**
     * <code>lt</code>
     * <p>The lt method.</p>
     * @param target {@link java.lang.String} <p>The target parameter is <code>String</code> type.</p>
     * @param value {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param andOfOr {@link java.lang.Boolean} <p>The and of or parameter is <code>Boolean</code> type.</p>
     * @see  java.lang.String
     * @see  java.lang.Object
     * @see  java.lang.Boolean
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The lt return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder lt(String target, Object value, Boolean andOfOr) {
        if (GeneralUtils.isUsable(value)) {
            this.andOfOr(andOfOr);
            this.append(target).lt();
            this.value(value);
        }
        return this;
    }


    /**
     * <code>gte</code>
     * <p>The gte method.</p>
     * @param target {@link java.lang.String} <p>The target parameter is <code>String</code> type.</p>
     * @param value {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param andOfOr {@link java.lang.Boolean} <p>The and of or parameter is <code>Boolean</code> type.</p>
     * @see  java.lang.String
     * @see  java.lang.Object
     * @see  java.lang.Boolean
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The gte return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder gte(String target, Object value, Boolean andOfOr) {
        if (GeneralUtils.isUsable(value)) {
            this.andOfOr(andOfOr);
            this.append(target).gte();
            this.value(value);
        }
        return this;
    }

    /**
     * <code>lte</code>
     * <p>The lte method.</p>
     * @param target {@link java.lang.String} <p>The target parameter is <code>String</code> type.</p>
     * @param value {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param andOfOr {@link java.lang.Boolean} <p>The and of or parameter is <code>Boolean</code> type.</p>
     * @see  java.lang.String
     * @see  java.lang.Object
     * @see  java.lang.Boolean
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The lte return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder lte(String target, Object value, Boolean andOfOr) {
        if (GeneralUtils.isUsable(value)) {
            this.andOfOr(andOfOr);
            this.append(target).lte();
            this.value(value);
        }
        return this;
    }

    /**
     * <code>value</code>
     * <p>The value method.</p>
     * @param value {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @see  java.lang.Object
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The value return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder value(Object value) {
        this.value(value, false);
        return this;
    }

    /**
     * <code>value</code>
     * <p>The value method.</p>
     * @param value {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param commaOfNone {@link java.lang.Boolean} <p>The comma of none parameter is <code>Boolean</code> type.</p>
     * @see  java.lang.Object
     * @see  java.lang.Boolean
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The value return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder value(Object value, Boolean commaOfNone) {
        if (value instanceof String) {
            this.sQuote().append(value).sQuote();
        } else if (value instanceof Date) {
            // the value is like '2020-09-11 00:00:00'
            this.sQuote().append(DateUtils.formatTime((Date) value)).sQuote();
        } else if (value instanceof RestKey) {
            this.append(((RestKey<?>) value).getKey());
        } else {
            this.append(value);
        }
        if (commaOfNone) {
            this.comma();
        } else {
            this.blank();
        }
        return this;
    }

    /**
     * <code>value</code>
     * <p>The value method.</p>
     * @param value {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param symbol {@link java.lang.String} <p>The symbol parameter is <code>String</code> type.</p>
     * @see  java.lang.Object
     * @see  java.lang.String
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The value return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder value(Object value, String symbol) {
        if (value instanceof String) {
            this.sQuote().append(value).sQuote().append(symbol).blank();
        } else if (value instanceof Date) {
            // the value is like '2020-09-11 00:00:00'
            this.sQuote().append(DateUtils.formatTime((Date) value)).sQuote().append(symbol).blank();
        } else if (value instanceof RestKey) {
            this.sQuote().append(((RestKey<?>) value).getKey()).sQuote().append(symbol).blank();
        } else {
            this.append(value).append(symbol).blank();
        }
        return this;
    }

    /**
     * <code>andOfOr</code>
     * <p>The and of or method.</p>
     * @param andOfOr {@link java.lang.Boolean} <p>The and of or parameter is <code>Boolean</code> type.</p>
     * @see  java.lang.Boolean
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The and of or return object is <code>SqlBuilder</code> type.</p>
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
     * <p>The and method.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The and return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder and() {
        this.keyword(SQLConstants.AND, false);
        return this;
    }

    /**
     * <code>or</code>
     * <p>The or method.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The or return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder or() {
        this.keyword(SQLConstants.OR, false);
        return this;
    }

    /**
     * <code>cdataLt</code>
     * <p>The cdata lt method.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The cdata lt return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder cdataLt() {
        this.append(ScriptConstants.CDATA_LT).blank();
        return this;
    }

    /**
     * <code>cdataGt</code>
     * <p>The cdata gt method.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The cdata gt return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder cdataGt() {
        this.blank().append(ScriptConstants.CDATA_GT);
        return this;
    }

    /**
     * <code>braceLt</code>
     * <p>The brace lt method.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The brace lt return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder braceLt() {
        this.blank().append(SQLConstants.BRACE_LT);
        return this;
    }

    /**
     * <code>braceGt</code>
     * <p>The brace gt method.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The brace gt return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder braceGt() {
        this.append(SQLConstants.BRACE_GT).blank();
        return this;
    }

    /**
     * <code>comma</code>
     * <p>The comma method.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The comma return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder comma() {
        this.append(SQLConstants.COMMA).blank();
        return this;
    }

    /**
     * <code>period</code>
     * <p>The period method.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The period return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder period() {
        this.append(SQLConstants.PERIOD);
        return this;
    }

    /**
     * <code>blank</code>
     * <p>The blank method.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The blank return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder blank() {
        this.append(SQLConstants.BLANK);
        return this;
    }

    /**
     * <code>sQuote</code>
     * <p>The s quote method.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The s quote return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder sQuote() {
        this.append(SQLConstants.SINGLE_QUOTE);
        return this;
    }

    /**
     * <code>dQuote</code>
     * <p>The d quote method.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The d quote return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder dQuote() {
        this.append(SQLConstants.DOUBLE_QUOTE);
        return this;
    }

    /**
     * <code>percent</code>
     * <p>The percent method.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The percent return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder percent() {
        this.append(SQLConstants.PERCENT);
        return this;
    }

    /**
     * <code>linefeed</code>
     * <p>The linefeed method.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The linefeed return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder linefeed() {
        this.append(SQLConstants.LINEFEED);
        return this;
    }

    /**
     * <code>eq</code>
     * <p>The eq method.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The eq return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder eq() {
        return this.keyword(SQLConstants.CONTRAST_EQ, false);
    }

    /**
     * <code>gt</code>
     * <p>The gt method.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The gt return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder gt() {
        return this.keyword(SQLConstants.CONTRAST_GT, false);
    }

    /**
     * <code>lt</code>
     * <p>The lt method.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The lt return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder lt() {
        return this.keyword(SQLConstants.CONTRAST_LT, false);
    }

    /**
     * <code>gte</code>
     * <p>The gte method.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The gte return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder gte() {
        return this.keyword(SQLConstants.CONTRAST_GTE, false);
    }

    /**
     * <code>lte</code>
     * <p>The lte method.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The lte return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder lte() {
        return this.keyword(SQLConstants.CONTRAST_LTE, false);
    }

    /**
     * <code>neq</code>
     * <p>The neq method.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The neq return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder neq() {
        return this.keyword(SQLConstants.CONTRAST_NEQ, false);
    }

    /**
     * <code>isn</code>
     * <p>The isn method.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The isn return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder isn() {
        return this.keyword(SQLConstants.IS_NULL, false);
    }

    /**
     * <code>inn</code>
     * <p>The inn method.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The inn return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder inn() {
        return this.keyword(SQLConstants.IS_NOT_NULL, false);
    }

    /**
     * <code>like</code>
     * <p>The like method.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The like return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder like() {
        return this.keyword(SQLConstants.LIKE, false);
    }

    /**
     * <code>in</code>
     * <p>The in method.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The in return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder in() {
        return this.keyword(SQLConstants.IN, false);
    }

    /**
     * <code>nin</code>
     * <p>The nin method.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The nin return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder nin() {
        return this.keyword(SQLConstants.NOT_IN, false);
    }

    /**
     * <code>limit</code>
     * <p>The limit method.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The limit return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder limit() {
        return this.keyword(SQLConstants.LIMIT, false);
    }

    /**
     * <code>insert</code>
     * <p>The insert method.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The insert return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder insert() {
        return this.keyword(SQLConstants.INSERT_INTO, true);
    }

    /**
     * <code>insertIgnore</code>
     * <p>The insert ignore method.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The insert ignore return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder insertIgnore() {
        return this.keyword(SQLConstants.INSERT_IGNORE_INTO, true);
    }

    /**
     * <code>update</code>
     * <p>The update method.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The update return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder update() {
        return this.keyword(SQLConstants.UPDATE, true);
    }

    /**
     * <code>select</code>
     * <p>The select method.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The select return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder select() {
        return this.keyword(SQLConstants.SELECT, true);
    }

    /**
     * <code>delete</code>
     * <p>The delete method.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The delete return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder delete() {
        return this.keyword(SQLConstants.DELETE, true);
    }

    /**
     * <code>where</code>
     * <p>The where method.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The where return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder where() {
        return this.keyword(SQLConstants.WHERE, true);
    }

    /**
     * <code>set</code>
     * <p>The set method.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The set return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder set() {
        return this.keyword(SQLConstants.SET, true);
    }

    /**
     * <code>values</code>
     * <p>The values method.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The values return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder values() {
        return this.keyword(SQLConstants.VALUES, true);
    }

    /**
     * <code>from</code>
     * <p>The from method.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The from return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder from() {
        return this.keyword(SQLConstants.FROM, true);
    }

    /**
     * <code>orderBy</code>
     * <p>The order by method.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The order by return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder orderBy() {
        return this.keyword(SQLConstants.ORDER_BY, true);
    }

    /**
     * <code>groupBy</code>
     * <p>The group by method.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The group by return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder groupBy() {
        return this.keyword(SQLConstants.GROUP_BY, true);
    }

    /**
     * <code>onDuplicateKey</code>
     * <p>The on duplicate key method.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The on duplicate key return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder onDuplicateKey() {
        return this.keyword(SQLConstants.ON_DUPLICATE_KEY_LT, true);
    }

    /**
     * <code>onConflict</code>
     * <p>The on conflict method.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The on conflict return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder onConflict() {
        return this.keyword(SQLConstants.ON_CONFLICT_LT, true);
    }

    /**
     * <code>doNothing</code>
     * <p>The do nothing method.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The do nothing return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder doNothing() {
        return this.keyword(SQLConstants.DO_NOTHING_GT, false);
    }

    /**
     * <code>doUpdate</code>
     * <p>The do update method.</p>
     * @param doOrNone boolean <p>The do or none parameter is <code>boolean</code> type.</p>
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The do update return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder doUpdate(boolean doOrNone) {
        return this.keyword(doOrNone ? SQLConstants.DO_UPDATE_GT : SQLConstants.UPDATE_GT, false);
    }

    /**
     * <code>keyword</code>
     * <p>The keyword method.</p>
     * @param keyword {@link java.lang.String} <p>The keyword parameter is <code>String</code> type.</p>
     * @param linefeed boolean <p>The linefeed parameter is <code>boolean</code> type.</p>
     * @see  java.lang.String
     * @return  {@link io.github.nichetoolkit.rice.builder.SqlBuilder} <p>The keyword return object is <code>SqlBuilder</code> type.</p>
     */
    public SqlBuilder keyword(String keyword, boolean linefeed) {
        if (linefeed) {
            this.linefeed();
        } else {
            this.blank();
        }
        this.append(keyword).blank();
        return this;
    }


}
