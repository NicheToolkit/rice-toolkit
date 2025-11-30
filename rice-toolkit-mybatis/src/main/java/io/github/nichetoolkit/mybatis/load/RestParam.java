package io.github.nichetoolkit.mybatis.load;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.rest.RestEntry;
import io.github.nichetoolkit.rest.RestValue;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.Setter;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;

/**
 * <code>RestParam</code>
 * <p>The rest param interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestValue
 * @see io.github.nichetoolkit.rest.RestEntry
 * @see java.io.Serializable
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk17
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public interface RestParam extends RestValue<String, Object>, RestEntry<String, Object>, Serializable {

    /**
     * <code>of</code>
     * <p>The of method.</p>
     * @param key   {@link java.lang.String} <p>The key parameter is <code>String</code> type.</p>
     * @param value {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @return {@link io.github.nichetoolkit.mybatis.load.RestParam} <p>The of return object is <code>RestParam</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    static RestParam of(String key, Object value) {
        return new RestParam.OfRestParam(key, value);
    }

    /**
     * <code>of</code>
     * <p>The of method.</p>
     * @param entry {@link java.util.Map.Entry} <p>The entry parameter is <code>Entry</code> type.</p>
     * @return {@link io.github.nichetoolkit.mybatis.load.RestParam} <p>The of return object is <code>RestParam</code> type.</p>
     * @see java.util.Map.Entry
     */
    static RestParam of(Map.Entry<String, Object> entry) {
        return new RestParam.OfRestParam(entry);
    }

    /**
     * <code>ofArray</code>
     * <p>The of array method.</p>
     * @param entries {@link java.util.Map.Entry} <p>The entries parameter is <code>Entry</code> type.</p>
     * @return {@link io.github.nichetoolkit.mybatis.load.RestParam} <p>The of array return object is <code>RestParam</code> type.</p>
     * @see java.util.Map.Entry
     * @see java.lang.SafeVarargs
     */
    @SafeVarargs
    static RestParam[] ofArray(Map.Entry<String, Object>... entries) {
        if (GeneralUtils.isEmpty(entries)) {
            return null;
        }
        return Arrays.stream(entries).map(RestParam.OfRestParam::new).toArray(RestParam[]::new);
    }

    /**
     * <code>ofArray</code>
     * <p>The of array method.</p>
     * @param map {@link java.util.Map} <p>The map parameter is <code>Map</code> type.</p>
     * @return {@link io.github.nichetoolkit.mybatis.load.RestParam} <p>The of array return object is <code>RestParam</code> type.</p>
     * @see java.util.Map
     */
    static RestParam[] ofArray(Map<String, Object> map) {
        if (GeneralUtils.isEmpty(map)) {
            return null;
        }
        return map.entrySet().stream().map(RestParam.OfRestParam::new).toArray(RestParam[]::new);
    }

    /**
     * <code>ofNull</code>
     * <p>The of null method.</p>
     * @return {@link io.github.nichetoolkit.mybatis.load.RestParam} <p>The of null return object is <code>RestParam</code> type.</p>
     */
    static RestParam ofNull() {
        return new RestParam.OfRestParam();
    }

    /**
     * <code>OfRestParam</code>
     * <p>The of rest param class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rest.RestValue.OfRestValue
     * @see lombok.Setter
     * @since Jdk17
     */
    @Setter
    class OfRestParam extends OfRestValue<String, Object> implements RestParam {

        /**
         * <code>OfRestParam</code>
         * <p>Instantiates a new of rest param.</p>
         */
        public OfRestParam() {
        }

        /**
         * <code>OfRestParam</code>
         * <p>Instantiates a new of rest param.</p>
         * @param key   {@link java.lang.String} <p>The key parameter is <code>String</code> type.</p>
         * @param value {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
         * @see java.lang.String
         * @see java.lang.Object
         */
        public OfRestParam(String key, Object value) {
            super(key, value);
        }

        /**
         * <code>OfRestParam</code>
         * <p>Instantiates a new of rest param.</p>
         * @param entry {@link java.util.Map.Entry} <p>The entry parameter is <code>Entry</code> type.</p>
         * @see java.util.Map.Entry
         */
        public OfRestParam(Map.Entry<String, Object> entry) {
            super(entry);
        }

    }
}
