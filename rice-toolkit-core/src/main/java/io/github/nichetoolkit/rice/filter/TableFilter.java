package io.github.nichetoolkit.rice.filter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.rice.RestTablekey;
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
 * @since Jdk1.8
 */
@Setter
@Getter
@SuppressWarnings("WeakerAccess")
@SuperBuilder(builderMethodName = "ofTableBuilder")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TableFilter<K> extends FickleFilter {
    /**
     * <code>tablekey</code>
     * <p>The <code>tablekey</code> field.</p>
     */
    protected K tablekey;

    /**
     * <code>TableFilter</code>
     * <p>Instantiates a new table filter.</p>
     */
    public TableFilter() {
    }

    /**
     * <code>toTablekey</code>
     * <p>The to tablekey method.</p>
     * @return {@link io.github.nichetoolkit.rice.RestTablekey} <p>The to tablekey return object is <code>RestTablekey</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTablekey
     */
    public RestTablekey<K> toTablekey() {
        return RestTablekey.of(this.tablekey);
    }
}
