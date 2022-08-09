package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.interceptor.RestRequestWrapper;
import io.github.nichetoolkit.rest.userlog.*;
import io.github.nichetoolkit.rest.util.BeanUtils;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.clazz.ClazzUtils;
import io.github.nichetoolkit.rice.interceptor.advice.RiceUserlogAdvice;
import io.github.nichetoolkit.rice.resolver.RiceUserHolder;
import io.github.nichetoolkit.rice.stereotype.RiceUserlog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;

import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.Set;

/**
 * <p>RiceUserlogService</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
public abstract class RiceUsernoteService<T extends RiceUsernotelog> extends RestUsernoteService implements RiceUserlogAdvice {

    private static final ThreadLocal<RiceUserlog> USER_LOG_HOLDER = new ThreadLocal<>();

    private RiceRequest request;
    private RiceResponse response;
    private RestUsernote usernote;
    private RiceUsernotelog<? extends RiceIdModel, ? extends RiceIdEntity> usernotelog;

    public RiceUsernoteService() {
        this.usernotelog = new RiceUsernotelog<>();
    }

    public abstract void userlog(RestRequestWrapper requestWrapper, RiceUserlog userlog) throws RestException;

    public abstract void usernote(T usernotelog) throws RestException;


    @SuppressWarnings(value = "unchecked")
    private Class<T> usernotelogClass() {
        return (Class<T>)((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public void userlog(HandlerMethod handlerMethod, RestRequestWrapper requestWrapper, RiceUserlog userlog) throws RestException {
        userlog(requestWrapper,userlog);
        USER_LOG_HOLDER.set(userlog);
    }

    @Override
    public void usernote(RestRequest request, RestResponse response, RestUsernote usernote) {
        try {
            if (GeneralUtils.isNotEmpty(request)) {
                this.request = new RiceRequest(request);
            }
            if (GeneralUtils.isNotEmpty(response)) {
                this.response = new RiceResponse(response);
            }
            if (GeneralUtils.isNotEmpty(usernote)) {
                this.usernote = usernote;
            }
            transfer();
            if (GeneralUtils.isNotEmpty(this.usernotelog)) {
                T renew = ClazzUtils.renew(usernotelogClass());
                if (GeneralUtils.isNotEmpty(renew)) {
                    BeanUtils.copyNonullProperties(this.usernotelog,renew);
                    renew.setTargetIds(this.usernotelog.getTargetIds());
                    usernote(renew);
                }
            } else {
                log.warn("the usernotelog is null!");
            }
        } catch (Exception exception) {
            log.warn("the usernotelog exception is ignored! error: {}", exception.getMessage());
        }
        this.usernotelog = new RiceUsernotelog<>();
    }



    protected void transfer() {
        applyUserInfo();
        applyNotelog();
        applyRequest();
        applyResponse();
        applyTargetIds();
    }

    protected void applyUserInfo() {
        RiceUserlog userlog = USER_LOG_HOLDER.get();
        if (GeneralUtils.isNotEmpty(userlog)) {
            if (GeneralUtils.isNotEmpty(userlog.userId())) {
                this.usernotelog.setUserId(userlog.userId());
            }
            this.usernotelog.setUsername(userlog.username());
        } else {
            RestUserInfo userInfo = RiceUserHolder.getUser();
            if (GeneralUtils.isNotEmpty(userInfo)) {
                if (GeneralUtils.isNotEmpty(userInfo.getId())) {
                    this.usernotelog.setUserId(String.valueOf(userInfo.getId()));
                }
                this.usernotelog.setUsername(userInfo.getUsername());
            }
        }
    }

    protected void applyNotelog() {
        if (GeneralUtils.isNotEmpty(this.usernote)) {
            usernotelog.setNotelog(this.usernote.getNotelog());
            LogType logType = this.usernote.getLogType();
            usernotelog.setLogType(logType);
            usernotelog.setUserlog(this.usernote.getUserlog());
            if (GeneralUtils.isEmpty(this.usernote.getUserlog()) && GeneralUtils.isNotEmpty(logType)) {
                usernotelog.setUserlog(logType.getField());
            }
            usernotelog.setLogKey(this.usernote.getLogKey());
            if (GeneralUtils.isEmpty(this.usernote.getLogKey()) && GeneralUtils.isNotEmpty(logType)) {
                usernotelog.setLogKey(logType.getKey());
            }
            usernotelog.setLogValue(this.usernote.getLogValue());
            if (GeneralUtils.isEmpty(this.usernote.getLogValue()) && GeneralUtils.isNotEmpty(logType)) {
                usernotelog.setLogValue(logType.getValue());
            }
        }
    }

    protected void applyRequest() {
        if (GeneralUtils.isNotEmpty(this.request)) {
            this.usernotelog.setIpAddress(this.request.getIpAddress());
            this.usernotelog.setUserAgent(this.request.getUserAgent());
            this.usernotelog.setRequestMethod(this.request.getMethod());
            this.usernotelog.setRequestParams(this.request.getParams());
            this.usernotelog.setRequestUrl(this.request.getUrl());
        }
    }

    protected void applyResponse() {
        if (GeneralUtils.isNotEmpty(this.response)) {
            if (GeneralUtils.isNotEmpty(this.response.getTime())) {
                this.usernotelog.setLogTime(new Date(this.response.getTime()));
            } else {
                this.usernotelog.setLogTime(new Date());
            }
            this.usernotelog.setResponseTime(this.response.getCostTime());
            this.usernotelog.setResponseStatus(this.response.getStatus());
            this.usernotelog.setResponseMessage(this.response.getMessage());
            this.usernotelog.setMethodName(this.response.getMethod());
            this.usernotelog.setMediaType(this.response.getMediaType());
        }
    }

    protected void applyTargetIds() {
        LogType logType = this.usernotelog.getLogType();
        if (GeneralUtils.isNotEmpty(logType)) {
            if (logType.isSave() && GeneralUtils.isNotEmpty(this.response) && this.response.isSuccess()) {
                if (GeneralUtils.isNotEmpty(this.response.getData())) {
                    if (logType == LogType.SAVE_ALL) {
                        Set<String> dataIds = this.response.toDataIds();
                        if (GeneralUtils.isNotEmpty(dataIds)) {
                            this.usernotelog.setTargetIds(dataIds);
                        }
                    } else {
                        String dataId = this.response.toDataId();
                        if (GeneralUtils.isNotEmpty(dataId)) {
                            this.usernotelog.setTargetIds(dataId);
                        }
                    }
                }
            } else if (logType.isDelete()
                    && GeneralUtils.isNotEmpty(this.response) && this.response.isSuccess()
                    && GeneralUtils.isNotEmpty(this.request)) {
                if (LogType.DELETE == logType) {
                    String paramId = this.request.toParamId();
                    if (GeneralUtils.isNotEmpty(paramId)) {
                        this.usernotelog.setTargetIds(paramId);
                    }
                } else if (LogType.DELETE_ID == logType) {
                    String urlId = this.request.toUrlId();
                    if (GeneralUtils.isNotEmpty(urlId)) {
                        this.usernotelog.setTargetIds(urlId);
                    }
                } else if (LogType.DELETE_ALL == logType) {
                    Set<String> paramIds = this.request.toParamIds();
                    Set<String> listIds = this.request.toListIds();
                    if (GeneralUtils.isNotEmpty(paramIds)) {
                        this.usernotelog.setTargetIds(paramIds);
                    } else if (GeneralUtils.isNotEmpty(listIds)) {
                        this.usernotelog.setTargetIds(listIds);
                    }
                } else if (LogType.DELETE_FILTER == logType) {
                    Set<String> filterIds = this.request.toFilterIds();
                    if (GeneralUtils.isNotEmpty(filterIds)) {
                        this.usernotelog.setTargetIds(filterIds);
                    }
                }
            }

        }
    }


}
