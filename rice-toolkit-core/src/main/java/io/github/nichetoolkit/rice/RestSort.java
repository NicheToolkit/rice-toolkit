package io.github.nichetoolkit.rice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.mybatis.enums.SortType;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <code>RestSort</code>
 * <p>The rest sort class.</p>
 * @param <S>  {@link io.github.nichetoolkit.rice.RestSort} <p>The generic parameter is <code>RestSort</code> type.</p>
 * @see  java.io.Serializable
 * @see  lombok.Setter
 * @see  lombok.Getter
 * @see  java.lang.SuppressWarnings
 * @see  com.fasterxml.jackson.annotation.JsonInclude
 * @see  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Setter
@Getter
@SuppressWarnings("WeakerAccess")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestSort<S extends RestSort<S>> implements Serializable {
    /**
     * <code>name</code>
     * {@link java.lang.String} <p>The <code>name</code> field.</p>
     * @see  java.lang.String
     */
    protected String name;
    /**
     * <code>type</code>
     * {@link io.github.nichetoolkit.mybatis.enums.SortType} <p>The <code>type</code> field.</p>
     * @see  io.github.nichetoolkit.mybatis.enums.SortType
     */
    protected SortType type = SortType.DESC;

    /**
     * <code>RestSort</code>
     * <p>Instantiates a new rest sort.</p>
     */
    public RestSort() {
    }

    /**
     * <code>RestSort</code>
     * <p>Instantiates a new rest sort.</p>
     * @param name {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    public RestSort(String name) {
        this.name = name;
    }

    /**
     * <code>RestSort</code>
     * <p>Instantiates a new rest sort.</p>
     * @param name {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @param type {@link io.github.nichetoolkit.mybatis.enums.SortType} <p>The type parameter is <code>SortType</code> type.</p>
     * @see  java.lang.String
     * @see  io.github.nichetoolkit.mybatis.enums.SortType
     */
    public RestSort(String name, SortType type) {
        this.name = name;
        this.type = type;
    }

    /**
     * <code>RestSort</code>
     * <p>Instantiates a new rest sort.</p>
     * @param builder {@link io.github.nichetoolkit.rice.RestSort.Builder} <p>The builder parameter is <code>Builder</code> type.</p>
     * @see  io.github.nichetoolkit.rice.RestSort.Builder
     */
    public RestSort(RestSort.Builder<S> builder) {
        this.name = builder.name;
        this.type = builder.type;
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
     * <p>The build method.</p>
     * @param sorts {@link java.lang.String} <p>The sorts parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  java.util.List
     * @return  {@link java.util.List} <p>The build return object is <code>List</code> type.</p>
     */
    public static List<RestSort<?>> build(String... sorts) {
        if (GeneralUtils.isEmpty(sorts)) {
            return Collections.emptyList();
        }
        return Arrays.stream(sorts).map(RestSort::new).collect(Collectors.toList());
    }

    /**
     * <code>build</code>
     * <p>The build method.</p>
     * @param sorts {@link java.util.Collection} <p>The sorts parameter is <code>Collection</code> type.</p>
     * @see  java.util.Collection
     * @see  java.util.List
     * @return  {@link java.util.List} <p>The build return object is <code>List</code> type.</p>
     */
    public static List<RestSort<?>> build(Collection<String> sorts) {
        if (GeneralUtils.isEmpty(sorts)) {
            return Collections.emptyList();
        }
        return sorts.stream().map(RestSort::new).collect(Collectors.toList());
    }

    /**
     * <code>Builder</code>
     * <p>The builder class.</p>
     * @param <S>  {@link io.github.nichetoolkit.rice.RestSort} <p>The generic parameter is <code>RestSort</code> type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk1.8
     */
    public static class Builder<S extends RestSort<S>> {
        /**
         * <code>name</code>
         * {@link java.lang.String} <p>The <code>name</code> field.</p>
         * @see  java.lang.String
         */
        protected String name;
        /**
         * <code>type</code>
         * {@link io.github.nichetoolkit.mybatis.enums.SortType} <p>The <code>type</code> field.</p>
         * @see  io.github.nichetoolkit.mybatis.enums.SortType
         */
        protected SortType type = SortType.DESC;

        /**
         * <code>Builder</code>
         * <p>Instantiates a new builder.</p>
         */
        public Builder() {
        }

        /**
         * <code>name</code>
         * <p>The name method.</p>
         * @param name {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
         * @see  java.lang.String
         * @return  {@link io.github.nichetoolkit.rice.RestSort.Builder} <p>The name return object is <code>Builder</code> type.</p>
         */
        public RestSort.Builder<S> name(String name) {
            this.name = name;
            return this;
        }

        /**
         * <code>type</code>
         * <p>The type method.</p>
         * @param type {@link io.github.nichetoolkit.mybatis.enums.SortType} <p>The type parameter is <code>SortType</code> type.</p>
         * @see  io.github.nichetoolkit.mybatis.enums.SortType
         * @return  {@link io.github.nichetoolkit.rice.RestSort.Builder} <p>The type return object is <code>Builder</code> type.</p>
         */
        public RestSort.Builder<S> type(SortType type) {
            this.type = type;
            return this;
        }

        /**
         * <code>type</code>
         * <p>The type method.</p>
         * @param type {@link java.lang.String} <p>The type parameter is <code>String</code> type.</p>
         * @see  java.lang.String
         * @return  {@link io.github.nichetoolkit.rice.RestSort.Builder} <p>The type return object is <code>Builder</code> type.</p>
         */
        public RestSort.Builder<S> type(String type) {
            this.type = SortType.parseKey(type);
            return this;
        }

        /**
         * <code>build</code>
         * <p>The build method.</p>
         * @return  {@link io.github.nichetoolkit.rice.RestSort} <p>The build return object is <code>RestSort</code> type.</p>
         */
        public RestSort<S> build() {
            return new RestSort<>(this);
        }
    }

}
