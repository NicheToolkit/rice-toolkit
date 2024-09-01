package io.github.nichetoolkit.rice.resolver;

import io.github.nichetoolkit.rest.RestHttpRequest;
import io.github.nichetoolkit.rest.RestLoggingKeyGenerator;
import io.github.nichetoolkit.rest.configure.RestLogbackProperties;
import io.github.nichetoolkit.rice.configure.RiceLoginProperties;
import io.github.nichetoolkit.rice.helper.PurviewHelper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultLoggingKeyResolver extends RestLoggingKeyGenerator {
    private final RiceLoginProperties loginProperties;

    public DefaultLoggingKeyResolver(RestLogbackProperties logbackProperties, RiceLoginProperties loginProperties) {
        super(logbackProperties);
        this.loginProperties = loginProperties;
    }

    @Override
    public String doAccessTokenHandle(RestHttpRequest httpRequest) {
        if (loginProperties.getEnabled()) {
            return PurviewHelper.resolveToken(httpRequest);
        }
        return null;
    }

    @Override
    public String doAccessAuthHandle(RestHttpRequest httpRequest) {
        return "";
    }
}
