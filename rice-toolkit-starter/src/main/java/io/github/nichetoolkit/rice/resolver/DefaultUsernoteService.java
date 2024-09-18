package io.github.nichetoolkit.rice.resolver;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestHttpRequest;
import io.github.nichetoolkit.rest.RestUsernoteAdvice;
import io.github.nichetoolkit.rest.resolver.RestGenericTypeResolver;
import io.github.nichetoolkit.rest.userlog.LoggingType;
import io.github.nichetoolkit.rest.userlog.RestRequestPack;
import io.github.nichetoolkit.rest.userlog.RestResponsePack;
import io.github.nichetoolkit.rest.userlog.RestUsernotePack;
import io.github.nichetoolkit.rest.userlog.stereotype.RestUserlog;
import io.github.nichetoolkit.rest.util.BeanUtils;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.RestUserInfo;
import io.github.nichetoolkit.rice.advice.UserlogAdvice;
import io.github.nichetoolkit.rice.RestUsernoteModel;
import io.github.nichetoolkit.rice.clazz.ClazzUtils;
import io.github.nichetoolkit.rice.pack.UserInfoPack;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * <code>DefaultUsernoteService</code>
 * <p>The type default usernote service class.</p>
 * @param <T> {@link io.github.nichetoolkit.rice.RestUsernoteModel} <p>the generic parameter is <code>RestUsernoteModel</code> type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestUsernoteModel
 * @see io.github.nichetoolkit.rice.advice.UserlogAdvice
 * @see io.github.nichetoolkit.rest.RestUsernoteAdvice
 * @see lombok.extern.slf4j.Slf4j
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@Slf4j
@SuppressWarnings({"WeakerAccess", "unchecked"})
public abstract class DefaultUsernoteService<T extends RestUsernoteModel<?, ?>> implements UserlogAdvice, RestUsernoteAdvice {
    /**
     * <code>usernote</code>
     * {@link io.github.nichetoolkit.rice.RestUsernoteModel} <p>the <code>usernote</code> field.</p>
     * @see io.github.nichetoolkit.rice.RestUsernoteModel
     */
    private RestUsernoteModel<?, ?> usernote;

    /**
     * <code>RestUsernoteService</code>
     * Instantiates a new rest usernote service.
     */
    public DefaultUsernoteService() {
        this.usernote = new RestUsernoteModel<>();
    }

    /**
     * <code>childClass</code>
     * <p>the class method.</p>
     * @return {@link java.lang.Class} <p>the class return object is <code>Class</code> type.</p>
     * @see java.lang.Class
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings(value = "unchecked")
    private Class<T> childClass() {
        return (Class<T>) RestGenericTypeResolver.resolveClass(RestGenericTypeResolver.resolveType(
                DefaultUsernoteService.class.getTypeParameters()[0], getClass(), DefaultUsernoteService.class));
    }

    @Override
    public void doAnnotationHandle(RestHttpRequest httpRequest, HttpServletResponse response, HandlerMethod handlerMethod, RestUserlog annotation) throws RestException {
        if (GeneralUtils.isEmpty(RestUserInfoHolder.getUser())) {
            RestUserInfo<?> userInfo = resolveUserInfo(httpRequest);
            RestUserInfoHolder.setUser(userInfo);
        }
    }

    @Override
    public void doUsernoteHandle(@NonNull RestRequestPack request, @NonNull RestResponsePack response, @NonNull RestUsernotePack usernote) {
        try {
            this.usernote = RestUsernoteModel.createBuilder()
                    .usernote(usernote).request(request)
                    .response(response).build();
            doTargetIdsHandle(request, response);
            doUserInfoHandle(response);
            if (GeneralUtils.isNotEmpty(this.usernote)) {
                T childUsernote = ClazzUtils.renew(childClass());
                if (GeneralUtils.isNotEmpty(childUsernote)) {
                    BeanUtils.copyNonullProperties(this.usernote, childUsernote);
                    childUsernote.setTargetIds(this.usernote.getTargetIds());
                    doUsernoteHandle(childUsernote);
                }
            } else {
                log.warn("the usernote is null!");
            }
        } catch (Exception exception) {
            log.warn("the usernote exception is ignored! error: {}", exception.getMessage());
        }
        this.usernote = new RestUsernoteModel<>();
    }

    /**
     * <code>resolveUserInfo</code>
     * <p>the user info method.</p>
     * @param httpRequest {@link io.github.nichetoolkit.rest.RestHttpRequest} <p>the http request parameter is <code>RestHttpRequest</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.RestUserInfo} <p>the user info return object is <code>RestUserInfo</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestHttpRequest
     * @see io.github.nichetoolkit.rice.RestUserInfo
     * @see io.github.nichetoolkit.rest.RestException
     */
    public abstract RestUserInfo<?> resolveUserInfo(RestHttpRequest httpRequest) throws RestException;

