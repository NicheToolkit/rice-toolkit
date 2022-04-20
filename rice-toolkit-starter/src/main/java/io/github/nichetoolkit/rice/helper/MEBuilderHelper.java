package io.github.nichetoolkit.rice.helper;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.actuator.BiConsumerActuator;
import io.github.nichetoolkit.rest.actuator.ConsumerActuator;
import io.github.nichetoolkit.rest.actuator.FunctionActuator;
import io.github.nichetoolkit.rest.util.CollectUtils;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.IdEntity;
import io.github.nichetoolkit.rice.IdModel;
import io.github.nichetoolkit.rice.service.SupperService;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>MEBuilderHelper</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@SuppressWarnings("MixedMutabilityReturnType")
public class MEBuilderHelper {

    public static <M, E> List<E> entityList(Collection<M> modelList, ConsumerActuator<M> consumer, FunctionActuator<M, E> function) throws RestException {
        if (GeneralUtils.isEmpty(modelList)) {
            return Collections.emptyList();
        }
        List<M> collect = modelList.stream().filter(Objects::nonNull).collect(Collectors.toList());
        List<E> entityList = new ArrayList<>();
        for (M model : collect) {
            if (model != null) {
                consumer.actuate(model);
                entityList.add(function.actuate(model));
            }
        }
        return entityList;
    }

    public static <M, E> List<E> entityList(Collection<M> modelList, FunctionActuator<M, E> function) throws RestException {
        if (GeneralUtils.isEmpty(modelList)) {
            return Collections.emptyList();
        }
        List<M> collect = modelList.stream().filter(Objects::nonNull).collect(Collectors.toList());
        List<E> entityList = new ArrayList<>();
        for (M model : collect) {
            if (model != null) {
                entityList.add(function.actuate(model));
            }
        }
        return entityList;
    }

    public static <I, M, E> List<M> modelList(Collection<E> entityList, FunctionActuator<E, M> function) throws RestException {
        if (GeneralUtils.isEmpty(entityList)) {
            return Collections.emptyList();
        }
        List<M> modelList = new ArrayList<>();
        for (E entity : entityList) {
            if (entity != null) {
                modelList.add(function.actuate(entity));
            }
        }
        return modelList;
    }

    /**
     * 通过目标id查询设置单个目标对象
     * @param entity 源对象实体
     * @param model 源对象模型
     * @param targetQueryByTargetId 通过目标对象id查询单个目标对象
     * @param entityGetTargetId 源对象实体获取目标对象id
     * @param sourceSetTarget 源对象设置单个目标对象
     * @param index isLoad序列
     * @param isLoadArray LoadArray参数
     * @param <I> 对象Id类型声明
     * @param <M> 源对象模型类型声明
     * @param <T> 目标对象模型类型声明
     * @param <E> 源对象实体类型声明
     * @throws RestException RestException
     */
    public static <I, M, T, E> void buildSingleTargetId(
            E entity, M model, FunctionActuator<I,T> targetQueryByTargetId,
            FunctionActuator<E, I> entityGetTargetId,
            BiConsumerActuator<M, T> sourceSetTarget,
            Integer index, Boolean... isLoadArray
    ) throws RestException {
        I targetId = entityGetTargetId.actuate(entity);
        if (GeneralUtils.isNotEmpty(targetId) && isLoadArray.length > index && isLoadArray[index]) {
            T target = targetQueryByTargetId.actuate(targetId);
            if (GeneralUtils.isNotEmpty(target)) {
                sourceSetTarget.actuate(model,target);
            }
        }

    }

    /**
     * 通过目标id查询设置单个目标对象
     * @param entity 源对象实体
     * @param model 源对象模型
     * @param targetQueryByTargetId 通过目标对象id查询单个目标对象
     * @param entityGetTargetId 源对象实体获取目标对象id
     * @param sourceSetTarget 源对象设置单个目标对象
     * @param <I> 对象Id类型声明
     * @param <M> 源对象模型类型声明
     * @param <T> 目标对象模型类型声明
     * @param <E> 源对象实体类型声明
     * @throws RestException RestException
     */
    public static <I, M, T, E> void buildSingleTargetId(
            E entity, M model, FunctionActuator<I,T> targetQueryByTargetId,
            FunctionActuator<E, I> entityGetTargetId,
            BiConsumerActuator<M, T> sourceSetTarget
    ) throws RestException {
        I targetId = entityGetTargetId.actuate(entity);
        T target = targetQueryByTargetId.actuate(targetId);
        if (GeneralUtils.isNotEmpty(target)) {
            sourceSetTarget.actuate(model,target);
        }
    }

    /**
     * 通过源对象id查询设置单个目标对象
     * @param entity 源对象实体
     * @param model 源对象模型
     * @param targetQueryBySourceId 通过源对象id查询单个目标对象
     * @param sourceSetTarget 源对象设置单个目标对象
     * @param index isLoad序列
     * @param isLoadArray LoadArray参数
     * @param <I> 对象Id类型声明
     * @param <M> 源对象模型类型声明
     * @param <T> 目标对象模型类型声明
     * @param <E> 源对象实体类型声明
     * @throws RestException RestException
     */
    public static <I, M, T, E extends IdEntity<I>> void buildSingleSourceId(
            E entity, M model, FunctionActuator<I,T> targetQueryBySourceId,
            BiConsumerActuator<M, T> sourceSetTarget,
            Integer index, Boolean... isLoadArray
    ) throws RestException {
        I sourceId = entity.getId();
        if (GeneralUtils.isNotEmpty(sourceId) && isLoadArray.length > index && isLoadArray[index]) {
            T target = targetQueryBySourceId.actuate(sourceId);
            if (GeneralUtils.isNotEmpty(target)) {
                sourceSetTarget.actuate(model,target);
            }
        }

    }

    /**
     * 通过源对象id查询设置单个目标对象
     * @param entity 源对象实体
     * @param model 源对象模型
     * @param targetQueryBySourceId 通过源对象id查询单个目标对象
     * @param sourceSetTarget 源对象设置单个目标对象
     * @param <I> 对象Id类型声明
     * @param <M> 源对象模型类型声明
     * @param <T> 目标对象模型类型声明
     * @param <E> 源对象实体类型声明
     * @throws RestException RestException
     */
    public static <I, M, T, E  extends IdEntity<I>> void buildSingleSourceId(
            E entity, M model, FunctionActuator<I,T> targetQueryBySourceId,
            BiConsumerActuator<M, T> sourceSetTarget
    ) throws RestException {
        I sourceId = entity.getId();
        T target = targetQueryBySourceId.actuate(sourceId);
        if (GeneralUtils.isNotEmpty(target)) {
            sourceSetTarget.actuate(model,target);
        }
    }

    /**
     * 通过源对象id查询设置单个目标对象
     * @param entity 源对象实体
     * @param model 源对象模型
     * @param targetQueryBySourceId 通过源对象id查询单个目标对象
     * @param entityGetSourceId 源对象实体获取源对象id
     * @param sourceSetTarget 源对象设置单个目标对象
     * @param index isLoad序列
     * @param isLoadArray LoadArray参数
     * @param <I> 对象Id类型声明
     * @param <M> 源对象模型类型声明
     * @param <T> 目标对象模型类型声明
     * @param <E> 源对象实体类型声明
     * @throws RestException RestException
     */
    public static <I, M, T, E> void buildSingleSourceId(
            E entity, M model, FunctionActuator<I,T> targetQueryBySourceId,
            FunctionActuator<E, I> entityGetSourceId,
            BiConsumerActuator<M, T> sourceSetTarget,
            Integer index, Boolean... isLoadArray
    ) throws RestException {
        I sourceId = entityGetSourceId.actuate(entity);
        if (GeneralUtils.isNotEmpty(sourceId) && isLoadArray.length > index && isLoadArray[index]) {
            T target = targetQueryBySourceId.actuate(sourceId);
            if (GeneralUtils.isNotEmpty(target)) {
                sourceSetTarget.actuate(model,target);
            }
        }
    }

