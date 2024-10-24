package io.github.nichetoolkit.rice.filter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.RestOperate;
import io.github.nichetoolkit.rice.RestSort;
import io.github.nichetoolkit.rice.builder.SqlBuilders;
import io.github.nichetoolkit.rice.enums.OperateType;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

/**
 * <code>TimeFilter</code>
 * <p>The time filter class.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @param <K> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.filter.IdFilter
 * @see java.lang.SuppressWarnings
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk1.8
 */
@SuppressWarnings({"WeakerAccess", "unchecked"})
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TimeFilter<I, K> extends IdFilter<I, K> {

    /**
     * <code>startTime</code>
     * {@link java.util.Date} <p>The <code>startTime</code> field.</p>
     * @see java.util.Date
     * @see org.springframework.format.annotation.DateTimeFormat
     * @see com.fasterxml.jackson.annotation.JsonFormat
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date startTime;
    /**
     * <code>endTime</code>
     * {@link java.util.Date} <p>The <code>endTime</code> field.</p>
     * @see java.util.Date
     * @see org.springframework.format.annotation.DateTimeFormat
     * @see com.fasterxml.jackson.annotation.JsonFormat
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date endTime;

    /**
     * <code>TimeFilter</code>
     * <p>Instantiates a new time filter.</p>
     */
    public TimeFilter() {
    }

    /**
     * <code>TimeFilter</code>
     * <p>Instantiates a new time filter.</p>
     * @param id I <p>The id parameter is <code>I</code> type.</p>
     */
    public TimeFilter(I id) {
        super(id);
    }

    /**
     * <code>TimeFilter</code>
     * <p>Instantiates a new time filter.</p>
     * @param ids I <p>The ids parameter is <code>I</code> type.</p>
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings(value = "unchecked")
    public TimeFilter(I... ids) {
        super(ids);
    }

    /**
     * <code>TimeFilter</code>
     * <p>Instantiates a new time filter.</p>
     * @param builder {@link io.github.nichetoolkit.rice.filter.TimeFilter.Builder} <p>The builder parameter is <code>Builder</code> type.</p>
     * @see io.github.nichetoolkit.rice.filter.TimeFilter.Builder
     */
    public TimeFilter(TimeFilter.Builder<I, K> builder) {
        super(builder);
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;
    }

    /**
     * <code>getStartTime</code>
     * <p>The get start time getter method.</p>
     * @return {@link java.util.Date} <p>The get start time return object is <code>Date</code> type.</p>
     * @see java.util.Date
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * <code>setStartTime</code>
     * <p>The set start time setter method.</p>
     * @param startTime {@link java.util.Date} <p>The start time parameter is <code>Date</code> type.</p>
     * @see java.util.Date
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * <code>getEndTime</code>
     * <p>The get end time getter method.</p>
     * @return {@link java.util.Date} <p>The get end time return object is <code>Date</code> type.</p>
     * @see java.util.Date
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * <code>setEndTime</code>
     * <p>The set end time setter method.</p>
     * @param endTime {@link java.util.Date} <p>The end time parameter is <code>Date</code> type.</p>
     * @see java.util.Date
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * <code>toTimeSql</code>
     * <p>The to time sql method.</p>
     * @param alias {@link java.lang.String} <p>The alias parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.filter.TimeFilter} <p>The to time sql return object is <code>TimeFilter</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.lang.NonNull
     */
    public TimeFilter<I, K> toTimeSql(@NonNull String alias) {
        if (GeneralUtils.isNotEmpty(this.startTime) && GeneralUtils.isNotEmpty(this.endTime) && this.startTime == this.endTime) {
            SqlBuilders.equal(SQL_BUILDER, alias, this.startTime);
        } else {
            SqlBuilders.range(SQL_BUILDER, alias, this.startTime, this.endTime);
        }
        return this;
    }

    @Override
    public TimeFilter<I, K> toIdSql(@NonNull String alias) {
        super.toIdSql(alias);
        return this;
    }

    @Override
    public TimeFilter<I, K> toOperateSql(@NonNull String alias) {
        super.toOperateSql(alias);
        return this;
    }

    @Override
    public String toKey() {
        String nameKey = super.toKey();
        StringBuilder keyBuilder = new StringBuilder();
        if (GeneralUtils.isNotEmpty(this.startTime)) {
            keyBuilder.append(this.startTime).append(PageFilter.PAGE_REGEX);
        }
        if (GeneralUtils.isNotEmpty(this.endTime)) {
            keyBuilder.append(this.endTime).append(PageFilter.PAGE_REGEX);
        }
        keyBuilder.append(nameKey);
        return keyBuilder.toString();
    }

    /**
     * <code>Builder</code>
     * <p>The builder class.</p>
     * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rice.filter.IdFilter.Builder
     * @since Jdk1.8
     */
    public static class Builder<I, K> extends IdFilter.Builder<I, K> {
        /**
         * <code>startTime</code>
         * {@link java.util.Date} <p>The <code>startTime</code> field.</p>
         * @see java.util.Date
         */
        protected Date startTime;
        /**
         * <code>endTime</code>
         * {@link java.util.Date} <p>The <code>endTime</code> field.</p>
         * @see java.util.Date
         */
        protected Date endTime;

        /**
         * <code>Builder</code>
         * <p>Instantiates a new builder.</p>
         */
        public Builder() {
        }

        /**
         * <code>startTime</code>
         * <p>The start time method.</p>
         * @param startTime {@link java.util.Date} <p>The start time parameter is <code>Date</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.filter.TimeFilter.Builder} <p>The start time return object is <code>Builder</code> type.</p>
         * @see java.util.Date
         */
        public TimeFilter.Builder<I, K> startTime(Date startTime) {
            this.startTime = startTime;
            return this;
        }

        /**
         * <code>endTime</code>
         * <p>The end time method.</p>
         * @param endTime {@link java.util.Date} <p>The end time parameter is <code>Date</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.filter.TimeFilter.Builder} <p>The end time return object is <code>Builder</code> type.</p>
         * @see java.util.Date
         */
        public TimeFilter.Builder<I, K> endTime(Date endTime) {
            this.endTime = endTime;
            return this;
        }

        @Override
        public TimeFilter.Builder<I, K> ids(@NonNull Collection<I> ids) {
            this.ids = new HashSet<>(ids);
            return this;
        }

        @Override
        @SuppressWarnings(value = "unchecked")
        public TimeFilter.Builder<I, K> ids(@NonNull I... ids) {
            this.ids = new HashSet<>(Arrays.asList(ids));
            return this;
        }

        @Override
        public TimeFilter.Builder<I, K> tablekey(K tablekey) {
            this.tablekey = tablekey;
            return this;
        }

        @Override
        public TimeFilter.Builder<I, K> isRemove(boolean isRemove) {
            this.isRemove = isRemove;
            return this;
        }

        @Override
        public TimeFilter.Builder<I, K> operate(OperateType operate) {
            this.operate = operate;
            return this;
        }

        @Override
        public TimeFilter.Builder<I, K> operate(Integer operate) {
            this.operate = OperateType.parseKey(operate);
            return this;
        }

        @Override
        public TimeFilter.Builder<I, K> operates(@NonNull Collection<OperateType> operates) {
            this.operates = new HashSet<>(operates);
            return this;
        }

        @Override
        public TimeFilter.Builder<I, K> operates(@NonNull OperateType... operates) {
            this.operates = new HashSet<>(Arrays.asList(operates));
            return this;
        }

        @Override
        public TimeFilter.Builder<I, K> operates(@NonNull Integer... operates) {
            this.operates = new HashSet<>(RestOperate.build(operates));
            return this;
        }

        @Override
        public TimeFilter.Builder<I, K> sorts(@NonNull Collection<RestSort<?>> sorts) {
            this.sorts = new HashSet<>(sorts);
            return this;
        }

        @Override
        public TimeFilter.Builder<I, K> sorts(@NonNull RestSort<?>... sorts) {
            this.sorts = new HashSet<>(Arrays.asList(sorts));
            return this;
        }

        @Override
        public TimeFilter.Builder<I, K> sorts(@NonNull String... sorts) {
            this.sorts = new HashSet<>(RestSort.build(sorts));
            return this;
        }

        @Override
        public TimeFilter.Builder<I, K> pageNum(Integer pageNum) {
            this.pageNum = pageNum;
            return this;
        }

        @Override
        public TimeFilter.Builder<I, K> pageSize(Integer pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        @Override
        public TimeFilter<I, K> build() {
            return new TimeFilter<>(this);
        }
    }
}
