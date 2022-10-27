package io.github.nichetoolkit.rice;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.github.nichetoolkit.rest.userlog.LogType;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

import java.util.*;

/**
 * <p>RiceUserlog</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(value= JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RiceUsernote<M extends RiceIdModel<M, E>, E extends RiceIdEntity<E, M>> extends RiceIdModel<M,E> {
    /** 用户id */
    protected String userId;
    /** 操作数据id集合 */
    protected Set<String> targetIds;
    /** 用户名 */
    protected String username;
    /** 用户代理 */
    protected String userAgent;
    /** 用户ip */
    protected String ipAddress;
    /** 请求方式 */
    protected String requestMethod;
    /** 请求参数 */
    protected String requestParams;
    /** 请求地址 */
    protected String requestUrl;
    /** 方法名称 */
    protected String methodName;
    /** 请求媒介类型 */
    protected String mediaType;
    /** 响应耗费时间 */
    protected Long responseTime;
    /** 响应状态码 */
    protected Integer responseStatus;
    /** 响应消息 */
    protected String responseMessage;
    /** 日志标题 */
    protected String notelog;
    /** 日志信息 */
    protected String userlog;
    /** 操作key */
    protected Integer logKey;
    /** 操作值 */
    protected String logValue;
    /** 日志类型 */
    protected LogType logType;
    /** 记录时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date logTime;

    public RiceUsernote() {
    }

    public RiceUsernote(String id) {
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
}
