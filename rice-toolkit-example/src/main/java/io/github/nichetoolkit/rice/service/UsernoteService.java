package io.github.nichetoolkit.rice.service;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestHttpRequest;
import io.github.nichetoolkit.rest.util.JsonUtils;
import io.github.nichetoolkit.rice.RestUserInfo;
import io.github.nichetoolkit.rice.RestUsernoteModel;
import io.github.nichetoolkit.rice.defaults.DefaultUsernoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <code>UsernoteService</code>
 * <p>The type usernote service class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.defaults.DefaultUsernoteService
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.stereotype.Service
 * @since Jdk1.8
 */
@Slf4j
@Service
public class UsernoteService extends DefaultUsernoteService<RestUsernoteModel<?, ?>> {

    /**
     * <code>tokenService</code>
     * {@link io.github.nichetoolkit.rice.service.TokenService} <p>The <code>tokenService</code> field.</p>
     * @see io.github.nichetoolkit.rice.service.TokenService
     */
    private final TokenService tokenService;

    /**
     * <code>UsernoteService</code>
     * <p>Instantiates a new usernote service.</p>
     * @param tokenService {@link io.github.nichetoolkit.rice.service.TokenService} <p>The token service parameter is <code>TokenService</code> type.</p>
     * @see io.github.nichetoolkit.rice.service.TokenService
     * @see org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired
    public UsernoteService(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public RestUserInfo<?> resolveUserInfo(RestHttpRequest httpRequest) throws RestException {
        return tokenService.resolveUserInfo(httpRequest);
    }

    @Override
    public void doUsernoteHandle(RestUsernoteModel<?, ?> usernote) throws RestException {
        log.info("the usernote model: {}", JsonUtils.parseJson(usernote));
    }
}
