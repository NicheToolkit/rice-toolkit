package io.github.nichetoolkit.rice;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import tools.jackson.core.type.TypeReference;
import io.github.nichetoolkit.rest.userlog.LoggingType;
import io.github.nichetoolkit.rest.userlog.RestRequestPack;
import io.github.nichetoolkit.rest.userlog.RestResponsePack;
import io.github.nichetoolkit.rest.userlog.RestUsernotePack;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.util.JsonUtils;
import io.github.nichetoolkit.rice.filter.IdFilter;
import io.github.nichetoolkit.rice.pack.IdPack;
import io.github.nichetoolkit.rice.pack.IdsPack;
import io.github.nichetoolkit.rice.pack.UserInfoPack;
import io.github.nichetoolkit.rice.pack.UserLoginPack;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;
import org.jspecify.annotations.NonNull;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <code>RestUsernoteModel</code>
 * <p>The rest usernote model class.</p>
 * @param <M> {@link io.github.nichetoolkit.rice.RestIdModel} <p>The generic parameter is <code>RestIdModel</code> type.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.RestIdEntity} <p>The generic parameter is <code>RestIdEntity</code> type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestIdModel
 * @see io.github.nichetoolkit.rice.RestIdEntity
 * @see lombok.Setter
 * @see lombok.Getter
 * @see lombok.experimental.SuperBuilder
 * @see lombok.EqualsAndHashCode
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk17
 */
