package io.github.nichetoolkit.rice.mapper;

import io.github.nichetoolkit.rice.RestInfoMapper;
import io.github.nichetoolkit.rice.simple.UserEntity;
import io.mybatis.mapper.Mapper;
import org.springframework.stereotype.Component;

/**
 * <code>UserMapper</code>
 * <p>The type user mapper interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestInfoMapper
 * @see io.mybatis.mapper.Mapper
 * @see org.springframework.stereotype.Component
 * @since Jdk1.8
 */
@Component
public interface UserMapper extends RestInfoMapper<UserEntity>, Mapper<UserEntity, String> {
}
