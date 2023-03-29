package io.github.nichetoolkit.rice.time;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;

/**
 * <p>TimeInstant</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@SuppressWarnings("WeakerAccess")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TimeInstant implements TimeValue<TimeInstant> {
    
    private final Instant instant;

    public TimeInstant() {
        this.instant = Instant.now();
    }

    public TimeInstant(Instant instant) {
        this.instant = instant;
    }

    public TimeInstant(Long value) {
        this.instant = new Date(value).toInstant();
    }

    public TimeInstant(Date value) {
        this.instant = value.toInstant();
    }

    public static TimeInstant now() {
        return new TimeInstant(Instant.now());
    }

    public static TimeInstant parse(String value) {
        return new TimeInstant(Instant.parse(value));
    }

    public static TimeInstant create(Long value) {
        return new TimeInstant(new Date(value).toInstant());
    }

    public static TimeInstant create(Date value) {
        return new TimeInstant(value.toInstant());
    }

    public Instant getInstant() {
        return instant;
    }

    public Date getDate() {
        return Date.from(this.instant);
    }

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
