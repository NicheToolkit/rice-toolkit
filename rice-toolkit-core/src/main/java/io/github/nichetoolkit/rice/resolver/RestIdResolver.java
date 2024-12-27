package io.github.nichetoolkit.rice.resolver;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.reflect.RestGenericTypes;
import org.springframework.beans.factory.InitializingBean;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <code>RestIdResolver</code>
 * <p>The rest id resolver interface.</p>
 * @param <I>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @see  org.springframework.beans.factory.InitializingBean
 * @see  java.lang.SuppressWarnings
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public interface RestIdResolver<I> extends InitializingBean {

    @Override
    default void afterPropertiesSet() throws Exception {
        Instance.caching(this.getClass());
    }

    /**
     * <code>idType</code>
     * <p>The id type method.</p>
     * @return  {@link java.lang.Class} <p>The id type return object is <code>Class</code> type.</p>
     * @see  java.lang.Class
     */
    default Class<I> idType() {
        return (Class<I>) Instance.idType(getClass());
    }

    /**
     * <code>resolve</code>
     * <p>The resolve method.</p>
     * @return I <p>The resolve return object is <code>I</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see  io.github.nichetoolkit.rest.RestException
     */
    I resolve() throws RestException;

    /**
     * <code>Instance</code>
     * <p>The instance class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk1.8
     */
    class Instance {
        /**
         * <code>RESOLVERS</code>
         * {@link java.util.Map} <p>The <code>RESOLVERS</code> field.</p>
         * @see  java.util.Map
         */
        static Map<Class<?>, Class<?>> RESOLVERS = new ConcurrentHashMap<>();

        /**
         * <code>idType</code>
         * <p>The id type method.</p>
         * @param resolverType {@link java.lang.Class} <p>The resolver type parameter is <code>Class</code> type.</p>
         * @see  java.lang.Class
         * @return  {@link java.lang.Class} <p>The id type return object is <code>Class</code> type.</p>
         */
        private static Class<?> idType(Class<? extends RestIdResolver> resolverType) {
            return RESOLVERS.get(resolverType);
        }

        /**
         * <code>caching</code>
         * <p>The caching method.</p>
         * @param resolverType {@link java.lang.Class} <p>The resolver type parameter is <code>Class</code> type.</p>
         * @see  java.lang.Class
         */
        private static void caching(Class<? extends RestIdResolver> resolverType) {
            Class<?> clazzOfId = RestGenericTypes.resolveClass(RestGenericTypes.resolveType(
                    RestIdResolver.class.getTypeParameters()[0], resolverType, RestIdResolver.class));
            if (!RESOLVERS.containsKey(resolverType)) {
                RESOLVERS.put(resolverType, clazzOfId);
            }
        }
    }
}
