package clojure.collections;

import java.util.Collection;
import java.util.Random;

public abstract class AbstractTest<T> {
  public static Random RAND = new Random();

  protected PersistentCollection<T> pCollection;

  protected void assertEquivalent() {
    assertEquivalent(pCollection);
  }

  protected void start() {
    pCollection = empty();
  }

  protected void add(T value) {
    pCollection = pCollection.with(value);
    getCollection().add(value);
  }

  protected void addAll(Collection<T> values) {
    pCollection = pCollection.withAll(values);
    getCollection().addAll(values);
  }

  protected void remove(T value) {
    pCollection = pCollection.without(value);
    getCollection().remove(value);
  }

  protected void removeAll(Collection<T> values) {
    pCollection = pCollection.withoutAll(values);
    getCollection().removeAll(values);
  }

  protected abstract PersistentCollection<T> empty();

  protected abstract T random();

  protected abstract Collection<T> getCollection();

  protected void assertEquivalent(PersistentCollection<T> pCollection) {
  }
}