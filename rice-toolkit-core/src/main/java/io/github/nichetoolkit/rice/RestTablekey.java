package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rest.RestKey;
import lombok.Setter;

import java.util.Objects;

/**
 * <code>RestTablekey</code>
 * <p>The rest tablekey interface.</p>
 * @param <K> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestKey
 * @since Jdk1.8
 */
public interface RestTablekey<K> extends RestKey<K> {

    /**
     * <code>getTablekey</code>
     * <p>The get tablekey getter method.</p>
     * @return K <p>The get tablekey return object is <code>K</code> type.</p>
     */
    default K getTablekey() {
        return null;
    }

    @Override
    default K getKey() {
        return getTablekey();
    }

    /**
     * <code>of</code>
     * <p>The of method.</p>
     * @param <K> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param key K <p>The key parameter is <code>K</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.RestTablekey} <p>The of return object is <code>RestTablekey</code> type.</p>
     */
    static <K> RestTablekey<K> of(K key) {
        return new OfRestTablekey<>(key);
    }

    /**
     * <code>of</code>
     * <p>The of method.</p>
     * @param <K> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param key {@link io.github.nichetoolkit.rest.RestKey} <p>The key parameter is <code>RestKey</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.RestTablekey} <p>The of return object is <code>RestTablekey</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestKey
     */
    static <K> RestTablekey<K> of(RestKey<K> key) {
        return new OfRestTablekey<>(key);
    }

    /**
     * <code>ofNull</code>
     * <p>The of null method.</p>
     * @param <K> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @return {@link io.github.nichetoolkit.rice.RestTablekey} <p>The of null return object is <code>RestTablekey</code> type.</p>
     */
    static <K> RestTablekey<K> ofNull() {
        return new OfRestTablekey<>();
    }

    /**
     * <code>OfRestTablekey</code>
     * <p>The of rest tablekey class.</p>
     * @param <K> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see lombok.Setter
     * @since Jdk1.8
     */
    @Setter
    class OfRestTablekey<K> implements RestTablekey<K> {
        /**
         * <code>tablekey</code>
         * <p>The <code>tablekey</code> field.</p>
         */
        private K tablekey;

        /**
         * <code>OfRestTablekey</code>
         * <p>Instantiates a new of rest tablekey.</p>
         */
        public OfRestTablekey() {
        }

        /**
         * <code>OfRestTablekey</code>
         * <p>Instantiates a new of rest tablekey.</p>
         * @param key {@link io.github.nichetoolkit.rest.RestKey} <p>The key parameter is <code>RestKey</code> type.</p>
         * @see io.github.nichetoolkit.rest.RestKey
         */
        public OfRestTablekey(RestKey<K> key) {
            this.tablekey = key.getKey();
        }

        /**
         * <code>OfRestTablekey</code>
         * <p>Instantiates a new of rest tablekey.</p>
         * @param tablekey K <p>The tablekey parameter is <code>K</code> type.</p>
         */
        public OfRestTablekey(K tablekey) {
            this.tablekey = tablekey;
        }

        @Override
        public K getTablekey() {
            return this.tablekey;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            OfRestTablekey<?> ofRestTablekey = (OfRestTablekey<?>) o;
            return Objects.equals(this.tablekey, ofRestTablekey.tablekey);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(this.tablekey);
        }
    }
}
