package pcollections.adapt;

import java.util.Set;

import pcollections.PersistentSet;


public class SetAdapter<E> extends CollectionAdapter<E> implements Set<E> {
  public SetAdapter(PersistentSet<E> set) {
    super(set);
  }
}
