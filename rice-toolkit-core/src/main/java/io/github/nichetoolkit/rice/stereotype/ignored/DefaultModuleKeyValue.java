package io.github.nichetoolkit.rice.stereotype.ignored;

import io.github.nichetoolkit.rest.RestValue;

/**
 * <p>DefaultModuleKeyValue</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class DefaultModuleKeyValue implements RestValue<String,Long> {
    @Override
    public Long getValue() {
        return 0L;
    }

    @Override
    public String name() {
        return null;
    }

    @Override
    public String getKey() {
        return null;
    }
}
