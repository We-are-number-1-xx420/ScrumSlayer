package io.github.wearenumberone.scrumslayer.mapping;

import io.github.wearenumberone.scrumslayer.render.Renderable;
import io.github.wearenumberone.scrumslayer.render.StyledCharacter;
import io.github.wearenumberone.scrumslayer.util.CardinalDirection;
import io.github.wearenumberone.scrumslayer.util.Grid;
import io.github.wearenumberone.scrumslayer.util.Vec2i;

import java.util.HashMap;
import java.util.Map;

public class World implements Renderable {
    private Grid<Room> roomGrid;
    private Room currentRoom;
    private boolean movementStatus = false;

    public Grid<StyledCharacter> render(){
        return this.currentRoom.render();

    }

    public void tryAnswer(String input){
        if ((this.currentRoom instanceof QuestionRoom) && (!this.currentRoom.isCleared)) {
            if(((QuestionRoom) this.currentRoom).tryAnswer(input)){
                this.currentRoom.setCleared(true);
            }else{
                //ToDo: monster pop up saying you got it wrong?
                ((QuestionRoom) this.currentRoom).summonMonster();
            }
        }
    }

    public void tryMove(String input){
        Vec2i oldPos = roomGrid.find(this.currentRoom);

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
            this.currentRoom.render();
        }
    }

    public void setMovementStatus(boolean movementStatus) {
        this.movementStatus =  movementStatus;
    }
    public boolean getMovementStatus() {
        return this.movementStatus;
    }

    public boolean canAccess(Vec2i position) {
        return roomGrid.exists(position) && this.currentRoom.isCleared;
    }

    public Grid<Room> getRoomGrid() {
        return this.roomGrid;
    }

    public void setRoomGrid(Grid<Room> roomGrid) {
        this.roomGrid = roomGrid;
    }

    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }
}