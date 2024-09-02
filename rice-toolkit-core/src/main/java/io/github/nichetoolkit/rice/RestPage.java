package io.github.nichetoolkit.rice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.pagehelper.PageInfo;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.filter.PageFilter;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * <code>RestPage</code>
 * <p>The type rest page class.</p>
 * @param <T> {@link java.lang.Object} <p>the parameter can be of any type.</p>
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
public class RestPage<T> implements Serializable {
    /**
     * <code>totals</code>
     * {@link java.lang.Long} <p>the <code>totals</code> field.</p>
     * @see java.lang.Long
     */
    protected Long totals;
    /**
     * <code>pages</code>
     * {@link java.lang.Long} <p>the <code>pages</code> field.</p>
     * @see java.lang.Long
     */
    protected Long pages;
    /**
     * <code>pageNum</code>
     * {@link java.lang.Long} <p>the <code>pageNum</code> field.</p>
     * @see java.lang.Long
     */
    protected Long pageNum;
    /**
     * <code>pageSize</code>
     * {@link java.lang.Long} <p>the <code>pageSize</code> field.</p>
     * @see java.lang.Long
     */
    protected Long pageSize;
    /**
     * <code>itemSize</code>
     * {@link java.lang.Long} <p>the <code>itemSize</code> field.</p>
     * @see java.lang.Long
     */
    protected Long itemSize;
    /**
     * <code>items</code>
     * {@link java.util.List} <p>the <code>items</code> field.</p>
     * @see java.util.List
     */
    protected List<T> items = Collections.emptyList();
    /**
     * <code>isFirstPage</code>
     * {@link java.lang.Boolean} <p>the <code>isFirstPage</code> field.</p>
     * @see java.lang.Boolean
     */
    protected Boolean isFirstPage;
    /**
     * <code>isLastPage</code>
     * {@link java.lang.Boolean} <p>the <code>isLastPage</code> field.</p>
     * @see java.lang.Boolean
     */
    protected Boolean isLastPage;

    /**
     * <code>RestPage</code>
     * Instantiates a new rest page.
     */
    public RestPage() {
    }

    /**
     * <code>RestPage</code>
     * Instantiates a new rest page.
     * @param items {@link java.util.Collection} <p>the items parameter is <code>Collection</code> type.</p>
     * @see java.util.Collection
     * @see org.springframework.lang.NonNull
     */
    public RestPage(@NonNull Collection<T> items) {
        this.pages = 1L;
        this.pageNum = 1L;
        this.items = new ArrayList<>(items);
        this.totals = (long) items.size();
        this.pageSize = (long) items.size();
        this.itemSize = (long) items.size();
        this.isFirstPage = true;
        this.isLastPage = true;
    }

