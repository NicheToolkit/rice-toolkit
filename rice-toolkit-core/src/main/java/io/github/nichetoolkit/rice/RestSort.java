package io.github.nichetoolkit.rice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.mybatis.enums.SortType;
import io.github.nichetoolkit.rest.util.GeneralUtils;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <code>RestSort</code>
 * <p>The type rest sort class.</p>
 * @param <S> {@link io.github.nichetoolkit.rice.RestSort} <p>the generic parameter is <code>RestSort</code> type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.io.Serializable
 * @see java.lang.SuppressWarnings
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk1.8
 */
@SuppressWarnings("WeakerAccess")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestSort<S extends RestSort<S>> implements Serializable {
    /**
     * <code>name</code>
     * {@link java.lang.String} <p>the <code>name</code> field.</p>
     * @see java.lang.String
     */
    protected String name;
    /**
     * <code>type</code>
     * {@link io.github.nichetoolkit.mybatis.enums.SortType} <p>the <code>type</code> field.</p>
     * @see io.github.nichetoolkit.mybatis.enums.SortType
     */
    protected SortType type = SortType.DESC;

    /**
     * <code>RestSort</code>
     * Instantiates a new rest sort.
     */
    public RestSort() {
    }

    /**
     * <code>RestSort</code>
     * Instantiates a new rest sort.
     * @param name {@link java.lang.String} <p>the name parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public RestSort(String name) {
        this.name = name;
    }

    /**
     * <code>RestSort</code>
     * Instantiates a new rest sort.
     * @param name {@link java.lang.String} <p>the name parameter is <code>String</code> type.</p>
     * @param type {@link io.github.nichetoolkit.mybatis.enums.SortType} <p>the type parameter is <code>SortType</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.mybatis.enums.SortType
     */
    public RestSort(String name, SortType type) {
        this.name = name;
        this.type = type;
    }

    /**
     * <code>RestSort</code>
     * Instantiates a new rest sort.
     * @param builder {@link io.github.nichetoolkit.rice.RestSort.Builder} <p>the builder parameter is <code>Builder</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestSort.Builder
     */
    public RestSort(RestSort.Builder<S> builder) {
        this.name = builder.name;
        this.type = builder.type;
    }

    /**
     * <code>getName</code>
     * <p>the name getter method.</p>
     * @return {@link java.lang.String} <p>the name return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public String getName() {
        return name;
    }

    /**
     * <code>setName</code>
     * <p>the name setter method.</p>
     * @param name {@link java.lang.String} <p>the name parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * <code>getType</code>
     * <p>the type getter method.</p>
     * @return {@link io.github.nichetoolkit.mybatis.enums.SortType} <p>the type return object is <code>SortType</code> type.</p>
     * @see io.github.nichetoolkit.mybatis.enums.SortType
     */
    public SortType getType() {
        return type;
    }

    /**
     * <code>setType</code>
     * <p>the type setter method.</p>
     * @param type {@link io.github.nichetoolkit.mybatis.enums.SortType} <p>the type parameter is <code>SortType</code> type.</p>
     * @see io.github.nichetoolkit.mybatis.enums.SortType
     */
    public void setType(SortType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RestSort)) return false;
        RestSort<?> sort = (RestSort<?>) o;
        return Objects.equals(name, sort.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        if (this.type != SortType.NONE) {
            return name.concat(" ").concat(type.getKey());
        }
        return "";
    }

    /**
     * <code>build</code>
     * <p>the method.</p>
     * @param sorts {@link java.lang.String} <p>the sorts parameter is <code>String</code> type.</p>
     * @return {@link java.util.List} <p>the return object is <code>List</code> type.</p>
     * @see java.lang.String
     * @see java.util.List
     */
    public static List<RestSort<?>> build(String... sorts) {
        if (GeneralUtils.isEmpty(sorts)) {
            return Collections.emptyList();
        }
        return Arrays.stream(sorts).map(RestSort::new).collect(Collectors.toList());
    }

    /**
     * <code>build</code>
     * <p>the method.</p>
     * @param sorts {@link java.util.Collection} <p>the sorts parameter is <code>Collection</code> type.</p>
     * @return {@link java.util.List} <p>the return object is <code>List</code> type.</p>
     * @see java.util.Collection
     * @see java.util.List
     */
    public static List<RestSort<?>> build(Collection<String> sorts) {
        if (GeneralUtils.isEmpty(sorts)) {
            return Collections.emptyList();
        }
        return sorts.stream().map(RestSort::new).collect(Collectors.toList());
    }

    /**
     * <code>Builder</code>
     * <p>The type builder class.</p>
     * @param <S> {@link io.github.nichetoolkit.rice.RestSort} <p>the generic parameter is <code>RestSort</code> type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk1.8
     */
    public static class Builder<S extends RestSort<S>> {
        /**
         * <code>name</code>
         * {@link java.lang.String} <p>the <code>name</code> field.</p>
         * @see java.lang.String
         */
        protected String name;
        /**
         * <code>type</code>
         * {@link io.github.nichetoolkit.mybatis.enums.SortType} <p>the <code>type</code> field.</p>
         * @see io.github.nichetoolkit.mybatis.enums.SortType
         */
        protected SortType type = SortType.DESC;

        /**
         * <code>Builder</code>
         * Instantiates a new builder.
         */
        public Builder() {
        }

        /**
         * <code>name</code>
         * <p>the method.</p>
         * @param name {@link java.lang.String} <p>the name parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.RestSort.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public RestSort.Builder<S> name(String name) {
            this.name = name;
            return this;
        }

        /**
         * <code>type</code>
         * <p>the method.</p>
         * @param type {@link io.github.nichetoolkit.mybatis.enums.SortType} <p>the type parameter is <code>SortType</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.RestSort.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see io.github.nichetoolkit.mybatis.enums.SortType
         */
        public RestSort.Builder<S> type(SortType type) {
            this.type = type;
            return this;
        }

        /**
         * <code>type</code>
         * <p>the method.</p>
         * @param type {@link java.lang.String} <p>the type parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.RestSort.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public RestSort.Builder<S> type(String type) {
            this.type = SortType.parseKey(type);
            return this;
        }

        /**
         * <code>build</code>
         * <p>the method.</p>
         * @return {@link io.github.nichetoolkit.rice.RestSort} <p>the return object is <code>RestSort</code> type.</p>
         */
        public RestSort<S> build() {
            return new RestSort<>(this);
        }
    }

}
