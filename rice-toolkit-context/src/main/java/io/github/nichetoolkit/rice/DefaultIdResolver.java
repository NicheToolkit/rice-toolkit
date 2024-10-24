package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestOptional;
import io.github.nichetoolkit.rest.error.supply.ResourceNotFoundException;
import io.github.nichetoolkit.rest.stream.RestStream;
import io.github.nichetoolkit.rest.util.BeanUtils;
import io.github.nichetoolkit.rest.util.OptionalUtils;
import io.github.nichetoolkit.rice.helper.ModelTypeHelper;
import io.github.nichetoolkit.rice.resolver.RestIdentityResolver;
import org.springframework.beans.factory.InitializingBean;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <code>DefaultIdResolver</code>
 * <p>The default id resolver class.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.resolver.RestIdentityResolver
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public abstract class DefaultIdResolver<I> implements RestIdentityResolver<I> {

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
    static <M extends RestId<I>, I> void resolveIdentity(M model) throws RestException {
        Class<?> identityType = ModelTypeHelper.identityType(model);
        List<RestIdentityResolver> resolvers = BeanUtils.beansOfType(RestIdentityResolver.class);
        OptionalUtils.ofEmpty(resolvers, "the bean of 'RestIdentityResolver' type is not found!", ResourceNotFoundException::new);
        Map<Class, List<RestIdentityResolver>> resolverMap = resolvers.stream().collect(Collectors.groupingBy(RestIdentityResolver::identityType));
        List<RestIdentityResolver> identityResolvers = resolverMap.get(identityType);
        OptionalUtils.ofEmpty(identityResolvers, "the bean of 'RestIdentityResolver' type for <I> is not found!", ResourceNotFoundException::new);
        RestOptional<RestIdentityResolver> identityResolver = RestStream.stream(identityResolvers).findAny();
        OptionalUtils.ofNull(identityResolver, "the bean of 'RestIdentityResolver' type for <I> is not found!", ResourceNotFoundException::new);
        RestIdentityResolver<I> resolver = (RestIdentityResolver<I>) identityResolver.get();
        I identity = resolver.resolve();
        model.setId(identity);
    }
}
