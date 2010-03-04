package clojure.collections;

import junit.framework.Assert;

import org.junit.Test;

public abstract class SetTest<T, C extends PersistentSet<T>> extends CollectionTest<T, C> {
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
