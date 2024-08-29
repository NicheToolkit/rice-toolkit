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

@SuppressWarnings("WeakerAccess")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestPage<T> implements Serializable {
    protected Long totals;
    protected Long pages;
    protected Long pageNum;
    protected Long pageSize;
    protected Long itemSize;
    protected List<T> items = Collections.emptyList();
    protected Boolean isFirstPage;
    protected Boolean isLastPage;

    public RestPage() {
    }

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

    public static <T, K> RestPage<T> result(Long totals, Collection<T> items, PageFilter filter) {
        if (GeneralUtils.isEmpty(filter.getPageSize())) {
            return new RestPage<>(items);
        } else {
            return new RestPage<>(totals, ((long) filter.getPageNum()), ((long) filter.getPageSize()), items);
        }
    }

    public static <T, K> RestPage<T> result(Long totals, Collection<T> items, com.github.pagehelper.Page page) {
        if (GeneralUtils.isEmpty(page)) {
            return new RestPage<>(items);
        } else {
            return new RestPage<>(totals, ((long) page.getPageNum()), ((long) page.getPageSize()), items);
        }
    }


    public static <T, K> RestPage<T> result(Long totals, Long pageNum, Long pageSize, Collection<T> items) {
        if (GeneralUtils.isEmpty(pageSize)) {
            return new RestPage<>(items);
        } else {
            return new RestPage<>(totals, pageNum, pageSize, items);
        }
    }

    public static <T, K> RestPage<T> result(Collection<T> items, com.github.pagehelper.Page page) {
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

    public Long getTotals() {
        return totals;
    }

    public void setTotals(Long totals) {
        this.totals = totals;
    }

    public Long getPages() {
        return pages;
    }

    public void setPages(Long pages) {
        this.pages = pages;
    }

    public Long getPageNum() {
        return pageNum;
    }

    public void setPageNum(Long pageNum) {
        this.pageNum = pageNum;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public Long getItemSize() {
        return itemSize;
    }

    public void setItemSize(Long itemSize) {
        this.itemSize = itemSize;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(Collection<T> items) {
        this.items = new ArrayList<>(items);
    }

    public Boolean getFirstPage() {
        return isFirstPage;
    }

    public void setFirstPage(Boolean firstPage) {
        isFirstPage = firstPage;
    }

    public Boolean getLastPage() {
        return isLastPage;
    }

    public void setLastPage(Boolean lastPage) {
        isLastPage = lastPage;
    }

    @SuppressWarnings({"WeakerAccess", "UnusedReturnValue"})
    public static class Builder<T> {
        protected Long totals;
        protected Long pages;
        protected Long pageNum;
        protected Long pageSize;
        protected Long itemSize;
        protected List<T> items = Collections.emptyList();
        protected Boolean isFirstPage;
        protected Boolean isLastPage;

        public Builder() {
        }

        public RestPage.Builder<T> totals(Long totals) {
            this.totals = totals;
            return this;
        }

        public RestPage.Builder<T> pages(Long pages) {
            this.pages = pages;
            return this;
        }

        public RestPage.Builder<T> pageNum(Long pageNum) {
            this.pageNum = pageNum;
            return this;
        }

        public RestPage.Builder<T> pageSize(Long pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public RestPage.Builder<T> itemSize(Long itemSize) {
            this.itemSize = itemSize;
            return this;
        }

        public RestPage.Builder<T> items(Collection<T> items) {
            this.items = new ArrayList<>(items);
            return this;
        }

        public RestPage.Builder<T> firstPage(Boolean firstPage) {
            this.isFirstPage = firstPage;
            return this;
        }

        public RestPage.Builder<T> lastPage(Boolean lastPage) {
            this.isLastPage = lastPage;
            return this;
        }

        public RestPage<T> build() {
            return new RestPage<>(this);
        }
    }
}
