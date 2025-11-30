package io.github.nichetoolkit.rice.mapper;

import com.baomidou.mybatisplus.core.mapper.Mapper;
import io.github.nichetoolkit.rice.RestInfoMapper;
import io.github.nichetoolkit.rice.simple.SimpleEntity;
import org.springframework.stereotype.Component;

/**
 * <code>SimpleMapper</code>
 * <p>The simple mapper interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestInfoMapper
 * @see com.baomidou.mybatisplus.core.mapper.Mapper
 * @see org.springframework.stereotype.Component
 * @since Jdk17
 */
@Component
public interface SimpleMapper extends RestInfoMapper<SimpleEntity>, Mapper<SimpleEntity> {
}
