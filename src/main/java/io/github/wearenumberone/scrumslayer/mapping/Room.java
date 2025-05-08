package io.github.wearenumberone.scrumslayer.mapping;

import io.github.wearenumberone.scrumslayer.entities.PlayerEntity;
import io.github.wearenumberone.scrumslayer.render.Renderable;
import io.github.wearenumberone.scrumslayer.render.StyledCharacter;
import io.github.wearenumberone.scrumslayer.util.Grid;
import io.github.wearenumberone.scrumslayer.entities.Entity;
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
    private String question;
    private int answer;
    private boolean cleared = false;
    private boolean locked;
    private Set<Entity> entities = new HashSet<>();
    //private Item unlockable;
    public Room(World world){
        this.controller = world;
    }

    public void activateEnemy(){
        System.out.println("You've activated my trap card!");
        System.out.println("The enemy has stolen one of your lives. Try the question again.");
        for(var entry : entities) if(entry instanceof PlayerEntity) {
            ((PlayerEntity) entry).setHitPoints(((PlayerEntity) entry).getHitPoints() - 1);
        }

        // TODO: call question asking method?
    }

    public Grid<StyledCharacter> render() {
        Grid<StyledCharacter> renderedGrid = this.layout.map(Tile::getAppearance);

        for (Entity entity : this.entities) renderedGrid.paste(entity.getPosition(), entity.render());

        return renderedGrid;
    }

    public Grid<Tile> getLayout() {
        return layout;
    }

    public void setLayout(Grid<Tile> layout) {
        this.layout = layout;
    }

    public String getQuestion() {
        return this.question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getAnswer() {
        return this.answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public boolean isCleared() {
        return this.cleared;
    }

    public void setCleared(boolean cleared) {
        this.cleared = cleared;
    }

    public boolean isLocked() {
        return this.locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public World getController() {
        return this.controller;
    }
}