package io.github.nichetoolkit.rice.defaults;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.identity.IdentityUtils;
import io.github.nichetoolkit.rice.RestId;
import io.github.nichetoolkit.rice.RestIdResolver;
import org.springframework.stereotype.Component;

/**
 * <code>DefaultStringIdResolver</code>
 * <p>The type default string id resolver class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestIdResolver
 * @see org.springframework.stereotype.Component
 * @since Jdk1.8
 */
@Component
public class DefaultStringIdResolver implements RestIdResolver<String> {

    @Override
    public <M extends RestId<String>> String resolveId(M model) throws RestException {
        return IdentityUtils.generateString();
    }
}
