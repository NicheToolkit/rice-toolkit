package io.github.nichetoolkit.rice.simple;

import io.github.nichetoolkit.rice.RestLoginResult;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * <code>LoginResult</code>
 * <p>The login result class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestLoginResult
 * @see lombok.Setter
 * @see lombok.Getter
 * @see lombok.experimental.SuperBuilder
 * @since Jdk1.8
 */
@Setter
@Getter
@SuperBuilder
public class LoginResult extends RestLoginResult<LoginResult> {
    /**
     * <code>userId</code>
     * {@link java.lang.String} <p>The <code>userId</code> field.</p>
     * @see java.lang.String
     */
    private String userId;
    /**
     * <code>user</code>
     * {@link io.github.nichetoolkit.rice.simple.UserModel} <p>The <code>user</code> field.</p>
     * @see io.github.nichetoolkit.rice.simple.UserModel
     */
    private UserModel user;

    /**
     * <code>LoginResult</code>
     * <p>Instantiates a new login result.</p>
     */
    public LoginResult() {
    }

    /**
     * <code>LoginResult</code>
     * <p>Instantiates a new login result.</p>
     * @param accessToken {@link java.lang.String} <p>The access token parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public LoginResult(String accessToken) {
        super(accessToken);
    }

}
