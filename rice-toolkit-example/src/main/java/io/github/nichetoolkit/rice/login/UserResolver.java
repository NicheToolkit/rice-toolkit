package io.github.nichetoolkit.rice.login;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestHttpRequest;
import io.github.nichetoolkit.rice.RestUserResolver;
import io.github.nichetoolkit.rice.service.TokenService;
import io.github.nichetoolkit.rice.simple.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * <code>UserResolver</code>
 * <p>The user resolver class.</p>
 * @see  io.github.nichetoolkit.rice.RestUserResolver
 * @see  org.springframework.stereotype.Component
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Component
public class UserResolver implements RestUserResolver {

    /**
     * <code>tokenService</code>
     * {@link io.github.nichetoolkit.rice.service.TokenService} <p>The <code>tokenService</code> field.</p>
     * @see  io.github.nichetoolkit.rice.service.TokenService
     */
    private final TokenService tokenService;

    /**
     * <code>UserResolver</code>
     * <p>Instantiates a new user resolver.</p>
     * @param tokenService {@link io.github.nichetoolkit.rice.service.TokenService} <p>The token service parameter is <code>TokenService</code> type.</p>
     * @see  io.github.nichetoolkit.rice.service.TokenService
     * @see  org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired
    public UserResolver(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @NonNull
    @Override
    public UserModel resolveUser(MethodParameter parameter, RestHttpRequest httpRequest) throws RestException {
        return tokenService.resolveUserInfo(httpRequest);
    }
}
