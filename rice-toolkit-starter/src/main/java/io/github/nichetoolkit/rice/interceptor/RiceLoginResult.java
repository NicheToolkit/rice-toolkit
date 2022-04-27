package io.github.nichetoolkit.rice.interceptor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * <p>RiceLoginResult</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Data
@JsonInclude(value= JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RiceLoginResult<T extends RiceLoginResult<T>> {

    private String accessToken;

    public RiceLoginResult() {
    }

    public RiceLoginResult(String accessToken) {
        this.accessToken = accessToken;
    }
}
