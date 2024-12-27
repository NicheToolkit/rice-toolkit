package io.github.nichetoolkit.rice.defaults;

import io.github.nichetoolkit.rest.RestHttpRequest;
import io.github.nichetoolkit.rest.RestLoggingKeyGenerator;
import io.github.nichetoolkit.rest.configure.RestLogbackProperties;
import io.github.nichetoolkit.rice.configure.RiceLoginProperties;
import io.github.nichetoolkit.rice.helper.HttpRequestHelper;
import lombok.extern.slf4j.Slf4j;

/**
 * <code>DefaultLoggingKeyResolver</code>
 * <p>The default logging key resolver class.</p>
 * @see  io.github.nichetoolkit.rest.RestLoggingKeyGenerator
 * @see  lombok.extern.slf4j.Slf4j
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Slf4j
public class DefaultLoggingKeyResolver extends RestLoggingKeyGenerator {
    /**
     * <code>loginProperties</code>
     * {@link io.github.nichetoolkit.rice.configure.RiceLoginProperties} <p>The <code>loginProperties</code> field.</p>
     * @see  io.github.nichetoolkit.rice.configure.RiceLoginProperties
     */
    private final RiceLoginProperties loginProperties;

    /**
     * <code>DefaultLoggingKeyResolver</code>
     * <p>Instantiates a new default logging key resolver.</p>
     * @param logbackProperties {@link io.github.nichetoolkit.rest.configure.RestLogbackProperties} <p>The logback properties parameter is <code>RestLogbackProperties</code> type.</p>
     * @param loginProperties {@link io.github.nichetoolkit.rice.configure.RiceLoginProperties} <p>The login properties parameter is <code>RiceLoginProperties</code> type.</p>
     * @see  io.github.nichetoolkit.rest.configure.RestLogbackProperties
     * @see  io.github.nichetoolkit.rice.configure.RiceLoginProperties
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
