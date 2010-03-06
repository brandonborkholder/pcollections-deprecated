package clojure.collections;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import java.util.Set;

import clojure.collections.adapt.CollectionAdapter;
import clojure.collections.adapt.ListAdapter;
import clojure.collections.adapt.MapAdapter;
import clojure.collections.adapt.RandomListAdapter;
import clojure.collections.adapt.SetAdapter;

/**
 * Copyright (c) Brandon Borkholder. All rights reserved. The use and
 * distribution terms for this software are covered by the Eclipse Public
 * License 1.0 (http://opensource.org/licenses/eclipse-1.0.php) which can be
 * found in the file epl-v10.html at the root of this distribution. By using
 * this software in any fashion, you are agreeing to be bound by the terms of
 * this license. You must not remove this notice, or any other, from this
 * software.
 */
public class PersistentCollections {
  static final Iterator EMPTY_ITR = new Iterator() {
    @Override
    public boolean hasNext() {
      return false;
    }

    @Override
    public Object next() {
      throw new NoSuchElementException();
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }
  };

  static final Comparator DEFAULT_COMPARATOR = new Comparator<Comparable>() {
    @Override
    public int compare(Comparable o1, Comparable o2) {
      return o1.compareTo(o2);
    }
  };

  static <E> Set<E> toSet(Iterable<E> iterable) {
    Set<E> set = new HashSet<E>();
    for (E value : iterable) {
      set.add(value);
    }

    return set;
  }

  static <T> PersistentStack<T> withoutHelper(PersistentStack<T> pList, Object item) {
    if (pList.isEmpty()) {
      return null;
    } else {
      if (areEqual(item, pList.peek())) {
        return pList.pop();
      } else {
        return withoutHelper(pList.pop(), item);
      }
    }
  }

  static <T> PersistentStack<T> withoutHelper(PersistentStack<T> pList, Collection<?> items) {
    if (pList.isEmpty() || items.isEmpty()) {
      return null;
    } else {
      PersistentStack<T> stack = withoutHelper(pList.pop(), items);
      if (items.contains(pList.peek())) {
        if (stack == null) {
          stack = pList.pop();
        }

        return stack;
      } else if (stack == null) {
        return null;
      } else {
        return stack.push(pList.peek());
      }
    }
  }

  static boolean orderAwareEquals(PersistentCollection<?> a, PersistentCollection<?> b) {
    if (a.size() == b.size()) {
      Iterator<?> aItr = a.iterator();
      Iterator<?> bItr = b.iterator();
      while (aItr.hasNext()) {
        if (!aItr.next().equals(bItr.next())) {
          return false;
        }
      }

      return true;
    } else {
      return false;
    }
  }

  /*
   * TODO This doesn't account for the number of times a value occurs.
   */
  static boolean equals(PersistentCollection<?> a, PersistentCollection<?> b) {
    if (a.size() == b.size()) {
      for (Object value : a) {
        if (!b.contains(value)) {
          return false;
        }
      }

      return true;
    } else {
      return false;
    }
  }

  static int orderAwareHashCode(PersistentCollection<?> collection) {
    int hash = 7;
    for (Object value : collection) {
      hash = 31 * hash + (value == null ? 0 : value.hashCode());
    }

    return hash;
  }

  static int hashCode(PersistentCollection<?> collection) {
    int hash = 3;
    for (Object value : collection) {
      hash += (value == null ? 0 : value.hashCode());
    }

    return hash;
  }

  static boolean areEqual(Object o1, Object o2) {
    return o1 == o2 || (o1 != null && o1.equals(o2));
  }

  public static <T extends Comparable<? super T>> T min(PersistentCollection<T> pCollection) {
    return Collections.min(asCollection(pCollection));
  }

  public static <T> T min(PersistentCollection<T> pCollection, Comparator<? super T> comparator) {
    return Collections.min(asCollection(pCollection), comparator);
  }

  public static <T extends Comparable<? super T>> T max(PersistentCollection<T> pCollection) {
    return Collections.max(asCollection(pCollection));
  }

  public static <T> T max(PersistentCollection<T> pCollection, Comparator<? super T> comparator) {
    return Collections.max(asCollection(pCollection), comparator);
  }

  public static <T extends Comparable<? super T>> int binarySearch(PersistentList<T> list, T key) {
    return Collections.binarySearch(asList(list), key);
  }

  public static <T> int binarySearch(PersistentList<T> list, T key, Comparator<? super T> comparator) {
    return Collections.binarySearch(asList(list), key, comparator);
  }

  public static <T, P extends PersistentCollection<T>> P intersection(P a, P b) {
    Set<T> unwanted = new HashSet<T>(asCollection(a));
    for (T value : b) {
      unwanted.remove(value);
    }

    return (P) a.withoutAll(unwanted);
  }

  public static <E> PersistentStack<E> reverse(PersistentStack<E> stack) {
    PersistentStack<E> newStack = SingleLinkedList.empty();
    for (E value : stack) {
      newStack = newStack.push(value);
    }

    return newStack;
  }

  public static <T> Set<T> asSet(PersistentSet<T> set) {
    return new SetAdapter<T>(set);
  }

  public static <T> Collection<T> asCollection(PersistentCollection<T> collection) {
    return new CollectionAdapter<T>(collection);
  }

  public static <T> List<T> asList(PersistentList<T> list) {
    if (list instanceof RandomAccess) {
      return new RandomListAdapter<T>(list);
    } else {
      return new ListAdapter<T>(list);
    }
  }

  public static <K, V> Map<K, V> asMap(PersistentMap<K, V> map) {
    return new MapAdapter<K, V>(map);
  }
}
