package io.github.wearenumberone.scrumslayer.item;

import io.github.wearenumberone.scrumslayer.render.ConsoleStyle;
import io.github.wearenumberone.scrumslayer.render.StyledCharacter;

public class KeyItem extends Item {
    public StyledCharacter getAppearance() {
        return new StyledCharacter('⚷', new ConsoleStyle().setForeground(ConsoleStyle.Color.YELLOW));
    }
}