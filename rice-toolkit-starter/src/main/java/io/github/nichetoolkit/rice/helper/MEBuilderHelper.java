package io.github.nichetoolkit.rice.helper;

import com.google.common.collect.Lists;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.actuator.BiConsumerActuator;
import io.github.nichetoolkit.rest.actuator.ConsumerActuator;
import io.github.nichetoolkit.rest.actuator.FunctionActuator;
import io.github.nichetoolkit.rest.util.CollectUtils;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.util.PartitionUtils;
import io.github.nichetoolkit.rice.RestId;
import io.github.nichetoolkit.rice.RestId;
import io.github.nichetoolkit.rice.RestId;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@SuppressWarnings("MixedMutabilityReturnType")
public class MEBuilderHelper {

    public static <I, T extends RestId<I>> List<T> partition(Collection<I> targetIdList, FunctionActuator<Collection<I>, List<T>> targetListQueryByIdList) throws RestException {
        Integer partitionSize = RiceContextHolder.beanProperties().getPartitionQuery();
        return partition(targetIdList,partitionSize,targetListQueryByIdList);
    }

    public static <I, T extends RestId<I>> List<T> partition(Collection<I> targetIdList, Integer partition,  FunctionActuator<Collection<I>, List<T>> targetListQueryByIdList) throws RestException {
        Set<I> targetIdSet = new HashSet<>(targetIdList);
        if (GeneralUtils.isNotEmpty(partition) && targetIdList.size() > partition) {
            Set<T> targetList = new LinkedHashSet<>();
            List<List<I>> partitionList =  Lists.partition(new ArrayList<>(targetIdSet), partition);
            for (List<I> partitions : partitionList) {
                List<T> targets = targetListQueryByIdList.actuate(partitions);
                targetList.addAll(targets);
            }
            return new ArrayList<>(targetList);
        } else {
            return targetListQueryByIdList.actuate(targetIdSet);
        }
    }


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

