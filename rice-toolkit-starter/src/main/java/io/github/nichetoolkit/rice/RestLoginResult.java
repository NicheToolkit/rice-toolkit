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

    private String token;

    /**
     * <code>RestLoginResult</code>
     * Instantiates a new rest login result.
     */
    public RestLoginResult() {
    }

    /**
     * <code>RestLoginResult</code>
     * Instantiates a new rest login result.
     * @param token {@link java.lang.String} <p>the token parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public RestLoginResult(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestLoginResult<?> that = (RestLoginResult<?>) o;
        return Objects.equals(token, that.token);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(token);
    }
}
