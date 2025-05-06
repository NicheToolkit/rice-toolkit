package io.github.nichetoolkit.mybatis.load;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.rest.RestEntry;
import io.github.nichetoolkit.rest.RestValue;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

/**
 * <code>RestLoad</code>
 * <p>The rest load interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestValue
 * @see io.github.nichetoolkit.rest.RestEntry
 * @see java.io.Serializable
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk1.8
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public interface RestLoad extends RestValue<String, Boolean>, RestEntry<String, Boolean>, Serializable {

    /**
     * <code>getIndex</code>
     * <p>The get index getter method.</p>
     * @return {@link java.lang.Integer} <p>The get index return object is <code>Integer</code> type.</p>
     * @see java.lang.Integer
     */
    Integer getIndex();

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
     * <code>of</code>
     * <p>The of method.</p>
     * @param index {@link java.lang.Integer} <p>The index parameter is <code>Integer</code> type.</p>
     * @return {@link io.github.nichetoolkit.mybatis.load.RestLoad} <p>The of return object is <code>RestLoad</code> type.</p>
     * @see java.lang.Integer
     */
    static RestLoad of(Integer index) {
        return new RestLoad.OfRestLoad(index);
    }

    /**
     * <code>of</code>
     * <p>The of method.</p>
     * @param key   {@link java.lang.String} <p>The key parameter is <code>String</code> type.</p>
     * @param index {@link java.lang.Integer} <p>The index parameter is <code>Integer</code> type.</p>
     * @return {@link io.github.nichetoolkit.mybatis.load.RestLoad} <p>The of return object is <code>RestLoad</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Integer
     */
    static RestLoad of(String key, Integer index) {
        return new RestLoad.OfRestLoad(key, index);
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
     * <code>of</code>
     * <p>The of method.</p>
     * @param index {@link java.lang.Integer} <p>The index parameter is <code>Integer</code> type.</p>
     * @param value {@link java.lang.Boolean} <p>The value parameter is <code>Boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.mybatis.load.RestLoad} <p>The of return object is <code>RestLoad</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.Boolean
     */
    static RestLoad of(Integer index, Boolean value) {
        return new RestLoad.OfRestLoad(index, value);
    }

    /**
     * <code>of</code>
     * <p>The of method.</p>
     * @param key   {@link java.lang.String} <p>The key parameter is <code>String</code> type.</p>
     * @param index {@link java.lang.Integer} <p>The index parameter is <code>Integer</code> type.</p>
     * @param value {@link java.lang.Boolean} <p>The value parameter is <code>Boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.mybatis.load.RestLoad} <p>The of return object is <code>RestLoad</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Integer
     * @see java.lang.Boolean
     */
    static RestLoad of(String key, Integer index, Boolean value) {
        return new RestLoad.OfRestLoad(key, index, value);
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
     * <code>of</code>
     * <p>The of method.</p>
     * @param index {@link java.lang.Integer} <p>The index parameter is <code>Integer</code> type.</p>
     * @param entry {@link java.util.Map.Entry} <p>The entry parameter is <code>Entry</code> type.</p>
     * @return {@link io.github.nichetoolkit.mybatis.load.RestLoad} <p>The of return object is <code>RestLoad</code> type.</p>
     * @see java.lang.Integer
     * @see java.util.Map.Entry
     */
    static RestLoad of(Integer index, Map.Entry<String, Boolean> entry) {
        return new RestLoad.OfRestLoad(index, entry);
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
     * @since Jdk1.8
     */
    @Setter
    class OfRestLoad extends RestValue.OfRestValue<String, Boolean> implements RestLoad {

        /**
         * <code>index</code>
         * {@link java.lang.Integer} <p>The <code>index</code> field.</p>
         * @see java.lang.Integer
         */
        private Integer index;

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
            this.index = 0;
        }

        /**
         * <code>OfRestLoad</code>
         * <p>Instantiates a new of rest load.</p>
         * @param index {@link java.lang.Integer} <p>The index parameter is <code>Integer</code> type.</p>
         * @see java.lang.Integer
         */
        public OfRestLoad(Integer index) {
            super(null, true);
            this.index = index;
        }

        /**
         * <code>OfRestLoad</code>
         * <p>Instantiates a new of rest load.</p>
         * @param key   {@link java.lang.String} <p>The key parameter is <code>String</code> type.</p>
         * @param index {@link java.lang.Integer} <p>The index parameter is <code>Integer</code> type.</p>
         * @see java.lang.String
         * @see java.lang.Integer
         */
        public OfRestLoad(String key, Integer index) {
            super(key, true);
            this.index = index;
        }

        /**
         * <code>OfRestLoad</code>
         * <p>Instantiates a new of rest load.</p>
         * @param index {@link java.lang.Integer} <p>The index parameter is <code>Integer</code> type.</p>
         * @param value {@link java.lang.Boolean} <p>The value parameter is <code>Boolean</code> type.</p>
         * @see java.lang.Integer
         * @see java.lang.Boolean
         */
        public OfRestLoad(Integer index, Boolean value) {
            super(null, value);
            this.index = index;
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
            this.index = 0;
        }

        /**
         * <code>OfRestLoad</code>
         * <p>Instantiates a new of rest load.</p>
         * @param key   {@link java.lang.String} <p>The key parameter is <code>String</code> type.</p>
         * @param index {@link java.lang.Integer} <p>The index parameter is <code>Integer</code> type.</p>
         * @param value {@link java.lang.Boolean} <p>The value parameter is <code>Boolean</code> type.</p>
         * @see java.lang.String
         * @see java.lang.Integer
         * @see java.lang.Boolean
         */
        public OfRestLoad(String key, Integer index, Boolean value) {
            super(key, value);
            this.index = index;
        }

        /**
         * <code>OfRestLoad</code>
         * <p>Instantiates a new of rest load.</p>
         * @param entry {@link java.util.Map.Entry} <p>The entry parameter is <code>Entry</code> type.</p>
         * @see java.util.Map.Entry
         */
        public OfRestLoad(Map.Entry<String, Boolean> entry) {
            super(entry);
            this.index = 0;
        }

        /**
         * <code>OfRestLoad</code>
         * <p>Instantiates a new of rest load.</p>
         * @param index {@link java.lang.Integer} <p>The index parameter is <code>Integer</code> type.</p>
         * @param entry {@link java.util.Map.Entry} <p>The entry parameter is <code>Entry</code> type.</p>
         * @see java.lang.Integer
         * @see java.util.Map.Entry
         */
        public OfRestLoad(Integer index, Map.Entry<String, Boolean> entry) {
            super(entry);
            this.index = index;
        }

        @Override
        public Integer getIndex() {
            return this.index;
        }

        @Override
        public String getKey() {
            String key = super.getKey();
            if (GeneralUtils.isEmpty(key) && GeneralUtils.isUsable(this.index)) {
                return String.valueOf(this.index);
            }
            return key;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            OfRestLoad that = (OfRestLoad) o;
            return Objects.equals(index, that.index);
        }

        @Override
        public int hashCode() {
            return Objects.hash(super.hashCode(), index);
        }
    }
}
