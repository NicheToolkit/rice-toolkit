package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.builder.SqlBuilders;
import io.github.nichetoolkit.rice.configure.RiceBeanProperties;
import io.github.nichetoolkit.rice.enums.DeleteType;
import io.github.nichetoolkit.rice.enums.OperateType;
import io.github.nichetoolkit.rice.enums.RemoveType;
import io.github.nichetoolkit.rice.filter.IdFilter;
import io.github.nichetoolkit.rice.filter.NameFilter;
import io.github.nichetoolkit.rice.jsonb.ContainRule;
import io.github.nichetoolkit.rice.jsonb.ContrastRule;
import io.github.nichetoolkit.rice.jsonb.EqualRule;
import io.github.nichetoolkit.rice.jsonb.RangeRule;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;

import java.util.*;

public abstract class BaseFilter<I, K> extends NameFilter<I, K> implements InitializingBean, ApplicationContextAware {

    private static ApplicationContext applicationContext;

    protected RiceBeanProperties beanProperties;

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        BaseFilter.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() {
        this.beanProperties = applicationContext.getBean(RiceBeanProperties.class);
    }

    public BaseFilter() {
    }

    @SuppressWarnings(value = "unchecked")
    public BaseFilter(I... ids) {
        super(ids);
    }

    public BaseFilter(BaseFilter.Builder<I, K> builder) {
        super(builder);
    }

    public BaseFilter<I, K> toRemoveSql(RemoveType removeType,String removeSign,Boolean removeIndex,String removeValue, @NonNull String alias) {
        if (GeneralUtils.isNotEmpty(removeSign)) {
            if (removeType == RemoveType.BOOLEAN || removeType == RemoveType.NUMBER) {
                if (removeIndex && GeneralUtils.isNotEmpty(removeValue)) {
                    if (this.isRemove) {
                        SqlBuilders.equal(SQL_BUILDER, alias, removeSign);
                    } else {
                        SqlBuilders.equal(SQL_BUILDER, alias, removeValue);
                    }
                } else {
                    if (this.isRemove) {
                        SqlBuilders.equal(SQL_BUILDER, alias, removeSign);
                    } else {
                        SqlBuilders.unequal(SQL_BUILDER, alias, removeSign);
                    }
                }
            } else if (removeType == RemoveType.DATETIME || removeType == RemoveType.IDENTITY) {
                if (this.isRemove) {
                    SqlBuilders.nonnull(SQL_BUILDER, alias);
                } else {
                    SqlBuilders.isnull(SQL_BUILDER, alias);
                }
            }
        }
        return this;
    }

    public BaseFilter<I, K> toQuerySql(DeleteType deleteType,RemoveType removeType,String removeSign,Boolean removeIndex,String removeValue, @NonNull String alias) {
        if (deleteType == DeleteType.OPERATE) {
            return toOperateSql(alias);
        } else if (deleteType == DeleteType.REMOVE) {
            return toRemoveSql(removeType,removeSign,removeIndex,removeValue, alias);
        }
        return this;
    }

    @Override
    public BaseFilter<I, K> toNameSql(@NonNull String alias) {
        super.toNameSql(alias);
        return this;
    }

    @Override
    public BaseFilter<I, K> toJsonbSql(@NonNull String alias) {
        super.toJsonbSql(alias);
        return this;
    }

    @Override
    public BaseFilter<I, K> toJsonbSql(@NonNull String alias, String variable) {
        super.toJsonbSql(alias, variable);
        return this;
    }

    @Override
    public BaseFilter<I, K> toTimeSql(@NonNull String alias) {
        super.toTimeSql(alias);
        return this;
    }

    @Override
    public BaseFilter<I, K> toIdSql(@NonNull String alias) {
        super.toIdSql(alias);
        return this;
    }

    @Override
    public BaseFilter<I, K> toOperateSql(@NonNull String alias) {
        super.toOperateSql(alias);
        return this;
    }

    public static abstract class Builder<I, K> extends NameFilter.Builder<I, K> {

        public Builder() {
        }

        @Override
        public BaseFilter.Builder<I, K> name(String name) {
            this.name = name;
            return this;
        }

        @Override
        public BaseFilter.Builder<I, K> names(Collection<String> names) {
            this.names = Optional.ofNullable(names).map(HashSet::new).orElse(null);
            return this;
        }

        @Override
        public BaseFilter.Builder<I, K> names(String... names) {
            this.names = Optional.ofNullable(names).map(propertyList -> new HashSet<>(Arrays.asList(propertyList))).orElse(null);
            return this;
        }

