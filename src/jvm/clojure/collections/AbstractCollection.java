package clojure.collections;

/**
 * Copyright (c) Brandon Borkholder. All rights reserved. The use and
 * distribution terms for this software are covered by the Eclipse Public
 * License 1.0 (http://opensource.org/licenses/eclipse-1.0.php) which can be
 * found in the file epl-v10.html at the root of this distribution. By using
 * this software in any fashion, you are agreeing to be bound by the terms of
 * this license. You must not remove this notice, or any other, from this
 * software.
 */
public abstract class AbstractCollection<T> implements PersistentCollection<T> {
  @Override
  public PersistentCollection<T> withAll(Iterable<? extends T> values) {
    PersistentCollection<T> collection = this;
    for (T value : values) {
      collection = collection.with(value);
    }

    return collection;
  }

  @Override
  public PersistentCollection<T> withoutAll(Iterable<?> values) {
    PersistentCollection<T> collection = this;
    for (Object value : values) {
      collection = collection.without(value);
    }

    return collection;
  }

  @Override
  public boolean containsAll(Iterable<?> values) {
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

    return builder.toString();
  }
}