    public static <I, M extends RestId<I>, T extends RestId<I>, E extends RestId<I>> void buildSingleTargetId(
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

    public static <I, M extends RestId<I>, T extends RestId<I>, E extends RestId<I>> void buildSingleTargetId(
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

    public static <I, M extends RestId<I>, T extends RestId<I>, E extends RestId<I>> void buildSingleSourceId(
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

    public static <I, M extends RestId<I>, T extends RestId<I>, E extends RestId<I>> void buildSingleSourceId(
            E entity, M model, FunctionActuator<I,T> targetQueryBySourceId,
            BiConsumerActuator<M, T> sourceSetTarget
    ) throws RestException {
        I sourceId = entity.getId();
        T target = targetQueryBySourceId.actuate(sourceId);
        if (GeneralUtils.isNotEmpty(target)) {
            sourceSetTarget.actuate(model,target);
        }
    }

    public static <I, M extends RestId<I>, T extends RestId<I>, E extends RestId<I>> void buildSingleSourceId(
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

    public static <I, M extends RestId<I>, T extends RestId<I>, E extends RestId<I>> void buildSingleSourceId(
            E entity, M model, FunctionActuator<I,T> targetQueryBySourceId,
            FunctionActuator<E, I> entityGetSourceId,
            BiConsumerActuator<M, T> sourceSetTarget
    ) throws RestException {
        I sourceId = entityGetSourceId.actuate(entity);
        if (GeneralUtils.isNotEmpty(sourceId)) {
            T target = targetQueryBySourceId.actuate(sourceId);
            if (GeneralUtils.isNotEmpty(target)) {
                sourceSetTarget.actuate(model,target);
            }
        }

    }


    public static <I, M extends RestId<I>, T extends RestId<I>> void buildMultiTargetId(
            M model, FunctionActuator<Collection<I>, List<T>> targetListQueryByTargetIdList,
            FunctionActuator<M, List<I>> sourceGetTargetIdList,
            BiConsumerActuator<M, Collection<T>> sourceSetTargetList,
            Integer index, Boolean... isLoadArray
    ) throws RestException {
        List<I> targetIdList = sourceGetTargetIdList.actuate(model);
        if (GeneralUtils.isNotEmpty(targetIdList) && isLoadArray.length > index && isLoadArray[index]) {
            List<T> targetList = partition(targetIdList,targetListQueryByTargetIdList);
            if (GeneralUtils.isNotEmpty(targetList)) {
                sourceSetTargetList.actuate(model,targetList);
            }
        }
    }

    public static <I, M extends RestId<I>, T extends RestId<I>> void buildMultiTargetId(
            M model, FunctionActuator<Collection<I>, List<T>> targetListQueryByTargetIdList,
            FunctionActuator<M, List<I>> sourceGetTargetIdList,
            BiConsumerActuator<M, Collection<T>> sourceSetTargetList
    ) throws RestException {
        List<I> targetIdList = sourceGetTargetIdList.actuate(model);
        List<T> targetList = partition(targetIdList,targetListQueryByTargetIdList);
        if (GeneralUtils.isNotEmpty(targetList)) {
            sourceSetTargetList.actuate(model,targetList);
        }
    }

    public static <I, M extends RestId<I>, T extends RestId<I>, E extends RestId<I>> void buildMultiSourceId(
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

    public static <I, M extends RestId<I>, T extends RestId<I>, E extends RestId<I>> void buildMultiSourceId(
            E entity, M model, FunctionActuator<I, List<T>> targetListQueryBySourceId,
            BiConsumerActuator<M, Collection<T>> sourceSetTargetList
    ) throws RestException {
        I sourceId = entity.getId();
        List<T> targetList = targetListQueryBySourceId.actuate(sourceId);
        if (GeneralUtils.isNotEmpty(targetList)) {
            sourceSetTargetList.actuate(model,targetList);
        }
    }

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
                sourceSetTargetList.actuate(model,targetList);
            }
        }
    }

    public static <I, M extends RestId<I>, T extends RestId<I>, E extends RestId<I>> void buildMultiSourceId(
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


    public static <I, M extends RestId<I>, T extends RestId<I>, E extends RestId<I>> void buildSingleTargetId(
            Collection<E> entityList, Collection<M> modelList, FunctionActuator<Collection<I>, List<T>> targetListQueryByTargetIdList,
            Function<E,I> entityGetTargetId, FunctionActuator<M,I> sourceGetTargetId, BiConsumerActuator<M, T> sourceSetTarget,
            Integer index, Boolean... isLoadArray
    ) throws RestException {
        List<I> targetIdList = entityList.stream().filter(GeneralUtils::isNotEmpty).map(entityGetTargetId).filter(GeneralUtils::isNotEmpty).distinct().collect(Collectors.toList());
        if (GeneralUtils.isNotEmpty(targetIdList) && isLoadArray.length > index && isLoadArray[index]) {
            List<T> targetList = partition(targetIdList,targetListQueryByTargetIdList);
            buildSingleTargetTargetId(modelList,targetList,sourceGetTargetId,sourceSetTarget);
        }
    }

    public static <I, M extends RestId<I>, T extends RestId<I>, E extends RestId<I>> void buildSingleTargetId(
            Collection<E> entityList, Collection<M> modelList, FunctionActuator<Collection<I>, List<T>> targetListQueryByTargetIdList,
            Function<E,I> entityGetTargetId, FunctionActuator<M,I> sourceGetTargetId, BiConsumerActuator<M, T> sourceSetTarget
    ) throws RestException {
        List<I> targetIdList = entityList.stream().filter(GeneralUtils::isNotEmpty).map(entityGetTargetId).filter(GeneralUtils::isNotEmpty).distinct().collect(Collectors.toList());
        if (GeneralUtils.isNotEmpty(targetIdList)) {
            List<T> targetList = partition(targetIdList,targetListQueryByTargetIdList);
            buildSingleTargetTargetId(modelList,targetList,sourceGetTargetId,sourceSetTarget);
        }
    }

    public static <I, M extends RestId<I>, T extends RestId<I>, E extends RestId<I>> void buildSingleTargetId(
            Collection<E> entityList, Collection<M> modelList, FunctionActuator<Collection<I>, List<T>> targetListQueryByTargetIdList,
            Function<E,I> entityGetTargetId, FunctionActuator<T,I> targetGetTargetId,
            FunctionActuator<M,I> sourceGetTargetId, BiConsumerActuator<M, T> sourceSetTarget,
            Integer index, Boolean... isLoadArray
    ) throws RestException {
        List<I> targetIdList = entityList.stream().filter(GeneralUtils::isNotEmpty).map(entityGetTargetId).filter(GeneralUtils::isNotEmpty).distinct().collect(Collectors.toList());
        if (GeneralUtils.isNotEmpty(targetIdList) && isLoadArray.length > index && isLoadArray[index]) {
            List<T> targetList = partition(targetIdList,targetListQueryByTargetIdList);
            buildSingleTargetTargetId(modelList,targetList,targetGetTargetId,sourceGetTargetId,sourceSetTarget);
        }
    }

    public static <I, M extends RestId<I>, T extends RestId<I>, E extends RestId<I>> void buildSingleTargetId(
            Collection<E> entityList, Collection<M> modelList, FunctionActuator<Collection<I>, List<T>> targetListQueryByTargetIdList,
            Function<E,I> entityGetTargetId, FunctionActuator<T,I> targetGetTargetId,
            FunctionActuator<M,I> sourceGetTargetId, BiConsumerActuator<M, T> sourceSetTarget
    ) throws RestException {
        List<I> targetIdList = entityList.stream().filter(GeneralUtils::isNotEmpty).map(entityGetTargetId).filter(GeneralUtils::isNotEmpty).distinct().collect(Collectors.toList());
        if (GeneralUtils.isNotEmpty(targetIdList)) {
            List<T> targetList = partition(targetIdList,targetListQueryByTargetIdList);
            buildSingleTargetTargetId(modelList,targetList,targetGetTargetId,sourceGetTargetId,sourceSetTarget);
        }
    }

    public static <I, M extends RestId<I>, T extends RestId<I>, E extends RestId<I>> void buildSingleSourceId(
            Collection<E> entityList, Collection<M> modelList, FunctionActuator<Collection<I>, List<T>> targetListQueryBySourceIdList,
            FunctionActuator<T,I> targetGetSourceId, BiConsumerActuator<M, T> sourceSetTarget,
            Integer index, Boolean... isLoadArray
    ) throws RestException {
        List<I> sourceIdList = entityList.stream().filter(GeneralUtils::isNotEmpty).map(RestId::getId).filter(GeneralUtils::isNotEmpty).distinct().collect(Collectors.toList());
        if (GeneralUtils.isNotEmpty(sourceIdList) && isLoadArray.length > index && isLoadArray[index]) {
            List<T> targetList = partition(sourceIdList,targetListQueryBySourceIdList);
            buildSingleTargetSourceId(modelList,targetList,targetGetSourceId,sourceSetTarget);
        }
    }

    public static <I, M extends RestId<I>, T extends RestId<I>, E extends RestId<I>> void buildSingleSourceId(
            Collection<E> entityList, Collection<M> modelList, FunctionActuator<Collection<I>, List<T>> targetListQueryBySourceIdList,
            FunctionActuator<T,I> targetGetSourceId, BiConsumerActuator<M, T> sourceSetTarget
    ) throws RestException {
        List<I> sourceIdList = entityList.stream().filter(GeneralUtils::isNotEmpty).map(RestId::getId).filter(GeneralUtils::isNotEmpty).distinct().collect(Collectors.toList());
        if (GeneralUtils.isNotEmpty(sourceIdList)) {
            List<T> targetList = partition(sourceIdList,targetListQueryBySourceIdList);
            buildSingleTargetSourceId(modelList,targetList,targetGetSourceId,sourceSetTarget);
        }
    }

    public static <I, M extends RestId<I>, T extends RestId<I>, E extends RestId<I>> void buildSingleSourceId(
            Collection<E> entityList, Collection<M> modelList, FunctionActuator<Collection<I>, List<T>> targetListQueryBySourceIdList,
            Function<E,I> entityGetSourceId, FunctionActuator<T,I> targetGetSourceId,
            FunctionActuator<M,I> sourceGetSourceId, BiConsumerActuator<M, T> sourceSetTarget,
            Integer index, Boolean... isLoadArray
    ) throws RestException {
        List<I> sourceIdList = entityList.stream().filter(GeneralUtils::isNotEmpty).map(entityGetSourceId).filter(GeneralUtils::isNotEmpty).distinct().collect(Collectors.toList());
        if (GeneralUtils.isNotEmpty(sourceIdList) && isLoadArray.length > index && isLoadArray[index]) {
            List<T> targetList = partition(sourceIdList,targetListQueryBySourceIdList);
            buildSingleTargetSourceId(modelList,targetList,targetGetSourceId,sourceGetSourceId,sourceSetTarget);
        }
    }

    public static <I, M extends RestId<I>, T extends RestId<I>, E extends RestId<I>> void buildSingleSourceId(
            Collection<E> entityList, Collection<M> modelList, FunctionActuator<Collection<I>, List<T>> targetListQueryBySourceIdList,
            Function<E,I> entityGetSourceId, FunctionActuator<T,I> targetGetSourceId,
            FunctionActuator<M,I> sourceGetSourceId, BiConsumerActuator<M, T> sourceSetTarget
    ) throws RestException {
        List<I> sourceIdList = entityList.stream().filter(GeneralUtils::isNotEmpty).map(entityGetSourceId).filter(GeneralUtils::isNotEmpty).distinct().collect(Collectors.toList());
        if (GeneralUtils.isNotEmpty(sourceIdList)) {
            List<T> targetList = partition(sourceIdList,targetListQueryBySourceIdList);
            buildSingleTargetSourceId(modelList,targetList,targetGetSourceId,sourceGetSourceId,sourceSetTarget);
        }
    }

    public static <I, M extends RestId<I>, T extends RestId<I>> void buildMultiTargetId(
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
            List<T> targetList = partition(targetIdSet,targetListQueryByTargetIdList);
            buildMultiTargetTargetId(modelList,targetList,sourceIdTargetIdListMap,sourceSetTargetList);
        }
    }

    public static <I, M extends RestId<I>, T extends RestId<I>> void buildMultiTargetId(
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
            List<T> targetList = partition(targetIdSet,targetListQueryByTargetIdList);
            buildMultiTargetTargetId(modelList,targetList,sourceIdTargetIdListMap,sourceSetTargetList);
        }
    }

    public static <I, M extends RestId<I>, T extends RestId<I>> void buildMultiTargetId(
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
            List<T> targetList = partition(targetIdSet,targetListQueryByTargetIdList);
            buildMultiTargetTargetId(modelList,targetList,sourceIdTargetIdListMap,targetGetTargetId,sourceGetSourceId,sourceSetTargetList);
        }
    }

