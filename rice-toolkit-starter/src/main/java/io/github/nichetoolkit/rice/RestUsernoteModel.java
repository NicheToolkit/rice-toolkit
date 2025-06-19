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
import org.springframework.lang.NonNull;

import java.util.*;
import java.util.stream.Collectors;

@Setter
@Getter
@SuperBuilder
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
    protected String loggingKey;
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
        if (GeneralUtils.isNotEmpty(idPacks)) {
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
            if (GeneralUtils.isNotEmpty(splitUrl) && GeneralUtils.isNotEmpty(splitUrl[splitUrl.length - 1])) {
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

    public RestUsernoteModel<M, E> request(@NonNull RestRequestPack request) {
        this.ipAddress = request.getIpAddress();
        this.userAgent = request.getUserAgent();
        this.requestMethod = request.getMethod();
        this.requestParams = request.getParams();
        this.requestUrl = request.getUrl();
        return this;
    }

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
