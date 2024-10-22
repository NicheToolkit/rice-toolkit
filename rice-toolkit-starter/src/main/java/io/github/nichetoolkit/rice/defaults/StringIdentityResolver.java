package io.github.nichetoolkit.rice.defaults;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.identity.IdentityUtils;
import io.github.nichetoolkit.rice.RestId;
import io.github.nichetoolkit.rice.RestIdResolver;

public class StringIdentityResolver implements RestIdResolver<String> {

    public static final StringIdentityResolver DEFAULT_RESOLVER = new StringIdentityResolver();

    @Override
    public <M extends RestId<String>> String resolve(M model) throws RestException {
        return IdentityUtils.valueOfString();
    }
}
