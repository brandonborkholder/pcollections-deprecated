package clojure.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

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

  static boolean equals(PersistentCollection<?> a, PersistentCollection<?> b) {
    return false;
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
}
