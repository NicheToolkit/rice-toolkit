package io.github.nichetoolkit.rice.service;

import com.xinfeng.multilink.domain.model.User;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.interceptor.RestRequestWrapper;
import io.github.nichetoolkit.rice.RiceUserAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;

@Component
public class LoginHandler implements RiceUserAdvice {

    @Autowired
    private LoginService loginService;

    @Override
    public User resolveArgument(MethodParameter parameter, RestRequestWrapper requestWrapper) throws RestException {
        return loginService.userInfo(requestWrapper);
    }
}
