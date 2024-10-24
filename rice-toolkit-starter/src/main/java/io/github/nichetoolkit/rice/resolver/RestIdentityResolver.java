package io.github.nichetoolkit.rice.resolver;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestOptional;
import io.github.nichetoolkit.rest.error.supply.ResourceNotFoundException;
import io.github.nichetoolkit.rest.reflect.RestGenericTypes;
import io.github.nichetoolkit.rest.stream.RestStream;
import io.github.nichetoolkit.rest.util.BeanUtils;
import io.github.nichetoolkit.rest.util.OptionalUtils;
import io.github.nichetoolkit.rice.RestId;
import io.github.nichetoolkit.rice.helper.ModelHelper;
import org.springframework.beans.factory.InitializingBean;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * <code>RestIdentityResolver</code>
 * <p>The rest identity resolver interface.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see org.springframework.beans.factory.InitializingBean
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@SuppressWarnings({"rawtypes","unchecked"})
public interface RestIdentityResolver<I> extends InitializingBean {

    @Override
    default void afterPropertiesSet() throws Exception {
        Instance.caching(this.getClass());
    }

    /**
     * <code>identityType</code>
     * <p>The identity type method.</p>
     * @return {@link java.lang.Class} <p>The identity type return object is <code>Class</code> type.</p>
     * @see java.lang.Class
     */
    default Class<I> identityType() {
        return (Class<I>) Instance.identityType(getClass());
    }

    /**
     * <code>resolve</code>
     * <p>The resolve method.</p>
     * @return I <p>The resolve return object is <code>I</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    I resolve() throws RestException;

    /**
     * <code>resolveIdentity</code>
     * <p>The resolve identity method.</p>
     * @param <M>   {@link io.github.nichetoolkit.rice.RestId} <p>The generic parameter is <code>RestId</code> type.</p>
     * @param <I>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param model M <p>The model parameter is <code>M</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestId
     * @see io.github.nichetoolkit.rest.RestException
     */
    static <M extends RestId<I>,I> void resolveIdentity(M model) throws RestException {
        Class<?> identityType = ModelHelper.identityType(model);
        List<RestIdentityResolver> resolvers = BeanUtils.beansOfType(RestIdentityResolver.class);
        OptionalUtils.ofEmpty(resolvers,"the bean of 'RestIdentityResolver' type is not found!", ResourceNotFoundException::new);
        Map<Class, List<RestIdentityResolver>> resolverMap= resolvers.stream().collect(Collectors.groupingBy(RestIdentityResolver::identityType));
        List<RestIdentityResolver> identityResolvers = resolverMap.get(identityType);
        OptionalUtils.ofEmpty(identityResolvers,"the bean of 'RestIdentityResolver' type for <I> is not found!", ResourceNotFoundException::new);
        RestOptional<RestIdentityResolver> identityResolver = RestStream.stream(identityResolvers).findAny();
        OptionalUtils.ofNull(identityResolver,"the bean of 'RestIdentityResolver' type for <I> is not found!", ResourceNotFoundException::new);
        RestIdentityResolver<I> resolver = (RestIdentityResolver<I>) identityResolver.get();
        I identity = resolver.resolve();
        model.setId(identity);
    }

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
         * <code>identityType</code>
         * <p>The identity type method.</p>
         * @param resolverType {@link java.lang.Class} <p>The resolver type parameter is <code>Class</code> type.</p>
         * @return {@link java.lang.Class} <p>The identity type return object is <code>Class</code> type.</p>
         * @see java.lang.Class
         */
        private static Class<?> identityType(Class<? extends RestIdentityResolver> resolverType) {
            return RESOLVERS.get(resolverType);
        }

        /**
         * <code>caching</code>
         * <p>The caching method.</p>
         * @param resolverType {@link java.lang.Class} <p>The resolver type parameter is <code>Class</code> type.</p>
         * @see java.lang.Class
         */
        private static void caching(Class<? extends RestIdentityResolver> resolverType) {
            Class<?> clazzOfId = RestGenericTypes.resolveClass(RestGenericTypes.resolveType(
                    RestIdentityResolver.class.getTypeParameters()[0], resolverType, RestIdentityResolver.class));
            if (!RESOLVERS.containsKey(resolverType)) {
                RESOLVERS.put(resolverType, clazzOfId);
            }
        }
    }
}
