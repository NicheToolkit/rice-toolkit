package io.github.nichetoolkit.rice.resolver;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.identity.IdentityUtils;
import io.github.nichetoolkit.rice.RestId;
import io.github.nichetoolkit.rice.RestIdResolver;
import org.springframework.stereotype.Component;

/**
 * <code>DefaultLongIdResolver</code>
 * <p>The type default long id resolver class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestIdResolver
 * @see org.springframework.stereotype.Component
 * @since Jdk1.8
 */
@Component
public class DefaultLongIdResolver implements RestIdResolver<Long> {

    @Override
    public <M extends RestId<Long>> Long resolveId(M model) throws RestException {
        return IdentityUtils.generateLong();
    }
}
