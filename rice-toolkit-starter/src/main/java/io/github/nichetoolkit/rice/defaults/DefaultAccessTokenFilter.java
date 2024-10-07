package io.github.nichetoolkit.rice.defaults;

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

/**
 * <code>DefaultAccessTokenFilter</code>
 * <p>The type default access token filter class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see org.springframework.web.filter.OncePerRequestFilter
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.stereotype.Component
 * @see org.springframework.core.annotation.Order
 * @since Jdk1.8
 */
@Slf4j
@Component
@Order(value = Ordered.HIGHEST_PRECEDENCE + 101)
public class DefaultAccessTokenFilter extends OncePerRequestFilter {
    /**
     * <code>loginProperties</code>
     * {@link io.github.nichetoolkit.rice.configure.RiceLoginProperties} <p>The <code>loginProperties</code> field.</p>
     * @see io.github.nichetoolkit.rice.configure.RiceLoginProperties
     */
    private final RiceLoginProperties loginProperties;
    /**
     * <code>accessValue</code>
     * {@link io.github.nichetoolkit.rest.RestAccessValue} <p>The <code>accessValue</code> field.</p>
     * @see io.github.nichetoolkit.rest.RestAccessValue
     */
    private RestAccessValue accessValue;

    /**
     * <code>DefaultAccessTokenFilter</code>
     * <p>Instantiates a new default access token filter.</p>
     * @param loginProperties {@link io.github.nichetoolkit.rice.configure.RiceLoginProperties} <p>The login properties parameter is <code>RiceLoginProperties</code> type.</p>
     * @see io.github.nichetoolkit.rice.configure.RiceLoginProperties
     * @see org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired(required = false)
    public DefaultAccessTokenFilter(RiceLoginProperties loginProperties) {
        this.loginProperties = loginProperties;
    }

    /**
     * <code>DefaultAccessTokenFilter</code>
     * <p>Instantiates a new default access token filter.</p>
     * @param loginProperties {@link io.github.nichetoolkit.rice.configure.RiceLoginProperties} <p>The login properties parameter is <code>RiceLoginProperties</code> type.</p>
     * @param accessValue     {@link io.github.nichetoolkit.rest.RestAccessValue} <p>The access value parameter is <code>RestAccessValue</code> type.</p>
     * @see io.github.nichetoolkit.rice.configure.RiceLoginProperties
     * @see io.github.nichetoolkit.rest.RestAccessValue
     * @see org.springframework.beans.factory.annotation.Autowired
     */
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
