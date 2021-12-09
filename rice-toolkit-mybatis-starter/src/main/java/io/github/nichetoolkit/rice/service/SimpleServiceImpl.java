package io.github.nichetoolkit.rice.service;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.RiceInfoService;
import io.github.nichetoolkit.rice.SimpleEntity;
import io.github.nichetoolkit.rice.SimpleFilter;
import io.github.nichetoolkit.rice.SimpleModel;
import org.springframework.stereotype.Service;

/**
 * <p>SimpleServiceImpl</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Service
//@RestService(mapper = SimpleMapper.class)
public class SimpleServiceImpl extends RiceInfoService<SimpleModel, SimpleEntity, SimpleFilter> implements SimpleService {

    @Override
    public String queryWhereSql(SimpleFilter filter) throws RestException {
        return filter.toTimeSql("time").addSorts("id").toSql();
    }
}
