package io.github.nichetoolkit.rice.filter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.github.nichetoolkit.rest.util.common.GeneralUtils;
import io.github.nichetoolkit.rice.RestSort;
import org.springframework.lang.NonNull;

import java.util.*;

/**
 * <p>SortFilter</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@SuppressWarnings({"WeakerAccess","UnusedReturnValue","MixedMutabilityReturnType"})
public class SortFilter extends PageFilter {
    @JsonIgnore
    public static final String SORT_REGEX = ",";
    @JsonIgnore
    public static final String SORT_ORDER = " ORDER BY ";

    protected Set<RestSort> sorts;

    public SortFilter() {
    }

    public SortFilter(@NonNull RestSort... sorts) {
        this.sorts = new HashSet<>(Arrays.asList(sorts));
    }

    public SortFilter(@NonNull String... sorts) {
        this.sorts = new HashSet<>(RestSort.build(sorts));
    }

    public SortFilter(@NonNull Collection<String> sorts) {
        this.sorts = new HashSet<>(RestSort.build(sorts));
    }

    public SortFilter(SortFilter.Builder builder) {
        super(builder);
        this.sorts = builder.sorts;
    }

    public List<RestSort> getSorts() {
        if (GeneralUtils.isNotEmpty(sorts)) {
            return new ArrayList<>(sorts);
        }
        return Collections.emptyList();
    }

    public void setSorts(@NonNull String... sorts) {
        this.setSorts(RestSort.build(sorts));
    }

    public void setSorts(@NonNull RestSort... sorts) {
        this.sorts = new HashSet<>(Arrays.asList(sorts));
    }

    @JsonSetter
    public void setSorts(@NonNull Collection<RestSort> sorts) {
        this.sorts = new HashSet<>(sorts);
    }

    public SortFilter addSorts(@NonNull String... sorts) {
        if (GeneralUtils.isEmpty(this.sorts)) {
            this.sorts = new HashSet<>(RestSort.build(sorts));
        } else {
            this.sorts.addAll(RestSort.build(sorts));
        }
        return this;
    }

    public SortFilter addSorts(@NonNull RestSort... sorts) {
        if (GeneralUtils.isEmpty(this.sorts)) {
            this.sorts = new HashSet<>(Arrays.asList(sorts));
        } else {
            this.sorts.addAll(Arrays.asList(sorts));
        }
        return this;
    }

    public SortFilter addSorts(@NonNull Collection<RestSort> sorts) {
        if (GeneralUtils.isEmpty(this.sorts)) {
            this.sorts = new HashSet<>(sorts);
        } else {
            this.sorts.addAll(sorts);
        }
        return this;
    }

    public String toSort() {
        StringBuilder sortBuilder = new StringBuilder();
        if (GeneralUtils.isNotEmpty(this.sorts)) {
            sortBuilder.append(SORT_ORDER);
            this.sorts.forEach(sort -> sortBuilder.append(sort.toString()).append(SORT_REGEX));
            sortBuilder.deleteCharAt(sortBuilder.length() - 1);
        }
        return sortBuilder.toString();
    }

    public static class Builder extends PageFilter.Builder {
        protected Set<RestSort> sorts;

        public Builder() {
        }

        public SortFilter.Builder sorts(@NonNull Collection<RestSort> sorts) {
            this.sorts = new HashSet<>(sorts);
            return this;
        }

        public SortFilter.Builder sorts(@NonNull RestSort... sorts) {
            this.sorts = new HashSet<>(Arrays.asList(sorts));
            return this;
        }

        @Override
        public SortFilter.Builder pageNum(Integer pageNum) {
            this.pageNum = pageNum;
            return this;
        }

        @Override
        public SortFilter.Builder pageSize(Integer pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        @Override
        public SortFilter build() {
            return new SortFilter(this);
        }
    }
}
