package io.github.nichetoolkit.rice.time;

import io.github.nichetoolkit.rest.util.GeneralUtils;
import org.springframework.lang.NonNull;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

/**
 * <p>TimeValue</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public interface TimeValue<S extends TimeValue<S>> {
    String INTERVAL_REGEX = "/";
    String DURATION_START = "P";

    Long id();
    /** 字符串类型 */
    String format();

    static TimeValue parse(@NonNull String timeValueText) {
        if (timeValueText.contains(INTERVAL_REGEX) || timeValueText.startsWith(DURATION_START)) {
            return TimeInterval.parse(timeValueText);
        } else {
            return TimeInstant.parse(timeValueText);
        }
    }

    static Long getTime(@NonNull TimeValue timeValue) {
        return getStartTime(timeValue).getTime();
    }

    static Date getStartTime(@NonNull TimeValue timeValue) {
        if (timeValue instanceof TimeInstant) {
            return ((TimeInstant) timeValue).getDate();
        } else {
            Instant start = ((TimeInterval) timeValue).getStart();
            return Date.from(start);
        }
    }

    static Date getEndTime(@NonNull TimeValue timeValue) {
        if (timeValue instanceof TimeInstant) {
            return ((TimeInstant) timeValue).getDate();
        } else {
            Instant start = ((TimeInterval) timeValue).getEnd();
            return Date.from(start);
        }
    }

    static TimeInterval interval(@NonNull Date start, @NonNull Date end) {
        Duration duration = Duration.between(start.toInstant(), end.toInstant());
        if (duration.isNegative()) {
            return null;
        } else {
            return TimeInterval.create(start, end);
        }
    }

    static TimeInstant instant(@NonNull Date time) {
        return TimeInstant.create(time);
    }

    static TimeValue value(@NonNull Date start, Date end) {
        if (GeneralUtils.isEmpty(end) || end.equals(start)) {
            return instant(start);
        }
        return interval(start, end);
    }
}
