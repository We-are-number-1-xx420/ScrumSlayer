package io.github.wearenumberone.scrumslayer.mapping;
import io.github.wearenumberone.scrumslayer.entities.Entity;
import io.github.wearenumberone.scrumslayer.entities.PlayerEntity;
import io.github.wearenumberone.scrumslayer.render.Renderable;
import io.github.wearenumberone.scrumslayer.render.StyledCharacter;
import io.github.wearenumberone.scrumslayer.util.CardinalDirection;
import io.github.wearenumberone.scrumslayer.util.Grid;
import io.github.wearenumberone.scrumslayer.util.Vec2i;

import java.util.ArrayList;
import java.util.List;

public abstract class Room implements Renderable {
    protected Grid<Tile> layout = new Grid<>(new Tile[][] {
            {Tile.WALL, Tile.WALL, Tile.WALL, Tile.WALL, Tile.WALL},
            {Tile.WALL, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.WALL},
            {Tile.WALL, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.WALL},
            {Tile.WALL, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.WALL},
            {Tile.WALL, Tile.WALL, Tile.WALL, Tile.WALL, Tile.WALL}
    });
    protected boolean isCleared;
    protected World parent;
    protected List<Entity> entities = new ArrayList<>();
    protected Vec2i position;
    protected boolean isLocked;

    public Room(World parent) {
        this.parent = parent;
    }

    public Grid<StyledCharacter> render(){
        if (this.parent.canAccess(this.position.add(CardinalDirection.NORTH))) this.layout.set(3, 5, Tile.DOOR_HORIZONTAL);
        if (this.parent.canAccess(this.position.add(CardinalDirection.EAST))) this.layout.set(0, 3, Tile.DOOR_VERTICAL);
        if (this.parent.canAccess(this.position.add(CardinalDirection.SOUTH))) this.layout.set(3, 0, Tile.DOOR_HORIZONTAL);
        if (this.parent.canAccess(this.position.add(CardinalDirection.WEST))) this.layout.set(5, 3, Tile.DOOR_VERTICAL);

        Grid<StyledCharacter> gridToRender = this.layout.map(Tile::getAppearance);
        for (Entity entity : this.entities) gridToRender.paste(entity instanceof PlayerEntity ? new Vec2i(2, 3) : new Vec2i(2, 1), entity.render());

        return gridToRender;
    }

    protected Grid<Tile> getLayout() {
        return layout;
    }

    protected void setLayout(Grid<Tile> layout) {
        this.layout = layout;
    }

    protected boolean isCleared() {
        return isCleared;
    }

    protected void setCleared(boolean cleared) {
        isCleared = cleared;
    }

    protected World getParent() {
        return parent;
    }

    protected void setParent(World parent) {
        this.parent = parent;
    }

    protected List<Entity> getEntities() {
        return entities;
    }

    protected void setEntities(List<Entity> entities) {
        this.entities = entities;
    }

    protected Vec2i getPosition() {
        return position;
    }

    protected void setPosition(Vec2i position) {
        this.position = position;
    }

    protected boolean isLocked() {
        return isLocked;
    }

    protected void setLocked(boolean locked) {
        isLocked = locked;
    }
}