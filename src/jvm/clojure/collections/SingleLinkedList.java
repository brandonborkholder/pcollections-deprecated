package clojure.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SingleLinkedList<T> extends AbstractCollection<T>
		implements
			PersistentStack<T> {
	public static final SingleLinkedList EMPTY = new Empty();

	protected final T value;

	protected final SingleLinkedList<T> next;

	public static <T> SingleLinkedList<T> empty() {
		return EMPTY;
	}

	protected SingleLinkedList(SingleLinkedList<T> next, T value) {
		this.next = next;
		this.value = value;
	}

	protected SingleLinkedList() {
		this(null, null);
	}

	@Override
	public boolean contains(Object value) {
		return this.value == value
				|| (this.value != null && this.value.equals(value))
				|| next.contains(value);
	}

	@Override
	public int size() {
		return 1 + next.size();
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersistentStack<T> with(T value) {
		return new SingleLinkedList<T>(this, value);
	}

	@Override
	public PersistentStack<T> withAll(Iterable<? extends T> values) {
		return (PersistentStack) super.withAll(values);
	}

	@Override
	public PersistentStack<T> without(Object value) {
		if (this.value == value
				|| (this.value != null && this.value.equals(value))) {
			return next;
		} else {
			return new SingleLinkedList<T>((SingleLinkedList) next
					.without(value), this.value);
		}
	}

	@Override
	public PersistentStack<T> withoutAll(Iterable<?> values) {
		return (PersistentStack) super.withoutAll(values);
	}

	@Override
	public T peek() {
		return value;
	}

	@Override
	public PersistentStack<T> pop() {
		return next;
	}

	protected static final class Empty<T> extends SingleLinkedList<T> {
		@Override
		public boolean contains(Object value) {
			return false;
		}

		@Override
		public boolean containsAll(Iterable<?> values) {
			return false;
		}

		@Override
		public int size() {
			return 0;
		}

		@Override
		public Iterator<T> iterator() {
			return null;
		}

		@Override
		public T peek() {
			throw new NoSuchElementException();
		}

		@Override
		public PersistentStack<T> pop() {
			return this;
		}

		@Override
		public PersistentStack<T> without(Object value) {
			return this;
		}

		@Override
		public PersistentStack<T> withoutAll(Iterable<?> values) {
			return this;
		}
	}
}
