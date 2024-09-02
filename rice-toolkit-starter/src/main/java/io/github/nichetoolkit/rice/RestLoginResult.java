package io.github.nichetoolkit.rice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * <code>RestLoginResult</code>
 * <p>The type rest login result class.</p>
 * @param <R> {@link io.github.nichetoolkit.rice.RestLoginResult} <p>the generic parameter is <code>RestLoginResult</code> type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.io.Serializable
 * @see lombok.Data
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk1.8
 */
@Data
@JsonInclude(value= JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestLoginResult<R extends RestLoginResult<R>> implements Serializable {

    private String accessToken;

    /**
     * <code>RestLoginResult</code>
     * Instantiates a new rest login result.
     */
    public RestLoginResult() {
    }

    /**
     * <code>RestLoginResult</code>
     * Instantiates a new rest login result.
     * @param accessToken {@link java.lang.String} <p>the access token parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public RestLoginResult(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestLoginResult<?> that = (RestLoginResult<?>) o;
        return Objects.equals(accessToken, that.accessToken);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(accessToken);
    }
}
