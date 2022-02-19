package io.github.nichetoolkit.rice.filter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.github.nichetoolkit.rest.util.JsonUtils;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>PageFilter</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@SuppressWarnings("WeakerAccess")
public class PageFilter implements Serializable {
    @JsonIgnore
    public static final String PAGE_REGEX = "_";
    @JsonIgnore
    public static final String PAGE_LIMIT = "LIMIT";
    @JsonIgnore
    public static final String PAGE_OFFSET = "OFFSET";
    @JsonIgnore
    protected final List<Boolean> LOAD_ARRAY = new ArrayList<>();
    protected Integer pageNum = 1;
    protected Integer pageSize = 0;

    public PageFilter() {
    }

    public PageFilter(Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public PageFilter(PageFilter.Builder builder) {
        this.pageNum = builder.pageNum;
        this.pageSize = builder.pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        if (pageNum > 0) {
            this.pageNum = pageNum;
        }
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return JsonUtils.parseJson(this);
    }

    public String toPageSql() {
        return PAGE_LIMIT + " " + this.pageSize +
                PAGE_OFFSET + " " + (this.pageNum - 1);
    }

    public <T> Page<T> toPage() {
        return PageHelper.startPage(pageNum, pageSize);
    }

    public Boolean[] toLoadArray() {
        return LOAD_ARRAY.toArray(new Boolean[0]);
    }

    public Boolean[] toLoadArray(@NonNull Boolean... isLoads) {
        this.addLoadArray(isLoads);
        return LOAD_ARRAY.toArray(new Boolean[0]);
    }

    public void addLoadArray(@NonNull Boolean... isLoads) {
        LOAD_ARRAY.addAll(Arrays.asList(isLoads));
    }

    public String name() {
        return this.getClass().getSimpleName();
    }

    public String toKey() {
        return this.pageNum + PAGE_REGEX + this.pageSize;
    }

    public static class Builder {
        protected Integer pageNum;
        protected Integer pageSize;
        public Builder() {
        }

        public PageFilter.Builder pageNum(Integer pageNum) {
            this.pageNum = pageNum;
            return this;
        }

        public PageFilter.Builder pageSize(Integer pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public PageFilter build() {
            return new PageFilter(this);
        }
    }

}
