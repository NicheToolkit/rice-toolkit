package io.github.nichetoolkit.rice.time;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;

/**
 * <code>TimeInstant</code>
 * <p>The type time instant class.</p>
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
public class TimeInstant implements TimeValue {

    /**
     * <code>instant</code>
     * {@link java.time.Instant} <p>The <code>instant</code> field.</p>
     * @see java.time.Instant
     */
    private final Instant instant;

    /**
     * <code>TimeInstant</code>
     * <p>Instantiates a new time instant.</p>
     */
    public TimeInstant() {
        this.instant = Instant.now();
    }

    /**
     * <code>TimeInstant</code>
     * <p>Instantiates a new time instant.</p>
     * @param instant {@link java.time.Instant} <p>The instant parameter is <code>Instant</code> type.</p>
     * @see java.time.Instant
     */
    public TimeInstant(Instant instant) {
        this.instant = instant;
    }

    /**
     * <code>TimeInstant</code>
     * <p>Instantiates a new time instant.</p>
     * @param value {@link java.lang.Long} <p>The value parameter is <code>Long</code> type.</p>
     * @see java.lang.Long
     */
    public TimeInstant(Long value) {
        this.instant = new Date(value).toInstant();
    }

    /**
     * <code>TimeInstant</code>
     * <p>Instantiates a new time instant.</p>
     * @param value {@link java.util.Date} <p>The value parameter is <code>Date</code> type.</p>
     * @see java.util.Date
     */
    public TimeInstant(Date value) {
        this.instant = value.toInstant();
    }

    /**
     * <code>now</code>
     * <p>The method.</p>
     * @return {@link io.github.nichetoolkit.rice.time.TimeInstant} <p>The return object is <code>TimeInstant</code> type.</p>
     */
    public static TimeInstant now() {
        return new TimeInstant(Instant.now());
    }

    /**
     * <code>parse</code>
     * <p>The method.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.time.TimeInstant} <p>The return object is <code>TimeInstant</code> type.</p>
     * @see java.lang.String
     */
    public static TimeInstant parse(String value) {
        return new TimeInstant(Instant.parse(value));
    }

    /**
     * <code>create</code>
     * <p>The method.</p>
     * @param value {@link java.lang.Long} <p>The value parameter is <code>Long</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.time.TimeInstant} <p>The return object is <code>TimeInstant</code> type.</p>
     * @see java.lang.Long
     */
    public static TimeInstant create(Long value) {
        return new TimeInstant(new Date(value).toInstant());
    }

    /**
     * <code>create</code>
     * <p>The method.</p>
     * @param value {@link java.util.Date} <p>The value parameter is <code>Date</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.time.TimeInstant} <p>The return object is <code>TimeInstant</code> type.</p>
     * @see java.util.Date
     */
    public static TimeInstant create(Date value) {
        return new TimeInstant(value.toInstant());
    }

    /**
     * <code>getInstant</code>
     * <p>The instant getter method.</p>
     * @return {@link java.time.Instant} <p>The instant return object is <code>Instant</code> type.</p>
     * @see java.time.Instant
     */
    public Instant getInstant() {
        return instant;
    }

    /**
     * <code>getDate</code>
     * <p>The date getter method.</p>
     * @return {@link java.util.Date} <p>The date return object is <code>Date</code> type.</p>
     * @see java.util.Date
     */
    public Date getDate() {
        return Date.from(this.instant);
    }

    /**
     * <code>getTime</code>
     * <p>The time getter method.</p>
     * @return {@link java.lang.Long} <p>The time return object is <code>Long</code> type.</p>
     * @see java.lang.Long
     */
    public Long getTime() {
        return this.getDate().getTime();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TimeInstant)) return false;
        TimeInstant that = (TimeInstant) o;
        return Objects.equals(instant, that.instant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(instant);
    }

    @Override
    public String toString() {
        return this.instant.toString();
    }

    @Override
    public String format() {
        return this.toString();
    }

    @Override
    public Long id() {
        return this.instant.getEpochSecond();
    }
}
