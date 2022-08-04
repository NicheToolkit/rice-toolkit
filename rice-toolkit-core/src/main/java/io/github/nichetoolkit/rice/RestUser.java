package io.github.nichetoolkit.rice;

/**
 * <p>RestUser</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public interface RestUser<I> extends RestId<I> {

    String getUsername();

    void setUsername(String username);

}
