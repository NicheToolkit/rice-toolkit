package io.github.nichetoolkit.rice.filter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.rice.RestTableKey;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * <code>TableFilter</code>
 * <p>The table filter class.</p>
 * @param <K> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.filter.FickleFilter
 * @see lombok.Setter
 * @see lombok.Getter
 * @see java.lang.SuppressWarnings
 * @see lombok.experimental.SuperBuilder
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk17
 */
@Setter
@Getter
@SuppressWarnings("WeakerAccess")
@SuperBuilder(builderMethodName = "ofTableBuilder")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TableFilter<K> extends FickleFilter {
    /**
     * <code>tableKey</code>
     * <p>The <code>tableKey</code> field.</p>
     */
    protected K tableKey;

    /**
     * <code>TableFilter</code>
     * <p>Instantiates a new table filter.</p>
     */
    public TableFilter() {
    }

    /**
     * <code>toTableKey</code>
     * <p>The to table key method.</p>
     * @return {@link io.github.nichetoolkit.rice.RestTableKey} <p>The to table key return object is <code>RestTableKey</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     */
    public RestTableKey<K> toTableKey() {
        return RestTableKey.of(this.tableKey);
    }
}
