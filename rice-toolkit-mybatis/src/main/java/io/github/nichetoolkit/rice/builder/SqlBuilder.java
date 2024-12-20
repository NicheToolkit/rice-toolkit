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

@Getter
@SuppressWarnings({"WeakerAccess", "UnusedReturnValue"})
public final class SqlBuilder implements Serializable, CharSequence {

    public static final String EMPTY = SQLConstants.EMPTY;

    private static final long serialVersionUID = 4383685877147921098L;

    private final StringBuilder sqlBuilder;

    public SqlBuilder() {
        this.sqlBuilder = new StringBuilder();
    }

    public SqlBuilder(StringBuilder sqlBuilder) {
        this.sqlBuilder = sqlBuilder;
    }

    public SqlBuilder(int capacity) {
        this.sqlBuilder = new StringBuilder(capacity);
    }

    public SqlBuilder(String str) throws RuntimeException {
        this.sqlBuilder = new StringBuilder(str);
    }

    public SqlBuilder(CharSequence seq) {
        this.sqlBuilder = new StringBuilder(seq);
    }

    public SqlBuilder clear() {
        this.sqlBuilder.delete(0, this.sqlBuilder.length());
        return this;
    }

    public SqlBuilder append(Object obj) {
        return append(String.valueOf(obj));
    }

    public SqlBuilder append(String str) {
        sqlBuilder.append(str);
        return this;
    }

    public SqlBuilder append(StringBuilder sb) {
        sqlBuilder.append(sb);
        return this;
    }

    public SqlBuilder append(SqlBuilder sb) {
        sqlBuilder.append(sb.sqlBuilder);
        return this;
    }

    public SqlBuilder append(StringBuffer sb) {
        sqlBuilder.append(sb);
        return this;
    }

    public SqlBuilder append(CharSequence s) {
        sqlBuilder.append(s);
        return this;
    }

    public SqlBuilder append(CharSequence s, int start, int end) {
        sqlBuilder.append(s, start, end);
        return this;
    }

    public SqlBuilder append(char[] str) {
        sqlBuilder.append(str);
        return this;
    }


    public SqlBuilder append(char[] str, int offset, int len) {
        sqlBuilder.append(str, offset, len);
        return this;
    }


    public SqlBuilder append(boolean b) {
        sqlBuilder.append(b);
        return this;
    }


    public SqlBuilder append(char c) {
        sqlBuilder.append(c);
        return this;
    }


    public SqlBuilder append(int i) {
        sqlBuilder.append(i);
        return this;
    }


    public SqlBuilder append(long lng) {
        sqlBuilder.append(lng);
        return this;
    }


    public SqlBuilder append(float f) {
        sqlBuilder.append(f);
        return this;
    }

    public SqlBuilder append(double d) {
        sqlBuilder.append(d);
        return this;
    }

    public SqlBuilder appendCodePoint(int codePoint) {
        sqlBuilder.appendCodePoint(codePoint);
        return this;
    }

    public SqlBuilder delete(int start) {
        sqlBuilder.delete(start, sqlBuilder.length());
        return this;
    }

    public SqlBuilder delete(int start, int end) {
        sqlBuilder.delete(start, end);
        return this;
    }

    public SqlBuilder deleteCharAt(int index) {
        sqlBuilder.deleteCharAt(index);
        return this;
    }

    public SqlBuilder deleteLastChar() {
        sqlBuilder.deleteCharAt(sqlBuilder.length() - 1);
        return this;
    }

    public SqlBuilder replace(int start, int end, String str) {
        sqlBuilder.replace(start, end, str);
        return this;
    }

    public SqlBuilder insert(int index, char[] str, int offset,
                             int len) {
        sqlBuilder.insert(index, str, offset, len);
        return this;
    }

    public SqlBuilder insert(int offset, Object obj) {
        sqlBuilder.insert(offset, obj);
        return this;
    }

    public SqlBuilder insert(int offset, String str) {
        sqlBuilder.insert(offset, str);
        return this;
    }

    public SqlBuilder insert(int offset, char[] str) {
        sqlBuilder.insert(offset, str);
        return this;
    }

    public SqlBuilder insert(int dstOffset, CharSequence s) {
        sqlBuilder.insert(dstOffset, s);
        return this;
    }

    public SqlBuilder insert(int dstOffset, CharSequence s,
                             int start, int end) {
        sqlBuilder.insert(dstOffset, s, start, end);
        return this;
    }

    public SqlBuilder insert(int offset, boolean b) {
        sqlBuilder.insert(offset, b);
        return this;
    }

