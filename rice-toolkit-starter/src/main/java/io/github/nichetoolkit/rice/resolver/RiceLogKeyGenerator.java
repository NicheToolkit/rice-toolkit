package io.github.nichetoolkit.rice.resolver;

import io.github.nichetoolkit.rest.configure.RestLogbackProperties;
import io.github.nichetoolkit.rest.interceptor.RestRequestWrapper;
import io.github.nichetoolkit.rest.logback.RestLogKeyGenerator;
import io.github.nichetoolkit.rice.configure.RiceLoginProperties;
import io.github.nichetoolkit.rice.helper.InterceptorHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class RiceLogKeyGenerator extends RestLogKeyGenerator {
    private final RiceLoginProperties loginProperties;

    public RiceLogKeyGenerator(RestLogbackProperties logbackProperties, RiceLoginProperties loginProperties) {
        super(logbackProperties);
        this.loginProperties = loginProperties;
    }

    @Override
    public String accessToken(RestRequestWrapper requestWrapper) {
        if (loginProperties.getEnabled()) {
            return InterceptorHelper.getRequestToken(requestWrapper);
        }
        return null;
    }
}
