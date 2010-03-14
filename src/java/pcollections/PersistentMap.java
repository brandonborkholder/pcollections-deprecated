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
 * and {@code PersistentMap} provides access to the set of keys via
 * {@link #keySet()} and collection of values via {@link #values()}.
 * </p>
 *
 * @author Brandon Borkholder
 * @version March 12, 2010
 */
public interface PersistentMap<K, V> extends Iterable<Entry<K, V>> {
  /**
   * Returns a {@code PersistentMap} which maps {@code key} to {@code value}. If
   * this map already contains an entry for {@code key}, the new map replaces
   * the existing entry.
   *
   * <p>
   * Some implementations may not allow {@code null} as a key or value. An
   * acceptable action is to throw {@code NullPointerException} is such
   * instances.
   * </p>
   *
   * @param key
   *          the key with which the value is to be associated
   * @param value
   *          the value to be associated with the key
   * @return a {@code PersistentMap} containing the {@code key} and the
   *         associated {@code value}
   * @throws ClassCastException
   *           if the class of the specified key or value prevents it from being
   *           stored in the map
   * @throws NullPointerException
   *           if the specified key or value is null and the map does not permit
   *           null keys or values
   * @throws IllegalArgumentException
   *           if some property of the specified key or value prevents it from
   *           being stored in the map
   */
  PersistentMap<K, V> with(K key, V value);

  /**
   * Returns a {@code PersistentMap} which contains all entries from the given
   * {@code Iterable}. As with {@code with(Object, Object)}, the returned map
   * replaces any existing keys in this map, if there are any. The effect of
   * this call is the same as repeatedly calling {@code with(Object, Object)}
   * for each entry, applied to the {@code PersistentMap} result of the previous
   * call.
   *
   * @param entries
   *          the {@code Iterable} of {@code Entry} objects to include in the
   *          new map
   * @return a {@code PersistentMap} containing each entry
   * @throws ClassCastException
   *           if the class of the specified key or value prevents it from being
   *           stored in the map
   * @throws NullPointerException
   *           if the specified key or value is null and the map does not permit
   *           null keys or values
   * @throws IllegalArgumentException
   *           if some property of the specified key or value prevents it from
   *           being stored in the map
   */
  PersistentMap<K, V> withAll(Iterable<? extends Entry<? extends K, ? extends V>> entries);

  /**
   * Returns a {@code PersistentMap} which does not contain an entry with the
   * given key. If no such entry exists, the returned map is equivalent to
   * {@code this}.
   *
   * @param key
   *          the key to check and remove from this map
   * @return a {@code PersistentMap} which does not contain an entry for {@code
   *         key}
   */
  PersistentMap<K, V> without(Object key);

  /**
   * Returns a {@code PersistentMap} which does not contain entries for any of
   * the keys in the given {@code Iterable}. No such entries exist, the returned
   * map is equivalent to {@code this}.
   *
   * @param keys
   *          the {@code Iterable} of keys to exclude in the returned map
   * @return a {@code PersistentMap} which does not contain any entries from
   *         {@code keys}
   */
  PersistentMap<K, V> withoutAll(Iterable<?> keys);

  /**
   * Returns a {@code PersistentSet} of all the keys in this map. The returned
   * set can be extended as any other {@code PersistentSet}. No guarantee is
   * made whether or not the set will use this {@code PersistentMap} as a
   * backing map.
   *
   * @return the set of keys contained in this map
   */
  PersistentSet<K> keySet();

  /**
   * Returns a {@code PersistentCollection} of all the values in this map. The
   * returned collection can be extended as any other {@code
   * PersistentCollection}.
   *
   * @return the collection of values contained in this map
   */
  PersistentCollection<V> values();

  V get(K key);

  boolean containsKey(Object key);

  boolean containsValue(Object value);

  /**
   * Returns the number of entries in this map. If this number is more than
   * {@link Integer#MAX_VALUE}, returns {@code Integer.MAX_VALUE}.
   *
   * @return number of entries in the map
   */
  int size();

  /**
   * Returns {@code true} if and only if this map contains no entries. This is
   * equivalent to the test {@code size() == 0}.
   *
   * @return {@code true} if the map is empty, {@code false} otherwise
   */
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
