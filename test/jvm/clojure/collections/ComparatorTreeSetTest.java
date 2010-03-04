package clojure.collections;

import java.util.Collection;
import java.util.Comparator;
import java.util.Set;

public class ComparatorTreeSetTest extends SetTest<A, TreeSet<A>> {
  static final Comparator<A> COMPARATOR = new CompareA();

  Set<A> set = new java.util.TreeSet<A>(COMPARATOR);

  @Override
  protected TreeSet<A> empty() {
    return TreeSet.empty(COMPARATOR);
  }

  @Override
  protected Collection<A> getCollection() {
    return set;
  }

  @Override
  protected A random() {
    return new A(RAND.nextInt());
  }
}

class A {
  final int a;

  A(int i) {
    a = i;
  }
}

class CompareA implements Comparator<A> {
  @Override
  public int compare(A o1, A o2) {
    return o1.a - o2.a;
  }
}
