package io.github.wearenumberone.scrumslayer.util;

public class Vec2i {
	private final int x;
	private final int y;

	public Vec2i(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}

	public Vec2i invert() {
		return new Vec2i(-this.x, -this.y);
	}

	public Vec2i add(Vec2i other) {
		return new Vec2i(this.x + other.x, this.y + other.y);
	}
	public Vec2i subtract(Vec2i other) {
		return new Vec2i(this.x - other.x, this.y - other.y);
	}

	public Vec2i multiply(int factor) {
		return new Vec2i(this.x * factor, this.y * factor);
	}

	public Vec2i modulo(Vec2i divisor) {
		return new Vec2i(this.x % divisor.x, this.y % divisor.y);
	}
	public Vec2f ofFloats() {
		return new Vec2f(this.x, this.y);
	}

	public int squaredMagnitude() {
		return (int) (Math.pow(this.x, 2) + Math.pow(this.y, 2));
	}
	public double magnitude() {
		return Math.sqrt(this.squaredMagnitude());
	}

	public boolean equals(Vec2i other) {
		return this.x == other.x && this.y == other.y;
	}
	public String toString() {
		return String.format("(%d, %d)", this.x, this.y);
	}

	public static final Vec2i ZERO = new Vec2i(0, 0);
	public static final Vec2i X_AXIS = new Vec2i(1, 0);
	public static final Vec2i Y_AXIS = new Vec2i(0, 1);
	public static final Vec2i IDENTITY = X_AXIS.add(Y_AXIS);
}