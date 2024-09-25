package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.reflect.RestGenericTypes;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface RestIdResolver<I>{
    @SuppressWarnings(value = "unchecked")
    default Class<I> clazzOfId() {
        return (Class<I>) CachingIdClass.clazz(getClass());
    }

    I resolveId() throws RestException;

    <M extends RestId<I>> I resolveId(M model) throws RestException;

    default <M extends RestId<I>> M resolveModel(M model) throws RestException {
        I id = resolveId();
        model.setId(id);
        return model;
    }

    class CachingIdClass {
        static Map<Class<?>, Class<?>> CLASS_MAP = new ConcurrentHashMap<>();

        private static Class<?> clazz(Class<?> clazz) {
            if (!CLASS_MAP.containsKey(clazz)) {
                CLASS_MAP.put(clazz, RestGenericTypes.resolveClass(RestGenericTypes.resolveType(
                        RestIdResolver.class.getTypeParameters()[0], clazz, RestIdResolver.class)));
            }
            return CLASS_MAP.get(clazz);
        }
    }
}
