package clojure.collections;

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
public abstract class AbstractMap<K, V> implements PersistentMap<K, V> {
  protected int hashCode = -1;

  @Override
  public boolean isEmpty() {
    return size() == 0;
  }

  @Override
  public PersistentMap<K, V> withAll(Iterable<? extends Entry<? extends K, ? extends V>> iterable) {
    PersistentMap<K, V> map = this;
    for (Entry<? extends K, ? extends V> entry : iterable) {
      map = map.with(entry.getKey(), entry.getValue());
    }

    return map;
  }

  @Override
  public PersistentMap<K, V> withoutAll(Iterable<?> iterable) {
    PersistentMap<K, V> map = this;
    for (Object key : iterable) {
      map = map.without(key);
    }

    return map;
  }

  @Override
  public int hashCode() {
    if (hashCode == -1) {
      hashCode = hash();
    }

    return hashCode;
  }

  protected abstract int hash();
}
