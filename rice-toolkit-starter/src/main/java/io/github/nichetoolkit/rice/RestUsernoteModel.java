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

/**
 * <code>RestUsernoteModel</code>
 * <p>The type rest usernote model class.</p>
 * @param <M> {@link io.github.nichetoolkit.rice.RestIdModel} <p>the generic parameter is <code>RestIdModel</code> type.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.RestIdEntity} <p>the generic parameter is <code>RestIdEntity</code> type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestIdModel
 * @see io.github.nichetoolkit.rice.RestIdEntity
 * @see lombok.Data
 * @see lombok.EqualsAndHashCode
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk1.8
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestUsernoteModel<M extends RestIdModel<M, E>, E extends RestIdEntity<E, M>> extends RestIdModel<M, E> {
    /**
     * <code>userId</code>
     * {@link java.lang.String} <p>the <code>userId</code> field.</p>
     * @see java.lang.String
     */
    protected String userId;
    /**
     * <code>targetIds</code>
     * {@link java.util.Set} <p>the <code>targetIds</code> field.</p>
     * @see java.util.Set
     */
    protected Set<String> targetIds;
    /**
     * <code>username</code>
     * {@link java.lang.String} <p>the <code>username</code> field.</p>
     * @see java.lang.String
     */
    protected String username;
    /**
     * <code>userAgent</code>
     * {@link java.lang.String} <p>the <code>userAgent</code> field.</p>
     * @see java.lang.String
     */
    protected String userAgent;
    /**
     * <code>ipAddress</code>
     * {@link java.lang.String} <p>the <code>ipAddress</code> field.</p>
     * @see java.lang.String
     */
    protected String ipAddress;
    /**
     * <code>requestMethod</code>
     * {@link java.lang.String} <p>the <code>requestMethod</code> field.</p>
     * @see java.lang.String
     */
    protected String requestMethod;
    /**
     * <code>requestParams</code>
     * {@link java.lang.String} <p>the <code>requestParams</code> field.</p>
     * @see java.lang.String
     */
    protected String requestParams;
    /**
     * <code>requestUrl</code>
     * {@link java.lang.String} <p>the <code>requestUrl</code> field.</p>
     * @see java.lang.String
     */
    protected String requestUrl;
    /**
     * <code>methodName</code>
     * {@link java.lang.String} <p>the <code>methodName</code> field.</p>
     * @see java.lang.String
     */
    protected String methodName;
    /**
     * <code>mediaType</code>
     * {@link java.lang.String} <p>the <code>mediaType</code> field.</p>
     * @see java.lang.String
     */
    protected String mediaType;
    /**
     * <code>responseTime</code>
     * {@link java.lang.Long} <p>the <code>responseTime</code> field.</p>
     * @see java.lang.Long
     */
    protected Long responseTime;
    /**
     * <code>responseStatus</code>
     * {@link java.lang.Integer} <p>the <code>responseStatus</code> field.</p>
     * @see java.lang.Integer
     */
    protected Integer responseStatus;
    /**
     * <code>responseMessage</code>
     * {@link java.lang.String} <p>the <code>responseMessage</code> field.</p>
     * @see java.lang.String
     */
    protected String responseMessage;
    /**
     * <code>notelog</code>
     * {@link java.lang.String} <p>the <code>notelog</code> field.</p>
     * @see java.lang.String
     */
    protected String notelog;
    /**
     * <code>userlog</code>
     * {@link java.lang.String} <p>the <code>userlog</code> field.</p>
     * @see java.lang.String
     */
    protected String userlog;
    /**
     * <code>loggingKey</code>
     * {@link java.lang.Integer} <p>the <code>loggingKey</code> field.</p>
     * @see java.lang.Integer
     */
    protected String loggingKey;
    /**
     * <code>loggingValue</code>
     * {@link java.lang.String} <p>the <code>loggingValue</code> field.</p>
     * @see java.lang.String
     */
    protected String loggingValue;
    /**
     * <code>loggingType</code>
     * {@link io.github.nichetoolkit.rest.userlog.LoggingType} <p>the <code>loggingType</code> field.</p>
     * @see io.github.nichetoolkit.rest.userlog.LoggingType
     */
    protected LoggingType loggingType;
    /**
     * <code>loggingTime</code>
     * {@link java.util.Date} <p>the <code>loggingTime</code> field.</p>
     * @see java.util.Date
     * @see org.springframework.format.annotation.DateTimeFormat
     * @see com.fasterxml.jackson.annotation.JsonFormat
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date loggingTime;

    /**
     * <code>RestUsernoteModel</code>
     * Instantiates a new rest usernote model.
     */
    public RestUsernoteModel() {
    }

    /**
     * <code>RestUsernoteModel</code>
     * Instantiates a new rest usernote model.
     * @param id {@link java.lang.String} <p>the id parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public RestUsernoteModel(String id) {
        super(id);
    }

    /**
     * <code>RestUsernoteModel</code>
     * Instantiates a new rest usernote model.
     * @param builder {@link io.github.nichetoolkit.rice.RestUsernoteModel.Builder} <p>the builder parameter is <code>Builder</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestUsernoteModel.Builder
     */
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

    /**
     * <code>getTargetIds</code>
     * <p>the target ids getter method.</p>
     * @return {@link java.util.List} <p>the target ids return object is <code>List</code> type.</p>
     * @see java.util.List
     */
    public List<String> getTargetIds() {
        if (GeneralUtils.isNotEmpty(targetIds)) {
            return new ArrayList<>(targetIds);
        }
        return Collections.emptyList();
    }

    /**
     * <code>setTargetIds</code>
     * <p>the target ids setter method.</p>
     * @param targetIds {@link java.lang.String} <p>the target ids parameter is <code>String</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.lang.NonNull
     */
    public void setTargetIds(@NonNull String... targetIds) {
        this.targetIds = new HashSet<>(Arrays.asList(targetIds));
    }

    /**
     * <code>setTargetIds</code>
     * <p>the target ids setter method.</p>
     * @param targetIds {@link java.util.Collection} <p>the target ids parameter is <code>Collection</code> type.</p>
     * @see java.util.Collection
     * @see org.springframework.lang.NonNull
     * @see com.fasterxml.jackson.annotation.JsonSetter
     */
    @JsonSetter
    public void setTargetIds(@NonNull Collection<String> targetIds) {
        this.targetIds = new HashSet<>(targetIds);
    }

    /**
     * <code>addTargetIds</code>
     * <p>the target ids method.</p>
     * @param targetIds {@link java.lang.String} <p>the target ids parameter is <code>String</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.lang.NonNull
     */
    public void addTargetIds(@NonNull String... targetIds) {
        if (GeneralUtils.isEmpty(this.targetIds)) {
            this.targetIds = new HashSet<>(Arrays.asList(targetIds));
        } else {
            this.targetIds.addAll(Arrays.asList(targetIds));
        }
    }

    /**
     * <code>addTargetIds</code>
     * <p>the target ids method.</p>
     * @param targetIds {@link java.util.Collection} <p>the target ids parameter is <code>Collection</code> type.</p>
     * @see java.util.Collection
     * @see org.springframework.lang.NonNull
     */
    public void addTargetIds(@NonNull Collection<String> targetIds) {
        if (GeneralUtils.isEmpty(this.targetIds)) {
            this.targetIds = new HashSet<>(targetIds);
        } else {
            this.targetIds.addAll(targetIds);
        }
    }

    /**
     * <code>toDataId</code>
     * <p>the data id method.</p>
     * @param responseData {@link java.lang.String} <p>the response data parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>the data id return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
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

    /**
     * <code>toDataIds</code>
     * <p>the data ids method.</p>
     * @param responseData {@link java.lang.String} <p>the response data parameter is <code>String</code> type.</p>
     * @return {@link java.util.Set} <p>the data ids return object is <code>Set</code> type.</p>
     * @see java.lang.String
     * @see java.util.Set
     */
    public static Set<String> toDataIds(String responseData) {
        if (GeneralUtils.isEmpty(responseData)) {
            return null;
        }
        List<IdPack> idPacks = JsonUtils.parseList(responseData, IdPack.class);
        if (GeneralUtils.isNotEmpty(idPacks)) {
            return idPacks.stream().map(IdPack::getId).filter(Objects::nonNull).collect(Collectors.toSet());
        }
        return null;
    }

    /**
     * <code>toDataUserInfo</code>
     * <p>the data user info method.</p>
     * @param responseData {@link java.lang.String} <p>the response data parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.pack.UserInfoPack} <p>the data user info return object is <code>UserInfoPack</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rice.pack.UserInfoPack
     */
    public static UserInfoPack toDataUserInfo(String responseData) {
        if (GeneralUtils.isNotEmpty(responseData)) {
            return JsonUtils.parseBean(responseData, UserInfoPack.class);
        }
        return null;
    }

    /**
     * <code>toDataUserLogin</code>
     * <p>the data user login method.</p>
     * @param responseData {@link java.lang.String} <p>the response data parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.pack.UserInfoPack} <p>the data user login return object is <code>UserInfoPack</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rice.pack.UserInfoPack
     */
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

    /**
     * <code>toUrlId</code>
     * <p>the url id method.</p>
     * @param requestUrl {@link java.lang.String} <p>the request url parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>the url id return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static String toUrlId(String requestUrl) {
        if (GeneralUtils.isNotEmpty(requestUrl)) {
            String[] splitUrl = requestUrl.trim().split("/");
            if (GeneralUtils.isNotEmpty(splitUrl) && GeneralUtils.isNotEmpty(splitUrl[splitUrl.length - 1])) {
                return splitUrl[splitUrl.length - 1];
            }
        }
        return null;
    }

    /**
     * <code>toParamId</code>
     * <p>the param id method.</p>
     * @param requestParams {@link java.lang.String} <p>the request params parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>the param id return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static String toParamId(String requestParams) {
        if (GeneralUtils.isNotEmpty(requestParams)) {
            IdPack idPack = JsonUtils.parseBean(requestParams, IdPack.class);
            if (GeneralUtils.isNotEmpty(idPack) && GeneralUtils.isNotEmpty(idPack.getId())) {
                return idPack.getId();
            }
        }
        return null;
    }

    /**
     * <code>toParamIds</code>
     * <p>the param ids method.</p>
     * @param requestParams {@link java.lang.String} <p>the request params parameter is <code>String</code> type.</p>
     * @return {@link java.util.Set} <p>the param ids return object is <code>Set</code> type.</p>
     * @see java.lang.String
     * @see java.util.Set
     */
    public static Set<String> toParamIds(String requestParams) {
        if (GeneralUtils.isNotEmpty(requestParams)) {
            IdsPack idsPack = JsonUtils.parseBean(requestParams, IdsPack.class);
            if (GeneralUtils.isNotEmpty(idsPack) && GeneralUtils.isNotEmpty(idsPack.getIds())) {
                String packIds = idsPack.getIds().trim();
                if (!packIds.startsWith("[")) {
                    packIds = "[".concat(packIds);
                }
                if (packIds.endsWith(",")) {
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

    /**
     * <code>toListIds</code>
     * <p>the list ids method.</p>
     * @param requestBody {@link java.lang.String} <p>the request body parameter is <code>String</code> type.</p>
     * @return {@link java.util.Set} <p>the list ids return object is <code>Set</code> type.</p>
     * @see java.lang.String
     * @see java.util.Set
     */
    public static Set<String> toListIds(String requestBody) {
        if (GeneralUtils.isNotEmpty(requestBody)) {
            List<String> idList = JsonUtils.parseBean(requestBody, new TypeReference<List<String>>() {
            });
            if (GeneralUtils.isNotEmpty(idList)) {
                return new HashSet<>(idList);
            }
        }
        return null;
    }


    /**
     * <code>toFilterIds</code>
     * <p>the filter ids method.</p>
     * @param requestBody {@link java.lang.String} <p>the request body parameter is <code>String</code> type.</p>
     * @return {@link java.util.Set} <p>the filter ids return object is <code>Set</code> type.</p>
     * @see java.lang.String
     * @see java.util.Set
     */
    public static Set<String> toFilterIds(String requestBody) {
        if (GeneralUtils.isNotEmpty(requestBody)) {
            IdFilter<String, String> idFilter = JsonUtils.parseBean(requestBody, new TypeReference<IdFilter<String, String>>() {
            });
            if (GeneralUtils.isNotEmpty(idFilter) && GeneralUtils.isNotEmpty(idFilter.toIds())) {
                return new HashSet<>(idFilter.toIds());
            }
        }
        return null;
    }

    /**
     * <code>createBuilder</code>
     * <p>the builder method.</p>
     * @param <M> {@link io.github.nichetoolkit.rice.RestIdModel} <p>the generic parameter is <code>RestIdModel</code> type.</p>
     * @param <E> {@link io.github.nichetoolkit.rice.RestIdEntity} <p>the generic parameter is <code>RestIdEntity</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.RestUsernoteModel.Builder} <p>the builder return object is <code>Builder</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestIdModel
     * @see io.github.nichetoolkit.rice.RestIdEntity
     * @see io.github.nichetoolkit.rice.RestUsernoteModel.Builder
     */
    public static <M extends RestIdModel<M, E>, E extends RestIdEntity<E, M>> RestUsernoteModel.Builder<M, E> createBuilder() {
        return new RestUsernoteModel.Builder<>();
    }

    /**
     * <code>Builder</code>
     * <p>The type builder class.</p>
     * @param <M> {@link io.github.nichetoolkit.rice.RestIdModel} <p>the generic parameter is <code>RestIdModel</code> type.</p>
     * @param <E> {@link io.github.nichetoolkit.rice.RestIdEntity} <p>the generic parameter is <code>RestIdEntity</code> type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rice.RestIdModel
     * @see io.github.nichetoolkit.rice.RestIdEntity
     * @see io.github.nichetoolkit.rice.RestIdModel.Builder
     * @since Jdk1.8
     */
    public static class Builder<M extends RestIdModel<M, E>, E extends RestIdEntity<E, M>> extends RestIdModel.Builder<M, E> {

        /**
         * <code>userId</code>
         * {@link java.lang.String} <p>the <code>userId</code> field.</p>
         * @see java.lang.String
         */
        protected String userId;
        /**
         * <code>targetIds</code>
         * {@link java.util.Set} <p>the <code>targetIds</code> field.</p>
         * @see java.util.Set
         */
        protected Set<String> targetIds;
        /**
         * <code>username</code>
         * {@link java.lang.String} <p>the <code>username</code> field.</p>
         * @see java.lang.String
         */
        protected String username;
        /**
         * <code>userAgent</code>
         * {@link java.lang.String} <p>the <code>userAgent</code> field.</p>
         * @see java.lang.String
         */
        protected String userAgent;
        /**
         * <code>ipAddress</code>
         * {@link java.lang.String} <p>the <code>ipAddress</code> field.</p>
         * @see java.lang.String
         */
        protected String ipAddress;
        /**
         * <code>requestMethod</code>
         * {@link java.lang.String} <p>the <code>requestMethod</code> field.</p>
         * @see java.lang.String
         */
        protected String requestMethod;
        /**
         * <code>requestParams</code>
         * {@link java.lang.String} <p>the <code>requestParams</code> field.</p>
         * @see java.lang.String
         */
        protected String requestParams;
        /**
         * <code>requestUrl</code>
         * {@link java.lang.String} <p>the <code>requestUrl</code> field.</p>
         * @see java.lang.String
         */
        protected String requestUrl;
        /**
         * <code>methodName</code>
         * {@link java.lang.String} <p>the <code>methodName</code> field.</p>
         * @see java.lang.String
         */
        protected String methodName;
        /**
         * <code>mediaType</code>
         * {@link java.lang.String} <p>the <code>mediaType</code> field.</p>
         * @see java.lang.String
         */
        protected String mediaType;
        /**
         * <code>responseTime</code>
         * {@link java.lang.Long} <p>the <code>responseTime</code> field.</p>
         * @see java.lang.Long
         */
        protected Long responseTime;
        /**
         * <code>responseStatus</code>
         * {@link java.lang.Integer} <p>the <code>responseStatus</code> field.</p>
         * @see java.lang.Integer
         */
        protected Integer responseStatus;
        /**
         * <code>responseMessage</code>
         * {@link java.lang.String} <p>the <code>responseMessage</code> field.</p>
         * @see java.lang.String
         */
        protected String responseMessage;
        /**
         * <code>notelog</code>
         * {@link java.lang.String} <p>the <code>notelog</code> field.</p>
         * @see java.lang.String
         */
        protected String notelog;
        /**
         * <code>userlog</code>
         * {@link java.lang.String} <p>the <code>userlog</code> field.</p>
         * @see java.lang.String
         */
        protected String userlog;
        /**
         * <code>loggingKey</code>
         * {@link java.lang.Integer} <p>the <code>loggingKey</code> field.</p>
         * @see java.lang.Integer
         */
        protected String loggingKey;
        /**
         * <code>loggingValue</code>
         * {@link java.lang.String} <p>the <code>loggingValue</code> field.</p>
         * @see java.lang.String
         */
        protected String loggingValue;
        /**
         * <code>loggingType</code>
         * {@link io.github.nichetoolkit.rest.userlog.LoggingType} <p>the <code>loggingType</code> field.</p>
         * @see io.github.nichetoolkit.rest.userlog.LoggingType
         */
        protected LoggingType loggingType;
        /**
         * <code>loggingTime</code>
         * {@link java.util.Date} <p>the <code>loggingTime</code> field.</p>
         * @see java.util.Date
         */
        protected Date loggingTime;

        /**
         * <code>Builder</code>
         * Instantiates a new builder.
         */
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

        /**
         * <code>userId</code>
         * <p>the id method.</p>
         * @param userId {@link java.lang.String} <p>the user id parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.RestUsernoteModel.Builder} <p>the id return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public RestUsernoteModel.Builder<M, E> userId(String userId) {
            this.userId = userId;
            return this;
        }

        /**
         * <code>targetIds</code>
         * <p>the ids method.</p>
         * @param targetIds {@link java.util.Collection} <p>the target ids parameter is <code>Collection</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.RestUsernoteModel.Builder} <p>the ids return object is <code>Builder</code> type.</p>
         * @see java.util.Collection
         * @see org.springframework.lang.NonNull
         */
        public RestUsernoteModel.Builder<M, E> targetIds(@NonNull Collection<String> targetIds) {
            this.targetIds = new HashSet<>(targetIds);
            return this;
        }

        /**
         * <code>targetIds</code>
         * <p>the ids method.</p>
         * @param targetIds {@link java.lang.String} <p>the target ids parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.RestUsernoteModel.Builder} <p>the ids return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         * @see org.springframework.lang.NonNull
         */
        public RestUsernoteModel.Builder<M, E> targetIds(@NonNull String... targetIds) {
            this.targetIds = new HashSet<>(Arrays.asList(targetIds));
            return this;
        }

        /**
         * <code>username</code>
         * <p>the method.</p>
         * @param username {@link java.lang.String} <p>the username parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.RestUsernoteModel.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public RestUsernoteModel.Builder<M, E> username(String username) {
            this.username = username;
            return this;
        }

        /**
         * <code>userAgent</code>
         * <p>the agent method.</p>
         * @param userAgent {@link java.lang.String} <p>the user agent parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.RestUsernoteModel.Builder} <p>the agent return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public RestUsernoteModel.Builder<M, E> userAgent(String userAgent) {
            this.userAgent = userAgent;
            return this;
        }

        /**
         * <code>ipAddress</code>
         * <p>the address method.</p>
         * @param ipAddress {@link java.lang.String} <p>the ip address parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.RestUsernoteModel.Builder} <p>the address return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public RestUsernoteModel.Builder<M, E> ipAddress(String ipAddress) {
            this.ipAddress = ipAddress;
            return this;
        }

        /**
         * <code>requestMethod</code>
         * <p>the method method.</p>
         * @param requestMethod {@link java.lang.String} <p>the request method parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.RestUsernoteModel.Builder} <p>the method return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public RestUsernoteModel.Builder<M, E> requestMethod(String requestMethod) {
            this.requestMethod = requestMethod;
            return this;
        }

        /**
         * <code>requestParams</code>
         * <p>the params method.</p>
         * @param requestParams {@link java.lang.String} <p>the request params parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.RestUsernoteModel.Builder} <p>the params return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public RestUsernoteModel.Builder<M, E> requestParams(String requestParams) {
            this.requestParams = requestParams;
            return this;
        }

        /**
         * <code>requestUrl</code>
         * <p>the url method.</p>
         * @param requestUrl {@link java.lang.String} <p>the request url parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.RestUsernoteModel.Builder} <p>the url return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public RestUsernoteModel.Builder<M, E> requestUrl(String requestUrl) {
            this.requestUrl = requestUrl;
            return this;
        }

        /**
         * <code>methodName</code>
         * <p>the name method.</p>
         * @param methodName {@link java.lang.String} <p>the method name parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.RestUsernoteModel.Builder} <p>the name return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public RestUsernoteModel.Builder<M, E> methodName(String methodName) {
            this.methodName = methodName;
            return this;
        }

        /**
         * <code>mediaType</code>
         * <p>the type method.</p>
         * @param mediaType {@link java.lang.String} <p>the media type parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.RestUsernoteModel.Builder} <p>the type return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public RestUsernoteModel.Builder<M, E> mediaType(String mediaType) {
            this.mediaType = mediaType;
            return this;
        }

        /**
         * <code>responseTime</code>
         * <p>the time method.</p>
         * @param responseTime {@link java.lang.Long} <p>the response time parameter is <code>Long</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.RestUsernoteModel.Builder} <p>the time return object is <code>Builder</code> type.</p>
         * @see java.lang.Long
         */
        public RestUsernoteModel.Builder<M, E> responseTime(Long responseTime) {
            this.responseTime = responseTime;
            return this;
        }

        /**
         * <code>responseStatus</code>
         * <p>the status method.</p>
         * @param responseStatus {@link java.lang.Integer} <p>the response status parameter is <code>Integer</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.RestUsernoteModel.Builder} <p>the status return object is <code>Builder</code> type.</p>
         * @see java.lang.Integer
         */
        public RestUsernoteModel.Builder<M, E> responseStatus(Integer responseStatus) {
            this.responseStatus = responseStatus;
            return this;
        }

        /**
         * <code>responseMessage</code>
         * <p>the message method.</p>
         * @param responseMessage {@link java.lang.String} <p>the response message parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.RestUsernoteModel.Builder} <p>the message return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public RestUsernoteModel.Builder<M, E> responseMessage(String responseMessage) {
            this.responseMessage = responseMessage;
            return this;
        }

        /**
         * <code>notelog</code>
         * <p>the method.</p>
         * @param notelog {@link java.lang.String} <p>the notelog parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.RestUsernoteModel.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public RestUsernoteModel.Builder<M, E> notelog(String notelog) {
            this.notelog = notelog;
            return this;
        }

        /**
         * <code>userlog</code>
         * <p>the method.</p>
         * @param userlog {@link java.lang.String} <p>the userlog parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.RestUsernoteModel.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public RestUsernoteModel.Builder<M, E> userlog(String userlog) {
            this.userlog = userlog;
            return this;
        }

        /**
         * <code>loggingKey</code>
         * <p>the key method.</p>
         * @param loggingKey {@link java.lang.Integer} <p>the logging key parameter is <code>Integer</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.RestUsernoteModel.Builder} <p>the key return object is <code>Builder</code> type.</p>
         * @see java.lang.Integer
         */
        public RestUsernoteModel.Builder<M, E> loggingKey(String loggingKey) {
            this.loggingKey = loggingKey;
            return this;
        }

        /**
         * <code>loggingValue</code>
         * <p>the value method.</p>
         * @param loggingValue {@link java.lang.String} <p>the logging value parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.RestUsernoteModel.Builder} <p>the value return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public RestUsernoteModel.Builder<M, E> loggingValue(String loggingValue) {
            this.loggingValue = loggingValue;
            return this;
        }

        /**
         * <code>loggingType</code>
         * <p>the type method.</p>
         * @param loggingType {@link io.github.nichetoolkit.rest.userlog.LoggingType} <p>the logging type parameter is <code>LoggingType</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.RestUsernoteModel.Builder} <p>the type return object is <code>Builder</code> type.</p>
         * @see io.github.nichetoolkit.rest.userlog.LoggingType
         */
        public RestUsernoteModel.Builder<M, E> loggingType(LoggingType loggingType) {
            this.loggingType = loggingType;
            return this;
        }

        /**
         * <code>loggingType</code>
         * <p>the type method.</p>
         * @param loggingType {@link java.lang.Integer} <p>the logging type parameter is <code>Integer</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.RestUsernoteModel.Builder} <p>the type return object is <code>Builder</code> type.</p>
         * @see java.lang.Integer
         */
        public RestUsernoteModel.Builder<M, E> loggingType(String loggingType) {
            this.loggingType = LoggingType.parseKey(loggingType);
            return this;
        }

        /**
         * <code>loggingTime</code>
         * <p>the time method.</p>
         * @param loggingTime {@link java.lang.Long} <p>the logging time parameter is <code>Long</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.RestUsernoteModel.Builder} <p>the time return object is <code>Builder</code> type.</p>
         * @see java.lang.Long
         * @see org.springframework.lang.NonNull
         */
        public RestUsernoteModel.Builder<M, E> loggingTime(@NonNull Long loggingTime) {
            this.loggingTime = new Date(loggingTime);
            return this;
        }

        /**
         * <code>loggingTime</code>
         * <p>the time method.</p>
         * @param loggingTime {@link java.util.Date} <p>the logging time parameter is <code>Date</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.RestUsernoteModel.Builder} <p>the time return object is <code>Builder</code> type.</p>
         * @see java.util.Date
         */
        public RestUsernoteModel.Builder<M, E> loggingTime(Date loggingTime) {
            this.loggingTime = loggingTime;
            return this;
        }

        /**
         * <code>usernote</code>
         * <p>the method.</p>
         * @param usernote {@link io.github.nichetoolkit.rest.userlog.RestUsernotePack} <p>the usernote parameter is <code>RestUsernotePack</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.RestUsernoteModel.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see io.github.nichetoolkit.rest.userlog.RestUsernotePack
         * @see org.springframework.lang.NonNull
         */
        public RestUsernoteModel.Builder<M, E> usernote(@NonNull RestUsernotePack usernote) {
            this.notelog = usernote.getNotelog();
            this.loggingType = usernote.getLoggingType();
            this.userlog = usernote.getUserlog();
            if (GeneralUtils.isEmpty(this.userlog) && GeneralUtils.isNotEmpty(this.loggingType)) {
                this.userlog = this.loggingType.getValue();
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

        /**
         * <code>request</code>
         * <p>the method.</p>
         * @param request {@link io.github.nichetoolkit.rest.userlog.RestRequestPack} <p>the request parameter is <code>RestRequestPack</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.RestUsernoteModel.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see io.github.nichetoolkit.rest.userlog.RestRequestPack
         * @see org.springframework.lang.NonNull
         */
        public RestUsernoteModel.Builder<M, E> request(@NonNull RestRequestPack request) {
            this.ipAddress = request.getIpAddress();
            this.userAgent = request.getUserAgent();
            this.requestMethod = request.getMethod();
            this.requestParams = request.getParams();
            this.requestUrl = request.getUrl();
            return this;
        }

        /**
         * <code>response</code>
         * <p>the method.</p>
         * @param response {@link io.github.nichetoolkit.rest.userlog.RestResponsePack} <p>the response parameter is <code>RestResponsePack</code> type.</p>
         * @return {@link io.github.nichetoolkit.rice.RestUsernoteModel.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see io.github.nichetoolkit.rest.userlog.RestResponsePack
         * @see org.springframework.lang.NonNull
         */
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
