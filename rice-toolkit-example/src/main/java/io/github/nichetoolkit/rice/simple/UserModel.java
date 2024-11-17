package io.github.nichetoolkit.rice.simple;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.nichetoolkit.rest.util.BeanUtils;
import io.github.nichetoolkit.rice.RestInfoModel;
import io.github.nichetoolkit.rice.RestUserInfo;
import io.github.nichetoolkit.rice.purview.PurviewType;

/**
 * <code>UserModel</code>
 * <p>The user model class.</p>
 * @see  io.github.nichetoolkit.rice.RestInfoModel
 * @see  io.github.nichetoolkit.rice.RestUserInfo
 * @see  com.fasterxml.jackson.annotation.JsonInclude
 * @see  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserModel extends RestInfoModel<UserModel, UserEntity> implements RestUserInfo<String> {
    /**
     * <code>LOGIN_TOKEN</code>
     * {@link java.lang.String} <p>The constant <code>LOGIN_TOKEN</code> field.</p>
     * @see  java.lang.String
     */
    public static final String LOGIN_TOKEN = "LOGIN_TOKEN";

    /**
     * <code>LOGIN_USER_ID</code>
     * {@link java.lang.String} <p>The constant <code>LOGIN_USER_ID</code> field.</p>
     * @see  java.lang.String
     */
    public static final String LOGIN_USER_ID = "USER_ID_";

    /**
     * <code>LOGIN_USER_INFO</code>
     * {@link java.lang.String} <p>The constant <code>LOGIN_USER_INFO</code> field.</p>
     * @see  java.lang.String
     */
    public static final String LOGIN_USER_INFO = "LOGIN_USER_INFO";
    /**
     * <code>username</code>
     * {@link java.lang.String} <p>The <code>username</code> field.</p>
     * @see  java.lang.String
     */
    private String username;
    /**
     * <code>password</code>
     * {@link java.lang.String} <p>The <code>password</code> field.</p>
     * @see  java.lang.String
     * @see  com.fasterxml.jackson.annotation.JsonProperty
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    /**
     * <code>purviewType</code>
     * {@link io.github.nichetoolkit.rice.purview.PurviewType} <p>The <code>purviewType</code> field.</p>
     * @see  io.github.nichetoolkit.rice.purview.PurviewType
     */
    private PurviewType purviewType;

    /**
     * <code>UserModel</code>
     * <p>Instantiates a new user model.</p>
     */
    public UserModel() {
    }

    /**
     * <code>UserModel</code>
     * <p>Instantiates a new user model.</p>
     * @param id {@link java.lang.String} <p>The id parameter is <code>String</code> type.</p>
     * @see  java.lang.String
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
     * <p>The get password getter method.</p>
     * @return  {@link java.lang.String} <p>The get password return object is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    public String getPassword() {
        return password;
    }

    /**
     * <code>setPassword</code>
     * <p>The set password setter method.</p>
     * @param password {@link java.lang.String} <p>The password parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * <code>getPurviewType</code>
     * <p>The get purview type getter method.</p>
     * @return  {@link io.github.nichetoolkit.rice.purview.PurviewType} <p>The get purview type return object is <code>PurviewType</code> type.</p>
     * @see  io.github.nichetoolkit.rice.purview.PurviewType
     */
    public PurviewType getPurviewType() {
        return purviewType;
    }

    /**
     * <code>setPurviewType</code>
     * <p>The set purview type setter method.</p>
     * @param purviewType {@link io.github.nichetoolkit.rice.purview.PurviewType} <p>The purview type parameter is <code>PurviewType</code> type.</p>
     * @see  io.github.nichetoolkit.rice.purview.PurviewType
     */
    public void setPurviewType(PurviewType purviewType) {
        this.purviewType = purviewType;
    }

    @Override
    public UserEntity toEntity() {
        UserEntity entity = new UserEntity();
        BeanUtils.copyNonnullProperties(this, entity);
        return entity;
    }
}
