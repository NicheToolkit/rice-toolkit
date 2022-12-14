package io.github.nichetoolkit.rice.service;

import com.github.pagehelper.Page;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestKey;
import io.github.nichetoolkit.rest.actuator.*;
import io.github.nichetoolkit.rest.error.data.DataQueryException;
import io.github.nichetoolkit.rest.helper.OptionalHelper;
import io.github.nichetoolkit.rest.helper.PartitionHelper;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.util.JsonUtils;
import io.github.nichetoolkit.rice.IdEntity;
import io.github.nichetoolkit.rice.IdModel;
import io.github.nichetoolkit.rice.RestPage;
import io.github.nichetoolkit.rice.RestTable;
import io.github.nichetoolkit.rice.clazz.ClazzHelper;
import io.github.nichetoolkit.rice.configure.RiceBeanProperties;
import io.github.nichetoolkit.rice.enums.DeleteType;
import io.github.nichetoolkit.rice.enums.OperateType;
import io.github.nichetoolkit.rice.enums.RemoveType;
import io.github.nichetoolkit.rice.enums.SaveType;
import io.github.nichetoolkit.rice.error.service.ServiceUnimplementedException;
import io.github.nichetoolkit.rice.error.service.ServiceUnknownException;
import io.github.nichetoolkit.rice.filter.IdFilter;
import io.github.nichetoolkit.rice.helper.MEBuilderHelper;
import io.github.nichetoolkit.rice.mapper.*;
import io.github.nichetoolkit.rice.service.advice.*;
import io.github.nichetoolkit.rice.service.stereotype.RestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.*;

