package io.github.nichetoolkit.mybatis.load;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.rest.RestEntry;
import io.github.nichetoolkit.rest.RestValue;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.Setter;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

/**
 * <code>RestLoad</code>
 * <p>The rest load interface.</p>
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
public interface RestLoad extends RestValue<String, Boolean>, RestEntry<String, Boolean>, Serializable {
    /**
     * <code>_KEY</code>
     * {@link java.lang.String} <p>The constant <code>_KEY</code> field.</p>
     * @see java.lang.String
     */
    String _KEY = "key";
    /**
     * <code>_VALUE</code>
     * {@link java.lang.String} <p>The constant <code>_VALUE</code> field.</p>
     * @see java.lang.String
     */
    String _VALUE = "value";


    /**
     * <code>of</code>
     * <p>The of method.</p>
     * @param key {@link java.lang.String} <p>The key parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.mybatis.load.RestLoad} <p>The of return object is <code>RestLoad</code> type.</p>
     * @see java.lang.String
     */
    static RestLoad of(String key) {
        return new RestLoad.OfRestLoad(key);
    }

    /**
     * <code>ofArray</code>
     * <p>The of array method.</p>
     * @param keys {@link java.lang.String} <p>The keys parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.mybatis.load.RestLoad} <p>The of array return object is <code>RestLoad</code> type.</p>
     * @see java.lang.String
     */
    static RestLoad[] ofArray(String... keys) {
        if (GeneralUtils.isEmpty(keys)) {
            return null;
        }
        return Arrays.stream(keys).map(RestLoad.OfRestLoad::new).toArray(RestLoad[]::new);
    }

    /**
     * <code>of</code>
     * <p>The of method.</p>
     * @param key   {@link java.lang.String} <p>The key parameter is <code>String</code> type.</p>
     * @param value {@link java.lang.Boolean} <p>The value parameter is <code>Boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.mybatis.load.RestLoad} <p>The of return object is <code>RestLoad</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Boolean
     */
    static RestLoad of(String key, Boolean value) {
        return new RestLoad.OfRestLoad(key, value);
    }


    /**
     * <code>ofArray</code>
     * <p>The of array method.</p>
     * @param entries {@link java.util.Collection} <p>The entries parameter is <code>Collection</code> type.</p>
     * @return {@link io.github.nichetoolkit.mybatis.load.RestLoad} <p>The of array return object is <code>RestLoad</code> type.</p>
     * @see java.util.Collection
     */
    static RestLoad[] ofArray(Collection<Map.Entry<String, Boolean>> entries) {
        if (GeneralUtils.isEmpty(entries)) {
            return null;
        }
        return entries.stream().map(RestLoad.OfRestLoad::new).toArray(RestLoad[]::new);
    }

    /**
     * <code>of</code>
     * <p>The of method.</p>
     * @param entry {@link java.util.Map.Entry} <p>The entry parameter is <code>Entry</code> type.</p>
     * @return {@link io.github.nichetoolkit.mybatis.load.RestLoad} <p>The of return object is <code>RestLoad</code> type.</p>
     * @see java.util.Map.Entry
     */
    static RestLoad of(Map.Entry<String, Boolean> entry) {
        return new RestLoad.OfRestLoad(entry);
    }

    /**
     * <code>ofArray</code>
     * <p>The of array method.</p>
     * @param map {@link java.util.Map} <p>The map parameter is <code>Map</code> type.</p>
     * @return {@link io.github.nichetoolkit.mybatis.load.RestLoad} <p>The of array return object is <code>RestLoad</code> type.</p>
     * @see java.util.Map
     */
    static RestLoad[] ofArray(Map<String, Boolean> map) {
        if (GeneralUtils.isEmpty(map)) {
            return null;
        }
        return map.entrySet().stream().map(RestLoad.OfRestLoad::new).toArray(RestLoad[]::new);
    }


    /**
     * <code>ofNull</code>
     * <p>The of null method.</p>
     * @return {@link io.github.nichetoolkit.mybatis.load.RestLoad} <p>The of null return object is <code>RestLoad</code> type.</p>
     */
    static RestLoad ofNull() {
        return new RestLoad.OfRestLoad();
    }

    /**
     * <code>OfRestLoad</code>
     * <p>The of rest load class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rest.RestValue.OfRestValue
     * @see lombok.Setter
     * @since Jdk17
     */
    @Setter
    class OfRestLoad extends RestValue.OfRestValue<String, Boolean> implements RestLoad {

        /**
         * <code>OfRestLoad</code>
         * <p>Instantiates a new of rest load.</p>
         */
        public OfRestLoad() {
        }

        /**
         * <code>OfRestLoad</code>
         * <p>Instantiates a new of rest load.</p>
         * @param key {@link java.lang.String} <p>The key parameter is <code>String</code> type.</p>
         * @see java.lang.String
         */
        public OfRestLoad(String key) {
            super(key, true);
        }

        /**
         * <code>OfRestLoad</code>
         * <p>Instantiates a new of rest load.</p>
         * @param key   {@link java.lang.String} <p>The key parameter is <code>String</code> type.</p>
         * @param value {@link java.lang.Boolean} <p>The value parameter is <code>Boolean</code> type.</p>
         * @see java.lang.String
         * @see java.lang.Boolean
         */
        public OfRestLoad(String key, Boolean value) {
            super(key, value);
        }

        /**
         * <code>OfRestLoad</code>
         * <p>Instantiates a new of rest load.</p>
         * @param entry {@link java.util.Map.Entry} <p>The entry parameter is <code>Entry</code> type.</p>
         * @see java.util.Map.Entry
         */
        public OfRestLoad(Map.Entry<String, Boolean> entry) {
            super(entry);
        }

    }
}
