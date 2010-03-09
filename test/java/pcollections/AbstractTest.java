package pcollections;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.Map.Entry;

import org.junit.Before;

import pcollections.PersistentCollection;

public abstract class AbstractTest<T, P extends PersistentCollection<T>, C extends Collection<T>> {
  public static Random RAND = new Random();

  protected List<Entry<C, P>> checkpoints = new ArrayList<Entry<C, P>>();

  protected P pCollection;

  protected void assertEquivalent() {
    checkpoint();
    for (Entry<C, P> entry : checkpoints) {
      assertEquivalent(entry.getKey(), entry.getValue());
    }
  }

  @Before
  public void start() {
    pCollection = empty();
  }

  protected void add(T value) {
    pCollection = (P) pCollection.with(value);
    getCollection().add(value);
    checkpoint();
  }

  protected void addAll(Collection<T> values) {
    pCollection = (P) pCollection.withAll(values);
    getCollection().addAll(values);
    checkpoint();
  }

  protected void remove(T value) {
    pCollection = (P) pCollection.without(value);
    getCollection().remove(value);
    checkpoint();
  }

  protected void removeAll(Collection<T> values) {
    pCollection = (P) pCollection.withoutAll(values);
    getCollection().removeAll(values);
    checkpoint();
  }

  protected void checkpoint() {
    checkpoints.add(new AbstractMap.SimpleImmutableEntry<C, P>(cloneCollection(), pCollection));
  }

  protected abstract P empty();

  protected abstract T random();

  protected abstract C getCollection();

  protected abstract C cloneCollection();

  protected void assertEquivalent(C collection, P pCollection) {
  }
}