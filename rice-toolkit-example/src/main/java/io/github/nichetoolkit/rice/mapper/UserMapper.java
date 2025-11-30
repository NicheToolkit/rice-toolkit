package io.github.nichetoolkit.rice.mapper;

import com.baomidou.mybatisplus.core.mapper.Mapper;
import io.github.nichetoolkit.rice.RestInfoMapper;
import io.github.nichetoolkit.rice.simple.UserEntity;
import org.springframework.stereotype.Component;

/**
 * <code>UserMapper</code>
 * <p>The user mapper interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestInfoMapper
 * @see com.baomidou.mybatisplus.core.mapper.Mapper
 * @see org.springframework.stereotype.Component
 * @since Jdk17
 */
@Component
public interface UserMapper extends RestInfoMapper<UserEntity>, Mapper<UserEntity> {
}
