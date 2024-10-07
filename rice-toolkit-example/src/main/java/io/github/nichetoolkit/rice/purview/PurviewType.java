package io.github.nichetoolkit.rice.purview;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.nichetoolkit.rest.RestReckon;
import io.github.nichetoolkit.rest.RestValue;

import java.util.Optional;

/**
 * <code>PurviewType</code>
 * <p>The type purview type enumeration.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestReckon
 * @since Jdk1.8
 */
public enum PurviewType implements RestReckon<Long> {
    /**
     * <code>PURVIEW_ALL</code>
     * <p>The purview all purview type field.</p>
     */
    PURVIEW_ALL("purview_all", 0L),
    /**
     * <code>PURVIEW_1</code>
     * <p>The purview 1 purview type field.</p>
     */
    PURVIEW_1("purview1", 1L),
    /**
     * <code>PURVIEW_2</code>
     * <p>The purview 2 purview type field.</p>
     */
    PURVIEW_2("purview2", 2L),
    /**
     * <code>PURVIEW_3</code>
     * <p>The purview 3 purview type field.</p>
     */
    PURVIEW_3("purview3", 4L),
    ;

    /**
     * <code>key</code>
     * {@link java.lang.String} <p>The <code>key</code> field.</p>
     * @see java.lang.String
     */
    private final String key;

    /**
     * <code>value</code>
     * {@link java.lang.Long} <p>The <code>value</code> field.</p>
     * @see java.lang.Long
     */
    private final Long value;

    /**
     * <code>PurviewType</code>
     * <p>Instantiates a new purview type.</p>
     * @param key   {@link java.lang.String} <p>The key parameter is <code>String</code> type.</p>
     * @param value {@link java.lang.Long} <p>The value parameter is <code>Long</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Long
     */
    PurviewType(String key, Long value) {
        this.key = key;
        this.value = value;
    }

    @JsonValue
    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public Long getValue() {
        return this.value;
    }

    /**
     * <code>parseKey</code>
     * <p>The key method.</p>
     * @param key {@link java.lang.String} <p>The key parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.purview.PurviewType} <p>The key return object is <code>PurviewType</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.annotation.JsonCreator
     */
    @JsonCreator
    public static PurviewType parseKey(String key) {
        PurviewType typeEnum = RestValue.parseKey(PurviewType.class, key);
        return Optional.ofNullable(typeEnum).orElse(PurviewType.PURVIEW_ALL);
    }

    /**
     * <code>parseValue</code>
     * <p>The value method.</p>
     * @param value {@link java.lang.Long} <p>The value parameter is <code>Long</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.purview.PurviewType} <p>The value return object is <code>PurviewType</code> type.</p>
     * @see java.lang.Long
     */
    public static PurviewType parseValue(Long value) {
        PurviewType typeEnum = RestValue.parseValue(PurviewType.class, value);
        return Optional.ofNullable(typeEnum).orElse(PurviewType.PURVIEW_ALL);
    }
}
