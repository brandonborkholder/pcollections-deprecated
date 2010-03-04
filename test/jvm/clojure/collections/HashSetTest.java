package clojure.collections;

import java.util.Collection;
import java.util.Set;

public class HashSetTest extends SetTest<String, HashSet<String>> {
  Set<String> set = new java.util.HashSet<String>();

  @Override
  protected HashSet<String> empty() {
    return HashSet.empty();
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
