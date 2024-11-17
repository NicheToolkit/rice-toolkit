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
 * The type Sql builder.
 */
@Getter
@SuppressWarnings({"WeakerAccess", "UnusedReturnValue"})
public final class SqlBuilder implements Serializable, CharSequence {

    /**
     * The constant EMPTY.
     */
    public static final String EMPTY = SQLConstants.EMPTY;

    private static final long serialVersionUID = 4383685877147921098L;

    private final StringBuilder sqlBuilder;

    /**
     * Instantiates a new Sql builder.
     */
    public SqlBuilder() {
        this.sqlBuilder = new StringBuilder();
    }

    /**
     * Instantiates a new Sql builder.
     *
     * @param sqlBuilder the sql builder
     */
    public SqlBuilder(StringBuilder sqlBuilder) {
        this.sqlBuilder = sqlBuilder;
    }

    /**
     * Instantiates a new Sql builder.
     *
     * @param capacity the capacity
     */
    public SqlBuilder(int capacity) {
        this.sqlBuilder = new StringBuilder(capacity);
    }

    /**
     * Instantiates a new Sql builder.
     *
     * @param str the str
     * @throws RuntimeException the runtime exception
     */
    public SqlBuilder(String str) throws RuntimeException {
        this.sqlBuilder = new StringBuilder(str);
    }

    /**
     * Instantiates a new Sql builder.
     *
     * @param seq the seq
     */
    public SqlBuilder(CharSequence seq) {
        this.sqlBuilder = new StringBuilder(seq);
    }

    /**
     * Clear sql builder.
     *
     * @return the sql builder
     */
    public SqlBuilder clear() {
        this.sqlBuilder.delete(0, this.sqlBuilder.length());
        return this;
    }

    /**
     * Append sql builder.
     *
     * @param obj the obj
     * @return the sql builder
     */
    public SqlBuilder append(Object obj) {
        return append(String.valueOf(obj));
    }

    /**
     * Append sql builder.
     *
     * @param str the str
     * @return the sql builder
     */
    public SqlBuilder append(String str) {
        sqlBuilder.append(str);
        return this;
    }

    /**
     * Append sql builder.
     *
     * @param sb the sb
     * @return the sql builder
     */
    public SqlBuilder append(StringBuilder sb) {
        sqlBuilder.append(sb);
        return this;
    }

    /**
     * Append sql builder.
     *
     * @param sb the sb
     * @return the sql builder
     */
    public SqlBuilder append(SqlBuilder sb) {
        sqlBuilder.append(sb.sqlBuilder);
        return this;
    }

    /**
     * Append sql builder.
     *
     * @param sb the sb
     * @return the sql builder
     */
    public SqlBuilder append(StringBuffer sb) {
        sqlBuilder.append(sb);
        return this;
    }

    /**
     * Append sql builder.
     *
     * @param s the s
     * @return the sql builder
     */
    public SqlBuilder append(CharSequence s) {
        sqlBuilder.append(s);
        return this;
    }

    /**
     * Append sql builder.
     *
     * @param s the s
     * @param start the start
     * @param end the end
     * @return the sql builder
     */
    public SqlBuilder append(CharSequence s, int start, int end) {
        sqlBuilder.append(s, start, end);
        return this;
    }

    /**
     * Append sql builder.
     *
     * @param str the str
     * @return the sql builder
     */
    public SqlBuilder append(char[] str) {
        sqlBuilder.append(str);
        return this;
    }


    /**
     * Append sql builder.
     *
     * @param str the str
     * @param offset the offset
     * @param len the len
     * @return the sql builder
     */
    public SqlBuilder append(char[] str, int offset, int len) {
        sqlBuilder.append(str, offset, len);
        return this;
    }


    /**
     * Append sql builder.
     *
     * @param b the b
     * @return the sql builder
     */
    public SqlBuilder append(boolean b) {
        sqlBuilder.append(b);
        return this;
    }


    /**
     * Append sql builder.
     *
     * @param c the c
     * @return the sql builder
     */
    public SqlBuilder append(char c) {
        sqlBuilder.append(c);
        return this;
    }


    /**
     * Append sql builder.
     *
     * @param i the
     * @return the sql builder
     */
    public SqlBuilder append(int i) {
        sqlBuilder.append(i);
        return this;
    }


