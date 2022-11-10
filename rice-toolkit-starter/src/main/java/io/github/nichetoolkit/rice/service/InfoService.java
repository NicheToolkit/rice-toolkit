package io.github.nichetoolkit.rice.service;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.helper.OptionalHelper;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.util.JsonUtils;
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
public abstract class InfoService<I,M extends InfoModel<I>, E extends InfoEntity<I>, F extends IdFilter<I>> extends SuperService<I,M,E,F> {

    protected InfoMapper<E,I> consumerMapper;

    @Override
    protected void optionalInit(@NonNull M model) throws RestException {
        super.optionalInit(model);
        if (isNameNonnull()) {
            OptionalHelper.fieldable(model.getName(), "名称不能为空！");
        }
    }

    protected void fieldRepeat(Boolean existByModel, M model) throws RestException {
        OptionalHelper.fieldRepeat(existByModel, JsonUtils.parseJson(model));
    }

    @SuppressWarnings(value = "unchecked")
    @Override
    public void doServiceHandle() {
        this.createActuator = (@NonNull M model) -> {
            if (isModelUnique()) {
                Boolean existByModel = existByModel(model);
                fieldRepeat(existByModel,model);
            } else if (isNameUnique()) {
                Boolean existByName = existByName(model.getName());
                OptionalHelper.nameRepeat(existByName, model.getName());
            }

        };
        this.updateActuator = (@NonNull M model) -> {
            if (isModelUnique()) {
                Boolean existByModel = existByModelAndNotId(model,model.getId());
                fieldRepeat(existByModel,model);
            } else if (isNameUnique()) {
                Boolean existByName = existByNameAndNotId(model.getName(),model.getId());
                OptionalHelper.nameRepeat(existByName,model.getName());
            }
        };
        if (super.superMapper instanceof InfoMapper) {
            this.consumerMapper = (InfoMapper) super.superMapper;
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

    protected Boolean existByModel(M model) throws RestException {
        if (GeneralUtils.isEmpty(model)) {
            return false;
        }
        E entity = this.createEntity(model);
        List<E> entityList = consumerMapper.findByEntity(entity);
        return GeneralUtils.isNotEmpty(entityList);
    }

    protected Boolean existByModelAndNotId(M model, I id) throws RestException {
        if (GeneralUtils.isEmpty(model)) {
            return false;
        }
        if (GeneralUtils.isEmpty(id)) {
            return existByModel(model);
        }
        E entity = this.createEntity(model);
        List<E> entityList = consumerMapper.findByEntityAndNotId(entity, id);
        return GeneralUtils.isNotEmpty(entityList);
    }
}
