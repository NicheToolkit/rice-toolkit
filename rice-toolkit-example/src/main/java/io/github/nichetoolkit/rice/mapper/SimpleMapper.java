package io.github.nichetoolkit.rice.mapper;

import io.github.nichetoolkit.rice.RestInfoMapper;
import io.github.nichetoolkit.rice.simple.SimpleEntity;
import io.mybatis.mapper.Mapper;
import org.springframework.stereotype.Component;

/**
 * <code>SimpleMapper</code>
 * <p>The type simple mapper interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestInfoMapper
 * @see io.mybatis.mapper.Mapper
 * @see org.springframework.stereotype.Component
 * @since Jdk1.8
 */
@Component
public interface SimpleMapper extends RestInfoMapper<SimpleEntity>, Mapper<SimpleEntity, String> {
}
