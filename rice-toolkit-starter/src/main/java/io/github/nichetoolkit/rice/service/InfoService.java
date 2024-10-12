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
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

/**
 * <code>InfoService</code>
 * <p>The type info service class.</p>
 * @param <M> {@link io.github.nichetoolkit.rice.RestInfo} <p>The generic parameter is <code>RestInfo</code> type.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.RestInfo} <p>The generic parameter is <code>RestInfo</code> type.</p>
 * @param <F> {@link io.github.nichetoolkit.rice.filter.IdFilter} <p>The generic parameter is <code>IdFilter</code> type.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @param <K> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestInfo
 * @see io.github.nichetoolkit.rice.filter.IdFilter
 * @see io.github.nichetoolkit.rice.service.SuperService
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@Slf4j
@SuppressWarnings("RedundantThrows")
public abstract class InfoService<M extends RestInfo<I>, E extends RestInfo<I>, F extends IdFilter<I, K>, I, K> extends SuperService<M, E, F, I, K> {

    /**
     * <code>infoMapper</code>
     * {@link io.github.nichetoolkit.rice.mapper.InfoMapper} <p>The <code>infoMapper</code> field.</p>
     * @see io.github.nichetoolkit.rice.mapper.InfoMapper
     */
    protected InfoMapper<E, I> infoMapper;

    @Override
    protected void optionalName(@NonNull M model) throws RestException {
        if (isNameOfNonnull()) {
            OptionalUtils.ofFieldNull(model.getName(), "The name can not be null！",log);
        }
    }

    /**
     * <code>fieldRepeat</code>
     * <p>The repeat method.</p>
     * @param existByModel {@link java.lang.Boolean} <p>The exist by model parameter is <code>Boolean</code> type.</p>
     * @param model        M <p>The model parameter is <code>M</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestException
     */
    protected void fieldRepeat(Boolean existByModel, M model) throws RestException {
        OptionalUtils.ofFieldRepeat(existByModel, JsonUtils.parseJson(model),log);
    }

    @Override
    protected void afterSuperHandle() throws RestException {
        this.createActuator = (K tablekey, @NonNull M model) -> {
            if (isModelOfUnique()) {
                Boolean existByModel = existByModel(tablekey, model);
                fieldRepeat(existByModel, model);
            } else if (isNameOfUnique()) {
                Boolean existByName = existByName(tablekey, model);
                OptionalUtils.ofNameRepeat(existByName, model.getName(),log);
            }

        };
        this.updateActuator = (K tablekey, @NonNull M model) -> {
            if (isModelOfUnique()) {
                Boolean existByModel = existByModelAndNotId(tablekey, model, model.getId());
                fieldRepeat(existByModel, model);
            } else if (isNameOfUnique()) {
                Boolean existByName = existByNameAndNotId(tablekey, model, model.getId());
                OptionalUtils.ofNameRepeat(existByName, model.getName(),log);
            }
        };
        if (super.superMapper instanceof InfoMapper) {
            this.infoMapper = (InfoMapper<E, I>) super.superMapper;
        }
    }

    /**
     * <code>existByName</code>
     * <p>The by name method.</p>
     * @param tablekey K <p>The tablekey parameter is <code>K</code> type.</p>
     * @param model    M <p>The model parameter is <code>M</code> type.</p>
     * @return {@link java.lang.Boolean} <p>The by name return object is <code>Boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestException
     */
    private Boolean existByName(K tablekey, M model) throws RestException {
        if (GeneralUtils.isEmpty(model.getName())) {
            return false;
        }
        String tablename = resolveTablename(tablekey, model);
        List<E> entityList;
        if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
            entityList = infoMapper.findDynamicByName(tablename, model.getName(), valueOfLogic());
        } else {
            entityList = infoMapper.findByName(model.getName(), valueOfLogic());
        }
        return GeneralUtils.isNotEmpty(entityList);
    }


    /**
     * <code>existByNameAndNotId</code>
     * <p>The by name and not id method.</p>
     * @param tablekey K <p>The tablekey parameter is <code>K</code> type.</p>
     * @param model    M <p>The model parameter is <code>M</code> type.</p>
     * @param id       I <p>The id parameter is <code>I</code> type.</p>
     * @return {@link java.lang.Boolean} <p>The by name and not id return object is <code>Boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
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
        if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
            entityList = infoMapper.findDynamicByNameAndNotId(tablename, model.getName(), id, valueOfLogic());
        } else {
            entityList = infoMapper.findByNameAndNotId(model.getName(), id, valueOfLogic());
        }
        return GeneralUtils.isNotEmpty(entityList);
    }

    /**
     * <code>existByModel</code>
     * <p>The by model method.</p>
     * @param tablekey K <p>The tablekey parameter is <code>K</code> type.</p>
     * @param model    M <p>The model parameter is <code>M</code> type.</p>
     * @return {@link java.lang.Boolean} <p>The by model return object is <code>Boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
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
        if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
            entityList = infoMapper.findDynamicByEntity(tablename, entity, valueOfLogic());
        } else {
            entityList = infoMapper.findByEntity(entity, valueOfLogic());
        }
        return GeneralUtils.isNotEmpty(entityList);
    }

    /**
     * <code>existByModelAndNotId</code>
     * <p>The by model and not id method.</p>
     * @param tablekey K <p>The tablekey parameter is <code>K</code> type.</p>
     * @param model    M <p>The model parameter is <code>M</code> type.</p>
     * @param id       I <p>The id parameter is <code>I</code> type.</p>
     * @return {@link java.lang.Boolean} <p>The by model and not id return object is <code>Boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
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
        if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
            entityList = infoMapper.findDynamicByEntityAndNotId(tablename, entity, id, valueOfLogic());
        } else {
            entityList = infoMapper.findByEntityAndNotId(entity, id, valueOfLogic());
        }
        return GeneralUtils.isNotEmpty(entityList);
    }

    /**
     * <code>findByName</code>
     * <p>The by name method.</p>
     * @param name      {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @param tablename {@link java.lang.String} <p>The tablename parameter is <code>String</code> type.</p>
     * @return {@link java.util.List} <p>The by name return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.RestException
     */
    private List<E> findByName(String name, String tablename) throws RestException {
        List<E> entityList;
        if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
            entityList = infoMapper.findDynamicByName(tablename, name, valueOfLogic());
        } else {
            entityList = infoMapper.findByName(name, valueOfLogic());
        }
        return entityList;
    }

    /**
     * <code>queryByName</code>
     * <p>The by name method.</p>
     * @param name        {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @param isLoadArray {@link java.lang.Boolean} <p>The is load array parameter is <code>Boolean</code> type.</p>
     * @return {@link java.util.List} <p>The by name return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
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
     * <p>The by name method.</p>
     * @param tablekey    K <p>The tablekey parameter is <code>K</code> type.</p>
     * @param name        {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @param isLoadArray {@link java.lang.Boolean} <p>The is load array parameter is <code>Boolean</code> type.</p>
     * @return {@link java.util.List} <p>The by name return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
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
                if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
                    entityList = loadMapper.findDynamicByNameLoad(tablename, name, valueOfLogic(), isLoadArray);
                } else {
                    entityList = loadMapper.findByNameLoad(name, valueOfLogic(), isLoadArray);
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
