package io.github.wearenumberone.scrumslayer.util;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class Grid<T> {
	private final FixedSizeList<FixedSizeList<T>> grid;

	public Grid(int width, int height) {
		this.grid = new FixedSizeList<>(height);
		for (int i = 0; i < this.grid.size(); i++) this.grid.set(i, new FixedSizeList<>(width));
	}
	public Grid(Vec2i size) {
		this(size.getX(), size.getY());
	}
	public Grid(Grid<T> grid) {
		this.grid = new FixedSizeList<>(grid.getHeight());
		for (int y = 0; y < grid.getHeight(); y++) this.grid.set(y, new FixedSizeList<>(grid.grid.get(y)));
	}
	public Grid(T[][] grid) {
		this.grid = new FixedSizeList<>(grid.length);
		for (int i = 0; i < grid.length; i++) this.grid.set(i, new FixedSizeList<>(grid[i]));
	}

	public T set(int x, int y, T value) {
		if (!this.isInBounds(x, y)) throw new IndexOutOfBoundsException(String.format("Position (%d, %d) out of bounds for size (%d, %d)", x, y, this.getWidth(), this.getHeight()));
		this.grid.get(y).set(x, value);
		return value;
	}
	public T set(Vec2i position, T value) {
		return this.set(position.getX(), position.getY(), value);
	}
	public void paste(int x, int y, Grid<T> grid) {
		this.paste(new Vec2i(x, y), grid);
	}
	public void paste(Vec2i position, Grid<T> grid) {
		if (!this.isInBounds(position) || !this.isInBounds(position.add(grid.size().subtract(Vec2i.IDENTITY)))) throw new IndexOutOfBoundsException("Pasting grid is out of bounds");

		grid.forEach((v, offset) -> this.set(position.add(offset), v));
	}
	public T get(int x, int y) {
		if (!this.isInBounds(x, y)) throw new IndexOutOfBoundsException(String.format("Position (%d, %d) out of bounds for size (%d, %d)", x, y, this.getWidth(), this.getHeight()));
		return this.grid.get(y).get(x);
	}
	public T get(Vec2i position) {
		return this.get(position.getX(), position.getY());
	}

	public boolean exists(int x, int y) {
		return this.isInBounds(x, y) && this.get(x, y) != null;
	}
	public boolean exists(Vec2i position) {
		return this.exists(position.getX(), position.getY());
	}

	public Vec2i find(Predicate<T> predicate) {
		for (int x = 0; x < this.getWidth(); x++) for (int y = 0; y < this.getHeight(); y++) {
			if (predicate.test(this.get(x, y))) return new Vec2i(x, y);
		}
		return null;
	}
	public Vec2i find(T value) {
		return this.find(v -> v.equals(value));
	}

	@FunctionalInterface
	public interface ForEachCallback<R> {
		void handle(R value, Vec2i position);
	}
	public void forEach(ForEachCallback<T> callback) {
		for (int y = 0; y < this.getHeight(); y++) for (int x = 0; x < this.getWidth(); x++) {
			callback.handle(this.get(x, y), new Vec2i(x, y));
		}
	}

	public List<Vec2i> positions() {
		List<Vec2i> result = new ArrayList<>();

		this.forEach((value, position) -> result.add(position));

		return result;
	}
	public List<T> values() {
		List<T> result = new ArrayList<>();

		this.forEach((value, position) -> result.add(value));

		return result;
	}

	public Grid<T> fill(Function<Vec2i, T> provider) {
		this.forEach((value, position) -> this.set(position, provider.apply(position)));
		return this;
	}
	public Grid<T> fill(T value) {
		return this.fill(position -> value);
	}

	public int getHeight() {
		return this.grid.size();
	}
	public int getWidth() {
		return this.grid.getFirst().size();
	}
	public Vec2i size() {
		return new Vec2i(this.getWidth(), this.getHeight());
	}
	public int getElementCount() {
		return this.getWidth() * this.getHeight();
	}

	public boolean isInBounds(int x, int y) {
		return x >= 0 && x < this.getWidth() && y >= 0 && y < this.getHeight();
	}
	public boolean isInBounds(Vec2i position) {
		return this.isInBounds(position.getX(), position.getY());
	}

	public FixedSizeList<T> getRow(int y) {
		return new FixedSizeList<>(this.grid.get(y));
	}
	public FixedSizeList<T> getFirstRow() {
		return this.getRow(0);
	}
	public FixedSizeList<T> getLastRow() {
		return this.getRow(this.getHeight() - 1);
	}
	public FixedSizeList<T> getColumn(int x) {
		FixedSizeList<T> result = new FixedSizeList<>(this.getHeight());

		for (int y = 0; y < result.size(); y++) {
			result.set(y, this.grid.get(y).get(x));
		}

		return result;
	}
	public FixedSizeList<T> getFirstColumn() {
		return this.getColumn(0);
	}
	public FixedSizeList<T> getLastColumn() {
		return this.getColumn(this.getWidth() - 1);
	}

	public FixedSizeList<FixedSizeList<T>> getRows() {
		return this.grid;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int y = 0; y < this.getHeight(); y++) {
			for (int x = 0; x < this.getWidth(); x++) {
				builder.append(String.valueOf(this.get(x, y)));
			}
			builder.append('\n');
		}
		return builder.toString();
	}

	public <K> Grid<K> map(Function<T, K> mapFunction) {
		Grid<K> result = new Grid<>(this.getWidth(), this.getHeight());
		for (int y = 0; y < this.getHeight(); y++) for (int x = 0; x < this.getWidth(); x++) {
			result.set(x, y, mapFunction.apply(this.get(x, y)));
		}
		return result;
	}

	public static Grid<Character> fromCharGrid(String charGrid) {
		String[] lines = charGrid.split("\n");

		int width = lines[0].length() - 1;
		int height = lines.length;

		return new Grid<Character>(width, height).fill(position -> lines[position.getY()].charAt(position.getX()));
	}

	public static <K> Grid<K> ofSingle(K value) {
		Grid<K> grid = new Grid<>(1, 1);
		grid.set(0, 0, value);
		return grid;
	}
}