package io.github.nichetoolkit.rice.time;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.rest.util.GeneralUtils;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@SuppressWarnings("WeakerAccess")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TimeInterval implements TimeValue {
    private Instant start;
    private Instant end;
    private Duration duration;

    public TimeInterval() {
    }

    public TimeInterval(Long start, Long end) {
        this.start = new Date(start).toInstant();
        this.end = new Date(end).toInstant();
        this.duration = Duration.between(this.start, this.end);
    }

    public TimeInterval(Date start, Date end) {
        this.start = start.toInstant();
        this.end = end.toInstant();
        this.duration = Duration.between(this.start, this.end);
    }

    public TimeInterval(Instant start, Instant end) {
        this.start = start;
        this.end = end;
        this.duration = Duration.between(this.start, this.end);
    }

    public TimeInterval(Instant start, Instant end, Duration duration) {
        this.start = start;
        this.end = end;
        this.duration = duration;
    }

    public static TimeInterval now() {
        return create(TimeInstant.now(), TimeInstant.now());
    }

    public static TimeInterval create(Long start, Long end) {
        return new TimeInterval(start, end);
    }

    public static TimeInterval create(Date start, Date end) {
        return new TimeInterval(start, end);
    }


    public static TimeInterval create(Instant start, Instant end) {
        return new TimeInterval(start, end);
    }

    public static TimeInterval create(TimeInstant start, TimeInstant end) {
        return create(start.getInstant(), end.getInstant());
    }

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

    public Instant getStart() {
        return start;
    }

    public void setStart(Instant start) {
        this.start = start;
    }

    public Instant getEnd() {
        return end;
    }

    public void setEnd(Instant end) {
        this.end = end;
    }

    public Duration getDuration() {
        return duration;
    }

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
