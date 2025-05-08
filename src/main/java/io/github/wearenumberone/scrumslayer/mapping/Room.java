package io.github.wearenumberone.scrumslayer.mapping;

import io.github.wearenumberone.scrumslayer.util.Grid;
import io.github.wearenumberone.scrumslayer.util.Vec2i;

public class Room {
    // ▢▢▥▨▬▩▦▣▤▧
    Tile [] [] map = {
            {Tile.WALL, Tile.WALL, Tile.WALL, Tile.WALL, Tile.WALL},
            {Tile.WALL, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.WALL},
            {Tile.WALL, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.WALL},
            {Tile.WALL, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.WALL},
            {Tile.WALL, Tile.WALL, Tile.WALL, Tile.WALL, Tile.WALL}
    };
    private World controller;
    private Vec2i position;
    private Grid<Tile> layout = new Grid<>(map);
    private String question;
    private int answer;
    private boolean cleared = false;
    private boolean locked;
    //private List<Character> entity;
    //private Item unlockable;


    public Room(World world){
        this.controller = world;
    }

    public void activateEnemy(){
        System.out.println("You've activated my trap card!");
        System.out.println("The enemy has stolen one of your lives. Try the question again.");
        // todo: playerStatus -1?
        // todo: call question asking method?
    }

    public String render() {
        StringBuilder builder = new StringBuilder();
        for (int j = 0; j < layout.getHeight(); j++){
            for (int i = 0; i < layout.getWidth(); i++) {
                builder.append(layout.get(i, j));
            }
            builder.append("\n");
        }
        //builder.append("\n" + this.question);
        return builder.toString();
    }

    public Tile[][] getMap() {
        return map;
    }

    public void setMap(Tile[][] map) {
        this.map = map;
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

    public void setController(World controller) {
        this.controller = controller;
    }

    public Vec2i getPosition() {
        return position;
    }

    public void setPosition(Vec2i position) {
        this.position = position;
    }
}