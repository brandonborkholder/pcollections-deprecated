package clojure.collections;

import java.util.Collection;
import java.util.Random;

import org.junit.Before;

public abstract class AbstractTest<T, C extends PersistentCollection<T>> {
  public static Random RAND = new Random();

  protected C pCollection;

  protected void assertEquivalent() {
    assertEquivalent(pCollection);
  }

  @Before
  public void start() {
    pCollection = empty();
  }

  protected void add(T value) {
    pCollection = (C) pCollection.with(value);
    getCollection().add(value);
  }

  protected void addAll(Collection<T> values) {
    pCollection = (C) pCollection.withAll(values);
    getCollection().addAll(values);
  }

  protected void remove(T value) {
    pCollection = (C) pCollection.without(value);
    getCollection().remove(value);
  }

  protected void removeAll(Collection<T> values) {
    pCollection = (C) pCollection.withoutAll(values);
    getCollection().removeAll(values);
  }

  protected abstract C empty();

  protected abstract T random();

  protected abstract Collection<T> getCollection();

  protected void assertEquivalent(C pCollection) {
  }
}