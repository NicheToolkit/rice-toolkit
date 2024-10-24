package io.github.nichetoolkit.rice.defaults;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.identity.IdentityUtils;
import io.github.nichetoolkit.rice.resolver.RestIdentityResolver;

public class StringIdentityResolver implements RestIdentityResolver<String> {

    public static final StringIdentityResolver DEFAULT_RESOLVER = new StringIdentityResolver();

    @Override
    public String resolve() throws RestException {
        return IdentityUtils.valueOfString();
    }
}