    /**
     * Append sql builder.
     *
     * @param lng the lng
     * @return the sql builder
     */
    public SqlBuilder append(long lng) {
        sqlBuilder.append(lng);
        return this;
    }


    /**
     * Append sql builder.
     *
     * @param f the f
     * @return the sql builder
     */
    public SqlBuilder append(float f) {
        sqlBuilder.append(f);
        return this;
    }

    /**
     * Append sql builder.
     *
     * @param d the d
     * @return the sql builder
     */
    public SqlBuilder append(double d) {
        sqlBuilder.append(d);
        return this;
    }

    /**
     * Append code point sql builder.
     *
     * @param codePoint the code point
     * @return the sql builder
     */
    public SqlBuilder appendCodePoint(int codePoint) {
        sqlBuilder.appendCodePoint(codePoint);
        return this;
    }

    /**
     * Delete sql builder.
     *
     * @param start the start
     * @return the sql builder
     */
    public SqlBuilder delete(int start) {
        sqlBuilder.delete(start, sqlBuilder.length());
        return this;
    }

    /**
     * Delete sql builder.
     *
     * @param start the start
     * @param end the end
     * @return the sql builder
     */
    public SqlBuilder delete(int start, int end) {
        sqlBuilder.delete(start, end);
        return this;
    }

    /**
     * Delete char at sql builder.
     *
     * @param index the index
     * @return the sql builder
     */
    public SqlBuilder deleteCharAt(int index) {
        sqlBuilder.deleteCharAt(index);
        return this;
    }

    /**
     * Delete last char sql builder.
     *
     * @return the sql builder
     */
    public SqlBuilder deleteLastChar() {
        sqlBuilder.deleteCharAt(sqlBuilder.length() - 1);
        return this;
    }

    /**
     * Replace sql builder.
     *
     * @param start the start
     * @param end the end
     * @param str the str
     * @return the sql builder
     */
    public SqlBuilder replace(int start, int end, String str) {
        sqlBuilder.replace(start, end, str);
        return this;
    }

    /**
     * Insert sql builder.
     *
     * @param index the index
     * @param str the str
     * @param offset the offset
     * @param len the len
     * @return the sql builder
     */
    public SqlBuilder insert(int index, char[] str, int offset,
                             int len) {
        sqlBuilder.insert(index, str, offset, len);
        return this;
    }

    /**
     * Insert sql builder.
     *
     * @param offset the offset
     * @param obj the obj
     * @return the sql builder
     */
    public SqlBuilder insert(int offset, Object obj) {
        sqlBuilder.insert(offset, obj);
        return this;
    }

    /**
     * Insert sql builder.
     *
     * @param offset the offset
     * @param str the str
     * @return the sql builder
     */
    public SqlBuilder insert(int offset, String str) {
        sqlBuilder.insert(offset, str);
        return this;
    }

    /**
     * Insert sql builder.
     *
     * @param offset the offset
     * @param str the str
     * @return the sql builder
     */
    public SqlBuilder insert(int offset, char[] str) {
        sqlBuilder.insert(offset, str);
        return this;
    }

    /**
     * Insert sql builder.
     *
     * @param dstOffset the dst offset
     * @param s the s
     * @return the sql builder
     */
    public SqlBuilder insert(int dstOffset, CharSequence s) {
        sqlBuilder.insert(dstOffset, s);
        return this;
    }

    /**
     * Insert sql builder.
     *
     * @param dstOffset the dst offset
     * @param s the s
     * @param start the start
     * @param end the end
     * @return the sql builder
     */
    public SqlBuilder insert(int dstOffset, CharSequence s,
                             int start, int end) {
        sqlBuilder.insert(dstOffset, s, start, end);
        return this;
    }

    /**
     * Insert sql builder.
     *
     * @param offset the offset
     * @param b the b
     * @return the sql builder
     */
    public SqlBuilder insert(int offset, boolean b) {
        sqlBuilder.insert(offset, b);
        return this;
    }

    /**
     * Insert sql builder.
     *
     * @param offset the offset
     * @param c the c
     * @return the sql builder
     */
    public SqlBuilder insert(int offset, char c) {
        sqlBuilder.insert(offset, c);
        return this;
    }

