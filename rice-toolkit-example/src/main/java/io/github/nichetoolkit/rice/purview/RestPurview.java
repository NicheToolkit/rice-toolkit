package io.github.nichetoolkit.rice.purview;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestOptional;
import io.github.nichetoolkit.rest.stream.RestStream;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;
import java.util.*;

/**
 * <code>RestPurview</code>
 * <p>The type rest purview interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.lang.annotation.Annotation
 * @see java.lang.annotation.Target
 * @see java.lang.annotation.Retention
 * @see java.lang.annotation.Documented
 * @see org.springframework.stereotype.Indexed
 * @since Jdk1.8
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
public @interface RestPurview {

    /**
     * <code>key</code>
     * <p>The method.</p>
     * @return {@link java.lang.String} <p>The return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    String key() default "";

    /**
     * <code>keys</code>
     * <p>The method.</p>
     * @return {@link java.lang.String} <p>The return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    String[] keys() default {};

    /**
     * <code>value</code>
     * <p>The method.</p>
     * @return long <p>The return object is <code>long</code> type.</p>
     */
    long value() default 0L;

    /**
     * <code>values</code>
     * <p>The method.</p>
     * @return long <p>The return object is <code>long</code> type.</p>
     */
    long[] values() default {};

    /**
     * <code>purview</code>
     * <p>The method.</p>
     * @return {@link io.github.nichetoolkit.rice.purview.PurviewType} <p>The return object is <code>PurviewType</code> type.</p>
     * @see io.github.nichetoolkit.rice.purview.PurviewType
     */
    PurviewType purview() default PurviewType.PURVIEW_ALL;

    /**
     * <code>purviews</code>
     * <p>The method.</p>
     * @return {@link io.github.nichetoolkit.rice.purview.PurviewType} <p>The return object is <code>PurviewType</code> type.</p>
     * @see io.github.nichetoolkit.rice.purview.PurviewType
     */
    PurviewType[] purviews() default {};

    /**
     * <code>Purview</code>
     * <p>The type purview class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk1.8
     */
    final class Purview {

        /**
         * <code>keys</code>
         * <p>The method.</p>
         * @param purview {@link io.github.nichetoolkit.rice.purview.RestPurview} <p>The purview parameter is <code>RestPurview</code> type.</p>
         * @return {@link java.util.List} <p>The return object is <code>List</code> type.</p>
         * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
         * @see org.springframework.lang.NonNull
         * @see java.util.List
         * @see io.github.nichetoolkit.rest.RestException
         */
        public static List<String> keys(@NonNull RestPurview purview) throws RestException {
            Set<String> keySet = new HashSet<>();
            RestOptional.ofEmptyable(purview.key()).ifEmptyPresent(keySet::add);
            RestOptional.ofEmptyable(purview.keys()).ifEmptyPresent(keys -> keySet.addAll(Arrays.asList(keys)));
            RestOptional.ofNullable(purview.purview()).nullFlatMap(value -> RestOptional.ofEmptyable(value.getKey())).emptyMap(keySet::add);
            RestOptional.ofNullable(purview.purviews()).ifNullPresent(values -> RestStream.stream(values).forEach(value -> RestOptional.ofNullable(value).nullFlatMap(module -> RestOptional.ofEmptyable(module.getKey())).emptyMap(keySet::add)));
            return new ArrayList<>(keySet);
        }

        /**
         * <code>values</code>
         * <p>The method.</p>
         * @param purview {@link io.github.nichetoolkit.rice.purview.RestPurview} <p>The purview parameter is <code>RestPurview</code> type.</p>
         * @return {@link java.util.List} <p>The return object is <code>List</code> type.</p>
         * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
         * @see org.springframework.lang.NonNull
         * @see java.util.List
         * @see io.github.nichetoolkit.rest.RestException
         */
        public static List<Long> values(@NonNull RestPurview purview) throws RestException {
            Set<Long> valueSet = new HashSet<>();
            RestOptional.ofEmptyable(purview.value()).ifEmptyPresent(valueSet::add);
            RestOptional.ofEmptyable(purview.values()).ifEmptyPresent(values -> Arrays.stream(values).forEach(valueSet::add));
            RestOptional.ofNullable(purview.purview()).nullFlatMap(value -> RestOptional.ofEmptyable(value.getValue())).emptyMap(valueSet::add);
            RestOptional.ofNullable(purview.purviews()).ifNullPresent(values -> RestStream.stream(values).forEach(value -> RestOptional.ofNullable(value).nullFlatMap(module -> RestOptional.ofEmptyable(module.getValue())).emptyMap(valueSet::add)));
            return new ArrayList<>(valueSet);
        }

    }

}
