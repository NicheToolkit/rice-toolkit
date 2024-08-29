package io.github.nichetoolkit.rice.interceptor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(value= JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RiceLoginResult<T extends RiceLoginResult<T>> implements Serializable {

    private String accessToken;

    public RiceLoginResult() {
    }

    public RiceLoginResult(String accessToken) {
        this.accessToken = accessToken;
    }
}
