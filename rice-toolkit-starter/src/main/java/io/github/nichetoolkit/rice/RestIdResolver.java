package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestOptional;
import io.github.nichetoolkit.rest.error.supply.ResourceNotFoundException;
import io.github.nichetoolkit.rest.reflect.RestGenericTypes;
import io.github.nichetoolkit.rest.stream.RestStream;
import io.github.nichetoolkit.rest.util.ContextUtils;
import io.github.nichetoolkit.rest.util.OptionalUtils;
import io.github.nichetoolkit.rice.helper.ModelHelper;
import org.springframework.beans.factory.InitializingBean;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * <code>RestIdResolver</code>
 * <p>The type rest id resolver interface.</p>
 * @param <I> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see org.springframework.beans.factory.InitializingBean
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@SuppressWarnings({"rawtypes","unchecked"})
public interface RestIdResolver<I> extends InitializingBean {

    @Override
    default void afterPropertiesSet() throws Exception {
        CachingIdClass.caching(this.getClass());
    }

    /**
     * <code>clazz</code>
     * <p>the method.</p>
     * @return {@link java.lang.Class} <p>the return object is <code>Class</code> type.</p>
     * @see java.lang.Class
     */
    default Class<I> clazz() {
        return (Class<I>) CachingIdClass.clazzOfId(getClass());
    }

    /**
     * <code>resolveId</code>
     * <p>the id method.</p>
     * @param <M>   {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param model M <p>the model parameter is <code>M</code> type.</p>
     * @return I <p>the id return object is <code>I</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    <M extends RestId<I>> I resolveId(M model) throws RestException;

    /**
     * <code>resolveModel</code>
     * <p>the model method.</p>
     * @param <M>   {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param <I>   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param model M <p>the model parameter is <code>M</code> type.</p>
     * @return I <p>the model return object is <code>I</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    static <M extends RestId<I>,I> I resolveModel(M model) throws RestException {
        Class<?> clazzOfId = ModelHelper.clazzOfModel(model);
        List<RestIdResolver> resolverBeans = ContextUtils.getBeans(RestIdResolver.class);
        OptionalUtils.ofEmpty(resolverBeans,"the bean of 'RestIdResolver' type is not found!", ResourceNotFoundException::new);
        Map<Class, List<RestIdResolver>> idOfResolverMap = resolverBeans.stream().collect(Collectors.groupingBy(RestIdResolver::clazz));
        List<RestIdResolver> restIdResolvers = idOfResolverMap.get(clazzOfId);
        OptionalUtils.ofEmpty(restIdResolvers,"the bean of 'RestIdResolver' type for <I> is not found!", ResourceNotFoundException::new);
        RestOptional<RestIdResolver> anyOptional = RestStream.stream(restIdResolvers).findAny();
        OptionalUtils.ofNull(anyOptional,"the bean of 'RestIdResolver' type for <I> is not found!", ResourceNotFoundException::new);
        RestIdResolver<I> restIdResolver = (RestIdResolver<I>) anyOptional.get();
        I id = restIdResolver.resolveId(model);
        model.setId(id);
        return id;
    }

    /**
     * <code>CachingIdClass</code>
     * <p>The type caching id class class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk1.8
     */
    class CachingIdClass {
        /**
         * <code>CLASS_MAP</code>
         * {@link java.util.Map} <p>the <code>CLASS_MAP</code> field.</p>
         * @see java.util.Map
         */
        static Map<Class<?>, Class<?>> CLASS_MAP = new ConcurrentHashMap<>();

        private static Class<?> clazzOfId(Class<? extends RestIdResolver> clazzOfResolver) {
            return CLASS_MAP.get(clazzOfResolver);
        }

        private static void caching(Class<? extends RestIdResolver> clazzOfResolver) {
            Class<?> clazzOfId = RestGenericTypes.resolveClass(RestGenericTypes.resolveType(
                    RestIdResolver.class.getTypeParameters()[0], clazzOfResolver, RestIdResolver.class));
            if (!CLASS_MAP.containsKey(clazzOfResolver)) {
                CLASS_MAP.put(clazzOfResolver, clazzOfId);
            }
        }
    }
}
