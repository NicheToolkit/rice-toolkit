package io.github.nichetoolkit.rice.simple;

import io.github.nichetoolkit.rest.util.BeanUtils;
import io.github.nichetoolkit.rice.RestInfoEntity;
import io.github.nichetoolkit.rice.RestUserInfo;
import io.mybatis.provider.Entity;

/**
 * <code>UserEntity</code>
 * <p>The type user entity class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestInfoEntity
 * @see io.mybatis.provider.Entity.Table
 * @since Jdk1.8
 */
@Entity.Table(value = "ntr_user")
public class UserEntity extends RestInfoEntity<UserEntity, UserModel>  {
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
     * <code>UserEntity</code>
     * Instantiates a new user entity.
     */
    public UserEntity() {
    }

    /**
     * <code>UserEntity</code>
     * Instantiates a new user entity.
     * @param id {@link java.lang.String} <p>the id parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public UserEntity(String id) {
        super(id);
    }

    /**
     * <code>getUsername</code>
     * <p>the username getter method.</p>
     * @return {@link java.lang.String} <p>the username return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * <code>setUsername</code>
     * <p>the username setter method.</p>
     * @param username {@link java.lang.String} <p>the username parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
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
    public UserModel toModel() {
        UserModel model = new UserModel();
        BeanUtils.copyNonnullProperties(this, model);
        return model;
    }
}
