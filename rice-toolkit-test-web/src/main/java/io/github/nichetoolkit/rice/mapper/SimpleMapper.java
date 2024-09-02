package io.github.nichetoolkit.rice.mapper;

import io.github.nichetoolkit.rice.RestInfoMapper;
import io.github.nichetoolkit.rice.simple.SimpleEntity;
import io.mybatis.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public interface SimpleMapper extends RestInfoMapper<SimpleEntity>, Mapper<SimpleEntity,String> {
}
