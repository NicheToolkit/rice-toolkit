package io.github.nichetoolkit.rice.service;

import com.github.pagehelper.Page;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestKey;
import io.github.nichetoolkit.rest.actuator.AnchorActuator;
import io.github.nichetoolkit.rest.actuator.BiConsumerActuator;
import io.github.nichetoolkit.rest.actuator.ConsumerActuator;
import io.github.nichetoolkit.rest.actuator.FunctionActuator;
import io.github.nichetoolkit.rest.error.data.DataQueryException;
import io.github.nichetoolkit.rest.error.natives.UnsupportedErrorException;
import io.github.nichetoolkit.rest.helper.PartitionHelper;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.util.JsonUtils;
import io.github.nichetoolkit.rest.util.OptionalUtils;
import io.github.nichetoolkit.rice.*;
import io.github.nichetoolkit.rice.advice.*;
import io.github.nichetoolkit.rice.enums.DeleteMode;
import io.github.nichetoolkit.rice.enums.OperateType;
import io.github.nichetoolkit.rice.enums.RemoveMode;
import io.github.nichetoolkit.rice.enums.SaveType;
import io.github.nichetoolkit.rice.error.service.ServiceUnknownException;
import io.github.nichetoolkit.rice.error.table.TablenameIsNullException;
import io.github.nichetoolkit.rice.filter.IdFilter;
import io.github.nichetoolkit.rice.helper.MEBuilderHelper;
import io.github.nichetoolkit.rice.mapper.*;
import io.github.nichetoolkit.rice.mapper.natives.*;
import io.github.nichetoolkit.rice.service.extend.OptionalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.*;

