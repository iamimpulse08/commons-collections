/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.commons.collections4.map.alternativepattern;

import org.apache.commons.collections4.OrderedMap;
import org.apache.commons.collections4.OrderedMapIterator;
import org.apache.commons.collections4.Unmodifiable;
import org.apache.commons.collections4.iterators.UnmodifiableOrderedMapIterator;
import org.apache.commons.collections4.map.AbstractIterableMap;

import java.io.Serializable;
import java.util.*;

public class UnmodifiableOrderedMapNoDecoratorLinkedHashMap<K,V> extends AbstractIterableMap<K, V> implements Unmodifiable, Serializable, OrderedMap<K, V> {

    /** Serialization version */
    private static final long serialVersionUID = 8136428161720526266L;

    private final List<K> orderedKeys;
    private final Map<K, V> data;
    private final Map<K, Integer> keyIndexMap;
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

        throw new UnsupportedOperationException("This method is not supported.");
/*        if (map instanceof Unmodifiable) {
            @SuppressWarnings("unchecked") // safe to upcast
            final OrderedMap<K, V> tmpMap = (OrderedMap<K, V>) map;
            return tmpMap;
        }
        return new UnmodifiableOrderedMapNoDecoratorLinkedHashMap<>(map);*/
    }

    /**
     * This constructor operation ensures that O(1) behaviour is still enforced by creating two copies of the data, which adds O(N+2) operations at run-time when constructing the object,
     * which in theory should use more energy via the DRAM; however, this is immeasurable within JoularJX; however, we can still see the impact on the CPUs energy readings.
     * @param map The ordered map, which is to be copied rather than decorated, must not be null.
     */
    @SuppressWarnings("unchecked")
    private UnmodifiableOrderedMapNoDecoratorLinkedHashMap(final OrderedMap<? extends K, ? extends V> map) { // TODO : Test LinkedHashMap implementation.
        Objects.requireNonNull(map, "map");

        this.orderedKeys = new ArrayList<>(map.keySet());
        this.data = new HashMap<>(map); // TODO : Test LinkedHashMap implementation

        this.keyIndexMap = new LinkedHashMap<>();

        for (int i = 0; i < orderedKeys.size(); i++) {
            keyIndexMap.put(orderedKeys.get(i), i);
        }
    }



    public OrderedMapIterator<K, V> mapIterator() {
        final OrderedMapIterator<K, V> iterator = ((OrderedMap<K, V>) data).mapIterator(); // include this behaviour
        return UnmodifiableOrderedMapIterator.unmodifiableOrderedMapIterator(iterator);
    }

    protected Map<K, V> decorated() {
        return data;
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return data.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return data.containsValue(value);
    }

    @Override
    public V get(final Object key) {
        return data.get(key);
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
        return data.keySet();
    }

    @Override
    public Collection<V> values() {
        return data.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return data.entrySet();
    }

    public V getDecorated(final K key) {
        return decorated().get(key);
    }

    @Override
    public K firstKey() {
        if (orderedKeys.isEmpty()) {
            throw new NoSuchElementException("Map is empty");
        }
        return orderedKeys.get(0);
    }

    @Override
    public K lastKey() {
        if (orderedKeys.isEmpty()) {
            throw new NoSuchElementException("Map is empty");
        }
        return orderedKeys.get(orderedKeys.size() - 1);
    }

    @Override
    public K nextKey(final K key) {
        Integer index = keyIndexMap.get(key);
        if (index == null || index >= orderedKeys.size() - 1) {
            return null;
        }
        return orderedKeys.get(index + 1);
    }

    @Override
    public K previousKey(final K key) {
        Integer index = keyIndexMap.get(key);
        if (index == null || index <= 0) {
            return null;
        }
        return orderedKeys.get(index - 1);
    }

    @Override
    public int hashCode() {
        return data.hashCode();
    }

    @Override
    public String toString() {
        return data.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return data.equals(obj);
    }


}
