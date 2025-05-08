package io.github.wearenumberone.scrumslayer.mapping;

import io.github.wearenumberone.scrumslayer.Question;
import io.github.wearenumberone.scrumslayer.entities.MonsterEntity;
import io.github.wearenumberone.scrumslayer.entities.PlayerEntity;
import io.github.wearenumberone.scrumslayer.item.KeyItem;
import io.github.wearenumberone.scrumslayer.render.Renderable;
import io.github.wearenumberone.scrumslayer.render.StyledCharacter;
import io.github.wearenumberone.scrumslayer.util.Grid;
import io.github.wearenumberone.scrumslayer.entities.Entity;
import io.github.wearenumberone.scrumslayer.util.Vec2i;

import java.util.*;

public class Room implements Renderable {
    private final World controller;
    private Grid<Tile> layout = new Grid<>(new Tile[][] {
       {Tile.WALL, Tile.WALL, Tile.WALL, Tile.WALL, Tile.WALL},
       {Tile.WALL, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.WALL},
       {Tile.WALL, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.WALL},
       {Tile.WALL, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.WALL},
       {Tile.WALL, Tile.WALL, Tile.WALL, Tile.WALL, Tile.WALL}
    });
    private KeyItem lockedKey;
    private Question question;
    private List<Entity> entities = new ArrayList<>();

    public Room(World world){
        this.controller = world;
    }

    public Grid<StyledCharacter> render() {
        this.entities.removeIf(Entity::isDead);

        Grid<StyledCharacter> renderedGrid = this.layout.map(Tile::getAppearance);

        for (Entity entity : this.entities) renderedGrid.paste(entity instanceof PlayerEntity ? new Vec2i(2, 3) : new Vec2i(2, 1), entity.render());

        return renderedGrid;
    }

    public boolean hasMonster(){
        for(var entry : this.entities){
            if(entry instanceof MonsterEntity) return true;
        }
        return false;
    }

    public Question getQuestion() {
        return question;
    }

    public Room setQuestion(Question question) {
        this.question = question;
        return this;
    }

    public Grid<Tile> getLayout() {
        return layout;
    }

    public void setLayout(Grid<Tile> layout) {
        this.layout = layout;
    }

    public KeyItem getLockedKey() {
        return lockedKey;
    }

    public void setLockedKey(KeyItem lockedKey) {
        this.lockedKey = lockedKey;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }

    public boolean isLocked() {
        return this.lockedKey != null;
    }

    public void setLocked(KeyItem key) {
        this.lockedKey = key;
    }

    public World getController() {
        return this.controller;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public Room addEntity(Entity entity) {
        this.entities.add(entity);
        return this;
    }

    public void removeEntity(Entity entity) {
        this.entities.remove(entity);
    }
}