    public SqlBuilder insert(int offset, char c) {
        sqlBuilder.insert(offset, c);
        return this;
    }

    public SqlBuilder insert(int offset, int i) {
        sqlBuilder.insert(offset, i);
        return this;
    }

    public SqlBuilder insert(int offset, long l) {
        sqlBuilder.insert(offset, l);
        return this;
    }

    public SqlBuilder insert(int offset, float f) {
        sqlBuilder.insert(offset, f);
        return this;
    }

    public SqlBuilder insert(int offset, double d) {
        sqlBuilder.insert(offset, d);
        return this;
    }


    public int indexOf(String str) {
        return sqlBuilder.indexOf(str);
    }


    public int indexOf(String str, int fromIndex) {
        return sqlBuilder.indexOf(str, fromIndex);
    }


    public int lastIndexOf(String str) {
        return sqlBuilder.lastIndexOf(str);
    }


    public int lastIndexOf(String str, int fromIndex) {
        return sqlBuilder.lastIndexOf(str, fromIndex);
    }


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

    public static SqlBuilder sqlBuilder() {
        return new SqlBuilder();
    }

    public static SqlBuilder sqlBuilder(String sql) {
        return new SqlBuilder(sql);
    }

    public SqlBuilder isn(String target, Boolean andOfOr) {
        if (GeneralUtils.isNotEmpty(target)) {
            this.andOfOr(andOfOr);
            this.append(target).isn();
        }
        return this;
    }

    public SqlBuilder inn(String target, Boolean andOfOr) {
        if (GeneralUtils.isNotEmpty(target)) {
            this.andOfOr(andOfOr);
            this.append(target).inn();
        }
        return this;
    }

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

    public SqlBuilder lk(String target, String value, Boolean andOfOr) {
        return lk(target, value, null, andOfOr);
    }

    public SqlBuilder lkl(String target, String value, Boolean andOfOr) {
        return lk(target, value, true, andOfOr);
    }

    public SqlBuilder lkg(String target, String value, Boolean andOfOr) {
        return lk(target, value, false, andOfOr);
    }

    public SqlBuilder lk(String target, String value) {
        return lk(target, value, null, null);
    }

    public SqlBuilder lkl(String target, String value) {
        return lk(target, value, true, null);
    }

    public SqlBuilder lkg(String target, String value) {
        return lk(target, value, false, null);
    }

    public SqlBuilder lk(Collection<String> targets, String value, Boolean andOfOr) {
        return lk(targets, value, null, andOfOr);
    }

    public SqlBuilder lkl(Collection<String> targets, String value, Boolean andOfOr) {
        return lk(targets, value, true, andOfOr);
    }

    public SqlBuilder lkg(Collection<String> targets, String value, Boolean andOfOr) {
        return lk(targets, value, false, andOfOr);
    }

    public SqlBuilder lk(Collection<String> targets, String value) {
        return lk(targets, value, null, null);
    }

    public SqlBuilder lkl(Collection<String> targets, String value) {
        return lk(targets, value, true, null);
    }

    public SqlBuilder lkg(Collection<String> targets, String value) {
        return lk(targets, value, false, null);
    }

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

    public SqlBuilder ain(String target, Collection<?> values) {
        return in(target, values, true);
    }

    public SqlBuilder oin(String target, Collection<?> values) {
        return in(target, values, false);
    }

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

    public SqlBuilder rb(String target, Object beginValue, Object endValue) {
        if (GeneralUtils.isUsable(beginValue) && GeneralUtils.isUsable(endValue)) {
            this.gt(target, endValue, true);
            this.lt(target, beginValue, true);
        }
        return this;
    }

    public SqlBuilder sb(String minTarget, String maxTarget, Object value) {
        if (GeneralUtils.isUsable(value)) {
            this.lt(minTarget, value, true);
            this.gt(maxTarget, value, true);
        }
        return this;
    }

    public SqlBuilder reb(String target, Object beginValue, Object endValue) {
        if (GeneralUtils.isUsable(beginValue) && GeneralUtils.isUsable(endValue)) {
            this.gte(target, endValue, true);
            this.lte(target, beginValue, true);
        }
        return this;
    }

    public SqlBuilder seb(String minTarget, String maxTarget, Object value) {
        if (GeneralUtils.isUsable(value)) {
            this.lte(minTarget, value, true);
            this.gte(maxTarget, value, true);
        }
        return this;
    }