    /**
     * <code>doUsernoteHandle</code>
     * <p>the usernote handle method.</p>
     * @param usernote T <p>the usernote parameter is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    public abstract void doUsernoteHandle(T usernote) throws RestException;

    /**
     * <code>doUserInfoHandle</code>
     * <p>the user info handle method.</p>
     * @param response {@link io.github.nichetoolkit.rest.userlog.RestResponsePack} <p>the response parameter is <code>RestResponsePack</code> type.</p>
     * @see io.github.nichetoolkit.rest.userlog.RestResponsePack
     */
    protected void doUserInfoHandle(RestResponsePack response) {
        if (GeneralUtils.isEmpty(RestUserInfoHolder.getUser())) {
            LoggingType loggingType = this.usernote.getLoggingType();
            if (GeneralUtils.isEmpty(loggingType) || !response.isSuccess()) {
                return;
            }
            UserInfoPack userInfoPack;
            switch (loggingType) {
                case USER_LOGON:
                    userInfoPack = RestUsernoteModel.toDataUserInfo(response.getData());
                    if (GeneralUtils.isNotEmpty(userInfoPack)) {
                        RestUserInfoHolder.setUser(userInfoPack);
                    }
                    break;
                case USER_LOGIN:
                    userInfoPack = RestUsernoteModel.toDataUserLogin(response.getData());
                    if (GeneralUtils.isNotEmpty(userInfoPack)) {
                        RestUserInfoHolder.setUser(userInfoPack);
                    }
                    break;
            }
        }
        RestUserInfo<?> userInfo = RestUserInfoHolder.getUser();
        if (GeneralUtils.isNotEmpty(userInfo)) {
            if (GeneralUtils.isNotEmpty(userInfo.getId())) {
                this.usernote.setUserId(String.valueOf(userInfo.getId()));
            }
            this.usernote.setUsername(userInfo.getUsername());
        }
    }

    /**
     * <code>doTargetIdsHandle</code>
     * <p>the target ids handle method.</p>
     * @param request  {@link io.github.nichetoolkit.rest.userlog.RestRequestPack} <p>the request parameter is <code>RestRequestPack</code> type.</p>
     * @param response {@link io.github.nichetoolkit.rest.userlog.RestResponsePack} <p>the response parameter is <code>RestResponsePack</code> type.</p>
     * @see io.github.nichetoolkit.rest.userlog.RestRequestPack
     * @see io.github.nichetoolkit.rest.userlog.RestResponsePack
     */
    protected void doTargetIdsHandle(RestRequestPack request, RestResponsePack response) {
        LoggingType loggingType = this.usernote.getLoggingType();
        if (GeneralUtils.isEmpty(loggingType)) {
            return;
        }
        if (!response.isSuccess()) {
            return;
        }
        switch (loggingType) {
            case CREATE:
            case UPDATE:
            case SAVE:
                String dataId = RestUsernoteModel.toDataId(response.getData());
                if (GeneralUtils.isNotEmpty(dataId)) {
                    this.usernote.setTargetIds(dataId);
                }
                break;
            case SAVE_ALL:
                Set<String> dataIds = RestUsernoteModel.toDataIds(response.getData());
                if (GeneralUtils.isNotEmpty(dataIds)) {
                    this.usernote.setTargetIds(dataIds);
                }
                break;
            case DELETE:
                String paramId = RestUsernoteModel.toParamId(request.getParams());
                if (GeneralUtils.isNotEmpty(paramId)) {
                    this.usernote.setTargetIds(paramId);
                }
                break;
            case DELETE_ID:
                String urlId = RestUsernoteModel.toUrlId(request.getUrl());
                if (GeneralUtils.isNotEmpty(urlId)) {
                    this.usernote.setTargetIds(urlId);
                }
                break;
            case DELETE_ALL:
                Set<String> paramIds = RestUsernoteModel.toParamIds(request.getParams());
                Set<String> listIds = RestUsernoteModel.toListIds(request.getBody());
                if (GeneralUtils.isNotEmpty(paramIds)) {
                    this.usernote.setTargetIds(paramIds);
                } else if (GeneralUtils.isNotEmpty(listIds)) {
                    this.usernote.setTargetIds(listIds);
                }
                break;
            case DELETE_FILTER:
                Set<String> filterIds = RestUsernoteModel.toFilterIds(request.getBody());
                if (GeneralUtils.isNotEmpty(filterIds)) {
                    this.usernote.setTargetIds(filterIds);
                }
                break;
        }
    }
}