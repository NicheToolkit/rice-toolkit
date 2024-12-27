package io.github.nichetoolkit.rice.defaults;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.identity.IdentityUtils;
import io.github.nichetoolkit.rice.DefaultIdResolver;

/**
 * <code>DefaultStringIdResolver</code>
 * <p>The default string id resolver class.</p>
 * @see  io.github.nichetoolkit.rice.DefaultIdResolver
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public class DefaultStringIdResolver extends DefaultIdResolver<String> {

    /**
     * <code>DEFAULT_RESOLVER</code>
     * {@link io.github.nichetoolkit.rice.defaults.DefaultStringIdResolver} <p>The constant <code>DEFAULT_RESOLVER</code> field.</p>
     */
    public static final DefaultStringIdResolver DEFAULT_RESOLVER = new DefaultStringIdResolver();

    @Override
    public String resolve() throws RestException {
        return IdentityUtils.valueOfString();
    }
}
