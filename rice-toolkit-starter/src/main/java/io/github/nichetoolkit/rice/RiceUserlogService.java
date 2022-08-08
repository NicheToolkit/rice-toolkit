package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rest.*;
import io.github.nichetoolkit.rest.log.LogType;
import io.github.nichetoolkit.rest.util.BeanUtils;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.clazz.ClazzUtils;
import io.github.nichetoolkit.rice.resolver.RiceUserHolder;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.Set;

/**
 * <p>RiceUserlogService</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
public abstract class RiceUserlogService<T extends RiceUserlog> extends RestNoteService {

    private RiceRequest request;
    private RiceResponse response;
    private RiceNotelog notelog;
    private RiceUserlog<? extends RiceIdModel, ? extends RiceIdEntity> userlog;

    public RiceUserlogService() {
        this.userlog = new RiceUserlog<>();
    }

    @SuppressWarnings(value = "unchecked")
    private Class<T> userlogClass() {
        return (Class<T>)((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public void handler(RestRequest restRequest, RestResponse restResponse, RestNotelog notelog) {
        try {
            if (GeneralUtils.isNotEmpty(restRequest)) {
                this.request = new RiceRequest(restRequest);
            }
            if (GeneralUtils.isNotEmpty(restResponse)) {
                this.response = new RiceResponse(restResponse);
            }
            if (GeneralUtils.isNotEmpty(notelog)) {
                this.notelog = new RiceNotelog(notelog);
            }
            transfer();
            if (GeneralUtils.isNotEmpty(this.userlog)) {
                T renew = ClazzUtils.renew(userlogClass());
                if (GeneralUtils.isNotEmpty(renew)) {
                    BeanUtils.copyNonullProperties(this.userlog,renew);
                    renew.setTargetIds(this.userlog.getTargetIds());
                    apply(renew);
                }
            }
            log.warn("the userlog is null!");
        } catch (Exception exception) {
            log.warn("the userlog exception is ignored! error: {}", exception.getMessage());
        }
        this.userlog = new RiceUserlog<>();
    }

    public abstract void apply(T userlog) throws RestException;

    protected void transfer() {
        applyUserInfo();
        applyNotelog();
        applyRequest();
        applyResponse();
        applyTargetIds();
    }

    protected void applyUserInfo() {
        RestUserInfo userInfo = RiceUserHolder.getUser();
        if (GeneralUtils.isNotEmpty(userInfo)) {
            if (GeneralUtils.isNotEmpty(userInfo.getId())) {
                this.userlog.setUserId(String.valueOf(userInfo.getId()));
            }
            this.userlog.setUsername(userInfo.getUsername());
        }
    }

    protected void applyNotelog() {
        if (GeneralUtils.isNotEmpty(this.notelog)) {
            userlog.setLogTitle(this.notelog.getTitle());
            LogType logType = this.notelog.getLogType();
            userlog.setLogType(logType);
            userlog.setLogMessage(this.notelog.getMessage());
            if (GeneralUtils.isEmpty(this.notelog.getMessage()) && GeneralUtils.isNotEmpty(logType)) {
                userlog.setLogMessage(logType.getField());
            }
            userlog.setLogKey(this.notelog.getKey());
            if (GeneralUtils.isEmpty(this.notelog.getKey()) && GeneralUtils.isNotEmpty(logType)) {
                userlog.setLogKey(logType.getKey());
            }
            userlog.setLogValue(this.notelog.getValue());
            if (GeneralUtils.isEmpty(this.notelog.getValue()) && GeneralUtils.isNotEmpty(logType)) {
                userlog.setLogValue(logType.getValue());
            }
        }
    }

    protected void applyRequest() {
        if (GeneralUtils.isNotEmpty(this.request)) {
            this.userlog.setIpAddress(this.request.getIpAddress());
            this.userlog.setUserAgent(this.request.getUserAgent());
            this.userlog.setRequestMethod(this.request.getMethod());
            this.userlog.setRequestParams(this.request.getParams());
            this.userlog.setRequestUrl(this.request.getUrl());
        }
    }

    protected void applyResponse() {
        if (GeneralUtils.isNotEmpty(this.response)) {
            if (GeneralUtils.isNotEmpty(this.response.getTime())) {
                this.userlog.setLogTime(new Date(this.response.getTime()));
            } else {
                this.userlog.setLogTime(new Date());
            }
            this.userlog.setResponseTime(this.response.getCostTime());
            this.userlog.setResponseStatus(this.response.getStatus());
            this.userlog.setResponseMessage(this.response.getMessage());
            this.userlog.setMethodName(this.response.getMethod());
            this.userlog.setMediaType(this.response.getMediaType());
        }
    }

    protected void applyTargetIds() {
        LogType logType = this.userlog.getLogType();
        if (GeneralUtils.isNotEmpty(logType)) {
            if (logType.isSave() && GeneralUtils.isNotEmpty(this.response) && this.response.isSuccess()) {
                if (GeneralUtils.isNotEmpty(this.response.getData())) {
                    if (logType == LogType.SAVE_ALL) {
                        Set<String> dataIds = this.response.toDataIds();
                        if (GeneralUtils.isNotEmpty(dataIds)) {
                            this.userlog.setTargetIds(dataIds);
                        }
                    } else {
                        String dataId = this.response.toDataId();
                        if (GeneralUtils.isNotEmpty(dataId)) {
                            this.userlog.setTargetIds(dataId);
                        }
                    }
                }
            } else if (logType.isDelete()
                    && GeneralUtils.isNotEmpty(this.response) && this.response.isSuccess()
                    && GeneralUtils.isNotEmpty(this.request)) {
                if (LogType.DELETE == logType) {
                    String paramId = this.request.toParamId();
                    if (GeneralUtils.isNotEmpty(paramId)) {
                        this.userlog.setTargetIds(paramId);
                    }
                } else if (LogType.DELETE_ID == logType) {
                    String urlId = this.request.toUrlId();
                    if (GeneralUtils.isNotEmpty(urlId)) {
                        this.userlog.setTargetIds(urlId);
                    }
                } else if (LogType.DELETE_ALL == logType) {
                    Set<String> paramIds = this.request.toParamIds();
                    Set<String> listIds = this.request.toListIds();
                    if (GeneralUtils.isNotEmpty(paramIds)) {
                        this.userlog.setTargetIds(paramIds);
                    } else if (GeneralUtils.isNotEmpty(listIds)) {
                        this.userlog.setTargetIds(listIds);
                    }
                } else if (LogType.DELETE_FILTER == logType) {
                    Set<String> filterIds = this.request.toFilterIds();
                    if (GeneralUtils.isNotEmpty(filterIds)) {
                        this.userlog.setTargetIds(filterIds);
                    }
                }
            }

        }
    }


}
