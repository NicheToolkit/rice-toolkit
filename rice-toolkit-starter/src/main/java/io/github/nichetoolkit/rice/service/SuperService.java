package io.github.nichetoolkit.rice.service;

import com.github.pagehelper.Page;
import io.github.nichetoolkit.mybatis.fickle.RestFickle;
import io.github.nichetoolkit.mybatis.load.RestLoad;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestKey;
import io.github.nichetoolkit.rest.actuator.ConsumerActuator;
import io.github.nichetoolkit.rest.error.natives.UnsupportedErrorException;
import io.github.nichetoolkit.rest.helper.PartitionHelper;
import io.github.nichetoolkit.rest.util.BeanUtils;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.util.JsonUtils;
import io.github.nichetoolkit.rest.util.OptionalUtils;
import io.github.nichetoolkit.rice.*;
import io.github.nichetoolkit.rice.enums.DeleteMode;
import io.github.nichetoolkit.rice.enums.OperateType;
import io.github.nichetoolkit.rice.error.service.ServiceUnknownException;
import io.github.nichetoolkit.rice.filter.AlertFilter;
import io.github.nichetoolkit.rice.filter.IdFilter;
import io.github.nichetoolkit.rice.mapper.*;
import io.github.nichetoolkit.rice.mapper.filter.*;
import io.github.nichetoolkit.rice.mapper.natives.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.*;

