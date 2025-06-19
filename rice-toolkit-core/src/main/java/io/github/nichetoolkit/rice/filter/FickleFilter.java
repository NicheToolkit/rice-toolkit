package io.github.nichetoolkit.rice.filter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.github.nichetoolkit.mybatis.fickle.RestFickle;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestKey;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.NonNull;

import java.util.*;

/**
 * <code>FickleFilter</code>
 * <p>The fickle filter class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.filter.OperateFilter
 * @see lombok.experimental.SuperBuilder
 * @see java.lang.SuppressWarnings
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk1.8
 */
@SuperBuilder(builderMethodName = "ofFickleBuilder")
@SuppressWarnings({"WeakerAccess", "MixedMutabilityReturnType"})
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FickleFilter extends OperateFilter {

    /**
     * <code>fickles</code>
     * {@link java.util.Set} <p>The <code>fickles</code> field.</p>
     * @see java.util.Set
     */
    protected Set<String> fickles;

    /**
     * <code>FickleFilter</code>
     * <p>Instantiates a new fickle filter.</p>
     */
    public FickleFilter() {
    }

    /**
     * <code>getFickles</code>
     * <p>The get fickles getter method.</p>
     * @return {@link java.util.List} <p>The get fickles return object is <code>List</code> type.</p>
     * @see java.util.List
     */
    public List<String> getFickles() {
        if (GeneralUtils.isNotEmpty(fickles)) {
            return new ArrayList<>(fickles);
        }
        return Collections.emptyList();
    }

    /**
     * <code>setFickles</code>
     * <p>The set fickles setter method.</p>
     * @param fickles {@link java.lang.String} <p>The fickles parameter is <code>String</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.lang.NonNull
     */
    public void setFickles(@NonNull String... fickles) {
        this.fickles = new HashSet<>(Arrays.asList(fickles));
    }

    /**
     * <code>setFickles</code>
     * <p>The set fickles setter method.</p>
     * @param fickles {@link java.util.Collection} <p>The fickles parameter is <code>Collection</code> type.</p>
     * @see java.util.Collection
     * @see org.springframework.lang.NonNull
     * @see com.fasterxml.jackson.annotation.JsonSetter
     */
    @JsonSetter
    public void setFickles(@NonNull Collection<String> fickles) {
        this.fickles = new HashSet<>(fickles);
    }

    /**
     * <code>setFickles</code>
     * <p>The set fickles setter method.</p>
     * @param fickles {@link java.util.List} <p>The fickles parameter is <code>List</code> type.</p>
     * @see java.util.List
     * @see org.springframework.lang.NonNull
     */
    public void setFickles(@NonNull List<RestKey<String>> fickles) {
        this.fickles = new HashSet<>(RestKey.keys(fickles));
    }

    /**
     * <code>setFickles</code>
     * <p>The set fickles setter method.</p>
     * @param fickles {@link java.util.Map} <p>The fickles parameter is <code>Map</code> type.</p>
     * @see java.util.Map
     * @see org.springframework.lang.NonNull
     */
    public void setFickles(@NonNull Map<String,RestKey<String>> fickles) {
        this.fickles = new HashSet<>(RestKey.keys(fickles.values()));
    }

    /**
     * <code>addFickles</code>
     * <p>The add fickles method.</p>
     * @param fickles {@link java.lang.String} <p>The fickles parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.filter.FickleFilter} <p>The add fickles return object is <code>FickleFilter</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.lang.NonNull
     */
    public FickleFilter addFickles(@NonNull String... fickles) {
        if (GeneralUtils.isEmpty(this.fickles)) {
            this.fickles = new HashSet<>(Arrays.asList(fickles));
        } else {
            this.fickles.addAll(Arrays.asList(fickles));
        }
        return this;
    }

    /**
     * <code>addFickles</code>
     * <p>The add fickles method.</p>
     * @param fickles {@link java.util.Collection} <p>The fickles parameter is <code>Collection</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.filter.FickleFilter} <p>The add fickles return object is <code>FickleFilter</code> type.</p>
     * @see java.util.Collection
     * @see org.springframework.lang.NonNull
     */
    public FickleFilter addFickles(@NonNull Collection<String> fickles) {
        if (GeneralUtils.isEmpty(this.fickles)) {
            this.fickles = new HashSet<>(fickles);
        } else {
            this.fickles.addAll(fickles);
        }
        return this;
    }

    /**
     * <code>addFickles</code>
     * <p>The add fickles method.</p>
     * @param fickles {@link java.util.List} <p>The fickles parameter is <code>List</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.filter.FickleFilter} <p>The add fickles return object is <code>FickleFilter</code> type.</p>
     * @see java.util.List
     * @see org.springframework.lang.NonNull
     */
    public final FickleFilter addFickles(@NonNull List<RestKey<String>> fickles) {
        if (GeneralUtils.isEmpty(this.fickles)) {
            this.fickles = new HashSet<>(RestKey.keys(fickles));
        } else {
            this.fickles.addAll(RestKey.keys(fickles));
        }
        return this;
    }

    /**
     * <code>addFickles</code>
     * <p>The add fickles method.</p>
     * @param fickles {@link java.util.Map} <p>The fickles parameter is <code>Map</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.filter.FickleFilter} <p>The add fickles return object is <code>FickleFilter</code> type.</p>
     * @see java.util.Map
     * @see org.springframework.lang.NonNull
     */
    public final FickleFilter addFickles(@NonNull Map<String,RestKey<String>> fickles) {
        if (GeneralUtils.isEmpty(this.fickles)) {
            this.fickles = new HashSet<>(RestKey.keys(fickles.values()));
        } else {
            this.fickles.addAll(RestKey.keys(fickles.values()));
        }
        return this;
    }

    /**
     * <code>toFickleArray</code>
     * <p>The to fickle array method.</p>
     * @return {@link io.github.nichetoolkit.mybatis.fickle.RestFickle} <p>The to fickle array return object is <code>RestFickle</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.mybatis.fickle.RestFickle
     * @see io.github.nichetoolkit.rest.RestException
     */
    public RestFickle<?>[] toFickleArray() throws RestException {
        if (GeneralUtils.isEmpty(this.fickles)) {
            return new RestFickle<?>[0];
        }
        return this.fickles.stream().map(RestFickle::of).toArray(RestFickle<?>[]::new);
    }




}
