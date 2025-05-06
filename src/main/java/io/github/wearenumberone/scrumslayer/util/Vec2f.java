package io.github.wearenumberone.scrumslayer.util;

public class Vec2f {
	private final float x;
	private final float y;

	public Vec2f(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return this.x;
	}
	public float getY() {
		return this.y;
	}

	public Vec2f inverted() {
		return new Vec2f(-this.x, -this.y);
	}

	public Vec2f add(Vec2f other) {
		return new Vec2f(this.x + other.x, this.y + other.y);
	}
	public Vec2f subtract(Vec2f other) {
		return new Vec2f(this.x - other.x, this.y - other.y);
	}
	public Vec2f add(Vec2i other) {
		return new Vec2f(this.x + other.getX(), this.y + other.getY());
	}
	public Vec2f subtract(Vec2i other) {
		return new Vec2f(this.x - other.getX(), this.y - other.getY());
	}

	public Vec2f multiply(float factor) {
		return new Vec2f(this.x * factor, this.y * factor);
	}
	public Vec2f divide(float divisor) {
		return new Vec2f(this.x / divisor, this.y / divisor);
	}

	public Vec2f modulo(Vec2f divisor) {
		return new Vec2f(this.x % divisor.x, this.y % divisor.y);
	}

	public int squaredMagnitude() {
		return (int) (Math.pow(this.x, 2) + Math.pow(this.y, 2));
	}
	public double magnitude() {
		return Math.sqrt(this.squaredMagnitude());
	}

	public boolean equals(Vec2f other) {
		return this.x == other.x && this.y == other.y;
	}
	public String toString() {
		return String.format("(%d, %d)", this.x, this.y);
	}

	public static final Vec2i ZERO = new Vec2i(0, 0);
	public static final Vec2i X_AXIS = new Vec2i(1, 0);
	public static final Vec2i Y_AXIS = new Vec2i(0, 1);
}