package pcollections;

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
 * Collection.remove(Object)}.
 * <p>
 * A persistent collection is defined to be a collection of objects, where each
 * version of the collection may be accessed and be further manipulated. This is
 * generally termed "fully persistent". This does not necessarily mean that each
 * collection is immutable and thread-safe.
 * </p>
 * 
 * @author Brandon Borkholder
 * @version March 3, 2010
 */
public interface PersistentCollection<T> extends Iterable<T> {
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
  PersistentCollection<T> withAll(Iterable<? extends T> values);

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
  PersistentCollection<T> withoutAll(Iterable<?> values);

  /**
   * Returns {@code true} the provided {@code value} is contained in this
   * collection.
   * 
   * @param value
   *          the element to check
   * @return {@code true} if the element is contained in this collection,
   *         {@code false} otherwise
   */
  boolean contains(Object value);

  /**
   * Returns {@code true} if and only if all of the values returned by the
   * {@code Iterable} {@code values} are contained in this collection.
   * 
   * @param values
   *          the {@code Iterable} of values to check
   * @return {@code true} if every element is contained in this set, {@code
   *         false} otherwise
   */
  boolean containsAll(Iterable<?> values);

  /**
   * Returns the number of values in this collection. If this number is more
   * than {@link Integer#MAX_VALUE}, returns {@code Integer.MAX_VALUE}.
   * 
   * @return number of elements in the collection
   */
  int size();

  /**
   * Returns {@code true} if and only if this collection contains no elements.
   * This is equivalent to the test {@code size() == 0}.
   * 
   * @return {@code true} if the collection is empty, {@code false} otherwise
   */
  boolean isEmpty();

  /**
   * Returns {@code true} if the given object is equal to this {@code
   * PersistentCollection}. As with {@link java.util.Collection#equals(Object)},
   * this interface does not add stipulations to the general {@code
   * Object.equals(Object)}, but implementations are encouraged to override this
   * method and compare equality based on elements of the collection.
   * <p>
   * Note that a {@code PersistentCollection} is never equal to a {@code
   * java.util.Collection}.
   * </p>
   * 
   * @param obj
   *          the object to check for equality
   * @return {@code true} if the given object is equal to this
   */
  @Override
  boolean equals(Object obj);

  /**
   * Returns the hash code value for this object. As with
   * {@link java.util.Collection#hashCode()}, this interface does not add
   * stipulations to the general {@code Object.hashCode()}, but implementations
   * are encouraged to return a hash code based on the content of the collection
   * and also appropriately override {@code equals(Object)}.
   * 
   * @return the hash code for this collection
   */
  @Override
  int hashCode();
}
