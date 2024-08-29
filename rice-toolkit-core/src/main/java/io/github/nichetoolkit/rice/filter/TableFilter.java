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

@SuppressWarnings("WeakerAccess")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TableFilter<K> extends OperateFilter {
    protected K tablekey;

    public TableFilter() {
    }

    public TableFilter(TableFilter.Builder<K> builder) {
        super(builder);
        this.tablekey = builder.tablekey;
    }

    public K getTablekey() {
        return tablekey;
    }

    public void setTablekey(K tablekey) {
        this.tablekey = tablekey;
    }

    public K toTablekey() {
        return this.tablekey;
    }

    public static class Builder<K> extends OperateFilter.Builder {
        protected K tablekey;

        public Builder() {
        }

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
