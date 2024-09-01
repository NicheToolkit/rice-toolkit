package io.github.nichetoolkit.rice.resolver;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestHttpRequest;
import io.github.nichetoolkit.rest.RestUsernoteAdvice;
import io.github.nichetoolkit.rest.userlog.LoggingType;
import io.github.nichetoolkit.rest.userlog.RestRequestPack;
import io.github.nichetoolkit.rest.userlog.RestResponsePack;
import io.github.nichetoolkit.rest.userlog.RestUsernotePack;
import io.github.nichetoolkit.rest.util.BeanUtils;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.RestUserInfo;
import io.github.nichetoolkit.rice.advice.annotation.UserlogAdvice;
import io.github.nichetoolkit.rice.RestUsernoteModel;
import io.github.nichetoolkit.rice.clazz.ClazzUtils;
import io.github.nichetoolkit.rice.pack.UserInfoPack;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.web.method.HandlerMethod;

import java.lang.reflect.ParameterizedType;
import java.util.Set;

@Slf4j
@SuppressWarnings({"WeakerAccess", "unchecked"})
public abstract class RestUsernoteService<T extends RestUsernoteModel<?, ?>> implements UserlogAdvice, RestUsernoteAdvice {
    private RestUsernoteModel<?, ?> usernote;

    public RestUsernoteService() {
        this.usernote = new RestUsernoteModel<>();
    }

    @SuppressWarnings(value = "unchecked")
    private Class<T> childClass() {
        return (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public void doUserlogHandle( RestHttpRequest httpRequest,HandlerMethod handlerMethod) throws RestException {
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

    public abstract RestUserInfo<?> resolveUserInfo(RestHttpRequest httpRequest) throws RestException;

    public abstract void doUsernoteHandle(T usernote) throws RestException;

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
