package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestOptional;
import io.github.nichetoolkit.rest.stream.RestCollectors;
import io.github.nichetoolkit.rest.stream.RestStream;
import io.github.nichetoolkit.rest.util.BeanUtils;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.resolver.RestColumnResolver;

import java.util.List;

/**
 * <code>DefaultColumnResolver</code>
 * <p>The default column resolver class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.resolver.RestColumnResolver
 * @since Jdk17
 */
public abstract class DefaultColumnResolver implements RestColumnResolver {

    /**
     * <code>resolveColumn</code>
     * <p>The resolve column method.</p>
     * @param fieldName {@link java.lang.String} <p>The field name parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>The resolve column return object is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static String resolveColumn(String fieldName) throws RestException {
        List<RestColumnResolver> resolvers = BeanUtils.beansOfType(RestColumnResolver.class);
        if (GeneralUtils.isEmpty(resolvers)) {
            return fieldName;
        }
        List<RestColumnResolver> filterResolvers = RestStream.stream(resolvers).filter(RestColumnResolver::isNotDefault).collect(RestCollectors.toList());
        if (GeneralUtils.isEmpty(filterResolvers)) {
            RestOptional<RestColumnResolver> resolverOptional = RestStream.stream(resolvers).findAny();
            return resolverOptional.mapOfEmpty(resolver -> resolver.resolve(fieldName)).orElse(fieldName);
        } else {
            RestOptional<RestColumnResolver> resolverOptional = RestStream.stream(filterResolvers).findAny();
            return resolverOptional.mapOfEmpty(resolver -> resolver.resolve(fieldName)).orElse(fieldName);
        }
    }

}
