package io.github.nichetoolkit.rice.service;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.RestInfoService;
import io.github.nichetoolkit.rice.simple.UserEntity;
import io.github.nichetoolkit.rice.simple.UserFilter;
import io.github.nichetoolkit.rice.simple.UserModel;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

/**
 * <code>UserServiceImpl</code>
 * <p>The type user service class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestInfoService
 * @see org.springframework.stereotype.Service
 * @since Jdk1.8
 */
@Service
public class UserServiceImpl extends RestInfoService<UserModel, UserEntity, UserFilter> implements UserService {

    @Override
    public String queryWhereSql(UserFilter filter) throws RestException {
        return filter.toTimeSql("create_time").toNameSql("name").toQuerySql(this, "logic_sign").toIdSql().addSorts("id").toSql();
    }
}
