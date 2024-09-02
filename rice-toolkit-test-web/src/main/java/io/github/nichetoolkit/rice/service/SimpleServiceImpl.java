package io.github.nichetoolkit.rice.service;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.RestInfoService;
import io.github.nichetoolkit.rice.simple.SimpleEntity;
import io.github.nichetoolkit.rice.simple.SimpleFilter;
import io.github.nichetoolkit.rice.simple.SimpleModel;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class SimpleServiceImpl extends RestInfoService<SimpleModel, SimpleEntity, SimpleFilter> implements SimpleService {

    @Override
    protected void optionalInit(@NonNull SimpleModel model) throws RestException {
        model.setTime(Optional.ofNullable(model.getTime()).orElse(new Date()));
    }

    @Override
    protected String dynamicTablename(@NonNull String tablekey) throws RestException {
        return "ntr_simple".concat(tablekey);
    }

    @Override
    public String queryWhereSql(SimpleFilter filter) throws RestException {
        return filter.toTimeSql("time").addSorts("id").toSql();
    }
}