    /**
     * 通过源对象id查询设置单个目标对象
     * @param entity 源对象实体
     * @param model 源对象模型
     * @param targetQueryBySourceId 通过源对象id查询单个目标对象
     * @param entityGetSourceId 源对象实体获取源对象id
     * @param sourceSetTarget 源对象设置单个目标对象
     * @param <I> 对象Id类型声明
     * @param <M> 源对象模型类型声明
     * @param <T> 目标对象模型类型声明
     * @param <E> 源对象实体类型声明
     * @throws RestException RestException
     */
    public static <I, M, T, E> void buildSingleSourceId(
            E entity, M model, FunctionActuator<I,T> targetQueryBySourceId,
            FunctionActuator<E, I> entityGetSourceId,
            BiConsumerActuator<M, T> sourceSetTarget
    ) throws RestException {
        I sourceId = entityGetSourceId.actuate(entity);
        T target = targetQueryBySourceId.actuate(sourceId);
        if (GeneralUtils.isNotEmpty(target)) {
            sourceSetTarget.actuate(model,target);
        }
    }

    /**
     * 通过目标对象id列表查询设置多个目标对象
     * @param model 源对象模型
     * @param targetListQueryByTargetIdList 通过目标对象id列表查询目标对象列表
     * @param sourceGetTargetIdList 源对象获取源对象id
     * @param sourceSetTargetList 源对象设置目标对象列表
     * @param index isLoad序列
     * @param isLoadArray LoadArray参数
     * @param <I> 对象Id类型声明
     * @param <M> 源对象模型类型声明
     * @param <T> 目标对象模型类型声明
     * @throws RestException RestException
     */
    public static <I, M, T> void buildMultiTargetId(
            M model, FunctionActuator<Collection<I>, List<T>> targetListQueryByTargetIdList,
            FunctionActuator<M, List<I>> sourceGetTargetIdList,
            BiConsumerActuator<M, Collection<T>> sourceSetTargetList,
            Integer index, Boolean... isLoadArray
    ) throws RestException {
        List<I> targetIdList = sourceGetTargetIdList.actuate(model);
        if (GeneralUtils.isNotEmpty(targetIdList) && isLoadArray.length > index && isLoadArray[index]) {
            Set<I> targetIdSet = new HashSet<>(targetIdList);
            List<T> targetList = targetListQueryByTargetIdList.actuate(targetIdSet);
            if (GeneralUtils.isNotEmpty(targetList)) {
                sourceSetTargetList.actuate(model,targetList);
            }
        }
    }

    /**
     * 通过目标对象id列表查询设置多个目标对象
     * @param model 源对象模型
     * @param targetListQueryByTargetIdList 通过目标对象id列表查询目标对象列表
     * @param sourceGetTargetIdList 源对象获取源对象id
     * @param sourceSetTargetList 源对象设置目标对象列表
     * @param <I> 对象Id类型声明
     * @param <M> 源对象模型类型声明
     * @param <T> 目标对象模型类型声明
     * @throws RestException RestException
     */
    public static <I, M, T> void buildMultiTargetId(
            M model, FunctionActuator<Collection<I>, List<T>> targetListQueryByTargetIdList,
            FunctionActuator<M, List<I>> sourceGetTargetIdList,
            BiConsumerActuator<M, Collection<T>> sourceSetTargetList
    ) throws RestException {
        List<I> targetIdList = sourceGetTargetIdList.actuate(model);
        Set<I> targetIdSet = new HashSet<>(targetIdList);
        List<T> targetList = targetListQueryByTargetIdList.actuate(targetIdSet);
        if (GeneralUtils.isNotEmpty(targetList)) {
            sourceSetTargetList.actuate(model,targetList);
        }
    }

    /**
     * 通过源id查询设置多个目标对象
     * @param entity 源对象实体
     * @param model 源对象模型
     * @param targetListQueryBySourceId 通过源对象id查询目标对象列表
     * @param sourceSetTargetList 源对象设置目标对象列表
     * @param index isLoad序列
     * @param isLoadArray LoadArray参数
     * @param <I> 对象Id类型声明
     * @param <M> 源对象模型类型声明
     * @param <T> 目标对象模型类型声明
     * @param <E> 源对象实体类型声明
     * @throws RestException RestException
     */
    public static <I, M, T, E  extends IdEntity<I>> void buildMultiSourceId(
            E entity, M model, FunctionActuator<I, List<T>> targetListQueryBySourceId,
            BiConsumerActuator<M, Collection<T>> sourceSetTargetList,
            Integer index, Boolean... isLoadArray
    ) throws RestException {
        I sourceId = entity.getId();
        if (GeneralUtils.isNotEmpty(sourceId) && isLoadArray.length > index && isLoadArray[index]) {
            List<T> targetList = targetListQueryBySourceId.actuate(sourceId);
            if (GeneralUtils.isNotEmpty(targetList)) {
                sourceSetTargetList.actuate(model,targetList);
            }
        }

    }

    /**
     * 通过源id查询设置多个目标对象
     * @param entity 源对象实体
     * @param model 源对象模型
     * @param targetListQueryBySourceId 通过源对象id查询目标对象列表
     * @param sourceSetTargetList 源对象设置目标对象列表
     * @param <I> 对象Id类型声明
     * @param <M> 源对象模型类型声明
     * @param <T> 目标对象模型类型声明
     * @param <E> 源对象实体类型声明
     * @throws RestException RestException
     */
    public static <I, M, T, E  extends IdEntity<I>> void buildMultiSourceId(
            E entity, M model, FunctionActuator<I, List<T>> targetListQueryBySourceId,
            BiConsumerActuator<M, Collection<T>> sourceSetTargetList
    ) throws RestException {
        I sourceId = entity.getId();
        List<T> targetList = targetListQueryBySourceId.actuate(sourceId);
        if (GeneralUtils.isNotEmpty(targetList)) {
            sourceSetTargetList.actuate(model,targetList);
        }
    }

    /**
     * 通过源id查询设置多个目标对象
     * @param entity 源对象实体
     * @param model 源对象模型
     * @param targetListQueryBySourceId 通过源对象id查询目标对象列表
     * @param entityGetSourceId 源对象实体获取源对象id
     * @param sourceSetTargetList 源对象设置目标对象列表
     * @param index isLoad序列
     * @param isLoadArray LoadArray参数
     * @param <I> 对象Id类型声明
     * @param <M> 源对象模型类型声明
     * @param <T> 目标对象模型类型声明
     * @param <E> 源对象实体类型声明
     * @throws RestException RestException
     */
    public static <I, M, T, E> void buildMultiSourceId(
            E entity, M model, FunctionActuator<I, List<T>> targetListQueryBySourceId,
            FunctionActuator<E, I> entityGetSourceId,
            BiConsumerActuator<M, Collection<T>> sourceSetTargetList,
            Integer index, Boolean... isLoadArray
    ) throws RestException {
        I sourceId = entityGetSourceId.actuate(entity);
        if (GeneralUtils.isNotEmpty(sourceId) && isLoadArray.length > index && isLoadArray[index]) {
            List<T> targetList = targetListQueryBySourceId.actuate(sourceId);
            if (GeneralUtils.isNotEmpty(targetList)) {
                sourceSetTargetList.actuate(model,targetList);
            }
        }
    }