    /**
     * Insert sql builder.
     *
     * @param offset the offset
     * @param i the
     * @return the sql builder
     */
    public SqlBuilder insert(int offset, int i) {
        sqlBuilder.insert(offset, i);
        return this;
    }

    /**
     * Insert sql builder.
     *
     * @param offset the offset
     * @param l the l
     * @return the sql builder
     */
    public SqlBuilder insert(int offset, long l) {
        sqlBuilder.insert(offset, l);
        return this;
    }

    /**
     * Insert sql builder.
     *
     * @param offset the offset
     * @param f the f
     * @return the sql builder
     */
    public SqlBuilder insert(int offset, float f) {
        sqlBuilder.insert(offset, f);
        return this;
    }

    /**
     * Insert sql builder.
     *
     * @param offset the offset
     * @param d the d
     * @return the sql builder
     */
    public SqlBuilder insert(int offset, double d) {
        sqlBuilder.insert(offset, d);
        return this;
    }


    /**
     * Index of int.
     *
     * @param str the str
     * @return the int
     */
    public int indexOf(String str) {
        return sqlBuilder.indexOf(str);
    }


    /**
     * Index of int.
     *
     * @param str the str
     * @param fromIndex the from index
     * @return the int
     */
    public int indexOf(String str, int fromIndex) {
        return sqlBuilder.indexOf(str, fromIndex);
    }


    /**
     * Last index of int.
     *
     * @param str the str
     * @return the int
     */
    public int lastIndexOf(String str) {
        return sqlBuilder.lastIndexOf(str);
    }


    /**
     * Last index of int.
     *
     * @param str the str
     * @param fromIndex the from index
     * @return the int
     */
    public int lastIndexOf(String str, int fromIndex) {
        return sqlBuilder.lastIndexOf(str, fromIndex);
    }


    /**
     * Reverse sql builder.
     *
     * @return the sql builder
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
     * Sql builder sql builder.
     *
     * @return the sql builder
     */
    public static SqlBuilder sqlBuilder() {
        return new SqlBuilder();
    }

    /**
     * Sql builder sql builder.
     *
     * @param sql the sql
     * @return the sql builder
     */
    public static SqlBuilder sqlBuilder(String sql) {
        return new SqlBuilder(sql);
    }

    /**
     * Isn sql builder.
     *
     * @param target the target
     * @param andOfOr the and of or
     * @return the sql builder
     */
    public SqlBuilder isn(String target, Boolean andOfOr) {
        if (GeneralUtils.isNotEmpty(target)) {
            this.andOfOr(andOfOr);
            this.append(target).isn();
        }
        return this;
    }

    /**
     * Inn sql builder.
     *
     * @param target the target
     * @param andOfOr the and of or
     * @return the sql builder
     */
    public SqlBuilder inn(String target, Boolean andOfOr) {
        if (GeneralUtils.isNotEmpty(target)) {
            this.andOfOr(andOfOr);
            this.append(target).inn();
        }
        return this;
    }

    /**
     * Eq sql builder.
     *
     * @param target the target
     * @param value the value
     * @param andOfOr the and of or
     * @return the sql builder
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
     * Neq sql builder.
     *
     * @param target the target
     * @param value the value
     * @param andOfOr the and of or
     * @return the sql builder
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
     * Lk sql builder.
     *
     * @param target the target
     * @param value the value
     * @param andOfOr the and of or
     * @return the sql builder
     */
    public SqlBuilder lk(String target, String value, Boolean andOfOr) {
        return lk(target, value, null, andOfOr);
    }

    /**
     * Lkl sql builder.
     *
     * @param target the target
     * @param value the value
     * @param andOfOr the and of or
     * @return the sql builder
     */
    public SqlBuilder lkl(String target, String value, Boolean andOfOr) {
        return lk(target, value, true, andOfOr);
    }

    /**
     * Lkg sql builder.
     *
     * @param target the target
     * @param value the value
     * @param andOfOr the and of or
     * @return the sql builder
     */
    public SqlBuilder lkg(String target, String value, Boolean andOfOr) {
        return lk(target, value, false, andOfOr);
    }

    /**
     * Lk sql builder.
     *
     * @param target the target
     * @param value the value
     * @return the sql builder
     */
    public SqlBuilder lk(String target, String value) {
        return lk(target, value, null, null);
    }

