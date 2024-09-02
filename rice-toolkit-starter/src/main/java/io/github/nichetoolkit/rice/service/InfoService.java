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
 * <code>InfoService</code>
 * <p>The type info service class.</p>
 * @param <K> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @param <I> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @param <M> {@link io.github.nichetoolkit.rice.InfoModel} <p>the generic parameter is <code>InfoModel</code> type.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.InfoEntity} <p>the generic parameter is <code>InfoEntity</code> type.</p>
 * @param <F> {@link io.github.nichetoolkit.rice.filter.IdFilter} <p>the generic parameter is <code>IdFilter</code> type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.InfoModel
 * @see io.github.nichetoolkit.rice.InfoEntity
 * @see io.github.nichetoolkit.rice.filter.IdFilter
 * @see io.github.nichetoolkit.rice.service.SuperService
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@SuppressWarnings("RedundantThrows")
public abstract class InfoService<K, I, M extends InfoModel<I>, E extends InfoEntity<I>, F extends IdFilter<I, K>> extends SuperService<K, I, M, E, F> {

    /**
     * <code>consumerMapper</code>
     * {@link io.github.nichetoolkit.rice.mapper.InfoMapper} <p>the <code>consumerMapper</code> field.</p>
     * @see io.github.nichetoolkit.rice.mapper.InfoMapper
     */
    protected InfoMapper<E, I> consumerMapper;

    @Override
    protected void optionalName(@NonNull M model) throws RestException {
        if (isNameNonnull()) {
            OptionalHelper.fieldable(model.getName(), "名称不能为空！");
        }
    }

    /**
     * <code>fieldRepeat</code>
     * <p>the repeat method.</p>
     * @param existByModel {@link java.lang.Boolean} <p>the exist by model parameter is <code>Boolean</code> type.</p>
     * @param model        M <p>the model parameter is <code>M</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestException
     */
    protected void fieldRepeat(Boolean existByModel, M model) throws RestException {
        OptionalHelper.fieldRepeat(existByModel, JsonUtils.parseJson(model));
    }

    @Override
    public void applyHandle() {
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
            this.consumerMapper = (InfoMapper<E, I>) super.superMapper;
        }
    }

    /**
     * <code>existByName</code>
     * <p>the by name method.</p>
     * @param tablekey K <p>the tablekey parameter is <code>K</code> type.</p>
     * @param model    M <p>the model parameter is <code>M</code> type.</p>
     * @return {@link java.lang.Boolean} <p>the by name return object is <code>Boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestException
     */
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


    /**
     * <code>existByNameAndNotId</code>
     * <p>the by name and not id method.</p>
     * @param tablekey K <p>the tablekey parameter is <code>K</code> type.</p>
     * @param model    M <p>the model parameter is <code>M</code> type.</p>
     * @param id       I <p>the id parameter is <code>I</code> type.</p>
     * @return {@link java.lang.Boolean} <p>the by name and not id return object is <code>Boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestException
     */
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

    /**
     * <code>existByModel</code>
     * <p>the by model method.</p>
     * @param tablekey K <p>the tablekey parameter is <code>K</code> type.</p>
     * @param model    M <p>the model parameter is <code>M</code> type.</p>
     * @return {@link java.lang.Boolean} <p>the by model return object is <code>Boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestException
     */
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

    /**
     * <code>existByModelAndNotId</code>
     * <p>the by model and not id method.</p>
     * @param tablekey K <p>the tablekey parameter is <code>K</code> type.</p>
     * @param model    M <p>the model parameter is <code>M</code> type.</p>
     * @param id       I <p>the id parameter is <code>I</code> type.</p>
     * @return {@link java.lang.Boolean} <p>the by model and not id return object is <code>Boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestException
     */
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