    /**
     * 通过源id查询设置多个目标对象
     * @param entity 源对象实体
     * @param model 源对象模型
     * @param targetListQueryBySourceId 通过源对象id查询目标对象列表
     * @param entityGetSourceId 源对象实体获取源对象id
     * @param sourceSetTargetList 源对象设置目标对象列表
     * @param <I> 对象Id类型声明
     * @param <M> 源对象模型类型声明
     * @param <T> 目标对象模型类型声明
     * @param <E> 源对象实体类型声明
     * @throws RestException RestException
     */
    public static <I, M, T, E> void buildMultiSourceId(
            E entity, M model, FunctionActuator<I, List<T>> targetListQueryBySourceId,
            FunctionActuator<E, I> entityGetSourceId,
            BiConsumerActuator<M, Collection<T>> sourceSetTargetList
    ) throws RestException {
        I sourceId = entityGetSourceId.actuate(entity);
        List<T> targetList = targetListQueryBySourceId.actuate(sourceId);
        if (GeneralUtils.isNotEmpty(targetList)) {
            sourceSetTargetList.actuate(model,targetList);
        }
    }


    /**
     * 通过目标对象id列表查询设置单个目标对象
     * @param entityList 源对象实体集合
     * @param modelList 源对象模型集合
     * @param targetListQueryByTargetIdList 通过目标对象id列表查询目标对象表
     * @param entityGetTargetId 源对象实体获取目标对象id
     * @param sourceGetTargetId 源对象模型获取目标对象id
     * @param sourceSetTarget 源对象设置单个目标对象
     * @param index isLoad序列
     * @param isLoadArray LoadArray参数
     * @param <I> 对象Id类型声明
     * @param <M> 源对象模型类型声明
     * @param <T> 目标对象模型类型声明
     * @param <E> 源对象实体类型声明
     * @throws RestException RestException
     */
    public static <I, M, T extends IdModel<I>, E> void buildSingleTargetId(
            Collection<E> entityList, Collection<M> modelList, FunctionActuator<Collection<I>, List<T>> targetListQueryByTargetIdList,
            Function<E,I> entityGetTargetId, FunctionActuator<M,I> sourceGetTargetId, BiConsumerActuator<M, T> sourceSetTarget,
            Integer index, Boolean... isLoadArray
    ) throws RestException {
        List<I> targetIdList = entityList.stream().filter(GeneralUtils::isNotEmpty).map(entityGetTargetId).distinct().collect(Collectors.toList());
        if (GeneralUtils.isNotEmpty(targetIdList) && isLoadArray.length > index && isLoadArray[index]) {
            List<T> targetList = targetListQueryByTargetIdList.actuate(targetIdList);
            buildSingleTargetTargetId(modelList,targetList,sourceGetTargetId,sourceSetTarget);
        }
    }

    /**
     * 通过目标对象id列表查询设置单个目标对象
     * @param entityList 源对象实体集合
     * @param modelList 源对象模型集合
     * @param targetListQueryByTargetIdList 通过目标对象id列表查询目标对象表
     * @param entityGetTargetId 源对象实体获取目标对象id
     * @param sourceGetTargetId 源对象模型获取目标对象id
     * @param sourceSetTarget 源对象设置单个目标对象
     * @param <I> 对象Id类型声明
     * @param <M> 源对象模型类型声明
     * @param <T> 目标对象模型类型声明
     * @param <E> 源对象实体类型声明
     * @throws RestException RestException
     */
    public static <I, M, T extends IdModel<I>, E> void buildSingleTargetId(
            Collection<E> entityList, Collection<M> modelList, FunctionActuator<Collection<I>, List<T>> targetListQueryByTargetIdList,
            Function<E,I> entityGetTargetId, FunctionActuator<M,I> sourceGetTargetId, BiConsumerActuator<M, T> sourceSetTarget
    ) throws RestException {
        List<I> targetIdList = entityList.stream().filter(GeneralUtils::isNotEmpty).map(entityGetTargetId).distinct().collect(Collectors.toList());
        if (GeneralUtils.isNotEmpty(targetIdList)) {
            List<T> targetList = targetListQueryByTargetIdList.actuate(targetIdList);
            buildSingleTargetTargetId(modelList,targetList,sourceGetTargetId,sourceSetTarget);
        }
    }

    /**
     * 通过目标对象id列表查询设置单个目标对象
     * @param entityList 源对象实体集合
     * @param modelList 源对象模型集合
     * @param targetListQueryByTargetIdList 通过目标对象id列表查询目标对象表
     * @param entityGetTargetId 源对象实体获取目标对象id
     * @param targetGetTargetId 目标对象模型获取目标对象id
     * @param sourceGetTargetId 源对象模型获取目标对象id
     * @param sourceSetTarget 源对象设置单个目标对象
     * @param index isLoad序列
     * @param isLoadArray LoadArray参数
     * @param <I> 对象Id类型声明
     * @param <M> 源对象模型类型声明
     * @param <T> 目标对象模型类型声明
     * @param <E> 源对象实体类型声明
     * @throws RestException RestException
     */
    public static <I, M, T, E> void buildSingleTargetId(
            Collection<E> entityList, Collection<M> modelList, FunctionActuator<Collection<I>, List<T>> targetListQueryByTargetIdList,
            Function<E,I> entityGetTargetId, FunctionActuator<T,I> targetGetTargetId,
            FunctionActuator<M,I> sourceGetTargetId, BiConsumerActuator<M, T> sourceSetTarget,
            Integer index, Boolean... isLoadArray
    ) throws RestException {
        List<I> targetIdList = entityList.stream().filter(GeneralUtils::isNotEmpty).map(entityGetTargetId).distinct().collect(Collectors.toList());
        if (GeneralUtils.isNotEmpty(targetIdList) && isLoadArray.length > index && isLoadArray[index]) {
            List<T> targetList = targetListQueryByTargetIdList.actuate(targetIdList);
            buildSingleTargetTargetId(modelList,targetList,targetGetTargetId,sourceGetTargetId,sourceSetTarget);
        }
    }

    /**
     * 通过目标对象id列表查询设置单个目标对象
     * @param entityList 源对象实体集合
     * @param modelList 源对象模型集合
     * @param targetListQueryByTargetIdList 通过目标对象id列表查询目标对象表
     * @param entityGetTargetId 源对象实体获取目标对象id
     * @param targetGetTargetId 目标对象模型获取目标对象id
     * @param sourceGetTargetId 源对象模型获取目标对象id
     * @param sourceSetTarget 源对象设置单个目标对象
     * @param <I> 对象Id类型声明
     * @param <M> 源对象模型类型声明
     * @param <T> 目标对象模型类型声明
     * @param <E> 源对象实体类型声明
     * @throws RestException RestException
     */
    public static <I, M, T, E> void buildSingleTargetId(
            Collection<E> entityList, Collection<M> modelList, FunctionActuator<Collection<I>, List<T>> targetListQueryByTargetIdList,
            Function<E,I> entityGetTargetId, FunctionActuator<T,I> targetGetTargetId,
            FunctionActuator<M,I> sourceGetTargetId, BiConsumerActuator<M, T> sourceSetTarget
    ) throws RestException {
        List<I> targetIdList = entityList.stream().filter(GeneralUtils::isNotEmpty).map(entityGetTargetId).distinct().collect(Collectors.toList());
        if (GeneralUtils.isNotEmpty(targetIdList)) {
            List<T> targetList = targetListQueryByTargetIdList.actuate(targetIdList);
            buildSingleTargetTargetId(modelList,targetList,targetGetTargetId,sourceGetTargetId,sourceSetTarget);
        }
    }

