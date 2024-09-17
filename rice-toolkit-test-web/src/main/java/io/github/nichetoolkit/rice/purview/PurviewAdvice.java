package io.github.nichetoolkit.rice.purview;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestHttpRequest;
import io.github.nichetoolkit.rest.RestReckon;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.util.OptionalUtils;
import io.github.nichetoolkit.rice.DefaultAdvice;
import io.github.nichetoolkit.rice.error.TokenPermissionException;
import io.github.nichetoolkit.rice.service.TokenService;
import io.github.nichetoolkit.rice.simple.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@Component
public class PurviewAdvice implements DefaultAdvice<RestPurview> {

    private final TokenService tokenService;

    @Autowired
    public PurviewAdvice(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public int order() {
        return 98;
    }

    @Override
    public void doAnnotationHandle(RestHttpRequest request, HttpServletResponse response, HandlerMethod handlerMethod, RestPurview purview) throws RestException {
        UserModel userModel = tokenService.resolveUserInfo(request);
        /* 用户权限可自定义实现 这里只做演示 */
        userModel.setPurviewType(PurviewType.PURVIEW_1);
        /* 自定义key模式 */
        purviewKeys(userModel,RestPurview.Purview.keys(purview));
        /* 自定义value模式 */
//        purviewValues(userModel,RestPurview.Purview.values(purview));
    }

    private void purviewKeys(UserModel userModel,List<String> purviewKeys) throws RestException {
        PurviewType purviewType = userModel.getPurviewType();
        if (GeneralUtils.isNotEmpty(purviewKeys)) {
            /* 用户权限为空时，校验不通过 */
            OptionalUtils.ofEmptyable(purviewType).emptyElseThrow(TokenPermissionException::new);
            String purviewTypeKey = purviewType.getKey();
            OptionalUtils.falseable(purviewKeys.contains(purviewTypeKey),TokenPermissionException::new);
        }
    }

    private void purviewValues(UserModel userModel,List<Long> purviewValues) throws RestException {
        PurviewType purviewType = userModel.getPurviewType();
        if (GeneralUtils.isNotEmpty(purviewValues)) {
            /* 用户权限为空时，校验不通过 */
            OptionalUtils.ofEmptyable(purviewType).emptyElseThrow(TokenPermissionException::new);
            Long annexValue = RestReckon.annexValue(purviewValues);
            Long value = purviewType.getValue();
            OptionalUtils.falseable(RestReckon.reachValue(annexValue,value),TokenPermissionException::new);
        }
    }
}
