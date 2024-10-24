package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestOptional;
import io.github.nichetoolkit.rest.stream.RestStream;
import io.github.nichetoolkit.rest.util.BeanUtils;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.enums.AutoMark;
import io.github.nichetoolkit.rice.resolver.RestLogicResolver;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <code>DefaultLogicResolver</code>
 * <p>The default logic resolver class.</p>
 * @param <L> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.resolver.RestLogicResolver
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@SuppressWarnings({"rawtypes","unchecked"})
public abstract class DefaultLogicResolver<L> implements RestLogicResolver<L> {

    /**
     * <code>resolveLogic</code>
     * <p>The resolve logic method.</p>
     * @param <L>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param autoMark {@link io.github.nichetoolkit.rice.enums.AutoMark} <p>The auto mark parameter is <code>AutoMark</code> type.</p>
     * @return L <p>The resolve logic return object is <code>L</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.enums.AutoMark
     * @see io.github.nichetoolkit.rest.RestException
     */
    static <L> L resolveLogic(AutoMark autoMark) throws RestException {
        Class<?> logicType = autoMark.getKey();
        List<RestLogicResolver> resolvers = BeanUtils.beansOfType(RestLogicResolver.class);
        if (GeneralUtils.isEmpty(resolvers)) {
            return null;
        }
        Map<Class, List<RestLogicResolver>> resolverMap= resolvers.stream().collect(Collectors.groupingBy(RestLogicResolver::logicType));
        List<RestLogicResolver> logicResolvers = resolverMap.get(logicType);
        if (GeneralUtils.isEmpty(logicResolvers)) {
            return null;
        }
        RestOptional<RestLogicResolver> logicResolver = RestStream.stream(logicResolvers).findAny();
        if (GeneralUtils.isEmpty(logicResolver)) {
            return null;
        }
        RestLogicResolver<L> resolver = (RestLogicResolver<L>) logicResolver.get();
        return resolver.resolve();
    }

}
