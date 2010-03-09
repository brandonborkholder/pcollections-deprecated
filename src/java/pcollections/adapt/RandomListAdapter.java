package pcollections.adapt;

import java.util.RandomAccess;

import pcollections.PersistentList;


public class RandomListAdapter<E> extends ListAdapter<E> implements RandomAccess {
  public RandomListAdapter(PersistentList<E> list) {
    super(list);
  }
}
