package io.github.nichetoolkit.rice.service;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.actuator.AnchorActuator;
import io.github.nichetoolkit.rest.actuator.BiConsumerActuator;
import io.github.nichetoolkit.rest.actuator.ConsumerActuator;
import io.github.nichetoolkit.rest.actuator.FunctionActuator;
import io.github.nichetoolkit.rest.error.data.DataQueryException;
import io.github.nichetoolkit.rest.error.natives.UnsupportedErrorException;
import io.github.nichetoolkit.rest.helper.PartitionHelper;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.util.OptionalUtils;
import io.github.nichetoolkit.rice.*;
import io.github.nichetoolkit.rice.advice.*;
import io.github.nichetoolkit.rice.enums.DeleteMode;
import io.github.nichetoolkit.rice.enums.OperateType;
import io.github.nichetoolkit.rice.enums.RemoveMode;
import io.github.nichetoolkit.rice.enums.SaveType;
import io.github.nichetoolkit.rice.error.table.TablenameIsNullException;
import io.github.nichetoolkit.rice.filter.IdFilter;
import io.github.nichetoolkit.rice.helper.MEBuilderHelper;
import io.github.nichetoolkit.rice.mapper.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;

import java.lang.reflect.Method;
import java.util.*;

@Slf4j
abstract class SuperAdvice<M extends RestId<I>, E extends RestId<I>, F extends IdFilter<I, K>, I, K> implements OptionalService<M, F, I, K>, FilterAdvice<F, I, K>, TablenameAdvice<M, I, K>,
        SaveAdvice<M, I>, AlertAdvice<E, I>, OperateAdvice<E, I>, DeleteAdvice<E, I>, RemoveAdvice<E, I>, MutateAdvice<M, E, I> {

    protected final ThreadLocal<F> queryFilterCache = new ThreadLocal<>();

    protected final ThreadLocal<Map<K, String>> tablenameCaches = new ThreadLocal<>();

    protected BiConsumerActuator<K, M> createActuator;

    protected BiConsumerActuator<K, M> updateActuator;

    protected SuperMapper<E, I> superMapper;

    private void logicActuator(E entity, M model) throws RestException {
        if (entity instanceof RestLogic && model instanceof RestLogic) {
            RestLogic logicEntity = (RestLogic) entity;
            RestLogic logicModel = (RestLogic) model;
            logicEntity.setLogic(logicModel.getLogic());
        }
    }

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

    @SuppressWarnings(value = "unchecked")
    protected M modelActuator(E entity, Boolean... isLoadArray) throws RestException {
        M model = this.createModel(entity);
        if (BuilderAdvice.class.isAssignableFrom(this.getClass())) {
            BuilderAdvice<M, E, I> builderAdvice = (BuilderAdvice<M, E, I>) this;
            builderAdvice.buildModel(entity, model, isLoadArray);
        }
        return model;
    }


    @SuppressWarnings(value = "unchecked")
    protected List<M> modelActuator(Collection<E> entityList, ConsumerActuator<E> actuator, Boolean... isLoadArray) throws RestException {
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

    private void optionalLogicAndOperate(@NonNull M model) throws RestException {
        if (model instanceof RestLogic) {
            RestLogic logicModel = (RestLogic) model;
            logicModel.setLogic(Optional.ofNullable(logicModel.getLogic()).orElse(valueOfLogic()));
        }
        if (model instanceof RestOperate) {
            RestOperate operateModel = (RestOperate) model;
            if (GeneralUtils.isEmpty(operateModel.getOperate())) {
                operateModel.setOperate(Optional.ofNullable(operateModel.getOperate()).orElse(OperateType.NONE));
            }
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
    }

    protected void optionalCreate(K tablekey, @NonNull M model) throws RestException {
        optionalDynamicTable(tablekey, model);
        optionalLogicAndOperate(model);
        if (GeneralUtils.isEmpty(model.getId()) || !isIdentityOfInvade()) {
            createActuator().actuate(tablekey, model);
        } else {
            invadeActuator().actuate(tablekey, model);
        }
    }

    protected void optionalUpdate(K tablekey, @NonNull M model) throws RestException {
        OptionalUtils.ofIdEmpty(model.getId(), log);
        optionalDynamicTable(tablekey, model);
        optionalLogicAndOperate(model);
        updateActuator().actuate(tablekey, model);
    }

    protected void optionalSave(K tablekey, @NonNull M model) throws RestException {
        optionalDynamicTable(tablekey, model);
        optionalLogicAndOperate(model);
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
                    throw new TablenameIsNullException("The tablename is null, the method of 'dynamicTablename' maybe to override it.");
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

    protected Integer single(K tablekey, M model, Object... idArray) throws RestException {
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


    protected E findById(I id, String tablename) throws RestException {
        E entity;
        if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
            entity = superMapper.findDynamicById(tablename, id);
        } else {
            entity = superMapper.findById(id);
        }
        return entity;
    }

    @SuppressWarnings(value = "unchecked")
    protected <L> List<E> findByLinkId(L linkId, String tablename) throws RestException {
        List<E> entityList;
        if (FindLinkMapper.class.isAssignableFrom(superMapper.getClass())) {
            FindLinkMapper<E, L, I> findLinkMapper = (FindLinkMapper<E, L, I>) superMapper;
            if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
                entityList = findLinkMapper.findDynamicByLinkId(tablename, linkId);
            } else {
                entityList = findLinkMapper.findByLinkId(linkId);
            }
        } else {
            throw new UnsupportedErrorException("The 'findByLinkId' method is unimplemented, the mapper must extends 'FindLinkMapper'.");
        }
        return entityList;
    }

    @SuppressWarnings({"unchecked", "Duplicates"})
    protected <S> void alertPartition(String tablename, Collection<I> idList, S status) throws RestException {
        if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
            PartitionHelper.partition(idList, this.partitionOfDelete(), ids -> ((AlertMapper<S, I>) superMapper).alertDynamicAll(tablename, ids, status));
        } else {
            PartitionHelper.partition(idList, this.partitionOfDelete(), ids -> ((AlertMapper<S, I>) superMapper).alertAll(ids, status));
        }
    }

    @SuppressWarnings({"unchecked", "Duplicates"})
    protected <L, S> void alertLinkPartition(String tablename, Collection<L> linkIdList, S status) throws RestException {
        if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
            PartitionHelper.partition(linkIdList, this.partitionOfDelete(), linkIds -> ((AlertLinkMapper<L, S, I>) superMapper).alertDynamicAllByLinkIds(tablename, linkIds, status));
        } else {
            PartitionHelper.partition(linkIdList, this.partitionOfDelete(), linkIds -> ((AlertLinkMapper<L, S, I>) superMapper).alertAllByLinkIds(linkIds, status));
        }
    }

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

    @SuppressWarnings(value = "unchecked")
    protected void operatePartition(String tablename, Collection<I> idList, OperateType operate) throws RestException {
        if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
            PartitionHelper.partition(idList, this.partitionOfDelete(), ids -> ((OperateMapper<I>) superMapper).operateDynamicAll(tablename, ids, operate.getKey()));
        } else {
            PartitionHelper.partition(idList, this.partitionOfDelete(), ids -> ((OperateMapper<I>) superMapper).operateAll(ids, operate.getKey()));
        }
    }

    @SuppressWarnings({"unchecked", "Duplicates"})
    protected <L> void operateLinkPartition(String tablename, Collection<L> linkIdList, OperateType operate) throws RestException {
        if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
            PartitionHelper.partition(linkIdList, this.partitionOfDelete(), linkIds -> ((OperateLinkMapper<L, I>) superMapper).operateDynamicAllByLinkIds(tablename, linkIds, operate.getKey()));
        } else {
            PartitionHelper.partition(linkIdList, this.partitionOfDelete(), linkIds -> ((OperateLinkMapper<L, I>) superMapper).operateAllByLinkIds(linkIds, operate.getKey()));
        }
    }

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

    protected List<E> findAll(Collection<I> idList, String tablename) throws RestException {
        List<E> entityList;
        if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
            entityList = PartitionHelper.query(idList, this.partitionOfQuery(), ids -> superMapper.findDynamicAll(tablename, ids));
        } else {
            entityList = PartitionHelper.query(idList, this.partitionOfQuery(), superMapper::findAll);
        }
        return entityList;
    }

    @SuppressWarnings(value = "unchecked")
    protected <L> List<E> findAllByLinkIds(Collection<L> linkIdList, String tablename) throws RestException {
        List<E> entityList;
        if (FindLinkMapper.class.isAssignableFrom(superMapper.getClass())) {
            FindLinkMapper<E, L, I> findLinkMapper = (FindLinkMapper<E, L, I>) superMapper;
            if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
                entityList = PartitionHelper.query(linkIdList, this.partitionOfQuery(), linkIds -> findLinkMapper.findDynamicAllByLinkIds(tablename, linkIds));
            } else {
                entityList = PartitionHelper.query(linkIdList, this.partitionOfQuery(), findLinkMapper::findAllByLinkIds);
            }
        } else {
            throw new UnsupportedErrorException("The 'findAllByLinkIds' method is unimplemented, the mapper must extends 'FindLinkMapper'.");
        }
        return entityList;
    }

    @SuppressWarnings({"unchecked", "Duplicates"})
    protected void removePartition(String tablename, Collection<I> idList, String logic) throws RestException {
        if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
            PartitionHelper.delete(idList, this.partitionOfDelete(), ids -> ((RemoveMapper<I>) superMapper).removeDynamicAll(tablename, ids, logic));
        } else {
            PartitionHelper.delete(idList, this.partitionOfDelete(), ids -> ((RemoveMapper<I>) superMapper).removeAll(ids, logic));
        }
    }

    @SuppressWarnings({"unchecked", "Duplicates"})
    protected <L> void removeLinkPartition(String tablename, Collection<L> linkIdList, String logic) throws RestException {
        if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
            PartitionHelper.delete(linkIdList, this.partitionOfDelete(), linkIds -> ((RemoveLinkMapper<L, I>) superMapper).removeDynamicAllByLinkIds(tablename, linkIds, logic));
        } else {
            PartitionHelper.delete(linkIdList, this.partitionOfDelete(), linkIds -> ((RemoveLinkMapper<L, I>) superMapper).removeAllByLinkIds(linkIds, logic));
        }
    }

    protected void removeAdvice(List<E> entityList, String logic, ConsumerActuator<String> removeActuator) throws RestException {
        if (!isBeforeSkip()) {
            this.beforeRemoveAll(entityList);
        }
        removeActuator.actuate(logic);
        if (!isAfterSkip()) {
            this.afterRemoveAll(entityList);
        }
        this.refresh();
    }


    protected void deletePartition(String tablename, Collection<I> idList) throws RestException {
        if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
            PartitionHelper.delete(idList, this.partitionOfDelete(), ids -> superMapper.deleteDynamicAll(tablename, ids));
        } else {
            PartitionHelper.delete(idList, this.partitionOfDelete(), ids -> superMapper.deleteAll(ids));
        }
    }

    @SuppressWarnings({"unchecked", "Duplicates"})
    protected <L> void deleteLinkPartition(String tablename, Collection<L> linkIdList) throws RestException {
        if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
            PartitionHelper.delete(linkIdList, this.partitionOfDelete(), linkIds -> ((DeleteLinkMapper<L, I>) superMapper).deleteDynamicAllByLinkIds(tablename, linkIds));
        } else {
            PartitionHelper.delete(linkIdList, this.partitionOfDelete(), linkIds -> ((DeleteLinkMapper<L, I>) superMapper).deleteAllByLinkIds(linkIds));
        }
    }

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

    protected List<E> findAllByWhere(String whereSql, String tablename) throws RestException {
        List<E> entityList;
        if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
            entityList = superMapper.findDynamicAllByWhere(tablename, whereSql);
        } else {
            entityList = superMapper.findAllByWhere(whereSql);
        }
        return entityList;
    }

    protected void deleteAllByWhere(String whereSql, String tablename, F filter) throws RestException {
        if (isBeforeSkip() && isAfterSkip()) {
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
    protected void removeAllByWhere(String removeWhereSql, String tablename, F filter) throws RestException {
        if (!(superMapper instanceof RemoveMapper)) {
            throw new UnsupportedErrorException("The mapper is not support method of 'removeAllWithFilter' with the delete model is 'REMOVE' !");
        }
        String logic = signOfLogic();
        if (isBeforeSkip() && isAfterSkip()) {
            if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
                ((RemoveMapper<I>) superMapper).removeDynamicAllByWhere(tablename, removeWhereSql, logic);
            } else {
                ((RemoveMapper<I>) superMapper).removeAllByWhere(removeWhereSql, logic);
            }
        } else {
            String queryWhereSql = queryWhereSql(filter);
            List<E> entityList = superMapper.findAllByWhere(queryWhereSql);
            if (GeneralUtils.isNotEmpty(entityList)) {
                removeAdvice(entityList, logic, sign -> {
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
    protected void operateAllByWhere(String operateWhereSql, String tablename, F filter) throws RestException {
        if (!(superMapper instanceof OperateMapper)) {
            throw new UnsupportedErrorException("The mapper is not support method of 'operateAllWithFilter' with the delete model is 'OPERATE' !");
        }
        OperateType operateType = GeneralUtils.isNotEmpty(filter.getOperate()) ? filter.getOperate() : OperateType.REMOVE;
        if (isBeforeSkip() && isAfterSkip()) {
            if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
                ((OperateMapper<I>) superMapper).operateDynamicAllByWhere(tablename, operateWhereSql, operateType.getKey());
            } else {
                ((OperateMapper<I>) superMapper).operateAllByWhere(operateWhereSql, operateType.getKey());
            }
        } else {
            String queryWhereSql = queryWhereSql(filter);
            List<E> entityList = superMapper.findAllByWhere(queryWhereSql);
            if (GeneralUtils.isNotEmpty(entityList)) {
                operateAdvice(entityList, operateType, operate -> {
                    if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
                        ((OperateMapper<I>) superMapper).operateDynamicAllByWhere(tablename, operateWhereSql, operate.getKey());
                    } else {
                        ((OperateMapper<I>) superMapper).operateAllByWhere(operateWhereSql, operate.getKey());
                    }
                });
            }
        }
    }

    protected final BiConsumerActuator<K, M> DEFAULT_CREATE_ACTUATOR = (K tablekey, @NonNull M model) -> {
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
            String message = "The data no found，id: " + model.getId();
            OptionalUtils.ofFalse(exist, message, "id", log, DataQueryException::new);
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
                String message = "The data no found，id: " + model.getId();
                OptionalUtils.ofFalse(exist, message, "id", log, DataQueryException::new);
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

    protected abstract void afterSuperHandle() throws RestException;

    protected abstract E createEntity(M model) throws RestException;

    protected abstract M createModel(E entity) throws RestException;

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

}
