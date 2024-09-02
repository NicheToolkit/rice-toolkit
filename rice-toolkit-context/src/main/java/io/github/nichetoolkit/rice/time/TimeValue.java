package io.github.nichetoolkit.rice.time;

import io.github.nichetoolkit.rest.util.GeneralUtils;
import org.springframework.lang.NonNull;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

/**
 * <code>TimeValue</code>
 * <p>The type time value interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface TimeValue {
    /**
     * <code>INTERVAL_REGEX</code>
     * {@link java.lang.String} <p>the constant <code>INTERVAL_REGEX</code> field.</p>
     * @see java.lang.String
     */
    String INTERVAL_REGEX = "/";
    /**
     * <code>DURATION_START</code>
     * {@link java.lang.String} <p>the constant <code>DURATION_START</code> field.</p>
     * @see java.lang.String
     */
    String DURATION_START = "P";

    /**
     * <code>id</code>
     * <p>the method.</p>
     * @return {@link java.lang.Long} <p>the return object is <code>Long</code> type.</p>
     * @see java.lang.Long
     */
    Long id();

    /**
     * <code>format</code>
     * <p>the method.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    String format();

    /**
     * <code>parse</code>
     * <p>the method.</p>
     * @param timeValueText {@link java.lang.String} <p>the time value text parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.time.TimeValue} <p>the return object is <code>TimeValue</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.lang.NonNull
     */
    static TimeValue parse(@NonNull String timeValueText) {
        if (timeValueText.contains(INTERVAL_REGEX) || timeValueText.startsWith(DURATION_START)) {
            return TimeInterval.parse(timeValueText);
        } else {
            return TimeInstant.parse(timeValueText);
        }
    }

    /**
     * <code>getTime</code>
     * <p>the time getter method.</p>
     * @param timeValue {@link io.github.nichetoolkit.rice.time.TimeValue} <p>the time value parameter is <code>TimeValue</code> type.</p>
     * @return {@link java.lang.Long} <p>the time return object is <code>Long</code> type.</p>
     * @see org.springframework.lang.NonNull
     * @see java.lang.Long
     */
    static Long getTime(@NonNull TimeValue timeValue) {
        return getStartTime(timeValue).getTime();
    }

    /**
     * <code>getStartTime</code>
     * <p>the start time getter method.</p>
     * @param timeValue {@link io.github.nichetoolkit.rice.time.TimeValue} <p>the time value parameter is <code>TimeValue</code> type.</p>
     * @return {@link java.util.Date} <p>the start time return object is <code>Date</code> type.</p>
     * @see org.springframework.lang.NonNull
     * @see java.util.Date
     */
    static Date getStartTime(@NonNull TimeValue timeValue) {
        if (timeValue instanceof TimeInstant) {
            return ((TimeInstant) timeValue).getDate();
        } else {
            Instant start = ((TimeInterval) timeValue).getStart();
            return Date.from(start);
        }
    }

    /**
     * <code>getEndTime</code>
     * <p>the end time getter method.</p>
     * @param timeValue {@link io.github.nichetoolkit.rice.time.TimeValue} <p>the time value parameter is <code>TimeValue</code> type.</p>
     * @return {@link java.util.Date} <p>the end time return object is <code>Date</code> type.</p>
     * @see org.springframework.lang.NonNull
     * @see java.util.Date
     */
    static Date getEndTime(@NonNull TimeValue timeValue) {
        if (timeValue instanceof TimeInstant) {
            return ((TimeInstant) timeValue).getDate();
        } else {
            Instant start = ((TimeInterval) timeValue).getEnd();
            return Date.from(start);
        }
    }

    /**
     * <code>interval</code>
     * <p>the method.</p>
     * @param start {@link java.util.Date} <p>the start parameter is <code>Date</code> type.</p>
     * @param end   {@link java.util.Date} <p>the end parameter is <code>Date</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.time.TimeInterval} <p>the return object is <code>TimeInterval</code> type.</p>
     * @see java.util.Date
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rice.time.TimeInterval
     */
    static TimeInterval interval(@NonNull Date start, @NonNull Date end) {
        Duration duration = Duration.between(start.toInstant(), end.toInstant());
        if (duration.isNegative()) {
            return null;
        } else {
            return TimeInterval.create(start, end);
        }
    }

    /**
     * <code>instant</code>
     * <p>the method.</p>
     * @param time {@link java.util.Date} <p>the time parameter is <code>Date</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.time.TimeInstant} <p>the return object is <code>TimeInstant</code> type.</p>
     * @see java.util.Date
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rice.time.TimeInstant
     */
    static TimeInstant instant(@NonNull Date time) {
        return TimeInstant.create(time);
    }

    /**
     * <code>value</code>
     * <p>the method.</p>
     * @param start {@link java.util.Date} <p>the start parameter is <code>Date</code> type.</p>
     * @param end   {@link java.util.Date} <p>the end parameter is <code>Date</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.time.TimeValue} <p>the return object is <code>TimeValue</code> type.</p>
     * @see java.util.Date
     * @see org.springframework.lang.NonNull
     */
    static TimeValue value(@NonNull Date start, Date end) {
        if (GeneralUtils.isEmpty(end) || end.equals(start)) {
            return instant(start);
        }
        return interval(start, end);
    }
}
