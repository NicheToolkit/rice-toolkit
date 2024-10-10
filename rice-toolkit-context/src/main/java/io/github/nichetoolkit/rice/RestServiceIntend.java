package io.github.nichetoolkit.rice;


import io.github.nichetoolkit.rest.reflect.RestGenericTypes;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.util.LoggerUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanDefinition;

/**
 * <code>RestServiceIntend</code>
 * <p>The type rest service intend interface.</p>
 * @param <B> {@link io.github.nichetoolkit.rice.RestServiceIntend} <p>The generic parameter is <code>RestServiceIntend</code> type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see org.springframework.beans.factory.InitializingBean
 * @since Jdk1.8
 */
public interface RestServiceIntend<B extends RestServiceIntend<B>> extends InitializingBean {

    @Override
    default void afterPropertiesSet() throws Exception {
    }

    /**
     * <code>afterAutowirePropertiesSet</code>
     * <p>The autowire properties set method.</p>
     */
    default void afterAutowirePropertiesSet() {
        LoggerUtils.debug("The service intend bean of [{}] type for named '{}' has be initiated.", beanType().getName(), beanName());
    }

    /**
     * <code>beanScope</code>
     * <p>The scope method.</p>
     * @return {@link java.lang.String} <p>The scope return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    default String beanScope() {
        return BeanDefinition.SCOPE_SINGLETON;
    }

    /**
     * <code>beanName</code>
     * <p>The name method.</p>
     * @return {@link java.lang.String} <p>The name return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    default String beanName() {
        return beanName(getClass());
    }

    /**
     * <code>beanType</code>
     * <p>The type method.</p>
     * @return {@link java.lang.Class} <p>The type return object is <code>Class</code> type.</p>
     * @see java.lang.Class
     */
    default Class<B> beanType() {
        return beanType(getClass());
    }

    /**
     * <code>beanName</code>
     * <p>The name method.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.lang.String} <p>The name return object is <code>String</code> type.</p>
     * @see java.lang.Class
     * @see java.lang.String
     */
    static String beanName(Class<?> clazz) {
        return GeneralUtils.camelCase(clazz.getSimpleName());
    }

    /**
     * <code>beanType</code>
     * <p>The type method.</p>
     * @param <B>   {@link io.github.nichetoolkit.rice.RestServiceIntend} <p>The generic parameter is <code>RestServiceIntend</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.lang.Class} <p>The type return object is <code>Class</code> type.</p>
     * @see java.lang.Class
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings(value = "unchecked")
    static <B extends RestServiceIntend<B>> Class<B> beanType(Class<?> clazz) {
        return (Class<B>) RestGenericTypes.resolveClass(RestGenericTypes.resolveType(
                RestServiceIntend.class.getTypeParameters()[0], clazz, RestServiceIntend.class));
    }

}
