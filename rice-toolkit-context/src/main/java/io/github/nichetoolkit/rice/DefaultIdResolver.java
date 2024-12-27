package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestOptional;
import io.github.nichetoolkit.rest.error.supply.ResourceNotFoundException;
import io.github.nichetoolkit.rest.stream.RestStream;
import io.github.nichetoolkit.rest.util.BeanUtils;
import io.github.nichetoolkit.rest.util.OptionalUtils;
import io.github.nichetoolkit.rice.helper.ModelTypeHelper;
import io.github.nichetoolkit.rice.resolver.RestIdResolver;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <code>DefaultIdResolver</code>
 * <p>The default id resolver class.</p>
 * @param <I>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @see  io.github.nichetoolkit.rice.resolver.RestIdResolver
 * @see  java.lang.SuppressWarnings
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public abstract class DefaultIdResolver<I> implements RestIdResolver<I> {

    /**
     * <code>resolveIdentity</code>
     * <p>The resolve identity method.</p>
     * @param <M>  {@link io.github.nichetoolkit.rice.RestId} <p>The generic parameter is <code>RestId</code> type.</p>
     * @param <I>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param model M <p>The model parameter is <code>M</code> type.</p>
     * @see  io.github.nichetoolkit.rice.RestId
     * @see  io.github.nichetoolkit.rest.RestException
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public static <M extends RestId<I>, I> void resolveIdentity(M model) throws RestException {
        Class<?> identityType = ModelTypeHelper.identityType(model);
        List<RestIdResolver> resolvers = BeanUtils.beansOfType(RestIdResolver.class);
        OptionalUtils.ofEmpty(resolvers, "the bean of 'RestIdResolver' type is not found!", ResourceNotFoundException::new);
        Map<Class, List<RestIdResolver>> resolverMap = resolvers.stream().collect(Collectors.groupingBy(RestIdResolver::idType));
        List<RestIdResolver> idResolvers = resolverMap.get(identityType);
        OptionalUtils.ofEmpty(idResolvers, "the bean of 'RestIdResolver' type for <I> is not found!", ResourceNotFoundException::new);
        RestOptional<RestIdResolver> idResolver = RestStream.stream(idResolvers).findAny();
        OptionalUtils.ofNull(idResolver, "the bean of 'RestIdResolver' type for <I> is not found!", ResourceNotFoundException::new);
        RestIdResolver<I> resolver = (RestIdResolver<I>) idResolver.get();
        I identity = resolver.resolve();
        model.setId(identity);
    }
}
