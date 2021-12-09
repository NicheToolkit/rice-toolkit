package io.github.nichetoolkit.rice.mapper;

import io.github.nichetoolkit.rice.RiceInfoMapper;
import io.github.nichetoolkit.rice.SimpleEntity;
import tk.mybatis.mapper.common.Mapper;

import javax.annotation.Resource;

/**
 * <p>SimpleMapper</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Resource
public interface SimpleMapper extends RiceInfoMapper<SimpleEntity>, Mapper<SimpleEntity> {
}
