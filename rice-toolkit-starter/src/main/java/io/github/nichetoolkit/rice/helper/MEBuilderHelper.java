package io.github.nichetoolkit.rice.helper;

import com.google.common.collect.Lists;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.actuator.BiConsumerActuator;
import io.github.nichetoolkit.rest.actuator.ConsumerActuator;
import io.github.nichetoolkit.rest.actuator.FunctionActuator;
import io.github.nichetoolkit.rest.holder.ApplicationContextHolder;
import io.github.nichetoolkit.rest.util.CollectUtils;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.RestId;
import io.github.nichetoolkit.rice.configure.RiceBeanProperties;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <code>MEBuilderHelper</code>
 * <p>The type me builder helper class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@SuppressWarnings("MixedMutabilityReturnType")
public class MEBuilderHelper {

    /**
     * <code>partition</code>
     * <p>the method.</p>
     * @param <I>                     {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <T>                     {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param targetIdList            {@link java.util.Collection} <p>the target id list parameter is <code>Collection</code> type.</p>
     * @param targetListQueryByIdList {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the target list query by id list parameter is <code>FunctionActuator</code> type.</p>
     * @return {@link java.util.List} <p>the return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestId
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <I, T extends RestId<I>> List<T> partition(Collection<I> targetIdList, FunctionActuator<Collection<I>, List<T>> targetListQueryByIdList) throws RestException {
        RiceBeanProperties beanProperties = ApplicationContextHolder.getBean(RiceBeanProperties.class);
        Integer partitionSize = beanProperties.getPartitionQuery();
        return partition(targetIdList, partitionSize, targetListQueryByIdList);
    }

    /**
     * <code>partition</code>
     * <p>the method.</p>
     * @param <I>                     {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <T>                     {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param targetIdList            {@link java.util.Collection} <p>the target id list parameter is <code>Collection</code> type.</p>
     * @param partition               {@link java.lang.Integer} <p>the partition parameter is <code>Integer</code> type.</p>
     * @param targetListQueryByIdList {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the target list query by id list parameter is <code>FunctionActuator</code> type.</p>
     * @return {@link java.util.List} <p>the return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestId
     * @see java.util.Collection
     * @see java.lang.Integer
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <I, T extends RestId<I>> List<T> partition(Collection<I> targetIdList, Integer partition, FunctionActuator<Collection<I>, List<T>> targetListQueryByIdList) throws RestException {
        Set<I> targetIdSet = new HashSet<>(targetIdList);
        if (GeneralUtils.isNotEmpty(partition) && targetIdList.size() > partition) {
            Set<T> targetList = new LinkedHashSet<>();
            List<List<I>> partitionList = Lists.partition(new ArrayList<>(targetIdSet), partition);
            for (List<I> partitions : partitionList) {
                List<T> targets = targetListQueryByIdList.actuate(partitions);
                targetList.addAll(targets);
            }
            return new ArrayList<>(targetList);
        } else {
            return targetListQueryByIdList.actuate(targetIdSet);
        }
    }

    /**
     * <code>entityList</code>
     * <p>the list method.</p>
     * @param <M>       {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <E>       {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param modelList {@link java.util.Collection} <p>the model list parameter is <code>Collection</code> type.</p>
     * @param consumer  {@link io.github.nichetoolkit.rest.actuator.ConsumerActuator} <p>the consumer parameter is <code>ConsumerActuator</code> type.</p>
     * @param function  {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the function parameter is <code>FunctionActuator</code> type.</p>
     * @return {@link java.util.List} <p>the list return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.actuator.ConsumerActuator
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <M, E> List<E> entityList(Collection<M> modelList, ConsumerActuator<M> consumer, FunctionActuator<M, E> function) throws RestException {
        if (GeneralUtils.isEmpty(modelList)) {
            return Collections.emptyList();
        }
        List<M> collect = modelList.stream().filter(Objects::nonNull).collect(Collectors.toList());
        Set<E> entityList = new LinkedHashSet<>();
        for (M model : collect) {
            if (model != null) {
                consumer.actuate(model);
                entityList.add(function.actuate(model));
            }
        }
        return new ArrayList<>(entityList);
    }

    /**
     * <code>entityList</code>
     * <p>the list method.</p>
     * @param <M>       {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <E>       {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param modelList {@link java.util.Collection} <p>the model list parameter is <code>Collection</code> type.</p>
     * @param function  {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the function parameter is <code>FunctionActuator</code> type.</p>
     * @return {@link java.util.List} <p>the list return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <M, E> List<E> entityList(Collection<M> modelList, FunctionActuator<M, E> function) throws RestException {
        if (GeneralUtils.isEmpty(modelList)) {
            return Collections.emptyList();
        }
        List<M> collect = modelList.stream().filter(Objects::nonNull).collect(Collectors.toList());
        Set<E> entityList = new LinkedHashSet<>();
        for (M model : collect) {
            if (model != null) {
                entityList.add(function.actuate(model));
            }
        }
        return new ArrayList<>(entityList);
    }

    /**
     * <code>indexList</code>
     * <p>the list method.</p>
     * @param <M>       {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <E>       {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param modelList {@link java.util.Collection} <p>the model list parameter is <code>Collection</code> type.</p>
     * @param function  {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the function parameter is <code>FunctionActuator</code> type.</p>
     * @return {@link java.util.List} <p>the list return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <M, E> List<E> indexList(Collection<M> modelList, FunctionActuator<M, List<E>> function) throws RestException {
        if (GeneralUtils.isEmpty(modelList)) {
            return Collections.emptyList();
        }
        List<M> collect = modelList.stream().filter(Objects::nonNull).collect(Collectors.toList());
        Set<E> entityList = new LinkedHashSet<>();
        for (M model : collect) {
            if (model != null) {
                entityList.addAll(function.actuate(model));
            }
        }
        return new ArrayList<>(entityList);
    }

    /**
     * <code>indexList</code>
     * <p>the list method.</p>
     * @param <M>       {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <E>       {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param modelList {@link java.util.Collection} <p>the model list parameter is <code>Collection</code> type.</p>
     * @param consumer  {@link io.github.nichetoolkit.rest.actuator.ConsumerActuator} <p>the consumer parameter is <code>ConsumerActuator</code> type.</p>
     * @param function  {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the function parameter is <code>FunctionActuator</code> type.</p>
     * @return {@link java.util.List} <p>the list return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.actuator.ConsumerActuator
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <M, E> List<E> indexList(Collection<M> modelList, ConsumerActuator<M> consumer, FunctionActuator<M, List<E>> function) throws RestException {
        if (GeneralUtils.isEmpty(modelList)) {
            return Collections.emptyList();
        }
        List<M> collect = modelList.stream().filter(Objects::nonNull).collect(Collectors.toList());
        Set<E> entityList = new LinkedHashSet<>();
        for (M model : collect) {
            if (model != null) {
                consumer.actuate(model);
                entityList.addAll(function.actuate(model));
            }
        }
        return new ArrayList<>(entityList);
    }

    /**
     * <code>modelList</code>
     * <p>the list method.</p>
     * @param <I>        {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <M>        {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <E>        {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param entityList {@link java.util.Collection} <p>the entity list parameter is <code>Collection</code> type.</p>
     * @param function   {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the function parameter is <code>FunctionActuator</code> type.</p>
     * @return {@link java.util.List} <p>the list return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <I, M, E> List<M> modelList(Collection<E> entityList, FunctionActuator<E, M> function) throws RestException {
        if (GeneralUtils.isEmpty(entityList)) {
            return Collections.emptyList();
        }
        Set<M> modelList = new LinkedHashSet<>();
        for (E entity : entityList) {
            if (entity != null) {
                modelList.add(function.actuate(entity));
            }
        }
        return new ArrayList<>(modelList);
    }


    /**
     * <code>modelList</code>
     * <p>the list method.</p>
     * @param <I>        {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <M>        {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <E>        {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param entityList {@link java.util.Collection} <p>the entity list parameter is <code>Collection</code> type.</p>
     * @param consumer   {@link io.github.nichetoolkit.rest.actuator.ConsumerActuator} <p>the consumer parameter is <code>ConsumerActuator</code> type.</p>
     * @param function   {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the function parameter is <code>FunctionActuator</code> type.</p>
     * @return {@link java.util.List} <p>the list return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.actuator.ConsumerActuator
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <I, M, E> List<M> modelList(Collection<E> entityList, ConsumerActuator<E> consumer, FunctionActuator<E, M> function) throws RestException {
        if (GeneralUtils.isEmpty(entityList)) {
            return Collections.emptyList();
        }
        Set<M> modelList = new LinkedHashSet<>();
        for (E entity : entityList) {
            if (entity != null) {
                consumer.actuate(entity);
                modelList.add(function.actuate(entity));
            }
        }
        return new ArrayList<>(modelList);
    }

    /**
     * <code>buildSingleTargetId</code>
     * <p>the single target id method.</p>
     * @param <I>                   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <M>                   {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <T>                   {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <E>                   {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param entity                E <p>the entity parameter is <code>E</code> type.</p>
     * @param model                 M <p>the model parameter is <code>M</code> type.</p>
     * @param targetQueryByTargetId {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the target query by target id parameter is <code>FunctionActuator</code> type.</p>
     * @param entityGetTargetId     {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the entity get target id parameter is <code>FunctionActuator</code> type.</p>
     * @param sourceSetTarget       {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>the source set target parameter is <code>BiConsumerActuator</code> type.</p>
     * @param index                 {@link java.lang.Integer} <p>the index parameter is <code>Integer</code> type.</p>
     * @param isLoadArray           {@link java.lang.Boolean} <p>the is load array parameter is <code>Boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestId
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     * @see java.lang.Integer
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <I, M extends RestId<I>, T extends RestId<I>, E extends RestId<I>> void buildSingleTargetId(
            E entity, M model, FunctionActuator<I, T> targetQueryByTargetId,
            FunctionActuator<E, I> entityGetTargetId,
            BiConsumerActuator<M, T> sourceSetTarget,
            Integer index, Boolean... isLoadArray
    ) throws RestException {
        I targetId = entityGetTargetId.actuate(entity);
        if (GeneralUtils.isNotEmpty(targetId) && isLoadArray.length > index && isLoadArray[index]) {
            T target = targetQueryByTargetId.actuate(targetId);
            if (GeneralUtils.isNotEmpty(target)) {
                sourceSetTarget.actuate(model, target);
            }
        }

    }

    /**
     * <code>buildSingleTargetId</code>
     * <p>the single target id method.</p>
     * @param <I>                   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <M>                   {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <T>                   {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <E>                   {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param entity                E <p>the entity parameter is <code>E</code> type.</p>
     * @param model                 M <p>the model parameter is <code>M</code> type.</p>
     * @param targetQueryByTargetId {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the target query by target id parameter is <code>FunctionActuator</code> type.</p>
     * @param entityGetTargetId     {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the entity get target id parameter is <code>FunctionActuator</code> type.</p>
     * @param sourceSetTarget       {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>the source set target parameter is <code>BiConsumerActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestId
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <I, M extends RestId<I>, T extends RestId<I>, E extends RestId<I>> void buildSingleTargetId(
            E entity, M model, FunctionActuator<I, T> targetQueryByTargetId,
            FunctionActuator<E, I> entityGetTargetId,
            BiConsumerActuator<M, T> sourceSetTarget
    ) throws RestException {
        I targetId = entityGetTargetId.actuate(entity);
        T target = targetQueryByTargetId.actuate(targetId);
        if (GeneralUtils.isNotEmpty(target)) {
            sourceSetTarget.actuate(model, target);
        }
    }

    /**
     * <code>buildSingleSourceId</code>
     * <p>the single source id method.</p>
     * @param <I>                   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <M>                   {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <T>                   {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <E>                   {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param entity                E <p>the entity parameter is <code>E</code> type.</p>
     * @param model                 M <p>the model parameter is <code>M</code> type.</p>
     * @param targetQueryBySourceId {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the target query by source id parameter is <code>FunctionActuator</code> type.</p>
     * @param sourceSetTarget       {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>the source set target parameter is <code>BiConsumerActuator</code> type.</p>
     * @param index                 {@link java.lang.Integer} <p>the index parameter is <code>Integer</code> type.</p>
     * @param isLoadArray           {@link java.lang.Boolean} <p>the is load array parameter is <code>Boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestId
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     * @see java.lang.Integer
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <I, M extends RestId<I>, T extends RestId<I>, E extends RestId<I>> void buildSingleSourceId(
            E entity, M model, FunctionActuator<I, T> targetQueryBySourceId,
            BiConsumerActuator<M, T> sourceSetTarget,
            Integer index, Boolean... isLoadArray
    ) throws RestException {
        I sourceId = entity.getId();
        if (GeneralUtils.isNotEmpty(sourceId) && isLoadArray.length > index && isLoadArray[index]) {
            T target = targetQueryBySourceId.actuate(sourceId);
            if (GeneralUtils.isNotEmpty(target)) {
                sourceSetTarget.actuate(model, target);
            }
        }

    }

    /**
     * <code>buildSingleSourceId</code>
     * <p>the single source id method.</p>
     * @param <I>                   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <M>                   {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <T>                   {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <E>                   {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param entity                E <p>the entity parameter is <code>E</code> type.</p>
     * @param model                 M <p>the model parameter is <code>M</code> type.</p>
     * @param targetQueryBySourceId {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the target query by source id parameter is <code>FunctionActuator</code> type.</p>
     * @param sourceSetTarget       {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>the source set target parameter is <code>BiConsumerActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestId
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <I, M extends RestId<I>, T extends RestId<I>, E extends RestId<I>> void buildSingleSourceId(
            E entity, M model, FunctionActuator<I, T> targetQueryBySourceId,
            BiConsumerActuator<M, T> sourceSetTarget
    ) throws RestException {
        I sourceId = entity.getId();
        T target = targetQueryBySourceId.actuate(sourceId);
        if (GeneralUtils.isNotEmpty(target)) {
            sourceSetTarget.actuate(model, target);
        }
    }

    /**
     * <code>buildSingleSourceId</code>
     * <p>the single source id method.</p>
     * @param <I>                   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <M>                   {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <T>                   {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <E>                   {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param entity                E <p>the entity parameter is <code>E</code> type.</p>
     * @param model                 M <p>the model parameter is <code>M</code> type.</p>
     * @param targetQueryBySourceId {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the target query by source id parameter is <code>FunctionActuator</code> type.</p>
     * @param entityGetSourceId     {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the entity get source id parameter is <code>FunctionActuator</code> type.</p>
     * @param sourceSetTarget       {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>the source set target parameter is <code>BiConsumerActuator</code> type.</p>
     * @param index                 {@link java.lang.Integer} <p>the index parameter is <code>Integer</code> type.</p>
     * @param isLoadArray           {@link java.lang.Boolean} <p>the is load array parameter is <code>Boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestId
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     * @see java.lang.Integer
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <I, M extends RestId<I>, T extends RestId<I>, E extends RestId<I>> void buildSingleSourceId(
            E entity, M model, FunctionActuator<I, T> targetQueryBySourceId,
            FunctionActuator<E, I> entityGetSourceId,
            BiConsumerActuator<M, T> sourceSetTarget,
            Integer index, Boolean... isLoadArray
    ) throws RestException {
        I sourceId = entityGetSourceId.actuate(entity);
        if (GeneralUtils.isNotEmpty(sourceId) && isLoadArray.length > index && isLoadArray[index]) {
            T target = targetQueryBySourceId.actuate(sourceId);
            if (GeneralUtils.isNotEmpty(target)) {
                sourceSetTarget.actuate(model, target);
            }
        }
    }

    /**
     * <code>buildSingleSourceId</code>
     * <p>the single source id method.</p>
     * @param <I>                   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <M>                   {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <T>                   {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <E>                   {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param entity                E <p>the entity parameter is <code>E</code> type.</p>
     * @param model                 M <p>the model parameter is <code>M</code> type.</p>
     * @param targetQueryBySourceId {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the target query by source id parameter is <code>FunctionActuator</code> type.</p>
     * @param entityGetSourceId     {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the entity get source id parameter is <code>FunctionActuator</code> type.</p>
     * @param sourceSetTarget       {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>the source set target parameter is <code>BiConsumerActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestId
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <I, M extends RestId<I>, T extends RestId<I>, E extends RestId<I>> void buildSingleSourceId(
            E entity, M model, FunctionActuator<I, T> targetQueryBySourceId,
            FunctionActuator<E, I> entityGetSourceId,
            BiConsumerActuator<M, T> sourceSetTarget
    ) throws RestException {
        I sourceId = entityGetSourceId.actuate(entity);
        if (GeneralUtils.isNotEmpty(sourceId)) {
            T target = targetQueryBySourceId.actuate(sourceId);
            if (GeneralUtils.isNotEmpty(target)) {
                sourceSetTarget.actuate(model, target);
            }
        }

    }


    /**
     * <code>buildMultiTargetId</code>
     * <p>the multi target id method.</p>
     * @param <I>                           {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <M>                           {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <T>                           {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param model                         M <p>the model parameter is <code>M</code> type.</p>
     * @param targetListQueryByTargetIdList {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the target list query by target id list parameter is <code>FunctionActuator</code> type.</p>
     * @param sourceGetTargetIdList         {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the source get target id list parameter is <code>FunctionActuator</code> type.</p>
     * @param sourceSetTargetList           {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>the source set target list parameter is <code>BiConsumerActuator</code> type.</p>
     * @param index                         {@link java.lang.Integer} <p>the index parameter is <code>Integer</code> type.</p>
     * @param isLoadArray                   {@link java.lang.Boolean} <p>the is load array parameter is <code>Boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestId
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     * @see java.lang.Integer
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <I, M extends RestId<I>, T extends RestId<I>> void buildMultiTargetId(
            M model, FunctionActuator<Collection<I>, List<T>> targetListQueryByTargetIdList,
            FunctionActuator<M, List<I>> sourceGetTargetIdList,
            BiConsumerActuator<M, Collection<T>> sourceSetTargetList,
            Integer index, Boolean... isLoadArray
    ) throws RestException {
        List<I> targetIdList = sourceGetTargetIdList.actuate(model);
        if (GeneralUtils.isNotEmpty(targetIdList) && isLoadArray.length > index && isLoadArray[index]) {
            List<T> targetList = partition(targetIdList, targetListQueryByTargetIdList);
            if (GeneralUtils.isNotEmpty(targetList)) {
                sourceSetTargetList.actuate(model, targetList);
            }
        }
    }

    /**
     * <code>buildMultiTargetId</code>
     * <p>the multi target id method.</p>
     * @param <I>                           {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <M>                           {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <T>                           {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param model                         M <p>the model parameter is <code>M</code> type.</p>
     * @param targetListQueryByTargetIdList {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the target list query by target id list parameter is <code>FunctionActuator</code> type.</p>
     * @param sourceGetTargetIdList         {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the source get target id list parameter is <code>FunctionActuator</code> type.</p>
     * @param sourceSetTargetList           {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>the source set target list parameter is <code>BiConsumerActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestId
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <I, M extends RestId<I>, T extends RestId<I>> void buildMultiTargetId(
            M model, FunctionActuator<Collection<I>, List<T>> targetListQueryByTargetIdList,
            FunctionActuator<M, List<I>> sourceGetTargetIdList,
            BiConsumerActuator<M, Collection<T>> sourceSetTargetList
    ) throws RestException {
        List<I> targetIdList = sourceGetTargetIdList.actuate(model);
        List<T> targetList = partition(targetIdList, targetListQueryByTargetIdList);
        if (GeneralUtils.isNotEmpty(targetList)) {
            sourceSetTargetList.actuate(model, targetList);
        }
    }

    /**
     * <code>buildMultiSourceId</code>
     * <p>the multi source id method.</p>
     * @param <I>                       {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <M>                       {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <T>                       {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <E>                       {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param entity                    E <p>the entity parameter is <code>E</code> type.</p>
     * @param model                     M <p>the model parameter is <code>M</code> type.</p>
     * @param targetListQueryBySourceId {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the target list query by source id parameter is <code>FunctionActuator</code> type.</p>
     * @param sourceSetTargetList       {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>the source set target list parameter is <code>BiConsumerActuator</code> type.</p>
     * @param index                     {@link java.lang.Integer} <p>the index parameter is <code>Integer</code> type.</p>
     * @param isLoadArray               {@link java.lang.Boolean} <p>the is load array parameter is <code>Boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestId
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     * @see java.lang.Integer
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <I, M extends RestId<I>, T extends RestId<I>, E extends RestId<I>> void buildMultiSourceId(
            E entity, M model, FunctionActuator<I, List<T>> targetListQueryBySourceId,
            BiConsumerActuator<M, Collection<T>> sourceSetTargetList,
            Integer index, Boolean... isLoadArray
    ) throws RestException {
        I sourceId = entity.getId();
        if (GeneralUtils.isNotEmpty(sourceId) && isLoadArray.length > index && isLoadArray[index]) {
            List<T> targetList = targetListQueryBySourceId.actuate(sourceId);
            if (GeneralUtils.isNotEmpty(targetList)) {
                sourceSetTargetList.actuate(model, targetList);
            }
        }

    }

    /**
     * <code>buildMultiSourceId</code>
     * <p>the multi source id method.</p>
     * @param <I>                       {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <M>                       {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <T>                       {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <E>                       {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param entity                    E <p>the entity parameter is <code>E</code> type.</p>
     * @param model                     M <p>the model parameter is <code>M</code> type.</p>
     * @param targetListQueryBySourceId {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the target list query by source id parameter is <code>FunctionActuator</code> type.</p>
     * @param sourceSetTargetList       {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>the source set target list parameter is <code>BiConsumerActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestId
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <I, M extends RestId<I>, T extends RestId<I>, E extends RestId<I>> void buildMultiSourceId(
            E entity, M model, FunctionActuator<I, List<T>> targetListQueryBySourceId,
            BiConsumerActuator<M, Collection<T>> sourceSetTargetList
    ) throws RestException {
        I sourceId = entity.getId();
        List<T> targetList = targetListQueryBySourceId.actuate(sourceId);
        if (GeneralUtils.isNotEmpty(targetList)) {
            sourceSetTargetList.actuate(model, targetList);
        }
    }

    /**
     * <code>buildMultiSourceId</code>
     * <p>the multi source id method.</p>
     * @param <I>                       {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <M>                       {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <T>                       {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <E>                       {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param entity                    E <p>the entity parameter is <code>E</code> type.</p>
     * @param model                     M <p>the model parameter is <code>M</code> type.</p>
     * @param targetListQueryBySourceId {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the target list query by source id parameter is <code>FunctionActuator</code> type.</p>
     * @param entityGetSourceId         {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the entity get source id parameter is <code>FunctionActuator</code> type.</p>
     * @param sourceSetTargetList       {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>the source set target list parameter is <code>BiConsumerActuator</code> type.</p>
     * @param index                     {@link java.lang.Integer} <p>the index parameter is <code>Integer</code> type.</p>
     * @param isLoadArray               {@link java.lang.Boolean} <p>the is load array parameter is <code>Boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestId
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     * @see java.lang.Integer
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <I, M extends RestId<I>, T extends RestId<I>, E extends RestId<I>> void buildMultiSourceId(
            E entity, M model, FunctionActuator<I, List<T>> targetListQueryBySourceId,
            FunctionActuator<E, I> entityGetSourceId,
            BiConsumerActuator<M, Collection<T>> sourceSetTargetList,
            Integer index, Boolean... isLoadArray
    ) throws RestException {
        I sourceId = entityGetSourceId.actuate(entity);
        if (GeneralUtils.isNotEmpty(sourceId) && isLoadArray.length > index && isLoadArray[index]) {
            List<T> targetList = targetListQueryBySourceId.actuate(sourceId);
            if (GeneralUtils.isNotEmpty(targetList)) {
                sourceSetTargetList.actuate(model, targetList);
            }
        }
    }

    /**
     * <code>buildMultiSourceId</code>
     * <p>the multi source id method.</p>
     * @param <I>                       {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <M>                       {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <T>                       {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <E>                       {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param entity                    E <p>the entity parameter is <code>E</code> type.</p>
     * @param model                     M <p>the model parameter is <code>M</code> type.</p>
     * @param targetListQueryBySourceId {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the target list query by source id parameter is <code>FunctionActuator</code> type.</p>
     * @param entityGetSourceId         {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the entity get source id parameter is <code>FunctionActuator</code> type.</p>
     * @param sourceSetTargetList       {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>the source set target list parameter is <code>BiConsumerActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestId
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <I, M extends RestId<I>, T extends RestId<I>, E extends RestId<I>> void buildMultiSourceId(
            E entity, M model, FunctionActuator<I, List<T>> targetListQueryBySourceId,
            FunctionActuator<E, I> entityGetSourceId,
            BiConsumerActuator<M, Collection<T>> sourceSetTargetList
    ) throws RestException {
        I sourceId = entityGetSourceId.actuate(entity);
        List<T> targetList = targetListQueryBySourceId.actuate(sourceId);
        if (GeneralUtils.isNotEmpty(targetList)) {
            sourceSetTargetList.actuate(model, targetList);
        }
    }


    /**
     * <code>buildSingleTargetId</code>
     * <p>the single target id method.</p>
     * @param <I>                           {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <M>                           {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <T>                           {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <E>                           {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param entityList                    {@link java.util.Collection} <p>the entity list parameter is <code>Collection</code> type.</p>
     * @param modelList                     {@link java.util.Collection} <p>the model list parameter is <code>Collection</code> type.</p>
     * @param targetListQueryByTargetIdList {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the target list query by target id list parameter is <code>FunctionActuator</code> type.</p>
     * @param entityGetTargetId             {@link java.util.function.Function} <p>the entity get target id parameter is <code>Function</code> type.</p>
     * @param sourceGetTargetId             {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the source get target id parameter is <code>FunctionActuator</code> type.</p>
     * @param sourceSetTarget               {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>the source set target parameter is <code>BiConsumerActuator</code> type.</p>
     * @param index                         {@link java.lang.Integer} <p>the index parameter is <code>Integer</code> type.</p>
     * @param isLoadArray                   {@link java.lang.Boolean} <p>the is load array parameter is <code>Boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestId
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see java.util.function.Function
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     * @see java.lang.Integer
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <I, M extends RestId<I>, T extends RestId<I>, E extends RestId<I>> void buildSingleTargetId(
            Collection<E> entityList, Collection<M> modelList, FunctionActuator<Collection<I>, List<T>> targetListQueryByTargetIdList,
            Function<E, I> entityGetTargetId, FunctionActuator<M, I> sourceGetTargetId, BiConsumerActuator<M, T> sourceSetTarget,
            Integer index, Boolean... isLoadArray
    ) throws RestException {
        List<I> targetIdList = entityList.stream().filter(GeneralUtils::isNotEmpty).map(entityGetTargetId).filter(GeneralUtils::isNotEmpty).distinct().collect(Collectors.toList());
        if (GeneralUtils.isNotEmpty(targetIdList) && isLoadArray.length > index && isLoadArray[index]) {
            List<T> targetList = partition(targetIdList, targetListQueryByTargetIdList);
            buildSingleTargetTargetId(modelList, targetList, sourceGetTargetId, sourceSetTarget);
        }
    }

    /**
     * <code>buildSingleTargetId</code>
     * <p>the single target id method.</p>
     * @param <I>                           {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <M>                           {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <T>                           {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <E>                           {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param entityList                    {@link java.util.Collection} <p>the entity list parameter is <code>Collection</code> type.</p>
     * @param modelList                     {@link java.util.Collection} <p>the model list parameter is <code>Collection</code> type.</p>
     * @param targetListQueryByTargetIdList {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the target list query by target id list parameter is <code>FunctionActuator</code> type.</p>
     * @param entityGetTargetId             {@link java.util.function.Function} <p>the entity get target id parameter is <code>Function</code> type.</p>
     * @param sourceGetTargetId             {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the source get target id parameter is <code>FunctionActuator</code> type.</p>
     * @param sourceSetTarget               {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>the source set target parameter is <code>BiConsumerActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestId
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see java.util.function.Function
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <I, M extends RestId<I>, T extends RestId<I>, E extends RestId<I>> void buildSingleTargetId(
            Collection<E> entityList, Collection<M> modelList, FunctionActuator<Collection<I>, List<T>> targetListQueryByTargetIdList,
            Function<E, I> entityGetTargetId, FunctionActuator<M, I> sourceGetTargetId, BiConsumerActuator<M, T> sourceSetTarget
    ) throws RestException {
        List<I> targetIdList = entityList.stream().filter(GeneralUtils::isNotEmpty).map(entityGetTargetId).filter(GeneralUtils::isNotEmpty).distinct().collect(Collectors.toList());
        if (GeneralUtils.isNotEmpty(targetIdList)) {
            List<T> targetList = partition(targetIdList, targetListQueryByTargetIdList);
            buildSingleTargetTargetId(modelList, targetList, sourceGetTargetId, sourceSetTarget);
        }
    }

    /**
     * <code>buildSingleTargetId</code>
     * <p>the single target id method.</p>
     * @param <I>                           {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <M>                           {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <T>                           {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <E>                           {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param entityList                    {@link java.util.Collection} <p>the entity list parameter is <code>Collection</code> type.</p>
     * @param modelList                     {@link java.util.Collection} <p>the model list parameter is <code>Collection</code> type.</p>
     * @param targetListQueryByTargetIdList {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the target list query by target id list parameter is <code>FunctionActuator</code> type.</p>
     * @param entityGetTargetId             {@link java.util.function.Function} <p>the entity get target id parameter is <code>Function</code> type.</p>
     * @param targetGetTargetId             {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the target get target id parameter is <code>FunctionActuator</code> type.</p>
     * @param sourceGetTargetId             {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the source get target id parameter is <code>FunctionActuator</code> type.</p>
     * @param sourceSetTarget               {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>the source set target parameter is <code>BiConsumerActuator</code> type.</p>
     * @param index                         {@link java.lang.Integer} <p>the index parameter is <code>Integer</code> type.</p>
     * @param isLoadArray                   {@link java.lang.Boolean} <p>the is load array parameter is <code>Boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestId
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see java.util.function.Function
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     * @see java.lang.Integer
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <I, M extends RestId<I>, T extends RestId<I>, E extends RestId<I>> void buildSingleTargetId(
            Collection<E> entityList, Collection<M> modelList, FunctionActuator<Collection<I>, List<T>> targetListQueryByTargetIdList,
            Function<E, I> entityGetTargetId, FunctionActuator<T, I> targetGetTargetId,
            FunctionActuator<M, I> sourceGetTargetId, BiConsumerActuator<M, T> sourceSetTarget,
            Integer index, Boolean... isLoadArray
    ) throws RestException {
        List<I> targetIdList = entityList.stream().filter(GeneralUtils::isNotEmpty).map(entityGetTargetId).filter(GeneralUtils::isNotEmpty).distinct().collect(Collectors.toList());
        if (GeneralUtils.isNotEmpty(targetIdList) && isLoadArray.length > index && isLoadArray[index]) {
            List<T> targetList = partition(targetIdList, targetListQueryByTargetIdList);
            buildSingleTargetTargetId(modelList, targetList, targetGetTargetId, sourceGetTargetId, sourceSetTarget);
        }
    }

    /**
     * <code>buildSingleTargetId</code>
     * <p>the single target id method.</p>
     * @param <I>                           {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <M>                           {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <T>                           {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <E>                           {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param entityList                    {@link java.util.Collection} <p>the entity list parameter is <code>Collection</code> type.</p>
     * @param modelList                     {@link java.util.Collection} <p>the model list parameter is <code>Collection</code> type.</p>
     * @param targetListQueryByTargetIdList {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the target list query by target id list parameter is <code>FunctionActuator</code> type.</p>
     * @param entityGetTargetId             {@link java.util.function.Function} <p>the entity get target id parameter is <code>Function</code> type.</p>
     * @param targetGetTargetId             {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the target get target id parameter is <code>FunctionActuator</code> type.</p>
     * @param sourceGetTargetId             {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the source get target id parameter is <code>FunctionActuator</code> type.</p>
     * @param sourceSetTarget               {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>the source set target parameter is <code>BiConsumerActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestId
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see java.util.function.Function
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <I, M extends RestId<I>, T extends RestId<I>, E extends RestId<I>> void buildSingleTargetId(
            Collection<E> entityList, Collection<M> modelList, FunctionActuator<Collection<I>, List<T>> targetListQueryByTargetIdList,
            Function<E, I> entityGetTargetId, FunctionActuator<T, I> targetGetTargetId,
            FunctionActuator<M, I> sourceGetTargetId, BiConsumerActuator<M, T> sourceSetTarget
    ) throws RestException {
        List<I> targetIdList = entityList.stream().filter(GeneralUtils::isNotEmpty).map(entityGetTargetId).filter(GeneralUtils::isNotEmpty).distinct().collect(Collectors.toList());
        if (GeneralUtils.isNotEmpty(targetIdList)) {
            List<T> targetList = partition(targetIdList, targetListQueryByTargetIdList);
            buildSingleTargetTargetId(modelList, targetList, targetGetTargetId, sourceGetTargetId, sourceSetTarget);
        }
    }

    /**
     * <code>buildSingleSourceId</code>
     * <p>the single source id method.</p>
     * @param <I>                           {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <M>                           {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <T>                           {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <E>                           {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param entityList                    {@link java.util.Collection} <p>the entity list parameter is <code>Collection</code> type.</p>
     * @param modelList                     {@link java.util.Collection} <p>the model list parameter is <code>Collection</code> type.</p>
     * @param targetListQueryBySourceIdList {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the target list query by source id list parameter is <code>FunctionActuator</code> type.</p>
     * @param targetGetSourceId             {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the target get source id parameter is <code>FunctionActuator</code> type.</p>
     * @param sourceSetTarget               {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>the source set target parameter is <code>BiConsumerActuator</code> type.</p>
     * @param index                         {@link java.lang.Integer} <p>the index parameter is <code>Integer</code> type.</p>
     * @param isLoadArray                   {@link java.lang.Boolean} <p>the is load array parameter is <code>Boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestId
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     * @see java.lang.Integer
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <I, M extends RestId<I>, T extends RestId<I>, E extends RestId<I>> void buildSingleSourceId(
            Collection<E> entityList, Collection<M> modelList, FunctionActuator<Collection<I>, List<T>> targetListQueryBySourceIdList,
            FunctionActuator<T, I> targetGetSourceId, BiConsumerActuator<M, T> sourceSetTarget,
            Integer index, Boolean... isLoadArray
    ) throws RestException {
        List<I> sourceIdList = entityList.stream().filter(GeneralUtils::isNotEmpty).map(RestId::getId).filter(GeneralUtils::isNotEmpty).distinct().collect(Collectors.toList());
        if (GeneralUtils.isNotEmpty(sourceIdList) && isLoadArray.length > index && isLoadArray[index]) {
            List<T> targetList = partition(sourceIdList, targetListQueryBySourceIdList);
            buildSingleTargetSourceId(modelList, targetList, targetGetSourceId, sourceSetTarget);
        }
    }

    /**
     * <code>buildSingleSourceId</code>
     * <p>the single source id method.</p>
     * @param <I>                           {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <M>                           {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <T>                           {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <E>                           {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param entityList                    {@link java.util.Collection} <p>the entity list parameter is <code>Collection</code> type.</p>
     * @param modelList                     {@link java.util.Collection} <p>the model list parameter is <code>Collection</code> type.</p>
     * @param targetListQueryBySourceIdList {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the target list query by source id list parameter is <code>FunctionActuator</code> type.</p>
     * @param targetGetSourceId             {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the target get source id parameter is <code>FunctionActuator</code> type.</p>
     * @param sourceSetTarget               {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>the source set target parameter is <code>BiConsumerActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestId
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <I, M extends RestId<I>, T extends RestId<I>, E extends RestId<I>> void buildSingleSourceId(
            Collection<E> entityList, Collection<M> modelList, FunctionActuator<Collection<I>, List<T>> targetListQueryBySourceIdList,
            FunctionActuator<T, I> targetGetSourceId, BiConsumerActuator<M, T> sourceSetTarget
    ) throws RestException {
        List<I> sourceIdList = entityList.stream().filter(GeneralUtils::isNotEmpty).map(RestId::getId).filter(GeneralUtils::isNotEmpty).distinct().collect(Collectors.toList());
        if (GeneralUtils.isNotEmpty(sourceIdList)) {
            List<T> targetList = partition(sourceIdList, targetListQueryBySourceIdList);
            buildSingleTargetSourceId(modelList, targetList, targetGetSourceId, sourceSetTarget);
        }
    }

    /**
     * <code>buildSingleSourceId</code>
     * <p>the single source id method.</p>
     * @param <I>                           {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <M>                           {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <T>                           {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <E>                           {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param entityList                    {@link java.util.Collection} <p>the entity list parameter is <code>Collection</code> type.</p>
     * @param modelList                     {@link java.util.Collection} <p>the model list parameter is <code>Collection</code> type.</p>
     * @param targetListQueryBySourceIdList {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the target list query by source id list parameter is <code>FunctionActuator</code> type.</p>
     * @param entityGetSourceId             {@link java.util.function.Function} <p>the entity get source id parameter is <code>Function</code> type.</p>
     * @param targetGetSourceId             {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the target get source id parameter is <code>FunctionActuator</code> type.</p>
     * @param sourceGetSourceId             {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the source get source id parameter is <code>FunctionActuator</code> type.</p>
     * @param sourceSetTarget               {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>the source set target parameter is <code>BiConsumerActuator</code> type.</p>
     * @param index                         {@link java.lang.Integer} <p>the index parameter is <code>Integer</code> type.</p>
     * @param isLoadArray                   {@link java.lang.Boolean} <p>the is load array parameter is <code>Boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestId
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see java.util.function.Function
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     * @see java.lang.Integer
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <I, M extends RestId<I>, T extends RestId<I>, E extends RestId<I>> void buildSingleSourceId(
            Collection<E> entityList, Collection<M> modelList, FunctionActuator<Collection<I>, List<T>> targetListQueryBySourceIdList,
            Function<E, I> entityGetSourceId, FunctionActuator<T, I> targetGetSourceId,
            FunctionActuator<M, I> sourceGetSourceId, BiConsumerActuator<M, T> sourceSetTarget,
            Integer index, Boolean... isLoadArray
    ) throws RestException {
        List<I> sourceIdList = entityList.stream().filter(GeneralUtils::isNotEmpty).map(entityGetSourceId).filter(GeneralUtils::isNotEmpty).distinct().collect(Collectors.toList());
        if (GeneralUtils.isNotEmpty(sourceIdList) && isLoadArray.length > index && isLoadArray[index]) {
            List<T> targetList = partition(sourceIdList, targetListQueryBySourceIdList);
            buildSingleTargetSourceId(modelList, targetList, targetGetSourceId, sourceGetSourceId, sourceSetTarget);
        }
    }

    /**
     * <code>buildSingleSourceId</code>
     * <p>the single source id method.</p>
     * @param <I>                           {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <M>                           {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <T>                           {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <E>                           {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param entityList                    {@link java.util.Collection} <p>the entity list parameter is <code>Collection</code> type.</p>
     * @param modelList                     {@link java.util.Collection} <p>the model list parameter is <code>Collection</code> type.</p>
     * @param targetListQueryBySourceIdList {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the target list query by source id list parameter is <code>FunctionActuator</code> type.</p>
     * @param entityGetSourceId             {@link java.util.function.Function} <p>the entity get source id parameter is <code>Function</code> type.</p>
     * @param targetGetSourceId             {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the target get source id parameter is <code>FunctionActuator</code> type.</p>
     * @param sourceGetSourceId             {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the source get source id parameter is <code>FunctionActuator</code> type.</p>
     * @param sourceSetTarget               {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>the source set target parameter is <code>BiConsumerActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestId
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see java.util.function.Function
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <I, M extends RestId<I>, T extends RestId<I>, E extends RestId<I>> void buildSingleSourceId(
            Collection<E> entityList, Collection<M> modelList, FunctionActuator<Collection<I>, List<T>> targetListQueryBySourceIdList,
            Function<E, I> entityGetSourceId, FunctionActuator<T, I> targetGetSourceId,
            FunctionActuator<M, I> sourceGetSourceId, BiConsumerActuator<M, T> sourceSetTarget
    ) throws RestException {
        List<I> sourceIdList = entityList.stream().filter(GeneralUtils::isNotEmpty).map(entityGetSourceId).filter(GeneralUtils::isNotEmpty).distinct().collect(Collectors.toList());
        if (GeneralUtils.isNotEmpty(sourceIdList)) {
            List<T> targetList = partition(sourceIdList, targetListQueryBySourceIdList);
            buildSingleTargetSourceId(modelList, targetList, targetGetSourceId, sourceGetSourceId, sourceSetTarget);
        }
    }

    /**
     * <code>buildMultiTargetId</code>
     * <p>the multi target id method.</p>
     * @param <I>                           {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <M>                           {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <T>                           {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param modelList                     {@link java.util.Collection} <p>the model list parameter is <code>Collection</code> type.</p>
     * @param targetListQueryByTargetIdList {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the target list query by target id list parameter is <code>FunctionActuator</code> type.</p>
     * @param sourceGetTargetIdList         {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the source get target id list parameter is <code>FunctionActuator</code> type.</p>
     * @param sourceSetTargetList           {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>the source set target list parameter is <code>BiConsumerActuator</code> type.</p>
     * @param index                         {@link java.lang.Integer} <p>the index parameter is <code>Integer</code> type.</p>
     * @param isLoadArray                   {@link java.lang.Boolean} <p>the is load array parameter is <code>Boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestId
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     * @see java.lang.Integer
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <I, M extends RestId<I>, T extends RestId<I>> void buildMultiTargetId(
            Collection<M> modelList, FunctionActuator<Collection<I>, List<T>> targetListQueryByTargetIdList,
            FunctionActuator<M, List<I>> sourceGetTargetIdList, BiConsumerActuator<M, Collection<T>> sourceSetTargetList,
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
                    sourceIdTargetIdListMap.putIfAbsent(sourceId, idList);
                }
            }
        }
        Set<I> targetIdSet = new HashSet<>(targetIdList);
        if (GeneralUtils.isNotEmpty(targetIdSet) && isLoadArray.length > index && isLoadArray[index]) {
            List<T> targetList = partition(targetIdSet, targetListQueryByTargetIdList);
            buildMultiTargetTargetId(modelList, targetList, sourceIdTargetIdListMap, sourceSetTargetList);
        }
    }

    /**
     * <code>buildMultiTargetId</code>
     * <p>the multi target id method.</p>
     * @param <I>                           {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <M>                           {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <T>                           {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param modelList                     {@link java.util.Collection} <p>the model list parameter is <code>Collection</code> type.</p>
     * @param targetListQueryByTargetIdList {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the target list query by target id list parameter is <code>FunctionActuator</code> type.</p>
     * @param sourceGetTargetIdList         {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the source get target id list parameter is <code>FunctionActuator</code> type.</p>
     * @param sourceSetTargetList           {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>the source set target list parameter is <code>BiConsumerActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestId
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <I, M extends RestId<I>, T extends RestId<I>> void buildMultiTargetId(
            Collection<M> modelList, FunctionActuator<Collection<I>, List<T>> targetListQueryByTargetIdList,
            FunctionActuator<M, List<I>> sourceGetTargetIdList, BiConsumerActuator<M, Collection<T>> sourceSetTargetList
    ) throws RestException {
        Map<I, List<I>> sourceIdTargetIdListMap = new HashMap<>();
        List<I> targetIdList = new ArrayList<>();
        for (M model : modelList) {
            if (GeneralUtils.isNotEmpty(model)) {
                I sourceId = model.getId();
                List<I> idList = sourceGetTargetIdList.actuate(model);
                if (GeneralUtils.isNotEmpty(idList)) {
                    targetIdList.addAll(idList);
                    sourceIdTargetIdListMap.putIfAbsent(sourceId, idList);
                }
            }
        }
        Set<I> targetIdSet = new HashSet<>(targetIdList);
        if (GeneralUtils.isNotEmpty(targetIdSet)) {
            List<T> targetList = partition(targetIdSet, targetListQueryByTargetIdList);
            buildMultiTargetTargetId(modelList, targetList, sourceIdTargetIdListMap, sourceSetTargetList);
        }
    }

    /**
     * <code>buildMultiTargetId</code>
     * <p>the multi target id method.</p>
     * @param <I>                           {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <M>                           {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <T>                           {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param modelList                     {@link java.util.Collection} <p>the model list parameter is <code>Collection</code> type.</p>
     * @param targetListQueryByTargetIdList {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the target list query by target id list parameter is <code>FunctionActuator</code> type.</p>
     * @param sourceGetSourceId             {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the source get source id parameter is <code>FunctionActuator</code> type.</p>
     * @param sourceGetTargetIdList         {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the source get target id list parameter is <code>FunctionActuator</code> type.</p>
     * @param targetGetTargetId             {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the target get target id parameter is <code>FunctionActuator</code> type.</p>
     * @param sourceSetTargetList           {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>the source set target list parameter is <code>BiConsumerActuator</code> type.</p>
     * @param index                         {@link java.lang.Integer} <p>the index parameter is <code>Integer</code> type.</p>
     * @param isLoadArray                   {@link java.lang.Boolean} <p>the is load array parameter is <code>Boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestId
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     * @see java.lang.Integer
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <I, M extends RestId<I>, T extends RestId<I>> void buildMultiTargetId(
            Collection<M> modelList, FunctionActuator<Collection<I>, List<T>> targetListQueryByTargetIdList,
            FunctionActuator<M, I> sourceGetSourceId, FunctionActuator<M, List<I>> sourceGetTargetIdList, FunctionActuator<T, I> targetGetTargetId,
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
                    sourceIdTargetIdListMap.putIfAbsent(sourceId, idList);
                }
            }
        }
        Set<I> targetIdSet = new HashSet<>(targetIdList);
        if (GeneralUtils.isNotEmpty(targetIdSet) && isLoadArray.length > index && isLoadArray[index]) {
            List<T> targetList = partition(targetIdSet, targetListQueryByTargetIdList);
            buildMultiTargetTargetId(modelList, targetList, sourceIdTargetIdListMap, targetGetTargetId, sourceGetSourceId, sourceSetTargetList);
        }
    }

    /**
     * <code>buildMultiTargetId</code>
     * <p>the multi target id method.</p>
     * @param <I>                           {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <M>                           {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <T>                           {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param modelList                     {@link java.util.Collection} <p>the model list parameter is <code>Collection</code> type.</p>
     * @param targetListQueryByTargetIdList {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the target list query by target id list parameter is <code>FunctionActuator</code> type.</p>
     * @param sourceGetSourceId             {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the source get source id parameter is <code>FunctionActuator</code> type.</p>
     * @param sourceGetTargetIdList         {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the source get target id list parameter is <code>FunctionActuator</code> type.</p>
     * @param targetGetTargetId             {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the target get target id parameter is <code>FunctionActuator</code> type.</p>
     * @param sourceSetTargetList           {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>the source set target list parameter is <code>BiConsumerActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestId
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <I, M extends RestId<I>, T extends RestId<I>> void buildMultiTargetId(
            Collection<M> modelList, FunctionActuator<Collection<I>, List<T>> targetListQueryByTargetIdList,
            FunctionActuator<M, I> sourceGetSourceId, FunctionActuator<M, List<I>> sourceGetTargetIdList, FunctionActuator<T, I> targetGetTargetId,
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
                    sourceIdTargetIdListMap.putIfAbsent(sourceId, idList);
                }
            }
        }
        Set<I> targetIdSet = new HashSet<>(targetIdList);
        if (GeneralUtils.isNotEmpty(targetIdSet)) {
            List<T> targetList = partition(targetIdSet, targetListQueryByTargetIdList);
            buildMultiTargetTargetId(modelList, targetList, sourceIdTargetIdListMap, targetGetTargetId, sourceGetSourceId, sourceSetTargetList);
        }
    }

    /**
     * <code>buildMultiSourceId</code>
     * <p>the multi source id method.</p>
     * @param <I>                           {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <M>                           {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <T>                           {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <E>                           {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param entityList                    {@link java.util.Collection} <p>the entity list parameter is <code>Collection</code> type.</p>
     * @param modelList                     {@link java.util.Collection} <p>the model list parameter is <code>Collection</code> type.</p>
     * @param targetListQueryBySourceIdList {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the target list query by source id list parameter is <code>FunctionActuator</code> type.</p>
     * @param targetGetSourceId             {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the target get source id parameter is <code>FunctionActuator</code> type.</p>
     * @param sourceSetTargetList           {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>the source set target list parameter is <code>BiConsumerActuator</code> type.</p>
     * @param index                         {@link java.lang.Integer} <p>the index parameter is <code>Integer</code> type.</p>
     * @param isLoadArray                   {@link java.lang.Boolean} <p>the is load array parameter is <code>Boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestId
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     * @see java.lang.Integer
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <I, M extends RestId<I>, T extends RestId<I>, E extends RestId<I>> void buildMultiSourceId(
            Collection<E> entityList, Collection<M> modelList, FunctionActuator<Collection<I>, List<T>> targetListQueryBySourceIdList,
            FunctionActuator<T, I> targetGetSourceId, BiConsumerActuator<M, Collection<T>> sourceSetTargetList,
            Integer index, Boolean... isLoadArray
    ) throws RestException {
        List<I> sourceIdList = entityList.stream().filter(GeneralUtils::isNotEmpty).map(RestId::getId).distinct().collect(Collectors.toList());
        if (GeneralUtils.isNotEmpty(sourceIdList) && isLoadArray.length > index && isLoadArray[index]) {
            List<T> targetList = partition(sourceIdList, targetListQueryBySourceIdList);
            buildMultiTargetSourceId(modelList, targetList, targetGetSourceId, sourceSetTargetList);
        }
    }

    /**
     * <code>buildMultiSourceId</code>
     * <p>the multi source id method.</p>
     * @param <I>                           {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <M>                           {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <T>                           {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <E>                           {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param entityList                    {@link java.util.Collection} <p>the entity list parameter is <code>Collection</code> type.</p>
     * @param modelList                     {@link java.util.Collection} <p>the model list parameter is <code>Collection</code> type.</p>
     * @param targetListQueryBySourceIdList {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the target list query by source id list parameter is <code>FunctionActuator</code> type.</p>
     * @param targetGetSourceId             {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the target get source id parameter is <code>FunctionActuator</code> type.</p>
     * @param sourceGetSourceId             {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the source get source id parameter is <code>FunctionActuator</code> type.</p>
     * @param sourceSetTargetList           {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>the source set target list parameter is <code>BiConsumerActuator</code> type.</p>
     * @param index                         {@link java.lang.Integer} <p>the index parameter is <code>Integer</code> type.</p>
     * @param isLoadArray                   {@link java.lang.Boolean} <p>the is load array parameter is <code>Boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestId
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     * @see java.lang.Integer
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <I, M extends RestId<I>, T extends RestId<I>, E extends RestId<I>> void buildMultiSourceId(
            Collection<E> entityList, Collection<M> modelList, FunctionActuator<Collection<I>, List<T>> targetListQueryBySourceIdList,
            FunctionActuator<T, I> targetGetSourceId, FunctionActuator<M, I> sourceGetSourceId,
            BiConsumerActuator<M, Collection<T>> sourceSetTargetList,
            Integer index, Boolean... isLoadArray
    ) throws RestException {
        List<I> sourceIdList = entityList.stream().filter(GeneralUtils::isNotEmpty).map(RestId::getId).filter(GeneralUtils::isNotEmpty).distinct().collect(Collectors.toList());
        if (GeneralUtils.isNotEmpty(sourceIdList) && isLoadArray.length > index && isLoadArray[index]) {
            List<T> targetList = partition(sourceIdList, targetListQueryBySourceIdList);
            buildMultiTargetSourceId(modelList, targetList, targetGetSourceId, sourceGetSourceId, sourceSetTargetList);
        }
    }

    /**
     * <code>buildMultiSourceId</code>
     * <p>the multi source id method.</p>
     * @param <I>                           {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <M>                           {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <T>                           {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <E>                           {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param entityList                    {@link java.util.Collection} <p>the entity list parameter is <code>Collection</code> type.</p>
     * @param modelList                     {@link java.util.Collection} <p>the model list parameter is <code>Collection</code> type.</p>
     * @param targetListQueryBySourceIdList {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the target list query by source id list parameter is <code>FunctionActuator</code> type.</p>
     * @param targetGetSourceId             {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the target get source id parameter is <code>FunctionActuator</code> type.</p>
     * @param sourceGetTargetId             {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the source get target id parameter is <code>FunctionActuator</code> type.</p>
     * @param sourceSetTargetList           {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>the source set target list parameter is <code>BiConsumerActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestId
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <I, M extends RestId<I>, T extends RestId<I>, E extends RestId<I>> void buildMultiSourceId(
            Collection<E> entityList, Collection<M> modelList, FunctionActuator<Collection<I>, List<T>> targetListQueryBySourceIdList,
            FunctionActuator<T, I> targetGetSourceId, FunctionActuator<M, I> sourceGetTargetId, BiConsumerActuator<M, Collection<T>> sourceSetTargetList
    ) throws RestException {
        List<I> sourceIdList = entityList.stream().filter(GeneralUtils::isNotEmpty).map(RestId::getId).filter(GeneralUtils::isNotEmpty).distinct().collect(Collectors.toList());
        if (GeneralUtils.isNotEmpty(sourceIdList)) {
            List<T> targetList = partition(sourceIdList, targetListQueryBySourceIdList);
            buildMultiTargetSourceId(modelList, targetList, targetGetSourceId, sourceGetTargetId, sourceSetTargetList);
        }
    }

    /**
     * <code>buildMultiSourceId</code>
     * <p>the multi source id method.</p>
     * @param <I>                           {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <M>                           {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <T>                           {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <E>                           {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param entityList                    {@link java.util.Collection} <p>the entity list parameter is <code>Collection</code> type.</p>
     * @param modelList                     {@link java.util.Collection} <p>the model list parameter is <code>Collection</code> type.</p>
     * @param targetListQueryBySourceIdList {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the target list query by source id list parameter is <code>FunctionActuator</code> type.</p>
     * @param entityGetSourceId             {@link java.util.function.Function} <p>the entity get source id parameter is <code>Function</code> type.</p>
     * @param targetGetSourceId             {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the target get source id parameter is <code>FunctionActuator</code> type.</p>
     * @param sourceGetTargetId             {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the source get target id parameter is <code>FunctionActuator</code> type.</p>
     * @param sourceSetTargetList           {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>the source set target list parameter is <code>BiConsumerActuator</code> type.</p>
     * @param index                         {@link java.lang.Integer} <p>the index parameter is <code>Integer</code> type.</p>
     * @param isLoadArray                   {@link java.lang.Boolean} <p>the is load array parameter is <code>Boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestId
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see java.util.function.Function
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     * @see java.lang.Integer
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <I, M extends RestId<I>, T extends RestId<I>, E extends RestId<I>> void buildMultiSourceId(
            Collection<E> entityList, Collection<M> modelList, FunctionActuator<Collection<I>, List<T>> targetListQueryBySourceIdList,
            Function<E, I> entityGetSourceId, FunctionActuator<T, I> targetGetSourceId,
            FunctionActuator<M, I> sourceGetTargetId, BiConsumerActuator<M, Collection<T>> sourceSetTargetList,
            Integer index, Boolean... isLoadArray
    ) throws RestException {
        List<I> sourceIdList = entityList.stream().filter(GeneralUtils::isNotEmpty).map(entityGetSourceId).filter(GeneralUtils::isNotEmpty).distinct().collect(Collectors.toList());
        if (GeneralUtils.isNotEmpty(sourceIdList) && isLoadArray.length > index && isLoadArray[index]) {
            List<T> targetList = partition(sourceIdList, targetListQueryBySourceIdList);
            buildMultiTargetSourceId(modelList, targetList, targetGetSourceId, sourceGetTargetId, sourceSetTargetList);
        }
    }

    /**
     * <code>buildMultiSourceId</code>
     * <p>the multi source id method.</p>
     * @param <I>                           {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <M>                           {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <T>                           {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <E>                           {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param entityList                    {@link java.util.Collection} <p>the entity list parameter is <code>Collection</code> type.</p>
     * @param modelList                     {@link java.util.Collection} <p>the model list parameter is <code>Collection</code> type.</p>
     * @param targetListQueryBySourceIdList {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the target list query by source id list parameter is <code>FunctionActuator</code> type.</p>
     * @param entityGetSourceId             {@link java.util.function.Function} <p>the entity get source id parameter is <code>Function</code> type.</p>
     * @param targetGetSourceId             {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the target get source id parameter is <code>FunctionActuator</code> type.</p>
     * @param sourceGetTargetId             {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the source get target id parameter is <code>FunctionActuator</code> type.</p>
     * @param sourceSetTargetList           {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>the source set target list parameter is <code>BiConsumerActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestId
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see java.util.function.Function
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <I, M extends RestId<I>, T extends RestId<I>, E extends RestId<I>> void buildMultiSourceId(
            Collection<E> entityList, Collection<M> modelList, FunctionActuator<Collection<I>, List<T>> targetListQueryBySourceIdList,
            Function<E, I> entityGetSourceId, FunctionActuator<T, I> targetGetSourceId,
            FunctionActuator<M, I> sourceGetTargetId, BiConsumerActuator<M, Collection<T>> sourceSetTargetList
    ) throws RestException {
        List<I> sourceIdList = entityList.stream().filter(GeneralUtils::isNotEmpty).map(entityGetSourceId).filter(GeneralUtils::isNotEmpty).distinct().collect(Collectors.toList());
        if (GeneralUtils.isNotEmpty(sourceIdList)) {
            List<T> targetList = partition(sourceIdList, targetListQueryBySourceIdList);
            buildMultiTargetSourceId(modelList, targetList, targetGetSourceId, sourceGetTargetId, sourceSetTargetList);
        }
    }


    /**
     * <code>buildSingleTargetTargetId</code>
     * <p>the single target target id method.</p>
     * @param <I>               {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <M>               {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <T>               {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param modelList         {@link java.util.Collection} <p>the model list parameter is <code>Collection</code> type.</p>
     * @param targetList        {@link java.util.Collection} <p>the target list parameter is <code>Collection</code> type.</p>
     * @param sourceGetTargetId {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the source get target id parameter is <code>FunctionActuator</code> type.</p>
     * @param sourceSetTarget   {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>the source set target parameter is <code>BiConsumerActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestId
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <I, M extends RestId<I>, T extends RestId<I>> void buildSingleTargetTargetId(
            Collection<M> modelList, Collection<T> targetList, FunctionActuator<M, I> sourceGetTargetId, BiConsumerActuator<M, T> sourceSetTarget
    ) throws RestException {
        if (GeneralUtils.isNotEmpty(targetList)) {
            Map<I, T> targetIdTargetMap = new HashMap<>();
            targetMapTargetId(targetList, targetIdTargetMap, RestId::getId);
            sourceTarget(modelList, sourceGetTargetId, targetIdTargetMap, sourceSetTarget);
        }
    }

    /**
     * <code>buildSingleTargetTargetId</code>
     * <p>the single target target id method.</p>
     * @param <I>               {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <M>               {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <T>               {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param modelList         {@link java.util.Collection} <p>the model list parameter is <code>Collection</code> type.</p>
     * @param targetList        {@link java.util.Collection} <p>the target list parameter is <code>Collection</code> type.</p>
     * @param targetGetTargetId {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the target get target id parameter is <code>FunctionActuator</code> type.</p>
     * @param sourceGetTargetId {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the source get target id parameter is <code>FunctionActuator</code> type.</p>
     * @param sourceSetTarget   {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>the source set target parameter is <code>BiConsumerActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestId
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <I, M extends RestId<I>, T extends RestId<I>> void buildSingleTargetTargetId(
            Collection<M> modelList, Collection<T> targetList, FunctionActuator<T, I> targetGetTargetId,
            FunctionActuator<M, I> sourceGetTargetId, BiConsumerActuator<M, T> sourceSetTarget
    ) throws RestException {
        if (GeneralUtils.isNotEmpty(targetList)) {
            Map<I, T> targetIdTargetMap = new HashMap<>();
            targetMapTargetId(targetList, targetIdTargetMap, targetGetTargetId);
            sourceTarget(modelList, sourceGetTargetId, targetIdTargetMap, sourceSetTarget);
        }
    }

    /**
     * <code>buildSingleTargetSourceId</code>
     * <p>the single target source id method.</p>
     * @param <I>               {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <M>               {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <T>               {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param modelList         {@link java.util.Collection} <p>the model list parameter is <code>Collection</code> type.</p>
     * @param targetList        {@link java.util.Collection} <p>the target list parameter is <code>Collection</code> type.</p>
     * @param targetGetSourceId {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the target get source id parameter is <code>FunctionActuator</code> type.</p>
     * @param sourceSetTarget   {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>the source set target parameter is <code>BiConsumerActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestId
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <I, M extends RestId<I>, T extends RestId<I>> void buildSingleTargetSourceId(
            Collection<M> modelList, Collection<T> targetList, FunctionActuator<T, I> targetGetSourceId,
            BiConsumerActuator<M, T> sourceSetTarget
    ) throws RestException {
        if (GeneralUtils.isNotEmpty(targetList)) {
            Map<I, T> sourceIdTargetMap = new HashMap<>();
            targetMapSourceId(targetList, sourceIdTargetMap, targetGetSourceId);
            sourceTarget(modelList, RestId::getId, sourceIdTargetMap, sourceSetTarget);
        }
    }

    /**
     * <code>buildSingleTargetSourceId</code>
     * <p>the single target source id method.</p>
     * @param <I>               {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <M>               {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <T>               {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param modelList         {@link java.util.Collection} <p>the model list parameter is <code>Collection</code> type.</p>
     * @param targetList        {@link java.util.Collection} <p>the target list parameter is <code>Collection</code> type.</p>
     * @param targetGetSourceId {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the target get source id parameter is <code>FunctionActuator</code> type.</p>
     * @param sourceGetSourceId {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the source get source id parameter is <code>FunctionActuator</code> type.</p>
     * @param sourceSetTarget   {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>the source set target parameter is <code>BiConsumerActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestId
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <I, M extends RestId<I>, T extends RestId<I>> void buildSingleTargetSourceId(
            Collection<M> modelList, Collection<T> targetList, FunctionActuator<T, I> targetGetSourceId,
            FunctionActuator<M, I> sourceGetSourceId, BiConsumerActuator<M, T> sourceSetTarget
    ) throws RestException {
        if (GeneralUtils.isNotEmpty(targetList)) {
            Map<I, T> sourceIdTargetMap = new HashMap<>();
            targetMapSourceId(targetList, sourceIdTargetMap, targetGetSourceId);
            sourceTarget(modelList, sourceGetSourceId, sourceIdTargetMap, sourceSetTarget);
        }
    }

    /**
     * <code>buildMultiTargetTargetId</code>
     * <p>the multi target target id method.</p>
     * @param <I>                     {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <M>                     {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <T>                     {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param modelList               {@link java.util.Collection} <p>the model list parameter is <code>Collection</code> type.</p>
     * @param targetList              {@link java.util.Collection} <p>the target list parameter is <code>Collection</code> type.</p>
     * @param sourceIdTargetIdListMap {@link java.util.Map} <p>the source id target id list map parameter is <code>Map</code> type.</p>
     * @param sourceSetTargetList     {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>the source set target list parameter is <code>BiConsumerActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestId
     * @see java.util.Collection
     * @see java.util.Map
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <I, M extends RestId<I>, T extends RestId<I>> void buildMultiTargetTargetId(
            Collection<M> modelList, Collection<T> targetList, Map<I, List<I>> sourceIdTargetIdListMap,
            BiConsumerActuator<M, Collection<T>> sourceSetTargetList
    ) throws RestException {
        if (GeneralUtils.isNotEmpty(targetList)) {
            Map<I, List<T>> sourceIdTargetListMap = new HashMap<>();
            targetListMapTargetId(targetList, sourceIdTargetListMap, sourceIdTargetIdListMap, RestId::getId);
            sourceTargetList(modelList, RestId::getId, sourceIdTargetListMap, sourceSetTargetList);
        }
    }

    /**
     * <code>buildMultiTargetTargetId</code>
     * <p>the multi target target id method.</p>
     * @param <I>                     {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <M>                     {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <T>                     {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param modelList               {@link java.util.Collection} <p>the model list parameter is <code>Collection</code> type.</p>
     * @param targetList              {@link java.util.Collection} <p>the target list parameter is <code>Collection</code> type.</p>
     * @param sourceIdTargetIdListMap {@link java.util.Map} <p>the source id target id list map parameter is <code>Map</code> type.</p>
     * @param targetGetTargetId       {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the target get target id parameter is <code>FunctionActuator</code> type.</p>
     * @param sourceGetSourceId       {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the source get source id parameter is <code>FunctionActuator</code> type.</p>
     * @param sourceSetTargetList     {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>the source set target list parameter is <code>BiConsumerActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestId
     * @see java.util.Collection
     * @see java.util.Map
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <I, M extends RestId<I>, T extends RestId<I>> void buildMultiTargetTargetId(
            Collection<M> modelList, Collection<T> targetList, Map<I, List<I>> sourceIdTargetIdListMap,
            FunctionActuator<T, I> targetGetTargetId, FunctionActuator<M, I> sourceGetSourceId,
            BiConsumerActuator<M, Collection<T>> sourceSetTargetList
    ) throws RestException {
        if (GeneralUtils.isNotEmpty(targetList)) {
            Map<I, List<T>> sourceIdTargetListMap = new HashMap<>();
            targetListMapTargetId(targetList, sourceIdTargetListMap, sourceIdTargetIdListMap, targetGetTargetId);
            sourceTargetList(modelList, sourceGetSourceId, sourceIdTargetListMap, sourceSetTargetList);
        }
    }

    /**
     * <code>buildMultiTargetSourceId</code>
     * <p>the multi target source id method.</p>
     * @param <I>                 {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <M>                 {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <T>                 {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param modelList           {@link java.util.Collection} <p>the model list parameter is <code>Collection</code> type.</p>
     * @param targetList          {@link java.util.Collection} <p>the target list parameter is <code>Collection</code> type.</p>
     * @param targetGetSourceId   {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the target get source id parameter is <code>FunctionActuator</code> type.</p>
     * @param sourceSetTargetList {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>the source set target list parameter is <code>BiConsumerActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestId
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <I, M extends RestId<I>, T extends RestId<I>> void buildMultiTargetSourceId(
            Collection<M> modelList, Collection<T> targetList,
            FunctionActuator<T, I> targetGetSourceId, BiConsumerActuator<M, Collection<T>> sourceSetTargetList
    ) throws RestException {
        if (GeneralUtils.isNotEmpty(targetList)) {
            Map<I, List<T>> sourceIdTargetListMap = new HashMap<>();
            targetListMapSourceId(targetList, sourceIdTargetListMap, targetGetSourceId);
            sourceTargetList(modelList, RestId::getId, sourceIdTargetListMap, sourceSetTargetList);
        }
    }

    /**
     * <code>buildMultiTargetSourceId</code>
     * <p>the multi target source id method.</p>
     * @param <I>                 {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <M>                 {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <T>                 {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param modelList           {@link java.util.Collection} <p>the model list parameter is <code>Collection</code> type.</p>
     * @param targetList          {@link java.util.Collection} <p>the target list parameter is <code>Collection</code> type.</p>
     * @param targetGetSourceId   {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the target get source id parameter is <code>FunctionActuator</code> type.</p>
     * @param sourceGetSourceId   {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the source get source id parameter is <code>FunctionActuator</code> type.</p>
     * @param sourceSetTargetList {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>the source set target list parameter is <code>BiConsumerActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestId
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <I, M extends RestId<I>, T extends RestId<I>> void buildMultiTargetSourceId(
            Collection<M> modelList, Collection<T> targetList,
            FunctionActuator<T, I> targetGetSourceId, FunctionActuator<M, I> sourceGetSourceId,
            BiConsumerActuator<M, Collection<T>> sourceSetTargetList
    ) throws RestException {
        if (GeneralUtils.isNotEmpty(targetList)) {
            Map<I, List<T>> sourceIdTargetListMap = new HashMap<>();
            targetListMapSourceId(targetList, sourceIdTargetListMap, targetGetSourceId);
            sourceTargetList(modelList, sourceGetSourceId, sourceIdTargetListMap, sourceSetTargetList);
        }
    }


    /**
     * <code>sourceTarget</code>
     * <p>the target method.</p>
     * @param <I>             {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <M>             {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <T>             {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param modelList       {@link java.util.Collection} <p>the model list parameter is <code>Collection</code> type.</p>
     * @param getIdKey        {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the get id key parameter is <code>FunctionActuator</code> type.</p>
     * @param keyIdTargetMap  {@link java.util.Map} <p>the key id target map parameter is <code>Map</code> type.</p>
     * @param sourceSetTarget {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>the source set target parameter is <code>BiConsumerActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestId
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see java.util.Map
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <I, M extends RestId<I>, T extends RestId<I>> void sourceTarget(
            Collection<M> modelList, FunctionActuator<M, I> getIdKey,
            Map<I, T> keyIdTargetMap, BiConsumerActuator<M, T> sourceSetTarget
    ) throws RestException {
        for (M model : modelList) {
            I keyId = getIdKey.actuate(model);
            if (GeneralUtils.isNotEmpty(keyId)) {
                T target = keyIdTargetMap.get(keyId);
                if (GeneralUtils.isNotEmpty(target)) {
                    sourceSetTarget.actuate(model, target);
                }
            }
        }
    }

    /**
     * <code>sourceTargetList</code>
     * <p>the target list method.</p>
     * @param <I>                 {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <M>                 {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <T>                 {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param modelList           {@link java.util.Collection} <p>the model list parameter is <code>Collection</code> type.</p>
     * @param getIdKey            {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the get id key parameter is <code>FunctionActuator</code> type.</p>
     * @param keyIdTargetListMap  {@link java.util.Map} <p>the key id target list map parameter is <code>Map</code> type.</p>
     * @param sourceSetTargetList {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>the source set target list parameter is <code>BiConsumerActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestId
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see java.util.Map
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <I, M extends RestId<I>, T extends RestId<I>> void sourceTargetList(
            Collection<M> modelList, FunctionActuator<M, I> getIdKey,
            Map<I, List<T>> keyIdTargetListMap, BiConsumerActuator<M, Collection<T>> sourceSetTargetList
    ) throws RestException {
        for (M model : modelList) {
            I keyId = getIdKey.actuate(model);
            if (GeneralUtils.isNotEmpty(keyId)) {
                List<T> targets = keyIdTargetListMap.get(keyId);
                if (GeneralUtils.isNotEmpty(targets)) {
                    sourceSetTargetList.actuate(model, targets);
                }
            }

        }
    }

    /**
     * <code>targetMapTargetId</code>
     * <p>the map target id method.</p>
     * @param <I>               {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <T>               {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param targetList        {@link java.util.Collection} <p>the target list parameter is <code>Collection</code> type.</p>
     * @param targetMap         {@link java.util.Map} <p>the target map parameter is <code>Map</code> type.</p>
     * @param targetGetTargetId {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the target get target id parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestId
     * @see java.util.Collection
     * @see java.util.Map
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <I, T extends RestId<I>> void targetMapTargetId(Collection<T> targetList, Map<I, T> targetMap, FunctionActuator<T, I> targetGetTargetId) throws RestException {
        for (T target : targetList) {
            if (GeneralUtils.isNotEmpty(target) && GeneralUtils.isNotEmpty(targetGetTargetId.actuate(target))) {
                targetMap.putIfAbsent(targetGetTargetId.actuate(target), target);
            }
        }
    }

    /**
     * <code>targetMapSourceId</code>
     * <p>the map source id method.</p>
     * @param <I>               {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <T>               {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param targetList        {@link java.util.Collection} <p>the target list parameter is <code>Collection</code> type.</p>
     * @param targetMap         {@link java.util.Map} <p>the target map parameter is <code>Map</code> type.</p>
     * @param targetGetSourceId {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the target get source id parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestId
     * @see java.util.Collection
     * @see java.util.Map
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <I, T extends RestId<I>> void targetMapSourceId(Collection<T> targetList, Map<I, T> targetMap, FunctionActuator<T, I> targetGetSourceId) throws RestException {
        for (T target : targetList) {
            if (GeneralUtils.isNotEmpty(target) && GeneralUtils.isNotEmpty(targetGetSourceId.actuate(target))) {
                targetMap.putIfAbsent(targetGetSourceId.actuate(target), target);
            }
        }
    }

    /**
     * <code>targetListMapTargetId</code>
     * <p>the list map target id method.</p>
     * @param <I>                     {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <T>                     {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param targetList              {@link java.util.Collection} <p>the target list parameter is <code>Collection</code> type.</p>
     * @param sourceIdTargetListMap   {@link java.util.Map} <p>the source id target list map parameter is <code>Map</code> type.</p>
     * @param sourceIdTargetIdListMap {@link java.util.Map} <p>the source id target id list map parameter is <code>Map</code> type.</p>
     * @param targetGetTargetId       {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the target get target id parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestId
     * @see java.util.Collection
     * @see java.util.Map
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <I, T extends RestId<I>> void targetListMapTargetId(Collection<T> targetList, Map<I, List<T>> sourceIdTargetListMap, Map<I, List<I>> sourceIdTargetIdListMap, FunctionActuator<T, I> targetGetTargetId) throws RestException {
        Map<I, T> targetIdTargetMap = new HashMap<>();
        targetMapTargetId(targetList, targetIdTargetMap, targetGetTargetId);
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
                sourceIdTargetListMap.putIfAbsent(sourceId, targets);
            }
        }
    }

    /**
     * <code>targetListMapSourceId</code>
     * <p>the list map source id method.</p>
     * @param <I>               {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <T>               {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param targetList        {@link java.util.Collection} <p>the target list parameter is <code>Collection</code> type.</p>
     * @param targetListMap     {@link java.util.Map} <p>the target list map parameter is <code>Map</code> type.</p>
     * @param targetGetSourceId {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the target get source id parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestId
     * @see java.util.Collection
     * @see java.util.Map
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <I, T extends RestId<I>> void targetListMapSourceId(Collection<T> targetList, Map<I, List<T>> targetListMap, FunctionActuator<T, I> targetGetSourceId) throws RestException {
        for (T target : targetList) {
            if (GeneralUtils.isNotEmpty(target) && GeneralUtils.isNotEmpty(targetGetSourceId.actuate(target))) {
                I sourceId = targetGetSourceId.actuate(target);
                CollectUtils.collect(sourceId, target, targetListMap);
            }
        }
    }

}
