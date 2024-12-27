package io.github.nichetoolkit.mybatis.handler;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.reflect.RestGenericTypes;
import org.springframework.beans.factory.InitializingBean;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <code>RestIdentityHandler</code>
 * <p>The rest identity handler interface.</p>
 * @param <I>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @see  org.springframework.beans.factory.InitializingBean
 * @see  java.lang.SuppressWarnings
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public interface RestIdentityHandler<I> extends InitializingBean {

    @Override
    default void afterPropertiesSet() throws Exception {
        Instance.caching(this.getClass());
    }

    /**
     * <code>identityType</code>
     * <p>The identity type method.</p>
     * @return  {@link java.lang.Class} <p>The identity type return object is <code>Class</code> type.</p>
     * @see  java.lang.Class
     */
    default Class<I> identityType() {
        return (Class<I>) Instance.identityType(getClass());
    }

    /**
     * <code>handle</code>
     * <p>The handle method.</p>
     * @param prefix {@link java.lang.String} <p>The prefix parameter is <code>String</code> type.</p>
     * @param idList {@link java.util.Collection} <p>The id list parameter is <code>Collection</code> type.</p>
     * @param identityType {@link java.lang.Class} <p>The identity type parameter is <code>Class</code> type.</p>
     * @see  java.lang.String
     * @see  java.util.Collection
     * @see  java.lang.Class
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link java.lang.String} <p>The handle return object is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    String handle(String prefix, Collection<I> idList, Class<I> identityType) throws RestException;

    /**
     * <code>Instance</code>
     * <p>The instance class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk1.8
     */
    class Instance {
        /**
         * <code>HANDLERS</code>
         * {@link java.util.Map} <p>The <code>HANDLERS</code> field.</p>
         * @see  java.util.Map
         */
        static Map<Class<?>, Class<?>> HANDLERS = new ConcurrentHashMap<>();

        /**
         * <code>identityType</code>
         * <p>The identity type method.</p>
         * @param handlerType {@link java.lang.Class} <p>The handler type parameter is <code>Class</code> type.</p>
         * @see  java.lang.Class
         * @return  {@link java.lang.Class} <p>The identity type return object is <code>Class</code> type.</p>
         */
        private static Class<?> identityType(Class<? extends RestIdentityHandler> handlerType) {
            return HANDLERS.get(handlerType);
        }

        /**
         * <code>caching</code>
         * <p>The caching method.</p>
         * @param handlerType {@link java.lang.Class} <p>The handler type parameter is <code>Class</code> type.</p>
         * @see  java.lang.Class
         */
        private static void caching(Class<? extends RestIdentityHandler> handlerType) {
            Class<?> clazzOfId = RestGenericTypes.resolveClass(RestGenericTypes.resolveType(
                    RestIdentityHandler.class.getTypeParameters()[0], handlerType, RestIdentityHandler.class));
            if (!HANDLERS.containsKey(handlerType)) {
                HANDLERS.put(handlerType, clazzOfId);
            }
        }
    }
}