    /**
     * Lkl sql builder.
     *
     * @param target the target
     * @param value the value
     * @return the sql builder
     */
    public SqlBuilder lkl(String target, String value) {
        return lk(target, value, true, null);
    }

    /**
     * Lkg sql builder.
     *
     * @param target the target
     * @param value the value
     * @return the sql builder
     */
    public SqlBuilder lkg(String target, String value) {
        return lk(target, value, false, null);
    }

    /**
     * Lk sql builder.
     *
     * @param targets the targets
     * @param value the value
     * @param andOfOr the and of or
     * @return the sql builder
     */
    public SqlBuilder lk(Collection<String> targets, String value, Boolean andOfOr) {
        return lk(targets, value, null, andOfOr);
    }

    /**
     * Lkl sql builder.
     *
     * @param targets the targets
     * @param value the value
     * @param andOfOr the and of or
     * @return the sql builder
     */
    public SqlBuilder lkl(Collection<String> targets, String value, Boolean andOfOr) {
        return lk(targets, value, true, andOfOr);
    }

    /**
     * Lkg sql builder.
     *
     * @param targets the targets
     * @param value the value
     * @param andOfOr the and of or
     * @return the sql builder
     */
    public SqlBuilder lkg(Collection<String> targets, String value, Boolean andOfOr) {
        return lk(targets, value, false, andOfOr);
    }

    /**
     * Lk sql builder.
     *
     * @param targets the targets
     * @param value the value
     * @return the sql builder
     */
    public SqlBuilder lk(Collection<String> targets, String value) {
        return lk(targets, value, null, null);
    }

    /**
     * Lkl sql builder.
     *
     * @param targets the targets
     * @param value the value
     * @return the sql builder
     */
    public SqlBuilder lkl(Collection<String> targets, String value) {
        return lk(targets, value, true, null);
    }

    /**
     * Lkg sql builder.
     *
     * @param targets the targets
     * @param value the value
     * @return the sql builder
     */
    public SqlBuilder lkg(Collection<String> targets, String value) {
        return lk(targets, value, false, null);
    }

    /**
     * Lk sql builder.
     *
     * @param targets the targets
     * @param value the value
     * @param ltOfGt the lt of gt
     * @param andOfOr the and of or
     * @return the sql builder
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
     * Lk sql builder.
     *
     * @param target the target
     * @param value the value
     * @param ltOfGt the lt of gt
     * @param andOfOr the and of or
     * @return the sql builder
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
     * Ain sql builder.
     *
     * @param target the target
     * @param values the values
     * @return the sql builder
     */
    public SqlBuilder ain(String target, Collection<?> values) {
        return in(target, values, true);
    }

    /**
     * Oin sql builder.
     *
     * @param target the target
     * @param values the values
     * @return the sql builder
     */
    public SqlBuilder oin(String target, Collection<?> values) {
        return in(target, values, false);
    }

    /**
     * Nin sql builder.
     *
     * @param target the target
     * @param values the values
     * @param andOfOr the and of or
     * @return the sql builder
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
     * In sql builder.
     *
     * @param target the target
     * @param values the values
     * @param andOfOr the and of or
     * @return the sql builder
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
     * Rb sql builder.
     *
     * @param target the target
     * @param beginValue the begin value
     * @param endValue the end value
     * @return the sql builder
     */
    public SqlBuilder rb(String target, Object beginValue, Object endValue) {
        if (GeneralUtils.isUsable(beginValue) && GeneralUtils.isUsable(endValue)) {
            this.gt(target, endValue, true);
            this.lt(target, beginValue, true);
        }
        return this;
    }

    /**
     * Sb sql builder.
     *
     * @param minTarget the min target
     * @param maxTarget the max target
     * @param value the value
     * @return the sql builder
     */
    public SqlBuilder sb(String minTarget, String maxTarget, Object value) {
        if (GeneralUtils.isUsable(value)) {
            this.lt(minTarget, value, true);
            this.gt(maxTarget, value, true);
        }
        return this;
    }

    /**
     * Reb sql builder.
     *
     * @param target the target
     * @param beginValue the begin value
     * @param endValue the end value
     * @return the sql builder
     */
    public SqlBuilder reb(String target, Object beginValue, Object endValue) {
        if (GeneralUtils.isUsable(beginValue) && GeneralUtils.isUsable(endValue)) {
            this.gte(target, endValue, true);
            this.lte(target, beginValue, true);
        }
        return this;
    }

