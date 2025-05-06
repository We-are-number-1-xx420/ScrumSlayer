package io.github.wearenumberone.scrumslayer.render;

import io.github.wearenumberone.scrumslayer.util.Grid;

public interface Renderable {
	Grid<StyledCharacter> render();
}