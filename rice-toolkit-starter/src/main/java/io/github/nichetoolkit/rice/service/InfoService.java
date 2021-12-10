package io.github.nichetoolkit.rice.service;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.helper.OptionalHelper;
import io.github.nichetoolkit.rest.util.common.GeneralUtils;
import io.github.nichetoolkit.rice.InfoEntity;
import io.github.nichetoolkit.rice.InfoModel;
import io.github.nichetoolkit.rice.filter.IdFilter;
import io.github.nichetoolkit.rice.mapper.InfoMapper;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * <p>InfoNonBuildServer</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@SuppressWarnings("RedundantThrows")
public abstract class InfoService<I,M extends InfoModel<I>, E extends InfoEntity<I>, F extends IdFilter<I>> extends SupperService<I,M,E,F> {

    protected InfoMapper<E,I> consumerMapper;

    @SuppressWarnings(value = "unchecked")
    @Override
    public void doServiceHandle() {
        this.createActuator = (@NonNull M model) -> {
            if (nameProperties.getNonnullEnabled()) {
                OptionalHelper.fieldable(model.getName(), "name is null！");
            }
            if (nameProperties.getUniqueEnabled()) {
                Boolean existByName = existByName(model.getName());
                OptionalHelper.nameRepeat(existByName, model.getName());
            }
        };
        this.updateActuator = (@NonNull M model) -> {
            if (nameProperties.getUniqueEnabled()) {
                Boolean existByName = existByNameAndNotId(model.getName(),model.getId());
                OptionalHelper.nameRepeat(existByName,model.getName());
            }
        };
        if (super.supperMapper instanceof InfoMapper) {
            this.consumerMapper = (InfoMapper) super.supperMapper;
        }
    }

    protected Boolean existByName(String name) throws RestException {
        if (GeneralUtils.isEmpty(name)) {
            return false;
        }
        List<E> entityList = consumerMapper.findByName(name);
        return GeneralUtils.isNotEmpty(entityList);
    }


    protected Boolean existByNameAndNotId(String name, I id) throws RestException {
        if (GeneralUtils.isEmpty(name)) {
            return false;
        }
        if (GeneralUtils.isEmpty(id)) {
            return existByName(name);
        }
        List<E> entityList = consumerMapper.findByNameAndNotId(name, id);
        return GeneralUtils.isNotEmpty(entityList);
    }
}
