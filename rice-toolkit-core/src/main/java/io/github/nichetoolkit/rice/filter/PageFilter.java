package io.github.nichetoolkit.rice.filter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.github.nichetoolkit.mybatis.load.RestLoad;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.parsing.JsonParsingIgnored;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.util.JsonUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <code>PageFilter</code>
 * <p>The page filter class.</p>
 * @see  java.io.Serializable
 * @see  java.lang.SuppressWarnings
 * @see  lombok.experimental.SuperBuilder
 * @see  com.fasterxml.jackson.annotation.JsonInclude
 * @see  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@SuppressWarnings("WeakerAccess")
@SuperBuilder(builderMethodName = "ofPageBuilder")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageFilter implements Serializable {
    /**
     * <code>PAGE_REGEX</code>
     * {@link java.lang.String} <p>The constant <code>PAGE_REGEX</code> field.</p>
     * @see  java.lang.String
     * @see  com.fasterxml.jackson.annotation.JsonIgnore
     * @see  io.github.nichetoolkit.rest.parsing.JsonParsingIgnored
     */
    @JsonIgnore
    @JsonParsingIgnored
    public static final String PAGE_REGEX = "_";
    /**
     * <code>PAGE_LIMIT</code>
     * {@link java.lang.String} <p>The constant <code>PAGE_LIMIT</code> field.</p>
     * @see  java.lang.String
     * @see  com.fasterxml.jackson.annotation.JsonIgnore
     * @see  io.github.nichetoolkit.rest.parsing.JsonParsingIgnored
     */
    @JsonIgnore
    @JsonParsingIgnored
    public static final String PAGE_LIMIT = "LIMIT";
    /**
     * <code>PAGE_OFFSET</code>
     * {@link java.lang.String} <p>The constant <code>PAGE_OFFSET</code> field.</p>
     * @see  java.lang.String
     * @see  com.fasterxml.jackson.annotation.JsonIgnore
     * @see  io.github.nichetoolkit.rest.parsing.JsonParsingIgnored
     */
    @JsonIgnore
    @JsonParsingIgnored
    public static final String PAGE_OFFSET = "OFFSET";
    /**
     * <code>FIELD_ARRAY</code>
     * {@link java.util.List} <p>The <code>FIELD_ARRAY</code> field.</p>
     * @see  java.util.List
     * @see  com.fasterxml.jackson.annotation.JsonIgnore
     * @see  io.github.nichetoolkit.rest.parsing.JsonParsingIgnored
     */
    @JsonIgnore
    @JsonParsingIgnored
    protected final List<String> FIELD_ARRAY = new ArrayList<>();
    /**
     * <code>LOAD_ARRAY</code>
     * {@link java.util.List} <p>The <code>LOAD_ARRAY</code> field.</p>
     * @see  java.util.List
     * @see  com.fasterxml.jackson.annotation.JsonIgnore
     * @see  io.github.nichetoolkit.rest.parsing.JsonParsingIgnored
     */
    @JsonIgnore
    @JsonParsingIgnored
    protected final List<RestLoad> LOAD_ARRAY = new ArrayList<>();

    /**
     * <code>pageNum</code>
     * {@link java.lang.Integer} <p>The <code>pageNum</code> field.</p>
     * @see  java.lang.Integer
     * @see  lombok.Getter
     */
    @Getter
    protected Integer pageNum = 1;
    /**
     * <code>pageSize</code>
     * {@link java.lang.Integer} <p>The <code>pageSize</code> field.</p>
     * @see  java.lang.Integer
     * @see  lombok.Getter
     * @see  lombok.Setter
     */
    @Getter
    @Setter
    protected Integer pageSize = 0;
    /**
     * <code>loadLastPage</code>
     * {@link java.lang.Boolean} <p>The <code>loadLastPage</code> field.</p>
     * @see  java.lang.Boolean
     * @see  lombok.Getter
     * @see  lombok.Setter
     */
    @Getter
    @Setter
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
     * @param pageNum {@link java.lang.Integer} <p>The page num parameter is <code>Integer</code> type.</p>
     * @param pageSize {@link java.lang.Integer} <p>The page size parameter is <code>Integer</code> type.</p>
     * @see  java.lang.Integer
     */
    public PageFilter(Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    /**
     * <code>setPageNum</code>
     * <p>The set page num setter method.</p>
     * @param pageNum {@link java.lang.Integer} <p>The page num parameter is <code>Integer</code> type.</p>
     * @see  java.lang.Integer
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

    @Override
    public String toString() {
        return JsonUtils.parseJson(this);
    }

    /**
     * <code>toPageSql</code>
     * <p>The to page sql method.</p>
     * @return  {@link java.lang.String} <p>The to page sql return object is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  io.github.nichetoolkit.rest.RestException
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public String toPageSql() throws RestException {
        return PAGE_LIMIT + " " + this.pageSize +
                PAGE_OFFSET + " " + (this.pageNum - 1);
    }

    /**
     * <code>toPage</code>
     * <p>The to page method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @return  {@link com.github.pagehelper.Page} <p>The to page return object is <code>Page</code> type.</p>
     * @see  com.github.pagehelper.Page
     * @see  io.github.nichetoolkit.rest.RestException
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public <T> Page<T> toPage() throws RestException {
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
     * <p>The to load array method.</p>
     * @return  {@link io.github.nichetoolkit.mybatis.load.RestLoad} <p>The to load array return object is <code>RestLoad</code> type.</p>
     * @see  io.github.nichetoolkit.mybatis.load.RestLoad
     * @see  io.github.nichetoolkit.rest.RestException
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public RestLoad[] toLoadArray() throws RestException {
        RestLoad[] toArray = LOAD_ARRAY.toArray(new RestLoad[0]);
        LOAD_ARRAY.clear();
        return toArray;
    }

    /**
     * <code>toLoadArray</code>
     * <p>The to load array method.</p>
     * @param isLoads {@link io.github.nichetoolkit.mybatis.load.RestLoad} <p>The is loads parameter is <code>RestLoad</code> type.</p>
     * @see  io.github.nichetoolkit.mybatis.load.RestLoad
     * @see  org.springframework.lang.NonNull
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link io.github.nichetoolkit.mybatis.load.RestLoad} <p>The to load array return object is <code>RestLoad</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public RestLoad[] toLoadArray(@NonNull RestLoad... isLoads) throws RestException {
        this.addLoadArray(isLoads);
        return toLoadArray();
    }

    /**
     * <code>toFieldArray</code>
     * <p>The to field array method.</p>
     * @return  {@link java.lang.String} <p>The to field array return object is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  io.github.nichetoolkit.rest.RestException
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public String[] toFieldArray() throws RestException {
        String[] toArray = FIELD_ARRAY.toArray(new String[0]);
        FIELD_ARRAY.clear();
        return toArray;
    }

    /**
     * <code>toFieldArray</code>
     * <p>The to field array method.</p>
     * @param fields {@link java.lang.String} <p>The fields parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  org.springframework.lang.NonNull
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link java.lang.String} <p>The to field array return object is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public String[] toFieldArray(@NonNull String... fields) throws RestException {
        this.addFieldArray(fields);
        return toFieldArray();
    }

    /**
     * <code>addLoadArray</code>
     * <p>The add load array method.</p>
     * @param isLoads {@link io.github.nichetoolkit.mybatis.load.RestLoad} <p>The is loads parameter is <code>RestLoad</code> type.</p>
     * @see  io.github.nichetoolkit.mybatis.load.RestLoad
     * @see  org.springframework.lang.NonNull
     */
    public void addLoadArray(@NonNull RestLoad... isLoads) {
        LOAD_ARRAY.addAll(Arrays.asList(isLoads));
    }

    /**
     * <code>addFieldArray</code>
     * <p>The add field array method.</p>
     * @param fields {@link java.lang.String} <p>The fields parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  org.springframework.lang.NonNull
     */
    public void addFieldArray(@NonNull String... fields) {
        FIELD_ARRAY.addAll(Arrays.asList(fields));
    }

    /**
     * <code>name</code>
     * <p>The name method.</p>
     * @return  {@link java.lang.String} <p>The name return object is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    public String name() {
        return this.getClass().getSimpleName();
    }

    /**
     * <code>toKey</code>
     * <p>The to key method.</p>
     * @return  {@link java.lang.String} <p>The to key return object is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    public String toKey() {
        return this.pageNum + PAGE_REGEX + this.pageSize;
    }
}
