package io.github.nichetoolkit.rice.resolver;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.reflect.RestGenericTypes;
import org.springframework.beans.factory.InitializingBean;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <code>RestLogicResolver</code>
 * <p>The rest logic resolver interface.</p>
 * @param <L> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see org.springframework.beans.factory.InitializingBean
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@SuppressWarnings({"rawtypes","unchecked"})
public interface RestLogicResolver<L> extends InitializingBean {

    @Override
    default void afterPropertiesSet() throws Exception {
        Instance.caching(this.getClass());
    }

    /**
     * <code>logicType</code>
     * <p>The logic type method.</p>
     * @return {@link java.lang.Class} <p>The logic type return object is <code>Class</code> type.</p>
     * @see java.lang.Class
     */
    default Class<L> logicType() {
        return (Class<L>) Instance.logicType(getClass());
    }

    /**
     * <code>resolve</code>
     * <p>The resolve method.</p>
     * @return L <p>The resolve return object is <code>L</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    L resolve() throws RestException;

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
         * @see java.util.Map
         */
        static Map<Class<?>, Class<?>> RESOLVERS = new ConcurrentHashMap<>();

        /**
         * <code>logicType</code>
         * <p>The logic type method.</p>
         * @param resolverType {@link java.lang.Class} <p>The resolver type parameter is <code>Class</code> type.</p>
         * @return {@link java.lang.Class} <p>The logic type return object is <code>Class</code> type.</p>
         * @see java.lang.Class
         */
        private static Class<?> logicType(Class<? extends RestLogicResolver> resolverType) {
            return RESOLVERS.get(resolverType);
        }

        /**
         * <code>caching</code>
         * <p>The caching method.</p>
         * @param resolverType {@link java.lang.Class} <p>The resolver type parameter is <code>Class</code> type.</p>
         * @see java.lang.Class
         */
        private static void caching(Class<? extends RestLogicResolver> resolverType) {
            Class<?> clazzOfId = RestGenericTypes.resolveClass(RestGenericTypes.resolveType(
                    RestLogicResolver.class.getTypeParameters()[0], resolverType, RestLogicResolver.class));
            if (!RESOLVERS.containsKey(resolverType)) {
                RESOLVERS.put(resolverType, clazzOfId);
            }
        }
    }
}
