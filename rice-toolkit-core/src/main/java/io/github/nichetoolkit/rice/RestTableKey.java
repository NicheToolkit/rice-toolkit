package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rest.RestKey;
import lombok.Setter;

import java.util.Objects;

/**
 * <code>RestTableKey</code>
 * <p>The rest table key interface.</p>
 * @param <K> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestKey
 * @since Jdk17
 */
public interface RestTableKey<K> extends RestKey<K> {

    /**
     * <code>getTableKey</code>
     * <p>The get table key getter method.</p>
     * @return K <p>The get table key return object is <code>K</code> type.</p>
     */
    default K getTableKey() {
        return null;
    }

    @Override
    default K getKey() {
        return getTableKey();
    }

    /**
     * <code>of</code>
     * <p>The of method.</p>
     * @param <K> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param key K <p>The key parameter is <code>K</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.RestTableKey} <p>The of return object is <code>RestTableKey</code> type.</p>
     */
    static <K> RestTableKey<K> of(K key) {
        return new OfRestTableKey<>(key);
    }

    /**
     * <code>of</code>
     * <p>The of method.</p>
     * @param <K> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param key {@link io.github.nichetoolkit.rest.RestKey} <p>The key parameter is <code>RestKey</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.RestTableKey} <p>The of return object is <code>RestTableKey</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestKey
     */
    static <K> RestTableKey<K> of(RestKey<K> key) {
        return new OfRestTableKey<>(key);
    }

    /**
     * <code>ofNull</code>
     * <p>The of null method.</p>
     * @param <K> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @return {@link io.github.nichetoolkit.rice.RestTableKey} <p>The of null return object is <code>RestTableKey</code> type.</p>
     */
    static <K> RestTableKey<K> ofNull() {
        return new OfRestTableKey<>();
    }

    /**
     * <code>OfRestTableKey</code>
     * <p>The of rest table key class.</p>
     * @param <K> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see lombok.Setter
     * @since Jdk17
     */
    @Setter
    class OfRestTableKey<K> implements RestTableKey<K> {
        /**
         * <code>tableKey</code>
         * <p>The <code>tableKey</code> field.</p>
         */
        private K tableKey;

        /**
         * <code>OfRestTableKey</code>
         * <p>Instantiates a new of rest table key.</p>
         */
        public OfRestTableKey() {
        }

        /**
         * <code>OfRestTableKey</code>
         * <p>Instantiates a new of rest table key.</p>
         * @param key {@link io.github.nichetoolkit.rest.RestKey} <p>The key parameter is <code>RestKey</code> type.</p>
         * @see io.github.nichetoolkit.rest.RestKey
         */
        public OfRestTableKey(RestKey<K> key) {
            this.tableKey = key.getKey();
        }

        /**
         * <code>OfRestTableKey</code>
         * <p>Instantiates a new of rest table key.</p>
         * @param tableKey K <p>The table key parameter is <code>K</code> type.</p>
         */
        public OfRestTableKey(K tableKey) {
            this.tableKey = tableKey;
        }

        @Override
        public K getTableKey() {
            return this.tableKey;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            OfRestTableKey<?> ofRestTableKey = (OfRestTableKey<?>) o;
            return Objects.equals(this.tableKey, ofRestTableKey.tableKey);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(this.tableKey);
        }
    }
}
