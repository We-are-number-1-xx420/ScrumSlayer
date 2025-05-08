package io.github.wearenumberone.scrumslayer.mapping;

import io.github.wearenumberone.scrumslayer.render.ConsoleStyle;
import io.github.wearenumberone.scrumslayer.render.Renderable;
import io.github.wearenumberone.scrumslayer.render.StyledCharacter;
import io.github.wearenumberone.scrumslayer.util.Grid;

public enum Tile implements Renderable {
    EMPTY(' ', new ConsoleStyle()),
    WALL('▦', new ConsoleStyle().setForeground(ConsoleStyle.Color.PURPLE)),
    DOOR_VERTICAL('|', new ConsoleStyle().setForeground(ConsoleStyle.Color.GREEN)),
    DOOR_HORIZONTAL('-', new ConsoleStyle().setForeground(ConsoleStyle.Color.GREEN));

    private final StyledCharacter styledCharacter;

    Tile(StyledCharacter styledCharacter) {
        this.styledCharacter = styledCharacter;
    }
    Tile(char character, ConsoleStyle style) {
        this(new StyledCharacter(character, style));
    }
    
    public StyledCharacter getAppearance() {
        return this.styledCharacter;
    }

    public Grid<StyledCharacter> render() {
        return Grid.ofSingle(this.getAppearance();
    }
    
    @Override
    public String toString() {
        return this.getAppearance().toString();
    }
}