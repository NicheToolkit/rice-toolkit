package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.enums.SortType;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>Sort</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@SuppressWarnings("WeakerAccess")
public class RestSort<S extends RestSort<S>> implements Serializable {
    protected String name;
    protected SortType type = SortType.DESC;

    public RestSort() {
    }

    public RestSort(String name) {
        this.name = name;
    }

    public RestSort(String name, SortType type) {
        this.name = name;
        this.type = type;
    }

    public RestSort(RestSort.Builder<S> builder) {
        this.name = builder.name;
        this.type = builder.type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SortType getType() {
        return type;
    }

    public void setType(SortType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RestSort)) return false;
        RestSort sort = (RestSort) o;
        return Objects.equals(name, sort.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name.concat(" ").concat(type.getKey());
    }

    public static List<RestSort> build(String... sorts) {
        if (GeneralUtils.isEmpty(sorts)) {
            return Collections.emptyList();
        }
        return Arrays.stream(sorts).map(RestSort::new).collect(Collectors.toList());
    }

    public static List<RestSort> build(Collection<String> sorts) {
        if (GeneralUtils.isEmpty(sorts)) {
            return Collections.emptyList();
        }
        return sorts.stream().map(RestSort::new).collect(Collectors.toList());
    }

    public static class Builder<S extends RestSort<S>> {
        protected String name;
        protected SortType type = SortType.DESC;
        public Builder() {
        }

        public RestSort.Builder<S> name(String name) {
            this.name = name;
            return this;
        }

        public RestSort.Builder<S> type(SortType type) {
            this.type = type;
            return this;
        }

        public RestSort.Builder<S> type(String type) {
            this.type = SortType.parseKey(type);
            return this;
        }
        
        public RestSort<S> build() {
            return new RestSort<>(this);
        }
    }

}
