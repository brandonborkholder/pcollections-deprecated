package clojure.collections;

public interface PersistentStack<T> extends PersistentCollection<T> {
	PersistentStack<T> with(T value);

	PersistentStack<T> withAll(Iterable<? extends T> values);

	PersistentStack<T> without(Object value);

	PersistentStack<T> withoutAll(Iterable<?> values);

	T peek();

	PersistentStack<T> pop();
}
