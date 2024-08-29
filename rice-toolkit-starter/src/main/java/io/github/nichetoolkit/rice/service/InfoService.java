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

@SuppressWarnings("RedundantThrows")
public abstract class InfoService<K, I, M extends InfoModel<I>, E extends InfoEntity<I>, F extends IdFilter<I, K>> extends SuperService<K, I, M, E, F> {

    protected InfoMapper<E, I> consumerMapper;

    @Override
    protected void optionalName(@NonNull M model) throws RestException {
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
        this.createActuator = (K tablekey, @NonNull M model) -> {
            if (isModelUnique()) {
                Boolean existByModel = existByModel(tablekey, model);
                fieldRepeat(existByModel, model);
            } else if (isNameUnique()) {
                Boolean existByName = existByName(tablekey, model);
                OptionalHelper.nameRepeat(existByName, model.getName());
            }

        };
        this.updateActuator = (K tablekey, @NonNull M model) -> {
            if (isModelUnique()) {
                Boolean existByModel = existByModelAndNotId(tablekey, model, model.getId());
                fieldRepeat(existByModel, model);
            } else if (isNameUnique()) {
                Boolean existByName = existByNameAndNotId(tablekey, model, model.getId());
                OptionalHelper.nameRepeat(existByName, model.getName());
            }
        };
        if (super.superMapper instanceof InfoMapper) {
            this.consumerMapper = (InfoMapper) super.superMapper;
        }
    }

    protected Boolean existByName(K tablekey, M model) throws RestException {
        if (GeneralUtils.isEmpty(model.getName())) {
            return false;
        }
        String tablename = tablename(tablekey, model);
        List<E> entityList;
        if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
            entityList = consumerMapper.findDynamicByName(tablename, model.getName(), removeValue());
        } else {
            entityList = consumerMapper.findByName(model.getName(), removeValue());
        }
        return GeneralUtils.isNotEmpty(entityList);
    }


    protected Boolean existByNameAndNotId(K tablekey, M model, I id) throws RestException {
        if (GeneralUtils.isEmpty(model.getName())) {
            return false;
        }
        if (GeneralUtils.isEmpty(id)) {
            return existByName(tablekey, model);
        }
        String tablename = tablename(tablekey, model);
        List<E> entityList;
        if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
            entityList = consumerMapper.findDynamicByNameAndNotId(tablename, model.getName(), id, removeValue());
        } else {
            entityList = consumerMapper.findByNameAndNotId(model.getName(), id, removeValue());
        }
        return GeneralUtils.isNotEmpty(entityList);
    }

    protected Boolean existByModel(K tablekey, M model) throws RestException {
        if (GeneralUtils.isEmpty(model)) {
            return false;
        }
        E entity = this.createEntity(model);
        String tablename = tablename(tablekey, model);
        List<E> entityList;
        if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
            entityList = consumerMapper.findDynamicByEntity(tablename, entity, removeValue());
        } else {
            entityList = consumerMapper.findByEntity(entity, removeValue());
        }
        return GeneralUtils.isNotEmpty(entityList);
    }

    protected Boolean existByModelAndNotId(K tablekey, M model, I id) throws RestException {
        if (GeneralUtils.isEmpty(model)) {
            return false;
        }
        if (GeneralUtils.isEmpty(id)) {
            return existByModel(tablekey, model);
        }
        E entity = this.createEntity(model);
        String tablename = tablename(tablekey, model);
        List<E> entityList;
        if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
            entityList = consumerMapper.findDynamicByEntityAndNotId(tablename, entity, id, removeValue());
        } else {
            entityList = consumerMapper.findByEntityAndNotId(entity, id, removeValue());
        }
        return GeneralUtils.isNotEmpty(entityList);
    }
}
