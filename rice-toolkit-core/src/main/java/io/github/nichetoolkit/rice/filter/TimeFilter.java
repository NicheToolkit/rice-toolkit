package io.github.nichetoolkit.rice.filter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.builder.SqlBuilders;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;
import org.jspecify.annotations.NonNull;

import java.util.Date;

/**
 * <code>TimeFilter</code>
 * <p>The time filter class.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @param <K> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.filter.IdFilter
 * @see lombok.Setter
 * @see lombok.Getter
 * @see lombok.experimental.SuperBuilder
 * @see java.lang.SuppressWarnings
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk17
 */
@Setter
@Getter
@SuperBuilder(builderMethodName = "ofTimeBuilder")
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
     * <code>toTimeSql</code>
     * <p>The to time sql method.</p>
     * @param alias {@link java.lang.String} <p>The alias parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.filter.TimeFilter} <p>The to time sql return object is <code>TimeFilter</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.jspecify.annotations.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    public TimeFilter<I, K> toTimeSql(@NonNull String alias) throws RestException {
        if (GeneralUtils.isNotEmpty(this.startTime) && GeneralUtils.isNotEmpty(this.endTime) && this.startTime == this.endTime) {
            SqlBuilders.equal(sqlBuilder(), alias, this.startTime);
        } else {
            SqlBuilders.range(sqlBuilder(), alias, this.startTime, this.endTime);
        }
        return this;
    }

    @Override
    public TimeFilter<I, K> toIdSql(@NonNull String alias) throws RestException {
        super.toIdSql(alias);
        return this;
    }

    @Override
    public TimeFilter<I, K> toOperateSql(@NonNull String alias) throws RestException {
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

}
