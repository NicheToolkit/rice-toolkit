package io.github.nichetoolkit.rice.filter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.shadow.ShadowMutiField;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.builder.SqlBuilders;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.NonNull;

import java.util.*;


/**
 * <code>NameFilter</code>
 * <p>The name filter class.</p>
 * @param <I>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @see  io.github.nichetoolkit.rice.filter.JsonbFilter
 * @see  lombok.Setter
 * @see  lombok.Getter
 * @see  lombok.experimental.SuperBuilder
 * @see  java.lang.SuppressWarnings
 * @see  com.fasterxml.jackson.annotation.JsonInclude
 * @see  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Setter
@Getter
@SuperBuilder(builderMethodName = "ofNameBuilder")
@SuppressWarnings({"WeakerAccess", "unchecked", "MixedMutabilityReturnType"})
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class NameFilter<I, K> extends JsonbFilter<I, K> {
    /**
     * <code>name</code>
     * {@link java.lang.String} <p>The <code>name</code> field.</p>
     * @see  java.lang.String
     */
    protected String name;
    /**
     * <code>names</code>
     * {@link java.util.Set} <p>The <code>names</code> field.</p>
     * @see  java.util.Set
     * @see  io.github.nichetoolkit.rest.shadow.ShadowMutiField
     */
    @ShadowMutiField
    protected Set<String> names;

    /**
     * <code>NameFilter</code>
     * <p>Instantiates a new name filter.</p>
     */
    public NameFilter() {
    }

    /**
     * <code>NameFilter</code>
     * <p>Instantiates a new name filter.</p>
     * @param id I <p>The id parameter is <code>I</code> type.</p>
     */
    public NameFilter(I id) {
        super(id);
    }

    /**
     * <code>NameFilter</code>
     * <p>Instantiates a new name filter.</p>
     * @param ids I <p>The ids parameter is <code>I</code> type.</p>
     * @see  java.lang.SuppressWarnings
     */
    @SuppressWarnings(value = "unchecked")
    public NameFilter(I... ids) {
        super(ids);
    }

    /**
     * <code>getNames</code>
     * <p>The get names getter method.</p>
     * @return  {@link java.util.List} <p>The get names return object is <code>List</code> type.</p>
     * @see  java.util.List
     */
    public List<String> getNames() {
        if (GeneralUtils.isNotEmpty(names)) {
            return new ArrayList<>(names);
        }
        return null;
    }

    /**
     * <code>setNames</code>
     * <p>The set names setter method.</p>
     * @param names {@link java.lang.String} <p>The names parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    public void setNames(String... names) {
        this.names = Optional.ofNullable(names).map(propertyList -> new HashSet<>(Arrays.asList(propertyList))).orElse(null);
    }

    /**
     * <code>setNames</code>
     * <p>The set names setter method.</p>
     * @param names {@link java.util.Collection} <p>The names parameter is <code>Collection</code> type.</p>
     * @see  java.util.Collection
     * @see  com.fasterxml.jackson.annotation.JsonSetter
     */
    @JsonSetter
    public void setNames(Collection<String> names) {
        this.names = Optional.ofNullable(names).map(HashSet::new).orElse(null);
    }

    /**
     * <code>addNames</code>
     * <p>The add names method.</p>
     * @param names {@link java.lang.String} <p>The names parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    public void addNames(String... names) {
        if (GeneralUtils.isEmpty(this.names)) {
            this.names = Optional.ofNullable(names).map(propertyList -> new HashSet<>(Arrays.asList(propertyList))).orElse(null);
        } else {
            Optional.ofNullable(names).ifPresent(propertyList -> this.names.addAll(Arrays.asList(propertyList)));
        }
    }

    /**
     * <code>addNames</code>
     * <p>The add names method.</p>
     * @param names {@link java.util.Collection} <p>The names parameter is <code>Collection</code> type.</p>
     * @see  java.util.Collection
     */
    public void addNames(Collection<String> names) {
        if (GeneralUtils.isEmpty(this.names)) {
            this.names = Optional.ofNullable(names).map(HashSet::new).orElse(null);
        } else {
            Optional.ofNullable(names).ifPresent(this.names::addAll);
        }
    }

    /**
     * <code>toNameSql</code>
     * <p>The to name sql method.</p>
     * @param alias {@link java.lang.String} <p>The alias parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  org.springframework.lang.NonNull
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link io.github.nichetoolkit.rice.filter.NameFilter} <p>The to name sql return object is <code>NameFilter</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public NameFilter<I,K> toNameSql(@NonNull String alias) throws RestException {
        if (GeneralUtils.isNotEmpty(this.name)) {
            SqlBuilders.like(sqlBuilder(), alias, this.name);
        } else if (GeneralUtils.isNotEmpty(this.names)) {
            SqlBuilders.in(sqlBuilder(), alias, this.names);
        }
        return this;
    }

    @Override
    public NameFilter<I, K> toJsonbSql(@NonNull String alias) throws RestException {
        super.toJsonbSql(alias);
        return this;
    }

    @Override
    public NameFilter<I, K> toJsonbSql(@NonNull String alias, String variable) throws RestException {
        super.toJsonbSql(alias, variable);
        return this;
    }

    @Override
    public NameFilter<I, K> toTimeSql(@NonNull String alias) throws RestException {
        super.toTimeSql(alias);
        return this;
    }

    @Override
    public NameFilter<I, K> toIdSql(@NonNull String alias) throws RestException {
        super.toIdSql(alias);
        return this;
    }

    @Override
    public NameFilter<I, K> toOperateSql(@NonNull String alias) throws RestException {
        super.toOperateSql(alias);
        return this;
    }

}
