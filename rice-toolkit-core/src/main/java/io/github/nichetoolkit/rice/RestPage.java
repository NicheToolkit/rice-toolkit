package io.github.nichetoolkit.rice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.pagehelper.PageInfo;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.filter.PageFilter;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * <code>RestPage</code>
 * <p>The rest page class.</p>
 * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
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
public class RestPage<T> implements Serializable {
    /**
     * <code>totals</code>
     * {@link java.lang.Long} <p>The <code>totals</code> field.</p>
     * @see  java.lang.Long
     */
    protected Long totals;
    /**
     * <code>pages</code>
     * {@link java.lang.Long} <p>The <code>pages</code> field.</p>
     * @see  java.lang.Long
     */
    protected Long pages;
    /**
     * <code>pageNum</code>
     * {@link java.lang.Long} <p>The <code>pageNum</code> field.</p>
     * @see  java.lang.Long
     */
    protected Long pageNum;
    /**
     * <code>pageSize</code>
     * {@link java.lang.Long} <p>The <code>pageSize</code> field.</p>
     * @see  java.lang.Long
     */
    protected Long pageSize;
    /**
     * <code>itemSize</code>
     * {@link java.lang.Long} <p>The <code>itemSize</code> field.</p>
     * @see  java.lang.Long
     */
    protected Long itemSize;
    /**
     * <code>items</code>
     * {@link java.util.Collection} <p>The <code>items</code> field.</p>
     * @see  java.util.Collection
     */
    protected Collection<T> items = Collections.emptyList();
    /**
     * <code>firstPage</code>
     * {@link java.lang.Boolean} <p>The <code>firstPage</code> field.</p>
     * @see  java.lang.Boolean
     */
    protected Boolean firstPage;
    /**
     * <code>lastPage</code>
     * {@link java.lang.Boolean} <p>The <code>lastPage</code> field.</p>
     * @see  java.lang.Boolean
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
     * @see  java.util.Collection
     * @see  org.springframework.lang.NonNull
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
     * @param totals {@link java.lang.Long} <p>The totals parameter is <code>Long</code> type.</p>
     * @param pageNum {@link java.lang.Long} <p>The page num parameter is <code>Long</code> type.</p>
     * @param pageSize {@link java.lang.Long} <p>The page size parameter is <code>Long</code> type.</p>
     * @param items {@link java.util.Collection} <p>The items parameter is <code>Collection</code> type.</p>
     * @see  java.lang.Long
     * @see  java.util.Collection
     * @see  org.springframework.lang.NonNull
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
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param totals {@link java.lang.Long} <p>The totals parameter is <code>Long</code> type.</p>
     * @param items {@link java.util.Collection} <p>The items parameter is <code>Collection</code> type.</p>
     * @param filter {@link io.github.nichetoolkit.rice.filter.PageFilter} <p>The filter parameter is <code>PageFilter</code> type.</p>
     * @see  java.lang.Long
     * @see  java.util.Collection
     * @see  io.github.nichetoolkit.rice.filter.PageFilter
     * @return  {@link io.github.nichetoolkit.rice.RestPage} <p>The result return object is <code>RestPage</code> type.</p>
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
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param totals {@link java.lang.Long} <p>The totals parameter is <code>Long</code> type.</p>
     * @param items {@link java.util.Collection} <p>The items parameter is <code>Collection</code> type.</p>
     * @param page {@link com.github.pagehelper.Page} <p>The page parameter is <code>Page</code> type.</p>
     * @see  java.lang.Long
     * @see  java.util.Collection
     * @see  com.github.pagehelper.Page
     * @return  {@link io.github.nichetoolkit.rice.RestPage} <p>The result return object is <code>RestPage</code> type.</p>
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
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param totals {@link java.lang.Long} <p>The totals parameter is <code>Long</code> type.</p>
     * @param pageNum {@link java.lang.Long} <p>The page num parameter is <code>Long</code> type.</p>
     * @param pageSize {@link java.lang.Long} <p>The page size parameter is <code>Long</code> type.</p>
     * @param items {@link java.util.Collection} <p>The items parameter is <code>Collection</code> type.</p>
     * @see  java.lang.Long
     * @see  java.util.Collection
     * @return  {@link io.github.nichetoolkit.rice.RestPage} <p>The result return object is <code>RestPage</code> type.</p>
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
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param items {@link java.util.Collection} <p>The items parameter is <code>Collection</code> type.</p>
     * @param page {@link com.github.pagehelper.Page} <p>The page parameter is <code>Page</code> type.</p>
     * @see  java.util.Collection
     * @see  com.github.pagehelper.Page
     * @return  {@link io.github.nichetoolkit.rice.RestPage} <p>The result return object is <code>RestPage</code> type.</p>
     */
    public static <T, K> RestPage<T> result(Collection<T> items, com.github.pagehelper.Page<?> page) {
        if (GeneralUtils.isEmpty(page)) {
            return new RestPage<>(items);
        } else {
            RestPage.Builder<T> pageBuilder = new RestPage.Builder<>();
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
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param items {@link java.util.Collection} <p>The items parameter is <code>Collection</code> type.</p>
     * @param page {@link io.github.nichetoolkit.rice.RestPage} <p>The page parameter is <code>RestPage</code> type.</p>
     * @see  java.util.Collection
     * @return  {@link io.github.nichetoolkit.rice.RestPage} <p>The result return object is <code>RestPage</code> type.</p>
     */
    public static <T, K> RestPage<T> result(Collection<T> items, RestPage<K> page) {
        if (GeneralUtils.isEmpty(page)) {
            return new RestPage<>(items);
        } else {
            RestPage.Builder<T> pageBuilder = new RestPage.Builder<>();
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
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param items {@link java.util.Collection} <p>The items parameter is <code>Collection</code> type.</p>
     * @param pageInfo {@link com.github.pagehelper.PageInfo} <p>The page info parameter is <code>PageInfo</code> type.</p>
     * @see  java.util.Collection
     * @see  com.github.pagehelper.PageInfo
     * @return  {@link io.github.nichetoolkit.rice.RestPage} <p>The result return object is <code>RestPage</code> type.</p>
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

    /**
     * <code>RestPage</code>
     * <p>Instantiates a new rest page.</p>
     * @param builder {@link io.github.nichetoolkit.rice.RestPage.Builder} <p>The builder parameter is <code>Builder</code> type.</p>
     * @see  io.github.nichetoolkit.rice.RestPage.Builder
     */
    public RestPage(RestPage.Builder<T> builder) {
        this.totals = builder.totals;
        this.pages = builder.pages;
        this.pageNum = builder.pageNum;
        this.pageSize = builder.pageSize;
        this.itemSize = builder.itemSize;
        this.items = builder.items;
        this.firstPage = builder.firstPage;
        this.lastPage = builder.lastPage;
    }

    /**
     * <code>Builder</code>
     * <p>The builder class.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @see  java.lang.SuppressWarnings
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk1.8
     */
    @SuppressWarnings({"WeakerAccess", "UnusedReturnValue"})
    public static class Builder<T> {
        /**
         * <code>totals</code>
         * {@link java.lang.Long} <p>The <code>totals</code> field.</p>
         * @see  java.lang.Long
         */
        protected Long totals;
        /**
         * <code>pages</code>
         * {@link java.lang.Long} <p>The <code>pages</code> field.</p>
         * @see  java.lang.Long
         */
        protected Long pages;
        /**
         * <code>pageNum</code>
         * {@link java.lang.Long} <p>The <code>pageNum</code> field.</p>
         * @see  java.lang.Long
         */
        protected Long pageNum;
        /**
         * <code>pageSize</code>
         * {@link java.lang.Long} <p>The <code>pageSize</code> field.</p>
         * @see  java.lang.Long
         */
        protected Long pageSize;
        /**
         * <code>itemSize</code>
         * {@link java.lang.Long} <p>The <code>itemSize</code> field.</p>
         * @see  java.lang.Long
         */
        protected Long itemSize;
        /**
         * <code>items</code>
         * {@link java.util.Collection} <p>The <code>items</code> field.</p>
         * @see  java.util.Collection
         */
        protected Collection<T> items = Collections.emptyList();
        /**
         * <code>firstPage</code>
         * {@link java.lang.Boolean} <p>The <code>firstPage</code> field.</p>
         * @see  java.lang.Boolean
         */
        protected Boolean firstPage;
        /**
         * <code>lastPage</code>
         * {@link java.lang.Boolean} <p>The <code>lastPage</code> field.</p>
         * @see  java.lang.Boolean
         */
        protected Boolean lastPage;

        /**
         * <code>Builder</code>
         * <p>Instantiates a new builder.</p>
         */
        public Builder() {
        }

        /**
         * <code>totals</code>
         * <p>The totals method.</p>
         * @param totals {@link java.lang.Long} <p>The totals parameter is <code>Long</code> type.</p>
         * @see  java.lang.Long
         * @return  {@link io.github.nichetoolkit.rice.RestPage.Builder} <p>The totals return object is <code>Builder</code> type.</p>
         */
        public RestPage.Builder<T> totals(Long totals) {
            this.totals = totals;
            return this;
        }

        /**
         * <code>pages</code>
         * <p>The pages method.</p>
         * @param pages {@link java.lang.Long} <p>The pages parameter is <code>Long</code> type.</p>
         * @see  java.lang.Long
         * @return  {@link io.github.nichetoolkit.rice.RestPage.Builder} <p>The pages return object is <code>Builder</code> type.</p>
         */
        public RestPage.Builder<T> pages(Long pages) {
            this.pages = pages;
            return this;
        }

        /**
         * <code>pageNum</code>
         * <p>The page num method.</p>
         * @param pageNum {@link java.lang.Long} <p>The page num parameter is <code>Long</code> type.</p>
         * @see  java.lang.Long
         * @return  {@link io.github.nichetoolkit.rice.RestPage.Builder} <p>The page num return object is <code>Builder</code> type.</p>
         */
        public RestPage.Builder<T> pageNum(Long pageNum) {
            this.pageNum = pageNum;
            return this;
        }

        /**
         * <code>pageSize</code>
         * <p>The page size method.</p>
         * @param pageSize {@link java.lang.Long} <p>The page size parameter is <code>Long</code> type.</p>
         * @see  java.lang.Long
         * @return  {@link io.github.nichetoolkit.rice.RestPage.Builder} <p>The page size return object is <code>Builder</code> type.</p>
         */
        public RestPage.Builder<T> pageSize(Long pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        /**
         * <code>itemSize</code>
         * <p>The item size method.</p>
         * @param itemSize {@link java.lang.Long} <p>The item size parameter is <code>Long</code> type.</p>
         * @see  java.lang.Long
         * @return  {@link io.github.nichetoolkit.rice.RestPage.Builder} <p>The item size return object is <code>Builder</code> type.</p>
         */
        public RestPage.Builder<T> itemSize(Long itemSize) {
            this.itemSize = itemSize;
            return this;
        }

        /**
         * <code>items</code>
         * <p>The items method.</p>
         * @param items {@link java.util.Collection} <p>The items parameter is <code>Collection</code> type.</p>
         * @see  java.util.Collection
         * @return  {@link io.github.nichetoolkit.rice.RestPage.Builder} <p>The items return object is <code>Builder</code> type.</p>
         */
        public RestPage.Builder<T> items(Collection<T> items) {
            this.items = new ArrayList<>(items);
            return this;
        }

        /**
         * <code>firstPage</code>
         * <p>The first page method.</p>
         * @param firstPage {@link java.lang.Boolean} <p>The first page parameter is <code>Boolean</code> type.</p>
         * @see  java.lang.Boolean
         * @return  {@link io.github.nichetoolkit.rice.RestPage.Builder} <p>The first page return object is <code>Builder</code> type.</p>
         */
        public RestPage.Builder<T> firstPage(Boolean firstPage) {
            this.firstPage = firstPage;
            return this;
        }

        /**
         * <code>lastPage</code>
         * <p>The last page method.</p>
         * @param lastPage {@link java.lang.Boolean} <p>The last page parameter is <code>Boolean</code> type.</p>
         * @see  java.lang.Boolean
         * @return  {@link io.github.nichetoolkit.rice.RestPage.Builder} <p>The last page return object is <code>Builder</code> type.</p>
         */
        public RestPage.Builder<T> lastPage(Boolean lastPage) {
            this.lastPage = lastPage;
            return this;
        }

        /**
         * <code>build</code>
         * <p>The build method.</p>
         * @return  {@link io.github.nichetoolkit.rice.RestPage} <p>The build return object is <code>RestPage</code> type.</p>
         */
        public RestPage<T> build() {
            return new RestPage<>(this);
        }
    }
}
