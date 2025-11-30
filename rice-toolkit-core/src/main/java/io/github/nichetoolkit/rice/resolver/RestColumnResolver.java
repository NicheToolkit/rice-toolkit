package io.github.nichetoolkit.rice.resolver;

import io.github.nichetoolkit.mybatis.enums.StyleType;
import io.github.nichetoolkit.rest.RestException;
import org.springframework.beans.factory.InitializingBean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <code>RestColumnResolver</code>
 * <p>The rest column resolver interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see org.springframework.beans.factory.InitializingBean
 * @since Jdk17
 */
public interface RestColumnResolver extends InitializingBean {

    @Override
    default void afterPropertiesSet() throws Exception {
        Instance.caching(this.getClass(), support());
    }

    /**
     * <code>isNotDefault</code>
     * <p>The is not default method.</p>
     * @return boolean <p>The is not default return object is <code>boolean</code> type.</p>
     */
    default boolean isNotDefault() {
        return false;
    }

    /**
     * <code>styleType</code>
     * <p>The style type method.</p>
     * @return {@link io.github.nichetoolkit.mybatis.enums.StyleType} <p>The style type return object is <code>StyleType</code> type.</p>
     * @see io.github.nichetoolkit.mybatis.enums.StyleType
     */
    default StyleType styleType() {
        return Instance.styleType(getClass());
    }

    /**
     * <code>support</code>
     * <p>The support method.</p>
     * @return {@link io.github.nichetoolkit.mybatis.enums.StyleType} <p>The support return object is <code>StyleType</code> type.</p>
     * @see io.github.nichetoolkit.mybatis.enums.StyleType
     */
    StyleType support();

    /**
     * <code>resolve</code>
     * <p>The resolve method.</p>
     * @param fieldName {@link java.lang.String} <p>The field name parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>The resolve return object is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    String resolve(String fieldName) throws RestException;

    /**
     * <code>Instance</code>
     * <p>The instance class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk17
     */
    class Instance {
        /**
         * <code>RESOLVERS</code>
         * {@link java.util.Map} <p>The <code>RESOLVERS</code> field.</p>
         * @see java.util.Map
         */
        static Map<Class<?>, StyleType> RESOLVERS = new ConcurrentHashMap<>();

        /**
         * <code>styleType</code>
         * <p>The style type method.</p>
         * @param resolverType {@link java.lang.Class} <p>The resolver type parameter is <code>Class</code> type.</p>
         * @return {@link io.github.nichetoolkit.mybatis.enums.StyleType} <p>The style type return object is <code>StyleType</code> type.</p>
         * @see java.lang.Class
         * @see io.github.nichetoolkit.mybatis.enums.StyleType
         */
        private static StyleType styleType(Class<? extends RestColumnResolver> resolverType) {
            return RESOLVERS.get(resolverType);
        }

        /**
         * <code>caching</code>
         * <p>The caching method.</p>
         * @param resolverType {@link java.lang.Class} <p>The resolver type parameter is <code>Class</code> type.</p>
         * @param styleType    {@link io.github.nichetoolkit.mybatis.enums.StyleType} <p>The style type parameter is <code>StyleType</code> type.</p>
         * @see java.lang.Class
         * @see io.github.nichetoolkit.mybatis.enums.StyleType
         */
        private static void caching(Class<? extends RestColumnResolver> resolverType, StyleType styleType) {
            if (!RESOLVERS.containsKey(resolverType)) {
                RESOLVERS.put(resolverType, styleType);
            }
        }
    }
}
