package io.github.nichetoolkit.rice.service;

import com.github.pagehelper.Page;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestKey;
import io.github.nichetoolkit.rest.actuator.ConsumerActuator;
import io.github.nichetoolkit.rest.error.data.DataQueryException;
import io.github.nichetoolkit.rest.helper.OptionalHelper;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.util.JsonUtils;
import io.github.nichetoolkit.rice.IdEntity;
import io.github.nichetoolkit.rice.IdModel;
import io.github.nichetoolkit.rice.RestPage;
import io.github.nichetoolkit.rice.clazz.ClazzHelper;
import io.github.nichetoolkit.rice.configure.RiceBeanKeyProperties;
import io.github.nichetoolkit.rice.configure.RiceBeanNameProperties;
import io.github.nichetoolkit.rice.enums.OperateType;
import io.github.nichetoolkit.rice.error.service.ServiceUnknownException;
import io.github.nichetoolkit.rice.filter.IdFilter;
import io.github.nichetoolkit.rice.helper.MEBuilderHelper;
import io.github.nichetoolkit.rice.mapper.SuperMapper;
import io.github.nichetoolkit.rice.mapper.LoadMapper;
import io.github.nichetoolkit.rice.service.advice.BuilderAdvice;
import io.github.nichetoolkit.rice.service.advice.SaveAdvice;
import io.github.nichetoolkit.rice.service.advice.ServiceAdvice;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * <p>SuperService</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@SuppressWarnings("RedundantThrows")
@Slf4j
public abstract class SuperService<I, M extends IdModel<I>, E extends IdEntity<I>, F extends IdFilter<I>>
        implements InitializingBean, ApplicationContextAware, OptionalService<I, M>, ServiceAdvice<I, F>, SaveAdvice<I, M> {

    private static ApplicationContext applicationContext;

    protected ConsumerActuator<M> createActuator;

    protected ConsumerActuator<M> updateActuator;

    protected SuperMapper<E, I> superMapper;

    private String simpleName;

    protected RiceBeanNameProperties nameProperties;

    protected RiceBeanKeyProperties keyProperties;

    protected ThreadLocal<F> filterCache = new ThreadLocal<>();

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        SuperService.applicationContext = applicationContext;
    }

    @SuppressWarnings(value = "unchecked")
    @Override
    public void afterPropertiesSet() throws Exception {
        this.keyProperties = applicationContext.getBean(RiceBeanKeyProperties.class);
        if (GeneralUtils.isEmpty(keyProperties)) {
            String message = "the bean of 'RiceBeanKeyProperties' type is not found!";
            log.warn(message);
            keyProperties = new RiceBeanKeyProperties();
        }
        this.nameProperties = applicationContext.getBean(RiceBeanNameProperties.class);
        if (GeneralUtils.isEmpty(nameProperties)) {
            String message = "the bean of 'RiceBeanNameProperties' type is not found!";
            log.error(message);
            throw new ServiceUnknownException(RiceBeanNameProperties.class.getName(), this.getClass().getName(),message);
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
                    throw new ServiceUnknownException(SuperMapper.class.getName(), this.getClass().getName(),message,exception1);
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
            builderAdvice.buildEntity(model,entity,idArray);
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
            /* 当buildEntity和buildEntityList都被复写的时候 优先调用buildEntityList */
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

    protected void refresh() throws RestException {}

    public M create(M model) throws RestException {
        return create(model, (I[]) null);
    }

    @SuppressWarnings(value = "unchecked")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public M create(M model, I... idArray) throws RestException {
        if (GeneralUtils.isEmpty(model)) {
            return null;
        }
        optionalCreate(model);
        E entity = entityActuator(model, idArray);
        Integer result = superMapper.save(entity);
        String message = "creating method has error with " + simpleName + ": " + JsonUtils.parseJson(model);
        OptionalHelper.create(result, message, simpleName);
        this.afterCreate(model);
        this.refresh();
        return model;
    }

    public M update(M model) throws RestException {
        return update(model, (I[]) null);
    }

    @SuppressWarnings(value = "unchecked")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public M update(M model, I... idArray) throws RestException {
        if (GeneralUtils.isEmpty(model)) {
            return null;
        }
        optionalUpdate(model);
        E entity = entityActuator(model, idArray);
        Integer result = superMapper.save(entity);
        String message = "updating method has error with " + simpleName + ": " + JsonUtils.parseJson(model);
        OptionalHelper.update(result, message, simpleName);
        this.afterUpdate(model);
        this.refresh();
        return model;
    }

    public M save(M model) throws RestException {
        return save(model, (I[]) null);
    }

    @SuppressWarnings(value = "unchecked")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public M save(M model, I... idArray) throws RestException {
        if (GeneralUtils.isEmpty(model)) {
            return null;
        }
        optionalSave(model);
        E entity = entityActuator(model, idArray);
        Integer result = superMapper.save(entity);
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
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public List<M> saveAll(Collection<M> modelList, I... idArray) throws RestException {
        if (GeneralUtils.isEmpty(modelList)) {
            return Collections.emptyList();
        }
        List<E> entityList = entityActuator(modelList, this::optionalSave, idArray);
        Boolean comparer = modelList.size() == superMapper.saveAll(entityList);
        String message = "saveAll method has error with " + simpleName + ": " + JsonUtils.parseJson(modelList);
        OptionalHelper.saveAll(comparer, message, simpleName);
        this.afterSaveAll(modelList);
        this.refresh();
        return new ArrayList<>(modelList);
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void operateById(I id, OperateType operate) throws RestException {
        if (GeneralUtils.isEmpty(id) || GeneralUtils.isEmpty(operate)) {
            return;
        }
        superMapper.operateById(id, operate.getKey());
        this.refresh();
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void operateAll(Collection<I> idList, OperateType operate) throws RestException {
        if (GeneralUtils.isEmpty(idList)) {
            return;
        }
        superMapper.operateAll(idList,operate.getKey());
        this.refresh();
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void alertById(I id, RestKey<Integer> keyType) throws RestException {
        if (GeneralUtils.isEmpty(id) || GeneralUtils.isEmpty(keyType)) {
            return;
        }
        superMapper.alertById(id, keyType.getKey());
        this.refresh();
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void alertAll(Collection<I> idList, RestKey<Integer> keyType) throws RestException {
        if (GeneralUtils.isEmpty(idList)) {
            return;
        }
        superMapper.alertAll(idList, keyType.getKey());
        this.refresh();
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void removeById(I id) throws RestException {
        if (GeneralUtils.isEmpty(id)) {
            return;
        }
        superMapper.removeById(id);
        this.refresh();
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void removeAll(Collection<I> idList) throws RestException {
        if (GeneralUtils.isEmpty(idList)) {
            return;
        }
        superMapper.removeAll(idList);
        this.refresh();
    }

    @Override
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void deleteById(I id) throws RestException {
        if (GeneralUtils.isEmpty(id)) {
            return;
        }
        superMapper.deleteById(id);
        this.refresh();
    }

    @Override
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void deleteAll(Collection<I> idList) throws RestException {
        if (GeneralUtils.isEmpty(idList)) {
            return;
        }
        superMapper.deleteAll(idList);
        this.refresh();
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public M queryById(I id, Boolean... isLoadArray) throws RestException {
        if (GeneralUtils.isEmpty(id)) {
            return null;
        }
        E entity;
        if (isLoadArray.length > 0 && LoadMapper.class.isAssignableFrom(superMapper.getClass())) {
            LoadMapper<E,I> loadMapper = (LoadMapper<E,I>) superMapper;
            Method findMethod = null;
            try {
                findMethod = loadMapper.getClass().getMethod("queryById", id.getClass(), Boolean[].class);
            } catch (NoSuchMethodException ignored) {
            }
            Method queryByIdMethod = findMethod;
            /* 当LoadMapper被复写的时候 优先调用LoadMapper的queryByIdMethod */
            if (queryByIdMethod != null && !queryByIdMethod.isDefault()) {
                entity = loadMapper.findById(id, isLoadArray);
            } else {
                entity = superMapper.findById(id);
            }
        } else {
            entity = superMapper.findById(id);
        }
        if (GeneralUtils.isEmpty(entity)) {
            return null;
        }
        return modelActuator(entity, isLoadArray);
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public List<M> queryAll(Collection<I> idList, Boolean... isLoadArray) throws RestException {
        if (GeneralUtils.isEmpty(idList)) {
            return Collections.emptyList();
        }
        List<E> entityList;
        if (isLoadArray.length > 0 && LoadMapper.class.isAssignableFrom(superMapper.getClass())) {
            LoadMapper<E,I> loadMapper = (LoadMapper<E,I>) superMapper;
            Method findMethod = null;
            try {
                findMethod = loadMapper.getClass().getMethod("queryAll", List.class, Boolean[].class);
            } catch (NoSuchMethodException ignored) {
            }
            Method queryByIdMethod = findMethod;
            /* 当LoadMapper被复写的时候 优先调用LoadMapper的queryByIdMethod */
            if (queryByIdMethod != null && !queryByIdMethod.isDefault()) {
                entityList = loadMapper.findAll(idList, isLoadArray);
            } else {
                entityList = superMapper.findAll(idList);
            }
        } else {
            entityList = superMapper.findAll(idList);
        }
        return modelActuator(entityList, isLoadArray);
    }

    @SuppressWarnings(value = "unchecked")
    public RestPage<M> queryAllWithFilter(F filter) throws RestException {
        optionalFilter(filter);
        filterCache.set(filter);
        String whereSql = queryWhereSql(filter);
        Boolean[] isLoadArray = loadArray(filter);
        Page<E> page = filter.toPage();
        List<E> entityList;
        if (isLoadArray.length > 0 && LoadMapper.class.isAssignableFrom(superMapper.getClass())) {
            LoadMapper<E,I> loadMapper = (LoadMapper<E,I>) superMapper;
            Method findMethod = null;
            try {
                findMethod = loadMapper.getClass().getMethod("findAllByWhere", List.class, Boolean[].class);
            } catch (NoSuchMethodException ignored) {
            }
            Method queryByIdMethod = findMethod;
            /* 当LoadMapper被复写的时候 优先调用LoadMapper的queryByIdMethod */
            if (queryByIdMethod != null && !queryByIdMethod.isDefault()) {
                entityList = loadMapper.findAllByWhere(whereSql,isLoadArray);
            } else {
                entityList = superMapper.findAllByWhere(whereSql);
            }
        } else {
            entityList = superMapper.findAllByWhere(whereSql);
        }
        List<M> modelList = modelActuator(entityList, isLoadArray);
        return RestPage.result(modelList, page);
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void deleteAllWithFilter(F filter) throws RestException {
        String whereSql = deleteWhereSql(filter);
        if (GeneralUtils.isNotEmpty(whereSql)) {
            superMapper.deleteAllByWhere(whereSql);
            this.refresh();
        }
    }

    private final ConsumerActuator<M> DEFAULT_CREATE_ACTUATOR = (@NonNull M model) -> {
        model.setId(ClazzHelper.generate(model));
        if (createActuator != null) {
            createActuator.actuate(model);
        }
        optional(model);
    };

    private final ConsumerActuator<M> DEFAULT_UPDATE_ACTUATOR = (@NonNull M model) -> {
        boolean exist = existById(model.getId());
        String message = "the data no found，id: " + model.getId();
        OptionalHelper.falseable(exist, message, "id", DataQueryException::new);
        if (updateActuator != null) {
            updateActuator.actuate(model);
        }
        optional(model);
    };

    private final ConsumerActuator<M> DEFAULT_INVADE_ACTUATOR = (@NonNull M model) -> {
        if (createActuator != null) {
            createActuator.actuate(model);
        }
        optional(model);
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
        return keyProperties.getInvadeEnabled();
    }

    protected Boolean isNameNonnull() {
        return nameProperties.getNonnullEnabled();
    }

    protected Boolean isNameUnique() {
        return nameProperties.getUniqueEnabled();
    }

    protected void optionalInit(@NonNull M model) throws RestException {

    }

    protected void optionalCreate(@NonNull M model) throws RestException {
        optionalInit(model);
        if (GeneralUtils.isEmpty(model.getId()) || !isIdInvade()) {
            createActuator().actuate(model);
        } else {
            invadeActuator().actuate(model);
        }
    }

    protected void optionalUpdate(@NonNull M model) throws RestException {
        OptionalHelper.idable(model.getId());
        updateActuator().actuate(model);
    }

    protected void optionalSave(@NonNull M model) throws RestException {
        if (GeneralUtils.isEmpty(model.getId())) {
            optionalInit(model);
            createActuator().actuate(model);
        } else {
            updateActuator().actuate(model);
        }
    }
}
