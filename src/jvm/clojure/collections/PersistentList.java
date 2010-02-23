package clojure.collections;

/**
 * Copyright (c) Brandon Borkholder. All rights reserved. The use and distribution
 * terms for this software are covered by the Eclipse Public License 1.0
 * (http://opensource.org/licenses/eclipse-1.0.php) which can be found in the
 * file epl-v10.html at the root of this distribution. By using this software in
 * any fashion, you are agreeing to be bound by the terms of this license. You
 * must not remove this notice, or any other, from this software.
 */
public interface PersistentList<T> extends PersistentCollection<T> {
	T get(int index);

	PersistentList<T> with(T value);

	PersistentList<T> withAll(Iterable<? extends T> values);

	PersistentList<T> with(int index, T value);

	int indexOf(Object value);

	int lastIndexOf(Object value);

	PersistentList<T> without(Object value);

	PersistentList<T> withoutAll(Iterable<?> values);

	PersistentList<T> without(int index);

	PersistentList<T> subList(int fromIndex, int toIndex);
}
