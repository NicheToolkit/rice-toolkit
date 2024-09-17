package io.github.nichetoolkit.rice.simple;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * <code>LoginRequest</code>
 * <p>The type login request class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.io.Serializable
 * @see lombok.Data
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk1.8
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginRequest implements Serializable {
    /**
     * <code>token</code>
     * {@link java.lang.String} <p>the <code>token</code> field.</p>
     * @see java.lang.String
     */
    private String token;
    /**
     * <code>account</code>
     * {@link java.lang.String} <p>the <code>account</code> field.</p>
     * @see java.lang.String
     */
    private String account;
    /**
     * <code>password</code>
     * {@link java.lang.String} <p>the <code>password</code> field.</p>
     * @see java.lang.String
     */
    private String password;

    /**
     * <code>LoginRequest</code>
     * Instantiates a new login request.
     */
    public LoginRequest() {
    }
}
