package clojure.collections.adapt;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;

import clojure.collections.PersistentCollection;

public class CollectionAdapter<E> extends AbstractCollection<E> {
  protected final PersistentCollection<E> collection;

  public CollectionAdapter(PersistentCollection<E> collection) {
    this.collection = collection;
  }

  @Override
  public Iterator<E> iterator() {
    return collection.iterator();
  }

  @Override
  public int size() {
    return collection.size();
  }

  @Override
  public boolean contains(Object o) {
    return collection.contains(o);
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    return collection.containsAll(c);
  }

  @Override
  public int hashCode() {
    return 11 * collection.hashCode();
  }
}