    /**
     * 通过源对象id列表查询设置单个目标对象
     * @param entityList 源对象实体集合
     * @param modelList 源对象模型集合
     * @param targetListQueryBySourceIdList 通过源对象id列表查询目标对象表
     * @param targetGetSourceId 目标对象模型获取源对象id
     * @param sourceSetTarget 源对象设置单个目标对象
     * @param index isLoad序列
     * @param isLoadArray LoadArray参数
     * @param <I> 对象Id类型声明
     * @param <M> 源对象模型类型声明
     * @param <T> 目标对象模型类型声明
     * @param <E> 源对象实体类型声明
     * @throws RestException RestException
     */
    public static <I, M extends IdModel<I>, T, E extends IdEntity<I>> void buildSingleSourceId(
            Collection<E> entityList, Collection<M> modelList, FunctionActuator<Collection<I>, List<T>> targetListQueryBySourceIdList,
            FunctionActuator<T,I> targetGetSourceId, BiConsumerActuator<M, T> sourceSetTarget,
            Integer index, Boolean... isLoadArray
    ) throws RestException {
        List<I> sourceIdList = entityList.stream().filter(GeneralUtils::isNotEmpty).map(IdEntity::getId).distinct().collect(Collectors.toList());
        if (GeneralUtils.isNotEmpty(sourceIdList) && isLoadArray.length > index && isLoadArray[index]) {
            List<T> targetList = targetListQueryBySourceIdList.actuate(sourceIdList);
            buildSingleTargetSourceId(modelList,targetList,targetGetSourceId,sourceSetTarget);
        }
    }

    /**
     * 通过源对象id列表查询设置单个目标对象
     * @param entityList 源对象实体集合
     * @param modelList 源对象模型集合
     * @param targetListQueryBySourceIdList 通过源对象id列表查询目标对象表
     * @param targetGetSourceId 目标对象模型获取源对象id
     * @param sourceSetTarget 源对象设置单个目标对象
     * @param <I> 对象Id类型声明
     * @param <M> 源对象模型类型声明
     * @param <T> 目标对象模型类型声明
     * @param <E> 源对象实体类型声明
     * @throws RestException RestException
     */
    public static <I, M extends IdModel<I>, T, E extends IdEntity<I>> void buildSingleSourceId(
            Collection<E> entityList, Collection<M> modelList, FunctionActuator<Collection<I>, List<T>> targetListQueryBySourceIdList,
            FunctionActuator<T,I> targetGetSourceId, BiConsumerActuator<M, T> sourceSetTarget
    ) throws RestException {
        List<I> sourceIdList = entityList.stream().filter(GeneralUtils::isNotEmpty).map(IdEntity::getId).distinct().collect(Collectors.toList());
        if (GeneralUtils.isNotEmpty(sourceIdList)) {
            List<T> targetList = targetListQueryBySourceIdList.actuate(sourceIdList);
            buildSingleTargetSourceId(modelList,targetList,targetGetSourceId,sourceSetTarget);
        }
    }

    /**
     * 通过源对象id列表查询设置单个目标对象
     * @param entityList 源对象实体集合
     * @param modelList 源对象模型集合
     * @param targetListQueryBySourceIdList 通过源对象id列表查询目标对象表
     * @param entityGetSourceId 源对象实体获取源对象id
     * @param targetGetSourceId 目标对象模型获取源对象id
     * @param sourceGetSourceId 源对象模型获取源对象id
     * @param sourceSetTarget 源对象设置单个目标对象
     * @param index isLoad序列
     * @param isLoadArray LoadArray参数
     * @param <I> 对象Id类型声明
     * @param <M> 源对象模型类型声明
     * @param <T> 目标对象模型类型声明
     * @param <E> 源对象实体类型声明
     * @throws RestException RestException
     */
    public static <I, M, T, E> void buildSingleSourceId(
            Collection<E> entityList, Collection<M> modelList, FunctionActuator<Collection<I>, List<T>> targetListQueryBySourceIdList,
            Function<E,I> entityGetSourceId, FunctionActuator<T,I> targetGetSourceId,
            FunctionActuator<M,I> sourceGetSourceId, BiConsumerActuator<M, T> sourceSetTarget,
            Integer index, Boolean... isLoadArray
    ) throws RestException {
        List<I> sourceIdList = entityList.stream().filter(GeneralUtils::isNotEmpty).map(entityGetSourceId).distinct().collect(Collectors.toList());
        if (GeneralUtils.isNotEmpty(sourceIdList) && isLoadArray.length > index && isLoadArray[index]) {
            List<T> targetList = targetListQueryBySourceIdList.actuate(sourceIdList);
            buildSingleTargetSourceId(modelList,targetList,targetGetSourceId,sourceGetSourceId,sourceSetTarget);
        }
    }

    /**
     * 通过源对象id列表查询设置单个目标对象
     * @param entityList 源对象实体集合
     * @param modelList 源对象模型集合
     * @param targetListQueryBySourceIdList 通过源对象id列表查询目标对象表
     * @param entityGetSourceId 源对象实体获取源对象id
     * @param targetGetSourceId 目标对象模型获取源对象id
     * @param sourceGetSourceId 源对象模型获取源对象id
     * @param sourceSetTarget 源对象设置单个目标对象
     * @param <I> 对象Id类型声明
     * @param <M> 源对象模型类型声明
     * @param <T> 目标对象模型类型声明
     * @param <E> 源对象实体类型声明
     * @throws RestException RestException
     */
    public static <I, M, T, E> void buildSingleSourceId(
            Collection<E> entityList, Collection<M> modelList, FunctionActuator<Collection<I>, List<T>> targetListQueryBySourceIdList,
            Function<E,I> entityGetSourceId, FunctionActuator<T,I> targetGetSourceId,
            FunctionActuator<M,I> sourceGetSourceId, BiConsumerActuator<M, T> sourceSetTarget
    ) throws RestException {
        List<I> sourceIdList = entityList.stream().filter(GeneralUtils::isNotEmpty).map(entityGetSourceId).distinct().collect(Collectors.toList());
        if (GeneralUtils.isNotEmpty(sourceIdList)) {
            List<T> targetList = targetListQueryBySourceIdList.actuate(sourceIdList);
            buildSingleTargetSourceId(modelList,targetList,targetGetSourceId,sourceGetSourceId,sourceSetTarget);
        }
    }

    /**
     * 通过目标对象id列表查询设置多个目标对象
     * @param modelList 源对象模型集合
     * @param targetListQueryByTargetIdList 通过目标对象id列表查询目标对象表
     * @param sourceGetTargetIdList 源对象模型获取目标对象id列表
     * @param sourceSetTargetList 源对象设置多个目标对象
     * @param index isLoad序列
     * @param isLoadArray LoadArray参数
     * @param <I> 对象Id类型声明
     * @param <M> 源对象模型类型声明
     * @param <T> 目标对象模型类型声明
     * @throws RestException RestException
     */
    public static <I, M extends IdModel<I>, T extends IdModel<I>> void buildMultiTargetId(
            Collection<M> modelList, FunctionActuator<Collection<I>, List<T>> targetListQueryByTargetIdList,
            FunctionActuator<M,List<I>> sourceGetTargetIdList, BiConsumerActuator<M, Collection<T>> sourceSetTargetList,
            Integer index, Boolean... isLoadArray
    ) throws RestException {
        Map<I, List<I>> sourceIdTargetIdListMap = new HashMap<>();
        List<I> targetIdList = new ArrayList<>();
        for (M model : modelList) {
            if (GeneralUtils.isNotEmpty(model)) {
                I sourceId = model.getId();
                List<I> idList = sourceGetTargetIdList.actuate(model);
                if (GeneralUtils.isNotEmpty(idList)) {
                    targetIdList.addAll(idList);
                    sourceIdTargetIdListMap.putIfAbsent(sourceId,idList);
                }
            }
        }
        Set<I> targetIdSet = new HashSet<>(targetIdList);
        if (GeneralUtils.isNotEmpty(targetIdSet) && isLoadArray.length > index && isLoadArray[index]) {
            List<T> targetList = targetListQueryByTargetIdList.actuate(targetIdSet);
            buildMultiTargetTargetId(modelList,targetList,sourceIdTargetIdListMap,sourceSetTargetList);
        }
    }

