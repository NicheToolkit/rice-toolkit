package io.github.nichetoolkit.rice.service;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.util.JsonUtils;
import io.github.nichetoolkit.rest.util.OptionalUtils;
import io.github.nichetoolkit.rice.RestInfo;
import io.github.nichetoolkit.rice.filter.IdFilter;
import io.github.nichetoolkit.rice.mapper.InfoMapper;
import io.github.nichetoolkit.rice.mapper.natives.FindLoadMapper;
import io.github.nichetoolkit.rice.mapper.natives.NameLoadMapper;
import org.springframework.lang.NonNull;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

/**
 * <code>InfoService</code>
 * <p>The type info service class.</p>
 * @param <M> {@link io.github.nichetoolkit.rice.InfoModel} <p>the generic parameter is <code>InfoModel</code> type.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.InfoEntity} <p>the generic parameter is <code>InfoEntity</code> type.</p>
 * @param <F> {@link io.github.nichetoolkit.rice.filter.IdFilter} <p>the generic parameter is <code>IdFilter</code> type.</p>
 * @param <I> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @param <K> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.InfoModel
 * @see io.github.nichetoolkit.rice.InfoEntity
 * @see io.github.nichetoolkit.rice.filter.IdFilter
 * @see io.github.nichetoolkit.rice.service.SuperService
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@SuppressWarnings("RedundantThrows")
public abstract class InfoService<M extends RestInfo<I>, E extends RestInfo<I>, F extends IdFilter<I, K>, I, K> extends SuperService<M, E, F, I, K> {

    /**
     * <code>infoMapper</code>
     * {@link io.github.nichetoolkit.rice.mapper.InfoMapper} <p>the <code>infoMapper</code> field.</p>
     * @see io.github.nichetoolkit.rice.mapper.InfoMapper
     */
    protected InfoMapper<E, I> infoMapper;

    @Override
    protected void optionalName(@NonNull M model) throws RestException {
        if (isNameNonnull()) {
            OptionalUtils.ofFieldNull(model.getName(), "the name can not be null！");
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
        OptionalUtils.ofFieldRepeat(existByModel, JsonUtils.parseJson(model));
    }

    @Override
    protected void applyHandle() throws RestException {
        this.createActuator = (K tablekey, @NonNull M model) -> {
            if (isModelUnique()) {
                Boolean existByModel = existByModel(tablekey, model);
                fieldRepeat(existByModel, model);
            } else if (isNameUnique()) {
                Boolean existByName = existByName(tablekey, model);
                OptionalUtils.ofNameRepeat(existByName, model.getName());
            }

        };
        this.updateActuator = (K tablekey, @NonNull M model) -> {
            if (isModelUnique()) {
                Boolean existByModel = existByModelAndNotId(tablekey, model, model.getId());
                fieldRepeat(existByModel, model);
            } else if (isNameUnique()) {
                Boolean existByName = existByNameAndNotId(tablekey, model, model.getId());
                OptionalUtils.ofNameRepeat(existByName, model.getName());
            }
        };
        if (super.superMapper instanceof InfoMapper) {
            this.infoMapper = (InfoMapper<E, I>) super.superMapper;
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
    private Boolean existByName(K tablekey, M model) throws RestException {
        if (GeneralUtils.isEmpty(model.getName())) {
            return false;
        }
        String tablename = resolveTablename(tablekey, model);
        List<E> entityList;
        if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
            entityList = infoMapper.findDynamicByName(tablename, model.getName(), logicValue());
        } else {
            entityList = infoMapper.findByName(model.getName(), logicValue());
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
    private Boolean existByNameAndNotId(K tablekey, M model, I id) throws RestException {
        if (GeneralUtils.isEmpty(model.getName())) {
            return false;
        }
        if (GeneralUtils.isEmpty(id)) {
            return existByName(tablekey, model);
        }
        String tablename = resolveTablename(tablekey, model);
        List<E> entityList;
        if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
            entityList = infoMapper.findDynamicByNameAndNotId(tablename, model.getName(), id, logicValue());
        } else {
            entityList = infoMapper.findByNameAndNotId(model.getName(), id, logicValue());
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
    private Boolean existByModel(K tablekey, M model) throws RestException {
        if (GeneralUtils.isEmpty(model)) {
            return false;
        }
        E entity = this.createEntity(model);
        String tablename = resolveTablename(tablekey, model);
        List<E> entityList;
        if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
            entityList = infoMapper.findDynamicByEntity(tablename, entity, logicValue());
        } else {
            entityList = infoMapper.findByEntity(entity, logicValue());
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
    private Boolean existByModelAndNotId(K tablekey, M model, I id) throws RestException {
        if (GeneralUtils.isEmpty(model)) {
            return false;
        }
        if (GeneralUtils.isEmpty(id)) {
            return existByModel(tablekey, model);
        }
        E entity = this.createEntity(model);
        String tablename = resolveTablename(tablekey, model);
        List<E> entityList;
        if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
            entityList = infoMapper.findDynamicByEntityAndNotId(tablename, entity, id, logicValue());
        } else {
            entityList = infoMapper.findByEntityAndNotId(entity, id, logicValue());
        }
        return GeneralUtils.isNotEmpty(entityList);
    }

    /**
     * <code>findByName</code>
     * <p>the by name method.</p>
     * @param name      {@link java.lang.String} <p>the name parameter is <code>String</code> type.</p>
     * @param tablename {@link java.lang.String} <p>the tablename parameter is <code>String</code> type.</p>
     * @return {@link java.util.List} <p>the by name return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.RestException
     */
    private List<E> findByName(String name, String tablename) throws RestException {
        List<E> entityList;
        if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
            entityList = infoMapper.findDynamicByName(tablename, name, logicValue());
        } else {
            entityList = infoMapper.findByName(name, logicValue());
        }
        return entityList;
    }

    /**
     * <code>queryByName</code>
     * <p>the by name method.</p>
     * @param name        {@link java.lang.String} <p>the name parameter is <code>String</code> type.</p>
     * @param isLoadArray {@link java.lang.Boolean} <p>the is load array parameter is <code>Boolean</code> type.</p>
     * @return {@link java.util.List} <p>the by name return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Boolean
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.RestException
     */
    public List<M> queryByName(String name, Boolean... isLoadArray) throws RestException {
        return queryByName(null, name, isLoadArray);
    }

    /**
     * <code>queryByName</code>
     * <p>the by name method.</p>
     * @param tablekey    K <p>the tablekey parameter is <code>K</code> type.</p>
     * @param name        {@link java.lang.String} <p>the name parameter is <code>String</code> type.</p>
     * @param isLoadArray {@link java.lang.Boolean} <p>the is load array parameter is <code>Boolean</code> type.</p>
     * @return {@link java.util.List} <p>the by name return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Boolean
     * @see java.util.List
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    public List<M> queryByName(K tablekey, String name, Boolean... isLoadArray) throws RestException {
        if (GeneralUtils.isEmpty(name)) {
            return null;
        }
        List<E> entityList;
        String tablename = resolveTablename(tablekey);
        if (isLoadArray.length > 0 && FindLoadMapper.class.isAssignableFrom(superMapper.getClass())) {
            NameLoadMapper<E, I> loadMapper = (NameLoadMapper<E, I>) superMapper;
            Method findMethod = null;
            try {
                findMethod = loadMapper.getClass().getMethod("findByNameLoad", String.class, String.class, Boolean[].class);
            } catch (NoSuchMethodException ignored) {
            }
            Method queryByNameMethod = findMethod;
            /* 当LoadMapper被复写的时候 优先调用LoadMapper的queryByNameMethod */
            if (queryByNameMethod != null && !queryByNameMethod.isDefault()) {
                if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
                    entityList = loadMapper.findDynamicByNameLoad(tablename, name, logicValue(), isLoadArray);
                } else {
                    entityList = loadMapper.findByNameLoad(name, logicValue(), isLoadArray);
                }
            } else {
                entityList = findByName(name, tablename);
            }
        } else {
            entityList = findByName(name, tablename);
        }
        if (GeneralUtils.isEmpty(entityList)) {
            return Collections.emptyList();
        }
        return mutateEntityList(entityList,entity -> {}, isLoadArray);
    }

}
