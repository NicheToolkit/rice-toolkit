package io.github.nichetoolkit.rice;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * <p>RestTablekey</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@SuppressWarnings("WeakerAccess")
public interface RestTablekey<K> {

    @JsonIgnore
    default K getTablekey() {
        return null;
    }

}
