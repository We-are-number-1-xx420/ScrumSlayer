package io.github.wearenumberone.scrumslayer.util;

import java.util.ArrayList;

public class FixedSizeList<T> extends ArrayList<T> {
	public FixedSizeList(int capacity) {
		super(capacity);
		for (int i = 0; i < capacity; i++) {
			super.add(null);
		}
	}
	public FixedSizeList(T[] initialElements) {
		super(initialElements.length);
		for (T element : initialElements) {
			super.add(element);
		}
	}
	public FixedSizeList(FixedSizeList<T> src) {
		super(src.size());
		for (T element : src) {
			super.add(element);
		}
	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException("Elements may not be cleared from a fixed size List.");
	}

	@Override
	public boolean add(T o) {
		throw new UnsupportedOperationException("Elements may not be added to a fixed size List, use set() instead.");
	}

	@Override
	public void add(int index, T element) {
		throw new UnsupportedOperationException("Elements may not be added to a fixed size List, use set() instead.");
	}

	@Override
	public T remove(int index) {
		throw new UnsupportedOperationException("Elements may not be removed from a fixed size List.");
	}

	@Override
	public boolean remove(Object o) {
		throw new UnsupportedOperationException("Elements may not be removed from a fixed size List.");
	}

	@Override
	protected void removeRange(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException("Elements may not be removed from a fixed size List.");
	}
}