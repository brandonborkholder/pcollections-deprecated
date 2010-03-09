package pcollections;

import java.util.Iterator;
import java.util.Map.Entry;

import clojure.lang.IPersistentMap;

/**
 * Copyright (c) Brandon Borkholder. All rights reserved. The use and
 * distribution terms for this software are covered by the Eclipse Public
 * License 1.0 (http://opensource.org/licenses/eclipse-1.0.php) which can be
 * found in the file epl-v10.html at the root of this distribution. By using
 * this software in any fashion, you are agreeing to be bound by the terms of
 * this license. You must not remove this notice, or any other, from this
 * software.
 */
public abstract class ClojureMap<K, V> extends AbstractMap<K, V> {
  private static final Object NOTFOUND = new Object();

  protected final IPersistentMap map;

  protected ClojureMap(IPersistentMap map) {
    this.map = map;
  }

  @Override
  public PersistentMap<K, V> with(K key, V value) {
    return wrap(map.assoc(key, value));
  }

  @Override
  public PersistentMap<K, V> without(Object key) {
    try {
      IPersistentMap newMap = map.without(key);
      return wrap(newMap);
    } catch (Exception exception) {
      return this;
    }
  }

  @Override
  public V get(K key) {
    return (V) map.valAt(key);
  }

  @Override
  public boolean containsKey(Object key) {
    return map.valAt(key, NOTFOUND) != NOTFOUND;
  }

  @Override
  public boolean containsValue(Object value) {
    for (Entry<K, V> entry : this) {
      if (PersistentCollections.areEqual(value, entry.getValue())) {
        return true;
      }
    }

    return false;
  }

  @Override
  public int size() {
    return map.count();
  }

  @Override
  public Iterator<Entry<K, V>> iterator() {
    return map.iterator();
  }

  @Override
  protected int hash() {
    return map.hashCode();
  }

  protected abstract PersistentMap<K, V> wrap(IPersistentMap map);
}
