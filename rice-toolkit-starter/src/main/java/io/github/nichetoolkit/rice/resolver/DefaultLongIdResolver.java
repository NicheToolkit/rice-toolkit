package io.github.nichetoolkit.rice.resolver;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.identity.IdentityUtils;
import io.github.nichetoolkit.rice.RestId;
import io.github.nichetoolkit.rice.RestIdResolver;
import org.springframework.stereotype.Component;

/**
 * <p>DefaultStringIdResolver</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Component
public class DefaultLongIdResolver implements RestIdResolver<Long> {

    @Override
    public <M extends RestId<Long>> Long resolveId(M model) throws RestException {
        return IdentityUtils.generateLong();
    }
}
