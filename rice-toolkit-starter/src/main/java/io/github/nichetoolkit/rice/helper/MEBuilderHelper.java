package io.github.nichetoolkit.rice.helper;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.actuator.BiConsumerActuator;
import io.github.nichetoolkit.rest.actuator.ConsumerActuator;
import io.github.nichetoolkit.rest.actuator.FunctionActuator;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.IdEntity;
import io.github.nichetoolkit.rice.IdModel;
import io.github.nichetoolkit.rice.service.SupperService;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>MEConvertHelper</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@SuppressWarnings("MixedMutabilityReturnType")
public class MEBuilderHelper {

    public static <I, M extends IdModel<I>, E extends IdEntity<I>> List<E> entityList(Collection<M> modelList, ConsumerActuator<M> consumer, FunctionActuator<M, E> function) throws RestException {
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

    public static <I, M extends IdModel<I>, E extends IdEntity<I>> List<M> modelList(Collection<E> entityList, FunctionActuator<E, M> function) throws RestException {
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

    public static <I, M extends IdModel<I>, T extends IdModel<I>, E extends IdEntity<I>, S extends SupperService<I, T, E, ?>> void buildSubject(
            E entity, M model, S supperService,
            FunctionActuator<E, I> getSourceIdActuator,
            BiConsumerActuator<M, T> setSourceTargetActuator
    ) throws RestException {
        I sourceId = getSourceIdActuator.actuate(entity);
        T queryById = supperService.queryById(sourceId);
        if (GeneralUtils.isNotEmpty(queryById)) {
            setSourceTargetActuator.actuate(model,queryById);
        }
    }

    public static <I, M extends IdModel<I>, T extends IdModel<I>, E extends IdEntity<I>, S extends SupperService<I, T, E, ?>> void buildSubject(
            List<E> entityList, Collection<M> modelList, S supperService,
            Function<E,I> entityGetTargetIdFunction,
            FunctionActuator<T,I> targetGetTargetIdActuator,
            FunctionActuator<M,I> sourceGetTargetIdActuator,
            BiConsumerActuator<M, T> sourceSetTargetActuator
    ) throws RestException {
        List<I> targetIdList = entityList.stream().filter(GeneralUtils::isNotEmpty).map(entityGetTargetIdFunction).distinct().collect(Collectors.toList());
        if (GeneralUtils.isNotEmpty(targetIdList)) {
            List<T> targetList = supperService.queryAll(targetIdList);
            buildSubject(modelList,targetList,targetGetTargetIdActuator,sourceGetTargetIdActuator,sourceSetTargetActuator);
        }
    }

    public static <I, M extends IdModel<I>, T extends IdModel<I>, E extends IdEntity<I>, S extends SupperService<I, T, E, ?>> void buildSubject(
            List<E> entityList, Collection<M> modelList,
            S supperService,
            Function<E,I> entityGetTargetIdFunction,
            FunctionActuator<T, I> targetGetTargetIdActuator,
            FunctionActuator<M,I> sourceGetTargetIdActuator,
            BiConsumerActuator<M, T> sourceSetTargetActuator,
            Integer index, Boolean... isLoadArray
    ) throws RestException {
        List<I> targetIdList = entityList.stream().filter(GeneralUtils::isNotEmpty).map(entityGetTargetIdFunction).distinct().collect(Collectors.toList());
        if (GeneralUtils.isNotEmpty(targetIdList) && isLoadArray.length > index && isLoadArray[index]) {
            List<T> targetList = supperService.queryAll(targetIdList);
            buildSubject(modelList,targetList,targetGetTargetIdActuator,sourceGetTargetIdActuator,sourceSetTargetActuator);
        }
    }


    public static <I, M extends IdModel<I>, T extends IdModel<I>> void buildSubject(
            Collection<M> modelList,
            Collection<T> targetList,
            FunctionActuator<T, I> targetGetTargetIdActuator,
            FunctionActuator<M,I> sourceGetTargetIdActuator,
            BiConsumerActuator<M, T> sourceSetTargetActuator
    ) throws RestException {
        if (GeneralUtils.isNotEmpty(targetList)) {
            Map<I, T> targetMap = new HashMap<>();
            targetsToMap(targetList,targetMap,targetGetTargetIdActuator);
            for (M model : modelList) {
                I targetId = sourceGetTargetIdActuator.actuate(model);
                T target = targetMap.get(targetId);
                if (GeneralUtils.isNotEmpty(target)) {
                    sourceSetTargetActuator.actuate(model, target);
                }
            }
        }
    }

    public static <I, M extends IdModel<I>, T extends IdModel<I>, E extends IdEntity<I>, S extends SupperService<I, T, E, ?>> void buildSubject(
            List<E> entityList, Collection<M> modelList, S supperService,
            FunctionActuator<T, I> targetGetSourceIdActuator,
            BiConsumerActuator<M, T> sourceSetTargetActuator
    ) throws RestException {
        List<I> targetIdList = entityList.stream().filter(GeneralUtils::isNotEmpty).map(IdEntity::getId).distinct().collect(Collectors.toList());
        if (GeneralUtils.isNotEmpty(targetIdList)) {
            List<T> targetList = supperService.queryAll(targetIdList);
            buildSubject(modelList, targetList, targetGetSourceIdActuator, sourceSetTargetActuator);
        }
    }

    public static <I, M extends IdModel<I>, T extends IdModel<I>, E extends IdEntity<I>, S extends SupperService<I, T, E, ?>> void buildSubject(
            List<E> entityList, Collection<M> modelList,
            S supperService,
            FunctionActuator<T, I> targetGetSourceIdActuator,
            BiConsumerActuator<M, T> sourceSetTargetActuator,
            Integer index, Boolean... isLoadArray
    ) throws RestException {
        List<I> targetIdList = entityList.stream().filter(GeneralUtils::isNotEmpty).map(IdEntity::getId).distinct().collect(Collectors.toList());
        if (GeneralUtils.isNotEmpty(targetIdList) && isLoadArray.length > index && isLoadArray[index]) {
            List<T> targetList = supperService.queryAll(targetIdList);
            buildSubject(modelList, targetList, targetGetSourceIdActuator, sourceSetTargetActuator);
        }
    }

    public static <I, M extends IdModel<I>, T extends IdModel<I>> void buildSubject(
            Collection<M> modelList,
            Collection<T> targetList,
            FunctionActuator<T, I> targetGetSourceIdActuator,
            BiConsumerActuator<M, T> sourceSetTargetActuator
    ) throws RestException {
        if (GeneralUtils.isNotEmpty(targetList)) {
            Map<I, T> targetMap = new HashMap<>();
            targetsToMap(targetList,targetMap,targetGetSourceIdActuator);
            for (M model : modelList) {
                I modelId = model.getId();
                T target = targetMap.get(modelId);
                if (GeneralUtils.isNotEmpty(target)) {
                    sourceSetTargetActuator.actuate(model, target);
                }
            }
        }
    }


    public static <I, T extends IdModel<I>> void targetsToMap(Collection<T> targetList, Map<I, T> targetMap, FunctionActuator<T, I> targetGetSourceIdActuator) throws RestException {
        for (T target : targetList) {
            if (GeneralUtils.isNotEmpty(target) && GeneralUtils.isNotEmpty(targetGetSourceIdActuator.actuate(target))) {
                targetMap.putIfAbsent(targetGetSourceIdActuator.actuate(target), target);
            }
        }
    }


    public static <I, M extends IdModel<I>, T extends IdModel<I>, E extends IdEntity<I>, S extends SupperService<I, T, E, ?>> void buildSubjects(
            List<E> entityList, Collection<M> modelList, S supperService,
            FunctionActuator<T, I> targetGetSourceIdActuator,
            BiConsumerActuator<M, List<T>> sourceSetTargetActuator
    ) throws RestException {
        List<I> targetIdList = entityList.stream().filter(GeneralUtils::isNotEmpty).map(IdEntity::getId).distinct().collect(Collectors.toList());
        if (GeneralUtils.isNotEmpty(targetIdList)) {
            List<T> targetList = supperService.queryAll(targetIdList);
            buildSubjects(modelList, targetList, targetGetSourceIdActuator, sourceSetTargetActuator);
        }
    }


    public static <I, M extends IdModel<I>, T extends IdModel<I>, E extends IdEntity<I>, S extends SupperService<I, T, E, ?>> void buildSubjects(
            List<E> entityList, Collection<M> modelList,
            S supperService,
            FunctionActuator<T, I> targetGetSourceIdActuator,
            BiConsumerActuator<M, List<T>> sourceSetTargetActuator,
            Integer index, Boolean... isLoadArray
    ) throws RestException {
        List<I> targetIdList = entityList.stream().filter(GeneralUtils::isNotEmpty).map(IdEntity::getId).distinct().collect(Collectors.toList());
        if (GeneralUtils.isNotEmpty(targetIdList) && isLoadArray.length > index && isLoadArray[index]) {
            List<T> targetList = supperService.queryAll(targetIdList);
            buildSubjects(modelList, targetList, targetGetSourceIdActuator, sourceSetTargetActuator);
        }
    }

    public static <I, M extends IdModel<I>, T extends IdModel<I>> void buildSubjects(
            Collection<M> modelList,
            Collection<T> targetList,
            FunctionActuator<T, I> targetGetSourceIdActuator,
            BiConsumerActuator<M, List<T>> sourceSetTargetActuator
    ) throws RestException {
        if (GeneralUtils.isNotEmpty(targetList)) {
            Map<I, List<T>> targetListMap = new HashMap<>();
            for (T target : targetList) {
                if (GeneralUtils.isNotEmpty(target) && GeneralUtils.isNotEmpty(targetGetSourceIdActuator.actuate(target))) {
                    targetListMap.computeIfAbsent(targetGetSourceIdActuator.actuate(target), k -> new ArrayList<>()).add(target);
                }
            }
            for (M model : modelList) {
                I modelId = model.getId();
                List<T> targets = targetListMap.get(modelId);
                if (GeneralUtils.isNotEmpty(targets)) {
                    sourceSetTargetActuator.actuate(model, targets);
                }
            }
        }
    }

}
