package clojure.collections;

import clojure.lang.IPersistentMap;
import clojure.lang.PersistentHashMap;

/**
 * Copyright (c) Brandon Borkholder. All rights reserved. The use and
 * distribution terms for this software are covered by the Eclipse Public
 * License 1.0 (http://opensource.org/licenses/eclipse-1.0.php) which can be
 * found in the file epl-v10.html at the root of this distribution. By using
 * this software in any fashion, you are agreeing to be bound by the terms of
 * this license. You must not remove this notice, or any other, from this
 * software.
 */
public class HashMap<K, V> extends ClojureMap<K, V> {
  private static final HashMap EMPTY = new HashMap(PersistentHashMap.EMPTY);

  public static <K, V> HashMap<K, V> empty() {
    return EMPTY;
  }

  protected HashMap(IPersistentMap map) {
    super(map);
  }

  @Override
  protected PersistentMap<K, V> wrap(IPersistentMap map) {
    return new HashMap<K, V>(map);
  }
}