/**
 * <p>SuperService</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@SuppressWarnings("RedundantThrows")
@Slf4j
public abstract class SuperService<I, M extends IdModel<I>, E extends IdEntity<I>, F extends IdFilter<I>>
        implements InitializingBean, ApplicationContextAware, OptionalService<I, M, F>, FilterAdvice<I, F>,
        SaveAdvice<I, M>, AlertAdvice<I>, OperateAdvice<I, E>, DeleteAdvice<I, E>, RemoveAdvice<I, E> {

    private static ApplicationContext applicationContext;

    protected ConsumerActuator<M> createActuator;

    protected ConsumerActuator<M> updateActuator;

    protected SuperMapper<E, I> superMapper;

    private String simpleName;

    protected RiceBeanProperties beanProperties;

    protected ThreadLocal<Map<String, String>> tableNameMapCache = new ThreadLocal<>();

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        SuperService.applicationContext = applicationContext;
    }

    @SuppressWarnings(value = "unchecked")
    @Override
    public void afterPropertiesSet() throws Exception {
        this.beanProperties = applicationContext.getBean(RiceBeanProperties.class);
        if (GeneralUtils.isEmpty(beanProperties)) {
            String message = "the bean of 'RiceBeanProperties' type is not found!";
            log.error(message);
            throw new ServiceUnknownException(RiceBeanProperties.class.getName(), this.getClass().getName(), message);
        }
        String commonBeanName;
        simpleName = this.getClass().getSimpleName();
        if (simpleName.contains("ServiceImpl")) {
            commonBeanName = simpleName.replace("ServiceImpl", "");
        } else if (simpleName.contains("Service")) {
            commonBeanName = simpleName.replace("Service", "");
        } else {
            commonBeanName = simpleName;
        }
        String firstCase = commonBeanName.substring(0, 1);
        String otherCase = commonBeanName.substring(1);
        String lowerBeanName = firstCase.toLowerCase().concat(otherCase);
        RestService service = this.getClass().getAnnotation(RestService.class);
        if (GeneralUtils.isNotEmpty(service)) {
            Class<? extends SuperMapper> mapper = service.mapper();
            if (SuperMapper.class.isAssignableFrom(mapper)) {
                this.superMapper = applicationContext.getBean(mapper);
            }
        } else {
            try {
                String mapperName = commonBeanName.concat("Mapper");
                this.superMapper = applicationContext.getBean(mapperName, SuperMapper.class);
            } catch (BeansException exception) {
                log.warn(exception.getMessage());
                try {
                    String lowerMapperName = lowerBeanName.concat("Mapper");
                    this.superMapper = applicationContext.getBean(lowerMapperName, SuperMapper.class);
                } catch (BeansException exception1) {
                    exception1.printStackTrace();
                    String message = "the service and mapper name must be like 'xxxService'/'xxxServiceImpl' and 'xxxMapper'";
                    log.error(message);
                    throw new ServiceUnknownException(SuperMapper.class.getName(), this.getClass().getName(), message, exception1);
                }
            }
        }
        this.doServiceHandle();
    }

    @SuppressWarnings(value = "unchecked")
    protected E entityActuator(M model, I... idArray) throws RestException {
        E entity = this.createEntity(model);
        if (BuilderAdvice.class.isAssignableFrom(this.getClass())) {
            BuilderAdvice builderAdvice = (BuilderAdvice) this;
            builderAdvice.buildEntity(model, entity, idArray);
        }
        return entity;
    }

    @SuppressWarnings(value = "unchecked")
    protected List<E> entityActuator(Collection<M> modelList, ConsumerActuator<M> actuator, I... idArray) throws RestException {
        List<E> entityList;
        if (BuilderAdvice.class.isAssignableFrom(this.getClass())) {
            BuilderAdvice builderAdvice = (BuilderAdvice) this;
            Method findMethod = null;
            try {
                findMethod = builderAdvice.getClass().getMethod("buildEntityList", Collection.class, List.class, Boolean[].class);
            } catch (NoSuchMethodException ignored) {
            }
            Method buildEntityListMethod = findMethod;
            /** 当buildEntity和buildEntityList都被复写的时候 优先调用buildEntityList */
            entityList = MEBuilderHelper.entityList(modelList, actuator, (M model) -> {
                E entity = createEntity(model);
                if (buildEntityListMethod == null || buildEntityListMethod.isDefault()) {
                    builderAdvice.buildEntity(model, entity, idArray);
                }
                return entity;
            });
            if (buildEntityListMethod != null && !buildEntityListMethod.isDefault()) {
                builderAdvice.buildEntityList(modelList, entityList, idArray);
            }
        } else {
            entityList = MEBuilderHelper.entityList(modelList, actuator, this::createEntity);
        }
        return entityList;
    }

    @SuppressWarnings(value = "unchecked")
    protected M modelActuator(E entity, Boolean... isLoadArray) throws RestException {
        M model = this.createModel(entity);
        if (BuilderAdvice.class.isAssignableFrom(this.getClass())) {
            BuilderAdvice builderAdvice = (BuilderAdvice) this;
            builderAdvice.buildModel(entity, model, isLoadArray);
        }
        return model;
    }

    @SuppressWarnings(value = "unchecked")
    protected List<M> modelActuator(Collection<E> entityList, Boolean... isLoadArray) throws RestException {
        List<M> modelList;
        if (BuilderAdvice.class.isAssignableFrom(this.getClass())) {
            BuilderAdvice builderAdvice = (BuilderAdvice) this;
            Method findMethod = null;
            try {
                findMethod = builderAdvice.getClass().getMethod("buildModelList", Collection.class, List.class, Boolean[].class);
            } catch (NoSuchMethodException ignored) {
            }
            Method buildModelListMethod = findMethod;
            /* 当buildModel和buildModelList都被复写的时候 优先调用buildModelList */
            modelList = MEBuilderHelper.modelList(entityList, (E entity) -> {
                M model = this.createModel(entity);
                if (buildModelListMethod == null || buildModelListMethod.isDefault()) {
                    builderAdvice.buildModel(entity, model, isLoadArray);
                }
                return model;
            });
            if (buildModelListMethod != null && !buildModelListMethod.isDefault()) {
                builderAdvice.buildModelList(entityList, modelList, isLoadArray);
            }
        } else {
            modelList = MEBuilderHelper.modelList(entityList, this::createModel);
        }
        return modelList;
    }

    protected abstract E createEntity(M model) throws RestException;

    protected abstract M createModel(E entity) throws RestException;

    protected void refresh() throws RestException {
    }

    @SuppressWarnings(value = "unchecked")
    private Integer single(String tableKey, M model, I... idArray) throws RestException {
        E entity = entityActuator(model, idArray);
        String tablename = tablename(tableKey, model);
        Integer result;
        if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
            result = superMapper.save(tablename, entity);
        } else {
            result = superMapper.save(entity);
        }
        return result;
    }

    @SuppressWarnings(value = "unchecked")
    public M create(M model, I... idArray) throws RestException {
        return create(null, model, idArray);
    }

    @SuppressWarnings(value = "unchecked")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public M create(String tableKey, M model, I... idArray) throws RestException {
        if (GeneralUtils.isEmpty(model)) {
            return null;
        }
        optionalCreate(tableKey, model);
        this.beforeCreate(model);
        Integer result = single(tableKey, model, idArray);
        String message = "creating method has error with " + simpleName + ": " + JsonUtils.parseJson(model);
        OptionalHelper.create(result, message, simpleName);
        this.afterCreate(model);
        this.refresh();
        return model;
    }

    @SuppressWarnings(value = "unchecked")
    public M update(M model, I... idArray) throws RestException {
        return update(null, model, idArray);
    }

    @SuppressWarnings(value = "unchecked")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public M update(String tableKey, M model, I... idArray) throws RestException {
        if (GeneralUtils.isEmpty(model)) {
            return null;
        }
        optionalUpdate(tableKey, model);
        this.beforeUpdate(model);
        Integer result = single(tableKey, model, idArray);
        String message = "updating method has error with " + simpleName + ": " + JsonUtils.parseJson(model);
        OptionalHelper.update(result, message, simpleName);
        this.afterUpdate(model);
        this.refresh();
        return model;
    }

    @SuppressWarnings(value = "unchecked")
    public M save(M model, I... idArray) throws RestException {
        return save(null, model, idArray);
    }

    @SuppressWarnings(value = "unchecked")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public M save(String tableKey, M model, I... idArray) throws RestException {
        if (GeneralUtils.isEmpty(model)) {
            return null;
        }
        optionalSave(tableKey, model);
        this.beforeSave(model);
        Integer result = single(tableKey, model, idArray);
        String message = "saving method has error with " + simpleName + ": " + JsonUtils.parseJson(model);
        OptionalHelper.save(result, message, simpleName);
        this.afterSave(model);
        this.refresh();
        return model;
    }

    public List<M> saveAll(Collection<M> modelList) throws RestException {
        return saveAll(modelList, (I[]) null);
    }

    @SuppressWarnings(value = "unchecked")
    public List<M> saveAll(Collection<M> modelList, I... idArray) throws RestException {
        return saveAll(null, modelList, (I[]) null);
    }

    @SuppressWarnings(value = "unchecked")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public List<M> saveAll(String tableKey, Collection<M> modelList, I... idArray) throws RestException {
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
        String tablename = tablename(tableKey, modelList);
        Integer result;
        if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
            result = PartitionHelper.save(entityList, this.savePartition(), entities -> superMapper.saveAll(tablename, entities));
        } else {
            result = PartitionHelper.save(entityList, this.savePartition(), superMapper::saveAll);
        }
        Boolean comparer = modelList.size() == result;
        String message = "saveAll method has error with " + simpleName + ": " + JsonUtils.parseJson(modelList);
        OptionalHelper.saveAll(comparer, message, simpleName);
        this.afterSaveAll(modelList);
        this.refresh();
        return new ArrayList<>(modelList);
    }

    public void operateById(I id, OperateType operate) throws RestException {
        operateById(null, id, operate);
    }

    private E findById(I id, String tablename) throws RestException {
        E entity;
        if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
            entity = superMapper.findById(tablename, id);
        } else {
            entity = superMapper.findById(id);
        }
        return entity;
    }

    @SuppressWarnings(value = "unchecked")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void operateById(String tableKey, I id, OperateType operate) throws RestException {
        if (GeneralUtils.isEmpty(id) || GeneralUtils.isEmpty(operate)) {
            return;
        }
        if (superMapper instanceof OperateMapper) {
            String tablename = tablename(tableKey);
            if (DeleteType.OPERATE != deleteModel() || (isBeforeSkip() && isAfterSkip())) {
                if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
                    ((OperateMapper<I>) superMapper).operateById(tablename, id, operate.getKey());
                } else {
                    ((OperateMapper<I>) superMapper).operateById(id, operate.getKey());
                }
            } else {
                E entity = findById(id, tablename);
                if (GeneralUtils.isNotEmpty(entity)) {
                    if (DeleteType.OPERATE == deleteModel() && !isBeforeSkip()) {
                        this.beforeOperate(entity);
                    }
                    if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
                        ((OperateMapper<I>) superMapper).operateById(tablename, id, operate.getKey());
                    } else {
                        ((OperateMapper<I>) superMapper).operateById(id, operate.getKey());
                    }
                    if (DeleteType.OPERATE == deleteModel() && !isAfterSkip()) {
                        this.afterOperate(entity);
                    }
                    this.refresh();
                }
            }
        }
    }

    public void operateAll(Collection<I> idList, OperateType operate) throws RestException {
        operateAll(null, idList, operate);
    }

    @SuppressWarnings(value = "unchecked")
    private void operatePartition(String tablename, Collection<I> idList, OperateType operate) throws RestException {
        if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
            PartitionHelper.partition(idList, this.deletePartition(), ids -> {
                ((OperateMapper<I>) superMapper).operateAll(tablename, ids, operate.getKey());
            });
        } else {
            PartitionHelper.partition(idList, this.deletePartition(), ids -> {
                ((OperateMapper<I>) superMapper).operateAll(ids, operate.getKey());
            });
        }
    }

    private void operateAdvice(List<E> entityList, OperateType operate, ConsumerActuator<OperateType> operateActuator) throws RestException {
        if (DeleteType.OPERATE == deleteModel() && !isBeforeSkip()) {
            this.beforeOperateAll(entityList);
        }
        operateActuator.actuate(operate);
        if (DeleteType.OPERATE == deleteModel() && !isAfterSkip()) {
            this.afterOperateAll(entityList);
        }
        this.refresh();
    }

    private List<E> findAll(Collection<I> idList, String tablename) throws RestException {
        List<E> entityList;
        if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
            entityList = PartitionHelper.query(idList, this.queryPartition(), ids -> superMapper.findAll(tablename, ids));
        } else {
            entityList = PartitionHelper.query(idList, this.queryPartition(), superMapper::findAll);
        }
        return entityList;
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void operateAll(String tableKey, Collection<I> idList, OperateType operate) throws RestException {
        if (GeneralUtils.isEmpty(idList)) {
            return;
        }
        if (superMapper instanceof OperateMapper) {
            String tablename = tablename(tableKey);
            if (DeleteType.OPERATE != deleteModel() || (isBeforeSkip() && isAfterSkip())) {
                operatePartition(tablename, idList, operate);
            } else {
                List<E> entityList = findAll(idList, tablename);
                if (GeneralUtils.isNotEmpty(entityList)) {
                    operateAdvice(entityList, operate, type -> operatePartition(tablename, idList, type));
                }
            }
        }
    }

    public void operateByLinkId(I linkId, OperateType operate) throws RestException {
        operateByLinkId(null, linkId, operate);
    }

    @SuppressWarnings(value = "unchecked")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void operateByLinkId(String tableKey, I linkId, OperateType operate) throws RestException {
        if (GeneralUtils.isEmpty(linkId)) {
            return;
        }
        if (superMapper instanceof OperateLinkMapper) {
            String tablename = tablename(tableKey);
            if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
                ((OperateLinkMapper<I>) superMapper).operateByLinkId(linkId, operate.getKey());
            } else {
                ((OperateLinkMapper<I>) superMapper).operateByLinkId(linkId, operate.getKey());
            }
            this.refresh();
        }
    }

    public void operateAllByLinkIds(Collection<I> linkIdList, OperateType operate) throws RestException {
        operateAllByLinkIds(null, linkIdList, operate);
    }

    @SuppressWarnings(value = "unchecked")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void operateAllByLinkIds(String tableKey, Collection<I> linkIdList, OperateType operate) throws RestException {
        if (GeneralUtils.isEmpty(linkIdList)) {
            return;
        }
        if (superMapper instanceof OperateLinkMapper) {
            String tablename = tablename(tableKey);
            if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
                PartitionHelper.partition(linkIdList, this.deletePartition(), linkIds -> {
                    ((OperateLinkMapper<I>) superMapper).operateAllByLinkIds(tablename, linkIds, operate.getKey());
                });
            } else {
                PartitionHelper.partition(linkIdList, this.deletePartition(), linkIds -> {
                    ((OperateLinkMapper<I>) superMapper).operateAllByLinkIds(linkIds, operate.getKey());
                });
            }
            this.refresh();
        }
    }

    public void alertById(I id, RestKey<Integer> keyType) throws RestException {
        alertById(null, id, keyType);
    }

    @SuppressWarnings(value = "unchecked")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void alertById(String tableKey, I id, RestKey<Integer> keyType) throws RestException {
        if (GeneralUtils.isEmpty(id) || GeneralUtils.isEmpty(keyType)) {
            return;
        }
        if (superMapper instanceof AlertMapper) {
            String tablename = tablename(tableKey);
            this.beforeAlert(id);
            if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
                ((AlertMapper<I>) superMapper).alertById(tablename, id, keyType.getKey());
            } else {
                ((AlertMapper<I>) superMapper).alertById(id, keyType.getKey());
            }
            this.afterAlert(id);
            this.refresh();
        }
    }

    public void alertAll(Collection<I> idList, RestKey<Integer> keyType) throws RestException {
        alertAll(null, idList, keyType);
    }

    @SuppressWarnings(value = "unchecked")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void alertAll(String tableKey, Collection<I> idList, RestKey<Integer> keyType) throws RestException {
        if (GeneralUtils.isEmpty(idList)) {
            return;
        }
        if (superMapper instanceof AlertMapper) {
            String tablename = tablename(tableKey);
            this.beforeAlertAll(idList);
            if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
                PartitionHelper.partition(idList, this.deletePartition(), ids -> {
                    ((AlertMapper<I>) superMapper).alertAll(tablename, ids, keyType.getKey());
                });
            } else {
                PartitionHelper.partition(idList, this.deletePartition(), ids -> {
                    ((AlertMapper<I>) superMapper).alertAll(ids, keyType.getKey());
                });
            }
            this.afterAlertAll(idList);
            this.refresh();
        }
    }

    public void alertById(I id, String field, RestKey<Integer> keyType) throws RestException {
        alertById(null, id, field, keyType);
    }

    @SuppressWarnings(value = "unchecked")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void alertById(String tableKey, I id, String field, RestKey<Integer> keyType) throws RestException {
        if (GeneralUtils.isEmpty(id) || GeneralUtils.isEmpty(keyType)) {
            return;
        }
        if (superMapper instanceof AlertFieldMapper) {
            String tablename = tablename(tableKey);
            this.beforeAlert(id);
            if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
                ((AlertFieldMapper<I>) superMapper).alertById(tablename, id, field, keyType.getKey());
            } else {
                ((AlertFieldMapper<I>) superMapper).alertById(id, field, keyType.getKey());
            }
            this.afterAlert(id);
            this.refresh();
        }
    }

    public void alertAll(Collection<I> idList, String field, RestKey<Integer> keyType) throws RestException {
        alertAll(null, idList, field, keyType);
    }

    @SuppressWarnings(value = "unchecked")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void alertAll(String tableKey, Collection<I> idList, String field, RestKey<Integer> keyType) throws RestException {
        if (GeneralUtils.isEmpty(idList)) {
            return;
        }
        if (superMapper instanceof AlertFieldMapper) {
            String tablename = tablename(tableKey);
            this.beforeAlertAll(idList);
            if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
                PartitionHelper.partition(idList, this.deletePartition(), ids -> {
                    ((AlertFieldMapper<I>) superMapper).alertAll(tablename, ids, field, keyType.getKey());
                });
            } else {
                PartitionHelper.partition(idList, this.deletePartition(), ids -> {
                    ((AlertFieldMapper<I>) superMapper).alertAll(ids, field, keyType.getKey());
                });
            }
            this.afterAlertAll(idList);
            this.refresh();
        }
    }

    public void alertById(I id, String field, String biField, RestKey<Integer> keyType) throws RestException {
        alertById(null, id, field, biField, keyType);
    }

    @SuppressWarnings(value = "unchecked")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void alertById(String tableKey, I id, String field, String biField, RestKey<Integer> keyType) throws RestException {
        if (GeneralUtils.isEmpty(id) || GeneralUtils.isEmpty(keyType)) {
            return;
        }
        if (superMapper instanceof AlertBiFieldMapper) {
            String tablename = tablename(tableKey);
            this.beforeAlert(id);
            if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
                ((AlertBiFieldMapper<I>) superMapper).alertById(tablename, id, field, biField, keyType.getKey());
            } else {
                ((AlertBiFieldMapper<I>) superMapper).alertById(id, field, biField, keyType.getKey());
            }
            this.afterAlert(id);
            this.refresh();
        }
    }

    public void alertAll(Collection<I> idList, String field, String biField, RestKey<Integer> keyType) throws RestException {
        alertAll(null, idList, field, biField, keyType);
    }

    @SuppressWarnings(value = "unchecked")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void alertAll(String tableKey, Collection<I> idList, String field, String biField, RestKey<Integer> keyType) throws RestException {
        if (GeneralUtils.isEmpty(idList)) {
            return;
        }
        if (superMapper instanceof AlertBiFieldMapper) {
            String tablename = tablename(tableKey);
            this.beforeAlertAll(idList);
            if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
                PartitionHelper.partition(idList, this.deletePartition(), ids -> {
                    ((AlertBiFieldMapper<I>) superMapper).alertAll(tablename, ids, field, biField, keyType.getKey());
                });
            } else {
                PartitionHelper.partition(idList, this.deletePartition(), ids -> {
                    ((AlertBiFieldMapper<I>) superMapper).alertAll(ids, field, biField, keyType.getKey());
                });
            }
            this.afterAlertAll(idList);
            this.refresh();
        }
    }

    public void removeById(I id) throws RestException {
        removeById(null, id);
    }

    @SuppressWarnings(value = "unchecked")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void removeById(String tableKey, I id) throws RestException {
        if (GeneralUtils.isEmpty(id)) {
            return;
        }
        String removeSign = removeSign();
        if (superMapper instanceof RemoveMapper) {
            String tablename = tablename(tableKey);
            if (DeleteType.REMOVE != deleteModel() || (isBeforeSkip() && isAfterSkip())) {
                if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
                    ((RemoveMapper) superMapper).removeById(tablename, id, removeSign);
                } else {
                    ((RemoveMapper) superMapper).removeById(id, removeSign);
                }
            } else {
                E entity = findById(id, tablename);
                if (GeneralUtils.isNotEmpty(entity)) {
                    if (DeleteType.REMOVE == deleteModel() && !isBeforeSkip()) {
                        this.beforeRemove(entity);
                    }
                    if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
                        ((RemoveMapper<I>) superMapper).removeById(tablename, id, removeSign);
                    } else {
                        ((RemoveMapper<I>) superMapper).removeById(id, removeSign);
                    }
                    if (DeleteType.REMOVE == deleteModel() && !isAfterSkip()) {
                        this.afterRemove(entity);
                    }
                    this.refresh();
                }
            }
        }
    }

    public void removeAll(Collection<I> idList) throws RestException {
        removeAll(null, idList);
    }

    @SuppressWarnings(value = "unchecked")
    private void removePartition(String tablename, Collection<I> idList, String removeSign) throws RestException {
        if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
            PartitionHelper.delete(idList, this.deletePartition(), ids -> {
                ((RemoveMapper<I>) superMapper).removeAll(tablename, ids, removeSign);
            });
        } else {
            PartitionHelper.delete(idList, this.deletePartition(), ids -> {
                ((RemoveMapper<I>) superMapper).removeAll(ids, removeSign);
            });
        }
    }

    private void removeAdvice(List<E> entityList, String removeSign, ConsumerActuator<String> removeActuator) throws RestException {
        if (DeleteType.REMOVE == deleteModel() && !isBeforeSkip()) {
            this.beforeRemoveAll(entityList);
        }
        removeActuator.actuate(removeSign);
        if (DeleteType.REMOVE == deleteModel() && !isAfterSkip()) {
            this.afterRemoveAll(entityList);
        }
        this.refresh();
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void removeAll(String tableKey, Collection<I> idList) throws RestException {
        if (GeneralUtils.isEmpty(idList)) {
            return;
        }
        String removeSign = removeSign();
        if (superMapper instanceof RemoveMapper) {
            String tablename = tablename(tableKey);
            if (DeleteType.REMOVE != deleteModel() || (isBeforeSkip() && isAfterSkip())) {
                removePartition(tablename, idList, removeSign);
            } else {
                List<E> entityList = findAll(idList, tablename);
                if (GeneralUtils.isNotEmpty(entityList)) {
                    removeAdvice(entityList, removeSign, sign -> removePartition(tablename, idList, sign));
                }
            }
        }
    }

    public void removeByLinkId(I linkId) throws RestException {
        removeByLinkId(null, linkId);
    }

    @SuppressWarnings(value = "unchecked")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void removeByLinkId(String tableKey, I linkId) throws RestException {
        if (GeneralUtils.isEmpty(linkId)) {
            return;
        }
        String removeSign = removeSign();
        if (superMapper instanceof RemoveLinkMapper) {
            String tablename = tablename(tableKey);
            if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
                ((RemoveLinkMapper<I>) superMapper).removeByLinkId(tablename, linkId, removeSign);
            } else {
                ((RemoveLinkMapper<I>) superMapper).removeByLinkId(linkId, removeSign);
            }
            this.refresh();
        }
    }

    public void removeAllByLinkIds(Collection<I> linkIdList) throws RestException {
        removeAllByLinkIds(null, linkIdList);
    }

    @SuppressWarnings(value = "unchecked")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void removeAllByLinkIds(String tableKey, Collection<I> linkIdList) throws RestException {
        if (GeneralUtils.isEmpty(linkIdList)) {
            return;
        }
        String removeSign = removeSign();
        if (superMapper instanceof RemoveLinkMapper) {
            String tablename = tablename(tableKey);
            if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
                PartitionHelper.partition(linkIdList, this.deletePartition(), linkIds -> {
                    ((RemoveLinkMapper<I>) superMapper).removeAllByLinkIds(tablename, linkIds, removeSign);
                });
            } else {
                PartitionHelper.partition(linkIdList, this.deletePartition(), linkIds -> {
                    ((RemoveLinkMapper<I>) superMapper).removeAllByLinkIds(linkIds, removeSign);
                });
            }
            this.refresh();
        }
    }

    @Override
    public void deleteById(I id) throws RestException {
        deleteById(null, id);
    }

    @Override
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void deleteById(String tableKey, I id) throws RestException {
        if (GeneralUtils.isEmpty(id)) {
            return;
        }
        DeleteType deleteModel = deleteModel();
        if (deleteModel == DeleteType.REMOVE) {
            removeById(tableKey, id);
        } else if (deleteModel == DeleteType.OPERATE) {
            operateById(tableKey, id, OperateType.REMOVE);
        } else {
            String tablename = tablename(tableKey);
            if (DeleteType.DELETE != deleteModel() || (isBeforeSkip() && isAfterSkip())) {
                if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
                    superMapper.deleteById(tablename, id);
                } else {
                    superMapper.deleteById(id);
                }
            } else {
                E entity = findById(id, tablename);
                if (GeneralUtils.isNotEmpty(entity)) {
                    if (DeleteType.DELETE == deleteModel() && !isBeforeSkip()) {
                        this.beforeDelete(entity);
                    }
                    if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
                        superMapper.deleteById(tablename, id);
                    } else {
                        superMapper.deleteById(id);
                    }
                    if (DeleteType.DELETE == deleteModel() && !isAfterSkip()) {
                        this.afterDelete(entity);
                    }
                    this.refresh();
                }
            }
        }
    }

    @Override
    public void deleteAll(Collection<I> idList) throws RestException {
        deleteAll(null, idList);
    }

    private void deletePartition(String tablename, Collection<I> idList) throws RestException {
        if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
            PartitionHelper.delete(idList, this.deletePartition(), ids -> superMapper.deleteAll(tablename, ids));
        } else {
            PartitionHelper.delete(idList, this.deletePartition(), ids -> superMapper.deleteAll(ids));
        }
    }

    private void deleteAdvice(List<E> entityList, AnchorActuator deleteActuator) throws RestException {
        if (DeleteType.DELETE == deleteModel() && !isBeforeSkip()) {
            this.beforeDeleteAll(entityList);
        }
        deleteActuator.actuate();
        if (DeleteType.DELETE == deleteModel() && !isAfterSkip()) {
            this.afterDeleteAll(entityList);
        }
        this.refresh();
    }

    @Override
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void deleteAll(String tableKey, Collection<I> idList) throws RestException {
        if (GeneralUtils.isEmpty(idList)) {
            return;
        }
        DeleteType deleteModel = deleteModel();
        if (deleteModel == DeleteType.REMOVE) {
            removeAll(tableKey, idList);
        } else if (deleteModel == DeleteType.OPERATE) {
            operateAll(tableKey, idList, OperateType.REMOVE);
        } else {
            String tablename = tablename(tableKey);
            if (DeleteType.DELETE != deleteModel() || (isBeforeSkip() && isAfterSkip())) {
                deletePartition(tablename, idList);
            } else {
                List<E> entityList = superMapper.findAll(idList);
                if (GeneralUtils.isNotEmpty(entityList)) {
                    deleteAdvice(entityList, () -> deletePartition(tablename, idList));
                }
            }
        }
    }

    public void deleteByLinkId(I linkId) throws RestException {
        deleteByLinkId(null, linkId);
    }

    @SuppressWarnings(value = "unchecked")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void deleteByLinkId(String tableKey, I linkId) throws RestException {
        if (GeneralUtils.isEmpty(linkId)) {
            return;
        }
        DeleteType deleteModel = deleteModel();
        if (deleteModel == DeleteType.REMOVE) {
            removeByLinkId(tableKey, linkId);
        } else if (deleteModel == DeleteType.OPERATE) {
            operateByLinkId(tableKey, linkId, OperateType.REMOVE);
        } else if (superMapper instanceof DeleteLinkMapper) {
            String tablename = tablename(tableKey);
            if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
                ((DeleteLinkMapper<I>) superMapper).deleteByLinkId(tablename, linkId);
            } else {
                ((DeleteLinkMapper<I>) superMapper).deleteByLinkId(linkId);
            }
            this.refresh();
        }
    }

    public void deleteAllByLinkIds(Collection<I> linkIdList) throws RestException {
        deleteAllByLinkIds(null, linkIdList);
    }


    @SuppressWarnings(value = "unchecked")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void deleteAllByLinkIds(String tableKey, Collection<I> linkIdList) throws RestException {
        if (GeneralUtils.isEmpty(linkIdList)) {
            return;
        }
        DeleteType deleteModel = deleteModel();
        if (deleteModel == DeleteType.REMOVE) {
            removeAllByLinkIds(tableKey, linkIdList);
        } else if (deleteModel == DeleteType.OPERATE) {
            operateAllByLinkIds(tableKey, linkIdList, OperateType.REMOVE);
        } else if (superMapper instanceof DeleteLinkMapper) {
            String tablename = tablename(tableKey);
            if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
                PartitionHelper.delete(linkIdList, this.deletePartition(), linkIds -> {
                    ((DeleteLinkMapper<I>) superMapper).deleteAllByLinkIds(tablename, linkIds);
                });
            } else {
                PartitionHelper.delete(linkIdList, this.deletePartition(), linkIds -> {
                    ((DeleteLinkMapper<I>) superMapper).deleteAllByLinkIds(linkIds);
                });
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
    public M queryById(String tableKey, I id, Boolean... isLoadArray) throws RestException {
        if (GeneralUtils.isEmpty(id)) {
            return null;
        }
        E entity;
        String tablename = tablename(tableKey);
        if (isLoadArray.length > 0 && LoadMapper.class.isAssignableFrom(superMapper.getClass())) {
            LoadMapper<E, I> loadMapper = (LoadMapper<E, I>) superMapper;
            Method findMethod = null;
            try {
                findMethod = loadMapper.getClass().getMethod("findByLoadId", id.getClass(), Boolean[].class);
            } catch (NoSuchMethodException ignored) {
            }
            Method queryByIdMethod = findMethod;
            /* 当LoadMapper被复写的时候 优先调用LoadMapper的queryByIdMethod */
            if (queryByIdMethod != null && !queryByIdMethod.isDefault()) {
                if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
                    entity = loadMapper.findByLoadId(tablename, id, isLoadArray);
                } else {
                    entity = loadMapper.findByLoadId(id, isLoadArray);
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
    public List<M> queryAll(String tableKey, Collection<I> idList, Boolean... isLoadArray) throws RestException {
        if (GeneralUtils.isEmpty(idList)) {
            return Collections.emptyList();
        }
        List<E> entityList;
        String tablename = tablename(tableKey);
        if (isLoadArray.length > 0 && LoadMapper.class.isAssignableFrom(superMapper.getClass())) {
            LoadMapper<E, I> loadMapper = (LoadMapper<E, I>) superMapper;
            Method findMethod = null;
            try {
                findMethod = loadMapper.getClass().getMethod("queryAllLoad", List.class, Boolean[].class);
            } catch (NoSuchMethodException ignored) {
            }
            Method queryAllMethod = findMethod;
            /** 当LoadMapper被复写的时候 优先调用LoadMapper的queryByIdMethod */
            if (queryAllMethod != null && !queryAllMethod.isDefault()) {
                if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
                    entityList = PartitionHelper.query(idList, this.queryPartition(), ids -> loadMapper.findAllLoad(tablename, ids, isLoadArray));
                } else {
                    entityList = PartitionHelper.query(idList, this.queryPartition(), ids -> loadMapper.findAllLoad(ids, isLoadArray));
                }
            } else {
                entityList = findAll(idList, tablename);
            }
        } else {
            entityList = findAll(idList, tablename);
        }
        return modelActuator(entityList, isLoadArray);
    }

    private List<E> findAllByWhere(String whereSql, String tablename) throws RestException {
        List<E> entityList;
        if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
            entityList = superMapper.findAllByWhere(tablename, whereSql);
        } else {
            entityList = superMapper.findAllByWhere(whereSql);
        }
        return entityList;
    }

    @SuppressWarnings(value = "unchecked")
    public RestPage<M> queryAllWithFilter(F filter) throws RestException {
        optionalQueryFilter(filter);
        String whereSql = queryWhereSql(filter);
        Boolean[] loadArray = findLoadArray(filter);
        Boolean[] isLoadArray = queryLoadArray(filter);
        String[] fieldArray = fieldArray(filter);
        String tableKey = tableKey(filter);
        String tablename = tablename(tableKey);
        Page<E> page;
        List<E> entityList;
        if (loadArray.length > 0 && LoadFilterMapper.class.isAssignableFrom(superMapper.getClass())) {
            LoadFilterMapper<E, I> loadFilterMapper = (LoadFilterMapper<E, I>) superMapper;
            Method findMethod = null;
            try {
                findMethod = loadFilterMapper.getClass().getMethod("findAllByLoadWhere", String.class, Boolean[].class);
            } catch (NoSuchMethodException ignored) {
            }
            Method findAllByWhereMethod = findMethod;
            /* 当LoadMapper被复写的时候 优先调用LoadMapper的findAllByWhereMethod */
            if (findAllByWhereMethod != null && !findAllByWhereMethod.isDefault()) {
                page = filter.toPage();
                if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
                    entityList = loadFilterMapper.findAllByLoadWhere(tablename, whereSql, loadArray);
                } else {
                    entityList = loadFilterMapper.findAllByLoadWhere(whereSql, loadArray);
                }
            } else {
                page = filter.toPage();
                entityList = findAllByWhere(whereSql, tablename);
            }
        } else if (fieldArray.length > 0 && FieldFilterMapper.class.isAssignableFrom(superMapper.getClass())) {
            FieldFilterMapper<E, I> fieldFilterMapper = (FieldFilterMapper<E, I>) superMapper;
            Method findMethod = null;
            try {
                findMethod = fieldFilterMapper.getClass().getMethod("findAllByFieldWhere", String.class, String[].class);
            } catch (NoSuchMethodException ignored) {
            }
            Method findAllByWhereMethod = findMethod;
            /* 当FindMapper被复写的时候 优先调用FindMapper的findAllByWhereMethod */
            if (findAllByWhereMethod != null && !findAllByWhereMethod.isDefault()) {
                page = filter.toPage();
                if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
                    entityList = fieldFilterMapper.findAllByFieldWhere(tablename, whereSql, fieldArray);
                } else {
                    entityList = fieldFilterMapper.findAllByFieldWhere(whereSql, fieldArray);
                }
            } else {
                page = filter.toPage();
                entityList = findAllByWhere(whereSql, tablename);
            }
        } else {
            page = filter.toPage();
            entityList = findAllByWhere(whereSql, tablename);
        }
        List<M> modelList = modelActuator(entityList, isLoadArray);
        return RestPage.result(modelList, page);
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void deleteAllWithFilter(F filter) throws RestException {
        DeleteType deleteModel = deleteModel();
        if (deleteModel == DeleteType.REMOVE) {
            removeAllWithFilter(filter);
        } else if (deleteModel == DeleteType.OPERATE) {
            operateAllWithFilter(filter);
        } else {
            optionalDeleteFilter(filter);
            String whereSql = deleteWhereSql(filter);
            String tableKey = tableKey(filter);
            String tablename = tablename(tableKey);
            if (GeneralUtils.isNotEmpty(whereSql)) {
                if (DeleteType.DELETE != deleteModel() || (isBeforeSkip() && isAfterSkip())) {
                    if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
                        superMapper.deleteAllByWhere(tablename, whereSql);
                    } else {
                        superMapper.deleteAllByWhere(whereSql);
                    }
                } else {
                    String queryWhereSql = queryWhereSql(filter);
                    List<E> entityList = superMapper.findAllByWhere(queryWhereSql);
                    if (GeneralUtils.isNotEmpty(entityList)) {
                        deleteAdvice(entityList, () -> {
                            if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
                                superMapper.deleteAllByWhere(tablename, whereSql);
                            } else {
                                superMapper.deleteAllByWhere(whereSql);
                            }
                        });
                    }
                }
            }
        }
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void removeAllWithFilter(F filter) throws RestException {
        optionalRemoveFilter(filter);
        String removeWhereSql = removeWhereSql(filter);
        String tableKey = tableKey(filter);
        String tablename = tablename(tableKey);
        if (GeneralUtils.isNotEmpty(removeWhereSql)) {
            String removeSign = removeSign();
            if (DeleteType.REMOVE != deleteModel() || (isBeforeSkip() && isAfterSkip())) {
                if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
                    ((RemoveMapper) superMapper).removeAllByWhere(tablename, removeWhereSql, removeSign);
                } else {
                    ((RemoveMapper) superMapper).removeAllByWhere(removeWhereSql, removeSign);
                }
            } else {
                String queryWhereSql = queryWhereSql(filter);
                List<E> entityList = superMapper.findAllByWhere(queryWhereSql);
                if (GeneralUtils.isNotEmpty(entityList) && superMapper instanceof RemoveMapper) {
                    removeAdvice(entityList, removeSign, sign -> {
                        if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
                            ((RemoveMapper) superMapper).removeAllByWhere(tablename, removeWhereSql, sign);
                        } else {
                            ((RemoveMapper) superMapper).removeAllByWhere(removeWhereSql, sign);
                        }
                    });
                }
            }
        }
    }


    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void operateAllWithFilter(F filter) throws RestException {
        optionalOperateFilter(filter);
        String operateWhereSql = operateWhereSql(filter);
        String tableKey = tableKey(filter);
        String tablename = tablename(tableKey);
        if (GeneralUtils.isNotEmpty(operateWhereSql)) {
            if (DeleteType.OPERATE != deleteModel() || (isBeforeSkip() && isAfterSkip())) {
                if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
                    ((OperateMapper) superMapper).operateAllByWhere(tablename, operateWhereSql, OperateType.REMOVE.getKey());
                } else {
                    ((OperateMapper) superMapper).operateAllByWhere(operateWhereSql, OperateType.REMOVE.getKey());
                }
            } else {
                String queryWhereSql = queryWhereSql(filter);
                List<E> entityList = superMapper.findAllByWhere(queryWhereSql);
                if (GeneralUtils.isNotEmpty(entityList) && superMapper instanceof OperateMapper) {
                    operateAdvice(entityList, OperateType.REMOVE, operate -> {
                        if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
                            ((OperateMapper) superMapper).operateAllByWhere(tablename, operateWhereSql, operate.getKey());
                        } else {
                            ((OperateMapper) superMapper).operateAllByWhere(operateWhereSql, operate.getKey());
                        }
                    });
                }
            }
        }
    }

    private final ConsumerActuator<M> DEFAULT_CREATE_ACTUATOR = (@NonNull M model) -> {
        model.setId(ClazzHelper.generate(model));
        optionalInit(model);
        optional(model);
        if (createActuator != null) {
            createActuator.actuate(model);
        }
    };

    private final ConsumerActuator<M> DEFAULT_UPDATE_ACTUATOR = (@NonNull M model) -> {
        boolean exist;
        if (isDynamicTable() && model instanceof RestTable) {
            exist = existById(((RestTable) model).getTableKey(), model.getId());
        } else {
            exist = existById(model.getId());
        }
        String message = "the data no found，id: " + model.getId();
        OptionalHelper.falseable(exist, message, "id", DataQueryException::new);
        optional(model);
        if (updateActuator != null) {
            updateActuator.actuate(model);
        }
    };

    private final ConsumerActuator<M> DEFAULT_INVADE_ACTUATOR = (@NonNull M model) -> {
        optionalInit(model);
        optional(model);
        if (createActuator != null) {
            createActuator.actuate(model);
        }
    };

    protected ConsumerActuator<M> createActuator() {
        return DEFAULT_CREATE_ACTUATOR;
    }

    protected ConsumerActuator<M> updateActuator() {
        return DEFAULT_UPDATE_ACTUATOR;
    }

    protected ConsumerActuator<M> invadeActuator() {
        return DEFAULT_INVADE_ACTUATOR;
    }

    protected Boolean isIdInvade() {
        return beanProperties.isIdInvade();
    }

    protected Boolean isNameNonnull() {
        return beanProperties.isNameNonnull();
    }

    protected Boolean isNameUnique() {
        return beanProperties.isNameUnique();
    }

    protected Boolean isModelUnique() {
        return beanProperties.isModelUnique();
    }

    protected Boolean isDynamicTable() {
        return beanProperties.isDynamicTable();
    }

    public DeleteType deleteModel() {
        return beanProperties.deleteModel();
    }

    public RemoveType removeModel() {
        return beanProperties.removeModel();
    }

    public Boolean removeIndex() {
        return beanProperties.removeIndex();
    }

    public Boolean booleanSign() {
        return beanProperties.booleanSign();
    }

    public Boolean booleanValue() {
        return beanProperties.booleanValue();
    }

    public Integer numberSign() {
        return beanProperties.numberSign();
    }

    public Integer numberValue() {
        return beanProperties.numberValue();
    }

    public String removeSign() {
        return RemoveType.sign(removeModel(), booleanSign(), numberSign());
    }

    public String removeValue() {
        return RemoveType.value(removeModel(), booleanValue(), numberValue());
    }

    public Boolean isBeforeSkip() {
        return beanProperties.isBeforeSkip();
    }

    public Boolean isAfterSkip() {
        return beanProperties.isAfterSkip();
    }

    public Integer queryPartition() {
        return beanProperties.getPartitionQuery();
    }

    public Integer savePartition() {
        return beanProperties.getPartitionQuery();
    }

    public Integer deletePartition() {
        return beanProperties.getPartitionQuery();
    }

    private String tablename(String tableKey) throws RestException {
        if (GeneralUtils.isEmpty(tableKey)) {
            return null;
        }
        Map<String, String> tablenameMap = tableNameMapCache.get();
        if (isDynamicTable() && GeneralUtils.isNotEmpty(tablenameMap)) {
            return tablenameMap.get(tableKey);
        }
        return null;
    }

    private String tablename(String tableKey, M model) throws RestException {
        if (GeneralUtils.isEmpty(tableKey) && GeneralUtils.isEmpty(model)) {
            return null;
        }
        Map<String, String> tablenameMap = tableNameMapCache.get();
        if (isDynamicTable() && GeneralUtils.isNotEmpty(tablenameMap)) {
            if (GeneralUtils.isNotEmpty(tableKey)) {
                return tablenameMap.get(tableKey);
            } else if (GeneralUtils.isNotEmpty(model) && model instanceof RestTable) {
                RestTable table = (RestTable) model;
                return tablenameMap.get(table.getTableKey());
            }
        }
        return null;
    }

    private String tablename(String tableKey, Collection<M> modelList) throws RestException {
        if (GeneralUtils.isEmpty(tableKey) && GeneralUtils.isEmpty(modelList)) {
            return null;
        } else if (GeneralUtils.isNotEmpty(tableKey)) {
            Map<String, String> tablenameMap = tableNameMapCache.get();
            if (isDynamicTable() && GeneralUtils.isNotEmpty(tablenameMap)) {
                return tablenameMap.get(tableKey);
            }
        } else if (GeneralUtils.isNotEmpty(modelList)) {
            Optional<M> optionalAny = modelList.stream().findAny();
            if (optionalAny.isPresent()) {
                M model = optionalAny.get();
                return tablename(tableKey, model);
            }
        }
        return null;
    }

    protected void optionalName(@NonNull M model) throws RestException {
    }

    protected void optionalInit(@NonNull M model) throws RestException {
    }

    protected void optionalTable(@NonNull String tablename) throws RestException {
    }

    protected String createTablename(@NonNull String tableKey) throws RestException {
        throw new ServiceUnimplementedException(this.getClass().getSimpleName(), "createTablename");
    }

    private void optionalLogicSign(@NonNull M model) throws RestException {
        if (GeneralUtils.isEmpty(model.getLogicSign())) {
            model.setLogicSign(removeValue());
        }
        optionalName(model);
    }

    private void optionalDynamicTable(String tableKey, @NonNull M model) throws RestException {
        if (isDynamicTable()) {
            Map<String, String> tableNameMap = tableNameMapCache.get();
            if (GeneralUtils.isEmpty(tableNameMap)) {
                tableNameMap = new HashMap<>();
                tableNameMapCache.set(tableNameMap);
            }
            String tableName = null;
            if (GeneralUtils.isNotEmpty(tableKey)) {
                tableName = createTablename(tableKey);
            } else if (model instanceof RestTable) {
                RestTable table = (RestTable) model;
                tableKey = table.getTableKey();
                tableName = table.getTableName();
            }
            if (GeneralUtils.isEmpty(tableKey)) {
                return;
            }
            String tablename = tableNameMap.get(tableKey);
            if (GeneralUtils.isNotEmpty(tablename)) {
                return;
            }
            if (GeneralUtils.isNotEmpty(tableName)) {
                optionalTable(tableName);
                tableNameMap.put(tableKey, tableName);
            }
        }
    }

    private void optionalCreate(String tableKey, @NonNull M model) throws RestException {
        optionalLogicSign(model);
        optionalDynamicTable(tableKey, model);
        if (GeneralUtils.isEmpty(model.getId()) || !isIdInvade()) {
            createActuator().actuate(model);
        } else {
            invadeActuator().actuate(model);
        }
        if (model.isSaveLower(SaveType.CREATE)) {
            model.setSave(SaveType.CREATE);
        }
    }

    private void optionalUpdate(String tableKey, @NonNull M model) throws RestException {
        OptionalHelper.idable(model.getId());
        optionalDynamicTable(tableKey, model);
        updateActuator().actuate(model);
        if (model.isSaveLower(SaveType.UPDATE)) {
            model.setSave(SaveType.UPDATE);
        }

    }

    private void optionalSave(String tableKey, @NonNull M model) throws RestException {
        if (GeneralUtils.isEmpty(model.getId())) {
            optionalLogicSign(model);
            optionalDynamicTable(tableKey, model);
            createActuator().actuate(model);
            if (model.isSaveLower(SaveType.CREATE)) {
                model.setSave(SaveType.CREATE);
            }
        } else {
            optionalDynamicTable(tableKey, model);
            updateActuator().actuate(model);
            if (model.isSaveLower(SaveType.UPDATE)) {
                model.setSave(SaveType.UPDATE);
            }
        }
    }
}
