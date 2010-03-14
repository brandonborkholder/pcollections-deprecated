package pcollections;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
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

  public static <T, P extends PersistentCollection<T>> P intersection(P a, P b) {
    Set<T> unwanted = new HashSet<T>(a);
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
}
