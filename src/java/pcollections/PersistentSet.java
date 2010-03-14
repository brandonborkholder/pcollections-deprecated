package pcollections;

import java.util.Collection;
import java.util.Set;

/**
 * Copyright (c) Brandon Borkholder. All rights reserved. The use and
 * distribution terms for this software are covered by the Eclipse Public
 * License 1.0 (http://opensource.org/licenses/eclipse-1.0.php) which can be
 * found in the file epl-v10.html at the root of this distribution. By using
 * this software in any fashion, you are agreeing to be bound by the terms of
 * this license. You must not remove this notice, or any other, from this
 * software.
 */
public interface PersistentSet<T> extends PersistentCollection<T>, Set<T> {
  @Override
  PersistentSet<T> with(T value);

  @Override
  PersistentSet<T> withAll(Collection<? extends T> values);

  @Override
  PersistentSet<T> without(Object value);

  @Override
  PersistentSet<T> withoutAll(Collection<?> values);
}
