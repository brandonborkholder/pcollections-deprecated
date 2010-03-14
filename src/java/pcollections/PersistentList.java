package pcollections;

import java.util.Collection;
import java.util.List;

/**
 * Copyright (c) Brandon Borkholder. All rights reserved. The use and
 * distribution terms for this software are covered by the Eclipse Public
 * License 1.0 (http://opensource.org/licenses/eclipse-1.0.php) which can be
 * found in the file epl-v10.html at the root of this distribution. By using
 * this software in any fashion, you are agreeing to be bound by the terms of
 * this license. You must not remove this notice, or any other, from this
 * software.
 */
public interface PersistentList<T> extends PersistentCollection<T>, List<T> {
  /**
   * Returns a new {@code PersistentList} with the given value appended to the
   * end of the contents of this list.
   *
   * @param value
   *          the value to add to this list
   * @return a {@code PersistentList} with the value at the end of the list
   */
  @Override
  PersistentList<T> with(T value);

  /**
   * Returns a new {@code PersistentList} with the given values appended to the
   * end of the contents of this list. The values are appended in the order
   * returned by the {@code Iterator} of {@code values}.
   *
   * @param values
   *          the {@code Iterable} of values to append
   * @return a {@code PersistentList} with the values appended to the list in
   *         order
   */
  @Override
  PersistentList<T> withAll(Collection<? extends T> values);

  /**
   * Returns a new {@code PersistentList} with {@code value} at index {@code
   * index} in the new list. This will replace the value at the given index in
   * this list; it will not shift the contents of the list.
   *
   * @param index
   *          the index for the given value
   * @param value
   *          the value to set
   * @return a {@code PersistentList} with the new value at the given index
   * @throws IndexOutOfBoundsException
   *           if the index is out of range
   */
  PersistentList<T> with(int index, T value);

  /**
   * Returns a {@code PersistentList} which does not contain the first
   * occurrence of {@code value} in this list. The contents of the list after
   * the first occurrence of {@code value} will be shifted down by 1. If this
   * list does not contain {@code value}, the returned list will be equivalent
   * to {@code this}.
   *
   * @param value
   *          the value to remove from this list
   * @return a {@code PersistentList} which does not contain the first
   *         occurrence of {@code value}
   */
  @Override
  PersistentList<T> without(Object value);

  /**
   * Returns a {@code PersistentList} which does not contain any of the values
   * in the given {@code Iterable}. If this list does not contain any of the
   * given values, the returned list will be equivalent to {@code this}.
   *
   * @param values
   *          the values to remove from this list
   * @return a {@code PersistentList} which does not contain any of the values
   *         in the given {@code Iterable}
   */
  @Override
  PersistentList<T> withoutAll(Collection<?> values);

  /**
   * Returns a {@code PersistentList} which is has removed the value at index
   * {@code index} in this list. The list is equivalent to this list for
   * elements in indices 0 through {@code index} - 1, and {@code index} + 1
   * through {@code #size()} shifted down by one index.
   *
   * @param index
   *          the index to remove
   * @return a {@code PersistentList} with all elements after {@code index}
   *         shifted down by one
   * @throws IndexOutOfBoundsException
   *           if the index is out of range
   */
  PersistentList<T> without(int index);

  /**
   * Returns a {@code PersistentList} including only the elements between the
   * given {@code fromIndex} and {@code toIndex}. If {@code fromIndex} and
   * {@code toIndex} are equal, the returned list is empty. The returned list
   * contains elements of this list at indexes from {@code fromIndex}
   * (inclusive) to {@code toIndex} (exclusive).
   *
   * @param fromIndex
   *          the first index for which to return a sublist
   * @param toIndex
   *          the last (exclusive) index for which to return a sublist
   * @return a {@code PersistentList} that contains the sublist
   * @throws IndexOutOfBoundsException
   *           if the indexes are not contained within this list or are
   *           decreasing
   */
  @Override
  PersistentList<T> subList(int fromIndex, int toIndex);

  @Override
  @Deprecated
  void add(int index, T element);

  @Override
  @Deprecated
  boolean addAll(int index, Collection<? extends T> c);

  @Override
  @Deprecated
  public T remove(int index);

  @Override
  @Deprecated
  T set(int index, T element);
}
