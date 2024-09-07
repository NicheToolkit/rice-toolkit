package io.github.nichetoolkit.rice.enums;

import io.github.nichetoolkit.rest.RestStereo;
import io.github.nichetoolkit.rest.stereotype.StereoEntry;

import java.lang.annotation.Annotation;

/**
 * <p>RoleType</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public enum RoleType implements RestStereo {
    A("a",1L),
    B("b",2L),
    ;

    private final String key;

    private final Long value;

    RoleType(String key,Long value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public Long getValue() {
        return this.value;
    }

    @Override
    public String getKey() {
        return this.key;
    }


    @Override
    public Class<? extends Annotation> annotationType() {
        return StereoEntry.class;
    }
}