    /**
     * 通过目标对象id列表查询设置多个目标对象
     * @param modelList 源对象模型集合
     * @param targetListQueryByTargetIdList 通过目标对象id列表查询目标对象表
     * @param sourceGetTargetIdList 源对象模型获取目标对象id列表
     * @param sourceSetTargetList 源对象设置多个目标对象
     * @param <I> 对象Id类型声明
     * @param <M> 源对象模型类型声明
     * @param <T> 目标对象模型类型声明
     * @throws RestException RestException
     */
    public static <I, M extends IdModel<I>, T extends IdModel<I>> void buildMultiTargetId(
            Collection<M> modelList, FunctionActuator<Collection<I>, List<T>> targetListQueryByTargetIdList,
            FunctionActuator<M,List<I>> sourceGetTargetIdList, BiConsumerActuator<M, Collection<T>> sourceSetTargetList
    ) throws RestException {
        Map<I, List<I>> sourceIdTargetIdListMap = new HashMap<>();
        List<I> targetIdList = new ArrayList<>();
        for (M model : modelList) {
            if (GeneralUtils.isNotEmpty(model)) {
                I sourceId = model.getId();
                List<I> idList = sourceGetTargetIdList.actuate(model);
                if (GeneralUtils.isNotEmpty(idList)) {
                    targetIdList.addAll(idList);
                    sourceIdTargetIdListMap.putIfAbsent(sourceId,idList);
                }
            }
        }
        Set<I> targetIdSet = new HashSet<>(targetIdList);
        if (GeneralUtils.isNotEmpty(targetIdSet)) {
            List<T> targetList = targetListQueryByTargetIdList.actuate(targetIdSet);
            buildMultiTargetTargetId(modelList,targetList,sourceIdTargetIdListMap,sourceSetTargetList);
        }
    }

    /**
     * 通过目标对象id列表查询设置多个目标对象
     * @param modelList 源对象模型集合
     * @param targetListQueryByTargetIdList 通过目标对象id列表查询目标对象表
     * @param sourceGetSourceId 源对象模型获取源对象id
     * @param sourceGetTargetIdList 源对象模型获取目标对象id列表
     * @param targetGetTargetId 目标对象模型获取目标对象id
     * @param sourceSetTargetList 源对象设置多个目标对象
     * @param index isLoad序列
     * @param isLoadArray LoadArray参数
     * @param <I> 对象Id类型声明
     * @param <M> 源对象模型类型声明
     * @param <T> 目标对象模型类型声明
     * @throws RestException RestException
     */
    public static <I, M, T> void buildMultiTargetId(
            Collection<M> modelList, FunctionActuator<Collection<I>, List<T>> targetListQueryByTargetIdList,
            FunctionActuator<M,I> sourceGetSourceId, FunctionActuator<M,List<I>> sourceGetTargetIdList, FunctionActuator<T,I> targetGetTargetId,
            BiConsumerActuator<M, Collection<T>> sourceSetTargetList,
            Integer index, Boolean... isLoadArray
    ) throws RestException {
        Map<I, List<I>> sourceIdTargetIdListMap = new HashMap<>();
        List<I> targetIdList = new ArrayList<>();
        for (M model : modelList) {
            if (GeneralUtils.isNotEmpty(model)) {
                I sourceId = sourceGetSourceId.actuate(model);
                List<I> idList = sourceGetTargetIdList.actuate(model);
                if (GeneralUtils.isNotEmpty(idList)) {
                    targetIdList.addAll(idList);
                    sourceIdTargetIdListMap.putIfAbsent(sourceId,idList);
                }
            }
        }
        Set<I> targetIdSet = new HashSet<>(targetIdList);
        if (GeneralUtils.isNotEmpty(targetIdSet) && isLoadArray.length > index && isLoadArray[index]) {
            List<T> targetList = targetListQueryByTargetIdList.actuate(targetIdSet);
            buildMultiTargetTargetId(modelList,targetList,sourceIdTargetIdListMap,targetGetTargetId,sourceGetSourceId,sourceSetTargetList);
        }
    }

    /**
     * 通过目标对象id列表查询设置多个目标对象
     * @param modelList 源对象模型集合
     * @param targetListQueryByTargetIdList 通过目标对象id列表查询目标对象表
     * @param sourceGetSourceId 源对象模型获取源对象id
     * @param sourceGetTargetIdList 源对象模型获取目标对象id列表
     * @param targetGetTargetId 目标对象模型获取目标对象id
     * @param sourceSetTargetList 源对象设置多个目标对象
     * @param <I> 对象Id类型声明
     * @param <M> 源对象模型类型声明
     * @param <T> 目标对象模型类型声明
     * @throws RestException RestException
     */
    public static <I, M, T> void buildMultiTargetId(
            Collection<M> modelList, FunctionActuator<Collection<I>, List<T>> targetListQueryByTargetIdList,
            FunctionActuator<M,I> sourceGetSourceId, FunctionActuator<M,List<I>> sourceGetTargetIdList, FunctionActuator<T,I> targetGetTargetId,
            BiConsumerActuator<M, Collection<T>> sourceSetTargetList
    ) throws RestException {
        Map<I, List<I>> sourceIdTargetIdListMap = new HashMap<>();
        List<I> targetIdList = new ArrayList<>();
        for (M model : modelList) {
            if (GeneralUtils.isNotEmpty(model)) {
                I sourceId = sourceGetSourceId.actuate(model);
                List<I> idList = sourceGetTargetIdList.actuate(model);
                if (GeneralUtils.isNotEmpty(idList)) {
                    targetIdList.addAll(idList);
                    sourceIdTargetIdListMap.putIfAbsent(sourceId,idList);
                }
            }
        }
        Set<I> targetIdSet = new HashSet<>(targetIdList);
        if (GeneralUtils.isNotEmpty(targetIdSet)) {
            List<T> targetList = targetListQueryByTargetIdList.actuate(targetIdSet);
            buildMultiTargetTargetId(modelList,targetList,sourceIdTargetIdListMap,targetGetTargetId,sourceGetSourceId,sourceSetTargetList);
        }
    }

