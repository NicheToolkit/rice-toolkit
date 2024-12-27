package io.github.nichetoolkit.rice;


import io.github.nichetoolkit.rest.reflect.RestGenericTypes;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.util.LoggerUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanDefinition;

/**
 * <code>RestServiceFitter</code>
 * <p>The rest service fitter interface.</p>
 * @param <F>  {@link io.github.nichetoolkit.rice.RestServiceFitter} <p>The generic parameter is <code>RestServiceFitter</code> type.</p>
 * @see  org.springframework.beans.factory.InitializingBean
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface RestServiceFitter<F extends RestServiceFitter<F>> extends InitializingBean {

    @Override
    default void afterPropertiesSet() {
    }

    /**
     * <code>afterAutowirePropertiesSet</code>
     * <p>The after autowire properties set method.</p>
     */
    default void afterAutowirePropertiesSet() {
        LoggerUtils.debug("The service fitter bean of [{}] type for named '{}' has be initiated.", beanType().getName(), beanName());
    }

    /**
     * <code>beanScope</code>
     * <p>The bean scope method.</p>
     * @return  {@link java.lang.String} <p>The bean scope return object is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    default String beanScope() {
        return BeanDefinition.SCOPE_SINGLETON;
    }

    /**
     * <code>beanName</code>
     * <p>The bean name method.</p>
     * @return  {@link java.lang.String} <p>The bean name return object is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    default String beanName() {
        return beanName(getClass());
    }

    /**
     * <code>beanType</code>
     * <p>The bean type method.</p>
     * @return  {@link java.lang.Class} <p>The bean type return object is <code>Class</code> type.</p>
     * @see  java.lang.Class
     */
    default Class<F> beanType() {
        return beanType(getClass());
    }

    /**
     * <code>beanName</code>
     * <p>The bean name method.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @see  java.lang.Class
     * @see  java.lang.String
     * @return  {@link java.lang.String} <p>The bean name return object is <code>String</code> type.</p>
     */
    static String beanName(Class<?> clazz) {
        return GeneralUtils.camelCase(clazz.getSimpleName());
    }

    /**
     * <code>beanType</code>
     * <p>The bean type method.</p>
     * @param <F>  {@link io.github.nichetoolkit.rice.RestServiceFitter} <p>The generic parameter is <code>RestServiceFitter</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @see  java.lang.Class
     * @see  java.lang.SuppressWarnings
     * @return  {@link java.lang.Class} <p>The bean type return object is <code>Class</code> type.</p>
     */
    @SuppressWarnings(value = "unchecked")
    static <F extends RestServiceFitter<F>> Class<F> beanType(Class<?> clazz) {
        return (Class<F>) RestGenericTypes.resolveClass(RestGenericTypes.resolveType(
                RestServiceFitter.class.getTypeParameters()[0], clazz, RestServiceFitter.class));
    }

}
