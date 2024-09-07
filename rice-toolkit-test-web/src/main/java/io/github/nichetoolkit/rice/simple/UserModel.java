package io.github.nichetoolkit.rice.simple;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.rest.util.BeanUtils;
import io.github.nichetoolkit.rice.RestInfoModel;
import io.github.nichetoolkit.rice.RestUserInfo;

/**
 * <code>UserModel</code>
 * <p>The type user model class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestInfoModel
 * @see io.github.nichetoolkit.rice.RestUserInfo
 * @since Jdk1.8
 */
@JsonInclude
public class UserModel extends RestInfoModel<UserModel, UserEntity> implements RestUserInfo<String> {
    public static final String LOGIN_TOKEN =  "LOGIN_TOKEN";

    public static final String LOGIN_USER_ID = "USER_ID_";

    public static final String LOGIN_USER_INFO = "LOGIN_USER_INFO";
    /**
     * <code>username</code>
     * {@link java.lang.String} <p>the <code>username</code> field.</p>
     * @see java.lang.String
     */
    private String username;
    /**
     * <code>password</code>
     * {@link java.lang.String} <p>the <code>password</code> field.</p>
     * @see java.lang.String
     */
    private String password;

    /**
     * <code>UserModel</code>
     * Instantiates a new user model.
     */
    public UserModel() {
    }

    /**
     * <code>UserModel</code>
     * Instantiates a new user model.
     * @param id {@link java.lang.String} <p>the id parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public UserModel(String id) {
        super(id);
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * <code>getPassword</code>
     * <p>the password getter method.</p>
     * @return {@link java.lang.String} <p>the password return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    /* hide password */
    public String getPassword() {
        return null;
    }

    /**
     * <code>password</code>
     * <p>the method.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public String password() {
        return this.password;
    }

    /**
     * <code>setPassword</code>
     * <p>the password setter method.</p>
     * @param password {@link java.lang.String} <p>the password parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public UserEntity toEntity() {
        UserEntity entity = new UserEntity();
        BeanUtils.copyNonullProperties(this, entity);
        return entity;
    }
}
