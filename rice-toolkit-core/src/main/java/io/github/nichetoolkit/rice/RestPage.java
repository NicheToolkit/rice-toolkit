package io.github.nichetoolkit.rice;

import com.github.pagehelper.PageInfo;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * <p>Page</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@SuppressWarnings("WeakerAccess")
public class RestPage<T> implements Serializable {
    /** 总数据量 */
    protected Long totals;
    /** 总页数 */
    protected Long pages;
    /** 当前页码 */
    protected Long pageNum;
    /** 页码大小 */
    protected Long pageSize;
    /** 当前页码大小 */
    protected Long itemSize;
    /** 数据 */
    protected List<T> items = Collections.emptyList();

    public RestPage() {
    }

    public RestPage(@NonNull Collection<T> items) {
        this.items = new ArrayList<>(items);
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
                    .pageNum((long)page.getPageNum())
                    .pageSize((long)page.getPageSize())
                    .pages((long)page.getPages())
                    .totals(page.getTotal());
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
                    .totals(page.getTotals());
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
            resultPage.setPageNum((long)pageInfo.getPageNum());
            resultPage.setPageSize((long)pageInfo.getPageSize());
            resultPage.setPages((long)pageInfo.getPages());
            resultPage.setTotals(pageInfo.getTotal());
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

    @SuppressWarnings({"WeakerAccess", "UnusedReturnValue"})
    public static class Builder<T> {
        protected Long totals;
        protected Long pages;
        protected Long pageNum;
        protected Long pageSize;
        protected Long itemSize;
        protected List<T> items = Collections.emptyList();

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

        public RestPage<T> build() {
            return new RestPage<>(this);
        }
    }
}
