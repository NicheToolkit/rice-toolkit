package io.github.nichetoolkit.rice.simple;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.nichetoolkit.rest.util.BeanUtils;
import io.github.nichetoolkit.rice.RestInfoModel;
import io.github.nichetoolkit.rice.RestUserInfo;
import io.github.nichetoolkit.rice.purview.PurviewType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * <code>UserModel</code>
 * <p>The user model class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestInfoModel
 * @see io.github.nichetoolkit.rice.RestUserInfo
 * @see lombok.experimental.SuperBuilder
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk17
 */
@SuperBuilder
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserModel extends RestInfoModel<UserModel, UserEntity> implements RestUserInfo<String> {
    /**
     * <code>LOGIN_TOKEN</code>
     * {@link java.lang.String} <p>The constant <code>LOGIN_TOKEN</code> field.</p>
     * @see java.lang.String
     */
    public static final String LOGIN_TOKEN = "LOGIN_TOKEN";

    /**
     * <code>LOGIN_USER_ID</code>
     * {@link java.lang.String} <p>The constant <code>LOGIN_USER_ID</code> field.</p>
     * @see java.lang.String
     */
    public static final String LOGIN_USER_ID = "USER_ID_";

    /**
     * <code>LOGIN_USER_INFO</code>
     * {@link java.lang.String} <p>The constant <code>LOGIN_USER_INFO</code> field.</p>
     * @see java.lang.String
     */
    public static final String LOGIN_USER_INFO = "LOGIN_USER_INFO";
    /**
     * <code>username</code>
     * {@link java.lang.String} <p>The <code>username</code> field.</p>
     * @see java.lang.String
     */
    private String username;
    /**
     * <code>password</code>
     * {@link java.lang.String} <p>The <code>password</code> field.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.annotation.JsonProperty
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    /**
     * <code>purviewType</code>
     * {@link io.github.nichetoolkit.rice.purview.PurviewType} <p>The <code>purviewType</code> field.</p>
     * @see io.github.nichetoolkit.rice.purview.PurviewType
     * @see lombok.Getter
     * @see lombok.Setter
     */
    @Getter
    @Setter
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
     * <code>password</code>
     * <p>The password method.</p>
     * @return {@link java.lang.String} <p>The password return object is <code>String</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    public String password() {
        return password;
    }

    @Override
    public UserEntity toEntity() {
        UserEntity entity = new UserEntity();
        BeanUtils.copyNonnullProperties(this, entity);
        return entity;
    }
}
