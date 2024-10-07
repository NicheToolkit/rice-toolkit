package io.github.nichetoolkit.rice;


import io.github.nichetoolkit.rest.reflect.RestGenericTypes;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.util.LoggerUtils;
import org.springframework.beans.factory.InitializingBean;

public interface RestServiceIntend<B extends RestServiceIntend<B>> extends InitializingBean {

    @Override
    default void afterPropertiesSet() throws Exception {
        LoggerUtils.debug("The service intend bean of [{}] type for named '{}' has be initiated.", beanType().getName(), beanName());
    }

    default String beanName() {
        return beanName(getClass());
    }

    default Class<B> beanType() {
        return beanType(getClass());
    }

    static String beanName(Class<?> clazz) {
        return GeneralUtils.camelCase(clazz.getSimpleName());
    }

    @SuppressWarnings(value = "unchecked")
    static <B extends RestServiceIntend<B>> Class<B> beanType(Class<?> clazz) {
        return (Class<B>) RestGenericTypes.resolveClass(RestGenericTypes.resolveType(
                RestServiceIntend.class.getTypeParameters()[0], clazz, RestServiceIntend.class));
    }

}
