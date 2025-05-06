package io.github.wearenumberone.scrumslayer.render;

public class ConsoleStyle {
	public enum Color {
		BLACK(0),
		RED(1),
		GREEN(2),
		YELLOW(3),
		BLUE(4),
		PURPLE(5),
		CYAN(6),
		WHITE(7),
		UNKNOWN(9);

		private final int n;

		Color(int n) {
			this.n = n;
		}

		public int getNumber() {
			return this.n;
		}
	}

	private Color foregroundColor;
	private Color backgroundColor;

	private boolean bold = false;
	private boolean underline = false;
	private boolean inverse = false;

	public ConsoleStyle setForeground(Color color) {
		this.foregroundColor = color;
		return this;
	}
	public ConsoleStyle setBackground(Color color) {
		this.backgroundColor = color;
		return this;
	}

	public ConsoleStyle setBold(boolean bold) {
		this.bold = bold;
		return this;
	}
	public ConsoleStyle setBold() {
		return this.setBold(true);
	}
	public ConsoleStyle setUnderlined(boolean underline) {
		this.underline = underline;
		return this;
	}
	public ConsoleStyle setUnderlined() {
		return this.setUnderlined(true);
	}
	public ConsoleStyle setInverse(boolean inverse) {
		this.inverse = inverse;
		return this;
	}
	public ConsoleStyle setInverse() {
		return this.setInverse(true);
	}

	public static final ConsoleStyle RESET = new ConsoleStyle();

	public String getPrefix() {
		StringBuilder prefixBuilder = new StringBuilder("\033[0");

		if (this.foregroundColor != null) prefixBuilder.append(";3").append(this.foregroundColor.getNumber());
		if (this.backgroundColor != null) prefixBuilder.append(";4").append(this.backgroundColor.getNumber());

		if (this.bold) prefixBuilder.append(";1");
		if (this.underline) prefixBuilder.append(";4");
		if (this.inverse) prefixBuilder.append(";7");

		prefixBuilder.append('m');
		return prefixBuilder.toString();
	}

	@Override
	public String toString() {
		return this.getPrefix();
	}
}