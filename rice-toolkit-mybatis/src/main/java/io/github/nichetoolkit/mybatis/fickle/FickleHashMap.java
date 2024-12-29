package io.github.nichetoolkit.mybatis.fickle;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * <code>FickleHashMap</code>
 * <p>The fickle hash map class.</p>
 * @param <F>  {@link io.github.nichetoolkit.mybatis.fickle.FickleField} <p>The generic parameter is <code>FickleField</code> type.</p>
 * @see  io.github.nichetoolkit.mybatis.fickle.FickleField
 * @see  java.util.LinkedHashMap
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public class FickleHashMap<F extends FickleField<?>> extends LinkedHashMap<String, F> {

    /**
     * <code>FickleHashMap</code>
     * <p>Instantiates a new fickle hash map.</p>
     */
    public FickleHashMap() {
    }

    /**
     * <code>FickleHashMap</code>
     * <p>Instantiates a new fickle hash map.</p>
     * @param initialCapacity int <p>The initial capacity parameter is <code>int</code> type.</p>
     */
    public FickleHashMap(int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * <code>FickleHashMap</code>
     * <p>Instantiates a new fickle hash map.</p>
     * @param initialCapacity int <p>The initial capacity parameter is <code>int</code> type.</p>
     * @param loadFactor float <p>The load factor parameter is <code>float</code> type.</p>
     */
    public FickleHashMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    /**
     * <code>FickleHashMap</code>
     * <p>Instantiates a new fickle hash map.</p>
     * @param m {@link java.util.Map} <p>The m parameter is <code>Map</code> type.</p>
     * @see  java.util.Map
     */
    public FickleHashMap(Map<? extends String, ? extends F> m) {
        super(m);
    }

    /**
     * <code>FickleHashMap</code>
     * <p>Instantiates a new fickle hash map.</p>
     * @param c {@link java.util.Collection} <p>The c parameter is <code>Collection</code> type.</p>
     * @see  java.util.Collection
     */
    public FickleHashMap(Collection<? extends F> c) {
        super(new FickleHashSet<>(c).toMap());
    }

    /**
     * <code>FickleHashMap</code>
     * <p>Instantiates a new fickle hash map.</p>
     * @param initialCapacity int <p>The initial capacity parameter is <code>int</code> type.</p>
     * @param loadFactor float <p>The load factor parameter is <code>float</code> type.</p>
     * @param accessOrder boolean <p>The access order parameter is <code>boolean</code> type.</p>
     */
    public FickleHashMap(int initialCapacity, float loadFactor, boolean accessOrder) {
        super(initialCapacity, loadFactor, accessOrder);
    }

    /**
     * <code>ofMerge</code>
     * <p>The of merge method.</p>
     * @param value F <p>The value parameter is <code>F</code> type.</p>
     * @param remappingFunction {@link java.util.function.BiFunction} <p>The remapping function parameter is <code>BiFunction</code> type.</p>
     * @see  java.util.function.BiFunction
     * @return F <p>The of merge return object is <code>F</code> type.</p>
     */
    public F ofMerge(F value, BiFunction<? super F, ? super F, ? extends F> remappingFunction) {
        return super.merge(value.getKey(), value, remappingFunction);
    }

    /**
     * <code>ofCompute</code>
     * <p>The of compute method.</p>
     * @param value F <p>The value parameter is <code>F</code> type.</p>
     * @param remappingFunction {@link java.util.function.BiFunction} <p>The remapping function parameter is <code>BiFunction</code> type.</p>
     * @see  java.util.function.BiFunction
     * @return F <p>The of compute return object is <code>F</code> type.</p>
     */
    public F ofCompute(F value, BiFunction<? super String, ? super F, ? extends F> remappingFunction) {
        return super.compute(value.getKey(), remappingFunction);
    }

    /**
     * <code>ofComputeIfPresent</code>
     * <p>The of compute if present method.</p>
     * @param value F <p>The value parameter is <code>F</code> type.</p>
     * @param remappingFunction {@link java.util.function.BiFunction} <p>The remapping function parameter is <code>BiFunction</code> type.</p>
     * @see  java.util.function.BiFunction
     * @return F <p>The of compute if present return object is <code>F</code> type.</p>
     */
    public F ofComputeIfPresent(F value, BiFunction<? super String, ? super F, ? extends F> remappingFunction) {
        return super.computeIfPresent(value.getKey(), remappingFunction);
    }

    /**
     * <code>ofComputeIfAbsent</code>
     * <p>The of compute if absent method.</p>
     * @param value F <p>The value parameter is <code>F</code> type.</p>
     * @param mappingFunction {@link java.util.function.Function} <p>The mapping function parameter is <code>Function</code> type.</p>
     * @see  java.util.function.Function
     * @return F <p>The of compute if absent return object is <code>F</code> type.</p>
     */
    public F ofComputeIfAbsent(F value, Function<? super String, ? extends F> mappingFunction) {
        return super.computeIfAbsent(value.getKey(), mappingFunction);
    }

    /**
     * <code>ofReplace</code>
     * <p>The of replace method.</p>
     * @param value F <p>The value parameter is <code>F</code> type.</p>
     * @return F <p>The of replace return object is <code>F</code> type.</p>
     */
    public F ofReplace(F value) {
        return super.replace(value.getKey(), value);
    }

    /**
     * <code>ofPutIfAbsent</code>
     * <p>The of put if absent method.</p>
     * @param value F <p>The value parameter is <code>F</code> type.</p>
     * @return F <p>The of put if absent return object is <code>F</code> type.</p>
     */
    public F ofPutIfAbsent(F value) {
        return super.putIfAbsent(value.getKey(), value);
    }

    /**
     * <code>ofRemove</code>
     * <p>The of remove method.</p>
     * @param value F <p>The value parameter is <code>F</code> type.</p>
     * @return F <p>The of remove return object is <code>F</code> type.</p>
     */
    public F ofRemove(F value) {
        return super.remove(value.getKey());
    }

    /**
     * <code>ofPutAll</code>
     * <p>The of put all method.</p>
     * @param c {@link java.util.Collection} <p>The c parameter is <code>Collection</code> type.</p>
     * @see  java.util.Collection
     */
    public void ofPutAll(Collection<? extends F> c) {
        super.putAll(new FickleHashSet<>(c).toMap());
    }

    /**
     * <code>ofPut</code>
     * <p>The of put method.</p>
     * @param value F <p>The value parameter is <code>F</code> type.</p>
     * @return F <p>The of put return object is <code>F</code> type.</p>
     */
    public F ofPut(F value) {
        return super.put(value.getKey(), value);
    }

    /**
     * <code>ofContainsKey</code>
     * <p>The of contains key method.</p>
     * @param value F <p>The value parameter is <code>F</code> type.</p>
     * @return boolean <p>The of contains key return object is <code>boolean</code> type.</p>
     */
    public boolean ofContainsKey(F value) {
        return super.containsKey(value.getKey());
    }

    /**
     * <code>ofContainsValue</code>
     * <p>The of contains value method.</p>
     * @param value F <p>The value parameter is <code>F</code> type.</p>
     * @return boolean <p>The of contains value return object is <code>boolean</code> type.</p>
     */
    public boolean ofContainsValue(F value) {
        return super.containsKey(value.getKey());
    }

}
