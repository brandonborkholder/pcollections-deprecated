package clojure.collections;

public abstract class AbstractCollection<T> implements PersistentCollection<T> {
	@Override
	public PersistentCollection<T> withAll(Iterable<? extends T> values) {
		PersistentCollection<T> collection = this;
		for (T value : values) {
			collection = collection.with(value);
		}

		return collection;
	}

	@Override
	public PersistentCollection<T> withoutAll(Iterable<?> values) {
		PersistentCollection<T> collection = this;
		for (Object value : values) {
			collection = collection.without(value);
		}

		return collection;
	}

	@Override
	public boolean containsAll(Iterable<?> values) {
		for (Object value : values) {
			if (!contains(value)) {
				return false;
			}
		}

		return true;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}
}
