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
 * <code>RestIdentityResolver</code>
 * <p>The rest identity resolver interface.</p>
 * @param <I> {@link Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see InitializingBean
 * @see SuppressWarnings
 * @since Jdk1.8
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public abstract class DefaultIdResolver<I> implements RestIdentityResolver<I> {

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