    /**
     * <code>RestPage</code>
     * Instantiates a new rest page.
     * @param totals   {@link java.lang.Long} <p>the totals parameter is <code>Long</code> type.</p>
     * @param pageNum  {@link java.lang.Long} <p>the page num parameter is <code>Long</code> type.</p>
     * @param pageSize {@link java.lang.Long} <p>the page size parameter is <code>Long</code> type.</p>
     * @param items    {@link java.util.Collection} <p>the items parameter is <code>Collection</code> type.</p>
     * @see java.lang.Long
     * @see java.util.Collection
     * @see org.springframework.lang.NonNull
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
        this.isFirstPage = this.pageNum == 1;
        this.isLastPage = this.pageNum.equals(this.pages) || this.pages == 0;
    }

    /**
     * <code>result</code>
     * <p>the method.</p>
     * @param <T>    {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <K>    {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param totals {@link java.lang.Long} <p>the totals parameter is <code>Long</code> type.</p>
     * @param items  {@link java.util.Collection} <p>the items parameter is <code>Collection</code> type.</p>
     * @param filter {@link io.github.nichetoolkit.rice.filter.PageFilter} <p>the filter parameter is <code>PageFilter</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.RestPage} <p>the return object is <code>RestPage</code> type.</p>
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
     * <p>the method.</p>
     * @param <T>    {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <K>    {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param totals {@link java.lang.Long} <p>the totals parameter is <code>Long</code> type.</p>
     * @param items  {@link java.util.Collection} <p>the items parameter is <code>Collection</code> type.</p>
     * @param page   {@link com.github.pagehelper.Page} <p>the page parameter is <code>Page</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.RestPage} <p>the return object is <code>RestPage</code> type.</p>
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
     * <p>the method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <K>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param totals   {@link java.lang.Long} <p>the totals parameter is <code>Long</code> type.</p>
     * @param pageNum  {@link java.lang.Long} <p>the page num parameter is <code>Long</code> type.</p>
     * @param pageSize {@link java.lang.Long} <p>the page size parameter is <code>Long</code> type.</p>
     * @param items    {@link java.util.Collection} <p>the items parameter is <code>Collection</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.RestPage} <p>the return object is <code>RestPage</code> type.</p>
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
     * <p>the method.</p>
     * @param <T>   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <K>   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param items {@link java.util.Collection} <p>the items parameter is <code>Collection</code> type.</p>
     * @param page  {@link com.github.pagehelper.Page} <p>the page parameter is <code>Page</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.RestPage} <p>the return object is <code>RestPage</code> type.</p>
     * @see java.util.Collection
     * @see com.github.pagehelper.Page
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
     * <p>the method.</p>
     * @param <T>   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <K>   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param items {@link java.util.Collection} <p>the items parameter is <code>Collection</code> type.</p>
     * @param page  {@link io.github.nichetoolkit.rice.RestPage} <p>the page parameter is <code>RestPage</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.RestPage} <p>the return object is <code>RestPage</code> type.</p>
     * @see java.util.Collection
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
     * <p>the method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <K>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param items    {@link java.util.Collection} <p>the items parameter is <code>Collection</code> type.</p>
     * @param pageInfo {@link com.github.pagehelper.PageInfo} <p>the page info parameter is <code>PageInfo</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.RestPage} <p>the return object is <code>RestPage</code> type.</p>
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

    /**
     * <code>RestPage</code>
     * Instantiates a new rest page.
     * @param builder {@link io.github.nichetoolkit.rice.RestPage.Builder} <p>the builder parameter is <code>Builder</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestPage.Builder
     */
    public RestPage(RestPage.Builder<T> builder) {
        this.totals = builder.totals;
        this.pages = builder.pages;
        this.pageNum = builder.pageNum;
        this.pageSize = builder.pageSize;
        this.itemSize = builder.itemSize;
        this.items = builder.items;
        this.isFirstPage = builder.isFirstPage;
        this.isLastPage = builder.isLastPage;
    }

    /**
     * <code>getTotals</code>
     * <p>the totals getter method.</p>
     * @return {@link java.lang.Long} <p>the totals return object is <code>Long</code> type.</p>
     * @see java.lang.Long
     */
    public Long getTotals() {
        return totals;
    }

    /**
     * <code>setTotals</code>
     * <p>the totals setter method.</p>
     * @param totals {@link java.lang.Long} <p>the totals parameter is <code>Long</code> type.</p>
     * @see java.lang.Long
     */
    public void setTotals(Long totals) {
        this.totals = totals;
    }

    /**
     * <code>getPages</code>
     * <p>the pages getter method.</p>
     * @return {@link java.lang.Long} <p>the pages return object is <code>Long</code> type.</p>
     * @see java.lang.Long
     */
    public Long getPages() {
        return pages;
    }

    /**
     * <code>setPages</code>
     * <p>the pages setter method.</p>
     * @param pages {@link java.lang.Long} <p>the pages parameter is <code>Long</code> type.</p>
     * @see java.lang.Long
     */
    public void setPages(Long pages) {
        this.pages = pages;
    }

    /**
     * <code>getPageNum</code>
     * <p>the page num getter method.</p>
     * @return {@link java.lang.Long} <p>the page num return object is <code>Long</code> type.</p>
     * @see java.lang.Long
     */
    public Long getPageNum() {
        return pageNum;
    }

    /**
     * <code>setPageNum</code>
     * <p>the page num setter method.</p>
     * @param pageNum {@link java.lang.Long} <p>the page num parameter is <code>Long</code> type.</p>
     * @see java.lang.Long
     */
    public void setPageNum(Long pageNum) {
        this.pageNum = pageNum;
    }

