package io.github.wearenumberone.scrumslayer.render;

public class StyledCharacter {
	private final char character;
	private final ConsoleStyle style;

	public StyledCharacter(char character, ConsoleStyle style) {
		this.character = character;
		this.style = style;
	}
	public StyledCharacter(char character) {
		this(character, ConsoleStyle.RESET);
	}

	public char getCharacter() {
		return this.character;
	}
	public ConsoleStyle getStyle() {
		return this.style;
	}

	@Override
	public String toString() {
		return this.style.getPrefix() + this.character + ConsoleStyle.RESET.getPrefix();
	}
}