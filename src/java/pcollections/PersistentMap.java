package pcollections;

import java.util.Map.Entry;

/**
 * Copyright (c) Brandon Borkholder. All rights reserved. The use and
 * distribution terms for this software are covered by the Eclipse Public
 * License 1.0 (http://opensource.org/licenses/eclipse-1.0.php) which can be
 * found in the file epl-v10.html at the root of this distribution. By using
 * this software in any fashion, you are agreeing to be bound by the terms of
 * this license. You must not remove this notice, or any other, from this
 * software.
 */
public interface PersistentMap<K, V> extends Iterable<Entry<K, V>> {
  PersistentMap<K, V> with(K key, V value);

  PersistentMap<K, V> withAll(Iterable<? extends Entry<? extends K, ? extends V>> iterable);

  PersistentMap<K, V> without(Object key);

  PersistentMap<K, V> withoutAll(Iterable<?> iterable);

  PersistentSet<K> keySet();

  PersistentCollection<V> values();

  V get(K key);

  boolean containsKey(Object key);

  boolean containsValue(Object value);

  int size();

  boolean isEmpty();
}
