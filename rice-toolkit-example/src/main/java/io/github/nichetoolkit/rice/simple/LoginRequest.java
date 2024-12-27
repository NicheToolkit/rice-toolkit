package io.github.nichetoolkit.rice.simple;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <code>LoginRequest</code>
 * <p>The login request class.</p>
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
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginRequest implements Serializable {
    /**
     * <code>token</code>
     * {@link java.lang.String} <p>The <code>token</code> field.</p>
     * @see  java.lang.String
     */
    private String token;
    /**
     * <code>account</code>
     * {@link java.lang.String} <p>The <code>account</code> field.</p>
     * @see  java.lang.String
     */
    private String account;
    /**
     * <code>password</code>
     * {@link java.lang.String} <p>The <code>password</code> field.</p>
     * @see  java.lang.String
     */
    private String password;
}
