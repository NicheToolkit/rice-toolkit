package io.github.nichetoolkit.rice.defaults;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.identity.IdentityUtils;
import io.github.nichetoolkit.rice.resolver.RestIdentityResolver;

public class LongIdentityResolver implements RestIdentityResolver<Long> {
    public static final LongIdentityResolver DEFAULT_RESOLVER = new LongIdentityResolver();

    @Override
    public Long resolve() throws RestException {
        return IdentityUtils.valueOfLong();
    }
}
