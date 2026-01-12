package io.github.nichetoolkit.rice.simple;

import io.github.nichetoolkit.rice.RestLoginResult;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Objects;

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
@NoArgsConstructor
public class LoginResult extends RestLoginResult {
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


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LoginResult that = (LoginResult) o;
        return Objects.equals(getUserId(), that.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getUserId());
    }
}
