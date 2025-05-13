package io.github.wearenumberone.scrumslayer.mapping;

import io.github.wearenumberone.scrumslayer.render.Renderable;
import io.github.wearenumberone.scrumslayer.render.StyledCharacter;
import io.github.wearenumberone.scrumslayer.util.CardinalDirection;
import io.github.wearenumberone.scrumslayer.util.Grid;
import io.github.wearenumberone.scrumslayer.util.Vec2i;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class World implements Renderable {
    private Grid<Room> roomGrid;
    private Vec2i currentPosition;
    private boolean movementStatus = false;

    public Grid<StyledCharacter> render(){
        return this.getCurrentRoom().render();
    }

    public void tryAnswer(String input) {
        if (this.getCurrentRoom() instanceof QuestionRoom room && !room.isCleared()) {
            if (room.tryAnswer(input)) {
                room.setCleared(true);
            } else {
                //TODO: monster pop up saying you got it wrong?
                room.summonMonster();
            }
        }
    }

    public void tryMove(String input){
        Vec2i oldPos = roomGrid.find(this.getCurrentRoom());

        Map<CardinalDirection, Boolean> canMoveMap = new HashMap<>();
        if (this.canAccess(oldPos.add(CardinalDirection.NORTH))) canMoveMap.put(CardinalDirection.NORTH, true);
        if (this.canAccess(oldPos.add(CardinalDirection.EAST))) canMoveMap.put(CardinalDirection.EAST, true);
        if (this.canAccess(oldPos.add(CardinalDirection.SOUTH))) canMoveMap.put(CardinalDirection.SOUTH, true);
        if (this.canAccess(oldPos.add(CardinalDirection.WEST))) canMoveMap.put(CardinalDirection.WEST, true);

        Vec2i targetOffset = switch (input.toLowerCase()){
            case "w" -> CardinalDirection.NORTH;
            case "a" -> CardinalDirection.WEST;
            case "s" -> CardinalDirection.SOUTH;
            case "d" -> CardinalDirection.EAST;
            default -> Vec2i.ZERO;
        };

        if (Boolean.FALSE.equals(canMoveMap.getOrDefault(targetOffset, false))) {
            System.out.println("You can't move that way!");
            return;
        }
        Vec2i newPos = oldPos.add(targetOffset);

        if(this.roomGrid.exists(newPos) && !this.roomGrid.get(newPos).isLocked() && !movementStatus){
            this.setCurrentRoom(roomGrid.get(newPos));
            this.getCurrentRoom().render();
        }
    }

    public void setMovementStatus(boolean movementStatus) {
        this.movementStatus =  movementStatus;
    }
    public boolean getMovementStatus() {
        return this.movementStatus;
    }

    public boolean canAccess(Vec2i position) {
        return roomGrid.exists(position) && this.getCurrentRoom().isCleared;
    }

    public Grid<Room> getRoomGrid() {
        return this.roomGrid;
    }

    public void setRoomGrid(Grid<Room> roomGrid) {
        this.roomGrid = roomGrid;
    }

    public Room getCurrentRoom() {
        return this.roomGrid.get(this.currentPosition);
    }

    public void setCurrentPosition(Vec2i position) {
        if (!this.roomGrid.isInBounds(position)) throw new ArrayIndexOutOfBoundsException(String.format("Position %s is out of bounds", position));
        if (!this.roomGrid.exists(position)) throw new NullPointerException(String.format("There is no Room at position %s", position));
        this.currentPosition = position;
    }

    public void setCurrentRoom(Room room) {
        Vec2i position = this.roomGrid.find(room);
        if (position == null) throw new NoSuchElementException("This Room is not present in this World");
        this.setCurrentPosition(position);
    }
}