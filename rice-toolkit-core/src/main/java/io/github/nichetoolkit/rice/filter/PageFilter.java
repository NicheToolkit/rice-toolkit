package io.github.nichetoolkit.rice.filter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.util.JsonUtils;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <code>PageFilter</code>
 * <p>The type page filter class.</p>
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
public class PageFilter implements Serializable {
    /**
     * <code>PAGE_REGEX</code>
     * {@link java.lang.String} <p>The constant <code>PAGE_REGEX</code> field.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    public static final String PAGE_REGEX = "_";
    /**
     * <code>PAGE_LIMIT</code>
     * {@link java.lang.String} <p>The constant <code>PAGE_LIMIT</code> field.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    public static final String PAGE_LIMIT = "LIMIT";
    /**
     * <code>PAGE_OFFSET</code>
     * {@link java.lang.String} <p>The constant <code>PAGE_OFFSET</code> field.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    public static final String PAGE_OFFSET = "OFFSET";
    /**
     * <code>FIELD_ARRAY</code>
     * {@link java.util.List} <p>The <code>FIELD_ARRAY</code> field.</p>
     * @see java.util.List
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    protected final List<String> FIELD_ARRAY = new ArrayList<>();
    /**
     * <code>LOAD_ARRAY</code>
     * {@link java.util.List} <p>The <code>LOAD_ARRAY</code> field.</p>
     * @see java.util.List
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    protected final List<Boolean> LOAD_ARRAY = new ArrayList<>();
    /**
     * <code>pageNum</code>
     * {@link java.lang.Integer} <p>The <code>pageNum</code> field.</p>
     * @see java.lang.Integer
     */
    protected Integer pageNum = 1;
    /**
     * <code>pageSize</code>
     * {@link java.lang.Integer} <p>The <code>pageSize</code> field.</p>
     * @see java.lang.Integer
     */
    protected Integer pageSize = 0;
    /**
     * <code>loadLastPage</code>
     * {@link java.lang.Boolean} <p>The <code>loadLastPage</code> field.</p>
     * @see java.lang.Boolean
     */
    protected Boolean loadLastPage = false;

    /**
     * <code>PageFilter</code>
     * <p>Instantiates a new page filter.</p>
     */
    public PageFilter() {
    }