    /**
     * <code>getPageSize</code>
     * <p>the page size getter method.</p>
     * @return {@link java.lang.Long} <p>the page size return object is <code>Long</code> type.</p>
     * @see java.lang.Long
     */
    public Long getPageSize() {
        return pageSize;
    }

    /**
     * <code>setPageSize</code>
     * <p>the page size setter method.</p>
     * @param pageSize {@link java.lang.Long} <p>the page size parameter is <code>Long</code> type.</p>
     * @see java.lang.Long
     */
    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * <code>getItemSize</code>
     * <p>the item size getter method.</p>
     * @return {@link java.lang.Long} <p>the item size return object is <code>Long</code> type.</p>
     * @see java.lang.Long
     */
    public Long getItemSize() {
        return itemSize;
    }

    /**
     * <code>setItemSize</code>
     * <p>the item size setter method.</p>
     * @param itemSize {@link java.lang.Long} <p>the item size parameter is <code>Long</code> type.</p>
     * @see java.lang.Long
     */
    public void setItemSize(Long itemSize) {
        this.itemSize = itemSize;
    }

    /**
     * <code>getItems</code>
     * <p>the items getter method.</p>
     * @return {@link java.util.List} <p>the items return object is <code>List</code> type.</p>
     * @see java.util.List
     */
    public List<T> getItems() {
        return items;
    }

    /**
     * <code>setItems</code>
     * <p>the items setter method.</p>
     * @param items {@link java.util.Collection} <p>the items parameter is <code>Collection</code> type.</p>
     * @see java.util.Collection
     */
    public void setItems(Collection<T> items) {
        this.items = new ArrayList<>(items);
    }

    /**
     * <code>getFirstPage</code>
     * <p>the first page getter method.</p>
     * @return {@link java.lang.Boolean} <p>the first page return object is <code>Boolean</code> type.</p>
     * @see java.lang.Boolean
     */
    public Boolean getFirstPage() {
        return isFirstPage;
    }

    /**
     * <code>setFirstPage</code>
     * <p>the first page setter method.</p>
     * @param firstPage {@link java.lang.Boolean} <p>the first page parameter is <code>Boolean</code> type.</p>
     * @see java.lang.Boolean
     */
    public void setFirstPage(Boolean firstPage) {
        isFirstPage = firstPage;
    }

    /**
     * <code>getLastPage</code>
     * <p>the last page getter method.</p>
     * @return {@link java.lang.Boolean} <p>the last page return object is <code>Boolean</code> type.</p>
     * @see java.lang.Boolean
     */
    public Boolean getLastPage() {
        return isLastPage;
    }

    /**
     * <code>setLastPage</code>
     * <p>the last page setter method.</p>
     * @param lastPage {@link java.lang.Boolean} <p>the last page parameter is <code>Boolean</code> type.</p>
     * @see java.lang.Boolean
     */
    public void setLastPage(Boolean lastPage) {
        isLastPage = lastPage;
    }

    /**
     * <code>Builder</code>
     * <p>The type builder class.</p>
     * @param <T> {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see java.lang.SuppressWarnings
     * @since Jdk1.8
     */
    @SuppressWarnings({"WeakerAccess", "UnusedReturnValue"})
    public static class Builder<T> {
        /**
         * <code>totals</code>
         * {@link java.lang.Long} <p>the <code>totals</code> field.</p>
         * @see java.lang.Long
         */
        protected Long totals;
        /**
         * <code>pages</code>
         * {@link java.lang.Long} <p>the <code>pages</code> field.</p>
         * @see java.lang.Long
         */
        protected Long pages;
        /**
         * <code>pageNum</code>
         * {@link java.lang.Long} <p>the <code>pageNum</code> field.</p>
         * @see java.lang.Long
         */
        protected Long pageNum;
        /**
         * <code>pageSize</code>
         * {@link java.lang.Long} <p>the <code>pageSize</code> field.</p>
         * @see java.lang.Long
         */
        protected Long pageSize;
        /**
         * <code>itemSize</code>
         * {@link java.lang.Long} <p>the <code>itemSize</code> field.</p>
         * @see java.lang.Long
         */
        protected Long itemSize;
        /**
         * <code>items</code>
         * {@link java.util.List} <p>the <code>items</code> field.</p>
         * @see java.util.List
         */
        protected List<T> items = Collections.emptyList();
        /**
         * <code>isFirstPage</code>
         * {@link java.lang.Boolean} <p>the <code>isFirstPage</code> field.</p>
         * @see java.lang.Boolean
         */
        protected Boolean isFirstPage;
        /**
         * <code>isLastPage</code>
         * {@link java.lang.Boolean} <p>the <code>isLastPage</code> field.</p>
         * @see java.lang.Boolean
         */
        protected Boolean isLastPage;

