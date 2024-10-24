package io.github.nichetoolkit.rice.pack;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <code>UserLoginPack</code>
 * <p>The user login pack class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.io.Serializable
 * @see lombok.Setter
 * @see lombok.Getter
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk1.8
 */
@Setter
@Getter
@JsonInclude(value= JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserLoginPack implements Serializable {
    /**
     * <code>user</code>
     * {@link io.github.nichetoolkit.rice.pack.UserInfoPack} <p>The <code>user</code> field.</p>
     * @see io.github.nichetoolkit.rice.pack.UserInfoPack
     */
    protected UserInfoPack user;
}