    /**
     * <code>PageFilter</code>
     * <p>Instantiates a new page filter.</p>
     * @param pageNum  {@link java.lang.Integer} <p>The page num parameter is <code>Integer</code> type.</p>
     * @param pageSize {@link java.lang.Integer} <p>The page size parameter is <code>Integer</code> type.</p>
     * @see java.lang.Integer
     */
    public PageFilter(Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    /**
     * <code>PageFilter</code>
     * <p>Instantiates a new page filter.</p>
     * @param builder {@link io.github.nichetoolkit.rice.filter.PageFilter.Builder} <p>The builder parameter is <code>Builder</code> type.</p>
     * @see io.github.nichetoolkit.rice.filter.PageFilter.Builder
     */
    public PageFilter(PageFilter.Builder builder) {
        this.pageNum = builder.pageNum;
        this.pageSize = builder.pageSize;
    }

    /**
     * <code>getPageNum</code>
     * <p>The page num getter method.</p>
     * @return {@link java.lang.Integer} <p>The page num return object is <code>Integer</code> type.</p>
     * @see java.lang.Integer
     */
    public Integer getPageNum() {
        return pageNum;
    }

    /**
     * <code>setPageNum</code>
     * <p>The page num setter method.</p>
     * @param pageNum {@link java.lang.Integer} <p>The page num parameter is <code>Integer</code> type.</p>
     * @see java.lang.Integer
     */
    public void setPageNum(Integer pageNum) {
        if (pageNum == null) {
            this.pageNum = 1;
        } else if (pageNum > 0) {
            this.pageNum = pageNum;
        } else {
            this.pageNum = 1;
        }
    }

    /**
     * <code>getPageSize</code>
     * <p>The page size getter method.</p>
     * @return {@link java.lang.Integer} <p>The page size return object is <code>Integer</code> type.</p>
     * @see java.lang.Integer
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * <code>setPageSize</code>
     * <p>The page size setter method.</p>
     * @param pageSize {@link java.lang.Integer} <p>The page size parameter is <code>Integer</code> type.</p>
     * @see java.lang.Integer
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * <code>getLoadLastPage</code>
     * <p>The load last page getter method.</p>
     * @return {@link java.lang.Boolean} <p>The load last page return object is <code>Boolean</code> type.</p>
     * @see java.lang.Boolean
     */
    public Boolean getLoadLastPage() {
        return loadLastPage;
    }

    /**
     * <code>setLoadLastPage</code>
     * <p>The load last page setter method.</p>
     * @param loadLastPage {@link java.lang.Boolean} <p>The load last page parameter is <code>Boolean</code> type.</p>
     * @see java.lang.Boolean
     */
    public void setLoadLastPage(Boolean loadLastPage) {
        this.loadLastPage = loadLastPage;
    }

    @Override
    public String toString() {
        return JsonUtils.parseJson(this);
    }


    /**
     * <code>toPageSql</code>
     * <p>The page sql method.</p>
     * @return {@link java.lang.String} <p>The page sql return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public String toPageSql() {
        return PAGE_LIMIT + " " + this.pageSize +
                PAGE_OFFSET + " " + (this.pageNum - 1);
    }

    /**
     * <code>toPage</code>
     * <p>The page method.</p>
     * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @return {@link com.github.pagehelper.Page} <p>The page return object is <code>Page</code> type.</p>
     * @see com.github.pagehelper.Page
     */
    public <T> Page<T> toPage() {
        if (GeneralUtils.isNotEmpty(this.pageSize)) {
            if (this.loadLastPage) {
                return PageHelper.startPage(Integer.MAX_VALUE, this.pageSize,true,true,null);
            } else {
                return PageHelper.startPage(this.pageNum, this.pageSize);
            }
        }
        return null;

    }

    /**
     * <code>toLoadArray</code>
     * <p>The load array method.</p>
     * @return {@link java.lang.Boolean} <p>The load array return object is <code>Boolean</code> type.</p>
     * @see java.lang.Boolean
     */
    public Boolean[] toLoadArray() {
        Boolean[] toArray = LOAD_ARRAY.toArray(new Boolean[0]);
        LOAD_ARRAY.clear();
        return toArray;
    }

    /**
     * <code>toLoadArray</code>
     * <p>The load array method.</p>
     * @param isLoads {@link java.lang.Boolean} <p>The is loads parameter is <code>Boolean</code> type.</p>
     * @return {@link java.lang.Boolean} <p>The load array return object is <code>Boolean</code> type.</p>
     * @see java.lang.Boolean
     * @see org.springframework.lang.NonNull
     */
    public Boolean[] toLoadArray(@NonNull Boolean... isLoads) {
        this.addLoadArray(isLoads);
        return toLoadArray();
    }

    /**
     * <code>toFieldArray</code>
     * <p>The field array method.</p>
     * @return {@link java.lang.String} <p>The field array return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public String[] toFieldArray() {
        String[] toArray = FIELD_ARRAY.toArray(new String[0]);
        FIELD_ARRAY.clear();
        return toArray;
    }

    /**
     * <code>toFieldArray</code>
     * <p>The field array method.</p>
     * @param fields {@link java.lang.String} <p>The fields parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>The field array return object is <code>String</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.lang.NonNull
     */
    public String[] toFieldArray(@NonNull String... fields) {
        this.addFieldArray(fields);
        return toFieldArray();
    }

    /**
     * <code>addLoadArray</code>
     * <p>The load array method.</p>
     * @param isLoads {@link java.lang.Boolean} <p>The is loads parameter is <code>Boolean</code> type.</p>
     * @see java.lang.Boolean
     * @see org.springframework.lang.NonNull
     */
    public void addLoadArray(@NonNull Boolean... isLoads) {
        LOAD_ARRAY.addAll(Arrays.asList(isLoads));
    }

    /**
     * <code>addFieldArray</code>
     * <p>The field array method.</p>
     * @param fields {@link java.lang.String} <p>The fields parameter is <code>String</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.lang.NonNull
     */
    public void addFieldArray(@NonNull String... fields) {
        FIELD_ARRAY.addAll(Arrays.asList(fields));
    }

    /**
     * <code>name</code>
     * <p>The method.</p>
     * @return {@link java.lang.String} <p>The return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public String name() {
        return this.getClass().getSimpleName();
    }

    /**
     * <code>toKey</code>
     * <p>The key method.</p>
     * @return {@link java.lang.String} <p>The key return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public String toKey() {
        return this.pageNum + PAGE_REGEX + this.pageSize;
    }

    /**
     * <code>Builder</code>
     * <p>The type builder class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk1.8
     */
    public static class Builder {
        /**
         * <code>pageNum</code>
         * {@link java.lang.Integer} <p>The <code>pageNum</code> field.</p>
         * @see java.lang.Integer
         */
        protected Integer pageNum;
        /**
         * <code>pageSize</code>
         * {@link java.lang.Integer} <p>The <code>pageSize</code> field.</p>
         * @see java.lang.Integer
         */
        protected Integer pageSize;

        /**
         * <code>Builder</code>
         * <p>Instantiates a new builder.</p>
         */
        public Builder() {
        }

        /**
         * <code>pageNum</code>
         * <p>The num method.</p>
         * @param pageNum {@link java.lang.Integer} <p>The page num parameter is <code>Integer</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.filter.PageFilter.Builder} <p>The num return object is <code>Builder</code> type.</p>
         * @see java.lang.Integer
         */
        public PageFilter.Builder pageNum(Integer pageNum) {
            this.pageNum = pageNum;
            return this;
        }

        /**
         * <code>pageSize</code>
         * <p>The size method.</p>
         * @param pageSize {@link java.lang.Integer} <p>The page size parameter is <code>Integer</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.filter.PageFilter.Builder} <p>The size return object is <code>Builder</code> type.</p>
         * @see java.lang.Integer
         */
        public PageFilter.Builder pageSize(Integer pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        /**
         * <code>build</code>
         * <p>The method.</p>
         * @return {@link io.github.nichetoolkit.rice.filter.PageFilter} <p>The return object is <code>PageFilter</code> type.</p>
         */
        public PageFilter build() {
            return new PageFilter(this);
        }
    }

}
