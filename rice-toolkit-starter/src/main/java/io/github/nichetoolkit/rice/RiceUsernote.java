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

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(value= JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RiceUsernote<M extends RiceIdModel<M, E>, E extends RiceIdEntity<E, M>> extends RiceIdModel<M,E> {
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
    protected Integer logKey;
    protected String logValue;
    protected LogType logType;
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
