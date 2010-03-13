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
/**
 * An object that maps keys to values. A map cannot contain duplicate keys; each
 * key can map to at most one value.
 * <p>
 * A {@code PersistentMap} is mostly equivalent to a {@link java.util.Map} in
 * that {@code with(Object, Object)} is analogous to {@code Map.put(Object,
 * Object)}, {@code without(Object)} is analogous to {@code Map.remove(Object)},
 * and {@code PersistentMap} provides access to the set of keys via {@code
 * keySet()} and collection of values via {@code values()}.
 * </p>
 *
 * @author Brandon Borkholder
 * @version March 12, 2010
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

  /**
   * Returns {@code true} if the given object is also a {@code PersistentMap}
   * and has equal content with this {@code PersistentMap}. Two {@code
   * PersistentMap}s are equal if their sets of keys are equal and for each key,
   * the two maps produce values which are equal to each other.
   *
   * <p>
   * Note that a {@code PersistentMap} is never equal to a {@code java.util.Map}
   * .
   * </p>
   *
   * @param obj
   *          the object to check for equality
   * @return {@code true} if the given object is equal to this
   */
  @Override
  boolean equals(Object obj);

  /**
   * Returns the hash code value for this object. The hash code for a {@code
   * PersistentMap} is defined to be the sum of the hash codes of each pair of
   * key and value. The hash code for a key/value pair is the hash code of the
   * key xor'ed with the hash code of the value.
   *
   * @return the hash code for this map
   */
  @Override
  int hashCode();
}