@Slf4j
@SuppressWarnings("RedundantThrows")
public abstract class SuperService<M extends RestId<I>, E extends RestId<I>, F extends IdFilter<I, K>, I, K>
        implements InitializingBean, OptionalService<M, F, I, K>, FilterAdvice<F, I, K>, TablenameAdvice<M, I, K>,
        SaveAdvice<M, I>, AlertAdvice<I>, OperateAdvice<E, I>, DeleteAdvice<E, I>, RemoveAdvice<E, I>, MutateAdvice<M, E, I> {

    private String simpleName;

    private final ThreadLocal<F> queryFilterCache = new ThreadLocal<>();

    private final ThreadLocal<Map<K, String>> tablenameCaches = new ThreadLocal<>();

    protected BiConsumerActuator<K, M> createActuator;

    protected BiConsumerActuator<K, M> updateActuator;

    protected SuperMapper<E, I> superMapper;

    @Override
    public void afterPropertiesSet() throws Exception {
        ServiceHolder.initOfService();
        this.simpleName = this.getClass().getSimpleName();
        String commonName = ServiceHolder.nameOfCommon(this.simpleName);
        String camelName = GeneralUtils.camelCase(commonName);
        this.superMapper = ServiceHolder.findSuperMapper(this.getClass());
        String message ="the service and mapper name must be like 'xxxService'/'xxxServiceImpl' and 'xxxMapper'.";
        OptionalUtils.ofNullException(this.superMapper,message,this.simpleName,ServiceUnknownException::new);
        this.afterSuperHandle();
    }

    private void logicActuator(E entity, M model) throws RestException {
        if (entity instanceof RestLogic && model instanceof RestLogic) {
            RestLogic logicEntity = (RestLogic) entity;
            RestLogic logicModel = (RestLogic) model;
            logicEntity.setLogic(logicModel.getLogic());
        }
    }

    @SuppressWarnings(value = "unchecked")
    private E entityActuator(M model, Object... idArray) throws RestException {
        E entity = this.createEntity(model);
        logicActuator(entity, model);
        if (BuilderAdvice.class.isAssignableFrom(this.getClass())) {
            BuilderAdvice<M, E, I> builderAdvice = (BuilderAdvice<M, E, I>) this;
            builderAdvice.buildEntity(model, entity, idArray);
        }
        return entity;
    }


    @SuppressWarnings(value = "unchecked")
    private List<E> entityActuator(Collection<M> modelList, ConsumerActuator<M> actuator, Object... idArray) throws RestException {
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

    @SuppressWarnings(value = "unchecked")
    private M modelActuator(E entity, Boolean... isLoadArray) throws RestException {
        M model = this.createModel(entity);
        if (BuilderAdvice.class.isAssignableFrom(this.getClass())) {
            BuilderAdvice<M, E, I> builderAdvice = (BuilderAdvice<M, E, I>) this;
            builderAdvice.buildModel(entity, model, isLoadArray);
        }
        return model;
    }


    @SuppressWarnings(value = "unchecked")
    private List<M> modelActuator(Collection<E> entityList, ConsumerActuator<E> actuator, Boolean... isLoadArray) throws RestException {
        List<M> modelList;
        if (BuilderAdvice.class.isAssignableFrom(this.getClass())) {
            BuilderAdvice<M, E, I> builderAdvice = (BuilderAdvice<M, E, I>) this;
            Method findMethod = null;
            try {
                findMethod = builderAdvice.getClass().getMethod("buildModelList", Collection.class, List.class, Boolean[].class);
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

    private void optionalLogic(@NonNull M model) throws RestException {
        if (model instanceof RestLogic) {
            RestLogic logicModel = (RestLogic) model;
            logicModel.setLogic(Optional.ofNullable(logicModel.getLogic()).orElse(valueOfLogic()));
        }
        optionalName(model);
    }

    @SuppressWarnings(value = "unchecked")
    private void optionalDynamicTable(K tablekey, @NonNull M model) throws RestException {
        if (isDynamicOfTable()) {
            Map<K, String> tablenameMap = tablenameCaches.get();
            if (GeneralUtils.isEmpty(tablenameMap)) {
                tablenameMap = new HashMap<>();
                tablenameCaches.set(tablenameMap);
            }
            if (GeneralUtils.isEmpty(tablekey) && model instanceof RestTablekey) {
                RestTablekey<K> tablekeyModel = (RestTablekey<K>) model;
                tablekey = tablekeyModel.getTablekey();
            }
            if (GeneralUtils.isEmpty(tablekey)) {
                return;
            }
            String tablename = tablenameMap.get(tablekey);
            if (GeneralUtils.isNotEmpty(tablename)) {
                return;
            }
            tablename = dynamicTablename(tablekey);
            if (GeneralUtils.isNotEmpty(tablename)) {
                optionalTablename(tablename);
                tablenameMap.put(tablekey, tablename);
            }
        }
        optionalLogic(model);
    }

    private void optionalCreate(K tablekey, @NonNull M model) throws RestException {
        optionalDynamicTable(tablekey, model);
        if (GeneralUtils.isEmpty(model.getId()) || !isIdentityOfInvade()) {
            createActuator().actuate(tablekey, model);
        } else {
            invadeActuator().actuate(tablekey, model);
        }
    }

    private void optionalUpdate(K tablekey, @NonNull M model) throws RestException {
        OptionalUtils.ofIdEmpty(model.getId());
        optionalDynamicTable(tablekey, model);
        updateActuator().actuate(tablekey, model);
    }

    private void optionalSave(K tablekey, @NonNull M model) throws RestException {
        optionalDynamicTable(tablekey, model);
        if (GeneralUtils.isEmpty(model.getId())) {
            createActuator().actuate(tablekey, model);
        } else {
            saveActuator().actuate(tablekey, model);
        }
    }

    private String tablename(K tablekey) throws RestException {
        if (GeneralUtils.isEmpty(tablekey)) {
            return null;
        }
        Map<K, String> tablenameMap = tablenameCaches.get();
        if (GeneralUtils.isEmpty(tablenameMap)) {
            tablenameMap = new HashMap<>();
            tablenameCaches.set(tablenameMap);
        }
        if (isDynamicOfTable()) {
            String tablename = tablenameMap.get(tablekey);
            if (GeneralUtils.isEmpty(tablename)) {
                tablename = dynamicTablename(tablekey);
                if (GeneralUtils.isNotEmpty(tablename)) {
                    optionalTablename(tablename);
                    tablenameMap.put(tablekey, tablename);
                } else {
                    throw new TablenameIsNullException("the tablename is null, the method of 'dynamicTablename' maybe to override it.");
                }
            }
            return tablename;
        }
        return null;
    }

    @SuppressWarnings(value = "unchecked")
    private String tablename(K tablekey, M model) throws RestException {
        if (GeneralUtils.isEmpty(tablekey) && GeneralUtils.isEmpty(model)) {
            return null;
        }
        Map<K, String> tablenameMap = tablenameCaches.get();
        if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablenameMap)) {
            if (GeneralUtils.isNotEmpty(tablekey)) {
                return tablenameMap.get(tablekey);
            } else if (GeneralUtils.isNotEmpty(model) && model instanceof RestTablekey) {
                RestTablekey<K> table = (RestTablekey<K>) model;
                return tablenameMap.get(table.getTablekey());
            }
        }
        return null;
    }

    private String tablename(K tablekey, Collection<M> modelList) throws RestException {
        if (GeneralUtils.isEmpty(tablekey) && GeneralUtils.isEmpty(modelList)) {
            return null;
        } else if (GeneralUtils.isNotEmpty(tablekey)) {
            Map<K, String> tablenameMap = tablenameCaches.get();
            if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablenameMap)) {
                return tablenameMap.get(tablekey);
            }
        } else if (GeneralUtils.isNotEmpty(modelList)) {
            Optional<M> optionalAny = modelList.stream().findAny();
            if (optionalAny.isPresent()) {
                M model = optionalAny.get();
                return tablename(tablekey, model);
            }
        }
        return null;
    }

    private Integer single(K tablekey, M model, Object... idArray) throws RestException {
        E entity = entityActuator(model, idArray);
        String tablename = tablename(tablekey, model);
        Integer result;
        if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
            result = superMapper.saveDynamic(tablename, entity);
        } else {
            result = superMapper.save(entity);
        }
        return result;
    }


    private E findById(I id, String tablename) throws RestException {
        E entity;
        if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
            entity = superMapper.findDynamicById(tablename, id);
        } else {
            entity = superMapper.findById(id);
        }
        return entity;
    }


    @SuppressWarnings(value = "unchecked")
    private void operatePartition(String tablename, Collection<I> idList, OperateType operate) throws RestException {
        if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
            PartitionHelper.partition(idList, this.partitionOfDelete(), ids -> ((OperateMapper<I>) superMapper).operateDynamicAll(tablename, ids, operate.getKey()));
        } else {
            PartitionHelper.partition(idList, this.partitionOfDelete(), ids -> ((OperateMapper<I>) superMapper).operateAll(ids, operate.getKey()));
        }
    }

    private void operateAdvice(List<E> entityList, OperateType operate, ConsumerActuator<OperateType> operateActuator) throws RestException {
        if (DeleteMode.OPERATE == deleteMode() && !isBeforeSkip()) {
            this.beforeOperateAll(entityList);
        }
        operateActuator.actuate(operate);
        if (DeleteMode.OPERATE == deleteMode() && !isAfterSkip()) {
            this.afterOperateAll(entityList);
        }
        this.refresh();
    }

    private List<E> findAll(Collection<I> idList, String tablename) throws RestException {
        List<E> entityList;
        if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
            entityList = PartitionHelper.query(idList, this.partitionOfQuery(), ids -> superMapper.findDynamicAll(tablename, ids));
        } else {
            entityList = PartitionHelper.query(idList, this.partitionOfQuery(), superMapper::findAll);
        }
        return entityList;
    }

    @SuppressWarnings(value = "unchecked")
    private void removePartition(String tablename, Collection<I> idList, String logicSign) throws RestException {
        if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
            PartitionHelper.delete(idList, this.partitionOfDelete(), ids -> ((RemoveMapper<I>) superMapper).removeDynamicAll(tablename, ids, logicSign));
        } else {
            PartitionHelper.delete(idList, this.partitionOfDelete(), ids -> ((RemoveMapper<I>) superMapper).removeAll(ids, logicSign));
        }
    }

    private void removeAdvice(List<E> entityList, String logicSign, ConsumerActuator<String> removeActuator) throws RestException {
        if (DeleteMode.REMOVE == deleteMode() && !isBeforeSkip()) {
            this.beforeRemoveAll(entityList);
        }
        removeActuator.actuate(logicSign);
        if (DeleteMode.REMOVE == deleteMode() && !isAfterSkip()) {
            this.afterRemoveAll(entityList);
        }
        this.refresh();
    }

    private void partitionOfDelete(String tablename, Collection<I> idList) throws RestException {
        if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
            PartitionHelper.delete(idList, this.partitionOfDelete(), ids -> superMapper.deleteDynamicAll(tablename, ids));
        } else {
            PartitionHelper.delete(idList, this.partitionOfDelete(), ids -> superMapper.deleteAll(ids));
        }
    }

    private void deleteAdvice(List<E> entityList, AnchorActuator deleteActuator) throws RestException {
        if (DeleteMode.DELETE == deleteMode() && !isBeforeSkip()) {
            this.beforeDeleteAll(entityList);
        }
        deleteActuator.actuate();
        if (DeleteMode.DELETE == deleteMode() && !isAfterSkip()) {
            this.afterDeleteAll(entityList);
        }
        this.refresh();
    }

    private List<E> findAllByWhere(String whereSql, String tablename) throws RestException {
        List<E> entityList;
        if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
            entityList = superMapper.findDynamicAllByWhere(tablename, whereSql);
        } else {
            entityList = superMapper.findAllByWhere(whereSql);
        }
        return entityList;
    }

    private void deleteAllByWhere(String whereSql, String tablename, F filter) throws RestException {
        if (DeleteMode.DELETE != deleteMode() || (isBeforeSkip() && isAfterSkip())) {
            if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
                superMapper.deleteDynamicAllByWhere(tablename, whereSql);
            } else {
                superMapper.deleteAllByWhere(whereSql);
            }
        } else {
            String queryWhereSql = queryWhereSql(filter);
            List<E> entityList = superMapper.findAllByWhere(queryWhereSql);
            if (GeneralUtils.isNotEmpty(entityList)) {
                deleteAdvice(entityList, () -> {
                    if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
                        superMapper.deleteDynamicAllByWhere(tablename, whereSql);
                    } else {
                        superMapper.deleteAllByWhere(whereSql);
                    }
                });
            }
        }
    }

    @SuppressWarnings(value = "unchecked")
    private void removeAllByWhere(String removeWhereSql, String tablename, F filter) throws RestException {
        if (!(superMapper instanceof RemoveMapper)) {
            throw new UnsupportedErrorException("the mapper is not support method of 'removeAllWithFilter' with the delete model is 'REMOVE' !");
        }
        String logicSign = signOfLogic();
        if (DeleteMode.REMOVE != deleteMode() || (isBeforeSkip() && isAfterSkip())) {
            if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
                ((RemoveMapper<I>) superMapper).removeDynamicAllByWhere(tablename, removeWhereSql, logicSign);
            } else {
                ((RemoveMapper<I>) superMapper).removeAllByWhere(removeWhereSql, logicSign);
            }
        } else {
            String queryWhereSql = queryWhereSql(filter);
            List<E> entityList = superMapper.findAllByWhere(queryWhereSql);
            if (GeneralUtils.isNotEmpty(entityList)) {
                removeAdvice(entityList, logicSign, sign -> {
                    if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
                        ((RemoveMapper<I>) superMapper).removeDynamicAllByWhere(tablename, removeWhereSql, sign);
                    } else {
                        ((RemoveMapper<I>) superMapper).removeAllByWhere(removeWhereSql, sign);
                    }
                });
            }
        }
    }

    @SuppressWarnings(value = "unchecked")
    private void operateAllByWhere(String operateWhereSql, String tablename, F filter) throws RestException {
        if (!(superMapper instanceof OperateMapper)) {
            throw new UnsupportedErrorException("the mapper is not support method of 'operateAllWithFilter' with the delete model is 'OPERATE' !");
        }
        if (DeleteMode.OPERATE != deleteMode() || (isBeforeSkip() && isAfterSkip())) {
            if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
                ((OperateMapper<I>) superMapper).operateDynamicAllByWhere(tablename, operateWhereSql, OperateType.REMOVE.getKey());
            } else {
                ((OperateMapper<I>) superMapper).operateAllByWhere(operateWhereSql, OperateType.REMOVE.getKey());
            }
        } else {
            String queryWhereSql = queryWhereSql(filter);
            List<E> entityList = superMapper.findAllByWhere(queryWhereSql);
            if (GeneralUtils.isNotEmpty(entityList)) {
                operateAdvice(entityList, OperateType.REMOVE, operate -> {
                    if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
                        ((OperateMapper<I>) superMapper).operateDynamicAllByWhere(tablename, operateWhereSql, operate.getKey());
                    } else {
                        ((OperateMapper<I>) superMapper).operateAllByWhere(operateWhereSql, operate.getKey());
                    }
                });
            }
        }
    }

    private final BiConsumerActuator<K, M> DEFAULT_CREATE_ACTUATOR = (K tablekey, @NonNull M model) -> {
        RestIdResolver.resolveModel(model);
        optionalInit(model);
        optional(model);
        if (createActuator != null) {
            createActuator.actuate(tablekey, model);
        }
        if (model instanceof RestSave) {
            RestSave saveModel = (RestSave) model;
            /* isSaveLower */
            if (saveModel.getSave().getKey() < SaveType.CREATE.getKey()) {
                saveModel.setSave(SaveType.CREATE);
            }
        }
    };

    @SuppressWarnings("unchecked")
    private final BiConsumerActuator<K, M> DEFAULT_UPDATE_ACTUATOR = (K tablekey, @NonNull M model) -> {
        if (isIdentityOfExistsCheck()) {
            boolean exist;
            if (isDynamicOfTable() && model instanceof RestTablekey) {
                exist = existById(((RestTablekey<K>) model).getTablekey(), model.getId());
            } else {
                exist = existById(model.getId());
            }
            String message = "the data no found，id: " + model.getId();
            OptionalUtils.ofFalse(exist, message, "id", DataQueryException::new);
        }
        optional(model);
        if (updateActuator != null) {
            updateActuator.actuate(tablekey, model);
        }
        if (model instanceof RestSave) {
            RestSave saveModel = (RestSave) model;
            /* isSaveLower */
            if (saveModel.getSave().getKey() < SaveType.UPDATE.getKey()) {
                saveModel.setSave(SaveType.UPDATE);
            }
        }

    };

    @SuppressWarnings(value = "unchecked")
    private final BiConsumerActuator<K, M> DEFAULT_SAVE_ACTUATOR = (K tablekey, @NonNull M model) -> {
        if (isIdentityOfExistsCheck()) {
            boolean exist;
            if (isDynamicOfTable() && model instanceof RestTablekey) {
                exist = existById(((RestTablekey<K>) model).getTablekey(), model.getId());
            } else {
                exist = existById(model.getId());
            }
            if (!exist && isIdentityOfInvade()) {
                invadeActuator().actuate(tablekey, model);
            } else {
                String message = "the data no found，id: " + model.getId();
                OptionalUtils.ofFalse(exist, message, "id", DataQueryException::new);
                optional(model);
                if (updateActuator != null) {
                    updateActuator.actuate(tablekey, model);
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
            updateActuator().actuate(tablekey, model);
        }
    };

    private final BiConsumerActuator<K, M> DEFAULT_INVADE_ACTUATOR = (K tablekey, @NonNull M model) -> {
        optionalInit(model);
        optional(model);
        if (createActuator != null) {
            createActuator.actuate(tablekey, model);
        }
        if (model instanceof RestSave) {
            RestSave saveModel = (RestSave) model;
            /* isSaveLower */
            if (saveModel.getSave().getKey() < SaveType.CREATE.getKey()) {
                saveModel.setSave(SaveType.CREATE);
            }
        }
    };

    protected void refresh() throws RestException {
    }

    protected F queryFilter() throws RestException {
        return this.queryFilterCache.get();
    }

    protected Map<K, String> tablenames() throws RestException {
        return this.tablenameCaches.get();
    }

    protected BiConsumerActuator<K, M> createActuator() {
        return DEFAULT_CREATE_ACTUATOR;
    }

    protected BiConsumerActuator<K, M> updateActuator() {
        return DEFAULT_UPDATE_ACTUATOR;
    }

    protected BiConsumerActuator<K, M> saveActuator() {
        return DEFAULT_SAVE_ACTUATOR;
    }

    protected BiConsumerActuator<K, M> invadeActuator() {
        return DEFAULT_INVADE_ACTUATOR;
    }
    
    protected boolean isIdentityOfInvade() {
        return ServiceHolder.identityOfInvade();
    }

    protected boolean isIdentityOfExistsCheck() {
        return ServiceHolder.identityOfCheck();
    }

    protected boolean isNameOfNonnull() {
        return ServiceHolder.nameOfNonnull();
    }

    protected boolean isNameOfUnique() {
        return ServiceHolder.nameOfUnique();
    }

    protected boolean isModelOfUnique() {
        return ServiceHolder.modelOfUnique();
    }

    protected boolean isDynamicOfTable() {
        return ServiceHolder.dynamicOfTable();
    }

    protected boolean signOfBoolean() {
        return ServiceHolder.signOfBoolean();
    }

    protected boolean valueOfBoolean() {
        return ServiceHolder.valueOfBoolean();
    }

    protected int signOfNumber() {
        return ServiceHolder.signOfNumber();
    }

    protected int valueOfNumber() {
        return ServiceHolder.valueOfNumber();
    }

    protected boolean isBeforeSkip() {
        return ServiceHolder.skipOfBefore();
    }

    protected boolean isAfterSkip() {
        return ServiceHolder.skipOfAfter();
    }

    protected int partitionOfQuery() {
        return ServiceHolder.partitionOfQuery();
    }

    protected int partitionOfSave() {
        return ServiceHolder.partitionOfSave();
    }

    protected int partitionOfDelete() {
        return ServiceHolder.partitionOfDelete();
    }
    
    

    protected void optionalName(@NonNull M model) throws RestException {
    }

    protected void optionalInit(@NonNull M model) throws RestException {
    }

    protected void optionalTablename(@NonNull String tablename) throws RestException {
    }

    protected String dynamicTablename(@NonNull K tablekey) throws RestException {
        return null;
    }


    protected abstract E createEntity(M model) throws RestException;

    protected abstract M createModel(E entity) throws RestException;

    protected abstract void afterSuperHandle() throws RestException;

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public M create(M model, Object... idArray) throws RestException {
        return create(null, model, idArray);
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public M create(K tablekey, M model, Object... idArray) throws RestException {
        if (GeneralUtils.isEmpty(model)) {
            return null;
        }
        optionalCreate(tablekey, model);
        this.beforeCreate(model);
        Integer result = single(tablekey, model, idArray);
        String message = "creating method has error with " + simpleName + ": " + JsonUtils.parseJson(model);
        OptionalUtils.ofCreate(result, message, simpleName);
        this.afterCreate(model);
        this.refresh();
        return model;
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public M update(M model, Object... idArray) throws RestException {
        return update(null, model, idArray);
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public M update(K tablekey, M model, Object... idArray) throws RestException {
        if (GeneralUtils.isEmpty(model)) {
            return null;
        }
        optionalUpdate(tablekey, model);
        this.beforeUpdate(model);
        Integer result = single(tablekey, model, idArray);
        String message = "updating method has error with " + simpleName + ": " + JsonUtils.parseJson(model);
        OptionalUtils.ofUpdate(result, message, simpleName);
        this.afterUpdate(model);
        this.refresh();
        return model;
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public M save(M model, Object... idArray) throws RestException {
        return save(null, model, idArray);
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public M save(K tablekey, M model, Object... idArray) throws RestException {
        if (GeneralUtils.isEmpty(model)) {
            return null;
        }
        optionalSave(tablekey, model);
        this.beforeSave(model);
        Integer result = single(tablekey, model, idArray);
        String message = "saving method has error with " + simpleName + ": " + JsonUtils.parseJson(model);
        OptionalUtils.ofSave(result, message, simpleName);
        this.afterSave(model);
        this.refresh();
        return model;
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public List<M> saveAll(Collection<M> modelList) throws RestException {
        return saveAll(modelList, (Object[]) null);
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public List<M> saveAll(Collection<M> modelList, Object... idArray) throws RestException {
        return saveAll(null, modelList, idArray);
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public List<M> saveAll(K tablekey, Collection<M> modelList, Object... idArray) throws RestException {
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
                optionalSave(tablekey, model);
            }
            this.beforeSaveAll(modelList);
            entityList = entityActuator(modelList, model -> {
            }, idArray);
        } else {
            entityList = entityActuator(modelList, model -> {
                this.optionalSave(tablekey, model);
                this.beforeSave(model);
            }, idArray);
        }
        String tablename = tablename(tablekey, modelList);
        Integer result;
        if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
            result = PartitionHelper.save(entityList, this.partitionOfSave(), entities -> superMapper.saveDynamicAll(tablename, entities));
        } else {
            result = PartitionHelper.save(entityList, this.partitionOfSave(), superMapper::saveAll);
        }
        Boolean present = modelList.size() == result;
        String message = "saveAll method has error with " + simpleName + ": " + JsonUtils.parseJson(modelList);
        OptionalUtils.ofSaveAll(present, message, simpleName);
        this.afterSaveAll(modelList);
        this.refresh();
        return new ArrayList<>(modelList);
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void operateById(I id, OperateType operate) throws RestException {
        operateById(null, id, operate);
    }

    @SuppressWarnings(value = "unchecked")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void operateById(K tablekey, I id, OperateType operate) throws RestException {
        if (GeneralUtils.isEmpty(id) || GeneralUtils.isEmpty(operate)) {
            return;
        }
        if (superMapper instanceof OperateMapper) {
            String tablename = tablename(tablekey);
            if (DeleteMode.OPERATE != deleteMode() || (isBeforeSkip() && isAfterSkip())) {
                if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
                    ((OperateMapper<I>) superMapper).operateDynamicById(tablename, id, operate.getKey());
                } else {
                    ((OperateMapper<I>) superMapper).operateById(id, operate.getKey());
                }
            } else {
                E entity = findById(id, tablename);
                if (GeneralUtils.isNotEmpty(entity)) {
                    if (DeleteMode.OPERATE == deleteMode() && !isBeforeSkip()) {
                        this.beforeOperate(entity);
                    }
                    if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
                        ((OperateMapper<I>) superMapper).operateDynamicById(tablename, id, operate.getKey());
                    } else {
                        ((OperateMapper<I>) superMapper).operateById(id, operate.getKey());
                    }
                    if (DeleteMode.OPERATE == deleteMode() && !isAfterSkip()) {
                        this.afterOperate(entity);
                    }
                    this.refresh();
                }
            }
        }
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void operateAll(Collection<I> idList, OperateType operate) throws RestException {
        operateAll(null, idList, operate);
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void operateAll(K tablekey, Collection<I> idList, OperateType operate) throws RestException {
        if (GeneralUtils.isEmpty(idList)) {
            return;
        }
        if (superMapper instanceof OperateMapper) {
            String tablename = tablename(tablekey);
            if (DeleteMode.OPERATE != deleteMode() || (isBeforeSkip() && isAfterSkip())) {
                operatePartition(tablename, idList, operate);
            } else {
                List<E> entityList = findAll(idList, tablename);
                if (GeneralUtils.isNotEmpty(entityList)) {
                    operateAdvice(entityList, operate, type -> operatePartition(tablename, idList, type));
                }
            }
        }
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void operateByLinkId(I linkId, OperateType operate) throws RestException {
        operateByLinkId(null, linkId, operate);
    }

    @SuppressWarnings(value = "unchecked")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void operateByLinkId(K tablekey, I linkId, OperateType operate) throws RestException {
        if (GeneralUtils.isEmpty(linkId)) {
            return;
        }
        if (superMapper instanceof OperateLinkMapper) {
            String tablename = tablename(tablekey);
            if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
                ((OperateLinkMapper<I>) superMapper).operateDynamicByLinkId(tablename, linkId, operate.getKey());
            } else {
                ((OperateLinkMapper<I>) superMapper).operateByLinkId(linkId, operate.getKey());
            }
            this.refresh();
        }
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void operateAllByLinkIds(Collection<I> linkIdList, OperateType operate) throws RestException {
        operateAllByLinkIds(null, linkIdList, operate);
    }

    @SuppressWarnings(value = "unchecked")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void operateAllByLinkIds(K tablekey, Collection<I> linkIdList, OperateType operate) throws RestException {
        if (GeneralUtils.isEmpty(linkIdList)) {
            return;
        }
        if (superMapper instanceof OperateLinkMapper) {
            String tablename = tablename(tablekey);
            if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
                PartitionHelper.partition(linkIdList, this.partitionOfDelete(), linkIds -> ((OperateLinkMapper<I>) superMapper).operateDynamicAllByLinkIds(tablename, linkIds, operate.getKey()));
            } else {
                PartitionHelper.partition(linkIdList, this.partitionOfDelete(), linkIds -> ((OperateLinkMapper<I>) superMapper).operateAllByLinkIds(linkIds, operate.getKey()));
            }
            this.refresh();
        }
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void alertById(I id, RestKey<Integer> keyType) throws RestException {
        alertById(null, id, keyType);
    }

    @SuppressWarnings(value = "unchecked")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void alertById(K tablekey, I id, RestKey<Integer> keyType) throws RestException {
        if (GeneralUtils.isEmpty(id) || GeneralUtils.isEmpty(keyType)) {
            return;
        }
        if (superMapper instanceof AlertMapper) {
            String tablename = tablename(tablekey);
            this.beforeAlert(id);
            if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
                ((AlertMapper<I>) superMapper).alertDynamicById(tablename, id, keyType.getKey());
            } else {
                ((AlertMapper<I>) superMapper).alertById(id, keyType.getKey());
            }
            this.afterAlert(id);
            this.refresh();
        }
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void alertAll(Collection<I> idList, RestKey<Integer> keyType) throws RestException {
        alertAll(null, idList, keyType);
    }

    @SuppressWarnings(value = "unchecked")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void alertAll(K tablekey, Collection<I> idList, RestKey<Integer> keyType) throws RestException {
        if (GeneralUtils.isEmpty(idList)) {
            return;
        }
        if (superMapper instanceof AlertMapper) {
            String tablename = tablename(tablekey);
            this.beforeAlertAll(idList);
            if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
                PartitionHelper.partition(idList, this.partitionOfDelete(), ids -> ((AlertMapper<I>) superMapper).alertDynamicAll(tablename, ids, keyType.getKey()));
            } else {
                PartitionHelper.partition(idList, this.partitionOfDelete(), ids -> ((AlertMapper<I>) superMapper).alertAll(ids, keyType.getKey()));
            }
            this.afterAlertAll(idList);
            this.refresh();
        }
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void alertFieldById(I id, String field, RestKey<Integer> keyType) throws RestException {
        alertFieldById(null, id, field, keyType);
    }

    @SuppressWarnings(value = "unchecked")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void alertFieldById(K tablekey, I id, String field, RestKey<Integer> keyType) throws RestException {
        if (GeneralUtils.isEmpty(id) || GeneralUtils.isEmpty(keyType)) {
            return;
        }
        if (superMapper instanceof AlertFieldMapper) {
            String tablename = tablename(tablekey);
            this.beforeAlert(id);
            if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
                ((AlertFieldMapper<I>) superMapper).alertDynamicFieldById(tablename, id, field, keyType.getKey());
            } else {
                ((AlertFieldMapper<I>) superMapper).alertFieldById(id, field, keyType.getKey());
            }
            this.afterAlert(id);
            this.refresh();
        }
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void alertFieldAll(Collection<I> idList, String field, RestKey<Integer> keyType) throws RestException {
        alertFieldAll(null, idList, field, keyType);
    }

    @SuppressWarnings(value = "unchecked")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void alertFieldAll(K tablekey, Collection<I> idList, String field, RestKey<Integer> keyType) throws RestException {
        if (GeneralUtils.isEmpty(idList)) {
            return;
        }
        if (superMapper instanceof AlertFieldMapper) {
            String tablename = tablename(tablekey);
            this.beforeAlertAll(idList);
            if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
                PartitionHelper.partition(idList, this.partitionOfDelete(), ids -> ((AlertFieldMapper<I>) superMapper).alertDynamicFieldAll(tablename, ids, field, keyType.getKey()));
            } else {
                PartitionHelper.partition(idList, this.partitionOfDelete(), ids -> ((AlertFieldMapper<I>) superMapper).alertFieldAll(ids, field, keyType.getKey()));
            }
            this.afterAlertAll(idList);
            this.refresh();
        }
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void removeById(I id) throws RestException {
        removeById(null, id);
    }

    @SuppressWarnings(value = "unchecked")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void removeById(K tablekey, I id) throws RestException {
        if (GeneralUtils.isEmpty(id)) {
            return;
        }
        String logicSign = signOfLogic();
        if (superMapper instanceof RemoveMapper) {
            String tablename = tablename(tablekey);
            if (DeleteMode.REMOVE != deleteMode() || (isBeforeSkip() && isAfterSkip())) {
                if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
                    ((RemoveMapper<I>) superMapper).removeDynamicById(tablename, id, logicSign);
                } else {
                    ((RemoveMapper<I>) superMapper).removeById(id, logicSign);
                }
            } else {
                E entity = findById(id, tablename);
                if (GeneralUtils.isNotEmpty(entity)) {
                    if (DeleteMode.REMOVE == deleteMode() && !isBeforeSkip()) {
                        this.beforeRemove(entity);
                    }
                    if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
                        ((RemoveMapper<I>) superMapper).removeDynamicById(tablename, id, logicSign);
                    } else {
                        ((RemoveMapper<I>) superMapper).removeById(id, logicSign);
                    }
                    if (DeleteMode.REMOVE == deleteMode() && !isAfterSkip()) {
                        this.afterRemove(entity);
                    }
                    this.refresh();
                }
            }
        }
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void removeAll(Collection<I> idList) throws RestException {
        removeAll(null, idList);
    }


    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void removeAll(K tablekey, Collection<I> idList) throws RestException {
        if (GeneralUtils.isEmpty(idList)) {
            return;
        }
        String logicSign = signOfLogic();
        if (superMapper instanceof RemoveMapper) {
            String tablename = tablename(tablekey);
            if (DeleteMode.REMOVE != deleteMode() || (isBeforeSkip() && isAfterSkip())) {
                removePartition(tablename, idList, logicSign);
            } else {
                List<E> entityList = findAll(idList, tablename);
                if (GeneralUtils.isNotEmpty(entityList)) {
                    removeAdvice(entityList, logicSign, sign -> removePartition(tablename, idList, sign));
                }
            }
        }
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void removeByLinkId(I linkId) throws RestException {
        removeByLinkId(null, linkId);
    }

    @SuppressWarnings(value = "unchecked")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void removeByLinkId(K tablekey, I linkId) throws RestException {
        if (GeneralUtils.isEmpty(linkId)) {
            return;
        }
        String logicSign = signOfLogic();
        if (superMapper instanceof RemoveLinkMapper) {
            String tablename = tablename(tablekey);
            if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
                ((RemoveLinkMapper<I>) superMapper).removeDynamicByLinkId(tablename, linkId, logicSign);
            } else {
                ((RemoveLinkMapper<I>) superMapper).removeByLinkId(linkId, logicSign);
            }
            this.refresh();
        }
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void removeAllByLinkIds(Collection<I> linkIdList) throws RestException {
        removeAllByLinkIds(null, linkIdList);
    }

    @SuppressWarnings(value = "unchecked")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void removeAllByLinkIds(K tablekey, Collection<I> linkIdList) throws RestException {
        if (GeneralUtils.isEmpty(linkIdList)) {
            return;
        }
        String logicSign = signOfLogic();
        if (superMapper instanceof RemoveLinkMapper) {
            String tablename = tablename(tablekey);
            if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
                PartitionHelper.partition(linkIdList, this.partitionOfDelete(), linkIds -> ((RemoveLinkMapper<I>) superMapper).removeDynamicAllByLinkIds(tablename, linkIds, logicSign));
            } else {
                PartitionHelper.partition(linkIdList, this.partitionOfDelete(), linkIds -> ((RemoveLinkMapper<I>) superMapper).removeAllByLinkIds(linkIds, logicSign));
            }
            this.refresh();
        }
    }

    @Override
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void deleteById(I id) throws RestException {
        deleteById(null, id);
    }

    @Override
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void deleteById(K tablekey, I id) throws RestException {
        if (GeneralUtils.isEmpty(id)) {
            return;
        }
        DeleteMode deleteModel = deleteMode();
        if (deleteModel == DeleteMode.REMOVE) {
            removeById(tablekey, id);
        } else if (deleteModel == DeleteMode.OPERATE) {
            operateById(tablekey, id, OperateType.REMOVE);
        } else {
            String tablename = tablename(tablekey);
            if (DeleteMode.DELETE != deleteMode() || (isBeforeSkip() && isAfterSkip())) {
                if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
                    superMapper.deleteDynamicById(tablename, id);
                } else {
                    superMapper.deleteById(id);
                }
            } else {
                E entity = findById(id, tablename);
                if (GeneralUtils.isNotEmpty(entity)) {
                    if (DeleteMode.DELETE == deleteMode() && !isBeforeSkip()) {
                        this.beforeDelete(entity);
                    }
                    if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
                        superMapper.deleteDynamicById(tablename, id);
                    } else {
                        superMapper.deleteById(id);
                    }
                    if (DeleteMode.DELETE == deleteMode() && !isAfterSkip()) {
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
    public void deleteAll(K tablekey, Collection<I> idList) throws RestException {
        if (GeneralUtils.isEmpty(idList)) {
            return;
        }
        DeleteMode deleteModel = deleteMode();
        if (deleteModel == DeleteMode.REMOVE) {
            removeAll(tablekey, idList);
        } else if (deleteModel == DeleteMode.OPERATE) {
            operateAll(tablekey, idList, OperateType.REMOVE);
        } else {
            String tablename = tablename(tablekey);
            if (DeleteMode.DELETE != deleteMode() || (isBeforeSkip() && isAfterSkip())) {
                partitionOfDelete(tablename, idList);
            } else {
                List<E> entityList = superMapper.findAll(idList);
                if (GeneralUtils.isNotEmpty(entityList)) {
                    deleteAdvice(entityList, () -> partitionOfDelete(tablename, idList));
                }
            }
        }
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void deleteByLinkId(I linkId) throws RestException {
        deleteByLinkId(null, linkId);
    }

    @SuppressWarnings(value = "unchecked")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void deleteByLinkId(K tablekey, I linkId) throws RestException {
        if (GeneralUtils.isEmpty(linkId)) {
            return;
        }
        DeleteMode deleteModel = deleteMode();
        if (deleteModel == DeleteMode.REMOVE) {
            removeByLinkId(tablekey, linkId);
        } else if (deleteModel == DeleteMode.OPERATE) {
            operateByLinkId(tablekey, linkId, OperateType.REMOVE);
        } else if (superMapper instanceof DeleteLinkMapper) {
            String tablename = tablename(tablekey);
            if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
                ((DeleteLinkMapper<I>) superMapper).deleteDynamicByLinkId(tablename, linkId);
            } else {
                ((DeleteLinkMapper<I>) superMapper).deleteByLinkId(linkId);
            }
            this.refresh();
        }
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void deleteAllByLinkIds(Collection<I> linkIdList) throws RestException {
        deleteAllByLinkIds(null, linkIdList);
    }


    @SuppressWarnings(value = "unchecked")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void deleteAllByLinkIds(K tablekey, Collection<I> linkIdList) throws RestException {
        if (GeneralUtils.isEmpty(linkIdList)) {
            return;
        }
        DeleteMode deleteModel = deleteMode();
        if (deleteModel == DeleteMode.REMOVE) {
            removeAllByLinkIds(tablekey, linkIdList);
        } else if (deleteModel == DeleteMode.OPERATE) {
            operateAllByLinkIds(tablekey, linkIdList, OperateType.REMOVE);
        } else if (superMapper instanceof DeleteLinkMapper) {
            String tablename = tablename(tablekey);
            if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
                PartitionHelper.delete(linkIdList, this.partitionOfDelete(), linkIds -> ((DeleteLinkMapper<I>) superMapper).deleteDynamicAllByLinkIds(tablename, linkIds));
            } else {
                PartitionHelper.delete(linkIdList, this.partitionOfDelete(), linkIds -> ((DeleteLinkMapper<I>) superMapper).deleteAllByLinkIds(linkIds));
            }
            this.refresh();
        }
    }

    @Override
    public M queryById(I id, Boolean... isLoadArray) throws RestException {
        return queryById(null, id, isLoadArray);
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public M queryById(K tablekey, I id, Boolean... isLoadArray) throws RestException {
        if (GeneralUtils.isEmpty(id)) {
            return null;
        }
        E entity;
        String tablename = tablename(tablekey);
        if (isLoadArray.length > 0 && FindLoadMapper.class.isAssignableFrom(superMapper.getClass())) {
            FindLoadMapper<E, I> loadMapper = (FindLoadMapper<E, I>) superMapper;
            Method findMethod = null;
            try {
                findMethod = loadMapper.getClass().getMethod("findByIdLoad", id.getClass(), Boolean[].class);
            } catch (NoSuchMethodException ignored) {
            }
            Method queryByIdMethod = findMethod;
            /* 当LoadMapper被复写的时候 优先调用LoadMapper的queryByIdMethod */
            if (queryByIdMethod != null && !queryByIdMethod.isDefault()) {
                if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
                    entity = loadMapper.findDynamicByIdLoad(tablename, id, isLoadArray);
                } else {
                    entity = loadMapper.findByIdLoad(id, isLoadArray);
                }
            } else {
                entity = findById(id, tablename);
            }
        } else {
            entity = findById(id, tablename);
        }
        if (GeneralUtils.isEmpty(entity)) {
            return null;
        }
        return modelActuator(entity, isLoadArray);
    }

    @Override
    public List<M> queryAll(Collection<I> idList, Boolean... isLoadArray) throws RestException {
        return queryAll(null, idList, isLoadArray);
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public List<M> queryAll(K tablekey, Collection<I> idList, Boolean... isLoadArray) throws RestException {
        if (GeneralUtils.isEmpty(idList)) {
            return Collections.emptyList();
        }
        List<E> entityList;
        String tablename = tablename(tablekey);
        if (isLoadArray.length > 0 && FindLoadMapper.class.isAssignableFrom(superMapper.getClass())) {
            FindLoadMapper<E, I> loadMapper = (FindLoadMapper<E, I>) superMapper;
            Method findMethod = null;
            try {
                findMethod = loadMapper.getClass().getMethod("queryAllLoad", List.class, Boolean[].class);
            } catch (NoSuchMethodException ignored) {
            }
            Method queryAllMethod = findMethod;
            /* 当LoadMapper被复写的时候 优先调用LoadMapper的queryByIdMethod */
            if (queryAllMethod != null && !queryAllMethod.isDefault()) {
                if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
                    entityList = PartitionHelper.query(idList, this.partitionOfQuery(), ids -> loadMapper.findDynamicAllLoad(tablename, ids, isLoadArray));
                } else {
                    entityList = PartitionHelper.query(idList, this.partitionOfQuery(), ids -> loadMapper.findAllLoad(ids, isLoadArray));
                }
            } else {
                entityList = findAll(idList, tablename);
            }
        } else {
            entityList = findAll(idList, tablename);
        }
        return modelActuator(entityList, null, isLoadArray);
    }

    @SuppressWarnings(value = "unchecked")
    public RestPage<M> queryAllWithFilter(F filter) throws RestException {
        optionalQueryFilter(filter);
        queryFilterCache.set(filter);
        String whereSql = queryWhereSql(filter);
        Boolean[] loadArray = findLoadArray(filter);
        Boolean[] isLoadArray = queryLoadArray(filter);
        String[] fieldArray = fieldArray(filter);
        K tablekey = tablekey(filter);
        String tablename = tablename(tablekey);
        Page<E> page;
        List<E> entityList;
        if (loadArray.length > 0 && FilterLoadMapper.class.isAssignableFrom(superMapper.getClass())) {
            FilterLoadMapper<E, I> loadFilterMapper = (FilterLoadMapper<E, I>) superMapper;
            Method findMethod = null;
            try {
                findMethod = loadFilterMapper.getClass().getMethod("findAllByLoadWhere", String.class, Boolean[].class);
            } catch (NoSuchMethodException ignored) {
            }
            Method findAllByWhereMethod = findMethod;
            /* 当LoadMapper被复写的时候 优先调用LoadMapper的findAllByWhereMethod */
            if (findAllByWhereMethod != null && !findAllByWhereMethod.isDefault()) {
                page = filter.toPage();
                if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
                    entityList = loadFilterMapper.findDynamicAllByLoadWhere(tablename, whereSql, loadArray);
                } else {
                    entityList = loadFilterMapper.findAllByLoadWhere(whereSql, loadArray);
                }
            } else {
                page = filter.toPage();
                entityList = findAllByWhere(whereSql, tablename);
            }
        } else if (fieldArray.length > 0 && FindFieldMapper.class.isAssignableFrom(superMapper.getClass())) {
            FindFieldMapper<E, I> fieldFilterMapper = (FindFieldMapper<E, I>) superMapper;
            Method findMethod = null;
            try {
                findMethod = fieldFilterMapper.getClass().getMethod("findAllByFieldWhere", String.class, String[].class);
            } catch (NoSuchMethodException ignored) {
            }
            Method findAllByWhereMethod = findMethod;
            /* 当FindMapper被复写的时候 优先调用FindMapper的findAllByWhereMethod */
            if (findAllByWhereMethod != null && !findAllByWhereMethod.isDefault()) {
                page = filter.toPage();
                if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
                    entityList = fieldFilterMapper.findDynamicAllByFieldWhere(tablename, whereSql, fieldArray);
                } else {
                    entityList = fieldFilterMapper.findAllByFieldWhere(whereSql, fieldArray);
                }
            } else {
                page = filter.toPage();
                entityList = findAllByWhere(whereSql, tablename);
            }
        } else if (FindFilterMapper.class.isAssignableFrom(superMapper.getClass())) {
            FindFilterMapper<E, F, I, K> filterMapper = (FindFilterMapper<E, F, I, K>) superMapper;
            Method findMethod = null;
            try {
                findMethod = filterMapper.getClass().getMethod("findAllByFilterWhere", String.class, IdFilter.class);
            } catch (NoSuchMethodException ignored) {
            }
            Method findAllByWhereMethod = findMethod;
            /* 当FindMapper被复写的时候 优先调用FindMapper的findAllByWhereMethod */
            if (findAllByWhereMethod != null && !findAllByWhereMethod.isDefault()) {
                page = filter.toPage();
                if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
                    entityList = filterMapper.findDynamicAllByFilterWhere(tablename, whereSql, filter);
                } else {
                    entityList = filterMapper.findAllByFilterWhere(whereSql, filter);
                }
            } else {
                page = filter.toPage();
                entityList = findAllByWhere(whereSql, tablename);
            }
        } else {
            page = filter.toPage();
            entityList = findAllByWhere(whereSql, tablename);
        }
        List<M> modelList = modelActuator(entityList, null, isLoadArray);
        queryFilterCache.remove();
        return RestPage.result(modelList, page);
    }

    @SuppressWarnings(value = "unchecked")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void deleteAllWithFilter(F filter) throws RestException {
        DeleteMode deleteModel = deleteMode();
        if (deleteModel == DeleteMode.REMOVE) {
            removeAllWithFilter(filter);
        } else if (deleteModel == DeleteMode.OPERATE) {
            operateAllWithFilter(filter);
        } else {
            optionalDeleteFilter(filter);
            String whereSql = deleteWhereSql(filter);
            K tablekey = tablekey(filter);
            String tablename = tablename(tablekey);
            if (GeneralUtils.isNotEmpty(whereSql)) {
                if (DeleteFilterMapper.class.isAssignableFrom(superMapper.getClass())) {
                    DeleteFilterMapper<E, F, I, K> filterMapper = (DeleteFilterMapper<E, F, I, K>) superMapper;
                    Method findMethod = null;
                    Method deleteMethod = null;
                    try {
                        findMethod = filterMapper.getClass().getMethod("findAllByFilterWhere", String.class, IdFilter.class);
                        deleteMethod = filterMapper.getClass().getMethod("deleteAllByFilterWhere", String.class, IdFilter.class);
                    } catch (NoSuchMethodException ignored) {
                    }
                    Method findAllByWhereMethod = findMethod;
                    Method deleteAllByWhereMethod = deleteMethod;
                    if (deleteAllByWhereMethod != null && !deleteAllByWhereMethod.isDefault()) {
                        if (DeleteMode.DELETE != deleteMode() || (isBeforeSkip() && isAfterSkip())) {
                            if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
                                filterMapper.deleteDynamicAllByFilterWhere(tablename, whereSql, filter);
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
                                    if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
                                        filterMapper.deleteDynamicAllByFilterWhere(tablename, whereSql, filter);
                                    } else {
                                        filterMapper.deleteAllByFilterWhere(whereSql, filter);
                                    }
                                });
                            }
                        }
                    } else {
                        deleteAllByWhere(whereSql, tablename, filter);
                    }
                } else {
                    deleteAllByWhere(whereSql, tablename, filter);
                }
            }
        }
    }

    @SuppressWarnings(value = "unchecked")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void removeAllWithFilter(F filter) throws RestException {
        optionalRemoveFilter(filter);
        String removeWhereSql = removeWhereSql(filter);
        K tablekey = tablekey(filter);
        String tablename = tablename(tablekey);
        if (GeneralUtils.isNotEmpty(removeWhereSql)) {
            String logicSign = signOfLogic();
            if (RemoveFilterMapper.class.isAssignableFrom(superMapper.getClass())) {
                RemoveFilterMapper<E, F, I, K> filterMapper = (RemoveFilterMapper<E, F, I, K>) superMapper;
                Method findMethod = null;
                Method removeMethod = null;
                try {
                    findMethod = filterMapper.getClass().getMethod("findAllByFilterWhere", String.class, IdFilter.class);
                    removeMethod = filterMapper.getClass().getMethod("removeAllByFilterWhere", String.class, IdFilter.class, String.class);
                } catch (NoSuchMethodException ignored) {
                }
                Method findAllByWhereMethod = findMethod;
                Method removeAllByWhereMethod = removeMethod;
                if (removeAllByWhereMethod != null && !removeAllByWhereMethod.isDefault()) {
                    if (DeleteMode.REMOVE != deleteMode() || (isBeforeSkip() && isAfterSkip())) {
                        if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
                            filterMapper.removeDynamicAllByFilterWhere(tablename, removeWhereSql, filter, logicSign);
                        } else {
                            filterMapper.removeAllByFilterWhere(removeWhereSql, filter, logicSign);
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
                            removeAdvice(entityList, logicSign, sign -> {
                                if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
                                    filterMapper.removeDynamicAllByFilterWhere(tablename, removeWhereSql, filter, sign);
                                } else {
                                    filterMapper.removeAllByFilterWhere(removeWhereSql, filter, sign);
                                }
                            });
                        }
                    }
                } else {
                    removeAllByWhere(removeWhereSql, tablename, filter);
                }
            } else {
                removeAllByWhere(removeWhereSql, tablename, filter);
            }

        }
    }

    @SuppressWarnings(value = "unchecked")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void operateAllWithFilter(F filter) throws RestException {
        optionalOperateFilter(filter);
        String operateWhereSql = operateWhereSql(filter);
        K tablekey = tablekey(filter);
        String tablename = tablename(tablekey);
        if (GeneralUtils.isNotEmpty(operateWhereSql)) {
            if (OperateFilterMapper.class.isAssignableFrom(superMapper.getClass())) {
                OperateFilterMapper<E, F, I, K> filterMapper = (OperateFilterMapper<E, F, I, K>) superMapper;
                Method findMethod = null;
                Method operateMethod = null;
                try {
                    findMethod = filterMapper.getClass().getMethod("findAllByFilterWhere", String.class, IdFilter.class);
                    operateMethod = filterMapper.getClass().getMethod("operateAllByFilterWhere", String.class, IdFilter.class, Integer.class);
                } catch (NoSuchMethodException ignored) {
                }
                Method findAllByWhereMethod = findMethod;
                Method operateAllByWhereMethod = operateMethod;
                if (operateAllByWhereMethod != null && !operateAllByWhereMethod.isDefault()) {
                    if (DeleteMode.OPERATE != deleteMode() || (isBeforeSkip() && isAfterSkip())) {
                        if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
                            filterMapper.operateDynamicAllByFilterWhere(tablename, operateWhereSql, filter, OperateType.REMOVE.getKey());
                        } else {
                            filterMapper.operateAllByFilterWhere(operateWhereSql, filter, OperateType.REMOVE.getKey());
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
                            operateAdvice(entityList, OperateType.REMOVE, operate -> {
                                if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
                                    filterMapper.operateDynamicAllByFilterWhere(tablename, operateWhereSql, filter, operate.getKey());
                                } else {
                                    filterMapper.operateAllByFilterWhere(operateWhereSql, filter, operate.getKey());
                                }
                            });
                        }
                    }
                } else {
                    operateAllByWhere(operateWhereSql, tablename, filter);
                }
            } else {
                operateAllByWhere(operateWhereSql, tablename, filter);
            }
        }
    }

    public DeleteMode deleteMode() {
        return ServiceHolder.deleteMode();
    }

    public RemoveMode removeMode() {
        return ServiceHolder.removeMode();
    }

    public Boolean judgeOfAccurate() {
        return ServiceHolder.judgeOfAccurate();
    }

    public String signOfLogic() {
        return ServiceHolder.signOfLogic();
    }

    public String valueOfLogic() {
        return ServiceHolder.valueOfLogic();
    }

    @Override
    public M mutateEntity(E model, Boolean... isLoadArray) throws RestException {
        return modelActuator(model, isLoadArray);
    }

    @Override
    public List<M> mutateEntityList(List<E> entityList, ConsumerActuator<E> actuator, Boolean... isLoadArray) throws RestException {
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

    @Override
    public String resolveTablename(K tablekey) throws RestException {
        return tablename(tablekey);
    }

    @Override
    public String resolveTablename(K tablekey, M model) throws RestException {
        return tablename(tablekey, model);
    }

    @Override
    public String resolveTablename(K tablekey, Collection<M> modelList) throws RestException {
        return tablename(tablekey, modelList);
    }

}
