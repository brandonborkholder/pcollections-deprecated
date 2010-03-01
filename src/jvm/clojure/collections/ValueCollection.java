package clojure.collections;

import java.util.Collection;
import java.util.HashSet;
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
public class ValueCollection<T> extends AbstractCollection<T> {
  protected final PersistentMap<?, T> map;

  protected final PersistentStack<T> appended;

  public ValueCollection(PersistentMap<?, T> map) {
    this(map, SingleLinkedList.<T> empty());
  }

  protected ValueCollection(PersistentMap<?, T> map, PersistentStack<T> appended) {
    this.map = map;
    this.appended = appended;
  }

  @Override
  public boolean contains(Object value) {
    return map.containsValue(value);
  }

  @Override
  public PersistentCollection<T> with(T value) {
    return new ValueCollection<T>(map, appended.with(value));
  }

  @Override
  public PersistentCollection<T> withAll(Iterable<? extends T> values) {
    return new ValueCollection<T>(map, appended.withAll(values));
  }

  @Override
  public PersistentCollection<T> without(Object value) {
    PersistentStack<T> stack = PersistentCollections.withoutHelper(appended, PersistentCollections.asList(value));
    if (stack == null) {
      Iterator<? extends Entry<?, T>> itr = map.iterator();
      while (itr.hasNext()) {
        Entry<?, T> entry = itr.next();
        if (PersistentCollections.areEqual(entry.getValue(), value)) {
          return new ValueCollection<T>(map.without(entry.getKey()), appended);
        }
      }

      return this;
    } else {
      return new ValueCollection<T>(map, stack);
    }
  }

  @Override
  public PersistentCollection<T> withoutAll(Iterable<?> values) {
    Collection<Object> collection = new HashSet<Object>(PersistentCollections.asList(values));
    PersistentStack<T> stack = PersistentCollections.withoutHelper(appended, PersistentCollections.asList(values));
    if (stack == null) {
      stack = appended;
    }

    PersistentMap<?, T> newMap = map;
    Iterator<? extends Entry<?, T>> itr = map.iterator();
    while (itr.hasNext()) {
      Entry<?, T> entry = itr.next();
      if (collection.contains(entry.getValue())) {
        newMap = newMap.without(entry.getKey());
      }
    }

    return new ValueCollection<T>(newMap, stack);
  }

  @Override
  protected int hash() {
    return PersistentCollections.hashCode(this);
  }

  @Override
  public int size() {
    return map.size();
  }

  @Override
  public Iterator<T> iterator() {
    return new ValueItr();
  }

  protected class ValueItr extends ImmutableItr<T> {
    Iterator<? extends Entry<?, T>> itr = map.iterator();

    Iterator<T> appendedItr = appended == null ? PersistentCollections.EMPTY_ITR : appended.iterator();

    @Override
    public boolean hasNext() {
      return itr.hasNext() || appendedItr.hasNext();
    }

    @Override
    public T next() {
      if (itr.hasNext()) {
        return itr.next().getValue();
      } else {
        return appendedItr.next();
      }
    }
  }
}
