package io.github.nichetoolkit.rice.service;

import com.github.pagehelper.Page;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestKey;
import io.github.nichetoolkit.rest.actuator.AnchorActuator;
import io.github.nichetoolkit.rest.actuator.BiConsumerActuator;
import io.github.nichetoolkit.rest.actuator.ConsumerActuator;
import io.github.nichetoolkit.rest.error.data.DataQueryException;
import io.github.nichetoolkit.rest.error.natives.UnsupportedErrorException;
import io.github.nichetoolkit.rest.helper.PartitionHelper;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.util.JsonUtils;
import io.github.nichetoolkit.rest.util.OptionalUtils;
import io.github.nichetoolkit.rice.IdEntity;
import io.github.nichetoolkit.rice.IdModel;
import io.github.nichetoolkit.rice.RestPage;
import io.github.nichetoolkit.rice.RestTablekey;
import io.github.nichetoolkit.rice.advice.*;
import io.github.nichetoolkit.rice.clazz.ClazzHelper;
import io.github.nichetoolkit.rice.configure.RiceBeanProperties;
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
import io.github.nichetoolkit.rice.stereotype.RestService;
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
 * <code>SuperService</code>
 * <p>The type super service class.</p>
 * @param <M> {@link io.github.nichetoolkit.rice.IdModel} <p>the generic parameter is <code>IdModel</code> type.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.IdEntity} <p>the generic parameter is <code>IdEntity</code> type.</p>
 * @param <F> {@link io.github.nichetoolkit.rice.filter.IdFilter} <p>the generic parameter is <code>IdFilter</code> type.</p>
 * @param <I> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @param <K> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.IdModel
 * @see io.github.nichetoolkit.rice.IdEntity
 * @see io.github.nichetoolkit.rice.filter.IdFilter
 * @see org.springframework.beans.factory.InitializingBean
 * @see org.springframework.context.ApplicationContextAware
 * @see io.github.nichetoolkit.rice.service.extend.OptionalService
 * @see io.github.nichetoolkit.rice.advice.FilterAdvice
 * @see io.github.nichetoolkit.rice.advice.SaveAdvice
 * @see io.github.nichetoolkit.rice.advice.AlertAdvice
 * @see io.github.nichetoolkit.rice.advice.OperateAdvice
 * @see io.github.nichetoolkit.rice.advice.DeleteAdvice
 * @see io.github.nichetoolkit.rice.advice.RemoveAdvice
 * @see java.lang.SuppressWarnings
 * @see lombok.extern.slf4j.Slf4j
 * @since Jdk1.8
 */
