package io.github.nichetoolkit.rice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * <code>RestLoginResult</code>
 * <p>The rest login result class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.io.Serializable
 * @see lombok.Setter
 * @see lombok.Getter
 * @see lombok.experimental.SuperBuilder
 * @see lombok.NoArgsConstructor
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk17
 */
@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestLoginResult implements Serializable {
    /**
     * <code>accessToken</code>
     * {@link java.lang.String} <p>The <code>accessToken</code> field.</p>
     * @see java.lang.String
     */
    private String accessToken;

    /**
     * <code>refreshToken</code>
     * {@link java.lang.String} <p>The <code>refreshToken</code> field.</p>
     * @see java.lang.String
     */
    private String refreshToken;
}
