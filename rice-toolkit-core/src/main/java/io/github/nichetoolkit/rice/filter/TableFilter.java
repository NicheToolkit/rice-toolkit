package io.github.nichetoolkit.rice.filter;

import io.github.nichetoolkit.rice.RestOperate;
import io.github.nichetoolkit.rice.RestSort;
import io.github.nichetoolkit.rice.enums.OperateType;
import org.springframework.lang.NonNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

/**
 * <p>TableFilter</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class TableFilter extends OperateFilter {
    /** 动态表key */
    protected String tableKey;

    public TableFilter() {
    }

    public TableFilter(TableFilter.Builder builder) {
        super(builder);
        this.tableKey = builder.tableKey;
    }

    public String getTableKey() {
        return tableKey;
    }

    public void setTableKey(String tableKey) {
        this.tableKey = tableKey;
    }
    
    public String toTableKey() {
        return this.tableKey;
    }

    public static class Builder extends OperateFilter.Builder {
        protected String tableKey;
        public Builder() {
        }

        public TableFilter.Builder tableKey(String tableKey) {
            this.tableKey = tableKey;
            return this;
        }

        @Override
        public TableFilter.Builder isRemove(boolean isRemove) {
            this.isRemove = isRemove;
            return this;
        }

        @Override
        public TableFilter.Builder operate(OperateType operate) {
            this.operate = operate;
            return this;
        }

        @Override
        public TableFilter.Builder operate(Integer operate) {
            this.operate = OperateType.parseKey(operate);
            return this;
        }

        @Override
        public TableFilter.Builder operates(@NonNull Collection<OperateType> operates) {
            this.operates = new HashSet<>(operates);
            return this;
        }

        @Override
        public TableFilter.Builder operates(@NonNull OperateType... operates) {
            this.operates = new HashSet<>(Arrays.asList(operates));
            return this;
        }

        @Override
        public TableFilter.Builder operates(@NonNull Integer... operates) {
            this.operates = new HashSet<>(RestOperate.build(operates));
            return this;
        }

        @Override
        public TableFilter.Builder sorts(@NonNull Collection<RestSort> sorts) {
            this.sorts = new HashSet<>(sorts);
            return this;
        }

        @Override
        public TableFilter.Builder sorts(@NonNull RestSort... sorts) {
            this.sorts = new HashSet<>(Arrays.asList(sorts));
            return this;
        }

        @Override
        public TableFilter.Builder sorts(@NonNull String... sorts) {
            this.sorts = new HashSet<>(RestSort.build(sorts));
            return this;
        }
        
        @Override
        public TableFilter.Builder pageNum(Integer pageNum) {
            this.pageNum = pageNum;
            return this;
        }

        @Override
        public TableFilter.Builder pageSize(Integer pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        @Override
        public TableFilter build() {
            return new TableFilter(this);
        }
    }
    
    
}