    @SuppressWarnings("Duplicates")
    public SqlBuilder reo(String target, Object beginValue, Object endValue, Boolean andOfOr) {
        this.andOfOr(andOfOr);
        this.braceLt();
        this.gte(target, endValue, null);
        this.lte(target, beginValue, false);
        this.braceGt();
        return this;
    }

    @SuppressWarnings("Duplicates")
    public SqlBuilder seo(String minTarget, String maxTarget, Object value, Boolean andOfOr) {
        this.andOfOr(andOfOr);
        this.braceLt();
        this.gte(maxTarget, value, null);
        this.lte(minTarget, value, false);
        this.braceGt();
        return this;
    }

    @SuppressWarnings("Duplicates")
    public SqlBuilder ro(String target, Object beginValue, Object endValue, Boolean andOfOr) {
        this.andOfOr(andOfOr);
        this.braceLt();
        this.gt(target, endValue, null);
        this.lt(target, beginValue, false);
        this.braceGt();
        return this;
    }

    @SuppressWarnings("Duplicates")
    public SqlBuilder so(String minTarget, String maxTarget, Object value, Boolean andOfOr) {
        this.andOfOr(andOfOr);
        this.braceLt();
        this.gt(maxTarget, value, null);
        this.lt(minTarget, value, false);
        this.braceGt();
        return this;
    }

    @SuppressWarnings("Duplicates")
    public SqlBuilder ra(String target, Object beginValue, Object endValue, Boolean andOfOr) {
        this.andOfOr(andOfOr);
        this.braceLt();
        this.gt(target, beginValue, null);
        this.lt(target, endValue, true);
        this.braceGt();
        return this;
    }

    @SuppressWarnings("Duplicates")
    public SqlBuilder sa(String minTarget, String maxTarget, Object value, Boolean andOfOr) {
        this.andOfOr(andOfOr);
        this.braceLt();
        this.gt(maxTarget, value, null);
        this.lt(minTarget, value, true);
        this.braceGt();
        return this;
    }

    @SuppressWarnings("Duplicates")
    public SqlBuilder rea(String target, Object beginValue, Object endValue, Boolean andOfOr) {
        this.andOfOr(andOfOr);
        this.braceLt();
        this.gte(target, beginValue, null);
        this.lte(target, endValue, true);
        this.braceGt();
        return this;
    }

    @SuppressWarnings("Duplicates")
    public SqlBuilder sea(String minTarget, String maxTarget, Object value, Boolean andOfOr) {
        this.andOfOr(andOfOr);
        this.braceLt();
        this.gte(maxTarget, value, null);
        this.lte(minTarget, value, true);
        this.braceGt();
        return this;
    }

    public SqlBuilder r(String target, Object beginValue, Object endValue, Boolean andOfOr) {
        this.gt(target, beginValue, andOfOr);
        this.lt(target, endValue, andOfOr);
        return this;
    }

    public SqlBuilder s(String minTarget, String maxTarget, Object value, Boolean andOfOr) {
        this.gt(maxTarget, value, andOfOr);
        this.lt(minTarget, value, andOfOr);
        return this;
    }

    public SqlBuilder re(String target, Object beginValue, Object endValue, Boolean andOfOr) {
        this.gte(target, beginValue, andOfOr);
        this.lte(target, endValue, andOfOr);
        return this;
    }

    public SqlBuilder se(String minTarget, String maxTarget, Object value, Boolean andOfOr) {
        this.gte(maxTarget, value, andOfOr);
        this.lte(minTarget, value, andOfOr);
        return this;
    }

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

    public SqlBuilder gt(String target, Object value, Boolean andOfOr) {
        if (GeneralUtils.isUsable(value)) {
            this.andOfOr(andOfOr);
            this.append(target).gt();
            this.value(value);
        }
        return this;
    }

    public SqlBuilder lt(String target, Object value, Boolean andOfOr) {
        if (GeneralUtils.isUsable(value)) {
            this.andOfOr(andOfOr);
            this.append(target).lt();
            this.value(value);
        }
        return this;
    }


    public SqlBuilder gte(String target, Object value, Boolean andOfOr) {
        if (GeneralUtils.isUsable(value)) {
            this.andOfOr(andOfOr);
            this.append(target).gte();
            this.value(value);
        }
        return this;
    }

    public SqlBuilder lte(String target, Object value, Boolean andOfOr) {
        if (GeneralUtils.isUsable(value)) {
            this.andOfOr(andOfOr);
            this.append(target).lte();
            this.value(value);
        }
        return this;
    }

    public SqlBuilder value(Object value) {
        this.value(value, false);
        return this;
    }

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

