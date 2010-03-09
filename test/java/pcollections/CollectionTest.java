package pcollections;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.HashSet;

import org.junit.Test;

import pcollections.PersistentCollection;

public abstract class CollectionTest<T, P extends PersistentCollection<T>, C extends Collection<T>> extends AbstractTest<T, P, C> {
  @Test
  public void singleWithTest() {
    add(random());
    assertEquivalent();
  }

  @Test
  public void withWithoutTest() {
    T value = random();
    add(value);
    remove(value);
    assertEquivalent();
  }

  @Test
  public void removeEmptyTest() {
    T value = random();
    remove(value);
    assertEquivalent();
  }

  @Test
  public void addManyTest() {
    for (int i = 0; i < 5; i++) {
      add(random());
    }

    assertEquivalent();
  }

  @Test
  public void addAllTest() {
    Collection<T> added = new HashSet<T>();
    for (int i = 0; i < 10; i++) {
      added.add(random());
    }

    addAll(added);
    assertEquivalent();
  }

  @Test
  public void removeSomeTest() {
    Collection<T> added = new HashSet<T>();
    for (int i = 0; i < 10; i++) {
      T value = random();
      added.add(value);
      add(value);
    }

    for (int i = 0; i < 10; i++) {
      add(random());
    }

    removeAll(added);
    assertEquivalent();
  }

  @Test
  public void removeAllTest() {
    Collection<T> added = new HashSet<T>();
    for (int i = 0; i < 10; i++) {
      T value = random();
      added.add(value);
      add(value);
    }

    removeAll(added);
    assertEquivalent();
  }

  @Override
  protected void assertEquivalent(C collection, P pCollection) {
    assertEquals(collection.size(), pCollection.size());
    for (T value : collection) {
      assertTrue(pCollection.contains(value));
    }
  }
}
