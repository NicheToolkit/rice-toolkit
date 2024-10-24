package io.github.nichetoolkit.rice.time;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.rest.util.GeneralUtils;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

/**
 * <code>TimeInterval</code>
 * <p>The time interval class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.time.TimeValue
 * @see java.lang.SuppressWarnings
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk1.8
 */
@SuppressWarnings("WeakerAccess")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TimeInterval implements TimeValue {
    /**
     * <code>start</code>
     * {@link java.time.Instant} <p>The <code>start</code> field.</p>
     * @see java.time.Instant
     */
    private Instant start;
    /**
     * <code>end</code>
     * {@link java.time.Instant} <p>The <code>end</code> field.</p>
     * @see java.time.Instant
     */
    private Instant end;
    /**
     * <code>duration</code>
     * {@link java.time.Duration} <p>The <code>duration</code> field.</p>
     * @see java.time.Duration
     */
    private Duration duration;

    /**
     * <code>TimeInterval</code>
     * <p>Instantiates a new time interval.</p>
     */
    public TimeInterval() {
    }

    /**
     * <code>TimeInterval</code>
     * <p>Instantiates a new time interval.</p>
     * @param start {@link java.lang.Long} <p>The start parameter is <code>Long</code> type.</p>
     * @param end   {@link java.lang.Long} <p>The end parameter is <code>Long</code> type.</p>
     * @see java.lang.Long
     */
    public TimeInterval(Long start, Long end) {
        this.start = new Date(start).toInstant();
        this.end = new Date(end).toInstant();
        this.duration = Duration.between(this.start, this.end);
    }

    /**
     * <code>TimeInterval</code>
     * <p>Instantiates a new time interval.</p>
     * @param start {@link java.util.Date} <p>The start parameter is <code>Date</code> type.</p>
     * @param end   {@link java.util.Date} <p>The end parameter is <code>Date</code> type.</p>
     * @see java.util.Date
     */
    public TimeInterval(Date start, Date end) {
        this.start = start.toInstant();
        this.end = end.toInstant();
        this.duration = Duration.between(this.start, this.end);
    }

    /**
     * <code>TimeInterval</code>
     * <p>Instantiates a new time interval.</p>
     * @param start {@link java.time.Instant} <p>The start parameter is <code>Instant</code> type.</p>
     * @param end   {@link java.time.Instant} <p>The end parameter is <code>Instant</code> type.</p>
     * @see java.time.Instant
     */
    public TimeInterval(Instant start, Instant end) {
        this.start = start;
        this.end = end;
        this.duration = Duration.between(this.start, this.end);
    }

    /**
     * <code>TimeInterval</code>
     * <p>Instantiates a new time interval.</p>
     * @param start    {@link java.time.Instant} <p>The start parameter is <code>Instant</code> type.</p>
     * @param end      {@link java.time.Instant} <p>The end parameter is <code>Instant</code> type.</p>
     * @param duration {@link java.time.Duration} <p>The duration parameter is <code>Duration</code> type.</p>
     * @see java.time.Instant
     * @see java.time.Duration
     */
    public TimeInterval(Instant start, Instant end, Duration duration) {
        this.start = start;
        this.end = end;
        this.duration = duration;
    }

    /**
     * <code>now</code>
     * <p>The now method.</p>
     * @return {@link io.github.nichetoolkit.rice.time.TimeInterval} <p>The now return object is <code>TimeInterval</code> type.</p>
     */
    public static TimeInterval now() {
        return create(TimeInstant.now(), TimeInstant.now());
    }

    /**
     * <code>create</code>
     * <p>The create method.</p>
     * @param start {@link java.lang.Long} <p>The start parameter is <code>Long</code> type.</p>
     * @param end   {@link java.lang.Long} <p>The end parameter is <code>Long</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.time.TimeInterval} <p>The create return object is <code>TimeInterval</code> type.</p>
     * @see java.lang.Long
     */
    public static TimeInterval create(Long start, Long end) {
        return new TimeInterval(start, end);
    }

    /**
     * <code>create</code>
     * <p>The create method.</p>
     * @param start {@link java.util.Date} <p>The start parameter is <code>Date</code> type.</p>
     * @param end   {@link java.util.Date} <p>The end parameter is <code>Date</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.time.TimeInterval} <p>The create return object is <code>TimeInterval</code> type.</p>
     * @see java.util.Date
     */
    public static TimeInterval create(Date start, Date end) {
        return new TimeInterval(start, end);
    }


    /**
     * <code>create</code>
     * <p>The create method.</p>
     * @param start {@link java.time.Instant} <p>The start parameter is <code>Instant</code> type.</p>
     * @param end   {@link java.time.Instant} <p>The end parameter is <code>Instant</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.time.TimeInterval} <p>The create return object is <code>TimeInterval</code> type.</p>
     * @see java.time.Instant
     */
    public static TimeInterval create(Instant start, Instant end) {
        return new TimeInterval(start, end);
    }

    /**
     * <code>create</code>
     * <p>The create method.</p>
     * @param start {@link io.github.nichetoolkit.rice.time.TimeInstant} <p>The start parameter is <code>TimeInstant</code> type.</p>
     * @param end   {@link io.github.nichetoolkit.rice.time.TimeInstant} <p>The end parameter is <code>TimeInstant</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.time.TimeInterval} <p>The create return object is <code>TimeInterval</code> type.</p>
     * @see io.github.nichetoolkit.rice.time.TimeInstant
     */
    public static TimeInterval create(TimeInstant start, TimeInstant end) {
        return create(start.getInstant(), end.getInstant());
    }

    /**
     * <code>parse</code>
     * <p>The parse method.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.time.TimeInterval} <p>The parse return object is <code>TimeInterval</code> type.</p>
     * @see java.lang.String
     */
    public static TimeInterval parse(String value) {
        Instant start = null;
        Instant end = null;
        Duration duration = null;
        if (value.contains(INTERVAL_REGEX)) {
            String[] split = value.split(INTERVAL_REGEX,-1);
            if (split.length > 0) {
                start = Instant.parse(split[0]);
            }
            if (split.length > 1) {
                end = Instant.parse(split[1]);
            }
            if (split.length > 2 && split[2].startsWith(DURATION_START)) {
                duration = Duration.parse(split[2]);
            }
        } else if (value.startsWith(DURATION_START)) {
            duration = Duration.parse(value);
        }
        return new TimeInterval(start, end, duration);
    }

    /**
     * <code>getStart</code>
     * <p>The get start getter method.</p>
     * @return {@link java.time.Instant} <p>The get start return object is <code>Instant</code> type.</p>
     * @see java.time.Instant
     */
    public Instant getStart() {
        return start;
    }

    /**
     * <code>setStart</code>
     * <p>The set start setter method.</p>
     * @param start {@link java.time.Instant} <p>The start parameter is <code>Instant</code> type.</p>
     * @see java.time.Instant
     */
    public void setStart(Instant start) {
        this.start = start;
    }

    /**
     * <code>getEnd</code>
     * <p>The get end getter method.</p>
     * @return {@link java.time.Instant} <p>The get end return object is <code>Instant</code> type.</p>
     * @see java.time.Instant
     */
    public Instant getEnd() {
        return end;
    }

    /**
     * <code>setEnd</code>
     * <p>The set end setter method.</p>
     * @param end {@link java.time.Instant} <p>The end parameter is <code>Instant</code> type.</p>
     * @see java.time.Instant
     */
    public void setEnd(Instant end) {
        this.end = end;
    }

    /**
     * <code>getDuration</code>
     * <p>The get duration getter method.</p>
     * @return {@link java.time.Duration} <p>The get duration return object is <code>Duration</code> type.</p>
     * @see java.time.Duration
     */
    public Duration getDuration() {
        return duration;
    }

    /**
     * <code>setDuration</code>
     * <p>The set duration setter method.</p>
     * @param duration {@link java.time.Duration} <p>The duration parameter is <code>Duration</code> type.</p>
     * @see java.time.Duration
     */
    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    @Override
    public Long id() {
        return this.start.getEpochSecond();
    }

    @Override
    public String toString() {
        return this.duration.toString();
    }

    @Override
    public String format() {
        if (GeneralUtils.isEmpty(this.start) || GeneralUtils.isEmpty(this.end)) {
            return this.toString();
        } else if (GeneralUtils.isEmpty(this.duration)) {
            return this.start.toString() + INTERVAL_REGEX + this.end.toString();
        } else {
            return this.start.toString() + INTERVAL_REGEX + this.end.toString() + INTERVAL_REGEX + this.duration.toString();
        }
    }


}
