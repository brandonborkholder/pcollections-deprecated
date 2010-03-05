package clojure.collections;

import java.util.Comparator;

import clojure.lang.IPersistentMap;
import clojure.lang.PersistentTreeMap;

/**
 * Copyright (c) Brandon Borkholder. All rights reserved. The use and
 * distribution terms for this software are covered by the Eclipse Public
 * License 1.0 (http://opensource.org/licenses/eclipse-1.0.php) which can be
 * found in the file epl-v10.html at the root of this distribution. By using
 * this software in any fashion, you are agreeing to be bound by the terms of
 * this license. You must not remove this notice, or any other, from this
 * software.
 */
public class TreeMap<K, V> extends ClojureMap<K, V> {
  static final TreeMap EMPTY = new TreeMap(PersistentTreeMap.EMPTY);

  public static <K, V> TreeMap<K, V> empty(Comparator<K> comparator) {
    return new TreeMap<K, V>(new PersistentTreeMap(null, comparator));
  }

  public static <K extends Comparable<K>, V> TreeMap<K, V> empty() {
    return EMPTY;
  }

  protected TreeMap(IPersistentMap map) {
    super(map);
  }

  @Override
  protected PersistentMap<K, V> wrap(IPersistentMap map) {
    return new TreeMap<K, V>(map);
  }
}
