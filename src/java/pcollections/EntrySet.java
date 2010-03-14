package pcollections;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map.Entry;

public class EntrySet<K, V> extends AbstractCollection<Entry<K, V>> implements PersistentSet<Entry<K, V>> {
  protected final PersistentMap<K, V> map;

  protected EntrySet(PersistentMap<K, V> map) {
    this.map = map;
  }

  @Override
  public boolean contains(Object o) {
    if (o instanceof Entry<?, ?>) {
      Entry<?, ?> entry = (Entry<?, ?>) o;
      return PersistentCollections.areEqual(map.get(entry.getKey()), entry.getValue());
    } else {
      return false;
    }
  }

  @Override
  public Iterator<Entry<K, V>> iterator() {
    return map.iterator();
  }

  @Override
  public int size() {
    return map.size();
  }

  @Override
  protected int hash() {
    return 11 * map.hashCode();
  }

  @Override
  public PersistentSet<Entry<K, V>> with(Entry<K, V> value) {
    return new EntrySet<K, V>(map.with(value.getKey(), value.getValue()));
  }

  @Override
  public PersistentSet<Entry<K, V>> withAll(Collection<? extends Entry<K, V>> values) {
    return (PersistentSet<Entry<K, V>>) super.withAll(values);
  }

  @Override
  public PersistentSet<Entry<K, V>> without(Object value) {
    if (value instanceof Entry<?, ?>) {
      Entry<?, ?> entry = (Entry<?, ?>) value;
      return new EntrySet<K, V>(map.without(entry.getKey()));
    } else {
      return this;
    }
  }

  @Override
  public PersistentSet<Entry<K, V>> withoutAll(Collection<?> values) {
    PersistentSet<Entry<K, V>> set = this;
    for (Object value : values) {
      set = set.without(value);
    }

    return set;
  }
}
