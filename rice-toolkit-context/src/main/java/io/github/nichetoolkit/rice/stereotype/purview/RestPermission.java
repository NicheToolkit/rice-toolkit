package io.github.nichetoolkit.rice.stereotype.purview;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.stereotype.StereoValue;
import io.github.nichetoolkit.rest.stream.RestStream;
import io.github.nichetoolkit.rest.util.OptionalUtils;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;
import java.util.*;

@Target({ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
public @interface RestPermission {

    String key() default "";

    String[] keys() default {};

    long value() default 0L;

    long[] values() default {};

    StereoValue permission() default @StereoValue;

    StereoValue[] permissions() default {};

    class Permission {

        static List<String> keys(@NonNull RestPermission permission) throws RestException {
            Set<String> keySet = new HashSet<>();
            OptionalUtils.ofEmptyable(permission.key()).ifEmptyPresent(keySet::add);
            OptionalUtils.ofEmptyable(permission.keys()).ifEmptyPresent(keys -> keySet.addAll(Arrays.asList(keys)));
            OptionalUtils.ofNullable(permission.permission()).nullFlatMap(value -> OptionalUtils.ofEmptyable(value.key())).emptyMap(keySet::add);
            OptionalUtils.ofNullable(permission.permissions()).ifNullPresent(values -> {
                RestStream.stream(values).forEach(value -> OptionalUtils.ofNullable(value).nullFlatMap(module -> OptionalUtils.ofEmptyable(module.key())).emptyMap(keySet::add));
            });
            return new ArrayList<>(keySet);
        }

        static List<Long> values(@NonNull RestPermission permission) throws RestException {
            Set<Long> valueSet = new HashSet<>();
            OptionalUtils.ofEmptyable(permission.value()).ifEmptyPresent(valueSet::add);
            OptionalUtils.ofEmptyable(permission.values()).ifEmptyPresent(values -> Arrays.stream(values).forEach(valueSet::add));
            OptionalUtils.ofNullable(permission.permission()).nullFlatMap(value -> OptionalUtils.ofEmptyable(value.value())).emptyMap(valueSet::add);
            OptionalUtils.ofNullable(permission.permissions()).ifNullPresent(values -> {
                RestStream.stream(values).forEach(value -> OptionalUtils.ofNullable(value).nullFlatMap(module -> OptionalUtils.ofEmptyable(module.value())).emptyMap(valueSet::add));
            });
            return new ArrayList<>(valueSet);
        }

    }

}
