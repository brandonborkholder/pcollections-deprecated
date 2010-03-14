package pcollections;

import java.util.Collection;
import java.util.Iterator;
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
class BaseSet<T> extends AbstractCollection<T> implements PersistentSet<T> {
  protected final PersistentMap<T, ?> map;

  public BaseSet(PersistentMap<T, ?> map) {
    this.map = map;
  }

  @Override
  public boolean contains(Object value) {
    return map.containsKey(value);
  }

  @Override
  public int size() {
    return map.size();
  }

  @Override
  public Iterator<T> iterator() {
    return new KeyItr();
  }

  @Override
  public PersistentSet<T> with(T value) {
    return wrap(map.with(value, null));
  }

  @Override
  public PersistentSet<T> withAll(Collection<? extends T> values) {
    PersistentMap<T, ?> newMap = map;
    for (T value : values) {
      newMap = newMap.with(value, null);
    }

    return wrap(newMap);
  }

  @Override
  public PersistentSet<T> without(Object value) {
    return wrap(map.without(value));
  }

  @Override
  public PersistentSet<T> withoutAll(Collection<?> values) {
    return wrap(map.withoutAll(values));
  }

  protected PersistentSet<T> wrap(PersistentMap<T, ?> map) {
    return new BaseSet<T>(map);
  }

  @Override
  protected int hash() {
    return PersistentCollections.hashCode(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    } else if (obj instanceof PersistentSet<?>) {
      return PersistentCollections.equals(this, (PersistentSet<?>) obj);
    } else {
      return false;
    }
  }

  protected class KeyItr extends ImmutableItr<T> {
    private final Iterator<? extends Entry<T, ?>> itr = map.iterator();

    @Override
    public boolean hasNext() {
      return itr.hasNext();
    }

    @Override
    public T next() {
      return itr.next().getKey();
    }
  }
}
