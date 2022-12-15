package io.github.nichetoolkit.rice.filter;

import com.fasterxml.jackson.annotation.JsonSetter;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.RestOperate;
import io.github.nichetoolkit.rice.enums.OperateType;
import org.springframework.lang.NonNull;

import java.util.*;

/**
 * <p>OperateFilter</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@SuppressWarnings({"WeakerAccess", "MixedMutabilityReturnType"})
public class OperateFilter extends SortFilter {
    /** 是否逻辑删除 */
    protected boolean isRemove;

    protected OperateType operate;

    protected Set<OperateType> operates;

    public OperateFilter() {
    }

    public OperateFilter(OperateFilter.Builder builder) {
        super(builder);
        this.isRemove = builder.isRemove;
        this.operate = builder.operate;
        this.operates = builder.operates;
    }

    public boolean isRemove() {
        return isRemove;
    }

    public void setRemove(boolean remove) {
        isRemove = remove;
    }

    public OperateType getOperate() {
        return operate;
    }

    public void setOperate(OperateType operate) {
        this.operate = operate;
    }

    public List<OperateType> getOperates() {
        if (GeneralUtils.isNotEmpty(operates)) {
            return new ArrayList<>(operates);
        }
        return Collections.emptyList();
    }

    public void setOperates(@NonNull Integer... operates) {
        this.setOperates(RestOperate.build(operates));
    }

    public void setOperates(@NonNull OperateType... operates) {
        this.operates = new HashSet<>(Arrays.asList(operates));
    }

    @JsonSetter
    public void setOperates(@NonNull Collection<OperateType> operates) {
        this.operates = new HashSet<>(operates);
    }

    public OperateFilter addOperates(@NonNull Integer... operates) {
        if (GeneralUtils.isEmpty(this.operates)) {
            this.operates = new HashSet<>(RestOperate.build(operates));
        } else {
            this.operates.addAll(RestOperate.build(operates));
        }
        return this;
    }

    public OperateFilter addOperates(@NonNull OperateType... operates) {
        if (GeneralUtils.isEmpty(this.operates)) {
            this.operates = new HashSet<>(Arrays.asList(operates));
        } else {
            this.operates.addAll(Arrays.asList(operates));
        }
        return this;
    }

    public OperateFilter addOperates(@NonNull Collection<OperateType> operates) {
        if (GeneralUtils.isEmpty(this.operates)) {
            this.operates = new HashSet<>(operates);
        } else {
            this.operates.addAll(operates);
        }
        return this;
    }

    public static class Builder extends SortFilter.Builder {
        protected boolean isRemove;
        protected OperateType operate;
        protected Set<OperateType> operates;

        public Builder() {
        }

        public OperateFilter.Builder isRemove(boolean isRemove) {
            this.isRemove = isRemove;
            return this;
        }

        public OperateFilter.Builder operate(OperateType operate) {
            this.operate = operate;
            return this;
        }

        public OperateFilter.Builder operate(Integer operate) {
            this.operate = OperateType.parseKey(operate);
            return this;
        }

        public OperateFilter.Builder operates(@NonNull Collection<OperateType> operates) {
            this.operates = new HashSet<>(operates);
            return this;
        }

        public OperateFilter.Builder operates(@NonNull OperateType... operates) {
            this.operates = new HashSet<>(Arrays.asList(operates));
            return this;
        }

        public OperateFilter.Builder operates(@NonNull Integer... operates) {
            this.operates = new HashSet<>(RestOperate.build(operates));
            return this;
        }

        @Override
        public OperateFilter.Builder pageNum(Integer pageNum) {
            this.pageNum = pageNum;
            return this;
        }

        @Override
        public OperateFilter.Builder pageSize(Integer pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        @Override
        public OperateFilter build() {
            return new OperateFilter(this);
        }
    }


}
