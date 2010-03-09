package pcollections;

import java.util.Collection;
import java.util.Iterator;

/**
 * Copyright (c) Brandon Borkholder. All rights reserved. The use and
 * distribution terms for this software are covered by the Eclipse Public
 * License 1.0 (http://opensource.org/licenses/eclipse-1.0.php) which can be
 * found in the file epl-v10.html at the root of this distribution. By using
 * this software in any fashion, you are agreeing to be bound by the terms of
 * this license. You must not remove this notice, or any other, from this
 * software.
 */
public class LinkedQueue<T> extends AbstractCollection<T> implements PersistentQueue<T> {
  private static final LinkedQueue EMPTY = new LinkedQueue(SingleLinkedList.empty(), SingleLinkedList.empty());

  protected final PersistentStack<T> front;

  protected final PersistentStack<T> rear;

  public static <T> LinkedQueue<T> empty() {
    return EMPTY;
  }

  protected LinkedQueue(PersistentStack<T> front, PersistentStack<T> rear) {
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
  public PersistentQueue<T> with(T value) {
    return offer(value);
  }

  @Override
  public PersistentQueue<T> withAll(Iterable<? extends T> values) {
    return (PersistentQueue<T>) super.withAll(values);
  }

  @Override
  public PersistentQueue<T> without(Object value) {
    PersistentStack<T> newFront = PersistentCollections.withoutHelper(front, value);
    if (newFront == null) {
      PersistentStack<T> newRear = PersistentCollections.withoutHelper(rear, value);
      if (newRear == null) {
        return this;
      } else {
        return new LinkedQueue<T>(front, newRear);
      }
    } else {
      return new LinkedQueue<T>(newFront, rear);
    }
  }

  @Override
  public PersistentQueue<T> withoutAll(Iterable<?> values) {
    Collection<?> collection = PersistentCollections.toSet(values);
    PersistentStack<T> newFront = PersistentCollections.withoutHelper(front, collection);
    PersistentStack<T> newRear = PersistentCollections.withoutHelper(rear, collection);

    if (newRear == null) {
      newRear = rear;
    }

    if (newFront == null) {
      newFront = front;
    } else if (newFront.isEmpty()) {
      newFront = PersistentCollections.reverse(newRear);
      newRear = SingleLinkedList.empty();
    }

    return new LinkedQueue<T>(newFront, newRear);
  }

  @Override
  public PersistentQueue<T> pop() {
    PersistentStack<T> newFront = front.pop();
    PersistentStack<T> newRear = rear;
    if (newFront.isEmpty()) {
      newFront = PersistentCollections.reverse(rear);
      newRear = SingleLinkedList.empty();
    }

    return new LinkedQueue<T>(newFront, newRear);
  }

  @Override
  public PersistentQueue<T> offer(T value) {
    if (front.isEmpty()) {
      return new LinkedQueue<T>(front.push(value), rear);
    } else {
      return new LinkedQueue<T>(front, rear.push(value));
    }
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    } else if (obj instanceof PersistentQueue<?>) {
      return PersistentCollections.orderAwareEquals(this, (PersistentCollection<?>) obj);
    } else {
      return false;
    }
  }

  @Override
  protected int hash() {
    return PersistentCollections.orderAwareHashCode(this);
  }

  protected class Itr extends ImmutableItr<T> {
    Iterator<T> firstItr = LinkedQueue.this.front.iterator();

    Iterator<T> secondItr = PersistentCollections.reverse(LinkedQueue.this.rear).iterator();

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
  }
}
