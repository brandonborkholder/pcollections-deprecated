package pcollections;

import java.util.Comparator;

/**
 * Copyright (c) Brandon Borkholder. All rights reserved. The use and
 * distribution terms for this software are covered by the Eclipse Public
 * License 1.0 (http://opensource.org/licenses/eclipse-1.0.php) which can be
 * found in the file epl-v10.html at the root of this distribution. By using
 * this software in any fashion, you are agreeing to be bound by the terms of
 * this license. You must not remove this notice, or any other, from this
 * software.
 */
public class PersistentTreeSet<T> extends BaseSet<T> {
  static final PersistentTreeSet EMPTY = new PersistentTreeSet(PersistentTreeMap.EMPTY);

  public static <T extends Comparable<T>> PersistentTreeSet<T> empty() {
    return EMPTY;
  }

  public static <T> PersistentTreeSet<T> empty(Comparator<T> comparator) {
    return new PersistentTreeSet<T>(PersistentTreeMap.empty(comparator));
  }

  protected PersistentTreeSet(PersistentTreeMap<T, ?> map) {
    super(map);
  }
}
