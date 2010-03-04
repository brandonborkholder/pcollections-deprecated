package clojure.collections;

import java.util.Collection;
import java.util.Set;

public class TreeSetTest extends SetTest<String, TreeSet<String>> {
  Set<String> set = new java.util.TreeSet<String>();

  @Override
  protected TreeSet<String> empty() {
    return TreeSet.empty();
  }

  @Override
  protected Collection<String> getCollection() {
    return set;
  }

  @Override
  protected String random() {
    return String.valueOf(RAND.nextInt());
  }
}