    /**
     * 通过源对象id列表查询设置多个目标对象
     * @param entityList 源对象实体集合
     * @param modelList 源对象模型集合
     * @param targetListQueryBySourceIdList 通过源对象id列表查询目标对象表
     * @param targetGetSourceId 目标对象模型获取源对象id
     * @param sourceSetTargetList 源对象设置多个目标对象
     * @param index isLoad序列
     * @param isLoadArray LoadArray参数
     * @param <I> 对象Id类型声明
     * @param <M> 源对象模型类型声明
     * @param <T> 目标对象模型类型声明
     * @param <E> 源对象实体类型声明
     * @throws RestException RestException
     */
    public static <I, M extends IdModel<I>, T,E extends IdEntity<I>> void buildMultiSourceId(
            Collection<E> entityList,Collection<M> modelList, FunctionActuator<Collection<I>, List<T>> targetListQueryBySourceIdList,
            FunctionActuator<T,I> targetGetSourceId, BiConsumerActuator<M, Collection<T>> sourceSetTargetList,
            Integer index, Boolean... isLoadArray
    ) throws RestException {
        List<I> sourceIdList = entityList.stream().filter(GeneralUtils::isNotEmpty).map(IdEntity::getId).distinct().collect(Collectors.toList());
        if (GeneralUtils.isNotEmpty(sourceIdList) && isLoadArray.length > index && isLoadArray[index]) {
            List<T> targetList = targetListQueryBySourceIdList.actuate(sourceIdList);
            buildMultiTargetSourceId(modelList,targetList,targetGetSourceId,sourceSetTargetList);
        }
    }

    /**
     * 通过源对象id列表查询设置多个目标对象
     * @param entityList 源对象实体集合
     * @param modelList 源对象模型集合
     * @param targetListQueryBySourceIdList 通过源对象id列表查询目标对象表
     * @param targetGetSourceId 目标对象模型获取源对象id
     * @param sourceGetSourceId 源对象模型获取目标对象id
     * @param sourceSetTargetList 源对象设置多个目标对象
     * @param index isLoad序列
     * @param isLoadArray LoadArray参数
     * @param <I> 对象Id类型声明
     * @param <M> 源对象模型类型声明
     * @param <T> 目标对象模型类型声明
     * @param <E> 源对象实体类型声明
     * @throws RestException RestException
     */
    public static <I, M, T,E extends IdEntity<I>> void buildMultiSourceId(
            Collection<E> entityList,Collection<M> modelList, FunctionActuator<Collection<I>, List<T>> targetListQueryBySourceIdList,
            FunctionActuator<T,I> targetGetSourceId, FunctionActuator<M,I> sourceGetSourceId,
            BiConsumerActuator<M, Collection<T>> sourceSetTargetList,
            Integer index, Boolean... isLoadArray
    ) throws RestException {
        List<I> sourceIdList = entityList.stream().filter(GeneralUtils::isNotEmpty).map(IdEntity::getId).distinct().collect(Collectors.toList());
        if (GeneralUtils.isNotEmpty(sourceIdList) && isLoadArray.length > index && isLoadArray[index]) {
            List<T> targetList = targetListQueryBySourceIdList.actuate(sourceIdList);
            buildMultiTargetSourceId(modelList,targetList,targetGetSourceId,sourceGetSourceId,sourceSetTargetList);
        }
    }

    /**
     * 通过源对象id列表查询设置多个目标对象
     * @param entityList 源对象实体集合
     * @param modelList 源对象模型集合
     * @param targetListQueryBySourceIdList 通过源对象id列表查询目标对象表
     * @param targetGetSourceId 目标对象模型获取源对象id
     * @param sourceGetTargetId 源对象模型获取目标对象id
     * @param sourceSetTargetList 源对象设置多个目标对象
     * @param <I> 对象Id类型声明
     * @param <M> 源对象模型类型声明
     * @param <T> 目标对象模型类型声明
     * @param <E> 源对象实体类型声明
     * @throws RestException RestException
     */
    public static <I, M, T,E extends IdEntity<I>> void buildMultiSourceId(
            Collection<E> entityList,Collection<M> modelList, FunctionActuator<Collection<I>, List<T>> targetListQueryBySourceIdList,
            FunctionActuator<T,I> targetGetSourceId, FunctionActuator<M,I> sourceGetTargetId, BiConsumerActuator<M, Collection<T>> sourceSetTargetList
    ) throws RestException {
        List<I> sourceIdList = entityList.stream().filter(GeneralUtils::isNotEmpty).map(IdEntity::getId).distinct().collect(Collectors.toList());
        if (GeneralUtils.isNotEmpty(sourceIdList)) {
            List<T> targetList = targetListQueryBySourceIdList.actuate(sourceIdList);
            buildMultiTargetSourceId(modelList,targetList,targetGetSourceId,sourceGetTargetId,sourceSetTargetList);
        }
    }

    /**
     * 通过源对象id列表查询设置多个目标对象
     * @param entityList 源对象实体集合
     * @param modelList 源对象模型集合
     * @param targetListQueryBySourceIdList 通过源对象id列表查询目标对象表
     * @param entityGetSourceId 源对象实体获取源对象id
     * @param targetGetSourceId 目标对象模型获取源对象id
     * @param sourceGetTargetId 源对象模型获取目标对象id
     * @param sourceSetTargetList 源对象设置多个目标对象
     * @param <I> 对象Id类型声明
     * @param <M> 源对象模型类型声明
     * @param <T> 目标对象模型类型声明
     * @param <E> 源对象实体类型声明
     * @throws RestException RestException
     */
    public static <I, M, T,E> void buildMultiSourceId(
            Collection<E> entityList,Collection<M> modelList, FunctionActuator<Collection<I>, List<T>> targetListQueryBySourceIdList,
            Function<E,I> entityGetSourceId, FunctionActuator<T,I> targetGetSourceId,
            FunctionActuator<M,I> sourceGetTargetId, BiConsumerActuator<M, Collection<T>> sourceSetTargetList,
            Integer index, Boolean... isLoadArray
    ) throws RestException {
        List<I> sourceIdList = entityList.stream().filter(GeneralUtils::isNotEmpty).map(entityGetSourceId).distinct().collect(Collectors.toList());
        if (GeneralUtils.isNotEmpty(sourceIdList) && isLoadArray.length > index && isLoadArray[index]) {
            List<T> targetList = targetListQueryBySourceIdList.actuate(sourceIdList);
            buildMultiTargetSourceId(modelList,targetList,targetGetSourceId,sourceGetTargetId,sourceSetTargetList);
        }
    }

    /**
     * 通过源对象id列表查询设置多个目标对象
     * @param entityList 源对象实体集合
     * @param modelList 源对象模型集合
     * @param targetListQueryBySourceIdList 通过源对象id列表查询目标对象表
     * @param entityGetSourceId 源对象实体获取源对象id
     * @param targetGetSourceId 目标对象模型获取源对象id
     * @param sourceGetTargetId 源对象模型获取目标对象id
     * @param sourceSetTargetList 源对象设置多个目标对象
     * @param <I> 对象Id类型声明
     * @param <M> 源对象模型类型声明
     * @param <T> 目标对象模型类型声明
     * @param <E> 源对象实体类型声明
     * @throws RestException RestException
     */
    public static <I, M, T,E> void buildMultiSourceId(
            Collection<E> entityList,Collection<M> modelList, FunctionActuator<Collection<I>, List<T>> targetListQueryBySourceIdList,
            Function<E,I> entityGetSourceId, FunctionActuator<T,I> targetGetSourceId,
            FunctionActuator<M,I> sourceGetTargetId, BiConsumerActuator<M, Collection<T>> sourceSetTargetList
    ) throws RestException {
        List<I> sourceIdList = entityList.stream().filter(GeneralUtils::isNotEmpty).map(entityGetSourceId).distinct().collect(Collectors.toList());
        if (GeneralUtils.isNotEmpty(sourceIdList)) {
            List<T> targetList = targetListQueryBySourceIdList.actuate(sourceIdList);
            buildMultiTargetSourceId(modelList,targetList,targetGetSourceId,sourceGetTargetId,sourceSetTargetList);
        }
    }


