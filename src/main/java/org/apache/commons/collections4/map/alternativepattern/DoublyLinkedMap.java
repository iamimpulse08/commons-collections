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

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class DoublyLinkedMap<K, V> {

    private final Map<K, ListNode<K>> orderedMap = new LinkedHashMap<>();

    public static class ListNode<K> {
        K key;
        ListNode<K> next;
        ListNode<K> prev;
    }

    private ListNode<K> head;
    private ListNode<K> tail;


    public static void main (String[] args) {
        Map<Integer, String> map = new HashMap<>();

        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        map.put(4, "four");
        map.put(5, "five");


        DoublyLinkedMap<Integer, String> doublyLinkedMap = new DoublyLinkedMap<>(map);

        System.out.println(doublyLinkedMap.getHead());
    }

    public DoublyLinkedMap(Map<K, V> map) {
        LinkedHashMap<K, V> linkedHashMap = new LinkedHashMap<>(map);

        ListNode<K> tempNode;

        for (Map.Entry<K, V> entry : linkedHashMap.entrySet()) {

            // gather attributes of the entry
            tempNode = new ListNode<>();
            tempNode.key = entry.getKey();

            // if head is null, this is the first element, otherwise append to the end of the list.
            if (head == null) {
                head = tempNode;
            }
            else {
                tail.next = tempNode;
            }
            tempNode.prev = tail;
            tail = tempNode;
            orderedMap.put(tempNode.key, tempNode);
        }
        if (head != null) {
            head.prev = tail;
            tail.next = head;
        }
    }

    public K getPrevious(K key) {
        return orderedMap.get(key).prev.key;
    }

    public K getNext(final K key) {
        return orderedMap.get(key).next.key;
    }

    public K getHead() {
        return head.key;
    }

    public K getTail() {
        return tail.key;
    }
}
