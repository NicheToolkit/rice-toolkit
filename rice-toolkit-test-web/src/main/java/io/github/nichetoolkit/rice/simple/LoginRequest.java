package io.github.nichetoolkit.rice.simple;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>LoginRequest</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginRequest implements Serializable {
    /** 访问令牌 */
    private String token;
    /** 用户账户 */
    private String account;
    /** 用户密码 */
    private String password;

    public LoginRequest() {
    }
}
