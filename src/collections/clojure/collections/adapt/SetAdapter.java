package clojure.collections.adapt;

import java.util.Set;

import clojure.collections.PersistentSet;

public class SetAdapter<E> extends CollectionAdapter<E> implements Set<E> {
  public SetAdapter(PersistentSet<E> set) {
    super(set);
  }
}
