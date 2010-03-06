package clojure.collections.adapt;

import java.util.RandomAccess;

import clojure.collections.PersistentList;

public class RandomListAdapter<E> extends ListAdapter<E> implements RandomAccess {
  public RandomListAdapter(PersistentList<E> list) {
    super(list);
  }
}
