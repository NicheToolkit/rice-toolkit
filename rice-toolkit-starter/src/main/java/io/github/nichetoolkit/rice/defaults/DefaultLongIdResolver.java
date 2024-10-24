package io.github.nichetoolkit.rice.defaults;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.identity.IdentityUtils;
import io.github.nichetoolkit.rice.DefaultIdResolver;

/**
 * <code>DefaultLongIdResolver</code>
 * <p>The default long id resolver class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.DefaultIdResolver
 * @since Jdk1.8
 */
public class DefaultLongIdResolver extends DefaultIdResolver<Long> {
    /**
     * <code>DEFAULT_RESOLVER</code>
     * {@link io.github.nichetoolkit.rice.defaults.DefaultLongIdResolver} <p>The constant <code>DEFAULT_RESOLVER</code> field.</p>
     */
    public static final DefaultLongIdResolver DEFAULT_RESOLVER = new DefaultLongIdResolver();

    @Override
    public Long resolve() throws RestException {
        return IdentityUtils.valueOfLong();
    }
}
