package pcollections;

import pcollections.PersistentTreeSet;


public class TreeSetTest extends SetTest<String, PersistentTreeSet<String>, java.util.TreeSet<String>> {
  java.util.TreeSet<String> set = new java.util.TreeSet<String>();

  @Override
  protected PersistentTreeSet<String> empty() {
    return PersistentTreeSet.empty();
  }

  @Override
  protected java.util.TreeSet<String> getCollection() {
    return set;
  }

  @Override
  protected java.util.TreeSet<String> cloneCollection() {
    return (java.util.TreeSet) set.clone();
  }

  @Override
  protected String random() {
    return String.valueOf(RAND.nextInt());
  }
}
