package pcollections;

import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;

import pcollections.PersistentSet;

public abstract class SetTest<T, P extends PersistentSet<T>, C extends Set<T>> extends CollectionTest<T, P, C> {
  @Test
  public void simpleSetTest() {
    T value = random();
    add(value);
    add(value);
    remove(value);

    assertEquivalent();
    Assert.assertTrue(pCollection.isEmpty());
  }

  @Test
  public void multipleAddTest() {
    T a = random();
    T b = random();
    add(a);
    add(b);
    add(a);
    add(b);
    add(random());
    add(a);
    add(random());

    assertEquivalent();
    Assert.assertEquals(4, pCollection.size());
  }

  @Test
  public void multipleRemoveTest() {
    T value = random();
    add(value);
    remove(value);
    remove(value);

    assertEquivalent();
  }
}
