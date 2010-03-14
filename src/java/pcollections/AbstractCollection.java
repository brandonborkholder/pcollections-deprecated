package pcollections;

import java.util.Collection;

/**
 * Copyright (c) Brandon Borkholder. All rights reserved. The use and
 * distribution terms for this software are covered by the Eclipse Public
 * License 1.0 (http://opensource.org/licenses/eclipse-1.0.php) which can be
 * found in the file epl-v10.html at the root of this distribution. By using
 * this software in any fashion, you are agreeing to be bound by the terms of
 * this license. You must not remove this notice, or any other, from this
 * software.
 */
abstract class AbstractCollection<T> implements PersistentCollection<T> {
  static final String PERSISTENT_ERROR = "Cannot modify persistent collection.";

  protected int hashCode = -1;

  @Override
  public PersistentCollection<T> withAll(Collection<? extends T> values) {
    PersistentCollection<T> collection = this;
    for (T value : values) {
      collection = collection.with(value);
    }

    return collection;
  }

  @Override
  public boolean containsAll(Collection<?> values) {
    for (Object value : values) {
      if (!contains(value)) {
        return false;
      }
    }

    return true;
  }

  @Override
  public boolean isEmpty() {
    return size() == 0;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder("[");
    boolean first = true;
    for (T value : this) {
      if (first) {
        first = false;
      } else {
        builder.append(", ");
      }

      builder.append(value);
    }

    return builder.append("]").toString();
  }

  @Override
  public int hashCode() {
    if (hashCode == -1) {
      hashCode = hash();
    }

    return hashCode;
  }

  protected abstract int hash();

  @Override
  public Object[] toArray() {
    return toArray(null);
  }

  @Override
  public <U extends Object> U[] toArray(U[] array) {
    int size = size();
    U[] r = array.length >= size ? array : (U[]) java.lang.reflect.Array.newInstance(array.getClass().getComponentType(), size);
    iterator();

    int count = 0;
    for (T value : this) {
      r[count++] = (U) value;
    }

    if (count < r.length - 1) {
      r[count] = null;
    }

    return r;
  }

  @Override
  public boolean add(T e) {
    throw new UnsupportedOperationException(PERSISTENT_ERROR);
  }

  @Override
  public boolean addAll(Collection<? extends T> c) {
    throw new UnsupportedOperationException(PERSISTENT_ERROR);
  }

  @Override
  public void clear() {
    throw new UnsupportedOperationException(PERSISTENT_ERROR);
  }

  @Override
  public boolean remove(Object o) {
    throw new UnsupportedOperationException(PERSISTENT_ERROR);
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    throw new UnsupportedOperationException(PERSISTENT_ERROR);
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    throw new UnsupportedOperationException(PERSISTENT_ERROR);
  }
}