    /**
     * 通过目标对象id列表设置单个目标对象
     * @param modelList 源对象模型集合
     * @param targetList 目标象模型集合
     * @param sourceGetTargetId 源对象模型获取目标对象id
     * @param sourceSetTarget 源对象设置单个目标对象
     * @param <I> 对象Id类型声明
     * @param <M> 源对象模型类型声明
     * @param <T> 目标对象模型类型声明
     * @throws RestException RestException
     */
    public static <I, M, T extends IdModel<I>> void buildSingleTargetTargetId(
            Collection<M> modelList, Collection<T> targetList, FunctionActuator<M,I> sourceGetTargetId, BiConsumerActuator<M, T> sourceSetTarget
    ) throws RestException {
        if (GeneralUtils.isNotEmpty(targetList)) {
            Map<I, T> targetIdTargetMap = new HashMap<>();
            targetMapTargetId(targetList,targetIdTargetMap,IdModel::getId);
            sourceTarget(modelList,sourceGetTargetId,targetIdTargetMap,sourceSetTarget);
        }
    }

    /**
     * 通过目标对象id列表设置单个目标对象
     * @param modelList 源对象模型集合
     * @param targetList 目标象模型集合
     * @param targetGetTargetId 目标对象模型获取目标对象id
     * @param sourceGetTargetId 源对象模型获取目标对象id
     * @param sourceSetTarget 源对象设置单个目标对象
     * @param <I> 对象Id类型声明
     * @param <M> 源对象模型类型声明
     * @param <T> 目标对象模型类型声明
     * @throws RestException RestException
     */
    public static <I, M, T> void buildSingleTargetTargetId(
            Collection<M> modelList, Collection<T> targetList, FunctionActuator<T, I> targetGetTargetId,
            FunctionActuator<M,I> sourceGetTargetId, BiConsumerActuator<M, T> sourceSetTarget
    ) throws RestException {
        if (GeneralUtils.isNotEmpty(targetList)) {
            Map<I, T> targetIdTargetMap = new HashMap<>();
            targetMapTargetId(targetList,targetIdTargetMap,targetGetTargetId);
            sourceTarget(modelList,sourceGetTargetId,targetIdTargetMap,sourceSetTarget);
        }
    }

    /**
     * 通过源对象id列表设置单个目标对象
     * @param modelList 源对象模型集合
     * @param targetList 目标象模型集合
     * @param targetGetSourceId 目标对象模型获取源对象id
     * @param sourceSetTarget 源对象设置单个目标对象
     * @param <I> 对象Id类型声明
     * @param <M> 源对象模型类型声明
     * @param <T> 目标对象模型类型声明
     * @throws RestException RestException
     */
    public static <I, M  extends IdModel<I>, T> void buildSingleTargetSourceId(
            Collection<M> modelList, Collection<T> targetList, FunctionActuator<T, I> targetGetSourceId,
            BiConsumerActuator<M, T> sourceSetTarget
    ) throws RestException {
        if (GeneralUtils.isNotEmpty(targetList)) {
            Map<I, T> sourceIdTargetMap = new HashMap<>();
            targetMapSourceId(targetList,sourceIdTargetMap,targetGetSourceId);
            sourceTarget(modelList,IdModel::getId,sourceIdTargetMap,sourceSetTarget);
        }
    }

    /**
     * 通过源对象id列表设置单个目标对象
     * @param modelList 源对象模型集合
     * @param targetList 目标象模型集合
     * @param targetGetSourceId 目标对象模型获取源对象id
     * @param sourceGetSourceId 源对象模型获取源对象id
     * @param sourceSetTarget 源对象设置单个目标对象
     * @param <I> 对象Id类型声明
     * @param <M> 源对象模型类型声明
     * @param <T> 目标对象模型类型声明
     * @throws RestException RestException
     */
    public static <I, M, T> void buildSingleTargetSourceId(
            Collection<M> modelList, Collection<T> targetList, FunctionActuator<T, I> targetGetSourceId,
            FunctionActuator<M,I> sourceGetSourceId, BiConsumerActuator<M, T> sourceSetTarget
    ) throws RestException {
        if (GeneralUtils.isNotEmpty(targetList)) {
            Map<I, T> sourceIdTargetMap = new HashMap<>();
            targetMapSourceId(targetList,sourceIdTargetMap,targetGetSourceId);
            sourceTarget(modelList,sourceGetSourceId,sourceIdTargetMap,sourceSetTarget);
        }
    }

    /**
     * 通过目标对象id列表设置多个目标对象
     * @param modelList 源对象模型集合
     * @param targetList 目标象模型集合
     * @param sourceIdTargetIdListMap 源对象id目标象模型 ListMap集合
     * @param sourceSetTargetList 源对象设置多个目标对象
     * @param <I> 对象Id类型声明
     * @param <M> 源对象模型类型声明
     * @param <T> 目标对象模型类型声明
     * @throws RestException RestException
     */
    public static <I, M extends IdModel<I>, T extends IdModel<I>> void buildMultiTargetTargetId(
            Collection<M> modelList, Collection<T> targetList, Map<I, List<I>> sourceIdTargetIdListMap,
            BiConsumerActuator<M, Collection<T>> sourceSetTargetList
    ) throws RestException {
        if (GeneralUtils.isNotEmpty(targetList)) {
            Map<I, List<T>> sourceIdTargetListMap = new HashMap<>();
            targetListMapTargetId(targetList,sourceIdTargetListMap,sourceIdTargetIdListMap,IdModel::getId);
            sourceTargetList(modelList,IdModel::getId,sourceIdTargetListMap,sourceSetTargetList);
        }
    }

    /**
     * 通过目标对象id列表设置多个目标对象
     * @param modelList 源对象模型集合
     * @param targetList 目标象模型集合
     * @param sourceIdTargetIdListMap 源对象id目标象模型 ListMap集合
     * @param targetGetTargetId 目标对象模型获取目标对象id
     * @param sourceGetSourceId 源对象模型获取源对象id
     * @param sourceSetTargetList 源对象设置多个目标对象
     * @param <I> 对象Id类型声明
     * @param <M> 源对象模型类型声明
     * @param <T> 目标对象模型类型声明
     * @throws RestException RestException
     */
    public static <I, M, T> void buildMultiTargetTargetId(
            Collection<M> modelList, Collection<T> targetList, Map<I, List<I>> sourceIdTargetIdListMap,
            FunctionActuator<T, I> targetGetTargetId, FunctionActuator<M,I> sourceGetSourceId,
            BiConsumerActuator<M, Collection<T>> sourceSetTargetList
    ) throws RestException {
        if (GeneralUtils.isNotEmpty(targetList)) {
            Map<I, List<T>> sourceIdTargetListMap = new HashMap<>();
            targetListMapTargetId(targetList,sourceIdTargetListMap,sourceIdTargetIdListMap,targetGetTargetId);
            sourceTargetList(modelList,sourceGetSourceId,sourceIdTargetListMap,sourceSetTargetList);
        }
    }

    /**
     * 通过源对象id列表设置多个目标对象
     * @param modelList 源对象模型集合
     * @param targetList 目标象模型集合
     * @param targetGetSourceId 目标对象模型获取源对象id
     * @param sourceSetTargetList 源对象设置多个目标对象
     * @param <I> 对象Id类型声明
     * @param <M> 源对象模型类型声明
     * @param <T> 目标对象模型类型声明
     * @throws RestException RestException
     */
    public static <I, M extends IdModel<I>, T> void buildMultiTargetSourceId(
            Collection<M> modelList, Collection<T> targetList,
            FunctionActuator<T, I> targetGetSourceId, BiConsumerActuator<M, Collection<T>> sourceSetTargetList
    ) throws RestException {
        if (GeneralUtils.isNotEmpty(targetList)) {
            Map<I, List<T>> sourceIdTargetListMap = new HashMap<>();
            targetListMapSourceId(targetList,sourceIdTargetListMap,targetGetSourceId);
            sourceTargetList(modelList,IdModel::getId,sourceIdTargetListMap,sourceSetTargetList);
        }
    }

