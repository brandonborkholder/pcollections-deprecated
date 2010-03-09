package pcollections;

import pcollections.PersistentHashSet;


public class HashSetTest extends SetTest<String, PersistentHashSet<String>, java.util.HashSet<String>> {
  java.util.HashSet<String> set = new java.util.HashSet<String>();

  @Override
  protected PersistentHashSet<String> empty() {
    return PersistentHashSet.empty();
  }

  @Override
  protected java.util.HashSet<String> getCollection() {
    return set;
  }

  @Override
  protected java.util.HashSet<String> cloneCollection() {
    return (java.util.HashSet) set.clone();
  }

  @Override
  protected String random() {
    return String.valueOf(RAND.nextInt());
  }
}
