package io.github.wearenumberone.scrumslayer.mapping;

import io.github.wearenumberone.scrumslayer.util.Grid;
import io.github.wearenumberone.scrumslayer.util.Vec2i;

public class World {
    private Grid<Room> roomList;
    private Room currentRoom;
    private String input;

    public void updateRoom(Room room){
        this.currentRoom = room;
    }

    public void setInput(String input){
        this.input = input;
        processInput();
    }

    public void processInput(){
        String regex = "\\d+";
        if(this.input.matches(regex)){
            this.tryAnswer();
        }else{
            this.tryMove();
        }
    }

    public void tryAnswer(){
        if(this.currentRoom.getAnswer() == Integer.parseInt(this.input)){
            this.currentRoom.setCleared(true);
        }else{
            this.currentRoom.activateEnemy();
        }
    }

    public void tryMove(){

    }

    public boolean [] canMove(Room room){
        Vec2i pos = room.getPosition();
        boolean [] boolArray = new boolean[4];
        boolArray[0] = roomList.exists(pos.getX(), pos.getY() + 1);
        boolArray[1] = roomList.exists(pos.getX(), pos.getY() -1);
        boolArray[2] = roomList.exists(pos.getX() + 1, pos.getY());
        boolArray[3] = roomList.exists(pos.getX() - 1, pos.getY());
        return boolArray;
    }


}