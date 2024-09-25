package io.github.nichetoolkit.rice.purview;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestHttpRequest;
import io.github.nichetoolkit.rest.RestOptional;
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

/**
 * <code>PurviewAdvice</code>
 * <p>The type purview advice class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.DefaultAdvice
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.stereotype.Component
 * @since Jdk1.8
 */
@Slf4j
@Component
public class PurviewAdvice implements DefaultAdvice<RestPurview> {

    private final TokenService tokenService;

    /**
     * <code>PurviewAdvice</code>
     * Instantiates a new purview advice.
     * @param tokenService {@link io.github.nichetoolkit.rice.service.TokenService} <p>the token service parameter is <code>TokenService</code> type.</p>
     * @see io.github.nichetoolkit.rice.service.TokenService
     * @see org.springframework.beans.factory.annotation.Autowired
     */
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
        /* custom user purview */
        userModel.setPurviewType(PurviewType.PURVIEW_1);
        /* custom key mode check*/
        purviewKeysCheck(userModel,RestPurview.Purview.keys(purview));
        /* custom value mode check*/
        purviewValuesCheck(userModel,RestPurview.Purview.values(purview));
    }

    private void purviewKeysCheck(UserModel userModel,List<String> purviewKeys) throws RestException {
        PurviewType purviewType = userModel.getPurviewType();
        if (GeneralUtils.isNotEmpty(purviewKeys)) {
            RestOptional.ofEmptyable(purviewType).emptyElseThrow(TokenPermissionException::new);
            String purviewTypeKey = purviewType.getKey();
            OptionalUtils.ofFalse(purviewKeys.contains(purviewTypeKey),TokenPermissionException::new);
        }
    }

    private void purviewValuesCheck(UserModel userModel,List<Long> purviewValues) throws RestException {
        PurviewType purviewType = userModel.getPurviewType();
        if (GeneralUtils.isNotEmpty(purviewValues)) {
            RestOptional.ofEmptyable(purviewType).emptyElseThrow(TokenPermissionException::new);
            Number annexValue = RestReckon.annexNumber(purviewValues);
            Long value = purviewType.getValue();
            OptionalUtils.ofFalse(RestReckon.reachNumber(annexValue,value),TokenPermissionException::new);
        }
    }
}
