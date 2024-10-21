package io.github.nichetoolkit.rice.defaults;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.identity.IdentityUtils;
import io.github.nichetoolkit.rice.RestId;
import io.github.nichetoolkit.rice.RestIdResolver;

public class LongIdentityResolver implements RestIdResolver<Long> {
    public static final LongIdentityResolver DEFAULT_RESOLVER = new LongIdentityResolver();
    @Override
    public <M extends RestId<Long>> Long resolve(M model) throws RestException {
        return IdentityUtils.generateLong();
    }
}
