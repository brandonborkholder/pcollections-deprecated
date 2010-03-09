package pcollections;

import pcollections.HashSet;


public class HashSetTest extends SetTest<String, HashSet<String>, java.util.HashSet<String>> {
  java.util.HashSet<String> set = new java.util.HashSet<String>();

  @Override
  protected HashSet<String> empty() {
    return HashSet.empty();
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
