package io.github.nichetoolkit.rice.login;


import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.util.OptionalUtils;
import io.github.nichetoolkit.rest.worker.sha.ShaWorker;
import io.github.nichetoolkit.rice.error.LoginInfoException;
import io.github.nichetoolkit.rice.error.LoginPasswordException;
import io.github.nichetoolkit.rice.error.TokenInvalidException;
import io.github.nichetoolkit.rice.service.UserService;
import io.github.nichetoolkit.rice.simple.LoginRequest;
import io.github.nichetoolkit.rice.simple.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * <p>AccountServiceImpl</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
@Service
public class LoginService {

    private final StringRedisTemplate redisTemplate;
    private final UserService userService;
    private final TokenService tokenService;

    @Autowired
    public LoginService(StringRedisTemplate redisTemplate, UserService userService, TokenService tokenService) {
        this.redisTemplate = redisTemplate;
        this.userService = userService;
        this.tokenService = tokenService;
    }


    public UserModel loginWithToken(LoginRequest loginRequest) throws RestException {
        String token = loginRequest.getToken();
        UserModel localUser = tokenService.resolveUserInfo(token);
        String userId = localUser.getId();
        OptionalUtils.falseable(GeneralUtils.isNotEmpty(userId), TokenInvalidException::new);
        return localUser;
    }

    public UserModel loginWithPassword(LoginRequest loginRequest) throws RestException {
        String account = loginRequest.getAccount();
        String password = loginRequest.getPassword();
        OptionalUtils.falseable(GeneralUtils.isNotEmpty(account) && GeneralUtils.isNotEmpty(password), LoginInfoException::new);
        List<UserModel> modelList = userService.queryByName(account);
        OptionalUtils.falseable(GeneralUtils.isNotEmpty(modelList), LoginInfoException::new);
        Optional<UserModel> firstOptional = modelList.stream().findFirst();
        UserModel localUser = firstOptional.orElseThrow(LoginInfoException::new);
        String localPassword = localUser.getPassword();
        if (GeneralUtils.isNotEmpty(localPassword)) {
            String encryptPassword = ShaWorker.encrypts(password);
            OptionalUtils.falseable(encryptPassword.equals(localPassword), LoginPasswordException::new);
        }
        return localUser;
    }


    public void logoutWithUserId(String userId) throws RestException {
        Object accessToken = redisTemplate.opsForValue().get(UserModel.LOGIN_TOKEN + userId);
        if (GeneralUtils.isNotEmpty(accessToken)) {
            redisTemplate.delete(UserModel.LOGIN_TOKEN + userId);
        }
    }

}
