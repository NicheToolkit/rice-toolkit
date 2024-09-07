package io.github.nichetoolkit.rice.resolver;

import io.github.nichetoolkit.rest.RestHttpRequest;
import io.github.nichetoolkit.rest.RestLoggingKeyGenerator;
import io.github.nichetoolkit.rest.configure.RestLogbackProperties;
import io.github.nichetoolkit.rice.configure.RiceLoginProperties;
import io.github.nichetoolkit.rice.helper.HttpRequestHelper;
import lombok.extern.slf4j.Slf4j;

/**
 * <code>DefaultLoggingKeyResolver</code>
 * <p>The type default logging key resolver class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestLoggingKeyGenerator
 * @see lombok.extern.slf4j.Slf4j
 * @since Jdk1.8
 */
@Slf4j
public class DefaultLoggingKeyResolver extends RestLoggingKeyGenerator {
    private final RiceLoginProperties loginProperties;

    /**
     * <code>DefaultLoggingKeyResolver</code>
     * Instantiates a new default logging key resolver.
     * @param logbackProperties {@link io.github.nichetoolkit.rest.configure.RestLogbackProperties} <p>the logback properties parameter is <code>RestLogbackProperties</code> type.</p>
     * @param loginProperties   {@link io.github.nichetoolkit.rice.configure.RiceLoginProperties} <p>the login properties parameter is <code>RiceLoginProperties</code> type.</p>
     * @see io.github.nichetoolkit.rest.configure.RestLogbackProperties
     * @see io.github.nichetoolkit.rice.configure.RiceLoginProperties
     */
    public DefaultLoggingKeyResolver(RestLogbackProperties logbackProperties, RiceLoginProperties loginProperties) {
        super(logbackProperties);
        this.loginProperties = loginProperties;
    }

    @Override
    public String doAccessTokenHandle(RestHttpRequest httpRequest) {
        if (loginProperties.getEnabled()) {
            return HttpRequestHelper.resolveToken(httpRequest);
        }
        return null;
    }

    @Override
    public String doAccessAuthHandle(RestHttpRequest httpRequest) {
        return "";
    }
}