    /**
     * 通过源对象id列表设置多个目标对象
     * @param modelList 源对象模型集合
     * @param targetList 目标象模型集合
     * @param targetGetSourceId 目标对象模型获取源对象id
     * @param sourceGetSourceId 源对象模型获取源对象id
     * @param sourceSetTargetList 源对象设置多个目标对象
     * @param <I> 对象Id类型声明
     * @param <M> 源对象模型类型声明
     * @param <T> 目标对象模型类型声明
     * @throws RestException RestException
     */
    public static <I, M, T> void buildMultiTargetSourceId(
            Collection<M> modelList, Collection<T> targetList,
            FunctionActuator<T, I> targetGetSourceId, FunctionActuator<M,I> sourceGetSourceId,
            BiConsumerActuator<M, Collection<T>> sourceSetTargetList
    ) throws RestException {
        if (GeneralUtils.isNotEmpty(targetList)) {
            Map<I, List<T>> sourceIdTargetListMap = new HashMap<>();
            targetListMapSourceId(targetList,sourceIdTargetListMap,targetGetSourceId);
            sourceTargetList(modelList,sourceGetSourceId,sourceIdTargetListMap,sourceSetTargetList);
        }
    }


    /**
     * 源对象设置单个目标对象
     * @param modelList 源对象模型集合
     * @param getIdKey 获取对象KeyId
     * @param keyIdTargetMap 对象KeyIdMap
     * @param sourceSetTarget 源对象设置单个目标对象
     * @param <I> 对象Id类型声明
     * @param <M> 源对象模型类型声明
     * @param <T> 目标对象模型类型声明
     * @throws RestException RestException
     */
    public static <I, M, T> void sourceTarget(
            Collection<M> modelList, FunctionActuator<M,I> getIdKey,
            Map<I, T> keyIdTargetMap, BiConsumerActuator<M, T> sourceSetTarget
    ) throws RestException {
        for (M model : modelList) {
            I keyId = getIdKey.actuate(model);
            T target = keyIdTargetMap.get(keyId);
            if (GeneralUtils.isNotEmpty(target)) {
                sourceSetTarget.actuate(model, target);
            }
        }
    }

    /**
     * 源对象设置多个目标对象
     * @param modelList 源对象模型集合
     * @param getIdKey 获取对象KeyId
     * @param keyIdTargetListMap 对象KeyIdListMap
     * @param sourceSetTargetList 源对象设置多个目标对象
     * @param <I> 对象Id类型声明
     * @param <M> 源对象模型类型声明
     * @param <T> 目标对象模型类型声明
     * @throws RestException RestException
     */
    public static <I, M, T> void sourceTargetList(
            Collection<M> modelList, FunctionActuator<M,I> getIdKey,
            Map<I, List<T>> keyIdTargetListMap, BiConsumerActuator<M, Collection<T>> sourceSetTargetList
    ) throws RestException {
        for (M model : modelList) {
            I keyId = getIdKey.actuate(model);
            List<T> targets = keyIdTargetListMap.get(keyId);
            if (GeneralUtils.isNotEmpty(targets)) {
                sourceSetTargetList.actuate(model, targets);
            }
        }
    }

    /**
     * 目标对象列表转换目标对象id目标对象Map
     * @param targetList 目标象模型集合
     * @param targetMap 目标象模型Map集合
     * @param targetGetTargetId 目标对象模型获取目标对象id
     * @param <I> 对象Id类型声明
     * @param <T> 目标对象模型类型声明
     * @throws RestException RestException
     */
    public static <I, T> void targetMapTargetId(Collection<T> targetList, Map<I, T> targetMap, FunctionActuator<T, I> targetGetTargetId) throws RestException {
        for (T target : targetList) {
            if (GeneralUtils.isNotEmpty(target) && GeneralUtils.isNotEmpty(targetGetTargetId.actuate(target))) {
                targetMap.putIfAbsent(targetGetTargetId.actuate(target), target);
            }
        }
    }

    /**
     * 目标对象列表转换源对象id目标对象Map
     * @param targetList 目标象模型集合
     * @param targetMap 目标象模型Map集合
     * @param targetGetSourceId 目标对象模型获取源对象id
     * @param <I> 对象Id类型声明
     * @param <T> 目标对象模型类型声明
     * @throws RestException RestException
     */
    public static <I, T> void targetMapSourceId(Collection<T> targetList, Map<I, T> targetMap, FunctionActuator<T, I> targetGetSourceId) throws RestException {
        for (T target : targetList) {
            if (GeneralUtils.isNotEmpty(target) && GeneralUtils.isNotEmpty(targetGetSourceId.actuate(target))) {
                targetMap.putIfAbsent(targetGetSourceId.actuate(target), target);
            }
        }
    }

    /**
     * 目标对象列表转换源对象id目标对象ListMap
     * @param targetList 目标象模型集合
     * @param sourceIdTargetListMap 源对象id目标象模型 ListMap集合
     * @param sourceIdTargetIdListMap 源对象id目标象id ListMap集合
     * @param targetGetTargetId 目标对象模型获取目标对象id
     * @param <I> 对象Id类型声明
     * @param <T> 目标对象模型类型声明
     * @throws RestException RestException
     */
    public static <I, T> void targetListMapTargetId(Collection<T> targetList, Map<I, List<T>> sourceIdTargetListMap,Map<I, List<I>> sourceIdTargetIdListMap, FunctionActuator<T, I> targetGetTargetId) throws RestException {
        Map<I, T> targetIdTargetMap = new HashMap<>();
        targetMapTargetId(targetList,targetIdTargetMap,targetGetTargetId);
        for (Map.Entry<I, List<I>> entry : sourceIdTargetIdListMap.entrySet()) {
            I sourceId = entry.getKey();
            List<I> targetIdList = entry.getValue();
            if (GeneralUtils.isNotEmpty(targetIdList)) {
                List<T> targets = new ArrayList<>();
                for (I targetId : targetIdList) {
                    T target = targetIdTargetMap.get(targetId);
                    if (GeneralUtils.isNotEmpty(target)) {
                        targets.add(target);
                    }
                }
                sourceIdTargetListMap.putIfAbsent(sourceId,targets);
            }
        }
    }

    /**
     * 目标对象列表转换源对象id目标对象ListMap
     * @param targetList 目标象模型集合
     * @param targetListMap 目标象模型ListMap集合
     * @param targetGetSourceId 目标对象模型获取源对象id
     * @param <I> 对象Id类型声明
     * @param <T> 目标对象模型类型声明
     * @throws RestException RestException
     */
    public static <I, T> void targetListMapSourceId(Collection<T> targetList, Map<I, List<T>> targetListMap, FunctionActuator<T, I> targetGetSourceId) throws RestException {
        for (T target : targetList) {
            if (GeneralUtils.isNotEmpty(target) && GeneralUtils.isNotEmpty(targetGetSourceId.actuate(target))) {
                I sourceId = targetGetSourceId.actuate(target);
                CollectUtils.collect(sourceId,target,targetListMap);
            }
        }
    }

}
