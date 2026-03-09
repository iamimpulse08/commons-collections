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

import java.io.Serializable;
import java.util.*;

public class UnmodifiableOrderedMapDecoratorLayer2<K,V> extends UnmodifiableOrderedMapDecorator<K, V> implements Unmodifiable, Serializable, OrderedMap<K, V> {

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
    public static <K, V> OrderedMap<K, V> unmodifiableOrderedMapNoDecorator(final OrderedMap<K, V> map) {
        if (map instanceof Unmodifiable) {
            @SuppressWarnings("unchecked") // safe to upcast
            final OrderedMap<K, V> tmpMap = (OrderedMap<K, V>) map;
            return tmpMap;
        }
        return new UnmodifiableOrderedMapDecoratorLayer2<>(map);
    }

    /**
     * This constructor operation ensures that O(1) behaviour is still enforced by creating two copies of the data, which adds O(N+2) operations at run-time when constructing the object,
     * which in theory should use more energy via the DRAM; however, this is immeasurable within JoularJX; however, we can still see the impact on the CPUs energy readings.
     * @param map The ordered map, which is to be copied rather than decorated, must not be null.
     */
    @SuppressWarnings("unchecked")
    public UnmodifiableOrderedMapDecoratorLayer2(final OrderedMap<K, V> map) { // TODO : Test LinkedHashMap implementation.
        super(map);
    }



    public OrderedMapIterator<K, V> mapIterator() {
        final OrderedMapIterator<K, V> iterator = ((OrderedMap<K, V>) decorated()).mapIterator(); // include this behaviour
        return UnmodifiableOrderedMapIterator.unmodifiableOrderedMapIterator(iterator);
    }


    @Override
    public int size() {
        return super.size();
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return super.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return super.containsValue(value);
    }

    @Override
    public V get(final Object key) {
        return super.get(key);
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
        return super.keySet();
    }

    @Override
    public Collection<V> values() {
        return super.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return super.entrySet();
    }

    public V getDecorated(final K key) {
        return decorated().get(key);
    }

    @Override
    public K firstKey() {
        return linkedMap.getHead();
    }

    @Override
    public K lastKey() {
        return linkedMap.getTail();
    }

    @Override
    public K nextKey(final K key) {
        return linkedMap.getNext(key);
    }

    @Override
    public K previousKey(final K key) {
        return linkedMap.getPrevious(key);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }


}
