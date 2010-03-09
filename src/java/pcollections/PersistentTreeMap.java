package pcollections;

import java.util.Comparator;

import clojure.lang.IPersistentMap;

/**
 * Copyright (c) Brandon Borkholder. All rights reserved. The use and
 * distribution terms for this software are covered by the Eclipse Public
 * License 1.0 (http://opensource.org/licenses/eclipse-1.0.php) which can be
 * found in the file epl-v10.html at the root of this distribution. By using
 * this software in any fashion, you are agreeing to be bound by the terms of
 * this license. You must not remove this notice, or any other, from this
 * software.
 */
public class PersistentTreeMap<K, V> extends ClojureMap<K, V> {
  static final PersistentTreeMap EMPTY = new PersistentTreeMap(clojure.lang.PersistentTreeMap.EMPTY);

  public static <K, V> PersistentTreeMap<K, V> empty(Comparator<K> comparator) {
    return new PersistentTreeMap<K, V>(new clojure.lang.PersistentTreeMap(null, comparator));
  }

  public static <K extends Comparable<K>, V> PersistentTreeMap<K, V> empty() {
    return EMPTY;
  }

  protected PersistentTreeMap(IPersistentMap map) {
    super(map);
  }

  @Override
  protected PersistentMap<K, V> wrap(IPersistentMap map) {
    return new PersistentTreeMap<K, V>(map);
  }
}
