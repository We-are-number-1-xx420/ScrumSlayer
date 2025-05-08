package io.github.wearenumberone.scrumslayer.mapping;

import io.github.wearenumberone.scrumslayer.entities.MonsterEntity;
import io.github.wearenumberone.scrumslayer.render.StyledCharacter;
import io.github.wearenumberone.scrumslayer.render.Renderable;
import io.github.wearenumberone.scrumslayer.util.CardinalDirection;
import io.github.wearenumberone.scrumslayer.util.Grid;
import io.github.wearenumberone.scrumslayer.util.Vec2i;

import java.util.HashMap;
import java.util.Map;

public class World implements Renderable {
    private Grid<Room> roomGrid;
    private Room currentRoom;

    public void setRoom(Room room){
        this.currentRoom = room;
    }

    public Grid<StyledCharacter> render(){
        return this.currentRoom.render();
    }

    public void tryAnswer(String input){
        if (!this.currentRoom.hasMonster() && this.currentRoom.getQuestion() != null){
            if (this.currentRoom.getQuestion().isCorrectAnswer(Integer.parseInt(input))) {
                this.currentRoom.setQuestion(null);
            } else {
                this.currentRoom.addEntity(new MonsterEntity(this.currentRoom.getQuestion()));
            }
        }
    }

    public void tryMove(String input){
        Vec2i oldPos = roomGrid.find(this.currentRoom);

        Map<CardinalDirection, Boolean> canMoveMap = this.canMove(oldPos);

        Vec2i targetOffset = switch (input.toLowerCase()){
            case "w" -> CardinalDirection.NORTH;
            case "a" -> CardinalDirection.WEST;
            case "s" -> CardinalDirection.SOUTH;
            case "d" -> CardinalDirection.EAST;
            default -> Vec2i.ZERO;
        };

        if (Boolean.FALSE.equals(canMoveMap.getOrDefault(targetOffset, false))) return; // TODO: Print failiure?
        Vec2i newPos = oldPos.add(targetOffset);

        if(this.roomGrid.exists(newPos) && !this.roomGrid.get(newPos).isLocked()){
            this.setCurrentRoom(roomGrid.get(newPos));
            this.currentRoom.render();
        }
    }

    public Map<CardinalDirection, Boolean> canMove(Vec2i position){
        Map<CardinalDirection, Boolean> result = new HashMap<>();
        for (CardinalDirection direction : CardinalDirection.all()) result.put(direction, roomGrid.exists(position.add(direction)));
        return result;
    }

    public Grid<Room> getRoomGrid() {
        return roomGrid;
    }

    public void setRoomGrid(Grid<Room> roomGrid) {
        this.roomGrid = roomGrid;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }
}