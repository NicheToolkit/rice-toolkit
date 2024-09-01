package io.github.nichetoolkit.rice.resolver;

import io.github.nichetoolkit.rest.RestAccessValue;
import io.github.nichetoolkit.rest.RestHttpRequest;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.configure.RiceLoginProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@Order(value = Ordered.HIGHEST_PRECEDENCE + 101)
public class DefaultAccessTokenFilter extends OncePerRequestFilter {
    private final RiceLoginProperties loginProperties;
    private RestAccessValue accessValue;

    @Autowired(required = false)
    public DefaultAccessTokenFilter(RiceLoginProperties loginProperties) {
        this.loginProperties = loginProperties;
    }

    @Autowired(required = false)
    public DefaultAccessTokenFilter(RiceLoginProperties loginProperties, RestAccessValue accessValue) {
        this.loginProperties = loginProperties;
        this.accessValue = accessValue;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        RestHttpRequest httpRequest = new RestHttpRequest(request);
        if (loginProperties.getEnabled()) {
            String accessTokenValue;
            String accessTokenHeader = loginProperties.getAccessTokenHeader();
            if (GeneralUtils.isNotEmpty(this.accessValue)) {
                accessTokenValue = this.accessValue.accessToken(httpRequest);
                if (GeneralUtils.isEmpty(accessTokenValue)) {
                    request.setAttribute(accessTokenHeader, accessTokenValue);
                }
            }
            String accessAuthValue;
            String accessAuthHeader = loginProperties.getAccessAuthHeader();
            if (GeneralUtils.isNotEmpty(this.accessValue)) {
                accessAuthValue = this.accessValue.accessAuth(httpRequest);
                if (GeneralUtils.isEmpty(accessAuthValue)) {
                    request.setAttribute(accessAuthHeader, accessAuthValue);
                }
            }
        }
        filterChain.doFilter(httpRequest, response);
    }
}
