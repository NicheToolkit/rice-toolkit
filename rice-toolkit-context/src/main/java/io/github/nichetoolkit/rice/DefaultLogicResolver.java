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

@SuppressWarnings({"rawtypes","unchecked"})
public abstract class DefaultLogicResolver<L> implements RestLogicResolver<L> {

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