    /**
     * Seb sql builder.
     *
     * @param minTarget the min target
     * @param maxTarget the max target
     * @param value the value
     * @return the sql builder
     */
    public SqlBuilder seb(String minTarget, String maxTarget, Object value) {
        if (GeneralUtils.isUsable(value)) {
            this.lte(minTarget, value, true);
            this.gte(maxTarget, value, true);
        }
        return this;
    }

    /**
     * Reo sql builder.
     *
     * @param target the target
     * @param beginValue the begin value
     * @param endValue the end value
     * @param andOfOr the and of or
     * @return the sql builder
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
     * Seo sql builder.
     *
     * @param minTarget the min target
     * @param maxTarget the max target
     * @param value the value
     * @param andOfOr the and of or
     * @return the sql builder
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
     * Ro sql builder.
     *
     * @param target the target
     * @param beginValue the begin value
     * @param endValue the end value
     * @param andOfOr the and of or
     * @return the sql builder
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
     * So sql builder.
     *
     * @param minTarget the min target
     * @param maxTarget the max target
     * @param value the value
     * @param andOfOr the and of or
     * @return the sql builder
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
     * Ra sql builder.
     *
     * @param target the target
     * @param beginValue the begin value
     * @param endValue the end value
     * @param andOfOr the and of or
     * @return the sql builder
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
     * Sa sql builder.
     *
     * @param minTarget the min target
     * @param maxTarget the max target
     * @param value the value
     * @param andOfOr the and of or
     * @return the sql builder
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
     * Rea sql builder.
     *
     * @param target the target
     * @param beginValue the begin value
     * @param endValue the end value
     * @param andOfOr the and of or
     * @return the sql builder
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
     * Sea sql builder.
     *
     * @param minTarget the min target
     * @param maxTarget the max target
     * @param value the value
     * @param andOfOr the and of or
     * @return the sql builder
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
     * R sql builder.
     *
     * @param target the target
     * @param beginValue the begin value
     * @param endValue the end value
     * @param andOfOr the and of or
     * @return the sql builder
     */
    public SqlBuilder r(String target, Object beginValue, Object endValue, Boolean andOfOr) {
        this.gt(target, beginValue, andOfOr);
        this.lt(target, endValue, andOfOr);
        return this;
    }

    /**
     * S sql builder.
     *
     * @param minTarget the min target
     * @param maxTarget the max target
     * @param value the value
     * @param andOfOr the and of or
     * @return the sql builder
     */
    public SqlBuilder s(String minTarget, String maxTarget, Object value, Boolean andOfOr) {
        this.gt(maxTarget, value, andOfOr);
        this.lt(minTarget, value, andOfOr);
        return this;
    }

    /**
     * Re sql builder.
     *
     * @param target the target
     * @param beginValue the begin value
     * @param endValue the end value
     * @param andOfOr the and of or
     * @return the sql builder
     */
    public SqlBuilder re(String target, Object beginValue, Object endValue, Boolean andOfOr) {
        this.gte(target, beginValue, andOfOr);
        this.lte(target, endValue, andOfOr);
        return this;
    }

    /**
     * Se sql builder.
     *
     * @param minTarget the min target
     * @param maxTarget the max target
     * @param value the value
     * @param andOfOr the and of or
     * @return the sql builder
     */
    public SqlBuilder se(String minTarget, String maxTarget, Object value, Boolean andOfOr) {
        this.gte(maxTarget, value, andOfOr);
        this.lte(minTarget, value, andOfOr);
        return this;
    }

    /**
     * Rs sql builder.
     *
     * @param target the target
     * @param beginValue the begin value
     * @param endValue the end value
     * @param andOfOr the and of or
     * @param beginOfEnd the begin of end
     * @return the sql builder
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
     * Ss sql builder.
     *
     * @param minTarget the min target
     * @param maxTarget the max target
     * @param value the value
     * @param andOfOr the and of or
     * @param beginOfEnd the begin of end
     * @return the sql builder
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
     * Gt sql builder.
     *
     * @param target the target
     * @param value the value
     * @param andOfOr the and of or
     * @return the sql builder
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
     * Lt sql builder.
     *
     * @param target the target
     * @param value the value
     * @param andOfOr the and of or
     * @return the sql builder
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
     * Gte sql builder.
     *
     * @param target the target
     * @param value the value
     * @param andOfOr the and of or
     * @return the sql builder
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
     * Lte sql builder.
     *
     * @param target the target
     * @param value the value
     * @param andOfOr the and of or
     * @return the sql builder
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
     * Value sql builder.
     *
     * @param value the value
     * @return the sql builder
     */
    public SqlBuilder value(Object value) {
        this.value(value, false);
        return this;
    }

