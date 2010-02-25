package clojure.collections;

import java.util.Iterator;
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
public class LinkedDeque<T> extends AbstractCollection<T> implements PersistentDeque<T> {
  private static final LinkedDeque EMPTY = new LinkedDeque(SingleLinkedList.empty(), SingleLinkedList.empty());

  protected final PersistentStack<T> front;

  protected final PersistentStack<T> rear;

  public static <T> LinkedDeque<T> empty() {
    return EMPTY;
  }

  protected LinkedDeque(PersistentStack<T> front, PersistentStack<T> rear) {
    this.front = front;
    this.rear = rear;
  }

  @Override
  public T peek() {
    return front.peek();
  }

  @Override
  public boolean contains(Object value) {
    return front.contains(value) || rear.contains(value);
  }

  @Override
  public int size() {
    return front.size() + rear.size();
  }

  @Override
  public Iterator<T> iterator() {
    return new Itr();
  }

  @Override
  public PersistentDeque<T> with(T value) {
    return offer(value);
  }

  @Override
  public PersistentDeque<T> withAll(Iterable<? extends T> values) {
    return (PersistentDeque<T>) super.withAll(values);
  }

  @Override
  public PersistentDeque<T> without(Object value) {
    return without(PersistentCollections.asList(value));
  }

  @Override
  public PersistentDeque<T> withoutAll(Iterable<?> values) {
    return without(PersistentCollections.asList(values));
  }

  protected PersistentDeque<T> without(List<?> list) {
    PersistentStack<T> newFront = PersistentCollections.withoutHelper(front, list);
    if (newFront == null) {
      newFront = front;
    }

    PersistentStack<T> newRear = PersistentCollections.withoutHelper(rear, list);
    if (newRear == null) {
      newRear = rear;
    }

    if (newFront.isEmpty()) {
      newFront = PersistentCollections.reverse(newRear);
      newRear = SingleLinkedList.empty();
    }

    return new LinkedDeque<T>(newFront, newRear);
  }

  @Override
  public PersistentDeque<T> pop() {
    PersistentStack<T> newFront = front.pop();
    PersistentStack<T> newRear = rear;
    if (newFront.isEmpty()) {
      newFront = PersistentCollections.reverse(rear);
      newRear = SingleLinkedList.empty();
    }

    return new LinkedDeque<T>(newFront, newRear);
  }

  @Override
  public PersistentDeque<T> push(T value) {
    return new LinkedDeque<T>(front.push(value), rear);
  }

  @Override
  public PersistentDeque<T> offer(T value) {
    if (front.isEmpty()) {
      return new LinkedDeque<T>(front.push(value), rear);
    } else {
      return new LinkedDeque<T>(front, rear.push(value));
    }
  }

  @Override
  public T first() {
    return front.peek();
  }

  @Override
  public T last() {
    if (rear.isEmpty()) {
      T value = null;
      for (T v : front) {
        value = v;
      }

      return value;
    } else {
      return rear.peek();
    }
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    } else if (obj instanceof PersistentQueue<?> || obj instanceof PersistentStack<?>) {
      return PersistentCollections.orderAwareEquals(this, (PersistentCollection<?>) obj);
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return PersistentCollections.orderAwareHashCode(this);
  }

  protected class Itr implements Iterator<T> {
    Iterator<T> firstItr = LinkedDeque.this.front.iterator();

    Iterator<T> secondItr = PersistentCollections.reverse(LinkedDeque.this.rear).iterator();

    @Override
    public boolean hasNext() {
      return firstItr.hasNext() || secondItr.hasNext();
    }

    @Override
    public T next() {
      if (firstItr.hasNext()) {
        return firstItr.next();
      } else {
        return secondItr.next();
      }
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }
}
