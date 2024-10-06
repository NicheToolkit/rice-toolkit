package io.github.nichetoolkit.rice.prepare;


import io.github.nichetoolkit.rest.reflect.RestGenericTypes;
import io.github.nichetoolkit.rest.util.GeneralUtils;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface RestPrepareResource<B extends RestPrepareResource<B>> {

    static Collection<Class<?>> beanTypes() {
        return Instance.BEAN_TYPES.values();
    }

    default String beanName() {
        return GeneralUtils.camelCase(getClass().getSimpleName());
    }

    @SuppressWarnings(value = "unchecked")
    default Class<B> beanType() {
        return (Class<B>) Instance.beanType(getClass());
    }

    class Instance {

        private static final Map<Class<?>, Class<?>> BEAN_TYPES = new ConcurrentHashMap<>();

        private static Class<?> beanType(Class<?> clazz) {
            if (!BEAN_TYPES.containsKey(clazz)) {
                BEAN_TYPES.put(clazz, RestGenericTypes.resolveClass(RestGenericTypes.resolveType(
                        RestPrepareResource.class.getTypeParameters()[0], clazz, RestPrepareResource.class)));
            }
            return BEAN_TYPES.get(clazz);
        }
    }

}
