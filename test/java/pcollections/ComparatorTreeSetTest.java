package pcollections;

import java.util.Comparator;

import pcollections.PersistentTreeSet;

public class ComparatorTreeSetTest extends SetTest<Number, PersistentTreeSet<Number>, java.util.TreeSet<Number>> {
  static final Comparator<Number> COMPARATOR = new Comparator<Number>() {
    @Override
    public int compare(Number o1, Number o2) {
      if (o1.intValue() > o2.intValue()) {
        return 1;
      } else if (o1.intValue() < o2.intValue()) {
        return -1;
      } else {
        return 0;
      }
    }
  };

  java.util.TreeSet<Number> set = new java.util.TreeSet<Number>(COMPARATOR);

  @Override
  protected PersistentTreeSet<Number> empty() {
    return PersistentTreeSet.empty(COMPARATOR);
  }

  @Override
  protected java.util.TreeSet<Number> getCollection() {
    return set;
  }

  @Override
  protected java.util.TreeSet<Number> cloneCollection() {
    return (java.util.TreeSet) set.clone();
  }

  @Override
  protected Number random() {
    return RAND.nextInt();
  }
}
