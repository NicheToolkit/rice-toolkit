package io.github.nichetoolkit.rice.service;

import com.github.pagehelper.Page;
import io.github.nichetoolkit.mybatis.fickle.RestFickle;
import io.github.nichetoolkit.mybatis.load.RestLoad;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestKey;
import io.github.nichetoolkit.rest.actuator.*;
import io.github.nichetoolkit.rest.error.data.DataQueryException;
import io.github.nichetoolkit.rest.error.natives.UnsupportedErrorException;
import io.github.nichetoolkit.rest.error.often.IdentityNullException;
import io.github.nichetoolkit.rest.helper.PartitionHelper;
import io.github.nichetoolkit.rest.stream.RestStream;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.util.OptionalUtils;
import io.github.nichetoolkit.rice.*;
import io.github.nichetoolkit.rice.advice.*;
import io.github.nichetoolkit.rice.enums.DeleteMode;
import io.github.nichetoolkit.rice.enums.OperateType;
import io.github.nichetoolkit.rice.enums.LogicMode;
import io.github.nichetoolkit.rice.enums.SaveType;
import io.github.nichetoolkit.rice.error.table.TableNameIsNullException;
import io.github.nichetoolkit.rice.filter.IdFilter;
import io.github.nichetoolkit.rice.helper.MEBuilderHelper;
import io.github.nichetoolkit.rice.mapper.*;
import io.github.nichetoolkit.rice.mapper.filter.AlertFilterMapper;
import io.github.nichetoolkit.rice.mapper.filter.FindFilterMapper;
import io.github.nichetoolkit.rice.mapper.natives.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;

import java.lang.reflect.Method;
import java.util.*;

/**
 * <code>SuperAdvice</code>
 * <p>The super advice class.</p>
 * @param <M> {@link io.github.nichetoolkit.rice.RestId} <p>The generic parameter is <code>RestId</code> type.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.RestId} <p>The generic parameter is <code>RestId</code> type.</p>
 * @param <F> {@link io.github.nichetoolkit.rice.filter.IdFilter} <p>The generic parameter is <code>IdFilter</code> type.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @param <K> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestId
 * @see io.github.nichetoolkit.rice.filter.IdFilter
 * @see io.github.nichetoolkit.rice.service.OptionalService
 * @see io.github.nichetoolkit.rice.advice.FilterAdvice
 * @see io.github.nichetoolkit.rice.advice.TableNameAdvice
 * @see io.github.nichetoolkit.rice.advice.SaveAdvice
 * @see io.github.nichetoolkit.rice.advice.AlertAdvice
 * @see io.github.nichetoolkit.rice.advice.OperateAdvice
 * @see io.github.nichetoolkit.rice.advice.DeleteAdvice
 * @see io.github.nichetoolkit.rice.advice.RemoveAdvice
 * @see io.github.nichetoolkit.rice.advice.MutateAdvice
 * @see lombok.extern.slf4j.Slf4j
 * @since Jdk1.8
 */
