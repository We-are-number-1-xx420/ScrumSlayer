package io.github.wearenumberone.scrumslayer.mapping;

import io.github.wearenumberone.scrumslayer.render.StyledCharacter;

public class StartingRoom extends Room {
	public StyledCharacter getMinimapAppearance() {
		return new StyledCharacter('S');
	}
}