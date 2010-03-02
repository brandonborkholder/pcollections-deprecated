package clojure.collections;

import java.util.Iterator;
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
public class SingleLinkedList<T> extends AbstractCollection<T> implements PersistentStack<T> {
  private static final SingleLinkedList EMPTY = new Empty();

  protected final T value;

  protected final SingleLinkedList<T> next;

  protected final int size;

  public static <T> SingleLinkedList<T> empty() {
    return EMPTY;
  }

  protected SingleLinkedList() {
    this.next = null;
    this.value = null;
    this.size = 0;
  }

  protected SingleLinkedList(SingleLinkedList<T> next, T value) {
    this.next = next;
    this.value = value;
    this.size = next.size() + 1;
  }

  @Override
  public boolean contains(Object value) {
    return isEqualValue(value) || next.contains(value);
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public Iterator<T> iterator() {
    return new Itr<T>(this);
  }

  @Override
  public PersistentStack<T> push(T value) {
    return new SingleLinkedList<T>(this, value);
  }

  @Override
  public PersistentStack<T> with(T value) {
    return push(value);
  }

  @Override
  public PersistentStack<T> withAll(Iterable<? extends T> values) {
    return (PersistentStack<T>) super.withAll(values);
  }

  @Override
  public PersistentStack<T> without(Object value) {
    PersistentStack<T> stack = PersistentCollections.withoutHelper(this, value);
    if (stack == null) {
      return this;
    } else {
      return stack;
    }
  }

  @Override
  public PersistentStack<T> withoutAll(Iterable<?> values) {
    PersistentStack<T> stack = PersistentCollections.withoutHelper(this, PersistentCollections.toSet(values));
    if (stack == null) {
      return this;
    } else {
      return stack;
    }
  }

  @Override
  public T peek() {
    return value;
  }

  @Override
  public PersistentStack<T> pop() {
    return next;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    } else if (obj instanceof PersistentStack<?>) {
      return PersistentCollections.orderAwareEquals(this, (PersistentStack<?>) obj);
    } else {
      return false;
    }
  }

  @Override
  protected int hash() {
    return PersistentCollections.orderAwareHashCode(this);
  }

  protected boolean isEqualValue(Object other) {
    return PersistentCollections.areEqual(value, other);
  }

  protected static class Itr<T> extends ImmutableItr<T> {
    SingleLinkedList<T> current;

    public Itr(SingleLinkedList<T> head) {
      current = head;
    }

    @Override
    public boolean hasNext() {
      return current != EMPTY;
    }

    @Override
    public T next() {
      if (hasNext()) {
        T value = current.value;
        current = current.next;
        return value;
      } else {
        throw new NoSuchElementException();
      }
    }
  }

  protected static final class Empty<T> extends SingleLinkedList<T> {
    @Override
    public boolean contains(Object value) {
      return false;
    }

    @Override
    public boolean containsAll(Iterable<?> values) {
      return false;
    }

    @Override
    public int size() {
      return 0;
    }

    @Override
    public Iterator<T> iterator() {
      return PersistentCollections.EMPTY_ITR;
    }

    @Override
    public T peek() {
      throw new NoSuchElementException();
    }

    @Override
    public PersistentStack<T> pop() {
      return this;
    }

    @Override
    public PersistentStack<T> without(Object value) {
      return this;
    }

    @Override
    public PersistentStack<T> withoutAll(Iterable<?> values) {
      return this;
    }
  }
}
