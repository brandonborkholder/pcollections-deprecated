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
public class TreeSet<T> extends BaseSet<T> {
  static final TreeSet EMPTY = new TreeSet(TreeMap.EMPTY);

  public static <T extends Comparable<T>> TreeSet<T> empty() {
    return EMPTY;
  }

  public static <T> TreeSet<T> empty(Comparator<T> comparator) {
    return new TreeSet<T>(TreeMap.empty(comparator));
  }

  protected TreeSet(TreeMap<T, ?> map) {
    super(map);
  }
}