        /**
         * <code>Builder</code>
         * Instantiates a new builder.
         */
        public Builder() {
        }

        /**
         * <code>totals</code>
         * <p>the method.</p>
         * @param totals {@link java.lang.Long} <p>the totals parameter is <code>Long</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.RestPage.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see java.lang.Long
         */
        public RestPage.Builder<T> totals(Long totals) {
            this.totals = totals;
            return this;
        }

        /**
         * <code>pages</code>
         * <p>the method.</p>
         * @param pages {@link java.lang.Long} <p>the pages parameter is <code>Long</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.RestPage.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see java.lang.Long
         */
        public RestPage.Builder<T> pages(Long pages) {
            this.pages = pages;
            return this;
        }

        /**
         * <code>pageNum</code>
         * <p>the num method.</p>
         * @param pageNum {@link java.lang.Long} <p>the page num parameter is <code>Long</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.RestPage.Builder} <p>the num return object is <code>Builder</code> type.</p>
         * @see java.lang.Long
         */
        public RestPage.Builder<T> pageNum(Long pageNum) {
            this.pageNum = pageNum;
            return this;
        }

        /**
         * <code>pageSize</code>
         * <p>the size method.</p>
         * @param pageSize {@link java.lang.Long} <p>the page size parameter is <code>Long</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.RestPage.Builder} <p>the size return object is <code>Builder</code> type.</p>
         * @see java.lang.Long
         */
        public RestPage.Builder<T> pageSize(Long pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        /**
         * <code>itemSize</code>
         * <p>the size method.</p>
         * @param itemSize {@link java.lang.Long} <p>the item size parameter is <code>Long</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.RestPage.Builder} <p>the size return object is <code>Builder</code> type.</p>
         * @see java.lang.Long
         */
        public RestPage.Builder<T> itemSize(Long itemSize) {
            this.itemSize = itemSize;
            return this;
        }

        /**
         * <code>items</code>
         * <p>the method.</p>
         * @param items {@link java.util.Collection} <p>the items parameter is <code>Collection</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.RestPage.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see java.util.Collection
         */
        public RestPage.Builder<T> items(Collection<T> items) {
            this.items = new ArrayList<>(items);
            return this;
        }

        /**
         * <code>firstPage</code>
         * <p>the page method.</p>
         * @param firstPage {@link java.lang.Boolean} <p>the first page parameter is <code>Boolean</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.RestPage.Builder} <p>the page return object is <code>Builder</code> type.</p>
         * @see java.lang.Boolean
         */
        public RestPage.Builder<T> firstPage(Boolean firstPage) {
            this.isFirstPage = firstPage;
            return this;
        }

        /**
         * <code>lastPage</code>
         * <p>the page method.</p>
         * @param lastPage {@link java.lang.Boolean} <p>the last page parameter is <code>Boolean</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.RestPage.Builder} <p>the page return object is <code>Builder</code> type.</p>
         * @see java.lang.Boolean
         */
        public RestPage.Builder<T> lastPage(Boolean lastPage) {
            this.isLastPage = lastPage;
            return this;
        }

        /**
         * <code>build</code>
         * <p>the method.</p>
         * @return {@link io.github.nichetoolkit.rice.RestPage} <p>the return object is <code>RestPage</code> type.</p>
         */
        public RestPage<T> build() {
            return new RestPage<>(this);
        }
    }
}