    /**
     * Value sql builder.
     *
     * @param value the value
     * @param commaOfNone the comma of none
     * @return the sql builder
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
     * Value sql builder.
     *
     * @param value the value
     * @param symbol the symbol
     * @return the sql builder
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
     * And of or sql builder.
     *
     * @param andOfOr the and of or
     * @return the sql builder
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
     * And sql builder.
     *
     * @return the sql builder
     */
    public SqlBuilder and() {
        this.keyword(SQLConstants.AND, false);
        return this;
    }

    /**
     * Or sql builder.
     *
     * @return the sql builder
     */
    public SqlBuilder or() {
        this.keyword(SQLConstants.OR, false);
        return this;
    }

    /**
     * Cdata lt sql builder.
     *
     * @return the sql builder
     */
    public SqlBuilder cdataLt() {
        this.append(ScriptConstants.CDATA_LT).blank();
        return this;
    }

    /**
     * Cdata gt sql builder.
     *
     * @return the sql builder
     */
    public SqlBuilder cdataGt() {
        this.blank().append(ScriptConstants.CDATA_GT);
        return this;
    }

    /**
     * Brace lt sql builder.
     *
     * @return the sql builder
     */
    public SqlBuilder braceLt() {
        this.blank().append(SQLConstants.BRACE_LT);
        return this;
    }

    /**
     * Brace gt sql builder.
     *
     * @return the sql builder
     */
    public SqlBuilder braceGt() {
        this.append(SQLConstants.BRACE_GT).blank();
        return this;
    }

    /**
     * Comma sql builder.
     *
     * @return the sql builder
     */
    public SqlBuilder comma() {
        this.append(SQLConstants.COMMA).blank();
        return this;
    }

    /**
     * Period sql builder.
     *
     * @return the sql builder
     */
    public SqlBuilder period() {
        this.append(SQLConstants.PERIOD);
        return this;
    }

    /**
     * Blank sql builder.
     *
     * @return the sql builder
     */
    public SqlBuilder blank() {
        this.append(SQLConstants.BLANK);
        return this;
    }

    /**
     * S quote sql builder.
     *
     * @return the sql builder
     */
    public SqlBuilder sQuote() {
        this.append(SQLConstants.SINGLE_QUOTE);
        return this;
    }

    /**
     * D quote sql builder.
     *
     * @return the sql builder
     */
    public SqlBuilder dQuote() {
        this.append(SQLConstants.DOUBLE_QUOTE);
        return this;
    }

    /**
     * Percent sql builder.
     *
     * @return the sql builder
     */
    public SqlBuilder percent() {
        this.append(SQLConstants.PERCENT);
        return this;
    }

    /**
     * Linefeed sql builder.
     *
     * @return the sql builder
     */
    public SqlBuilder linefeed() {
        this.append(SQLConstants.LINEFEED);
        return this;
    }

    /**
     * Eq sql builder.
     *
     * @return the sql builder
     */
    public SqlBuilder eq() {
        return this.keyword(SQLConstants.CONTRAST_EQ, false);
    }

    /**
     * Gt sql builder.
     *
     * @return the sql builder
     */
    public SqlBuilder gt() {
        return this.keyword(SQLConstants.CONTRAST_GT, false);
    }

    /**
     * Lt sql builder.
     *
     * @return the sql builder
     */
    public SqlBuilder lt() {
        return this.keyword(SQLConstants.CONTRAST_LT, false);
    }

    /**
     * Gte sql builder.
     *
     * @return the sql builder
     */
    public SqlBuilder gte() {
        return this.keyword(SQLConstants.CONTRAST_GTE, false);
    }

