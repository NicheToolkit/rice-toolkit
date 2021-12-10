package io.github.nichetoolkit.rice.helper;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.actuator.ConsumerActuator;
import io.github.nichetoolkit.rest.actuator.FunctionActuator;
import io.github.nichetoolkit.rest.util.common.GeneralUtils;
import io.github.nichetoolkit.rice.IdEntity;
import io.github.nichetoolkit.rice.IdModel;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>MEConvertHelper</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class MEBuilderHelper {

    public static <I,M extends IdModel<I>, E extends IdEntity<I>> List<E> entityList(Collection<M> modelList, ConsumerActuator<M> consumer, FunctionActuator<M,E> function) throws RestException {
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

    public static<I,M extends IdModel<I>, E extends IdEntity<I>> List<M> modelList(Collection<E> entityList,FunctionActuator<E,M> function) throws RestException {
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
    
}
