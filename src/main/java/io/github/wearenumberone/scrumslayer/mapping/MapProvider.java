package io.github.wearenumberone.scrumslayer.mapping;

import io.github.wearenumberone.scrumslayer.util.Grid;

public interface MapProvider {
	Grid<Room> provide();
}