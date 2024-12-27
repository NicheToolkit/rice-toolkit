package io.github.nichetoolkit.rice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

/**
 * <code>RestLoginResult</code>
 * <p>The rest login result class.</p>
 * @param <R>  {@link io.github.nichetoolkit.rice.RestLoginResult} <p>The generic parameter is <code>RestLoginResult</code> type.</p>
 * @see  java.io.Serializable
 * @see  lombok.Setter
 * @see  lombok.Getter
 * @see  com.fasterxml.jackson.annotation.JsonInclude
 * @see  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Setter
@Getter
@JsonInclude(value= JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestLoginResult<R extends RestLoginResult<R>> implements Serializable {
    /**
     * <code>token</code>
     * {@link java.lang.String} <p>The <code>token</code> field.</p>
     * @see  java.lang.String
     */
    private String token;

    /**
     * <code>RestLoginResult</code>
     * <p>Instantiates a new rest login result.</p>
     */
    public RestLoginResult() {
    }

    /**
     * <code>RestLoginResult</code>
     * <p>Instantiates a new rest login result.</p>
     * @param token {@link java.lang.String} <p>The token parameter is <code>String</code> type.</p>
     * @see  java.lang.String
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
