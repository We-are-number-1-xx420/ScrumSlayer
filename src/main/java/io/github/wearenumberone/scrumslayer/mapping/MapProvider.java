package io.github.wearenumberone.scrumslayer.mapping;

import io.github.wearenumberone.scrumslayer.util.Grid;

public class MapProvider {
	public static Grid<Room> provide() {
		return Grid.fromCharGrid("""
		_______
		_______
		_______
		_______
		_______
		_______
		S______
		""").map((character -> switch (character) {
			case 'S' -> new StartingRoom();
			case '_' -> null;
			default -> null;
		}));
	}
}