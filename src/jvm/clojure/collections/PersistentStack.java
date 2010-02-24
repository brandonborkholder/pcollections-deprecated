package clojure.collections;

public interface PersistentStack<T> extends PersistentCollection<T> {
  @Override
  PersistentStack<T> with(T value);

  @Override
  PersistentStack<T> withAll(Iterable<? extends T> values);

  @Override
  PersistentStack<T> without(Object value);

  @Override
  PersistentStack<T> withoutAll(Iterable<?> values);

  T peek();

  PersistentStack<T> pop();

  PersistentStack<T> push(T value);
}
