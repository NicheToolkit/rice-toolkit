package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.mybatis.handler.RestAlertnessHandler;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestOptional;
import io.github.nichetoolkit.rest.error.supply.ResourceNotFoundException;
import io.github.nichetoolkit.rest.stream.RestStream;
import io.github.nichetoolkit.rest.util.BeanUtils;
import io.github.nichetoolkit.rest.util.OptionalUtils;
import io.github.nichetoolkit.rice.filter.StatusFilter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <code>DefaultAlertnessHandler</code>
 * <p>The default alertness handler class.</p>
 * @param <S>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @see  io.github.nichetoolkit.mybatis.handler.RestAlertnessHandler
 * @see  java.lang.SuppressWarnings
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public abstract class DefaultAlertnessHandler<S> implements RestAlertnessHandler<S> {

    /**
     * <code>toSqlHandle</code>
     * <p>The to sql handle method.</p>
     * @param <F>  {@link io.github.nichetoolkit.rice.filter.StatusFilter} <p>The generic parameter is <code>StatusFilter</code> type.</p>
     * @param <S>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param prefix {@link java.lang.String} <p>The prefix parameter is <code>String</code> type.</p>
     * @param filter F <p>The filter parameter is <code>F</code> type.</p>
     * @see  io.github.nichetoolkit.rice.filter.StatusFilter
     * @see  java.lang.String
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link java.lang.String} <p>The to sql handle return object is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public static <F extends StatusFilter<S>,S> String toSqlHandle(String prefix, F filter) throws RestException {
        Class<?> statusType = filter.getStatusType();
        List<RestAlertnessHandler> handlers = BeanUtils.beansOfType(RestAlertnessHandler.class);
        OptionalUtils.ofEmpty(handlers, "the bean of 'RestAlertnessHandler' type for <S> is not found!", ResourceNotFoundException::new);
        Map<Class, List<RestAlertnessHandler>> handlerMap = handlers.stream().collect(Collectors.groupingBy(RestAlertnessHandler::alertnessType));
        List<RestAlertnessHandler> alertnessHandlers = handlerMap.get(statusType);
        OptionalUtils.ofEmpty(alertnessHandlers, "the bean of 'RestAlertnessHandler' type for <S> is not found!", ResourceNotFoundException::new);
        RestOptional<RestAlertnessHandler> alertnessHandler= RestStream.stream(alertnessHandlers).findAny();
        OptionalUtils.ofNull(alertnessHandler, "the bean of 'RestAlertnessHandler' type for <S> is not found!", ResourceNotFoundException::new);
        RestAlertnessHandler<S> handler = (RestAlertnessHandler<S>) alertnessHandler.get();
        return handler.handle(prefix, filter.toStatuses(), statusType);
    }
}
