package io.github.wearenumberone.scrumslayer.mapping;

import io.github.wearenumberone.scrumslayer.render.StyledCharacter;
import io.github.wearenumberone.scrumslayer.render.Renderable;
import io.github.wearenumberone.scrumslayer.util.Grid;
import io.github.wearenumberone.scrumslayer.util.Vec2i;

public class World implements Renderable {
    private Grid<Room> roomGrid;
    private Room currentRoom;
    private String input;

    public void updateRoom(Room room){
        this.currentRoom = room;
    }

    public Grid<StyledCharacter> render(){
        return this.currentRoom.render();
    }

    public void setInput(String input){
        this.input = input;
        processInput();
    }

    private void processInput(){
        String regex = "\\d+";
        if(this.input.matches(regex)){
            this.tryAnswer();
        }else{
            this.tryMove();
        }
    }

    public void tryAnswer(){
        if (this.currentRoom.isCleared()){
            if (this.currentRoom.getAnswer() == Integer.parseInt(this.input)) {
                this.currentRoom.setCleared(true);
            }else{
                this.currentRoom.activateEnemy();
            }
        }
    }

    public void tryMove(){
        int posX = this.currentRoom.getPosition().getX();
        int posY = this.currentRoom.getPosition().getY();

        switch (this.input.toLowerCase()){
            case "w" -> posY += 1;
            case "a" -> posX -= 1;
            case "s" -> posY -= 1;
            case "d" -> posX += 1;
            default -> System.err.println("Invalid input");
        }

        if(this.roomGrid.exists(posX, posY) && !this.roomGrid.get(posX, posY).isLocked()){
            this.setCurrentRoom(roomGrid.get(posX, posY));
            this.currentRoom.render();
        }
    }

    public boolean [] canMove(Room room){
        Vec2i pos = room.getPosition();
        boolean [] boolArray = new boolean[4];
        boolArray[0] = roomGrid.exists(pos.getX(), pos.getY() + 1);
        boolArray[1] = roomGrid.exists(pos.getX(), pos.getY() -1);
        boolArray[2] = roomGrid.exists(pos.getX() + 1, pos.getY());
        boolArray[3] = roomGrid.exists(pos.getX() - 1, pos.getY());
        return boolArray;
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

    public String getInput() {
        return input;
    }
}