@Setter
@Getter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestUsernoteModel<M extends RestIdModel<M, E>, E extends RestIdEntity<E, M>> extends RestIdModel<M, E> {
    /**
     * <code>userId</code>
     * {@link java.lang.String} <p>The <code>userId</code> field.</p>
     * @see java.lang.String
     */
    protected String userId;
    /**
     * <code>targetIds</code>
     * {@link java.util.Set} <p>The <code>targetIds</code> field.</p>
     * @see java.util.Set
     */
    protected Set<String> targetIds;
    /**
     * <code>username</code>
     * {@link java.lang.String} <p>The <code>username</code> field.</p>
     * @see java.lang.String
     */
    protected String username;
    /**
     * <code>userAgent</code>
     * {@link java.lang.String} <p>The <code>userAgent</code> field.</p>
     * @see java.lang.String
     */
    protected String userAgent;
    /**
     * <code>ipAddress</code>
     * {@link java.lang.String} <p>The <code>ipAddress</code> field.</p>
     * @see java.lang.String
     */
    protected String ipAddress;
    /**
     * <code>requestMethod</code>
     * {@link java.lang.String} <p>The <code>requestMethod</code> field.</p>
     * @see java.lang.String
     */
    protected String requestMethod;
    /**
     * <code>requestParams</code>
     * {@link java.lang.String} <p>The <code>requestParams</code> field.</p>
     * @see java.lang.String
     */
    protected String requestParams;
    /**
     * <code>requestUrl</code>
     * {@link java.lang.String} <p>The <code>requestUrl</code> field.</p>
     * @see java.lang.String
     */
    protected String requestUrl;
    /**
     * <code>methodName</code>
     * {@link java.lang.String} <p>The <code>methodName</code> field.</p>
     * @see java.lang.String
     */
    protected String methodName;
    /**
     * <code>mediaType</code>
     * {@link java.lang.String} <p>The <code>mediaType</code> field.</p>
     * @see java.lang.String
     */
    protected String mediaType;
    /**
     * <code>responseTime</code>
     * {@link java.lang.Long} <p>The <code>responseTime</code> field.</p>
     * @see java.lang.Long
     */
    protected Long responseTime;
    /**
     * <code>responseStatus</code>
     * {@link java.lang.Integer} <p>The <code>responseStatus</code> field.</p>
     * @see java.lang.Integer
     */
    protected Integer responseStatus;
    /**
     * <code>responseMessage</code>
     * {@link java.lang.String} <p>The <code>responseMessage</code> field.</p>
     * @see java.lang.String
     */
    protected String responseMessage;
    /**
     * <code>notelog</code>
     * {@link java.lang.String} <p>The <code>notelog</code> field.</p>
     * @see java.lang.String
     */
    protected String notelog;
    /**
     * <code>userlog</code>
     * {@link java.lang.String} <p>The <code>userlog</code> field.</p>
     * @see java.lang.String
     */
    protected String userlog;
    /**
     * <code>loggingKey</code>
     * {@link java.lang.String} <p>The <code>loggingKey</code> field.</p>
     * @see java.lang.String
     */
    protected String loggingKey;
    /**
     * <code>loggingValue</code>
     * {@link java.lang.String} <p>The <code>loggingValue</code> field.</p>
     * @see java.lang.String
     */
    protected String loggingValue;
    /**
     * <code>loggingType</code>
     * {@link io.github.nichetoolkit.rest.userlog.LoggingType} <p>The <code>loggingType</code> field.</p>
     * @see io.github.nichetoolkit.rest.userlog.LoggingType
     */
    protected LoggingType loggingType;
    /**
     * <code>loggingTime</code>
     * {@link java.util.Date} <p>The <code>loggingTime</code> field.</p>
     * @see java.util.Date
     * @see org.springframework.format.annotation.DateTimeFormat
     * @see com.fasterxml.jackson.annotation.JsonFormat
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date loggingTime;

    /**
     * <code>RestUsernoteModel</code>
     * <p>Instantiates a new rest usernote model.</p>
     */
    public RestUsernoteModel() {
    }

    /**
     * <code>RestUsernoteModel</code>
     * <p>Instantiates a new rest usernote model.</p>
     * @param id {@link java.lang.String} <p>The id parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public RestUsernoteModel(String id) {
        super(id);
    }

    /**
     * <code>getTargetIds</code>
     * <p>The get target ids getter method.</p>
     * @return {@link java.util.List} <p>The get target ids return object is <code>List</code> type.</p>
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
     * <p>The set target ids setter method.</p>
     * @param targetIds {@link java.lang.String} <p>The target ids parameter is <code>String</code> type.</p>
     * @see java.lang.String
     * @see org.jspecify.annotations.NonNull
     */
    public void setTargetIds(@NonNull String... targetIds) {
        this.targetIds = new HashSet<>(Arrays.asList(targetIds));
    }

    /**
     * <code>setTargetIds</code>
     * <p>The set target ids setter method.</p>
     * @param targetIds {@link java.util.Collection} <p>The target ids parameter is <code>Collection</code> type.</p>
     * @see java.util.Collection
     * @see org.jspecify.annotations.NonNull
     * @see com.fasterxml.jackson.annotation.JsonSetter
     */
    @JsonSetter
    public void setTargetIds(@NonNull Collection<String> targetIds) {
        this.targetIds = new HashSet<>(targetIds);
    }

    /**
     * <code>addTargetIds</code>
     * <p>The add target ids method.</p>
     * @param targetIds {@link java.lang.String} <p>The target ids parameter is <code>String</code> type.</p>
     * @see java.lang.String
     * @see org.jspecify.annotations.NonNull
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
     * <p>The add target ids method.</p>
     * @param targetIds {@link java.util.Collection} <p>The target ids parameter is <code>Collection</code> type.</p>
     * @see java.util.Collection
     * @see org.jspecify.annotations.NonNull
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
     * <p>The to data id method.</p>
     * @param responseData {@link java.lang.String} <p>The response data parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>The to data id return object is <code>String</code> type.</p>
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
     * <p>The to data ids method.</p>
     * @param responseData {@link java.lang.String} <p>The response data parameter is <code>String</code> type.</p>
     * @return {@link java.util.Set} <p>The to data ids return object is <code>Set</code> type.</p>
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
     * <p>The to data user info method.</p>
     * @param responseData {@link java.lang.String} <p>The response data parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.pack.UserInfoPack} <p>The to data user info return object is <code>UserInfoPack</code> type.</p>
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
     * <p>The to data user login method.</p>
     * @param responseData {@link java.lang.String} <p>The response data parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.pack.UserInfoPack} <p>The to data user login return object is <code>UserInfoPack</code> type.</p>
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
     * <p>The to url id method.</p>
     * @param requestUrl {@link java.lang.String} <p>The request url parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>The to url id return object is <code>String</code> type.</p>
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
     * <p>The to param id method.</p>
     * @param requestParams {@link java.lang.String} <p>The request params parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>The to param id return object is <code>String</code> type.</p>
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
     * <p>The to param ids method.</p>
     * @param requestParams {@link java.lang.String} <p>The request params parameter is <code>String</code> type.</p>
     * @return {@link java.util.Set} <p>The to param ids return object is <code>Set</code> type.</p>
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
     * <p>The to list ids method.</p>
     * @param requestBody {@link java.lang.String} <p>The request body parameter is <code>String</code> type.</p>
     * @return {@link java.util.Set} <p>The to list ids return object is <code>Set</code> type.</p>
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
     * <p>The to filter ids method.</p>
     * @param requestBody {@link java.lang.String} <p>The request body parameter is <code>String</code> type.</p>
     * @return {@link java.util.Set} <p>The to filter ids return object is <code>Set</code> type.</p>
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
     * <code>usernote</code>
     * <p>The usernote method.</p>
     * @param usernote {@link io.github.nichetoolkit.rest.userlog.RestUsernotePack} <p>The usernote parameter is <code>RestUsernotePack</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.RestUsernoteModel} <p>The usernote return object is <code>RestUsernoteModel</code> type.</p>
     * @see io.github.nichetoolkit.rest.userlog.RestUsernotePack
     * @see org.jspecify.annotations.NonNull
     */
    public RestUsernoteModel<M, E> usernote(@NonNull RestUsernotePack usernote) {
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
     * <p>The request method.</p>
     * @param request {@link io.github.nichetoolkit.rest.userlog.RestRequestPack} <p>The request parameter is <code>RestRequestPack</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.RestUsernoteModel} <p>The request return object is <code>RestUsernoteModel</code> type.</p>
     * @see io.github.nichetoolkit.rest.userlog.RestRequestPack
     * @see org.jspecify.annotations.NonNull
     */
    public RestUsernoteModel<M, E> request(@NonNull RestRequestPack request) {
        this.ipAddress = request.getIpAddress();
        this.userAgent = request.getUserAgent();
        this.requestMethod = request.getMethod();
        this.requestParams = request.getParams();
        this.requestUrl = request.getUrl();
        return this;
    }

    /**
     * <code>response</code>
     * <p>The response method.</p>
     * @param response {@link io.github.nichetoolkit.rest.userlog.RestResponsePack} <p>The response parameter is <code>RestResponsePack</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.RestUsernoteModel} <p>The response return object is <code>RestUsernoteModel</code> type.</p>
     * @see io.github.nichetoolkit.rest.userlog.RestResponsePack
     * @see org.jspecify.annotations.NonNull
     */
    public RestUsernoteModel<M, E> response(@NonNull RestResponsePack response) {
        this.loggingTime = new Date(response.getTime());
        this.responseTime = response.getCostTime();
        this.responseStatus = response.getStatus();
        this.responseMessage = response.getMessage();
        this.methodName = response.getMethod();
        this.mediaType = response.getMediaType();
        return this;
    }

}
