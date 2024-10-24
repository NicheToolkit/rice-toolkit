package io.github.nichetoolkit.rice.defaults;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.identity.IdentityUtils;
import io.github.nichetoolkit.rice.DefaultIdResolver;

public class DefaultLongIdResolver extends DefaultIdResolver<Long> {
    public static final DefaultLongIdResolver DEFAULT_RESOLVER = new DefaultLongIdResolver();

    @Override
    public Long resolve() throws RestException {
        return IdentityUtils.valueOfLong();
    }
}