    public SqlBuilder and() {
        this.keyword(SQLConstants.AND, false);
        return this;
    }

    public SqlBuilder or() {
        this.keyword(SQLConstants.OR, false);
        return this;
    }

    public SqlBuilder cdataLt() {
        this.append(ScriptConstants.CDATA_LT).blank();
        return this;
    }

    public SqlBuilder cdataGt() {
        this.blank().append(ScriptConstants.CDATA_GT);
        return this;
    }

    public SqlBuilder braceLt() {
        this.blank().append(SQLConstants.BRACE_LT);
        return this;
    }

    public SqlBuilder braceGt() {
        this.append(SQLConstants.BRACE_GT).blank();
        return this;
    }

    public SqlBuilder comma() {
        this.append(SQLConstants.COMMA).blank();
        return this;
    }

    public SqlBuilder period() {
        this.append(SQLConstants.PERIOD);
        return this;
    }

    public SqlBuilder blank() {
        this.append(SQLConstants.BLANK);
        return this;
    }

    public SqlBuilder sQuote() {
        this.append(SQLConstants.SINGLE_QUOTE);
        return this;
    }

    public SqlBuilder dQuote() {
        this.append(SQLConstants.DOUBLE_QUOTE);
        return this;
    }

    public SqlBuilder percent() {
        this.append(SQLConstants.PERCENT);
        return this;
    }

    public SqlBuilder linefeed() {
        this.append(SQLConstants.LINEFEED);
        return this;
    }

    public SqlBuilder eq() {
        return this.keyword(SQLConstants.CONTRAST_EQ, false);
    }

    public SqlBuilder gt() {
        return this.keyword(SQLConstants.CONTRAST_GT, false);
    }

    public SqlBuilder lt() {
        return this.keyword(SQLConstants.CONTRAST_LT, false);
    }

    public SqlBuilder gte() {
        return this.keyword(SQLConstants.CONTRAST_GTE, false);
    }

    public SqlBuilder lte() {
        return this.keyword(SQLConstants.CONTRAST_LTE, false);
    }

    public SqlBuilder neq() {
        return this.keyword(SQLConstants.CONTRAST_NEQ, false);
    }

    public SqlBuilder isn() {
        return this.keyword(SQLConstants.IS_NULL, false);
    }

    public SqlBuilder inn() {
        return this.keyword(SQLConstants.IS_NOT_NULL, false);
    }

    public SqlBuilder like() {
        return this.keyword(SQLConstants.LIKE, false);
    }

    public SqlBuilder in() {
        return this.keyword(SQLConstants.IN, false);
    }

    public SqlBuilder nin() {
        return this.keyword(SQLConstants.NOT_IN, false);
    }

    public SqlBuilder limit() {
        return this.keyword(SQLConstants.LIMIT, false);
    }

    public SqlBuilder insert() {
        return this.keyword(SQLConstants.INSERT_INTO, true);
    }

    public SqlBuilder insertIgnore() {
        return this.keyword(SQLConstants.INSERT_IGNORE_INTO, true);
    }

    public SqlBuilder update() {
        return this.keyword(SQLConstants.UPDATE, true);
    }

    public SqlBuilder select() {
        return this.keyword(SQLConstants.SELECT, true);
    }

    public SqlBuilder delete() {
        return this.keyword(SQLConstants.DELETE, true);
    }

    public SqlBuilder where() {
        return this.keyword(SQLConstants.WHERE, true);
    }

    public SqlBuilder set() {
        return this.keyword(SQLConstants.SET, true);
    }

    public SqlBuilder values() {
        return this.keyword(SQLConstants.VALUES, true);
    }

    public SqlBuilder from() {
        return this.keyword(SQLConstants.FROM, true);
    }

    public SqlBuilder orderBy() {
        return this.keyword(SQLConstants.ORDER_BY, true);
    }

    public SqlBuilder groupBy() {
        return this.keyword(SQLConstants.GROUP_BY, true);
    }

    public SqlBuilder onDuplicateKey() {
        return this.keyword(SQLConstants.ON_DUPLICATE_KEY_LT, true);
    }

    public SqlBuilder onConflict() {
        return this.keyword(SQLConstants.ON_CONFLICT_LT, true);
    }

    public SqlBuilder doNothing() {
        return this.keyword(SQLConstants.DO_NOTHING_GT, false);
    }

    public SqlBuilder doUpdate(boolean doOrNone) {
        return this.keyword(doOrNone ? SQLConstants.DO_UPDATE_GT : SQLConstants.UPDATE_GT, false);
    }

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
