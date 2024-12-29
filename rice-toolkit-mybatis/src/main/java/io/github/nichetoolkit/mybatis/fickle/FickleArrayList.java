package io.github.nichetoolkit.mybatis.fickle;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestKey;
import io.github.nichetoolkit.rest.stream.DefaultSpliterator;
import io.github.nichetoolkit.rest.stream.DefaultSpliterators;
import io.github.nichetoolkit.rest.stream.DefaultStreamSupport;
import io.github.nichetoolkit.rest.stream.RestStream;
import io.github.nichetoolkit.rest.util.GeneralUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <code>FickleArrayList</code>
 * <p>The fickle array list class.</p>
 * @param <F>  {@link io.github.nichetoolkit.mybatis.fickle.FickleField} <p>The generic parameter is <code>FickleField</code> type.</p>
 * @see  io.github.nichetoolkit.mybatis.fickle.FickleField
 * @see  java.util.ArrayList
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public class FickleArrayList<F extends FickleField<?>> extends ArrayList<F> {

    /**
     * <code>FickleArrayList</code>
     * <p>Instantiates a new fickle array list.</p>
     */
    public FickleArrayList() {
    }

    /**
     * <code>FickleArrayList</code>
     * <p>Instantiates a new fickle array list.</p>
     * @param initialCapacity int <p>The initial capacity parameter is <code>int</code> type.</p>
     */
    public FickleArrayList(int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * <code>FickleArrayList</code>
     * <p>Instantiates a new fickle array list.</p>
     * @param c {@link java.util.Collection} <p>The c parameter is <code>Collection</code> type.</p>
     * @see  java.util.Collection
     */
    public FickleArrayList(Collection<? extends F> c) {
        super(c);
    }

    /**
     * <code>FickleArrayList</code>
     * <p>Instantiates a new fickle array list.</p>
     * @param m {@link java.util.Map} <p>The m parameter is <code>Map</code> type.</p>
     * @see  java.util.Map
     */
    public FickleArrayList(Map<? extends String, ? extends F> m) {
        super(m.values());
    }

    /**
     * <code>toMap</code>
     * <p>The to map method.</p>
     * @return  {@link java.util.Map} <p>The to map return object is <code>Map</code> type.</p>
     * @see  java.util.Map
     */
    public Map<? extends String, ? extends F> toMap() {
        return this.stream().collect(Collectors
                .toMap(RestKey::getKey, Function.identity(), (oldValue, newValue) -> {
                    if (GeneralUtils.isEmpty(newValue) && !newValue.isEmpty()) {
                        return newValue;
                    } else {
                        return oldValue;
                    }
                }));
    }

    /**
     * <code>ofSpliterator</code>
     * <p>The of spliterator method.</p>
     * @return  {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>The of spliterator return object is <code>DefaultSpliterator</code> type.</p>
     * @see  io.github.nichetoolkit.rest.stream.DefaultSpliterator
     */
    public DefaultSpliterator<F> ofSpliterator() {
        return DefaultSpliterators.spliterator(this);
    }

    /**
     * <code>ofStream</code>
     * <p>The of stream method.</p>
     * @return  {@link io.github.nichetoolkit.rest.stream.RestStream} <p>The of stream return object is <code>RestStream</code> type.</p>
     * @see  io.github.nichetoolkit.rest.stream.RestStream
     * @see  io.github.nichetoolkit.rest.RestException
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public RestStream<F> ofStream() throws RestException {
        return DefaultStreamSupport.stream(ofSpliterator(), false);
    }

    /**
     * <code>ofParallelStream</code>
     * <p>The of parallel stream method.</p>
     * @return  {@link io.github.nichetoolkit.rest.stream.RestStream} <p>The of parallel stream return object is <code>RestStream</code> type.</p>
     * @see  io.github.nichetoolkit.rest.stream.RestStream
     * @see  io.github.nichetoolkit.rest.RestException
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public RestStream<F> ofParallelStream() throws RestException {
        return DefaultStreamSupport.stream(ofSpliterator(), true);
    }
}
