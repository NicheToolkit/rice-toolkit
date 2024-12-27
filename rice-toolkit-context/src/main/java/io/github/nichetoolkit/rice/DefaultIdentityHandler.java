package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.mybatis.handler.RestIdentityHandler;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestOptional;
import io.github.nichetoolkit.rest.error.supply.ResourceNotFoundException;
import io.github.nichetoolkit.rest.stream.RestStream;
import io.github.nichetoolkit.rest.util.BeanUtils;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.util.OptionalUtils;
import io.github.nichetoolkit.rice.filter.IdFilter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <code>DefaultIdentityHandler</code>
 * <p>The default identity handler class.</p>
 * @param <I>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @see  io.github.nichetoolkit.mybatis.handler.RestIdentityHandler
 * @see  java.lang.SuppressWarnings
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public abstract class DefaultIdentityHandler<I> implements RestIdentityHandler<I> {

    /**
     * <code>DEFAULT_IDENTITY_TYPE</code>
     * {@link java.lang.Class} <p>The constant <code>DEFAULT_IDENTITY_TYPE</code> field.</p>
     * @see  java.lang.Class
     */
    private static final Class<Serializable> DEFAULT_IDENTITY_TYPE = Serializable.class;

    /**
     * <code>toSqlHandle</code>
     * <p>The to sql handle method.</p>
     * @param <F>  {@link io.github.nichetoolkit.rice.filter.IdFilter} <p>The generic parameter is <code>IdFilter</code> type.</p>
     * @param <I>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param prefix {@link java.lang.String} <p>The prefix parameter is <code>String</code> type.</p>
     * @param filter F <p>The filter parameter is <code>F</code> type.</p>
     * @see  io.github.nichetoolkit.rice.filter.IdFilter
     * @see  java.lang.String
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link java.lang.String} <p>The to sql handle return object is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public static <F extends IdFilter<I,?>, I> String toSqlHandle(String prefix, F filter) throws RestException {
        Class<I> idType = filter.getIdType();
        List<RestIdentityHandler> handlers = BeanUtils.beansOfType(RestIdentityHandler.class);
        OptionalUtils.ofEmpty(handlers, "the bean of 'RestIdentityHandler' type for <I> is not found!", ResourceNotFoundException::new);
        Map<Class, List<RestIdentityHandler>> handlerMap = handlers.stream().collect(Collectors.groupingBy(RestIdentityHandler::identityType));
        List<RestIdentityHandler> identityHandlers = handlerMap.get(idType);
        if (GeneralUtils.isEmpty(identityHandlers)) {
            identityHandlers = handlerMap.get(DEFAULT_IDENTITY_TYPE);
        }
        OptionalUtils.ofEmpty(identityHandlers, "the bean of 'RestIdentityHandler' type for <I> is not found!", ResourceNotFoundException::new);
        RestOptional<RestIdentityHandler> identityHandler = RestStream.stream(identityHandlers).findAny();
        OptionalUtils.ofNull(identityHandler, "the bean of 'RestIdentityHandler' type for <I> is not found!", ResourceNotFoundException::new);
        RestIdentityHandler<I> handler = (RestIdentityHandler<I>) identityHandler.get();
        return handler.handle(prefix, filter.toIds(), idType);
    }
}
