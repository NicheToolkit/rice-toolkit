package io.github.nichetoolkit.rice.resolver;

import io.github.nichetoolkit.rest.configure.RestLogbackProperties;
import io.github.nichetoolkit.rest.interceptor.RestRequestWrapper;
import io.github.nichetoolkit.rest.logback.RestLogKeyGenerator;
import io.github.nichetoolkit.rice.helper.InterceptorHelper;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>RiceLogKeyGenerator</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
public class RiceLogKeyGenerator extends RestLogKeyGenerator {

    public RiceLogKeyGenerator(RestLogbackProperties logbackProperties) {
        super(logbackProperties);
    }

    @Override
    public String accessToken(RestRequestWrapper requestWrapper) {
        return InterceptorHelper.getRequestToken(requestWrapper);
    }
}
