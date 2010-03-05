package clojure.collections.adapt;

import java.util.AbstractList;
import java.util.Iterator;

import clojure.collections.PersistentList;

public class ListAdapter<E> extends AbstractList<E> {
  protected final PersistentList<E> list;

  public ListAdapter(PersistentList<E> list) {
    this.list = list;
  }

  @Override
  public E get(int index) {
    return list.get(index);
  }

  @Override
  public Iterator<E> iterator() {
    return list.iterator();
  }

  @Override
  public int size() {
    return list.size();
  }

  @Override
  public int hashCode() {
    return 3 * list.hashCode();
  }
}
