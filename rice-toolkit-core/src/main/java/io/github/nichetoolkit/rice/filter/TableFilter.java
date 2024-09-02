package io.github.nichetoolkit.rice.filter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.rice.RestOperate;
import io.github.nichetoolkit.rice.RestSort;
import io.github.nichetoolkit.rice.enums.OperateType;
import org.springframework.lang.NonNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

/**
 * <code>TableFilter</code>
 * <p>The type table filter class.</p>
 * @param <K> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.filter.OperateFilter
 * @see java.lang.SuppressWarnings
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk1.8
 */
@SuppressWarnings("WeakerAccess")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TableFilter<K> extends OperateFilter {
    /**
     * <code>tablekey</code>
     * <p>the <code>tablekey</code> field.</p>
     */
    protected K tablekey;

    /**
     * <code>TableFilter</code>
     * Instantiates a new table filter.
     */
    public TableFilter() {
    }

    /**
     * <code>TableFilter</code>
     * Instantiates a new table filter.
     * @param builder {@link io.github.nichetoolkit.rice.filter.TableFilter.Builder} <p>the builder parameter is <code>Builder</code> type.</p>
     * @see io.github.nichetoolkit.rice.filter.TableFilter.Builder
     */
    public TableFilter(TableFilter.Builder<K> builder) {
        super(builder);
        this.tablekey = builder.tablekey;
    }

    /**
     * <code>getTablekey</code>
     * <p>the tablekey getter method.</p>
     * @return K <p>the tablekey return object is <code>K</code> type.</p>
     */
    public K getTablekey() {
        return tablekey;
    }

    /**
     * <code>setTablekey</code>
     * <p>the tablekey setter method.</p>
     * @param tablekey K <p>the tablekey parameter is <code>K</code> type.</p>
     */
    public void setTablekey(K tablekey) {
        this.tablekey = tablekey;
    }

    /**
     * <code>toTablekey</code>
     * <p>the tablekey method.</p>
     * @return K <p>the tablekey return object is <code>K</code> type.</p>
     */
    public K toTablekey() {
        return this.tablekey;
    }

    /**
     * <code>Builder</code>
     * <p>The type builder class.</p>
     * @param <K> {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rice.filter.OperateFilter.Builder
     * @since Jdk1.8
     */
    public static class Builder<K> extends OperateFilter.Builder {
        /**
         * <code>tablekey</code>
         * <p>the <code>tablekey</code> field.</p>
         */
        protected K tablekey;

        /**
         * <code>Builder</code>
         * Instantiates a new builder.
         */
        public Builder() {
        }

        /**
         * <code>tablekey</code>
         * <p>the method.</p>
         * @param tablekey K <p>the tablekey parameter is <code>K</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.filter.TableFilter.Builder} <p>the return object is <code>Builder</code> type.</p>
         */
        public TableFilter.Builder<K> tablekey(K tablekey) {
            this.tablekey = tablekey;
            return this;
        }

        @Override
        public TableFilter.Builder<K> isRemove(boolean isRemove) {
            this.isRemove = isRemove;
            return this;
        }

        @Override
        public TableFilter.Builder<K> operate(OperateType operate) {
            this.operate = operate;
            return this;
        }

        @Override
        public TableFilter.Builder<K> operate(Integer operate) {
            this.operate = OperateType.parseKey(operate);
            return this;
        }

        @Override
        public TableFilter.Builder<K> operates(@NonNull Collection<OperateType> operates) {
            this.operates = new HashSet<>(operates);
            return this;
        }

        @Override
        public TableFilter.Builder<K> operates(@NonNull OperateType... operates) {
            this.operates = new HashSet<>(Arrays.asList(operates));
            return this;
        }

        @Override
        public TableFilter.Builder<K> operates(@NonNull Integer... operates) {
            this.operates = new HashSet<>(RestOperate.build(operates));
            return this;
        }

        @Override
        public TableFilter.Builder<K> sorts(@NonNull Collection<RestSort<?>> sorts) {
            this.sorts = new HashSet<>(sorts);
            return this;
        }

        @Override
        public TableFilter.Builder<K> sorts(@NonNull RestSort<?>... sorts) {
            this.sorts = new HashSet<>(Arrays.asList(sorts));
            return this;
        }

        @Override
        public TableFilter.Builder<K> sorts(@NonNull String... sorts) {
            this.sorts = new HashSet<>(RestSort.build(sorts));
            return this;
        }

        @Override
        public TableFilter.Builder<K> pageNum(Integer pageNum) {
            this.pageNum = pageNum;
            return this;
        }

        @Override
        public TableFilter.Builder<K> pageSize(Integer pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        @Override
        public TableFilter<K> build() {
            return new TableFilter<>(this);
        }
    }


}