        @Override
        public BaseFilter.Builder<I, K> contrasts(Collection<ContrastRule> contrasts) {
            this.contrasts = Optional.ofNullable(contrasts).map(HashSet::new).orElse(null);
            return this;
        }

        @Override
        public BaseFilter.Builder<I, K> contrasts(ContrastRule... contrasts) {
            this.contrasts = Optional.ofNullable(contrasts).map(contrastList -> new HashSet<>(Arrays.asList(contrastList))).orElse(null);
            return this;
        }

        @Override
        public BaseFilter.Builder<I, K> ranges(Collection<RangeRule> ranges) {
            this.ranges = Optional.ofNullable(ranges).map(HashSet::new).orElse(null);
            return this;
        }

        @Override
        public BaseFilter.Builder<I, K> ranges(RangeRule... ranges) {
            this.ranges = Optional.ofNullable(ranges).map(rangeList -> new HashSet<>(Arrays.asList(rangeList))).orElse(null);
            return this;
        }

        @Override
        public BaseFilter.Builder<I, K> equals(Collection<EqualRule> equals) {
            this.equals = Optional.ofNullable(equals).map(HashSet::new).orElse(null);
            return this;
        }

        @Override
        public BaseFilter.Builder<I, K> equals(EqualRule... equals) {
            this.equals = Optional.ofNullable(equals).map(equalList -> new HashSet<>(Arrays.asList(equalList))).orElse(null);
            return this;
        }

        @Override
        public BaseFilter.Builder<I, K> contains(Collection<ContainRule> contains) {
            this.contains = Optional.ofNullable(contains).map(HashSet::new).orElse(null);
            return this;
        }

        @Override
        public BaseFilter.Builder<I, K> contains(ContainRule... contains) {
            this.contains = Optional.ofNullable(contains).map(containList -> new HashSet<>(Arrays.asList(containList))).orElse(null);
            return this;
        }

        @Override
        public BaseFilter.Builder<I, K> startTime(Date startTime) {
            this.startTime = startTime;
            return this;
        }

        @Override
        public BaseFilter.Builder<I, K> endTime(Date endTime) {
            this.endTime = endTime;
            return this;
        }

        @Override
        public BaseFilter.Builder<I, K> ids(@NonNull Collection<I> ids) {
            this.ids = new HashSet<>(ids);
            return this;
        }

        @SuppressWarnings(value = "unchecked")
        @Override
        public BaseFilter.Builder<I, K> ids(@NonNull I... ids) {
            this.ids = new HashSet<>(Arrays.asList(ids));
            return this;
        }

        @Override
        public BaseFilter.Builder<I, K> tablekey(K tablekey) {
            this.tablekey = tablekey;
            return this;
        }

        @Override
        public BaseFilter.Builder<I, K> isRemove(boolean isRemove) {
            this.isRemove = isRemove;
            return this;
        }

        @Override
        public BaseFilter.Builder<I, K> operate(OperateType operate) {
            this.operate = operate;
            return this;
        }

        @Override
        public BaseFilter.Builder<I, K> operate(Integer operate) {
            this.operate = OperateType.parseKey(operate);
            return this;
        }

        @Override
        public BaseFilter.Builder<I, K> operates(@NonNull Collection<OperateType> operates) {
            this.operates = new HashSet<>(operates);
            return this;
        }

        @Override
        public BaseFilter.Builder<I, K> operates(@NonNull OperateType... operates) {
            this.operates = new HashSet<>(Arrays.asList(operates));
            return this;
        }

        @Override
        public BaseFilter.Builder<I, K> operates(@NonNull Integer... operates) {
            this.operates = new HashSet<>(RestOperate.build(operates));
            return this;
        }

        @Override
        public BaseFilter.Builder<I, K> sorts(@NonNull Collection<RestSort<?>> sorts) {
            this.sorts = new HashSet<>(sorts);
            return this;
        }

        @Override
        public BaseFilter.Builder<I, K> sorts(@NonNull RestSort<?>... sorts) {
            this.sorts = new HashSet<>(Arrays.asList(sorts));
            return this;
        }

        @Override
        public BaseFilter.Builder<I, K> sorts(@NonNull String... sorts) {
            this.sorts = new HashSet<>(RestSort.build(sorts));
            return this;
        }

        @Override
        public BaseFilter.Builder<I, K> pageNum(Integer pageNum) {
            this.pageNum = pageNum;
            return this;
        }

        @Override
        public BaseFilter.Builder<I, K> pageSize(Integer pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        @Override
        public abstract BaseFilter<I, K> build();
    }
}
