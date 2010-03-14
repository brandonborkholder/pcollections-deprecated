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
/**
 * The root interface in the <i>persistent collection hierarchy</i>. The
 * persistent collections hierarchy reflects the hierarchy of
 * {@link java.util.Collection}s and intends to follow Java idioms where
 * appropriate. {@code with(Object)} is analogous to {@code
 * Collection.add(Object)}, {@code without(Object)} is analogous to {@code
 * Collection.remove(Object)}. All mutation methods from {@code Collection} are
 * marked as deprecated and will throw an {@link UnsupportedOperationException}.
 * <p>
 * A persistent collection is defined to be a collection of objects, where each
 * version of the collection may be accessed and be further manipulated. This is
 * generally termed "fully persistent".
 * </p>
 *
 * @author Brandon Borkholder
 * @version March 3, 2010
 */
public interface PersistentCollection<T> extends Collection<T> {
  /**
   * Returns a collection that includes all the values in this {@code
   * PersistentCollection} and the provided {@code value}. The returned
   * collection may not be different than this collection (as in the case of a
   * set), but the new collection is guaranteed to include the value at least
   * once.
   *
   * @param value
   *          the value to include in the collection
   * @return a collection that contains the given value
   */
  PersistentCollection<T> with(T value);

  /**
   * Returns a collection that includes all the values in this {@code
   * PersistentCollection} and the provided {@code values}. As in
   * {@link #with(Object)}, the returned collection is only guaranteed to
   * contain the provided values, not necessarily adding them if they are
   * present already.
   *
   * @param values
   *          an {@code Iterable} representing all the values to add
   * @return a collection that contains all the given values
   */
  PersistentCollection<T> withAll(Collection<? extends T> values);

  /**
   * Returns a collection that does not contain the first occurrence of {@code
   * value} in this collection. If this collection does not contain the given
   * value, the returned collection may be {@code this}.
   *
   * @param value
   *          the value to remove
   * @return a collection that does not contain the given value in the first
   *         place it occurs
   */
  PersistentCollection<T> without(Object value);

  /**
   * Returns a collection that does not contain any of the values in the given
   * {@code Iterable}. If this collection does not intersect the {@code
   * Iterable}, the return collection may be {@code this}.
   *
   * @param values
   *          the values to remove
   * @return a collection that does not contain any of the values from {@code
   *         values}
   */
  PersistentCollection<T> withoutAll(Collection<?> values);

  @Override
  @Deprecated
  boolean add(T e);

  @Override
  @Deprecated
  boolean addAll(Collection<? extends T> c);

  @Override
  @Deprecated
  public boolean remove(Object o);

  @Override
  @Deprecated
  public void clear();

  @Override
  @Deprecated
  public boolean removeAll(Collection<?> c);

  @Override
  @Deprecated
  public boolean retainAll(Collection<?> c);
}
