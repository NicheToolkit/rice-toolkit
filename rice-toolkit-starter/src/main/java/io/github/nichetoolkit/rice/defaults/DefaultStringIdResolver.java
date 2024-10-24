package io.github.nichetoolkit.rice.defaults;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.identity.IdentityUtils;
import io.github.nichetoolkit.rice.DefaultIdResolver;

public class DefaultStringIdResolver extends DefaultIdResolver<String> {

    public static final DefaultStringIdResolver DEFAULT_RESOLVER = new DefaultStringIdResolver();

    @Override
    public String resolve() throws RestException {
        return IdentityUtils.valueOfString();
    }
}
