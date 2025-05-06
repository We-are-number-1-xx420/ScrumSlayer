package io.github.wearenumberone.scrumslayer.util;

public class CardinalDirection extends Vec2i {
	private CardinalDirection(int x, int y) {
		super(x, y);
	}

	public CardinalDirection rotateLeft() {
		return new CardinalDirection(this.getY(), -this.getX());
	}
	public CardinalDirection rotateRight() {
		return new CardinalDirection(-this.getY(), this.getX());
	}

	public static CardinalDirection fromNumber(int direction) {
		return switch (direction % 4) {
			case 0 -> CardinalDirection.NORTH;
			case 1 -> CardinalDirection.EAST;
			case 2 -> CardinalDirection.SOUTH;
			case 3 -> CardinalDirection.WEST;
			default -> throw new AssertionError("Somehow int % 4 is not in the range 0 <= n < 4");
		};
	}
	public int toNumber() {
		if (this.getY() == -1) return 0;
		else if (this.getX() == 1) return 1;
		else if (this.getY() == 1) return 2;
		else if (this.getX() == -1) return 3;
		throw new AssertionError("This line should never be reached");
	}

	public static CardinalDirection[] all() {
		return directions;
	}

	public static final CardinalDirection NORTH = new CardinalDirection(0, -1);
	public static final CardinalDirection EAST = new CardinalDirection(1, 0);
	public static final CardinalDirection SOUTH = new CardinalDirection(0, 1);
	public static final CardinalDirection WEST = new CardinalDirection(-1, 0);

	private static final CardinalDirection[] directions = {CardinalDirection.NORTH, CardinalDirection.EAST, CardinalDirection.SOUTH, CardinalDirection.WEST};
}