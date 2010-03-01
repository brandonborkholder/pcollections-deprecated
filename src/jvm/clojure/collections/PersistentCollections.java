package clojure.collections;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Map.Entry;

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

  static <E> List<E> asList(Iterable<E> iterable) {
    List<E> list = new ArrayList<E>();
    for (E value : iterable) {
      list.add(value);
    }

    return list;
  }

  static <E> List<E> asList(final E value) {
    List<E> list = new ArrayList<E>(1);
    list.add(value);
    return list;
  }

  public static <E> PersistentStack<E> reverse(PersistentStack<E> stack) {
    PersistentStack<E> newStack = SingleLinkedList.empty();
    for (E value : stack) {
      newStack = newStack.push(value);
    }

    return newStack;
  }

  static <T> PersistentStack<T> withoutHelper(PersistentStack<T> pList, List<?> items) {
    if (items.isEmpty() || pList.isEmpty()) {
      return null;
    } else {
      int index = items.indexOf(pList.peek());
      if (index >= 0) {
        items.remove(index);
        PersistentStack<T> stack = withoutHelper(pList.pop(), items);
        if (stack == null) {
          return pList.pop();
        } else {
          return stack;
        }
      } else {
        PersistentStack<T> stack = withoutHelper(pList.pop(), items);
        if (stack == null) {
          return null;
        } else {
          return stack.push(pList.peek());
        }
      }
    }
  }

  static <T> Iterable<Entry<T, T>> expandIterable(final Iterable<T> iterable) {
    return new Iterable<Entry<T, T>>() {
      @Override
      public Iterator<Entry<T, T>> iterator() {
        return new Iterator<Entry<T, T>>() {
          Iterator<T> itr = iterable.iterator();

          @Override
          public boolean hasNext() {
            return itr.hasNext();
          }

          @Override
          public Entry<T, T> next() {
            T value = itr.next();
            return new SimpleImmutableEntry<T, T>(value, value);
          }

          @Override
          public void remove() {
            throw new UnsupportedOperationException();
          }
        };
      }
    };
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
}