    public static <I, M extends RestId<I>, T extends RestId<I>> void buildMultiTargetId(
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
            List<T> targetList = partition(targetIdSet,targetListQueryByTargetIdList);
            buildMultiTargetTargetId(modelList,targetList,sourceIdTargetIdListMap,targetGetTargetId,sourceGetSourceId,sourceSetTargetList);
        }
    }

    public static <I, M extends RestId<I>, T extends RestId<I>,E extends RestId<I>> void buildMultiSourceId(
            Collection<E> entityList,Collection<M> modelList, FunctionActuator<Collection<I>, List<T>> targetListQueryBySourceIdList,
            FunctionActuator<T,I> targetGetSourceId, BiConsumerActuator<M, Collection<T>> sourceSetTargetList,
            Integer index, Boolean... isLoadArray
    ) throws RestException {
        List<I> sourceIdList = entityList.stream().filter(GeneralUtils::isNotEmpty).map(RestId::getId).distinct().collect(Collectors.toList());
        if (GeneralUtils.isNotEmpty(sourceIdList) && isLoadArray.length > index && isLoadArray[index]) {
            List<T> targetList = partition(sourceIdList,targetListQueryBySourceIdList);
            buildMultiTargetSourceId(modelList,targetList,targetGetSourceId,sourceSetTargetList);
        }
    }

    public static <I, M extends RestId<I>, T extends RestId<I>,E extends RestId<I>> void buildMultiSourceId(
            Collection<E> entityList,Collection<M> modelList, FunctionActuator<Collection<I>, List<T>> targetListQueryBySourceIdList,
            FunctionActuator<T,I> targetGetSourceId, FunctionActuator<M,I> sourceGetSourceId,
            BiConsumerActuator<M, Collection<T>> sourceSetTargetList,
            Integer index, Boolean... isLoadArray
    ) throws RestException {
        List<I> sourceIdList = entityList.stream().filter(GeneralUtils::isNotEmpty).map(RestId::getId).filter(GeneralUtils::isNotEmpty).distinct().collect(Collectors.toList());
        if (GeneralUtils.isNotEmpty(sourceIdList) && isLoadArray.length > index && isLoadArray[index]) {
            List<T> targetList = partition(sourceIdList,targetListQueryBySourceIdList);
            buildMultiTargetSourceId(modelList,targetList,targetGetSourceId,sourceGetSourceId,sourceSetTargetList);
        }
    }

    public static <I, M extends RestId<I>, T extends RestId<I>,E extends RestId<I>> void buildMultiSourceId(
            Collection<E> entityList,Collection<M> modelList, FunctionActuator<Collection<I>, List<T>> targetListQueryBySourceIdList,
            FunctionActuator<T,I> targetGetSourceId, FunctionActuator<M,I> sourceGetTargetId, BiConsumerActuator<M, Collection<T>> sourceSetTargetList
    ) throws RestException {
        List<I> sourceIdList = entityList.stream().filter(GeneralUtils::isNotEmpty).map(RestId::getId).filter(GeneralUtils::isNotEmpty).distinct().collect(Collectors.toList());
        if (GeneralUtils.isNotEmpty(sourceIdList)) {
            List<T> targetList = partition(sourceIdList,targetListQueryBySourceIdList);
            buildMultiTargetSourceId(modelList,targetList,targetGetSourceId,sourceGetTargetId,sourceSetTargetList);
        }
    }

    public static <I, M extends RestId<I>, T extends RestId<I>,E extends RestId<I>> void buildMultiSourceId(
            Collection<E> entityList,Collection<M> modelList, FunctionActuator<Collection<I>, List<T>> targetListQueryBySourceIdList,
            Function<E,I> entityGetSourceId, FunctionActuator<T,I> targetGetSourceId,
            FunctionActuator<M,I> sourceGetTargetId, BiConsumerActuator<M, Collection<T>> sourceSetTargetList,
            Integer index, Boolean... isLoadArray
    ) throws RestException {
        List<I> sourceIdList = entityList.stream().filter(GeneralUtils::isNotEmpty).map(entityGetSourceId).filter(GeneralUtils::isNotEmpty).distinct().collect(Collectors.toList());
        if (GeneralUtils.isNotEmpty(sourceIdList) && isLoadArray.length > index && isLoadArray[index]) {
            List<T> targetList = partition(sourceIdList,targetListQueryBySourceIdList);
            buildMultiTargetSourceId(modelList,targetList,targetGetSourceId,sourceGetTargetId,sourceSetTargetList);
        }
    }

    public static <I, M extends RestId<I>, T extends RestId<I>,E extends RestId<I>> void buildMultiSourceId(
            Collection<E> entityList,Collection<M> modelList, FunctionActuator<Collection<I>, List<T>> targetListQueryBySourceIdList,
            Function<E,I> entityGetSourceId, FunctionActuator<T,I> targetGetSourceId,
            FunctionActuator<M,I> sourceGetTargetId, BiConsumerActuator<M, Collection<T>> sourceSetTargetList
    ) throws RestException {
        List<I> sourceIdList = entityList.stream().filter(GeneralUtils::isNotEmpty).map(entityGetSourceId).filter(GeneralUtils::isNotEmpty).distinct().collect(Collectors.toList());
        if (GeneralUtils.isNotEmpty(sourceIdList)) {
            List<T> targetList = partition(sourceIdList,targetListQueryBySourceIdList);
            buildMultiTargetSourceId(modelList,targetList,targetGetSourceId,sourceGetTargetId,sourceSetTargetList);
        }
    }


    public static <I, M extends RestId<I>, T extends RestId<I>> void buildSingleTargetTargetId(
            Collection<M> modelList, Collection<T> targetList, FunctionActuator<M,I> sourceGetTargetId, BiConsumerActuator<M, T> sourceSetTarget
    ) throws RestException {
        if (GeneralUtils.isNotEmpty(targetList)) {
            Map<I, T> targetIdTargetMap = new HashMap<>();
            targetMapTargetId(targetList,targetIdTargetMap,RestId::getId);
            sourceTarget(modelList,sourceGetTargetId,targetIdTargetMap,sourceSetTarget);
        }
    }

    public static <I, M extends RestId<I>, T extends RestId<I>> void buildSingleTargetTargetId(
            Collection<M> modelList, Collection<T> targetList, FunctionActuator<T, I> targetGetTargetId,
            FunctionActuator<M,I> sourceGetTargetId, BiConsumerActuator<M, T> sourceSetTarget
    ) throws RestException {
        if (GeneralUtils.isNotEmpty(targetList)) {
            Map<I, T> targetIdTargetMap = new HashMap<>();
            targetMapTargetId(targetList,targetIdTargetMap,targetGetTargetId);
            sourceTarget(modelList,sourceGetTargetId,targetIdTargetMap,sourceSetTarget);
        }
    }

    public static <I, M extends RestId<I>, T extends RestId<I>> void buildSingleTargetSourceId(
            Collection<M> modelList, Collection<T> targetList, FunctionActuator<T, I> targetGetSourceId,
            BiConsumerActuator<M, T> sourceSetTarget
    ) throws RestException {
        if (GeneralUtils.isNotEmpty(targetList)) {
            Map<I, T> sourceIdTargetMap = new HashMap<>();
            targetMapSourceId(targetList,sourceIdTargetMap,targetGetSourceId);
            sourceTarget(modelList,RestId::getId,sourceIdTargetMap,sourceSetTarget);
        }
    }

    public static <I, M extends RestId<I>, T extends RestId<I>> void buildSingleTargetSourceId(
            Collection<M> modelList, Collection<T> targetList, FunctionActuator<T, I> targetGetSourceId,
            FunctionActuator<M,I> sourceGetSourceId, BiConsumerActuator<M, T> sourceSetTarget
    ) throws RestException {
        if (GeneralUtils.isNotEmpty(targetList)) {
            Map<I, T> sourceIdTargetMap = new HashMap<>();
            targetMapSourceId(targetList,sourceIdTargetMap,targetGetSourceId);
            sourceTarget(modelList,sourceGetSourceId,sourceIdTargetMap,sourceSetTarget);
        }
    }

    public static <I, M extends RestId<I>, T extends RestId<I>> void buildMultiTargetTargetId(
            Collection<M> modelList, Collection<T> targetList, Map<I, List<I>> sourceIdTargetIdListMap,
            BiConsumerActuator<M, Collection<T>> sourceSetTargetList
    ) throws RestException {
        if (GeneralUtils.isNotEmpty(targetList)) {
            Map<I, List<T>> sourceIdTargetListMap = new HashMap<>();
            targetListMapTargetId(targetList,sourceIdTargetListMap,sourceIdTargetIdListMap,RestId::getId);
            sourceTargetList(modelList,RestId::getId,sourceIdTargetListMap,sourceSetTargetList);
        }
    }

    public static <I, M extends RestId<I>, T extends RestId<I>> void buildMultiTargetTargetId(
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

    public static <I, M extends RestId<I>, T extends RestId<I>> void buildMultiTargetSourceId(
            Collection<M> modelList, Collection<T> targetList,
            FunctionActuator<T, I> targetGetSourceId, BiConsumerActuator<M, Collection<T>> sourceSetTargetList
    ) throws RestException {
        if (GeneralUtils.isNotEmpty(targetList)) {
            Map<I, List<T>> sourceIdTargetListMap = new HashMap<>();
            targetListMapSourceId(targetList,sourceIdTargetListMap,targetGetSourceId);
            sourceTargetList(modelList,RestId::getId,sourceIdTargetListMap,sourceSetTargetList);
        }
    }

    public static <I, M extends RestId<I>, T extends RestId<I>> void buildMultiTargetSourceId(
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


    public static <I, M extends RestId<I>, T extends RestId<I>> void sourceTarget(
            Collection<M> modelList, FunctionActuator<M,I> getIdKey,
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

    public static <I, M extends RestId<I>, T extends RestId<I>> void sourceTargetList(
            Collection<M> modelList, FunctionActuator<M,I> getIdKey,
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

    public static <I, T extends RestId<I>> void targetMapTargetId(Collection<T> targetList, Map<I, T> targetMap, FunctionActuator<T, I> targetGetTargetId) throws RestException {
        for (T target : targetList) {
            if (GeneralUtils.isNotEmpty(target) && GeneralUtils.isNotEmpty(targetGetTargetId.actuate(target))) {
                targetMap.putIfAbsent(targetGetTargetId.actuate(target), target);
            }
        }
    }

    public static <I, T extends RestId<I>> void targetMapSourceId(Collection<T> targetList, Map<I, T> targetMap, FunctionActuator<T, I> targetGetSourceId) throws RestException {
        for (T target : targetList) {
            if (GeneralUtils.isNotEmpty(target) && GeneralUtils.isNotEmpty(targetGetSourceId.actuate(target))) {
                targetMap.putIfAbsent(targetGetSourceId.actuate(target), target);
            }
        }
    }

    public static <I, T extends RestId<I>> void targetListMapTargetId(Collection<T> targetList, Map<I, List<T>> sourceIdTargetListMap,Map<I, List<I>> sourceIdTargetIdListMap, FunctionActuator<T, I> targetGetTargetId) throws RestException {
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

    public static <I, T extends RestId<I>> void targetListMapSourceId(Collection<T> targetList, Map<I, List<T>> targetListMap, FunctionActuator<T, I> targetGetSourceId) throws RestException {
        for (T target : targetList) {
            if (GeneralUtils.isNotEmpty(target) && GeneralUtils.isNotEmpty(targetGetSourceId.actuate(target))) {
                I sourceId = targetGetSourceId.actuate(target);
                CollectUtils.collect(sourceId,target,targetListMap);
            }
        }
    }

}
