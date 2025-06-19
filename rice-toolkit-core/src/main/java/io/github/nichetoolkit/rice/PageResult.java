package io.github.nichetoolkit.rice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.pagehelper.Page;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Collection;

/**
 * <code>PageResult</code>
 * <p>The page result class.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.RestId} <p>The generic parameter is <code>RestId</code> type.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestId
 * @see lombok.Getter
 * @see lombok.Setter
 * @see lombok.experimental.SuperBuilder
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk1.8
 */
@Getter
@Setter
@SuperBuilder
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageResult<E extends RestId<I>,I> {
    /**
     * <code>page</code>
     * {@link com.github.pagehelper.Page} <p>The <code>page</code> field.</p>
     * @see com.github.pagehelper.Page
     */
    private Page<E> page;
    /**
     * <code>entities</code>
     * {@link java.util.Collection} <p>The <code>entities</code> field.</p>
     * @see java.util.Collection
     */
    private Collection<E> entities;

    /**
     * <code>PageResult</code>
     * <p>Instantiates a new page result.</p>
     */
    public PageResult() {
    }

    /**
     * <code>PageResult</code>
     * <p>Instantiates a new page result.</p>
     * @param page     {@link com.github.pagehelper.Page} <p>The page parameter is <code>Page</code> type.</p>
     * @param entities {@link java.util.Collection} <p>The entities parameter is <code>Collection</code> type.</p>
     * @see com.github.pagehelper.Page
     * @see java.util.Collection
     */
    public PageResult(Page<E> page, Collection<E> entities) {
        this.page = page;
        this.entities = entities;
    }

    /**
     * <code>builder</code>
     * <p>The builder method.</p>
     * @param <E> {@link io.github.nichetoolkit.rice.RestId} <p>The generic parameter is <code>RestId</code> type.</p>
     * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @return {@link io.github.nichetoolkit.rice.PageResult} <p>The builder return object is <code>PageResult</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestId
     */
    public static <E extends RestId<I>,I> PageResult<E,I> builder() {
        return new PageResult<>();
    }

    /**
     * <code>builder</code>
     * <p>The builder method.</p>
     * @param <E>      {@link io.github.nichetoolkit.rice.RestId} <p>The generic parameter is <code>RestId</code> type.</p>
     * @param <I>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param page     {@link com.github.pagehelper.Page} <p>The page parameter is <code>Page</code> type.</p>
     * @param entities {@link java.util.Collection} <p>The entities parameter is <code>Collection</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.PageResult} <p>The builder return object is <code>PageResult</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestId
     * @see com.github.pagehelper.Page
     * @see java.util.Collection
     */
    public static <E extends RestId<I>,I> PageResult<E,I> builder(Page<E> page, Collection<E> entities) {
        return new PageResult<>(page,entities);
    }
}
