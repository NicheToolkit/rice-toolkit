package io.github.nichetoolkit.rice;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.type.TypeReference;
import io.github.nichetoolkit.rest.userlog.LoggingType;
import io.github.nichetoolkit.rest.userlog.RestRequestPack;
import io.github.nichetoolkit.rest.userlog.RestResponsePack;
import io.github.nichetoolkit.rest.userlog.RestUsernotePack;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.util.JsonUtils;
import io.github.nichetoolkit.rice.enums.OperateType;
import io.github.nichetoolkit.rice.enums.SaveType;
import io.github.nichetoolkit.rice.filter.IdFilter;
import io.github.nichetoolkit.rice.pack.IdPack;
import io.github.nichetoolkit.rice.pack.IdsPack;
import io.github.nichetoolkit.rice.pack.UserInfoPack;
import io.github.nichetoolkit.rice.pack.UserLoginPack;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

import java.util.*;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestUsernoteModel<M extends RestIdModel<M, E>, E extends RestIdEntity<E, M>> extends RestIdModel<M, E> {
    protected String userId;
    protected Set<String> targetIds;
    protected String username;
    protected String userAgent;
    protected String ipAddress;
    protected String requestMethod;
    protected String requestParams;
    protected String requestUrl;
    protected String methodName;
    protected String mediaType;
    protected Long responseTime;
    protected Integer responseStatus;
    protected String responseMessage;
    protected String notelog;
    protected String userlog;
    protected Integer loggingKey;
    protected String loggingValue;
    protected LoggingType loggingType;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date loggingTime;

    public RestUsernoteModel() {
    }

    public RestUsernoteModel(String id) {
        super(id);
    }

    public RestUsernoteModel(RestUsernoteModel.Builder<M, E> builder) {
        super(builder);
        this.userId = builder.userId;
        this.targetIds = builder.targetIds;
        this.username = builder.username;
        this.userAgent = builder.userAgent;
        this.ipAddress = builder.ipAddress;
        this.requestMethod = builder.requestMethod;
        this.requestParams = builder.requestParams;
        this.requestUrl = builder.requestUrl;
        this.methodName = builder.methodName;
        this.mediaType = builder.mediaType;
        this.responseTime = builder.responseTime;
        this.responseStatus = builder.responseStatus;
        this.responseMessage = builder.responseMessage;
        this.notelog = builder.notelog;
        this.userlog = builder.userlog;
        this.loggingKey = builder.loggingKey;
        this.loggingValue = builder.loggingValue;
        this.loggingType = builder.loggingType;
        this.loggingTime = builder.loggingTime;

    }

    public List<String> getTargetIds() {
        if (GeneralUtils.isNotEmpty(targetIds)) {
            return new ArrayList<>(targetIds);
        }
        return Collections.emptyList();
    }

    public void setTargetIds(@NonNull String... targetIds) {
        this.targetIds = new HashSet<>(Arrays.asList(targetIds));
    }

    @JsonSetter
    public void setTargetIds(@NonNull Collection<String> targetIds) {
        this.targetIds = new HashSet<>(targetIds);
    }

    public void addTargetIds(@NonNull String... targetIds) {
        if (GeneralUtils.isEmpty(this.targetIds)) {
            this.targetIds = new HashSet<>(Arrays.asList(targetIds));
        } else {
            this.targetIds.addAll(Arrays.asList(targetIds));
        }
    }

    public void addTargetIds(@NonNull Collection<String> targetIds) {
        if (GeneralUtils.isEmpty(this.targetIds)) {
            this.targetIds = new HashSet<>(targetIds);
        } else {
            this.targetIds.addAll(targetIds);
        }
    }

    public static String toDataId(String responseData) {
        if (GeneralUtils.isEmpty(responseData)) {
            return null;
        }
        IdPack idPack = JsonUtils.parseBean(responseData, IdPack.class);
        if (GeneralUtils.isNotEmpty(idPack) && GeneralUtils.isNotEmpty(idPack.getId())) {
            return idPack.getId();
        }
        return null;
    }

    public static Set<String> toDataIds(String responseData) {
        if (GeneralUtils.isEmpty(responseData)) {
            return null;
        }
        List<IdPack> idPacks = JsonUtils.parseList(responseData, IdPack.class);
        if (GeneralUtils.isNotEmpty(idPacks) ) {
            return idPacks.stream().map(IdPack::getId).filter(Objects::nonNull).collect(Collectors.toSet());
        }
        return null;
    }

    public static UserInfoPack toDataUserInfo(String responseData) {
        if (GeneralUtils.isNotEmpty(responseData)) {
            return JsonUtils.parseBean(responseData, UserInfoPack.class);
        }
        return null;
    }

    public static UserInfoPack toDataUserLogin(String responseData) {
        if (GeneralUtils.isEmpty(responseData)) {
            return null;
        }
        UserLoginPack userLoginPack = JsonUtils.parseBean(responseData, UserLoginPack.class);
        if (GeneralUtils.isNotEmpty(userLoginPack)) {
            return userLoginPack.getUser();
        }
        return null;
    }

    public static String toUrlId(String requestUrl) {
        if (GeneralUtils.isNotEmpty(requestUrl)) {
            String[] splitUrl = requestUrl.trim().split("/");
            if (GeneralUtils.isNotEmpty(splitUrl) && GeneralUtils.isNotEmpty(splitUrl[splitUrl.length-1])) {
                return splitUrl[splitUrl.length - 1];
            }
        }
        return null;
    }

    public static String toParamId(String requestParams) {
        if (GeneralUtils.isNotEmpty(requestParams)) {
            IdPack idPack = JsonUtils.parseBean(requestParams, IdPack.class);
            if (GeneralUtils.isNotEmpty(idPack) && GeneralUtils.isNotEmpty(idPack.getId())) {
                return idPack.getId();
            }
        }
        return null;
    }

    public static Set<String> toParamIds(String requestParams) {
        if (GeneralUtils.isNotEmpty(requestParams)) {
            IdsPack idsPack = JsonUtils.parseBean(requestParams, IdsPack.class);
            if (GeneralUtils.isNotEmpty(idsPack) && GeneralUtils.isNotEmpty(idsPack.getIds())) {
                String packIds = idsPack.getIds().trim();
                if (!packIds.startsWith("[")) {
                    packIds = "[".concat(packIds);
                }
                if (packIds.endsWith(",") ){
                    packIds = packIds.substring(0, packIds.length() - 1);
                }
                if (!packIds.endsWith("]")) {
                    packIds = packIds.concat("]");
                }
                List<String> idList = JsonUtils.parseList(packIds, String.class);
                return new HashSet<>(idList);
            }
        }
        return null;
    }

    public static Set<String> toListIds(String requestBody) {
        if (GeneralUtils.isNotEmpty(requestBody)) {
            List<String> idList = JsonUtils.parseBean(requestBody, new TypeReference<List<String>>() {});
            if (GeneralUtils.isNotEmpty(idList)) {
                return new HashSet<>(idList);
            }
        }
        return null;
    }


    public static Set<String> toFilterIds(String requestBody) {
        if (GeneralUtils.isNotEmpty(requestBody)) {
            IdFilter<String,String> idFilter = JsonUtils.parseBean(requestBody, new TypeReference<IdFilter<String,String>>() {});
            if (GeneralUtils.isNotEmpty(idFilter) && GeneralUtils.isNotEmpty(idFilter.toIds())) {
                return new HashSet<>(idFilter.toIds());
            }
        }
        return null;
    }

    public static <M extends RestIdModel<M, E>, E extends RestIdEntity<E, M>> RestUsernoteModel.Builder<M, E> createBuilder() {
        return new RestUsernoteModel.Builder<>();
    }

    public static class Builder<M extends RestIdModel<M, E>, E extends RestIdEntity<E, M>> extends RestIdModel.Builder<M, E> {

        protected String userId;
        protected Set<String> targetIds;
        protected String username;
        protected String userAgent;
        protected String ipAddress;
        protected String requestMethod;
        protected String requestParams;
        protected String requestUrl;
        protected String methodName;
        protected String mediaType;
        protected Long responseTime;
        protected Integer responseStatus;
        protected String responseMessage;
        protected String notelog;
        protected String userlog;
        protected Integer loggingKey;
        protected String loggingValue;
        protected LoggingType loggingType;
        protected Date loggingTime;

        public Builder() {
        }

        @Override
        public RestUsernoteModel.Builder<M, E> id(String id) {
            this.id = id;
            return this;
        }

        @Override
        public RestUsernoteModel.Builder<M, E> createTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }

        @Override
        public RestUsernoteModel.Builder<M, E> createTime(@NonNull Long createTime) {
            this.createTime = new Date(createTime);
            return this;
        }

        @Override
        public RestUsernoteModel.Builder<M, E> updateTime(Date updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        @Override
        public RestUsernoteModel.Builder<M, E> updateTime(@NonNull Long updateTime) {
            this.updateTime = new Date(updateTime);
            return this;
        }

        @Override
        public RestUsernoteModel.Builder<M, E> operate(OperateType operate) {
            this.operate = operate;
            return this;
        }

        @Override
        public RestUsernoteModel.Builder<M, E> operate(Integer operate) {
            this.operate = OperateType.parseKey(operate);
            return this;
        }

        @Override
        public RestUsernoteModel.Builder<M, E> save(SaveType save) {
            this.save = save;
            return this;
        }

        @Override
        public RestUsernoteModel.Builder<M, E> save(Integer save) {
            this.save = SaveType.parseKey(save);
            return this;
        }

        public RestUsernoteModel.Builder<M, E> userId(String userId) {
            this.userId = userId;
            return this;
        }

        public RestUsernoteModel.Builder<M, E> targetIds(@NonNull Collection<String> targetIds) {
            this.targetIds = new HashSet<>(targetIds);
            return this;
        }

        public RestUsernoteModel.Builder<M, E> targetIds(@NonNull String... targetIds) {
            this.targetIds = new HashSet<>(Arrays.asList(targetIds));
            return this;
        }

        public RestUsernoteModel.Builder<M, E> username(String username) {
            this.username = username;
            return this;
        }

        public RestUsernoteModel.Builder<M, E> userAgent(String userAgent) {
            this.userAgent = userAgent;
            return this;
        }

        public RestUsernoteModel.Builder<M, E> ipAddress(String ipAddress) {
            this.ipAddress = ipAddress;
            return this;
        }

        public RestUsernoteModel.Builder<M, E> requestMethod(String requestMethod) {
            this.requestMethod = requestMethod;
            return this;
        }

        public RestUsernoteModel.Builder<M, E> requestParams(String requestParams) {
            this.requestParams = requestParams;
            return this;
        }

        public RestUsernoteModel.Builder<M, E> requestUrl(String requestUrl) {
            this.requestUrl = requestUrl;
            return this;
        }

        public RestUsernoteModel.Builder<M, E> methodName(String methodName) {
            this.methodName = methodName;
            return this;
        }

        public RestUsernoteModel.Builder<M, E> mediaType(String mediaType) {
            this.mediaType = mediaType;
            return this;
        }

        public RestUsernoteModel.Builder<M, E> responseTime(Long responseTime) {
            this.responseTime = responseTime;
            return this;
        }

        public RestUsernoteModel.Builder<M, E> responseStatus(Integer responseStatus) {
            this.responseStatus = responseStatus;
            return this;
        }

        public RestUsernoteModel.Builder<M, E> responseMessage(String responseMessage) {
            this.responseMessage = responseMessage;
            return this;
        }

        public RestUsernoteModel.Builder<M, E> notelog(String notelog) {
            this.notelog = notelog;
            return this;
        }

        public RestUsernoteModel.Builder<M, E> userlog(String userlog) {
            this.userlog = userlog;
            return this;
        }

        public RestUsernoteModel.Builder<M, E> loggingKey(Integer loggingKey) {
            this.loggingKey = loggingKey;
            return this;
        }

        public RestUsernoteModel.Builder<M, E> loggingValue(String loggingValue) {
            this.loggingValue = loggingValue;
            return this;
        }

        public RestUsernoteModel.Builder<M, E> loggingType(LoggingType loggingType) {
            this.loggingType = loggingType;
            return this;
        }

        public RestUsernoteModel.Builder<M, E> loggingType(Integer loggingType) {
            this.loggingType = LoggingType.parseKey(loggingType);
            return this;
        }

        public RestUsernoteModel.Builder<M, E> loggingTime(@NonNull Long loggingTime) {
            this.loggingTime = new Date(loggingTime);
            return this;
        }

        public RestUsernoteModel.Builder<M, E> loggingTime(Date loggingTime) {
            this.loggingTime = loggingTime;
            return this;
        }

        public RestUsernoteModel.Builder<M, E> usernote(@NonNull RestUsernotePack usernote) {
            this.notelog = usernote.getNotelog();
            this.loggingType = usernote.getLoggingType();
            this.userlog = usernote.getUserlog();
            if (GeneralUtils.isEmpty(this.userlog) && GeneralUtils.isNotEmpty(this.loggingType)) {
                this.userlog = this.loggingType.getField();
            }
            this.loggingKey = usernote.getLoggingKey();
            if (GeneralUtils.isEmpty(loggingKey) && GeneralUtils.isNotEmpty(this.loggingType)) {
                this.loggingKey = this.loggingType.getKey();
            }
            this.loggingValue = usernote.getLoggingValue();
            if (GeneralUtils.isEmpty(this.loggingValue) && GeneralUtils.isNotEmpty(this.loggingType)) {
                this.loggingValue = this.loggingType.getValue();
            }
            return this;
        }

        public RestUsernoteModel.Builder<M, E> request(@NonNull RestRequestPack request) {
            this.ipAddress = request.getIpAddress();
            this.userAgent = request.getUserAgent();
            this.requestMethod = request.getMethod();
            this.requestParams = request.getParams();
            this.requestUrl = request.getUrl();
            return this;
        }

        public RestUsernoteModel.Builder<M, E> response(@NonNull RestResponsePack response) {
            this.loggingTime = new Date(response.getTime());
            this.responseTime = response.getCostTime();
            this.responseStatus = response.getStatus();
            this.responseMessage = response.getMessage();
            this.methodName = response.getMethod();
            this.mediaType = response.getMediaType();
            return this;
        }

        @Override
        public RestUsernoteModel<M, E> build() {
            return new RestUsernoteModel<>(this);
        }
    }

}
