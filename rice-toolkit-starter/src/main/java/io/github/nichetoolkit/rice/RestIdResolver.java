package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestOptional;
import io.github.nichetoolkit.rest.error.supply.ResourceNotFoundException;
import io.github.nichetoolkit.rest.reflect.RestGenericTypes;
import io.github.nichetoolkit.rest.stream.RestStream;
import io.github.nichetoolkit.rest.util.BeanUtils;
import io.github.nichetoolkit.rest.util.GeneralUtils;
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
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see org.springframework.beans.factory.InitializingBean
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@SuppressWarnings({"rawtypes","unchecked"})
public interface RestIdResolver<I> extends InitializingBean {

    @Override
    default void afterPropertiesSet() throws Exception {
        Instance.caching(this.getClass());
    }

    /**
     * <code>type</code>
     * <p>The method.</p>
     * @return {@link java.lang.Class} <p>The return object is <code>Class</code> type.</p>
     * @see java.lang.Class
     */
    default Class<I> type() {
        return (Class<I>) Instance.type(getClass());
    }

    /**
     * <code>resolve</code>
     * <p>The method.</p>
     * @param <M>   {@link io.github.nichetoolkit.rice.RestId} <p>The generic parameter is <code>RestId</code> type.</p>
     * @param model M <p>The model parameter is <code>M</code> type.</p>
     * @return I <p>The return object is <code>I</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    <M extends RestId<I>> I resolve(M model) throws RestException;

    /**
     * <code>resolveModel</code>
     * <p>The model method.</p>
     * @param <M>   {@link io.github.nichetoolkit.rice.RestId} <p>The generic parameter is <code>RestId</code> type.</p>
     * @param <I>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param model M <p>The model parameter is <code>M</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    static <M extends RestId<I>,I> void resolveModel(M model) throws RestException {
        Class<?> identityType = ModelHelper.identityType(model);
        List<RestIdResolver> resolvers = BeanUtils.beansOfType(RestIdResolver.class);
        OptionalUtils.ofEmpty(resolvers,"the bean of 'RestIdResolver' type is not found!", ResourceNotFoundException::new);
        Map<Class, List<RestIdResolver>> resolverMap= resolvers.stream().collect(Collectors.groupingBy(RestIdResolver::type));
        List<RestIdResolver> identityResolvers = resolverMap.get(identityType);
        OptionalUtils.ofEmpty(identityResolvers,"the bean of 'RestIdResolver' type for <I> is not found!", ResourceNotFoundException::new);
        RestOptional<RestIdResolver> identityResolver = RestStream.stream(identityResolvers).findAny();
        OptionalUtils.ofNull(identityResolver,"the bean of 'RestIdResolver' type for <I> is not found!", ResourceNotFoundException::new);
        RestIdResolver<I> resolver = (RestIdResolver<I>) identityResolver.get();
        I identity = resolver.resolve(model);
        model.setId(identity);
    }

    /**
     * <code>Instance</code>
     * <p>The type instance class.</p>
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
         * <code>type</code>
         * <p>The method.</p>
         * @param resolverType {@link java.lang.Class} <p>The resolver type parameter is <code>Class</code> type.</p>
         * @return {@link java.lang.Class} <p>The return object is <code>Class</code> type.</p>
         * @see java.lang.Class
         */
        private static Class<?> type(Class<? extends RestIdResolver> resolverType) {
            return RESOLVERS.get(resolverType);
        }

        /**
         * <code>caching</code>
         * <p>The method.</p>
         * @param resolverType {@link java.lang.Class} <p>The resolver type parameter is <code>Class</code> type.</p>
         * @see java.lang.Class
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
