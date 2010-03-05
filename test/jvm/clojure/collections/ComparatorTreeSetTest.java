package clojure.collections;

import java.util.Collection;
import java.util.Comparator;
import java.util.Set;

public class ComparatorTreeSetTest extends SetTest<Number, TreeSet<Number>> {
  static final Comparator<Number> COMPARATOR = new Comparator<Number>() {
    @Override
    public int compare(Number o1, Number o2) {
      return o1.intValue() - o2.intValue();
    }
  };

  Set<Number> set = new java.util.TreeSet<Number>(COMPARATOR);

  @Override
  protected TreeSet<Number> empty() {
    return TreeSet.empty(COMPARATOR);
  }

  @Override
  protected Collection<Number> getCollection() {
    return set;
  }

  @Override
  protected Number random() {
    return RAND.nextInt();
  }
}
