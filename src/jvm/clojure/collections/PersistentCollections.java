package clojure.collections;

import java.util.AbstractSet;
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

  static <E> Set<E> container(final Iterable<E> iterable) {
    return new AbstractSet<E>() {
      @Override
      public Iterator<E> iterator() {
        return iterable.iterator();
      }

      @Override
      public int size() {
        int count = 0;
        for (Iterator<E> itr = iterator(); itr.hasNext(); itr.next()) {
          count++;
        }
        return count;
      }
    };
  }

  static <E> Set<E> container(final E value) {
    return new AbstractSet<E>() {
      @Override
      public Iterator<E> iterator() {
        return new Iterator<E>() {
          boolean first = true;

          @Override
          public boolean hasNext() {
            return first;
          }

          @Override
          public E next() {
            if (first) {
              first = false;
              return value;
            } else {
              throw new NoSuchElementException();
            }
          }

          @Override
          public void remove() {
            throw new UnsupportedOperationException();
          }
        };
      }

      @Override
      public int size() {
        return 1;
      }
    };
  }
}
