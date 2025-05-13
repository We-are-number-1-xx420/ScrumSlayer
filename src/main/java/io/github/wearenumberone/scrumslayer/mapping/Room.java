package io.github.wearenumberone.scrumslayer.mapping;

import io.github.wearenumberone.scrumslayer.entities.Entity;
import io.github.wearenumberone.scrumslayer.events.Event;
import io.github.wearenumberone.scrumslayer.events.EventType;
import io.github.wearenumberone.scrumslayer.render.Renderable;
import io.github.wearenumberone.scrumslayer.render.StyledCharacter;
import io.github.wearenumberone.scrumslayer.util.*;

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
    protected Event event;
    protected List<EventType> eventTypes = new ArrayList<>();

    protected Room(World parent) {
        this.parent = parent;
    }

    public void updateRender() {
        for (CardinalDirection direction : CardinalDirection.all())
            this.layout.set(new Vec2i(2, 2).add(direction.multiply(2)), direction.getX() == 0 ? Tile.DOOR_VERTICAL : Tile.DOOR_HORIZONTAL);

        for (EventType events : eventTypes){
            this.event = new Event(events, this);
            this.event.executeEvent();
        }
    }
    public Grid<StyledCharacter> render(){
        this.updateRender();
        return this.layout.map(Tile::getAppearance);
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
    protected void addEntity(Entity entity) {
        this.entities.add(entity);
    }
    protected void removeEntity(Entity entity){
        this.entities.remove(entity);
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