package io.github.nichetoolkit.rice.mapper;

import io.github.nichetoolkit.rice.RiceInfoMapper;
import io.github.nichetoolkit.rice.simple.SimpleEntity;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

@Component
public interface SimpleMapper extends RiceInfoMapper<SimpleEntity>, Mapper<SimpleEntity> {
}
