package io.github.nichetoolkit.rice.simple;

import io.github.nichetoolkit.rest.util.BeanUtils;
import io.github.nichetoolkit.rice.RestInfoEntity;
import io.github.nichetoolkit.rice.RestUserInfo;
import io.mybatis.provider.Entity;

@Entity.Table(value = "ntr_user")
public class UserEntity extends RestInfoEntity<UserEntity, UserModel>  {
    private String username;
    private String password;

    public UserEntity() {
    }

    public UserEntity(String id) {
        super(id);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /* hide password */
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public UserModel toModel() {
        UserModel model = new UserModel();
        BeanUtils.copyNonullProperties(this, model);
        return model;
    }
}