@Slf4j
abstract class SuperAdvice<M extends RestId<I>, E extends RestId<I>, F extends IdFilter<I, K>, I, K> implements OptionalService<M, F, I, K>, FilterAdvice<F, I, K>, TableNameAdvice<M, I, K>,
        SaveAdvice<M, I>, AlertAdvice<E, I>, OperateAdvice<E, I>, DeleteAdvice<E, I>, RemoveAdvice<E, I>, MutateAdvice<M, E, I> {

    /**
     * <code>queryFilterCache</code>
     * {@link java.lang.ThreadLocal} <p>The <code>queryFilterCache</code> field.</p>
     * @see java.lang.ThreadLocal
     */
    protected final ThreadLocal<F> queryFilterCache = new ThreadLocal<>();

    /**
     * <code>tableNameCaches</code>
     * {@link java.lang.ThreadLocal} <p>The <code>tableNameCaches</code> field.</p>
     * @see java.lang.ThreadLocal
     */
    protected final ThreadLocal<Map<K, String>> tableNameCaches = new ThreadLocal<>();

    /**
     * <code>createActuator</code>
     * {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>The <code>createActuator</code> field.</p>
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     */
    protected BiConsumerActuator<RestTableKey<K>, M> createActuator;

    /**
     * <code>updateActuator</code>
     * {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>The <code>updateActuator</code> field.</p>
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     */
    protected BiConsumerActuator<RestTableKey<K>, M> updateActuator;

    /**
     * <code>superMapper</code>
     * {@link io.github.nichetoolkit.rice.mapper.SuperMapper} <p>The <code>superMapper</code> field.</p>
     * @see io.github.nichetoolkit.rice.mapper.SuperMapper
     */
    protected SuperMapper<E, I> superMapper;

    /**
     * <code>tableMapper</code>
     * {@link io.github.nichetoolkit.rice.mapper.TableMapper} <p>The <code>tableMapper</code> field.</p>
     * @see io.github.nichetoolkit.rice.mapper.TableMapper
     */
    protected TableMapper tableMapper;

    /**
     * <code>logicActuator</code>
     * <p>The logic actuator method.</p>
     * @param entity E <p>The entity parameter is <code>E</code> type.</p>
     * @param model  M <p>The model parameter is <code>M</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    private void logicActuator(E entity, M model) throws RestException {
        if (entity instanceof RestLogic && model instanceof RestLogic) {
            RestLogic logicEntity = (RestLogic) entity;
            RestLogic logicModel = (RestLogic) model;
            logicEntity.setLogic(logicModel.getLogic());
        }
    }

    /**
     * <code>entityActuator</code>
     * <p>The entity actuator method.</p>
     * @param model   M <p>The model parameter is <code>M</code> type.</p>
     * @param idArray {@link java.lang.Object} <p>The id array parameter is <code>Object</code> type.</p>
     * @return E <p>The entity actuator return object is <code>E</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Object
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    protected E entityActuator(M model, Object... idArray) throws RestException {
        E entity = this.createEntity(model);
        logicActuator(entity, model);
        if (BuilderAdvice.class.isAssignableFrom(this.getClass())) {
            BuilderAdvice<M, E, I> builderAdvice = (BuilderAdvice<M, E, I>) this;
            builderAdvice.buildEntity(model, entity, idArray);
        }
        return entity;
    }


    /**
     * <code>entityActuator</code>
     * <p>The entity actuator method.</p>
     * @param modelList {@link java.util.Collection} <p>The model list parameter is <code>Collection</code> type.</p>
     * @param actuator  {@link io.github.nichetoolkit.rest.actuator.ConsumerActuator} <p>The actuator parameter is <code>ConsumerActuator</code> type.</p>
     * @param idArray   {@link java.lang.Object} <p>The id array parameter is <code>Object</code> type.</p>
     * @return {@link java.util.List} <p>The entity actuator return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.actuator.ConsumerActuator
     * @see java.lang.Object
     * @see java.util.List
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    protected List<E> entityActuator(Collection<M> modelList, ConsumerActuator<M> actuator, Object... idArray) throws RestException {
        List<E> entityList;
        if (BuilderAdvice.class.isAssignableFrom(this.getClass())) {
            BuilderAdvice<M, E, I> builderAdvice = (BuilderAdvice<M, E, I>) this;
            Method entityListFindMethod = null;
            try {
                entityListFindMethod = builderAdvice.getClass().getMethod("buildEntityList", Collection.class, List.class, Object[].class);
            } catch (NoSuchMethodException ignored) {
            }
            Method buildEntityListMethod = entityListFindMethod;
            /* 当buildEntity和buildEntityList都被复写的时候 优先调用buildEntityList */
            FunctionActuator<M, E> entityFunction = (M model) -> {
                E entity = createEntity(model);
                logicActuator(entity, model);
                if (buildEntityListMethod == null || buildEntityListMethod.isDefault()) {
                    builderAdvice.buildEntity(model, entity, idArray);
                }
                return entity;
            };
            if (GeneralUtils.isNotNull(actuator)) {
                entityList = MEBuilderHelper.entityList(modelList, actuator, entityFunction);
            } else {
                entityList = MEBuilderHelper.entityList(modelList, entityFunction);
            }
            if (buildEntityListMethod != null && !buildEntityListMethod.isDefault()) {
                builderAdvice.buildEntityList(modelList, entityList, idArray);
            }
        } else {
            if (GeneralUtils.isNotNull(actuator)) {
                entityList = MEBuilderHelper.entityList(modelList, actuator, this::createEntity);
            } else {
                entityList = MEBuilderHelper.entityList(modelList, this::createEntity);
            }
        }
        return entityList;
    }

    /**
     * <code>modelActuator</code>
     * <p>The model actuator method.</p>
     * @param entity      E <p>The entity parameter is <code>E</code> type.</p>
     * @param isLoadArray {@link io.github.nichetoolkit.mybatis.load.RestLoad} <p>The is load array parameter is <code>RestLoad</code> type.</p>
     * @return M <p>The model actuator return object is <code>M</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.mybatis.load.RestLoad
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    protected M modelActuator(E entity, RestLoad... isLoadArray) throws RestException {
        M model = this.createModel(entity);
        if (BuilderAdvice.class.isAssignableFrom(this.getClass())) {
            BuilderAdvice<M, E, I> builderAdvice = (BuilderAdvice<M, E, I>) this;
            builderAdvice.buildModel(entity, model, isLoadArray);
        }
        return model;
    }


    /**
     * <code>modelActuator</code>
     * <p>The model actuator method.</p>
     * @param entityList  {@link java.util.Collection} <p>The entity list parameter is <code>Collection</code> type.</p>
     * @param actuator    {@link io.github.nichetoolkit.rest.actuator.ConsumerActuator} <p>The actuator parameter is <code>ConsumerActuator</code> type.</p>
     * @param isLoadArray {@link io.github.nichetoolkit.mybatis.load.RestLoad} <p>The is load array parameter is <code>RestLoad</code> type.</p>
     * @return {@link java.util.List} <p>The model actuator return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.actuator.ConsumerActuator
     * @see io.github.nichetoolkit.mybatis.load.RestLoad
     * @see java.util.List
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    protected List<M> modelActuator(Collection<E> entityList, ConsumerActuator<E> actuator, RestLoad... isLoadArray) throws RestException {
        List<M> modelList;
        if (BuilderAdvice.class.isAssignableFrom(this.getClass())) {
            BuilderAdvice<M, E, I> builderAdvice = (BuilderAdvice<M, E, I>) this;
            Method findMethod = null;
            try {
                findMethod = builderAdvice.getClass().getMethod("buildModelList", Collection.class, List.class, RestLoad[].class);
            } catch (NoSuchMethodException ignored) {
            }
            Method buildModelListMethod = findMethod;
            /* 当buildModel和buildModelList都被复写的时候 优先调用buildModelList */
            FunctionActuator<E, M> modelFunction = (E entity) -> {
                M model = this.createModel(entity);
                if (buildModelListMethod == null || buildModelListMethod.isDefault()) {
                    builderAdvice.buildModel(entity, model, isLoadArray);
                }
                return model;
            };
            if (GeneralUtils.isNotNull(actuator)) {
                modelList = MEBuilderHelper.modelList(entityList, actuator, modelFunction);
            } else {
                modelList = MEBuilderHelper.modelList(entityList, modelFunction);
            }
            if (buildModelListMethod != null && !buildModelListMethod.isDefault()) {
                builderAdvice.buildModelList(entityList, modelList, isLoadArray);
            }
        } else {
            if (GeneralUtils.isNotNull(actuator)) {
                modelList = MEBuilderHelper.modelList(entityList, actuator, this::createModel);
            } else {
                modelList = MEBuilderHelper.modelList(entityList, this::createModel);
            }
        }
        return modelList;
    }

    /**
     * <code>optionalLogicAndOperate</code>
     * <p>The optional logic and operate method.</p>
     * @param model M <p>The model parameter is <code>M</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    private void optionalLogicAndOperate(@NonNull M model) throws RestException {
        if (model instanceof RestLogic) {
            RestLogic logicModel = (RestLogic) model;
            logicModel.setLogic(Optional.ofNullable(logicModel.getLogic()).orElse(unmarkOfLogic()));
        }
        if (model instanceof RestOperate) {
            RestOperate operateModel = (RestOperate) model;
            if (GeneralUtils.isEmpty(operateModel.getOperate())) {
                operateModel.setOperate(Optional.ofNullable(operateModel.getOperate()).orElse(OperateType.NONE));
            }
        }
        optionalName(model);
    }

    /**
     * <code>optionalDynamicTable</code>
     * <p>The optional dynamic table method.</p>
     * @param tableKey {@link io.github.nichetoolkit.rice.RestTableKey} <p>The table key parameter is <code>RestTableKey</code> type.</p>
     * @param model    M <p>The model parameter is <code>M</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see org.springframework.lang.NonNull
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    private void optionalDynamicTable(RestTableKey<K> tableKey, @NonNull M model) throws RestException {
        if (isDynamicOfTable()) {
            Map<K, String> tableNameMap = tableNameCaches.get();
            if (GeneralUtils.isEmpty(tableNameMap)) {
                tableNameMap = new HashMap<>();
                tableNameCaches.set(tableNameMap);
            }
            if (GeneralUtils.isEmpty(tableKey) && model instanceof RestTableKey) {
                tableKey = (RestTableKey<K>) model;
            }
            if (GeneralUtils.isEmpty(tableKey) || GeneralUtils.isEmpty(tableKey.getTableKey())) {
                return;
            }
            K ofTableKey = tableKey.getTableKey();
            String tableName = tableNameMap.get(tableKey.getTableKey());
            if (GeneralUtils.isNotEmpty(tableName)) {
                return;
            }
            tableName = dynamicTableName(ofTableKey);
            if (GeneralUtils.isNotEmpty(tableName)) {
                optionalTableName(tableName);
                tableNameMap.put(ofTableKey, tableName);
            }
        }
    }

    /**
     * <code>optionalCreate</code>
     * <p>The optional create method.</p>
     * @param tableKey {@link io.github.nichetoolkit.rice.RestTableKey} <p>The table key parameter is <code>RestTableKey</code> type.</p>
     * @param model    M <p>The model parameter is <code>M</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    protected void optionalCreate(RestTableKey<K> tableKey, @NonNull M model) throws RestException {
        model.initialize();
        optionalDynamicTable(tableKey, model);
        optionalLogicAndOperate(model);
        if (model.isEmpty() || !isIdentityOfInvade()) {
            createActuator().actuate(tableKey, model);
        } else {
            invadeActuator().actuate(tableKey, model);
        }
    }

    /**
     * <code>optionalUpdate</code>
     * <p>The optional update method.</p>
     * @param tableKey {@link io.github.nichetoolkit.rice.RestTableKey} <p>The table key parameter is <code>RestTableKey</code> type.</p>
     * @param model    M <p>The model parameter is <code>M</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    protected void optionalUpdate(RestTableKey<K> tableKey, @NonNull M model) throws RestException {
        model.initialize();
        OptionalUtils.ofTrueException(model.isEmpty(), log, IdentityNullException::new);
        optionalDynamicTable(tableKey, model);
        optionalLogicAndOperate(model);
        updateActuator().actuate(tableKey, model);
    }

    /**
     * <code>optionalSave</code>
     * <p>The optional save method.</p>
     * @param tableKey {@link io.github.nichetoolkit.rice.RestTableKey} <p>The table key parameter is <code>RestTableKey</code> type.</p>
     * @param model    M <p>The model parameter is <code>M</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    protected void optionalSave(RestTableKey<K> tableKey, @NonNull M model) throws RestException {
        model.initialize();
        optionalDynamicTable(tableKey, model);
        optionalLogicAndOperate(model);
        if (model.isEmpty()) {
            createActuator().actuate(tableKey, model);
        } else {
            saveActuator().actuate(tableKey, model);
        }
    }

    /**
     * <code>tableName</code>
     * <p>The table name method.</p>
     * @param tableKey K <p>The table key parameter is <code>K</code> type.</p>
     * @return {@link java.lang.String} <p>The table name return object is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    private String tableName(K tableKey) throws RestException {
        if (GeneralUtils.isEmpty(tableKey)) {
            return null;
        }
        Map<K, String> tableNameMap = tableNameCaches.get();
        if (GeneralUtils.isEmpty(tableNameMap)) {
            tableNameMap = new HashMap<>();
            tableNameCaches.set(tableNameMap);
        }
        if (isDynamicOfTable()) {
            String tableName = tableNameMap.get(tableKey);
            if (GeneralUtils.isEmpty(tableName)) {
                tableName = dynamicTableName(tableKey);
                if (GeneralUtils.isNotEmpty(tableName)) {
                    optionalTableName(tableName);
                    tableNameMap.put(tableKey, tableName);
                } else {
                    throw new TableNameIsNullException("The tableName is null, the method of 'dynamicTableName' maybe to override it.");
                }
            }
            return tableName;
        }
        return null;
    }

    /**
     * <code>tableName</code>
     * <p>The table name method.</p>
     * @param tableKey K <p>The table key parameter is <code>K</code> type.</p>
     * @param model    M <p>The model parameter is <code>M</code> type.</p>
     * @return {@link java.lang.String} <p>The table name return object is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    private String tableName(K tableKey, M model) throws RestException {
        if (GeneralUtils.isEmpty(tableKey) && GeneralUtils.isEmpty(model)) {
            return null;
        }
        Map<K, String> tableNameMap = tableNameCaches.get();
        if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tableNameMap)) {
            if (GeneralUtils.isNotEmpty(tableKey)) {
                return tableNameMap.get(tableKey);
            } else if (GeneralUtils.isNotEmpty(model) && model instanceof RestTableKey) {
                RestTableKey<K> table = (RestTableKey<K>) model;
                return tableNameMap.get(table.getTableKey());
            }
        }
        return null;
    }

    /**
     * <code>tableName</code>
     * <p>The table name method.</p>
     * @param tableKey  K <p>The table key parameter is <code>K</code> type.</p>
     * @param modelList {@link java.util.Collection} <p>The model list parameter is <code>Collection</code> type.</p>
     * @return {@link java.lang.String} <p>The table name return object is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    private String tableName(K tableKey, Collection<M> modelList) throws RestException {
        if (GeneralUtils.isEmpty(tableKey) && GeneralUtils.isEmpty(modelList)) {
            return null;
        } else if (GeneralUtils.isNotEmpty(tableKey)) {
            Map<K, String> tableNameMap = tableNameCaches.get();
            if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tableNameMap)) {
                return tableNameMap.get(tableKey);
            }
        } else if (GeneralUtils.isNotEmpty(modelList)) {
            Optional<M> optionalAny = modelList.stream().findAny();
            if (optionalAny.isPresent()) {
                M model = optionalAny.get();
                return tableName(tableKey, model);
            }
        }
        return null;
    }

    /**
     * <code>tableFickle</code>
     * <p>The table fickle method.</p>
     * @param tableName  {@link java.lang.String} <p>The table name parameter is <code>String</code> type.</p>
     * @param fickleList {@link java.util.Collection} <p>The fickle list parameter is <code>Collection</code> type.</p>
     * @return {@link io.github.nichetoolkit.mybatis.fickle.RestFickle} <p>The table fickle return object is <code>RestFickle</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.util.Collection
     * @see io.github.nichetoolkit.mybatis.fickle.RestFickle
     * @see io.github.nichetoolkit.rest.RestException
     */
    protected RestFickle<?>[] tableFickle(String tableName, Collection<RestFickle<?>> fickleList) throws RestException {
        if (GeneralUtils.isEmpty(fickleList)) {
            return tableFickle(tableName);
        }
        RestFickle<?>[] fickleArray = fickleList.toArray(new RestFickle[0]);
        return tableFickle(tableName, fickleArray);
    }

    /**
     * <code>tableFickle</code>
     * <p>The table fickle method.</p>
     * @param tableName   {@link java.lang.String} <p>The table name parameter is <code>String</code> type.</p>
     * @param fickleArray {@link io.github.nichetoolkit.mybatis.fickle.RestFickle} <p>The fickle array parameter is <code>RestFickle</code> type.</p>
     * @return {@link io.github.nichetoolkit.mybatis.fickle.RestFickle} <p>The table fickle return object is <code>RestFickle</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.mybatis.fickle.RestFickle
     * @see io.github.nichetoolkit.rest.RestException
     */
    protected RestFickle<?>[] tableFickle(String tableName, RestFickle<?>... fickleArray) throws RestException {
        if (isFickleField()) {
            List<String> tableColumns;
            if (GeneralUtils.isNotEmpty(tableName) && GeneralUtils.isNotEmpty(tableMapper)) {
                tableColumns = tableMapper.findTableColumns(tableName);
            } else {
                tableColumns = superMapper.findColumns();
            }
            if (GeneralUtils.isEmpty(tableColumns)) {
                return fickleArray;
            }
            if (isFickleOfAuto()) {
                return tableColumns.stream().map(RestFickle::of).toArray(RestFickle[]::new);
            }
            if (GeneralUtils.isNotEmpty(fickleArray)) {
                return RestStream.stream(fickleArray).filter(Objects::nonNull).filter(fickle -> {
                    if (GeneralUtils.isNotEmpty(fickle.getKey())) {
                        return tableColumns.contains(fickle.getKey());
                    } else {
                        String fickleName = fickle.getName();
                        String columnName = DefaultColumnResolver.resolveColumn(fickleName);
                        return tableColumns.contains(columnName);
                    }
                }).distinct().toArray(RestFickle[]::new);
            }
        }
        return fickleArray;
    }

    /**
     * <code>single</code>
     * <p>The single method.</p>
     * @param tableKey {@link io.github.nichetoolkit.rice.RestTableKey} <p>The table key parameter is <code>RestTableKey</code> type.</p>
     * @param model    M <p>The model parameter is <code>M</code> type.</p>
     * @param idArray  {@link java.lang.Object} <p>The id array parameter is <code>Object</code> type.</p>
     * @return {@link java.lang.Integer} <p>The single return object is <code>Integer</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestTableKey
     * @see java.lang.Object
     * @see java.lang.Integer
     * @see io.github.nichetoolkit.rest.RestException
     */
    protected Integer single(RestTableKey<K> tableKey, M model, Object... idArray) throws RestException {
        E entity = entityActuator(model, idArray);
        K key = Optional.ofNullable(tableKey).map(RestTableKey::getTableKey).orElse(null);
        String tableName = tableName(key, model);
        Integer result;
        if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tableName)) {
            result = superMapper.saveDynamic(tableName, entity);
        } else {
            result = superMapper.save(entity);
        }
        return result;
    }


    /**
     * <code>findById</code>
     * <p>The find by id method.</p>
     * @param id        I <p>The id parameter is <code>I</code> type.</p>
     * @param tableName {@link java.lang.String} <p>The table name parameter is <code>String</code> type.</p>
     * @return E <p>The find by id return object is <code>E</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    protected E findById(I id, String tableName) throws RestException {
        E entity;
        if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tableName)) {
            entity = superMapper.findDynamicById(tableName, id);
        } else {
            entity = superMapper.findById(id);
        }
        return entity;
    }

    /**
     * <code>findByIdLoad</code>
     * <p>The find by id load method.</p>
     * @param id          I <p>The id parameter is <code>I</code> type.</p>
     * @param tableName   {@link java.lang.String} <p>The table name parameter is <code>String</code> type.</p>
     * @param isLoadArray {@link io.github.nichetoolkit.mybatis.load.RestLoad} <p>The is load array parameter is <code>RestLoad</code> type.</p>
     * @return E <p>The find by id load return object is <code>E</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.mybatis.load.RestLoad
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    protected E findByIdLoad(I id, String tableName, RestLoad... isLoadArray) throws RestException {
        E entity;
        FindLoadMapper<E, I> loadMapper = (FindLoadMapper<E, I>) superMapper;
        Method findMethod = null;
        try {
            findMethod = loadMapper.getClass().getMethod("findByIdLoad", Object.class, RestLoad[].class);
        } catch (NoSuchMethodException ignored) {
        }
        Method queryByIdMethod = findMethod;
        /* 当LoadMapper被复写的时候 优先调用LoadMapper的findByIdMethod */
        if (queryByIdMethod != null && !queryByIdMethod.isDefault()) {
            if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tableName)) {
                entity = loadMapper.findDynamicByIdLoad(tableName, id, isLoadArray);
            } else {
                entity = loadMapper.findByIdLoad(id, isLoadArray);
            }
        } else {
            entity = findById(id, tableName);
        }
        return entity;
    }

    /**
     * <code>findByIdFickle</code>
     * <p>The find by id fickle method.</p>
     * @param id          I <p>The id parameter is <code>I</code> type.</p>
     * @param tableName   {@link java.lang.String} <p>The table name parameter is <code>String</code> type.</p>
     * @param fickleArray {@link io.github.nichetoolkit.mybatis.fickle.RestFickle} <p>The fickle array parameter is <code>RestFickle</code> type.</p>
     * @return E <p>The find by id fickle return object is <code>E</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.mybatis.fickle.RestFickle
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    protected E findByIdFickle(I id, String tableName, RestFickle<?>... fickleArray) throws RestException {
        E entity;
        FindFickleMapper<E, I> fickleMapper = (FindFickleMapper<E, I>) superMapper;
        Method findMethod = null;
        try {
            findMethod = fickleMapper.getClass().getMethod("findByIdFickle", Object.class, RestFickle[].class);
        } catch (NoSuchMethodException ignored) {
        }
        Method queryByIdMethod = findMethod;
        /* 当LoadMapper被复写的时候 优先调用fickleMapper的findByIdMethod */
        if (queryByIdMethod != null && !queryByIdMethod.isDefault()) {
            if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tableName)) {
                entity = fickleMapper.findDynamicByIdFickle(tableName, id, fickleArray);
            } else {
                entity = fickleMapper.findByIdFickle(id, fickleArray);
            }
        } else {
            entity = findById(id, tableName);
        }
        return entity;
    }

    /**
     * <code>findByIdFickleLoad</code>
     * <p>The find by id fickle load method.</p>
     * @param id          I <p>The id parameter is <code>I</code> type.</p>
     * @param tableName   {@link java.lang.String} <p>The table name parameter is <code>String</code> type.</p>
     * @param fickleArray {@link io.github.nichetoolkit.mybatis.fickle.RestFickle} <p>The fickle array parameter is <code>RestFickle</code> type.</p>
     * @param isLoadArray {@link io.github.nichetoolkit.mybatis.load.RestLoad} <p>The is load array parameter is <code>RestLoad</code> type.</p>
     * @return E <p>The find by id fickle load return object is <code>E</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.mybatis.fickle.RestFickle
     * @see io.github.nichetoolkit.mybatis.load.RestLoad
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    protected E findByIdFickleLoad(I id, String tableName, RestFickle<?>[] fickleArray, RestLoad... isLoadArray) throws RestException {
        E entity;
        FickleLoadMapper<E, I> fickleLoadMapper = (FickleLoadMapper<E, I>) superMapper;
        Method findMethod = null;
        try {
            findMethod = fickleLoadMapper.getClass().getMethod("findByIdFickleLoad", Object.class, RestFickle[].class, RestLoad[].class);
        } catch (NoSuchMethodException ignored) {
        }
        Method queryByIdMethod = findMethod;
        /* 当LoadMapper被复写的时候 优先调用fickleMapper的findByIdMethod */
        if (queryByIdMethod != null && !queryByIdMethod.isDefault()) {
            if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tableName)) {
                entity = fickleLoadMapper.findDynamicByIdFickleLoad(tableName, id, fickleArray, isLoadArray);
            } else {
                entity = fickleLoadMapper.findByIdFickleLoad(id, fickleArray, isLoadArray);
            }
        } else {
            entity = findById(id, tableName);
        }
        return entity;
    }

    /**
     * <code>findByLinkId</code>
     * <p>The find by link id method.</p>
     * @param <L>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableName {@link java.lang.String} <p>The table name parameter is <code>String</code> type.</p>
     * @param linkId    L <p>The link id parameter is <code>L</code> type.</p>
     * @param linkName  {@link io.github.nichetoolkit.rest.RestKey} <p>The link name parameter is <code>RestKey</code> type.</p>
     * @return {@link java.util.List} <p>The find by link id return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestKey
     * @see java.util.List
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    protected <L> List<E> findByLinkId(String tableName, L linkId, RestKey<String> linkName) throws RestException {
        List<E> entityList;
        if (FindLinkMapper.class.isAssignableFrom(superMapper.getClass())) {
            FindLinkMapper<E, L, I> findLinkMapper = (FindLinkMapper<E, L, I>) superMapper;
            boolean linkNamePresent = GeneralUtils.isNotEmpty(linkName) && GeneralUtils.isNotEmpty(linkName.getKey());
            if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tableName)) {
                if (linkNamePresent) {
                    entityList = findLinkMapper.findDynamicByLinkId(tableName, linkId, linkName.getKey());
                } else {
                    entityList = findLinkMapper.findDynamicByLinkId(tableName, linkId);
                }
            } else {
                if (linkNamePresent) {
                    entityList = findLinkMapper.findByLinkId(linkId, linkName.getKey());
                } else {
                    entityList = findLinkMapper.findByLinkId(linkId);
                }
            }
        } else {
            throw new UnsupportedErrorException("The 'findByLinkId' method is unimplemented, the mapper must extends 'FindLinkMapper'.");
        }
        return entityList;
    }

    /**
     * <code>findByLinkIdLoad</code>
     * <p>The find by link id load method.</p>
     * @param <L>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableName   {@link java.lang.String} <p>The table name parameter is <code>String</code> type.</p>
     * @param linkId      L <p>The link id parameter is <code>L</code> type.</p>
     * @param linkName    {@link io.github.nichetoolkit.rest.RestKey} <p>The link name parameter is <code>RestKey</code> type.</p>
     * @param isLoadArray {@link io.github.nichetoolkit.mybatis.load.RestLoad} <p>The is load array parameter is <code>RestLoad</code> type.</p>
     * @return {@link java.util.List} <p>The find by link id load return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestKey
     * @see io.github.nichetoolkit.mybatis.load.RestLoad
     * @see java.util.List
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    protected <L> List<E> findByLinkIdLoad(String tableName, L linkId, RestKey<String> linkName, RestLoad... isLoadArray) throws RestException {
        List<E> entityList;
        LinkLoadMapper<E, L, I> loadMapper = (LinkLoadMapper<E, L, I>) superMapper;
        Method findMethod = null;
        try {
            findMethod = loadMapper.getClass().getMethod("findByLinkIdLoad", Object.class, RestLoad[].class);
        } catch (NoSuchMethodException ignored) {
        }
        Method queryByIdMethod = findMethod;
        /* 当LoadMapper被复写的时候 优先调用LoadMapper的queryByIdMethod */
        if (queryByIdMethod != null && !queryByIdMethod.isDefault()) {
            boolean linkNamePresent = GeneralUtils.isNotEmpty(linkName) && GeneralUtils.isNotEmpty(linkName.getKey());
            if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tableName)) {
                if (linkNamePresent) {
                    entityList = loadMapper.findDynamicByLinkIdLoad(tableName, linkId, linkName.getKey(), isLoadArray);
                } else {
                    entityList = loadMapper.findDynamicByLinkIdLoad(tableName, linkId, isLoadArray);
                }
            } else {
                if (linkNamePresent) {
                    entityList = loadMapper.findByLinkIdLoad(linkId, linkName.getKey(), isLoadArray);
                } else {
                    entityList = loadMapper.findByLinkIdLoad(linkId, isLoadArray);
                }
            }
        } else {
            entityList = findByLinkId(tableName, linkId, linkName);
        }
        return entityList;
    }

    /**
     * <code>findByLinkIdFickle</code>
     * <p>The find by link id fickle method.</p>
     * @param <L>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableName   {@link java.lang.String} <p>The table name parameter is <code>String</code> type.</p>
     * @param linkId      L <p>The link id parameter is <code>L</code> type.</p>
     * @param linkName    {@link io.github.nichetoolkit.rest.RestKey} <p>The link name parameter is <code>RestKey</code> type.</p>
     * @param fickleArray {@link io.github.nichetoolkit.mybatis.fickle.RestFickle} <p>The fickle array parameter is <code>RestFickle</code> type.</p>
     * @return {@link java.util.List} <p>The find by link id fickle return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestKey
     * @see io.github.nichetoolkit.mybatis.fickle.RestFickle
     * @see java.util.List
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    protected <L> List<E> findByLinkIdFickle(String tableName, L linkId, RestKey<String> linkName, RestFickle<?>... fickleArray) throws RestException {
        List<E> entityList;
        LinkFickleMapper<E, L, I> fickleMapper = (LinkFickleMapper<E, L, I>) superMapper;
        Method findMethod = null;
        try {
            findMethod = fickleMapper.getClass().getMethod("findByLinkIdFickle", Object.class, RestFickle[].class);
        } catch (NoSuchMethodException ignored) {
        }
        Method queryByIdMethod = findMethod;
        /* 当LoadMapper被复写的时候 优先调用LoadMapper的queryByIdMethod */
        if (queryByIdMethod != null && !queryByIdMethod.isDefault()) {
            boolean linkNamePresent = GeneralUtils.isNotEmpty(linkName) && GeneralUtils.isNotEmpty(linkName.getKey());
            if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tableName)) {
                if (linkNamePresent) {
                    entityList = fickleMapper.findDynamicByLinkIdFickle(tableName, linkId, linkName.getKey(), fickleArray);
                } else {
                    entityList = fickleMapper.findDynamicByLinkIdFickle(tableName, linkId, fickleArray);
                }
            } else {
                if (linkNamePresent) {
                    entityList = fickleMapper.findByLinkIdFickle(linkId, linkName.getKey(), fickleArray);
                } else {
                    entityList = fickleMapper.findByLinkIdFickle(linkId, fickleArray);
                }
            }
        } else {
            entityList = findByLinkId(tableName, linkId, linkName);
        }
        return entityList;
    }

    /**
     * <code>findByLinkIdFickleLoad</code>
     * <p>The find by link id fickle load method.</p>
     * @param <L>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableName   {@link java.lang.String} <p>The table name parameter is <code>String</code> type.</p>
     * @param linkId      L <p>The link id parameter is <code>L</code> type.</p>
     * @param linkName    {@link io.github.nichetoolkit.rest.RestKey} <p>The link name parameter is <code>RestKey</code> type.</p>
     * @param fickleArray {@link io.github.nichetoolkit.mybatis.fickle.RestFickle} <p>The fickle array parameter is <code>RestFickle</code> type.</p>
     * @param isLoadArray {@link io.github.nichetoolkit.mybatis.load.RestLoad} <p>The is load array parameter is <code>RestLoad</code> type.</p>
     * @return {@link java.util.List} <p>The find by link id fickle load return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestKey
     * @see io.github.nichetoolkit.mybatis.fickle.RestFickle
     * @see io.github.nichetoolkit.mybatis.load.RestLoad
     * @see java.util.List
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    protected <L> List<E> findByLinkIdFickleLoad(String tableName, L linkId, RestKey<String> linkName, RestFickle<?>[] fickleArray, RestLoad... isLoadArray) throws RestException {
        List<E> entityList;
        FickleLinkMapper<E, L, I> fickleLinkMapper = (FickleLinkMapper<E, L, I>) superMapper;
        Method findMethod = null;
        try {
            findMethod = fickleLinkMapper.getClass().getMethod("findByLinkIdFickleLoad", Object.class, RestFickle[].class, RestLoad[].class);
        } catch (NoSuchMethodException ignored) {
        }
        Method queryByIdMethod = findMethod;
        /* 当LoadMapper被复写的时候 优先调用LoadMapper的queryByIdMethod */
        if (queryByIdMethod != null && !queryByIdMethod.isDefault()) {
            boolean linkNamePresent = GeneralUtils.isNotEmpty(linkName) && GeneralUtils.isNotEmpty(linkName.getKey());
            if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tableName)) {
                if (linkNamePresent) {
                    entityList = fickleLinkMapper.findDynamicByLinkIdFickleLoad(tableName, linkId, linkName.getKey(), fickleArray, isLoadArray);
                } else {
                    entityList = fickleLinkMapper.findDynamicByLinkIdFickleLoad(tableName, linkId, fickleArray, isLoadArray);
                }
            } else {
                if (linkNamePresent) {
                    entityList = fickleLinkMapper.findByLinkIdFickleLoad(linkId, linkName.getKey(), fickleArray, isLoadArray);
                } else {
                    entityList = fickleLinkMapper.findByLinkIdFickleLoad(linkId, fickleArray, isLoadArray);
                }
            }
        } else {
            entityList = findByLinkId(tableName, linkId, linkName);
        }
        return entityList;
    }

    /**
     * <code>alertId</code>
     * <p>The alert id method.</p>
     * @param <S>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableName  {@link java.lang.String} <p>The table name parameter is <code>String</code> type.</p>
     * @param id         I <p>The id parameter is <code>I</code> type.</p>
     * @param status     S <p>The status parameter is <code>S</code> type.</p>
     * @param statusName {@link io.github.nichetoolkit.rest.RestKey} <p>The status name parameter is <code>RestKey</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestKey
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings("unchecked")
    public <S> void alertId(String tableName, I id, S status, RestKey<String> statusName) throws RestException {
        boolean statusNamePresent = GeneralUtils.isNotEmpty(statusName) && GeneralUtils.isNotEmpty(statusName.getKey());
        if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tableName)) {
            if (statusNamePresent) {
                ((AlertMapper<S, I>) superMapper).alertDynamicById(tableName, id, status, statusName.getKey());
            } else {
                ((AlertMapper<S, I>) superMapper).alertDynamicById(tableName, id, status);
            }
        } else {
            if (statusNamePresent) {
                ((AlertMapper<S, I>) superMapper).alertById(id, status, statusName.getKey());
            } else {
                ((AlertMapper<S, I>) superMapper).alertById(id, status);
            }
        }
    }

    /**
     * <code>alertLinkId</code>
     * <p>The alert link id method.</p>
     * @param <L>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <S>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableName  {@link java.lang.String} <p>The table name parameter is <code>String</code> type.</p>
     * @param linkId     L <p>The link id parameter is <code>L</code> type.</p>
     * @param linkName   {@link io.github.nichetoolkit.rest.RestKey} <p>The link name parameter is <code>RestKey</code> type.</p>
     * @param status     S <p>The status parameter is <code>S</code> type.</p>
     * @param statusName {@link io.github.nichetoolkit.rest.RestKey} <p>The status name parameter is <code>RestKey</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestKey
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings("unchecked")
    protected <L, S> void alertLinkId(String tableName, L linkId, RestKey<String> linkName, S status, RestKey<String> statusName) throws RestException {
        boolean linkNamePresent = GeneralUtils.isNotEmpty(linkName) && GeneralUtils.isNotEmpty(linkName.getKey());
        boolean statusNamePresent = GeneralUtils.isNotEmpty(statusName) && GeneralUtils.isNotEmpty(statusName.getKey());
        if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tableName)) {
            if (linkNamePresent) {
                if (statusNamePresent) {
                    ((AlertLinkMapper<L, S, I>) superMapper).alertDynamicByLinkId(tableName, linkId, linkName.getKey(), status, statusName.getKey());
                } else {
                    ((AlertLinkMapper<L, S, I>) superMapper).alertDynamicByLinkId(tableName, linkId, linkName.getKey(), status);
                }
            } else {
                if (statusNamePresent) {
                    ((AlertLinkMapper<L, S, I>) superMapper).alertDynamicByLinkId(tableName, linkId, status, statusName.getKey());
                } else {
                    ((AlertLinkMapper<L, S, I>) superMapper).alertDynamicByLinkId(tableName, linkId, status);
                }
            }
        } else {
            if (linkNamePresent) {
                if (statusNamePresent) {
                    ((AlertLinkMapper<L, S, I>) superMapper).alertByLinkId(linkId, linkName.getKey(), status, statusName.getKey());
                } else {
                    ((AlertLinkMapper<L, S, I>) superMapper).alertByLinkId(linkId, linkName.getKey(), status);
                }
            } else {
                if (statusNamePresent) {
                    ((AlertLinkMapper<L, S, I>) superMapper).alertByLinkId(linkId, status, statusName.getKey());
                } else {
                    ((AlertLinkMapper<L, S, I>) superMapper).alertByLinkId(linkId, status);
                }
            }
        }
    }


    /**
     * <code>alertPartition</code>
     * <p>The alert partition method.</p>
     * @param <S>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableName  {@link java.lang.String} <p>The table name parameter is <code>String</code> type.</p>
     * @param idList     {@link java.util.Collection} <p>The id list parameter is <code>Collection</code> type.</p>
     * @param status     S <p>The status parameter is <code>S</code> type.</p>
     * @param statusName {@link io.github.nichetoolkit.rest.RestKey} <p>The status name parameter is <code>RestKey</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestKey
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings({"unchecked", "Duplicates"})
    protected <S> void alertPartition(String tableName, Collection<I> idList, S status, RestKey<String> statusName) throws RestException {
        if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tableName)) {
            if (GeneralUtils.isNotEmpty(statusName) && GeneralUtils.isNotEmpty(statusName.getKey())) {
                PartitionHelper.partition(idList, this.partitionOfDelete(), ids -> ((AlertMapper<S, I>) superMapper).alertDynamicAll(tableName, ids, status, statusName.getKey()));
            } else {
                PartitionHelper.partition(idList, this.partitionOfDelete(), ids -> ((AlertMapper<S, I>) superMapper).alertDynamicAll(tableName, ids, status));
            }
        } else {
            if (GeneralUtils.isNotEmpty(statusName) && GeneralUtils.isNotEmpty(statusName.getKey())) {
                PartitionHelper.partition(idList, this.partitionOfDelete(), ids -> ((AlertMapper<S, I>) superMapper).alertAll(ids, status, statusName.getKey()));
            } else {
                PartitionHelper.partition(idList, this.partitionOfDelete(), ids -> ((AlertMapper<S, I>) superMapper).alertAll(ids, status));
            }
        }
    }

    /**
     * <code>alertLinkIdPartition</code>
     * <p>The alert link id partition method.</p>
     * @param <L>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <S>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableName  {@link java.lang.String} <p>The table name parameter is <code>String</code> type.</p>
     * @param linkIdList {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @param linkName   {@link io.github.nichetoolkit.rest.RestKey} <p>The link name parameter is <code>RestKey</code> type.</p>
     * @param status     S <p>The status parameter is <code>S</code> type.</p>
     * @param statusName {@link io.github.nichetoolkit.rest.RestKey} <p>The status name parameter is <code>RestKey</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestKey
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings({"unchecked", "Duplicates"})
    protected <L, S> void alertLinkIdPartition(String tableName, Collection<L> linkIdList, RestKey<String> linkName, S status, RestKey<String> statusName) throws RestException {
        boolean linkNamePresent = GeneralUtils.isNotEmpty(linkName) && GeneralUtils.isNotEmpty(linkName.getKey());
        boolean statusNamePresent = GeneralUtils.isNotEmpty(statusName) && GeneralUtils.isNotEmpty(statusName.getKey());
        if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tableName)) {
            if (linkNamePresent) {
                if (statusNamePresent) {
                    PartitionHelper.partition(linkIdList, this.partitionOfDelete(), linkIds -> ((AlertLinkMapper<L, S, I>) superMapper).alertDynamicAllByLinkIds(tableName, linkIds, linkName.getKey(), status, statusName.getKey()));
                } else {
                    PartitionHelper.partition(linkIdList, this.partitionOfDelete(), linkIds -> ((AlertLinkMapper<L, S, I>) superMapper).alertDynamicAllByLinkIds(tableName, linkIds, linkName.getKey(), status));
                }
            } else {
                if (statusNamePresent) {
                    PartitionHelper.partition(linkIdList, this.partitionOfDelete(), linkIds -> ((AlertLinkMapper<L, S, I>) superMapper).alertDynamicAllByLinkIds(tableName, linkIds, status, statusName.getKey()));
                } else {
                    PartitionHelper.partition(linkIdList, this.partitionOfDelete(), linkIds -> ((AlertLinkMapper<L, S, I>) superMapper).alertDynamicAllByLinkIds(tableName, linkIds, status));
                }
            }
        } else {
            if (linkNamePresent) {
                if (statusNamePresent) {
                    PartitionHelper.partition(linkIdList, this.partitionOfDelete(), linkIds -> ((AlertLinkMapper<L, S, I>) superMapper).alertAllByLinkIds(linkIds, linkName.getKey(), status, statusName.getKey()));
                } else {
                    PartitionHelper.partition(linkIdList, this.partitionOfDelete(), linkIds -> ((AlertLinkMapper<L, S, I>) superMapper).alertAllByLinkIds(linkIds, linkName.getKey(), status));
                }
            } else {
                if (statusNamePresent) {
                    PartitionHelper.partition(linkIdList, this.partitionOfDelete(), linkIds -> ((AlertLinkMapper<L, S, I>) superMapper).alertAllByLinkIds(linkIds, status, statusName.getKey()));
                } else {
                    PartitionHelper.partition(linkIdList, this.partitionOfDelete(), linkIds -> ((AlertLinkMapper<L, S, I>) superMapper).alertAllByLinkIds(linkIds, status));
                }
            }
        }
    }

    /**
     * <code>alertAdvice</code>
     * <p>The alert advice method.</p>
     * @param <S>            {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param entityList     {@link java.util.List} <p>The entity list parameter is <code>List</code> type.</p>
     * @param status         S <p>The status parameter is <code>S</code> type.</p>
     * @param statusActuator {@link io.github.nichetoolkit.rest.actuator.ConsumerActuator} <p>The status actuator parameter is <code>ConsumerActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.actuator.ConsumerActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    protected <S> void alertAdvice(List<E> entityList, S status, ConsumerActuator<S> statusActuator) throws RestException {
        if (!isBeforeSkip()) {
            this.beforeAlertAll(entityList);
        }
        statusActuator.actuate(status);
        if (!isAfterSkip()) {
            this.afterAlertAll(entityList);
        }
        this.refresh();
    }

    /**
     * <code>alertFilterWhere</code>
     * <p>The alert filter where method.</p>
     * @param <S>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableName     {@link java.lang.String} <p>The table name parameter is <code>String</code> type.</p>
     * @param alertWhereSql {@link java.lang.String} <p>The alert where sql parameter is <code>String</code> type.</p>
     * @param filter        F <p>The filter parameter is <code>F</code> type.</p>
     * @param status        S <p>The status parameter is <code>S</code> type.</p>
     * @param statusName    {@link io.github.nichetoolkit.rest.RestKey} <p>The status name parameter is <code>RestKey</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestKey
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings({"unchecked", "Duplicates"})
    protected <S> void alertFilterWhere(String tableName, String alertWhereSql, F filter, S status, RestKey<String> statusName) throws RestException {
        boolean statusNamePresent = GeneralUtils.isNotEmpty(statusName) && GeneralUtils.isNotEmpty(statusName.getKey());
        if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tableName)) {
            if (statusNamePresent) {
                ((AlertFilterMapper<E, F, S, I, K>) superMapper).alertDynamicAllByFilterWhere(tableName, alertWhereSql, filter, status, statusName.getKey());
            } else {
                ((AlertFilterMapper<E, F, S, I, K>) superMapper).alertDynamicAllByFilterWhere(tableName, alertWhereSql, filter, status);
            }
        } else {
            if (statusNamePresent) {
                ((AlertFilterMapper<E, F, S, I, K>) superMapper).alertAllByFilterWhere(alertWhereSql, filter, status, statusName.getKey());
            } else {
                ((AlertFilterMapper<E, F, S, I, K>) superMapper).alertAllByFilterWhere(alertWhereSql, filter, status);
            }
        }
    }

    /**
     * <code>alertAllWhere</code>
     * <p>The alert all where method.</p>
     * @param <S>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableName     {@link java.lang.String} <p>The table name parameter is <code>String</code> type.</p>
     * @param alertWhereSql {@link java.lang.String} <p>The alert where sql parameter is <code>String</code> type.</p>
     * @param status        S <p>The status parameter is <code>S</code> type.</p>
     * @param statusName    {@link io.github.nichetoolkit.rest.RestKey} <p>The status name parameter is <code>RestKey</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestKey
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings({"unchecked", "Duplicates"})
    protected <S> void alertAllWhere(String tableName, String alertWhereSql, S status, RestKey<String> statusName) throws RestException {
        boolean statusNamePresent = GeneralUtils.isNotEmpty(statusName) && GeneralUtils.isNotEmpty(statusName.getKey());
        if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tableName)) {
            if (statusNamePresent) {
                ((AlertMapper<S, I>) superMapper).alertDynamicAllByWhere(tableName, alertWhereSql, status, statusName.getKey());
            } else {
                ((AlertMapper<S, I>) superMapper).alertDynamicAllByWhere(tableName, alertWhereSql, status);
            }
        } else {
            if (statusNamePresent) {
                ((AlertMapper<S, I>) superMapper).alertAllByWhere(alertWhereSql, status, statusName.getKey());
            } else {
                ((AlertMapper<S, I>) superMapper).alertAllByWhere(alertWhereSql, status);
            }
        }
    }

    /**
     * <code>operateId</code>
     * <p>The operate id method.</p>
     * @param tableName {@link java.lang.String} <p>The table name parameter is <code>String</code> type.</p>
     * @param id        I <p>The id parameter is <code>I</code> type.</p>
     * @param operate   {@link io.github.nichetoolkit.rice.enums.OperateType} <p>The operate parameter is <code>OperateType</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rice.enums.OperateType
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    protected void operateId(String tableName, I id, OperateType operate) throws RestException {
        if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tableName)) {
            ((OperateMapper<I>) superMapper).operateDynamicById(tableName, id, operate.getKey());
        } else {
            ((OperateMapper<I>) superMapper).operateById(id, operate.getKey());
        }
    }

    /**
     * <code>operateLinkId</code>
     * <p>The operate link id method.</p>
     * @param <L>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableName {@link java.lang.String} <p>The table name parameter is <code>String</code> type.</p>
     * @param linkId    L <p>The link id parameter is <code>L</code> type.</p>
     * @param linkName  {@link io.github.nichetoolkit.rest.RestKey} <p>The link name parameter is <code>RestKey</code> type.</p>
     * @param operate   {@link io.github.nichetoolkit.rice.enums.OperateType} <p>The operate parameter is <code>OperateType</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestKey
     * @see io.github.nichetoolkit.rice.enums.OperateType
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    protected <L> void operateLinkId(String tableName, L linkId, RestKey<String> linkName, OperateType operate) throws RestException {
        boolean linkNamePresent = GeneralUtils.isNotEmpty(linkName) && GeneralUtils.isNotEmpty(linkName.getKey());
        if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tableName)) {
            if (linkNamePresent) {
                ((OperateLinkMapper<L, I>) superMapper).operateDynamicByLinkId(tableName, linkId, linkName.getKey(), operate.getKey());
            } else {
                ((OperateLinkMapper<L, I>) superMapper).operateDynamicByLinkId(tableName, linkId, operate.getKey());
            }
        } else {
            if (linkNamePresent) {
                ((OperateLinkMapper<L, I>) superMapper).operateByLinkId(linkId, linkName.getKey(), operate.getKey());
            } else {
                ((OperateLinkMapper<L, I>) superMapper).operateByLinkId(linkId, operate.getKey());
            }
        }
    }

    /**
     * <code>operatePartition</code>
     * <p>The operate partition method.</p>
     * @param tableName {@link java.lang.String} <p>The table name parameter is <code>String</code> type.</p>
     * @param idList    {@link java.util.Collection} <p>The id list parameter is <code>Collection</code> type.</p>
     * @param operate   {@link io.github.nichetoolkit.rice.enums.OperateType} <p>The operate parameter is <code>OperateType</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rice.enums.OperateType
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    protected void operatePartition(String tableName, Collection<I> idList, OperateType operate) throws RestException {
        if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tableName)) {
            PartitionHelper.partition(idList, this.partitionOfDelete(), ids -> ((OperateMapper<I>) superMapper).operateDynamicAll(tableName, ids, operate.getKey()));
        } else {
            PartitionHelper.partition(idList, this.partitionOfDelete(), ids -> ((OperateMapper<I>) superMapper).operateAll(ids, operate.getKey()));
        }
    }

    /**
     * <code>operateLinkIdPartition</code>
     * <p>The operate link id partition method.</p>
     * @param <L>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableName  {@link java.lang.String} <p>The table name parameter is <code>String</code> type.</p>
     * @param linkIdList {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @param linkName   {@link io.github.nichetoolkit.rest.RestKey} <p>The link name parameter is <code>RestKey</code> type.</p>
     * @param operate    {@link io.github.nichetoolkit.rice.enums.OperateType} <p>The operate parameter is <code>OperateType</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestKey
     * @see io.github.nichetoolkit.rice.enums.OperateType
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings({"unchecked", "Duplicates"})
    protected <L> void operateLinkIdPartition(String tableName, Collection<L> linkIdList, RestKey<String> linkName, OperateType operate) throws RestException {
        boolean linkNamePresent = GeneralUtils.isNotEmpty(linkName) && GeneralUtils.isNotEmpty(linkName.getKey());
        if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tableName)) {
            if (linkNamePresent) {
                PartitionHelper.partition(linkIdList, this.partitionOfDelete(), linkIds -> ((OperateLinkMapper<L, I>) superMapper).operateDynamicAllByLinkIds(tableName, linkIds, linkName.getKey(), operate.getKey()));
            } else {
                PartitionHelper.partition(linkIdList, this.partitionOfDelete(), linkIds -> ((OperateLinkMapper<L, I>) superMapper).operateDynamicAllByLinkIds(tableName, linkIds, operate.getKey()));
            }
        } else {
            if (linkNamePresent) {
                PartitionHelper.partition(linkIdList, this.partitionOfDelete(), linkIds -> ((OperateLinkMapper<L, I>) superMapper).operateAllByLinkIds(linkIds, linkName.getKey(), operate.getKey()));
            } else {
                PartitionHelper.partition(linkIdList, this.partitionOfDelete(), linkIds -> ((OperateLinkMapper<L, I>) superMapper).operateAllByLinkIds(linkIds, operate.getKey()));
            }
        }
    }

    /**
     * <code>operateAdvice</code>
     * <p>The operate advice method.</p>
     * @param entityList      {@link java.util.List} <p>The entity list parameter is <code>List</code> type.</p>
     * @param operate         {@link io.github.nichetoolkit.rice.enums.OperateType} <p>The operate parameter is <code>OperateType</code> type.</p>
     * @param operateActuator {@link io.github.nichetoolkit.rest.actuator.ConsumerActuator} <p>The operate actuator parameter is <code>ConsumerActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.List
     * @see io.github.nichetoolkit.rice.enums.OperateType
     * @see io.github.nichetoolkit.rest.actuator.ConsumerActuator
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings("Duplicates")
    protected void operateAdvice(List<E> entityList, OperateType operate, ConsumerActuator<OperateType> operateActuator) throws RestException {
        if (!isBeforeSkip()) {
            this.beforeOperateAll(entityList);
        }
        operateActuator.actuate(operate);
        if (!isAfterSkip()) {
            this.afterOperateAll(entityList);
        }
        this.refresh();
    }

    /**
     * <code>findAll</code>
     * <p>The find all method.</p>
     * @param idList    {@link java.util.Collection} <p>The id list parameter is <code>Collection</code> type.</p>
     * @param tableName {@link java.lang.String} <p>The table name parameter is <code>String</code> type.</p>
     * @return {@link java.util.List} <p>The find all return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see java.lang.String
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.RestException
     */
    protected List<E> findAll(Collection<I> idList, String tableName) throws RestException {
        List<E> entityList;
        if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tableName)) {
            entityList = PartitionHelper.query(idList, this.partitionOfQuery(), ids -> superMapper.findDynamicAll(tableName, ids));
        } else {
            entityList = PartitionHelper.query(idList, this.partitionOfQuery(), superMapper::findAll);
        }
        return entityList;
    }

    /**
     * <code>findAllLoad</code>
     * <p>The find all load method.</p>
     * @param idList      {@link java.util.Collection} <p>The id list parameter is <code>Collection</code> type.</p>
     * @param tableName   {@link java.lang.String} <p>The table name parameter is <code>String</code> type.</p>
     * @param isLoadArray {@link io.github.nichetoolkit.mybatis.load.RestLoad} <p>The is load array parameter is <code>RestLoad</code> type.</p>
     * @return {@link java.util.List} <p>The find all load return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see java.lang.String
     * @see io.github.nichetoolkit.mybatis.load.RestLoad
     * @see java.util.List
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    protected List<E> findAllLoad(Collection<I> idList, String tableName, RestLoad... isLoadArray) throws RestException {
        List<E> entityList;
        FindLoadMapper<E, I> loadMapper = (FindLoadMapper<E, I>) superMapper;
        Method findMethod = null;
        try {
            findMethod = loadMapper.getClass().getMethod("findAllLoad", Collection.class, RestLoad[].class);
        } catch (NoSuchMethodException ignored) {
        }
        Method queryAllMethod = findMethod;
        /* 当LoadMapper被复写的时候 优先调用LoadMapper的findAllMethod */
        if (queryAllMethod != null && !queryAllMethod.isDefault()) {
            if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tableName)) {
                entityList = PartitionHelper.query(idList, this.partitionOfQuery(), ids -> loadMapper.findDynamicAllLoad(tableName, ids, isLoadArray));
            } else {
                entityList = PartitionHelper.query(idList, this.partitionOfQuery(), ids -> loadMapper.findAllLoad(ids, isLoadArray));
            }
        } else {
            entityList = findAll(idList, tableName);
        }
        return entityList;
    }


    /**
     * <code>findAllFickle</code>
     * <p>The find all fickle method.</p>
     * @param idList      {@link java.util.Collection} <p>The id list parameter is <code>Collection</code> type.</p>
     * @param tableName   {@link java.lang.String} <p>The table name parameter is <code>String</code> type.</p>
     * @param fickleArray {@link io.github.nichetoolkit.mybatis.fickle.RestFickle} <p>The fickle array parameter is <code>RestFickle</code> type.</p>
     * @return {@link java.util.List} <p>The find all fickle return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see java.lang.String
     * @see io.github.nichetoolkit.mybatis.fickle.RestFickle
     * @see java.util.List
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    protected List<E> findAllFickle(Collection<I> idList, String tableName, RestFickle<?>... fickleArray) throws RestException {
        List<E> entityList;
        FindFickleMapper<E, I> fickleMapper = (FindFickleMapper<E, I>) superMapper;
        Method findMethod = null;
        try {
            findMethod = fickleMapper.getClass().getMethod("findAllFickle", Collection.class, RestFickle[].class);
        } catch (NoSuchMethodException ignored) {
        }
        Method queryAllMethod = findMethod;
        /* 当fickleMapper被复写的时候 优先调用fickleMapper的findAllMethod */
        if (queryAllMethod != null && !queryAllMethod.isDefault()) {
            if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tableName)) {
                entityList = PartitionHelper.query(idList, this.partitionOfQuery(), ids -> fickleMapper.findDynamicAllFickle(tableName, ids, fickleArray));
            } else {
                entityList = PartitionHelper.query(idList, this.partitionOfQuery(), ids -> fickleMapper.findAllFickle(ids, fickleArray));
            }
        } else {
            entityList = findAll(idList, tableName);
        }
        return entityList;
    }

    /**
     * <code>findAllFickleLoad</code>
     * <p>The find all fickle load method.</p>
     * @param idList      {@link java.util.Collection} <p>The id list parameter is <code>Collection</code> type.</p>
     * @param tableName   {@link java.lang.String} <p>The table name parameter is <code>String</code> type.</p>
     * @param fickleArray {@link io.github.nichetoolkit.mybatis.fickle.RestFickle} <p>The fickle array parameter is <code>RestFickle</code> type.</p>
     * @param isLoadArray {@link io.github.nichetoolkit.mybatis.load.RestLoad} <p>The is load array parameter is <code>RestLoad</code> type.</p>
     * @return {@link java.util.List} <p>The find all fickle load return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see java.lang.String
     * @see io.github.nichetoolkit.mybatis.fickle.RestFickle
     * @see io.github.nichetoolkit.mybatis.load.RestLoad
     * @see java.util.List
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    protected List<E> findAllFickleLoad(Collection<I> idList, String tableName, RestFickle<?>[] fickleArray, RestLoad... isLoadArray) throws RestException {
        List<E> entityList;
        FickleLoadMapper<E, I> fickleLoadMapper = (FickleLoadMapper<E, I>) superMapper;
        Method findMethod = null;
        try {
            findMethod = fickleLoadMapper.getClass().getMethod("findAllFickleLoad", Collection.class, RestFickle[].class, RestLoad[].class);
        } catch (NoSuchMethodException ignored) {
        }
        Method queryAllMethod = findMethod;
        /* 当fickleMapper被复写的时候 优先调用fickleMapper的findAllMethod */
        if (queryAllMethod != null && !queryAllMethod.isDefault()) {
            if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tableName)) {
                entityList = PartitionHelper.query(idList, this.partitionOfQuery(), ids -> fickleLoadMapper.findDynamicAllFickleLoad(tableName, ids, fickleArray, isLoadArray));
            } else {
                entityList = PartitionHelper.query(idList, this.partitionOfQuery(), ids -> fickleLoadMapper.findAllFickleLoad(ids, fickleArray, isLoadArray));
            }
        } else {
            entityList = findAll(idList, tableName);
        }
        return entityList;
    }


    /**
     * <code>findAllByLinkIds</code>
     * <p>The find all by link ids method.</p>
     * @param <L>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableName  {@link java.lang.String} <p>The table name parameter is <code>String</code> type.</p>
     * @param linkIdList {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @param linkName   {@link io.github.nichetoolkit.rest.RestKey} <p>The link name parameter is <code>RestKey</code> type.</p>
     * @return {@link java.util.List} <p>The find all by link ids return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestKey
     * @see java.util.List
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    protected <L> List<E> findAllByLinkIds(String tableName, Collection<L> linkIdList, RestKey<String> linkName) throws RestException {
        List<E> entityList;
        if (FindLinkMapper.class.isAssignableFrom(superMapper.getClass())) {
            FindLinkMapper<E, L, I> findLinkMapper = (FindLinkMapper<E, L, I>) superMapper;
            boolean linkNamePresent = GeneralUtils.isNotEmpty(linkName) && GeneralUtils.isNotEmpty(linkName.getKey());
            if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tableName)) {
                if (linkNamePresent) {
                    entityList = PartitionHelper.query(linkIdList, this.partitionOfQuery(), linkIds -> findLinkMapper.findDynamicAllByLinkIds(tableName, linkIds, linkName.getKey()));
                } else {
                    entityList = PartitionHelper.query(linkIdList, this.partitionOfQuery(), linkIds -> findLinkMapper.findDynamicAllByLinkIds(tableName, linkIds));
                }
            } else {
                if (linkNamePresent) {
                    entityList = PartitionHelper.query(linkIdList, this.partitionOfQuery(), linkIds -> findLinkMapper.findAllByLinkIds(linkIds, linkName.getKey()));
                } else {
                    entityList = PartitionHelper.query(linkIdList, this.partitionOfQuery(), findLinkMapper::findAllByLinkIds);
                }
            }
        } else {
            throw new UnsupportedErrorException("The 'findAllByLinkIds' method is unimplemented, the mapper must extends 'FindLinkMapper'.");
        }
        return entityList;
    }

    /**
     * <code>findAllByLinkIdsLoad</code>
     * <p>The find all by link ids load method.</p>
     * @param <L>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableName   {@link java.lang.String} <p>The table name parameter is <code>String</code> type.</p>
     * @param linkIdList  {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @param linkName    {@link io.github.nichetoolkit.rest.RestKey} <p>The link name parameter is <code>RestKey</code> type.</p>
     * @param isLoadArray {@link io.github.nichetoolkit.mybatis.load.RestLoad} <p>The is load array parameter is <code>RestLoad</code> type.</p>
     * @return {@link java.util.List} <p>The find all by link ids load return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestKey
     * @see io.github.nichetoolkit.mybatis.load.RestLoad
     * @see java.util.List
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    protected <L> List<E> findAllByLinkIdsLoad(String tableName, Collection<L> linkIdList, RestKey<String> linkName, RestLoad... isLoadArray) throws RestException {
        List<E> entityList;
        LinkLoadMapper<E, L, I> loadMapper = (LinkLoadMapper<E, L, I>) superMapper;
        Method findMethod = null;
        try {
            findMethod = loadMapper.getClass().getMethod("findAllByLinkIdsLoad", Collection.class, RestLoad[].class);
        } catch (NoSuchMethodException ignored) {
        }
        Method queryAllMethod = findMethod;
        /* 当LoadMapper被复写的时候 优先调用LoadMapper的queryByIdMethod */
        if (queryAllMethod != null && !queryAllMethod.isDefault()) {
            boolean linkNamePresent = GeneralUtils.isNotEmpty(linkName) && GeneralUtils.isNotEmpty(linkName.getKey());
            if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tableName)) {
                if (linkNamePresent) {
                    entityList = PartitionHelper.query(linkIdList, this.partitionOfQuery(), linkIds -> loadMapper.findDynamicAllByLinkIdsLoad(tableName, linkIds, linkName.getKey(), isLoadArray));
                } else {
                    entityList = PartitionHelper.query(linkIdList, this.partitionOfQuery(), linkIds -> loadMapper.findDynamicAllByLinkIdsLoad(tableName, linkIds, isLoadArray));
                }
            } else {
                if (linkNamePresent) {
                    entityList = PartitionHelper.query(linkIdList, this.partitionOfQuery(), linkIds -> loadMapper.findAllByLinkIdsLoad(linkIds, linkName.getKey(), isLoadArray));
                } else {
                    entityList = PartitionHelper.query(linkIdList, this.partitionOfQuery(), linkIds -> loadMapper.findAllByLinkIdsLoad(linkIds, isLoadArray));
                }
            }
        } else {
            entityList = findAllByLinkIds(tableName, linkIdList, linkName);
        }
        return entityList;
    }

    /**
     * <code>findAllByLinkIdsFickle</code>
     * <p>The find all by link ids fickle method.</p>
     * @param <L>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableName   {@link java.lang.String} <p>The table name parameter is <code>String</code> type.</p>
     * @param linkIdList  {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @param linkName    {@link io.github.nichetoolkit.rest.RestKey} <p>The link name parameter is <code>RestKey</code> type.</p>
     * @param fickleArray {@link io.github.nichetoolkit.mybatis.fickle.RestFickle} <p>The fickle array parameter is <code>RestFickle</code> type.</p>
     * @return {@link java.util.List} <p>The find all by link ids fickle return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestKey
     * @see io.github.nichetoolkit.mybatis.fickle.RestFickle
     * @see java.util.List
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    protected <L> List<E> findAllByLinkIdsFickle(String tableName, Collection<L> linkIdList, RestKey<String> linkName, RestFickle<?>... fickleArray) throws RestException {
        List<E> entityList;
        LinkFickleMapper<E, L, I> fickleMapper = (LinkFickleMapper<E, L, I>) superMapper;
        Method findMethod = null;
        try {
            findMethod = fickleMapper.getClass().getMethod("findAllByLinkIdsFickle", Collection.class, RestFickle[].class);
        } catch (NoSuchMethodException ignored) {
        }
        Method queryAllMethod = findMethod;
        /* 当LoadMapper被复写的时候 优先调用LoadMapper的queryByIdMethod */
        if (queryAllMethod != null && !queryAllMethod.isDefault()) {
            boolean linkNamePresent = GeneralUtils.isNotEmpty(linkName) && GeneralUtils.isNotEmpty(linkName.getKey());
            if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tableName)) {
                if (linkNamePresent) {
                    entityList = PartitionHelper.query(linkIdList, this.partitionOfQuery(), linkIds -> fickleMapper.findDynamicAllByLinkIdsFickle(tableName, linkIds, linkName.getKey(), fickleArray));
                } else {
                    entityList = PartitionHelper.query(linkIdList, this.partitionOfQuery(), linkIds -> fickleMapper.findDynamicAllByLinkIdsFickle(tableName, linkIds, fickleArray));
                }
            } else {
                if (linkNamePresent) {
                    entityList = PartitionHelper.query(linkIdList, this.partitionOfQuery(), linkIds -> fickleMapper.findAllByLinkIdsFickle(linkIds, linkName.getKey(), fickleArray));
                } else {
                    entityList = PartitionHelper.query(linkIdList, this.partitionOfQuery(), linkIds -> fickleMapper.findAllByLinkIdsFickle(linkIds, fickleArray));
                }
            }
        } else {
            entityList = findAllByLinkIds(tableName, linkIdList, linkName);
        }
        return entityList;
    }

    /**
     * <code>findAllByLinkIdsFickleLoad</code>
     * <p>The find all by link ids fickle load method.</p>
     * @param <L>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableName   {@link java.lang.String} <p>The table name parameter is <code>String</code> type.</p>
     * @param linkIdList  {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @param linkName    {@link io.github.nichetoolkit.rest.RestKey} <p>The link name parameter is <code>RestKey</code> type.</p>
     * @param fickleArray {@link io.github.nichetoolkit.mybatis.fickle.RestFickle} <p>The fickle array parameter is <code>RestFickle</code> type.</p>
     * @param isLoadArray {@link io.github.nichetoolkit.mybatis.load.RestLoad} <p>The is load array parameter is <code>RestLoad</code> type.</p>
     * @return {@link java.util.List} <p>The find all by link ids fickle load return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestKey
     * @see io.github.nichetoolkit.mybatis.fickle.RestFickle
     * @see io.github.nichetoolkit.mybatis.load.RestLoad
     * @see java.util.List
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    protected <L> List<E> findAllByLinkIdsFickleLoad(String tableName, Collection<L> linkIdList, RestKey<String> linkName, RestFickle<?>[] fickleArray, RestLoad... isLoadArray) throws RestException {
        List<E> entityList;
        FickleLinkMapper<E, L, I> fickleLinkMapper = (FickleLinkMapper<E, L, I>) superMapper;
        Method findMethod = null;
        try {
            findMethod = fickleLinkMapper.getClass().getMethod("findAllByLinkIdsFickleLoad", Collection.class, RestFickle[].class, RestLoad[].class);
        } catch (NoSuchMethodException ignored) {
        }
        Method queryAllMethod = findMethod;
        /* 当LoadMapper被复写的时候 优先调用LoadMapper的queryByIdMethod */
        if (queryAllMethod != null && !queryAllMethod.isDefault()) {
            boolean linkNamePresent = GeneralUtils.isNotEmpty(linkName) && GeneralUtils.isNotEmpty(linkName.getKey());
            if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tableName)) {
                if (linkNamePresent) {
                    entityList = PartitionHelper.query(linkIdList, this.partitionOfQuery(), linkIds -> fickleLinkMapper.findDynamicAllByLinkIdsFickleLoad(tableName, linkIds, linkName.getKey(), fickleArray, isLoadArray));
                } else {
                    entityList = PartitionHelper.query(linkIdList, this.partitionOfQuery(), linkIds -> fickleLinkMapper.findDynamicAllByLinkIdsFickleLoad(tableName, linkIds, fickleArray, isLoadArray));
                }
            } else {
                if (linkNamePresent) {
                    entityList = PartitionHelper.query(linkIdList, this.partitionOfQuery(), linkIds -> fickleLinkMapper.findAllByLinkIdsFickleLoad(linkIds, linkName.getKey(), fickleArray, isLoadArray));
                } else {
                    entityList = PartitionHelper.query(linkIdList, this.partitionOfQuery(), linkIds -> fickleLinkMapper.findAllByLinkIdsFickleLoad(linkIds, fickleArray, isLoadArray));
                }
            }
        } else {
            entityList = findAllByLinkIds(tableName, linkIdList, linkName);
        }
        return entityList;
    }

    /**
     * <code>findAllByLoadWhere</code>
     * <p>The find all by load where method.</p>
     * @param whereSql    {@link java.lang.String} <p>The where sql parameter is <code>String</code> type.</p>
     * @param tableName   {@link java.lang.String} <p>The table name parameter is <code>String</code> type.</p>
     * @param filter      F <p>The filter parameter is <code>F</code> type.</p>
     * @param isLoadArray {@link io.github.nichetoolkit.mybatis.load.RestLoad} <p>The is load array parameter is <code>RestLoad</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.PageResult} <p>The find all by load where return object is <code>PageResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.mybatis.load.RestLoad
     * @see io.github.nichetoolkit.rice.PageResult
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    protected PageResult<E, I> findAllByLoadWhere(String whereSql, String tableName, F filter, RestLoad... isLoadArray) throws RestException {
        PageResult<E, I> pageResult;
        List<E> entityList;
        FilterLoadMapper<E, I> loadFilterMapper = (FilterLoadMapper<E, I>) superMapper;
        Method findMethod = null;
        try {
            findMethod = loadFilterMapper.getClass().getMethod("findAllByLoadWhere", String.class, RestLoad[].class);
        } catch (NoSuchMethodException ignored) {
        }
        Method findAllByWhereMethod = findMethod;
        /* 当LoadMapper被复写的时候 优先调用LoadMapper的findAllByWhereMethod */
        if (findAllByWhereMethod != null && !findAllByWhereMethod.isDefault()) {
            if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tableName)) {
                Page<E> page = filter.toPage();
                entityList = loadFilterMapper.findDynamicAllByLoadWhere(tableName, whereSql, isLoadArray);
                pageResult = PageResult.builder(page, entityList);
            } else {
                Page<E> page = filter.toPage();
                entityList = loadFilterMapper.findAllByLoadWhere(whereSql, isLoadArray);
                pageResult = PageResult.builder(page, entityList);
            }
        } else {
            Page<E> page = filter.toPage();
            entityList = findAllByWhere(whereSql, tableName);
            pageResult = PageResult.builder(page, entityList);
        }
        return pageResult;
    }

    /**
     * <code>findAllByFilterWhere</code>
     * <p>The find all by filter where method.</p>
     * @param whereSql  {@link java.lang.String} <p>The where sql parameter is <code>String</code> type.</p>
     * @param tableName {@link java.lang.String} <p>The table name parameter is <code>String</code> type.</p>
     * @param filter    F <p>The filter parameter is <code>F</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.PageResult} <p>The find all by filter where return object is <code>PageResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rice.PageResult
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    protected PageResult<E, I> findAllByFilterWhere(String whereSql, String tableName, F filter) throws RestException {
        PageResult<E, I> pageResult;
        List<E> entityList;
        FindFilterMapper<E, F, I, K> filterMapper = (FindFilterMapper<E, F, I, K>) superMapper;
        Method findMethod = null;
        try {
            findMethod = filterMapper.getClass().getMethod("findAllByFilterWhere", String.class, Object.class);
        } catch (NoSuchMethodException ignored) {
        }
        Method findAllByWhereMethod = findMethod;
        /* 当FindMapper被复写的时候 优先调用FindMapper的findAllByWhereMethod */
        if (findAllByWhereMethod != null && !findAllByWhereMethod.isDefault()) {
            if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tableName)) {
                Page<E> page = filter.toPage();
                entityList = filterMapper.findDynamicAllByFilterWhere(tableName, whereSql, filter);
                pageResult = PageResult.builder(page, entityList);
            } else {
                Page<E> page = filter.toPage();
                entityList = filterMapper.findAllByFilterWhere(whereSql, filter);
                pageResult = PageResult.builder(page, entityList);
            }
        } else {
            Page<E> page = filter.toPage();
            entityList = findAllByWhere(whereSql, tableName);
            pageResult = PageResult.builder(page, entityList);
        }
        return pageResult;
    }

    /**
     * <code>findAllByFickleWhere</code>
     * <p>The find all by fickle where method.</p>
     * @param whereSql    {@link java.lang.String} <p>The where sql parameter is <code>String</code> type.</p>
     * @param tableName   {@link java.lang.String} <p>The table name parameter is <code>String</code> type.</p>
     * @param filter      F <p>The filter parameter is <code>F</code> type.</p>
     * @param fickleArray {@link io.github.nichetoolkit.mybatis.fickle.RestFickle} <p>The fickle array parameter is <code>RestFickle</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.PageResult} <p>The find all by fickle where return object is <code>PageResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.mybatis.fickle.RestFickle
     * @see io.github.nichetoolkit.rice.PageResult
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    protected PageResult<E, I> findAllByFickleWhere(String whereSql, String tableName, F filter, RestFickle<?>... fickleArray) throws RestException {
        PageResult<E, I> pageResult;
        List<E> entityList;
        FilterFickleMapper<E, I> filterMapper = (FilterFickleMapper<E, I>) superMapper;
        Method findMethod = null;
        try {
            findMethod = filterMapper.getClass().getMethod("findAllByFickleWhere", String.class, RestFickle[].class);
        } catch (NoSuchMethodException ignored) {
        }
        Method findAllByWhereMethod = findMethod;
        /* 当FindMapper被复写的时候 优先调用FindMapper的findAllByWhereMethod */
        if (findAllByWhereMethod != null && !findAllByWhereMethod.isDefault()) {
            if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tableName)) {
                Page<E> page = filter.toPage();
                entityList = filterMapper.findDynamicAllByFickleWhere(tableName, whereSql, fickleArray);
                pageResult = PageResult.builder(page, entityList);
            } else {
                Page<E> page = filter.toPage();
                entityList = filterMapper.findAllByFickleWhere(whereSql, fickleArray);
                pageResult = PageResult.builder(page, entityList);
            }
        } else {
            Page<E> page = filter.toPage();
            entityList = findAllByWhere(whereSql, tableName);
            pageResult = PageResult.builder(page, entityList);
        }
        return pageResult;
    }

    /**
     * <code>findAllByFickleLoadWhere</code>
     * <p>The find all by fickle load where method.</p>
     * @param whereSql    {@link java.lang.String} <p>The where sql parameter is <code>String</code> type.</p>
     * @param tableName   {@link java.lang.String} <p>The table name parameter is <code>String</code> type.</p>
     * @param filter      F <p>The filter parameter is <code>F</code> type.</p>
     * @param fickleArray {@link io.github.nichetoolkit.mybatis.fickle.RestFickle} <p>The fickle array parameter is <code>RestFickle</code> type.</p>
     * @param isLoadArray {@link io.github.nichetoolkit.mybatis.load.RestLoad} <p>The is load array parameter is <code>RestLoad</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.PageResult} <p>The find all by fickle load where return object is <code>PageResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.mybatis.fickle.RestFickle
     * @see io.github.nichetoolkit.mybatis.load.RestLoad
     * @see io.github.nichetoolkit.rice.PageResult
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    protected PageResult<E, I> findAllByFickleLoadWhere(String whereSql, String tableName, F filter, RestFickle<?>[] fickleArray, RestLoad... isLoadArray) throws RestException {
        PageResult<E, I> pageResult;
        List<E> entityList;
        FickleFilterMapper<E, I> filterMapper = (FickleFilterMapper<E, I>) superMapper;
        Method findMethod = null;
        try {
            findMethod = filterMapper.getClass().getMethod("findAllByFickleLoadWhere", String.class, RestFickle[].class, RestLoad[].class);
        } catch (NoSuchMethodException ignored) {
        }
        Method findAllByWhereMethod = findMethod;
        /* 当FindMapper被复写的时候 优先调用FindMapper的findAllByWhereMethod */
        if (findAllByWhereMethod != null && !findAllByWhereMethod.isDefault()) {
            if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tableName)) {
                Page<E> page = filter.toPage();
                entityList = filterMapper.findDynamicAllByFickleLoadWhere(tableName, whereSql, fickleArray, isLoadArray);
                pageResult = PageResult.builder(page, entityList);
            } else {
                Page<E> page = filter.toPage();
                entityList = filterMapper.findAllByFickleLoadWhere(whereSql, fickleArray, isLoadArray);
                pageResult = PageResult.builder(page, entityList);
            }
        } else {
            Page<E> page = filter.toPage();
            entityList = findAllByWhere(whereSql, tableName);
            pageResult = PageResult.builder(page, entityList);
        }
        return pageResult;
    }

    /**
     * <code>removeId</code>
     * <p>The remove id method.</p>
     * @param tableName {@link java.lang.String} <p>The table name parameter is <code>String</code> type.</p>
     * @param id        I <p>The id parameter is <code>I</code> type.</p>
     * @param logic     {@link java.lang.Object} <p>The logic parameter is <code>Object</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings("unchecked")
    public void removeId(String tableName, I id, Object logic) throws RestException {
        if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tableName)) {
            ((RemoveMapper<I>) superMapper).removeDynamicById(tableName, id, logic);
        } else {
            ((RemoveMapper<I>) superMapper).removeById(id, logic);
        }
    }

    /**
     * <code>removeLinkId</code>
     * <p>The remove link id method.</p>
     * @param <L>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableName {@link java.lang.String} <p>The table name parameter is <code>String</code> type.</p>
     * @param linkId    L <p>The link id parameter is <code>L</code> type.</p>
     * @param linkName  {@link io.github.nichetoolkit.rest.RestKey} <p>The link name parameter is <code>RestKey</code> type.</p>
     * @param logic     {@link java.lang.Object} <p>The logic parameter is <code>Object</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestKey
     * @see java.lang.Object
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings("unchecked")
    protected <L> void removeLinkId(String tableName, L linkId, RestKey<String> linkName, Object logic) throws RestException {
        boolean linkNamePresent = GeneralUtils.isNotEmpty(linkName) && GeneralUtils.isNotEmpty(linkName.getKey());
        if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tableName)) {
            if (linkNamePresent) {
                ((RemoveLinkMapper<L, I>) superMapper).removeDynamicByLinkId(tableName, linkId, linkName.getKey(), logic);
            } else {
                ((RemoveLinkMapper<L, I>) superMapper).removeDynamicByLinkId(tableName, linkId, logic);
            }
        } else {
            if (linkNamePresent) {
                ((RemoveLinkMapper<L, I>) superMapper).removeByLinkId(linkId, linkName.getKey(), logic);
            } else {
                ((RemoveLinkMapper<L, I>) superMapper).removeByLinkId(linkId, logic);
            }
        }
    }

    /**
     * <code>removePartition</code>
     * <p>The remove partition method.</p>
     * @param tableName {@link java.lang.String} <p>The table name parameter is <code>String</code> type.</p>
     * @param idList    {@link java.util.Collection} <p>The id list parameter is <code>Collection</code> type.</p>
     * @param logic     {@link java.lang.Object} <p>The logic parameter is <code>Object</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.util.Collection
     * @see java.lang.Object
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings({"unchecked", "Duplicates"})
    protected void removePartition(String tableName, Collection<I> idList, Object logic) throws RestException {
        if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tableName)) {
            PartitionHelper.delete(idList, this.partitionOfDelete(), ids -> ((RemoveMapper<I>) superMapper).removeDynamicAll(tableName, ids, logic));
        } else {
            PartitionHelper.delete(idList, this.partitionOfDelete(), ids -> ((RemoveMapper<I>) superMapper).removeAll(ids, logic));
        }
    }

    /**
     * <code>removeLinkIdPartition</code>
     * <p>The remove link id partition method.</p>
     * @param <L>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableName  {@link java.lang.String} <p>The table name parameter is <code>String</code> type.</p>
     * @param linkIdList {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @param linkName   {@link io.github.nichetoolkit.rest.RestKey} <p>The link name parameter is <code>RestKey</code> type.</p>
     * @param logic      {@link java.lang.Object} <p>The logic parameter is <code>Object</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestKey
     * @see java.lang.Object
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings({"unchecked", "Duplicates"})
    protected <L> void removeLinkIdPartition(String tableName, Collection<L> linkIdList, RestKey<String> linkName, Object logic) throws RestException {
        boolean linkNamePresent = GeneralUtils.isNotEmpty(linkName) && GeneralUtils.isNotEmpty(linkName.getKey());
        if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tableName)) {
            if (linkNamePresent) {
                PartitionHelper.delete(linkIdList, this.partitionOfDelete(), linkIds -> ((RemoveLinkMapper<L, I>) superMapper).removeDynamicAllByLinkIds(tableName, linkIds, linkName.getKey(), logic));
            } else {
                PartitionHelper.delete(linkIdList, this.partitionOfDelete(), linkIds -> ((RemoveLinkMapper<L, I>) superMapper).removeDynamicAllByLinkIds(tableName, linkIds, logic));
            }
        } else {
            if (linkNamePresent) {
                PartitionHelper.delete(linkIdList, this.partitionOfDelete(), linkIds -> ((RemoveLinkMapper<L, I>) superMapper).removeAllByLinkIds(linkIds, linkName.getKey(), logic));
            } else {
                PartitionHelper.delete(linkIdList, this.partitionOfDelete(), linkIds -> ((RemoveLinkMapper<L, I>) superMapper).removeAllByLinkIds(linkIds, logic));
            }
        }
    }

    /**
     * <code>removeAdvice</code>
     * <p>The remove advice method.</p>
     * @param entityList     {@link java.util.List} <p>The entity list parameter is <code>List</code> type.</p>
     * @param logic          {@link java.lang.Object} <p>The logic parameter is <code>Object</code> type.</p>
     * @param removeActuator {@link io.github.nichetoolkit.rest.actuator.ConsumerActuator} <p>The remove actuator parameter is <code>ConsumerActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.List
     * @see java.lang.Object
     * @see io.github.nichetoolkit.rest.actuator.ConsumerActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    protected void removeAdvice(List<E> entityList, Object logic, ConsumerActuator<Object> removeActuator) throws RestException {
        if (!isBeforeSkip()) {
            this.beforeRemoveAll(entityList);
        }
        removeActuator.actuate(logic);
        if (!isAfterSkip()) {
            this.afterRemoveAll(entityList);
        }
        this.refresh();
    }

    /**
     * <code>deleteId</code>
     * <p>The delete id method.</p>
     * @param tableName {@link java.lang.String} <p>The table name parameter is <code>String</code> type.</p>
     * @param id        I <p>The id parameter is <code>I</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public void deleteId(String tableName, I id) throws RestException {
        if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tableName)) {
            superMapper.deleteDynamicById(tableName, id);
        } else {
            superMapper.deleteById(id);
        }
    }

    /**
     * <code>deleteLinkId</code>
     * <p>The delete link id method.</p>
     * @param <L>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableName {@link java.lang.String} <p>The table name parameter is <code>String</code> type.</p>
     * @param linkId    L <p>The link id parameter is <code>L</code> type.</p>
     * @param linkName  {@link io.github.nichetoolkit.rest.RestKey} <p>The link name parameter is <code>RestKey</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestKey
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings("unchecked")
    public <L> void deleteLinkId(String tableName, L linkId, RestKey<String> linkName) throws RestException {
        boolean linkNamePresent = GeneralUtils.isNotEmpty(linkName) && GeneralUtils.isNotEmpty(linkName.getKey());
        if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tableName)) {
            if (linkNamePresent) {
                ((DeleteLinkMapper<L, I>) superMapper).deleteDynamicByLinkId(tableName, linkId, linkName.getKey());
            } else {
                ((DeleteLinkMapper<L, I>) superMapper).deleteDynamicByLinkId(tableName, linkId);
            }
        } else {
            if (linkNamePresent) {
                ((DeleteLinkMapper<L, I>) superMapper).deleteByLinkId(linkId, linkName.getKey());
            } else {
                ((DeleteLinkMapper<L, I>) superMapper).deleteByLinkId(linkId);
            }
        }
    }

    /**
     * <code>deletePartition</code>
     * <p>The delete partition method.</p>
     * @param tableName {@link java.lang.String} <p>The table name parameter is <code>String</code> type.</p>
     * @param idList    {@link java.util.Collection} <p>The id list parameter is <code>Collection</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestException
     */
    protected void deletePartition(String tableName, Collection<I> idList) throws RestException {
        if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tableName)) {
            PartitionHelper.delete(idList, this.partitionOfDelete(), ids -> superMapper.deleteDynamicAll(tableName, ids));
        } else {
            PartitionHelper.delete(idList, this.partitionOfDelete(), ids -> superMapper.deleteAll(ids));
        }
    }

    /**
     * <code>deleteLinkIdPartition</code>
     * <p>The delete link id partition method.</p>
     * @param <L>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param tableName  {@link java.lang.String} <p>The table name parameter is <code>String</code> type.</p>
     * @param linkIdList {@link java.util.Collection} <p>The link id list parameter is <code>Collection</code> type.</p>
     * @param linkName   {@link io.github.nichetoolkit.rest.RestKey} <p>The link name parameter is <code>RestKey</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestKey
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings({"unchecked", "Duplicates"})
    protected <L> void deleteLinkIdPartition(String tableName, Collection<L> linkIdList, RestKey<String> linkName) throws RestException {
        boolean linkNamePresent = GeneralUtils.isNotEmpty(linkName) && GeneralUtils.isNotEmpty(linkName.getKey());
        if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tableName)) {
            if (linkNamePresent) {
                PartitionHelper.delete(linkIdList, this.partitionOfDelete(), linkIds -> ((DeleteLinkMapper<L, I>) superMapper).deleteDynamicAllByLinkIds(tableName, linkIds, linkName.getKey()));
            } else {
                PartitionHelper.delete(linkIdList, this.partitionOfDelete(), linkIds -> ((DeleteLinkMapper<L, I>) superMapper).deleteDynamicAllByLinkIds(tableName, linkIds));
            }
        } else {
            if (linkNamePresent) {
                PartitionHelper.delete(linkIdList, this.partitionOfDelete(), linkIds -> ((DeleteLinkMapper<L, I>) superMapper).deleteAllByLinkIds(linkIds, linkName.getKey()));
            } else {
                PartitionHelper.delete(linkIdList, this.partitionOfDelete(), linkIds -> ((DeleteLinkMapper<L, I>) superMapper).deleteAllByLinkIds(linkIds));
            }
        }
    }

    /**
     * <code>deleteAdvice</code>
     * <p>The delete advice method.</p>
     * @param entityList     {@link java.util.List} <p>The entity list parameter is <code>List</code> type.</p>
     * @param deleteActuator {@link io.github.nichetoolkit.rest.actuator.AnchorActuator} <p>The delete actuator parameter is <code>AnchorActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.actuator.AnchorActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    protected void deleteAdvice(List<E> entityList, AnchorActuator deleteActuator) throws RestException {
        if (!isBeforeSkip()) {
            this.beforeDeleteAll(entityList);
        }
        deleteActuator.actuate();
        if (!isAfterSkip()) {
            this.afterDeleteAll(entityList);
        }
        this.refresh();
    }

    /**
     * <code>findAllByWhere</code>
     * <p>The find all by where method.</p>
     * @param whereSql  {@link java.lang.String} <p>The where sql parameter is <code>String</code> type.</p>
     * @param tableName {@link java.lang.String} <p>The table name parameter is <code>String</code> type.</p>
     * @return {@link java.util.List} <p>The find all by where return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.RestException
     */
    protected List<E> findAllByWhere(String whereSql, String tableName) throws RestException {
        List<E> entityList;
        if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tableName)) {
            entityList = superMapper.findDynamicAllByWhere(tableName, whereSql);
        } else {
            entityList = superMapper.findAllByWhere(whereSql);
        }
        return entityList;
    }

    /**
     * <code>deleteAllByWhere</code>
     * <p>The delete all by where method.</p>
     * @param whereSql  {@link java.lang.String} <p>The where sql parameter is <code>String</code> type.</p>
     * @param tableName {@link java.lang.String} <p>The table name parameter is <code>String</code> type.</p>
     * @param filter    F <p>The filter parameter is <code>F</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    protected void deleteAllByWhere(String whereSql, String tableName, F filter) throws RestException {
        if (isBeforeSkip() && isAfterSkip()) {
            if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tableName)) {
                superMapper.deleteDynamicAllByWhere(tableName, whereSql);
            } else {
                superMapper.deleteAllByWhere(whereSql);
            }
        } else {
            String queryWhereSql = queryWhereSql(filter);
            List<E> entityList = superMapper.findAllByWhere(queryWhereSql);
            if (GeneralUtils.isNotEmpty(entityList)) {
                deleteAdvice(entityList, () -> {
                    if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tableName)) {
                        superMapper.deleteDynamicAllByWhere(tableName, whereSql);
                    } else {
                        superMapper.deleteAllByWhere(whereSql);
                    }
                });
            }
        }
    }

    /**
     * <code>removeAllByWhere</code>
     * <p>The remove all by where method.</p>
     * @param removeWhereSql {@link java.lang.String} <p>The remove where sql parameter is <code>String</code> type.</p>
     * @param tableName      {@link java.lang.String} <p>The table name parameter is <code>String</code> type.</p>
     * @param filter         F <p>The filter parameter is <code>F</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    protected void removeAllByWhere(String removeWhereSql, String tableName, F filter) throws RestException {
        if (!(superMapper instanceof RemoveMapper)) {
            throw new UnsupportedErrorException("The mapper is not support method of 'removeAllByWhere' with the delete model is 'REMOVE', it must to extends 'RemoveMapper'.");
        }
        Object logic = markOfLogic();
        if (isBeforeSkip() && isAfterSkip()) {
            if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tableName)) {
                ((RemoveMapper<I>) superMapper).removeDynamicAllByWhere(tableName, removeWhereSql, logic);
            } else {
                ((RemoveMapper<I>) superMapper).removeAllByWhere(removeWhereSql, logic);
            }
        } else {
            String queryWhereSql = queryWhereSql(filter);
            List<E> entityList = superMapper.findAllByWhere(queryWhereSql);
            if (GeneralUtils.isNotEmpty(entityList)) {
                removeAdvice(entityList, logic, sign -> {
                    if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tableName)) {
                        ((RemoveMapper<I>) superMapper).removeDynamicAllByWhere(tableName, removeWhereSql, sign);
                    } else {
                        ((RemoveMapper<I>) superMapper).removeAllByWhere(removeWhereSql, sign);
                    }
                });
            }
        }
    }

    /**
     * <code>operateAllByWhere</code>
     * <p>The operate all by where method.</p>
     * @param operateWhereSql {@link java.lang.String} <p>The operate where sql parameter is <code>String</code> type.</p>
     * @param tableName       {@link java.lang.String} <p>The table name parameter is <code>String</code> type.</p>
     * @param filter          F <p>The filter parameter is <code>F</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    protected void operateAllByWhere(String operateWhereSql, String tableName, F filter) throws RestException {
        if (!(superMapper instanceof OperateMapper)) {
            throw new UnsupportedErrorException("The mapper is not support method of 'operateAllByWhere' with the delete model is 'OPERATE', it must to extends 'OperateMapper'.");
        }
        OperateType operateType = GeneralUtils.isNotEmpty(filter.getOperate()) ? filter.getOperate() : OperateType.REMOVE;
        if (isBeforeSkip() && isAfterSkip()) {
            if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tableName)) {
                ((OperateMapper<I>) superMapper).operateDynamicAllByWhere(tableName, operateWhereSql, operateType.getKey());
            } else {
                ((OperateMapper<I>) superMapper).operateAllByWhere(operateWhereSql, operateType.getKey());
            }
        } else {
            String queryWhereSql = queryWhereSql(filter);
            List<E> entityList = superMapper.findAllByWhere(queryWhereSql);
            if (GeneralUtils.isNotEmpty(entityList)) {
                operateAdvice(entityList, operateType, operate -> {
                    if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tableName)) {
                        ((OperateMapper<I>) superMapper).operateDynamicAllByWhere(tableName, operateWhereSql, operate.getKey());
                    } else {
                        ((OperateMapper<I>) superMapper).operateAllByWhere(operateWhereSql, operate.getKey());
                    }
                });
            }
        }
    }

    /**
     * <code>alertAllByWhere</code>
     * <p>The alert all by where method.</p>
     * @param <S>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param alertWhereSql {@link java.lang.String} <p>The alert where sql parameter is <code>String</code> type.</p>
     * @param tableName     {@link java.lang.String} <p>The table name parameter is <code>String</code> type.</p>
     * @param filter        F <p>The filter parameter is <code>F</code> type.</p>
     * @param status        S <p>The status parameter is <code>S</code> type.</p>
     * @param statusName    {@link io.github.nichetoolkit.rest.RestKey} <p>The status name parameter is <code>RestKey</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestKey
     * @see io.github.nichetoolkit.rest.RestException
     */
    protected <S> void alertAllByWhere(String alertWhereSql, String tableName, F filter,S status,RestKey<String> statusName) throws RestException {
        if (isBeforeSkip() && isAfterSkip()) {
            alertAllWhere(tableName,alertWhereSql,status,statusName);
        } else {
            String queryWhereSql = queryWhereSql(filter);
            List<E> entityList = superMapper.findAllByWhere(queryWhereSql);
            if (GeneralUtils.isNotEmpty(entityList)) {
                alertAdvice(entityList, status, alertStatus -> alertAllWhere(tableName,alertWhereSql,alertStatus,statusName));
            }
        }
    }

    /**
     * <code>DEFAULT_CREATE_ACTUATOR</code>
     * {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>The <code>DEFAULT_CREATE_ACTUATOR</code> field.</p>
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     */
    protected final BiConsumerActuator<RestTableKey<K>, M> DEFAULT_CREATE_ACTUATOR = (RestTableKey<K> tableKey, @NonNull M model) -> {
        DefaultIdResolver.resolveIdentity(model);
        optionalInit(model);
        optional(model);
        if (createActuator != null) {
            createActuator.actuate(tableKey, model);
        }
        if (model instanceof RestSave) {
            RestSave saveModel = (RestSave) model;
            /* isSaveLower */
            if (saveModel.getSave().getKey() < SaveType.CREATE.getKey()) {
                saveModel.setSave(SaveType.CREATE);
            }
        }
    };

    /**
     * <code>DEFAULT_UPDATE_ACTUATOR</code>
     * {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>The <code>DEFAULT_UPDATE_ACTUATOR</code> field.</p>
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("unchecked")
    private final BiConsumerActuator<RestTableKey<K>, M> DEFAULT_UPDATE_ACTUATOR = (RestTableKey<K> tableKey, @NonNull M model) -> {
        if (isIdentityOfExistsCheck()) {
            boolean exist;
            if (isDynamicOfTable() && model instanceof RestTableKey) {
                exist = existById(((RestTableKey<K>) model), model.getId());
            } else {
                exist = existById(model.getId());
            }
            String message = "The data no found，id: " + model.getId();
            OptionalUtils.ofFalse(exist, message, "id", log, DataQueryException::new);
        }
        optional(model);
        if (updateActuator != null) {
            updateActuator.actuate(tableKey, model);
        }
        if (model instanceof RestSave) {
            RestSave saveModel = (RestSave) model;
            /* isSaveLower */
            if (saveModel.getSave().getKey() < SaveType.UPDATE.getKey()) {
                saveModel.setSave(SaveType.UPDATE);
            }
        }

    };

    /**
     * <code>DEFAULT_SAVE_ACTUATOR</code>
     * {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>The <code>DEFAULT_SAVE_ACTUATOR</code> field.</p>
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings(value = "unchecked")
    private final BiConsumerActuator<RestTableKey<K>, M> DEFAULT_SAVE_ACTUATOR = (RestTableKey<K> tableKey, @NonNull M model) -> {
        if (isIdentityOfExistsCheck()) {
            boolean exist;
            if (isDynamicOfTable() && model instanceof RestTableKey) {
                exist = existById((RestTableKey<K>) model, model.getId());
            } else {
                exist = existById(model.getId());
            }
            if (!exist && isIdentityOfInvade()) {
                invadeActuator().actuate(tableKey, model);
            } else {
                String message = "The data no found，id: " + model.getId();
                OptionalUtils.ofFalse(exist, message, "id", log, DataQueryException::new);
                optional(model);
                if (updateActuator != null) {
                    updateActuator.actuate(tableKey, model);
                }
                if (model instanceof RestSave) {
                    RestSave saveModel = (RestSave) model;
                    /* isSaveLower */
                    if (saveModel.getSave().getKey() < SaveType.UPDATE.getKey()) {
                        saveModel.setSave(SaveType.UPDATE);
                    }
                }
            }
        } else {
            updateActuator().actuate(tableKey, model);
        }
    };

    /**
     * <code>DEFAULT_INVADE_ACTUATOR</code>
     * {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>The <code>DEFAULT_INVADE_ACTUATOR</code> field.</p>
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     */
    private final BiConsumerActuator<RestTableKey<K>, M> DEFAULT_INVADE_ACTUATOR = (RestTableKey<K> tableKey, @NonNull M model) -> {
        optionalInit(model);
        optional(model);
        if (createActuator != null) {
            createActuator.actuate(tableKey, model);
        }
        if (model instanceof RestSave) {
            RestSave saveModel = (RestSave) model;
            /* isSaveLower */
            if (saveModel.getSave().getKey() < SaveType.CREATE.getKey()) {
                saveModel.setSave(SaveType.CREATE);
            }
        }
    };

    /**
     * <code>refresh</code>
     * <p>The refresh method.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    protected void refresh() throws RestException {
    }

    /**
     * <code>queryFilter</code>
     * <p>The query filter method.</p>
     * @return F <p>The query filter return object is <code>F</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    protected F queryFilter() throws RestException {
        return this.queryFilterCache.get();
    }

    /**
     * <code>tableNames</code>
     * <p>The table names method.</p>
     * @return {@link java.util.Map} <p>The table names return object is <code>Map</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Map
     * @see io.github.nichetoolkit.rest.RestException
     */
    protected Map<K, String> tableNames() throws RestException {
        return this.tableNameCaches.get();
    }

    /**
     * <code>createActuator</code>
     * <p>The create actuator method.</p>
     * @return {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>The create actuator return object is <code>BiConsumerActuator</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     */
    protected BiConsumerActuator<RestTableKey<K>, M> createActuator() {
        return DEFAULT_CREATE_ACTUATOR;
    }

    /**
     * <code>updateActuator</code>
     * <p>The update actuator method.</p>
     * @return {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>The update actuator return object is <code>BiConsumerActuator</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     */
    protected BiConsumerActuator<RestTableKey<K>, M> updateActuator() {
        return DEFAULT_UPDATE_ACTUATOR;
    }

    /**
     * <code>saveActuator</code>
     * <p>The save actuator method.</p>
     * @return {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>The save actuator return object is <code>BiConsumerActuator</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     */
    protected BiConsumerActuator<RestTableKey<K>, M> saveActuator() {
        return DEFAULT_SAVE_ACTUATOR;
    }

    /**
     * <code>invadeActuator</code>
     * <p>The invade actuator method.</p>
     * @return {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>The invade actuator return object is <code>BiConsumerActuator</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     */
    protected BiConsumerActuator<RestTableKey<K>, M> invadeActuator() {
        return DEFAULT_INVADE_ACTUATOR;
    }

    /**
     * <code>isFickleField</code>
     * <p>The is fickle field method.</p>
     * @return boolean <p>The is fickle field return object is <code>boolean</code> type.</p>
     */
    protected boolean isFickleField() {
        return ServiceHolder.fickleField();
    }

    /**
     * <code>isFickleOfAuto</code>
     * <p>The is fickle of auto method.</p>
     * @return boolean <p>The is fickle of auto return object is <code>boolean</code> type.</p>
     */
    protected boolean isFickleOfAuto() {
        return ServiceHolder.fickleOfAuto();
    }

    /**
     * <code>isIdentityOfInvade</code>
     * <p>The is identity of invade method.</p>
     * @return boolean <p>The is identity of invade return object is <code>boolean</code> type.</p>
     */
    protected boolean isIdentityOfInvade() {
        return ServiceHolder.identityOfInvade();
    }

    /**
     * <code>isIdentityOfExistsCheck</code>
     * <p>The is identity of exists check method.</p>
     * @return boolean <p>The is identity of exists check return object is <code>boolean</code> type.</p>
     */
    protected boolean isIdentityOfExistsCheck() {
        return ServiceHolder.identityOfCheck();
    }

    /**
     * <code>isNameOfNonnull</code>
     * <p>The is name of nonnull method.</p>
     * @return boolean <p>The is name of nonnull return object is <code>boolean</code> type.</p>
     */
    protected boolean isNameOfNonnull() {
        return ServiceHolder.nameOfNonnull();
    }

    /**
     * <code>isNameOfUnique</code>
     * <p>The is name of unique method.</p>
     * @return boolean <p>The is name of unique return object is <code>boolean</code> type.</p>
     */
    protected boolean isNameOfUnique() {
        return ServiceHolder.nameOfUnique();
    }

    /**
     * <code>isModelOfUnique</code>
     * <p>The is model of unique method.</p>
     * @return boolean <p>The is model of unique return object is <code>boolean</code> type.</p>
     */
    protected boolean isModelOfUnique() {
        return ServiceHolder.modelOfUnique();
    }

    /**
     * <code>isDynamicOfTable</code>
     * <p>The is dynamic of table method.</p>
     * @return boolean <p>The is dynamic of table return object is <code>boolean</code> type.</p>
     */
    protected boolean isDynamicOfTable() {
        return ServiceHolder.dynamicOfTable();
    }

    /**
     * <code>isBeforeSkip</code>
     * <p>The is before skip method.</p>
     * @return boolean <p>The is before skip return object is <code>boolean</code> type.</p>
     */
    protected boolean isBeforeSkip() {
        return ServiceHolder.skipOfBefore();
    }

    /**
     * <code>isAfterSkip</code>
     * <p>The is after skip method.</p>
     * @return boolean <p>The is after skip return object is <code>boolean</code> type.</p>
     */
    protected boolean isAfterSkip() {
        return ServiceHolder.skipOfAfter();
    }

    /**
     * <code>useSaveResult</code>
     * <p>The use save result method.</p>
     * @return boolean <p>The use save result return object is <code>boolean</code> type.</p>
     */
    protected boolean useSaveResult() {
        return !ServiceHolder.ignoredOfSaveResult();
    }

    /**
     * <code>partitionOfQuery</code>
     * <p>The partition of query method.</p>
     * @return int <p>The partition of query return object is <code>int</code> type.</p>
     */
    protected int partitionOfQuery() {
        return ServiceHolder.partitionOfQuery();
    }

    /**
     * <code>partitionOfSave</code>
     * <p>The partition of save method.</p>
     * @return int <p>The partition of save return object is <code>int</code> type.</p>
     */
    protected int partitionOfSave() {
        return ServiceHolder.partitionOfSave();
    }

    /**
     * <code>partitionOfDelete</code>
     * <p>The partition of delete method.</p>
     * @return int <p>The partition of delete return object is <code>int</code> type.</p>
     */
    protected int partitionOfDelete() {
        return ServiceHolder.partitionOfDelete();
    }

    /**
     * <code>optionalName</code>
     * <p>The optional name method.</p>
     * @param model M <p>The model parameter is <code>M</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    protected void optionalName(@NonNull M model) throws RestException {
    }

    /**
     * <code>optionalInit</code>
     * <p>The optional init method.</p>
     * @param model M <p>The model parameter is <code>M</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    protected void optionalInit(@NonNull M model) throws RestException {
    }

    /**
     * <code>optionalTableName</code>
     * <p>The optional table name method.</p>
     * @param tableName {@link java.lang.String} <p>The table name parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    protected void optionalTableName(@NonNull String tableName) throws RestException {
    }

    /**
     * <code>dynamicTableName</code>
     * <p>The dynamic table name method.</p>
     * @param tableKey K <p>The table key parameter is <code>K</code> type.</p>
     * @return {@link java.lang.String} <p>The dynamic table name return object is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.lang.NonNull
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    protected String dynamicTableName(@NonNull K tableKey) throws RestException {
        return null;
    }

    /**
     * <code>afterSuperHandle</code>
     * <p>The after super handle method.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    protected void afterSuperHandle() throws RestException {
    }

    /**
     * <code>createEntity</code>
     * <p>The create entity method.</p>
     * @param model M <p>The model parameter is <code>M</code> type.</p>
     * @return E <p>The create entity return object is <code>E</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    protected abstract E createEntity(M model) throws RestException;

    /**
     * <code>createModel</code>
     * <p>The create model method.</p>
     * @param entity E <p>The entity parameter is <code>E</code> type.</p>
     * @return M <p>The create model return object is <code>M</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    protected abstract M createModel(E entity) throws RestException;

    @Override
    public String resolveTableName(RestTableKey<K> tableKey) throws RestException {
        return tableName(Optional.ofNullable(tableKey).map(RestTableKey::getTableKey).orElse(null));
    }

    @Override
    public String resolveTableName(RestTableKey<K> tableKey, M model) throws RestException {
        return tableName(Optional.ofNullable(tableKey).map(RestTableKey::getTableKey).orElse(null), model);
    }

    @Override
    public String resolveTableName(RestTableKey<K> tableKey, Collection<M> modelList) throws RestException {
        return tableName(Optional.ofNullable(tableKey).map(RestTableKey::getTableKey).orElse(null), modelList);
    }

    /**
     * <code>deleteMode</code>
     * <p>The delete mode method.</p>
     * @return {@link io.github.nichetoolkit.rice.enums.DeleteMode} <p>The delete mode return object is <code>DeleteMode</code> type.</p>
     * @see io.github.nichetoolkit.rice.enums.DeleteMode
     */
    public DeleteMode deleteMode() {
        return ServiceHolder.deleteMode();
    }

    /**
     * <code>logicMode</code>
     * <p>The logic mode method.</p>
     * @return {@link io.github.nichetoolkit.rice.enums.LogicMode} <p>The logic mode return object is <code>LogicMode</code> type.</p>
     * @see io.github.nichetoolkit.rice.enums.LogicMode
     */
    public LogicMode logicMode() {
        return ServiceHolder.logicMode();
    }

    /**
     * <code>judgeOfAccurate</code>
     * <p>The judge of accurate method.</p>
     * @return {@link java.lang.Boolean} <p>The judge of accurate return object is <code>Boolean</code> type.</p>
     * @see java.lang.Boolean
     */
    public Boolean judgeOfAccurate() {
        return ServiceHolder.judgeOfAccurate();
    }

    /**
     * <code>markOfLogic</code>
     * <p>The mark of logic method.</p>
     * @return {@link java.lang.Object} <p>The mark of logic return object is <code>Object</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Object
     * @see io.github.nichetoolkit.rest.RestException
     */
    public Object markOfLogic() throws RestException {
        return ServiceHolder.markOfLogic();
    }

    /**
     * <code>unmarkOfLogic</code>
     * <p>The unmark of logic method.</p>
     * @return {@link java.lang.Object} <p>The unmark of logic return object is <code>Object</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Object
     * @see io.github.nichetoolkit.rest.RestException
     */
    public Object unmarkOfLogic() throws RestException {
        return ServiceHolder.unmarkOfLogic();
    }

}