@SuppressWarnings("RedundantThrows")
@Slf4j
public abstract class SuperService<M extends IdModel<I>, E extends IdEntity<I>, F extends IdFilter<I, K>, I, K>
        implements InitializingBean, ApplicationContextAware, OptionalService<M, F, I, K>, FilterAdvice<F, I, K>,
        SaveAdvice<M, I>, AlertAdvice<I>, OperateAdvice<E, I>, DeleteAdvice<E, I>, RemoveAdvice<E, I> {

    /**
     * <code>applicationContext</code>
     * {@link org.springframework.context.ApplicationContext} <p>the constant <code>applicationContext</code> field.</p>
     * @see org.springframework.context.ApplicationContext
     */
    private static ApplicationContext applicationContext;

    /**
     * <code>createActuator</code>
     * {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>the <code>createActuator</code> field.</p>
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     */
    protected BiConsumerActuator<K, M> createActuator;

    /**
     * <code>updateActuator</code>
     * {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>the <code>updateActuator</code> field.</p>
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     */
    protected BiConsumerActuator<K, M> updateActuator;

    /**
     * <code>superMapper</code>
     * {@link io.github.nichetoolkit.rice.mapper.SuperMapper} <p>the <code>superMapper</code> field.</p>
     * @see io.github.nichetoolkit.rice.mapper.SuperMapper
     */
    protected SuperMapper<E, I> superMapper;

    /**
     * <code>simpleName</code>
     * {@link java.lang.String} <p>the <code>simpleName</code> field.</p>
     * @see java.lang.String
     */
    private String simpleName;

    /**
     * <code>beanProperties</code>
     * {@link io.github.nichetoolkit.rice.configure.RiceBeanProperties} <p>the <code>beanProperties</code> field.</p>
     * @see io.github.nichetoolkit.rice.configure.RiceBeanProperties
     */
    protected RiceBeanProperties beanProperties;

    /**
     * <code>queryFilterCache</code>
     * {@link java.lang.ThreadLocal} <p>the <code>queryFilterCache</code> field.</p>
     * @see java.lang.ThreadLocal
     */
    private final ThreadLocal<F> queryFilterCache = new ThreadLocal<>();

    /**
     * <code>tablenameMapCache</code>
     * {@link java.lang.ThreadLocal} <p>the <code>tablenameMapCache</code> field.</p>
     * @see java.lang.ThreadLocal
     */
    private final ThreadLocal<Map<K, String>> tablenameMapCache = new ThreadLocal<>();

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
            Class<?> mapper = service.mapper();
            if (SuperMapper.class.isAssignableFrom(mapper)) {
                this.superMapper = (SuperMapper<E, I>) applicationContext.getBean(mapper);
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
                    String message = "the service and mapper name must be like 'xxxService'/'xxxServiceImpl' and 'xxxMapper'";
                    log.error(message);
                    throw new ServiceUnknownException(SuperMapper.class.getName(), this.getClass().getName(), message, exception1);
                }
            }
        }
        this.applyHandle();
    }

    /**
     * <code>applyHandle</code>
     * <p>the handle method.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    protected abstract void applyHandle() throws RestException;

    /**
     * <code>entityActuator</code>
     * <p>the actuator method.</p>
     * @param model   M <p>the model parameter is <code>M</code> type.</p>
     * @param idArray {@link java.lang.Object} <p>the id array parameter is <code>Object</code> type.</p>
     * @return E <p>the actuator return object is <code>E</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Object
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    protected E entityActuator(M model, Object... idArray) throws RestException {
        E entity = this.createEntity(model);
        entity.setLogicSign(model.getLogicSign());
        if (BuilderAdvice.class.isAssignableFrom(this.getClass())) {
            BuilderAdvice<M, E, I> builderAdvice = (BuilderAdvice<M, E, I>) this;
            builderAdvice.buildEntity(model, entity, idArray);
        }
        return entity;
    }

    /**
     * <code>entityActuator</code>
     * <p>the actuator method.</p>
     * @param modelList {@link java.util.Collection} <p>the model list parameter is <code>Collection</code> type.</p>
     * @param actuator  {@link io.github.nichetoolkit.rest.actuator.ConsumerActuator} <p>the actuator parameter is <code>ConsumerActuator</code> type.</p>
     * @param idArray   {@link java.lang.Object} <p>the id array parameter is <code>Object</code> type.</p>
     * @return {@link java.util.List} <p>the actuator return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
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
            entityList = MEBuilderHelper.entityList(modelList, actuator, (M model) -> {
                E entity = createEntity(model);
                entity.setLogicSign(model.getLogicSign());
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

    /**
     * <code>modelActuator</code>
     * <p>the actuator method.</p>
     * @param entity      E <p>the entity parameter is <code>E</code> type.</p>
     * @param isLoadArray {@link java.lang.Boolean} <p>the is load array parameter is <code>Boolean</code> type.</p>
     * @return M <p>the actuator return object is <code>M</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    protected M modelActuator(E entity, Boolean... isLoadArray) throws RestException {
        M model = this.createModel(entity);
        if (BuilderAdvice.class.isAssignableFrom(this.getClass())) {
            BuilderAdvice<M, E, I> builderAdvice = (BuilderAdvice<M, E, I>) this;
            builderAdvice.buildModel(entity, model, isLoadArray);
        }
        return model;
    }

    /**
     * <code>modelActuator</code>
     * <p>the actuator method.</p>
     * @param entityList  {@link java.util.Collection} <p>the entity list parameter is <code>Collection</code> type.</p>
     * @param isLoadArray {@link java.lang.Boolean} <p>the is load array parameter is <code>Boolean</code> type.</p>
     * @return {@link java.util.List} <p>the actuator return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see java.lang.Boolean
     * @see java.util.List
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    protected List<M> modelActuator(Collection<E> entityList, Boolean... isLoadArray) throws RestException {
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

    /**
     * <code>createEntity</code>
     * <p>the entity method.</p>
     * @param model M <p>the model parameter is <code>M</code> type.</p>
     * @return E <p>the entity return object is <code>E</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    protected abstract E createEntity(M model) throws RestException;

    /**
     * <code>createModel</code>
     * <p>the model method.</p>
     * @param entity E <p>the entity parameter is <code>E</code> type.</p>
     * @return M <p>the model return object is <code>M</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    protected abstract M createModel(E entity) throws RestException;

    /**
     * <code>refresh</code>
     * <p>the method.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    protected void refresh() throws RestException {
    }

    /**
     * <code>queryFilter</code>
     * <p>the filter method.</p>
     * @return F <p>the filter return object is <code>F</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    protected F queryFilter() throws RestException {
        return this.queryFilterCache.get();
    }

    /**
     * <code>tablenames</code>
     * <p>the method.</p>
     * @return {@link java.util.Map} <p>the return object is <code>Map</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.util.Map
     * @see io.github.nichetoolkit.rest.RestException
     */
    protected Map<K, String> tablenames() throws RestException {
        return this.tablenameMapCache.get();
    }

    /**
     * <code>single</code>
     * <p>the method.</p>
     * @param tablekey K <p>the tablekey parameter is <code>K</code> type.</p>
     * @param model    M <p>the model parameter is <code>M</code> type.</p>
     * @param idArray  {@link java.lang.Object} <p>the id array parameter is <code>Object</code> type.</p>
     * @return {@link java.lang.Integer} <p>the return object is <code>Integer</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Object
     * @see java.lang.Integer
     * @see io.github.nichetoolkit.rest.RestException
     */
    private Integer single(K tablekey, M model, Object... idArray) throws RestException {
        E entity = entityActuator(model, idArray);
        String tablename = tablename(tablekey, model);
        Integer result;
        if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
            result = superMapper.saveDynamic(tablename, entity);
        } else {
            result = superMapper.save(entity);
        }
        return result;
    }

    /**
     * <code>create</code>
     * <p>the method.</p>
     * @param model   M <p>the model parameter is <code>M</code> type.</p>
     * @param idArray {@link java.lang.Object} <p>the id array parameter is <code>Object</code> type.</p>
     * @return M <p>the return object is <code>M</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
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
     * <p>the method.</p>
     * @param tablekey K <p>the tablekey parameter is <code>K</code> type.</p>
     * @param model    M <p>the model parameter is <code>M</code> type.</p>
     * @param idArray  {@link java.lang.Object} <p>the id array parameter is <code>Object</code> type.</p>
     * @return M <p>the return object is <code>M</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Object
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public M create(K tablekey, M model, Object... idArray) throws RestException {
        if (GeneralUtils.isEmpty(model)) {
            return null;
        }
        optionalCreate(tablekey, model);
        this.beforeCreate(model);
        Integer result = single(tablekey, model, idArray);
        String message = "creating method has error with " + simpleName + ": " + JsonUtils.parseJson(model);
        OptionalUtils.create(result, message, simpleName);
        this.afterCreate(model);
        this.refresh();
        return model;
    }

    /**
     * <code>update</code>
     * <p>the method.</p>
     * @param model   M <p>the model parameter is <code>M</code> type.</p>
     * @param idArray {@link java.lang.Object} <p>the id array parameter is <code>Object</code> type.</p>
     * @return M <p>the return object is <code>M</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
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
     * <p>the method.</p>
     * @param tablekey K <p>the tablekey parameter is <code>K</code> type.</p>
     * @param model    M <p>the model parameter is <code>M</code> type.</p>
     * @param idArray  {@link java.lang.Object} <p>the id array parameter is <code>Object</code> type.</p>
     * @return M <p>the return object is <code>M</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Object
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public M update(K tablekey, M model, Object... idArray) throws RestException {
        if (GeneralUtils.isEmpty(model)) {
            return null;
        }
        optionalUpdate(tablekey, model);
        this.beforeUpdate(model);
        Integer result = single(tablekey, model, idArray);
        String message = "updating method has error with " + simpleName + ": " + JsonUtils.parseJson(model);
        OptionalUtils.update(result, message, simpleName);
        this.afterUpdate(model);
        this.refresh();
        return model;
    }

    /**
     * <code>save</code>
     * <p>the method.</p>
     * @param model   M <p>the model parameter is <code>M</code> type.</p>
     * @param idArray {@link java.lang.Object} <p>the id array parameter is <code>Object</code> type.</p>
     * @return M <p>the return object is <code>M</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
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
     * <p>the method.</p>
     * @param tablekey K <p>the tablekey parameter is <code>K</code> type.</p>
     * @param model    M <p>the model parameter is <code>M</code> type.</p>
     * @param idArray  {@link java.lang.Object} <p>the id array parameter is <code>Object</code> type.</p>
     * @return M <p>the return object is <code>M</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Object
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public M save(K tablekey, M model, Object... idArray) throws RestException {
        if (GeneralUtils.isEmpty(model)) {
            return null;
        }
        optionalSave(tablekey, model);
        this.beforeSave(model);
        Integer result = single(tablekey, model, idArray);
        String message = "saving method has error with " + simpleName + ": " + JsonUtils.parseJson(model);
        OptionalUtils.save(result, message, simpleName);
        this.afterSave(model);
        this.refresh();
        return model;
    }

    /**
     * <code>saveAll</code>
     * <p>the all method.</p>
     * @param modelList {@link java.util.Collection} <p>the model list parameter is <code>Collection</code> type.</p>
     * @return {@link java.util.List} <p>the all return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
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
     * <p>the all method.</p>
     * @param modelList {@link java.util.Collection} <p>the model list parameter is <code>Collection</code> type.</p>
     * @param idArray   {@link java.lang.Object} <p>the id array parameter is <code>Object</code> type.</p>
     * @return {@link java.util.List} <p>the all return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
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
     * <p>the all method.</p>
     * @param tablekey  K <p>the tablekey parameter is <code>K</code> type.</p>
     * @param modelList {@link java.util.Collection} <p>the model list parameter is <code>Collection</code> type.</p>
     * @param idArray   {@link java.lang.Object} <p>the id array parameter is <code>Object</code> type.</p>
     * @return {@link java.util.List} <p>the all return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see java.lang.Object
     * @see java.util.List
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
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
        if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
            result = PartitionHelper.save(entityList, this.savePartition(), entities -> superMapper.saveDynamicAll(tablename, entities));
        } else {
            result = PartitionHelper.save(entityList, this.savePartition(), superMapper::saveAll);
        }
        Boolean comparer = modelList.size() == result;
        String message = "saveAll method has error with " + simpleName + ": " + JsonUtils.parseJson(modelList);
        OptionalUtils.saveAll(comparer, message, simpleName);
        this.afterSaveAll(modelList);
        this.refresh();
        return new ArrayList<>(modelList);
    }

    /**
     * <code>operateById</code>
     * <p>the by id method.</p>
     * @param id      I <p>the id parameter is <code>I</code> type.</p>
     * @param operate {@link io.github.nichetoolkit.rice.enums.OperateType} <p>the operate parameter is <code>OperateType</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.enums.OperateType
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void operateById(I id, OperateType operate) throws RestException {
        operateById(null, id, operate);
    }

    /**
     * <code>findById</code>
     * <p>the by id method.</p>
     * @param id        I <p>the id parameter is <code>I</code> type.</p>
     * @param tablename {@link java.lang.String} <p>the tablename parameter is <code>String</code> type.</p>
     * @return E <p>the by id return object is <code>E</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    private E findById(I id, String tablename) throws RestException {
        E entity;
        if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
            entity = superMapper.findDynamicById(tablename, id);
        } else {
            entity = superMapper.findById(id);
        }
        return entity;
    }

    /**
     * <code>operateById</code>
     * <p>the by id method.</p>
     * @param tablekey K <p>the tablekey parameter is <code>K</code> type.</p>
     * @param id       I <p>the id parameter is <code>I</code> type.</p>
     * @param operate  {@link io.github.nichetoolkit.rice.enums.OperateType} <p>the operate parameter is <code>OperateType</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.enums.OperateType
     * @see java.lang.SuppressWarnings
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void operateById(K tablekey, I id, OperateType operate) throws RestException {
        if (GeneralUtils.isEmpty(id) || GeneralUtils.isEmpty(operate)) {
            return;
        }
        if (superMapper instanceof OperateMapper) {
            String tablename = tablename(tablekey);
            if (DeleteMode.OPERATE != deleteMode() || (isBeforeSkip() && isAfterSkip())) {
                if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
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
                    if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
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

    /**
     * <code>operateAll</code>
     * <p>the all method.</p>
     * @param idList  {@link java.util.Collection} <p>the id list parameter is <code>Collection</code> type.</p>
     * @param operate {@link io.github.nichetoolkit.rice.enums.OperateType} <p>the operate parameter is <code>OperateType</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
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
     * <code>operatePartition</code>
     * <p>the partition method.</p>
     * @param tablename {@link java.lang.String} <p>the tablename parameter is <code>String</code> type.</p>
     * @param idList    {@link java.util.Collection} <p>the id list parameter is <code>Collection</code> type.</p>
     * @param operate   {@link io.github.nichetoolkit.rice.enums.OperateType} <p>the operate parameter is <code>OperateType</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rice.enums.OperateType
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    private void operatePartition(String tablename, Collection<I> idList, OperateType operate) throws RestException {
        if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
            PartitionHelper.partition(idList, this.deletePartition(), ids -> ((OperateMapper<I>) superMapper).operateDynamicAll(tablename, ids, operate.getKey()));
        } else {
            PartitionHelper.partition(idList, this.deletePartition(), ids -> ((OperateMapper<I>) superMapper).operateAll(ids, operate.getKey()));
        }
    }

    /**
     * <code>operateAdvice</code>
     * <p>the advice method.</p>
     * @param entityList      {@link java.util.List} <p>the entity list parameter is <code>List</code> type.</p>
     * @param operate         {@link io.github.nichetoolkit.rice.enums.OperateType} <p>the operate parameter is <code>OperateType</code> type.</p>
     * @param operateActuator {@link io.github.nichetoolkit.rest.actuator.ConsumerActuator} <p>the operate actuator parameter is <code>ConsumerActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.util.List
     * @see io.github.nichetoolkit.rice.enums.OperateType
     * @see io.github.nichetoolkit.rest.actuator.ConsumerActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
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

    /**
     * <code>findAll</code>
     * <p>the all method.</p>
     * @param idList    {@link java.util.Collection} <p>the id list parameter is <code>Collection</code> type.</p>
     * @param tablename {@link java.lang.String} <p>the tablename parameter is <code>String</code> type.</p>
     * @return {@link java.util.List} <p>the all return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see java.lang.String
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.RestException
     */
    private List<E> findAll(Collection<I> idList, String tablename) throws RestException {
        List<E> entityList;
        if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
            entityList = PartitionHelper.query(idList, this.queryPartition(), ids -> superMapper.findDynamicAll(tablename, ids));
        } else {
            entityList = PartitionHelper.query(idList, this.queryPartition(), superMapper::findAll);
        }
        return entityList;
    }

    /**
     * <code>operateAll</code>
     * <p>the all method.</p>
     * @param tablekey K <p>the tablekey parameter is <code>K</code> type.</p>
     * @param idList   {@link java.util.Collection} <p>the id list parameter is <code>Collection</code> type.</p>
     * @param operate  {@link io.github.nichetoolkit.rice.enums.OperateType} <p>the operate parameter is <code>OperateType</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rice.enums.OperateType
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
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

    /**
     * <code>operateByLinkId</code>
     * <p>the by link id method.</p>
     * @param linkId  I <p>the link id parameter is <code>I</code> type.</p>
     * @param operate {@link io.github.nichetoolkit.rice.enums.OperateType} <p>the operate parameter is <code>OperateType</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.enums.OperateType
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void operateByLinkId(I linkId, OperateType operate) throws RestException {
        operateByLinkId(null, linkId, operate);
    }

    /**
     * <code>operateByLinkId</code>
     * <p>the by link id method.</p>
     * @param tablekey K <p>the tablekey parameter is <code>K</code> type.</p>
     * @param linkId   I <p>the link id parameter is <code>I</code> type.</p>
     * @param operate  {@link io.github.nichetoolkit.rice.enums.OperateType} <p>the operate parameter is <code>OperateType</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.enums.OperateType
     * @see java.lang.SuppressWarnings
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void operateByLinkId(K tablekey, I linkId, OperateType operate) throws RestException {
        if (GeneralUtils.isEmpty(linkId)) {
            return;
        }
        if (superMapper instanceof OperateLinkMapper) {
            String tablename = tablename(tablekey);
            if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
                ((OperateLinkMapper<I>) superMapper).operateDynamicByLinkId(tablename, linkId, operate.getKey());
            } else {
                ((OperateLinkMapper<I>) superMapper).operateByLinkId(linkId, operate.getKey());
            }
            this.refresh();
        }
    }

    /**
     * <code>operateAllByLinkIds</code>
     * <p>the all by link ids method.</p>
     * @param linkIdList {@link java.util.Collection} <p>the link id list parameter is <code>Collection</code> type.</p>
     * @param operate    {@link io.github.nichetoolkit.rice.enums.OperateType} <p>the operate parameter is <code>OperateType</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rice.enums.OperateType
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void operateAllByLinkIds(Collection<I> linkIdList, OperateType operate) throws RestException {
        operateAllByLinkIds(null, linkIdList, operate);
    }

    /**
     * <code>operateAllByLinkIds</code>
     * <p>the all by link ids method.</p>
     * @param tablekey   K <p>the tablekey parameter is <code>K</code> type.</p>
     * @param linkIdList {@link java.util.Collection} <p>the link id list parameter is <code>Collection</code> type.</p>
     * @param operate    {@link io.github.nichetoolkit.rice.enums.OperateType} <p>the operate parameter is <code>OperateType</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rice.enums.OperateType
     * @see java.lang.SuppressWarnings
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void operateAllByLinkIds(K tablekey, Collection<I> linkIdList, OperateType operate) throws RestException {
        if (GeneralUtils.isEmpty(linkIdList)) {
            return;
        }
        if (superMapper instanceof OperateLinkMapper) {
            String tablename = tablename(tablekey);
            if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
                PartitionHelper.partition(linkIdList, this.deletePartition(), linkIds -> ((OperateLinkMapper<I>) superMapper).operateDynamicAllByLinkIds(tablename, linkIds, operate.getKey()));
            } else {
                PartitionHelper.partition(linkIdList, this.deletePartition(), linkIds -> ((OperateLinkMapper<I>) superMapper).operateAllByLinkIds(linkIds, operate.getKey()));
            }
            this.refresh();
        }
    }

    /**
     * <code>alertById</code>
     * <p>the by id method.</p>
     * @param id      I <p>the id parameter is <code>I</code> type.</p>
     * @param keyType {@link io.github.nichetoolkit.rest.RestKey} <p>the key type parameter is <code>RestKey</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestKey
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void alertById(I id, RestKey<Integer> keyType) throws RestException {
        alertById(null, id, keyType);
    }

    /**
     * <code>alertById</code>
     * <p>the by id method.</p>
     * @param tablekey K <p>the tablekey parameter is <code>K</code> type.</p>
     * @param id       I <p>the id parameter is <code>I</code> type.</p>
     * @param keyType  {@link io.github.nichetoolkit.rest.RestKey} <p>the key type parameter is <code>RestKey</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestKey
     * @see java.lang.SuppressWarnings
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void alertById(K tablekey, I id, RestKey<Integer> keyType) throws RestException {
        if (GeneralUtils.isEmpty(id) || GeneralUtils.isEmpty(keyType)) {
            return;
        }
        if (superMapper instanceof AlertMapper) {
            String tablename = tablename(tablekey);
            this.beforeAlert(id);
            if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
                ((AlertMapper<I>) superMapper).alertDynamicById(tablename, id, keyType.getKey());
            } else {
                ((AlertMapper<I>) superMapper).alertById(id, keyType.getKey());
            }
            this.afterAlert(id);
            this.refresh();
        }
    }

    /**
     * <code>alertAll</code>
     * <p>the all method.</p>
     * @param idList  {@link java.util.Collection} <p>the id list parameter is <code>Collection</code> type.</p>
     * @param keyType {@link io.github.nichetoolkit.rest.RestKey} <p>the key type parameter is <code>RestKey</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestKey
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void alertAll(Collection<I> idList, RestKey<Integer> keyType) throws RestException {
        alertAll(null, idList, keyType);
    }

    /**
     * <code>alertAll</code>
     * <p>the all method.</p>
     * @param tablekey K <p>the tablekey parameter is <code>K</code> type.</p>
     * @param idList   {@link java.util.Collection} <p>the id list parameter is <code>Collection</code> type.</p>
     * @param keyType  {@link io.github.nichetoolkit.rest.RestKey} <p>the key type parameter is <code>RestKey</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestKey
     * @see java.lang.SuppressWarnings
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void alertAll(K tablekey, Collection<I> idList, RestKey<Integer> keyType) throws RestException {
        if (GeneralUtils.isEmpty(idList)) {
            return;
        }
        if (superMapper instanceof AlertMapper) {
            String tablename = tablename(tablekey);
            this.beforeAlertAll(idList);
            if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
                PartitionHelper.partition(idList, this.deletePartition(), ids -> ((AlertMapper<I>) superMapper).alertDynamicAll(tablename, ids, keyType.getKey()));
            } else {
                PartitionHelper.partition(idList, this.deletePartition(), ids -> ((AlertMapper<I>) superMapper).alertAll(ids, keyType.getKey()));
            }
            this.afterAlertAll(idList);
            this.refresh();
        }
    }

    /**
     * <code>alertFieldById</code>
     * <p>the field by id method.</p>
     * @param id      I <p>the id parameter is <code>I</code> type.</p>
     * @param field   {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param keyType {@link io.github.nichetoolkit.rest.RestKey} <p>the key type parameter is <code>RestKey</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestKey
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void alertFieldById(I id, String field, RestKey<Integer> keyType) throws RestException {
        alertFieldById(null, id, field, keyType);
    }

    /**
     * <code>alertFieldById</code>
     * <p>the field by id method.</p>
     * @param tablekey K <p>the tablekey parameter is <code>K</code> type.</p>
     * @param id       I <p>the id parameter is <code>I</code> type.</p>
     * @param field    {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param keyType  {@link io.github.nichetoolkit.rest.RestKey} <p>the key type parameter is <code>RestKey</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestKey
     * @see java.lang.SuppressWarnings
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void alertFieldById(K tablekey, I id, String field, RestKey<Integer> keyType) throws RestException {
        if (GeneralUtils.isEmpty(id) || GeneralUtils.isEmpty(keyType)) {
            return;
        }
        if (superMapper instanceof AlertFieldMapper) {
            String tablename = tablename(tablekey);
            this.beforeAlert(id);
            if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
                ((AlertFieldMapper<I>) superMapper).alertDynamicFieldById(tablename, id, field, keyType.getKey());
            } else {
                ((AlertFieldMapper<I>) superMapper).alertFieldById(id, field, keyType.getKey());
            }
            this.afterAlert(id);
            this.refresh();
        }
    }

    /**
     * <code>alertFieldAll</code>
     * <p>the field all method.</p>
     * @param idList  {@link java.util.Collection} <p>the id list parameter is <code>Collection</code> type.</p>
     * @param field   {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param keyType {@link io.github.nichetoolkit.rest.RestKey} <p>the key type parameter is <code>RestKey</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestKey
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void alertFieldAll(Collection<I> idList, String field, RestKey<Integer> keyType) throws RestException {
        alertFieldAll(null, idList, field, keyType);
    }

    /**
     * <code>alertFieldAll</code>
     * <p>the field all method.</p>
     * @param tablekey K <p>the tablekey parameter is <code>K</code> type.</p>
     * @param idList   {@link java.util.Collection} <p>the id list parameter is <code>Collection</code> type.</p>
     * @param field    {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param keyType  {@link io.github.nichetoolkit.rest.RestKey} <p>the key type parameter is <code>RestKey</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestKey
     * @see java.lang.SuppressWarnings
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void alertFieldAll(K tablekey, Collection<I> idList, String field, RestKey<Integer> keyType) throws RestException {
        if (GeneralUtils.isEmpty(idList)) {
            return;
        }
        if (superMapper instanceof AlertFieldMapper) {
            String tablename = tablename(tablekey);
            this.beforeAlertAll(idList);
            if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
                PartitionHelper.partition(idList, this.deletePartition(), ids -> ((AlertFieldMapper<I>) superMapper).alertDynamicFieldAll(tablename, ids, field, keyType.getKey()));
            } else {
                PartitionHelper.partition(idList, this.deletePartition(), ids -> ((AlertFieldMapper<I>) superMapper).alertFieldAll(ids, field, keyType.getKey()));
            }
            this.afterAlertAll(idList);
            this.refresh();
        }
    }

    /**
     * <code>removeById</code>
     * <p>the by id method.</p>
     * @param id I <p>the id parameter is <code>I</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void removeById(I id) throws RestException {
        removeById(null, id);
    }

    /**
     * <code>removeById</code>
     * <p>the by id method.</p>
     * @param tablekey K <p>the tablekey parameter is <code>K</code> type.</p>
     * @param id       I <p>the id parameter is <code>I</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.SuppressWarnings
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void removeById(K tablekey, I id) throws RestException {
        if (GeneralUtils.isEmpty(id)) {
            return;
        }
        String logicSign = logicSign();
        if (superMapper instanceof RemoveMapper) {
            String tablename = tablename(tablekey);
            if (DeleteMode.REMOVE != deleteMode() || (isBeforeSkip() && isAfterSkip())) {
                if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
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
                    if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
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

    /**
     * <code>removeAll</code>
     * <p>the all method.</p>
     * @param idList {@link java.util.Collection} <p>the id list parameter is <code>Collection</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void removeAll(Collection<I> idList) throws RestException {
        removeAll(null, idList);
    }

    /**
     * <code>removePartition</code>
     * <p>the partition method.</p>
     * @param tablename  {@link java.lang.String} <p>the tablename parameter is <code>String</code> type.</p>
     * @param idList     {@link java.util.Collection} <p>the id list parameter is <code>Collection</code> type.</p>
     * @param logicSign {@link java.lang.String} <p>the remove sign parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.util.Collection
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    private void removePartition(String tablename, Collection<I> idList, String logicSign) throws RestException {
        if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
            PartitionHelper.delete(idList, this.deletePartition(), ids -> ((RemoveMapper<I>) superMapper).removeDynamicAll(tablename, ids, logicSign));
        } else {
            PartitionHelper.delete(idList, this.deletePartition(), ids -> ((RemoveMapper<I>) superMapper).removeAll(ids, logicSign));
        }
    }

    /**
     * <code>removeAdvice</code>
     * <p>the advice method.</p>
     * @param entityList     {@link java.util.List} <p>the entity list parameter is <code>List</code> type.</p>
     * @param logicSign     {@link java.lang.String} <p>the remove sign parameter is <code>String</code> type.</p>
     * @param removeActuator {@link io.github.nichetoolkit.rest.actuator.ConsumerActuator} <p>the remove actuator parameter is <code>ConsumerActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.util.List
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.ConsumerActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
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

    /**
     * <code>removeAll</code>
     * <p>the all method.</p>
     * @param tablekey K <p>the tablekey parameter is <code>K</code> type.</p>
     * @param idList   {@link java.util.Collection} <p>the id list parameter is <code>Collection</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void removeAll(K tablekey, Collection<I> idList) throws RestException {
        if (GeneralUtils.isEmpty(idList)) {
            return;
        }
        String logicSign = logicSign();
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

    /**
     * <code>removeByLinkId</code>
     * <p>the by link id method.</p>
     * @param linkId I <p>the link id parameter is <code>I</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void removeByLinkId(I linkId) throws RestException {
        removeByLinkId(null, linkId);
    }

    /**
     * <code>removeByLinkId</code>
     * <p>the by link id method.</p>
     * @param tablekey K <p>the tablekey parameter is <code>K</code> type.</p>
     * @param linkId   I <p>the link id parameter is <code>I</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.SuppressWarnings
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void removeByLinkId(K tablekey, I linkId) throws RestException {
        if (GeneralUtils.isEmpty(linkId)) {
            return;
        }
        String logicSign = logicSign();
        if (superMapper instanceof RemoveLinkMapper) {
            String tablename = tablename(tablekey);
            if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
                ((RemoveLinkMapper<I>) superMapper).removeDynamicByLinkId(tablename, linkId, logicSign);
            } else {
                ((RemoveLinkMapper<I>) superMapper).removeByLinkId(linkId, logicSign);
            }
            this.refresh();
        }
    }

    /**
     * <code>removeAllByLinkIds</code>
     * <p>the all by link ids method.</p>
     * @param linkIdList {@link java.util.Collection} <p>the link id list parameter is <code>Collection</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void removeAllByLinkIds(Collection<I> linkIdList) throws RestException {
        removeAllByLinkIds(null, linkIdList);
    }

    /**
     * <code>removeAllByLinkIds</code>
     * <p>the all by link ids method.</p>
     * @param tablekey   K <p>the tablekey parameter is <code>K</code> type.</p>
     * @param linkIdList {@link java.util.Collection} <p>the link id list parameter is <code>Collection</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see java.lang.SuppressWarnings
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void removeAllByLinkIds(K tablekey, Collection<I> linkIdList) throws RestException {
        if (GeneralUtils.isEmpty(linkIdList)) {
            return;
        }
        String logicSign = logicSign();
        if (superMapper instanceof RemoveLinkMapper) {
            String tablename = tablename(tablekey);
            if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
                PartitionHelper.partition(linkIdList, this.deletePartition(), linkIds -> ((RemoveLinkMapper<I>) superMapper).removeDynamicAllByLinkIds(tablename, linkIds, logicSign));
            } else {
                PartitionHelper.partition(linkIdList, this.deletePartition(), linkIds -> ((RemoveLinkMapper<I>) superMapper).removeAllByLinkIds(linkIds, logicSign));
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
                if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
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
                    if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
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

    /**
     * <code>deletePartition</code>
     * <p>the partition method.</p>
     * @param tablename {@link java.lang.String} <p>the tablename parameter is <code>String</code> type.</p>
     * @param idList    {@link java.util.Collection} <p>the id list parameter is <code>Collection</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.RestException
     */
    private void deletePartition(String tablename, Collection<I> idList) throws RestException {
        if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
            PartitionHelper.delete(idList, this.deletePartition(), ids -> superMapper.deleteDynamicAll(tablename, ids));
        } else {
            PartitionHelper.delete(idList, this.deletePartition(), ids -> superMapper.deleteAll(ids));
        }
    }

    /**
     * <code>deleteAdvice</code>
     * <p>the advice method.</p>
     * @param entityList     {@link java.util.List} <p>the entity list parameter is <code>List</code> type.</p>
     * @param deleteActuator {@link io.github.nichetoolkit.rest.actuator.AnchorActuator} <p>the delete actuator parameter is <code>AnchorActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.actuator.AnchorActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
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
                deletePartition(tablename, idList);
            } else {
                List<E> entityList = superMapper.findAll(idList);
                if (GeneralUtils.isNotEmpty(entityList)) {
                    deleteAdvice(entityList, () -> deletePartition(tablename, idList));
                }
            }
        }
    }

    /**
     * <code>deleteByLinkId</code>
     * <p>the by link id method.</p>
     * @param linkId I <p>the link id parameter is <code>I</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void deleteByLinkId(I linkId) throws RestException {
        deleteByLinkId(null, linkId);
    }

    /**
     * <code>deleteByLinkId</code>
     * <p>the by link id method.</p>
     * @param tablekey K <p>the tablekey parameter is <code>K</code> type.</p>
     * @param linkId   I <p>the link id parameter is <code>I</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.SuppressWarnings
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
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
            if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
                ((DeleteLinkMapper<I>) superMapper).deleteDynamicByLinkId(tablename, linkId);
            } else {
                ((DeleteLinkMapper<I>) superMapper).deleteByLinkId(linkId);
            }
            this.refresh();
        }
    }

    /**
     * <code>deleteAllByLinkIds</code>
     * <p>the all by link ids method.</p>
     * @param linkIdList {@link java.util.Collection} <p>the link id list parameter is <code>Collection</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void deleteAllByLinkIds(Collection<I> linkIdList) throws RestException {
        deleteAllByLinkIds(null, linkIdList);
    }


    /**
     * <code>deleteAllByLinkIds</code>
     * <p>the all by link ids method.</p>
     * @param tablekey   K <p>the tablekey parameter is <code>K</code> type.</p>
     * @param linkIdList {@link java.util.Collection} <p>the link id list parameter is <code>Collection</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see java.lang.SuppressWarnings
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
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
            if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
                PartitionHelper.delete(linkIdList, this.deletePartition(), linkIds -> ((DeleteLinkMapper<I>) superMapper).deleteDynamicAllByLinkIds(tablename, linkIds));
            } else {
                PartitionHelper.delete(linkIdList, this.deletePartition(), linkIds -> ((DeleteLinkMapper<I>) superMapper).deleteAllByLinkIds(linkIds));
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
                if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
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
                if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
                    entityList = PartitionHelper.query(idList, this.queryPartition(), ids -> loadMapper.findDynamicAllLoad(tablename, ids, isLoadArray));
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

    /**
     * <code>findAllByWhere</code>
     * <p>the all by where method.</p>
     * @param whereSql  {@link java.lang.String} <p>the where sql parameter is <code>String</code> type.</p>
     * @param tablename {@link java.lang.String} <p>the tablename parameter is <code>String</code> type.</p>
     * @return {@link java.util.List} <p>the all by where return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.RestException
     */
    private List<E> findAllByWhere(String whereSql, String tablename) throws RestException {
        List<E> entityList;
        if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
            entityList = superMapper.findDynamicAllByWhere(tablename, whereSql);
        } else {
            entityList = superMapper.findAllByWhere(whereSql);
        }
        return entityList;
    }

    /**
     * <code>queryAllWithFilter</code>
     * <p>the all with filter method.</p>
     * @param filter F <p>the filter parameter is <code>F</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.RestPage} <p>the all with filter return object is <code>RestPage</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestPage
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
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
                if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
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
                if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
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
                if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
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
        List<M> modelList = modelActuator(entityList, isLoadArray);
        queryFilterCache.remove();
        return RestPage.result(modelList, page);
    }

    /**
     * <code>deleteAllByWhere</code>
     * <p>the all by where method.</p>
     * @param whereSql  {@link java.lang.String} <p>the where sql parameter is <code>String</code> type.</p>
     * @param tablename {@link java.lang.String} <p>the tablename parameter is <code>String</code> type.</p>
     * @param filter    F <p>the filter parameter is <code>F</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    private void deleteAllByWhere(String whereSql, String tablename, F filter) throws RestException {
        if (DeleteMode.DELETE != deleteMode() || (isBeforeSkip() && isAfterSkip())) {
            if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
                superMapper.deleteDynamicAllByWhere(tablename, whereSql);
            } else {
                superMapper.deleteAllByWhere(whereSql);
            }
        } else {
            String queryWhereSql = queryWhereSql(filter);
            List<E> entityList = superMapper.findAllByWhere(queryWhereSql);
            if (GeneralUtils.isNotEmpty(entityList)) {
                deleteAdvice(entityList, () -> {
                    if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
                        superMapper.deleteDynamicAllByWhere(tablename, whereSql);
                    } else {
                        superMapper.deleteAllByWhere(whereSql);
                    }
                });
            }
        }
    }

    /**
     * <code>deleteAllWithFilter</code>
     * <p>the all with filter method.</p>
     * @param filter F <p>the filter parameter is <code>F</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
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
                            if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
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
                                    if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
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

    /**
     * <code>removeAllByWhere</code>
     * <p>the all by where method.</p>
     * @param removeWhereSql {@link java.lang.String} <p>the remove where sql parameter is <code>String</code> type.</p>
     * @param tablename      {@link java.lang.String} <p>the tablename parameter is <code>String</code> type.</p>
     * @param filter         F <p>the filter parameter is <code>F</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    private void removeAllByWhere(String removeWhereSql, String tablename, F filter) throws RestException {
        if (!(superMapper instanceof RemoveMapper)) {
            throw new UnsupportedErrorException("the mapper is not support method of 'removeAllWithFilter' with the delete model is 'REMOVE' !");
        }
        String logicSign = logicSign();
        if (DeleteMode.REMOVE != deleteMode() || (isBeforeSkip() && isAfterSkip())) {
            if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
                ((RemoveMapper<I>) superMapper).removeDynamicAllByWhere(tablename, removeWhereSql, logicSign);
            } else {
                ((RemoveMapper<I>) superMapper).removeAllByWhere(removeWhereSql, logicSign);
            }
        } else {
            String queryWhereSql = queryWhereSql(filter);
            List<E> entityList = superMapper.findAllByWhere(queryWhereSql);
            if (GeneralUtils.isNotEmpty(entityList)) {
                removeAdvice(entityList, logicSign, sign -> {
                    if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
                        ((RemoveMapper<I>) superMapper).removeDynamicAllByWhere(tablename, removeWhereSql, sign);
                    } else {
                        ((RemoveMapper<I>) superMapper).removeAllByWhere(removeWhereSql, sign);
                    }
                });
            }
        }
    }

    /**
     * <code>removeAllWithFilter</code>
     * <p>the all with filter method.</p>
     * @param filter F <p>the filter parameter is <code>F</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.SuppressWarnings
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void removeAllWithFilter(F filter) throws RestException {
        optionalRemoveFilter(filter);
        String removeWhereSql = removeWhereSql(filter);
        K tablekey = tablekey(filter);
        String tablename = tablename(tablekey);
        if (GeneralUtils.isNotEmpty(removeWhereSql)) {
            String logicSign = logicSign();
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
                        if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
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
                                if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
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

    /**
     * <code>operateAllByWhere</code>
     * <p>the all by where method.</p>
     * @param operateWhereSql {@link java.lang.String} <p>the operate where sql parameter is <code>String</code> type.</p>
     * @param tablename       {@link java.lang.String} <p>the tablename parameter is <code>String</code> type.</p>
     * @param filter          F <p>the filter parameter is <code>F</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    private void operateAllByWhere(String operateWhereSql, String tablename, F filter) throws RestException {
        if (!(superMapper instanceof OperateMapper)) {
            throw new UnsupportedErrorException("the mapper is not support method of 'operateAllWithFilter' with the delete model is 'OPERATE' !");
        }
        if (DeleteMode.OPERATE != deleteMode() || (isBeforeSkip() && isAfterSkip())) {
            if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
                ((OperateMapper<I>) superMapper).operateDynamicAllByWhere(tablename, operateWhereSql, OperateType.REMOVE.getKey());
            } else {
                ((OperateMapper<I>) superMapper).operateAllByWhere(operateWhereSql, OperateType.REMOVE.getKey());
            }
        } else {
            String queryWhereSql = queryWhereSql(filter);
            List<E> entityList = superMapper.findAllByWhere(queryWhereSql);
            if (GeneralUtils.isNotEmpty(entityList)) {
                operateAdvice(entityList, OperateType.REMOVE, operate -> {
                    if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
                        ((OperateMapper<I>) superMapper).operateDynamicAllByWhere(tablename, operateWhereSql, operate.getKey());
                    } else {
                        ((OperateMapper<I>) superMapper).operateAllByWhere(operateWhereSql, operate.getKey());
                    }
                });
            }
        }
    }


    /**
     * <code>operateAllWithFilter</code>
     * <p>the all with filter method.</p>
     * @param filter F <p>the filter parameter is <code>F</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.SuppressWarnings
     * @see org.springframework.transaction.annotation.Transactional
     * @see io.github.nichetoolkit.rest.RestException
     */
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
                        if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
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
                                if (isDynamicTable() && GeneralUtils.isNotEmpty(tablename)) {
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

    /**
     * <code>DEFAULT_CREATE_ACTUATOR</code>
     * {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>the <code>DEFAULT_CREATE_ACTUATOR</code> field.</p>
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     */
    private final BiConsumerActuator<K, M> DEFAULT_CREATE_ACTUATOR = (K tablekey, @NonNull M model) -> {
        model.setId(ClazzHelper.generate(model));
        optionalInit(model);
        optional(model);
        if (createActuator != null) {
            createActuator.actuate(tablekey, model);
        }
        if (model.isSaveLower(SaveType.CREATE)) {
            model.setSave(SaveType.CREATE);
        }
    };

    /**
     * <code>DEFAULT_UPDATE_ACTUATOR</code>
     * {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>the <code>DEFAULT_UPDATE_ACTUATOR</code> field.</p>
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings(value = "unchecked")
    private final BiConsumerActuator<K, M> DEFAULT_UPDATE_ACTUATOR = (K tablekey, @NonNull M model) -> {
        if (isIdExist()) {
            boolean exist;
            if (isDynamicTable() && model instanceof RestTablekey) {
                exist = existById(((RestTablekey<K>) model).getTablekey(), model.getId());
            } else {
                exist = existById(model.getId());
            }
            String message = "the data no found，id: " + model.getId();
            OptionalUtils.falseable(exist, message, "id", DataQueryException::new);
        }
        optional(model);
        if (updateActuator != null) {
            updateActuator.actuate(tablekey, model);
        }
        if (model.isSaveLower(SaveType.UPDATE)) {
            model.setSave(SaveType.UPDATE);
        }
    };

    /**
     * <code>DEFAULT_SAVE_ACTUATOR</code>
     * {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>the <code>DEFAULT_SAVE_ACTUATOR</code> field.</p>
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings(value = "unchecked")
    private final BiConsumerActuator<K, M> DEFAULT_SAVE_ACTUATOR = (K tablekey, @NonNull M model) -> {
        if (isIdExist()) {
            boolean exist;
            if (isDynamicTable() && model instanceof RestTablekey) {
                exist = existById(((RestTablekey<K>) model).getTablekey(), model.getId());
            } else {
                exist = existById(model.getId());
            }
            if (!exist && isIdInvade()) {
                invadeActuator().actuate(tablekey, model);
            } else {
                String message = "the data no found，id: " + model.getId();
                OptionalUtils.falseable(exist, message, "id", DataQueryException::new);
                optional(model);
                if (updateActuator != null) {
                    updateActuator.actuate(tablekey, model);
                }
                if (model.isSaveLower(SaveType.UPDATE)) {
                    model.setSave(SaveType.UPDATE);
                }
            }
        } else {
            updateActuator().actuate(tablekey, model);
        }
    };

    /**
     * <code>DEFAULT_INVADE_ACTUATOR</code>
     * {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>the <code>DEFAULT_INVADE_ACTUATOR</code> field.</p>
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     */
    private final BiConsumerActuator<K, M> DEFAULT_INVADE_ACTUATOR = (K tablekey, @NonNull M model) -> {
        optionalInit(model);
        optional(model);
        if (createActuator != null) {
            createActuator.actuate(tablekey, model);
        }
        if (model.isSaveLower(SaveType.CREATE)) {
            model.setSave(SaveType.CREATE);
        }
    };

    /**
     * <code>createActuator</code>
     * <p>the actuator method.</p>
     * @return {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>the actuator return object is <code>BiConsumerActuator</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     */
    protected BiConsumerActuator<K, M> createActuator() {
        return DEFAULT_CREATE_ACTUATOR;
    }

    /**
     * <code>updateActuator</code>
     * <p>the actuator method.</p>
     * @return {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>the actuator return object is <code>BiConsumerActuator</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     */
    protected BiConsumerActuator<K, M> updateActuator() {
        return DEFAULT_UPDATE_ACTUATOR;
    }

    /**
     * <code>saveActuator</code>
     * <p>the actuator method.</p>
     * @return {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>the actuator return object is <code>BiConsumerActuator</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     */
    protected BiConsumerActuator<K, M> saveActuator() {
        return DEFAULT_SAVE_ACTUATOR;
    }

    /**
     * <code>invadeActuator</code>
     * <p>the actuator method.</p>
     * @return {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>the actuator return object is <code>BiConsumerActuator</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     */
    protected BiConsumerActuator<K, M> invadeActuator() {
        return DEFAULT_INVADE_ACTUATOR;
    }

    /**
     * <code>isIdInvade</code>
     * <p>the id invade method.</p>
     * @return {@link java.lang.Boolean} <p>the id invade return object is <code>Boolean</code> type.</p>
     * @see java.lang.Boolean
     */
    protected Boolean isIdInvade() {
        return beanProperties.isIdInvade();
    }

    /**
     * <code>isIdExist</code>
     * <p>the id exist method.</p>
     * @return {@link java.lang.Boolean} <p>the id exist return object is <code>Boolean</code> type.</p>
     * @see java.lang.Boolean
     */
    protected Boolean isIdExist() {
        return beanProperties.isIdExist();
    }

    /**
     * <code>isNameNonnull</code>
     * <p>the name nonnull method.</p>
     * @return {@link java.lang.Boolean} <p>the name nonnull return object is <code>Boolean</code> type.</p>
     * @see java.lang.Boolean
     */
    protected Boolean isNameNonnull() {
        return beanProperties.isNameNonnull();
    }

    /**
     * <code>isNameUnique</code>
     * <p>the name unique method.</p>
     * @return {@link java.lang.Boolean} <p>the name unique return object is <code>Boolean</code> type.</p>
     * @see java.lang.Boolean
     */
    protected Boolean isNameUnique() {
        return beanProperties.isNameUnique();
    }

    /**
     * <code>isModelUnique</code>
     * <p>the model unique method.</p>
     * @return {@link java.lang.Boolean} <p>the model unique return object is <code>Boolean</code> type.</p>
     * @see java.lang.Boolean
     */
    protected Boolean isModelUnique() {
        return beanProperties.isModelUnique();
    }

    /**
     * <code>isDynamicTable</code>
     * <p>the dynamic table method.</p>
     * @return {@link java.lang.Boolean} <p>the dynamic table return object is <code>Boolean</code> type.</p>
     * @see java.lang.Boolean
     */
    protected Boolean isDynamicTable() {
        return beanProperties.isDynamicTable();
    }

    /**
     * <code>deleteMode</code>
     * <p>the mode method.</p>
     * @return {@link io.github.nichetoolkit.rice.enums.DeleteMode} <p>the mode return object is <code>DeleteMode</code> type.</p>
     * @see io.github.nichetoolkit.rice.enums.DeleteMode
     */
    public DeleteMode deleteMode() {
        return beanProperties.deleteMode();
    }

    /**
     * <code>removeMode</code>
     * <p>the mode method.</p>
     * @return {@link io.github.nichetoolkit.rice.enums.RemoveMode} <p>the mode return object is <code>RemoveMode</code> type.</p>
     * @see io.github.nichetoolkit.rice.enums.RemoveMode
     */
    public RemoveMode removeMode() {
        return beanProperties.removeMode();
    }

    /**
     * <code>pinpointSign</code>
     * <p>the sign method.</p>
     * @return {@link java.lang.Boolean} <p>the sign return object is <code>Boolean</code> type.</p>
     * @see java.lang.Boolean
     */
    public Boolean pinpointEnabled() {
        return beanProperties.pinpointEnabled();
    }

    /**
     * <code>booleanSign</code>
     * <p>the sign method.</p>
     * @return {@link java.lang.Boolean} <p>the sign return object is <code>Boolean</code> type.</p>
     * @see java.lang.Boolean
     */
    public Boolean booleanSign() {
        return beanProperties.booleanSign();
    }

    /**
     * <code>booleanValue</code>
     * <p>the value method.</p>
     * @return {@link java.lang.Boolean} <p>the value return object is <code>Boolean</code> type.</p>
     * @see java.lang.Boolean
     */
    public Boolean booleanValue() {
        return beanProperties.booleanValue();
    }

    /**
     * <code>numberSign</code>
     * <p>the sign method.</p>
     * @return {@link java.lang.Integer} <p>the sign return object is <code>Integer</code> type.</p>
     * @see java.lang.Integer
     */
    public Integer numberSign() {
        return beanProperties.numberSign();
    }

    /**
     * <code>numberValue</code>
     * <p>the value method.</p>
     * @return {@link java.lang.Integer} <p>the value return object is <code>Integer</code> type.</p>
     * @see java.lang.Integer
     */
    public Integer numberValue() {
        return beanProperties.numberValue();
    }

    /**
     * <code>removeSign</code>
     * <p>the sign method.</p>
     * @return {@link java.lang.String} <p>the sign return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public String logicSign() {
        return RemoveMode.sign(removeMode(), booleanSign(), numberSign());
    }

    /**
     * <code>removeValue</code>
     * <p>the value method.</p>
     * @return {@link java.lang.String} <p>the value return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public String logicValue() {
        return RemoveMode.value(removeMode(), booleanValue(), numberValue());
    }

    /**
     * <code>isBeforeSkip</code>
     * <p>the before skip method.</p>
     * @return {@link java.lang.Boolean} <p>the before skip return object is <code>Boolean</code> type.</p>
     * @see java.lang.Boolean
     */
    public Boolean isBeforeSkip() {
        return beanProperties.isBeforeSkip();
    }

    /**
     * <code>isAfterSkip</code>
     * <p>the after skip method.</p>
     * @return {@link java.lang.Boolean} <p>the after skip return object is <code>Boolean</code> type.</p>
     * @see java.lang.Boolean
     */
    public Boolean isAfterSkip() {
        return beanProperties.isAfterSkip();
    }

    /**
     * <code>queryPartition</code>
     * <p>the partition method.</p>
     * @return {@link java.lang.Integer} <p>the partition return object is <code>Integer</code> type.</p>
     * @see java.lang.Integer
     */
    public Integer queryPartition() {
        return beanProperties.getPartitionQuery();
    }

    /**
     * <code>savePartition</code>
     * <p>the partition method.</p>
     * @return {@link java.lang.Integer} <p>the partition return object is <code>Integer</code> type.</p>
     * @see java.lang.Integer
     */
    public Integer savePartition() {
        return beanProperties.getPartitionQuery();
    }

    /**
     * <code>deletePartition</code>
     * <p>the partition method.</p>
     * @return {@link java.lang.Integer} <p>the partition return object is <code>Integer</code> type.</p>
     * @see java.lang.Integer
     */
    public Integer deletePartition() {
        return beanProperties.getPartitionQuery();
    }

    /**
     * <code>tablename</code>
     * <p>the method.</p>
     * @param tablekey K <p>the tablekey parameter is <code>K</code> type.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    protected String tablename(K tablekey) throws RestException {
        if (GeneralUtils.isEmpty(tablekey)) {
            return null;
        }
        Map<K, String> tablenameMap = tablenameMapCache.get();
        if (GeneralUtils.isEmpty(tablenameMap)) {
            tablenameMap = new HashMap<>();
            tablenameMapCache.set(tablenameMap);
        }
        if (isDynamicTable()) {
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

    /**
     * <code>tablename</code>
     * <p>the method.</p>
     * @param tablekey K <p>the tablekey parameter is <code>K</code> type.</p>
     * @param model    M <p>the model parameter is <code>M</code> type.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    protected String tablename(K tablekey, M model) throws RestException {
        if (GeneralUtils.isEmpty(tablekey) && GeneralUtils.isEmpty(model)) {
            return null;
        }
        Map<K, String> tablenameMap = tablenameMapCache.get();
        if (isDynamicTable() && GeneralUtils.isNotEmpty(tablenameMap)) {
            if (GeneralUtils.isNotEmpty(tablekey)) {
                return tablenameMap.get(tablekey);
            } else if (GeneralUtils.isNotEmpty(model) && model instanceof RestTablekey) {
                RestTablekey<K> table = (RestTablekey<K>) model;
                return tablenameMap.get(table.getTablekey());
            }
        }
        return null;
    }

    /**
     * <code>tablename</code>
     * <p>the method.</p>
     * @param tablekey  K <p>the tablekey parameter is <code>K</code> type.</p>
     * @param modelList {@link java.util.Collection} <p>the model list parameter is <code>Collection</code> type.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    protected String tablename(K tablekey, Collection<M> modelList) throws RestException {
        if (GeneralUtils.isEmpty(tablekey) && GeneralUtils.isEmpty(modelList)) {
            return null;
        } else if (GeneralUtils.isNotEmpty(tablekey)) {
            Map<K, String> tablenameMap = tablenameMapCache.get();
            if (isDynamicTable() && GeneralUtils.isNotEmpty(tablenameMap)) {
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

    /**
     * <code>optionalName</code>
     * <p>the name method.</p>
     * @param model M <p>the model parameter is <code>M</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    protected void optionalName(@NonNull M model) throws RestException {
    }

    /**
     * <code>optionalInit</code>
     * <p>the init method.</p>
     * @param model M <p>the model parameter is <code>M</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    protected void optionalInit(@NonNull M model) throws RestException {
    }

    /**
     * <code>optionalTablename</code>
     * <p>the tablename method.</p>
     * @param tablename {@link java.lang.String} <p>the tablename parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    protected void optionalTablename(@NonNull String tablename) throws RestException {
    }

    /**
     * <code>dynamicTablename</code>
     * <p>the tablename method.</p>
     * @param tablekey K <p>the tablekey parameter is <code>K</code> type.</p>
     * @return {@link java.lang.String} <p>the tablename return object is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.lang.NonNull
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    protected String dynamicTablename(@NonNull K tablekey) throws RestException {
        return null;
    }

    /**
     * <code>optionalLogicSign</code>
     * <p>the logic sign method.</p>
     * @param model M <p>the model parameter is <code>M</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    private void optionalLogicSign(@NonNull M model) throws RestException {
        model.setLogicSign(Optional.ofNullable(model.getLogicSign()).orElse(logicValue()));
        optionalName(model);
    }

    /**
     * <code>optionalDynamicTable</code>
     * <p>the dynamic table method.</p>
     * @param tablekey K <p>the tablekey parameter is <code>K</code> type.</p>
     * @param model    M <p>the model parameter is <code>M</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.lang.NonNull
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    private void optionalDynamicTable(K tablekey, @NonNull M model) throws RestException {
        if (isDynamicTable()) {
            Map<K, String> tablenameMap = tablenameMapCache.get();
            if (GeneralUtils.isEmpty(tablenameMap)) {
                tablenameMap = new HashMap<>();
                tablenameMapCache.set(tablenameMap);
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
        optionalLogicSign(model);
    }

    /**
     * <code>optionalCreate</code>
     * <p>the create method.</p>
     * @param tablekey K <p>the tablekey parameter is <code>K</code> type.</p>
     * @param model    M <p>the model parameter is <code>M</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    private void optionalCreate(K tablekey, @NonNull M model) throws RestException {
        optionalDynamicTable(tablekey, model);
        if (GeneralUtils.isEmpty(model.getId()) || !isIdInvade()) {
            createActuator().actuate(tablekey, model);
        } else {
            invadeActuator().actuate(tablekey, model);
        }
    }

    /**
     * <code>optionalUpdate</code>
     * <p>the update method.</p>
     * @param tablekey K <p>the tablekey parameter is <code>K</code> type.</p>
     * @param model    M <p>the model parameter is <code>M</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    private void optionalUpdate(K tablekey, @NonNull M model) throws RestException {
        OptionalUtils.idable(model.getId());
        optionalDynamicTable(tablekey, model);
        updateActuator().actuate(tablekey, model);
    }

    /**
     * <code>optionalSave</code>
     * <p>the save method.</p>
     * @param tablekey K <p>the tablekey parameter is <code>K</code> type.</p>
     * @param model    M <p>the model parameter is <code>M</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    private void optionalSave(K tablekey, @NonNull M model) throws RestException {
        optionalDynamicTable(tablekey, model);
        if (GeneralUtils.isEmpty(model.getId())) {
            createActuator().actuate(tablekey, model);
        } else {
            saveActuator().actuate(tablekey, model);
        }
    }
}
