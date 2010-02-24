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
public interface PersistentQueue<T> extends PersistentCollection<T> {
  @Override
  PersistentQueue<T> with(T value);

  @Override
  PersistentQueue<T> withAll(Iterable<? extends T> values);

  @Override
  PersistentQueue<T> without(Object value);

  @Override
  PersistentQueue<T> withoutAll(Iterable<?> values);

  T peek();

  PersistentQueue<T> pop();

  PersistentQueue<T> offer(T value);
}
