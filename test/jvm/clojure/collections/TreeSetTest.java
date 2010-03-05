package clojure.collections;


public class TreeSetTest extends SetTest<String, TreeSet<String>, java.util.TreeSet<String>> {
  java.util.TreeSet<String> set = new java.util.TreeSet<String>();

  @Override
  protected TreeSet<String> empty() {
    return TreeSet.empty();
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
