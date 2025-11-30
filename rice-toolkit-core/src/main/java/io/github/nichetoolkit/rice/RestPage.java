package io.github.nichetoolkit.rice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.pagehelper.PageInfo;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.filter.PageFilter;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.jspecify.annotations.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * <code>RestPage</code>
 * <p>The rest page class.</p>
 * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.io.Serializable
 * @see lombok.Setter
 * @see lombok.Getter
 * @see java.lang.SuppressWarnings
 * @see lombok.experimental.SuperBuilder
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk17
 */
@Setter
@Getter
@SuppressWarnings("WeakerAccess")
@SuperBuilder
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestPage<T> implements Serializable {
    /**
     * <code>totals</code>
     * {@link java.lang.Long} <p>The <code>totals</code> field.</p>
     * @see java.lang.Long
     */
    protected Long totals;
    /**
     * <code>pages</code>
     * {@link java.lang.Long} <p>The <code>pages</code> field.</p>
     * @see java.lang.Long
     */
    protected Long pages;
    /**
     * <code>pageNum</code>
     * {@link java.lang.Long} <p>The <code>pageNum</code> field.</p>
     * @see java.lang.Long
     */
    protected Long pageNum;
    /**
     * <code>pageSize</code>
     * {@link java.lang.Long} <p>The <code>pageSize</code> field.</p>
     * @see java.lang.Long
     */
    protected Long pageSize;
    /**
     * <code>itemSize</code>
     * {@link java.lang.Long} <p>The <code>itemSize</code> field.</p>
     * @see java.lang.Long
     */
    protected Long itemSize;
    /**
     * <code>items</code>
     * {@link java.util.Collection} <p>The <code>items</code> field.</p>
     * @see java.util.Collection
     * @see lombok.Builder.Default
     */
    @Builder.Default
    protected Collection<T> items = Collections.emptyList();
    /**
     * <code>firstPage</code>
     * {@link java.lang.Boolean} <p>The <code>firstPage</code> field.</p>
     * @see java.lang.Boolean
     */
    protected Boolean firstPage;
    /**
     * <code>lastPage</code>
     * {@link java.lang.Boolean} <p>The <code>lastPage</code> field.</p>
     * @see java.lang.Boolean
     */
    protected Boolean lastPage;

    /**
     * <code>RestPage</code>
     * <p>Instantiates a new rest page.</p>
     */
    public RestPage() {
    }

    /**
     * <code>RestPage</code>
     * <p>Instantiates a new rest page.</p>
     * @param items {@link java.util.Collection} <p>The items parameter is <code>Collection</code> type.</p>
     * @see java.util.Collection
     * @see org.jspecify.annotations.NonNull
     */
    public RestPage(@NonNull Collection<T> items) {
        this.pages = 1L;
        this.pageNum = 1L;
        this.items = new ArrayList<>(items);
        this.totals = (long) items.size();
        this.pageSize = (long) items.size();
        this.itemSize = (long) items.size();
        this.firstPage = true;
        this.lastPage = true;
    }

    /**
     * <code>RestPage</code>
     * <p>Instantiates a new rest page.</p>
     * @param totals   {@link java.lang.Long} <p>The totals parameter is <code>Long</code> type.</p>
     * @param pageNum  {@link java.lang.Long} <p>The page num parameter is <code>Long</code> type.</p>
     * @param pageSize {@link java.lang.Long} <p>The page size parameter is <code>Long</code> type.</p>
     * @param items    {@link java.util.Collection} <p>The items parameter is <code>Collection</code> type.</p>
     * @see java.lang.Long
     * @see java.util.Collection
     * @see org.jspecify.annotations.NonNull
     */
    public RestPage(Long totals, Long pageNum, Long pageSize, @NonNull Collection<T> items) {
        this.totals = totals;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.items = new ArrayList<>(items);
        if (pageSize != null && pageSize > 0L) {
            this.pages = totals / pageSize;
            if (totals % pageSize != 0L) {
                this.pages = this.pages + 1L;
            }
            this.itemSize = GeneralUtils.isEmpty(this.items) ? 0L : (long) items.size();
        } else {
            this.pageNum = 0L;
            this.pages = 0L;
            this.itemSize = 0L;
        }
        this.firstPage = this.pageNum == 1;
        this.lastPage = this.pageNum.equals(this.pages) || this.pages == 0;
    }

    /**
     * <code>result</code>
     * <p>The result method.</p>
     * @param <T>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param totals {@link java.lang.Long} <p>The totals parameter is <code>Long</code> type.</p>
     * @param items  {@link java.util.Collection} <p>The items parameter is <code>Collection</code> type.</p>
     * @param filter {@link io.github.nichetoolkit.rice.filter.PageFilter} <p>The filter parameter is <code>PageFilter</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.RestPage} <p>The result return object is <code>RestPage</code> type.</p>
     * @see java.lang.Long
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rice.filter.PageFilter
     */
    public static <T, K> RestPage<T> result(Long totals, Collection<T> items, PageFilter filter) {
        if (GeneralUtils.isEmpty(filter.getPageSize())) {
            return new RestPage<>(items);
        } else {
            return new RestPage<>(totals, ((long) filter.getPageNum()), ((long) filter.getPageSize()), items);
        }
    }

    /**
     * <code>result</code>
     * <p>The result method.</p>
     * @param <T>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param totals {@link java.lang.Long} <p>The totals parameter is <code>Long</code> type.</p>
     * @param items  {@link java.util.Collection} <p>The items parameter is <code>Collection</code> type.</p>
     * @param page   {@link com.github.pagehelper.Page} <p>The page parameter is <code>Page</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.RestPage} <p>The result return object is <code>RestPage</code> type.</p>
     * @see java.lang.Long
     * @see java.util.Collection
     * @see com.github.pagehelper.Page
     */
    public static <T, K> RestPage<T> result(Long totals, Collection<T> items, com.github.pagehelper.Page<?> page) {
        if (GeneralUtils.isEmpty(page)) {
            return new RestPage<>(items);
        } else {
            return new RestPage<>(totals, ((long) page.getPageNum()), ((long) page.getPageSize()), items);
        }
    }


    /**
     * <code>result</code>
     * <p>The result method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param totals   {@link java.lang.Long} <p>The totals parameter is <code>Long</code> type.</p>
     * @param pageNum  {@link java.lang.Long} <p>The page num parameter is <code>Long</code> type.</p>
     * @param pageSize {@link java.lang.Long} <p>The page size parameter is <code>Long</code> type.</p>
     * @param items    {@link java.util.Collection} <p>The items parameter is <code>Collection</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.RestPage} <p>The result return object is <code>RestPage</code> type.</p>
     * @see java.lang.Long
     * @see java.util.Collection
     */
    public static <T, K> RestPage<T> result(Long totals, Long pageNum, Long pageSize, Collection<T> items) {
        if (GeneralUtils.isEmpty(pageSize)) {
            return new RestPage<>(items);
        } else {
            return new RestPage<>(totals, pageNum, pageSize, items);
        }
    }

    /**
     * <code>result</code>
     * <p>The result method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param items {@link java.util.Collection} <p>The items parameter is <code>Collection</code> type.</p>
     * @param page  {@link com.github.pagehelper.Page} <p>The page parameter is <code>Page</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.RestPage} <p>The result return object is <code>RestPage</code> type.</p>
     * @see java.util.Collection
     * @see com.github.pagehelper.Page
     */
    public static <T, K> RestPage<T> result(Collection<T> items, com.github.pagehelper.Page<?> page) {
        if (GeneralUtils.isEmpty(page)) {
            return new RestPage<>(items);
        } else {
            RestPageBuilder<T, ?, ?> pageBuilder = RestPage.builder();
            if (GeneralUtils.isNotEmpty(items)) {
                pageBuilder.items(items);
            }
            pageBuilder.itemSize((long) page.getResult().size())
                    .pageNum((long) page.getPageNum())
                    .pageSize((long) page.getPageSize())
                    .pages((long) page.getPages())
                    .totals(page.getTotal())
                    .firstPage(page.getPageNum() == 1)
                    .lastPage(page.getPageNum() == page.getPages() || page.getPages() == 0);
            return pageBuilder.build();
        }
    }

    /**
     * <code>result</code>
     * <p>The result method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param items {@link java.util.Collection} <p>The items parameter is <code>Collection</code> type.</p>
     * @param page  {@link io.github.nichetoolkit.rice.RestPage} <p>The page parameter is <code>RestPage</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.RestPage} <p>The result return object is <code>RestPage</code> type.</p>
     * @see java.util.Collection
     */
    public static <T, K> RestPage<T> result(Collection<T> items, RestPage<K> page) {
        if (GeneralUtils.isEmpty(page)) {
            return new RestPage<>(items);
        } else {
            RestPageBuilder<T, ?, ?> pageBuilder = RestPage.builder();
            if (GeneralUtils.isNotEmpty(items)) {
                pageBuilder.items(items);
            }
            pageBuilder.itemSize(page.getItemSize())
                    .pageNum(page.getPageNum())
                    .pageSize(page.getPageSize())
                    .pages(page.getPages())
                    .totals(page.getTotals())
                    .firstPage(page.getFirstPage())
                    .lastPage(page.getLastPage());
            return pageBuilder.build();
        }
    }

    /**
     * <code>result</code>
     * <p>The result method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param items    {@link java.util.Collection} <p>The items parameter is <code>Collection</code> type.</p>
     * @param pageInfo {@link com.github.pagehelper.PageInfo} <p>The page info parameter is <code>PageInfo</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.RestPage} <p>The result return object is <code>RestPage</code> type.</p>
     * @see java.util.Collection
     * @see com.github.pagehelper.PageInfo
     */
    public static <T, K> RestPage<T> result(Collection<T> items, PageInfo<K> pageInfo) {
        if (GeneralUtils.isEmpty(pageInfo)) {
            return new RestPage<>(items);
        } else {
            RestPage<T> resultPage = new RestPage<>();
            if (GeneralUtils.isNotEmpty(items)) {
                resultPage.setItems(items);
            }
            resultPage.setPageNum((long) pageInfo.getPageNum());
            resultPage.setPageSize((long) pageInfo.getPageSize());
            resultPage.setPages((long) pageInfo.getPages());
            resultPage.setTotals(pageInfo.getTotal());
            resultPage.setFirstPage(pageInfo.isIsFirstPage());
            resultPage.setLastPage(pageInfo.isIsLastPage());
            return resultPage;
        }
    }
}
