package io.github.nichetoolkit.rice;

public interface RestUserInfo<I> extends RestInfo<I> {

    String getUsername();

    void setUsername(String username);

}
