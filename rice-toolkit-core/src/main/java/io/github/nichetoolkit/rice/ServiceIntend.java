package io.github.nichetoolkit.rice;


import io.github.nichetoolkit.rest.reflect.RestGenericTypes;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.util.LoggerUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanDefinition;

/**
 * <code>ServiceIntend</code>
 * <p>The service intend interface.</p>
 * @param <B> {@link io.github.nichetoolkit.rice.ServiceIntend} <p>The generic parameter is <code>ServiceIntend</code> type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see org.springframework.beans.factory.InitializingBean
 * @since Jdk1.8
 */
public interface ServiceIntend<B extends ServiceIntend<B>> extends InitializingBean {

    @Override
    default void afterPropertiesSet() {
    }

    /**
     * <code>afterAutowirePropertiesSet</code>
     * <p>The after autowire properties set method.</p>
     */
    default void afterAutowirePropertiesSet() {
        LoggerUtils.debug("The service intend bean of [{}] type for named '{}' has be initiated.", beanType().getName(), beanName());
    }

    /**
     * <code>beanScope</code>
     * <p>The bean scope method.</p>
     * @return {@link java.lang.String} <p>The bean scope return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    default String beanScope() {
        return BeanDefinition.SCOPE_SINGLETON;
    }

    /**
     * <code>beanName</code>
     * <p>The bean name method.</p>
     * @return {@link java.lang.String} <p>The bean name return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    default String beanName() {
        return beanName(getClass());
    }

    /**
     * <code>beanType</code>
     * <p>The bean type method.</p>
     * @return {@link java.lang.Class} <p>The bean type return object is <code>Class</code> type.</p>
     * @see java.lang.Class
     */
    default Class<B> beanType() {
        return beanType(getClass());
    }

    /**
     * <code>beanName</code>
     * <p>The bean name method.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.lang.String} <p>The bean name return object is <code>String</code> type.</p>
     * @see java.lang.Class
     * @see java.lang.String
     */
    static String beanName(Class<?> clazz) {
        return GeneralUtils.camelCase(clazz.getSimpleName());
    }

    /**
     * <code>beanType</code>
     * <p>The bean type method.</p>
     * @param <B>   {@link io.github.nichetoolkit.rice.ServiceIntend} <p>The generic parameter is <code>ServiceIntend</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.lang.Class} <p>The bean type return object is <code>Class</code> type.</p>
     * @see java.lang.Class
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings(value = "unchecked")
    static <B extends ServiceIntend<B>> Class<B> beanType(Class<?> clazz) {
        return (Class<B>) RestGenericTypes.resolveClass(RestGenericTypes.resolveType(
                ServiceIntend.class.getTypeParameters()[0], clazz, ServiceIntend.class));
    }

}
