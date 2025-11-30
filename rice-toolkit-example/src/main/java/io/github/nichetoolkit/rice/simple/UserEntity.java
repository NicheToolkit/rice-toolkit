package io.github.nichetoolkit.rice.simple;

import io.github.nichetoolkit.rest.util.BeanUtils;
import io.github.nichetoolkit.rice.RestInfoEntity;
import io.mybatis.provider.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * <code>UserEntity</code>
 * <p>The user entity class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestInfoEntity
 * @see lombok.Setter
 * @see lombok.Getter
 * @see lombok.experimental.SuperBuilder
 * @see io.mybatis.provider.Entity.Table
 * @since Jdk17
 */
@Setter
@Getter
@SuperBuilder
@Entity.Table(value = "ntr_user")
public class UserEntity extends RestInfoEntity<UserEntity, UserModel>  {
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
     */
    /* hide password */
    private String password;

    /**
     * <code>UserEntity</code>
     * <p>Instantiates a new user entity.</p>
     */
    public UserEntity() {
    }

    /**
     * <code>UserEntity</code>
     * <p>Instantiates a new user entity.</p>
     * @param id {@link java.lang.String} <p>The id parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public UserEntity(String id) {
        super(id);
    }

    @Override
    public UserModel toModel() {
        UserModel model = new UserModel();
        BeanUtils.copyNonnullProperties(this, model);
        return model;
    }
}
