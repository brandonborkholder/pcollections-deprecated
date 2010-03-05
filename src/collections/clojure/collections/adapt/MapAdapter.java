package clojure.collections.adapt;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import clojure.collections.PersistentMap;

public class MapAdapter<K, V> extends AbstractMap<K, V> {
  protected final PersistentMap<K, V> map;

  public MapAdapter(PersistentMap<K, V> map) {
    this.map = map;
  }

  @Override
  public boolean containsKey(Object key) {
    return map.containsKey(key);
  }

  @Override
  public boolean containsValue(Object value) {
    return map.containsValue(value);
  }

  @Override
  public Set<K> keySet() {
    return new SetAdapter<K>(map.keySet());
  }

  @Override
  public Collection<V> values() {
    return new CollectionAdapter<V>(map.values());
  }

  @Override
  public int size() {
    return map.size();
  }

  @Override
  public Set<Entry<K, V>> entrySet() {
    return new AbstractSet<Entry<K, V>>() {
      @Override
      public Iterator<Entry<K, V>> iterator() {
        return map.iterator();
      }

      @Override
      public int size() {
        return map.size();
      }
    };
  }
}
