package clojure.collections;

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
public class LinkedDeque<T> extends AbstractCollection<T> implements PersistentDeque<T> {
  protected PersistentStack<T> front;

  protected PersistentStack<T> rear;

  @Override
  public T peek() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean contains(Object value) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public int size() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public Iterator<T> iterator() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public PersistentDeque<T> with(T value) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public PersistentDeque<T> withAll(Iterable<? extends T> values) {
    return (PersistentDeque) super.withAll(values);
  }

  @Override
  public PersistentDeque<T> without(Object value) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public PersistentDeque<T> withoutAll(Iterable<?> values) {
    return (PersistentDeque) super.withoutAll(values);
  }

  @Override
  public PersistentDeque<T> pop() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public PersistentDeque<T> push(T value) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public PersistentDeque<T> offer(T value) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public T first() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public T last() {
    // TODO Auto-generated method stub
    return null;
  }

}