    /**
     * Lte sql builder.
     *
     * @return the sql builder
     */
    public SqlBuilder lte() {
        return this.keyword(SQLConstants.CONTRAST_LTE, false);
    }

    /**
     * Neq sql builder.
     *
     * @return the sql builder
     */
    public SqlBuilder neq() {
        return this.keyword(SQLConstants.CONTRAST_NEQ, false);
    }

    /**
     * Isn sql builder.
     *
     * @return the sql builder
     */
    public SqlBuilder isn() {
        return this.keyword(SQLConstants.IS_NULL, false);
    }

    /**
     * Inn sql builder.
     *
     * @return the sql builder
     */
    public SqlBuilder inn() {
        return this.keyword(SQLConstants.IS_NOT_NULL, false);
    }

    /**
     * Like sql builder.
     *
     * @return the sql builder
     */
    public SqlBuilder like() {
        return this.keyword(SQLConstants.LIKE, false);
    }

    /**
     * In sql builder.
     *
     * @return the sql builder
     */
    public SqlBuilder in() {
        return this.keyword(SQLConstants.IN, false);
    }

    /**
     * Nin sql builder.
     *
     * @return the sql builder
     */
    public SqlBuilder nin() {
        return this.keyword(SQLConstants.NOT_IN, false);
    }

    /**
     * Limit sql builder.
     *
     * @return the sql builder
     */
    public SqlBuilder limit() {
        return this.keyword(SQLConstants.LIMIT, false);
    }

    /**
     * Insert sql builder.
     *
     * @return the sql builder
     */
    public SqlBuilder insert() {
        return this.keyword(SQLConstants.INSERT, true);
    }

    /**
     * Update sql builder.
     *
     * @return the sql builder
     */
    public SqlBuilder update() {
        return this.keyword(SQLConstants.UPDATE, true);
    }

    /**
     * Select sql builder.
     *
     * @return the sql builder
     */
    public SqlBuilder select() {
        return this.keyword(SQLConstants.SELECT, true);
    }

    /**
     * Delete sql builder.
     *
     * @return the sql builder
     */
    public SqlBuilder delete() {
        return this.keyword(SQLConstants.DELETE, true);
    }

    /**
     * Where sql builder.
     *
     * @return the sql builder
     */
    public SqlBuilder where() {
        return this.keyword(SQLConstants.WHERE, true);
    }

    /**
     * Set sql builder.
     *
     * @return the sql builder
     */
    public SqlBuilder set() {
        return this.keyword(SQLConstants.SET, true);
    }

    /**
     * Values sql builder.
     *
     * @return the sql builder
     */
    public SqlBuilder values() {
        return this.keyword(SQLConstants.VALUES, true);
    }

    /**
     * From sql builder.
     *
     * @return the sql builder
     */
    public SqlBuilder from() {
        return this.keyword(SQLConstants.FROM, true);
    }

    /**
     * Order by sql builder.
     *
     * @return the sql builder
     */
    public SqlBuilder orderBy() {
        return this.keyword(SQLConstants.ORDER_BY, true);
    }

    /**
     * Group by sql builder.
     *
     * @return the sql builder
     */
    public SqlBuilder groupBy() {
        return this.keyword(SQLConstants.GROUP_BY, true);
    }

    /**
     * On duplicate key sql builder.
     *
     * @return the sql builder
     */
    public SqlBuilder onDuplicateKey() {
        return this.keyword(SQLConstants.ON_DUPLICATE_KEY_LT, true);
    }

    /**
     * On conflict sql builder.
     *
     * @return the sql builder
     */
    public SqlBuilder onConflict() {
        return this.keyword(SQLConstants.ON_CONFLICT_LT, true);
    }

    /**
     * Do nothing sql builder.
     *
     * @return the sql builder
     */
    public SqlBuilder doNothing() {
        return this.keyword(SQLConstants.DO_NOTHING_GT, false);
    }

    /**
     * Do update sql builder.
     *
     * @param doOrNone the do or none
     * @return the sql builder
     */
    public SqlBuilder doUpdate(boolean doOrNone) {
        return this.keyword(doOrNone ? SQLConstants.DO_UPDATE_GT : SQLConstants.UPDATE_GT, false);
    }

    /**
     * Keyword sql builder.
     *
     * @param keyword the keyword
     * @param linefeed the linefeed
     * @return the sql builder
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
