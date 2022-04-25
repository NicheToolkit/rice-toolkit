package io.github.nichetoolkit.rice.filter;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.RestOperate;
import io.github.nichetoolkit.rice.RestSort;
import io.github.nichetoolkit.rice.builder.SqlBuilders;
import io.github.nichetoolkit.rice.enums.OperateType;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

/**
 * <p>TimeFilter</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@SuppressWarnings("WeakerAccess")
public class TimeFilter<D,I> extends IdFilter<I> {

    /** default like '2020-01-01 00:00:00' */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected D startTime;
    /** default like '2020-01-02 00:00:00' */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected D endTime;

    public TimeFilter() {
    }

    public TimeFilter(I id) {
        super(id);
    }

    @SuppressWarnings(value = "unchecked")
    public TimeFilter(I... ids) {
        super(ids);
    }

    public TimeFilter(TimeFilter.Builder<D,I> builder) {
        super(builder);
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;
    }

    public D getStartTime() {
        return startTime;
    }

    public void setStartTime(D startTime) {
        this.startTime = startTime;
    }

    public D getEndTime() {
        return endTime;
    }

    public void setEndTime(D endTime) {
        this.endTime = endTime;
    }

    public TimeFilter<D,I> toTimeSql(@NonNull String alias) {
        if (GeneralUtils.isNotEmpty(this.startTime) && GeneralUtils.isNotEmpty(this.endTime) && this.startTime == this.endTime ) {
            SqlBuilders.equal(SQL_BUILDER, alias, this.startTime);
        } else {
            SqlBuilders.range(SQL_BUILDER, alias, this.startTime, this.endTime);
        }
        return this;
    }

    @Override
    public TimeFilter<D,I> toIdSql(@NonNull String alias) {
        super.toIdSql(alias);
        return this;
    }

    @Override
    public TimeFilter<D,I> toOperateSql(@NonNull String alias) {
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

    public static class Builder<D,I> extends IdFilter.Builder<I> {
        protected D startTime;
        protected D endTime;

        public Builder() {
        }

        public TimeFilter.Builder<D,I> startTime(D startTime) {
            this.startTime = startTime;
            return this;
        }

        public TimeFilter.Builder<D,I> endTime(D endTime) {
            this.endTime = endTime;
            return this;
        }

        @Override
        public TimeFilter.Builder<D,I> ids(@NonNull Collection<I> ids) {
            this.ids = new HashSet<>(ids);
            return this;
        }

        @Override
        @SuppressWarnings(value = "unchecked")
        public TimeFilter.Builder<D,I> ids(@NonNull I... ids) {
            this.ids = new HashSet<>(Arrays.asList(ids));
            return this;
        }

        @Override
        public TimeFilter.Builder<D,I> isRemove(boolean isRemove) {
            this.isRemove = isRemove;
            return this;
        }

        @Override
        public TimeFilter.Builder<D,I> operate(OperateType operate) {
            this.operate = operate;
            return this;
        }

        @Override
        public TimeFilter.Builder<D,I> operate(Integer operate) {
            this.operate = OperateType.parseKey(operate);
            return this;
        }

        @Override
        public TimeFilter.Builder<D,I> operates(@NonNull Collection<OperateType> operates) {
            this.operates = new HashSet<>(operates);
            return this;
        }

        @Override
        public TimeFilter.Builder<D,I> operates(@NonNull OperateType... operates) {
            this.operates = new HashSet<>(Arrays.asList(operates));
            return this;
        }

        @Override
        public TimeFilter.Builder<D,I> operates(@NonNull Integer... operates) {
            this.operates = new HashSet<>(RestOperate.build(operates));
            return this;
        }

        @Override
        public TimeFilter.Builder<D,I> sorts(@NonNull Collection<RestSort> sorts) {
            this.sorts = new HashSet<>(sorts);
            return this;
        }

        @Override
        public TimeFilter.Builder<D,I> sorts(@NonNull RestSort... sorts) {
            this.sorts = new HashSet<>(Arrays.asList(sorts));
            return this;
        }

        @Override
        public TimeFilter.Builder sorts(@NonNull String... sorts) {
            this.sorts = new HashSet<>(RestSort.build(sorts));
            return this;
        }

        @Override
        public TimeFilter.Builder<D,I> pageNum(Integer pageNum) {
            this.pageNum = pageNum;
            return this;
        }

        @Override
        public TimeFilter.Builder<D,I> pageSize(Integer pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        @Override
        public TimeFilter<D,I> build() {
            return new TimeFilter<>(this);
        }
    }
}