/**
 * <code>SuperService</code>
 * <p>The super service class.</p>
 * @param <M> {@link io.github.nichetoolkit.rice.RestId} <p>The generic parameter is <code>RestId</code> type.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.RestId} <p>The generic parameter is <code>RestId</code> type.</p>
 * @param <F> {@link io.github.nichetoolkit.rice.filter.IdFilter} <p>The generic parameter is <code>IdFilter</code> type.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @param <K> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestId
 * @see io.github.nichetoolkit.rice.filter.IdFilter
 * @see io.github.nichetoolkit.rice.service.SuperAdvice
 * @see org.springframework.beans.factory.InitializingBean
 * @see lombok.extern.slf4j.Slf4j
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@Slf4j
@SuppressWarnings("RedundantThrows")
public abstract class SuperService<M extends RestId<I>, E extends RestId<I>, F extends IdFilter<I, K>, I, K>
        extends SuperAdvice<M, E, F, I, K> implements InitializingBean {

    /**
     * <code>simpleName</code>
     * {@link java.lang.String} <p>The <code>simpleName</code> field.</p>
     * @see java.lang.String
     */
    private String simpleName;

    @Override
    public void afterPropertiesSet() throws Exception {
        ServiceHolder.initOfService();
        ServiceHolder.initOfServiceFitter();
        this.simpleName = this.getClass().getSimpleName();
        this.superMapper = ServiceHolder.findSuperMapper(this.getClass());
        String superMessage = "The service and mapper name must be like 'xxxService'/'xxxServiceImpl' and 'xxxMapper'.";
        OptionalUtils.ofNullException(this.superMapper, superMessage, this.simpleName, log, ServiceUnknownException::new);
        this.tableMapper = BeanUtils.beanOfType(TableMapper.class);
        if (isFickleField()) {
            String tableMessage = "The bean of table mapper is no found, it's possible that you don't need auto fickle.";
            OptionalUtils.ofNullException(this.tableMapper, tableMessage, this.simpleName, log, ServiceUnknownException::new);
        }
        this.afterSuperHandle();
    }

    /**
     * <code>create</code>
     * <p>The create method.</p>
     * @param model   M <p>The model parameter is <code>M</code> type.</p>
     * @param idArray {@link java.lang.Object} <p>The id array parameter is <code>Object</code> type.</p>
     * @return M <p>The create return object is <code>M</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Object
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public M create(M model, Object... idArray) throws RestException {
        return create(null, model, idArray);
    }

    /**
     * <code>create</code>
     * <p>The create method.</p>
     * @param tableKey {@link io.github.nichetoolkit.rice.RestTableKey} <p>The table key parameter is <code>RestTableKey</code> type.</p>
     * @param model    M <p>The model parameter is <code>M</code> type.</p>
     * @param idArray  {@link java.lang.Object} <p>The id array parameter is <code>Object</code> type.</p>
     * @return M <p>The create return object is <code>M</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see java.lang.Object
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public M create(RestTableKey<K> tableKey, M model, Object... idArray) throws RestException {
        if (GeneralUtils.isEmpty(model)) {
            return null;
        }
        optionalCreate(tableKey, model);
        this.beforeCreate(model);
        Integer result = single(tableKey, model, idArray);
        if (useSaveResult()) {
            String message = "The creating method has error with " + simpleName + ": " + JsonUtils.parseJson(model);
            OptionalUtils.ofCreate(result, message, simpleName, log);
        }
        this.afterCreate(model);
        this.refresh();
        return model;
    }

    /**
     * <code>update</code>
     * <p>The update method.</p>
     * @param model   M <p>The model parameter is <code>M</code> type.</p>
     * @param idArray {@link java.lang.Object} <p>The id array parameter is <code>Object</code> type.</p>
     * @return M <p>The update return object is <code>M</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Object
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public M update(M model, Object... idArray) throws RestException {
        return update(null, model, idArray);
    }

    /**
     * <code>update</code>
     * <p>The update method.</p>
     * @param tableKey {@link io.github.nichetoolkit.rice.RestTableKey} <p>The table key parameter is <code>RestTableKey</code> type.</p>
     * @param model    M <p>The model parameter is <code>M</code> type.</p>
     * @param idArray  {@link java.lang.Object} <p>The id array parameter is <code>Object</code> type.</p>
     * @return M <p>The update return object is <code>M</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see java.lang.Object
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public M update(RestTableKey<K> tableKey, M model, Object... idArray) throws RestException {
        if (GeneralUtils.isEmpty(model)) {
            return null;
        }
        optionalUpdate(tableKey, model);
        this.beforeUpdate(model);
        Integer result = single(tableKey, model, idArray);
        if (useSaveResult()) {
            String message = "The updating method has error with " + simpleName + ": " + JsonUtils.parseJson(model);
            OptionalUtils.ofUpdate(result, message, simpleName, log);
        }
        this.afterUpdate(model);
        this.refresh();
        return model;
    }

    /**
     * <code>save</code>
     * <p>The save method.</p>
     * @param model   M <p>The model parameter is <code>M</code> type.</p>
     * @param idArray {@link java.lang.Object} <p>The id array parameter is <code>Object</code> type.</p>
     * @return M <p>The save return object is <code>M</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Object
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public M save(M model, Object... idArray) throws RestException {
        return save(null, model, idArray);
    }

    /**
     * <code>save</code>
     * <p>The save method.</p>
     * @param tableKey {@link io.github.nichetoolkit.rice.RestTableKey} <p>The table key parameter is <code>RestTableKey</code> type.</p>
     * @param model    M <p>The model parameter is <code>M</code> type.</p>
     * @param idArray  {@link java.lang.Object} <p>The id array parameter is <code>Object</code> type.</p>
     * @return M <p>The save return object is <code>M</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see java.lang.Object
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public M save(RestTableKey<K> tableKey, M model, Object... idArray) throws RestException {
        if (GeneralUtils.isEmpty(model)) {
            return null;
        }
        optionalSave(tableKey, model);
        this.beforeSave(model);
        Integer result = single(tableKey, model, idArray);
        if (useSaveResult()) {
            String message = "The saving method has error with " + simpleName + ": " + JsonUtils.parseJson(model);
            OptionalUtils.ofSave(result, message, simpleName, log);
        }
        this.afterSave(model);
        this.refresh();
        return model;
    }

    /**
     * <code>saveAll</code>
     * <p>The save all method.</p>
     * @param modelList {@link java.util.Collection} <p>The model list parameter is <code>Collection</code> type.</p>
     * @return {@link java.util.List} <p>The save all return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see java.util.List
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public List<M> saveAll(Collection<M> modelList) throws RestException {
        return saveAll(modelList, (Object[]) null);
    }

    /**
     * <code>saveAll</code>
     * <p>The save all method.</p>
     * @param modelList {@link java.util.Collection} <p>The model list parameter is <code>Collection</code> type.</p>
     * @param idArray   {@link java.lang.Object} <p>The id array parameter is <code>Object</code> type.</p>
     * @return {@link java.util.List} <p>The save all return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see java.lang.Object
     * @see java.util.List
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public List<M> saveAll(Collection<M> modelList, Object... idArray) throws RestException {
        return saveAll(null, modelList, idArray);
    }

    /**
     * <code>saveAll</code>
     * <p>The save all method.</p>
     * @param tableKey  {@link io.github.nichetoolkit.rice.RestTableKey} <p>The table key parameter is <code>RestTableKey</code> type.</p>
     * @param modelList {@link java.util.Collection} <p>The model list parameter is <code>Collection</code> type.</p>
     * @param idArray   {@link java.lang.Object} <p>The id array parameter is <code>Object</code> type.</p>
     * @return {@link java.util.List} <p>The save all return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see java.util.Collection
     * @see java.lang.Object
     * @see java.util.List
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public List<M> saveAll(RestTableKey<K> tableKey, Collection<M> modelList, Object... idArray) throws RestException {
        if (GeneralUtils.isEmpty(modelList)) {
            return Collections.emptyList();
        }
        Method beforeSaveAllMethod = null;
        try {
            beforeSaveAllMethod = this.getClass().getMethod("beforeSaveAll", Collection.class);
        } catch (NoSuchMethodException ignored) {
        }
        List<E> entityList;
        if (beforeSaveAllMethod != null && !beforeSaveAllMethod.isDefault()) {
            for (M model : modelList) {
                optionalSave(tableKey, model);
            }
            this.beforeSaveAll(modelList);
            entityList = entityActuator(modelList, model -> {
            }, idArray);
        } else {
            entityList = entityActuator(modelList, model -> {
                this.optionalSave(tableKey, model);
                this.beforeSave(model);
            }, idArray);
        }
        String tableName = resolveTableName(tableKey, modelList);
        Integer result;
        if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tableName)) {
            result = PartitionHelper.save(entityList, this.partitionOfSave(), entities -> superMapper.saveDynamicAll(tableName, entities));
        } else {
            result = PartitionHelper.save(entityList, this.partitionOfSave(), superMapper::saveAll);
        }
        if (useSaveResult()) {
            Boolean present = modelList.size() == result;
            String message = "The saveAll method has error with " + simpleName + ": " + JsonUtils.parseJson(modelList);
            OptionalUtils.ofSaveAll(present, message, simpleName, log);
        }
        this.afterSaveAll(modelList);
        this.refresh();
        return new ArrayList<>(modelList);
    }

    /**
     * <code>operateById</code>
     * <p>The operate by id method.</p>
     * @param id      I <p>The id parameter is <code>I</code> type.</p>
     * @param operate {@link io.github.nichetoolkit.rice.enums.OperateType} <p>The operate parameter is <code>OperateType</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.enums.OperateType
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void operateById(I id, OperateType operate) throws RestException {
        operateById(null, id, operate);
    }

    /**
     * <code>operateById</code>
     * <p>The operate by id method.</p>
     * @param tableKey {@link io.github.nichetoolkit.rice.RestTableKey} <p>The table key parameter is <code>RestTableKey</code> type.</p>
     * @param id       I <p>The id parameter is <code>I</code> type.</p>
     * @param operate  {@link io.github.nichetoolkit.rice.enums.OperateType} <p>The operate parameter is <code>OperateType</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see io.github.nichetoolkit.rice.enums.OperateType
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void operateById(RestTableKey<K> tableKey, I id, OperateType operate) throws RestException {
        if (GeneralUtils.isEmpty(id) || GeneralUtils.isEmpty(operate)) {
            return;
        }
        if (superMapper instanceof OperateMapper) {
            String tableName = resolveTableName(tableKey);
            if (isBeforeSkip() && isAfterSkip()) {
                operateId(tableName, id, operate);
            } else {
                E entity = findById(id, tableName);
                if (GeneralUtils.isNotEmpty(entity)) {
                    if (!isBeforeSkip()) {
                        this.beforeOperate(entity);
                    }
                    operateId(tableName, id, operate);
                    if (!isAfterSkip()) {
                        this.afterOperate(entity);
                    }
                    this.refresh();
                }
            }
        }
    }

    /**
     * <code>operateAll</code>
     * <p>The operate all method.</p>
     * @param idList  {@link java.util.Collection} <p>The id list parameter is <code>Collection</code> type.</p>
     * @param operate {@link io.github.nichetoolkit.rice.enums.OperateType} <p>The operate parameter is <code>OperateType</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rice.enums.OperateType
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void operateAll(Collection<I> idList, OperateType operate) throws RestException {
        operateAll(null, idList, operate);
    }

    /**
     * <code>operateAll</code>
     * <p>The operate all method.</p>
     * @param tableKey {@link io.github.nichetoolkit.rice.RestTableKey} <p>The table key parameter is <code>RestTableKey</code> type.</p>
     * @param idList   {@link java.util.Collection} <p>The id list parameter is <code>Collection</code> type.</p>
     * @param operate  {@link io.github.nichetoolkit.rice.enums.OperateType} <p>The operate parameter is <code>OperateType</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rice.enums.OperateType
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void operateAll(RestTableKey<K> tableKey, Collection<I> idList, OperateType operate) throws RestException {
        if (GeneralUtils.isEmpty(idList)) {
            return;
        }
        if (superMapper instanceof OperateMapper) {
            String tableName = resolveTableName(tableKey);
            if (isBeforeSkip() && isAfterSkip()) {
                operatePartition(tableName, idList, operate);
            } else {
                List<E> entityList = findAll(idList, tableName);
                if (GeneralUtils.isNotEmpty(entityList)) {
                    operateAdvice(entityList, operate, type -> operatePartition(tableName, idList, type));
                }
            }
        }
    }

    /**
     * <code>operateByLinkId</code>
     * <p>The operate by link id method.</p>
     * @param <L>     {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param linkId  L <p>The link id parameter is <code>L</code> type.</p>
     * @param operate {@link io.github.nichetoolkit.rice.enums.OperateType} <p>The operate parameter is <code>OperateType</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.enums.OperateType
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L> void operateByLinkId(L linkId, OperateType operate) throws RestException {
        operateByLinkId(null, linkId, operate);
    }

    /**
     * <code>operateByLinkId</code>
     * <p>The operate by link id method.</p>
     * @param <L>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableKey {@link io.github.nichetoolkit.rice.RestTableKey} <p>The table key parameter is <code>RestTableKey</code> type.</p>
     * @param linkId   L <p>The link id parameter is <code>L</code> type.</p>
     * @param operate  {@link io.github.nichetoolkit.rice.enums.OperateType} <p>The operate parameter is <code>OperateType</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see io.github.nichetoolkit.rice.enums.OperateType
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L> void operateByLinkId(RestTableKey<K> tableKey, L linkId, OperateType operate) throws RestException {
        operateByLinkId(tableKey, linkId, null, operate);
    }

    /**
     * <code>operateByLinkId</code>
     * <p>The operate by link id method.</p>
     * @param <L>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param linkId   L <p>The link id parameter is <code>L</code> type.</p>
     * @param linkName {@link io.github.nichetoolkit.rest.RestKey} <p>The link name parameter is <code>RestKey</code> type.</p>
     * @param operate  {@link io.github.nichetoolkit.rice.enums.OperateType} <p>The operate parameter is <code>OperateType</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestKey
     * @see io.github.nichetoolkit.rice.enums.OperateType
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L> void operateByLinkId(L linkId, RestKey<String> linkName, OperateType operate) throws RestException {
        operateByLinkId(null, linkId, linkName, operate);
    }

    /**
     * <code>operateByLinkId</code>
     * <p>The operate by link id method.</p>
     * @param <L>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableKey {@link io.github.nichetoolkit.rice.RestTableKey} <p>The table key parameter is <code>RestTableKey</code> type.</p>
     * @param linkId   L <p>The link id parameter is <code>L</code> type.</p>
     * @param linkName {@link io.github.nichetoolkit.rest.RestKey} <p>The link name parameter is <code>RestKey</code> type.</p>
     * @param operate  {@link io.github.nichetoolkit.rice.enums.OperateType} <p>The operate parameter is <code>OperateType</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see io.github.nichetoolkit.rest.RestKey
     * @see io.github.nichetoolkit.rice.enums.OperateType
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L> void operateByLinkId(RestTableKey<K> tableKey, L linkId, RestKey<String> linkName, OperateType operate) throws RestException {
        if (GeneralUtils.isEmpty(linkId)) {
            return;
        }
        if (superMapper instanceof OperateLinkMapper) {
            String tableName = resolveTableName(tableKey);
            if (isBeforeSkip() && isAfterSkip()) {
                operateLinkId(tableName, linkId, linkName, operate);
            } else {
                List<E> entityList = findByLinkId(tableName, linkId, linkName);
                if (GeneralUtils.isNotEmpty(entityList)) {
                    if (!isBeforeSkip()) {
                        this.beforeOperateAll(entityList);
                    }
                    operateLinkId(tableName, linkId, linkName, operate);
                    if (!isAfterSkip()) {
                        this.afterOperateAll(entityList);
                    }
                    this.refresh();
                }
            }
        }
    }

    /**
     * <code>operateAllByLinkIds</code>
     * <p>The operate all by link ids method.</p>
     * @param <L>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param linkIdList {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @param operate    {@link io.github.nichetoolkit.rice.enums.OperateType} <p>The operate parameter is <code>OperateType</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rice.enums.OperateType
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L> void operateAllByLinkIds(Collection<L> linkIdList, OperateType operate) throws RestException {
        operateAllByLinkIds(null, linkIdList, operate);
    }

    /**
     * <code>operateAllByLinkIds</code>
     * <p>The operate all by link ids method.</p>
     * @param <L>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableKey   {@link io.github.nichetoolkit.rice.RestTableKey} <p>The table key parameter is <code>RestTableKey</code> type.</p>
     * @param linkIdList {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @param operate    {@link io.github.nichetoolkit.rice.enums.OperateType} <p>The operate parameter is <code>OperateType</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rice.enums.OperateType
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L> void operateAllByLinkIds(RestTableKey<K> tableKey, Collection<L> linkIdList, OperateType operate) throws RestException {
        operateAllByLinkIds(tableKey, linkIdList, null, operate);
    }

    /**
     * <code>operateAllByLinkIds</code>
     * <p>The operate all by link ids method.</p>
     * @param <L>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param linkIdList {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @param linkName   {@link io.github.nichetoolkit.rest.RestKey} <p>The link name parameter is <code>RestKey</code> type.</p>
     * @param operate    {@link io.github.nichetoolkit.rice.enums.OperateType} <p>The operate parameter is <code>OperateType</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestKey
     * @see io.github.nichetoolkit.rice.enums.OperateType
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L> void operateAllByLinkIds(Collection<L> linkIdList, RestKey<String> linkName, OperateType operate) throws RestException {
        operateAllByLinkIds(null, linkIdList, linkName, operate);
    }

    /**
     * <code>operateAllByLinkIds</code>
     * <p>The operate all by link ids method.</p>
     * @param <L>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableKey   {@link io.github.nichetoolkit.rice.RestTableKey} <p>The table key parameter is <code>RestTableKey</code> type.</p>
     * @param linkIdList {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @param linkName   {@link io.github.nichetoolkit.rest.RestKey} <p>The link name parameter is <code>RestKey</code> type.</p>
     * @param operate    {@link io.github.nichetoolkit.rice.enums.OperateType} <p>The operate parameter is <code>OperateType</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestKey
     * @see io.github.nichetoolkit.rice.enums.OperateType
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L> void operateAllByLinkIds(RestTableKey<K> tableKey, Collection<L> linkIdList, RestKey<String> linkName, OperateType operate) throws RestException {
        if (GeneralUtils.isEmpty(linkIdList)) {
            return;
        }
        if (superMapper instanceof OperateLinkMapper) {
            String tableName = resolveTableName(tableKey);
            if (isBeforeSkip() && isAfterSkip()) {
                operateLinkIdPartition(tableName, linkIdList, linkName, operate);
            } else {
                List<E> entityList = findAllByLinkIds(tableName, linkIdList, linkName);
                if (GeneralUtils.isNotEmpty(entityList)) {
                    operateAdvice(entityList, operate, type -> operateLinkIdPartition(tableName, linkIdList, linkName, type));
                }
            }
        }
    }

    /**
     * <code>alertById</code>
     * <p>The alert by id method.</p>
     * @param <S>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param id    I <p>The id parameter is <code>I</code> type.</p>
     * @param state S <p>The state parameter is <code>S</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <S> void alertById(I id, S state) throws RestException {
        alertById(null, id, state, null);
    }

    /**
     * <code>alertById</code>
     * <p>The alert by id method.</p>
     * @param <S>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableKey {@link io.github.nichetoolkit.rice.RestTableKey} <p>The table key parameter is <code>RestTableKey</code> type.</p>
     * @param id       I <p>The id parameter is <code>I</code> type.</p>
     * @param state    S <p>The state parameter is <code>S</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <S> void alertById(RestTableKey<K> tableKey, I id, S state) throws RestException {
        alertById(tableKey, id, state, null);
    }

    /**
     * <code>alertById</code>
     * <p>The alert by id method.</p>
     * @param <S>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param id        I <p>The id parameter is <code>I</code> type.</p>
     * @param state     S <p>The state parameter is <code>S</code> type.</p>
     * @param stateName {@link io.github.nichetoolkit.rest.RestKey} <p>The state name parameter is <code>RestKey</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestKey
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <S> void alertById(I id, S state, RestKey<String> stateName) throws RestException {
        alertById(null, id, state, stateName);
    }

    /**
     * <code>alertById</code>
     * <p>The alert by id method.</p>
     * @param <S>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableKey  {@link io.github.nichetoolkit.rice.RestTableKey} <p>The table key parameter is <code>RestTableKey</code> type.</p>
     * @param id        I <p>The id parameter is <code>I</code> type.</p>
     * @param state     S <p>The state parameter is <code>S</code> type.</p>
     * @param stateName {@link io.github.nichetoolkit.rest.RestKey} <p>The state name parameter is <code>RestKey</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see io.github.nichetoolkit.rest.RestKey
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <S> void alertById(RestTableKey<K> tableKey, I id, S state, RestKey<String> stateName) throws RestException {
        if (GeneralUtils.isEmpty(id) || GeneralUtils.isEmpty(state)) {
            return;
        }
        if (superMapper instanceof AlertMapper) {
            String tableName = resolveTableName(tableKey);
            if (isBeforeSkip() && isAfterSkip()) {
                alertId(tableName, id, state, stateName);
            } else {
                E entity = findById(id, tableName);
                if (GeneralUtils.isNotEmpty(entity)) {
                    if (!isBeforeSkip()) {
                        this.beforeAlert(entity);
                    }
                    alertId(tableName, id, state, stateName);
                    if (!isAfterSkip()) {
                        this.afterAlert(entity);
                    }
                    this.refresh();
                }
            }
        }
    }

    /**
     * <code>alertAll</code>
     * <p>The alert all method.</p>
     * @param <S>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param idList {@link java.util.Collection} <p>The id list parameter is <code>Collection</code> type.</p>
     * @param state  S <p>The state parameter is <code>S</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <S> void alertAll(Collection<I> idList, S state) throws RestException {
        alertAll(null, idList, state, null);
    }

    /**
     * <code>alertAll</code>
     * <p>The alert all method.</p>
     * @param <S>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableKey {@link io.github.nichetoolkit.rice.RestTableKey} <p>The table key parameter is <code>RestTableKey</code> type.</p>
     * @param idList   {@link java.util.Collection} <p>The id list parameter is <code>Collection</code> type.</p>
     * @param state    S <p>The state parameter is <code>S</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see java.util.Collection
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <S> void alertAll(RestTableKey<K> tableKey, Collection<I> idList, S state) throws RestException {
        alertAll(tableKey, idList, state, null);
    }

    /**
     * <code>alertAll</code>
     * <p>The alert all method.</p>
     * @param <S>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param idList    {@link java.util.Collection} <p>The id list parameter is <code>Collection</code> type.</p>
     * @param state     S <p>The state parameter is <code>S</code> type.</p>
     * @param stateName {@link io.github.nichetoolkit.rest.RestKey} <p>The state name parameter is <code>RestKey</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestKey
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <S> void alertAll(Collection<I> idList, S state, RestKey<String> stateName) throws RestException {
        alertAll(null, idList, state, stateName);
    }

    /**
     * <code>alertAll</code>
     * <p>The alert all method.</p>
     * @param <S>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableKey  {@link io.github.nichetoolkit.rice.RestTableKey} <p>The table key parameter is <code>RestTableKey</code> type.</p>
     * @param idList    {@link java.util.Collection} <p>The id list parameter is <code>Collection</code> type.</p>
     * @param state     S <p>The state parameter is <code>S</code> type.</p>
     * @param stateName {@link io.github.nichetoolkit.rest.RestKey} <p>The state name parameter is <code>RestKey</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestKey
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <S> void alertAll(RestTableKey<K> tableKey, Collection<I> idList, S state, RestKey<String> stateName) throws RestException {
        if (GeneralUtils.isEmpty(idList) || GeneralUtils.isEmpty(state)) {
            return;
        }
        if (superMapper instanceof AlertMapper) {
            String tableName = resolveTableName(tableKey);
            if (isBeforeSkip() && isAfterSkip()) {
                alertPartition(tableName, idList, state, stateName);
            } else {
                List<E> entityList = findAll(idList, tableName);
                if (GeneralUtils.isNotEmpty(entityList)) {
                    alertAdvice(entityList, state, alertState -> alertPartition(tableName, idList, alertState, stateName));
                }
            }
        }
    }

    /**
     * <code>alertByLinkId</code>
     * <p>The alert by link id method.</p>
     * @param <L>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <S>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param linkId L <p>The link id parameter is <code>L</code> type.</p>
     * @param state  S <p>The state parameter is <code>S</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L, S> void alertByLinkId(L linkId, S state) throws RestException {
        alertByLinkId(null, linkId, state, null);
    }

    /**
     * <code>alertByLinkId</code>
     * <p>The alert by link id method.</p>
     * @param <L>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <S>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableKey {@link io.github.nichetoolkit.rice.RestTableKey} <p>The table key parameter is <code>RestTableKey</code> type.</p>
     * @param linkId   L <p>The link id parameter is <code>L</code> type.</p>
     * @param state    S <p>The state parameter is <code>S</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L, S> void alertByLinkId(RestTableKey<K> tableKey, L linkId, S state) throws RestException {
        alertByLinkId(tableKey, linkId, null, state, null);
    }

    /**
     * <code>alertByLinkId</code>
     * <p>The alert by link id method.</p>
     * @param <L>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <S>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param linkId   L <p>The link id parameter is <code>L</code> type.</p>
     * @param linkName {@link io.github.nichetoolkit.rest.RestKey} <p>The link name parameter is <code>RestKey</code> type.</p>
     * @param state    S <p>The state parameter is <code>S</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestKey
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L, S> void alertByLinkId(L linkId, RestKey<String> linkName, S state) throws RestException {
        alertByLinkId(null, linkId, linkName, state, null);
    }

    /**
     * <code>alertByLinkId</code>
     * <p>The alert by link id method.</p>
     * @param <L>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <S>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableKey {@link io.github.nichetoolkit.rice.RestTableKey} <p>The table key parameter is <code>RestTableKey</code> type.</p>
     * @param linkId   L <p>The link id parameter is <code>L</code> type.</p>
     * @param linkName {@link io.github.nichetoolkit.rest.RestKey} <p>The link name parameter is <code>RestKey</code> type.</p>
     * @param state    S <p>The state parameter is <code>S</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see io.github.nichetoolkit.rest.RestKey
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L, S> void alertByLinkId(RestTableKey<K> tableKey, L linkId, RestKey<String> linkName, S state) throws RestException {
        alertByLinkId(tableKey, linkId, linkName, state, null);
    }

    /**
     * <code>alertByLinkId</code>
     * <p>The alert by link id method.</p>
     * @param <L>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <S>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param linkId    L <p>The link id parameter is <code>L</code> type.</p>
     * @param state     S <p>The state parameter is <code>S</code> type.</p>
     * @param stateName {@link io.github.nichetoolkit.rest.RestKey} <p>The state name parameter is <code>RestKey</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestKey
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L, S> void alertByLinkId(L linkId, S state, RestKey<String> stateName) throws RestException {
        alertByLinkId(null, linkId, state, stateName);
    }

    /**
     * <code>alertByLinkId</code>
     * <p>The alert by link id method.</p>
     * @param <L>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <S>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableKey  {@link io.github.nichetoolkit.rice.RestTableKey} <p>The table key parameter is <code>RestTableKey</code> type.</p>
     * @param linkId    L <p>The link id parameter is <code>L</code> type.</p>
     * @param state     S <p>The state parameter is <code>S</code> type.</p>
     * @param stateName {@link io.github.nichetoolkit.rest.RestKey} <p>The state name parameter is <code>RestKey</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see io.github.nichetoolkit.rest.RestKey
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L, S> void alertByLinkId(RestTableKey<K> tableKey, L linkId, S state, RestKey<String> stateName) throws RestException {
        alertByLinkId(tableKey, linkId, null, state, stateName);
    }

    /**
     * <code>alertByLinkId</code>
     * <p>The alert by link id method.</p>
     * @param <L>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <S>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param linkId    L <p>The link id parameter is <code>L</code> type.</p>
     * @param linkName  {@link io.github.nichetoolkit.rest.RestKey} <p>The link name parameter is <code>RestKey</code> type.</p>
     * @param state     S <p>The state parameter is <code>S</code> type.</p>
     * @param stateName {@link io.github.nichetoolkit.rest.RestKey} <p>The state name parameter is <code>RestKey</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestKey
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L, S> void alertByLinkId(L linkId, RestKey<String> linkName, S state, RestKey<String> stateName) throws RestException {
        alertByLinkId(null, linkId, linkName, state, stateName);
    }

    /**
     * <code>alertByLinkId</code>
     * <p>The alert by link id method.</p>
     * @param <L>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <S>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableKey  {@link io.github.nichetoolkit.rice.RestTableKey} <p>The table key parameter is <code>RestTableKey</code> type.</p>
     * @param linkId    L <p>The link id parameter is <code>L</code> type.</p>
     * @param linkName  {@link io.github.nichetoolkit.rest.RestKey} <p>The link name parameter is <code>RestKey</code> type.</p>
     * @param state     S <p>The state parameter is <code>S</code> type.</p>
     * @param stateName {@link io.github.nichetoolkit.rest.RestKey} <p>The state name parameter is <code>RestKey</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see io.github.nichetoolkit.rest.RestKey
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L, S> void alertByLinkId(RestTableKey<K> tableKey, L linkId, RestKey<String> linkName, S state, RestKey<String> stateName) throws RestException {
        if (GeneralUtils.isEmpty(linkId) || GeneralUtils.isEmpty(state)) {
            return;
        }
        if (superMapper instanceof AlertLinkMapper) {
            String tableName = resolveTableName(tableKey);
            if (isBeforeSkip() && isAfterSkip()) {
                alertLinkId(tableName, linkId, linkName, state, stateName);
            } else {
                List<E> entityList = findByLinkId(tableName, linkId, linkName);
                if (GeneralUtils.isNotEmpty(entityList)) {
                    if (!isBeforeSkip()) {
                        this.beforeAlertAll(entityList);
                    }
                    alertLinkId(tableName, linkId, linkName, state, stateName);
                    if (!isAfterSkip()) {
                        this.afterAlertAll(entityList);
                    }
                    this.refresh();
                }
            }
        }
    }

    /**
     * <code>alertAllByLinkIds</code>
     * <p>The alert all by link ids method.</p>
     * @param <L>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <S>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param linkIdList {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @param state      S <p>The state parameter is <code>S</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L, S> void alertAllByLinkIds(Collection<L> linkIdList, S state) throws RestException {
        alertAllByLinkIds(null, linkIdList, state, null);
    }

    /**
     * <code>alertAllByLinkIds</code>
     * <p>The alert all by link ids method.</p>
     * @param <L>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <S>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableKey   {@link io.github.nichetoolkit.rice.RestTableKey} <p>The table key parameter is <code>RestTableKey</code> type.</p>
     * @param linkIdList {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @param state      S <p>The state parameter is <code>S</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see java.util.Collection
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L, S> void alertAllByLinkIds(RestTableKey<K> tableKey, Collection<L> linkIdList, S state) throws RestException {
        alertAllByLinkIds(tableKey, linkIdList, null, state, null);
    }

    /**
     * <code>alertAllByLinkIds</code>
     * <p>The alert all by link ids method.</p>
     * @param <L>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <S>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param linkIdList {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @param linkName   {@link io.github.nichetoolkit.rest.RestKey} <p>The link name parameter is <code>RestKey</code> type.</p>
     * @param state      S <p>The state parameter is <code>S</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestKey
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L, S> void alertAllByLinkIds(Collection<L> linkIdList, RestKey<String> linkName, S state) throws RestException {
        alertAllByLinkIds(null, linkIdList, linkName, state, null);
    }

    /**
     * <code>alertAllByLinkIds</code>
     * <p>The alert all by link ids method.</p>
     * @param <L>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <S>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableKey   {@link io.github.nichetoolkit.rice.RestTableKey} <p>The table key parameter is <code>RestTableKey</code> type.</p>
     * @param linkIdList {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @param linkName   {@link io.github.nichetoolkit.rest.RestKey} <p>The link name parameter is <code>RestKey</code> type.</p>
     * @param state      S <p>The state parameter is <code>S</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestKey
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L, S> void alertAllByLinkIds(RestTableKey<K> tableKey, Collection<L> linkIdList, RestKey<String> linkName, S state) throws RestException {
        alertAllByLinkIds(tableKey, linkIdList, linkName, state, null);
    }

    /**
     * <code>alertAllByLinkIds</code>
     * <p>The alert all by link ids method.</p>
     * @param <L>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <S>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param linkIdList {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @param state      S <p>The state parameter is <code>S</code> type.</p>
     * @param stateName  {@link io.github.nichetoolkit.rest.RestKey} <p>The state name parameter is <code>RestKey</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestKey
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L, S> void alertAllByLinkIds(Collection<L> linkIdList, S state, RestKey<String> stateName) throws RestException {
        alertAllByLinkIds(null, linkIdList, state, stateName);
    }

    /**
     * <code>alertAllByLinkIds</code>
     * <p>The alert all by link ids method.</p>
     * @param <L>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <S>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableKey   {@link io.github.nichetoolkit.rice.RestTableKey} <p>The table key parameter is <code>RestTableKey</code> type.</p>
     * @param linkIdList {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @param state      S <p>The state parameter is <code>S</code> type.</p>
     * @param stateName  {@link io.github.nichetoolkit.rest.RestKey} <p>The state name parameter is <code>RestKey</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestKey
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L, S> void alertAllByLinkIds(RestTableKey<K> tableKey, Collection<L> linkIdList, S state, RestKey<String> stateName) throws RestException {
        alertAllByLinkIds(tableKey, linkIdList, null, state, stateName);
    }

    /**
     * <code>alertAllByLinkIds</code>
     * <p>The alert all by link ids method.</p>
     * @param <L>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <S>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param linkIdList {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @param linkName   {@link io.github.nichetoolkit.rest.RestKey} <p>The link name parameter is <code>RestKey</code> type.</p>
     * @param state      S <p>The state parameter is <code>S</code> type.</p>
     * @param stateName  {@link io.github.nichetoolkit.rest.RestKey} <p>The state name parameter is <code>RestKey</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestKey
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L, S> void alertAllByLinkIds(Collection<L> linkIdList, RestKey<String> linkName, S state, RestKey<String> stateName) throws RestException {
        alertAllByLinkIds(null, linkIdList, linkName, state, stateName);
    }

    /**
     * <code>alertAllByLinkIds</code>
     * <p>The alert all by link ids method.</p>
     * @param <L>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <S>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableKey   {@link io.github.nichetoolkit.rice.RestTableKey} <p>The table key parameter is <code>RestTableKey</code> type.</p>
     * @param linkIdList {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @param linkName   {@link io.github.nichetoolkit.rest.RestKey} <p>The link name parameter is <code>RestKey</code> type.</p>
     * @param state      S <p>The state parameter is <code>S</code> type.</p>
     * @param stateName  {@link io.github.nichetoolkit.rest.RestKey} <p>The state name parameter is <code>RestKey</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestKey
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L, S> void alertAllByLinkIds(RestTableKey<K> tableKey, Collection<L> linkIdList, RestKey<String> linkName, S state, RestKey<String> stateName) throws RestException {
        if (GeneralUtils.isEmpty(linkIdList) || GeneralUtils.isEmpty(state)) {
            return;
        }
        if (superMapper instanceof AlertLinkMapper) {
            String tableName = resolveTableName(tableKey);
            if (isBeforeSkip() && isAfterSkip()) {
                alertLinkIdPartition(tableName, linkIdList, linkName, state, stateName);
            } else {
                List<E> entityList = findAllByLinkIds(tableName, linkIdList, linkName);
                if (GeneralUtils.isNotEmpty(entityList)) {
                    alertAdvice(entityList, state, type -> alertLinkIdPartition(tableName, linkIdList, linkName, type, stateName));
                }
            }
        }
    }

    /**
     * <code>removeById</code>
     * <p>The remove by id method.</p>
     * @param id I <p>The id parameter is <code>I</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void removeById(I id) throws RestException {
        removeById(null, id);
    }

    /**
     * <code>removeById</code>
     * <p>The remove by id method.</p>
     * @param tableKey {@link io.github.nichetoolkit.rice.RestTableKey} <p>The table key parameter is <code>RestTableKey</code> type.</p>
     * @param id       I <p>The id parameter is <code>I</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void removeById(RestTableKey<K> tableKey, I id) throws RestException {
        if (GeneralUtils.isEmpty(id)) {
            return;
        }
        Object logic = markOfLogic();
        if (superMapper instanceof RemoveMapper) {
            String tableName = resolveTableName(tableKey);
            if (isBeforeSkip() && isAfterSkip()) {
                removeId(tableName, id, logic);
            } else {
                E entity = findById(id, tableName);
                if (GeneralUtils.isNotEmpty(entity)) {
                    if (!isBeforeSkip()) {
                        this.beforeRemove(entity);
                    }
                    removeId(tableName, id, logic);
                    if (!isAfterSkip()) {
                        this.afterRemove(entity);
                    }
                    this.refresh();
                }
            }
        }
    }

    /**
     * <code>removeAll</code>
     * <p>The remove all method.</p>
     * @param idList {@link java.util.Collection} <p>The id list parameter is <code>Collection</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void removeAll(Collection<I> idList) throws RestException {
        removeAll(null, idList);
    }

    /**
     * <code>removeAll</code>
     * <p>The remove all method.</p>
     * @param tableKey {@link io.github.nichetoolkit.rice.RestTableKey} <p>The table key parameter is <code>RestTableKey</code> type.</p>
     * @param idList   {@link java.util.Collection} <p>The id list parameter is <code>Collection</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see java.util.Collection
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void removeAll(RestTableKey<K> tableKey, Collection<I> idList) throws RestException {
        if (GeneralUtils.isEmpty(idList)) {
            return;
        }
        Object logic = markOfLogic();
        if (superMapper instanceof RemoveMapper) {
            String tableName = resolveTableName(tableKey);
            if (isBeforeSkip() && isAfterSkip()) {
                removePartition(tableName, idList, logic);
            } else {
                List<E> entityList = findAll(idList, tableName);
                if (GeneralUtils.isNotEmpty(entityList)) {
                    removeAdvice(entityList, logic, sign -> removePartition(tableName, idList, sign));
                }
            }
        }
    }

    /**
     * <code>removeByLinkId</code>
     * <p>The remove by link id method.</p>
     * @param <L>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param linkId L <p>The link id parameter is <code>L</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L> void removeByLinkId(L linkId) throws RestException {
        removeByLinkId(null, linkId);
    }

    /**
     * <code>removeByLinkId</code>
     * <p>The remove by link id method.</p>
     * @param <L>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableKey {@link io.github.nichetoolkit.rice.RestTableKey} <p>The table key parameter is <code>RestTableKey</code> type.</p>
     * @param linkId   L <p>The link id parameter is <code>L</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L> void removeByLinkId(RestTableKey<K> tableKey, L linkId) throws RestException {
        removeByLinkId(tableKey, linkId, null);
    }

    /**
     * <code>removeByLinkId</code>
     * <p>The remove by link id method.</p>
     * @param <L>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param linkId   L <p>The link id parameter is <code>L</code> type.</p>
     * @param linkName {@link io.github.nichetoolkit.rest.RestKey} <p>The link name parameter is <code>RestKey</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestKey
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L> void removeByLinkId(L linkId, RestKey<String> linkName) throws RestException {
        removeByLinkId(null, linkId, linkName);
    }

    /**
     * <code>removeByLinkId</code>
     * <p>The remove by link id method.</p>
     * @param <L>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableKey {@link io.github.nichetoolkit.rice.RestTableKey} <p>The table key parameter is <code>RestTableKey</code> type.</p>
     * @param linkId   L <p>The link id parameter is <code>L</code> type.</p>
     * @param linkName {@link io.github.nichetoolkit.rest.RestKey} <p>The link name parameter is <code>RestKey</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see io.github.nichetoolkit.rest.RestKey
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L> void removeByLinkId(RestTableKey<K> tableKey, L linkId, RestKey<String> linkName) throws RestException {
        if (GeneralUtils.isEmpty(linkId)) {
            return;
        }
        Object logic = markOfLogic();
        if (superMapper instanceof RemoveLinkMapper) {
            String tableName = resolveTableName(tableKey);
            if (isBeforeSkip() && isAfterSkip()) {
                removeLinkId(tableName, linkId, linkName, logic);
            } else {
                List<E> entityList = findByLinkId(tableName, linkId, linkName);
                if (GeneralUtils.isNotEmpty(entityList)) {
                    if (!isBeforeSkip()) {
                        this.beforeRemoveAll(entityList);
                    }
                    removeLinkId(tableName, linkId, linkName, logic);
                    if (!isAfterSkip()) {
                        this.afterRemoveAll(entityList);
                    }
                    this.refresh();
                }
            }
        }
    }

    /**
     * <code>removeAllByLinkIds</code>
     * <p>The remove all by link ids method.</p>
     * @param <L>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param linkIdList {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L> void removeAllByLinkIds(Collection<L> linkIdList) throws RestException {
        removeAllByLinkIds(null, linkIdList);
    }

    /**
     * <code>removeAllByLinkIds</code>
     * <p>The remove all by link ids method.</p>
     * @param <L>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableKey   {@link io.github.nichetoolkit.rice.RestTableKey} <p>The table key parameter is <code>RestTableKey</code> type.</p>
     * @param linkIdList {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see java.util.Collection
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L> void removeAllByLinkIds(RestTableKey<K> tableKey, Collection<L> linkIdList) throws RestException {
        removeAllByLinkIds(tableKey, linkIdList, null);
    }

    /**
     * <code>removeAllByLinkIds</code>
     * <p>The remove all by link ids method.</p>
     * @param <L>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param linkIdList {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @param linkName   {@link io.github.nichetoolkit.rest.RestKey} <p>The link name parameter is <code>RestKey</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestKey
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L> void removeAllByLinkIds(Collection<L> linkIdList, RestKey<String> linkName) throws RestException {
        removeAllByLinkIds(null, linkIdList, linkName);
    }

    /**
     * <code>removeAllByLinkIds</code>
     * <p>The remove all by link ids method.</p>
     * @param <L>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableKey   {@link io.github.nichetoolkit.rice.RestTableKey} <p>The table key parameter is <code>RestTableKey</code> type.</p>
     * @param linkIdList {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @param linkName   {@link io.github.nichetoolkit.rest.RestKey} <p>The link name parameter is <code>RestKey</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestKey
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L> void removeAllByLinkIds(RestTableKey<K> tableKey, Collection<L> linkIdList, RestKey<String> linkName) throws RestException {
        if (GeneralUtils.isEmpty(linkIdList)) {
            return;
        }
        Object logic = markOfLogic();
        if (superMapper instanceof RemoveLinkMapper) {
            String tableName = resolveTableName(tableKey);
            if (isBeforeSkip() && isAfterSkip()) {
                removeLinkIdPartition(tableName, linkIdList, linkName, logic);
            } else {
                List<E> entityList = findAllByLinkIds(tableName, linkIdList, linkName);
                if (GeneralUtils.isNotEmpty(entityList)) {
                    removeAdvice(entityList, logic, sign -> removeLinkIdPartition(tableName, linkIdList, linkName, sign));
                }
            }
        }
    }

    @Override
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void deleteById(I id) throws RestException {
        deleteById(null, id);
    }

    @Override
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void deleteById(RestTableKey<K> tableKey, I id) throws RestException {
        if (GeneralUtils.isEmpty(id)) {
            return;
        }
        DeleteMode deleteModel = deleteMode();
        if (deleteModel == DeleteMode.REMOVE) {
            removeById(tableKey, id);
        } else if (deleteModel == DeleteMode.OPERATE) {
            operateById(tableKey, id, OperateType.REMOVE);
        } else {
            String tableName = resolveTableName(tableKey);
            if (isBeforeSkip() && isAfterSkip()) {
                deleteId(tableName, id);
            } else {
                E entity = findById(id, tableName);
                if (GeneralUtils.isNotEmpty(entity)) {
                    if (!isBeforeSkip()) {
                        this.beforeDelete(entity);
                    }
                    deleteId(tableName, id);
                    if (!isAfterSkip()) {
                        this.afterDelete(entity);
                    }
                    this.refresh();
                }
            }
        }
    }

    @Override
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void deleteAll(Collection<I> idList) throws RestException {
        deleteAll(null, idList);
    }

    @Override
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void deleteAll(RestTableKey<K> tableKey, Collection<I> idList) throws RestException {
        if (GeneralUtils.isEmpty(idList)) {
            return;
        }
        DeleteMode deleteModel = deleteMode();
        if (deleteModel == DeleteMode.REMOVE) {
            removeAll(tableKey, idList);
        } else if (deleteModel == DeleteMode.OPERATE) {
            operateAll(tableKey, idList, OperateType.REMOVE);
        } else {
            String tableName = resolveTableName(tableKey);
            if (isBeforeSkip() && isAfterSkip()) {
                deletePartition(tableName, idList);
            } else {
                List<E> entityList = superMapper.findAll(idList);
                if (GeneralUtils.isNotEmpty(entityList)) {
                    deleteAdvice(entityList, () -> deletePartition(tableName, idList));
                }
            }
        }
    }

    /**
     * <code>deleteByLinkId</code>
     * <p>The delete by link id method.</p>
     * @param <L>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param linkId L <p>The link id parameter is <code>L</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L> void deleteByLinkId(L linkId) throws RestException {
        deleteByLinkId(null, linkId);
    }

    /**
     * <code>deleteByLinkId</code>
     * <p>The delete by link id method.</p>
     * @param <L>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableKey {@link io.github.nichetoolkit.rice.RestTableKey} <p>The table key parameter is <code>RestTableKey</code> type.</p>
     * @param linkId   L <p>The link id parameter is <code>L</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L> void deleteByLinkId(RestTableKey<K> tableKey, L linkId) throws RestException {
        deleteByLinkId(tableKey, linkId, null);
    }

    /**
     * <code>deleteByLinkId</code>
     * <p>The delete by link id method.</p>
     * @param <L>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param linkId   L <p>The link id parameter is <code>L</code> type.</p>
     * @param linkName {@link io.github.nichetoolkit.rest.RestKey} <p>The link name parameter is <code>RestKey</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestKey
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L> void deleteByLinkId(L linkId, RestKey<String> linkName) throws RestException {
        deleteByLinkId(null, linkId, linkName);
    }

    /**
     * <code>deleteByLinkId</code>
     * <p>The delete by link id method.</p>
     * @param <L>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableKey {@link io.github.nichetoolkit.rice.RestTableKey} <p>The table key parameter is <code>RestTableKey</code> type.</p>
     * @param linkId   L <p>The link id parameter is <code>L</code> type.</p>
     * @param linkName {@link io.github.nichetoolkit.rest.RestKey} <p>The link name parameter is <code>RestKey</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see io.github.nichetoolkit.rest.RestKey
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L> void deleteByLinkId(RestTableKey<K> tableKey, L linkId, RestKey<String> linkName) throws RestException {
        if (GeneralUtils.isEmpty(linkId)) {
            return;
        }
        DeleteMode deleteModel = deleteMode();
        if (deleteModel == DeleteMode.REMOVE) {
            removeByLinkId(tableKey, linkId, linkName);
        } else if (deleteModel == DeleteMode.OPERATE) {
            operateByLinkId(tableKey, linkId, linkName, OperateType.REMOVE);
        } else if (superMapper instanceof DeleteLinkMapper) {
            String tableName = resolveTableName(tableKey);
            if (isBeforeSkip() && isAfterSkip()) {
                deleteLinkId(tableName, linkId, linkName);
            } else {
                List<E> entityList = findByLinkId(tableName, linkId, linkName);
                if (GeneralUtils.isNotEmpty(entityList)) {
                    if (!isBeforeSkip()) {
                        this.beforeDeleteAll(entityList);
                    }
                    deleteLinkId(tableName, linkId, linkName);
                    if (!isAfterSkip()) {
                        this.afterDeleteAll(entityList);
                    }
                    this.refresh();
                }
            }
        }
    }

    /**
     * <code>deleteAllByLinkIds</code>
     * <p>The delete all by link ids method.</p>
     * @param <L>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param linkIdList {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L> void deleteAllByLinkIds(Collection<L> linkIdList) throws RestException {
        deleteAllByLinkIds(null, linkIdList);
    }

    /**
     * <code>deleteAllByLinkIds</code>
     * <p>The delete all by link ids method.</p>
     * @param <L>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableKey   {@link io.github.nichetoolkit.rice.RestTableKey} <p>The table key parameter is <code>RestTableKey</code> type.</p>
     * @param linkIdList {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see java.util.Collection
     * @see java.lang.SuppressWarnings
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings("Duplicates")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L> void deleteAllByLinkIds(RestTableKey<K> tableKey, Collection<L> linkIdList) throws RestException {
        deleteAllByLinkIds(tableKey, linkIdList, null);
    }

    /**
     * <code>deleteAllByLinkIds</code>
     * <p>The delete all by link ids method.</p>
     * @param <L>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param linkIdList {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @param linkName   {@link io.github.nichetoolkit.rest.RestKey} <p>The link name parameter is <code>RestKey</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestKey
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L> void deleteAllByLinkIds(Collection<L> linkIdList, RestKey<String> linkName) throws RestException {
        deleteAllByLinkIds(null, linkIdList, linkName);
    }

    /**
     * <code>deleteAllByLinkIds</code>
     * <p>The delete all by link ids method.</p>
     * @param <L>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableKey   {@link io.github.nichetoolkit.rice.RestTableKey} <p>The table key parameter is <code>RestTableKey</code> type.</p>
     * @param linkIdList {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @param linkName   {@link io.github.nichetoolkit.rest.RestKey} <p>The link name parameter is <code>RestKey</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestKey
     * @see java.lang.SuppressWarnings
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings("Duplicates")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L> void deleteAllByLinkIds(RestTableKey<K> tableKey, Collection<L> linkIdList, RestKey<String> linkName) throws RestException {
        if (GeneralUtils.isEmpty(linkIdList)) {
            return;
        }
        DeleteMode deleteModel = deleteMode();
        if (deleteModel == DeleteMode.REMOVE) {
            removeAllByLinkIds(tableKey, linkIdList, linkName);
        } else if (deleteModel == DeleteMode.OPERATE) {
            operateAllByLinkIds(tableKey, linkIdList, linkName, OperateType.REMOVE);
        } else if (superMapper instanceof DeleteLinkMapper) {
            String tableName = resolveTableName(tableKey);
            if (isBeforeSkip() && isAfterSkip()) {
                deleteLinkIdPartition(tableName, linkIdList, linkName);
            } else {
                List<E> entityList = findAllByLinkIds(tableName, linkIdList, linkName);
                if (GeneralUtils.isNotEmpty(entityList)) {
                    deleteAdvice(entityList, () -> deleteLinkIdPartition(tableName, linkIdList, linkName));
                }
            }
        }
    }

    @Override
    public M queryById(I id, RestLoad... isLoadArray) throws RestException {
        return queryById(null, id, isLoadArray);
    }

    /**
     * <code>queryById</code>
     * <p>The query by id method.</p>
     * @param id          I <p>The id parameter is <code>I</code> type.</p>
     * @param fickleList  {@link java.util.Collection} <p>The fickle list parameter is <code>Collection</code> type.</p>
     * @param isLoadArray {@link io.github.nichetoolkit.mybatis.load.RestLoad} <p>The is load array parameter is <code>RestLoad</code> type.</p>
     * @return M <p>The query by id return object is <code>M</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.mybatis.load.RestLoad
     * @see io.github.nichetoolkit.rest.RestException
     */
    public M queryById(I id, Collection<RestFickle<?>> fickleList, RestLoad... isLoadArray) throws RestException {
        return queryById(null, id, fickleList, isLoadArray);
    }

    @Override
    public M queryById(RestTableKey<K> tableKey, I id, RestLoad... isLoadArray) throws RestException {
        return queryById(tableKey, id, null, isLoadArray);
    }


    /**
     * <code>queryById</code>
     * <p>The query by id method.</p>
     * @param tableKey    {@link io.github.nichetoolkit.rice.RestTableKey} <p>The table key parameter is <code>RestTableKey</code> type.</p>
     * @param id          I <p>The id parameter is <code>I</code> type.</p>
     * @param fickleList  {@link java.util.Collection} <p>The fickle list parameter is <code>Collection</code> type.</p>
     * @param isLoadArray {@link io.github.nichetoolkit.mybatis.load.RestLoad} <p>The is load array parameter is <code>RestLoad</code> type.</p>
     * @return M <p>The query by id return object is <code>M</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see java.util.Collection
     * @see io.github.nichetoolkit.mybatis.load.RestLoad
     * @see io.github.nichetoolkit.rest.RestException
     */
    public M queryById(RestTableKey<K> tableKey, I id, Collection<RestFickle<?>> fickleList, RestLoad... isLoadArray) throws RestException {
        if (GeneralUtils.isEmpty(id)) {
            return null;
        }
        E entity;
        String tableName = resolveTableName(tableKey);
        RestFickle<?>[] tableFickle = tableFickle(tableName, fickleList);
        if (GeneralUtils.isNotEmpty(isLoadArray) && GeneralUtils.isNotEmpty(tableFickle)
                && isFickleField() && FickleLoadMapper.class.isAssignableFrom(superMapper.getClass())) {
            entity = findByIdFickleLoad(id, tableName, tableFickle, isLoadArray);
        } else if (GeneralUtils.isNotEmpty(tableFickle) && isFickleField() && FindFickleMapper.class.isAssignableFrom(superMapper.getClass())) {
            entity = findByIdFickle(id, tableName, tableFickle);
        } else if (GeneralUtils.isNotEmpty(isLoadArray) && FindLoadMapper.class.isAssignableFrom(superMapper.getClass())) {
            entity = findByIdLoad(id, tableName, isLoadArray);
        } else {
            entity = findById(id, tableName);
        }
        if (GeneralUtils.isEmpty(entity)) {
            return null;
        }
        return modelActuator(entity, isLoadArray);
    }

    @Override
    public List<M> queryAll(Collection<I> idList, RestLoad... isLoadArray) throws RestException {
        return queryAll(null, idList, isLoadArray);
    }

    /**
     * <code>queryAll</code>
     * <p>The query all method.</p>
     * @param idList      {@link java.util.Collection} <p>The id list parameter is <code>Collection</code> type.</p>
     * @param fickleList  {@link java.util.Collection} <p>The fickle list parameter is <code>Collection</code> type.</p>
     * @param isLoadArray {@link io.github.nichetoolkit.mybatis.load.RestLoad} <p>The is load array parameter is <code>RestLoad</code> type.</p>
     * @return {@link java.util.List} <p>The query all return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.mybatis.load.RestLoad
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.RestException
     */
    public List<M> queryAll(Collection<I> idList, Collection<RestFickle<?>> fickleList, RestLoad... isLoadArray) throws RestException {
        return queryAll(null, idList, fickleList, isLoadArray);
    }

    @Override
    public List<M> queryAll(RestTableKey<K> tableKey, Collection<I> idList, RestLoad... isLoadArray) throws RestException {
        return queryAll(tableKey, idList, null, isLoadArray);
    }

    /**
     * <code>queryAll</code>
     * <p>The query all method.</p>
     * @param tableKey    {@link io.github.nichetoolkit.rice.RestTableKey} <p>The table key parameter is <code>RestTableKey</code> type.</p>
     * @param idList      {@link java.util.Collection} <p>The id list parameter is <code>Collection</code> type.</p>
     * @param fickleList  {@link java.util.Collection} <p>The fickle list parameter is <code>Collection</code> type.</p>
     * @param isLoadArray {@link io.github.nichetoolkit.mybatis.load.RestLoad} <p>The is load array parameter is <code>RestLoad</code> type.</p>
     * @return {@link java.util.List} <p>The query all return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see java.util.Collection
     * @see io.github.nichetoolkit.mybatis.load.RestLoad
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.RestException
     */
    public List<M> queryAll(RestTableKey<K> tableKey, Collection<I> idList, Collection<RestFickle<?>> fickleList, RestLoad... isLoadArray) throws RestException {
        if (GeneralUtils.isEmpty(idList)) {
            return Collections.emptyList();
        }
        List<E> entityList;
        String tableName = resolveTableName(tableKey);
        RestFickle<?>[] tableFickle = tableFickle(tableName, fickleList);
        if (GeneralUtils.isNotEmpty(isLoadArray) && GeneralUtils.isNotEmpty(tableFickle)
                && isFickleField() && FickleLoadMapper.class.isAssignableFrom(superMapper.getClass())) {
            entityList = findAllFickleLoad(idList, tableName, tableFickle, isLoadArray);
        } else if (GeneralUtils.isNotEmpty(tableFickle) && isFickleField() && FindFickleMapper.class.isAssignableFrom(superMapper.getClass())) {
            entityList = findAllFickle(idList, tableName, tableFickle);
        } else if (GeneralUtils.isNotEmpty(isLoadArray) && FindLoadMapper.class.isAssignableFrom(superMapper.getClass())) {
            entityList = findAllLoad(idList, tableName, isLoadArray);
        } else {
            entityList = findAll(idList, tableName);
        }
        return modelActuator(entityList, null, isLoadArray);
    }

    /**
     * <code>queryAllWithFilter</code>
     * <p>The query all with filter method.</p>
     * @param filter F <p>The filter parameter is <code>F</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.RestPage} <p>The query all with filter return object is <code>RestPage</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestPage
     * @see io.github.nichetoolkit.rest.RestException
     */
    public RestPage<M> queryAllWithFilter(F filter) throws RestException {
        optionalQueryFilter(filter);
        queryFilterCache.set(filter);
        String whereSql = queryWhereSql(filter);
        RestLoad[] loadArray = findLoadArray(filter);
        RestLoad[] isLoadArray = queryLoadArray(filter);
        String[] fieldArray = fieldArray(filter);
        RestFickle<?>[] fickleArray = fickleArray(filter);
        RestTableKey<K> tableKey = tableKey(filter);
        String tableName = resolveTableName(tableKey);
        RestFickle<?>[] tableFickle = tableFickle(tableName, fickleArray);
        PageResult<E, I> pageResult;
        if (GeneralUtils.isNotEmpty(loadArray) && GeneralUtils.isNotEmpty(tableFickle)
                && isFickleField() && FickleFilterMapper.class.isAssignableFrom(superMapper.getClass())) {
            pageResult = findAllByFickleLoadWhere(whereSql, tableName, filter, tableFickle, loadArray);
        } else if (GeneralUtils.isNotEmpty(tableFickle) && isFickleField()
                && FilterFickleMapper.class.isAssignableFrom(superMapper.getClass())) {
            pageResult = findAllByFickleWhere(whereSql, tableName, filter, tableFickle);
        } else if (GeneralUtils.isNotEmpty(loadArray) && FilterLoadMapper.class.isAssignableFrom(superMapper.getClass())) {
            pageResult = findAllByLoadWhere(whereSql, tableName, filter, loadArray);
        } else if (FindFilterMapper.class.isAssignableFrom(superMapper.getClass())) {
            pageResult = findAllByFilterWhere(whereSql, tableName, filter);
        } else {
            Page<E> page = filter.toPage();
            List<E> entityList = findAllByWhere(whereSql, tableName);
            pageResult = PageResult.builder(page, entityList);
        }
        List<M> modelList = modelActuator(pageResult.getEntities(), null, isLoadArray);
        queryFilterCache.remove();
        return RestPage.result(modelList, pageResult.getPage());
    }

    /**
     * <code>queryByLinkId</code>
     * <p>The query by link id method.</p>
     * @param <L>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param linkId      L <p>The link id parameter is <code>L</code> type.</p>
     * @param isLoadArray {@link io.github.nichetoolkit.mybatis.load.RestLoad} <p>The is load array parameter is <code>RestLoad</code> type.</p>
     * @return {@link java.util.List} <p>The query by link id return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.mybatis.load.RestLoad
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.RestException
     */
    public <L> List<M> queryByLinkId(L linkId, RestLoad... isLoadArray) throws RestException {
        return queryByLinkId(null, linkId, isLoadArray);
    }

    /**
     * <code>queryByLinkId</code>
     * <p>The query by link id method.</p>
     * @param <L>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param linkId      L <p>The link id parameter is <code>L</code> type.</p>
     * @param fickleList  {@link java.util.Collection} <p>The fickle list parameter is <code>Collection</code> type.</p>
     * @param isLoadArray {@link io.github.nichetoolkit.mybatis.load.RestLoad} <p>The is load array parameter is <code>RestLoad</code> type.</p>
     * @return {@link java.util.List} <p>The query by link id return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.mybatis.load.RestLoad
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.RestException
     */
    public <L> List<M> queryByLinkId(L linkId, Collection<RestFickle<?>> fickleList, RestLoad... isLoadArray) throws RestException {
        return queryByLinkId(null, linkId, null, fickleList, isLoadArray);
    }

    /**
     * <code>queryByLinkId</code>
     * <p>The query by link id method.</p>
     * @param <L>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableKey    {@link io.github.nichetoolkit.rice.RestTableKey} <p>The table key parameter is <code>RestTableKey</code> type.</p>
     * @param linkId      L <p>The link id parameter is <code>L</code> type.</p>
     * @param isLoadArray {@link io.github.nichetoolkit.mybatis.load.RestLoad} <p>The is load array parameter is <code>RestLoad</code> type.</p>
     * @return {@link java.util.List} <p>The query by link id return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see io.github.nichetoolkit.mybatis.load.RestLoad
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.RestException
     */
    public <L> List<M> queryByLinkId(RestTableKey<K> tableKey, L linkId, RestLoad... isLoadArray) throws RestException {
        return queryByLinkId(tableKey, linkId, null, null, isLoadArray);
    }

    /**
     * <code>queryByLinkId</code>
     * <p>The query by link id method.</p>
     * @param <L>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableKey    {@link io.github.nichetoolkit.rice.RestTableKey} <p>The table key parameter is <code>RestTableKey</code> type.</p>
     * @param linkId      L <p>The link id parameter is <code>L</code> type.</p>
     * @param fickleList  {@link java.util.Collection} <p>The fickle list parameter is <code>Collection</code> type.</p>
     * @param isLoadArray {@link io.github.nichetoolkit.mybatis.load.RestLoad} <p>The is load array parameter is <code>RestLoad</code> type.</p>
     * @return {@link java.util.List} <p>The query by link id return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see java.util.Collection
     * @see io.github.nichetoolkit.mybatis.load.RestLoad
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.RestException
     */
    public <L> List<M> queryByLinkId(RestTableKey<K> tableKey, L linkId, Collection<RestFickle<?>> fickleList, RestLoad... isLoadArray) throws RestException {
        return queryByLinkId(tableKey, linkId, null, fickleList, isLoadArray);
    }

    /**
     * <code>queryByLinkId</code>
     * <p>The query by link id method.</p>
     * @param <L>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param linkId      L <p>The link id parameter is <code>L</code> type.</p>
     * @param linkName    {@link io.github.nichetoolkit.rest.RestKey} <p>The link name parameter is <code>RestKey</code> type.</p>
     * @param isLoadArray {@link io.github.nichetoolkit.mybatis.load.RestLoad} <p>The is load array parameter is <code>RestLoad</code> type.</p>
     * @return {@link java.util.List} <p>The query by link id return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestKey
     * @see io.github.nichetoolkit.mybatis.load.RestLoad
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.RestException
     */
    public <L> List<M> queryByLinkId(L linkId, RestKey<String> linkName, RestLoad... isLoadArray) throws RestException {
        return queryByLinkId(null, linkId, linkName, isLoadArray);
    }

    /**
     * <code>queryByLinkId</code>
     * <p>The query by link id method.</p>
     * @param <L>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param linkId      L <p>The link id parameter is <code>L</code> type.</p>
     * @param linkName    {@link io.github.nichetoolkit.rest.RestKey} <p>The link name parameter is <code>RestKey</code> type.</p>
     * @param fickleList  {@link java.util.Collection} <p>The fickle list parameter is <code>Collection</code> type.</p>
     * @param isLoadArray {@link io.github.nichetoolkit.mybatis.load.RestLoad} <p>The is load array parameter is <code>RestLoad</code> type.</p>
     * @return {@link java.util.List} <p>The query by link id return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestKey
     * @see java.util.Collection
     * @see io.github.nichetoolkit.mybatis.load.RestLoad
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.RestException
     */
    public <L> List<M> queryByLinkId(L linkId, RestKey<String> linkName, Collection<RestFickle<?>> fickleList, RestLoad... isLoadArray) throws RestException {
        return queryByLinkId(null, linkId, linkName, fickleList, isLoadArray);
    }

    /**
     * <code>queryByLinkId</code>
     * <p>The query by link id method.</p>
     * @param <L>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableKey    {@link io.github.nichetoolkit.rice.RestTableKey} <p>The table key parameter is <code>RestTableKey</code> type.</p>
     * @param linkId      L <p>The link id parameter is <code>L</code> type.</p>
     * @param linkName    {@link io.github.nichetoolkit.rest.RestKey} <p>The link name parameter is <code>RestKey</code> type.</p>
     * @param isLoadArray {@link io.github.nichetoolkit.mybatis.load.RestLoad} <p>The is load array parameter is <code>RestLoad</code> type.</p>
     * @return {@link java.util.List} <p>The query by link id return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see io.github.nichetoolkit.rest.RestKey
     * @see io.github.nichetoolkit.mybatis.load.RestLoad
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.RestException
     */
    public <L> List<M> queryByLinkId(RestTableKey<K> tableKey, L linkId, RestKey<String> linkName, RestLoad... isLoadArray) throws RestException {
        return queryByLinkId(tableKey, linkId, linkName, null, isLoadArray);
    }

    /**
     * <code>queryByLinkId</code>
     * <p>The query by link id method.</p>
     * @param <L>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableKey    {@link io.github.nichetoolkit.rice.RestTableKey} <p>The table key parameter is <code>RestTableKey</code> type.</p>
     * @param linkId      L <p>The link id parameter is <code>L</code> type.</p>
     * @param linkName    {@link io.github.nichetoolkit.rest.RestKey} <p>The link name parameter is <code>RestKey</code> type.</p>
     * @param fickleList  {@link java.util.Collection} <p>The fickle list parameter is <code>Collection</code> type.</p>
     * @param isLoadArray {@link io.github.nichetoolkit.mybatis.load.RestLoad} <p>The is load array parameter is <code>RestLoad</code> type.</p>
     * @return {@link java.util.List} <p>The query by link id return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see io.github.nichetoolkit.rest.RestKey
     * @see java.util.Collection
     * @see io.github.nichetoolkit.mybatis.load.RestLoad
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.RestException
     */
    public <L> List<M> queryByLinkId(RestTableKey<K> tableKey, L linkId, RestKey<String> linkName, Collection<RestFickle<?>> fickleList, RestLoad... isLoadArray) throws RestException {
        if (GeneralUtils.isEmpty(linkId)) {
            return Collections.emptyList();
        }
        List<E> entityList;
        String tableName = resolveTableName(tableKey);
        RestFickle<?>[] tableFickle = tableFickle(tableName, fickleList);
        if (GeneralUtils.isNotEmpty(isLoadArray) && GeneralUtils.isNotEmpty(tableFickle)
                && isFickleField() && FickleLinkMapper.class.isAssignableFrom(superMapper.getClass())) {
            entityList = findByLinkIdFickleLoad(tableName, linkId, linkName, tableFickle, isLoadArray);
        } else if (GeneralUtils.isNotEmpty(tableFickle) && isFickleField() && LinkFickleMapper.class.isAssignableFrom(superMapper.getClass())) {
            entityList = findByLinkIdFickle(tableName, linkId, linkName, tableFickle);
        } else if (GeneralUtils.isNotEmpty(isLoadArray) && LinkLoadMapper.class.isAssignableFrom(superMapper.getClass())) {
            entityList = findByLinkIdLoad(tableName, linkId, linkName, isLoadArray);
        } else {
            entityList = findByLinkId(tableName, linkId, linkName);
        }
        if (GeneralUtils.isEmpty(entityList)) {
            return Collections.emptyList();
        }
        return modelActuator(entityList, null, isLoadArray);
    }

    /**
     * <code>queryAllByLinkIds</code>
     * <p>The query all by link ids method.</p>
     * @param <L>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param linkIdList  {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @param isLoadArray {@link io.github.nichetoolkit.mybatis.load.RestLoad} <p>The is load array parameter is <code>RestLoad</code> type.</p>
     * @return {@link java.util.List} <p>The query all by link ids return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.mybatis.load.RestLoad
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.RestException
     */
    public <L> List<M> queryAllByLinkIds(Collection<L> linkIdList, RestLoad... isLoadArray) throws RestException {
        return queryAllByLinkIds(null, linkIdList, isLoadArray);
    }

    /**
     * <code>queryAllByLinkIds</code>
     * <p>The query all by link ids method.</p>
     * @param <L>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param linkIdList  {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @param fickleList  {@link java.util.Collection} <p>The fickle list parameter is <code>Collection</code> type.</p>
     * @param isLoadArray {@link io.github.nichetoolkit.mybatis.load.RestLoad} <p>The is load array parameter is <code>RestLoad</code> type.</p>
     * @return {@link java.util.List} <p>The query all by link ids return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.mybatis.load.RestLoad
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.RestException
     */
    public <L> List<M> queryAllByLinkIds(Collection<L> linkIdList, Collection<RestFickle<?>> fickleList, RestLoad... isLoadArray) throws RestException {
        return queryAllByLinkIds(null, linkIdList, fickleList, isLoadArray);
    }

    /**
     * <code>queryAllByLinkIds</code>
     * <p>The query all by link ids method.</p>
     * @param <L>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableKey    {@link io.github.nichetoolkit.rice.RestTableKey} <p>The table key parameter is <code>RestTableKey</code> type.</p>
     * @param linkIdList  {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @param isLoadArray {@link io.github.nichetoolkit.mybatis.load.RestLoad} <p>The is load array parameter is <code>RestLoad</code> type.</p>
     * @return {@link java.util.List} <p>The query all by link ids return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see java.util.Collection
     * @see io.github.nichetoolkit.mybatis.load.RestLoad
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.RestException
     */
    public <L> List<M> queryAllByLinkIds(RestTableKey<K> tableKey, Collection<L> linkIdList, RestLoad... isLoadArray) throws RestException {
        return queryAllByLinkIds(tableKey, linkIdList, null, null, isLoadArray);
    }

    /**
     * <code>queryAllByLinkIds</code>
     * <p>The query all by link ids method.</p>
     * @param <L>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableKey    {@link io.github.nichetoolkit.rice.RestTableKey} <p>The table key parameter is <code>RestTableKey</code> type.</p>
     * @param linkIdList  {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @param fickleList  {@link java.util.Collection} <p>The fickle list parameter is <code>Collection</code> type.</p>
     * @param isLoadArray {@link io.github.nichetoolkit.mybatis.load.RestLoad} <p>The is load array parameter is <code>RestLoad</code> type.</p>
     * @return {@link java.util.List} <p>The query all by link ids return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see java.util.Collection
     * @see io.github.nichetoolkit.mybatis.load.RestLoad
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.RestException
     */
    public <L> List<M> queryAllByLinkIds(RestTableKey<K> tableKey, Collection<L> linkIdList, Collection<RestFickle<?>> fickleList, RestLoad... isLoadArray) throws RestException {
        return queryAllByLinkIds(tableKey, linkIdList, null, fickleList, isLoadArray);
    }

    /**
     * <code>queryAllByLinkIds</code>
     * <p>The query all by link ids method.</p>
     * @param <L>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param linkIdList  {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @param linkName    {@link io.github.nichetoolkit.rest.RestKey} <p>The link name parameter is <code>RestKey</code> type.</p>
     * @param isLoadArray {@link io.github.nichetoolkit.mybatis.load.RestLoad} <p>The is load array parameter is <code>RestLoad</code> type.</p>
     * @return {@link java.util.List} <p>The query all by link ids return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestKey
     * @see io.github.nichetoolkit.mybatis.load.RestLoad
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.RestException
     */
    public <L> List<M> queryAllByLinkIds(Collection<L> linkIdList, RestKey<String> linkName, RestLoad... isLoadArray) throws RestException {
        return queryAllByLinkIds(null, linkIdList, linkName, isLoadArray);
    }

    /**
     * <code>queryAllByLinkIds</code>
     * <p>The query all by link ids method.</p>
     * @param <L>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param linkIdList  {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @param linkName    {@link io.github.nichetoolkit.rest.RestKey} <p>The link name parameter is <code>RestKey</code> type.</p>
     * @param fickleList  {@link java.util.Collection} <p>The fickle list parameter is <code>Collection</code> type.</p>
     * @param isLoadArray {@link io.github.nichetoolkit.mybatis.load.RestLoad} <p>The is load array parameter is <code>RestLoad</code> type.</p>
     * @return {@link java.util.List} <p>The query all by link ids return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestKey
     * @see io.github.nichetoolkit.mybatis.load.RestLoad
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.RestException
     */
    public <L> List<M> queryAllByLinkIds(Collection<L> linkIdList, RestKey<String> linkName, Collection<RestFickle<?>> fickleList, RestLoad... isLoadArray) throws RestException {
        return queryAllByLinkIds(null, linkIdList, linkName, fickleList, isLoadArray);
    }

    /**
     * <code>queryAllByLinkIds</code>
     * <p>The query all by link ids method.</p>
     * @param <L>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableKey    {@link io.github.nichetoolkit.rice.RestTableKey} <p>The table key parameter is <code>RestTableKey</code> type.</p>
     * @param linkIdList  {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @param linkName    {@link io.github.nichetoolkit.rest.RestKey} <p>The link name parameter is <code>RestKey</code> type.</p>
     * @param isLoadArray {@link io.github.nichetoolkit.mybatis.load.RestLoad} <p>The is load array parameter is <code>RestLoad</code> type.</p>
     * @return {@link java.util.List} <p>The query all by link ids return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestKey
     * @see io.github.nichetoolkit.mybatis.load.RestLoad
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.RestException
     */
    public <L> List<M> queryAllByLinkIds(RestTableKey<K> tableKey, Collection<L> linkIdList, RestKey<String> linkName, RestLoad... isLoadArray) throws RestException {
        return queryAllByLinkIds(tableKey, linkIdList, linkName, null, isLoadArray);
    }

    /**
     * <code>queryAllByLinkIds</code>
     * <p>The query all by link ids method.</p>
     * @param <L>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableKey    {@link io.github.nichetoolkit.rice.RestTableKey} <p>The table key parameter is <code>RestTableKey</code> type.</p>
     * @param linkIdList  {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @param linkName    {@link io.github.nichetoolkit.rest.RestKey} <p>The link name parameter is <code>RestKey</code> type.</p>
     * @param fickleList  {@link java.util.Collection} <p>The fickle list parameter is <code>Collection</code> type.</p>
     * @param isLoadArray {@link io.github.nichetoolkit.mybatis.load.RestLoad} <p>The is load array parameter is <code>RestLoad</code> type.</p>
     * @return {@link java.util.List} <p>The query all by link ids return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestKey
     * @see io.github.nichetoolkit.mybatis.load.RestLoad
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.RestException
     */
    public <L> List<M> queryAllByLinkIds(RestTableKey<K> tableKey, Collection<L> linkIdList, RestKey<String> linkName, Collection<RestFickle<?>> fickleList, RestLoad... isLoadArray) throws RestException {
        if (GeneralUtils.isEmpty(linkIdList)) {
            return Collections.emptyList();
        }
        List<E> entityList;
        String tableName = resolveTableName(tableKey);
        RestFickle<?>[] tableFickle = tableFickle(tableName, fickleList);
        if (GeneralUtils.isNotEmpty(isLoadArray) && GeneralUtils.isNotEmpty(tableFickle)
                && isFickleField() && FickleLinkMapper.class.isAssignableFrom(superMapper.getClass())) {
            entityList = findAllByLinkIdsFickleLoad(tableName, linkIdList, linkName, tableFickle, isLoadArray);
        } else if (GeneralUtils.isNotEmpty(tableFickle) && isFickleField() && LinkFickleMapper.class.isAssignableFrom(superMapper.getClass())) {
            entityList = findAllByLinkIdsFickle(tableName, linkIdList, linkName, tableFickle);
        } else if (GeneralUtils.isNotEmpty(isLoadArray) && LinkLoadMapper.class.isAssignableFrom(superMapper.getClass())) {
            entityList = findAllByLinkIdsLoad(tableName, linkIdList, linkName, isLoadArray);
        } else {
            entityList = findAllByLinkIds(tableName, linkIdList, linkName);
        }
        return modelActuator(entityList, null, isLoadArray);
    }


    /**
     * <code>deleteAllWithFilter</code>
     * <p>The delete all with filter method.</p>
     * @param filter F <p>The filter parameter is <code>F</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.SuppressWarnings
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void deleteAllWithFilter(F filter) throws RestException {
        DeleteMode deleteModel = deleteMode();
        if (deleteModel == DeleteMode.REMOVE) {
            removeAllWithFilter(filter);
        } else if (deleteModel == DeleteMode.OPERATE) {
            filter.setOperate(OperateType.DELETE);
            operateAllWithFilter(filter);
        } else {
            optionalDeleteFilter(filter);
            String whereSql = deleteWhereSql(filter);
            RestTableKey<K> tableKey = tableKey(filter);
            String tableName = resolveTableName(tableKey);
            if (GeneralUtils.isNotEmpty(whereSql)) {
                if (DeleteFilterMapper.class.isAssignableFrom(superMapper.getClass())) {
                    DeleteFilterMapper<E, F, I, K> filterMapper = (DeleteFilterMapper<E, F, I, K>) superMapper;
                    Method findMethod = null;
                    Method deleteMethod = null;
                    try {
                        findMethod = filterMapper.getClass().getMethod("findAllByFilterWhere", String.class, Object.class);
                        deleteMethod = filterMapper.getClass().getMethod("deleteAllByFilterWhere", String.class, Object.class);
                    } catch (NoSuchMethodException ignored) {
                    }
                    Method findAllByWhereMethod = findMethod;
                    Method deleteAllByWhereMethod = deleteMethod;
                    if (deleteAllByWhereMethod != null && !deleteAllByWhereMethod.isDefault()) {
                        if (isBeforeSkip() && isAfterSkip()) {
                            if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tableName)) {
                                filterMapper.deleteDynamicAllByFilterWhere(tableName, whereSql, filter);
                            } else {
                                filterMapper.deleteAllByFilterWhere(whereSql, filter);
                            }
                        } else {
                            String queryWhereSql = queryWhereSql(filter);
                            List<E> entityList;
                            if (!findAllByWhereMethod.isDefault()) {
                                entityList = filterMapper.findAllByFilterWhere(queryWhereSql, filter);
                            } else {
                                entityList = superMapper.findAllByWhere(queryWhereSql);
                            }
                            if (GeneralUtils.isNotEmpty(entityList)) {
                                deleteAdvice(entityList, () -> {
                                    if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tableName)) {
                                        filterMapper.deleteDynamicAllByFilterWhere(tableName, whereSql, filter);
                                    } else {
                                        filterMapper.deleteAllByFilterWhere(whereSql, filter);
                                    }
                                });
                            }
                        }
                    } else {
                        deleteAllByWhere(whereSql, tableName, filter);
                    }
                } else {
                    deleteAllByWhere(whereSql, tableName, filter);
                }
            }
        }
    }

    /**
     * <code>removeAllWithFilter</code>
     * <p>The remove all with filter method.</p>
     * @param filter F <p>The filter parameter is <code>F</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.SuppressWarnings
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void removeAllWithFilter(F filter) throws RestException {
        optionalRemoveFilter(filter);
        String removeWhereSql = removeWhereSql(filter);
        RestTableKey<K> tableKey = tableKey(filter);
        String tableName = resolveTableName(tableKey);
        if (GeneralUtils.isNotEmpty(removeWhereSql)) {
            Object logic = markOfLogic();
            if (RemoveFilterMapper.class.isAssignableFrom(superMapper.getClass())) {
                RemoveFilterMapper<E, F, I, K> filterMapper = (RemoveFilterMapper<E, F, I, K>) superMapper;
                Method findMethod = null;
                Method removeMethod = null;
                try {
                    findMethod = filterMapper.getClass().getMethod("findAllByFilterWhere", String.class, Object.class);
                    removeMethod = filterMapper.getClass().getMethod("removeAllByFilterWhere", String.class, Object.class, Object.class);
                } catch (NoSuchMethodException ignored) {
                }
                Method findAllByWhereMethod = findMethod;
                Method removeAllByWhereMethod = removeMethod;
                if (removeAllByWhereMethod != null && !removeAllByWhereMethod.isDefault()) {
                    if (isBeforeSkip() && isAfterSkip()) {
                        if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tableName)) {
                            filterMapper.removeDynamicAllByFilterWhere(tableName, removeWhereSql, filter, logic);
                        } else {
                            filterMapper.removeAllByFilterWhere(removeWhereSql, filter, logic);
                        }
                    } else {
                        String queryWhereSql = queryWhereSql(filter);
                        List<E> entityList;
                        if (!findAllByWhereMethod.isDefault()) {
                            entityList = filterMapper.findAllByFilterWhere(queryWhereSql, filter);
                        } else {
                            entityList = superMapper.findAllByWhere(queryWhereSql);
                        }
                        if (GeneralUtils.isNotEmpty(entityList)) {
                            removeAdvice(entityList, logic, sign -> {
                                if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tableName)) {
                                    filterMapper.removeDynamicAllByFilterWhere(tableName, removeWhereSql, filter, sign);
                                } else {
                                    filterMapper.removeAllByFilterWhere(removeWhereSql, filter, sign);
                                }
                            });
                        }
                    }
                } else {
                    removeAllByWhere(removeWhereSql, tableName, filter);
                }
            } else {
                removeAllByWhere(removeWhereSql, tableName, filter);
            }

        }
    }

    /**
     * <code>operateAllWithFilter</code>
     * <p>The operate all with filter method.</p>
     * @param filter F <p>The filter parameter is <code>F</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.SuppressWarnings
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void operateAllWithFilter(F filter) throws RestException {
        optionalOperateFilter(filter);
        String operateWhereSql = operateWhereSql(filter);
        RestTableKey<K> tableKey = tableKey(filter);
        String tableName = resolveTableName(tableKey);
        if (GeneralUtils.isNotEmpty(operateWhereSql)) {
            if (OperateFilterMapper.class.isAssignableFrom(superMapper.getClass())) {
                OperateFilterMapper<E, F, I, K> filterMapper = (OperateFilterMapper<E, F, I, K>) superMapper;
                Method findMethod = null;
                Method operateMethod = null;
                try {
                    findMethod = filterMapper.getClass().getMethod("findAllByFilterWhere", String.class, Object.class);
                    operateMethod = filterMapper.getClass().getMethod("operateAllByFilterWhere", String.class, Object.class, Integer.class);
                } catch (NoSuchMethodException ignored) {
                }
                Method findAllByWhereMethod = findMethod;
                Method operateAllByWhereMethod = operateMethod;
                if (operateAllByWhereMethod != null && !operateAllByWhereMethod.isDefault()) {
                    OperateType operateType = GeneralUtils.isNotEmpty(filter.getOperate()) ? filter.getOperate() : OperateType.REMOVE;
                    if (isBeforeSkip() && isAfterSkip()) {
                        if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tableName)) {
                            filterMapper.operateDynamicAllByFilterWhere(tableName, operateWhereSql, filter, operateType.getKey());
                        } else {
                            filterMapper.operateAllByFilterWhere(operateWhereSql, filter, operateType.getKey());
                        }
                    } else {
                        String queryWhereSql = queryWhereSql(filter);
                        List<E> entityList;
                        if (!findAllByWhereMethod.isDefault()) {
                            entityList = filterMapper.findAllByFilterWhere(queryWhereSql, filter);
                        } else {
                            entityList = superMapper.findAllByWhere(queryWhereSql);
                        }
                        if (GeneralUtils.isNotEmpty(entityList)) {
                            operateAdvice(entityList, operateType, operate -> {
                                if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tableName)) {
                                    filterMapper.operateDynamicAllByFilterWhere(tableName, operateWhereSql, filter, operate.getKey());
                                } else {
                                    filterMapper.operateAllByFilterWhere(operateWhereSql, filter, operate.getKey());
                                }
                            });
                        }
                    }
                } else {
                    operateAllByWhere(operateWhereSql, tableName, filter);
                }
            } else {
                operateAllByWhere(operateWhereSql, tableName, filter);
            }
        }
    }

    /**
     * <code>alertAllWithFilter</code>
     * <p>The alert all with filter method.</p>
     * @param <S>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param filter F <p>The filter parameter is <code>F</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.SuppressWarnings
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <S> void alertAllWithFilter(F filter) throws RestException {
        optionalAlertFilter(filter);
        boolean isPresentFilter = AlertFilter.class.isAssignableFrom(filter.getClass());
        String messageOfFilter = "The 'alertAllWithFilter' method is invoked error, the filter must extends 'AlertFilter'.";
        OptionalUtils.ofFalse(isPresentFilter, messageOfFilter, "alertAllWithFilter", log, UnsupportedErrorException::new);
        assert filter instanceof AlertFilter;
        AlertFilter<S> alertFilter = (AlertFilter<S>) filter;
        if (GeneralUtils.isEmpty(alertFilter.getState())) {
            return;
        }
        String alertWhereSql = alertWhereSql(filter);
        RestTableKey<K> tableKey = tableKey(filter);
        String tableName = resolveTableName(tableKey);
        S state = alertFilter.getState();
        RestKey<String> stateName = alertFilter.getStateName();
        if (GeneralUtils.isNotEmpty(alertWhereSql)) {
            if (AlertFilterMapper.class.isAssignableFrom(superMapper.getClass())) {
                AlertFilterMapper<E, F, S, I, K> filterMapper = (AlertFilterMapper<E, F, S, I, K>) superMapper;
                Method findMethod = null;
                Method alertMethod = null;
                try {
                    findMethod = filterMapper.getClass().getMethod("findAllByFilterWhere", String.class, Object.class);
                    alertMethod = filterMapper.getClass().getMethod("alertAllByFilterWhere", String.class, Object.class, Object.class);
                } catch (NoSuchMethodException ignored) {
                }
                Method findAllByWhereMethod = findMethod;
                Method alertAllByWhereMethod = alertMethod;
                if (alertAllByWhereMethod != null && !alertAllByWhereMethod.isDefault()) {
                    if (isBeforeSkip() && isAfterSkip()) {
                        alertFilterWhere(tableName, alertWhereSql, filter, state, stateName);
                    } else {
                        String queryWhereSql = queryWhereSql(filter);
                        List<E> entityList;
                        if (!findAllByWhereMethod.isDefault()) {
                            entityList = filterMapper.findAllByFilterWhere(queryWhereSql, filter);
                        } else {
                            entityList = superMapper.findAllByWhere(queryWhereSql);
                        }
                        if (GeneralUtils.isNotEmpty(entityList)) {
                            alertAdvice(entityList, state, alertState -> alertFilterWhere(tableName, alertWhereSql, filter, alertState, stateName));
                        }
                    }
                } else {
                    alertAllByWhere(alertWhereSql, tableName, filter, state, stateName);
                }
            } else {
                alertAllByWhere(alertWhereSql, tableName, filter, state, stateName);
            }
        }
    }

    @Override
    public M mutateEntity(E model, RestLoad... isLoadArray) throws RestException {
        return modelActuator(model, isLoadArray);
    }

    @Override
    public List<M> mutateEntityList(List<E> entityList, ConsumerActuator<E> actuator, RestLoad... isLoadArray) throws RestException {
        return modelActuator(entityList, actuator, isLoadArray);
    }

    @Override
    public E mutateModel(M model, Object... idArray) throws RestException {
        return entityActuator(model, idArray);
    }

    @Override
    public List<E> mutateModelList(List<M> modelList, ConsumerActuator<M> actuator, Object... idArray) throws RestException {
        return entityActuator(modelList, actuator, idArray);
    }

}
