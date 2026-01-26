package org.apache.commons.collections4.map.alternativepattern;

import org.apache.commons.collections4.OrderedMap;
import org.apache.commons.collections4.OrderedMapIterator;
import org.apache.commons.collections4.Unmodifiable;
import org.apache.commons.collections4.collection.UnmodifiableCollection;
import org.apache.commons.collections4.iterators.UnmodifiableOrderedMapIterator;
import org.apache.commons.collections4.map.AbstractIterableMap;
import org.apache.commons.collections4.map.UnmodifiableEntrySet;
import org.apache.commons.collections4.map.UnmodifiableOrderedMap;
import org.apache.commons.collections4.set.UnmodifiableSet;

import java.io.Serializable;
import java.util.*;

public class UnmodifiableOrderedMapNoDecorator<K,V> extends AbstractIterableMap<K, V> implements Unmodifiable, Serializable, OrderedMap<K, V> {

    transient Map<K ,V> map;
    /** Serialization version */
    private static final long serialVersionUID = 8136428161720526266L;

    /**
     * Factory method to create an unmodifiable sorted map.
     *
     * @param <K>  the key type
     * @param <V>  the value type
     * @param map  the map to decorate, must not be null
     * @return a new ordered map
     * @throws NullPointerException if map is null
     * @since 4.0
     */
    public static <K, V> OrderedMap<K, V> unmodifiableOrderedMapNoDecorator(final OrderedMap<? extends K, ? extends V> map) {
        if (map instanceof Unmodifiable) {
            @SuppressWarnings("unchecked") // safe to upcast
            final OrderedMap<K, V> tmpMap = (OrderedMap<K, V>) map;
            return tmpMap;
        }
        return new UnmodifiableOrderedMapNoDecorator<>(map);
    }

    @SuppressWarnings("unchecked")
    private UnmodifiableOrderedMapNoDecorator(final OrderedMap<? extends K, ? extends V> map) {
        this.map = (OrderedMap<K, V>) Objects.requireNonNull(map, "map");
    }



    public OrderedMapIterator<K, V> mapIterator() {
        final OrderedMapIterator<K, V> iterator = ((OrderedMap<K, V>) map).mapIterator(); // include this behaviour
        return UnmodifiableOrderedMapIterator.unmodifiableOrderedMapIterator(iterator);
    }

    protected Map<K, V> decorated() {
        return map;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public V get(final Object key) {
        return map.get(key);
    }

    @Override
    public V put(final K key, final V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(final Object key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putAll(final Map<? extends K, ? extends V> mapToCopy) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<K> keySet() {
        return UnmodifiableSet.unmodifiableSet(map.keySet());
    }

    @Override
    public Collection<V> values() {
        return UnmodifiableCollection.unmodifiableCollection(map.values());
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return UnmodifiableEntrySet.unmodifiableEntrySet(map.entrySet());
    }

    public V getDecorated(final K key) {
        return decorated().get(key);
    }

    @Override
    public K firstKey() {
        return ((OrderedMap<K, V>) map).firstKey();
    }

    @Override
    public K lastKey() {
        return ((OrderedMap<K, V>) map).lastKey();
    }

    @Override
    public K nextKey(final K key) {
        return ((OrderedMap<K, V>) map).nextKey(key);
    }

    @Override
    public K previousKey(final K key) {
        return ((OrderedMap<K, V>) map).previousKey(key);
    }

    @Override
    public int hashCode() {
        return map.hashCode();
    }

    @Override
    public String toString() {
        return map.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return map.equals(obj);
    }


}
