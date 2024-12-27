package io.github.nichetoolkit.mybatis.handler;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.reflect.RestGenericTypes;
import org.springframework.beans.factory.InitializingBean;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <code>RestAlertnessHandler</code>
 * <p>The rest alertness handler interface.</p>
 * @param <S>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @see  org.springframework.beans.factory.InitializingBean
 * @see  java.lang.SuppressWarnings
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public interface RestAlertnessHandler<S> extends InitializingBean {

    @Override
    default void afterPropertiesSet() throws Exception {
        Instance.caching(this.getClass());
    }

    /**
     * <code>alertnessType</code>
     * <p>The alertness type method.</p>
     * @return  {@link java.lang.Class} <p>The alertness type return object is <code>Class</code> type.</p>
     * @see  java.lang.Class
     */
    default Class<S> alertnessType() {
        return (Class<S>) Instance.alertnessType(getClass());
    }

    /**
     * <code>handle</code>
     * <p>The handle method.</p>
     * @param alias {@link java.lang.String} <p>The alias parameter is <code>String</code> type.</p>
     * @param statusList {@link java.util.Collection} <p>The status list parameter is <code>Collection</code> type.</p>
     * @param alertnessType {@link java.lang.Class} <p>The alertness type parameter is <code>Class</code> type.</p>
     * @see  java.lang.String
     * @see  java.util.Collection
     * @see  java.lang.Class
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link java.lang.String} <p>The handle return object is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    String handle(String prefix, Collection<S> statusList, Class<?> alertnessType) throws RestException;

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
         * <code>alertnessType</code>
         * <p>The alertness type method.</p>
         * @param handlerType {@link java.lang.Class} <p>The handler type parameter is <code>Class</code> type.</p>
         * @see  java.lang.Class
         * @return  {@link java.lang.Class} <p>The alertness type return object is <code>Class</code> type.</p>
         */
        private static Class<?> alertnessType(Class<? extends RestAlertnessHandler> handlerType) {
            return HANDLERS.get(handlerType);
        }

        /**
         * <code>caching</code>
         * <p>The caching method.</p>
         * @param handlerType {@link java.lang.Class} <p>The handler type parameter is <code>Class</code> type.</p>
         * @see  java.lang.Class
         */
        private static void caching(Class<? extends RestAlertnessHandler> handlerType) {
            Class<?> clazzOfId = RestGenericTypes.resolveClass(RestGenericTypes.resolveType(
                    RestAlertnessHandler.class.getTypeParameters()[0], handlerType, RestAlertnessHandler.class));
            if (!HANDLERS.containsKey(handlerType)) {
                HANDLERS.put(handlerType, clazzOfId);
            }
        }
    }
}
