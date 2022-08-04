package io.github.nichetoolkit.rice;

/**
 * <p>RestUserInfo</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public interface RestUserInfo<I> extends RestInfo<I> {

    String getUsername();

    void setUsername(String username);

}
